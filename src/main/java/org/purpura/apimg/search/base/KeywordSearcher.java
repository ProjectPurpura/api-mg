package org.purpura.apimg.search.base;

import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class KeywordSearcher<T> {
    private @Setter SearchOptions options;
    private final Class<T> clazz;
    
    
    public KeywordSearcher(Class<T> clazz) {
        this.clazz = clazz;
        this.setOptions(SearchOptions.builder().build());
    }


    public List<T> search(List<T> list, KeywordList keywords) {
        return list.stream()
                .filter(model -> matchObject(model, keywords))
                .toList();
    }
    public List<T> search(List<T> list, String keywords) throws IllegalArgumentException {
        KeywordList keywordList = KeywordList.from(keywords);

        return search(list, keywordList);
    }

    protected String getClassName() {
        return clazz.getSimpleName();
    }
    
    private boolean isExcludeField(String fieldName) {
        if (options == null) return false;
        if (options.getExcludeFields() == null) return false;

        return options.getExcludeFields().contains(fieldName);
    }

    private boolean isIgnoreChildren() {
        if (options == null) return false;
        return options.isIgnoreChildren();
    }


    /**
     * Usa reflexão para obter os valores dos campos e verifica se eles estão contidos na lista de palavras-chave, 
     * independentemente de maiúsculas ou minúsculas
     */
    private boolean matchObject(T model, KeywordList keywordList) {
        for (java.lang.reflect.Field field : model.getClass().getDeclaredFields()) {
            if (!isExcludeField(field.getName())) {
                field.setAccessible(true);
                try {
                    Object value = field.get(model);
                    
                    if (value == null) continue;
                    
                    // Check if field is a List
                    if (value instanceof List<?> list && !list.isEmpty() && !isIgnoreChildren()) {
                        if (matchOnList(keywordList, field, list)) return true;
                    } else {
                        if (matchField(keywordList, value)) return true;
                    }

                } catch (IllegalAccessException e) {
                    continue;
                } catch (Exception e) {
                    // Handle reflection exceptions gracefully
                    continue;
                }
            }
        }
        
        return false;
    }

    private static boolean matchField(KeywordList keywordList, Object value) {
        // Original string matching logic
        String stringValue = value.toString().toLowerCase().trim();
        return Arrays.stream(keywordList.keywords())
                .anyMatch(keyword -> stringValue.contains(keyword.toLowerCase().trim()));
    }

    @SuppressWarnings("unchecked")
    private boolean matchOnList(KeywordList keywordList, Field field, List<?> list) {
        // Get the generic type of the List
        java.lang.reflect.ParameterizedType paramType =
            (java.lang.reflect.ParameterizedType) field.getGenericType();
        Class<?> listElementType = (Class<?>) paramType.getActualTypeArguments()[0];

        // Create child KeywordSearcher for the List element type
        KeywordSearcher<Object> childSearcher = createChildSearcher(listElementType);

        // Search within the List using the child searcher
        List<Object> typedList = (List<Object>)list;
        List<Object> matches = childSearcher.search(typedList, keywordList);

        return !matches.isEmpty();
    }

    /**
     * Creates a child KeywordSearcher for the specified class type
     */
    @SuppressWarnings("unchecked")
    private KeywordSearcher<Object> createChildSearcher(Class<?> elementType) {
        return new KeywordSearcher<>((Class<Object>) elementType);
    }
}

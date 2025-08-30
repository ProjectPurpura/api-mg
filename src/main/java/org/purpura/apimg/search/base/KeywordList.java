package org.purpura.apimg.search.base;

public record KeywordList(String[] keywords) {

    public static final String keywordDividePattern = "[,\\s]";

    public static KeywordList from(String list) throws IllegalArgumentException {
        if (list == null || list.isBlank()) {throw new IllegalArgumentException("Keyword list cannot be null or blank");}

        // split string by comma and spaces
        String[] keywords = list.split(keywordDividePattern);
        return new KeywordList(keywords);
    }
}

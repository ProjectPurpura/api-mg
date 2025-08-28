package org.purpura.apimg.search.base;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class KeywordSearcherOptions {
    private List<String> excludeFields;
    private boolean ignoreChildren;
}

package org.purpura.apimg.search.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchOptions {
    private List<String> excludeFields;
    private boolean ignoreChildren;
}

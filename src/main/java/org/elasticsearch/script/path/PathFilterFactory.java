package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;
import java.util.Map;

public class PathFilterFactory implements NativeScriptFactory {

    @Override
    public PathFilter newScript(@Nullable Map<String,Object> params) {
        String field = ((String) params.get("field"));
        String path = ((String) params.get("path"));

        PathFilter PathFilter = new PathFilter(field, path);

        if (params.containsKey("minLevel")) {
            Integer minLevel = Integer.parseInt(params.get("minLevel").toString());
            PathFilter.setMinLevel(minLevel);
        }
        if (params.containsKey("maxLevel")) {
            Integer maxLevel = Integer.parseInt(params.get("maxLevel").toString());
            PathFilter.setMaxLevel(maxLevel);
        }
        return PathFilter;
    }
}

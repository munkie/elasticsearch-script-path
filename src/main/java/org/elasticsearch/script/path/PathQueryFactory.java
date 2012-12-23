package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Map;

public class PathQueryFactory implements NativeScriptFactory {

    @Override
    public PathQuery newScript(@Nullable Map<String,Object> params) {
        String field = ((String) params.get("field"));
        String path = ((String) params.get("path"));
        return new PathQuery(field, path);
    }
}

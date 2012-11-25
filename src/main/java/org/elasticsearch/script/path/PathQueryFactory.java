package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Map;

public class PathQueryFactory implements NativeScriptFactory {

    @Override
    public ExecutableScript newScript(@Nullable Map<String,Object> params) {
        return new PathQuery(params);
    }
}

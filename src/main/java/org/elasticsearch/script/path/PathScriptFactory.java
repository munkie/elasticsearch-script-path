package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Map;

abstract public class PathScriptFactory implements NativeScriptFactory {

    protected Map<String,Object> params;

    @Override
    public PathScript newScript(@Nullable Map<String,Object> params) {

        this.params = params;

        String field = ((String) params.get("field"));
        String path = ((String) params.get("path"));

        Boolean direct = parseDirect();

        return createScript(field, path, direct);
    }

    protected Boolean parseDirect() {
        if (params.containsKey("direct")) {
            Object directParam = params.get("direct");
            if (directParam instanceof Boolean) {
                return (Boolean) directParam;
            } else if (directParam instanceof String) {
                return (!directParam.equals("false") && !directParam.equals("off") && !directParam.equals("0"));
            } else if (directParam instanceof Integer) {
                return !directParam.equals(0);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    abstract protected PathScript createScript(String field, String path, Boolean direct);
}

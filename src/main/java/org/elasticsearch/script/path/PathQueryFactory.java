package org.elasticsearch.script.path;

public class PathQueryFactory extends PathScriptFactory {

    protected PathScript createScript(String field, String path, Boolean direct) {
        return new PathQuery(field, path, direct);
    }
}

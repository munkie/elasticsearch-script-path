package org.elasticsearch.script.path;

public class PathFilterFactory extends PathScriptFactory {

    void setLevels(PathFilter pathFilter) {
        if (params.containsKey("minLevel")) {
            Integer minLevel = Integer.parseInt(params.get("minLevel").toString());
            pathFilter.setMinLevel(minLevel);
        }
        if (params.containsKey("maxLevel")) {
            Integer maxLevel = Integer.parseInt(params.get("maxLevel").toString());
            pathFilter.setMaxLevel(maxLevel);
        }
    }

    protected PathFilter createScript(String field, String path, Boolean direct) {
        PathFilter pathFilter = new PathFilter(field, path, direct);
        setLevels(pathFilter);
        return pathFilter;
    }
}

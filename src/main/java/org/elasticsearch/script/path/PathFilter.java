package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;

import java.util.Map;

public class PathFilter extends PathScript {

    protected Integer minLevel = 0;
    protected Integer maxLevel = Integer.MAX_VALUE;

    public PathFilter(String field, String path) {
        super(field, path);
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    @Override
    public Object run() {

        for (Integer step : getSteps()) {
            if (step >= minLevel && step <= maxLevel) {
                return true;
            }
        }
        return false;
    }
}

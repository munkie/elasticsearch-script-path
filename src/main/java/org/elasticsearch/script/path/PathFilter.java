package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class PathFilter extends PathScript {

    protected Integer minLevel = 0;
    protected Integer maxLevel = Integer.MAX_VALUE;

    public PathFilter(@Nullable Map<String,Object> params) {
        super(params);
        setLevels(params);
    }

    protected void setLevels(Map<String,Object> params) {
        if (params.containsKey("minLevel")) {
            minLevel = Integer.parseInt(params.get("minLevel").toString());
        }
        if (params.containsKey("maxLevel")) {
            maxLevel = Integer.parseInt(params.get("maxLevel").toString());
        }
        logger.info(String.format("min: %d, max: %d", minLevel, maxLevel));
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

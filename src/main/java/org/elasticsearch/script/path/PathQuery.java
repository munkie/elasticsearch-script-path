package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class PathQuery extends PathScript {

    public PathQuery(String field, String path) {
        super(field, path);
    }

    @Override
    public Object run() {

        Integer minStep = Integer.MAX_VALUE;
        for (Integer step : getSteps()) {
            minStep = Math.min(step, minStep);
        }

        if (minStep.equals(Integer.MAX_VALUE)) {
            return 0;
        } else {
            return 1 / ((double) minStep + 1);
        }
    }
}

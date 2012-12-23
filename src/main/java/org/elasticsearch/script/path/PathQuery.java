package org.elasticsearch.script.path;

public class PathQuery extends PathScript {

    public PathQuery(String field, String path, Boolean direct) {
        super(field, path, direct);
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

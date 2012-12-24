package org.elasticsearch.script.path;

public class PathQuery extends PathScript {

    public PathQuery(String field, String path, Boolean direct) {
        super(field, path, direct);
    }

    @Override
    public Object run() {

        Integer minSteps = NOT_FOUND;
        for (Integer steps : getSteps()) {
            minSteps = Math.min(steps, minSteps);
        }

        if (minSteps.equals(NOT_FOUND)) {
            return 0;
        } else {
            return 1 / ((double) minSteps + 1);
        }
    }
}

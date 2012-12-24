package org.elasticsearch.script.path;

public class PathFilter extends PathScript {

    protected Integer minLevel = 0;
    protected Integer maxLevel = Integer.MAX_VALUE;

    public PathFilter(String field, String path, Boolean direct) {
        super(field, path, direct);
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    @Override
    public Object run() {

        for (Integer steps : getSteps()) {
            if (!steps.equals(NOT_FOUND) && steps >= minLevel && steps <= maxLevel) {
                return true;
            }
        }
        return false;
    }
}

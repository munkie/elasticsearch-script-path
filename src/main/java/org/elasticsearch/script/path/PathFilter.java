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

        for (Integer step : getSteps()) {
            if (step >= minLevel && step <= maxLevel) {
                return true;
            }
        }
        return false;
    }
}

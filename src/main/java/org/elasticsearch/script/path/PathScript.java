package org.elasticsearch.script.path;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.index.fielddata.ScriptDocValues;
import org.elasticsearch.script.AbstractSearchScript;

import java.util.ArrayList;

abstract public class PathScript extends AbstractSearchScript {

    final static public Integer NOT_FOUND = Integer.MAX_VALUE;

    protected String path;
    protected String[] pathNodes;
    protected String field;
    protected Boolean direct;

    protected ESLogger logger;

    public PathScript(String field, String path, Boolean direct) {
        logger = Loggers.getLogger(getClass());

        this.field = field;
        this.path = path;
        this.direct = direct;

        pathNodes = parsePath(path);
    }

    protected ArrayList<Integer> getSteps() {
        ScriptDocValues.Strings doc = docFieldStrings(field);

        ArrayList<Integer> steps = new ArrayList<Integer>();

        if (!doc.isEmpty()) {
            for (String docPath : doc.getValues()) {
                String[] docPathNodes = parsePath(docPath);
                steps.add(comparePaths(docPathNodes, pathNodes));
            }
        }

        return steps;
    }

    protected String[] parsePath(String path) {
        return path.split("\\.");
    }

    protected Integer comparePaths(String[] docPathNodes, String[] pathNodes) {
        int minLength = Math.min(docPathNodes.length, pathNodes.length);
        int i = 0;
        while (i < minLength && pathNodes[i].equals(docPathNodes[i])) {
            ++i;
        }
        // No common node
        if (0 == i) {
            return NOT_FOUND;
        // Path is not parent
        } else if (direct && i < minLength) {
            return NOT_FOUND;
        // Doc path is deeper then query path
        } else if (direct && docPathNodes.length > pathNodes.length) {
            return NOT_FOUND;
        } else if (direct) {
            return pathNodes.length - docPathNodes.length;
        } else {
            return docPathNodes.length + pathNodes.length - 2 * i;
        }
    }
}

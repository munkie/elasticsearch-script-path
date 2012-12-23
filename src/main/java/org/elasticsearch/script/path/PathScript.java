package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.script.AbstractSearchScript;
import org.elasticsearch.index.field.data.strings.StringDocFieldData;

import java.util.ArrayList;
import java.util.Map;

abstract public class PathScript extends AbstractSearchScript {

    protected String path;
    protected String[] pathNodes;
    protected String field;

    protected ESLogger logger;

    public PathScript(String field, String path) {
        logger = Loggers.getLogger(getClass());

        this.field = field;
        this.path = path;

        pathNodes = parsePath(path);
    }

    protected ArrayList<Integer> getSteps() {
        StringDocFieldData doc = doc().field(field);

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

    protected Integer comparePaths(String[] pathA, String[] pathB) {
        int minLength = Math.min(pathA.length, pathB.length);
        int i = 0;
        while (i < minLength && pathB[i].equals(pathA[i])) {
            ++i;
        }
        if (0 == i) {
            return Integer.MAX_VALUE;
        } else {
            return pathA.length + pathB.length - 2 * i;
        }
    }
}

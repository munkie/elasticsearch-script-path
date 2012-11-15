package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.script.AbstractDoubleSearchScript;
import org.elasticsearch.index.field.data.strings.StringDocFieldData;

import java.util.Map;

public class PathScript extends AbstractDoubleSearchScript {

    protected String path;
    protected String[] pathNodes;
    protected String field;

    protected ESLogger logger;

    public PathScript(@Nullable Map<String,Object> params) {
        logger = Loggers.getLogger(getClass());

        field = ((String) params.get("field"));
        path = ((String) params.get("path"));
        pathNodes = parsePath(path);
    }

    @Override
    public double runAsDouble() {
        StringDocFieldData doc = doc().field(field);
        if (doc.isEmpty()) {
            return 0;
        }

        String[] docPaths = doc.getValues();
        Integer steps = Integer.MAX_VALUE;
        for (String docPath : docPaths) {
            String[] docPathNodes = parsePath(docPath);
            steps = Math.min(comparePaths(docPathNodes, pathNodes), steps);
        }
        if (steps.equals(Integer.MAX_VALUE)) {
            return 0;
        } else {
            return 1 / ((double) steps + 1);
        }
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

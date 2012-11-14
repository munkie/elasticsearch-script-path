package org.elasticsearch.script.path;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.AbstractFloatSearchScript;
import org.elasticsearch.index.field.data.strings.StringDocFieldData;
import org.elasticsearch.script.ScriptException;

import java.util.Map;

public class PathScript extends AbstractFloatSearchScript {

    protected String path;
    protected String field;

    public PathScript(@Nullable Map<String,Object> params) {
        path = ((String) params.get("path"));
        field = ((String) params.get("field"));
    }

    @Override
    public float runAsFloat() {
        StringDocFieldData doc = doc().field(field);

        if (doc.isEmpty()) {
            return 0;
        }

        String[] paths = doc.getValues();
        Integer minScore = Integer.MAX_VALUE;
        for (int i = 0; i < paths.length; i++) {
            minScore = Math.min(comparePaths(paths[i], path), minScore);
        }
        if (minScore == Integer.MAX_VALUE) {
            return 0;
        } else {
            return 1 / (minScore + 1);
        }
    }

    protected Integer[] parsePath(String path) throws ScriptException {
        String[] split = path.split(".");
        Integer[] results = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                results[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException e) {
                String msg = String.format("Non integer value in path: %s at position %d", path, i);
                throw new ScriptException(msg, e);
            }
        }
        return results;
    }

    protected Integer comparePaths(String pathA, String pathB) {
        Integer[] pathANodes = parsePath(pathA);
        Integer[] pathBNodes = parsePath(pathB);
        return comparePaths(pathANodes, pathBNodes);
    }

    protected Integer comparePaths(Integer[] pathA, Integer[] pathB) {
        int minLength = Math.min(pathA.length, pathB.length);
        for (int i = 0; i < minLength; i++) {
            if (pathA[i] != pathB[i]) {
                return pathA.length + pathA.length - 2 * (i + 1);
            }
        }
        return Integer.MAX_VALUE;
    }
}

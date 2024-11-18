package com.coffeeisoxigen.model.board;

import java.util.HashMap;
import java.util.Map;

public class MapGeneratorFactory {
    private static final Map<String, IMapGenerator> generators = new HashMap<>();

    static {
        generators.put("Default", new MapGeneratorBasic());
        generators.put("Custom", new MapGeneratorCustom());
        // Add more generators as needed
    }

    public static Board createMap(String type, int width, int height) {
        IMapGenerator generator = generators.get(type);
        if (generator != null) {
            return generator.generateMap(width, height);
        } else {
            throw new IllegalArgumentException("Unknown map type: " + type);
        }
    }

    public static int getDefaultWidth(String type) {
        IMapGenerator generator = generators.get(type);
        if (generator != null) {
            return generator.getDefaultWidth();
        } else {
            throw new IllegalArgumentException("Unknown map type: " + type);
        }
    }

    public static int getDefaultHeight(String type) {
        IMapGenerator generator = generators.get(type);
        if (generator != null) {
            return generator.getDefaultHeight();
        } else {
            throw new IllegalArgumentException("Unknown map type: " + type);
        }
    }

    public static String[] getAvailableMapTypes() {
        return generators.keySet().toArray(String[]::new);
    }
}
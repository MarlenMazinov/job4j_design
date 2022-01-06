package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().forEach(line -> {
                if (!line.equals("") && !line.contains("#")) {
                    if (line.indexOf("=") != line.lastIndexOf("=")) {
                        throw new IllegalArgumentException();
                    }
                    String[] arr = line.split("=");
                    if (arr.length < 2 || arr[0].equals("")) {
                        throw new IllegalArgumentException();
                    }
                    values.put(arr[0], arr[1]);
                }
            });
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}
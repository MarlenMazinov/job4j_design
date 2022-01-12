package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    private void parse(String[] args) {
        if (Arrays.asList(args).isEmpty()) {
            throw new IllegalArgumentException("No arguments.");
        }
        for (String arg : args) {
            if (arg != null) {
                String[] arr = patternMatching(arg);
                arr[0] = arr[0].replaceFirst("-", "");
                values.put(arr[0], arr[1]);
            } else {
                break;
            }
        }
    }

    private String[] patternMatching(String arg) {
        String[] arr;
        if (arg.contains("=")) {
            if (arg.indexOf("=") != arg.lastIndexOf("=")) {
                throw new IllegalArgumentException("Argument must contain only one \"=\".");
            }
            if (arg.startsWith("=") || arg.endsWith("=")) {
                throw new IllegalArgumentException("Argument isn't complete.");
            }
            if (arg.contains(" ")) {
                throw new IllegalArgumentException("Argument mustn't contain spaces.");
            }
            arr = arg.split("=");
            if (!arr[0].contains("-")) {
                throw new IllegalArgumentException("First part of argument must start with\"-\".");
            }
        } else {
            throw new IllegalArgumentException("Argument must be separated by \"=\".");
        }
        return arr;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
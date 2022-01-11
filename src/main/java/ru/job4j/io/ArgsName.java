package ru.job4j.io;

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
        for (String arg : args) {
            if (arg != null) {
                String[] key = arg.split("=");
                if (key.length != 2) {
                    throw new IllegalArgumentException();
                }
                key[0] = key[0].replaceFirst("-", "");
                values.put(key[0], key[1]);
            } else {
                break;
            }
        }
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
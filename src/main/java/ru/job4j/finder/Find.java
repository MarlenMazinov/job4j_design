package ru.job4j.finder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

public class Find {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of arguments must be equal to four.");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("First argument must be directory.");
        }
        if (!argsName.get("o").equals("log1.txt")) {
            throw new IllegalArgumentException("Last argument must be log.txt");
        }
        List<Path> rsl = new ArrayList<>();
        if (argsName.get("t").equals("name")) {
            rsl = Search.search(Paths.get(argsName.get("d")),
                    p -> p.toFile().getName().equals(argsName.get("n")));
        }
        if (argsName.get("t").equals("mask")) {
            rsl = Search.search(Paths.get(argsName.get("d")),
                    p -> p.toFile().getName().contains(argsName.get("n").replaceFirst("\\*", "")));
        }
        if (argsName.get("t").equals("regex")) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
            rsl = Search.search(Paths.get(argsName.get("d")),
                    p -> pattern.matches(argsName.get("n"), p.toFile().getName()));
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("o")))) {
            rsl.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

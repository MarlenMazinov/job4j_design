package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    Path root;

    public SearchFiles(Predicate<Path> condition, Path root) {
        this.condition = condition;
        this.root = root;
    }

    List<Path> rsl = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        if (condition.test(path)) {
            rsl.add(path);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return rsl;
    }

}

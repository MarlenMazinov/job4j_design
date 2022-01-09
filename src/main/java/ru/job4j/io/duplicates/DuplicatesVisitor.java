package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> tmpMap = new HashMap<>();
    List<FileProperty> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File currentFile = file.toFile();
        if (currentFile.isFile()) {
            FileProperty fileProperty = new FileProperty(currentFile.length(),
                    currentFile.getName());
            if (tmpMap.containsKey(fileProperty)) {
                List<Path> list = tmpMap.get(fileProperty);
                list.add(file);
                tmpMap.replace(fileProperty, list);
            } else {
                List<Path> newList = new ArrayList<>();
                newList.add(file);
                tmpMap.put(fileProperty, newList);
                list.add(fileProperty);
            }
        }
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getMap() {
        list.forEach(k -> {
            if (tmpMap.containsKey(k)) {
                if (tmpMap.get(k).size() < 2) {
                    tmpMap.remove(k);
                }
            }
        });
        return tmpMap;
    }
}
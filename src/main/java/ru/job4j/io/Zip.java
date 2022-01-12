package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            sources.forEach(file -> {
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(file))) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    zip.write(out.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            throw new IllegalArgumentException("Number of arguments must be equal to three.");
        }
        ArgsName names = ArgsName.of(args);
        if (!Paths.get(names.get("d")).toFile().exists()) {
            throw new IllegalArgumentException("No such directories exist.");
        }
        if (!Paths.get(names.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(names.get("d") + "isn't directory.");
        }

        List<File> list = new ArrayList<>();
        Search.search(Paths.get(names.get("d")),
                        p -> !p.toFile().getName().endsWith(names.get("e"))).
                forEach(p -> list.add(new File(p.toFile().getPath())));
        packFiles(list, new File(names.get("o")));
    }
}
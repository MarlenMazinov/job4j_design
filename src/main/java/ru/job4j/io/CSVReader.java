package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of arguments must be equal to four.");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Paths.get(argsName.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("No such files exist.");
        }
        handle(argsName);
    }

    public static void handle(ArgsName argsName) {
        List<String> stringsList = new ArrayList<>();
        List<List<String>> listOfLists = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter("\n")) {
            while (scanner.hasNext()) {
                stringsList.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < stringsList.size(); i++) {
            Scanner scanner = new Scanner(stringsList.get(i))
                    .useDelimiter(argsName.get("delimiter"));
            listOfLists.add(new ArrayList<>());
            while (scanner.hasNext()) {
                listOfLists.get(i).add(scanner.next());
            }
        }
        String[] filterArr = argsName.get("filter").split(",");
        int[] columnsArr = new int[filterArr.length];
        for (String field : listOfLists.get(0)) {
            for (int i = 0; i < columnsArr.length; i++) {
                if (filterArr[i].equals(field)) {
                    columnsArr[i] = listOfLists.get(0).indexOf(field);
                }
            }
        }
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (List<String> list : listOfLists) {
            StringJoiner lineJoiner = new StringJoiner(argsName.get("delimiter"));
            for (int i : columnsArr) {
                lineJoiner.add(list.get(i));
            }
            joiner.add(lineJoiner.toString());
        }
        joiner.add("");
        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(joiner);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out")))) {
                pw.print(joiner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
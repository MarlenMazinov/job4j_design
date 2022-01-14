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
        if (Paths.get(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException("First argument isn't file.");
        }
        ArgsName argsName = ArgsName.of(args);
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
        int firstIndex = 0;
        int secondIndex = 0;
        String[] filterArr = argsName.get("filter").split(",");
        for (String field : listOfLists.get(0)) {
            if (field.equals(filterArr[0])) {
                firstIndex = listOfLists.get(0).indexOf(field);
            }
            if (field.equals(filterArr[1])) {
                secondIndex = listOfLists.get(0).indexOf(field);
            }
        }
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (List<String> list : listOfLists) {
            joiner.add(list.get(firstIndex)
                    .concat(argsName.get("delimiter")).concat(list.get(secondIndex)));
        }
        joiner.add("");
        if (argsName.get("out").equals("stdout")) {
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
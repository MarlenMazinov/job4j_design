package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            int counter = 0;
            String read;
            while ((read = in.readLine()) != null) {
                if (counter == 0) {
                    if (read.startsWith("400") || read.startsWith("500")) {
                        stringBuilder.append(read.substring(4)).append("; ");
                        counter++;
                    }
                } else {
                    if (!read.startsWith("400") && !read.startsWith("500")) {
                        stringBuilder.append(read.substring(4)).append("\n");
                        counter = 0;
                    }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(stringBuilder);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./analizy/server.log", "./analizy/unavailable.csv");
    }
}
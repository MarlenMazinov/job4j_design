package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String answer;
        while (!OUT.equals(str)) {
            if (STOP.equals(str)) {
                while (!CONTINUE.equals(str)) {
                    log.add(str);
                    str = in.nextLine();
                }
            }
            answer = phrases.get((int) (Math.random() * phrases.size()));
            System.out.println(answer);
            log.add(str);
            log.add(answer);
            str = in.nextLine();
        }
        log.add(str);
        saveLog(log);
        System.exit(0);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}
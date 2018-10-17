package it.sevenbits.formatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Character> readTextFromFile (String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        List<Character> text = new ArrayList<>();

        int character = bufferedReader.read();
        while (character != -1) {
            text.add((char)character);
            character = bufferedReader.read();
        }

        bufferedReader.close();
        return text;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Not enough arguments (expected 1).");
        }

        List<Character> text;

        try {
            text = readTextFromFile(args[0]);
        } catch (IOException e) {
            System.err.println("An instance of IOException was thrown.");
            return;
        }

        Formatter formatter = new Formatter();
        System.out.println(formatter.format(text));
    }
}

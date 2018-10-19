package it.sevenbits.formatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static String readTextFromFile (String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();

        int character = bufferedReader.read();
        while (character != -1) {
            stringBuilder.append((char) character);
            character = bufferedReader.read();
        }

        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Incorrect number of arguments (expected: 1).");
        }

        String text;

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

package it.sevenbits.formatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        List<Character> text = new ArrayList<>();

        int character = bufferedReader.read();
        while (character != -1) {
            text.add((char)character);
            character = bufferedReader.read();
        }

        Formatter formatter = new Formatter();
        System.out.println(formatter.format(text));
    }
}

package by.sem3.kr1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Functional {
    public static void run() {
        try {
            encripting();
            descripting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> encriptList(List<String> list) {
        List<String> encripted = new ArrayList<>();
        for (String item : list) {
            encripted.add(encriptWord(item));
        }
        return encripted;
    }

    private static List<String> descriptList(List<String> list) {
        List<String> descripted = new ArrayList<>();
        for(String item : list) {
            descripted.add(descriptWord(item));
        }
        return descripted;
    }

    private static String encriptWord(String word) {
        StringBuilder sb = new StringBuilder();
        int length = word.length();
        for (int i = 0; i < (length + 1) / 2; i++) {
            sb.append(word.charAt(i));
            if (i != length / 2) {
                sb.append(word.charAt(length - 1 - i));
            }
        }
        sb.append('#');
        return sb.toString();
    }

    private static String descriptWord(String word) {
        StringBuilder sb = new StringBuilder();
        StringBuilder secondPart = new StringBuilder();
        int length = word.length() - 1;
        for(int i = 0; i < length; i += 2) {
            sb.append(word.charAt(i));
            if(i < length - 1) {
                secondPart.append(word.charAt(i + 1));
            }
        }
        sb.append(secondPart.reverse());
        return sb.toString();
    }

    private static List<String> readWords(String filePath) throws IOException {
        Scanner fileScan = new Scanner(new File(filePath));
        Scanner lineScan;
        List<String> words = new ArrayList<>();
        String line;
        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            lineScan = new Scanner(line).useDelimiter("[,. ]+");
            while (lineScan.hasNext()) {
                words.add(lineScan.next());
            }
            lineScan.close();
        }
        fileScan.close();
        return words;
    }

    private static List<String> readEncriptedWords(String filePath) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        List<String> encripted = new ArrayList<>();
        while (sc.hasNextLine()) {
            encripted.add(sc.nextLine());
        }
        return encripted;
    }

    private static void encripting() throws IOException {
        PrintStream ps = new PrintStream("results/output1.txt");
        List<String> words = readWords("resources/input.txt");
        List<String> encriptedWords = encriptList(words);
        for (String item : encriptedWords) {
            ps.println(item);
        }
        ps.close();
    }

    private static void descripting() throws IOException {
        PrintStream ps = new PrintStream("results/output2.txt");
        List<String> encriptedWords = readEncriptedWords("results/output1.txt");
        List<String> words = descriptList(encriptedWords);
        for(String item : words) {
            ps.println(item);
        }
        ps.close();
    }
}

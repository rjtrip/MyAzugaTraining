
/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
package com.main.java;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pipes {
    String answer = null;


    /**
     * This method Verify the input number with head and tail commands.
     */
    public static boolean numberChecker(String storedValue, int num) {
        return num == 0 || storedValue.equals("") || storedValue.equals("Illegal Input");
    }

    /**
     * This method Verify the String with all other commands except head and tail.
     */

    public static boolean stringChecker(String storedValue) {
        if (storedValue.equals("") || storedValue.equals("Illegal Input")) {
            return true;
        }
        return false;
    }


    /**
     * Previous answer becomes input for head with -n.
     */

    public static String head(String storedValue, int num) {

        if (numberChecker(storedValue, num)) {
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedValue.split("\n\r|\n|\r");

        if (num > arr.length)
            num = arr.length;
        for (int j = 0; j < num; j++) {
            if (j != num - 1) {
                data.append(arr[j]).append("\n");
            } else {
                data.append(arr[j]);
            }
        }
        return data.toString();
    }

    /**
     * Previous answer becomes input for tail with -n.
     */

    public static String tail(String storedValue, int num) {

        if (numberChecker(storedValue, num)) {
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] reverse = storedValue.split("\n\r|\n|\r");
        int length = reverse.length;

        if (num > reverse.length)
            num = reverse.length;
        for (int j = length - num; j < length; j++) {
            if (j != length - 1) {
                data.append(reverse[j]).append("\n");
            } else {
                data.append(reverse[j]);
            }
        }
        return data.toString();
    }

    /**
     * Search file in pwd and return contents in form of String.
     */

    public static String cat(String filename) throws IOException {
        String data;
        try {
            String path;
            if (filename == null) {
                path = System.getProperty("user.dir");
            } else {
                path = filename;
            }
            data = Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new IOException("path doen't exists");
        }
        return data;
    }

    /**
     * List files in pwd.
     */
    public static String ls() {
        String path = System.getProperty("user.dir");
        File f = new File(path);
        String[] arr = f.list();

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < Objects.requireNonNull(arr).length; i++) {
            if (arr[i].charAt(0) != '.')
                data.append(arr[i]).append("\n");
        }
        return data.toString();
    }

    /**
     * Number of lines, word count, byte/characters count of answer variable.
     */

    public static String wc(String storedValue) {
        if (stringChecker(storedValue)) {
            return "Illegal Input";
        }
        long wordCount, lineCount;
        String[] arr = storedValue.split("\n\r|\n|\r");
        lineCount = arr.length+1;

        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(storedValue);
        ArrayList<String> array = new ArrayList<>();
        while (m.find()) {
            array.add(m.group());
        }
        wordCount = array.size();

        String data = (lineCount == 0 ? 1 : lineCount - 1) + "\t" + wordCount + "\t" + storedValue.length();
        return data;
    }

    /**
     * Sorts the contents of answer variable, line by line.
     */

    public static String mySort(String storedValue) {
        if (stringChecker(storedValue)) {
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedValue.split("\n\r|\n|\r");

        Arrays.sort(arr, Comparator.comparingInt(str -> str.charAt(0)));

        for (String s : arr) {
            data.append(s).append("\n");
        }
        return data.toString();
    }

    /**
     * Select is used to call respective methods.
     */

    public String select(String str) throws Exception {


        String[] myList = str.split("\\s");

        switch (myList[0]) {
            case "cat": {
                answer = cat(myList[1]);
            }
            break;
            case "head": {
                String storedValue = answer;
                int num = Character.getNumericValue(myList[1].charAt(1));
                answer = head(storedValue, num);
            }
            break;
            case "tail": {
                String storedValue = answer;
                int num = Character.getNumericValue(myList[1].charAt(1));

                answer = tail(storedValue, num);
            }
            break;
            case "ls": {
                answer = ls();
            }
            break;
            case "wc": {
                String storedValue = answer;

                answer = wc(storedValue);
            }
            break;
            case "sort": {
                String storedValue = answer;

                answer = mySort(storedValue);
            }
            break;
            default:
            System.out.println("No such command exists");
        }
        return answer;

    }

    public String pipes(String args) {
        String result = null;
        Pipes pipes = new Pipes();
        try {
            String[] command = args.split("\\|");
            ArrayList<String> myList = new ArrayList<>();
        /* First: Split the input from pipe " | " .
           leftTrim: Remove all the left spaces.
           rightTrim: Remove all right spaces.
           Center: Replace all middle spaces by single space.
        */
            for (String s : command) {
                String leftTrim = s.replaceAll("^\\s+", "");
                String rightTrim = leftTrim.replaceAll("\\s+$", "");
                String center = rightTrim.replaceAll("\\s+", " ");
                myList.add(center);
            }
            // Evaluate all commands one by one.
            int i = 0;
            while (i < myList.size()) {
                result = pipes.select(myList.get(i));
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error occured "+e.getMessage());
        }
        return result;
    }
}
package com.seal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length != 2)
            throw new RuntimeException("Inefficient Argument!. Should be 2");

        System.out.println("... Application Started ...");
        System.out.println(String.format("Read file from [%s]", args[0]));
        InputStreamReader isr = new InputStreamReader(new FileInputStream(args[0]));
        BufferedReader br = new BufferedReader(isr);

        Solution solution = new Solution();
        Instant start = Instant.now();
        SortedSet<Integer> result = solution.solver(br);
        Instant end = Instant.now();

        String resultString = result
                .stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("... Output ...");
        System.out.println(resultString);
        System.out.println("...");

        System.out.println(String.format("Elapsed Time = [%d] second", TimeUnit.MILLISECONDS.toSeconds(Duration.between(start, end).toMillis())));
        System.out.println(String.format("Write result to [%s]", args[1]));
        Files.write(Paths.get(args[1]), resultString.getBytes());
        System.out.println("... The End ...");
    }
}

class Solution {
    public SortedSet<Integer> solver(BufferedReader br) throws IOException {
        String str;
        String prev = "";
        int prevLineNumber = 0;
        SortedSet<Integer> set = new TreeSet<>();

        for (int i = 1; ((str = br.readLine()) != null); i++) {
            if (str.equalsIgnoreCase("P1") || str.equalsIgnoreCase("P2")) {
                if (str.equalsIgnoreCase(prev)) {
                    set.add(prevLineNumber);
                    set.add(i);
                }
                prev = str;
                prevLineNumber = i;
            }
        }

        return set;
    }
}

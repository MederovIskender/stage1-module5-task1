package com.epam.mjc;

import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Collections.list;

public class InterfaceCreator {

    public Predicate<List<String>> isValuesStartWithUpperCase() {
        return list -> list.stream().allMatch(s -> s.matches("\\p{Upper}.*"));
    }

    public Consumer<List<Integer>> addEvenValuesAtTheEnd() {
        return list -> {
            List<Integer> evenValues = new java.util.ArrayList<>();
            for (Integer number : list) {
                if (number % 2 == 0) {
                    evenValues.add(number);
                }
            }
            list.addAll(evenValues);
        };
    }

    public Supplier<List<String>> filterCollection(List<String> values) {
        return () -> {
            List<String> filteredList = new ArrayList<>();
            for (String value : values) {
                if (isValidSentence(value)) {
                    filteredList.add(value);
                }
            }
            return filteredList;
        };

    }

    public Function<List<String>, Map<String, Integer>> stringSize() {
        return list -> {
            Map<String, Integer> resultMap = new HashMap<>();
            for (String value : list) {
                resultMap.put(value, value.length());
            }
            return resultMap;
        };
    }

    public BiFunction<List<Integer>, List<Integer>, List<Integer>> concatList() {
        return (list1, list2) -> {
            List<Integer> resultList = new ArrayList<>();
            resultList.addAll(list1);
            resultList.addAll(list2);
            return resultList;
        };
    }

    private static boolean isValidSentence(String value) {
        String[] words = value.split(" "); // Split the sentence into words using space as the delimiter
        return value.startsWith(Character.toString(value.charAt(0)).toUpperCase()) // Start with an uppercase letter
                && value.endsWith(".") // End with a period ('.')
                && words.length > 3; // Contains more than 3 words
    }
}

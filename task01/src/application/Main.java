package task01.src.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final int COL_NAME = 0;
    public static final int COL_CAT = 1;
    public static final int COL_RATING = 2;

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Please specify the CSV file name. Thank you");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            int lines = 0;
            while (bufferedReader.readLine() != null) {
                lines++;
            }
            Map<String, List<App>> categorised = bufferedReader.lines()
                    .skip(1)
                    .map(row -> row.trim().split(","))
                    .map(fields -> new App(fields[COL_NAME], fields[COL_CAT].toUpperCase(),
                            Double.parseDouble(fields[COL_RATING])))
                    .collect(Collectors.groupingBy(App::getCategory));

            for (String category : categorised.keySet()) {
                List<App> apps = categorised.get(category);
                System.out.println("Category: " + category);
                System.out.println("Count: " + apps.size());
                for (App app : apps) {
                    Map<String, Double> ratings = new HashMap<>();
                    String name = app.getName();
                    double rating = app.getRating();
                    double discarded = 0;
                    if (rating != null) {
                        discarded++;
                    }
                    ratings.put(name, rating);
                    double highest = Collections.max(ratings.values());
                    double lowest = Collections.min(ratings.values());
                    double sum = 0;
                    sum += rating;
                    double average = sum / apps.size();
                    System.out.println("Highest: " + highest);
                    System.out.println("Lowest: " + lowest);
                    System.out.println("Average: " + average);
                    System.out.println("Discarded: " + discarded);
                }

            }

            System.out.println("Total lines in file: " + lines);

        }
    }

}
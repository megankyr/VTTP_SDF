package task01.src.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
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
            Map<String, Map<String, Integer>> = bufferedReader.readlines();




            Map<String, List<App>> categorised = bufferedReader.lines()
                    .skip(1)
                    .map(row -> row.trim().split(","))
                    .map(fields -> new App(fields[COL_NAME], fields[COL_CAT], Double.parseDouble(fields[COL_RATING])))
                    .collect(Collectors.groupingBy(App::getCategory));

            for (String category : categorised.keySet()) {
                List<App> apps = categorised.get(category);
                List
                System.out.println("Category: " + category);
                System.out.println("Highest: " + Collections.max(apps));
                System.out.println("Count: " + apps.size());
                for (App app : apps) {
                    





                    double rating = app.getRating();
                    int size = apps.size();
                    double average = rating / size;
                    System.out.println("Average: " + average);
                    System.out.println("Highest: " + app.getHighest());
                    System.out.println("Lowest: " + app.getLowest());
    
                }
            }

        }
    }
}
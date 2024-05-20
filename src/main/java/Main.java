import GUI.LoginUI;
import Models.User;
import Services.UserService;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserService();
        User user = us.getUserByUsername("demo");

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginUI();

            }
        });

        // List<String> newCommonWords = Arrays.asList(
        // "Woman", "Child", "Adventure", "Quest", "Secret", "Mystery", "Magic",
        // "Witch",
        // "Wizard", "Dragon", "Forest", "Desert", "Island", "Castle", "Tower", "Moon",
        // "Sun", "Galaxy", "Alien", "Robot", "Future", "Past", "Legend", "Myth",
        // "Hero",
        // "Villain", "Escape", "Chase", "Race", "Battle", "Mission", "Dreams",
        // "Nightmare",
        // "Love", "Hate", "Fear", "Courage", "Friendship", "Betrayal", "Survival",
        // "Death",
        // "Life", "Joy", "Sorrow", "Fate", "Destiny", "Time", "Memory", "Ghost",
        // "Spirit"
        // );
        // MovieService ms = new MovieService();
        // MovieManager mm = new MovieManager();
        // for (String word:newCommonWords){
        // try {
        // List<Movie> movies = mm.searchMovies(word);
        // for (Movie movie : movies) {
        // ms.createMovie(movie);
        // System.out.println(movie.getMovieId());
        //
        // }
        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // } catch (JSONException e) {
        // throw new RuntimeException(e);
        // }
        // }

        // DbFunctions.getConnection();
        // MovieManager movieManager = new MovieManager();
        // try {
        // List<Movie> movies = movieManager.searchMovies("Avengers");
        // for (Movie movie : movies) {
        // System.out.println("Title: " + movie.getTitle());
        // System.out.println("Release Year: " + movie.getReleaseYear());
        // System.out.println("Description: " + movie.getDescription());
        // System.out.println("Rating: " + movie.getRating());
        // System.out.println("Cover Image URL: " + movie.getCoverImageUrl());
        // System.out.println();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

}
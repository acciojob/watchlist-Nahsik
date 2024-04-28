package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private final HashMap<String, Movie> movieMap;
    private final HashMap<String, Director> directorMap;
    private final HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository() {
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director) {
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director) {
        if (movieMap.containsKey(movie) && directorMap.containsKey(director)) {
            List<String> list = directorMovieMapping.getOrDefault(director, new ArrayList<>());
            list.add(movie);
            directorMovieMapping.put(director, list);
        }
    }

    public Movie findMovie(String movie) {
        return movieMap.get(movie);
    }

    public Director findDirector(String director) {
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director) {
        return directorMovieMapping.get(director);
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director) {
        directorMap.remove(director);
        directorMovieMapping.get(director).forEach(movie -> {
            movieMap.remove(movie);
        });
        directorMovieMapping.remove(director);
    }

    public void deleteAllDirector() {
        for (String director : directorMap.keySet()) {
            deleteDirector(director);
        }
    }
}
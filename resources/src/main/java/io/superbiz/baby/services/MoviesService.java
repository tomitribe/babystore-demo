/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.superbiz.baby.services;

import io.superbiz.baby.model.Movie;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
@Lock(LockType.READ)
public class MoviesService {

    public List<Movie> catalog;

    public int id = 0;

    public List<Movie> getCatalog() {
        return catalog;
    }

    public MoviesService() {
        catalog = new ArrayList();
        load();

    }


    public Movie addMovie(Movie movie) {
        this.id++;
        movie.setId(this.id);
        catalog.add(movie);
        return movie;
    }


    public List<Movie> getMovies() {
        return catalog;
    }


    public void deleteMovie(long id) {
        boolean b = catalog.removeIf(obj -> obj.getId() == id);
    }

    public Movie find(Long id) {
        for (Movie movie : catalog) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }


    public void updateMovie(Long id, Movie newMovieData) {
        Movie oldMovieData = find(id);
        if (newMovieData.getTitle() != null) {
            oldMovieData.setTitle(newMovieData.getTitle());
        }
        if (newMovieData.getDirector() != null) {
            oldMovieData.setDirector(newMovieData.getDirector());
        }
        if (newMovieData.getYear() != -1) {
            oldMovieData.setYear(newMovieData.getYear());
        }
        if (newMovieData.getGenre() != null) {
            oldMovieData.setGenre(newMovieData.getGenre());
        }
        if (newMovieData.getRating() != -1) {
            oldMovieData.setRating(newMovieData.getRating());
        }

    }


    public int count() {
        return catalog.size();
    }

    public void clear() {
        catalog.clear();
    }

    public void load() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("movies.txt");
        Scanner scanner = new Scanner(is);
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            String[] line =  scanner.nextLine().split(",");
            addMovie(
                    new Movie(
                            line[0],
                            line[1],
                            line[2],
                            Integer.parseInt(line[3]),
                            Integer.parseInt(line[4])
                    )
            );
        }
    }

}
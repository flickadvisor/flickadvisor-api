package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Movie;
import models.UserMovie;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.MovieRepository;

import javax.inject.Named;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by enda on 16/02/16.
 */
@Named
@Singleton
public class MovieController extends Controller {

    private final MovieRepository repo;

    @Inject
    public MovieController(MovieRepository repo) {
        this.repo = repo;
    }


    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result addMovie() {
        String jsonStr = request().body().asText();

        if (jsonStr == null) {
            return badRequest();
        }

        JsonNode json = Json.parse(jsonStr);
        Movie userMovie = Json.fromJson(json, Movie.class);

        if (repo.movieExists(userMovie.id)) {
            return status(CONFLICT);
        }

        repo.addMovie(userMovie);
        return ok();
    }

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result updateMovie() {
        String jsonStr = request().body().asText();

        if (jsonStr == null) {
            return badRequest();
        }

        JsonNode json = Json.parse(jsonStr);
        Movie userMovie = Json.fromJson(json, Movie.class);

        if (repo.movieExists(userMovie.id)) {
            return status(CONFLICT);
        }

        repo.updateMovie(userMovie);
        return ok();
    }

    @Transactional(readOnly = true)
    public Result getMoviesForUser(long userId) {
        List<UserMovie> userMovies = repo.getMoviesForUser(userId);
        return userMovies == null ? notFound() : ok(toJson(userMovies));
    }

}

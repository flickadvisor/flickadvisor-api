package repositories;

import com.google.inject.ImplementedBy;
import models.Movie;
import models.MovieReview;
import models.UserMovie;
import org.springframework.stereotype.Repository;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by enda on 16/02/16.
 */
@ImplementedBy(MovieRepositoryImpl.class)
public interface MovieRepository {
    List<UserMovie> getMoviesForUser(long id);
    void addMovie(Movie movie);
    void updateMovie(Movie userMovie);
    boolean movieExists(long id);
    void addReview(MovieReview review);
}

@Repository
class MovieRepositoryImpl implements MovieRepository {

    @Override
    public List<UserMovie> getMoviesForUser(long id) {
        List<UserMovie> userMovies =
                (List<UserMovie>) JPA.em().createQuery(
                        "select u from users_movies u where u.user.id = " + id)
                        .getResultList();
        return userMovies.isEmpty() ? null : userMovies;
    }

    @Override
    public void addMovie(Movie movie) {
        JPA.em().persist(movie);
    }

    @Override
    public void updateMovie(Movie userMovie) {
        JPA.em().merge(userMovie);
    }

    @Override
    public boolean movieExists(long id) {
        List<Movie> userMovies =
                (List<Movie>) JPA.em().createQuery(
                        "select u from movies u where u.id = " + id)
                        .getResultList();
        return !userMovies.isEmpty();
    }

    @Override
    public void addReview(MovieReview review) {
        JPA.em().persist(review);
    }

}

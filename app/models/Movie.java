package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by enda on 16/02/16.
 */
@Entity(name = "movies")
public class Movie {

    @Id
    public Long id;
    @OneToMany
    public List<MovieReview> reviews;

    public Movie() {
    }

    public Movie(Long id, List<MovieReview> reviews) {
        this.id = id;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MovieReview> reviews) {
        this.reviews = reviews;
    }
}

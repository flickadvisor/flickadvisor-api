package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by enda on 15/02/16.
 */
@Entity(name = "tv_series")
public class Series {

    @Id
    public Long id;
    @OneToMany
    public List<SeriesReview> reviews;

    public Series() {
    }

    public Series(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SeriesReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<SeriesReview> reviews) {
        this.reviews = reviews;
    }
}

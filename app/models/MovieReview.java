package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by enda on 16/02/16.
 */
@Entity(name = "movie_reviews")
public class MovieReview {

    @Id
    public Long id;
    @Constraints.MaxLength(5)
    public int rating;
    public String description;
    public Timestamp date;
}

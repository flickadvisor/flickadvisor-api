package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * Created by enda on 15/02/16.
 */
@Entity(name = "series_reviews")
public class SeriesReview {

    @Id
    public Long id;
    @Constraints.MaxLength(5)
    public float rating;
    public String description;
    public Timestamp date;
    @ManyToOne
    public User user;
}

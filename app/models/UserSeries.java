package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by enda on 17/02/16.
 */
@Entity(name = "users_series")
public class UserSeries {

    @Id
    public long id;
    @ManyToOne
    @JsonBackReference
    public Series series;
    @ManyToOne
    @JsonBackReference
    public User user;
    public boolean isOnWatchList;
    public boolean isOnWatchedList;
    public boolean isHidden;
}

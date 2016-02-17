package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by enda on 17/02/16.
 */
@Entity(name = "users_movies")
public class UserMovie {

    @Id
    public long id;
    @ManyToOne
    @JsonBackReference
    public Movie movie;
    @ManyToOne
    @JsonBackReference
    public User user;
    public boolean isOnWatchList;
    public boolean isOnWatchedList;
    public boolean isHidden;

    public UserMovie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOnWatchList() {
        return isOnWatchList;
    }

    public void setOnWatchList(boolean onWatchList) {
        isOnWatchList = onWatchList;
    }

    public boolean isOnWatchedList() {
        return isOnWatchedList;
    }

    public void setOnWatchedList(boolean onWatchedList) {
        isOnWatchedList = onWatchedList;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}

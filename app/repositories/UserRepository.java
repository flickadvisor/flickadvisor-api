package repositories;

import com.google.inject.ImplementedBy;
import models.User;
import org.springframework.stereotype.Repository;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by enda on 16/02/16.
 */
@ImplementedBy(UserRepositoryImpl.class)
public interface UserRepository {
    User addUser(User user);
    void updateUser(User user);
    User getUserWithEmail(String email);
    User getUser();
}

@Repository
class UserRepositoryImpl implements UserRepository {

    @Override
    public User addUser(User user) {
        JPA.em().persist(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        JPA.em().merge(user);
    }

    @Override
    public User getUserWithEmail(String email) {
        List<User> users = (List<User>)JPA.em().createQuery(
                "select u from users u where u.email = :email")
                .setParameter("email", email)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUser() {
        List<User> users = (List<User>)JPA.em().createQuery(
                "select u from users u where u.id = " + 1)
                .getResultList();
        return users.get(0);
    }
}

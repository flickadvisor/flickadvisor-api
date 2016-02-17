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
    void addUser(User user);
    void updateUser(User user);
    User getUserWithEmail(String email);
}

@Repository
class UserRepositoryImpl implements UserRepository {

    @Override
    public void addUser(User user) {
        JPA.em().persist(user);
    }

    @Override
    public void updateUser(User user) {
        JPA.em().merge(user);
    }

    @Override
    public User getUserWithEmail(String email) {
        List<User> users = (List<User>)JPA.em().createQuery(
                "select u from users u where u.email = " + email)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}

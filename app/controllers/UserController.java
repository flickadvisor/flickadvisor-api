package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by enda on 16/02/16.
 */
@Named @Singleton
public class UserController extends Controller {

    private final UserRepository repo;

    @Inject
    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public Result createUser() {
        RequestBody body = request().body();

        if (body == null) {
            return badRequest();
        }
        JsonNode json = body.asJson();

        String email = json.path("email").asText();
        String password = json.path("password").asText();

        if (repo.getUserWithEmail(email) != null) {
            return status(CONFLICT);
        } else {
            User user = new User(email, hashPassword(password));
            user = repo.addUser(user);
            return ok(Json.toJson(user));
        }
    }

    @Transactional(readOnly = true)
    public Result getUserWithEmail(String email) {
        if (email == null) {
            return badRequest();
        } else {
            User user = repo.getUserWithEmail(email);

            return user == null ? notFound() : ok(Json.toJson(user));
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
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
        }

        User user = new User(email, hashPassword(password));

        repo.addUser(user);

        return ok();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

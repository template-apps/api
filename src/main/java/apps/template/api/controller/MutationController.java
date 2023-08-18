package apps.template.api.controller;

import apps.template.api.transfer.SignupRequest;
import apps.template.api.transfer.SubscribeRequest;
import apps.template.api.transfer.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.HttpMethod.PUT;

@Controller
public class MutationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MutationController.class);

    @Value("${user_service_uri}")
    private String userServiceURI;

    @MutationMapping
    public String authenticate(@Argument final String userName, @Argument final String password) {
        return new RestTemplate().exchange(URI.create(userServiceURI + "/authenticate"), PUT, new HttpEntity<>(new UserCredentials(userName, password)), String.class).getBody();
    }

    @MutationMapping
    public Boolean authorize(@Argument final String userToken) {
        return new RestTemplate().getForObject(URI.create(userServiceURI + "/validateUserToken/" + userToken), Boolean.class);
    }

    @MutationMapping
    public String signup(@Argument final String email, @Argument final String name, @Argument final String password, @Argument final String avatarUrl) {
        return new RestTemplate().exchange(URI.create(userServiceURI + "/signup"), PUT, new HttpEntity<>(new SignupRequest(email, name, avatarUrl, password)), String.class).getBody();
    }

    @MutationMapping
    public Boolean subscribe(@Argument final String email) {
        return new RestTemplate().exchange(URI.create(userServiceURI + "/subscribe"), PUT, new HttpEntity<>(new SubscribeRequest(email)), Boolean.class).getBody();
    }
}

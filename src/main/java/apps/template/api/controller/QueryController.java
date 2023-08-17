package apps.template.api.controller;

import apps.template.api.transfer.User;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Controller
public class QueryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryController.class);

    @Value("${user_service_uri}")
    private String userServiceURI;

    @QueryMapping
    public User userForToken(@Argument final String userToken) {
        validateUserToken(userToken);

        return new RestTemplate().getForObject(
                URI.create(userServiceURI + "/userForToken/" + userToken), User.class);
    }

    private void validateUserToken(final String userToken) {
        if (!BooleanUtils.isTrue(new RestTemplate().getForObject(
                URI.create(userServiceURI + "/validateUserToken/" + userToken), Boolean.class))) {
            throw new IllegalStateException("Unauthorised user access");
        }
    }
}

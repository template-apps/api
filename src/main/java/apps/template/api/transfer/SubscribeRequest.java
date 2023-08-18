package apps.template.api.transfer;

import java.util.Objects;

public class SubscribeRequest {
    public SubscribeRequest() {
    }

    private String email;

    public SubscribeRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" + "email='" + email + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeRequest that = (SubscribeRequest) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

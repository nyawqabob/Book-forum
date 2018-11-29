package by.iba.boot_learning.constants.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:security.properties")
@ConfigurationProperties()
public class SecurityData {

    private String authenticateEndpoint;
    private String userProfileEndpoint;
    private String logoutEndpoint;

    public String getLogoutEndpoint() {
        return logoutEndpoint;
    }

    public void setLogoutEndpoint(String logoutEndpoint) {
        this.logoutEndpoint = logoutEndpoint;
    }

    public String getUserProfileEndpoint() {
        return userProfileEndpoint;
    }

    public void setUserProfileEndpoint(String userProfileEndpoint) {
        this.userProfileEndpoint = userProfileEndpoint;
    }

    public String getAuthenticateEndpoint() {
        return authenticateEndpoint;
    }

    public void setAuthenticateEndpoint(String authenticateEndpoint) {
        this.authenticateEndpoint = authenticateEndpoint;
    }
}

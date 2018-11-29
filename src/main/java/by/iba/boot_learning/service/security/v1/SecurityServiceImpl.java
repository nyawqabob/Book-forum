package by.iba.boot_learning.service.security.v1;

import by.iba.boot_learning.constants.security.SecurityData;
import by.iba.boot_learning.entity.user.TestUser;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.exceptions.ServiceException;
import by.iba.boot_learning.service.security.SecurityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityServiceImpl implements SecurityService {

    public static final Logger LOGGER = LogManager.getLogger(SecurityServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SecurityData securityData;

    public boolean isRightData(HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        try {
            restTemplate.exchange(securityData.getAuthenticateEndpoint(), HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.info("Status: " + e.getStatusCode().toString() + ". Error: " + e.getMessage());
            throw new ServiceException("Authentication error.");
        }
        return true;
    }

    public TestUser getCurrentUser(HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        TestUser testUser;
        try {
            ResponseEntity<TestUser> testUserResponseEntity = restTemplate.exchange(securityData.getUserProfileEndpoint(), HttpMethod.GET, httpEntity, TestUser.class);
            testUser = testUserResponseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.info("Status: " + e.getStatusCode().toString() + ". Error: " + e.getMessage());
            throw new ServiceException("Authentication error.");
        }
        return testUser;
    }


}

package nextstep.jwp;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import nextstep.jwp.db.InMemoryUserRepository;
import nextstep.jwp.model.User;

public class UserService {

    public UserService() {
    }


    public Optional<User> findUser(String uri) {
        Map<String, String> requestUserData = extractQueryFromLoginUri(uri);
        String account = requestUserData.getOrDefault("account", null);
        String password = requestUserData.getOrDefault("password", null);

        Optional<User> user = InMemoryUserRepository.findByAccountAndPassword(account, password);

        return user;
    }

    public void saveUser(String requestBody) {
        Map<String, String> registerData = extractRegisterDataFromRequestBody(requestBody);

        InMemoryUserRepository.save(new User(InMemoryUserRepository.autoIncrementId,
                registerData.get("account"),
                registerData.get("password"),
                registerData.get("email")));
        InMemoryUserRepository.autoIncrementId += 1;
    }

    private Map<String, String> extractQueryFromLoginUri(String uri) {
        Map<String, String> extractedQuery = new HashMap<>();

        int index = uri.indexOf("?");
        if (index == -1) {
            return extractedQuery;
        }
        String queryString = uri.substring(index + 1);

        String[] splitQuery = queryString.split("[&=]");

        for (int i = 0; i < splitQuery.length; i += 2) {
            extractedQuery.put(splitQuery[i], splitQuery[i + 1]);
        }

        return extractedQuery;
    }

    private Map<String, String> extractRegisterDataFromRequestBody(String requestBody) {
        Map<String, String> extractData = new HashMap<>();
        String[] splitRequestBody = requestBody.split("=|&");

        for (int i = 0; i < splitRequestBody.length; i += 2) {
            extractData.put(splitRequestBody[i], splitRequestBody[i + 1]);
        }
        return extractData;
    }
}
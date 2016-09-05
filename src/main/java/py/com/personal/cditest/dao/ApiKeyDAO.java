package py.com.personal.cditest.dao;


import py.com.personal.cditest.model.ApiKey;

import javax.inject.Inject;

public class ApiKeyDAO {


    @Inject
    Database database;

    public void insert(ApiKey apiKey) {
        database.getApiKeys().add(apiKey);
    }

    public void remove(String token) {
        synchronized (database.getApiKeys()) {
            ApiKey toRemove = null;
            for (ApiKey apiKey : database.getApiKeys()) {
                if (apiKey.getToken().equals(token)) {
                    toRemove = apiKey;
                }
            }
            database.getApiKeys().remove(toRemove);
        }
    }

    public ApiKey find(String token) {
        ApiKey found = null;
        synchronized (database.getApiKeys()) {
            for (ApiKey apiKey : database.getApiKeys()) {
                if (apiKey.getToken().equals(token)) {
                    found = apiKey;
                    break;
                }
            }
        }
        return found;
    }
}

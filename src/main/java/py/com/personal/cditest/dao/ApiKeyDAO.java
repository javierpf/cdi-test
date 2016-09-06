package py.com.personal.cditest.dao;


import py.com.personal.cditest.model.ApiKey;

public interface ApiKeyDAO {
    public void insert(ApiKey apiKey);
    public void remove(String token);
    public ApiKey find(String token);
}

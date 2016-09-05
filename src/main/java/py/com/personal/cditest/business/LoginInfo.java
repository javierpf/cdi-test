package py.com.personal.cditest.business;

import org.jboss.weld.environment.se.contexts.ThreadScoped;
import py.com.personal.cditest.model.ApiKey;
import py.com.personal.cditest.model.Usuario;

import javax.annotation.PostConstruct;

@ThreadScoped
public class LoginInfo {

    private Boolean sessionActive;
    private Usuario loggedUser;
    private ApiKey apiKey;

    @PostConstruct
    private void init(){
        this.sessionActive = false;
    }

    public Boolean getSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(Boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public Usuario getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Usuario loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }
}

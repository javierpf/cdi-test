package py.com.personal.cditest.business;


import py.com.personal.cditest.dao.ApiKeyDAO;
import py.com.personal.cditest.dao.PermisoDAO;
import py.com.personal.cditest.dao.UsuarioDAO;
import py.com.personal.cditest.model.ApiKey;
import py.com.personal.cditest.model.Usuario;

import javax.inject.Inject;
import java.util.UUID;

public class AuthenticationBusiness {

    @Inject
    UsuarioDAO usuarioDAO;

    @Inject
    LoginInfo loginInfo;

    @Inject
    ApiKeyDAO apiKeyDAO;

    public String login(String username, String password) throws Exception {
        String token;
        if (loginInfo.getSessionActive()) {
            throw new Exception("No se puede loguear, existe una sesion activa");
        }
        Usuario u = usuarioDAO.getUser(username);
        if (u == null) {
            throw new Exception("No se encuentra usuario con username especificado");
        }
        if (u.getPassword().equals(password)) {
            System.out.println("Usuario: " + username + " logueado.");
            loginInfo.setSessionActive(true);
            loginInfo.setLoggedUser(u);
            token = Long.toString(UUID.randomUUID().getLeastSignificantBits());
            ApiKey apiKey = new ApiKey(token, u);
            apiKeyDAO.insert(apiKey);
        } else {
            throw new Exception("Contrasenha invalida;");
        }
        return token;

    }

    public void activate(String token) throws Exception {
        loginInfo.setSessionActive(true);
        ApiKey apiKey = apiKeyDAO.find(token);
        if (apiKey == null) {
            throw new Exception("No se pudo activar sesion, token invalido");
        }
        loginInfo.setApiKey(apiKey);
        loginInfo.setLoggedUser(apiKey.getUsuario());

    }

    public void logout(String token) {
        apiKeyDAO.remove(token);
    }
}

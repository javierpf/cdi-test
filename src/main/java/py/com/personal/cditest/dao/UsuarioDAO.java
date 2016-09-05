package py.com.personal.cditest.dao;


import py.com.personal.cditest.model.Usuario;

import javax.inject.Inject;

public class UsuarioDAO {

    @Inject
    Database database;

    public Usuario getUser(String username) {
        Usuario found = null;
        synchronized (database.getUsuarios()) {
            for (Usuario usuario : database.getUsuarios()) {
                if (usuario.getUsername().equals(username)) {
                    found = usuario;
                    break;
                }
            }
        }

        return found;
    }
}

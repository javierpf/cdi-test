package py.com.personal.cditest.service;

import py.com.personal.bc.common.sockets.handler.BasicHandler;

public class AutenticationService implements BasicHandler{

    public String login(String payload) throws Exception{
        System.out.println("Se llamo con payload: " + payload);
        return "Ok";
    }
}

package py.com.personal.cditest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import py.com.personal.bc.common.sockets.handler.BasicHandler;
import py.com.personal.cditest.business.AuthenticationBusiness;
import py.com.personal.cditest.service.payloads.LoginPayload;
import py.com.personal.cditest.service.payloads.LogoutPayload;

import javax.inject.Inject;

public class AutenticationService implements BasicHandler{

    //AutenticationService.login~~~{"username":"juan","password":"hola"}
    //AutenticationService.login~~~{"username":"jose","password":"chau"}
    @Inject
    AuthenticationBusiness authenticationBusiness;

    @Inject
    ObjectMapper objectMapper;

    public String login(String payload) throws Exception{
        try {
            LoginPayload loginPayload = objectMapper.readValue(payload, LoginPayload.class);
            return authenticationBusiness.login(loginPayload.getUsername(), loginPayload.getPassword());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }

    //AutenticationService.logout~~~{"token":"-6516174473254642639"}
    public String logout(String payload) throws Exception{
        try {
            LogoutPayload logoutPayload = objectMapper.readValue(payload, LogoutPayload.class);
            authenticationBusiness.logout(logoutPayload.getToken());
            return "Logout exitoso";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }
}

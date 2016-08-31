package py.com.personal.cditest.util;

import py.com.personal.cditest.business.LoginInfo;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Secured
@Interceptor
public class SecuredInterceptor {

    @Inject
    LoginInfo loginInfo;

    @AroundInvoke
    public Object transactionInterceptor(InvocationContext context)
            throws Throwable {

        System.out.println("Se intercepta invocacion");
        if(!loginInfo.getSessionActive()){
            throw new Exception("No hay usuario logueado");
        }

        RequiresPermission requiresPermission = context.getMethod().getAnnotation(RequiresPermission.class);
        if(requiresPermission != null){
            for(String permiso : requiresPermission.value()){
                if(!loginInfo.getLoggedUser().getPermissions().contains(permiso)){
                    throw new Exception("El usuario logueado no tiene el permiso: " + permiso + " para ejecutar" +
                            "la accion: " + context.getMethod().getName());
                }
            }
        }

        return context.proceed();

    }
}

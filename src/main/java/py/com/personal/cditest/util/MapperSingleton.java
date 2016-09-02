package py.com.personal.cditest.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MapperSingleton {
    private static ObjectMapper mapper = new ObjectMapper();

    @Produces
    public static ObjectMapper getMapper() {
        return mapper;
    }

}
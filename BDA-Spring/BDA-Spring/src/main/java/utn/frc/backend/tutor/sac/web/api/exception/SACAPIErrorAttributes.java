package utn.frc.backend.tutor.sac.web.api.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Set;

//@Component
//https://www.baeldung.com/exception-handling-for-rest-with-spring
public class SACAPIErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes =
                super.getErrorAttributes(webRequest, options);

        // fines demostrativos simplemente
        Set<String> oriAttributes = errorAttributes.keySet();

        errorAttributes.put("locale", webRequest.getLocale().toString());
        errorAttributes.remove("error");

        //...

        return errorAttributes;
    }
}

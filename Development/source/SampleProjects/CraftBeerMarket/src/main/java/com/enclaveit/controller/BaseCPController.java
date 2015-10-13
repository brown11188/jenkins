package com.enclaveit.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by Hung (Charles) V. PHAM on 09/22/2015.
 */

public class BaseCPController {
	private static final Logger logger = LoggerFactory.getLogger(BaseCPController.class);

    /**
     * Result map.
     *
     * @param success the success
     * @param result the result
     * @return the map
     */
    protected Map<String, Object> resultMap(final Boolean success, final Object result) {
        return new HashMap<String, Object>() {{
            put("success", success);
            if (result != null) {
                put("results", result);
            }
        }};
    }

    /**
     * Handle exception.
     * Handle exception.
     *
     * @param exception the exception
     * @return the object
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception exception) {
        logger.error("Exception processing request", exception);
        Map<String, Object> result = new HashMap<>();
        String messTrace = exception.toString();
        if(messTrace.contains("ValidateException")) {
            if (messTrace.contains("block")){
                JSONObject jsonObject = new JSONObject();
                JSONObject childObject = new JSONObject();
                childObject.put("message", "Unauthorized: Your user got blocked following reports that you do not comply to Spark's Terms & Conditions.");
                childObject.put("code", 423);
                jsonObject.put("errors", childObject);
                return new ResponseEntity(jsonObject, HttpStatus.LOCKED);
            }
            JSONObject jsonObject = new JSONObject();
            JSONObject childObject = new JSONObject();
            childObject.put("message", "Unauthorized: Authentication token was either missing or invalid.");
            childObject.put("code", 401);
            jsonObject.put("errors", childObject);
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        else {
            result.put("errors", exception.toString());
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

}

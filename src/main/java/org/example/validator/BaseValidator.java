package org.example.validator;

public class BaseValidator {
    public static Long validate(Long id) throws Exception {
        if(id == null) {
            throw new Exception("id is null");
        }
        return id;
    }
}

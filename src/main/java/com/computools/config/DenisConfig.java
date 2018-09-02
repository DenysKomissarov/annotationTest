package com.computools.config;

import com.computools.annotation.DenisBean;
import com.computools.annotation.DenisConfigAnnotation;
import com.computools.model.User;

@DenisConfigAnnotation
public class DenisConfig {

    @DenisBean
    public User user(){
        User us = new User();
        us.setName("Vasya");
        return us;
    }
}

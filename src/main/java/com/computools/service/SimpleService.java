package com.computools.service;

import com.computools.annotation.DenisAutowire;
import com.computools.annotation.Service;
import com.computools.model.User;

@Service(name = "SimpleServiceAnnotation")
public class SimpleService {

    @DenisAutowire
    private User user;

//    public SimpleService(User user){
//        this.user = user;
//    }

    public void initService(){};


    public void notInitService(){
        System.out.println( "notInitService===================");
    };
}

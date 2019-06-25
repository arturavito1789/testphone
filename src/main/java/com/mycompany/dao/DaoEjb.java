package com.mycompany.dao;

import javax.ejb.Stateless;

@Stateless
public class DaoEjb {
    
    private String name = "EGB init";

    public String getName() {
        return name;
    }

   
    
}

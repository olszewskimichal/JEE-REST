package com.example.helloworld;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Resources {
	@SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private static EntityManager em;
    
    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}

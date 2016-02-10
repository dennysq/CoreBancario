/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.dao;

import com.teamj.distribuidas.ebanking.model.Usuario;
import com.teamj.distribuidas.ebanking.persistence.PersistenceManager;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Dennys
 */

public class UsuarioDAO extends BasicDAO<Usuario, ObjectId> {
   
    public UsuarioDAO(Class<Usuario> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

}

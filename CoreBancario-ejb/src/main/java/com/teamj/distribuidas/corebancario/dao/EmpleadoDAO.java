/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.model.Empleado;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class EmpleadoDAO extends DefaultGenericDAOImple<Empleado, Integer> {

    public EmpleadoDAO() {
        super(Empleado.class);
    }

}

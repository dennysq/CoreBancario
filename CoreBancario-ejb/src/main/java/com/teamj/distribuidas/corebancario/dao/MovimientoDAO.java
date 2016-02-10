/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import com.teamj.distribuidas.corebancario.model.Movimiento;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class MovimientoDAO extends DefaultGenericDAOImple<Movimiento, Integer> {

    public MovimientoDAO() {
        super(Movimiento.class);
    }

}

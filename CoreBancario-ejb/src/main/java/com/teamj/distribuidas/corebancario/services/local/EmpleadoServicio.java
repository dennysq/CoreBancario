/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.local;

import com.teamj.distribuidas.corebancario.dao.EmpleadoDAO;
import com.teamj.distribuidas.corebancario.model.Empleado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class EmpleadoServicio {

    @EJB
    EmpleadoDAO empleadoDAO;

    /**
     *
     * @param empleado empleado que requiere logearse
     * @return Empleado de la base de datos con su id
     */
    public Empleado login(Empleado empleado) {
        List<Empleado> tempList = this.empleadoDAO.find(empleado);
        if (tempList != null && tempList.size() == 1) {

            return tempList.get(0);

        }
        return null;
    }

}

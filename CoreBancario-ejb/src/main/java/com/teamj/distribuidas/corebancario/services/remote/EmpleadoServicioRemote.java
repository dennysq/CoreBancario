/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import com.teamj.distribuidas.corebancario.dao.EmpleadoDAO;
import com.teamj.distribuidas.corebancario.model.Empleado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@Stateless
@Remote(EmpleadoServicioInterface.class)
public class EmpleadoServicioRemote implements EmpleadoServicioInterface {

    @EJB
    EmpleadoDAO empleadoDAO;

    @Override
    public boolean login(String nombreUsuario, String password) {
        System.out.println("Login Empleado...");
        System.out.println("Datos Ingresados " + nombreUsuario + password);

        Empleado empleado = new Empleado();
        empleado.setNombreUsuario(nombreUsuario);
        empleado.setPassword(password);

        List<Empleado> tempList = this.empleadoDAO.find(empleado);
        if (tempList != null && tempList.size() == 1) {

            return true;

        }
        return false;
    }

}

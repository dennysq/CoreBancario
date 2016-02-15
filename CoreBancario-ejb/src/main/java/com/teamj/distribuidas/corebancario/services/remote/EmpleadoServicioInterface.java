/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import javax.ejb.Remote;

/**
 *
 * @author Dennys
 */
@Remote
public interface EmpleadoServicioInterface {

    public boolean login(String nombreUsuario,String password);
}

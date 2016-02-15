/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.model.Cuenta;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Dennys
 */
@Remote
public interface ClienteServicioInterface {

    public Cliente obtenerClientePorIdentificacion(String identificacion);

    public List<Cuenta> obtenerCuentasPorCliente(String identificacion);
}

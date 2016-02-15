/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import com.teamj.distribuidas.corebancario.dao.ClienteDAO;
import com.teamj.distribuidas.corebancario.dao.CuentaDAO;
import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.model.Cuenta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@Stateless
@Remote(ClienteServicioInterface.class)
public class ClienteServicioRemote implements ClienteServicioInterface {

    @EJB
    ClienteDAO clienteDAO;

    @EJB
    CuentaDAO cuentaDAO;

    @Override
    public Cliente obtenerClientePorIdentificacion(String identificacion) {
        Cliente c = new Cliente();
        c.setIdentificacion(identificacion);
        List<Cliente> clientes = this.clienteDAO.find(c);
        if (clientes != null && clientes.size() == 1) {
            return clientes.get(0);
        }
        return null;
    }

    @Override
    public List<Cuenta> obtenerCuentasPorCliente(String identificacion) {
        Cliente temp = obtenerClientePorIdentificacion(identificacion);
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(temp);
        return this.cuentaDAO.find(cuenta);
    }

}

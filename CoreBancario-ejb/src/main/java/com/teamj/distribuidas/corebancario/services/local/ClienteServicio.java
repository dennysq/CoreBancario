/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.local;

import com.teamj.distribuidas.corebancario.dao.ClienteDAO;
import com.teamj.distribuidas.corebancario.dao.CuentaDAO;
import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.validation.ValidationException;
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
public class ClienteServicio {

    @EJB
    ClienteDAO clienteDAO;

    @EJB
    CuentaDAO cuentaDAO;

    public Cliente validarCliente(Cliente client, String cuenta) throws ValidationException {
        try {
            List<Cliente> temp = this.clienteDAO.find(client);
            if (temp != null && temp.size() == 1) {
                for (Cuenta c : temp.get(0).getCuentas()) {
                    if (c.getNumero().equals(cuenta)) {
                        return temp.get(0);
                    }
                }
            } else {

                System.out.println("No se ha encontrado el cliente con esa identificacion: " + client.getIdentificacion());
                return null;
            }
            System.out.println("El cliente no se ha validado con esa cuenta");
            Cliente aux = new Cliente();
            //CNV cuenta no valida
            aux.setNombre("CNV");
            return aux;
        } catch (Exception e) {
            throw new ValidationException("Error al consultar en la base de datos");
        }

    }

    public Cliente obtenerClientePorIdentificacion(String identificacion) {
        Cliente c = new Cliente();
        c.setIdentificacion(identificacion);
        List<Cliente> clientes = this.clienteDAO.find(c);
        if (clientes != null && clientes.size() == 1) {
            return clientes.get(0);
        }
        return null;
    }

    public List<Cuenta> obtenerCuentasPorCliente(String identificacion) {
        Cliente temp = obtenerClientePorIdentificacion(identificacion);
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(temp);
        return this.cuentaDAO.find(cuenta);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.services;

import com.teamj.distribuidas.corebancario.dao.ClienteDAO;
import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.services.local.ClienteServicio;
import com.teamj.distribuidas.corebancario.validation.ValidationException;
import com.teamj.distribuidas.ebanking.dao.UsuarioDAO;
import com.teamj.distribuidas.ebanking.model.Usuario;
import com.teamj.distribuidas.ebanking.persistence.PersistenceManager;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class UsuarioServicio {

    UsuarioDAO usuarioDAO;
    @EJB
    ClienteServicio clienteServicio;

    @PostConstruct
    public void init() {
        this.usuarioDAO = new UsuarioDAO(Usuario.class, PersistenceManager.instance().datastore());
    }

    public String createEbankingUser(Usuario user, String cuenta) throws ValidationException {
        try {

            Usuario temp = this.usuarioDAO.findOne("nombreUsuario", user.getNombreUsuario());
            if (temp == null) {
                Cliente cliente = new Cliente();
                cliente.setIdentificacion(user.getIdentificacion());
                Cliente clienteBase = clienteServicio.validarCliente(cliente, cuenta);
                if (clienteBase != null) {
                    if (!clienteBase.getNombre().equals("CNV")) {
                        user.setCodigoBase(clienteBase.getId());
                        user.setActivo("0");
                        user.setMontoMaximo(300.0f);
                        user.setCorreo(clienteBase.getEmail());
                        this.usuarioDAO.save(user);
                        return "1";
                    } else {
                        return "4";
                    }
                } else {
                    return "2";
                }
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return "3";
    }

    public Usuario login(Usuario u) throws ValidationException {
        Usuario temp = null;
        try {
            Query q = PersistenceManager.instance().datastore().createQuery(Usuario.class)
                    .filter("nombreUsuario =", u.getNombreUsuario())
                    .filter("password =", u.getPassword());

            temp = this.usuarioDAO.findOne(q);
        } catch (Exception e) {

            throw new ValidationException(e.getMessage());
        }
        return temp;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.web;

import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.services.local.ClienteServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dennys
 */
@ManagedBean
@ViewScoped
public class ConsolidadoBean implements Serializable {

    private Cliente cliente;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    @EJB
    private ClienteServicio clienteServicio;

    public Cliente getCliente() {
        return cliente;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PostConstruct
    public void init() {
        this.cliente = this.clienteServicio.obtenerClientePorIdentificacion(this.sessionBean.getUser().getIdentificacion());

    }

    public String getTipoCuenta(String tipo) {
        if (tipo.equals("AH")) {
            return "Ahorros";
        }
        if (tipo.equals("CO")) {
            return "Corriente";
        }
        return "NO VALIDO?";
    }
}

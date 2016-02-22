/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.web;

import com.teamj.distribuidas.corebancario.services.local.CuentaServicio;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dennys
 */
@ManagedBean
@ViewScoped
public class TelefoniaBean {
 
    @EJB
    private CuentaServicio cuentaServicio;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private String numero;
    private BigDecimal monto;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getNumero() {
        return numero;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @PostConstruct
    public void init() {
        this.monto = BigDecimal.ZERO;
    }
    
    
}

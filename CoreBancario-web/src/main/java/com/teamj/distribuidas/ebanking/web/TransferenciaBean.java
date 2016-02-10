/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.web;

import com.teamj.distribuidas.corebancario.model.Cliente;
import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.services.local.ClienteServicio;
import com.teamj.distribuidas.corebancario.services.local.CuentaServicio;
import com.teamj.distribuidas.corebancario.validation.ValidationException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Dennys
 */
@ManagedBean
@ViewScoped
public class TransferenciaBean implements Serializable {

    private List<Cuenta> cuentasDebito;
    private Cuenta cuentaCredito;
    private Cuenta cuentaSeleccionada;
    @EJB
    private ClienteServicio clienteServicio;
    @EJB
    private CuentaServicio cuentaServicio;

    private Cliente clienteActual;
    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private Integer idCuentaSeleccionada;
    private Boolean cuentaEncontrada;
    private BigDecimal monto;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCuentaServicio(CuentaServicio cuentaServicio) {
        this.cuentaServicio = cuentaServicio;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setCuentaEncontrada(Boolean cuentaEncontrada) {
        this.cuentaEncontrada = cuentaEncontrada;
    }

    public Boolean getCuentaEncontrada() {
        return cuentaEncontrada;
    }

    public CuentaServicio getCuentaServicio() {
        return cuentaServicio;
    }

    public Cuenta getCuentaCredito() {
        return cuentaCredito;
    }

    public void setIdCuentaSeleccionada(Integer idCuentaSeleccionada) {
        this.idCuentaSeleccionada = idCuentaSeleccionada;
    }

    public Integer getIdCuentaSeleccionada() {
        return idCuentaSeleccionada;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public List<Cuenta> getCuentasDebito() {
        return cuentasDebito;
    }

    public void setCuentaCredito(Cuenta cuentaCredito) {
        this.cuentaCredito = cuentaCredito;
    }

    public void setCuentasDebito(List<Cuenta> cuentasDebito) {
        this.cuentasDebito = cuentasDebito;
    }

    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }

    public Cuenta getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    @PostConstruct
    public void init() {
        this.cuentaEncontrada = false;
        this.cuentasDebito = this.clienteServicio.obtenerCuentasPorCliente(this.sessionBean.getUser().getIdentificacion());
        this.clienteActual = this.clienteServicio.obtenerClientePorIdentificacion(this.sessionBean.getUser().getIdentificacion());
        this.cuentaCredito = new Cuenta();
    }

    public void cargarCuentaDebito(ValueChangeEvent event) {
        for (Cuenta c : this.cuentasDebito) {
            if (c.getId().equals(idCuentaSeleccionada)) {
                cuentaSeleccionada = c;
                break;
            }
        }
    }

    public void buscarCuenta() {
        if (cuentaCredito.getNumero() != null) {
            Cuenta temp = this.cuentaServicio.obtenerCuentaPorNumero(cuentaCredito.getNumero());
            if (temp != null) {
                cuentaEncontrada = true;
                this.cuentaCredito = temp;
            } else {

                cuentaEncontrada = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El número de cuenta no existe"));
            }
        }
    }

    public void transferir() {
        try {
            this.cuentaServicio.transferir(descripcion, monto, cuentaSeleccionada, cuentaCredito);
        } catch (ValidationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error No Controlado", e.getMessage()));

        }
    }
}

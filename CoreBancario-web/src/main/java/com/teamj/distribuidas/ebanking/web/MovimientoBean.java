/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.web;

import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.model.Movimiento;
import com.teamj.distribuidas.corebancario.services.local.ClienteServicio;
import com.teamj.distribuidas.corebancario.services.local.CuentaServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Dennys
 */
@ManagedBean
@ViewScoped
public class MovimientoBean implements Serializable {

    @EJB
    private ClienteServicio clienteServicio;
    @EJB
    private CuentaServicio cuentaServicio;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private List<Movimiento> movimientos;
    private List<Cuenta> cuentas;
    private Integer idCuentaSeleccionada;

    public Integer getIdCuentaSeleccionada() {
        return idCuentaSeleccionada;
    }

    public void setIdCuentaSeleccionada(Integer idCuentaSeleccionada) {
        this.idCuentaSeleccionada = idCuentaSeleccionada;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @PostConstruct
    public void init() {
        this.cuentas = this.clienteServicio.obtenerCuentasPorCliente(this.sessionBean.getUser().getIdentificacion());

    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void cargarMovimientos(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            this.movimientos = this.cuentaServicio.obtenerMovimientosPorCuenta((Integer) event.getNewValue());
        } else {
            if (this.movimientos != null) {
                this.movimientos.clear();
            }
        }
    }

    public String obtenerDescripcionTipoMovimiento(String tipo) {

        if (tipo.equals("DE")) {
            return "Depósito";
        }
        if (tipo.equals("RE")) {
            return "Retiro";

        }
        if (tipo.equals("TC")) {
            return "Transf. Acreditada";

        }
        if (tipo.equals("TD")) {
            return "Transf. Debitada";

        }
        if (tipo.equals("SA")) {
            return "Recarga";

        }
        return "DESC";

    }

    public String obtenerSignoTipoMovimiento(String tipo) {
        if (tipo.equals("DE") || tipo.equals("TC")) {
            return "+";
        }
        return "-";
    }
}

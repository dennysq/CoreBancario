/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.local;

import com.teamj.distribuidas.corebancario.dao.CuentaDAO;
import com.teamj.distribuidas.corebancario.dao.MovimientoDAO;
import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.model.Movimiento;
import com.teamj.distribuidas.corebancario.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Date;
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
public class CuentaServicio {

    @EJB
    CuentaDAO cuentaDAO;
    @EJB
    MovimientoDAO movimientoDAO;

    public List<Movimiento> obtenerMovimientosPorCuenta(Integer idCuenta) {
        Cuenta cuenta = this.cuentaDAO.findById(idCuenta, false);
        if (cuenta != null) {
            cuenta.getMovimientos().size();
            return cuenta.getMovimientos();
        }
        return null;

    }

    public Cuenta obtenerCuentaPorNumero(String numero) {
        Cuenta c = new Cuenta();
        c.setNumero(numero);
        List<Cuenta> cuentas = this.cuentaDAO.find(c);
        if (cuentas != null && cuentas.size() == 1) {
            return cuentas.get(0);
        }
        return null;
    }

    public void transferir(String desc, BigDecimal monto, Cuenta debito, Cuenta credito) throws ValidationException {

        debito.setSaldo(debito.getSaldo().subtract(monto));
        credito.setSaldo(credito.getSaldo().add(monto));

        try {
            Date date = new Date();
            Cuenta deb = this.cuentaDAO.update(debito);
            Cuenta cred = this.cuentaDAO.update(credito);
            Movimiento mdebito = new Movimiento();
            mdebito.setCuenta(deb);
            mdebito.setFechaHora(date);
            mdebito.setMonto(monto);
            mdebito.setTipo("TD");
            mdebito.setSaldo(debito.getSaldo());
            mdebito.setDescripcion(desc);
            Movimiento mcredito = new Movimiento();
            mcredito.setCuenta(cred);
            mcredito.setFechaHora(date);
            mcredito.setMonto(monto);
            mcredito.setTipo("TC");
            mcredito.setSaldo(credito.getSaldo());
            mcredito.setDescripcion(desc);
            this.movimientoDAO.insert(mdebito);
            this.movimientoDAO.insert(mcredito);
            
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }

    }
}

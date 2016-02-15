/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import com.teamj.distribuidas.corebancario.dao.CuentaDAO;
import com.teamj.distribuidas.corebancario.dao.MovimientoDAO;
import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.model.Movimiento;
import com.teamj.distribuidas.corebancario.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@Stateless
@Remote(CuentaServicioInterface.class)
public class CuentaServicioRemote implements CuentaServicioInterface {

    @EJB
    CuentaDAO cuentaDAO;
    @EJB
    MovimientoDAO movimientoDAO;

    @Override
    public void deposito(Cuenta cuenta, BigDecimal monto, String desc) throws ValidationException {

        cuenta.setSaldo(cuenta.getSaldo().add(monto));

        try {
            Date date = new Date();

            Cuenta cred = this.cuentaDAO.update(cuenta);

            Movimiento mcredito = new Movimiento();
            mcredito.setCuenta(cred);
            mcredito.setFechaHora(date);
            mcredito.setMonto(monto);
            mcredito.setTipo("DE");
            mcredito.setSaldo(cred.getSaldo());
            mcredito.setDescripcion(desc);
            this.movimientoDAO.insert(mcredito);

        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public void retiro(Cuenta cuenta, BigDecimal monto, String desc) throws ValidationException {

        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));

        try {
            Date date = new Date();
            Cuenta deb = this.cuentaDAO.update(cuenta);

            Movimiento mdebito = new Movimiento();
            mdebito.setCuenta(deb);
            mdebito.setFechaHora(date);
            mdebito.setMonto(monto);
            mdebito.setTipo("RE");
            mdebito.setSaldo(deb.getSaldo());
            mdebito.setDescripcion(desc);

            this.movimientoDAO.insert(mdebito);

        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }
}

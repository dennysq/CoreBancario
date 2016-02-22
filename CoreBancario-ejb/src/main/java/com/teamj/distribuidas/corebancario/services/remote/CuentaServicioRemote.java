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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    public boolean deposito(String numeroCuenta, String tipoCuenta, String montoString, String fecha) throws ValidationException {

        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(numeroCuenta);
        cuenta.setTipo(tipoCuenta);
        List<Cuenta> temp = this.cuentaDAO.find(cuenta);
        if (temp != null && temp.size() == 1) {
            cuenta = temp.get(0);
            BigDecimal monto = new BigDecimal(montoString);

            cuenta.setSaldo(cuenta.getSaldo().add(monto));

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

                Cuenta cred = this.cuentaDAO.update(cuenta);

                Movimiento mcredito = new Movimiento();
                mcredito.setCuenta(cred);
                mcredito.setFechaHora(sdf.parse(fecha));
                mcredito.setMonto(monto);
                mcredito.setTipo("DE");
                mcredito.setSaldo(cred.getSaldo());
                mcredito.setDescripcion("OP. VENTANILLA");
                this.movimientoDAO.insert(mcredito);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean retiro(String numeroCuenta, String tipoCuenta, String montoString, String fecha) throws ValidationException {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(numeroCuenta);
        cuenta.setTipo(tipoCuenta);
        List<Cuenta> temp = this.cuentaDAO.find(cuenta);
        if (temp != null && temp.size() == 1) {
            cuenta = temp.get(0);
            BigDecimal monto = new BigDecimal(montoString);
            if (monto.floatValue() > cuenta.getSaldo().floatValue()) {

                cuenta.setSaldo(cuenta.getSaldo().subtract(monto));

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

                    Cuenta deb = this.cuentaDAO.update(cuenta);
                    Movimiento mdebito = new Movimiento();
                    mdebito.setCuenta(deb);
                    mdebito.setFechaHora(sdf.parse(fecha));
                    mdebito.setMonto(monto);
                    mdebito.setTipo("RE");
                    mdebito.setSaldo(deb.getSaldo());
                    mdebito.setDescripcion("OP. VENTANILLA");
                    this.movimientoDAO.insert(mdebito);
                    return true;
                } catch (Exception e) {
                    throw new ValidationException(e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public Cuenta obtenerCuenta(String numeroCuenta, String tipoCuenta) throws ValidationException {
        Cuenta c = new Cuenta();
        c.setTipo(tipoCuenta);
        c.setNumero(numeroCuenta);
        List<Cuenta> temp = this.cuentaDAO.find(c);
        if (temp != null && temp.size() == 1) {
            return temp.get(0);
        }
        return null;

    }

}

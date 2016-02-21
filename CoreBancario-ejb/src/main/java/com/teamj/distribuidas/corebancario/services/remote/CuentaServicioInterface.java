/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.validation.ValidationException;
import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author Dennys
 */
@Remote
public interface CuentaServicioInterface {

    public void deposito(Cuenta cuenta, BigDecimal monto, String desc) throws ValidationException;

    public void retiro(Cuenta cuenta, BigDecimal monto, String desc) throws ValidationException;
    
    public Cuenta obtenerCuenta(String numeroCuenta, String tipoCuenta) throws ValidationException;
}

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

    public boolean deposito(String numeroCuenta, String tipoCuenta , String montoString,String fecha) throws ValidationException;

    public boolean retiro(String numeroCuenta, String tipoCuenta , String montoString,String fecha) throws ValidationException;
    
    public Cuenta obtenerCuenta(String numeroCuenta, String tipoCuenta) throws ValidationException;
}

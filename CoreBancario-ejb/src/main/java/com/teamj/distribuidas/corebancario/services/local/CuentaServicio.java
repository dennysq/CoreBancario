/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.local;

import com.teamj.distribuidas.corebancario.dao.CuentaDAO;
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
    
}

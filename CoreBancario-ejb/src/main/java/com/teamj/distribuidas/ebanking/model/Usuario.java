/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.model;

import com.teamj.distribuidas.ebanking.persistence.BaseEntity;
import java.io.Serializable;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author Dennys
 */
@Entity(noClassnameStored = true)
public class Usuario extends BaseEntity implements Serializable{

    private String nombreUsuario;
    private String password;
    private String correo;
    private String identificacion;
    private Integer codigoBase;
    private String activo;
    private Float montoMaximo;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getCodigoBase() {
        return codigoBase;
    }

    public void setCodigoBase(Integer codigoBase) {
        this.codigoBase = codigoBase;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Float getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(Float montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombreUsuario=" + nombreUsuario + ", password=" + password + ", correo=" + correo + ", identificacion=" + identificacion + ", codigoBase=" + codigoBase + ", activo=" + activo + ", montoMaximo=" + montoMaximo + '}';
    }

}

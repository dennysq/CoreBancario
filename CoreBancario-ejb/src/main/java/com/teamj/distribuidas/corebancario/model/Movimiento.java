/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "MOVIMIENTO")
public class Movimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_MOVIMIENTO")
    private Integer id;

    @Column(name = "MONTO", nullable = false)
    private BigDecimal monto;

    @Column(name = "FECHA_Y_HORA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaHora;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "TIPO_MOVIMIENTO", nullable = false)
    private String tipo;

    @Column(name = "DESCRIPCION_MOVIMIENTO")
    private String descripcion;

    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO_CUENTA")
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movimiento other = (Movimiento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "id=" + id + ", monto=" + monto + ", fechaHora=" + fechaHora + ", saldo=" + saldo + ", tipo=" + tipo + ", cuenta=" + cuenta + '}';
    }

}

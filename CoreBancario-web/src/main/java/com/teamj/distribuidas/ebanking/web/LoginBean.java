/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.web;

import com.teamj.distribuidas.corebancario.validation.ValidationException;
import com.teamj.distribuidas.ebanking.model.Usuario;

import com.teamj.distribuidas.ebanking.services.UsuarioServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean
public class LoginBean extends CrudBean implements Serializable {

    private String identificacionUsuario;
    private String cuentaUsuario;

    private String nombreUsuarioL;
    private String nombreUsuario;
    private String claveUsuarioL;
    private String claveUsuario;

    @EJB
    private UsuarioServicio usuarioServicio;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    public void setNombreUsuarioL(String nombreUsuarioL) {
        this.nombreUsuarioL = nombreUsuarioL;
    }

    public void setClaveUsuarioL(String claveUsuarioL) {
        this.claveUsuarioL = claveUsuarioL;
    }

    public String getClaveUsuarioL() {
        return claveUsuarioL;
    }

    public String getNombreUsuarioL() {
        return nombreUsuarioL;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    @PostConstruct
    public void init() {
        // this.usuario = new Usuario();
    }

    public String login() {

        FacesMessage msg = null;
        if (nombreUsuario != null && !nombreUsuario.isEmpty() && claveUsuario != null && !claveUsuario.isEmpty()) {

            Usuario u = new Usuario();
            u.setNombreUsuario(nombreUsuario);
            u.setPassword(claveUsuario);
            Usuario loggedUser = usuarioServicio.login(u);
            try {
                if (loggedUser != null) {

                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", loggedUser);
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombreUsuario);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return this.sessionBean.login(loggedUser);

                } else {

                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error",
                            "Credenciales no válidas");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } catch (Exception e) {
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Llene todos los campos para continuar");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
        return null;
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();

    }

    public void beginSignUp() {
        this.beginCreate();
        cuentaUsuario = "";
        claveUsuarioL = "";
        identificacionUsuario = "";
        nombreUsuarioL = "";
    }

    public void signUp() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (cuentaUsuario != null && !cuentaUsuario.isEmpty()
                    && claveUsuarioL != null && !claveUsuarioL.isEmpty()
                    && identificacionUsuario != null && !identificacionUsuario.isEmpty()
                    && nombreUsuarioL != null && !nombreUsuarioL.isEmpty()) {
                Usuario u = new Usuario();
                u.setIdentificacion(identificacionUsuario);
                u.setNombreUsuario(nombreUsuarioL);
                u.setPassword(claveUsuarioL);
                String resp = usuarioServicio.createEbankingUser(u, cuentaUsuario);
                if (resp.equals("1")) {

                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "El registro se completó correctamente"));
                    RequestContext.getCurrentInstance().execute("PF('signup_dialog_var').hide()");
                } else if (resp.equals("3")) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "El nombre de usuario ya existe"));
                } else if (resp.equals("2")) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "El cliente no se encuentra en la base de datos del banco"));
                } else if (resp.equals("4")) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "El número de cuenta no es válido para el cliente con la identificación: " + identificacionUsuario));
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "Complete todos los campos para continuar"));
            }
        } catch (ValidationException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error no controlado", e.getMessage()));
        }
        this.nombreUsuario = this.nombreUsuarioL;
        this.reset();

    }
}

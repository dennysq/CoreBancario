/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.web;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.commons.beanutils.BeanUtilsBean;

/**
 *
 * @author Dennys
 */
@ApplicationScoped
@ManagedBean
public class MainBean {

    private String webSocketIp;
    private String webSocketPort;

    public String getWebSocketIp() {
        return webSocketIp;
    }

    public void setWebSocketIp(String WebSocketIp) {
        this.webSocketIp = WebSocketIp;
    }

    public void setWebSocketPort(String webSocketPort) {
        this.webSocketPort = webSocketPort;
    }

    public String getWebSocketPort() {
        return webSocketPort;
    }

    @PostConstruct
    public void init() {
        BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
        beanUtilsBean.getConvertUtils().register(
                new org.apache.commons.beanutils.converters.BigDecimalConverter(null), BigDecimal.class);
        webSocketIp = "192.168.1.143";
        webSocketPort = "8082";
    }

}

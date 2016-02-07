/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.validation;

/**
 *
 * @author H200
 */
public class ValidationException extends RuntimeException {

    private String appMessage;

    public ValidationException(String message) {
        this.appMessage = message;
    }

    public ValidationException(Exception e, String appMessage) {
        super(e);
        this.appMessage = appMessage;
    }

    public String getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }

}

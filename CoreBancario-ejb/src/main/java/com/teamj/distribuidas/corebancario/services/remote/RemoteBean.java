/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.corebancario.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@Stateless
@Remote(RemoteInterface.class)
public class RemoteBean implements RemoteInterface {

    private List<String> values = new ArrayList();

    @Override
    public int addValue(String value) {
        this.values.add(value);
        return this.values.size();
    }

}

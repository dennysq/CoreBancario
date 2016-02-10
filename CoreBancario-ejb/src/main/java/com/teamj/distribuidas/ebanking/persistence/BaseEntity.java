/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.ebanking.persistence;

import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author Freddy
 */
public class BaseEntity {

    @Id
    protected ObjectId id;

    @Version
    private long version;

    protected Date creationDate;
    protected Date lastUpdateDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id + ", version=" + version + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + '}';
    }

    @PrePersist
    public void prePersist() {

        this.creationDate = (creationDate == null) ? new Date() : creationDate;

        this.lastUpdateDate = (lastUpdateDate == null) ? creationDate : new Date();

    }

}

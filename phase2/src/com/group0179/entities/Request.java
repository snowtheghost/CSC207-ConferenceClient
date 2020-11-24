package com.group0179.entities;

import java.util.UUID;

public class Request {
    private UUID requestID;
    private String typeOfRequest; //make this a combobox
    private String urgency; //make this a combobox
    private String requestContent; //this is entirely custom
    private boolean isPending;
    private boolean isAddressed;

    public Request(String typeOfRequest, String urgency, String requestContent){
        this.requestID = UUID.randomUUID();
        this.typeOfRequest = typeOfRequest;
        this.urgency = urgency;
        this.requestContent = requestContent;
        this.isPending = false;
        this.isAddressed = false;
    }
    public UUID getRequestID(){
        return this.requestID;
    }

    public String getTypeOfRequest(){
        return this.typeOfRequest;
    }

    public String getUrgency(){
        return this.urgency;
    }

    public String getRequestContent(){
        return this.requestContent;
    }

    public boolean isPending() {
        return this.isPending;
    }

    public void setPending(boolean pending) {
        this.isPending = pending;
    }

    public boolean isAddressed() {
        return this.isAddressed;
    }

    public void setAddressed(boolean addressed) {
        isAddressed = addressed;
    }
}

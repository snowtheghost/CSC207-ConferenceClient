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
    /**
     * @return a UUID which represents Request User ID
     */
    public UUID getRequestID(){
        return this.requestID;
    }
    /**
     * @return a String which represents type of request
     */
    public String getTypeOfRequest(){
        return this.typeOfRequest;
    }
    /**
     * @return a String that represents urgency level of request
     */
    public String getUrgency(){
        return this.urgency;
    }
    /**
     * @return a String that represents content of request
     */
    public String getRequestContent(){
        return this.requestContent;
    }
    /**
     * @return a boolean that represents whether request is pending or not
     */
    public boolean isPending() {
        return this.isPending;
    }
    /**
     * Sets pending level for this request
     * @param pending
     */
    public void setPending(boolean pending) {
        this.isPending = pending;
    }
    /**
     * @return a boolean that represents whether request is addressed or not
     */
    public boolean isAddressed() {
        return this.isAddressed;
    }
    /**
     * Sets addressed level for this request
     * @param addressed
     */
    public void setAddressed(boolean addressed) {
        isAddressed = addressed;
    }
}

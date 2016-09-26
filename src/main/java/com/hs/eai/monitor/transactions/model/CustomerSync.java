package com.hs.eai.monitor.transactions.model;

import java.util.Date;


public class CustomerSync implements java.io.Serializable {

	
	private static final long serialVersionUID = -5204916092936772401L;

	private Integer id;

	private Integer messageType;

	private Integer transactionStatus;

	private String messageContent;

	private Integer systemSourceId;

	private Integer systemDestinationId;

	private Integer salesOrganizationId;

	private Integer countryId;

	private String orderNumber;

	private String billTo;

	private String shipTo;

	private String addressGuid;
	private String name;

	private Date dateCreated;

	private Date dateSent;

	public CustomerSync() {
	}

	public CustomerSync(int id, Integer messageType, Integer transactionStatus, Integer systemSourceId, Integer systemDestinationId,Integer salesOrganizationId, Date dateCreated) {
		this.id = id;
		this.messageType = messageType;
		this.transactionStatus = transactionStatus;
		this.systemSourceId = systemSourceId;
		this.systemDestinationId = systemDestinationId;
		this.dateCreated = dateCreated;
		this.salesOrganizationId = salesOrganizationId;
	}

	public CustomerSync(int id, Integer messageType, Integer transactionStatus, String messageContent, Integer systemSourceId, Integer systemDestinationId,Integer salesOrganizationId, Date dateCreated, Date dateSent) {
		this.id = id;
		this.messageType = messageType;
		this.transactionStatus = transactionStatus;
		this.messageContent = messageContent;
		this.systemSourceId = systemSourceId;
		this.systemDestinationId = systemDestinationId;
		this.dateCreated = dateCreated;
		this.dateSent = dateSent;
		this.salesOrganizationId = salesOrganizationId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMessageType() {
		return this.messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(Integer transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getSystemSourceId() {
		return this.systemSourceId;
	}

	public void setSystemSourceId(Integer systemSourceId) {
		this.systemSourceId = systemSourceId;
	}

	public Integer getSystemDestinationId() {
		return this.systemDestinationId;
	}

	public void setSystemDestinationId(Integer systemDestinationId) {
		this.systemDestinationId = systemDestinationId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateSent() {
		return this.dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public Integer getSalesOrganizationId() {
		return salesOrganizationId;
	}

	public void setSalesOrganizationId(Integer salesOrganizationId) {
		this.salesOrganizationId = salesOrganizationId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getAddressGuid() {
		return addressGuid;
	}

	public void setAddressGuid(String addressGuid) {
		this.addressGuid = addressGuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
}


package com.wipro.ddvs.entity;

;

	public class VerificationLog {
	    private String logId;
	    private String documentId;
	    private String officerId;
	    private String date;
	    private String notes;

	    public VerificationLog(String logId, String documentId, String officerId,
	                           String date, String notes) {
	        this.logId = logId;
	        this.documentId = documentId;
	        this.officerId = officerId;
	        this.date = date;
	        this.notes = notes;
	    }

	    public String getDocumentId() {
	        return documentId;
	    }

	    public String getNotes() {
	        return notes;
	    }
	}



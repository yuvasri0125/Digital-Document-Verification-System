package com.wipro.ddvs.service;



	import java.util.ArrayList;
	import com.wipro.ddvs.entity.*;
	import com.wipro.ddvs.util.*;

	public class VerificationService {

	    private ArrayList<User> users;
	    private ArrayList<Document> documents;
	    private ArrayList<Officer> officers;
	    private ArrayList<VerificationLog> logs;

	    public VerificationService(ArrayList<User> users,
	                               ArrayList<Document> documents,
	                               ArrayList<Officer> officers,
	                               ArrayList<VerificationLog> logs) {
	        this.users = users;
	        this.documents = documents;
	        this.officers = officers;
	        this.logs = logs;
	    }

	    public void uploadDocument(Document d) throws UserNotFoundException {
	        boolean userExists = false;
	        for (User u : users) {
	            if (u.getUserId().equals(d.getUserId())) {
	                userExists = true;
	            }
	        }
	        if (!userExists) {
	            throw new UserNotFoundException("User not found");
	        }
	        documents.add(d);
	    }

	    public void assignOfficer(String documentId, String officerId)
	            throws DocumentNotFoundException, OfficerNotFoundException, InvalidVerificationException {

	        Document doc = null;
	        for (Document d : documents) {
	            if (d.getDocumentId().equals(documentId)) {
	                doc = d;
	            }
	        }
	        if (doc == null)
	            throw new DocumentNotFoundException("Document not found");

	        if (doc.getAssignedOfficerId() != null)
	            throw new InvalidVerificationException("Officer already assigned");

	        boolean officerExists = false;
	        for (Officer o : officers) {
	            if (o.getOfficerId().equals(officerId)) {
	                officerExists = true;
	            }
	        }
	        if (!officerExists)
	            throw new OfficerNotFoundException("Officer not found");

	        doc.setAssignedOfficerId(officerId);
	        doc.setStatus("UNDER_REVIEW");
	    }

	    public void updateStatus(String documentId, String status)
	            throws DocumentNotFoundException {

	        for (Document d : documents) {
	            if (d.getDocumentId().equals(documentId)) {
	                d.setStatus(status);
	                return;
	            }
	        }
	        throw new DocumentNotFoundException("Document not found");
	    }

	    public void addVerificationLog(VerificationLog log)
	            throws InvalidVerificationException {

	        if (log.getNotes().isEmpty()) {
	            throw new InvalidVerificationException("Notes cannot be empty");
	        }
	        logs.add(log);
	    }

	    public String generateVerificationReport(String documentId) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Verification Report\n");

	        for (VerificationLog log : logs) {
	            if (log.getDocumentId().equals(documentId)) {
	                sb.append("Note: ").append(log.getNotes()).append("\n");
	            }
	        }
	        return sb.toString();
	    }
	}



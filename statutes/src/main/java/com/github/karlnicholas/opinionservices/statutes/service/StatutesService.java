package com.github.karlnicholas.opinionservices.statutes.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.github.karlnicholas.opinionservices.statutes.StatutesRoot;
import com.github.karlnicholas.opinionservices.statutes.StatutesTitles;
import com.github.karlnicholas.opinionservices.statutes.service.dto.StatuteKey;

@RegisterRestClient
public interface StatutesService {

	String STATUTES = "statutes";
	String STATUTESTITLES = "statutestitles";
	String STATUTEHIERARCHY = "statutehierarchy";
	String STATUTESANDHIERARCHIES = "statutesandhierarchies";

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	List<StatutesRoot> getStatutesRoots();

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	StatutesTitles[] getStatutesTitles();

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	StatutesRoot getStatuteHierarchy(String fullFacet);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	List<StatutesRoot> getStatutesAndHierarchies(List<StatuteKey> statuteKeys);

}

package com.github.karlnicholas.opinionservices.statutes.service.client;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.github.karlnicholas.opinionservices.statutes.StatutesRoot;
import com.github.karlnicholas.opinionservices.statutes.StatutesTitles;
import com.github.karlnicholas.opinionservices.statutes.service.StatutesService;
import com.github.karlnicholas.opinionservices.statutes.service.dto.StatuteKey;

import static com.github.karlnicholas.opinionservices.statutes.service.StatutesService.*;

@Path("")
@RegisterRestClient
public class StatutesServiceClientImpl {

	@Inject
    @RestClient
    StatutesService statutesService;
    
	@GET
	@Path(STATUTES)
    @Produces(MediaType.APPLICATION_JSON)
	public List<StatutesRoot> getStatutesRoots() {
		return statutesService.getStatutesRoots();
	}

	@GET
	@Path(STATUTESTITLES)
    @Produces(MediaType.APPLICATION_JSON)
	public StatutesTitles[] getStatutesTitles() {
		return statutesService.getStatutesTitles();
	}

	@GET
	@Path(STATUTEHIERARCHY)
    @Produces(MediaType.APPLICATION_JSON)
	public StatutesRoot getStatuteHierarchy(@QueryParam("fullFacet") String fullFacet) {
		return statutesService.getStatuteHierarchy(fullFacet);
	}

	@POST
	@Path(STATUTESANDHIERARCHIES)
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public List<StatutesRoot> getStatutesAndHierarchies(List<StatuteKey> statuteKeys) {
		return statutesService.getStatutesAndHierarchies(statuteKeys);
	}
}

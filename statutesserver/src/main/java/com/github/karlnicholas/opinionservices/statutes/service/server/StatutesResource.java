package com.github.karlnicholas.opinionservices.statutes.service.server;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.karlnicholas.opinionservices.statutes.SectionNumber;
import com.github.karlnicholas.opinionservices.statutes.StatutesRoot;
import com.github.karlnicholas.opinionservices.statutes.StatutesTitles;
import com.github.karlnicholas.opinionservices.statutes.api.IStatutesApi;
import com.github.karlnicholas.opinionservices.statutes.service.dto.StatuteKey;

@Path("/hello")
public class StatutesResource {
	private final IStatutesApi iStatutesApi;

	public StatutesResource() {
		this.iStatutesApi = ApiImplSingleton.getInstance().getStatutesApi();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<StatutesRoot> getStatutesRoots() {
		return iStatutesApi.getStatutes();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public StatutesTitles[] getStatutesTitles() {
		return iStatutesApi.getStatutesTitles();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public StatutesRoot getStatuteHierarchy(@QueryParam("fullFacet") String fullFacet) {
		return iStatutesApi.getStatutesHierarchy(fullFacet);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces()
	public List<StatutesRoot> getStatutesAndHierarchies(List<StatuteKey> statutesKeys) {
			// This is a section
			List<StatutesRoot> rv = statutesKeys.stream().map(statuteKey -> {
				String lawCode = statuteKey.getLawCode();
				SectionNumber sectionNumber = new SectionNumber();
				sectionNumber.setPosition(-1);
				sectionNumber.setSectionNumber(statuteKey.getSectionNumber());
				return Optional.ofNullable(iStatutesApi.findReference(lawCode, sectionNumber))
						.map(statutesBaseClass->statutesBaseClass.getFullFacet());
			})
			.filter(Optional::isPresent)
			.map(Optional::get)
			.map(iStatutesApi::getStatutesHierarchy)
					.collect(Collectors.groupingBy(StatutesRoot::getLawCode, Collectors.reducing((sr1, sr2)->{
						return (StatutesRoot)sr1.mergeReferenceStatute(sr2);
					})))
				.values().stream().map(Optional::get).collect(Collectors.toList());
			return rv;
	}

}

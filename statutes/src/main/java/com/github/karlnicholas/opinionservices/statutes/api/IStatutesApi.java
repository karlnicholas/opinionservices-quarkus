package com.github.karlnicholas.opinionservices.statutes.api;

import java.util.List;
import java.util.Map;

import com.github.karlnicholas.opinionservices.statutes.SectionNumber;
import com.github.karlnicholas.opinionservices.statutes.StatutesBaseClass;
import com.github.karlnicholas.opinionservices.statutes.StatutesRoot;
import com.github.karlnicholas.opinionservices.statutes.StatutesTitles;

public interface IStatutesApi {
	public List<StatutesRoot> getStatutes();
	
    public StatutesBaseClass findReference(String lawCode, SectionNumber sectionNumber);
    public StatutesTitles[] getStatutesTitles();
    public String getShortTitle(String lawCode);
	public String getTitle(String lawCode);
    public Map<String, StatutesTitles> getMapStatutesToTitles();
    
    public boolean loadStatutes();	// no exceptions allowed

	public StatutesRoot findReferenceByLawCode(String lawCode);
    
	public StatutesRoot getStatutesHierarchy(String fullFacet);


}
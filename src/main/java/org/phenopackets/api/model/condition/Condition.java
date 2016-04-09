package org.phenopackets.api.model.condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;
import org.phenopackets.api.model.environment.Environment;
import org.phenopackets.api.model.ontology.ClassInstance;

import java.util.Objects;

/**
 * An abstract class that encompasses both {@link DiseaseOccurrence}s and {@link Phenotype}s
 *
 * @author cjm
 */
public abstract class Condition extends ClassInstance {

    @JsonProperty("has_location")
    @JsonPropertyDescription("the location in an organism or cell in which the phenotype manifests")
    @JsonldProperty("http://purl.obolibrary.org/obo/BFO_0000066")
    private OrganismalSite hasLocation;

    @JsonProperty("attribute_of")
    @JsonPropertyDescription("the entity which this phenotype is about -- normally bundled into ontology class")
    private OrganismalSite attributeOf;
    
    @JsonProperty("onset")
    @JsonPropertyDescription("the time region in which the condition is first manifest")
    private TemporalRegion timeOfOnset;

    @JsonProperty("offset")
    @JsonPropertyDescription("the time region in which the condition ceases to manifest")
    private TemporalRegion timeOfFinishing;

    @JsonProperty("severity")
    @JsonPropertyDescription("the degree to which the phenotype is manifest, related to the concept of expressivity, see http://www.ncbi.nlm.nih.gov/books/NBK22090/")
    private ConditionSeverity severity;

    @JsonProperty("frequency")
    @JsonPropertyDescription("the frequency with which the phenotype manifests, related to the concept of penetrance, see http://www.ncbi.nlm.nih.gov/books/NBK22090/")
    private ConditionSeverity frequency;

    @JsonProperty("environment")
    @JsonPropertyDescription("the environment in which the phenotype is expressed")
    private Environment environment;

    public Condition(Builder builder) {
        super(builder);
        this.timeOfOnset = builder.timeOfOnset;
        this.timeOfFinishing = builder.timeOfFinishing;
    }

    public Condition() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the timeOfOnset
     */
    public TemporalRegion getTimeOfOnset() {
        return timeOfOnset;
    }

    /**
     * @param timeOfOnset the timeOfOnset to set
     */
    public void setTimeOfOnset(TemporalRegion timeOfOnset) {
        this.timeOfOnset = timeOfOnset;
    }

    /**
     * @return the timeOfFinishing
     */
    public TemporalRegion getTimeOfFinishing() {
        return timeOfFinishing;
    }

    /**
     * @param timeOfFinishing the timeOfFinishing to set
     */
    public void setTimeOfFinishing(TemporalRegion timeOfFinishing) {
        this.timeOfFinishing = timeOfFinishing;
    }


    /**
     * @return the hasLocation
     */
    public OrganismalSite getHasLocation() {
        return hasLocation;
    }

	/**
	 * @return the severity
	 */
	public ConditionSeverity getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(ConditionSeverity severity) {
		this.severity = severity;
	}
	

	/**
     * @return the frequency
     */
    public ConditionSeverity getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(ConditionSeverity frequency) {
        this.frequency = frequency;
    }

    /**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}



	/**
     * @param hasLocation the hasLocation to set
     */
    public void setHasLocation(OrganismalSite hasLocation) {
        this.hasLocation = hasLocation;
    }


    public abstract static class Builder extends ClassInstance.Builder {
        protected TemporalRegion timeOfOnset;
        protected TemporalRegion timeOfFinishing;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(hasLocation, condition.hasLocation) &&
                Objects.equals(timeOfOnset, condition.timeOfOnset) &&
                Objects.equals(timeOfFinishing, condition.timeOfFinishing) &&
                Objects.equals(severity, condition.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasLocation, timeOfOnset, timeOfFinishing, severity);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "hasLocation=" + hasLocation +
                ", timeOfOnset=" + timeOfOnset +
                ", timeOfFinishing=" + timeOfFinishing +
                ", severity=" + severity +
                '}';
    }
}

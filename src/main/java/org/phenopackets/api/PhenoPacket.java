package org.phenopackets.api;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldId;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;

import java.util.ArrayList;
import java.util.List;

import org.phenopackets.api.model.association.DiseaseOccurrenceAssociation;
import org.phenopackets.api.model.association.EnvironmentAssociation;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.entity.Disease;
import org.phenopackets.api.model.entity.Organism;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.entity.Variant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;

/**
 * Top level container
 *
 * @author cjm
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
@JsonDeserialize(builder = PhenoPacket.Builder.class)
public class PhenoPacket {

    @JsonldId
    private final String id;

    @JsonldProperty("http://purl.org/dc/elements/1.1/title")
    private final String title;

    /*
     * due to typing in yaml not using a 'type' fields in the same way as json
     * this doesn't serialise and deserialise in the way we want to represent the entities,
     * so we need to specify them explicitly in distinct lists. Plus it makes the API cleaner
     * as you had to add an entity twice.
     */

    // ---- ENTITIES ----
    private final List<Person> persons;
    private final List<Organism> organisms;
    private final List<Variant> variants;
    private final List<Disease> diseases;

    // ---- PROFILES/ASSOCIATIONS ----
    private final List<PhenotypeAssociation> phenotypeAssociations;
    private final List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociations;
    private final List<EnvironmentAssociation> environmentAssociations;

    private PhenoPacket(Builder builder) {
        id = builder.id;
        title = builder.title;

        variants = ImmutableList.copyOf(builder.variants);
        persons = ImmutableList.copyOf(builder.persons);
        organisms = ImmutableList.copyOf(builder.organisms);
        diseases = ImmutableList.copyOf(builder.diseases);

        phenotypeAssociations = ImmutableList.copyOf(builder.phenotypeAssociations);
        diseaseOccurrenceAssociations = ImmutableList.copyOf(builder.diseaseOccurrenceAssociations);
        environmentAssociations = ImmutableList.copyOf(builder.environmentAssociations);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the variants
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<Variant> getVariants() {
        return variants;
    }

    /**
     * @return the persons
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * @return the organisms
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<Organism> getOrganisms() {
        return organisms;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Disease> getDiseases() {
        return diseases;
    }

    /**
     * @return the phenotype_profile
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<PhenotypeAssociation> getPhenotypeAssociations() {
        return phenotypeAssociations;
    }

    /**
     * @return the diseaseOccurrenceAssociations
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<DiseaseOccurrenceAssociation> getDiseaseOccurrenceAssociations() {
        return diseaseOccurrenceAssociations;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<EnvironmentAssociation> getEnvironmentAssociations() {
        return environmentAssociations;
    }

    public static class Builder {

        @JsonProperty
        private String id;
        @JsonProperty
        private String title;

        // ---- ENTITIES ----
        @JsonProperty
        private List<Variant> variants;
        @JsonProperty
        private List<Person> persons;
        @JsonProperty
        private List<Organism> organisms;
        @JsonProperty
        private List<Disease> diseases;

        // ---- PROFILES/ASSOCIATIONS ----
        @JsonProperty("phenotype_profile")
        private List<PhenotypeAssociation> phenotypeAssociations;

        @JsonProperty("diagnosis_profile")
        private List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociations;

        @JsonProperty("environment_profile")
        private List<EnvironmentAssociation> environmentAssociations;

        @JsonCreator
        public Builder() {
            this.variants = new ArrayList<>();
            this.persons = new ArrayList<>();
            this.organisms = new ArrayList<>();
            this.diseases = new ArrayList<>();

            this.phenotypeAssociations = new ArrayList<>();
            this.diseaseOccurrenceAssociations = new ArrayList<>();
            this.environmentAssociations = new ArrayList<>();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder addVariant(Variant variant) {
            variants.add(variant);
            return this;
        }

        /**
         * @param variants the variants to set
         */
        public Builder setVariants(List<Variant> variants) {
            this.variants = variants;
            return this;
        }

        public Builder addPerson(Person person) {
            persons.add(person);
            return this;
        }

        /**
         * @param persons the persons to set
         */
        public Builder setPersons(List<Person> persons) {
            this.persons = persons;
            return this;
        }

        public Builder addOrganism(Organism organism) {
            organisms.add(organism);
            return this;
        }

        /**
         * @param organisms the organisms to set
         */
        public Builder setOrganisms(List<Organism> organisms) {
            this.organisms = organisms;
            return this;
        }

        public Builder addDisease(Disease disease) {
            diseases.add(disease);
            return this;
        }

        public Builder setDiseases(List<Disease> diseases) {
            this.diseases = diseases;
            return this;
        }

        public Builder addPhenotypeAssociation(PhenotypeAssociation a) {
            phenotypeAssociations.add(a);
            return this;
        }

        /**
         * @param phenotypeAssociations the phenotype_profile to set
         */
        public Builder setPhenotypeAssociations(List<PhenotypeAssociation> phenotypeAssociations) {
            this.phenotypeAssociations = phenotypeAssociations;
            return this;
        }

        public Builder addDiseaseOccurrenceAssociation(DiseaseOccurrenceAssociation diseaseOccurrenceAssociation) {
            this.diseaseOccurrenceAssociations.add(diseaseOccurrenceAssociation);
            return this;
        }

        /**
         * @param diseaseOccurrenceAssociations the diseaseOccurrenceAssociationList to set
         */
        public Builder setDiseaseOccurrenceAssociations(List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociations) {
            this.diseaseOccurrenceAssociations = diseaseOccurrenceAssociations;
            return this;
        }

        public Builder addEnvironmentAssociation(EnvironmentAssociation environmentAssociation) {
            environmentAssociations.add(environmentAssociation);
            return this;
        }

        public Builder setEnvironmentAssociations(List<EnvironmentAssociation> environmentAssociations) {
            this.environmentAssociations = environmentAssociations;
            return this;
        }

        public PhenoPacket build() {
            return new PhenoPacket(this);
        }
    }

}

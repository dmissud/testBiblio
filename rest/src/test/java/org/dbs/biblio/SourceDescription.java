package org.dbs.biblio;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SourceDescription {
    @JsonProperty("dataName")
    private String dataName;


    @JsonProperty("sizeOf")
    private Integer sizeOf;
    public String getDataName() {
        return dataName;
    }

    public Integer getSizeOf() {
        return sizeOf;
    }
}
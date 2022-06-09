package com.vaxsys.dto;

public class VaccineDto {
    private Integer id;
    private String name;
    private String description;
    private DiseaseDto disease;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiseaseDto getDisease() {
        return disease;
    }

    public void setDisease(DiseaseDto disease) {
        this.disease = disease;
    }
}

package com.dkoper.orion.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComparisonElementDto {

    private int id;
    private String latitude;
    private String longitude;
    private String times;
    private String altitude;
    private String accuracy;
    private ComparisonDto comparisonDto;

}

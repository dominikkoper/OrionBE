package com.dkoper.orion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComparisonDto {

    private int id;
    private List<ComparisonElementDto> comparisons;
    private String createdBy;
    private LocalDateTime createdOn;
}

package com.dkoper.orion.service;

import com.dkoper.orion.dto.ComparisonDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ComparisonService {

    ComparisonDto getById(int comparisonId);
    List<ComparisonDto> getAll();
    ComparisonDto save(ComparisonDto comparisonDto);
    void cleanUp();
}

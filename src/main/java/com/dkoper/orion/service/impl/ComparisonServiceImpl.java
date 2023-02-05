package com.dkoper.orion.service.impl;

import com.dkoper.orion.dto.ComparisonDto;
import com.dkoper.orion.model.Comparison;
import com.dkoper.orion.model.ComparisonElement;
import com.dkoper.orion.repository.ComparisonElementRepository;
import com.dkoper.orion.repository.ComparisonRepository;
import com.dkoper.orion.service.ComparisonService;
import lombok.AllArgsConstructor;;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ComparisonServiceImpl implements ComparisonService {

    private final ComparisonRepository comparisonRepository;
    private final ComparisonElementRepository comparisonElementRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ComparisonDto getById(int comparisonId) {
        return modelMapper.map(comparisonRepository.findComparisonById(comparisonId).orElseThrow(), ComparisonDto.class);
    }

    @Override
    public List<ComparisonDto> getAll() {
        List<ComparisonDto> comparisonDtoList = new ArrayList<>();
        comparisonRepository.findAll().forEach(comparison -> {
            comparisonDtoList.add(modelMapper.map(comparison, ComparisonDto.class));
        });
        return comparisonDtoList;
    }

    @Override
    public ComparisonDto save(ComparisonDto comparisonDto) {
        comparisonDto.setCreatedOn(LocalDateTime.now());
        Comparison savedComparison = comparisonRepository.save(modelMapper.map(comparisonDto, Comparison.class));
        comparisonDto.getComparisons().forEach(comparisonElementDto -> {
            ComparisonElement comparisonElement = modelMapper.map(comparisonElementDto, ComparisonElement.class);
            comparisonElement.setComparison(savedComparison);
            comparisonElementRepository.save(comparisonElement);
        });
        return modelMapper.map(savedComparison, ComparisonDto.class);
    }

    @Override
    @Transactional
    public void cleanUp() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        comparisonRepository.deleteByCreatedOnBefore(oneMonthAgo);
    }
}

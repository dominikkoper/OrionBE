package com.dkoper.orion.controller;

import com.dkoper.orion.dto.ComparisonDto;
import com.dkoper.orion.service.ComparisonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/comparison")
@AllArgsConstructor
public class ComparisonController {
    
    private ComparisonService comparisonService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComparisonDto>getComparison(@RequestParam int comparisonId) {
        return ResponseEntity.ok()
                .headers(setHeaders())
                .body(comparisonService.getById(comparisonId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComparisonDto>saveComparison(@RequestBody ComparisonDto comparisonDto) {
        return ResponseEntity.ok()
                .headers(setHeaders())
                .body(comparisonService.save(comparisonDto));
    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComparisonDto>>getAllComparisons() {
        return ResponseEntity.ok()
                .headers(setHeaders())
                .body(comparisonService.getAll());
    }

    private HttpHeaders setHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Access-Control-Allow-Headers", "*");
        httpHeaders.set("Access-Control-Expose-Headers", "*");
            httpHeaders.set("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        return httpHeaders;

    }
}

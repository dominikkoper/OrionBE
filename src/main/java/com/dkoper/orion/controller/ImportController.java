package com.dkoper.orion.controller;

import com.dkoper.orion.dto.ComparisonElementDto;
import com.dkoper.orion.service.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/import")
@AllArgsConstructor
public class ImportController {

    private final ImportService importService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComparisonElementDto>> importFile(@RequestPart MultipartFile file) {
        try {
            return ResponseEntity.ok().headers(setHeaders()).body(importService.getFromImport(file));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @GetMapping(path = "/template", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> getTemplate() {
        StreamingResponseBody streamingResponseBody;
        try {
            streamingResponseBody = importService.getTemplate();
            return ResponseEntity.ok().body(streamingResponseBody);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    private HttpHeaders setHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Access-Control-Allow-Headers", "*");
        httpHeaders.set("Access-Control-Expose-Headers", "*");
        httpHeaders.set("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        return httpHeaders;

    }
}

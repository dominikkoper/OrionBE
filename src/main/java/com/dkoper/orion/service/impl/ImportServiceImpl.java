package com.dkoper.orion.service.impl;

import com.dkoper.orion.dto.ComparisonElementDto;
import com.dkoper.orion.mapper.ExcelMapper;
import com.dkoper.orion.model.ComparisonElement;
import com.dkoper.orion.service.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportServiceImpl implements ImportService {

    private final String file = "C:/Users/catch/IdeaProjects/orion/src/main/resources/templates/template.xlsx";

    private final ExcelMapper excelMapper;


    @Override
    public StreamingResponseBody getTemplate() throws IOException {
        return excelMapper.getTemplate(file);
    }

    @Override
    public List<ComparisonElementDto> getFromImport(MultipartFile file) throws IOException {
        return excelMapper.getFromFile(file).toJavaList();
    }
}

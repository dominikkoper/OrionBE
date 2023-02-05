package com.dkoper.orion.service;

import com.dkoper.orion.dto.ComparisonElementDto;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;

public interface ImportService {

    StreamingResponseBody getTemplate() throws IOException;
    
    List<ComparisonElementDto> getFromImport(MultipartFile file) throws IOException;
}

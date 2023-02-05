package com.dkoper.orion.mapper;

import com.dkoper.orion.dto.ComparisonElementDto;
import io.vavr.collection.List;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.apache.poi.ss.usermodel.CellType.STRING;

@Service
public class ExcelMapper {

    private static final String FILE_TYPE = "application/vnd.ms-excel";
    private static final int SHEET = 0;

    public StreamingResponseBody getTemplate(String file) throws IOException {

        StreamingResponseBody streamingResponseBody = null;
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bos.close();

        byte[] buffer = bos.toByteArray();
        if (Objects.nonNull(buffer)){
            InputStream inputStream = new ByteArrayInputStream(buffer);
            streamingResponseBody = outputStream -> {
                int read;
                byte[] data = new byte[1024];
                while ((read = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, read);
                }
                inputStream.close();
            };
        }
        return streamingResponseBody;
    }

    @SneakyThrows
    public List<ComparisonElementDto> getFromFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = FILE_TYPE.equals(file.getContentType())? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(SHEET);

        List<ComparisonElementDto> comparisonElementDtos = List.empty();
        for (Row row : sheet) {
            List<String> cells = List.empty();
            for(int c=0; c<row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(STRING);
                cells = cells.append(cell.getStringCellValue());
            }
            if (row.getRowNum()>0) {
                ComparisonElementDto comparisonElementDto = mapComparisonElementFromRow(cells);
                if (!comparisonElementDto.getLatitude().isEmpty() && !comparisonElementDto.getLongitude().isEmpty() && !comparisonElementDto.getAccuracy().isEmpty() && Objects.nonNull(comparisonElementDto.getTimes()))
                comparisonElementDtos = comparisonElementDtos.append(comparisonElementDto);
            }
        }
        return comparisonElementDtos;
    }

    private ComparisonElementDto mapComparisonElementFromRow(List<String> row) {
        return ComparisonElementDto.builder()
                .latitude(row.get(1))
                .longitude(row.get(2))
                .times(row.get(3))
                .altitude(row.get(4))
                .accuracy(row.get(5))
                .build();
    }

}

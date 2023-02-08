package com.testBatch.testBatch.Service;

import com.testBatch.testBatch.Model.Sale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Sale> getSalesDataFromExcel(InputStream inputStream) {
        List<Sale> sales = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("sales");

            int rowIndex = 0;
            for(Row row : sheet) {
                if(rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Sale sale = new Sale();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 :
                            System.out.println("===============> cell 0: "+cell);
                            sale.setRegion(cell.getStringCellValue());
                            break;
                        case 1 :
                            System.out.println("===============> cell 1: "+cell);
                            sale.setRep(cell.getStringCellValue()); break;
                        case 2 :
                            System.out.println("===============> cell 2: "+cell);
                            sale.setItem(cell.getStringCellValue());break;
                        case 3 :
                            System.out.println("===============> cell 3: "+cell);
                            sale.setUnit(cell.getNumericCellValue());
                            break;
                        case 4 :
                            System.out.println("===============> cell 4: "+cell);
                            sale.setCost(cell.getNumericCellValue());
                        case 5 :
                            System.out.println("===============> cell 5: "+cell);
                            sale.setTotal(cell.getNumericCellValue());
                        default:{

                        }
                    }
                    cellIndex++;
                }
                sales.add(sale);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return sales;
    }
}


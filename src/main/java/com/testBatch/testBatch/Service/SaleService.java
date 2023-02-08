package com.testBatch.testBatch.Service;

import com.testBatch.testBatch.Model.Sale;
import com.testBatch.testBatch.Repository.SaleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepo saleRepo;

    public void saveSalesToDatabase(MultipartFile file) {
        if(ExcelUploadService.isValidExcelFile(file)) {
            try {
                List<Sale> sales = ExcelUploadService.getSalesDataFromExcel(file.getInputStream());
                saleRepo.saveAll(sales);
            } catch (IOException e) {
                throw new IllegalArgumentException("the file is not a valid excel......");
            }
        }
    }

    public List<Sale> getSales() {
        return saleRepo.findAll();
    }
}

package com.testBatch.testBatch.Controller;

import com.testBatch.testBatch.Model.Sale;
import com.testBatch.testBatch.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/upload-sales-data")
    public ResponseEntity<?> uploadSalesData(@RequestParam("file") MultipartFile file) {
        saleService.saveSalesToDatabase(file);
        return ResponseEntity.ok(Map.of("Message", "sales data saved to database"));
    }

    @GetMapping
    public List<Sale> getSales() {
        if(saleService == null) {
            return new ArrayList<>();
        }
        return saleService.getSales();
    }

}

package com.testBatch.testBatch.Repository;

import com.testBatch.testBatch.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Integer> {
}

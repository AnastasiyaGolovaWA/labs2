package com.example.labs2.repository;

import com.example.labs2.models.Calculations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalculationsRepository extends JpaRepository<Calculations, Long> {
    @Query(value = "select *\n" +
            "from calculations\n" +
            "where id IN (select id from calculations where number_system_one = 2)\n" +
            "  AND (number_system_two = 10)\n" +
            "  AND (operation_name = 'ADDITION')\n" +
            "  AND (date_created > '2022-09-24') AND (date_created < '2022-09-30')" , nativeQuery=true)
    List<Calculations> findByParameters();
}

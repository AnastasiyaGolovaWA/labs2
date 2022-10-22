package com.example.labs2.repository;

import com.example.labs2.models.Calculations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CalculationsRepository extends JpaRepository<Calculations, Long> {
    @Query(value = "select *\n" +
            "from calculations\n" +
            "where id IN (select id from calculations where number_system_one= :numberSystemOne OR :numberSystemOne=0)\n" +
            "  AND (number_system_two = :numberSystemTwo OR :numberSystemTwo=0)\n" +
            "  AND (operation_name = :operationName OR :operationName='')\n" +
            "  AND (date_created > :startDate) AND (date_created < :endDate)", nativeQuery = true)
    List<Calculations> findByParameters(int numberSystemOne, int numberSystemTwo, String operationName, Date startDate, Date endDate);

    @Transactional
    @Modifying
    @Query(value = "delete from calculations", nativeQuery = true)
    void clear();

    @Transactional
    @Query(value = "select date_created from calculations where number_one=:numberOne", nativeQuery = true)
    Date findDate(String numberOne);
}

package com.example.labs2.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NumbersDTO {
    private Integer numberSystemOne;

    private Integer numberSystemTwo;

    String operationName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;
}

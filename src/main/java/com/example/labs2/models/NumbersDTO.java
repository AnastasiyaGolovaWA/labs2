package com.example.labs2.models;

import lombok.Data;

import java.util.Date;

@Data
public class NumbersDTO {
    private Integer numberSystemOne;

    private Integer numberSystemTwo;

    String operationName;

    Date startDate;

    Date endDate;
}

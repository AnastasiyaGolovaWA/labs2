package com.example.labs2.models;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NumberDate {

    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;

    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;
}

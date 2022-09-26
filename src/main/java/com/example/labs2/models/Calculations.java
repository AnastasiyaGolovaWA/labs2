package com.example.labs2.models;

import com.example.labs2.models.enums.OperationsEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "calculations")
public class Calculations {
    @Id
    @Column
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number_one")
    private String numberOne;

    @NotNull
    @Column(name = "number_system_one")
    private Integer numberSystemOne;

    @NotNull
    @Column(name = "number_two")
    private String numberTwo;

    @NotNull
    @Column(name = "number_system_two")
    private Integer numberSystemTwo;

    @NotNull
    @Column(name = "date")
    private String date;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_name")
    private OperationsEnum operationsEnum;

    public Calculations() {

    }
}

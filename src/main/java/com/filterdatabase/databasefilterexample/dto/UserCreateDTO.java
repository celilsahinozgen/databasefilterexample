package com.filterdatabase.databasefilterexample.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserCreateDTO {


    private String name;

    private String lastName;

    private String tckNo;

    private String okul;

    private String telefon;

    private String serhir;

    private String memleket;

    private String anneadı;

    private String babaadı;

    private LocalDateTime updateDate = LocalDateTime.now();

    private LocalDate dogumtarihi;
}

package com.filterdatabase.databasefilterexample.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {

    private String name;

    private String lastName;

    private String tckNo;

    private String okul;

    private String telefon;

    private String serhir;

    private LocalDate dogumtarihi;


}

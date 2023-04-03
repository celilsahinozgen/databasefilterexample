package com.filterdatabase.databasefilterexample.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@Data
public class User {

    public static final String ZAMANPATTERIN = "yyyy-MM-dd HH:mm:ss";
    public static final String YILPATTERIN = "yyyy-MM-dd";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "tckno")
    private String tckNo;

    @Column(name = "okul")
    private String okul;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "serhir")
    private String serhir;

    @Column(name = "memleket")
    private String memleket;

    @Column(name = "anneadı")
    private String anneadi;

    @Column(name = "babaadı")
    private String babaadı;


    @Column(name = "OLUSDURMATARIHI")
    @DateTimeFormat(pattern = ZAMANPATTERIN)
    LocalDateTime createDate;

    @Column(name = "GUNCELLEME")
    @DateTimeFormat(pattern = ZAMANPATTERIN)
    LocalDateTime updateDate;

    @Column(name = "DOGUMTARIHI")
    @DateTimeFormat(pattern = YILPATTERIN)
    LocalDate dogumtarihi;



}

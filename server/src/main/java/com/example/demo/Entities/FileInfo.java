package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "Files")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

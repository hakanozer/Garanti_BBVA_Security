package com.garanti.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    @NotEmpty
    @Length(min = 1, max = 100)
    @NotNull
    @Column(length = 100)
    private String title;

    @NotEmpty
    @Length(min = 1, max = 500)
    @NotNull
    @Column(length = 500)
    private String detail;

}

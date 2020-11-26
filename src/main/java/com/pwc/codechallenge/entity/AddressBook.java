package com.pwc.codechallenge.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address_book")
public class AddressBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "Address book name is mandatory")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "addressBook", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Size(min = 1, message = "Atleast one person is required")
    private List<Person> persons;
}

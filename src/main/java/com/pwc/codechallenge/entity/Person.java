package com.pwc.codechallenge.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable, Comparable<Person>{
    public static final Comparator<Person> BY_NAME = new ByName();
    public static final Comparator<Person> BY_PHONE = new ByPhone();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Person name is mandatory")
    private String name;
    @NotBlank(message = "Person phone number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Pls enter a valid phone number")
    private String phoneNum;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "addressbook_id", nullable = false)
    private AddressBook addressBook;

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName());
    }

    private static class ByPhone implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getPhoneNum().compareTo(o2.getPhoneNum());
        }
    }

    private static class ByName implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}

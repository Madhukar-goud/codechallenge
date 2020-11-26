package com.pwc.codechallenge.repository;


import com.pwc.codechallenge.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {

    AddressBook findAddressBookByName(String name);

}

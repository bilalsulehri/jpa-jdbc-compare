package com.example.compare.pg.repository;

import com.example.compare.pg.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

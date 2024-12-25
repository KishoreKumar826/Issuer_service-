package com.example.library_IssuerMs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_IssuerMs.Domain.Issuer;

public interface repo extends JpaRepository<Issuer,Integer> {

}

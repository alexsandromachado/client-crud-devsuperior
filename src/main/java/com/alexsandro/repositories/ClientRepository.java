package com.alexsandro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexsandro.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}

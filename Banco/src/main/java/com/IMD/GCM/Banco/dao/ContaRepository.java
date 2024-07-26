package com.IMD.GCM.Banco.dao;

import com.IMD.GCM.Banco.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByNumero(String numero);
}
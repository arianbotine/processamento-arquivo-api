package com.file.conversor.repository;

import com.file.conversor.repository.entity.Produto;
import com.file.conversor.repository.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

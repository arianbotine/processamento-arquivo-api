package com.file.conversor.repository.dto;

import com.file.conversor.repository.entity.Pedido;
import com.file.conversor.repository.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoDto {

    private Long id;

    private Pedido pedido;

    private Produto produto;

    private Float valor;
}

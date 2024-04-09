package br.com.useinet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.useinet.model.Pedido;
import br.com.useinet.model.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido salvarPedido(Pedido pedido) {
		if (pedido.getDataCadastro() == null) {
			pedido.setDataCadastro(new Date());
		}
		if (pedido.getQuantidade() == 0) {
			pedido.setQuantidade(1);
		}
		if (pedido.getQuantidade() > 5 && pedido.getQuantidade() < 10) {
			pedido.setValorTotal(pedido.getValorUnitario() * pedido.getQuantidade() * 0.95);
		} else if (pedido.getQuantidade() >= 10) {
			pedido.setValorTotal(pedido.getValorUnitario() * pedido.getQuantidade() * 0.9);
		} else {
			pedido.setValorTotal(pedido.getValorUnitario() * pedido.getQuantidade());
		}

		return pedidoRepository.save(pedido);
	}

	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}

	public Pedido buscarPedidoPorNumeroControle(String numeroControle) {
		return pedidoRepository.findByNumeroControle(numeroControle);
	}
}

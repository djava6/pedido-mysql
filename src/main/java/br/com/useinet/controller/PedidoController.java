package br.com.useinet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.useinet.model.Pedido;
import br.com.useinet.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
		Pedido pedidoExistente = pedidoService.buscarPedidoPorNumeroControle(pedido.getNumeroControle());
		if (pedidoExistente != null) {
			return new ResponseEntity<>(pedidoExistente, HttpStatus.BAD_REQUEST);
		}
		Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
		return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> pedidos = pedidoService.listarPedidos();
		return new ResponseEntity<>(pedidos, HttpStatus.OK);
	}

	@GetMapping("/{numeroControle}")
	public ResponseEntity<Pedido> buscarPedidoPorNumeroControle(@PathVariable String numeroControle) {
		Pedido pedido = pedidoService.buscarPedidoPorNumeroControle(numeroControle);
		if (pedido == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
}

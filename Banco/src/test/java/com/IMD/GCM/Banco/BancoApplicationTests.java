package com.IMD.GCM.Banco;

import com.IMD.GCM.Banco.dao.ContaRepository;
import com.IMD.GCM.Banco.entity.Conta;
import com.IMD.GCM.Banco.entity.ContaBonus;
import com.IMD.GCM.Banco.entity.ContaPoupanca;
import com.IMD.GCM.Banco.entity.ContaSimples;
import com.IMD.GCM.Banco.exceptions.ContaNaoEncontradaException;
import com.IMD.GCM.Banco.exceptions.SaldoInsuficienteException;
import com.IMD.GCM.Banco.exceptions.ValorNegativoException;
import com.IMD.GCM.Banco.service.ContaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BancoApplicationTests {

	@Mock
	private ContaRepository contaRepository;

	@InjectMocks
	private ContaServiceImpl contaService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCadastrarContaSimples() {
		Conta conta = new ContaSimples();
		conta.setNumero("123");
		conta.setSaldo(100.0);

		when(contaRepository.save(any(Conta.class))).thenReturn(conta);

		Conta result = contaService.cadastrarConta("123", 100.0, "SIMPLES");
		assertNotNull(result);
		assertInstanceOf(ContaSimples.class, result);
		assertEquals("123", result.getNumero());
		assertEquals(100, result.getSaldo());
	}

	@Test
	public void testCadastrarContaBonus() {
		Conta conta = new ContaBonus();
		conta.setNumero("123");
		conta.setSaldo(0.0);

		when(contaRepository.save(any(Conta.class))).thenReturn(conta);

		Conta result = contaService.cadastrarConta("123", 0.0, "BONUS");
		assertNotNull(result);
		assertInstanceOf(ContaBonus.class, result);
		assertEquals("123", result.getNumero());
		assertEquals(0.0, result.getSaldo());
	}

	@Test
	public void testCadastrarContaPoupanca() {
		Conta conta = new ContaPoupanca();
		conta.setNumero("123");
		conta.setSaldo(100.0);

		when(contaRepository.save(any(Conta.class))).thenReturn(conta);

		Conta result = contaService.cadastrarConta("123", 100.0, "POUPANCA");
		assertNotNull(result);
		assertInstanceOf(ContaPoupanca.class, result);
		assertEquals("123", result.getNumero());
		assertEquals(100.0, result.getSaldo());
	}

	// Testes para consultar conta
	@Test
	public void testConsultarContaSimples() {
		Conta conta = new ContaSimples();
		conta.setNumero("123");

		when(contaRepository.findByNumero("123")).thenReturn(conta);

		Conta result = contaService.consultarConta("123");
		assertNotNull(result);
		assertInstanceOf(ContaSimples.class, result);
		assertEquals("123", result.getNumero());
	}

	@Test
	public void testConsultarContaNaoEncontrada() {
		when(contaRepository.findByNumero("123")).thenReturn(null);

		assertThrows(ContaNaoEncontradaException.class, () -> contaService.consultarConta("123"));
	}

	// Teste para consultar saldo
	@Test
	public void testConsultarSaldo() {
		Conta conta = new ContaSimples();
		conta.setSaldo(100.0);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

		double saldo = contaService.consultarSaldo(1L);
		assertEquals(100.0, saldo);
	}

	// Testes para crédito
	@Test
	public void testCreditoNormal() {
		Conta conta = new ContaSimples();
		conta.setSaldo(100.0);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
		when(contaRepository.save(any(Conta.class))).thenReturn(conta);

		Conta result = contaService.credito(1L, 50.0);
		assertEquals(150.0, result.getSaldo());
	}

	@Test
	public void testCreditoValorNegativo() {
		assertThrows(ValorNegativoException.class, () -> contaService.credito(1L, -50.0));
	}

	@Test
	public void testCreditoContaBonus() {
		ContaBonus conta = new ContaBonus();
		conta.setSaldo(100.0);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
		when(contaRepository.save(any(Conta.class))).thenReturn(conta);

		Conta result = contaService.credito(1L, 50.0);
		assertEquals(150.0, result.getSaldo());
		assertEquals(10 + (int) (50.0 / 100), conta.getPontuacao());
	}

	// Testes para débito
	@Test
	public void testDebitoNormal() {
		Conta conta = new ContaSimples();
		conta.setSaldo(100.0);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
		when(contaRepository.save(any(Conta.class))).thenReturn(conta);

		Conta result = contaService.debito(1L, 50.0);
		assertEquals(50.0, result.getSaldo());
	}

	@Test
	public void testDebitoValorNegativo() {
		assertThrows(ValorNegativoException.class, () -> contaService.debito(1L, -50.0));
	}

	@Test
	public void testDebitoSaldoInsuficiente() {
		Conta conta = new ContaSimples();
		conta.setSaldo(100.0);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

		assertThrows(SaldoInsuficienteException.class, () -> contaService.debito(1L, 2000.0));
	}

	// Testes para transferência
	@Test
	public void testTransferenciaValorNegativo() {
		assertThrows(ValorNegativoException.class, () -> contaService.transferencia(1L, 2L, -50.0));
	}

	@Test
	public void testTransferenciaSaldoInsuficiente() {
		Conta contaOrigem = new ContaSimples();
		contaOrigem.setSaldo(100.0);
		Conta contaDestino = new ContaSimples();
		contaDestino.setSaldo(50.0);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(contaOrigem));
		when(contaRepository.findById(2L)).thenReturn(Optional.of(contaDestino));

		assertThrows(SaldoInsuficienteException.class, () -> contaService.transferencia(1L, 2L, 2000.0));
	}

	@Test
	public void testTransferenciaContaBonus() {
		Conta contaOrigem = new ContaSimples();
		contaOrigem.setSaldo(100.0);
		ContaBonus contaDestino = new ContaBonus();
		contaDestino.setSaldo(50.0);
		int pontuacaoEsperada = contaDestino.getPontuacao() + (int) (contaDestino.getSaldo()/150);

		when(contaRepository.findById(1L)).thenReturn(Optional.of(contaOrigem));
		when(contaRepository.findById(2L)).thenReturn(Optional.of(contaDestino));
		when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		contaService.transferencia(1L, 2L, 50.0);
		assertEquals(pontuacaoEsperada, contaDestino.getPontuacao());
	}

	// Teste para render juros
	@Test
	public void testRenderJuros() {
		ContaPoupanca conta = new ContaPoupanca();
		conta.setSaldo(100.0);

		when(contaRepository.findAll()).thenReturn(List.of(conta));
		when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		contaService.renderJuros(10.0);
		assertEquals(110.0, conta.getSaldo(), 0.01);  // Margem de erro de 0.01
	}

}
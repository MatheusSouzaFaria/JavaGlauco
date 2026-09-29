package com.fatec.itu.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.itu.demo.entites.Cliente;
import com.fatec.itu.demo.repositories.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado."));
    }

    public Cliente save(Cliente cliente) {
        validarCliente(cliente);

        if (cliente.getCpf() != null && repository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        cliente.setId(null);
        return repository.save(cliente);
    }

    public Cliente update(Cliente cliente, Long id) {
        validarCliente(cliente);

        Cliente clienteAtual = findById(id);

        if (cliente.getCpf() != null
                && !cliente.getCpf().equals(clienteAtual.getCpf())
                && repository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        clienteAtual.setNome(cliente.getNome());
        clienteAtual.setEmail(cliente.getEmail());
        clienteAtual.setTelefone(cliente.getTelefone());
        clienteAtual.setCpf(cliente.getCpf());

        return repository.save(clienteAtual);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cliente não cadastrado.");
        }

        repository.deleteById(id);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Os dados do cliente são obrigatórios.");
        }

        if (isBlank(cliente.getNome())) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }

        if (isBlank(cliente.getEmail())) {
            throw new IllegalArgumentException("O e-mail do cliente é obrigatório.");
        }

        if (isBlank(cliente.getCpf())) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }

        cliente.setNome(cliente.getNome().trim());
        cliente.setEmail(cliente.getEmail().trim());
        cliente.setCpf(cliente.getCpf().trim());

        if (cliente.getTelefone() != null) {
            cliente.setTelefone(cliente.getTelefone().trim());
        }
    }

    private boolean isBlank(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}

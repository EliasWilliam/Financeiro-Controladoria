package br.com.financeiro.model;


import br.com.financeiro.controllers.dto.ClienteDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String cpfCnpj;
    private String nome;
    private LocalDate dataNascimento;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public static Cliente from(ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        cliente.codigo = clienteDTO.getCodigo();
        cliente.nome = clienteDTO.getNome();
        cliente.cpfCnpj = clienteDTO.getCpfCnpj();
        cliente.dataNascimento = clienteDTO.getDataNascimento();
        return cliente;
    }
}

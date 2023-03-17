/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.time.LocalDate;

/**
 *
 * @author tiago
 */
public class Treinador extends Utilizador{
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Treinador() {
    }

    public Treinador(String nome, String email, LocalDate dataNascimento, String id, String pw, String tipo) {
        super(id, pw, tipo);
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


   
    
    
}



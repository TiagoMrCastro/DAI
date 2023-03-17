/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import javax.swing.JFrame;

/**
 *
 * @author tiago
 */
import javax.swing.*;
import java.awt.*;

public class TelaAdministrador extends JFrame {

    private JButton btnAdicionarUtilizador;
    private JButton btnRemoverUtilizador;
    private JButton btnEditarDadosUtilizadores;

    public TelaAdministrador() {
        setTitle("Tela Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adicionar um título no topo da tela
        JLabel titulo = new JLabel("GESTÃO DE UTILIZADORES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Criar painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new BoxLayout(panelBotoes, BoxLayout.Y_AXIS));

        // Adicionar botão "Adicionar Utilizador"
        btnAdicionarUtilizador = new JButton("Adicionar Utilizador");
        btnAdicionarUtilizador.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdicionarUtilizador.setMaximumSize(new Dimension(200, 30));
        panelBotoes.add(btnAdicionarUtilizador);
        panelBotoes.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre botões

        // Adicionar botão "Remover Utilizador"
        btnRemoverUtilizador = new JButton("Remover Utilizador");
        btnRemoverUtilizador.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRemoverUtilizador.setMaximumSize(new Dimension(200, 30));
        panelBotoes.add(btnRemoverUtilizador);
        panelBotoes.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre botões

        // Adicionar botão "Editar dados Utilizadores"
        btnEditarDadosUtilizadores = new JButton("Editar dados Utilizadores");
        btnEditarDadosUtilizadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditarDadosUtilizadores.setMaximumSize(new Dimension(200, 30));
        panelBotoes.add(btnEditarDadosUtilizadores);

        add(panelBotoes, BorderLayout.CENTER);

        // Configurar o tamanho da janela e centralizar
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
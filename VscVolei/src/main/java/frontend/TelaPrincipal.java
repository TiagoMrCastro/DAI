/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import javax.swing.*;
import java.awt.*;
import backend.Conexao;
import backend.Utilizador;
import backend.UtilizadorDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TelaPrincipal extends JFrame {

    private JTextField campoId;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoSair;
    private JLabel labelTitulo;
    private JLabel labelSubTitulo;
    private JLabel labelUsuario, labelBackground;

    ;
    
public TelaPrincipal() {
        super("VSC VOLEI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        // Cria um JLabel com a imagem de fundo
        JLabel labelBackground = new JLabel(new ImageIcon("C:\\Users\\tiago\\OneDrive\\Ambiente de Trabalho\\VscVolei\\src\\main\\resources\\Vitoria.jpg.jpeg"));
        labelBackground.setBounds(0, 0, 800, 600);
        add(labelBackground);

        labelTitulo = new JLabel("VSC VOLEI");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        labelTitulo.setBounds(270, 20, 200, 50);
        labelTitulo.setForeground(Color.WHITE); // Altera a cor do texto para branco
        labelTitulo.setHorizontalAlignment(JLabel.CENTER); // Centraliza o texto horizontalmente
        labelBackground.add(labelTitulo);

        labelSubTitulo = new JLabel("Autenticação");
        labelSubTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        labelSubTitulo.setBounds(270, 80, 200, 25);
        labelSubTitulo.setForeground(Color.WHITE); // Altera a cor do texto para branco
        labelSubTitulo.setHorizontalAlignment(JLabel.CENTER); // Centraliza o texto horizontalmente
        labelBackground.add(labelSubTitulo);

        labelUsuario = new JLabel("ID:");
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUsuario.setBounds(240, 130, 80, 25);
        labelUsuario.setForeground(Color.WHITE); // Altera a cor do texto para branco
        labelBackground.add(labelUsuario);

        campoId = new JTextField(10);
        campoId.setBounds(270, 130, 200, 25);
        labelBackground.add(campoId);

        JLabel labelSenha = new JLabel("PW:");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSenha.setBounds(240, 170, 80, 25);
        labelSenha.setForeground(Color.WHITE); // Altera a cor do texto para branco
        labelBackground.add(labelSenha);

        campoSenha = new JPasswordField(10);
        campoSenha.setBounds(270, 170, 200, 25);
        labelBackground.add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(280, 210, 80, 25);
        botaoEntrar.setHorizontalAlignment(JButton.CENTER); // Centraliza o botão horizontalmente
        labelBackground.add(botaoEntrar);

        botaoSair = new JButton("Sair");
        botaoSair.setBounds(370, 210, 80, 25);
        botaoSair.setHorizontalAlignment(JButton.CENTER); // Centraliza o botão horizontalmente
        labelBackground.add(botaoSair);

        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = campoId.getText();
                String pw = new String(campoSenha.getPassword());

                // Adicione a lógica para autenticar o usuário aqui.
                String tipoUsuario = autenticarUtilizador(id, pw);

                if (tipoUsuario == null) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "ID ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    TelaPrincipal.this.dispose();

                    switch (tipoUsuario) {
                        case "Administrador":
                            new TelaAdministrador().setVisible(true);
                            break;
                        case "Treinador":
                            new TelaTreinador().setVisible(true);
                            break;
                        case "Jogador":
                            new TelaJogador().setVisible(true);
                            break;
                    }
                }
            }
        });
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Finaliza a aplicação quando o botão Sair é clicado
            }
        });
    }

    private String autenticarUtilizador(String id, String pw) {
        String tipoUtilizador = null;

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            if (conn != null) {
                UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
                Utilizador utilizador = utilizadorDAO.buscarUtilizadorPorIdSenha(id, pw);

                if (utilizador != null) {
                    tipoUtilizador = utilizador.getTipo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoUtilizador;
    }

    public static void main(String[] args) {
        // Crie o usuário administrador
        Utilizador admin = new Utilizador("admin3", "admin3", "Administrador");

        // Crie uma instância da classe UtilizadorDAO
        UtilizadorDAO utilizadorDAO = new UtilizadorDAO();

        // Insira o usuário administrador na base de dados
        utilizadorDAO.inserirUtilizador(admin);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
}

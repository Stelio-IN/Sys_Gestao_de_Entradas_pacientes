/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.GestorController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    
    // Componentes
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    
    public TelaLogin() {
        super("Tela de Login");
        
        // Configurações da janela
        setLayout(null); // Layout manual (coordenadas)
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza
        
        // Criando componentes
        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(30, 30, 80, 25);
        add(labelUsuario);
        
        campoUsuario = new JTextField();
        campoUsuario.setBounds(100, 30, 200, 25);
        add(campoUsuario);
        
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(30, 70, 80, 25);
        add(labelSenha);
        
        campoSenha = new JPasswordField();
        campoSenha.setBounds(100, 70, 200, 25);
        add(campoSenha);
        
        botaoLogin = new JButton("Login");
        botaoLogin.setBounds(120, 110, 100, 30);
        add(botaoLogin);
        
        // Ação do botão
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                GestorController gestor = new GestorController();
                boolean result = gestor.loginGestor(usuario, senha);
                        // Aqui você pode fazer a validação dos dados
                if (result) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TelaLogin();
    }
}

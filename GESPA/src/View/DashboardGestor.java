/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.GestorController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardGestor extends JFrame {

    public DashboardGestor() {
        super("Dashboard do Gestor");
        setLayout(null);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Seção Médicos
        JLabel labelMedicos = new JLabel("Gestão de Médicos");
        labelMedicos.setBounds(50, 20, 200, 30);
        add(labelMedicos);

        JButton btnCadastrarMedico = new JButton("Cadastrar Médico");
        btnCadastrarMedico.setBounds(50, 60, 200, 30);
        add(btnCadastrarMedico);

        JButton btnEditarMedico = new JButton("Editar Médico");
        btnEditarMedico.setBounds(50, 100, 200, 30);
        add(btnEditarMedico);

        JButton btnDesativarMedico = new JButton("Desativar Médico");
        btnDesativarMedico.setBounds(50, 140, 200, 30);
        add(btnDesativarMedico);

        JButton btnReativarMedico = new JButton("Reativar Médico");
        btnReativarMedico.setBounds(50, 180, 200, 30);
        add(btnReativarMedico);

        JButton btnListarMedicos = new JButton("Listar Médicos");
        btnListarMedicos.setBounds(50, 220, 200, 30);
        add(btnListarMedicos);

        // Seção Recepcionistas
        JLabel labelRecepcionistas = new JLabel("Gestão de Recepcionistas");
        labelRecepcionistas.setBounds(300, 20, 250, 30);
        add(labelRecepcionistas);

        JButton btnCadastrarRecepcionista = new JButton("Cadastrar Recepcionista");
        btnCadastrarRecepcionista.setBounds(300, 60, 250, 30);
        add(btnCadastrarRecepcionista);

        JButton btnEditarRecepcionista = new JButton("Editar Recepcionista");
        btnEditarRecepcionista.setBounds(300, 100, 250, 30);
        add(btnEditarRecepcionista);

        JButton btnDesativarRecepcionista = new JButton("Desativar Recepcionista");
        btnDesativarRecepcionista.setBounds(300, 140, 250, 30);
        add(btnDesativarRecepcionista);

        JButton btnReativarRecepcionista = new JButton("Reativar Recepcionista");
        btnReativarRecepcionista.setBounds(300, 180, 250, 30);
        add(btnReativarRecepcionista);

        JButton btnListarRecepcionistas = new JButton("Listar Recepcionistas");
        btnListarRecepcionistas.setBounds(300, 220, 250, 30);
        add(btnListarRecepcionistas);

        // Seção Serviços
        JLabel labelServicos = new JLabel("Gestão de Serviços");
        labelServicos.setBounds(50, 270, 200, 30);
        add(labelServicos);

        JButton btnCadastrarServico = new JButton("Cadastrar Serviço");
        btnCadastrarServico.setBounds(50, 310, 200, 30);
        add(btnCadastrarServico);

        JButton btnEditarServico = new JButton("Editar Serviço");
        btnEditarServico.setBounds(50, 350, 200, 30);
        add(btnEditarServico);

        JButton btnDesativarServico = new JButton("Desativar Serviço");
        btnDesativarServico.setBounds(50, 390, 200, 30);
        add(btnDesativarServico);

        JButton btnReativarServico = new JButton("Reativar Serviço");
        btnReativarServico.setBounds(50, 430, 200, 30);
        add(btnReativarServico);

        JButton btnListarServicos = new JButton("Listar Serviços");
        btnListarServicos.setBounds(50, 470, 200, 30);
        add(btnListarServicos);

        // Seção Relatórios
        JLabel labelRelatorio = new JLabel("Relatórios");
        labelRelatorio.setBounds(300, 270, 200, 30);
        add(labelRelatorio);

        JButton btnRelatorios = new JButton("Ver Relatórios");
        btnRelatorios.setBounds(300, 310, 250, 30);
        add(btnRelatorios);

        // Ações dos botões (exemplo só com Listar)
        btnListarMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorController gestor = new GestorController();
                gestor.listarMedico();
            }
        });

        btnListarRecepcionistas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorController gestor = new GestorController();
                gestor.listarRecepcionistas();
            }
        });

        btnListarServicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorController gestor = new GestorController();
                gestor.listarServicos();
            }
        });

        btnRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // AQUI VOCÊ PODE CRIAR UMA TELA DE RELATÓRIOS
                JOptionPane.showMessageDialog(null, "Relatórios gerados com sucesso!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardGestor();
    }
}

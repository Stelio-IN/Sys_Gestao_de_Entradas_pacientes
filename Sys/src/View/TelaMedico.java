/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.*;
import Controller.*;
import javax.swing.table.DefaultTableModel;

public class TelaMedico extends JFrame {
    private Medico medico;
    private MedicoController medicoController;
    private PacienteController pacienteController;
    private ConsultaController consultaController;
    
    private JTabbedPane abas;
    private JPanel panelConsultas, panelAtendimento, panelPacientes;
    
    // Componentes da aba de Consultas
    private JTable tblConsultas;
    private JComboBox<String> cmbFiltroConsulta;
    private JButton btnIniciarConsulta, btnFinalizarConsulta;
    private JTextField txtPesquisaPaciente;
    
    // Componentes da aba de Atendimento
    private JTextArea txtDiagnostico, txtMedicacao, txtObservacoes;
    private JButton btnSalvarAtendimento;
    private Consulta consultaAtual;
    
    // Componentes da aba de Pacientes
    private JTable tblPacientes;
    private JTextField txtPesquisaHistorico;
    private JButton btnVerHistorico;
    
    public TelaMedico(Medico medico) {
        this.medico = medico;
        this.medicoController = new MedicoController();
        this.pacienteController = new PacienteController();
        this.consultaController = new ConsultaController();
        
        configurarJanela();
        inicializarComponentes();
        carregarDadosIniciais();
        
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Sistema Hospitalar - Médico: " + medico.getNome());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Barra de status
        JLabel lblStatus = new JLabel("Usuário: " + medico.getNome() + " | CRM: " + medico.getCrm() + 
                                    " | Especialidade: " + medico.getEspecialidade().getEspecialidade() + 
                                    " | " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        lblStatus.setBorder(BorderFactory.createEtchedBorder());
        add(lblStatus, BorderLayout.SOUTH);
        
        // Área principal com abas
        abas = new JTabbedPane();
        add(abas, BorderLayout.CENTER);
    }
    
    private void inicializarComponentes() {
        criarAbaConsultas();
        criarAbaAtendimento();
        criarAbaPacientes();
    }
    
    private void carregarDadosIniciais() {
        atualizarTabelaConsultas();
        atualizarTabelaPacientes();
    }
    
    private void criarAbaConsultas() {
        panelConsultas = new JPanel(new BorderLayout(10, 10));
        panelConsultas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel de pesquisa/filtro
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        panelFiltros.add(new JLabel("Filtrar:"));
        cmbFiltroConsulta = new JComboBox<>(new String[]{
            "Consultas de Hoje", 
            "Pendentes", 
            "Em Andamento", 
            "Todas as Consultas"
        });
        cmbFiltroConsulta.setSelectedIndex(0);
        cmbFiltroConsulta.addActionListener(e -> atualizarTabelaConsultas());
        panelFiltros.add(cmbFiltroConsulta);
        
        panelFiltros.add(new JLabel("Paciente:"));
        txtPesquisaPaciente = new JTextField(15);
        panelFiltros.add(txtPesquisaPaciente);
        
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> atualizarTabelaConsultas());
        panelFiltros.add(btnPesquisar);
        
        // Tabela de consultas
        String[] colunas = {"ID", "Data", "Hora", "Paciente", "Serviço", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblConsultas = new JTable(model);
        tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tblConsultas);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnIniciarConsulta = new JButton("Iniciar Consulta");
        btnIniciarConsulta.addActionListener(e -> iniciarConsulta());
        panelBotoes.add(btnIniciarConsulta);
        
        btnFinalizarConsulta = new JButton("Finalizar Consulta");
        btnFinalizarConsulta.addActionListener(e -> finalizarConsulta());
        btnFinalizarConsulta.setEnabled(false);
        panelBotoes.add(btnFinalizarConsulta);
        
        // Adicionar componentes ao painel principal
        panelConsultas.add(panelFiltros, BorderLayout.NORTH);
        panelConsultas.add(scrollPane, BorderLayout.CENTER);
        panelConsultas.add(panelBotoes, BorderLayout.SOUTH);
        
        abas.addTab("Minhas Consultas", panelConsultas);
    }
    
    private void criarAbaAtendimento() {
        panelAtendimento = new JPanel(new BorderLayout(10, 10));
        panelAtendimento.setBorder(BorderFactory.createTitledBorder("Atendimento Médico"));
        
        // Painel de informações da consulta
        JPanel panelInfoConsulta = new JPanel(new GridLayout(0, 2, 5, 5));
        panelInfoConsulta.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblPaciente = new JLabel("Paciente:");
        JTextField txtPacienteInfo = new JTextField();
        txtPacienteInfo.setEditable(false);
        panelInfoConsulta.add(lblPaciente);
        panelInfoConsulta.add(txtPacienteInfo);
        
        JLabel lblDataHora = new JLabel("Data/Hora:");
        JTextField txtDataHoraInfo = new JTextField();
        txtDataHoraInfo.setEditable(false);
        panelInfoConsulta.add(lblDataHora);
        panelInfoConsulta.add(txtDataHoraInfo);
        
        JLabel lblServico = new JLabel("Serviço:");
        JTextField txtServicoInfo = new JTextField();
        txtServicoInfo.setEditable(false);
        panelInfoConsulta.add(lblServico);
        panelInfoConsulta.add(txtServicoInfo);
        
        // Painel de formulário de atendimento
        JPanel panelFormulario = new JPanel(new BorderLayout(5, 5));
        
        JPanel panelDiagnostico = new JPanel(new BorderLayout());
        panelDiagnostico.add(new JLabel("Diagnóstico:"), BorderLayout.NORTH);
        txtDiagnostico = new JTextArea(5, 30);
        txtDiagnostico.setLineWrap(true);
        panelDiagnostico.add(new JScrollPane(txtDiagnostico), BorderLayout.CENTER);
        
        JPanel panelMedicacao = new JPanel(new BorderLayout());
        panelMedicacao.add(new JLabel("Medicação:"), BorderLayout.NORTH);
        txtMedicacao = new JTextArea(3, 30);
        txtMedicacao.setLineWrap(true);
        panelMedicacao.add(new JScrollPane(txtMedicacao), BorderLayout.CENTER);
        
        JPanel panelObservacoes = new JPanel(new BorderLayout());
        panelObservacoes.add(new JLabel("Observações:"), BorderLayout.NORTH);
        txtObservacoes = new JTextArea(3, 30);
        txtObservacoes.setLineWrap(true);
        panelObservacoes.add(new JScrollPane(txtObservacoes), BorderLayout.CENTER);
        
        panelFormulario.add(panelDiagnostico, BorderLayout.NORTH);
        panelFormulario.add(panelMedicacao, BorderLayout.CENTER);
        panelFormulario.add(panelObservacoes, BorderLayout.SOUTH);
        
        // Painel de botões
        btnSalvarAtendimento = new JButton("Salvar Atendimento");
        btnSalvarAtendimento.addActionListener(e -> salvarAtendimento());
        btnSalvarAtendimento.setEnabled(false);
        
        // Adicionar componentes ao painel principal
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelInfoConsulta, BorderLayout.NORTH);
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        
        panelAtendimento.add(panelSuperior, BorderLayout.CENTER);
        panelAtendimento.add(btnSalvarAtendimento, BorderLayout.SOUTH);
        
        abas.addTab("Atendimento", panelAtendimento);
    }
    
    private void criarAbaPacientes() {
        panelPacientes = new JPanel(new BorderLayout(10, 10));
        panelPacientes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel de pesquisa
        JPanel panelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelPesquisa.add(new JLabel("Pesquisar Paciente:"));
        txtPesquisaHistorico = new JTextField(20);
        panelPesquisa.add(txtPesquisaHistorico);
        
        btnVerHistorico = new JButton("Ver Histórico");
        btnVerHistorico.addActionListener(e -> verHistoricoPaciente());
        panelPesquisa.add(btnVerHistorico);
        
        // Tabela de pacientes
        String[] colunas = {"BI", "Nome", "Data Nasc.", "Telefone", "Gênero"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPacientes = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblPacientes);
        
        // Adicionar componentes ao painel principal
        panelPacientes.add(panelPesquisa, BorderLayout.NORTH);
        panelPacientes.add(scrollPane, BorderLayout.CENTER);
        
        abas.addTab("Pacientes", panelPacientes);
    }
    
    private void atualizarTabelaConsultas() {
        DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
        model.setRowCount(0); // Limpar tabela
        
        String filtro = cmbFiltroConsulta.getSelectedItem().toString();
        String termoPaciente = txtPesquisaPaciente.getText().trim();
        
        // Obter consultas do médico
        java.util.List<Consulta> consultas = consultaController.listarConsultasPorMedico(medico.getCrm());
        
        // Aplicar filtros
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String hoje = dateFormat.format(new Date());
        
        for (Consulta c : consultas) {
            // Filtrar por termo de pesquisa
            if (!termoPaciente.isEmpty() && 
                !c.getPaciente().getNome().toLowerCase().contains(termoPaciente.toLowerCase()) &&
                !c.getPaciente().getBI().contains(termoPaciente)) {
                continue;
            }
            
            // Filtrar por status/data
            switch (filtro) {
                case "Consultas de Hoje":
                    if (!dateFormat.format(c.getData()).equals(hoje)) continue;
                    break;
                case "Pendentes":
                    if (!c.getStatus().equals("Pendente")) continue;
                    break;
                case "Em Andamento":
                    if (!c.getStatus().equals("Em Andamento")) continue;
                    break;
                // "Todas as Consultas" não aplica filtro adicional
            }
            
            model.addRow(new Object[]{
                c.getId(),
                dateFormat.format(c.getData()),
                c.getHora(),
                c.getPaciente().getNome(),
                c.getServico().getNomeServico(),
                c.getStatus()
            });
        }
    }
    
    private void atualizarTabelaPacientes() {
        DefaultTableModel model = (DefaultTableModel) tblPacientes.getModel();
        model.setRowCount(0); // Limpar tabela
        
        String termo = txtPesquisaHistorico.getText().trim();
        java.util.List<Paciente> pacientes;
        
        if (termo.isEmpty()) {
            // Listar pacientes atendidos pelo médico
            pacientes = consultaController.listarConsultasPorMedico(medico.getCrm()).stream()
                .map(Consulta::getPaciente)
                .distinct()
                .toList();
        } else {
            // Filtrar pacientes
            pacientes = pacienteController.filtrarPacientes(termo).stream()
                .filter(p -> consultaController.listarConsultasPorMedico(medico.getCrm()).stream()
                    .anyMatch(c -> c.getPaciente().getBI().equals(p.getBI())))
                .toList();
        }
        
        // Preencher tabela
        for (Paciente p : pacientes) {
            model.addRow(new Object[]{
                p.getBI(),
                p.getNome(),
                p.getDataNasc(),
                p.getTelefone(),
                p.getGenero()
            });
        }
    }
    
    private void iniciarConsulta() {
        int linhaSelecionada = tblConsultas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para iniciar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = tblConsultas.getValueAt(linhaSelecionada, 0).toString();
        String statusConsulta = tblConsultas.getValueAt(linhaSelecionada, 5).toString();
        
        if (!statusConsulta.equals("Pendente")) {
            JOptionPane.showMessageDialog(this, 
                "Só é possível iniciar consultas com status 'Pendente'", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        consultaAtual = consultaController.buscarConsultaPorId(idConsulta);
        if (consultaAtual == null) {
            JOptionPane.showMessageDialog(this, "Consulta não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Atualizar status da consulta
        consultaController.atualizarStatusConsulta(idConsulta, "Em Andamento");
        
        // Preencher informações na aba de atendimento
        JPanel panelInfo = (JPanel) ((JPanel) panelAtendimento.getComponent(0)).getComponent(0);
        
        ((JTextField) panelInfo.getComponent(1)).setText(consultaAtual.getPaciente().getNome());
        ((JTextField) panelInfo.getComponent(3)).setText(
            new SimpleDateFormat("dd/MM/yyyy HH:mm").format(consultaAtual.getData()) + " - " + consultaAtual.getHora());
        ((JTextField) panelInfo.getComponent(5)).setText(consultaAtual.getServico().getNomeServico());
        
        // Limpar campos de atendimento
        txtDiagnostico.setText("");
        txtMedicacao.setText("");
        txtObservacoes.setText("");
        
        // Habilitar edição e botão de salvar
        txtDiagnostico.setEditable(true);
        txtMedicacao.setEditable(true);
        txtObservacoes.setEditable(true);
        btnSalvarAtendimento.setEnabled(true);
        btnFinalizarConsulta.setEnabled(true);
        
        // Mudar para aba de atendimento
        abas.setSelectedIndex(1);
        
        JOptionPane.showMessageDialog(this, "Consulta iniciada com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void finalizarConsulta() {
        if (consultaAtual == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma consulta em andamento", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(
            this, 
            "Tem certeza que deseja finalizar esta consulta?", 
            "Confirmar Finalização", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            salvarAtendimento();
            
            // Atualizar status da consulta
            consultaController.atualizarStatusConsulta(consultaAtual.getId(), "Finalizada");
            
            // Desabilitar edição e botão de salvar
            txtDiagnostico.setEditable(false);
            txtMedicacao.setEditable(false);
            txtObservacoes.setEditable(false);
            btnSalvarAtendimento.setEnabled(false);
            btnFinalizarConsulta.setEnabled(false);
            
            // Atualizar tabela de consultas
            atualizarTabelaConsultas();
            
            JOptionPane.showMessageDialog(this, "Consulta finalizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void salvarAtendimento() {
        if (consultaAtual == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma consulta em andamento", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String diagnostico = txtDiagnostico.getText().trim();
        String medicacao = txtMedicacao.getText().trim();
        String observacoes = txtObservacoes.getText().trim();
        
        if (diagnostico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Diagnóstico é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Registrar atendimento
        consultaController.registrarDiagnostico(
            consultaAtual.getId(), 
            diagnostico, 
            medicacao, 
            observacoes
        );
        
        JOptionPane.showMessageDialog(this, "Atendimento salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void verHistoricoPaciente() {
        int linhaSelecionada = tblPacientes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para ver o histórico", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String biPaciente = tblPacientes.getValueAt(linhaSelecionada, 0).toString();
        Paciente paciente = (Paciente) pacienteController.buscarPorBi(biPaciente);
        
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Criar diálogo para exibir histórico
        JDialog dialog = new JDialog(this, "Histórico Médico - " + paciente.getNome(), true);
        dialog.setSize(800, 600);
        dialog.setLayout(new BorderLayout());
        
        // Tabela de histórico
        String[] colunas = {"Data", "Hora", "Serviço", "Diagnóstico", "Medicação"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable tblHistorico = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblHistorico);
        
        // Obter histórico do paciente com este médico
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        consultaController.listarConsultasPorMedico(medico.getCrm()).stream()
            .filter(c -> c.getPaciente().getBI().equals(biPaciente))
            .filter(c -> c.getStatus().equals("Finalizada"))
            .forEach(c -> {
                model.addRow(new Object[]{
                    dateFormat.format(c.getData()),
                    c.getHora(),
                    c.getServico().getNomeServico(),
                    c.getDiagnostico(),
                    c.getMedicacao()
                });
            });
        
        // Painel de informações do paciente
        JPanel panelInfo = new JPanel(new GridLayout(0, 2, 5, 5));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelInfo.add(new JLabel("Paciente:"));
        panelInfo.add(new JLabel(paciente.getNome()));
        panelInfo.add(new JLabel("BI:"));
        panelInfo.add(new JLabel(paciente.getBI()));
        panelInfo.add(new JLabel("Data Nasc.:"));
        panelInfo.add(new JLabel(paciente.getDataNasc()));
        panelInfo.add(new JLabel("Gênero:"));
        panelInfo.add(new JLabel(paciente.getGenero()));
        
        // Adicionar componentes ao diálogo
        dialog.add(panelInfo, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dialog.dispose());
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnFechar);
        dialog.add(panelBotoes, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Para teste - criar um médico mock
        Especialidade especialidade = new Especialidade("CARDIOLOGIA", "Especialidade em doenças do coração");
        Medico medicoTeste = new Medico(
            especialidade, "CRM12345", "MED001", 
            "senha123", "111111111", "Dr. João Silva", 
            "919999999", "Av. Principal, 100"
        );
        
        SwingUtilities.invokeLater(() -> new TelaMedico(medicoTeste));
    }
}
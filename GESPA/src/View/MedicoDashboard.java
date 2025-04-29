package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.Especialidade;
import Model.Medico;
import Model.Paciente;
import Model.Consulta;
import Model.Consulta.StatusConsulta;
import Model.Servico;
import Controller.MedicoController;

public class MedicoDashboard extends JFrame {
    
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel perfilPanel, pacientesPanel, consultasPanel, historicosPanel;
    private MedicoController controller;
    private Medico medicoLogado;
    
    // Componentes da aba Perfil
    private JTextField txtCrm, txtNome, txtBi, txtTelefone, txtEndereco, txtCodFunc;
    private JTextField txtEspecialidade; // Alterado para TextField já que Especialidade não é enum
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnAtualizarPerfil;
    
    // Componentes da aba Pacientes
    private JTable tblPacientes;
    private DefaultTableModel pacienteTableModel;
    private JTextField txtPesquisaPaciente;
    private JButton btnPesquisar, btnVerDetalhes;
    
    // Componentes da aba Consultas
    private JTable tblConsultas;
    private DefaultTableModel consultaTableModel;
    private JComboBox<String> cmbFiltroConsulta;
    private JButton btnIniciarConsulta, btnFinalizarConsulta, btnCancelarConsulta;
    
    // Componentes da aba Históricos
    private JTable tblHistorico;
    private DefaultTableModel historicoTableModel;
    private JTextArea txtObservacao;
    private JButton btnAdicionarObservacao;
    private JTextField txtIdPaciente;
    
    public MedicoDashboard(String crm) {
        controller = new MedicoController();
        medicoLogado = controller.buscarMedicoPorCrm(crm);
        
        if (medicoLogado == null) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado no sistema!", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        // Configuração do frame principal
        setTitle("Dashboard do Médico - Sistema Hospitalar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicialização dos componentes
        initComponents();
        
        // Carregamento inicial de dados
        carregarDados();
        
        setVisible(true);
    }
    
    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(10, 10, 765, 540);
        
        // Inicialização das abas
        initPerfilPanel();
        initPacientesPanel();
        initConsultasPanel();
        initHistoricosPanel();
        
        // Adicionar abas ao TabbedPane
        tabbedPane.addTab("Meu Perfil", perfilPanel);
        tabbedPane.addTab("Pacientes", pacientesPanel);
        tabbedPane.addTab("Consultas", consultasPanel);
        tabbedPane.addTab("Históricos", historicosPanel);
        
        mainPanel.add(tabbedPane);
        
        add(mainPanel);
    }
    
    private void initPerfilPanel() {
        perfilPanel = new JPanel();
        perfilPanel.setLayout(null);
        
        // Área do perfil
        JPanel panelDados = new JPanel();
        panelDados.setLayout(null);
        panelDados.setBounds(10, 10, 740, 490);
        panelDados.setBorder(BorderFactory.createTitledBorder("Meus Dados"));
        
        // Labels
        JLabel lblCrm = new JLabel("CRM:");
        lblCrm.setBounds(20, 30, 100, 25);
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 70, 100, 25);
        JLabel lblBi = new JLabel("BI:");
        lblBi.setBounds(20, 110, 100, 25);
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 150, 100, 25);
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(20, 190, 100, 25);
        JLabel lblCodFunc = new JLabel("Cód. Func.:");
        lblCodFunc.setBounds(20, 230, 100, 25);
        JLabel lblEspecialidade = new JLabel("Especialidade:");
        lblEspecialidade.setBounds(20, 270, 100, 25);
        JLabel lblPassword = new JLabel("Nova Password:");
        lblPassword.setBounds(20, 310, 100, 25);
        JLabel lblConfirmPassword = new JLabel("Confirmar Password:");
        lblConfirmPassword.setBounds(20, 350, 150, 25);
        
        // Campos de texto
        txtCrm = new JTextField();
        txtCrm.setBounds(170, 30, 200, 25);
        txtCrm.setEditable(false); // O CRM não pode ser alterado
        
        txtNome = new JTextField();
        txtNome.setBounds(170, 70, 200, 25);
        txtNome.setEditable(false); // O nome não pode ser alterado
        
        txtBi = new JTextField();
        txtBi.setBounds(170, 110, 200, 25);
        txtBi.setEditable(false); // O BI não pode ser alterado
        
        txtTelefone = new JTextField();
        txtTelefone.setBounds(170, 150, 200, 25);
        
        txtEndereco = new JTextField();
        txtEndereco.setBounds(170, 190, 400, 25);
        
        txtCodFunc = new JTextField();
        txtCodFunc.setBounds(170, 230, 200, 25);
        txtCodFunc.setEditable(false); // O código de funcionário não pode ser alterado
        
        // Alterado para TextField já que Especialidade não é enum no código fornecido
        txtEspecialidade = new JTextField();
        txtEspecialidade.setBounds(170, 270, 200, 25);
        txtEspecialidade.setEditable(false); // A especialidade não pode ser alterada pelo médico
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 310, 200, 25);
        
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(170, 350, 200, 25);
        
        // Botão Atualizar
        btnAtualizarPerfil = new JButton("Atualizar Meus Dados");
        btnAtualizarPerfil.setBounds(170, 400, 200, 30);
        btnAtualizarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPerfil();
            }
        });
        
        // Adicionar componentes ao painel
        panelDados.add(lblCrm);
        panelDados.add(txtCrm);
        panelDados.add(lblNome);
        panelDados.add(txtNome);
        panelDados.add(lblBi);
        panelDados.add(txtBi);
        panelDados.add(lblTelefone);
        panelDados.add(txtTelefone);
        panelDados.add(lblEndereco);
        panelDados.add(txtEndereco);
        panelDados.add(lblCodFunc);
        panelDados.add(txtCodFunc);
        panelDados.add(lblEspecialidade);
        panelDados.add(txtEspecialidade);
        panelDados.add(lblPassword);
        panelDados.add(txtPassword);
        panelDados.add(lblConfirmPassword);
        panelDados.add(txtConfirmPassword);
        panelDados.add(btnAtualizarPerfil);
        
        perfilPanel.add(panelDados);
    }
    
    private void initPacientesPanel() {
        pacientesPanel = new JPanel();
        pacientesPanel.setLayout(null);
        
        // Área de pesquisa
        JPanel panelPesquisa = new JPanel();
        panelPesquisa.setLayout(null);
        panelPesquisa.setBounds(10, 10, 740, 80);
        panelPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisar Pacientes"));
        
        JLabel lblPesquisa = new JLabel("Nome ou BI:");
        lblPesquisa.setBounds(20, 30, 150, 25);
        
        txtPesquisaPaciente = new JTextField();
        txtPesquisaPaciente.setBounds(170, 30, 300, 25);
        
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBounds(480, 30, 100, 25);
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPacientes();
            }
        });
        
        btnVerDetalhes = new JButton("Ver Detalhes");
        btnVerDetalhes.setBounds(590, 30, 120, 25);
        btnVerDetalhes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetalhesPaciente();
            }
        });
        
        panelPesquisa.add(lblPesquisa);
        panelPesquisa.add(txtPesquisaPaciente);
        panelPesquisa.add(btnPesquisar);
        panelPesquisa.add(btnVerDetalhes);
        
        // Tabela de pacientes
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 100, 740, 400);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Meus Pacientes"));
        
        // Ajustado para corresponder ao modelo de Paciente no controlador
        String[] colunas = {"BI", "Nome", "Ano Nascimento", "Telefone", "Endereço"};
        pacienteTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPacientes = new JTable(pacienteTableModel);
        JScrollPane scrollPane = new JScrollPane(tblPacientes);
        scrollPane.setBounds(10, 20, 720, 370);
        panelTabela.add(scrollPane);
        
        pacientesPanel.add(panelPesquisa);
        pacientesPanel.add(panelTabela);
    }
    
    private void initConsultasPanel() {
        consultasPanel = new JPanel();
        consultasPanel.setLayout(null);
        
        // Área de filtro
        JPanel panelFiltro = new JPanel();
        panelFiltro.setLayout(null);
        panelFiltro.setBounds(10, 10, 740, 80);
        panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtrar Consultas"));
        
        JLabel lblFiltro = new JLabel("Mostrar:");
        lblFiltro.setBounds(20, 30, 80, 25);
        
        String[] opcoesFiltro = {"Todas as Consultas", "Consultas para Hoje", "Consultas Pendentes", "Consultas Realizadas", "Consultas Canceladas", "Consultas Em Andamento"};
        cmbFiltroConsulta = new JComboBox<>(opcoesFiltro);
        cmbFiltroConsulta.setBounds(100, 30, 200, 25);
        cmbFiltroConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarConsultas();
            }
        });
        
        btnIniciarConsulta = new JButton("Iniciar Consulta");
        btnIniciarConsulta.setBounds(310, 30, 130, 25);
        btnIniciarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarConsulta();
            }
        });
        
        btnFinalizarConsulta = new JButton("Finalizar Consulta");
        btnFinalizarConsulta.setBounds(450, 30, 140, 25);
        btnFinalizarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarConsulta();
            }
        });
        
        btnCancelarConsulta = new JButton("Cancelar");
        btnCancelarConsulta.setBounds(600, 30, 100, 25);
        btnCancelarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarConsulta();
            }
        });
        
        panelFiltro.add(lblFiltro);
        panelFiltro.add(cmbFiltroConsulta);
        panelFiltro.add(btnIniciarConsulta);
        panelFiltro.add(btnFinalizarConsulta);
        panelFiltro.add(btnCancelarConsulta);
        
        // Tabela de consultas
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 100, 740, 400);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Minhas Consultas"));
        
        String[] colunas = {"ID", "Paciente", "Data", "Hora", "Serviço", "Status"};
        consultaTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblConsultas = new JTable(consultaTableModel);
        JScrollPane scrollPane = new JScrollPane(tblConsultas);
        scrollPane.setBounds(10, 20, 720, 370);
        panelTabela.add(scrollPane);
        
        consultasPanel.add(panelFiltro);
        consultasPanel.add(panelTabela);
    }
    
    private void initHistoricosPanel() {
        historicosPanel = new JPanel();
        historicosPanel.setLayout(null);
        
        // Área de identificação do paciente
        JPanel panelIdentificacao = new JPanel();
        panelIdentificacao.setLayout(null);
        panelIdentificacao.setBounds(10, 10, 740, 60);
        panelIdentificacao.setBorder(BorderFactory.createTitledBorder("Paciente"));
        
        JLabel lblIdPaciente = new JLabel("BI do Paciente:");
        lblIdPaciente.setBounds(20, 25, 150, 25);
        
        txtIdPaciente = new JTextField();
        txtIdPaciente.setBounds(180, 25, 150, 25);
        
        JButton btnBuscarHistorico = new JButton("Buscar Histórico");
        btnBuscarHistorico.setBounds(350, 25, 150, 25);
        btnBuscarHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHistoricoPaciente();
            }
        });
        
        panelIdentificacao.add(lblIdPaciente);
        panelIdentificacao.add(txtIdPaciente);
        panelIdentificacao.add(btnBuscarHistorico);
        
        // Tabela de histórico
        JPanel panelHistorico = new JPanel();
        panelHistorico.setLayout(null);
        panelHistorico.setBounds(10, 80, 740, 250);
        panelHistorico.setBorder(BorderFactory.createTitledBorder("Histórico de Consultas"));
        
        String[] colunas = {"Data", "Médico", "Serviço", "Diagnóstico", "Medicação"};
        historicoTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblHistorico = new JTable(historicoTableModel);
        JScrollPane scrollPane = new JScrollPane(tblHistorico);
        scrollPane.setBounds(10, 20, 720, 220);
        panelHistorico.add(scrollPane);
        
        // Área de observações
        JPanel panelObservacao = new JPanel();
        panelObservacao.setLayout(null);
        panelObservacao.setBounds(10, 340, 740, 160);
        panelObservacao.setBorder(BorderFactory.createTitledBorder("Adicionar Observação ao Histórico"));
        
        txtObservacao = new JTextArea();
        txtObservacao.setLineWrap(true);
        txtObservacao.setWrapStyleWord(true);
        JScrollPane scrollObservacao = new JScrollPane(txtObservacao);
        scrollObservacao.setBounds(10, 20, 720, 100);
        
        btnAdicionarObservacao = new JButton("Adicionar Observação");
        btnAdicionarObservacao.setBounds(10, 125, 180, 25);
        btnAdicionarObservacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarObservacao();
            }
        });
        
        panelObservacao.add(scrollObservacao);
        panelObservacao.add(btnAdicionarObservacao);
        
        historicosPanel.add(panelIdentificacao);
        historicosPanel.add(panelHistorico);
        historicosPanel.add(panelObservacao);
    }
    
    private void carregarDados() {
        // Carregar dados do médico logado
        if (medicoLogado != null) {
            txtCrm.setText(medicoLogado.getCrm());
            txtNome.setText(medicoLogado.getNome());
            txtBi.setText(medicoLogado.getBI());
            txtTelefone.setText(medicoLogado.getTelefone());
            txtEndereco.setText(medicoLogado.getEndereco());
            txtCodFunc.setText(medicoLogado.getCod_func());
            txtEspecialidade.setText(medicoLogado.getEspecialidade().getEspecialidade());
        }
        
        // Carregar listas de pacientes e consultas
        carregarPacientes();
        carregarConsultas();
    }
    
    private void carregarPacientes() {
        // Limpar tabela
        pacienteTableModel.setRowCount(0);
        
        // Buscar pacientes do médico atual
        ArrayList<Paciente> pacientes = controller.listarPacientesPorMedico(medicoLogado.getCrm());
        
        // Preencher tabela
        for (Paciente p : pacientes) {
            Object[] row = {
                p.getBI(),
                p.getNome(),
                p.getDataNasc(),
                p.getTelefone(),
                p.getEndereco()
            };
            pacienteTableModel.addRow(row);
        }
    }
    
    private void carregarConsultas() {
        // Limpar tabela
        consultaTableModel.setRowCount(0);
        
        // Buscar consultas do médico atual
        ArrayList<Consulta> consultas = controller.listarConsultasPorMedico(medicoLogado.getCrm());
        
        // Preencher tabela
        for (Consulta c : consultas) {
            Object[] row = {
                c.getId(),
                c.getPaciente().getNome(),
                c.getData(),
                c.getHora(),
                c.getServico().getNomeServico(),
                c.getStatus()
            };
            consultaTableModel.addRow(row);
        }
    }
    
    // Métodos de ação
    
    private void atualizarPerfil() {
        try {
            String telefone = txtTelefone.getText();
            String endereco = txtEndereco.getText();
            String senha = new String(txtPassword.getPassword());
            String confirmSenha = new String(txtConfirmPassword.getPassword());
            
            // Validações
            if (telefone.isEmpty() || endereco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Telefone e Endereço são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!senha.isEmpty() && !senha.equals(confirmSenha)) {
                JOptionPane.showMessageDialog(this, "As senhas não conferem!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Se a senha estiver vazia, mantém a senha atual
            if (senha.isEmpty()) {
                senha = medicoLogado.getPassword();
            }
            
            // Atualizar dados no controller
            boolean sucesso = controller.atualizarPerfilMedico(medicoLogado.getCrm(), senha, endereco, telefone);
            
            if (sucesso) {
                // Atualizar objeto local
                medicoLogado.setTelefone(telefone);
                medicoLogado.setEndereco(endereco);
                medicoLogado.setPassword(senha);
                
                JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                // Limpar campos de senha
                txtPassword.setText("");
                txtConfirmPassword.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar perfil!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar perfil: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void pesquisarPacientes() {
        String termo = txtPesquisaPaciente.getText().trim();
        
        if (termo.isEmpty()) {
            carregarPacientes(); // Se vazio, carrega todos
            return;
        }
        
        // Limpar tabela
        pacienteTableModel.setRowCount(0);
        
        // Buscar pacientes pelo termo
        ArrayList<Paciente> pacientes = controller.pesquisarPacientes(medicoLogado.getCrm(), termo);
        
        // Preencher tabela
        for (Paciente p : pacientes) {
            Object[] row = {
                p.getBI(),
                p.getNome(),
                p.getDataNasc(),
                p.getTelefone(),
                p.getEndereco()
            };
            pacienteTableModel.addRow(row);
        }
    }
    
    private void verDetalhesPaciente() {
        int selectedRow = tblPacientes.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para ver os detalhes!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String bi = tblPacientes.getValueAt(selectedRow, 0).toString();
        Paciente paciente = controller.buscarPacientePorBI(bi);
        
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Mostrar detalhes em uma janela de diálogo
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("BI: ").append(paciente.getBI()).append("\n");
        detalhes.append("Nome: ").append(paciente.getNome()).append("\n");
        detalhes.append("Ano de Nascimento: ").append(paciente.getDataNasc()).append("\n");
        detalhes.append("Telefone: ").append(paciente.getTelefone()).append("\n");
        detalhes.append("Endereço: ").append(paciente.getEndereco()).append("\n");
        detalhes.append("Gênero: ").append(paciente.getGenero()).append("\n");
        
        JTextArea textArea = new JTextArea(detalhes.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Detalhes do Paciente", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void filtrarConsultas() {
        String filtro = (String) cmbFiltroConsulta.getSelectedItem();
        
        // Limpar tabela
        consultaTableModel.setRowCount(0);
        
        // Buscar consultas de acordo com o filtro
        ArrayList<Consulta> consultas = controller.filtrarConsultas(medicoLogado.getCrm(), filtro);
        
        // Preencher tabela
        for (Consulta c : consultas) {
            Object[] row = {
                c.getId(),
                c.getPaciente().getNome(),
                c.getData(),
                c.getHora(),
                c.getServico().getNomeServico(),
                c.getStatus()
            };
            consultaTableModel.addRow(row);
        }
    }
    
    private void iniciarConsulta() {
        int selectedRow = tblConsultas.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para iniciar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = tblConsultas.getValueAt(selectedRow, 0).toString();
        String status = tblConsultas.getValueAt(selectedRow, 5).toString();
        
        if (!status.equals("PENDENTE")) {
            JOptionPane.showMessageDialog(this, "Apenas consultas pendentes podem ser iniciadas!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Atualizar status para "Em Andamento"
        boolean sucesso = controller.atualizarStatusConsulta(idConsulta, StatusConsulta.EM_ANDAMENTO);
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Consulta iniciada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Recarregar tabela
            carregarConsultas();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao iniciar consulta!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void finalizarConsulta() {
        int selectedRow = tblConsultas.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para finalizar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = tblConsultas.getValueAt(selectedRow, 0).toString();
        String status = tblConsultas.getValueAt(selectedRow, 5).toString();
        
        if (!status.equals("EM_ANDAMENTO")) {
            JOptionPane.showMessageDialog(this, "Apenas consultas em andamento podem ser finalizadas!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Pedir informações sobre a consulta
        JTextField txtDiagnostico = new JTextField();
        JTextField txtMedicacao = new JTextField();
        JTextArea txtObservacoes = new JTextArea(5, 20);
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);
        JScrollPane scrollObservacoes = new JScrollPane(txtObservacoes);
        
        Object[] message = {
            "Diagnóstico:", txtDiagnostico,
            "Medicação:", txtMedicacao,
            "Observações:", scrollObservacoes
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Finalizar Consulta", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String diagnostico = txtDiagnostico.getText();
            String medicacao = txtMedicacao.getText();
            String observacoes = txtObservacoes.getText();
            
            if (diagnostico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O diagnóstico é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Atualizar status para "Finalizada"
            boolean sucesso = controller.finalizarConsulta(idConsulta, diagnostico, medicacao, observacoes);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Consulta finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Recarregar tabela
                carregarConsultas();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao finalizar consulta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cancelarConsulta() {
        int selectedRow = tblConsultas.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para cancelar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = tblConsultas.getValueAt(selectedRow, 0).toString();
        String status = tblConsultas.getValueAt(selectedRow, 5).toString();
        
        if (status.equals("FINALIZADA") || status.equals("CANCELADA")) {
            JOptionPane.showMessageDialog(this, "Esta consulta não pode ser cancelada!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirmação
        int option = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar esta consulta?", "Confirmar Cancelamento", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            // Solicitar motivo do cancelamento
            String motivo = JOptionPane.showInputDialog(this, "Motivo do cancelamento:");
            
            if (motivo == null || motivo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "É necessário informar o motivo do cancelamento!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Atualizar status para "Cancelada"
            boolean sucesso = controller.cancelarConsulta(idConsulta, motivo);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Recarregar tabela
                carregarConsultas();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cancelar consulta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void buscarHistoricoPaciente() {
        String bi = txtIdPaciente.getText().trim();
        
        if (bi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o BI do paciente!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar se o paciente existe
        Paciente paciente = controller.buscarPacientePorBI(bi);
        
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Limpar tabela
        historicoTableModel.setRowCount(0);
        
        // Buscar histórico do paciente
        ArrayList<Consulta> historico = controller.buscarHistoricoPaciente(bi);
        
        if (historico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há histórico de consultas para este paciente!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Preencher tabela de históric o
        for (Consulta c : historico) {
            if (c.getStatus() == StatusConsulta.FINALIZADA) {
                Object[] row = {
                    c.getData(),
                    c.getMedico().getNome(),
                    c.getServico().getNomeServico(),
                    c.getDiagnostico(),
                    c.getMedicacao()
                };
                historicoTableModel.addRow(row);
            }
        }
    }
    
    private void adicionarObservacao() {
        String bi = txtIdPaciente.getText().trim();
        String observacao = txtObservacao.getText().trim();
        
        if (bi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o BI do paciente!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (observacao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A observação não pode estar vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar se o paciente existe
        Paciente paciente = controller.buscarPacientePorBI(bi);
        
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Adicionar observação ao histórico
        boolean sucesso = controller.adicionarObservacaoAoHistorico(bi, medicoLogado.getCrm(), observacao);
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Observação adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtObservacao.setText("");
            // Recarregar histórico
            buscarHistoricoPaciente();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar observação!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // Para teste
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String crmTest = JOptionPane.showInputDialog("Digite o CRM do médico para login:");
                if (crmTest != null && !crmTest.isEmpty()) {
                    new MedicoDashboard(crmTest);
                }
            }
        });
    }
}
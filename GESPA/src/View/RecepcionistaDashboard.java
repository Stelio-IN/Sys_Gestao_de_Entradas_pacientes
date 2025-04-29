package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Model.Acompanhante;
import Model.Paciente;
import Model.Servico;
import Model.Recepcionista;
import Model.Consulta;
import Model.RegistroEntrada;
import Model.Consulta.StatusConsulta;
import Controller.RecepcionistaController;

public class RecepcionistaDashboard extends JFrame {
    
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel perfilPanel, registroEntradaPanel, consultasPanel, pacientesPanel;
    private RecepcionistaController controller;
    private Recepcionista recepcionistaLogado;
    
    // Componentes da aba Perfil
    private JTextField txtIdFuncionario, txtNome, txtBi, txtTelefone, txtEndereco;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnAtualizarPerfil;
    
    // Componentes da aba Registro de Entrada
    private JTextField txtBiPaciente, txtNomePaciente, txtTelefonePaciente, txtEnderecoPaciente;
    private JTextField txtDataNascPaciente;
    private JComboBox<String> cmbGeneroPaciente;
    private JComboBox<String> cmbEstadoPaciente;
    private JComboBox<String> cmbServico;
    private JPanel panelAcompanhante;
    private JTextField txtBiAcompanhante, txtNomeAcompanhante, txtTelefoneAcompanhante;
    private JTextField txtEnderecoAcompanhante;
    private JComboBox<String> cmbTipoAcompanhante, cmbGeneroAcompanhante;
    private JCheckBox chkTemAcompanhante;
    private JButton btnRegistrarEntrada, btnLimparFormulario, btnBuscarPaciente;
    
    // Componentes da aba Consultas
    private JTable tblConsultas;
    private DefaultTableModel consultaTableModel;
    private JComboBox<String> cmbFiltroConsulta;
    private JButton btnAgendarConsulta, btnCancelarConsulta, btnVerDetalhesConsulta;
    private JTextField txtPesquisaConsulta;
    
    // Componentes da aba Pacientes
    private JTable tblPacientes;
    private DefaultTableModel pacienteTableModel;
    private JTextField txtPesquisaPaciente;
    private JButton btnPesquisar, btnVerDetalhesPaciente, btnNovoRegistro;
    
    // Componentes para a tabela de registros
    private JTable tblRegistros;
    private DefaultTableModel registroTableModel;
    
    public RecepcionistaDashboard(String idFuncionario) {
        controller = new RecepcionistaController();
        recepcionistaLogado = controller.buscarRecepcionistaPorId(idFuncionario);
        
        if (recepcionistaLogado == null) {
            JOptionPane.showMessageDialog(null, "Recepcionista não encontrado no sistema!", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        // Configuração do frame principal
        setTitle("Dashboard do Atendente - Sistema Hospitalar");
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
        initRegistroEntradaPanel();
        initConsultasPanel();
        initPacientesPanel();
        
        // Adicionar abas ao TabbedPane
        tabbedPane.addTab("Meu Perfil", perfilPanel);
        tabbedPane.addTab("Registro de Entrada", registroEntradaPanel);
        tabbedPane.addTab("Consultas", consultasPanel);
        tabbedPane.addTab("Pacientes", pacientesPanel);
        
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
        JLabel lblIdFuncionario = new JLabel("ID Funcionário:");
        lblIdFuncionario.setBounds(20, 30, 150, 25);
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 70, 100, 25);
        JLabel lblBi = new JLabel("BI:");
        lblBi.setBounds(20, 110, 100, 25);
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 150, 100, 25);
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(20, 190, 100, 25);
        JLabel lblPassword = new JLabel("Nova Password:");
        lblPassword.setBounds(20, 230, 100, 25);
        JLabel lblConfirmPassword = new JLabel("Confirmar Password:");
        lblConfirmPassword.setBounds(20, 270, 150, 25);
        
        // Campos de texto
        txtIdFuncionario = new JTextField();
        txtIdFuncionario.setBounds(170, 30, 200, 25);
        txtIdFuncionario.setEditable(false);
        
        txtNome = new JTextField();
        txtNome.setBounds(170, 70, 200, 25);
        txtNome.setEditable(false);
        
        txtBi = new JTextField();
        txtBi.setBounds(170, 110, 200, 25);
        txtBi.setEditable(false);
        
        txtTelefone = new JTextField();
        txtTelefone.setBounds(170, 150, 200, 25);
        
        txtEndereco = new JTextField();
        txtEndereco.setBounds(170, 190, 400, 25);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 230, 200, 25);
        
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(170, 270, 200, 25);
        
        // Botão Atualizar
        btnAtualizarPerfil = new JButton("Atualizar Meus Dados");
        btnAtualizarPerfil.setBounds(170, 320, 200, 30);
        btnAtualizarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPerfil();
            }
        });
        
        // Adicionar componentes ao painel
        panelDados.add(lblIdFuncionario);
        panelDados.add(txtIdFuncionario);
        panelDados.add(lblNome);
        panelDados.add(txtNome);
        panelDados.add(lblBi);
        panelDados.add(txtBi);
        panelDados.add(lblTelefone);
        panelDados.add(txtTelefone);
        panelDados.add(lblEndereco);
        panelDados.add(txtEndereco);
        panelDados.add(lblPassword);
        panelDados.add(txtPassword);
        panelDados.add(lblConfirmPassword);
        panelDados.add(txtConfirmPassword);
        panelDados.add(btnAtualizarPerfil);
        
        perfilPanel.add(panelDados);
    }
    
    private void initRegistroEntradaPanel() {
        registroEntradaPanel = new JPanel();
        registroEntradaPanel.setLayout(null);
        
        // Painel para dados do paciente
        JPanel panelPaciente = new JPanel();
        panelPaciente.setLayout(null);
        panelPaciente.setBounds(10, 10, 740, 210);
        panelPaciente.setBorder(BorderFactory.createTitledBorder("Dados do Paciente"));
        
        JLabel lblBiPaciente = new JLabel("BI:");
        lblBiPaciente.setBounds(20, 30, 100, 25);
        
        txtBiPaciente = new JTextField();
        txtBiPaciente.setBounds(120, 30, 200, 25);
        
        btnBuscarPaciente = new JButton("Buscar");
        btnBuscarPaciente.setBounds(330, 30, 80, 25);
        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });
        
        JLabel lblNomePaciente = new JLabel("Nome:");
        lblNomePaciente.setBounds(20, 65, 100, 25);
        
        txtNomePaciente = new JTextField();
        txtNomePaciente.setBounds(120, 65, 300, 25);
        
        JLabel lblTelefonePaciente = new JLabel("Telefone:");
        lblTelefonePaciente.setBounds(430, 65, 100, 25);
        
        txtTelefonePaciente = new JTextField();
        txtTelefonePaciente.setBounds(530, 65, 180, 25);
        
        JLabel lblEnderecoPaciente = new JLabel("Endereço:");
        lblEnderecoPaciente.setBounds(20, 100, 100, 25);
        
        txtEnderecoPaciente = new JTextField();
        txtEnderecoPaciente.setBounds(120, 100, 590, 25);
        
        JLabel lblDataNascPaciente = new JLabel("Data Nasc.:");
        lblDataNascPaciente.setBounds(20, 135, 100, 25);
        
        txtDataNascPaciente = new JTextField();
        txtDataNascPaciente.setBounds(120, 135, 100, 25);
        
        JLabel lblGeneroPaciente = new JLabel("Gênero:");
        lblGeneroPaciente.setBounds(230, 135, 100, 25);
        
        String[] generos = {"Masculino", "Feminino", "Outro"};
        cmbGeneroPaciente = new JComboBox<>(generos);
        cmbGeneroPaciente.setBounds(330, 135, 100, 25);
        
        JLabel lblEstadoPaciente = new JLabel("Estado:");
        lblEstadoPaciente.setBounds(20, 170, 100, 25);
        
        String[] estados = {"Vivo", "Morto"};
        cmbEstadoPaciente = new JComboBox<>(estados);
        cmbEstadoPaciente.setBounds(120, 170, 100, 25);
        cmbEstadoPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se o estado for "Morto", desabilita serviços e acompanhante
                boolean isVivo = cmbEstadoPaciente.getSelectedItem().equals("Vivo");
                cmbServico.setEnabled(isVivo);
                chkTemAcompanhante.setEnabled(isVivo);
                panelAcompanhante.setEnabled(isVivo);
                
                // Atualiza componentes do painel acompanhante
                Component[] components = panelAcompanhante.getComponents();
                for (Component c : components) {
                    c.setEnabled(isVivo && chkTemAcompanhante.isSelected());
                }
            }
        });
        
        JLabel lblServico = new JLabel("Serviço:");
        lblServico.setBounds(230, 170, 100, 25);
        
        // Obter serviços disponíveis do controller
        ArrayList<Servico> servicos = controller.listarServicos();
        String[] servicosNomes = new String[servicos.size()];
        for (int i = 0; i < servicos.size(); i++) {
            servicosNomes[i] = servicos.get(i).getNomeServico();
        }
        
        cmbServico = new JComboBox<>(servicosNomes);
        cmbServico.setBounds(330, 170, 200, 25);
        
        panelPaciente.add(lblBiPaciente);
        panelPaciente.add(txtBiPaciente);
        panelPaciente.add(btnBuscarPaciente);
        panelPaciente.add(lblNomePaciente);
        panelPaciente.add(txtNomePaciente);
        panelPaciente.add(lblTelefonePaciente);
        panelPaciente.add(txtTelefonePaciente);
        panelPaciente.add(lblEnderecoPaciente);
        panelPaciente.add(txtEnderecoPaciente);
        panelPaciente.add(lblDataNascPaciente);
        panelPaciente.add(txtDataNascPaciente);
        panelPaciente.add(lblGeneroPaciente);
        panelPaciente.add(cmbGeneroPaciente);
        panelPaciente.add(lblEstadoPaciente);
        panelPaciente.add(cmbEstadoPaciente);
        panelPaciente.add(lblServico);
        panelPaciente.add(cmbServico);
        
        // Painel para dados do acompanhante
        panelAcompanhante = new JPanel();
        panelAcompanhante.setLayout(null);
        panelAcompanhante.setBounds(10, 230, 740, 180);
        panelAcompanhante.setBorder(BorderFactory.createTitledBorder("Dados do Acompanhante"));
        
        chkTemAcompanhante = new JCheckBox("Tem Acompanhante");
        chkTemAcompanhante.setBounds(20, 25, 150, 25);
        chkTemAcompanhante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean temAcompanhante = chkTemAcompanhante.isSelected();
                Component[] components = panelAcompanhante.getComponents();
                for (Component c : components) {
                    if (c != chkTemAcompanhante) {
                        c.setEnabled(temAcompanhante);
                    }
                }
            }
        });
        
        JLabel lblBiAcompanhante = new JLabel("BI:");
        lblBiAcompanhante.setBounds(20, 55, 100, 25);
        lblBiAcompanhante.setEnabled(false);
        
        txtBiAcompanhante = new JTextField();
        txtBiAcompanhante.setBounds(120, 55, 200, 25);
        txtBiAcompanhante.setEnabled(false);
        
        JLabel lblNomeAcompanhante = new JLabel("Nome:");
        lblNomeAcompanhante.setBounds(20, 90, 100, 25);
        lblNomeAcompanhante.setEnabled(false);
        
        txtNomeAcompanhante = new JTextField();
        txtNomeAcompanhante.setBounds(120, 90, 300, 25);
        txtNomeAcompanhante.setEnabled(false);
        
        JLabel lblTelefoneAcompanhante = new JLabel("Telefone:");
        lblTelefoneAcompanhante.setBounds(430, 90, 100, 25);
        lblTelefoneAcompanhante.setEnabled(false);
        
        txtTelefoneAcompanhante = new JTextField();
        txtTelefoneAcompanhante.setBounds(530, 90, 180, 25);
        txtTelefoneAcompanhante.setEnabled(false);
        
        JLabel lblEnderecoAcompanhante = new JLabel("Endereço:");
        lblEnderecoAcompanhante.setBounds(20, 125, 100, 25);
        lblEnderecoAcompanhante.setEnabled(false);
        
        txtEnderecoAcompanhante = new JTextField();
        txtEnderecoAcompanhante.setBounds(120, 125, 590, 25);
        txtEnderecoAcompanhante.setEnabled(false);
        
        JLabel lblTipoAcompanhante = new JLabel("Tipo:");
        lblTipoAcompanhante.setBounds(20, 160, 100, 25);
        lblTipoAcompanhante.setEnabled(false);
        
        String[] tipos = {"Familiar", "Amigo", "Responsável Legal", "Outro"};
        cmbTipoAcompanhante = new JComboBox<>(tipos);
        cmbTipoAcompanhante.setBounds(120, 160, 200, 25);
        cmbTipoAcompanhante.setEnabled(false);
        
        JLabel lblGeneroAcompanhante = new JLabel("Gênero:");
        lblGeneroAcompanhante.setBounds(330, 160, 100, 25);
        lblGeneroAcompanhante.setEnabled(false);
        
        cmbGeneroAcompanhante = new JComboBox<>(generos);
        cmbGeneroAcompanhante.setBounds(430, 160, 100, 25);
        cmbGeneroAcompanhante.setEnabled(false);
        
        panelAcompanhante.add(chkTemAcompanhante);
        panelAcompanhante.add(lblBiAcompanhante);
        panelAcompanhante.add(txtBiAcompanhante);
        panelAcompanhante.add(lblNomeAcompanhante);
        panelAcompanhante.add(txtNomeAcompanhante);
        panelAcompanhante.add(lblTelefoneAcompanhante);
        panelAcompanhante.add(txtTelefoneAcompanhante);
        panelAcompanhante.add(lblEnderecoAcompanhante);
        panelAcompanhante.add(txtEnderecoAcompanhante);
        panelAcompanhante.add(lblTipoAcompanhante);
        panelAcompanhante.add(cmbTipoAcompanhante);
        panelAcompanhante.add(lblGeneroAcompanhante);
        panelAcompanhante.add(cmbGeneroAcompanhante);
        
        // Painel para botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(null);
        panelBotoes.setBounds(10, 420, 740, 80);
        
        btnRegistrarEntrada = new JButton("Registrar Entrada");
        btnRegistrarEntrada.setBounds(20, 20, 150, 40);
        btnRegistrarEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEntrada();
            }
        });
        
        btnLimparFormulario = new JButton("Limpar Formulário");
        btnLimparFormulario.setBounds(200, 20, 150, 40);
        btnLimparFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormularioEntrada();
            }
        });
        
        panelBotoes.add(btnRegistrarEntrada);
        panelBotoes.add(btnLimparFormulario);
        
        // Adicionar painéis à aba de registro
        registroEntradaPanel.add(panelPaciente);
        registroEntradaPanel.add(panelAcompanhante);
        registroEntradaPanel.add(panelBotoes);
    }
    
    private void initConsultasPanel() {
        consultasPanel = new JPanel();
        consultasPanel.setLayout(null);
        
        // Área de pesquisa e filtro
        JPanel panelPesquisa = new JPanel();
        panelPesquisa.setLayout(null);
        panelPesquisa.setBounds(10, 10, 740, 80);
        panelPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisar Consultas"));
        
        JLabel lblPesquisa = new JLabel("BI do Paciente:");
        lblPesquisa.setBounds(20, 30, 100, 25);
        
        txtPesquisaConsulta = new JTextField();
        txtPesquisaConsulta.setBounds(120, 30, 180, 25);
        
        JLabel lblFiltro = new JLabel("Filtrar:");
        lblFiltro.setBounds(310, 30, 50, 25);
        
        String[] opcoesFiltro = {"Todas as Consultas", "Consultas de Hoje", "Pendentes", "Em Andamento", "Finalizadas", "Canceladas"};
        cmbFiltroConsulta = new JComboBox<>(opcoesFiltro);
        cmbFiltroConsulta.setBounds(360, 30, 150, 25);
        cmbFiltroConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarConsultas();
            }
        });
        
        JButton btnPesquisarConsulta = new JButton("Pesquisar");
        btnPesquisarConsulta.setBounds(520, 30, 100, 25);
        btnPesquisarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarConsultas();
            }
        });
        
        panelPesquisa.add(lblPesquisa);
        panelPesquisa.add(txtPesquisaConsulta);
        panelPesquisa.add(lblFiltro);
        panelPesquisa.add(cmbFiltroConsulta);
        panelPesquisa.add(btnPesquisarConsulta);
        
        // Tabela de consultas
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 100, 740, 350);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Lista de Consultas"));
        
        String[] colunas = {"ID", "Paciente", "Médico", "Data", "Hora", "Serviço", "Status"};
        consultaTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblConsultas = new JTable(consultaTableModel);
        JScrollPane scrollPane = new JScrollPane(tblConsultas);
        scrollPane.setBounds(10, 20, 720, 280);
        panelTabela.add(scrollPane);
        
        // Botões de ação
        JPanel panelAcoes = new JPanel();
        panelAcoes.setLayout(null);
        panelAcoes.setBounds(10, 460, 740, 50);
        
        btnAgendarConsulta = new JButton("Agendar Nova Consulta");
        btnAgendarConsulta.setBounds(10, 10, 180, 30);
        btnAgendarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agendarConsulta();
            }
        });
        
        btnCancelarConsulta = new JButton("Cancelar Consulta");
        btnCancelarConsulta.setBounds(200, 10, 150, 30);
        btnCancelarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarConsulta();
            }
        });
        
        btnVerDetalhesConsulta = new JButton("Ver Detalhes");
        btnVerDetalhesConsulta.setBounds(360, 10, 120, 30);
        btnVerDetalhesConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetalhesConsulta();
            }
        });
        
        panelAcoes.add(btnAgendarConsulta);
        panelAcoes.add(btnCancelarConsulta);
        panelAcoes.add(btnVerDetalhesConsulta);
        
        consultasPanel.add(panelPesquisa);
        consultasPanel.add(panelTabela);
        consultasPanel.add(panelAcoes);
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
        
        btnVerDetalhesPaciente = new JButton("Ver Detalhes");
        btnVerDetalhesPaciente.setBounds(590, 30, 120, 25);
        btnVerDetalhesPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetalhesPaciente();
            }
        });
        
        panelPesquisa.add(lblPesquisa);
        panelPesquisa.add(txtPesquisaPaciente);
        panelPesquisa.add(btnPesquisar);
        panelPesquisa.add(btnVerDetalhesPaciente);
        
        // Tabela de pacientes
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 100, 740, 350);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Pacientes Registrados"));
        
        String[] colunas = {"BI", "Nome", "Data Nasc.", "Telefone", "Endereço", "Gênero"};
        pacienteTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPacientes = new JTable(pacienteTableModel);
        JScrollPane scrollPane = new JScrollPane(tblPacientes);
        scrollPane.setBounds(10, 20, 720, 320);
        panelTabela.add(scrollPane);
        
        // Botão para novo registro
        JPanel panelAcoes = new JPanel();
        panelAcoes.setLayout(null);
        panelAcoes.setBounds(10, 460, 740, 50);
        
        btnNovoRegistro = new JButton("Novo Registro de Entrada");
        btnNovoRegistro.setBounds(10, 10, 200, 30);
        btnNovoRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1); // Muda para a aba de registro de entrada
                limparFormularioEntrada();
            }
        });
        
        JButton btnVerHistorico = new JButton("Ver Histórico de Registros");
        btnVerHistorico.setBounds(220, 10, 200, 30);
        btnVerHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistoricoRegistros();
            }
        });
        
        panelAcoes.add(btnNovoRegistro);
        panelAcoes.add(btnVerHistorico);
        
        pacientesPanel.add(panelPesquisa);
        pacientesPanel.add(panelTabela);
        pacientesPanel.add(panelAcoes);
    }
    
    private void carregarDados() {
        // Carregar dados do recepcionista logado
        if (recepcionistaLogado != null) {
            txtIdFuncionario.setText(recepcionistaLogado.getCod_func());
            txtNome.setText(recepcionistaLogado.getNome());
            txtBi.setText(recepcionistaLogado.getBI());
            txtTelefone.setText(recepcionistaLogado.getTelefone());
            txtEndereco.setText(recepcionistaLogado.getEndereco());
        }
        
        // Carregar listas
        carregarPacientes();
        carregarConsultas();
    }
    
        
        // Buscar todos os pacientes
        private void carregarPacientes() {
        // Limpar tabela
        pacienteTableModel.setRowCount(0);
        
        // Buscar todos os pacientes
        ArrayList<Paciente> pacientes = controller.listarPacientes();
        
        // Preencher a tabela
        for (Paciente p : pacientes) {
            String[] rowData = {
                p.getBI(),
                p.getNome(),
                p.getDataNasc(),
                p.getTelefone(),
                p.getEndereco(),
                p.getGenero()
            };
            pacienteTableModel.addRow(rowData);
        }
    }
    
    private void carregarConsultas() {
        // Limpar tabela
        consultaTableModel.setRowCount(0);
        
        // Buscar todas as consultas
        ArrayList<Consulta> consultas = controller.listarConsultas();
        
        // Preencher a tabela
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        for (Consulta c : consultas) {
            String[] rowData = {
                c.getId(),
                c.getPaciente().getNome(),
                c.getMedico().getNome(),
                dateFormat.format(c.getDataConsulta()),
                timeFormat.format(c.getHoraConsulta()),
                c.getServico().getNomeServico(),
                c.getStatus().toString()
            };
            consultaTableModel.addRow(rowData);
        }
    }
    
    private void pesquisarPacientes() {
        String termoBusca = txtPesquisaPaciente.getText().trim();
        
        // Se o campo estiver vazio, mostrar todos os pacientes
        if (termoBusca.isEmpty()) {
            carregarPacientes();
            return;
        }
        
        // Limpar tabela
        pacienteTableModel.setRowCount(0);
        
        // Buscar pacientes pelo termo
        ArrayList<Paciente> pacientes = controller.buscarPacientes(termoBusca);
        
        // Preencher a tabela
        for (Paciente p : pacientes) {
            String[] rowData = {
                p.getBI(),
                p.getNome(),
                p.getDataNascimento(),
                p.getTelefone(),
                p.getEndereco(),
                p.getGenero()
            };
            pacienteTableModel.addRow(rowData);
        }
    }
    
    private void verDetalhesPaciente() {
        int selectedRow = tblPacientes.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para ver detalhes.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String bi = (String) tblPacientes.getValueAt(selectedRow, 0);
        Paciente paciente = controller.buscarPacientePorBi(bi);
        
        if (paciente != null) {
            // Exibir janela de detalhes do paciente
            DetalhesPacienteDialog dialog = new DetalhesPacienteDialog(this, paciente);
            dialog.setVisible(true);
        }
    }
    
    private void mostrarHistoricoRegistros() {
        int selectedRow = tblPacientes.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para ver o histórico.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String bi = (String) tblPacientes.getValueAt(selectedRow, 0);
        
        // Criar janela para exibir histórico
        JDialog historicoDialog = new JDialog(this, "Histórico de Registros", true);
        historicoDialog.setSize(700, 500);
        historicoDialog.setLocationRelativeTo(this);
        historicoDialog.setLayout(new BorderLayout());
        
        // Criar modelo para a tabela de registros
        String[] colunas = {"ID", "Data", "Hora", "Serviço", "Acompanhante"};
        registroTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable tblHistorico = new JTable(registroTableModel);
        JScrollPane scrollPane = new JScrollPane(tblHistorico);
        
        // Buscar histórico do paciente
        ArrayList<RegistroEntrada> registros = controller.buscarRegistrosPorPaciente(bi);
        
        // Preencher a tabela
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        for (RegistroEntrada r : registros) {
            String acompanhante = (r.getAcompanhante() != null) ? r.getAcompanhante().getNome() : "Sem acompanhante";
            
            String[] rowData = {
                r.getId(),
                dateFormat.format(r.getDataRegistro()),
                timeFormat.format(r.getHoraRegistro()),
                r.getServico().getNomeServico(),
                acompanhante
            };
            registroTableModel.addRow(rowData);
        }
        
        historicoDialog.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> historicoDialog.dispose());
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnFechar);
        
        historicoDialog.add(panelBotoes, BorderLayout.SOUTH);
        historicoDialog.setVisible(true);
    }
    
    private void buscarPaciente() {
        String bi = txtBiPaciente.getText().trim();
        
        if (bi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o BI do paciente para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Paciente paciente = controller.buscarPacientePorBi(bi);
        
        if (paciente != null) {
            // Preencher campos com dados do paciente
            txtNomePaciente.setText(paciente.getNome());
            txtTelefonePaciente.setText(paciente.getTelefone());
            txtEnderecoPaciente.setText(paciente.getEndereco());
            txtDataNascPaciente.setText(paciente.getDataNascimento());
            cmbGeneroPaciente.setSelectedItem(paciente.getGenero());
            cmbEstadoPaciente.setSelectedItem(paciente.getEstado());
        } else {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado. Preencha os dados para um novo registro.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            txtNomePaciente.setText("");
            txtTelefonePaciente.setText("");
            txtEnderecoPaciente.setText("");
            txtDataNascPaciente.setText("");
            cmbGeneroPaciente.setSelectedIndex(0);
            cmbEstadoPaciente.setSelectedIndex(0);
        }
    }
    
    private void limparFormularioEntrada() {
        // Limpar campos do paciente
        txtBiPaciente.setText("");
        txtNomePaciente.setText("");
        txtTelefonePaciente.setText("");
        txtEnderecoPaciente.setText("");
        txtDataNascPaciente.setText("");
        cmbGeneroPaciente.setSelectedIndex(0);
        cmbEstadoPaciente.setSelectedIndex(0);
        cmbServico.setSelectedIndex(0);
        
        // Limpar campos do acompanhante
        chkTemAcompanhante.setSelected(false);
        txtBiAcompanhante.setText("");
        txtNomeAcompanhante.setText("");
        txtTelefoneAcompanhante.setText("");
        txtEnderecoAcompanhante.setText("");
        cmbTipoAcompanhante.setSelectedIndex(0);
        cmbGeneroAcompanhante.setSelectedIndex(0);
        
        // Desabilitar campos do acompanhante
        Component[] components = panelAcompanhante.getComponents();
        for (Component c : components) {
            if (c != chkTemAcompanhante) {
                c.setEnabled(false);
            }
        }
    }
    
    private void registrarEntrada() {
        // Validar campos obrigatórios
        if (txtBiPaciente.getText().trim().isEmpty() || txtNomePaciente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos BI e Nome do paciente são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verificar se o paciente está vivo
        if (cmbEstadoPaciente.getSelectedItem().equals("Morto")) {
            JOptionPane.showMessageDialog(this, "Não é possível registrar entrada para pacientes falecidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Criar ou buscar paciente
            String biPaciente = txtBiPaciente.getText().trim();
            Paciente paciente = controller.buscarPacientePorBi(biPaciente);
            
            if (paciente == null) {
                // Criar novo paciente
                paciente = new Paciente();
                paciente.setBI(biPaciente);
                paciente.setNome(txtNomePaciente.getText().trim());
                paciente.setTelefone(txtTelefonePaciente.getText().trim());
                paciente.setEndereco(txtEnderecoPaciente.getText().trim());
                paciente.setDataNascimento(txtDataNascPaciente.getText().trim());
                paciente.setGenero(cmbGeneroPaciente.getSelectedItem().toString());
                paciente.setEstado(cmbEstadoPaciente.getSelectedItem().toString());
                
                // Salvar paciente
                controller.salvarPaciente(paciente);
            }
            
            // Criar acompanhante, se necessário
            Acompanhante acompanhante = null;
            if (chkTemAcompanhante.isSelected()) {
                if (txtBiAcompanhante.getText().trim().isEmpty() || txtNomeAcompanhante.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Os campos BI e Nome do acompanhante são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                acompanhante = new Acompanhante();
                acompanhante.setBI(txtBiAcompanhante.getText().trim());
                acompanhante.setNome(txtNomeAcompanhante.getText().trim());
                acompanhante.setTelefone(txtTelefoneAcompanhante.getText().trim());
                acompanhante.setEndereco(txtEnderecoAcompanhante.getText().trim());
                acompanhante.setTipoAcompanhante(cmbTipoAcompanhante.getSelectedItem().toString());
                acompanhante.setGenero(cmbGeneroAcompanhante.getSelectedItem().toString());
            }
            
            // Buscar serviço selecionado
            String nomeServico = cmbServico.getSelectedItem().toString();
            Servico servico = controller.buscarServicoPorNome(nomeServico);
            
            // Criar registro de entrada
            RegistroEntrada registro = new RegistroEntrada();
            registro.setPaciente(paciente);
            registro.setAcompanhante(acompanhante);
            registro.setServico(servico);
            registro.setDataRegistro(new Date());
            registro.setHoraRegistro(new Date());
            registro.setRecepcionista(recepcionistaLogado);
            
            // Salvar registro
            boolean sucesso = controller.salvarRegistroEntrada(registro);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Registro de entrada realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparFormularioEntrada();
                carregarPacientes();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao registrar entrada. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void atualizarPerfil() {
        // Validar campos
        if (txtTelefone.getText().trim().isEmpty() || txtEndereco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Telefone e Endereço são campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verificar se as senhas coincidem
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        
        if (!password.isEmpty() && !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Atualizar dados do recepcionista
            recepcionistaLogado.setTelefone(txtTelefone.getText().trim());
            recepcionistaLogado.setEndereco(txtEndereco.getText().trim());
            
            // Atualizar senha se foi informada
            if (!password.isEmpty()) {
                recepcionistaLogado.setPassword(password);
            }
            
            // Salvar alterações
            boolean sucesso = controller.atualizarRecepcionista(recepcionistaLogado);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtPassword.setText("");
                txtConfirmPassword.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar perfil. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void agendarConsulta() {
        // Abrir janela de agendamento de consulta
        AgendarConsultaDialog dialog = new AgendarConsultaDialog(this, controller);
        dialog.setVisible(true);
        
        // Recarregar consultas após fechamento do diálogo
        carregarConsultas();
    }
    
    private void cancelarConsulta() {
        int selectedRow = tblConsultas.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para cancelar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = (String) tblConsultas.getValueAt(selectedRow, 0);
        String statusConsulta = (String) tblConsultas.getValueAt(selectedRow, 6);
        
        // Verificar se a consulta já está cancelada ou finalizada
        if (statusConsulta.equals(StatusConsulta.CANCELADA.toString()) || 
            statusConsulta.equals(StatusConsulta.FINALIZADA.toString())) {
            JOptionPane.showMessageDialog(this, "Não é possível cancelar uma consulta que já está " + statusConsulta + ".", 
                                         "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirmar cancelamento
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar esta consulta?", 
                                                "Confirmar Cancelamento", JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            boolean sucesso = controller.cancelarConsulta(idConsulta);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarConsultas();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cancelar consulta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void verDetalhesConsulta() {
        int selectedRow = tblConsultas.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para ver detalhes.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = (String) tblConsultas.getValueAt(selectedRow, 0);
        Consulta consulta = controller.buscarConsultaPorId(idConsulta);
        
        if (consulta != null) {
            // Exibir janela de detalhes da consulta
            DetalhesConsultaDialog dialog = new DetalhesConsultaDialog(this, consulta);
            dialog.setVisible(true);
        }
    }
    
    private void pesquisarConsultas() {
        String termoBusca = txtPesquisaConsulta.getText().trim();
        
        // Se o campo estiver vazio, aplicar apenas o filtro
        if (termoBusca.isEmpty()) {
            filtrarConsultas();
            return;
        }
        
        // Limpar tabela
        consultaTableModel.setRowCount(0);
        
        // Aplicar filtro e termo de busca
        String filtro = cmbFiltroConsulta.getSelectedItem().toString();
        ArrayList<Consulta> consultas = controller.buscarConsultas(termoBusca, filtro);
        
        // Preencher a tabela
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        for (Consulta c : consultas) {
            String[] rowData = {
                c.getId(),
                c.getPaciente().getNome(),
                c.getMedico().getNome(),
                dateFormat.format(c.getDataConsulta()),
                timeFormat.format(c.getHoraConsulta()),
                c.getServico().getNomeServico(),
                c.getStatus().toString()
            };
            consultaTableModel.addRow(rowData);
        }
    }
    
    private void filtrarConsultas() {
        // Limpar tabela
        consultaTableModel.setRowCount(0);
        
        // Aplicar filtro selecionado
        String filtro = cmbFiltroConsulta.getSelectedItem().toString();
        ArrayList<Consulta> consultas = controller.filtrarConsultas(filtro);
        
        // Preencher a tabela
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        for (Consulta c : consultas) {
            String[] rowData = {
                c.getId(),
                c.getPaciente().getNome(),
                c.getMedico().getNome(),
                dateFormat.format(c.getDataConsulta()),
                timeFormat.format(c.getHoraConsulta()),
                c.getServico().getNomeServico(),
                c.getStatus().toString()
            };
            consultaTableModel.addRow(rowData);
        }
    }
    
    // Classe interna para diálogo de detalhes do paciente
    private class DetalhesPacienteDialog extends JDialog {
        
        public DetalhesPacienteDialog(JFrame parent, Paciente paciente) {
            super(parent, "Detalhes do Paciente", true);
            setSize(600, 400);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());
            
            // Painel de informações do paciente
            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new GridLayout(6, 2, 10, 10));
            panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            panelInfo.add(new JLabel("BI:"));
            panelInfo.add(new JLabel(paciente.getBI()));
            
            panelInfo.add(new JLabel("Nome:"));
            panelInfo.add(new JLabel(paciente.getNome()));
            
            panelInfo.add(new JLabel("Data de Nascimento:"));
            panelInfo.add(new JLabel(paciente.getDataNascimento()));
            
            panelInfo.add(new JLabel("Telefone:"));
            panelInfo.add(new JLabel(paciente.getTelefone()));
            
            panelInfo.add(new JLabel("Endereço:"));
            panelInfo.add(new JLabel(paciente.getEndereco()));
            
            panelInfo.add(new JLabel("Gênero:"));
            panelInfo.add(new JLabel(paciente.getGenero()));
            
            // Painel de histórico médico
            JPanel panelHistorico = new JPanel();
            panelHistorico.setLayout(new BorderLayout());
            panelHistorico.setBorder(BorderFactory.createTitledBorder("Histórico de Consultas"));
            
            // Tabela de consultas do paciente
            String[] colunas = {"Data", "Hora", "Serviço", "Médico", "Status"};
            DefaultTableModel historicoModel = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            JTable tblHistorico = new JTable(historicoModel);
            JScrollPane scrollPane = new JScrollPane(tblHistorico);
            
            // Buscar histórico de consultas do paciente
            ArrayList<Consulta> historico = controller.buscarHistoricoConsultas(paciente.getBI());
            
            // Preencher a tabela
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            
            for (Consulta c : historico) {
                String[] rowData = {
                    dateFormat.format(c.getDataConsulta()),
                    timeFormat.format(c.getHoraConsulta()),
                    c.getServico().getNomeServico(),
                    c.getMedico().getNome(),
                    c.getStatus().toString()
                };
                historicoModel.addRow(rowData);
            }
            
            panelHistorico.add(scrollPane, BorderLayout.CENTER);
            
            // Adicionar painéis ao diálogo
            add(panelInfo, BorderLayout.NORTH);
            add(panelHistorico, BorderLayout.CENTER);
            
            // Botão Fechar
            JButton btnFechar = new JButton("Fechar");
            btnFechar.addActionListener(e -> dispose());
            
            JPanel panelBotoes = new JPanel();
            panelBotoes.add(btnFechar);
            
            add(panelBotoes, BorderLayout.SOUTH);
        }
    }
    
    // Classe interna para diálogo de detalhes da consulta
    private class DetalhesConsultaDialog extends JDialog {
        
        public DetalhesConsultaDialog(JFrame parent, Consulta consulta) {
            super(parent, "Detalhes da Consulta", true);
            setSize(600, 400);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());
            
            // Painel de informações da consulta
            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new GridLayout(7, 2, 10, 10));
            panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            
            panelInfo.add(new JLabel("ID da Consulta:"));
            panelInfo.add(new JLabel(consulta.getId()));
            
            panelInfo.add(new JLabel("Paciente:"));
            panelInfo.add(new JLabel(consulta.getPaciente().getNome()));
            
            panelInfo.add(new JLabel("Médico:"));
            panelInfo.add(new JLabel(consulta.getMedico().getNome()));
            
            panelInfo.add(new JLabel("Data:"));
            panelInfo.add(new JLabel(dateFormat.format(consulta.getDataConsulta())));
            
            panelInfo.add(new JLabel("Hora:"));
            panelInfo.add(new JLabel(timeFormat.format(consulta.getHoraConsulta())));
            
            panelInfo.add(new JLabel("Serviço:"));
            panelInfo.add(new JLabel(consulta.getServico().getNomeServico()));
            
            panelInfo.add(new JLabel("Status:"));
            panelInfo.add(new JLabel(consulta.getStatus().toString()));
            
            // Painel de observações (se existirem)
            JPanel panelObs = new JPanel();
            panelObs.setLayout(new BorderLayout());
            panelObs.setBorder(BorderFactory.createTitledBorder("Observações"));
            
            JTextArea txtObs = new JTextArea();
            txtObs.setEditable(false);
            txtObs.setLineWrap(true);
            txtObs.setWrapStyleWord(true);
            
            if (consulta.getObservacoes() != null && !consulta.getObservacoes().isEmpty()) {
                txtObs.setText(consulta.getObservacoes());
            } else {
                txtObs.setText("Nenhuma observação registrada.");
            }
            
            JScrollPane scrollObs = new JScrollPane(txtObs);
            panelObs.add(scrollObs, BorderLayout.CENTER);
            
            // Adicionar painéis ao diálogo
            add(panelInfo, BorderLayout.NORTH);
            add(panelObs, BorderLayout.CENTER);
            
            // Botão Fechar
            JButton btnFechar = new JButton("Fechar");
            btnFechar.addActionListener(e -> dispose());
            
            JPanel panelBotoes = new JPanel();
            panelBotoes.add(btnFechar);
            
            add(panelBotoes, BorderLayout.SOUTH);
        }
    }
    
    // Classe interna para diálogo de agendamento de consulta
    private class AgendarConsultaDialog extends JDialog {
        
        private JTextField txtBiPaciente, txtNomePaciente;
        private JComboBox<String> cmbMedico, cmbServico;
        private JTextField txtData;
        private JComboBox<String> cmbHora;
        private JButton btnBuscarPaciente, btnAgendar, btnCancelar;
        
        public AgendarConsultaDialog(JFrame parent, RecepcionistaController controller) {
            super(parent, "Agendar Consulta", true);
            setSize(500, 400);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());
            
            // Painel principal
            JPanel panelMain = new JPanel();
            panelMain.setLayout(null);
            panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            // Componentes
            JLabel lblBiPaciente = new JLabel("BI do Paciente:");
            lblBiPaciente.setBounds(20, 20, 100, 25);
            
            txtBiPaciente = new JTextField();
            txtBiPaciente.setBounds(130, 20, 200, 25);
            
            btnBuscarPaciente = new JButton("Buscar");
            btnBuscarPaciente.setBounds(340, 20, 80, 25);
            btnBuscarPaciente.addActionListener(e -> {
                String bi = txtBiPaciente.getText().trim();
                if (!bi.isEmpty()) {
                    Paciente paciente = controller.buscarPacientePorBi(bi);
                    if (paciente != null) {
                        txtNomePaciente.setText(paciente.getNome());
                    } else {
                        JOptionPane.showMessageDialog(this, "Paciente não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        txtNomePaciente.setText("");
                    }
                }
            });
            
            JLabel lblNomePaciente = new JLabel("Nome:");
            lblNomePaciente.setBounds(20, 55, 100, 25);
            
            txtNomePaciente = new JTextField();
            txtNomePaciente.setBounds(130, 55, 290, 25);
            txtNomePaciente.setEditable(false);
            
            JLabel lblServico = new JLabel("Serviço:");
            lblServico.setBounds(20, 90, 100, 25);
            
            // Obter serviços disponíveis
            ArrayList<Servico> servicos = controller.listarServicos();
            String[] servicosNomes = new String[servicos.size()];
            for (int i = 0; i < servicos.size(); i++) {
                servicosNomes[i] = servicos.get(i).getNomeServico();
            }
            
            cmbServico = new JComboBox<>(servicosNomes);
            cmbServico.setBounds(130, 90, 290, 25);
            cmbServico.addActionListener(e -> {
                // Ao selecionar um serviço, atualizar a lista de médicos disponíveis
                String servicoSelecionado = cmbServico.getSelectedItem().toString();
                ArrayList<String> medicos = controller.listarMedicosPorServico(servicoSelecionado);
                            cmbMedico = new JComboBox<>();
            cmbMedico.setBounds(130, 125, 290, 25);
            
            JLabel lblMedico = new JLabel("Médico:");
            lblMedico.setBounds(20, 125, 100, 25);
            
            JLabel lblData = new JLabel("Data:");
            lblData.setBounds(20, 160, 100, 25);
            
            txtData = new JTextField();
            txtData.setBounds(130, 160, 150, 25);
            
            JLabel lblHora = new JLabel("Hora:");
            lblHora.setBounds(20, 195, 100, 25);
            
            // Horários disponíveis (pode ser melhorado para verificar disponibilidade real)
            String[] horarios = {"08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00"};
            cmbHora = new JComboBox<>(horarios);
            cmbHora.setBounds(130, 195, 100, 25);
            
            // Painel de botões
            JPanel panelBotoes = new JPanel();
            panelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
            
            btnAgendar = new JButton("Agendar");
            btnAgendar.addActionListener(e -> agendarConsulta());
            
            btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(e -> dispose());
            
            panelBotoes.add(btnAgendar);
            panelBotoes.add(btnCancelar);
            
            // Adicionar componentes ao painel principal
            panelMain.add(lblBiPaciente);
            panelMain.add(txtBiPaciente);
            panelMain.add(btnBuscarPaciente);
            panelMain.add(lblNomePaciente);
            panelMain.add(txtNomePaciente);
            panelMain.add(lblServico);
            panelMain.add(cmbServico);
            panelMain.add(lblMedico);
            panelMain.add(cmbMedico);
            panelMain.add(lblData);
            panelMain.add(txtData);
            panelMain.add(lblHora);
            panelMain.add(cmbHora);
            
            // Adicionar painéis ao diálogo
            add(panelMain, BorderLayout.CENTER);
            add(panelBotoes, BorderLayout.SOUTH);
        }
        
        private void agendarConsulta() {
            // Validar campos
            if (txtBiPaciente.getText().trim().isEmpty() || txtNomePaciente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o BI do paciente e busque seu registro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (cmbServico.getSelectedItem() == null || cmbMedico.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Selecione um serviço e um médico.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (txtData.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a data da consulta.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                // Criar objeto Consulta
                Consulta consulta = new Consulta();
                
                // Buscar paciente
                Paciente paciente = controller.buscarPacientePorBi(txtBiPaciente.getText().trim());
                if (paciente == null) {
                    JOptionPane.showMessageDialog(this, "Paciente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                consulta.setPaciente(paciente);
                
                // Buscar médico
                String nomeMedico = cmbMedico.getSelectedItem().toString();
                // (Implementar busca do médico no controller)
                
                // Buscar serviço
                String nomeServico = cmbServico.getSelectedItem().toString();
                Servico servico = controller.buscarServicoPorNome(nomeServico);
                consulta.setServico(servico);
                
                // Definir data e hora
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dataConsulta = dateFormat.parse(txtData.getText().trim());
                consulta.setDataConsulta(dataConsulta);
                
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date horaConsulta = timeFormat.parse(cmbHora.getSelectedItem().toString());
                consulta.setHoraConsulta(horaConsulta);
                
                // Definir status inicial
                consulta.setStatus(StatusConsulta.PENDENTE);
                
                // Salvar consulta
                boolean sucesso = controller.salvarConsulta(consulta);
                
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Consulta agendada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao agendar consulta. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar data/hora: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
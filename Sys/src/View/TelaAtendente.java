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

public class TelaAtendente extends JFrame {
    private Recepcionista recepcionista;
    private RecepcionistaController recepcionistaController;
    private PacienteController pacienteController;
    private ServicoController servicoController;
    private RegistroEntradaController registroController;
    private AcompanhanteController acompanhanteController;
    private ConsultaController consultaController;
    
    private JTabbedPane abas;
    private JPanel panelRegistroEntrada, panelConsultas, panelPacientes;
    
    // Componentes da aba de Registro de Entrada
    private JTextField txtBiPaciente, txtNomePaciente, txtTelefonePaciente, txtEnderecoPaciente;
    private JTextField txtDataNascPaciente;
    private JComboBox<String> cmbGeneroPaciente, cmbEstadoPaciente, cmbServico;
    private JPanel panelAcompanhante;
    private JTextField txtBiAcompanhante, txtNomeAcompanhante, txtTelefoneAcompanhante;
    private JTextField txtEnderecoAcompanhante;
    private JComboBox<String> cmbTipoAcompanhante, cmbGeneroAcompanhante;
    private JCheckBox chkTemAcompanhante;
    private JButton btnRegistrarEntrada, btnLimparFormulario, btnBuscarPaciente;
    
    // Componentes da aba de Consultas
    private JTable tblConsultas;
    private JComboBox<String> cmbFiltroConsulta;
    private JButton btnAgendarConsulta, btnCancelarConsulta, btnAtualizarConsulta;
    private JTextField txtPesquisaConsulta;
    
    // Componentes da aba de Pacientes
    private JTable tblPacientes;
    private JTextField txtPesquisaPaciente;
    private JButton btnPesquisarPaciente, btnNovoPaciente;
    
    public TelaAtendente(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
        this.recepcionistaController = new RecepcionistaController();
        this.pacienteController = new PacienteController();
        this.servicoController = new ServicoController();
        this.registroController = new RegistroEntradaController();
        this.acompanhanteController = new AcompanhanteController();
        this.consultaController = new ConsultaController();
        
        configurarJanela();
        inicializarComponentes();
        carregarDadosIniciais();
        
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Sistema Hospitalar - Atendente: " + recepcionista.getNome());
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Barra de status
        JLabel lblStatus = new JLabel("Usuário: " + recepcionista.getNome() + " | " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        lblStatus.setBorder(BorderFactory.createEtchedBorder());
        add(lblStatus, BorderLayout.SOUTH);
        
        // Área principal com abas
        abas = new JTabbedPane();
        add(abas, BorderLayout.CENTER);
    }
    
    private void inicializarComponentes() {
        criarAbaRegistroEntrada();
        criarAbaConsultas();
        criarAbaPacientes();
    }
    
    private void carregarDadosIniciais() {
        // Carregar serviços disponíveis
        DefaultComboBoxModel<String> modelServicos = new DefaultComboBoxModel<>();
        for (Servico servico : servicoController.listarTodosServicos()) {
            modelServicos.addElement(servico.getNomeServico());
        }
        cmbServico.setModel(modelServicos);
        
        // Carregar pacientes na tabela
        atualizarTabelaPacientes();
        
        // Carregar consultas na tabela
        atualizarTabelaConsultas();
    }
    
    private void criarAbaRegistroEntrada() {
        panelRegistroEntrada = new JPanel(new BorderLayout(10, 10));
        panelRegistroEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel de dados do paciente
        JPanel panelPaciente = new JPanel();
        panelPaciente.setBorder(BorderFactory.createTitledBorder("Dados do Paciente"));
        panelPaciente.setLayout(new GridLayout(0, 2, 5, 5));
        
        panelPaciente.add(new JLabel("BI:"));
        txtBiPaciente = new JTextField();
        panelPaciente.add(txtBiPaciente);
        
        btnBuscarPaciente = new JButton("Buscar Paciente");
        btnBuscarPaciente.addActionListener(e -> buscarPaciente());
        panelPaciente.add(btnBuscarPaciente);
        panelPaciente.add(new JLabel()); // Espaço vazio
        
        panelPaciente.add(new JLabel("Nome:"));
        txtNomePaciente = new JTextField();
        panelPaciente.add(txtNomePaciente);
        
        panelPaciente.add(new JLabel("Telefone:"));
        txtTelefonePaciente = new JTextField();
        panelPaciente.add(txtTelefonePaciente);
        
        panelPaciente.add(new JLabel("Endereço:"));
        txtEnderecoPaciente = new JTextField();
        panelPaciente.add(txtEnderecoPaciente);
        
        panelPaciente.add(new JLabel("Data Nasc.:"));
        txtDataNascPaciente = new JTextField();
        panelPaciente.add(txtDataNascPaciente);
        
        panelPaciente.add(new JLabel("Gênero:"));
        cmbGeneroPaciente = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        panelPaciente.add(cmbGeneroPaciente);
        
        panelPaciente.add(new JLabel("Estado:"));
        cmbEstadoPaciente = new JComboBox<>(new String[]{"Vivo", "Morto"});
        cmbEstadoPaciente.addActionListener(e -> atualizarEstadoPaciente());
        panelPaciente.add(cmbEstadoPaciente);
        
        panelPaciente.add(new JLabel("Serviço:"));
        cmbServico = new JComboBox<>();
        panelPaciente.add(cmbServico);
        
        // Painel de acompanhante
        panelAcompanhante = new JPanel();
        panelAcompanhante.setBorder(BorderFactory.createTitledBorder("Dados do Acompanhante"));
        panelAcompanhante.setLayout(new GridLayout(0, 2, 5, 5));
        
        chkTemAcompanhante = new JCheckBox("Paciente acompanhado?");
        chkTemAcompanhante.addActionListener(e -> toggleAcompanhante());
        panelAcompanhante.add(chkTemAcompanhante);
        panelAcompanhante.add(new JLabel());
        
        panelAcompanhante.add(new JLabel("BI:"));
        txtBiAcompanhante = new JTextField();
        txtBiAcompanhante.setEnabled(false);
        panelAcompanhante.add(txtBiAcompanhante);
        
        panelAcompanhante.add(new JLabel("Nome:"));
        txtNomeAcompanhante = new JTextField();
        txtNomeAcompanhante.setEnabled(false);
        panelAcompanhante.add(txtNomeAcompanhante);
        
        panelAcompanhante.add(new JLabel("Telefone:"));
        txtTelefoneAcompanhante = new JTextField();
        txtTelefoneAcompanhante.setEnabled(false);
        panelAcompanhante.add(txtTelefoneAcompanhante);
        
        panelAcompanhante.add(new JLabel("Endereço:"));
        txtEnderecoAcompanhante = new JTextField();
        txtEnderecoAcompanhante.setEnabled(false);
        panelAcompanhante.add(txtEnderecoAcompanhante);
        
        panelAcompanhante.add(new JLabel("Tipo:"));
        cmbTipoAcompanhante = new JComboBox<>(new String[]{"Familiar", "Amigo", "Responsável Legal", "Outro"});
        cmbTipoAcompanhante.setEnabled(false);
        panelAcompanhante.add(cmbTipoAcompanhante);
        
        panelAcompanhante.add(new JLabel("Gênero:"));
        cmbGeneroAcompanhante = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        cmbGeneroAcompanhante.setEnabled(false);
        panelAcompanhante.add(cmbGeneroAcompanhante);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRegistrarEntrada = new JButton("Registrar Entrada");
        btnRegistrarEntrada.addActionListener(e -> registrarEntrada());
        panelBotoes.add(btnRegistrarEntrada);
        
        btnLimparFormulario = new JButton("Limpar Formulário");
        btnLimparFormulario.addActionListener(e -> limparFormularioEntrada());
        panelBotoes.add(btnLimparFormulario);
        
        // Adicionar componentes ao painel principal
        JPanel panelFormulario = new JPanel(new BorderLayout());
        panelFormulario.add(panelPaciente, BorderLayout.NORTH);
        panelFormulario.add(panelAcompanhante, BorderLayout.CENTER);
        
        panelRegistroEntrada.add(panelFormulario, BorderLayout.CENTER);
        panelRegistroEntrada.add(panelBotoes, BorderLayout.SOUTH);
        
        abas.addTab("Registro de Entrada", panelRegistroEntrada);
    }
    
    private void criarAbaConsultas() {
        panelConsultas = new JPanel(new BorderLayout(10, 10));
        panelConsultas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel de pesquisa
        JPanel panelPesquisa = new JPanel(new BorderLayout(5, 5));
        
        JPanel panelFiltros = new JPanel();
        panelFiltros.add(new JLabel("Filtrar:"));
        cmbFiltroConsulta = new JComboBox<>(new String[]{
            "Todas as Consultas", 
            "Consultas de Hoje", 
            "Pendentes", 
            "Em Andamento", 
            "Finalizadas", 
            "Canceladas"
        });
        cmbFiltroConsulta.addActionListener(e -> atualizarTabelaConsultas());
        panelFiltros.add(cmbFiltroConsulta);
        
        panelPesquisa.add(panelFiltros, BorderLayout.WEST);
        
        JPanel panelPesquisaBi = new JPanel();
        panelPesquisaBi.add(new JLabel("BI do Paciente:"));
        txtPesquisaConsulta = new JTextField(15);
        panelPesquisaBi.add(txtPesquisaConsulta);
        
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> atualizarTabelaConsultas());
        panelPesquisaBi.add(btnPesquisar);
        
        panelPesquisa.add(panelPesquisaBi, BorderLayout.EAST);
        
        // Tabela de consultas
        String[] colunas = {"ID", "Paciente", "Médico", "Data", "Hora", "Serviço", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblConsultas = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblConsultas);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAgendarConsulta = new JButton("Agendar Consulta");
        btnAgendarConsulta.addActionListener(e -> agendarConsulta());
        panelBotoes.add(btnAgendarConsulta);
        
        btnCancelarConsulta = new JButton("Cancelar Consulta");
        btnCancelarConsulta.addActionListener(e -> cancelarConsulta());
        panelBotoes.add(btnCancelarConsulta);
        
        btnAtualizarConsulta = new JButton("Atualizar Status");
        btnAtualizarConsulta.addActionListener(e -> atualizarStatusConsulta());
        panelBotoes.add(btnAtualizarConsulta);
        
        // Adicionar componentes ao painel principal
        panelConsultas.add(panelPesquisa, BorderLayout.NORTH);
        panelConsultas.add(scrollPane, BorderLayout.CENTER);
        panelConsultas.add(panelBotoes, BorderLayout.SOUTH);
        
        abas.addTab("Gestão de Consultas", panelConsultas);
    }
    
    private void criarAbaPacientes() {
        panelPacientes = new JPanel(new BorderLayout(10, 10));
        panelPacientes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel de pesquisa
        JPanel panelPesquisa = new JPanel();
        panelPesquisa.add(new JLabel("Pesquisar (Nome ou BI):"));
        txtPesquisaPaciente = new JTextField(20);
        panelPesquisa.add(txtPesquisaPaciente);
        
        btnPesquisarPaciente = new JButton("Pesquisar");
        btnPesquisarPaciente.addActionListener(e -> atualizarTabelaPacientes());
        panelPesquisa.add(btnPesquisarPaciente);
        
        btnNovoPaciente = new JButton("Novo Paciente");
        btnNovoPaciente.addActionListener(e -> cadastrarNovoPaciente());
        panelPesquisa.add(btnNovoPaciente);
        
        // Tabela de pacientes
        String[] colunas = {"BI", "Nome", "Data Nasc.", "Telefone", "Endereço", "Gênero", "Estado"};
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
        
        abas.addTab("Gestão de Pacientes", panelPacientes);
    }
    
    // Métodos de funcionalidade
    private void buscarPaciente() {
        String bi = txtBiPaciente.getText().trim();
        if (bi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o BI do paciente", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Paciente paciente = (Paciente) pacienteController.buscarPorBi(bi);
        if (paciente != null) {
            txtNomePaciente.setText(paciente.getNome());
            txtTelefonePaciente.setText(paciente.getTelefone());
            txtEnderecoPaciente.setText(paciente.getEndereco());
            txtDataNascPaciente.setText(paciente.getDataNasc());
            cmbGeneroPaciente.setSelectedItem(paciente.getGenero());
            cmbEstadoPaciente.setSelectedItem(paciente.getEstado());
        } else {
            JOptionPane.showMessageDialog(this, "Paciente não encontrado. Preencha os dados para novo cadastro.", 
                                         "Informação", JOptionPane.INFORMATION_MESSAGE);
            limparCamposPaciente();
        }
    }
    
    private void limparCamposPaciente() {
        txtNomePaciente.setText("");
        txtTelefonePaciente.setText("");
        txtEnderecoPaciente.setText("");
        txtDataNascPaciente.setText("");
        cmbGeneroPaciente.setSelectedIndex(0);
        cmbEstadoPaciente.setSelectedIndex(0);
    }
    
    private void toggleAcompanhante() {
        boolean temAcompanhante = chkTemAcompanhante.isSelected();
        txtBiAcompanhante.setEnabled(temAcompanhante);
        txtNomeAcompanhante.setEnabled(temAcompanhante);
        txtTelefoneAcompanhante.setEnabled(temAcompanhante);
        txtEnderecoAcompanhante.setEnabled(temAcompanhante);
        cmbTipoAcompanhante.setEnabled(temAcompanhante);
        cmbGeneroAcompanhante.setEnabled(temAcompanhante);
    }
    
    private void atualizarEstadoPaciente() {
        boolean pacienteVivo = cmbEstadoPaciente.getSelectedItem().equals("Vivo");
        cmbServico.setEnabled(pacienteVivo);
        chkTemAcompanhante.setEnabled(pacienteVivo);
        
        if (!pacienteVivo) {
            chkTemAcompanhante.setSelected(false);
            toggleAcompanhante();
        }
    }
    
    private void limparFormularioEntrada() {
        txtBiPaciente.setText("");
        limparCamposPaciente();
        cmbServico.setSelectedIndex(0);
        
        chkTemAcompanhante.setSelected(false);
        txtBiAcompanhante.setText("");
        txtNomeAcompanhante.setText("");
        txtTelefoneAcompanhante.setText("");
        txtEnderecoAcompanhante.setText("");
        cmbTipoAcompanhante.setSelectedIndex(0);
        cmbGeneroAcompanhante.setSelectedIndex(0);
        toggleAcompanhante();
    }
    
    private void registrarEntrada() {
        // Validação dos campos obrigatórios
        if (txtBiPaciente.getText().trim().isEmpty() || txtNomePaciente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "BI e Nome do paciente são obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Verificar/Cadastrar paciente
            Paciente paciente;
            String biPaciente = txtBiPaciente.getText().trim();
            
            if (pacienteController.buscarPorBi(biPaciente) == null) {
                // Novo paciente
                paciente = new Paciente(
                    biPaciente,
                    txtNomePaciente.getText().trim(),
                    txtTelefonePaciente.getText().trim(),
                    txtEnderecoPaciente.getText().trim()
                );
                paciente.setDataNasc(txtDataNascPaciente.getText().trim());
                paciente.setGenero(cmbGeneroPaciente.getSelectedItem().toString());
                paciente.setEstado(cmbEstadoPaciente.getSelectedItem().toString());
                
                pacienteController.cadastrarPaciente(
                    biPaciente,
                    paciente.getNome(),
                    paciente.getTelefone(),
                    paciente.getEndereco(),
                    paciente.getDataNasc(),
                    paciente.getGenero(),
                    paciente.getEstado()
                );
            } else {
                paciente = (Paciente) pacienteController.buscarPorBi(biPaciente);
            }
            
            // Verificar/Cadastrar acompanhante
            Acompanhante acompanhante = null;
            if (chkTemAcompanhante.isSelected()) {
                String biAcompanhante = txtBiAcompanhante.getText().trim();
                if (biAcompanhante.isEmpty() || txtNomeAcompanhante.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "BI e Nome do acompanhante são obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (acompanhanteController.buscarAcompanhantePorBi(biAcompanhante) == null) {
                    acompanhante = new Acompanhante(
                        biAcompanhante,
                        txtNomeAcompanhante.getText().trim(),
                        txtTelefoneAcompanhante.getText().trim(),
                        txtEnderecoAcompanhante.getText().trim()
                    );
                    acompanhante.setTipo(cmbTipoAcompanhante.getSelectedItem().toString());
                    acompanhante.setSexo(cmbGeneroAcompanhante.getSelectedItem().toString());
                    
                    acompanhanteController.cadastrarAcompanhante(
                        biAcompanhante,
                        acompanhante.getNome(),
                        acompanhante.getTelefone(),
                        acompanhante.getEndereco(),
                        acompanhante.getTipo(),
                        acompanhante.getSexo()
                    );
                } else {
                    acompanhante = acompanhanteController.buscarAcompanhantePorBi(biAcompanhante);
                }
            }
            
            // Obter serviço selecionado
          //  Servico servico = servicoController.buscarServicoPorNome(cmbServico.getSelectedItem().toString());
            
            
              Servico servico = new Servico("1","pee","asd",25);
            
            // Criar registro de entrada
            registroController.registrarEntrada(
                paciente,
                recepcionista,
                cmbEstadoPaciente.getSelectedItem().toString(),
                servico,
                acompanhante
            );
            
            JOptionPane.showMessageDialog(this, "Registro de entrada realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparFormularioEntrada();
            atualizarTabelaPacientes();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar entrada: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void atualizarTabelaConsultas() {
        DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
        model.setRowCount(0); // Limpar tabela
        
        String filtro = cmbFiltroConsulta.getSelectedItem().toString();
        String biPaciente = txtPesquisaConsulta.getText().trim();
        
        // Obter consultas filtradas
        java.util.List<Consulta> consultas;
        if (!biPaciente.isEmpty()) {
            consultas = consultaController.listarConsultasPorPaciente(biPaciente);
        } else {
            consultas = consultaController.listarConsultasPorPaciente(""); // Todas as consultas
        }
        
        // Filtrar por status se necessário
        if (!filtro.equals("Todas as Consultas")) {
            consultas.removeIf(c -> !c.getStatus().equals(filtro));
        }
        
        // Preencher tabela
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Consulta c : consultas) {
            model.addRow(new Object[]{
                c.getId(),
                c.getPaciente().getNome(),
                c.getMedico().getNome(),
                dateFormat.format(c.getData()),
                c.getHora(),
                c.getServico().getNomeServico(),
                c.getStatus()
            });
        }
    }
    
    private void agendarConsulta() {
        // Implementar diálogo para agendamento de consulta
        JDialog dialog = new JDialog(this, "Agendar Nova Consulta", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Componentes do formulário
        JLabel lblBiPaciente = new JLabel("BI do Paciente:");
        JTextField txtBiPacienteConsulta = new JTextField();
        
        JLabel lblNomePaciente = new JLabel("Nome:");
        JTextField txtNomePacienteConsulta = new JTextField();
        txtNomePacienteConsulta.setEditable(false);
        
        JButton btnBuscarPaciente = new JButton("Buscar");
        btnBuscarPaciente.addActionListener(e -> {
            Paciente p = (Paciente) pacienteController.buscarPorBi(txtBiPacienteConsulta.getText().trim());
            if (p != null) {
                txtNomePacienteConsulta.setText(p.getNome());
            } else {
                JOptionPane.showMessageDialog(dialog, "Paciente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JLabel lblServico = new JLabel("Serviço:");
        JComboBox<String> cmbServicoConsulta = new JComboBox<>();
        for (Servico s : servicoController.listarTodosServicos()) {
            cmbServicoConsulta.addItem(s.getNomeServico());
        }
        
        JLabel lblMedico = new JLabel("Médico:");
        JComboBox<String> cmbMedicoConsulta = new JComboBox<>();
        // Aqui seria necessário carregar os médicos do controller
        
        JLabel lblData = new JLabel("Data (dd/mm/aaaa):");
        JTextField txtDataConsulta = new JTextField();
        
        JLabel lblHora = new JLabel("Hora (hh:mm):");
        JTextField txtHoraConsulta = new JTextField();
        
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.addActionListener(e -> {
            // Validar e agendar consulta
            try {
                Paciente paciente = (Paciente) pacienteController.buscarPorBi(txtBiPacienteConsulta.getText().trim());
                if (paciente == null) {
                    JOptionPane.showMessageDialog(dialog, "Paciente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Servico servico = servicoController.buscarServicoPorNome(cmbServicoConsulta.getSelectedItem().toString());
                // Médico seria obtido do combobox
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date data = sdf.parse(txtDataConsulta.getText().trim());
                
                String hora = txtHoraConsulta.getText().trim();
                
                // Aqui chamaria o método para agendar a consulta
                // consultaController.agendarConsulta(...);
                
                JOptionPane.showMessageDialog(dialog, "Consulta agendada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                atualizarTabelaConsultas();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao agendar consulta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        // Adicionar componentes ao diálogo
        dialog.add(lblBiPaciente);
        dialog.add(txtBiPacienteConsulta);
        dialog.add(new JLabel());
        dialog.add(btnBuscarPaciente);
        dialog.add(lblNomePaciente);
        dialog.add(txtNomePacienteConsulta);
        dialog.add(lblServico);
        dialog.add(cmbServicoConsulta);
        dialog.add(lblMedico);
        dialog.add(cmbMedicoConsulta);
        dialog.add(lblData);
        dialog.add(txtDataConsulta);
        dialog.add(lblHora);
        dialog.add(txtHoraConsulta);
        dialog.add(btnAgendar);
        dialog.add(btnCancelar);
        
        dialog.setVisible(true);
    }
    
    private void cancelarConsulta() {
        int linhaSelecionada = tblConsultas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para cancelar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = tblConsultas.getValueAt(linhaSelecionada, 0).toString();
        String statusConsulta = tblConsultas.getValueAt(linhaSelecionada, 6).toString();
        
        if (statusConsulta.equals("Cancelada")) {
            JOptionPane.showMessageDialog(this, "Esta consulta já está cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (statusConsulta.equals("Finalizada")) {
            JOptionPane.showMessageDialog(this, "Não é possível cancelar uma consulta finalizada", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(
            this, 
            "Tem certeza que deseja cancelar esta consulta?", 
            "Confirmar Cancelamento", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            consultaController.cancelarConsulta(idConsulta);
            atualizarTabelaConsultas();
            JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void atualizarStatusConsulta() {
        int linhaSelecionada = tblConsultas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para atualizar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idConsulta = tblConsultas.getValueAt(linhaSelecionada, 0).toString();
        String statusAtual = tblConsultas.getValueAt(linhaSelecionada, 6).toString();
        
        String[] opcoes;
        if (statusAtual.equals("Pendente")) {
            opcoes = new String[]{"Em Andamento", "Cancelada"};
        } else if (statusAtual.equals("Em Andamento")) {
            opcoes = new String[]{"Finalizada", "Cancelada"};
        } else {
            JOptionPane.showMessageDialog(this, "Esta consulta não pode ter seu status alterado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String novoStatus = (String) JOptionPane.showInputDialog(
            this,
            "Selecione o novo status:",
            "Atualizar Status",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );
        
        if (novoStatus != null) {
            consultaController.atualizarStatusConsulta(idConsulta, novoStatus);
            atualizarTabelaConsultas();
            JOptionPane.showMessageDialog(this, "Status atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void atualizarTabelaPacientes() {
        DefaultTableModel model = (DefaultTableModel) tblPacientes.getModel();
        model.setRowCount(0); // Limpar tabela
        
        String termo = txtPesquisaPaciente.getText().trim();
        java.util.List<Paciente> pacientes;
        
        if (termo.isEmpty()) {
            pacientes = pacienteController.listarTodos().stream()
                .filter(p -> p instanceof Paciente)
                .map(p -> (Paciente) p)
                .toList();
        } else {
            pacientes = pacienteController.filtrarPacientes(termo);
        }
        
        // Preencher tabela
        for (Paciente p : pacientes) {
            model.addRow(new Object[]{
                p.getBI(),
                p.getNome(),
                p.getDataNasc(),
                p.getTelefone(),
                p.getEndereco(),
                p.getGenero(),
                p.getEstado()
            });
        }
    }
    
    private void cadastrarNovoPaciente() {
        JDialog dialog = new JDialog(this, "Cadastrar Novo Paciente", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Componentes do formulário
        JLabel lblBi = new JLabel("BI:");
        JTextField txtBi = new JTextField();
        
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        
        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();
        
        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField();
        
        JLabel lblDataNasc = new JLabel("Data Nasc. (dd/mm/aaaa):");
        JTextField txtDataNasc = new JTextField();
        
        JLabel lblGenero = new JLabel("Gênero:");
        JComboBox<String> cmbGenero = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        
        JLabel lblEstado = new JLabel("Estado:");
        JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Vivo", "Morto"});
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                boolean sucesso = pacienteController.cadastrarPaciente(
                    txtBi.getText().trim(),
                    txtNome.getText().trim(),
                    txtTelefone.getText().trim(),
                    txtEndereco.getText().trim(),
                    txtDataNasc.getText().trim(),
                    cmbGenero.getSelectedItem().toString(),
                    cmbEstado.getSelectedItem().toString()
                );
                
                if (sucesso) {
                    JOptionPane.showMessageDialog(dialog, "Paciente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                    atualizarTabelaPacientes();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar paciente. BI já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar paciente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        // Adicionar componentes ao diálogo
        dialog.add(lblBi);
        dialog.add(txtBi);
        dialog.add(lblNome);
        dialog.add(txtNome);
        dialog.add(lblTelefone);
        dialog.add(txtTelefone);
        dialog.add(lblEndereco);
        dialog.add(txtEndereco);
        dialog.add(lblDataNasc);
        dialog.add(txtDataNasc);
        dialog.add(lblGenero);
        dialog.add(cmbGenero);
        dialog.add(lblEstado);
        dialog.add(cmbEstado);
        dialog.add(btnSalvar);
        dialog.add(btnCancelar);
        
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Para teste - criar um recepcionista mock
        Recepcionista recepcionistaTeste = new Recepcionista(
            "REC001", "senha123", "123456789", 
            "Paula", "912345678", "Rua Teste, 123"
        );
        
        SwingUtilities.invokeLater(() -> new TelaAtendente(recepcionistaTeste));
    }
}
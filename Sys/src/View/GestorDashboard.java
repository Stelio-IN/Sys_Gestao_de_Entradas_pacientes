package view;
import Controller.Ficheiro;
import Controller.GestorController;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Especialidade;
import Model.Medico;
import Model.Pessoa;
import Model.Recepcionista;
import Model.Servico;
import java.util.ArrayList;

public class GestorDashboard extends JFrame {
    
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel medicosPanel, recepcionistasPanel, servicosPanel, relatoriosPanel;
    private GestorController controller;
    
    // Componentes da aba Médicos
    private JTextField txtCrmMedico, txtNomeMedico, txtBiMedico, txtTelefoneMedico, txtEnderecoMedico;
    private JTextField txtCodFuncMedico, txtPasswordMedico;
    private JComboBox<Especialidade> cmbEspecialidade;
    private JButton btnCadastrarMedico, btnEditarMedico, btnDesativarMedico, btnReativarMedico;
    private JTable tblMedicos;
    private DefaultTableModel medicoTableModel;
    
    // Componentes da aba Recepcionistas
    private JTextField txtCodFuncRecep, txtNomeRecep, txtBiRecep, txtTelefoneRecep, txtEnderecoRecep, txtPasswordRecep;
    private JButton btnCadastrarRecep, btnEditarRecep, btnDesativarRecep, btnReativarRecep;
    private JTable tblRecepcionistas;
    private DefaultTableModel recepTableModel;
    
    // Componentes da aba Serviços
    private JTextField txtIdServico, txtNomeServico, txtDescricaoServico;
    private JButton btnCadastrarServico, btnEditarServico, btnDesativarServico, btnReativarServico;
    private JTable tblServicos;
    private DefaultTableModel servicoTableModel;
    
    // Componentes da aba Relatórios
    private JComboBox<String> cmbTipoRelatorio;
    private JButton btnGerarRelatorio;
    private JTextArea txtAreaRelatorio;
    
    public GestorDashboard() {
        controller = new GestorController();
        
        // Configuração do frame principal
        setTitle("Dashboard do Gestor - Sistema Hospitalar");
        setSize(900, 600);
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
        tabbedPane.setBounds(10, 10, 850, 540);
        
        // Inicialização das abas
        initMedicosPanel();
        initRecepcionistasPanel();
        initServicosPanel();
        initRelatoriosPanel();
        
        // Adicionar abas ao TabbedPane
        tabbedPane.addTab("Médicos", medicosPanel);
        tabbedPane.addTab("Recepcionistas", recepcionistasPanel);
        tabbedPane.addTab("Serviços", servicosPanel);
        tabbedPane.addTab("Relatórios", relatoriosPanel);
        
        mainPanel.add(tabbedPane);
        
        add(mainPanel);
    }
    
    private void initMedicosPanel() {
        medicosPanel = new JPanel();
        medicosPanel.setLayout(null);
        
        // Área de cadastro/edição
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 10, 836, 200);
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastro/Edição de Médicos"));
        
        // Labels
        JLabel lblCrm = new JLabel("CRM:");
        lblCrm.setBounds(20, 30, 80, 25);
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 80, 25);
        JLabel lblBi = new JLabel("BI:");
        lblBi.setBounds(20, 90, 80, 25);
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 120, 80, 25);
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(20, 150, 80, 25);
        JLabel lblCodFunc = new JLabel("Cód. Func.:");
        lblCodFunc.setBounds(350, 30, 80, 25);
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(350, 60, 80, 25);
        JLabel lblEspecialidade = new JLabel("Especialidade:");
        lblEspecialidade.setBounds(350, 90, 100, 25);
        
        // Campos de texto
        txtCrmMedico = new JTextField();
        txtCrmMedico.setBounds(110, 30, 200, 25);
        txtNomeMedico = new JTextField();
        txtNomeMedico.setBounds(110, 60, 200, 25);
        txtBiMedico = new JTextField();
        txtBiMedico.setBounds(110, 90, 200, 25);
        txtTelefoneMedico = new JTextField();
        txtTelefoneMedico.setBounds(110, 120, 200, 25);
        txtEnderecoMedico = new JTextField();
        txtEnderecoMedico.setBounds(110, 150, 200, 25);
        txtCodFuncMedico = new JTextField();
        txtCodFuncMedico.setBounds(440, 30, 200, 25);
        txtPasswordMedico = new JTextField();
        txtPasswordMedico.setBounds(440, 60, 200, 25);
        
        // Combobox especialidade
        //cmbEspecialidade = new JComboBox<>();
        cmbEspecialidade = new JComboBox<>();
        cmbEspecialidade.setBounds(440, 90, 200, 25);
        
        // Botões
        btnCadastrarMedico = new JButton("Cadastrar");
        btnCadastrarMedico.setBounds(350, 150, 120, 30);
        btnCadastrarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarMedico();
            }
        });
        
        btnEditarMedico = new JButton("Editar");
        btnEditarMedico.setBounds(480, 150, 80, 30);
        btnEditarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarMedico();
            }
        });
        
        btnDesativarMedico = new JButton("Desativar");
        btnDesativarMedico.setBounds(570, 150, 100, 30);
        btnDesativarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desativarMedico();
            }
        });
        
        btnReativarMedico = new JButton("Reativar");
        btnReativarMedico.setBounds(680, 150, 100, 30);
        btnReativarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reativarMedico();
            }
        });
        
        // Adicionar componentes ao painel de cadastro
        panelCadastro.add(lblCrm);
        panelCadastro.add(txtCrmMedico);
        panelCadastro.add(lblNome);
        panelCadastro.add(txtNomeMedico);
        panelCadastro.add(lblBi);
        panelCadastro.add(txtBiMedico);
        panelCadastro.add(lblTelefone);
        panelCadastro.add(txtTelefoneMedico);
        panelCadastro.add(lblEndereco);
        panelCadastro.add(txtEnderecoMedico);
        panelCadastro.add(lblCodFunc);
        panelCadastro.add(txtCodFuncMedico);
        panelCadastro.add(lblPassword);
        panelCadastro.add(txtPasswordMedico);
        panelCadastro.add(lblEspecialidade);
        panelCadastro.add(cmbEspecialidade);
        panelCadastro.add(btnCadastrarMedico);
        panelCadastro.add(btnEditarMedico);
        panelCadastro.add(btnDesativarMedico);
        panelCadastro.add(btnReativarMedico);
        
        // Tabela de médicos
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 220, 836, 280);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Lista de Médicos"));
        
        String[] colunas = {"CRM", "Nome", "Especialidade", "Telefone", "Status"};
        medicoTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblMedicos = new JTable(medicoTableModel);
        JScrollPane scrollPane = new JScrollPane(tblMedicos);
        scrollPane.setBounds(10, 20, 820, 250);
        panelTabela.add(scrollPane);
        
        // Adicionar painéis ao painel de médicos
        medicosPanel.add(panelCadastro);
        medicosPanel.add(panelTabela);
    }
    
    private void initRecepcionistasPanel() {
        recepcionistasPanel = new JPanel();
        recepcionistasPanel.setLayout(null);
        
        // Área de cadastro/edição
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 10, 836, 180);
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastro/Edição de Recepcionistas"));
        
        // Labels
        JLabel lblCodFunc = new JLabel("Cód. Func.:");
        lblCodFunc.setBounds(20, 30, 80, 25);
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 80, 25);
        JLabel lblBi = new JLabel("BI:");
        lblBi.setBounds(20, 90, 80, 25);
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 120, 80, 25);
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(350, 30, 80, 25);
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(350, 60, 80, 25);
        
        // Campos de texto
        txtCodFuncRecep = new JTextField();
        txtCodFuncRecep.setBounds(110, 30, 200, 25);
        txtNomeRecep = new JTextField();
        txtNomeRecep.setBounds(110, 60, 200, 25);
        txtBiRecep = new JTextField();
        txtBiRecep.setBounds(110, 90, 200, 25);
        txtTelefoneRecep = new JTextField();
        txtTelefoneRecep.setBounds(110, 120, 200, 25);
        txtEnderecoRecep = new JTextField();
        txtEnderecoRecep.setBounds(440, 30, 200, 25);
        txtPasswordRecep = new JTextField();
        txtPasswordRecep.setBounds(440, 60, 200, 25);
        
        // Botões
        btnCadastrarRecep = new JButton("Cadastrar");
        btnCadastrarRecep.setBounds(350, 120, 120, 30);
        btnCadastrarRecep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarRecepcionista();
            }
        });
        
        btnEditarRecep = new JButton("Editar");
        btnEditarRecep.setBounds(480, 120, 80, 30);
        btnEditarRecep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarRecepcionista();
            }
        });
        
        btnDesativarRecep = new JButton("Desativar");
        btnDesativarRecep.setBounds(570, 120, 100, 30);
        btnDesativarRecep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desativarRecepcionista();
            }
        });
        
        btnReativarRecep = new JButton("Reativar");
        btnReativarRecep.setBounds(680, 120, 100, 30);
        btnReativarRecep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reativarRecepcionista();
            }
        });
        
        // Adicionar componentes ao painel de cadastro
        panelCadastro.add(lblCodFunc);
        panelCadastro.add(txtCodFuncRecep);
        panelCadastro.add(lblNome);
        panelCadastro.add(txtNomeRecep);
        panelCadastro.add(lblBi);
        panelCadastro.add(txtBiRecep);
        panelCadastro.add(lblTelefone);
        panelCadastro.add(txtTelefoneRecep);
        panelCadastro.add(lblEndereco);
        panelCadastro.add(txtEnderecoRecep);
        panelCadastro.add(lblPassword);
        panelCadastro.add(txtPasswordRecep);
        panelCadastro.add(btnCadastrarRecep);
        panelCadastro.add(btnEditarRecep);
        panelCadastro.add(btnDesativarRecep);
        panelCadastro.add(btnReativarRecep);
        
        // Tabela de recepcionistas
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 200, 836, 300);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Lista de Recepcionistas"));
        
        String[] colunas = {"Cód. Func.", "Nome", "Telefone", "Endereço", "Status"};
        recepTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblRecepcionistas = new JTable(recepTableModel);
        JScrollPane scrollPane = new JScrollPane(tblRecepcionistas);
        scrollPane.setBounds(10, 20, 820, 270);
        panelTabela.add(scrollPane);
        
        // Adicionar painéis ao painel de recepcionistas
        recepcionistasPanel.add(panelCadastro);
        recepcionistasPanel.add(panelTabela);
    }
    
    private void initServicosPanel() {
        servicosPanel = new JPanel();
        servicosPanel.setLayout(null);
        
        // Área de cadastro/edição
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 10, 836, 150);
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastro/Edição de Serviços"));
        
        // Labels
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 30, 80, 25);
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 80, 25);
        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(20, 90, 80, 25);
        
        // Campos de texto
        txtIdServico = new JTextField();
        txtIdServico.setBounds(110, 30, 200, 25);
        txtNomeServico = new JTextField();
        txtNomeServico.setBounds(110, 60, 200, 25);
        txtDescricaoServico = new JTextField();
        txtDescricaoServico.setBounds(110, 90, 500, 25);
        
        // Botões
        btnCadastrarServico = new JButton("Cadastrar");
        btnCadastrarServico.setBounds(350, 30, 120, 30);
        btnCadastrarServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarServico();
            }
        });
        
        btnEditarServico = new JButton("Editar");
        btnEditarServico.setBounds(480, 30, 80, 30);
        btnEditarServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarServico();
            }
        });
        
        btnDesativarServico = new JButton("Desativar");
        btnDesativarServico.setBounds(570, 30, 100, 30);
        btnDesativarServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desativarServico();
            }
        });
        
        btnReativarServico = new JButton("Reativar");
        btnReativarServico.setBounds(680, 30, 100, 30);
        btnReativarServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reativarServico();
            }
        });
        
        // Adicionar componentes ao painel de cadastro
        panelCadastro.add(lblId);
        panelCadastro.add(txtIdServico);
        panelCadastro.add(lblNome);
        panelCadastro.add(txtNomeServico);
        panelCadastro.add(lblDescricao);
        panelCadastro.add(txtDescricaoServico);
        panelCadastro.add(btnCadastrarServico);
        panelCadastro.add(btnEditarServico);
        panelCadastro.add(btnDesativarServico);
        panelCadastro.add(btnReativarServico);
        
        // Tabela de serviços
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(null);
        panelTabela.setBounds(10, 170, 836, 330);
        panelTabela.setBorder(BorderFactory.createTitledBorder("Lista de Serviços"));
        
        String[] colunas = {"ID", "Nome", "Descrição", "Status"};
        servicoTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblServicos = new JTable(servicoTableModel);
        JScrollPane scrollPane = new JScrollPane(tblServicos);
        scrollPane.setBounds(10, 20, 820, 300);
        panelTabela.add(scrollPane);
        
        // Adicionar painéis ao painel de serviços
        servicosPanel.add(panelCadastro);
        servicosPanel.add(panelTabela);
    }
    
    private void initRelatoriosPanel() {
        relatoriosPanel = new JPanel();
        relatoriosPanel.setLayout(null);
        
        // Área de seleção de relatório
        JPanel panelSelecao = new JPanel();
        panelSelecao.setLayout(null);
        panelSelecao.setBounds(10, 10, 836, 70);
        panelSelecao.setBorder(BorderFactory.createTitledBorder("Seleção de Relatório"));
        
        JLabel lblTipo = new JLabel("Tipo de Relatório:");
        lblTipo.setBounds(20, 30, 120, 25);
        
        String[] tiposRelatorio = {
            "Médicos Ativos", 
            "Médicos Inativos", 
            "Recepcionistas Ativos", 
            "Recepcionistas Inativos", 
            "Serviços Disponíveis", 
            "Serviços Indisponíveis"
        };
        cmbTipoRelatorio = new JComboBox<>(tiposRelatorio);
        cmbTipoRelatorio.setBounds(150, 30, 200, 25);
        
        btnGerarRelatorio = new JButton("Gerar Relatório");
        btnGerarRelatorio.setBounds(370, 30, 150, 30);
        btnGerarRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
        
        // Adicionar componentes ao painel de seleção
        panelSelecao.add(lblTipo);
        panelSelecao.add(cmbTipoRelatorio);
        panelSelecao.add(btnGerarRelatorio);
        
        // Área de visualização do relatório
        JPanel panelVisualizacao = new JPanel();
        panelVisualizacao.setLayout(null);
        panelVisualizacao.setBounds(10, 90, 840, 410);
        panelVisualizacao.setBorder(BorderFactory.createTitledBorder("Visualização do Relatório"));
        
        txtAreaRelatorio = new JTextArea();
        txtAreaRelatorio.setEditable(false);
        txtAreaRelatorio.setWrapStyleWord(true);
        txtAreaRelatorio.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtAreaRelatorio);
        scrollPane.setBounds(10, 20, 820, 380);
        panelVisualizacao.add(scrollPane);
        
        // Adicionar painéis ao painel de relatórios
        relatoriosPanel.add(panelSelecao);
        relatoriosPanel.add(panelVisualizacao);
    }
    
    private void carregarDados() {
        Ficheiro f1 = new Ficheiro();
          ArrayList<Pessoa> lista = f1.carregarDoArquivo("pessoa");
           ArrayList<Servico> listaServico = f1.carregarDoArquivo("servico");
         //  DefaultTableModel model = (DefaultTableModel) tabela.getModel();
         
       //   String[] colunas = {"CRM", "Nome", "Especialidade", "Telefone", "Status"};
        medicoTableModel.setRowCount(0);
        recepTableModel.setRowCount(0);
        servicoTableModel.setRowCount(0);
                
        
                 for (Pessoa p : lista) {
                    if (p instanceof Medico) {
                        Medico m = (Medico) p;
                  medicoTableModel.addRow(new Object[]{((Medico) p).getCrm(),m.getNome(),m.getEspecialidade().getEspecialidade(),m.getTelefone(),m.isStatus()});
           
                    }
                }
               //  String[] colunas = {"Cód. Func.", "Nome", "Telefone", "Endereço", "Status"};
                 for (Pessoa p : lista) {
                    if (p instanceof Recepcionista) {
                        Recepcionista m = (Recepcionista) p;
                  recepTableModel.addRow(new Object[]{m.getCod_func(),m.getNome(),m.getTelefone(),m.getEndereco(),m.isStatus()});
           
                    }
                }
                 
                 //String[] colunas = {"ID", "Nome", "Descrição", "Status"};
                 for (Servico s : listaServico) {       
                  servicoTableModel.addRow(new Object[]{s.getId(),s.getNomeServico(),s.getDescricao(),s.isStatus()}); 
                }
                 
                 ArrayList<Especialidade> listaEspecialidades = new ArrayList<>();
listaEspecialidades.add(new Especialidade("Cardiologia", "Trata do coração"));
listaEspecialidades.add(new Especialidade("Pediatria", "Cuida da saúde infantil"));
listaEspecialidades.add(new Especialidade("Dermatologia", "Trata da pele"));
listaEspecialidades.add(new Especialidade("Ortopedia", "Trata dos ossos e articulações"));


for (Especialidade esp : listaEspecialidades) {
    cmbEspecialidade.addItem(esp);
}

    
        // Aqui seriam implementados os métodos para carregar os dados iniciais
        // das tabelas de médicos, recepcionistas e serviços
        // Usando os métodos do controller como listarMedico(), listarRecepcionistas() e listarServicos()
    }
    
    // Métodos para ações dos botões de Médicos
    private void cadastrarMedico() {
        try {
            String crm = txtCrmMedico.getText();
            String nome = txtNomeMedico.getText();
            String bi = txtBiMedico.getText();
            String telefone = txtTelefoneMedico.getText();
            String endereco = txtEnderecoMedico.getText();
            String codFunc = txtCodFuncMedico.getText();
            String password = txtPasswordMedico.getText();
            Especialidade especialidade = (Especialidade) cmbEspecialidade.getSelectedItem();
           // Especialidade especialidade = new Especialidade("", "");
            // Validação básica
            if (crm.isEmpty() || nome.isEmpty() || bi.isEmpty() || telefone.isEmpty() || 
                endereco.isEmpty() || codFunc.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.cadastrarMedico(especialidade, crm, codFunc, password, bi, nome, telefone, endereco);
            JOptionPane.showMessageDialog(this, "Médico cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposMedico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarMedico() {
        try {
            String crm = txtCrmMedico.getText();
            String telefone = txtTelefoneMedico.getText();
            String endereco = txtEnderecoMedico.getText();
            String password = txtPasswordMedico.getText();
           // Especialidade especialidade = (Especialidade) cmbEspecialidade.getSelectedItem();
              Especialidade especialidade = new Especialidade("", "");
            // Validação básica
            if (crm.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "CRM, Telefone, Endereço e Password são obrigatórios para edição!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.editarMedico(crm, especialidade, password, endereco, telefone);
            JOptionPane.showMessageDialog(this, "Médico editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposMedico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao editar médico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void desativarMedico() {
        try {
            String crm = txtCrmMedico.getText();
            
            // Validação básica
            if (crm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o CRM do médico a ser desativado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.desativarMedico(crm);
            JOptionPane.showMessageDialog(this, "Médico desativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposMedico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao desativar médico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void reativarMedico() {
        try {
            String crm = txtCrmMedico.getText();
            
            // Validação básica
            if (crm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o CRM do médico a ser reativado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.reativarMedico(crm);
            JOptionPane.showMessageDialog(this, "Médico reativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposMedico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao reativar médico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparCamposMedico() {
        txtCrmMedico.setText("");
        txtNomeMedico.setText("");
        txtBiMedico.setText("");
        txtTelefoneMedico.setText("");
        txtEnderecoMedico.setText("");
        txtCodFuncMedico.setText("");
        txtPasswordMedico.setText("");
      //  cmbEspecialidade.setSelectedIndex(0);
    }
    
    // Métodos para ações dos botões de Recepcionistas
    private void cadastrarRecepcionista() {
        try {
            String codFunc = txtCodFuncRecep.getText();
            String nome = txtNomeRecep.getText();
            String bi = txtBiRecep.getText();
            String telefone = txtTelefoneRecep.getText();
            String endereco = txtEnderecoRecep.getText();
            String password = txtPasswordRecep.getText();
            
            // Validação básica
            if (codFunc.isEmpty() || nome.isEmpty() || bi.isEmpty() || 
                telefone.isEmpty() || endereco.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.cadastrarRecepcionista(codFunc, bi, nome, telefone, endereco);
            JOptionPane.showMessageDialog(this, "Recepcionista cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposRecepcionista();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar recepcionista: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarRecepcionista() {
        try {
            String codFunc = txtCodFuncRecep.getText();
            String telefone = txtTelefoneRecep.getText();
            String endereco = txtEnderecoRecep.getText();
            String password = txtPasswordRecep.getText();
            // Validação básica
            if (codFunc.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código de Funcionário, Telefone, Endereço e Password são obrigatórios para edição!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.editarRecepcionista(codFunc, password, endereco, telefone);
            JOptionPane.showMessageDialog(this, "Recepcionista editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposRecepcionista();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao editar recepcionista: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void desativarRecepcionista() {
        try {
            String codFunc = txtCodFuncRecep.getText();
            
            // Validação básica
            if (codFunc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o Código de Funcionário do recepcionista a ser desativado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.desativarRecepcionista(codFunc);
            JOptionPane.showMessageDialog(this, "Recepcionista desativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposRecepcionista();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao desativar recepcionista: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void reativarRecepcionista() {
        try {
            String codFunc = txtCodFuncRecep.getText();
            
            // Validação básica
            if (codFunc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o Código de Funcionário do recepcionista a ser reativado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.reativarRecepcionista(codFunc);
            JOptionPane.showMessageDialog(this, "Recepcionista reativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposRecepcionista();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao reativar recepcionista: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparCamposRecepcionista() {
        txtCodFuncRecep.setText("");
        txtNomeRecep.setText("");
        txtBiRecep.setText("");
        txtTelefoneRecep.setText("");
        txtEnderecoRecep.setText("");
        txtPasswordRecep.setText("");
    }
    
    // Métodos para ações dos botões de Serviços
    private void cadastrarServico() {
        try {
            String id = txtIdServico.getText();
            String nome = txtNomeServico.getText();
            String descricao = txtDescricaoServico.getText();
            
            // Validação básica
            if (id.isEmpty() || nome.isEmpty() || descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.cadastrarServico(id, nome, descricao);
            JOptionPane.showMessageDialog(this, "Serviço cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposServico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar serviço: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarServico() {
        try {
            String id = txtIdServico.getText();
            String nome = txtNomeServico.getText();
            String descricao = txtDescricaoServico.getText();
            
            // Validação básica
            if (id.isEmpty() || nome.isEmpty() || descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios para edição!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.editarServico(id, nome, descricao);
            JOptionPane.showMessageDialog(this, "Serviço editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposServico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao editar serviço: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void desativarServico() {
        try {
            String id = txtIdServico.getText();
            
            // Validação básica
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o ID do serviço a ser desativado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.desativarServico(id);
            JOptionPane.showMessageDialog(this, "Serviço desativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposServico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao desativar serviço: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void reativarServico() {
        try {
            String id = txtIdServico.getText();
            
            // Validação básica
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o ID do serviço a ser reativado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.activarServico(id);
            JOptionPane.showMessageDialog(this, "Serviço reativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar campos e recarregar tabela
            limparCamposServico();
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao reativar serviço: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparCamposServico() {
        txtIdServico.setText("");
        txtNomeServico.setText("");
        txtDescricaoServico.setText("");
    }
    
    // Método para ação do botão Gerar Relatório
    private void gerarRelatorio() {
        String tipoRelatorio = (String) cmbTipoRelatorio.getSelectedItem();
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append("RELATÓRIO: ").append(tipoRelatorio).append("\n");
        relatorio.append("Data de geração: ").append(new java.util.Date()).append("\n\n");
        Ficheiro f1 = new Ficheiro();
        ArrayList<Pessoa> lista = f1.carregarDoArquivo("pessoa");
        ArrayList<Servico> listaServico = f1.carregarDoArquivo("servico");
        
        switch(tipoRelatorio) {
            case "Médicos Ativos":
                relatorio.append("LISTA DE MÉDICOS ATIVOS\n");
                relatorio.append("=======================\n\n");
                
                for (Pessoa p : lista) {
                    if (p instanceof Medico) {
                        Medico m = (Medico) p;
                        if (m.isStatus()) {
                            relatorio.append("CRM: ").append(m.getCrm()).append("\n");
                            relatorio.append("Nome: ").append(m.getNome()).append("\n");
                            relatorio.append("Especialidade: ").append(m.getEspecialidade()).append("\n");
                            relatorio.append("Telefone: ").append(m.getTelefone()).append("\n");
                            relatorio.append("Endereço: ").append(m.getEndereco()).append("\n");
                            relatorio.append("-----------------------\n");
                        }
                    }
                }
                break;
                
            case "Médicos Inativos":
                relatorio.append("LISTA DE MÉDICOS INATIVOS\n");
                relatorio.append("========================\n\n");
                
                for (Pessoa p : lista) {
                    if (p instanceof Medico) {
                        Medico m = (Medico) p;
                        if (!m.isStatus()) {
                            relatorio.append("CRM: ").append(m.getCrm()).append("\n");
                            relatorio.append("Nome: ").append(m.getNome()).append("\n");
                            relatorio.append("Especialidade: ").append(m.getEspecialidade()).append("\n");
                            relatorio.append("Telefone: ").append(m.getTelefone()).append("\n");
                            relatorio.append("Endereço: ").append(m.getEndereco()).append("\n");
                            relatorio.append("-----------------------\n");
                        }
                    }
                }
                break;
                
            case "Recepcionistas Ativos":
                relatorio.append("LISTA DE RECEPCIONISTAS ATIVOS\n");
                relatorio.append("=============================\n\n");
                
                for (Pessoa p : lista) {
                    if (p instanceof Recepcionista) {
                        Recepcionista r = (Recepcionista) p;
                        if (r.isStatus()) {
                            relatorio.append("Código: ").append(r.getCod_func()).append("\n");
                            relatorio.append("Nome: ").append(r.getNome()).append("\n");
                            relatorio.append("Telefone: ").append(r.getTelefone()).append("\n");
                            relatorio.append("Endereço: ").append(r.getEndereco()).append("\n");
                            relatorio.append("-----------------------\n");
                        }
                    }
                }
                break;
                
            case "Recepcionistas Inativos":
                relatorio.append("LISTA DE RECEPCIONISTAS INATIVOS\n");
                relatorio.append("==============================\n\n");
                
                for (Pessoa p : lista) {
                    if (p instanceof Recepcionista) {
                        Recepcionista r = (Recepcionista) p;
                        if (!r.isStatus()) {
                            relatorio.append("Código: ").append(r.getCod_func()).append("\n");
                            relatorio.append("Nome: ").append(r.getNome()).append("\n");
                            relatorio.append("Telefone: ").append(r.getTelefone()).append("\n");
                            relatorio.append("Endereço: ").append(r.getEndereco()).append("\n");
                            relatorio.append("-----------------------\n");
                        }
                    }
                }
                break;
                
            case "Serviços Disponíveis":
                relatorio.append("LISTA DE SERVIÇOS DISPONÍVEIS\n");
                relatorio.append("===========================\n\n");
                
                for (Servico s : listaServico) {
                    if (s.isStatus()) {
                        relatorio.append("ID: ").append(s.getId()).append("\n");
                        relatorio.append("Nome: ").append(s.getNomeServico()).append("\n");
                        relatorio.append("Descrição: ").append(s.getDescricao()).append("\n");
                        relatorio.append("-----------------------\n");
                    }
                }
                break;
                
            case "Serviços Indisponíveis":
                relatorio.append("LISTA DE SERVIÇOS INDISPONÍVEIS\n");
                relatorio.append("==============================\n\n");
                
                for (Servico s : listaServico) {
                    if (!s.isStatus()) {
                        relatorio.append("ID: ").append(s.getId()).append("\n");
                        relatorio.append("Nome: ").append(s.getNomeServico()).append("\n");
                        relatorio.append("Descrição: ").append(s.getDescricao()).append("\n");
                        relatorio.append("-----------------------\n");
                    }
                }
                break;
        }
        
        txtAreaRelatorio.setText(relatorio.toString());
    }
    
    // Método main para executar a aplicação
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestorDashboard();
            }
        });
    }
}
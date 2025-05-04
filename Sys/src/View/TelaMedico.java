package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.*;
import Controller.*;
import javax.swing.table.DefaultTableModel;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

public class TelaMedico extends JFrame {
    // Cores do tema
    private final Color corPrimaria = new Color(41, 128, 185); // Azul hospitalar
    private final Color corSecundaria = new Color(236, 240, 241); // Cinza claro
    private final Color corTexto = new Color(44, 62, 80); // Azul escuro
    private final Color corDestaque = new Color(26, 188, 156); // Verde hospitalar
    private final Color corSombra = new Color(0, 0, 0, 30); // Sombra semi-transparente
    
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
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 1100, 750, 15, 15));
        
        // Painel principal com gradiente
        JPanel painelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gp = new GradientPaint(0, 0, new Color(240, 248, 255), 
                             0, getHeight(), new Color(214, 234, 248));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        
        // Barra de título personalizada
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setOpaque(false);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel lblTitulo = new JLabel("GESPA - Área Médica");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(corTexto);
        panelTitulo.add(lblTitulo, BorderLayout.WEST);
        
        // Botões da janela
        JPanel panelBotoesJanela = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panelBotoesJanela.setOpaque(false);
        
        JButton btnMinimizar = criarBotaoJanela("−");
        btnMinimizar.addActionListener(e -> setState(JFrame.ICONIFIED));
        panelBotoesJanela.add(btnMinimizar);
        
        JButton btnFechar = criarBotaoJanela("×");
        btnFechar.addActionListener(e -> System.exit(0));
        panelBotoesJanela.add(btnFechar);
        
        panelTitulo.add(panelBotoesJanela, BorderLayout.EAST);
        painelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        
        // Barra de status
        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelStatus.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        panelStatus.setBackground(new Color(255, 255, 255, 180));
        
        JLabel lblStatus = new JLabel(String.format(
            "<html><b>Usuário:</b> %s | <b>CRM:</b> %s | <b>Especialidade:</b> %s | %s</html>",
            medico.getNome(),
            medico.getCrm(),
            medico.getEspecialidade().getEspecialidade(),
            new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())
        ));
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatus.setForeground(corTexto);
        panelStatus.add(lblStatus);
        
        painelPrincipal.add(panelStatus, BorderLayout.SOUTH);
        
        // Área principal com abas
        abas = new JTabbedPane();
        abas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        abas.setBackground(Color.WHITE);
        abas.setForeground(corTexto);
        
        // Estilizar as abas
        UIManager.put("TabbedPane.selected", corSecundaria);
        UIManager.put("TabbedPane.borderHightlightColor", corPrimaria);
        UIManager.put("TabbedPane.contentAreaColor", corSecundaria);
        
        painelPrincipal.add(abas, BorderLayout.CENTER);
        
        // Efeito para arrastar a janela
        MouseAdapter dragListener = new MouseAdapter() {
            private int mouseX, mouseY;
            
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
            
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocation().x + e.getX() - mouseX,
                            getLocation().y + e.getY() - mouseY);
            }
        };
        
        panelTitulo.addMouseListener(dragListener);
        panelTitulo.addMouseMotionListener(dragListener);
        
        setContentPane(painelPrincipal);
    }
    
    private JButton criarBotaoJanela(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(corTexto);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(30, 30));
        
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setForeground(texto.equals("×") ? Color.RED : corPrimaria);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                botao.setForeground(corTexto);
            }
        });
        
        return botao;
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
        panelConsultas.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelConsultas.setOpaque(false);
        
        // Painel de pesquisa/filtro
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFiltros.setOpaque(false);
        
        JLabel lblFiltro = new JLabel("Filtrar:");
        lblFiltro.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFiltros.add(lblFiltro);
        
        cmbFiltroConsulta = new JComboBox<>(new String[]{
            "Consultas de Hoje", 
            "Pendentes", 
            "Em Andamento", 
            "Todas as Consultas"
        });
        estilizarComboBox(cmbFiltroConsulta);
        cmbFiltroConsulta.setSelectedIndex(0);
        cmbFiltroConsulta.addActionListener(e -> atualizarTabelaConsultas());
        panelFiltros.add(cmbFiltroConsulta);
        
        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFiltros.add(lblPaciente);
        
        txtPesquisaPaciente = new JTextField(15);
        estilizarCampoTexto(txtPesquisaPaciente);
        panelFiltros.add(txtPesquisaPaciente);
        
        JButton btnPesquisar = criarBotao("Pesquisar", corPrimaria);
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
        estilizarTabela(tblConsultas);
        JScrollPane scrollPane = new JScrollPane(tblConsultas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBotoes.setOpaque(false);
        
        btnIniciarConsulta = criarBotao("Iniciar Consulta", corDestaque);
        btnIniciarConsulta.addActionListener(e -> iniciarConsulta());
        panelBotoes.add(btnIniciarConsulta);
        
        btnFinalizarConsulta = criarBotao("Finalizar Consulta", corPrimaria);
        btnFinalizarConsulta.addActionListener(e -> finalizarConsulta());
        btnFinalizarConsulta.setEnabled(false);
        panelBotoes.add(btnFinalizarConsulta);
        
        // Adicionar componentes ao painel principal
        panelConsultas.add(panelFiltros, BorderLayout.NORTH);
        panelConsultas.add(scrollPane, BorderLayout.CENTER);
        panelConsultas.add(panelBotoes, BorderLayout.SOUTH);
        
        abas.addTab("Minhas Consultas", criarIconeConsulta(), panelConsultas);
    }
    
    private void criarAbaAtendimento() {
        panelAtendimento = new JPanel(new BorderLayout(10, 10));
        panelAtendimento.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelAtendimento.setOpaque(false);
        
        // Painel de informações da consulta
        JPanel panelInfoConsulta = new JPanel(new GridLayout(0, 2, 10, 10));
        panelInfoConsulta.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Informações da Consulta",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 13),
            corTexto
        ));
        panelInfoConsulta.setBackground(new Color(255, 255, 255, 180));
        
        JLabel lblPaciente = criarLabelInfo("Paciente:");
        JTextField txtPacienteInfo = criarCampoInfo();
        panelInfoConsulta.add(lblPaciente);
        panelInfoConsulta.add(txtPacienteInfo);
        
        JLabel lblDataHora = criarLabelInfo("Data/Hora:");
        JTextField txtDataHoraInfo = criarCampoInfo();
        panelInfoConsulta.add(lblDataHora);
        panelInfoConsulta.add(txtDataHoraInfo);
        
        JLabel lblServico = criarLabelInfo("Serviço:");
        JTextField txtServicoInfo = criarCampoInfo();
        panelInfoConsulta.add(lblServico);
        panelInfoConsulta.add(txtServicoInfo);
        
        // Painel de formulário de atendimento
        JPanel panelFormulario = new JPanel(new BorderLayout(10, 10));
        panelFormulario.setOpaque(false);
        
        JPanel panelDiagnostico = criarPainelTextArea("Diagnóstico:", txtDiagnostico = new JTextArea(5, 30));
        JPanel panelMedicacao = criarPainelTextArea("Medicação:", txtMedicacao = new JTextArea(3, 30));
        JPanel panelObservacoes = criarPainelTextArea("Observações:", txtObservacoes = new JTextArea(3, 30));
        
        panelFormulario.add(panelDiagnostico, BorderLayout.NORTH);
        panelFormulario.add(panelMedicacao, BorderLayout.CENTER);
        panelFormulario.add(panelObservacoes, BorderLayout.SOUTH);
        
        // Painel de botões
        btnSalvarAtendimento = criarBotao("Salvar Atendimento", corDestaque);
        btnSalvarAtendimento.addActionListener(e -> salvarAtendimento());
        btnSalvarAtendimento.setEnabled(false);
        
        // Adicionar componentes ao painel principal
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setOpaque(false);
        panelSuperior.add(panelInfoConsulta, BorderLayout.NORTH);
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        
        panelAtendimento.add(panelSuperior, BorderLayout.CENTER);
        panelAtendimento.add(btnSalvarAtendimento, BorderLayout.SOUTH);
        
        abas.addTab("Atendimento", criarIconeAtendimento(), panelAtendimento);
    }
    
    private void criarAbaPacientes() {
        panelPacientes = new JPanel(new BorderLayout(10, 10));
        panelPacientes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPacientes.setOpaque(false);
        
        // Painel de pesquisa
        JPanel panelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelPesquisa.setOpaque(false);
        
        JLabel lblPesquisa = new JLabel("Pesquisar Paciente:");
        lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelPesquisa.add(lblPesquisa);
        
        txtPesquisaHistorico = new JTextField(20);
        estilizarCampoTexto(txtPesquisaHistorico);
        panelPesquisa.add(txtPesquisaHistorico);
        
        btnVerHistorico = criarBotao("Ver Histórico", corPrimaria);
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
        estilizarTabela(tblPacientes);
        JScrollPane scrollPane = new JScrollPane(tblPacientes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        // Adicionar componentes ao painel principal
        panelPacientes.add(panelPesquisa, BorderLayout.NORTH);
        panelPacientes.add(scrollPane, BorderLayout.CENTER);
        
        abas.addTab("Pacientes", criarIconePaciente(), panelPacientes);
    }
    
    // Métodos auxiliares para estilização
    private JLabel criarLabelInfo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(corTexto);
        return label;
    }
    
    private JTextField criarCampoInfo() {
        JTextField campo = new JTextField();
        campo.setEditable(false);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campo.setBackground(Color.WHITE);
        return campo;
    }
    
    private JPanel criarPainelTextArea(String titulo, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(corTexto);
        panel.add(label, BorderLayout.NORTH);
        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(corFundo.darker());
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                } else if (getModel().isRollover()) {
                    g2d.setColor(corFundo.brighter());
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                } else {
                    g2d.setColor(corFundo);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }
                
                FontMetrics fm = g2d.getFontMetrics();
                Rectangle2D rect = fm.getStringBounds(getText(), g2d);
                
                int textX = (getWidth() - (int) rect.getWidth()) / 2;
                int textY = (getHeight() - (int) rect.getHeight()) / 2 + fm.getAscent();
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                g2d.drawString(getText(), textX, textY);
            }
        };
        
        botao.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botao.setForeground(Color.WHITE);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(150, 35));
        
        return botao;
    }
    
    private void estilizarComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }
    
    private void estilizarCampoTexto(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }
    
    private void estilizarTabela(JTable tabela) {
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabela.setRowHeight(25);
        tabela.setSelectionBackground(corPrimaria);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setGridColor(new Color(240, 240, 240));
        tabela.setShowGrid(true);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        tabela.setFillsViewportHeight(true);
        tabela.setAutoCreateRowSorter(true);
        
        // Cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(corPrimaria);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
    }
    
    private Icon criarIconeConsulta() {
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(corPrimaria);
        g2d.fillOval(2, 2, 12, 12);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(6, 4, 4, 8);
        g2d.fillRect(4, 6, 8, 4);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    private Icon criarIconeAtendimento() {
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(corDestaque);
        g2d.fillRoundRect(2, 2, 12, 12, 3, 3);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(5, 4, 6, 8);
        g2d.fillRect(4, 5, 8, 6);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    private Icon criarIconePaciente() {
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(new Color(155, 89, 182)); // Roxo
        g2d.fillOval(2, 2, 12, 12);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(5, 4, 6, 6);
        g2d.fillRect(5, 10, 6, 3);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    // Métodos de funcionalidade (mantidos da versão original)
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
        
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TelaMedico(medicoTeste);
        });
    }
}
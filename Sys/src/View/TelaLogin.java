package View;

import Controller.Ficheiro;
import Controller.GestorController;
import Model.Gestor;
import Model.Medico;
import Model.Pessoa;
import Model.Recepcionista;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import view.GestorDashboard;

public class TelaLogin extends JFrame {

    Ficheiro f1 = new Ficheiro();
    ArrayList<Pessoa> lista = new ArrayList<>();

    // Componentes
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JPanel painelPrincipal;
    private JLabel logoLabel;

    // Cores do tema
    private Color corPrimaria = new Color(41, 128, 185); // Azul hospitalar
    private Color corSecundaria = new Color(236, 240, 241); // Cinza claro
    private Color corTexto = new Color(44, 62, 80); // Azul escuro
    private Color corDestaque = new Color(26, 188, 156); // Verde hospitalar
    private Color corLogo = new Color(220, 53, 69); // Vermelho
    private Color corSombra = new Color(0, 0, 0, 30); // Sombra semi-transparente

    public TelaLogin() {
        super("Sistema de Gestão Hospitalar");

        // Configurações da janela
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza
        setResizable(false);
        setUndecorated(true); // Remove bordas padrão da janela
        setShape(new RoundRectangle2D.Double(0, 0, 420, 550, 15, 15)); // Bordas arredondadas

        // Painel principal com gradient suave
        painelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient de fundo mais suave
                GradientPaint gp = new GradientPaint(0, 0, new Color(240, 248, 255), 
                                                    0, getHeight(), new Color(214, 234, 248));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Adiciona um sutil efeito de brilho na parte superior
                GradientPaint highlight = new GradientPaint(
                    0, 0, new Color(255, 255, 255, 100),
                    0, getHeight()/3, new Color(255, 255, 255, 0));
                g2d.setPaint(highlight);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight()/2, 15, 15);
            }
        };
        painelPrincipal.setLayout(null);
        setContentPane(painelPrincipal);

        // Botão de fechar na janela
        JButton btnFechar = new JButton("×");
        btnFechar.setBounds(385, 10, 25, 25);
        btnFechar.setForeground(corTexto);
        btnFechar.setFont(new Font("Arial", Font.BOLD, 18));
        btnFechar.setBorder(null);
        btnFechar.setContentAreaFilled(false);
        btnFechar.setFocusPainted(false);
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFechar.addActionListener(e -> System.exit(0));
        btnFechar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnFechar.setForeground(corLogo);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnFechar.setForeground(corTexto);
            }
        });
        painelPrincipal.add(btnFechar);

        // Logo do hospital com cor vermelha
        ImageIcon logoIcon = criarLogoHospital();
        logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(160, 30, 100, 100);
        painelPrincipal.add(logoLabel);

        // Título do Sistema com efeito de sombra
        JLabel labelSombra = new JLabel("GESPA");
        labelSombra.setFont(new Font("Segoe UI", Font.BOLD, 28));
        labelSombra.setForeground(corSombra);
        labelSombra.setBounds(172, 142, 250, 40);
        painelPrincipal.add(labelSombra);
        
        JLabel labelTitulo = new JLabel("GESPA");
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        labelTitulo.setForeground(corPrimaria);
        labelTitulo.setBounds(170, 140, 250, 40);
        painelPrincipal.add(labelTitulo);
        
        // Subtítulo
        JLabel labelSubtitulo = new JLabel("Sistema de Gestão Hospitalar");
        labelSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelSubtitulo.setForeground(new Color(102, 102, 102));
        labelSubtitulo.setBounds(118, 175, 250, 20);
        painelPrincipal.add(labelSubtitulo);

        // Painel de formulário com sombra
        JPanel painelFormulario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Desenha a sombra
                g2d.setColor(corSombra);
                g2d.fillRoundRect(5, 5, getWidth(), getHeight(), 20, 20);
                
                // Desenha o painel
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth()-5, getHeight()-5, 20, 20);
            }
        };
        painelFormulario.setLayout(null);
        painelFormulario.setBounds(60, 210, 300, 240);
        painelFormulario.setOpaque(false);

        // Componentes do formulário
        JLabel labelUsuario = new JLabel("Usuário");
        labelUsuario.setBounds(25, 20, 80, 25);
        labelUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelUsuario.setForeground(corTexto);
        painelFormulario.add(labelUsuario);

        // Ícone de usuário
        JLabel iconUsuario = new JLabel(criarIconeUsuario());
        iconUsuario.setBounds(25, 48, 24, 24);
        painelFormulario.add(iconUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(55, 45, 220, 30);
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, new Color(230, 230, 230)),
                new EmptyBorder(2, 8, 2, 8)));
        campoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        painelFormulario.add(campoUsuario);

        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setBounds(25, 85, 80, 25);
        labelSenha.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelSenha.setForeground(corTexto);
        painelFormulario.add(labelSenha);

        // Ícone de senha
        JLabel iconSenha = new JLabel(criarIconeSenha());
        iconSenha.setBounds(25, 113, 24, 24);
        painelFormulario.add(iconSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(55, 110, 220, 30);
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, new Color(230, 230, 230)),
                new EmptyBorder(2, 8, 2, 8)));
        campoSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoSenha.setEchoChar('•'); // Caractere de senha mais moderno
        painelFormulario.add(campoSenha);

        // Checkbox para mostrar/ocultar senha
        JCheckBox showPassword = new JCheckBox("Mostrar senha");
        showPassword.setBounds(25, 145, 120, 20);
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPassword.setForeground(corTexto);
        showPassword.setOpaque(false);
        showPassword.setFocusPainted(false);
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                campoSenha.setEchoChar((char) 0);
            } else {
                campoSenha.setEchoChar('•');
            }
        });
        painelFormulario.add(showPassword);

        // Botão de login com efeito de elevação
        botaoLogin = new JButton("Entrar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(corPrimaria.darker());
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                } else if (getModel().isRollover()) {
                    g2d.setColor(corDestaque);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                } else {
                    // Efeito de gradiente suave
                    GradientPaint gp = new GradientPaint(
                        0, 0, corPrimaria,
                        0, getHeight(), corPrimaria.darker());
                    g2d.setPaint(gp);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
                
                // Configuração do texto do botão
                FontMetrics fm = g2d.getFontMetrics();
                Rectangle2D rect = fm.getStringBounds(getText(), g2d);
                
                int textX = (getWidth() - (int) rect.getWidth()) / 2;
                int textY = (getHeight() - (int) rect.getHeight()) / 2 + fm.getAscent();
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                g2d.drawString(getText(), textX, textY);
            }
        };
        botaoLogin.setBounds(25, 180, 250, 40);
        botaoLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botaoLogin.setFocusPainted(false);
        botaoLogin.setBorderPainted(false);
        botaoLogin.setContentAreaFilled(false);
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelFormulario.add(botaoLogin);

        painelPrincipal.add(painelFormulario);

        // Rodapé com efeito de transparência
        JPanel painelRodape = new JPanel();
        painelRodape.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelRodape.setBounds(0, 500, 420, 30);
        painelRodape.setOpaque(false);
        
        JLabel labelRodape = new JLabel("© 2025 GESPA - Todos os direitos reservados");
        labelRodape.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        labelRodape.setForeground(new Color(100, 100, 100));
        painelRodape.add(labelRodape);
        
        painelPrincipal.add(painelRodape);

        // Efeito para arrastar a janela sem bordas
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
        
        painelPrincipal.addMouseListener(dragListener);
        painelPrincipal.addMouseMotionListener(dragListener);

        // Ação do botão
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (usuario.isEmpty() || senha.isEmpty()) {
                    mostrarMensagemErro("Por favor, preencha todos os campos.");
                    return;
                }

                lista = f1.carregarDoArquivo("pessoa");
                for (Pessoa a : lista) {
                    if (a instanceof Medico medico) {
                        if (medico.getCod_func().equals(usuario) && medico.getPassword().equals(senha)) {
                            mostrarMensagemSucesso("Login bem-sucedido!");
                            TelaMedico tela = new TelaMedico(medico);
                            dispose(); // Fecha a tela de login
                             return;
                        }
                    } else if (a instanceof Gestor gestor) {
                        if (gestor.getEmail().equals(usuario) && gestor.getPassword().equals(senha)) {
                            mostrarMensagemSucesso("Login bem-sucedido!");
                            GestorDashboard tela0 = new GestorDashboard();
                             return;
                        }
                    } else if (a instanceof Recepcionista recepcionista) {
                        if (recepcionista.getCod_func().equals(usuario) && recepcionista.getPassword().equals(senha)) {
                            mostrarMensagemSucesso("Login bem-sucedido!");
                            TelaAtendente tela1 = new TelaAtendente(recepcionista);
                             return;
                        }
                    }
                }
                mostrarMensagemErro("Usuário ou senha incorretos.");
            }
        });

        // Permite login com a tecla Enter
        KeyListener enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botaoLogin.doClick();
                }
            }
        };

        campoUsuario.addKeyListener(enterKeyListener);
        campoSenha.addKeyListener(enterKeyListener);

        setVisible(true);
    }

    // Método para criar um logo de hospital moderno com cor vermelha
    private ImageIcon criarLogoHospital() {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha um círculo vermelho como fundo
        g2d.setColor(corLogo);
        g2d.fillOval(5, 5, 90, 90);
        
        // Adiciona brilho sutil
        GradientPaint highlight = new GradientPaint(
            25, 15, new Color(255, 255, 255, 80),
            75, 50, new Color(255, 255, 255, 0));
        g2d.setPaint(highlight);
        g2d.fillOval(10, 10, 80, 40);

        // Desenha um símbolo de cruz médica com sombra
        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.fillRect(32, 22, 40, 60); // Vertical - sombra
        g2d.fillRect(22, 42, 60, 20); // Horizontal - sombra

        g2d.setColor(Color.WHITE);
        g2d.fillRect(30, 20, 40, 60); // Vertical
        g2d.fillRect(20, 40, 60, 20); // Horizontal

        g2d.dispose();
        return new ImageIcon(image);
    }

    // Método para criar ícone de usuário
    private ImageIcon criarIconeUsuario() {
        BufferedImage image = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(corPrimaria);
        // Desenha a cabeça
        g2d.fillOval(7, 2, 10, 10);
        // Desenha o corpo
        g2d.fillOval(4, 12, 16, 12);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    // Método para criar ícone de senha
    private ImageIcon criarIconeSenha() {
        BufferedImage image = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(corPrimaria);
        // Desenha o corpo do cadeado
        g2d.fillRoundRect(5, 9, 14, 12, 3, 3);
        // Desenha o arco do cadeado
        g2d.setStroke(new BasicStroke(2.5f));
        g2d.drawArc(7, 4, 10, 10, 0, 180);
        
        g2d.dispose();
        return new ImageIcon(image);
    }

    private void mostrarMensagemErro(String mensagem) {
        // Usar um JDialog personalizado em vez de JOptionPane para manter a estética
        JDialog dialog = new JDialog(this, "Erro de Login", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.errorIcon"));
        panel.add(iconLabel, BorderLayout.WEST);
        
        JLabel msgLabel = new JLabel("<html><body>" + mensagem + "</body></html>");
        msgLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(msgLabel, BorderLayout.CENTER);
        
        JButton okButton = new JButton("OK");
        okButton.setBackground(corLogo);
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setUndecorated(true);
        dialog.setShape(new RoundRectangle2D.Double(0, 0, 300, 150, 15, 15));
        
        dialog.setVisible(true);
    }

    private void mostrarMensagemSucesso(String mensagem) {
        // Usar um JDialog personalizado em vez de JOptionPane para manter a estética
        JDialog dialog = new JDialog(this, "Sucesso", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        panel.add(iconLabel, BorderLayout.WEST);
        
        JLabel msgLabel = new JLabel("<html><body>" + mensagem + "</body></html>");
        msgLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(msgLabel, BorderLayout.CENTER);
        
        JButton okButton = new JButton("OK");
        okButton.setBackground(corDestaque);
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setUndecorated(true);
        dialog.setShape(new RoundRectangle2D.Double(0, 0, 300, 150, 15, 15));
        
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Define o look and feel para uma aparência mais moderna
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Ajusta configurações globais de UI
            UIManager.put("Button.arc", 10);
            UIManager.put("TextField.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin();
            }
        });
    }
}
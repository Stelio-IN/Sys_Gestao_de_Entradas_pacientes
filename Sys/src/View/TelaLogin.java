package View;

import Controller.GestorController;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class TelaLogin extends JFrame {
    
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
    
    public TelaLogin() {
        super("Sistema de Gestão Hospitalar");
        
        // Configurações da janela
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza
        setResizable(false);
        
        // Painel principal com gradient
        painelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, corSecundaria, 0, getHeight(), new Color(224, 234, 252));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelPrincipal.setLayout(null);
        setContentPane(painelPrincipal);
        
        // Logo do hospital (simulado com um ícone de saúde)
        ImageIcon logoIcon = criarIconeSimples();
        logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(150, 40, 100, 100);
        painelPrincipal.add(logoLabel);
        
        // Título
        JLabel labelTitulo = new JLabel("GESPA");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(corPrimaria);
        labelTitulo.setBounds(75, 150, 250, 30);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(labelTitulo);
        
        // Painel de formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(null);
        painelFormulario.setBounds(50, 190, 300, 200);
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(20, 20, 20, 20)));
        
        // Componentes do formulário
        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(20, 20, 80, 25);
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
        labelUsuario.setForeground(corTexto);
        painelFormulario.add(labelUsuario);
        
        campoUsuario = new JTextField();
        campoUsuario.setBounds(20, 45, 260, 30);
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(2, 8, 2, 8)));
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        painelFormulario.add(campoUsuario);
        
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(20, 85, 80, 25);
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 12));
        labelSenha.setForeground(corTexto);
        painelFormulario.add(labelSenha);
        
        campoSenha = new JPasswordField();
        campoSenha.setBounds(20, 110, 260, 30);
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(2, 8, 2, 8)));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        painelFormulario.add(campoSenha);
        
        botaoLogin = new JButton("Entrar");
        botaoLogin.setBounds(20, 150, 260, 35);
        botaoLogin.setBackground(corPrimaria);
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLogin.setFocusPainted(false);
        botaoLogin.setBorderPainted(false);
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelFormulario.add(botaoLogin);
        
        // Adiciona efeitos de hover no botão
        botaoLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoLogin.setBackground(corDestaque);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                botaoLogin.setBackground(corPrimaria);
            }
        });
        
        painelPrincipal.add(painelFormulario);
        
        // Rodapé
        JLabel labelRodape = new JLabel("© 2025 - Todos os direitos reservados");
        labelRodape.setFont(new Font("Arial", Font.PLAIN, 10));
        labelRodape.setForeground(new Color(100, 100, 100));
        labelRodape.setBounds(110, 420, 200, 20);
        painelPrincipal.add(labelRodape);
        
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
                
                GestorController gestor = new GestorController();
                boolean result = gestor.loginGestor(usuario, senha);
                
                if (result) {
                    mostrarMensagemSucesso("Login bem-sucedido!");
                    // Aqui você pode abrir a próxima tela
                    dispose(); // Fecha a tela de login
                } else {
                    mostrarMensagemErro("Usuário ou senha incorretos.");
                }
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
    
    // Método para criar um ícone simples (simulando um logo hospital)
    private ImageIcon criarIconeSimples() {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenha um círculo azul como fundo
        g2d.setColor(corPrimaria);
        g2d.fillOval(5, 5, 90, 90);
        
        // Desenha um símbolo de cruz médica
        g2d.setColor(Color.WHITE);
        g2d.fillRect(30, 20, 40, 60); // Vertical
        g2d.fillRect(20, 40, 60, 20); // Horizontal
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro de Login",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    private void mostrarMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
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
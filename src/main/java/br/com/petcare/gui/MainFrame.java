package br.com.petcare.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    // cores do sistema
    public static final Color COR_PRINCIPAL = new Color(0, 128, 128); 
    public static final Color COR_DESTAQUE = new Color(255, 127, 80); 
    public static final Color COR_FUNDO = new Color(245, 245, 245); 
    public static final Color COR_TEXTO_BRANCO = Color.WHITE;

    public MainFrame() {
        setTitle("PetCare - Sistema de Gerenciamento");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // fontes
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));

        initUI();
    }

    private void initUI() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(COR_PRINCIPAL);
        sidebarPanel.setPreferredSize(new Dimension(200, 0));

        JLabel lblTitulo = new JLabel("PETCARE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO_BRANCO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        JButton btnInicio = criarBotaoMenu("Início");
        JButton btnCadastro = criarBotaoMenu("Cadastro (Dono e Pet)");
        JButton btnAgendamento = criarBotaoMenu("Agendamentos");

        sidebarPanel.add(lblTitulo);
        sidebarPanel.add(btnInicio);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(btnCadastro);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(btnAgendamento);

        add(sidebarPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(COR_FUNDO);

        cardPanel.add(new DashboardPanel(), "DASHBOARD");
        cardPanel.add(new CadastroPanel(), "CADASTRO");
        cardPanel.add(new AgendamentoPanel(), "AGENDAMENTO");

        add(cardPanel, BorderLayout.CENTER);

        btnInicio.addActionListener(e -> cardLayout.show(cardPanel, "DASHBOARD"));
        btnCadastro.addActionListener(e -> cardLayout.show(cardPanel, "CADASTRO"));
        btnAgendamento.addActionListener(e -> cardLayout.show(cardPanel, "AGENDAMENTO"));
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(COR_TEXTO_BRANCO);
        btn.setBackground(COR_PRINCIPAL); // Mesma cor do fundo para parecer aba
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // hover do botao
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(COR_PRINCIPAL.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(COR_PRINCIPAL);
            }
        });
        
        return btn;
    }
}

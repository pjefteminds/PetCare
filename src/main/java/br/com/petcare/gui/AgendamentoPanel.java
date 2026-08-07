package br.com.petcare.gui;

import javax.swing.*;
import java.awt.*;

public class AgendamentoPanel extends JPanel {

    public AgendamentoPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(MainFrame.COR_FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initUI();
    }

    private void initUI() {
        JLabel lblTitulo = new JLabel("Novo Agendamento");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(MainFrame.COR_PRINCIPAL);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(criarLabel("Buscar Cliente / Pet *"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        JComboBox<String> cbClientePet = new JComboBox<>(new String[]{"Selecione...", "Maria Silva - Rex", "João Souza - Bolinha"});
        cbClientePet.setPreferredSize(new Dimension(300, 35));
        centerPanel.add(cbClientePet, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(criarLabel("Escolher Serviço *"), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JComboBox<String> cbServico = new JComboBox<>(new String[]{"Selecione...", "Banho e Tosa", "Apenas Banho", "Consulta Veterinária", "Vacina"});
        cbServico.setPreferredSize(new Dimension(300, 35));
        centerPanel.add(cbServico, gbc);

        JPanel panelDataHora = new JPanel(new GridLayout(1, 2, 20, 0));
        panelDataHora.setBackground(Color.WHITE);
        
        JPanel panelData = new JPanel(new BorderLayout());
        panelData.setBackground(Color.WHITE);
        panelData.add(criarLabel("Data *"), BorderLayout.NORTH);
        JTextField txtData = new JTextField("DD/MM/AAAA");
        txtData.setPreferredSize(new Dimension(150, 35));
        panelData.add(txtData, BorderLayout.CENTER);
        
        JPanel panelHora = new JPanel(new BorderLayout());
        panelHora.setBackground(Color.WHITE);
        panelHora.add(criarLabel("Horário *"), BorderLayout.NORTH);
        JComboBox<String> cbHora = new JComboBox<>(new String[]{"08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00"});
        cbHora.setPreferredSize(new Dimension(150, 35));
        panelHora.add(cbHora, BorderLayout.CENTER);

        panelDataHora.add(panelData);
        panelDataHora.add(panelHora);

        gbc.gridx = 0; gbc.gridy = 4;
        centerPanel.add(panelDataHora, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.insets = new Insets(30, 10, 10, 10);
        JButton btnConfirmar = new JButton("CONFIRMAR AGENDAMENTO");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirmar.setBackground(MainFrame.COR_DESTAQUE);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setPreferredSize(new Dimension(300, 50));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnConfirmar.addActionListener(e -> {
            if (cbClientePet.getSelectedIndex() == 0 || cbServico.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Selecione o Cliente e o Serviço.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Agendamento confirmado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            cbClientePet.setSelectedIndex(0);
            cbServico.setSelectedIndex(0);
            txtData.setText("DD/MM/AAAA");
            cbHora.setSelectedIndex(0);
        });

        centerPanel.add(btnConfirmar, gbc);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.setBackground(MainFrame.COR_FUNDO);
        wrapper.add(centerPanel);

        add(wrapper, BorderLayout.CENTER);
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        return label;
    }
}

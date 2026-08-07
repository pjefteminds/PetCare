package br.com.petcare.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(MainFrame.COR_FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initUI();
    }

    private void initUI() {
        JLabel lblTitulo = new JLabel("Início - Resumo do Dia");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(MainFrame.COR_PRINCIPAL);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centroPanel = new JPanel(new BorderLayout(0, 20));
        centroPanel.setBackground(MainFrame.COR_FUNDO);

        JPanel cartoesPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cartoesPanel.setBackground(MainFrame.COR_FUNDO);
        cartoesPanel.add(criarCartao("Agendamentos Hoje", "5", MainFrame.COR_PRINCIPAL));
        cartoesPanel.add(criarCartao("Serviço Mais Pedido", "Banho e Tosa", MainFrame.COR_DESTAQUE));
        cartoesPanel.add(criarCartao("Clientes Ativos", "12", MainFrame.COR_PRINCIPAL));
        
        centroPanel.add(cartoesPanel, BorderLayout.NORTH);

        JPanel tabelaPanel = new JPanel(new BorderLayout());
        tabelaPanel.setBackground(Color.WHITE);
        tabelaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblTabela = new JLabel("Próximos Clientes a Chegar");
        lblTabela.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTabela.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        tabelaPanel.add(lblTabela, BorderLayout.NORTH);

        String[] colunas = {"Horário", "Cliente", "Pet", "Serviço", "Status"};
        Object[][] dados = {
            {"14:00", "Maria Silva", "Rex", "Banho", "Confirmado"},
            {"15:30", "João Souza", "Bolinha", "Tosa", "Pendente"},
            {"16:00", "Ana Costa", "Mia", "Consulta", "Confirmado"}
        };

        JTable table = new JTable(new DefaultTableModel(dados, colunas));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);
        tabelaPanel.add(scrollPane, BorderLayout.CENTER);

        centroPanel.add(tabelaPanel, BorderLayout.CENTER);

        add(centroPanel, BorderLayout.CENTER);
    }

    private JPanel criarCartao(String titulo, String valor, Color corFundo) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(corFundo);
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(Color.WHITE);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 24));

        painel.add(lblTitulo, BorderLayout.NORTH);
        painel.add(lblValor, BorderLayout.CENTER);

        return painel;
    }
}

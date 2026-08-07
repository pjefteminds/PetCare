package br.com.petcare.gui;

import br.com.petcare.dao.ClienteDAO;
import br.com.petcare.dao.PetDAO;
import br.com.petcare.model.Cliente;
import br.com.petcare.model.Pet;

import javax.swing.*;
import java.awt.*;

public class CadastroPanel extends JPanel {

    private JTextField txtNomeCliente, txtTelefone, txtEmail, txtEndereco;
    private JTextField txtNomePet, txtRaca, txtIdade;
    private JComboBox<String> cbEspecie;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private PetDAO petDAO = new PetDAO();

    public CadastroPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(MainFrame.COR_FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initUI();
    }

    private void initUI() {
        // Título
        JLabel lblTitulo = new JLabel("Cadastro Rápido (Cliente e Pet)");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(MainFrame.COR_PRINCIPAL);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        formPanel.setBackground(MainFrame.COR_FUNDO);

        JPanel panelDono = new JPanel();
        panelDono.setLayout(new BoxLayout(panelDono, BoxLayout.Y_AXIS));
        panelDono.setBackground(Color.WHITE);
        panelDono.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblDono = new JLabel("Dados do Cliente (Dono)");
        lblDono.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblDono.setForeground(MainFrame.COR_PRINCIPAL);
        panelDono.add(lblDono);
        panelDono.add(Box.createRigidArea(new Dimension(0, 20)));

        txtNomeCliente = addCampo(panelDono, "Nome Completo *");
        txtTelefone = addCampo(panelDono, "Telefone *");
        txtEmail = addCampo(panelDono, "E-mail");
        txtEndereco = addCampo(panelDono, "Endereço Completo");

        JPanel panelPet = new JPanel();
        panelPet.setLayout(new BoxLayout(panelPet, BoxLayout.Y_AXIS));
        panelPet.setBackground(Color.WHITE);
        panelPet.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblPet = new JLabel("Dados do Pet");
        lblPet.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPet.setForeground(MainFrame.COR_PRINCIPAL);
        panelPet.add(lblPet);
        panelPet.add(Box.createRigidArea(new Dimension(0, 20)));

        txtNomePet = addCampo(panelPet, "Nome do Pet *");
        
        panelPet.add(criarLabel("Espécie *"));
        cbEspecie = new JComboBox<>(new String[]{"Cachorro", "Gato", "Pássaro", "Roedor", "Outro"});
        cbEspecie.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelPet.add(cbEspecie);
        panelPet.add(Box.createRigidArea(new Dimension(0, 15)));

        txtRaca = addCampo(panelPet, "Raça");
        txtIdade = addCampo(panelPet, "Idade (anos)");

        formPanel.add(panelDono);
        formPanel.add(panelPet);

        add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(MainFrame.COR_FUNDO);
        
        JButton btnSalvar = new JButton("SALVAR CADASTRO");
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalvar.setBackground(MainFrame.COR_DESTAQUE);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(250, 50));
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(e -> salvarCadastro());
        
        footerPanel.add(btnSalvar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private JTextField addCampo(JPanel panel, String labelTexto) {
        panel.add(criarLabel(labelTexto));
        JTextField txtField = new JTextField();
        txtField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(txtField);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        return txtField;
    }

    private void salvarCadastro() {
        String nomeCliente = txtNomeCliente.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String nomePet = txtNomePet.getText().trim();

        if (nomeCliente.isEmpty() || telefone.isEmpty() || nomePet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios (*).", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Cliente cliente = new Cliente();
            cliente.setNome(nomeCliente);
            cliente.setTelefone(telefone);
            cliente.setEmail(txtEmail.getText().trim());
            cliente.setEndereco(txtEndereco.getText().trim());
            clienteDAO.salvar(cliente);

            Pet pet = new Pet();
            pet.setNome(nomePet);
            pet.setEspecie(cbEspecie.getSelectedItem().toString());
            pet.setRaca(txtRaca.getText().trim());
            
            String strIdade = txtIdade.getText().trim();
            pet.setIdade(strIdade.isEmpty() ? 0 : Integer.parseInt(strIdade));
            pet.setCliente(cliente);
            
            petDAO.salvar(pet);

            JOptionPane.showMessageDialog(this, "Cadastro de Cliente e Pet salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "A idade do Pet deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNomeCliente.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtNomePet.setText("");
        cbEspecie.setSelectedIndex(0);
        txtRaca.setText("");
        txtIdade.setText("");
    }
}

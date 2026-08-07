CREATE DATABASE IF NOT EXISTS petcare;
USE petcare;

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS Pet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    raca VARCHAR(50),
    idade INT,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2),
    duracaoMinutos INT
);

CREATE TABLE IF NOT EXISTS Funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50),
    login VARCHAR(50),
    senha VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    status VARCHAR(50) DEFAULT 'AGENDADO',
    pet_id INT,
    servico_id INT,
    funcionario_id INT,
    FOREIGN KEY (pet_id) REFERENCES Pet(id) ON DELETE CASCADE,
    FOREIGN KEY (servico_id) REFERENCES Servico(id) ON DELETE SET NULL,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE SET NULL
);

-- Dados iniciais para teste
INSERT INTO Cliente (nome, telefone, email, endereco) VALUES 
('João Silva', '11999999999', 'joao@email.com', 'Rua A, 123'),
('Maria Oliveira', '11888888888', 'maria@email.com', 'Av B, 456');

INSERT INTO Pet (nome, especie, raca, idade, cliente_id) VALUES
('Rex', 'Cachorro', 'Labrador', 3, 1),
('Mimi', 'Gato', 'Siamês', 2, 2);

INSERT INTO Servico (descricao, preco, duracaoMinutos) VALUES
('Banho e Tosa', 80.00, 60),
('Consulta Veterinária', 150.00, 30);

INSERT INTO Funcionario (nome, cargo, login, senha) VALUES
('Carlos Souza', 'Veterinário', 'carlos', '1234'),
('Ana Paula', 'Recepcionista', 'ana', '1234');

INSERT INTO Agendamento (data, hora, status, pet_id, servico_id, funcionario_id) VALUES
('2023-11-10', '10:00:00', 'AGENDADO', 1, 1, 2),
('2023-11-11', '14:30:00', 'AGENDADO', 2, 2, 1);

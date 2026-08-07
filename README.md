# PetCare - Sistema de Gerenciamento para Petshop

## Status do Projeto
**Status:** Finalizado

---

## Tecnologias Aplicadas
As tecnologias utilizadas no desenvolvimento do projeto seguem o escopo de conteúdos abordados ao longo do curso:

- **Java 17 (JDK 17)** - Linguagem de programação orientada a objetos utilizada no desenvolvimento das regras de negócio.
- **Java Swing** - Framework para construção da Interface Gráfica de Usuário (GUI) desktop.
- **JDBC (Java Database Connectivity)** - API Java para comunicação e execução de comandos SQL no banco de dados.
- **MySQL** - Sistema de Gerenciamento de Banco de Dados Relacional (SGBD) para armazenamento persistente de dados.
- **Apache Maven** - Ferramenta para gerenciamento de dependências, estrutura do projeto e automação de build.
- **Git & GitHub** - Ferramentas de controle de versão de código e hospedagem de repositório remoto.

---

## Time de Desenvolvedores
- **Pedro Jefte** - / Aluno SENAC

---

## Objetivo do Software
O **PetCare** é um sistema de gerenciamento desenvolvido para atender às necessidades operacionais de petshops e clínicas veterinárias. O software tem como objetivo centralizar o cadastro de clientes, pets, serviços e funcionários, além de automatizar a rotina de agendamentos de atendimentos. 

Com uma interface desktop amigável e integração a banco de dados relacional, a aplicação visa reduzir erros de agendamento, otimizar a organização de horários e melhorar o atendimento ao cliente.

---

## Funcionalidades do Sistema (Requisitos)

### Requisitos Funcionais (RF)
- **RF01 - Gestão de Clientes:** Permitir o cadastro, consulta, atualização e exclusão de dados dos tutores dos pets (nome, telefone, e-mail e endereço).
- **RF02 - Gestão de Pets:** Permitir o cadastramento dos animais associando-os diretamente ao seu respectivo tutor, incluindo informações como nome, espécie, raça e idade.
- **RF03 - Catálogo de Serviços:** Permitir o gerenciamento dos serviços oferecidos pelo estabelecimento (descrição, valor em reais e tempo estimado de duração em minutos).
- **RF04 - Gestão de Funcionários:** Manter o registro da equipe de trabalho (nome, cargo, login e senha de acesso ao sistema).
- **RF05 - Agendamento de Atendimentos:** Realizar a marcação de horários vinculando um Pet, um Serviço e um Funcionário a uma data e hora específicas, com controle de status (`AGENDADO`, `EM_ANDAMENTO`, `CONCLUIDO`, `CANCELADO`).
- **RF06 - Painel Dashboard:** Exibir resumos quantitativos dos cadastros e listar os próximos agendamentos cadastrados no sistema.

### Requisitos Não Funcionais (RNF)
- **RNF01 - Interface Gráfica:** A interface deve ser desenvolvida utilizando Java Swing com o Look and Feel Nimbus para garantir boa usabilidade e padrão visual moderno.
- **RNF02 - Arquitetura de Software:** O projeto deve seguir o padrão de arquitetura MVC/DAO (Data Access Object), mantendo a separação entre interface gráfica, modelos e acesso a dados.
- **RNF03 - Persistência Relacional:** Os dados devem ser armazenados em banco MySQL com garantia de integridade referencial por meio de chaves estrangeiras.
- **RNF04 - Portabilidade e Build:** O projeto deve ser gerenciável via Apache Maven (`pom.xml`), permitindo fácil compilação e execução em diferentes ambientes Java 17.

---

## Como Executar o Projeto

1. Certifique-se de ter o **JDK 17** e o **Apache Maven** instalados.
2. Execute o script `database.sql` em seu servidor MySQL local para criar o banco de dados `petcare` e as tabelas.
3. Verifique as credenciais de conexão no arquivo `src/main/java/br/com/petcare/util/ConexaoBD.java`.
4. Compile e execute a aplicação via Maven:
   ```bash
   mvn clean compile exec:java
   ```

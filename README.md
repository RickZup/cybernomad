# 🌐 CyberNomad - Encontre recursos gratuitos perto de você

## 🤔 Você já?

Bem-vindo ao CyberNomad! Quem já esteve em um lugar desconhecido e precisou de recursos como acesso à internet, um banheiro limpo ou até mesmo um lugar para carregar seu celular? Você não está sozinho! O CyberNomad é uma aplicação dedicada a ajudar pessoas, especialmente adolescentes de baixa renda, a encontrar recursos gratuitos em sua área.

### 🚀 O que o CyberNomad faz?

O CyberNomad não oferece diretamente esses recursos, mas funciona como um mapa interativo que mostra onde você pode encontrá-los. Se você está em busca de internet, um computador, banheiro, tomadas para carregar seus dispositivos móveis ou até mesmo água potável, o CyberNomad pode mostrar os locais próximos que oferecem esses serviços.

### 💡 Como Funciona?

- **Visualize os Hubs:** Os locais são chamados de "Hubs" e são cadastrados na aplicação. Você pode visualizar os Hubs já cadastrados, incluindo detalhes como endereço, pontos de referência e horário de funcionamento.

- **Encontre o que Precisa:** Utilize a busca para encontrar o recurso que você está procurando em sua área. Você verá uma lista de Hubs que oferecem o recurso desejado.

- **Veja na Prática:** Acesse as informações detalhadas de cada Hub para obter orientações sobre como chegar lá e aproveitar o recurso gratuito.

### 🔮 O Futuro do CyberNomad

Em busca de melhorias! No futuro, será possível medir a distância até os Hubs usando o GPS do dispositivo ou buscar locais por proximidade. Além disso, a pretenção é que os próprios usuários cadastrem Hubs, tornando o CyberNomad uma comunidade colaborativa onde todos podem contribuir.

---

# 📖 CyberNomad - Documentação Técnica

Bem-vindo à documentação técnica do CyberNomad, uma API que permite mapear recursos gratuitos em locais próximos.

## 🚀 Introdução

O CyberNomad é uma API desenvolvida com o Spring Boot que tem como objetivo mapear pontos de recursos gratuitos em várias localidades. Esta documentação fornecerá uma visão geral dos endpoints disponíveis e das tecnologias relevantes usadas no projeto.

**Nota:** Atualmente, o CyberNomad é uma API e não possui um frontend completo.

## 📋 Endpoints da API

### 📍 Hubs

A API CyberNomad fornece os seguintes endpoints para gerenciar Hubs:

- `POST /hubs`: Cadastra um novo Hub.
- `GET /hubs`: Retorna todos os Hubs cadastrados.
- `GET /hubs/{id}`: Retorna os detalhes de um Hub específico com base no ID.
- `PUT /hubs/{id}`: Atualiza os detalhes de um Hub existente.
- `DELETE /hubs/{id}`: Exclui um Hub com base no ID.

### 🏠 Endereços

Endpoints para gerenciar Endereços:

- `POST /adress`: Cadastra um novo Endereço.
- `GET /adress`: Retorna todos os Endereços cadastrados.
- `GET /adress/{id}`: Retorna os detalhes de um Endereço específico com base no ID.
- `PUT /adress/{id}`: Atualiza os detalhes de um Endereço existente.
- `DELETE /adress/{id}`: Exclui um Endereço com base no ID.

## 💡 Tecnologias Utilizadas

As principais tecnologias e informações do arquivo `pom.xml` incluem:

- **Java:** Versão 11.
- **Spring Boot:** Versão 2.7.15.
- **Banco de Dados:** PostgreSQL (runtime) e H2 Database (test).
- **Testes Unitários:** JUnit e Mockito.
- **Maven:** Sistema de gerenciamento de dependências.

O arquivo `pom.xml` do projeto contém as dependências específicas e configurações do Maven para as bibliotecas usadas.

**Documentação Swagger**: A aplicação foi documentada com Swagger para facilitar a compreensão e teste das APIs. Você pode acessar a documentação em: http://localhost:8080/swagger-ui.html.


## 🤝 Contribuição

Estamos abertos a sugestões e colaborações da comunidade! Se deseja colaborar, sinta-se à vontade para enviar pull requests e relatar problemas.

---

© 2023 CyberNomad. Desenvolvido por Ricardo dos Santos. Contate-me em contato.ricardo.santos.95@gmail.com.

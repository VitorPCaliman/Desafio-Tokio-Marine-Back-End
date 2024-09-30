# Transferências Backend

Este é o backend da aplicação de agendamento de transferências, responsável por calcular as taxas baseadas nas datas de agendamento e realizar operações de CRUD (Criar, Ler, Atualizar e Excluir) em transferências. As persistências são realizadas em memória utilizando o banco de dados H2.

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![H2](https://img.shields.io/badge/H2-003B57?style=for-the-badge&logo=h2&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## 🚀 Instruções para Rodar a Aplicação

1. Clone o repositório:
    ```bash
    git clone https://github.com/VitorPCaliman/Desafio-Tokio-Marine-Back-End.git
    cd tranferencia
    ```

2. A aplicação usa um banco de dados em memória (H2), portanto, não há necessidade de configurar um banco de dados externo.

3. Configure as propriedades do H2 no arquivo `application.properties` (opcional, caso já esteja configurado):
    ```properties
    spring.datasource.url=jdbc:h2:mem:transaction_db
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.h2.console.enabled=true
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Compile e execute a aplicação:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. Acesse a API em: `http://localhost:8080/transferencias`.

6. É possível testar os Endpoints pelo PostMan `http://localhost:8080/transferencias`.

7. Acesse o console do banco H2 em: `http://localhost:8080/h2-console` (username: `sa`, password: `password`).

## 📐 Decisões de Arquitetura

- **Persistência em Memória (H2)**: Para simplificar o desenvolvimento e os testes, foi utilizado o banco H2 em memória, eliminando a necessidade de configurar um banco de dados externo.
- **Camada de Serviço**: As regras de negócios, como o cálculo de taxas baseado na data de agendamento, foram implementadas na camada de serviço, permitindo uma separação clara entre a lógica de negócios e a camada de controle.
- **Camada de Model**: Criada a camada das entidades para mapear os objetos salvos e consultados.
- **API REST**: A aplicação foi desenvolvida como uma API RESTful utilizando o Spring Boot, garantindo uma comunicação simples e eficiente com o frontend.

## 📊 Modelos Utilizados

### Transferência

| Campo              | Tipo        | Descrição                                        |
|--------------------|-------------|--------------------------------------------------|
| `id`               | `Long`      | Identificador único da transferência             |
| `contaOrigem`       | `String`    | Conta de onde sairá a transferência              |
| `contaDestino`      | `String`    | Conta de destino da transferência                |
| `valorTransferencia`| `BigDecimal`| Valor da transferência                           |
| `taxa`              | `Double`    | Taxa calculada com base na data de transferência |
| `dataAgendamento`   | `LocalDate` | Data do agendamento                              |
| `dataTransferencia` | `LocalDate` | Data da transferência                            |

## 💡 Melhorias e Próximos Passos

1. **Autenticação e Autorização**: Implementar autenticação com Spring Security e JWT para proteger a API.
2. **Persistência em Banco Externo**: Migrar a persistência para um banco de dados relacional (PostgreSQL, MySQL) em produção.
3. **Filtros Avançados**: Melhorar os endpoints de listagem de transferências com filtros por data, valor, e estado.
4. **Validações Customizadas**: Adicionar validações mais detalhadas para os campos, como formato da conta.
5. **Cacheamento**: Implementar cache para transferências agendadas, melhorando a performance da API em operações de leitura.

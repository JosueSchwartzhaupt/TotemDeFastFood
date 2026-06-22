# Totem de Autoatendimento para Lanchonetes

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos.  
O sistema simula um totem de autoatendimento utilizado em lanchonetes e restaurantes, permitindo que clientes realizem pedidos e que o estabelecimento acompanhe o preparo em tempo real.

---

## Funcionalidades

### Área do Cliente
- Visualização do cardápio por categorias
- Adição e remoção de itens no pedido
- Seleção de tamanhos (bebidas) e sabores (sobremesas)
- Visualização do valor total do pedido
- Confirmação do pedido

### Área do Estabelecimento
- Listagem de pedidos realizados
- Acompanhamento do status dos pedidos
- Atualização de status:
  - A Preparar
  - Preparando
  - Pronto
- Controle de navegação entre pedidos
- Reinício do sistema para um novo dia

---

## Tecnologias Utilizadas

- Java 21
- JavaFX
- Maven

---

## Requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java JDK 21 ou superior
- Maven 3.9 ou superior

---

## Estrutura do Projeto
```
src
└── main
    ├── java
    │   └── com.josuesch
    │       ├── controller
    │       ├── model
    │       └── App.java
    └── resources
        └── fxml
```
---

## Como Executar

### 1. Clonar o projeto
```
git clone <url-do-repositorio>
cd <nome-do-projeto>
```
### 2. Compilar o projeto
```
mvn clean install
```
### 3. Executar o sistema
```
mvn javafx:run
```
---

## Casos de Teste

### Teste 1 – Realização de Pedido
1. Adicionar itens ao pedido.
2. Verificar atualização do valor total.
3. Confirmar o pedido.

Resultado esperado:
- Pedido gerado com número automático.
- Pedido aparece na área do estabelecimento com status "A Preparar".

---

### Teste 2 – Atualização de Status
1. Selecionar um pedido.
2. Iniciar preparo.
3. Concluir pedido.

Resultado esperado:
- Status muda para "Preparando".
- Depois muda para "Pronto".

---

### Teste 3 – Alteração de Itens
1. Adicionar bebida ou sobremesa.
2. Alterar tamanho ou sabor.

Resultado esperado:
- Item atualizado corretamente no pedido.
- Valor total recalculado.

---

### Teste 4 – Reinício do Sistema
1. Finalizar todos os pedidos.
2. Iniciar novo dia.

Resultado esperado:
- Todos os pedidos são removidos.
- Numeração reinicia em 1.

---

## Observações

- Sistema em memória (sem banco de dados)
- Interface JavaFX com duas áreas (cliente e estabelecimento)

---

## Autor

- Josué Henrique Becker Schwartzhaupt

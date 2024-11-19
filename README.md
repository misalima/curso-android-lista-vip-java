# Lista de Cursos Desejados - Aplicativo Android

Aplicativo Android para gerenciar uma lista de pessoas e seus interesses em cursos.

## Funcionalidades
- **Adicionar Pessoa e Interesse**: Insira nome, sobrenome, telefone e curso desejado.
- **Visualizar Pessoas**: Veja todos os registros de interesse.
- **Gerenciar Cursos**: Adicione e remova cursos disponíveis.

## Arquitetura
O app segue o padrão **MVC**:
- **Model**: Lida com a estrutura de dados e o banco de dados SQLite.
- **View**: Gerencia a interface do usuário.
- **Controller**: Faz a ligação entre Model e View.

## Banco de Dados
Banco de dados local SQLite com duas tabelas:
- **Person**: `id`, `firstName`, `lastName`, `phone`, `course`
- **Course**: `id`, `name`

## Como Usar
1. **Navegue** pelo menu lateral.
2. **Adicione uma pessoa** com os dados de interesse no curso na página Home.
3. **Visualize** a lista de pessoas interessadas e os cursos disponíveis na página Lista.
4. **Gerencie os cursos** na tela de gerenciamento de cursos.

## Tecnologias/Padrões Utilizados
- **Java** para Android
- **Gradle** para gerenciamento de dependências
- **SQLite** para persistência de dados
- **MVC** para organização do projeto

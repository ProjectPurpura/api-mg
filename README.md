# **API para Acesso ao MongoDB do Purpura 💜**
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)

## **Overview 📝**

Bem-vindo à API do MongoDB do Purpura! Esta API foi projetada para gerenciar e organizar os dados essenciais do Purpura, abrangendo os seguintes domínios:

- **Empresas**: Gerencie informações de empresas cadastradas.
- **Chaves Pix**: Controle e organize chaves Pix associadas.
- **Endereços**: Administre endereços de forma eficiente.
- **Resíduos**: Gerencie dados relacionados a resíduos.
- **Conversas**: 
  - Inclui suporte a **chat em tempo real** utilizando WebSockets com Redis.
  - Permite comunicação instantânea entre usuários utilizando conteúdo em markdown.
-----

## **Funcionalidades Principais 🚀**

### **Empresas**
- Adicionar, atualizar, buscar e deletar empresas.
- Pesquisar empresas com filtros avançados.

### **Chaves Pix**
- Adicionar, atualizar, buscar e deletar chaves Pix.
- Gerenciamento de chaves Pix com validações específicas.

### **Endereços**
- Adicionar, atualizar, buscar e deletar endereços.
- Listagem completa de endereços cadastrados.

### **Resíduos**
- Adicionar, atualizar, buscar e deletar resíduos.
- Controle detalhado de resíduos gerados.

### **Conversas e Chat em Tempo Real**
- Suporte a WebSockets para comunicação em tempo real.
- Gerenciamento de mensagens e chats.

-----

## **Testes e Ferramentas 🧪**

### **Coleção de Testes do Bruno**
- Utilize a coleção de testes localizada em: `bruno-api-mg` para validar as funcionalidades da API.

### **Swagger**
- Explore e teste os endpoints da API diretamente no Swagger:
  [https://mongodb-api-purpura.onrender.com/swagger-ui/index.html#](https://mongodb-api-purpura.onrender.com/swagger-ui/index.html#)

### **WebSockets**
- Teste a funcionalidade de WebSockets utilizando o projeto Python disponível em:
  [websocket-test-api-mg](websocket-test-api-mg/README.md)

-----

## **Como Executar a API 🛠️**

1. Certifique-se de ter o Docker e o IntelliJ instalado.
2. Clone este repositório.
3. Utilize o plugin DotEnv para ter acesso às variáveis de ambiente.
4. Configure as variáveis de ambiente conforme o arquivo `.env`
5. Mude as configurações de execução do projeto `ApiMgApplication` para utilizar o perfil `dev`.
6. Dentro ainda das configurações, ative o plugin Dotenv.
7. Utilize o arquivo `compose.yaml` para configurar e iniciar os serviços necessários.
8. Execute o projeto.
9. Acesse o Swagger para explorar os endpoints disponíveis.

-----

## **Contribuidores 💃**

Criado com 💜 por:

- **Felipe Fernandes dos Santos Oliveira**: Back-end, testes e documentação.
- **Emílio Stuart Palumbo**: Esteira de deploy e infraestrutura.

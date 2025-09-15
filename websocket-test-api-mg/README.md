# **Chat em Tempo Real (WebSocket) 💬**

Este documento detalha a funcionalidade de chat em tempo real da API, que utiliza WebSockets para comunicação instantânea.

-----

### **Endpoints 🌠**

O sistema de chat usa STOMP sobre WebSockets.

* **URL do WebSocket:** `ws://localhost:8081/ws-chat`
* **Enviar uma mensagem:** `/app/chat` (STOMP)
* **Inscrever-se para mensagens:** `/topic/chat.<chatId>`

-----

### **Requisitos 📜**
* Python 3.8+

### **Como instalar 📤**

1. **Clonar o repositório**
```
https://github.com/PurPuraAmbiental/api-mg.git
```
2. **Rodar o projeto do IntelliJ IDEA na IDE**
3. **Entrar no projeto de testes e ativar o venv**
```
cd websocket-test-api-mg
.\venv\Scripts\activate
```

4. Pronto! Agora é seguir os passos abaixo para testar a funcionalidade.

### **Exemplo de uso 🚀**

#### **Enviando uma Mensagem**

Para enviar uma mensagem, faça uma requisição POST para o endpoint `/app/chat` com a seguinte estrutura JSON:

```json
{
  "chatId": "sala1",
  "senderId": "user123",
  "content": "Olá, mundo!"
}
```

#### **Recebendo Mensagens**

Para receber mensagens de um chat específico, inscreva-se no tópico `/topic/chat.<chatId>`.

Por exemplo, para receber mensagens da sala1, você se inscreveria em:

`/topic/chat.sala1`

-----

### **Cliente WebSocket em Python 🛠️**

Para facilitar os testes do chat em tempo real, uma ferramenta de linha de comando está disponível.

* **Repositório:** `websocket-test-api-mg`
* **Leia o README:** `websocket-test-api-mg/README.md`

-----

### **Como Usar o Cliente de Teste**

1.  Envie uma requisição POST para a API para criar uma sala de chat.
2.  Abra dois terminais e execute o cliente de teste com o mesmo `chatId` para ambos os participantes.
3.  **Sintaxe do Cliente:**
    ```bash
    python main.py <chatId> <senderId>
    ```
4.  **Exemplo:**
    ```bash
    python main.py 256a4c5a-258f-46e4-a41f-7dae4cbfadf4 user123
    ```

Ao abrir dois terminais com o mesmo `chatId`, você pode enviar mensagens e testar a funcionalidade de chat em tempo real.
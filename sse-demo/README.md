# SSE Demo

Exemplo simples de **Server-Sent Events (SSE)** usando Spring Boot e
`SseEmitter`.

## O que é SSE?

SSE permite que o servidor mantenha uma conexão HTTP aberta e envie eventos
para o cliente em tempo real. A comunicação é unidirecional:

```text
servidor -> cliente
```

É útil para notificações, progresso de tarefas, logs ao vivo e atualização de
status sem que o cliente precise ficar consultando o servidor repetidamente.

## Como funciona neste projeto

O endpoint `GET /stream-sse`:

- abre uma conexão SSE;
- envia dez eventos;
- envia um evento a cada segundo;
- identifica cada evento com um ID de `1` a `10`;
- usa o nome de evento `message`;
- encerra a conexão após o décimo evento.

As mensagens enviadas têm este formato:

```text
Mensagem via SSE #1
Mensagem via SSE #2
...
Mensagem via SSE #10
```

## Requisitos

- Java 17 ou superior;
- Maven (ou o Maven Wrapper incluído no projeto).

## Executando a aplicação

No diretório do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```bat
mvnw.cmd spring-boot:run
```

Por padrão, a aplicação inicia em:

```text
http://localhost:8080
```

## Testando com cURL

Use `-N` (`--no-buffer`) para que o cURL mostre cada evento assim que ele
chegar:

```bash
curl -N http://localhost:8080/stream-sse
```

## Testando no navegador

Abra o console do navegador e execute:

```javascript
const source = new EventSource("http://localhost:8080/stream-sse");

source.addEventListener("message", (event) => {
  console.log({
    id: event.lastEventId,
    mensagem: event.data,
  });
});

source.addEventListener("error", (error) => {
  console.error("Erro na conexão SSE:", error);
});
```

Como o servidor encerra a conexão depois de dez mensagens, o navegador pode
tentar reconectá-la automaticamente. Em uma aplicação real, trate esse
comportamento conforme a necessidade:

```javascript
source.close();
```

## Estrutura principal

- `src/main/java/com/example/sse/SseDemoApplication.java`: inicializa a
  aplicação Spring Boot.
- `src/main/java/com/example/sse/SseController.java`: disponibiliza o endpoint
  SSE e envia os eventos em uma tarefa assíncrona.
- `src/main/resources/application.properties`: configura o nome da aplicação.

## SSE não é a melhor opção para arquivos binários

Este exemplo envia texto usando o formato `text/event-stream`. Para PDFs,
imagens, vídeos ou arquivos ZIP, prefira uma resposta HTTP de download ou
streaming, usando `Resource`, `InputStreamResource` ou
`StreamingResponseBody`.

Converter um arquivo para Base64 e enviá-lo pelo SSE é possível, mas aumenta o
tamanho dos dados e não é eficiente. Uma arquitetura comum é:

```text
GET /arquivo         -> envia o PDF ou outro arquivo
GET /arquivo/status  -> envia o progresso via SSE
```

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Maven

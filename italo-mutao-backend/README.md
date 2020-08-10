# italo-mutao-backend

Etapas para Backend na raiz do projeto execute os seguintes comandos:
1 - Rode: mvn clean install
2 - Rode: docker-compose up --build --force-recreate

Explicações:
1 - Builda o projeto criando o arquivo jar o qual será utilizado na próxima etapa.
2 - Cria e sobe um container local com a aplicação, e a executa.

Obs: Para melhor visualização da api deixei o Swagger habilitado e exposto (cors/autenticação) em http://localhost:8080/swagger-ui.html

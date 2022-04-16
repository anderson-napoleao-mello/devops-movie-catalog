# Introduction
## Projeto de Conclusão de disciplina.

Esse projeto implementa dois serviços simples em spring boot para demostrar:

1. Um serviço de catálogo de filmes que permite cadastrar e consultar filmes.
2. Um serviço de ticket que consulta os filmes que são lançamento(lançados no mês corrente).
3. Um Banco de Dados Postgres SQL para armazenar os dados.
4. Um serviço Redis para armazenar os lançamentos do mês.
5. Um conjunto de aplicaçoes ELK (Elasticsearch, Logstash, Kibana) para agregar e visualizar logs
6. Um servidor ZIPKIN para visualização de logs
7. Um Serviço PROMETHEUS para armazenar dados da execução
8. Um Serviço GRAFANA para visualização de métricas armazenadas no Prometheus


Esses são os passos para executar a aplicação:

## Configuração
1. [Apache Maven](http://maven.apache.org)  O código é compilado com Java 11. 
2. [Git](http://git-scm.com)
3. [Docker](https://www.docker.com/products/docker-desktop)


## Como Executar

Para clonar e executar a aplicação, você vai precisar [Git](https://git-scm.com), [Maven](https://maven.apache.org/), [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
<p>Do Terminal:</p>

```bash
# Clonar o repositorio
$ git clone https://github.com/anderson-napoleao-mello/devops-movie-catalog.git

# navegar para o diretório onde temos o codigo fonte
$ cd movie-catalog

# Para executar os serviços como imagens docker 
# executar o comando:
$ mvn clean package dockerfile:build

# Agora execute o docker-compose para iniciar a imagem docker: 
$ docker-compose -f docker/docker-compose.yml up
```

# O Comando Build

Nós executamos o plugin [Spotify dockerfile](https://github.com/spotify/dockerfile-maven) definido no pom.xml.

A execução do comando acima na raiz do diretório do projeto criará todos os projetos. Se tudo for compilado com sucesso, você deverá ver uma mensagem indicando que a compilação foi bem-sucedida.

# O Comando Up

<p>Este comando executará nossos serviços usando o arquivo docker-compose.yml localizado no diretório /docker.</p>

<p>Se tudo iniciar sem erros, você deverá ver um monte de informações do Spring Boot na saída padrão. Neste ponto, todos os serviços necessários estarão em execução.</p>

# Database

No diretório docker estão os scripts de banco de dados.

## Contact

anderson.mello@al.infnet.edu.br

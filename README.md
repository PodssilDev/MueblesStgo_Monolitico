# Técnicas de Ingeniería de Software PEP 1 2022-2: Aplicación Monolítica 

* Autor: John Serrano
* Sección: 13319-0-A-1
* Profesor: Alcides Quispe

## Descripción
En este repositorio se encuentra la aplicación monolítica desarrollada para la PEP 1 de Técnicas de Ingeniería de Software en 2022. La aplicación esta desarrollada principalmente en Java, utilizando [SpringBoot](https://start.spring.io) y HTML5. 

## Herramientas utilizadas

Se utilizan las siguientes herramientas principales para desarrollar el proyecto:

* [Java 18](https://www.oracle.com/java/technologies/downloads/): La aplicación utiliza la Programación Orientada a Objetos y se desarrolla utilizando capas, compuestas por Servicios, Entidades, Controladores y Repositorios
* [IntelliJ IDEA Ultimate 2022.2.2](https://www.jetbrains.com/idea/download/#section=windows): IDE perfecto para trabajar con Java y SpringBoot. Tiene bastante buena compatibilidad con varios plugins y es perfecto para desarollar un proyecto monolítico.
* [HTML 5](https://www.manualweb.net/html5/): Se utiliza HTML5 para el desarrollo de las "vistas" del proyecto, junto con otros plugins de SpringBoot.
* [Visual Studio Code](https://code.visualstudio.com): IDE con multiples compatibilidades que sirve como alternativa a IntelliJ y para editar archivos no provenientes de Java.
* [Docker / Docker-Compose / Docker Desktop](https://www.docker.com): Se utiliza Docker junto con Docker-Compose para crear contenedores de Imágenes y asi poder levantar la aplicación en distintos PCs localmente. Las imágenes de Docker se descargan desde [Docker Hub](https://hub.docker.com).
* [Jenkins](https://www.jenkins.io): Se utiliza para automatizar todo el proceso de el ensamblado de la aplicación junto con la creación de imágenes de Docker y la subida de estas a Docker Hub.
* [SonarQube](https://www.sonarqube.org): Se utiliza para testear código y obtener los Code Smells del proyecto (Buenas prácticas)
* [Terraform](https://learn.hashicorp.com/tutorials/terraform/install-cli?in=terraform/aws-get-started): Se utiliza para poder levantar la aplicación en un servidor web, como lo es [Digtal Ocean](https://www.digitalocean.com)

## Comandos importantes

Los siguientes son comandos importantes a tener en consideración.

### Maven

* Maven Install:
```sh
mvn clean install
```
* Maven Install (Sin tests):
```sh
mvn clean install -DskipTests
```
* Maven Run
```sh
mvn spring-boot:run
```
### Docker
La cuenta de DockerHub utilizada es johnserrano159 y el repositorio se llama mueblesstgopep1.

* Ver contenedores:
```sh
docker ps
```
* Ver Imágenes:
```sh
docker image ls
```
* Docker Build (Crear Imágen)
```sh
docker build -t johnserrano159/mueblesstgopep1 .
```
* Eliminar Imágen
```sh
docker rmi <nombre imagen>
```
* Eliminar Contenedor
```sh
docker rm -f <nombre contenedor>
```
* Subir Imágen a DockerHub
```sh
docker push johnserrano159/mueblesstgopep1
```
* Levantar Contenedor
```sh
docker-compose up
```
* Bajar Contenedor
```sh
docker-compose down
```

### Terraform
* Levantar Máquina Virtual de Digital Ocean:
```sh
ssh root@45.55.40.59
```
En la consola de la MV, se debe aplicar docker-compose. La aplicación queda disponible en 45.55.40.59:8090/main

### Jenkins
En la carpeta donde se encuentra el archivo "jenkins.war":
* Levantar Jenkins (localhost:8080/):
```sh
java -jar jenkins.war
```

### SonarQube
Para analizar manualmente con SonarQube, en la carpeta del proyecto:
```sh
mvn clean verify sonar:sonar -Dsonar.projectKey=TingesoPEP1 -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_51eadf747122b06a85b8adcf823a4c2a87311940
```

## Ejecución paso a paso:
1. Ejecutar Jenkins. Esto permitirá que se prueben todos los Tests Unitarios creados y se suba la imágen del proyecto a DockerHub.
2. Revisar reporte de SonarQube. Esto permite comprobar los Codes Smells junto con el porcentaje de cobertura de los Tests.
3. Levantar la Máquina Virtual de Digital Ocean.
4. Levantar la aplicación mediante Docker-Compose.
5. Acceder a la dirección web de la aplicación y probar cosas.

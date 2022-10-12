# Técnicas de Ingeniería de Software PEP 1 2022-2: Aplicación Monolítica 

* Autor: John Serrano
* Sección: 13319-0-A-1
* Profesor: Alcides Quispe
* Nota después de la evaluación: 7.0/7.0

## Descripción
En este repositorio se encuentra la aplicación monolítica desarrollada para la PEP 1 de Técnicas de Ingeniería de Software en 2022. La aplicación esta desarrollada principalmente en Java, utilizando [SpringBoot](https://start.spring.io) y HTML5. 

## Herramientas utilizadas

Se utilizaron las siguientes herramientas principales para desarrollar el proyecto:

* [Java 18](https://www.oracle.com/java/technologies/downloads/): La aplicación utiliza la Programación Orientada a Objetos y se desarrolla utilizando capas, compuestas por Servicios, Entidades, Controladores y Repositorios
* [IntelliJ IDEA Ultimate 2022.2.2](https://www.jetbrains.com/idea/download/#section=windows): IDE perfecto para trabajar con Java y SpringBoot. Tiene bastante buena compatibilidad con varios plugins y es perfecto para desarollar un proyecto monolítico.
* [HTML 5](https://www.manualweb.net/html5/): Se utiliza HTML5 para el desarrollo de las "vistas" del proyecto, junto con otros plugins de SpringBoot.
* [Visual Studio Code](https://code.visualstudio.com): IDE con multiples compatibilidades que sirve como alternativa a IntelliJ y para editar archivos no provenientes de Java.
* [Docker / Docker-Compose / Docker Desktop](https://www.docker.com): Se utiliza Docker junto con Docker-Compose para crear contenedores de Imágenes y asi poder levantar la aplicación en distintos PCs localmente. Las imágenes de Docker se descargan desde [Docker Hub](https://hub.docker.com).
* [Jenkins](https://www.jenkins.io): Se utiliza para automatizar todo el proceso de el ensamblado de la aplicación junto con la creación de imágenes de Docker y la subida de estas a Docker Hub.
* [SonarQube](https://www.sonarqube.org): Se utiliza para testear código y obtener los Code Smells del proyecto (Buenas prácticas)
* [Terraform](https://learn.hashicorp.com/tutorials/terraform/install-cli?in=terraform/aws-get-started): Se utiliza para poder levantar la aplicación en un servidor web, como lo es [Digtal Ocean](https://www.digitalocean.com)

## Imágenes de la aplicación

### Menú principal

![image](https://user-images.githubusercontent.com/91446330/195421610-760b72d8-74ce-4ce1-98f5-12a5ecb00fb2.png)

### Ver empleados

![image](https://user-images.githubusercontent.com/91446330/195421668-518e0a12-4404-41a5-bcae-e111c5e9cb9a.png)


### Cargar DATA.TXT

![image](https://user-images.githubusercontent.com/91446330/195421732-1d99356b-c866-4c2c-8918-90717c92474c.png)


### Ver el último DATA.TXT cargado

![image](https://user-images.githubusercontent.com/91446330/195421812-32a9d3db-317e-4499-9006-d1a09ffba527.png)

### Ingresar Justificativos

![image](https://user-images.githubusercontent.com/91446330/195421864-9717ceb0-6541-4ec7-af7b-0116bec1274e.png)

### Ingresar autorizaciones

![image](https://user-images.githubusercontent.com/91446330/195421899-6ee65cf8-6e63-486f-9c3b-a54216dad547.png)


### Planilla de Sueldos

![image](https://user-images.githubusercontent.com/91446330/195422032-cffe7a0f-e295-4708-ab4d-a12088f5736a.png)

## Reporte de SonarQube

![image](https://user-images.githubusercontent.com/91446330/195422414-fe544ec9-e6c2-465b-81bc-295dbef3696e.png)

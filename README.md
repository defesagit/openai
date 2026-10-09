# Spring AI: uso de `PromptTemplateController`

Proyecto de práctica con **Spring Boot** y **Spring AI**. El objetivo principal es aprender a construir un prompt a partir de una plantilla externa, insertar valores dinámicos y enviar el resultado a un modelo de chat de OpenAI.

## Objetivos de aprendizaje

- Inyectar una plantilla de prompt almacenada en `src/main/resources`.
- Definir variables dentro de la plantilla y proporcionar sus valores desde una solicitud HTTP.
- Usar `ChatClient` para enviar instrucciones y datos al modelo.
- Exponer la funcionalidad mediante un endpoint REST.

## Cómo funciona

`PromptTemplateController` expone `GET /api/email`. Recibe el nombre del cliente y el mensaje que envió, carga `userPromptTemplate.st`, completa las variables `{customerName}` y `{customerMessage}` y solicita al modelo una respuesta profesional. La respuesta del endpoint es solo el cuerpo del correo, sin asunto ni firma.

La plantilla se encuentra en:

```text
src/main/resources/promptTemplates/userPromptTemplate.st
```

Su contenido define el contexto del correo y las variables que Spring AI completa antes de llamar al modelo. El controlador agrega ademas instrucciones de sistema para orientar el tono y la tarea.

## Requisitos

- Java 25
- Acceso a una API key de OpenAI

Las versiones de Spring Boot y Spring AI se configuran en `build.gradle`.

## Configuracion

La aplicación lee la clave desde la variable de entorno `OPENAI_API_KEY`; no la escribas directamente en el código ni la subas al repositorio.

En macOS o Linux, configúrala para la sesión actual de Terminal:

```bash
export OPENAI_API_KEY="tu-api-key"
```

En PowerShell:

```powershell
$env:OPENAI_API_KEY = "tu-api-key"
```

## Ejecutar la aplicacion

Desde la raiz del proyecto:

```bash
./gradlew bootRun
```

La aplicacion se inicia en `http://localhost:8080`.

## Probar el endpoint

Con `curl`, usando `--get` y `--data-urlencode` para codificar los parametros:

```bash
curl --get "http://localhost:8080/api/email" \
  --data-urlencode "customerName=Ana" \
  --data-urlencode "customerMessage=Mi pedido llego con un producto danado."
```

La respuesta será el texto generado para el cuerpo del correo. Como se trata de una llamada a un modelo de lenguaje, el contenido puede variar entre solicitudes.

## Estructura relevante

```text
src/main/java/com/defesasoft/openai/
├── SpringAiApplication.java
├── config/
│   └── ChatClientConfig.java
└── controller/
    ├── ChatController.java
    └── PromptTemplateController.java

src/main/resources/
├── application.properties
└── promptTemplates/
    └── userPromptTemplate.st
```

`ChatController` muestra otro ejemplo de interacción con `ChatClient`, mientras que `PromptTemplateController` se centra en una plantilla externa con parámetros dinámicos.

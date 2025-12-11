# App de Enseñanza de Náhuatl

Aplicación móvil desarrollada en **Android Studio (Java)** con conexión a **Firebase**, cuyo objetivo es facilitar el aprendizaje del idioma náhuatl mediante lecciones interactivas, glosarios, búsqueda por voz y módulos educativos. Incluye también una versión offline para consulta rápida.

## 📱 Funcionalidades principales

### Módulo Online
- **Autenticación de usuarios** con Firebase Authentication.
- **Glosario ampliado** cargado desde Firestore.
- **Lecciones estructuradas**, cada una con:
  - Preguntas de texto
  - Preguntas con imágenes
  - Traducciones náhuatl–español
- **Búsqueda por voz** (Speech-to-Text).
- **Progreso del usuario** almacenado en Firestore (lecciones completadas).
- **Sección de números** y lecturas adicionales.
- Navegación mediante **barra inferior personalizada**.

### Módulo Offline
- Glosario básico integrado localmente.
- Botones que reproducen audio de pronunciación.
- Funciona sin conexión.

### Versión Wear OS (proyecto paralelo)
- Glosario estático optimizado para reloj.
- Notificaciones periódicas para aprender nuevas palabras.

## 🛠 Tecnologías utilizadas
- **Java** (Android Studio)
- **Firebase Authentication**
- **Cloud Firestore**
- **Firebase Storage** (en caso de imágenes)
- **Android Jetpack Components**
- **XML** para UI
- **SpeechRecognizer API** (búsqueda por voz)

#

## 🚀 Objetivo del proyecto
Promover el aprendizaje del idioma náhuatl mediante herramientas tecnológicas accesibles, combinando contenido educativo con funcionalidades modernas y una experiencia intuitiva.

## 📌 Estado del proyecto
Proyecto en desarrollo y mejora continua.

## © Derechos
El código de este proyecto **no es de uso libre**, no cuenta con una licencia open source.  
**Todos los derechos reservados por el autor.**

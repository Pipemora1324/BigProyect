
# 🌱 PlantSim - Simulador de Crecimiento de Plantas

## 🌟 Descripción General

**PlantSim** es una innovadora aplicación web desarrollada como trabajo final para los cursos de **Estructura de Datos** y **Patrones de Software**. Este simulador permite modelar el crecimiento de plantas en función de variables ambientales como la luz, la humedad y la temperatura. Además, ofrece recomendaciones personalizadas para el cuidado de las plantas y una gestión interactiva del entorno vegetal simulado.

Este proyecto no solo aplica conceptos técnicos fundamentales, sino que también refleja nuestro compromiso con el aprendizaje práctico y el trabajo colaborativo.

---

## 🛠️ Tecnologías Utilizadas

   El desarrollo de PlantSim se apoya en un stack tecnológico moderno y robusto, que permite trabajar de manera eficiente tanto en el frontend como en el backend:

- 🔧 Backend: Spring Boot (Java 21)

   Framework robusto para crear APIs RESTful en Java.

   Contiene la lógica del negocio y conexión con la base de datos.

- 💻 Frontend: Angular 17

   Framework de desarrollo web para crear interfaces dinámicas y modernas.

   Consume los servicios del backend y ofrece una experiencia interactiva al usuario.

- 🗄️ Base de Datos: H2 (en memoria)

   Base de datos relacional que corre en memoria, ideal para pruebas y prototipos.

   No requiere instalación adicional ni configuración compleja.

   🧩 Requisitos Previos

   Para poder ejecutar y compilar el proyecto correctamente en tu máquina, asegúrate de tener instaladas las siguientes herramientas:

   - 📦 Node.js (v16 o superior): necesario para ejecutar Angular y manejar paquetes npm.

   - 🛠️ Maven (v3.8+): utilizado para compilar y ejecutar el proyecto backend con Spring Boot.

   - ☕ JDK 17 o superior: requerido para ejecutar aplicaciones Java con Spring Boot.

   - 🌐 Navegador moderno (como Chrome, Firefox o Edge): para acceder a la aplicación web.

---

## 🧮 Estructuras de Datos Implementadas

1. **📦 Pila (Stack)**  
   Utilizada para mantener un historial de las plantas que han sido visitadas recientemente por el usuario.

2. **🔃 Cola (Queue)**  
   Se emplea para gestionar las operaciones pendientes, como tareas de cuidado que deben ejecutarse en orden.

3. **🌀 Array Circular**  
   Permite una rotación eficiente de las plantas recomendadas, mostrando siempre nuevas sugerencias sin repetir en exceso.

4. **🔗 Lista Enlazada**  
   Gestiona dinámicamente las tareas de cuidado de cada planta, permitiendo agregarlas o eliminarlas fácilmente.

---

## 🧩 Patrones de Diseño Aplicados

1. **📐 MVC (Model-View-Controller)**  
   Organización base de la arquitectura del sistema, separando claramente la lógica de negocio, la vista y el controlador.

2. **🔒 Singleton**  
   Implementado tanto en servicios de Spring Boot como en Angular para asegurar una única instancia de servicios clave.

3. **👀 Observer**  
   Patrón observable aplicado usando RxJS en Angular para gestionar reactividad en tiempo real.

4. **💉 Dependency Injection**  
   Utilizado en ambos frameworks (Angular y Spring Boot) para facilitar el manejo y la inyección de dependencias.

---

## 🚀 Funcionalidades Principales

- 🌿 Gestión completa de plantas (CRUD)
- 🌤️ Simulación de crecimiento según condiciones ambientales
- 💧 Recomendaciones personalizadas de cuidado
- 🗂️ Historial de plantas visitadas
- 📝 Sistema dinámico de tareas de cuidado

---

## 💻 Cómo Ejecutar el Proyecto

### 🔧 Backend

1. Navegar a la carpeta `/backend`
2. Ejecutar `mvn spring-boot:run`
3. El servidor estará disponible en `http://localhost:8082`

### 🎨 Frontend

1. Navegar a la carpeta `/Frontend/plantsim-frontend`
2. Ejecutar `npm install`
3. Ejecutar `ng serve`
4. Abrir `http://localhost:4200` en tu navegador favorito

---

## 🧠 Conclusión

Desarrollar **PlantSim** fue una experiencia formativa y enriquecedora que nos permitió aplicar conceptos clave de **estructuras de datos** y **patrones de diseño** en un entorno práctico y desafiante. Aprendimos a identificar qué estructura o patrón era más adecuado para cada situación, y cómo estos principios pueden mejorar significativamente la organización y escalabilidad de un software.

Si bien el proyecto aún puede optimizarse y expandirse en funcionalidades, consideramos que es una base sólida y un aporte importante para nuestro **portafolio profesional**. Nos motiva seguir perfeccionándolo y nos llena de orgullo poder incluirlo en nuestra hoja de vida como una demostración concreta de nuestras habilidades y conocimientos.

---

## 🎓 Información Académica

**Trabajo Final:**  
Aplicación Web "PlantSim"  
Materias: *Estructura de Datos* y *Patrones de Software*

**Integrantes del equipo:**  
- 👨‍💻 David Fernando Ramírez de la Parra  
- 👨‍💻 Daniers Alexander Solarte Limas  
- 👨‍💻 Juan Felipe Mora Revelo  

**Universidad:** Universidad Cooperativa de Colombia  
**Carrera:** Ingeniería de Software, Cuarto Semestre
**Profesor:** Jhonatan Andrés Mideros Narváez

---


# 🌱 PlantSim - Simulador de Crecimiento de Plantas

## 🌟 Descripción General

**PlantSim** es una innovadora aplicación web desarrollada como trabajo final para los cursos de **Estructura de Datos** y **Patrones de Software**. Este simulador permite modelar el crecimiento de plantas en función de variables ambientales como la luz, la humedad y la temperatura. Además, ofrece recomendaciones personalizadas para el cuidado de las plantas y una gestión interactiva del entorno vegetal simulado.

Este proyecto no solo aplica conceptos técnicos fundamentales, sino que también refleja nuestro compromiso con el aprendizaje práctico y el trabajo colaborativo.

---

## 🛠️ Tecnologías Utilizadas

- **Backend**: ⚙️ Spring Boot (Java 21)
- **Frontend**: 🎨 Angular 17
- **Base de Datos**: 🧠 H2 (en memoria)

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

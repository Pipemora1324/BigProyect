# PlantSim - Simulador de Crecimiento de Plantas

## Descripción
PlantSim es una aplicación web desarrollada como proyecto para el curso de Estructura de Datos y Patrones de Software. Permite simular el crecimiento de plantas basado en variables ambientales y ofrece recomendaciones para su cuidado.

## Tecnologías Utilizadas
- **Backend**: Spring Boot (Java)
- **Frontend**: Angular 17
- **Base de Datos**: H2 (en memoria)

## Estructuras de Datos Implementadas
1. **Pila (Stack)**: Utilizada para el historial de plantas visitadas
2. **Cola (Queue)**: Implementada para registrar operaciones pendientes
3. **Array Circular**: Usado para la rotación de plantas recomendadas
4. **Lista Enlazada**: Aplicada para gestionar tareas de cuidado

## Patrones de Diseño
1. **MVC (Model-View-Controller)**: Arquitectura general del sistema
2. **Singleton**: Servicios en Spring Boot y Angular
3. **Observer**: Implementado a través de Observables de RxJS
4. **Dependency Injection**: Utilizado tanto en Angular como en Spring Boot

## Funcionalidades
- Gestión completa de plantas (CRUD)
- Simulación de crecimiento basada en condiciones ambientales
- Recomendaciones de cuidado según el estado de la planta
- Historial de plantas visitadas
- Sistema de tareas de cuidado

## Cómo Ejecutar el Proyecto
### Backend
1. Navegar a la carpeta `/backend`
2. Ejecutar `mvn spring-boot:run`
3. El servidor se iniciará en `http://localhost:8082`

### Frontend
1. Navegar a la carpeta `/frontend`
2. Navega a la carpeta  `plantsim-frontend`
2. Ejecutar `npm install`
3. Ejecutar `ng serve`
4. Acceder a `http://localhost:4200` en el navegador
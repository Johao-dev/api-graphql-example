# GRAPHQL APIs

GraphQL es un lenguaje de consultas para APIs y un entorno de ejecucion para ejecutar esas consultas con los datos
existentes. Fue desarrollado por Facebook en 2012 y liberado como proyecto open souce en 2015.

---

## ¿En que se diferencia de REST?

| Caracteristica       | GraphQL                                          | REST                                |
| -------------------- | ------------------------------------------------ | ----------------------------------- |
| Consulta de datos    | El cliente define exactamente que quiere.        | El servidor define que se devuelve. |
| Multiples recursos   | Consulta multiples recursos en una sola llamada. | Requiere multiples peticiones.      |
| Versionado           | Sin necesidad de versiones.                      | Versiones frecuentes (v1, v2, etc.) |
| Overfetch/Underfetch | Evitado                                          | Comun (obtienes de mas o de menos). |

---

## Principales conceptos clave de GraphQL

### 1. **Schema**

Define la forma y estructura de los datos disponibles. Es **tipado y fuertemente estructurado.**

Ejemplo de schema:

```graphql
type Usuario {
    id: ID!
    nombre: String!
    email: String!
}

type Query {
    usuarios: [Usuario]
    usuarioPorId(id: ID!): Usuario
}
```

### 2. **Query**

Es la operacion para **leer datos.**

```graphql
query {
    usuarios {
        nombre
        email
    }
}
```

### 3. **Mutation**

Sirve para **modificar datos** (crear, actualizar, elimiinar).

```graphql
mutation {
    crearUsuario(nombre: "zuzz", email: "zuzz@gmail.com") {
        id
    }
}
```

### 4. **Subscription**

Permite al cliente **recibir datos en tiempo real** mediante WebSockets.

```graphql
subscription {
    usuarioCreado {
        id
        nombre
    }
}
```

### 5. **Resolvers**

Son funciones que resuelven el valor de cada campo en el schema.

Ejemplo en JS:

```js
const resolvers = {
    Query: {
        usuarios: () => obtenerTodosLosUsuarios(),
    },
    Mutation: {
        crearUsuario: (_, args) => crearNuevoUsuario(args),
    },
};
```

---

## Ventajas de GraphQL

1. **Consultas precisas:** El cliente pide solo lo que necesita.
2. **Menor numero de llamadas HTTP.**
3. **Documentacion automatica:** Herramientas como GraphiQL o Apollo Studio.
4. **Sin versionado:** Evolucion mas limpia de la API.
5. **Tipado fuerte:** Validacion estatica.
6. **Mas amigable para frontends modernos.**

## Desventajas / Riesgos

1. **Sobrecarga del servidor:** Consultas muy grandes pueden colapsar el backend.
2. **Complejidad inicial mayor:** Comparado con una REST simple.
3. **Caching mas complejo:** No tan directo como REST con HTTP.
4. **Exposicion innecesaria:** Si no se controla, puede exponer relaciones no deseadas.
5. **Seguridad:** Necesita control de profundidad, validacion y autorizacion granular.

---

## Arquitectura GraphQL

* **Cliente ->** Realiza consultas GraphQL.
* **Servidor GraphQL ->** Interpreta, valida y resuelve las querys con resolvers.
* **Origen de datos ->** Bases de datos -> APIs REST, servicios externos, etc.

---

## Seguridad en GraphQL

* **Autenticacion:** Generalmente con JWT, OAuth2, etc.
* **Autorizacion:** En los resolvers.
* **Depth Limiting:** Para evitar consultas infinitas (`graphql-depth-limit`).
* **Rate Limiting:** Contra abuso.
* **Persisted queries:** Evitar consultas arbitrarias en produccion.

---

## Herramientas utiles

* **GraphiQL / Playground:** IDEs para explorar tu API.
* **Apollo Studio:** Observabilidad.
* **GraphQL Voyager:** Visualiza tu schema como un diagrama.
* **Postman:** Tambien soporta GraphQL.

---

## Buenas practicas

1. **Evita logica en el resolver directamente.** Usa capas de servicio.
2. **Define limites de profundidad y tamaño de consulta.**
3. **Documenta los campos con descripciones.**
4. **Utiliza `@deprecated` para cambios graduales.**
5. **Haz uso de fragmentos en queries para reutilizacion.**

---

## Testing

* **Unit Tests** de resolvers.
* **Integration Tests** con herramientas como Apollo TestClient o Postman.
* Validacion de schema con `graphql-tools`.

---

## Casos de uso comunes

* Aplicaciones moviles y SPA donde la eficiencia del ancho de banda importa.
* Sistemas con multiples fuentes de datos (federacion).
* APIs que evolucionan constantemente.

---

## GraphQL vs. REST: ¿Cúal usar?

* Usa **GraphQL** si:
  * El cliente necesita controlar la estructura de los datos.
  * Hay multiples microservicios o fuentes de datos.
  * Hay alto cambio en las vistas del frontend.

* Usa **REST** si:
  * Tienes necesidades simples y bien definidas.
  * El consumo principal es por sistemas legados o integraciones externas simples.
  * El control del trafico HTTP es vital (caching nativo, proxies, CDN).
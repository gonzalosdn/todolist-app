# 📌 ToDo List API REST

Esta API permite gestionar tareas mediante operaciones CRUD.

## 📍 Autenticación

### 🔹 Iniciar sesión
**Método:** `POST`
**URL:** `http://localhost:8080/login`

**Descripción:** Permite a los usuarios autenticarse con su nombre de usuario y contraseña. Devuelve un token JWT.

**Body (JSON):**
```json
{
    "username": "Pepe",
    "password": "12345"
}
```

**Respuestas:**
✅ 200 (OK) - Autenticación exitosa:
```json
{
  "message": "Hola Usuario, has iniciado sesion con exito!",  
  "token": "eyJhb0IjoxNzQx3zKI...",
  "username": "Usuario"
}
```

❌ 401 (Unauthorized) - Credenciales incorrectas:
```json
{
  "message": "Error en la autenticacion username o password incorrectos!",
  "error": "Bad credentials"
}
```

---

## 📍 Usuarios

### 🔹 Registrar usuario
**Método:** `POST`
**URL:** `http://localhost:8080/auth/register`

**Descripción:** Registra un nuevo usuario.

**Body (JSON):**
```json
{
    "username": "Jose",
    "password": "12345"
}
```

**Respuestas:**
✅ 201 (Created) - Registro exitoso:
```json
{
    "id": 4,
    "username": "Jose",
    "roles": [
        {
            "id": 2,
            "name": "ROLE_USER"
        }
    ],
    "enabled": true
}
```

❌ 400 (Bad Request) - Datos inválidos:
```json
{
    "username": "El username no debe estar vacío"
}
```

❌ 400 (Bad Request) - Usuario ya existente:
```json
{
    "username": "El username ya esta registrado"
}
```

---

## 📍 Tareas

### 🔹 Obtener lista de tareas
**Método:** `GET`
**URL:** `http://localhost:8080/tasks`

**Descripción:** Devuelve una lista de todas las tareas del usuario autenticado.

**Respuestas:**
✅ 200 (OK) - Lista de tareas:
```json
[
  {
        "title": "Pasear perros",
        "description": "descripcion",
        "createdAt": "2025-03-04T21:26:25.143815",
        "status": false
    },
  {
        "title": "Hacer almuerzo",
        "description": "descripcion",
        "createdAt": "2025-03-04T21:26:25.143815",
        "status": false
    }
]
```

❌ 401 (Unauthorized) - No autenticado:
```json
{
  "message": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.",
  "error": "El token JWT es invalido!"
}
```

### 🔹 Obtener una tarea por ID
**Método:** `GET`
**URL:** `http://localhost:8080/tasks/{id}`

**Descripción:** Devuelve los detalles de una tarea específica.

**Respuestas:**
✅ 200 (OK) - Tarea encontrada:
```json
{
    "title": "Pasear perros PUT",
    "description": "Llevar a la plaza a los perros",
    "createdAt": "2025-03-04T21:26:25.143815",
    "status": true
}
```

❌ 404 (Not Found) - Tarea no encontrada:
```json
{
    "message": "Task 4 NOT FOUND",
    "error": "Not Found",
    "status": 404,
    "timestamp": "2025-03-12T21:20:45.4154665"
}
```

❌ 401 (Unauthorized) - No autenticado:
```json
{
    "error": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.",
    "message": "El token JWT es invalido!"
}
```

### 🔹 Crear una nueva tarea
**Método:** `POST`
**URL:** `http://localhost:8080/tasks`

**Body (JSON):**
```json
{
    "title": "Tarea nueva",
    "description": "descripcion"
}
```

**Respuestas:**
✅ 201 (Created) - Tarea creada:
```json
{
    "title": "Tarea nueva",
    "description": "description",
    "createdAt": "2025-03-12T20:55:26.6738301",
    "status": false
}
```

### 🔹 Eliminar una tarea por ID
**Método:** `DELETE`
**URL:** `http://localhost:8080/tasks/{id}`

**Respuestas:**
✅ 204 (No Content) - Tarea eliminada.
```json
{}
```

❌ 404 (Not Found) - Tarea no encontrada:
```json
{
    "message": "Task 4 NOT FOUND",
    "error": "Not Found",
    "status": 404,
    "timestamp": "2025-03-12T21:20:45.4154665"
}
```

❌ 401 (Unauthorized) - No autenticado:
```json
{
    "error": "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.",
    "message": "El token JWT es invalido!"
}
```

❌ 403 (Forbidden) - No autorizado:
```json
{
    "message": "Access is denied",
    "error": "Forbidden",
    "status": 403,
    "timestamp": "2025-03-12T21:22:30.987654"
}
```

---

📖 **Documentación generada desde Postman** 🚀


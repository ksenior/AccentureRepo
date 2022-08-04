
# Accenture Reto Java

El proyecto fue realizado con la implementacion de Java Spring Boot y base de datos H2 (para la gestion de permisos)
y swagger para la documentación del servicio.



## Arquitectura del Proyecto

Se dividio el proyecto de forma modular (paquetes / microservicios) independientes por cada entidad y/o función del proyecto.
## Ejemplos

- Asignacion de Permisos
```java
{
	"idUser":1,
	"idAlbum":1,
	"albumUserPermission":[
		{
			"permission":{
				"id":1,
				"name":"Lectura"
			}
		},
		{
			"permission":{
				"id":2,
				"name":"Escritura"
			}
		}
		]
  }
```

- Actualizacion / Eliminacion de Permisos
```java
{
	"id":1,
	"albumUserPermission":[
		{
			"permission":{
				"id":1,
				"name":"Lectura"
			}
		}
		]
  }
```


## Variables

Se utilizaron variables en el archivo application.properties tales como:

`endpoint.mock`
`puerto del server`

Los datos de conexion a la base de datos de H2 se encuentra en el application.properties


## Autores

- [@KevinSenior](https://github.com/ksenior)


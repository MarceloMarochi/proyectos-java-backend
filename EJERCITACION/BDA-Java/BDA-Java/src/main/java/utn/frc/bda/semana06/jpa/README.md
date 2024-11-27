### Conceptos a tratar:

- JPA
    - Especificación e Implementaciones.
    - Entity. EntityManager(Factory). Persistence Unit. JPQL.
    - Configuración del entorno y dependencias.
    - Configuración PU.
    - Creación Entity.
    - Contexto y estados.
    - Anotaciones (entidad y atributos).
    - CRUD con EM.
    - Named queries y Native queries.
    - Transactions.
    - Relaciones entre entidades.
    - Lazy/Eager loading.
    - Herencia relacional vs OO: diferencias conceptuales e incompatibilidades entre implementaciones y con la especificación.
    - Enhancement de classes.



### Observaciones
##### LONG LIVE CLASSIC AND VANILLA JDBC APPROACH !!!

- persistence.xml NO debería estar en un proyecto base/librería
    - pero si no se incluye, NO se "optimizan" las clases at build time
        - NO se puede incluir el plugin (si aplica) para optimización/enhancement
          "at build time" de las clases.
    - otra opción:
        - configurar un "javaagent" "at load time" en proyectos clientes


- herencia datos <> herencia poo
    - alumno --> persona
    - "herencia" en el mundo de los datos es más bien "tipificación"


- inconsistencia en implementaciones
    - eclipselink:
        - NO cumple la especificación de joined inheritance
            - NO queda otra que modelar:

                    Alumno "contains" Persona
                            en vez de
                    Alumno "extends" Persona

              (lo cual ES correcto/coherente en el mundo relacional
              pero NO lo es para el paradigma de objetos)
    - openjpa:
        - construye subclasses en vez de base classes
            - NO fciona subclassfetchmode=none (para evitar join innecesario)

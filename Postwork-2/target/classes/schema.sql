CREATE TABLE IF NOT EXISTS estudiante (
    id              bigint
                    NOT NULL
                    AUTO_INCREMENT,
    nombre_completo VARCHAR(45)
                    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS materia (
    id     bigint
           NOT NULL
           AUTO_INCREMENT,
    nombre VARCHAR(45)
           NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS curso (
    id         bigint
               NOT NULL
               AUTO_INCREMENT,
    ciclo      VARCHAR(4)
               NOT NULL,
    id_materia bigint
               NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_materia) REFERENCES materia(id)
);

CREATE TABLE IF NOT EXISTS curso_estudiante (
    id_curso      bigint
                  NOT NULL,
    id_estudiante bigint
                  NOT NULL,
    calificacion  int
                  NOT NULL,
    UNIQUE KEY ( id_curso, id_estudiante ),
    FOREIGN KEY (id_curso) REFERENCES curso(id),
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id)
);

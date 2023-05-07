DROP TABLE IF EXISTS curso_estudiante;
DROP TABLE IF EXISTS curso;
DROP TABLE IF EXISTS materia;
DROP TABLE IF EXISTS estudiante;

CREATE TABLE estudiante (
    id              bigint
                    NOT NULL
                    AUTO_INCREMENT,
    nombre_completo VARCHAR(45)
                    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE materia (
    id     bigint
           NOT NULL
           AUTO_INCREMENT,
    nombre VARCHAR(45)
           NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE curso (
    id         bigint
               NOT NULL
               AUTO_INCREMENT,
    ciclo      VARCHAR(4)
               NOT NULL,
    id_materia bigint
               NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE curso
    ADD CONSTRAINT fk_curso$materia
    FOREIGN KEY (id_materia)
    REFERENCES materia(id);

CREATE TABLE curso_estudiante (
    id_curso      bigint
                  NOT NULL,
    id_estudiante bigint
                  NOT NULL,
    calificacion  int
                  NOT NULL
);

CREATE UNIQUE INDEX i_curso_estudiante
    ON curso_estudiante (id_curso, id_estudiante);

ALTER TABLE curso_estudiante
    ADD CONSTRAINT fk_curso_estudiante$curso
    FOREIGN KEY (id_curso)
    REFERENCES curso(id);

ALTER TABLE curso_estudiante
    ADD CONSTRAINT fk_curso_estudiante$estudiante
    FOREIGN KEY (id_estudiante)
    REFERENCES estudiante(id);


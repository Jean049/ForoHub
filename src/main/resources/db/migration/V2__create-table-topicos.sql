CREATE TABLE topicos(
    id BIGINT AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje  VARCHAR(350) NOT NULL UNIQUE,
    fecha_creacion DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(autor_id) REFERENCES usuarios(id)
)
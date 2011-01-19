-- Table: temas

-- DROP TABLE temas;

CREATE TABLE temas
(
  id serial NOT NULL,
  nome character varying(50) NOT NULL,
  CONSTRAINT "PK_TEMA" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE temas OWNER TO postgres;



-- Table: comentarios

-- DROP TABLE comentarios;

CREATE TABLE comentarios
(
  id serial NOT NULL,
  id_tema bigint NOT NULL,
  conteudo character varying(200) NOT NULL,
  CONSTRAINT "PK_COMENTARIOS" PRIMARY KEY (id),
  CONSTRAINT "FK_TEMA" FOREIGN KEY (id_tema)
      REFERENCES temas (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comentarios OWNER TO postgres;
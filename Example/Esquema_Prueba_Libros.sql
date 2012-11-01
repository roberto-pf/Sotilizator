/*
Created: 27/10/2012
Modified: 27/10/2012
Model: Oracle 10g
Database: Oracle 10g
*/



-- Drop foreign key indexes section -------------------------------------------------

DROP INDEX IX_PRUEBA_FK_LIBRO_AUTOR
/
DROP INDEX IX_PRUEBA_FK_LIBRO_GENERO
/

-- Drop tables section ---------------------------------------------------

DROP TABLE LIBROS CASCADE CONSTRAINTS PURGE
/
DROP TABLE GENERO CASCADE CONSTRAINTS PURGE
/
DROP TABLE AUTOR CASCADE CONSTRAINTS PURGE
/

-- Drop sequences section --------------------------------------------------- 

DROP SEQUENCE SQ_GENERO_ID
/
DROP SEQUENCE SQ_LIBRO_ID
/
DROP SEQUENCE SQ_AUTOR_ID
/

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE SQ_AUTOR_ID
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE SQ_LIBRO_ID
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE SQ_GENERO_ID
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table AUTOR

CREATE TABLE AUTOR(
  id_autor Number NOT NULL,
  nombre Varchar2(50 ),
  apellidos Varchar2(100 ),
  fecha_nacimiento Date,
  telefono Varchar2(30 ),
  mujer Number(1,0),
  hombre Number(1,0)
)
/

-- Add keys for table AUTOR

ALTER TABLE AUTOR ADD CONSTRAINT PRUEBA_PK_AUTOR PRIMARY KEY (id_autor)
/

-- Table GENERO

CREATE TABLE GENERO(
  id_genero Number NOT NULL,
  nombre Varchar2(30 )
)
/

-- Add keys for table GENERO

ALTER TABLE GENERO ADD CONSTRAINT PRUEBA_PK_GENERO PRIMARY KEY (id_genero)
/

-- Table LIBROS

CREATE TABLE LIBROS(
  id_libro Number NOT NULL,
  isbn Varchar2(30 ),
  titulo Varchar2(100 ),
  descripcion Varchar2(255 ),
  resumen Clob,
  importe_recaudado Number(22,2),
  id_autor Number NOT NULL,
  id_genero Number NOT NULL
)
/

-- Add keys for table LIBROS

ALTER TABLE LIBROS ADD CONSTRAINT PRUEBA_PK_LIBRO PRIMARY KEY (id_libro)
/

-- Create relationships section ------------------------------------------------- 

CREATE INDEX IX_PRUEBA_FK_LIBRO_AUTOR ON LIBROS (id_autor) 
/
ALTER TABLE LIBROS ADD CONSTRAINT PRUEBA_FK_LIBRO_AUTOR FOREIGN KEY (id_autor) REFERENCES AUTOR (id_autor) ON DELETE CASCADE
/

CREATE INDEX IX_PRUEBA_FK_LIBRO_GENERO ON LIBROS (id_genero) 
/
ALTER TABLE LIBROS ADD CONSTRAINT PRUEBA_FK_LIBRO_GENERO FOREIGN KEY (id_genero) REFERENCES GENERO (id_genero) ON DELETE CASCADE
/





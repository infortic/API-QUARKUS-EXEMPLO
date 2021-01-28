-- Drop table

-- DROP TABLE public.beneficio;

CREATE TABLE public.beneficio (
	nro_beneficio int8 NOT NULL,
	cod_cooperativa int8 NOT NULL,
	cod_pac int8 NULL,
	dv_conta_corrente int8 NOT NULL,
	nro_conta_corrente int8 NOT NULL,
	vlr_beneficio numeric(19,2) NOT NULL,
	CONSTRAINT beneficio_pkey PRIMARY KEY (nro_beneficio)
);


insert into public.beneficio 
	(nro_beneficio, cod_cooperativa, cod_pac, dv_conta_corrente, nro_conta_corrente, vlr_beneficio)
values
	(12345601, 1075, null, 9, 1441, 100),
	(12345600, 1075, null, 9, 1441, 100),
	(12345602, 1075, null, 9, 1441, 100),
	(12345603, 1075, null, 9, 1441, 100),
	(12345604, 1075, null, 9, 1441, 100),
	(12345605, 1075, null, 9, 1441, 100),
	(12345606, 1075, null, 9, 1441, 100),
	(12345607, 1075, null, 9, 1441, 100),
	(12345608, 1075, null, 9, 1441, 100),
	(12345609, 1075, null, 9, 1441, 100),
	(12345610, 1075, null, 9, 1441, 100),
	(12345611, 1075, null, 9, 1441, 100),
	(12345612, 1075, null, 9, 1441, 100),
	(12345613, 1075, null, 9, 1441, 100),
	(12345614, 1075, null, 9, 1441, 100),
	(12345615, 1075, null, 9, 1441, 100),
	(12345616, 1075, null, 9, 1441, 100),
	(12345617, 1075, null, 9, 1441, 100),
	(12345618, 1075, null, 9, 1441, 100),
	(12345619, 1075, null, 9, 1441, 100),
	(12345620, 1075, null, 9, 1441, 100),
	(12345621, 1075, null, 9, 1441, 100);


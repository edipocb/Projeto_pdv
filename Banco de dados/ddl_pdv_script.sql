CREATE SCHEMA pdv;

CREATE TABLE pdv.cliente(
	id_cliente INT NOT NULL PRIMARY KEY GENERATED
	ALWAYS AS IDENTITY,
	nome TEXT NOT NULL,
	cpf INT NOT NULL,
	telefone TEXT NOT NULL,
	email TEXT NOT NULL
)

CREATE TABLE pdv.produto(
	id_produto INT NOT NULL PRIMARY KEY GENERATED
	ALWAYS AS IDENTITY,
	nome TEXT NOT NULL,
	preco NUMERIC(18,4),
	estoque INT NOT NULL
)

CREATE TABLE pdv.venda(
	id_venda INT NOT NULL PRIMARY KEY GENERATED
	ALWAYS AS IDENTITY,
	data_venda TIMESTAMPTZ,
	valor_total NUMERIC(18,4),
	id_cliente INT NOT NULL REFERENCES pdv.cliente(id_cliente)	
)

CREATE TABLE pdv.item_venda(
	id_item_venda INT NOT NULL PRIMARY KEY GENERATED
	ALWAYS AS IDENTITY,
	quantidade_item INT NOT NULL,
	preco_unitario NUMERIC(18,4),
	id_venda INT NOT NULL REFERENCES pdv.venda(id_venda),
	id_produto INT NOT NULL REFERENCES pdv.produto(id_produto)
)











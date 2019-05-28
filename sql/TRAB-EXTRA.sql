-- CRIAR BANCO

CREATE TABLE fornecedor (
    cnpj VARCHAR(14) PRIMARY KEY,
    razao_social VARCHAR(60) NOT NULL,
    email varchar(120) not null,
    nome_fantasia VARCHAR(60)
);

CREATE TABLE contrato (
    id_contrato SERIAL PRIMARY KEY,
    data_assinatura DATE NOT NULL,
    prazo INTEGER NOT NULL,
    descricao VARCHAR(120) NOT NULL,
    valor_total MONEY NOT NULL,
    fornecedor_responsavel VARCHAR(14) REFERENCES fornecedor(cnpj)
);

CREATE TABLE fatura (
    id_fatura SERIAL PRIMARY KEY,
    data_envio DATE,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor MONEY NOT NULL,

    contrato_referente INTEGER REFERENCES contrato(id_contrato)
);

-- INSERIR DADOS

INSERT INTO fornecedor VALUES ('81335818000167', 'Almeida Marcenaria Ltda', 'almeidamarcenaria@yahoo.com.br', 'Almeida Marcenaria');
INSERT INTO fornecedor VALUES ('40158809000160', 'Martinelli Comunicação Visual Ltda', 'atendimento@martinellicomunicacao.com.br', 'Martinelli');
INSERT INTO fornecedor VALUES ('60768453000194', 'Intel Corporation', 'suporte@intel.com.br', 'Intel');
INSERT INTO fornecedor VALUES ('80707142000122', 'Juliana e Fátima Buffet ME', 'faleconosco@julianaefatimabuffetme.com.br','Buffet da Ju');
INSERT INTO fornecedor VALUES ('40429579000127', 'Catarina e Gustavo Entulhos ME', 'manutencao@catarinaegustavoentulhosme.com.br', 'Guga Entulhos');

INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-02-02',
 180,
 'Manutenção de estofado',
 '2500.60',
 '81335818000167'
);

INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-02-25',
 260,
 'Plotação de frota',
 '4499.99',
 '40158809000160'
);
INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-03-02',
 260,
 'Marketing',
 '3200.40',
 '40158809000160'
);

INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-03-25',
 90,
 'Compra de computadores',
 '5000.87',
 '60768453000194'
);
INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-05-02',
 90,
 'Compra de Servidores',
 '2440.85',
 '60768453000194'
);

INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-05-25',
 360,
 'Coletar entulhos',
 '1230.40',
 '80707142000122'
);
INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-06-02',
 90,
 'Coletar lixo radiotivo',
 '4214.12',
 '80707142000122'
);
INSERT INTO contrato (data_assinatura, prazo, descricao, valor_total, fornecedor_responsavel) VALUES
(
 '2019-06-25',
 60,
 'Coletar peças para reciclagem',
 '214.11',
 '80707142000122'
);

INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-02-02',
 '2019-02-06',
 '2019-02-06',
 500.12,
 1
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-03-19',
 '2019-03-25',
 '2019-03-25',
 500.12,
 1
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-04-20',
 '2019-04-25',
 '2019-04-28',
 500.12,
 1
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-18',
 '2019-05-25',
 null,
 500.12,
 1
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-06-25',
 null,
 500.12,
 1
);

INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-02-28',
 '2019-03-02',
 '2019-03-02',
 900.00,
 2
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-03-24',
 '2019-03-29',
 '2019-03-29',
 900.00,
 2
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-04-20',
 '2019-04-29',
 '2019-04-30',
 900.00,
 2
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-21',
 '2019-05-29',
 null,
 900.00,
 2
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-06-29',
 null,
 900.00,
 2
);

INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-03-5',
 '2019-03-8',
 '2019-03-5',
 640.08,
 3
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-04-23',
 '2019-04-28',
 '2019-04-29',
 640.08,
 3
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-22',
 '2019-05-28',
 null,
 640.08,
 3
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-06-28',
 null,
 640.08,
 3
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-07-28',
 null,
 640.08,
 3
);

INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-03-26',
 '2019-03-29',
 '2019-03-26',
 1000.17,
 4
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-04-15',
 '2019-04-20',
 '2019-04-26',
 1000.17,
 4
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-15',
 '2019-05-20',
 '2019-05-20',
 1000.17,
 4
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-06-20',
 null,
 1000.17,
 4
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-06-20',
 null,
 1000.17,
 4
);

INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-05',
 '2019-05-10',
 '2019-05-11',
 488.17,
 5
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-11',
 '2019-05-15',
 null,
 488.17,
 5
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 '2019-05-28',
 '2019-06-15',
 '2019-05-28',
 488.17,
 5
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-07-15',
 null,
 488.17,
 5
);
INSERT INTO fatura (data_envio, data_vencimento, data_pagamento, valor, contrato_referente) VALUES
(
 null,
 '2019-07-15',
 null,
 488.17,
 5
);

-- CONSULTAS

SELECT * FROM fornecedor;

SELECT f.razao_social, count(c) as total_contratos FROM fornecedor as f INNER JOIN contrato c on f.cnpj = c.fornecedor_responsavel GROUP BY f.razao_social;

SELECT f.razao_social, sum(f2.valor)
FROM fornecedor as f
INNER JOIN contrato c on f.cnpj = c.fornecedor_responsavel
INNER JOIN fatura f2 on c.id_contrato = f2.contrato_referente
GROUP BY f.razao_social;

SELECT f.razao_social, sum(c.valor_total)
FROM fornecedor as f
INNER JOIN contrato c on f.cnpj = c.fornecedor_responsavel
GROUP BY f.razao_social;
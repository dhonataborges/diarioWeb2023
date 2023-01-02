
---------------------------------------------------------Primeiro Ano - anos iniciais------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Primeiro Ano - anos iniciais", 1, 1);

---------------------------------------------------------Segundo Ano - anos iniciais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Segunda Ano - anos iniciais", 2, 2);

---------------------------------------------------------Terceiro Ano - anos iniciais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Terceira Ano - anos iniciais", 3, 3);

---------------------------------------------------------Quarto Ano - anos iniciais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Quarta Ano - anos iniciais", 4, 4);

---------------------------------------------------------Quinto Ano - anos iniciais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Quinta Ano - anos iniciais", 5, 5);

---------------------------------------------------------Sexto Ano - anos finais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Sexto Ano - anos finais", 6, 6);

---------------------------------------------------------Sétimo Ano - anos finais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Sétimo Ano - anos finais", 7, 7);

---------------------------------------------------------Oitado Ano - anos finais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Oitavo Ano - anos finais", 8, 8);

---------------------------------------------------------Nono Ano - anos finais-------------------------------------------------------------
insert into tb_serie_nivel_subnivel (id, descricao, nivel, subnivel) values (default, "Nono Ano - anos finais", 9, 9);

insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (1, ' Investigação sobre a origem e a formação da língua portuguesa.', "Artes", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (2, ' Investigação sobre a origem e a formação do Empreendendorismo.', "Cultura Empreendedora", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (3, ' Investigação sobre a origem e a formação da Ciências.', "Ciências", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (4, ' Investigação sobre a origem e a formação Educação Física.', "Educação Física", 1);																		
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (5, ' Investigação sobre a origem e a formação do Ensino Religioso.', "Ensino Religioso", 1);																		
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (6, ' Investigação sobre a origem e a formação da Educação Física', "Educação Física", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (7, ' Investigação sobre a origem e a formação da Geografia.', "Geografia", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (8, ' Investigação sobre a origem e a formação da História.', "História", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (9, ' Investigação sobre a origem e a formação da Matemática.', "Matemática", 1);
insert into tb_disciplina (id, ementa, nome, serie_nivel_subnivel_id) values (10, ' Investigação sobre a origem e a formação da língua portuguesa.', "Língua Portuguesa", 1);

insert into tb_turma (id, ano_letivo, descricao, sala, serie_nivel_subnivel_id) values (1, 2022, 'Monteiro Lobato', 1, 1);
insert into tb_turma (id, ano_letivo, descricao, sala, serie_nivel_subnivel_id) values (2, 2022, 'Dom Pedro 2', 2, 3);
insert into tb_turma (id, ano_letivo, descricao, sala, serie_nivel_subnivel_id) values (3, 2022, 'Tiradentes', 3, 2);
insert into tb_turma (id, ano_letivo, descricao, sala, serie_nivel_subnivel_id) values (4, 2022, 'Guimarães Rosa', 4, 3);

insert into tb_aluno (id, nome) values (1, 'Junior Santos');
insert into tb_aluno (id, nome) values (2, 'Carlos Horizonte Oliveira');
insert into tb_aluno (id, nome) values (3, 'Jão Paulo de Assis');
insert into tb_aluno (id, nome) values (4, 'Nikolas Campinas de Cais');
insert into tb_aluno (id, nome) values (5, 'Ana Batista Nogueira');
insert into tb_aluno (id, nome) values (6, 'Lucas Borges dos Santos');
insert into tb_aluno (id, nome) values (7, 'Luiz Neto Oliveira');
insert into tb_aluno (id, nome) values (8, 'Paulo de Assis Nobrega');
insert into tb_aluno (id, nome) values (9, 'Rui Kaio de Cais');
insert into tb_aluno (id, nome) values (10, 'Ana Paula Pereira');

insert into tb_matricula (aluno_id, cod_mat, turma_id) values (1, "00001", 1)
insert into tb_matricula (aluno_id, cod_mat, turma_id) values (2, "00002", 1)
insert into tb_matricula (aluno_id, cod_mat, turma_id) values (3, "00003", 1)
insert into tb_matricula (aluno_id, cod_mat, turma_id) values (4, "00004", 1)
insert into tb_matricula (aluno_id, cod_mat, turma_id) values (5, "00005", 1)

insert into tb_aula (id, data, hora_inicio, hora_fim, conteudo) values (1, "2022-07-11", "12:00", "13:00", "conteudo aplicado...");
insert into tb_aula (id, data, hora_inicio, hora_fim, conteudo) values (2, "2022-07-12", "10:00", "11:00", "conteudo aplicado...");
insert into tb_aula (id, data, hora_inicio, hora_fim, conteudo) values (3, "2022-07-13", "15:00", "16:00", "conteudo aplicado...");
insert into tb_aula (id, data, hora_inicio, hora_fim, conteudo) values (4, "2022-07-14", "07:00", "08:00", "conteudo aplicado...");
insert into tb_aula (id, data, hora_inicio, hora_fim, conteudo) values (5, "2022-07-15", "12:00", "13:00", "conteudo aplicado...");

insert into tb_aluno_aula (id, frequencia, aluno_id, aula_id) values (1, true, 1, 3);
insert into tb_aluno_aula (id, frequencia, aluno_id, aula_id) values (2, true, 1, 2);
insert into tb_aluno_aula (id, frequencia, aluno_id, aula_id) values (3, true, 7, 4);

insert into tb_atividade (id, data_criacao, data_entrega, descricao, nota_maxima, tipo) values (default, "2022-07-11", "2022-07-15", "Teste avaliativo", 5.0, "teste");
insert into tb_atividade (id, data_criacao, data_entrega, descricao, nota_maxima, tipo) values (default, "2022-07-11", "2022-07-16", "Teste avaliativo", 0.5, "teste");
insert into tb_atividade (id, data_criacao, data_entrega, descricao, nota_maxima, tipo) values (default, "2022-07-15", "2022-07-18", "Prova", 25.0, "prova");
insert into tb_atividade (id, data_criacao, data_entrega, descricao, nota_maxima, tipo) values (default, "2022-07-16", "2022-07-17", "Ativiade avaliativa", 0.3, "atividade");
insert into tb_atividade (id, data_criacao, data_entrega, descricao, nota_maxima, tipo) values (default, "2022-07-17", "2022-07-19", "Trabalho avaliativo", 10.0, "trabalho");

insert into tb_aluno_atividade (id, nota, aluno_id, atividade_id) values (1, 5.0, 1, 1);

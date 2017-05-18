## Resolução da questão_04

### Observações iniciais
Para que o projeto seja execultada sem erros se faz necessario a contrução de um banco postgres e outro mysql.
para facilitar as atividades construa os bancos com as seguinte configurações em conformidade com o arquivo (acesso.properties) no repositório
(src/main/resouces) e conforme arquivo (banco.sql) de construção das tabelas no mesmo ambiente.
<br>
Assim como se faz necessário a construção do projeto com as devidas depencência previamente definidas no arquivo (pom.xml) do projeto.

### modo de execução em 2 (passos) passos: <br>
1º Execulta-se o Servidor2 do node2; <br>
2º Execulta-se o Cliente do node1; <br>

### veja a execulsão no cliente
Que solicita a inserção de nome que será atribuido ao objeto pessoa e posteriormente encaminha o objeto pessoa para o node2 que insere no banco de dados
postgres e mysql de forma synchronized para garantir a integridade dos dados.
<br>
E retorna  ao node1 a efetividade da operação se bem sucedida ou não.
De modo a encaminhar novamente caso não seja bem sucedida.
solicitando ao usuario que envie novamente ou nao.
Enviar novamente: \n 1 - sim \n 0 - não"

### e digiete conforme o menu (1) para execultar novamente ou (0) para encerrar;

------------ <br>
 Menu <br>
 1 para execultar novamente; <br>
 0 para encerra. <br>
Digite: <br>


### Caso não possua um ou outro gerenciador de banco de dado instalado se faz necessário a instalação dos mesmos o mudanças no código da classe Servidor2 em node2 no método main no while procure:
Tal trexo de código para não inserir no mysql fazendo os ajuste abaixo descritos.

/*
<br>
  comente esta linha de código para não inserção no banco mysql: <br>
  boolean cadastrouMy = insertMysql(pessoa); <br>
            
  retire do if logo abaixo tal trexo: <br>
   && cadastrouMy <br>
            
*/ 

### Ou o trexo de código que se segue para não inserir no banco de dados postgresql fazendo os ajuste abaixo descritos.

/*<br>

  comente esta linha de código para não inserção no banco postgresql: <br> 
  boolean cadastrouPost = insertPostgres(pessoa); <br>
            
  retire do if logo abaixo tal trexo: <br>
  cadastrouPost && <br>
            
*/ <br>


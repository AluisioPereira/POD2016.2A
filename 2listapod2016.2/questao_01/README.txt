## 2º lista de POD 2016.2
## Resolução da questão_01

### Observações iniciais
Para que o projeto seja executada sem erros se faz necessário a construção dos bancos de dados: <br>
Postgres, e Mysql [conforme as configurações especificadas no arquivo (acesso.properties) que se encontra nos repositórios
(src/main/resouces) dos projetos node1 e node2 ou se preferir mude o arquivo para as configurações especificas do seu banco. <br> 
Posteriormente construa as tabela conforme arquivo (banco.sql) que se encontra no mesmo repositório (src/main/resouces)]. <br>

Assim como se faz necessário a reconstrução dos projetos com as devidas dependências previamente definidas no arquivo (pom.xml) do projeto.

### modo de execução:
1º Baixe/clone este projeto (depositando o um repositório desejado do seu computador);
2º Abra o cmd/terminal navegue até os diretório dos projeto (na raiz dos projeto cliente, node1, node2);
3º Limpe e construa com dependência os projetos (maven);
4º Certifique-se de que o classpath estar vazio com o comando "set classpath = " em aspas.
5º Inicie o rmiregistry "rmiregistry" (sem aspas) dentro das pastas src/main/java de cado node (node1 e node2)  em que será aberto uma janela vazia (no windows) para o programa rmiregistry (não feche a! se preferir minimize a mesma);
6º Execute o Node1 (Servidor1) e o Node2 (Servidor2);
7º Execute o Cliente (Cliente);
8º Acompanhe a execução. 

<br>
<hr>
### PROBLEMAS NO DESENVOLVIMENTO PARA GARANTIR A CONSISTÊNCIA DOS DADOS:
1 - Tem que ser tratado construção do acesso aos métodos de modo a traçar um ambiente diferente para cada servidor de banco de dados na realização inserção e/ou busca das informações;
2 - Tem que ser tratado a construção da pessoa (objeto) para que os mesmos dados que foram recuperados do banco de dados mysql seja garantindo que não haja discrepância em relação ao banco de dados do postgres;
3 - Tem que ser trabalhado para que a realização das atividades remotas de modo sejam sincronizadas garantindo que a próximo operação só execute mediante o termino de em execução;
4 - Foi preciso ainda tratar a interação com o usuário de modo que seja possível a inserção do usuário na possibilidade de uma nova tentativa (tentar novamente) para as informações prestadas quando o resultado da operação adicionar/remover/buscar não ser afirmativa.
5 - Foi preciso a construção de um tratamento para a verificação da consistência das informações presente nos bancos (postgres e mysql) para verificara a igualdade dos objetos (pessoa) consultados. 







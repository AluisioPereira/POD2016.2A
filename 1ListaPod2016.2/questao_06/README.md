## Resolução da questão_06

### Observações iniciais
Para que o projeto seja execultada sem erros se faz necessario a contrução de um banco postgres.
com as configurações especificadas no arquivo (acesso.properties) que se encontra no repositório
(src/main/resouces) ou se preferir mude o mesmo para as configurações do seu banco, posteriormente construa uma tabela rodando o arquivo sql 
(banco.sql) que se encontra no mesmo repositório (src/main/resouces).

Assim como se faz necessário a construção do projeto com as devidas depencência previamente definidas no arquivo (pom.xml) do projeto.

### modo de execução em 3 (passos) passos:
1º Execulta-se o Node3 (Principal3) [para as devidas inserções e atualizações no banco de dados]; <br>
2º Execulta-se o Node1 (Principal1) [para aguardar as notificações ao mesmo enviadas]; <br>
3º Execulta-se o Node2 (Principal2) [para notificar ao node1 as mudanças e atualizações do banco de dados]; <br>

### veja a execulsão no cliente
### Node3:
Contruindo pessoa: 
Informe o nome: "Informe um nome para a construção da pessoa"
------------ <br>
 Menu <br>
 1 para execultar novamente; <br>
 0 para encerra. <br>
Digite: "informe uma escolha desejada" <br>
### Node1:
Node1 esperando notificações... <br>
node1 foi notificado. <br>
Pessoas no banco: <br>
---------------------- 
Id: "id" | Nome: "nome"
----------------------


### Diferênça a ser considerada quanto o padrão observe a implementação aqui apresentada

No padrão observe temos as funcionas resultantende de uma assinatura quanto  a importância do objeto desejado temos alguem responsalve pela buplicação de dado (fato, evento)  e aqueles objetos reposnavel por (assinarem) tal publicação, deste modo recebendo as publicações assim que publicadas, enquanto os objetos estiverem ouvindo as publicações continuaram recebendo as mesmas, se parar de observar as novas publicações não será mais enviadas a tais objetos (assinantes). Na implmentação aqui descrita temos essa mesma abordagem em que o node3 é responsavle por realizar as atualizações no banco de dados postgres e o node2 realiza a busca das informações e comunica a node1 que novas informações foram adicionadas, logo o que diferencia  esta implementação do padrão observe resulta da automaticidade que se fazia necessário para a construção de uma abordagem autonoma para as devidas notificações mediante uma atualização, sendo preciso uma abordagem  de certa forma manual para o processo de busca e notificação. 






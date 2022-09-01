/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Determinísticos.
**/
package lfa;

import csv.TableManager;

public class AFD {
    private final TableManager tableManager;
    private String[][] newTable;
    
    //Construtor
    public AFD() {
        this.tableManager = new TableManager();
    }
    
    //Executa a leitura da tabela de entrada
    public void input(String path) {
        System.out.println("Action => Read INPUT");
        this.tableManager.readInputFile(path);
    }
    
    //Retorna a tabela de entrada
    public String[][] getOriginalTable() {
        return this.tableManager.getReadedTable();
    }
    
    //Exibe todo o conteúdo da tabela
    public String show(String[][] srcTable) {
        String table = "\n";
        
        for (String[] row : srcTable) {
            table = table + "| ";
            for (String cell : row) {
                table = table + cell + " | ";
            }
            table = table + "\n";
        }
        
        return table;
    }
    
    //Executa a gravação da tabela de saída
    public void output(String path, String[][] srcTable) {
        System.out.println("Action => Write OUTPUT");
        this.tableManager.createOutputFile(path, srcTable);
    }
    
    /*
    Gera nova tabela de Autômato Finito Determinı́stico, seguindo a ordem abaixo:
    1) Geração de um Autômato Finito Não Determinı́stico (AFND);
    2) [NÃO IMPLEMENTADO] Determinização.
    3) [NÃO IMPLEMENTADO] Minimização.
    */
    public void generate(String[][] srcTable) {
        //Variáveis
        AFND afnd = new AFND();
        
        //Autômato Finito Não Determinı́stico
        afnd.create(srcTable);
        //Determinização
        //<Insira código aqui>
        //Minimização
        //<Insira código aqui>
        
        //Salva a nova tabela
        this.newTable = afnd.getAFND();
    }
    
    //Retorna a nova tabela gerada
    public String[][] getGeneratedTable() {
        return this.newTable;
    }
}

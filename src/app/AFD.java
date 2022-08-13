/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Determinísticos.
**/
package app;

import csv.TableManager;

public class AFD {
    private final TableManager tableManager;
    
    //Construtor
    public AFD() {
        this.tableManager = new TableManager();
    }
    
    //Executa a leitura da tabela de entrada
    public void input(String path) {
        System.out.println("Action => Read INPUT");
        this.tableManager.readInputFile(path);
        Globals.TABLE = this.tableManager.getTable();
    }
    
    //Exibe toda a tabela do AFD
    public String showAFD(String[][] srcTable) {
        String table = "\nTabela:\n";
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
}

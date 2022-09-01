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
    
    //Exibe todo o conteúdo da tabela (com formatação de espaçamento)
    public String show(String[][] srcTable) {
        String table = "\n";
        int maxSize = 0;
        
        //Procura pela cédula de maior tamanho
        for(int i = 0; i < srcTable.length; i++) {
            for(int j = 0; j < srcTable[i].length; j++) {
                if(srcTable[i][j].length() > maxSize)
                    maxSize = srcTable[i][j].length();
            }
        }
        
        //Checa se 'maxSize' é ímpar
        if(maxSize % 2 != 0)
            maxSize++; //Transforma em par
        
        //Monta a tabela
        for (String[] row : srcTable) {
            table = table + "|";
            
            for (String cell : row) {
                //Quantidade de espaços antes e depois da cédula
                int evenCell = cell.length() - 1;
                if(evenCell % 2 != 0)
                    evenCell++;
                int spaces = (maxSize - evenCell) / 2;
                
                //String de espaço extra (caso tamanho da cédula seja par)
                String extraSpace = "";
                if(cell.length() % 2 == 0)
                    extraSpace = " ";
                //String de espaços em branco
                String cellVoid = "";
                for(int i = 0; i < spaces; i++)
                    cellVoid += " ";
                
                //Monta a cédula
                table = table + cellVoid + cell + cellVoid + extraSpace + "|";
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

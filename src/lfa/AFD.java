/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Determinísticos.
**/
package lfa;

import app.Globals;
import app.gui.SimpleGUI;
import csv.TableManager;

public class AFD {
    private final TableManager tableManager;
    private String[][] originalTable;
    private String[][] newTable;
    
    //Construtor
    public AFD() {
        this.tableManager = new TableManager();
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
    
    //Executa em modo de linha de comando
    public void cmdMode() {
        //Executa leitura da tabela
        this.tableManager.readInputFile(Globals.INPUT);
        
        //Checa se não houveram erros
        if(!Globals.ERROR) {
            //Armazena tabela
            this.originalTable = this.tableManager.getReadedTable();
            
            //Mostra tabela lida
            System.out.println("\nINPUT:" + this.show(this.originalTable));
            
            //Gera nova tabela
            this.generate(this.originalTable);
            
            //Mostra nova tabela
            System.out.println("\nOUTPUT:" + this.show(this.newTable));
            
            //Grava nova tabela
            this.tableManager.createOutputFile(Globals.OUTPUT, this.newTable);
        }
    }
    
    //Executa em modo de interface gráfica
    public void guiMode(SimpleGUI hud) {
        //Executa leitura da tabela
        this.tableManager.readInputFile(hud.getFieldInput());
        
        //Checa se não houveram erros na leitura
        if(!Globals.ERROR) {
            //Armazena tabela
            this.originalTable = this.tableManager.getReadedTable();
            
            //Mostra tabela lida
            System.out.println();
            hud.printLog("INPUT:" + this.show(this.originalTable));
            
            //Gera nova tabela
            this.generate(this.originalTable);
            
            //Mostra nova tabela
            hud.printLog("OUTPUT:" + this.show(this.newTable));
            
            //Grava nova tabela
            this.tableManager.createOutputFile(hud.getFieldOutput(), this.newTable);
            
            //Checa se houveram erros na gravação
            if(Globals.ERROR) {
                String msg = "[ERRO] ";
                msg += "Não foi possível gravar o arquivo de saída.\n";
                msg += "Cheque o arquivo \"error.log\" para mais detalhes...";
                hud.infoLog(msg);
            }
        }
        //Erro na leitura do arquivo
        else {
            String msg = "[ERRO] ";
            msg += "Não foi possível abrir o arquivo de entrada.\n";
            msg += "Cheque o arquivo \"error.log\" para mais detalhes...";
            hud.infoLog(msg);
        }
    }
}

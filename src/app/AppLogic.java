/**
*** @author chrisGrando
*** Classe destinada à lógica global do aplicativo.
**/
package app;

import csv.TableManager;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AppLogic {
    private final TableManager tableManager;
    
    //Construtor
    public AppLogic() {
        this.tableManager = new TableManager();
    }
    
    //Inicia a aplicação
    public void start() throws IOException, CsvValidationException {
        System.out.println("AppLogic => ON");
        
        //Parâmetros passados por linha de comando
        for(String currentArg : Globals.ARGS) {
            //Parâmetro contém hífem
            if(currentArg.contains("-")) {
                //Ativar interface gráfica
                if(currentArg.regionMatches(1, "gui", 0, 3)) {
                    Globals.GUI = true;
                    System.out.println("GUI => ON");
                }
            }
            /*
            Parâmetro NÃO contém hífem.
            ======================================================
            Usado como caminho de diretório relativo/absoluto para
            planílhas de entrada e/ou saída.
            */
            else {
                //Arquivo de entrada
                if(Globals.INPUT == null) {
                    Globals.INPUT = currentArg;
                    System.out.println("Input => OK");
                }
                //Arquivo de saída
                else if(Globals.OUTPUT == null) {
                    Globals.OUTPUT = currentArg;
                    System.out.println("Output => OK");
                }
            }
        }
        /*
        Ativar interface gráfica se não existirem parâmetos, ou então,
        caso o caminho de diretório de entrada/saída seja nulo.
        */
        if(Globals.ARGS == null || Globals.INPUT == null || Globals.OUTPUT == null) {
            Globals.GUI = true;
            System.out.println("Parâmetros inexistentes ou não satisfeitos...");
            System.out.println("GUI => ON");
        }
        
        //Fim da inicialização, início da execução
        this.exec();
    }
    
    //Executa a aplicação
    private void exec() throws IOException, CsvValidationException, FileNotFoundException {
        /*
        TO DO:
        Inverter condição quando a interface gráfica for implementada!
        */
        if(Globals.GUI) {
            //Executa leitura da tabela
            this.tableManager.readInputFile(Globals.INPUT);
            //Mostra tabela
            this.tableManager.printTable();
            //Grava nova tabela
            this.tableManager.createOutputFile(Globals.OUTPUT);
        }
    }
}

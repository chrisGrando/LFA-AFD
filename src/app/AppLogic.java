/**
*** @author chrisGrando
*** Classe destinada à lógica global do aplicativo.
**/
package app;

import app.gui.SimpleGUI;

public class AppLogic {
    
    //Inicia a aplicação
    public void start() {
        System.out.println("AppLogic => ON");
        
        //Parâmetros passados por linha de comando
        for(String currentArg : Globals.ARGS) {
            /*
            Parâmetro contém hífem.
            ======================================================
            Usado para ativação de gatilhos.
            Exemplo: -gui => interface gráfica
            */
            if(currentArg.startsWith("-")) {
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
    }
    
    //Executa a aplicação
    public void exec() {
        //Exibe a interface gráfica (se ativada)
        if(Globals.GUI) {
            SimpleGUI hud = new SimpleGUI();
            hud.startWindow();
        }
        //Se a interface gráfica estiver desativada
        else {
            AFD afd = new AFD();
            //Executa leitura da tabela
            afd.input(Globals.INPUT);
            //Mostra tabela
            System.out.println(afd.showAFD(Globals.TABLE));
            //Grava nova tabela
            afd.output(Globals.OUTPUT, Globals.TABLE);
        }
    }
}

/**
*** @author chrisGrando
*** Classe destinada somente à inicialização básica.
*** (Lógica do software fica armazenada em AppLogic)
**/
package app;

import java.io.PrintStream;

public class Main {

    public static void main(String[] args)
      throws Exception {
        //Configura caracteres como "Unicode"
        System.setOut(new PrintStream(System.out, true, "UTF8"));
        System.setErr(new PrintStream(System.err, true, "UTF8"));
        
        //Salva todos os prints e logs de erro em um arquivo de texto
        TextOutput textOutput = new TextOutput();
        textOutput.changeOUT("/log/console.log");
        textOutput.changeERR("/log/error.log");
        
        //Inicializa classe de lógica do aplicativo
        AppLogic app = new AppLogic();
        app.start(args);
        
        //Executa o programa
        app.exec();
    }
}

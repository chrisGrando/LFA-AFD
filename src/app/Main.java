/**
*** @author chrisGrando
*** Classe destinada somente à inicialização básica.
*** (Lógica do software fica armazenada em AppLogic)
**/
package app;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args)
      throws IOException, CsvValidationException, FileNotFoundException {
        //Armazena parâmetros passados por linha de comando
        Globals.ARGS = args;
        
        //Inicializando classe de lógica do aplicativo
        AppLogic app = new AppLogic();
        app.start();
        app.exec();
    }
}

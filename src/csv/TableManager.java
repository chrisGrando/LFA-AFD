/**
*** @author chrisGrando
*** Classe destinada ao controle geral dos arquivos e tabelas CSV.
**/
package csv;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableManager {
    private String[][] inputTable;
    
    //Lê e carrega arquivo de entrada
    public void readInputFile(String path) {
        try {
            TableReader tableReader = new TableReader();
            tableReader.read(path);
            this.inputTable = tableReader.getFullTable();
        }
        catch (IOException | CsvValidationException error) {
            String msg = "Unable to read the file...";
            Logger.getLogger(TableManager.class.getName()).log(Level.SEVERE, msg, error);
        }
    }
    
    //Cria e grava o arquivo de saída
    public void createOutputFile(String path, String[][] newTable) {
        try {
            TableWriter tableWriter = new TableWriter();
            tableWriter.write(path, newTable);
        }
        catch (IOException error) {
            String msg = "Unable to write the file...";
            Logger.getLogger(TableManager.class.getName()).log(Level.SEVERE, msg, error);
        }
    }
    
    //Retorna a tabela lida
    public String[][] getReadedTable() {
        return this.inputTable;
    }
}

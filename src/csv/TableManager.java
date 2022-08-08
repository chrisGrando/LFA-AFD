/**
*** @author chrisGrando
*** Classe destinada ao controle geral dos arquivos CSV.
**/
package csv;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

public class TableManager {
    private TableReader tableReader;
    
    //Lê e carrega arquivo de entrada
    public void readInputFile(String path) throws IOException, CsvValidationException {
        this.tableReader = new TableReader();
        this.tableReader.read(path);
    }
    
    //Exibe arquivo de entrada em forma de String
    public void printInputFile() {
        System.out.println("\n" + this.tableReader.getTableString());
    }
}

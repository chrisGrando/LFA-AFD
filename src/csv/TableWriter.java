/**
*** @author chrisGrando
*** Classe destinada a gravar o arquivo CSV.
**/
package csv;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TableWriter {
    private CSVWriter csvTable = null;
    
    //Grava o arquivo
    public void write(String path, String[][] table) {
        try {
            //Classes, objetos e variáveis necessárias para a gravação do arquivo
            File file = new File(path);
            FileWriter outputFile = new FileWriter(file);
            this.csvTable = new CSVWriter(outputFile);
            
            //Converte tabela em String, linha por linha, para arquivo CSV
            for (String[] line : table) {
                csvTable.writeNext(line);
            }
            
            //Fecha o arquivo
            csvTable.close();
        }
        catch (IOException error) {
            System.out.println(error);
        }
    }
    
    //String simples com o conteúdo do arquivo
    public String getTableString() {
        String table = this.csvTable.toString();
        return table;
    }
}

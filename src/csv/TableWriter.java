/**
*** @author chrisGrando
*** Classe destinada a gravar o arquivo CSV.
**/
package csv;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TableWriter {
    
    //Grava o arquivo
    public void write(String path, List<String[]> table)
      throws IOException {
        //Classes, objetos e variáveis necessárias para a gravação do arquivo
        CSVWriter csvWriter;
        File file = new File(path);
        FileWriter outputFile = new FileWriter(file);
        csvWriter = new CSVWriter(outputFile);

        //Converte tabela em String, linha por linha, para arquivo CSV
        for (String[] line : table) {
            csvWriter.writeNext(line);
        }

        //Fecha o arquivo
        csvWriter.close();
    }
}

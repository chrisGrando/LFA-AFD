/**
*** @author chrisGrando
*** Classe destinada à leitura do arquivo CSV.
**/
package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableReader {
    private final List<String[]> readedFile;
    
    //Construtor
    public TableReader() {
        this.readedFile = new ArrayList<>();
    }
    
    //Abre e lê o arquivo
    public void read(String path)
      throws IOException, CsvValidationException {
        //Classes, objetos e variáveis necessárias para a leitura do arquivo
        FileReader inputFile = new FileReader(path);
        CSVReader csvReader = new CSVReader(inputFile);
        String[] nextRecord;

        //Lê linha por linha do arquivo
        while ((nextRecord = csvReader.readNext()) != null) {
            /*
            Ignora a linha caso a primeira cédula não possua um dos símbolos:
            -> "#"    (token)
            -> "::="  (gramática)
            */
            if(!nextRecord[0].equals("#") && !nextRecord[0].contains("::="))
                continue;
            
            //Adiciona linha no array
            this.readedFile.add(nextRecord);
        }
        
        //Fecha o arquivo
        csvReader.close();
    }
    
    //Tabela em formato de String
    public List<String[]> getFullTable() {
        return this.readedFile;
    }
}

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
    private String tableString[][];
    
    //Abre e lê o arquivo
    public void read(String path)
      throws IOException, CsvValidationException {
        //Classes, objetos e variáveis necessárias para a leitura do arquivo
        FileReader inputFile = new FileReader(path);
        CSVReader csvReader = new CSVReader(inputFile);
        String[] nextRecord;
        List<String[]> lineList = new ArrayList<>();
        int size = 0;

        //Lê linha por linha do arquivo
        while ((nextRecord = csvReader.readNext()) != null) {
            //Ignora a linha caso não possua um sı́mbolo com o formato "::="
            if(!nextRecord[0].contains("::="))
                continue;
            
            //Adiciona linha no array
            lineList.add(nextRecord);
            size++;
        }
        
        //Adiciona todas as linhas em uma matriz de String
        this.tableString = new String[size][];
        for (int i = 0; i < size; i++) {
            this.tableString[i] = lineList.get(i);
        }
    }
    
    //Tabela em formato de String
    public String[][] getFullTable() {
        return this.tableString;
    }
}

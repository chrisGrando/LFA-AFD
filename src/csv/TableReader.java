/**
*** @author chrisGrando
*** Classe destinada à leitura do arquivo CSV.
**/
package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableReader {
    private String tableString[][];
    private CSVReader csvTable = null;
    
    //Abre e lê o arquivo
    public void read(String path) throws IOException, CsvValidationException, FileNotFoundException {
        //Classes, objetos e variáveis necessárias para a leitura do arquivo
        FileReader filereader = new FileReader(path);
        this.csvTable = new CSVReader(filereader);
        String[] nextRecord;
        List<String[]> lineList = new ArrayList<>();
        int size = 0;

        //Lê linha por linha do arquivo
        while ((nextRecord = this.csvTable.readNext()) != null) {
            lineList.add(nextRecord);
            size++;
        }
        
        //Adiciona todas as linhas em uma matriz de String
        this.tableString = new String[size][];
        for (int i = 0; i < size; i++) {
            this.tableString[i] = lineList.get(i);
        }
    }
    
    //String simples com o conteúdo do arquivo
    public String getTableString() {
        String table = this.csvTable.toString();
        return table;
    }
    
    //Tabela em formato de String
    public String[][] getFullTable() {
        return this.tableString;
    }
}

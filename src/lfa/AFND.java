/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Não Determinísticos.
**/
package lfa;

import java.util.ArrayList;
import java.util.List;

public class AFND {
    private String[][] myAFND;
    private final char[] lowerAlphabet;
    private final char[] upperAlphabet;
    
    //Contrutor
    public AFND() {
        lowerAlphabet = new char[] {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
          'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        upperAlphabet = new char[] {
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
          'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
    }
    
    //Cria tabela de AFND
    public void create(String[][] srcTable) {
        List<String[]> rowList = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        currentLine.add("ƍ"); //Primeira cédula da tabela
        
        //Cria a primeira linha da tabela com os rótulos
        for(String[] row : srcTable) {  
            //Vasculha cada cédula e procura por letras minúsculas
            for(String column : row) {
                //Pula a cédula se conter o símbolo "::="
                if(column.contains("::="))
                    continue;
                //Adiciona a letra minúscula como rótulo (se houver) sem repetir
                String letter = this.findLowerLetter(column);
                if(letter != null && !currentLine.contains(letter)) {
                    currentLine.add(letter);
                }
            }
        }
        rowList.add(this.arrayToVector(currentLine));
        
        //Adiciona todas as linhas na tabela
        this.myAFND = new String[rowList.size()][];
        for(int i = 0; i < rowList.size(); i++) {
            this.myAFND[i] = rowList.get(i);
        }
    }
    
    //Converte array para vetor
    private String[] arrayToVector(List<String> myList) {
        String[] vector = new String[myList.size()];
        
        for(int i = 0; i < myList.size(); i++) {
            vector[i] = myList.get(i);
        }
        
        return vector;
    }
    
    //Procura por letra minúscula do alfabeto
    private String findLowerLetter(String txt) {
        for(char l : txt.toCharArray()) {
            for(char a : this.lowerAlphabet) {
                if(Character.compare(l, a) == 0)
                    return Character.toString(l);
            }
        }  
        return null;
    }
    
    //Procura por letra maiúscula do alfabeto
    private String findUpperLetter(String txt) {
        for(char l : txt.toCharArray()) {
            for(char a : this.upperAlphabet) {
                if(Character.compare(l, a) == 0)
                    return Character.toString(l);
            }
        }  
        return null;
    }
    
    //Retorna AFND criado
    public String[][] getAFND() {
        return this.myAFND;
    }
}

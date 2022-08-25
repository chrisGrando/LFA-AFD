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
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
          'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        upperAlphabet = new char[] {
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
          'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
    }
    
    //Cria tabela de AFND
    public void create(String[][] srcTable) {
        List<String[]> rowList = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        currentLine.add("ƍ"); //Primeira cédula da tabela
        
        /*
        RÓTULOS #1
        =================================================================
        Monta a primeira linha da tabela, composta por letras minúsculas.
        */
        
        //Vasculha cada linha
        for(String[] row : srcTable) {  
            //Vasculha cada coluna
            for(String column : row) {
                //Pula a cédula se conter o símbolo "#" ou "::="
                if(column.equals("#") || column.contains("::="))
                    continue;
                
                //Caso a cédula seja um "ε" (símbolo terminal)
                if(column.contains("ε") && !currentLine.contains("£")) {
                    currentLine.add("£");
                    continue;
                }
                
                //Procura por letras minúsculas
                String letters = this.findAlphabetLetter(column, this.lowerAlphabet);
                //Pula a cédula se não houverem letras minúsculas
                if(letters == null)
                    continue;
                
                //Adiciona a(s) letra(s) minúscula(s) como rótulo, sem repetir
                for(char l: letters.toCharArray()) {
                    if(!currentLine.contains(String.valueOf(l))) {
                        currentLine.add(String.valueOf(l));
                        break;
                    }
                }
            }
        }
        rowList.add(this.arrayToVector(currentLine));
        
        /*
        RÓTULOS #2
        ==================================================================
        Monta a primeira coluna da tabela, composta por letras maiúsculas.
        Preenche todas as outras cédulas, que não são rótulos, com "-".
        */
        
        //Vasculha cada linha
        for(String[] row : srcTable) {
            String tmpCell = "";
            
            //Pula a linha se conter o símbolo "#"
            if(row[0].equals("#"))
                continue;
            
            //Vasculha cada coluna
            for(String column : row) {
                //Procura por letras maiúsculas
                String letters = this.findAlphabetLetter(column, this.upperAlphabet);
                //Pula a cédula se não houverem letras maiúsculas
                if(letters == null)
                    continue;
                
                //Adiciona a(s) letra(s) maiúscula(s) como rótulo, sem repetir
                for(char l: letters.toCharArray()) {
                    if(!tmpCell.contains(String.valueOf(l)))
                        tmpCell += String.valueOf(l);
                }
            }
            
            //Adiciona todas linhas com a primeira coluna da tabela
            for(char t : tmpCell.toCharArray()) {
                //Limpa a lista da linha atual
                currentLine.clear();
                
                //Adiciona a letra na linha
                if(!this.isAlreadyIncluded(t, rowList)) {
                    currentLine.add(String.valueOf(t));
                
                    //Preenche todas as outras cédulas com "-"
                    for(int i = 1; i < rowList.get(0).length; i++)
                        currentLine.add("-");

                    //Adiciona linha atual
                    rowList.add(this.arrayToVector(currentLine));
                }
            }
        }
        
        //Adiciona todas as linhas na tabela
        this.myAFND = new String[rowList.size()][];
        for(int i = 0; i < rowList.size(); i++) {
            this.myAFND[i] = rowList.get(i);
        }
    }
    
    //Checa se uma gramática já foi inclusa
    private boolean isAlreadyIncluded(char c, List<String[]> list) {
        for(String[] l: list) {
            if(Character.compare(c, l[0].charAt(0)) == 0)
                //Já foi inclusa
                return true;
        }
        //Não foi inclusa
        return false;
    }
    
    //Converte array para vetor
    private String[] arrayToVector(List<String> myList) {
        String[] vector = new String[myList.size()];
        
        for(int i = 0; i < myList.size(); i++) {
            vector[i] = myList.get(i);
        }
        
        return vector;
    }
    
    //Procura por letra do alfabeto
    private String findAlphabetLetter(String txt, char[] alphabet) {
        String result = "";
        for(char t : txt.toCharArray()) {
            for(char a : alphabet) {
                if(Character.compare(t, a) == 0)
                    result += Character.toString(t);
            }
        }
        
        //Caso seja encontrada, no mínimo, 1 letra
        if(!result.isBlank())
            return result;
        
        //Caso nenhuma letra seja encontrada
        return null;
    }
    
    //Retorna AFND criado
    public String[][] getAFND() {
        return this.myAFND;
    }
}

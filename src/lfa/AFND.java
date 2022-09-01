/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Não Determinísticos.
**/
package lfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AFND {
    private String[][] myAFND;
    private final char[] lowerAlphabet;
    private final char[] upperAlphabet;
    private long auxLabel = 1;
    
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
        List<String[]> rowList = new ArrayList<>(); //Lista de Linhas
        List<String> currentLine = new ArrayList<>(); //Lista de Colunas
        List<String> labels = new ArrayList<>(); //Lista rótulos
        List<String> tokenList = new ArrayList<>(); //Lista de tokens
        List<String> grammarList = new ArrayList<>(); //Lista de gramáticas
        
        /*
        RÓTULOS
        =================================================================
        Monta a primeira linha da tabela, composta por letras minúsculas.
        */
        currentLine.add("δ"); //Primeira cédula da tabela
        
        //Vasculha cada linha
        for(String[] row : srcTable) {  
            //Vasculha cada coluna
            for(String column : row) {
                //Pula a cédula se conter o símbolo "#" ou "::="
                if(column.equals("#") || column.contains("::="))
                    continue;
                
                //Caso a cédula seja um "ε" (símbolo terminal)
                if(column.contains("ε")) {
                    if(!currentLine.contains("$"))
                        currentLine.add("$");
                    //Pula para a próxima cédula
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
                    }
                }
            }
        }
        
        //Armazena a linha com os rótulos
        labels.addAll(currentLine);
        rowList.add(this.arrayToVector(currentLine));
        
        /*
        ESTADOS => Tokens
        ==================================================================
        Processa os estados dos tokens.
        */
        
        //Cria a gramática inicial (S)
        rowList.add(this.addNewGrammar(rowList, "S"));
        String lastState;
        
        //Procura por tokens
        for(String[] row : srcTable) {
            if(row[0].equals("#"))
                tokenList.add(row[1]);
        }
        
        //Processa os estados dos tokens
        for(String t : tokenList) {
            
            //Cria nova gramática
            rowList.add(this.addNewGrammar(rowList));
            //Limpa a lista da linha atual
            currentLine.clear();
            //Número da linha atual
            int currentIndex = 1; //1 = Linha S
            //Número da última linha criada
            int lastIndex = rowList.size() - 1;
            //Obtém linha da gramática inicial
            currentLine.addAll(Arrays.asList(rowList.get(currentIndex)));
            //ID da última gramática
            lastState = rowList.get(lastIndex)[0];
            
            //Percorre todas as letras do token
            for(int i = 0; i < t.length(); i++) {
                //Caractere atual
                String s = Character.toString(t.charAt(i));
                
                //Novo valor da cédula
                String newValue;
                //Se estiver vazia => Substitui
                if(currentLine.get(labels.indexOf(s)).equals("–"))
                    newValue = lastState;
                //Se já está ocupada => Adiciona
                else
                    newValue = currentLine.get(labels.indexOf(s)) + ";" + lastState;
                
                //Armazena novo valor na linha
                currentLine.set(labels.indexOf(s), newValue);
                rowList.set(currentIndex, this.arrayToVector(currentLine));
                
                //Limpa a lista da linha atual
                currentLine.clear();
                //Obtém linha da última gramática criada
                currentLine.addAll(Arrays.asList(rowList.get(lastIndex)));
                
                //Cria nova gramática, se não estiver no último elemento do token
                if(i != (t.length() - 1)) {
                    currentIndex = lastIndex;
                    rowList.add(this.addNewGrammar(rowList));
                    lastIndex = rowList.size() - 1;
                    lastState = rowList.get(lastIndex)[0]; //ID da última gramática
                }
                //Marca última gramática criada como final, se estiver no último elemento do token
                else {
                    lastState = currentLine.get(0);
                    currentLine.set(0, "*" + currentLine.get(0));
                    rowList.set(lastIndex, this.arrayToVector(currentLine));
                }
            }
        }
        
        /*
        ESTADOS => Gramáticas
        ==================================================================
        Processa os estados das gramáticas.
        */
        
        //Cria uma "gramática final"
        rowList.add(this.addNewGrammar(rowList));
        //Obtém índice
        int finalIndex = rowList.size() - 1;
        //Marca como final
        currentLine.clear();
        currentLine.addAll(Arrays.asList(rowList.get(finalIndex)));
        String idFinal = currentLine.get(0); //ID da gramática final
        currentLine.set(0, "*" + idFinal);
        rowList.set(finalIndex, this.arrayToVector(currentLine));
        
        //Procura por gramáticas e checa a sintaxe
        for(String[] row : srcTable) {
            
            //Checa sintaxe da 1ª coluna
            if(this.isGrammarCorrect(row[0]) && row[0].contains("::=")) {
                
                //Verifica todas as outas colunas
                for(int i = 1; i < row.length; i++) {
                    //Primeiro caractere na coluna
                    char c = row[i].charAt(0);
                    //Verifica se é uma letra minúscula
                    String firstLetter = this.findAlphabetLetter(Character.toString(c), this.lowerAlphabet);
                    
                    //Verifica se é símbolo terminal
                    if(row[i].equals("ε")) {
                        //Adiciona coluna
                        String id = Character.toString(row[0].charAt(1)); //ID da linha
                        grammarList.add(id + "$");
                        //Pula para a próxima cédula
                        continue;
                    }
                    //Verifica se obedece à sintaxe
                    if(firstLetter != null && this.isGrammarCorrect(row[i])) {
                        //Adiciona coluna
                        grammarList.add(row[i]);
                    }
                }
                
                //Trabalha com as gramáticas encontradas
                for(String g : grammarList) {
                    int line;
                    int cell;
                    String key;
                    String newValue;
                    
                    //Se for símbolo terminal
                    if(g.contains("$")) {
                        //Procura linha e cédula que possua a gramática
                        key = Character.toString(g.charAt(0));
                        line = this.findRowIndex(key, rowList);
                        cell = labels.indexOf("$");
                        
                        //Novo valor da cédula
                        newValue = idFinal;
                        
                        //Novo valor da linha
                        currentLine.clear();
                        currentLine.addAll(Arrays.asList(rowList.get(line)));
                        currentLine.set(cell, newValue);
                        rowList.set(line, this.arrayToVector(currentLine));
                        
                        //Atualiza linha final
                        currentLine.clear();
                        currentLine.addAll(Arrays.asList(rowList.get(finalIndex)));
                        currentLine.set(cell, newValue);
                        rowList.set(finalIndex, this.arrayToVector(currentLine));
                        
                        //Próxima gramática
                        continue;
                    }
                    
                    //Rótulo da cédula
                    String currentLabel = Character.toString(g.charAt(0));
                    //Procura linha e cédula que possua a gramática
                    key = Character.toString(g.charAt(2));
                    line = this.findRowIndex(key, rowList);
                    cell = labels.indexOf(currentLabel);
                    //Novo valor da cédula
                    newValue = currentLine.get(cell);
                    
                    //Linha foi encontrada
                    if(line > -1) {
                        //Obtém a linha
                        currentLine.clear();
                        currentLine.addAll(Arrays.asList(rowList.get(line)));
                        
                        //Cédula vazia
                        if(newValue.equals("–"))
                            newValue = idFinal;
                        //Cédula ocupada
                        else if(!newValue.contains(idFinal))
                            newValue += ";" + idFinal;
                        
                        //Armazena novo valor da linha
                        currentLine.set(cell, newValue);
                        rowList.set(line, this.arrayToVector(currentLine));
                    }
                    //Linha NÃO foi encontrada
                    else {
                        //Cria nova gramática
                        rowList.add(this.addNewGrammar(rowList, key));
                        currentLine.clear();
                        line = rowList.size() - 1;
                        currentLine.addAll(Arrays.asList(rowList.get(line)));
                        
                        //Preenche cédula
                        newValue = idFinal;
                        currentLine.set(cell, newValue);
                        rowList.set(line, this.arrayToVector(currentLine));
                    }
                    
                    //Atualiza linha final
                    currentLine.clear();
                    currentLine.addAll(Arrays.asList(rowList.get(finalIndex)));
                    currentLine.set(cell, newValue);
                    rowList.set(finalIndex, this.arrayToVector(currentLine));
                }
            }
        }
        
        /*
        FIM
        ==================================================================
        Adiciona todas as linhas na tabela.
        */
        this.myAFND = new String[rowList.size()][];
        for(int i = 0; i < rowList.size(); i++) {
            this.myAFND[i] = rowList.get(i);
        }
    }
    
    //Checa se a sintaxe da gramática está correta
    private boolean isGrammarCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            
            switch (check) {
                //Começa por "<"?
                case 0 -> {
                    if(Character.compare(c, '<') == 0)
                        check++;
                }
                //Possui letra maiúscula?
                case 1 -> {
                    String l = this.findAlphabetLetter(Character.toString(c), this.upperAlphabet);
                    if(l != null)
                        check++;
                    else
                        check = 0;
                }
                //Termina com ">"?
                case 2 -> {
                    if(Character.compare(c, '>') == 0)
                        check++;
                    else
                        check = 0;
                }
            }
        }
        
        //Gramática está correta
        if(check > 2) {
            result = true;
        }
        
        return result;
    }
    
    //Procura pelo índice de uma linha
    private int findRowIndex(String key, List<String[]> list) {
        
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i)[0].contains(key))
                return i; //Linha encontrada
        }
        
        //Linha não encontrada
        return -1;
    }
    
    //Adiciona nova gramática (sem letra ou alfabeto específico)
    private String[] addNewGrammar(List<String[]> list) {
        return this.addNewGrammar(list, null);
    }
    
    //Adiciona nova gramática
    private String[] addNewGrammar(List<String[]> list, String custom) {
        char[] auxAlphabet;
        String[] grammar = null;
        int size = list.get(0).length;
        
        //Se for pedido uma ou várias letras específicas
        if(custom != null) {
            auxAlphabet = custom.toCharArray();
        }
        //Usar alfabeto padrão se não houverem especificações
        else {
            auxAlphabet = this.upperAlphabet;
        }
        
        //Procura por uma letra não usada para adicionar à lista
        for(char c : auxAlphabet) {
            if(!this.isAlreadyIncluded(c, list)) {
                grammar = new String[size];
                //Letra da nova gramática
                grammar[0] = Character.toString(c);
                //Interrompe loop
                break;
            }
        }
        
        //Caso nenhuma letra disponível seja encontrada...
        if(grammar == null) {
            grammar = new String[size];
            //...Será usado um número no lugar
            grammar[0] = Long.toString(this.auxLabel);
            this.auxLabel++;
        }
        
        //Preenche todas as outras cédulas com "–"
        for(int i = 1; i < size; i++) 
            grammar[i] = "–";
        
        //Retorna nova gramática
        return grammar;
    }
    
    //Checa se uma gramática já foi inclusa
    private boolean isAlreadyIncluded(char c, List<String[]> list) {
        
        for(String[] l: list) {
            //Estado normal
            if(l[0].length() == 1) {
                if(Character.compare(c, l[0].charAt(0)) == 0)
                    //Já foi inclusa
                    return true;
            }
            //Estado final
            if(l[0].length() > 1) {
                if(Character.compare(c, l[0].charAt(1)) == 0)
                    //Já foi inclusa
                    return true;
            }
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

/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Não Determinísticos
*** (AFND), ou em inglês, Non-deterministic Finite Automaton (NDFA).
**/
package lfa;

import java.util.ArrayList;
import java.util.List;

public class NDFA {
    //Listas
    private List<String[]> arrayListNDFA; //Tabela AFND completa
    private final List<String> labels;    //Lista de rótulos (colunas)
    //Alfabetos
    private final char[] lowerAlphabet;
    private final char[] upperAlphabet;
    private int numericAlphabet = 0;
    
    //Construtor
    public NDFA() {
        //Inicializa as listas
        arrayListNDFA = new ArrayList<>();
        labels = new ArrayList<>();
        
        //Alfabeto minúsculo
        lowerAlphabet = new char[] {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
          'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        
        //Alfabeto maiúsculo
        upperAlphabet = new char[] {
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
          'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
    }
    
    //Cria tabela de AFND
    public void create(List<String[]> srcTable) {
        /*
        RÓTULOS
        =================================================================
        Monta a primeira linha da tabela, composta por letras minúsculas
        e números.
        */
        this.labels.add("δ"); //Primeira cédula da tabela
        
        //Percorre cada linha da tabela de entrada
        for(String[] line : srcTable) {
            String cell;
            
            //Tokens
            if(line[0].equals("#") && line.length > 1) {
                cell = line[1].toLowerCase();
                
                //Percorre e registra cada letra do token como rótulo
                for(char c : cell.toCharArray()) {
                    //Checa se é número ou letra minúscula
                    if(this.isValidLabel(c)) {
                        String l = Character.toString(c);
                        //Adiciona rótulo (sem repetir)
                        if(!this.labels.contains(l))
                            this.labels.add(l);
                    }
                }
            }
            
            //Gramáticas
            else if(this.isGrammarRuleCorrect(line[0]) && line.length > 1) {
                //Registra o primeiro símbolo de cada gramática como rótulo
                for(int i = 1; i < line.length; i++) {
                    //Pula a cédula se for um símbolo terminal
                    if(line[i].contains("ε"))
                        continue;
                    
                    //Checa se a sintaxe da gramática está correta
                    if(this.isGrammarSyntaxCorrect(line[i]))
                        cell = line[i];
                    //Pula a cédula caso incorreta
                    else
                        continue;
                    
                    //Adiciona rótulo (sem repetir)
                    String l = Character.toString(cell.charAt(0));
                    if(!this.labels.contains(l))
                        this.labels.add(l);
                }
            }
        }
        
        //Salva rótulos na tabela
        this.arrayListNDFA.add(this.arrayListToVector(this.labels));
        
        /*
        TOKENS
        =================================================================
        Monta as próximas linhas da tabela, criando gramáticas a partir
        de tokens.
        */
        boolean isFirstToken = true;
        List<String> tokenList = new ArrayList<>();
        String previousLetter = "";
        
        //Obtém lista dos tokens
        for(String[] line : srcTable) {
            if(line[0].equals("#") && line.length > 1)
                tokenList.add(line[1].toLowerCase());
        }
        
        //Gera gramáticas para cada letra de cada token
        for(String token : tokenList) {
            for(int i = 0; i < token.length(); i++) {
                //Primeiro token
                if(isFirstToken) {
                    //Desativa gatilho
                    isFirstToken = false;
                    
                    //Cria primeira gramática
                    String[] grammar = this.addNewGrammar(this.arrayListNDFA, "S");
                    this.arrayListNDFA.add(grammar);
                    previousLetter = "S";
                }
                
                //Cria nova gramática para o próximo item
                String[] grammar = this.addNewGrammar(this.arrayListNDFA);
                this.arrayListNDFA.add(grammar);
                
                //Adiciona ponteiro na gramática anterior
                this.arrayListNDFA = this.addPointerOnList(
                    this.arrayListNDFA,
                    previousLetter,
                    Character.toString(token.charAt(i)),
                    grammar[0]
                );
                
                //Atualiza variável da gramática anterior
                previousLetter = grammar[0];
            }
            
            /*
            Após a última letra do token, marca a última gramática criada
            como final.
            */
            int last = this.arrayListNDFA.size() - 1;
            String row = this.arrayListNDFA.get(last)[0];
            this.arrayListNDFA = this.setAsFinal(this.arrayListNDFA, row);
            
            /*
            Se não for o último token da lista, então o ponteiro da gramática
            anterior volta para o "S"
            */
            String finalItemOnList = tokenList.get(tokenList.size() - 1);
            if(!token.equals(finalItemOnList))
                previousLetter = "S";
        }
        
        /*
        Gramáticas
        =================================================================
        Executa a última etapa da criação do AFND, aplicando as regras
        de gramáticas inclusas no arquivo.
        */
        List<String[]> grammarList = new ArrayList<>();
        
        //Obtém lista das gramáticas
        for(String[] line : srcTable) {
            boolean isLineCorrect = true;
            
            //Pula a linha se for um token
            if(line[0].equals("#"))
                continue;
            
            //Checa se a sintaxe da linha inteira está correta
            if(this.isGrammarRuleCorrect(line[0]) && line.length > 1) {
                //Percorre cada cédula da linha
                for(int i = 1; i < line.length; i++) {
                    String cell = line[i];
                    
                    //Checa a sintaxe da cédula está incorreta
                    if(!this.isGrammarSyntaxCorrect(cell)) {
                        isLineCorrect = false;
                        break;
                    }
                }
            }
            //Sintaxe da linha está incorreta
            else
                isLineCorrect = false;
            
            //Se não houverem problemas na linha, adicione na lista
            if(isLineCorrect)
                grammarList.add(line);
        }
        
        //Procura por gramáticas já usadas
        List<String[]> alreadyUsed = new ArrayList<>();
        
        //Linha
        for(String[] line : grammarList) {
            //Coluna
            for(String cell : line) {
                //Pula a cédula se for a gramática inicial (S::=)
                if(cell.equals("<S>::="))
                    continue;
                
                //Pula a cédula se for símbolo terminal
                if(cell.equals("ε"))
                    continue;
                
                //Ponteiro de gramática da cédula
                int begin = cell.indexOf("<") + 1;
                int end = cell.indexOf(">");
                String pointer = cell.substring(begin, end);
                
                //Já existe?
                if(this.isAlreadyIncluded(pointer, this.arrayListNDFA)) {
                    //Já foi incluído na lista de usados?
                    boolean isItOnAlreadyUsedList = false;
                    for(String[] row : alreadyUsed) {
                        if(row[0].equals(pointer)) {
                            isItOnAlreadyUsedList = true;
                            break;
                        }
                    }
                    
                    //Ainda não foi incluído na lista de usados
                    if(!isItOnAlreadyUsedList) {
                        //Cria nova gramática
                        String[] grammar = this.addNewGrammar(this.arrayListNDFA);
                        this.arrayListNDFA.add(grammar);
                        
                        //Marca ponteiro para substituição
                        String[] pointerToReplace = {
                            pointer,
                            grammar[0]
                        };
                        
                        //Inclui na lista de usados
                        alreadyUsed.add(pointerToReplace);
                    }
                }
                //Ainda não existe
                else {
                    //Cria nova gramática (com o nome do ponteiro)
                    String[] grammar = this.addNewGrammar(this.arrayListNDFA, pointer);
                    this.arrayListNDFA.add(grammar);
                    
                    /*
                    Para evitar que o ponteiro seja interpretado como reutilizado,
                    caso o mesmo seja encontrado novamente em um futuro laço do
                    loop, será adicionado o ponteiro na lista de usados.
                    (Neste caso, o conteúdo do ponteiro antigo e do novo serão
                    os mesmos)
                    */
                    String[] pointerToKeep = {
                        pointer,
                        grammar[0]
                    };

                    //Inclui na lista de usados
                    alreadyUsed.add(pointerToKeep);
                }
            }
        }
        
        //Aplica substituição de ponteiros de gramáticas já usadas
        for(String[] current : alreadyUsed) {
            //Linha
            for(int i = 0; i < grammarList.size(); i++) {
                //Coluna
                for(int j = 0; j < grammarList.get(i).length; j++) {
                    //Pula a cédula caso não seja necessário substituir
                    if(current[0].equals(current[1]))
                        continue;
                    
                    //Lista de gramáticas
                    String[] line = grammarList.get(i);
                    String cell = line[j];
                    
                    //Pula a cédula se for símbolo terminal
                    if(cell.equals("ε"))
                        continue;
                    
                    //Pula a cédula se for a gramática inicial (S::=)
                    if(cell.equals("<S>::="))
                        continue;
                    
                    //Ponteiros
                    String oldPointer = "<" + current[0] + ">";
                    String newPointer = "<" + current[1] + ">";
                    
                    //Checa se contém o ponteiro marcado para substituição
                    if(cell.contains(oldPointer)) {
                        //Substui ponteiro
                        cell = cell.replace(oldPointer, newPointer);
                        line[j] = cell;
                        grammarList.set(i, line);
                    }
                }
            }
        }
        
        //Após a substituição, insere os ponteiros na tabela
        for(String[] row : grammarList) {
            //Gramática da linha atual
            int begin = row[0].indexOf("<") + 1;
            int end = row[0].indexOf(">");
            String currentGrammar = row[0].substring(begin, end);
            
            //Ponteiros da linha atual
            for(int i = 1; i < row.length; i++) {
                //Marca gramática como final se for símbolo terminal
                if(row[i].equals("ε")) {
                    this.arrayListNDFA = this.setAsFinal(this.arrayListNDFA, currentGrammar);
                    continue;
                }
                
                //ID da coluna
                char c = row[i].charAt(0);
                String label = Character.toString(c);
                
                //ID do ponteiro
                int p_begin = row[i].indexOf("<") + 1;
                int p_end = row[i].indexOf(">");
                String pointer = row[i].substring(p_begin, p_end);
                
                //Insere ponteiro
                this.arrayListNDFA = this.addPointerOnList(
                    this.arrayListNDFA,
                    currentGrammar,
                    label, 
                    pointer
                );
            }
        }
    }
    
    //Marca gramática como final (ex.: *F)
    private List<String[]> setAsFinal(List<String[]> list, String row) {
        int idRow = -1;
        
        //Número da linha
        for(String[] line : list) {
            if(line[0].equals(row)) {
                idRow = list.indexOf(line);
                break;
            }
        }
        
        //Se a linha for encontrada, marca como final
        if(idRow > -1) {
            String cell = list.get(idRow)[0];
            String[] aux = list.get(idRow);
            
            //Adiciona asterísco
            cell = "*" + cell;
            aux[0] = cell;
            
            //Atualiza a lista
            list.set(idRow, aux);
        }
        
        //Retorna a lista (com ou sem modificações)
        return list;
    }
    
    //Checa se um ponteiro já está incluso na cédula
    private boolean isPointerAlreadyOnCell(String cell, String pointer) {
        List<String> allPointers = new ArrayList<>();
        String item = "";
        
        //Examina todos os caracteres da cédula
        for(char c : cell.toCharArray()) {
            /*
            Se o caractere atual for um ";" e a variável 'item' não estiver
            vazia, então adiciona o conteúdo dela na lista.
            */
            if(Character.compare(c, ';') == 0 && !item.isBlank()) {
                allPointers.add(item);
                item = "";
                continue;
            }
            
            //Armazena o caractere atual na variável
            item += Character.toString(c);
        }
        
        /*
        Se a variável 'item' não estiver vazia após o loop, então o conteúdo
        dela será adicionado na lista.
        */
        if(!item.isBlank())
            allPointers.add(item);
        
        //Verifica se o ponteiro já está presente na cédula
        for(String current : allPointers) {
            //Ponteiro encontrado
            if(current.equals(pointer))
                return true;
        }
        
        //Ponteiro não está na cédula
        return false;
    }
    
    //Adiciona um ponteiro no item de uma gramática para outra
    private List<String[]> addPointerOnList
      (List<String[]> list, String row, String column, String pointer) {
        int idRow = -1;
        int idLabel = -1;
        
        //Número da linha e da coluna
        for(String[] line : list) {
            if(line[0].equals(row)) {
                //Linha
                idRow = list.indexOf(line);
                
                //Coluna
                idLabel = this.labels.indexOf(column);
                
                //Interrompe loop
                break;
            }
        }
        
        //Se a linha e a coluna forem encontradas, adiciona o ponteiro
        if(idRow > -1 && idLabel > -1) {
            String cell = list.get(idRow)[idLabel];
            String[] aux = list.get(idRow);
            
            //Cédula está vazia
            if(cell.equals("–"))
                cell = pointer;
            
            //Cédula já está ocupada
            else {
                //Adiciona somente se o ponteiro ainda não estiver presente
                if(!this.isPointerAlreadyOnCell(cell, pointer))
                    cell += ";" + pointer;
            }
            
            //Atualiza a lista
            aux[idLabel] = cell;
            list.set(idRow, aux);
        }
        
        //Retorna a lista (com ou sem modificações)
        return list;
    }
    
    //Checa se é número ou letra minúscula válida como rótulo
    private boolean isValidLabel(char c) {
        //É número?
        if(Character.isDigit(c))
            return true;
        
        //É letra minúscula?
        else if(this.findAlphabetLetter(c, this.lowerAlphabet) != null)
            return true;
        
        //Charactere inválido
        return false;
    }
    
    //Checa se a nomeação da regra de gramática está correta (ex.: <S>::=, <11>::=)
    private boolean isGrammarRuleCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            /*
            Condições para pular o loop atual:
            1) Espaço em branco.
            2) Número com mais de um dígito (ex.: 101).
            */
            
            //Espaço em branco
            if(Character.isSpaceChar(c))
                continue;
            
            //Número com mais de um dígito
            if(check == 2 && Character.isDigit(c))
                continue;
            
            //Checa regra
            switch (check) {
                //Começa com "<"?
                case 0 -> {
                    if(Character.compare(c, '<') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Segue com letra maiúscula ou número?
                case 1 -> {
                    //Letra maiúscula
                    String curChar = this.findAlphabetLetter(
                        Character.toString(c),
                        this.upperAlphabet
                    );
                    if(curChar != null)
                        check++;
                    
                    //Número
                    else if(Character.isDigit(c))
                        check++;
                    
                    //Regra inválida
                    else
                        check = -1;
                }
                //Segue com ">"?
                case 2 -> {
                    if(Character.compare(c, '>') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Segue com ":"?
                case 3 -> {
                    if(Character.compare(c, ':') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Segue com outro ":"?
                case 4 -> {
                    if(Character.compare(c, ':') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Termina com "="?
                case 5 -> {
                    if(Character.compare(c, '=') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Regra inválida OU qualquer coisa além do "="
                default -> {
                    check = -1;
                    break;
                }
            }
        }
        
        //Gramática está correta
        if(check == 6) {
            result = true;
        }
        
        return result;
    }
    
    //Checa se a sintaxe da gramática está correta (ex.: x<S>, 1<A>, 0<10>, ε)
    private boolean isGrammarSyntaxCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            /*
            Condições para pular o loop atual:
            1) Espaço em branco.
            2) Número com mais de um dígito (ex.: 101).
            3) Símbolo terminal (ε).
            */
            
            //Espaço em branco
            if(Character.isSpaceChar(c))
                continue;
            
            //Número com mais de um dígito
            if(check == 3 && Character.isDigit(c)) {
                continue;
            }
            
            //Símbolo terminal
            if(check == 0 && Character.compare(c, 'ε') == 0) {
                check = 4;
                continue;
            }
            
            //Checa sintaxe
            switch (check) {
                //Começa com letra minúscula ou número?
                case 0 -> {
                    //Letra minúscula
                    String curChar = this.findAlphabetLetter(
                        Character.toString(c),
                        this.lowerAlphabet
                    );
                    if(curChar != null)
                        check++;
                    
                    //Número
                    else if(Character.isDigit(c))
                        check++;
                    
                    //Sintaxe incorreta
                    else
                        check = -1;
                }
                //Segue com "<"?
                case 1 -> {
                    if(Character.compare(c, '<') == 0)
                        check++;
                    else
                        check = -1; //Sintaxe incorreta
                }
                //Segue com letra maiúscula ou número?
                case 2 -> {
                    //Letra maiúscula
                    String curChar = this.findAlphabetLetter(
                        Character.toString(c),
                        this.upperAlphabet
                    );
                    if(curChar != null)
                        check++;
                    
                    //Número
                    else if(Character.isDigit(c))
                        check++;
                    
                    //Sintaxe incorreta
                    else
                        check = -1;
                }
                //Termina com ">"?
                case 3 -> {
                    if(Character.compare(c, '>') == 0)
                        check++;
                    else
                        check = -1; //Sintaxe incorreta
                }
                //Sintaxe incorreta OU qualquer coisa além do ">" ou do "ε"
                default -> {
                    check = -1;
                    break;
                }
            }
        }
        
        //Gramática está correta
        if(check == 4) {
            result = true;
        }
        
        return result;
    }
    
    //Adiciona nova gramática (SEM letra(s) específica(s))
    private String[] addNewGrammar(List<String[]> list) {
        //Alfabeto maiúsculo padrão (versão string)
        String upperAux = "";
        
        //Char array para string
        for(char c : this.upperAlphabet)
            upperAux += Character.toString(c);
        
        return this.addNewGrammar(list, upperAux);
    }
    
    //Adiciona nova gramática (COM letra(s) específica(s))
    private String[] addNewGrammar(List<String[]> list, String custom) {
        String[] grammar = null;
        final int size = list.get(0).length;
        
        //Procura por uma letra não usada para adicionar à lista
        for(char c : custom.toCharArray()) {
            if(!this.isAlreadyIncluded(c, list)) {
                grammar = new String[size];
                //Letra da nova gramática
                grammar[0] = Character.toString(c);
                //Interrompe loop
                break;
            }
        }
        
        /*
        Caso nenhuma letra disponível seja encontrada, será usado um número
        no lugar.
        */
        while(grammar == null) {
            //Converte número inteiro em String
            String int_name = Integer.toString(this.numericAlphabet);

            //Número já foi usado
            if(this.isAlreadyIncluded(int_name, list)) {
               this.numericAlphabet++;
               continue;
            }

            //Número disponível
            grammar = new String[size];
            grammar[0] = int_name;
            this.numericAlphabet++;
        }
        
        //Preenche todas as outras cédulas com "–"
        for(int i = 1; i < size; i++) 
            grammar[i] = "–";
        
        //Retorna nova gramática
        return grammar;
    }
    
    //Checa se uma gramática já foi inclusa (versão char)
    private boolean isAlreadyIncluded(char c, List<String[]> list) {
        String grammar = Character.toString(c);
        return this.isAlreadyIncluded(grammar, list);
    }
    
    //Checa se uma gramática já foi inclusa (versão string)
    private boolean isAlreadyIncluded(String grammarName, List<String[]> list) {
        String tmp_grammar;
        
        for(String[] l: list) {
            //Estado final (*)
            if(l[0].contains("*"))
                tmp_grammar = l[0].substring(1); //Remove o asterísco
            //Estado normal
            else
                tmp_grammar = l[0];
            
            //Checa se já existe
            if(grammarName.equals(tmp_grammar))
                //Já foi inclusa
                return true;
        }
        
        //Não foi inclusa
        return false;
    }
    
    //Procura por letra do alfabeto especificada (versão char)
    private String findAlphabetLetter(char c, char[] alphabet) {
        String str_char = Character.toString(c);
        return this.findAlphabetLetter(str_char, alphabet);
    }
    
    //Procura por letra do alfabeto especificada (versão string)
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
    
    //Converte ArrayList de Strings para vetor de Strings
    private String[] arrayListToVector(List<String> list) {
        final int size = list.size();
        String[] vector = new String[size];
        
        for(int i = 0; i < size; i++) {
            vector[i] = list.get(i);
        }
        
        return vector;
    }
    
    //Retorna AFND criado
    public List<String[]> getNDFA() {
        return this.arrayListNDFA;
    }
}

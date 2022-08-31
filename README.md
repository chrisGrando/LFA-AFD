# AFD
**A**utômato **F**inito **D**eterminístico.

## Descrição
Este é um projeto prático do CCR de **L**inguagens **F**ormais e **A**utômatos (LFA), realizado na **U**niversidade **F**ederal **F**ronteira **S**ul (UFFS).
O objetivo deste trabalho é criar um software que, a partir de um arquivo de entrada, contendo tokens e regras de gramática, gere uma tabela de **A**utômatos **F**initos **D**eterminísticos.

Para uma descrição mais detalhada, leia o arquivo [Projeto.pdf](https://github.com/chrisGrando/LFA-AFD/blob/main/Projeto.pdf).

## Compilar/Executar projeto

### Instalar dependências
Antes de abrir o projeto, é necessário ter instalado alguns softwares no sistema:

- [Liberica JDK 18](https://bell-sw.com/pages/downloads/#/java-18-current) <br>
(Versão modificada do Java JDK 18 usada nesse projeto)
- [Apache NetBeans](https://netbeans.apache.org/download/index.html) <br>
(IDE usada nesse projeto, requer versão 14 ou superior)

### Abrir & Compilar
Supondo que as dependências já estejam corretamente instaladas e a IDE aberta:

1. Clique em *File -> Open Project...*

2. Selecione a pasta do projeto e clique em *Open Project*.

3. (Opcional) Na aba *Projects*, clique com o botão direito em 🍵*LFA*, vá em *Properties -> Run*.
Copie e cole no campo *Arguments* o texto abaixo, depois clique em *Ok*:

> -gui "csv/input.csv" "csv/output.csv"

4. Clique no ícone 🔨 *Build Project* para compilar pela primeira vez.

Se a compilação for bem sucedida, então o projeto já está corretamente configurado e pronto para usar.<br>
Os arquivos compilados estarão disponíveis na pasta *dist*.

### Executar

- Dentro da IDE:<br>
Clique no botão ▶️ *Run Project*.

- Com interface gráfica:<br>
Duplo clique em **LFA.jar**.

- Na linha de comando (com parâmetros):
> java -jar LFA.jar -gui [arquivo de entrada] [arquivo de saída]

Sendo os parâmetros:
- -gui: (Opcional) Força a interface gráfica a ser ativada.
- [arquivo de entrada]: Diretório relativo ou absoluto do arquivo de entrada. Exemplos:
	- *../csv/input.csv*
	- *D:\src\LFA-AFD\csv\input.csv*
- [arquivo de saída]: Diretório relativo ou absoluto do arquivo de saída. Exemplos:
	- *../csv/output.csv*
	- *D:\src\LFA-AFD\csv\output.csv*

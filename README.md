# AFD
**A**utômato **F**inito **D**eterminístico.

**Índice:**
1. [Descrição](#about)
	+ [Progresso](#progress)
2. [Compilar projeto](#project)
	1. [Instalar dependências](#install)
	2. [Abrir & Compilar](#compile)
3. [Executar](#run)
4. [Parâmetros de linha de comando](#args)

## Descrição <a name="about"></a>

Este é um projeto prático do CCR de **L**inguagens **F**ormais e **A**utômatos (LFA), realizado na **U**niversidade **F**ederal **F**ronteira **S**ul (UFFS).
O objetivo deste trabalho é criar um software que, a partir de um arquivo de entrada, contendo tokens e regras de gramática, gere uma tabela de **A**utômatos **F**initos **D**eterminísticos.

Para uma descrição mais detalhada, leia o arquivo [Projeto.pdf](https://github.com/chrisGrando/LFA-AFD/blob/main/Projeto.pdf).

### Progresso <a name="progress"></a>

(✅) AFND <br>
(❌) Determinização <br>
(❌) Minimização

## Compilar projeto <a name="project"></a>

### Instalar dependências <a name="install"></a>
Antes de abrir o projeto, é necessário ter instalado alguns softwares no sistema:

- [Liberica JDK 17](https://bell-sw.com/pages/downloads/#/java-17-lts) <br>
(Versão modificada do Java JDK usada nesse projeto, requer versão 17)
- [Apache NetBeans](https://netbeans.apache.org/download/index.html) <br>
(IDE usada nesse projeto, requer versão 15 ou superior)

> OBS.: Certifique-se que possua o item "JDK 17" listado como plataforma entrando em *Tools -> Java Platforms*.
> Caso não esteja listado, utilize o seguinte guia para adicioná-lo na lista:
>
>[Visão Geral do Suporte a JDK 8 no NetBeans IDE](https://netbeans.apache.org/kb/docs/java/javase-jdk8_pt_BR.html) <br>
>(Este guia está um pouco desatualizado, mas o esquema ainda é o mesmo. Apenas troque o "JKD 8" pelo "JDK 17")

### Abrir & Compilar <a name="compile"></a>
Supondo que as dependências já estejam corretamente instaladas e a IDE aberta:

1. Clique em *File -> Open Project...*

2. Selecione a pasta do projeto e clique em *Open Project*.

3. (Opcional) Na aba *Projects*, clique com o botão direito em 🍵*LFA*, vá em *Properties -> Run*.
Copie e cole no campo *Arguments* o texto abaixo, depois clique em *Ok*:

```
-gui "csv/input_1.csv" "csv/output.csv"
```

4. Clique no ícone 🔨 *Build Project* para compilar pela primeira vez.

Se a compilação for bem sucedida, então o projeto já está corretamente configurado e pronto para usar.<br>
Os arquivos compilados estarão disponíveis na pasta *dist*.

## Executar <a name="run"></a>

- Dentro da IDE:<br>
Clique no botão ▶️ *Run Project*.

- Com interface gráfica:<br>
Duplo clique em **LFA.jar**.

- Na linha de comando (com parâmetros):
```
java -jar LFA.jar -gui [arquivo de entrada] [arquivo de saída]
```

## Parâmetros de linha de comando <a name="args"></a>

- -gui: (Opcional) Força a interface gráfica a ser ativada.
- [arquivo de entrada]: Diretório relativo ou absoluto do arquivo de entrada. Exemplos:
	- *../csv/input.csv*
	- *D:\src\LFA-AFD\csv\input.csv*
- [arquivo de saída]: Diretório relativo ou absoluto do arquivo de saída. Exemplos:
	- *../csv/output.csv*
	- *D:\src\LFA-AFD\csv\output.csv*

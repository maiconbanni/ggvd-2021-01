# ggvd-2021-01
Tarefas da disciplina de Gerência de Grandes Volumes de Dados

## Tarefa 01
#### Definição do Problema

Dado um conjunto de documentos de texto (ou seja, arquivos de texto) de entrada, crie um índice com a lista de ngrams (por exemplo, bigrams) contidos nesses documentos junto com o número de vezes que os ngrams foram encontrados em todos os documentos e a lista de arquivos onde o ngrams aparecem.

#### Entrada

A lista de arquivos é fornecida em um diretório (esse diretório pode conter um número arbitrário de arquivos), por exemplo:

/home/danielcmo/{Arquivo1.txt,Arquivo2.txt,Arquivo3.txt,...}

O diretório deve estar no seu sistema de aquivos local (NÃO USAR HDFS). Alguns arquivos de exemplo estão anexados a esta atividade. Esses arquivos são livros clássicos baixados do site do projeto Gutenberg (https://www.gutenberg.org/).

#### Saída

A saída consiste em um arquivo que contém a lista de ngrams (por exemplo, bigrams) identificados nos documentos de entrada, junto com o número de vezes que o ngram foi encontrado em todos os documentos e a lista de arquivos onde os ngrams foram encontrados. Por exemplo:

um menino 1 Arquivo1.txt
a cela 1 Arquivo3.txt
o cavalo 2 Arquivo1.txt Arquivo2.txt

No exemplo acima, o bigram "o cavalo" foi encontrado 2 vezes no total, e foi encontrado nos arquivos Arquivo1.txt e Arquivo2.txt.

#### Argumentos do  programa

Seu programa receberá 4 argumentos

- args[0]: Valor N do ngram (e.g, 2 para um bigram)
- args[1]: A contagem mínina para ser incluído na saída (e.g., só desejo mostrar ngrams que apareçam mais de 5 vezes)
- args[2]: O diretório onde os arquivos se encontram
- args[3]: O diretório onde os arquivos de saída serão gravados

A ordem dos argumentos é IMPORTANTE!

##### Avisos IMPORTANTES

NÃO incluir nenhuma dependência no seu código a não ser hadoop-core
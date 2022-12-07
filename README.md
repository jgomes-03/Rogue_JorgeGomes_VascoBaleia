# Rogue_JorgeGomes_VascoBaleia

Este repositório apresenta a solução proposta, pelo @MKingPT e pelo @vb-03 para o projeto Rogue-like game, sendo o projeto final da cadeira Programação Orientada a Objetos pertencente à **Licenciatura de Telecomunicações e Informática** no [ISCTE - Instituto Universitário de Lisboa](https://iscte-iul.pt), no ano letivo de 2022/2023.

Para o desenvolvimento do projeto tivemos como base o jogo open-source Rogue-like [asdasd](asdasdasd). Todo o desenvolvimento foi realizado por conta própria, tendo-nos baseado apenas no conceito utilizado no jogo asdasd e não tendo utilizado ou re-utilizado qualquer parte do código disponibilizado pelo mesmo.

## Dependências
De forma a conseguir compilar sem quaisquer problemas, aconselhamos vivamente a seguir as seguintes dependências:

    1. EclipseIDE for Java Developers (v.2022‑12 ou mais recente)[^1]
    2. Git Plugin para EclipseIDE (correspondente à versão instalada)[^2]
    3. Java SE 8 (ou mais recente)[^3]
    4. GraphPack_2022_2023_V1_3[^4]
    
## Como descarregar/instalar?

Se pretender descarregar o projeto para a sua área de trabalho, poderá fazê-lo de duas formas diferentes:

### 1. Diretamente através do EclipseIDE

Passo 1: Abrir o EclipseIDE na sua workspace de trabalho.

![This is an image](/readme-imgs/readme1.png)

Passo 2: Depois de aberto, clique no botão "*Importar projetos...*"

![This is an image](/readme-imgs/readme2.png)

Passo 3: Na janela de import, selecione a opção *"Projetos do Git (com smart import)"* dentro da pasta *"Git"*

![This is an image](/readme-imgs/readme3.png)

Passo 4: Na janela do *Git Source*, insira o link do repositório no campo em *"Localização/URL"*. Os restantes campos serão preenchidos automaticamente conforme as configurações do repositório. Insira as suas credênciais nos campos em *"Autenticação"*
    
    Link para o repositório: https://github.com/MKingPT/Rogue_JorgeGomes_VascoBaleia

![This is an image](/readme-imgs/readme4.png)

Passo 4: Selecione o destino pretendido para o repositório local. Configure as opções disponiveis da forma que achar mais conveniente.

![This is an image](/readme-imgs/readme5.png)

Passo 5: Após selecionar o destino do repositório, e esperar que o Eclipse crie e carregue o repositório, irá obter um resultado semelhante ao abaixo. Se não chegou a este resultado, então o import do repositório não foi concluído. Deverá tentar de novo desde o **Passo 2**

![This is an image](/readme-imgs/readme6.png)


### 2. Através da consola de comandos (CMD ou GitBash)

Passo 1: Através do terminal, escolha o diretório no qual pretende colocar o repositório

Passo 2: Depois de escolher o diretório, insira os comandos abaixo:

```
git clone https://github.com/MKingPT/Rogue_JorgeGomes_VascoBaleia
git pull
```

Passo 3: Criado o repositório, siga todos os passos apresentados a cima (a partir do Passo 1).

**Notas:** 
- Em vez de selecionar a opção *"Projetos do Git"*, selecione a opção *"Projeto de um diretório existente..."* (Passo 3)
- O passo 4 não será executado neste caso.

## Inicializar o jogo

Depois de criado o repositório e adicionado ao EclipseIDE, poderá inicializar o jogo abrindo o ficheiro [MainExample.java](src/pt/iscte/poo/test/MainExample.java) e clicando no Run (botão verde na barra de ferramentas). Isso irá abrir uma janela e o jogo estará desponivel.

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.reismarcelo.grafos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Marcelo dos Reis
 */
public class Grafo {

    private int numVertices;   // No. de vértices 
    private String caminho;

    // Lista de adjacências dos vértices
    private LinkedList<Integer> adj[];

    private int pesos[][];

    private int tmpMatriz[][];
    private LinkedList<Integer> tmpAdj[];
    private int tmpCaminho[];
    private int tmpCc[];

    // Constructor 
    Grafo() {
        numVertices = 0;
        adj = null;
    }

    //=======================================================================
    // 1. verificar se o grafo é completo
    // retorna verdadeiro ou falso
    public boolean completo() {
        boolean cmp = true;

        for (int i = 0; i < numVertices; i++) {
            if (adj[i].size() != (numVertices - 1)) {
                cmp = false;
                break;
            }
        }

        return cmp;
    }

    //=======================================================================
    // 2. completar o grafo
    // completa o grafo com as arestas que faltam
    public void completar() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    adicionarAresta(i, j);
                }
            }
        }
    }

    //=======================================================================
    // 3. busca em largura
    // faz uma busca em largura a partir do vértice v
    // e retorna uma string com o caminho percorrido
    String buscaEmLargura(int v) {
        caminho = "";

        if (numVertices == 0) {
            return caminho;
        }

        boolean visitado[] = new boolean[numVertices];

        LinkedList<Integer> fila = new LinkedList<>();

        visitado[v] = true;
        fila.add(v);

        while (!fila.isEmpty()) {
            v = fila.poll();
            caminho += v + "\t";

            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visitado[n]) {
                    visitado[n] = true;
                    fila.add(n);
                }
            }
        }
        return caminho;
    }

    //=======================================================================
    // 4. busca em profundidade
    // faz uma busca em profundidade a partir do vértice v
    // e retorna uma string com o caminho percorrido
    String buscaEmProfundidade(int v) {
        caminho = "";

        if (numVertices == 0) {
            return caminho;
        }

        boolean visitado[] = new boolean[numVertices];

        buscaEmProfundidadeR(v, visitado);

        return caminho;
    }

    // função auxiliar para busca recursiva
    private void buscaEmProfundidadeR(int v, boolean visitado[]) {
        visitado[v] = true;
        caminho += v + "\t";

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visitado[n]) {
                buscaEmProfundidadeR(n, visitado);
            }
        }
    }

    //=======================================================================
    // 5. número de componentes do grafo
    // retorna o número de componentes
    public int numeroDeComponentes() {
        int id = 0;

        if (numVertices == 0) {
            return 0;
        }

        tmpCc = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            tmpCc[i] = -1;
        }

        for (int v = 0; v < numVertices; v++) {
            if (tmpCc[v] == -1) {
                componentesBuscaEmProfundidadeR(v, id++);
            }
        }

        return id;
    }

    // função auxiliar recursiva para marcar os números de componentes
    private void componentesBuscaEmProfundidadeR(int v, int id) {
        tmpCc[v] = id;

        Iterator<Integer> it = adj[v].listIterator();
        if (it == null) {
            return;
        }

        while (it.hasNext()) {
            Integer n = it.next();
            if (tmpCc[n] == -1) {
                componentesBuscaEmProfundidadeR(n, id);
            }
        }
    }

    public String componentes() {
        int id = 0;
        String componentes = "";

        if (numVertices == 0) {
            return "";
        }

        for (int i = 0; i < numVertices; i++) {
            if (tmpCc[i] > id) {
                id = tmpCc[i];
            }
        }

        for (int j = 0; j <= id; j++) {
            for (int i = 0; i < numVertices; i++) {
                if (tmpCc[i] == j) {
                    componentes += i + "-";
                }
            }
            componentes = componentes.substring(0, componentes.length() - 1);
            componentes += "\t";
        }

        return componentes;
    }

    //=======================================================================
    // 6. retorna o ciclo Hamiltoniano
    // retorna vazio se não existir
    public String cicloHamiltoniano() {
        caminho = "";

        if (numVertices == 0) {
            return caminho;
        }

        if (hamiltoniano()) {
            for (int i = 0; i < numVertices; i++) {
                caminho += tmpCaminho[i] + "\t";
            }

            // Let us print the first vertex again to show the 
            // complete cycle 
            caminho += tmpCaminho[0] + "\t";
        }

        return caminho;
    }

    // função auxiliar que verifica se o novo vértice é adjacente ao anterior
    // para verificar a viabilidade de usar esse vértice
    boolean viavel(int v, int pos) {
        if (tmpMatriz[tmpCaminho[pos - 1]][v] == 0) {
            return false;
        }

        for (int i = 0; i < pos; i++) {
            if (tmpCaminho[i] == v) {
                return false;
            }
        }

        return true;
    }

    // função auxiliar recursiva
    boolean hamiltonianoR(int pos) {
        if (pos == numVertices) {
            if (tmpMatriz[tmpCaminho[pos - 1]][tmpCaminho[0]] == 1) {
                return true;
            } else {
                return false;
            }
        }

        for (int v = 1; v < numVertices; v++) {
            if (viavel(v, pos)) {
                tmpCaminho[pos] = v;

                if (hamiltonianoR(pos + 1) == true) {
                    return true;
                }
                tmpCaminho[pos] = -1;
            }
        }
        return false;
    }

    // função auxiliar que retorna se o grafo é hamiltoniano
    // algoritmo backtracking a partir do vértice 0
    public boolean hamiltoniano() {
        if (numVertices == 0) {
            return false;
        }

        tmpMatriz = new int[numVertices][numVertices];
        tmpCaminho = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                tmpMatriz[i][j] = 0;
            }

            Iterator<Integer> it = adj[i].listIterator();
            while (it.hasNext()) {
                tmpMatriz[i][it.next()] = 1;
            }
        }

        for (int i = 0; i < numVertices; i++) {
            tmpCaminho[i] = -1;
        }

        tmpCaminho[0] = 0;
        if (hamiltonianoR(1) == false) {
            return false;
        }

        return true;
    }

    //=======================================================================
    // 7. retorna se o grafo é euleriano
    // retorna verdadeiro ou falso
    public boolean euleriano() {
        if (numVertices == 0) {
            return false;
        }
        if (conectado() == false) {
            return false;
        }

        int impar = 0;
        for (int i = 0; i < numVertices; i++) {
            if (adj[i].size() % 2 != 0) {
                impar++;
            }
        }

        if (impar > 0) {
            return false;
        }
        return true;
    }

    // retorna uma string com um caminho euleriano
    // ou vazio se não existir
    public String caminhoEuleriano() {
        caminho = "";

        if (numVertices == 0) {
            return caminho;
        }

        Integer u = 0;
        for (int i = 0; i < numVertices; i++) {
            if (adj[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }

        tmpAdj = new LinkedList[numVertices];
        if (numVertices > 0) {
            for (int i = 0; i < numVertices; i++) {
                tmpAdj[i] = (LinkedList<Integer>) adj[i].clone();
            }
        }

        caminhoEulerianoR(u);

        return caminho;
    }

    // função auxiliar caminho euleriano recursiva
    private void caminhoEulerianoR(Integer u) {
        for (int i = 0; i < tmpAdj[u].size(); i++) {
            Integer v = tmpAdj[u].get(i);

            if (proximaArestaViavel(u, v)) {
                caminho += u + "-" + v + "\t";

                tmpAdj[u].remove(v);
                tmpAdj[v].remove(u);
                caminhoEulerianoR(v);
            }
        }
    }

    private boolean proximaArestaViavel(Integer u, Integer v) {
        if (tmpAdj[u].size() == 1) {
            return true;
        }

        boolean[] visitado = new boolean[this.numVertices];
        int count1 = contarBuscaEmProfundidadeR(u, visitado);

        tmpAdj[u].remove(v);
        tmpAdj[v].remove(u);
        visitado = new boolean[this.numVertices];
        int count2 = contarBuscaEmProfundidadeR(u, visitado);

        tmpAdj[u].add(v);
        tmpAdj[v].add(u);
        return (count1 <= count2);
    }

    private int contarBuscaEmProfundidadeR(Integer v, boolean[] visitado) {
        visitado[v] = true;
        int count = 1;

        for (int w : tmpAdj[v]) {
            if (!visitado[w]) {
                count = count + contarBuscaEmProfundidadeR(w, visitado);
            }
        }
        return count;
    }

    //=======================================================================
    // 8. retorna um caminho euleriano se o grafo for unicursal
    // retorna vazio se não for unicursal
    public String unicursal() {
        if ((numVertices > 0) && (conectado())) {

            int impar = 0;
            for (int i = 0; i < numVertices; i++) {
                if (adj[i].size() % 2 != 0) {
                    impar++;
                }
            }

            if (impar == 2) {
                caminhoEuleriano();
                return caminho;
            }
        }
        return "";
    }

    //=======================================================================
    // 9. Retorna as arestas que fazem parte da árvore geradora mínima
    // algoritmo de Prim
    public String arvoreGeradoraMinima() {
        int pai[] = new int[numVertices];
        int chave[] = new int[numVertices];
        Boolean mst[] = new Boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            chave[i] = Integer.MAX_VALUE;
            mst[i] = false;
        }

        int n = numeroDeComponentes();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (tmpCc[j] == i) {
                    chave[j] = 0;
                    pai[j] = -1;

                    int numV = 0;
                    for (int k = 0; k < numVertices; k++) {
                        if (tmpCc[k] == i) {
                            numV++;
                        }
                    }

                    for (int count = 0; count < numV - 1; count++) {
                        int u = menorChave(chave, mst, i);

                        mst[u] = true;

                        for (int v = 0; v < numVertices; v++) {
                            if (tmpCc[v] != i) {
                                continue;
                            }

                            if (pesos[u][v] != 0 && mst[v] == false && pesos[u][v] < chave[v]) {
                                pai[v] = u;
                                chave[v] = pesos[u][v];
                            }
                        }
                    }
                    break;
                }
            }
        }
        return arestasArvore(pai);
    }

    private int menorChave(int chave[], Boolean mst[], int id) {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numVertices; v++) {
            if (tmpCc[v] != id) {
                continue;
            }

            if (mst[v] == false && chave[v] < min) {
                min = chave[v];
                min_index = v;
            }
        }

        return min_index;
    }

    private String arestasArvore(int parent[]) {
        String arvore = "";

        for (int i = 0; i < numVertices; i++) {
            if (parent[i] == -1) {
                continue;
            }

            arvore += parent[i] + "-" + i + ": " + pesos[i][parent[i]] + ": " + (tmpCc[i] + 1) + "\n";
        }

        return arvore;
    }

    //=======================================================================
    // funções básicas
    public int tamanho() {
        return numVertices;
    }

    public void adicionarVertice() {
        LinkedList<Integer> novaAdj[] = new LinkedList[numVertices + 1];
        int novosPesos[][] = new int[numVertices + 1][numVertices + 1];

        if (numVertices > 0) {
            System.arraycopy(adj, 0, novaAdj, 0, numVertices);
        }
        novaAdj[numVertices] = new LinkedList<>();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                novosPesos[i][j] = pesos[i][j];
            }
        }

        adj = novaAdj;
        pesos = novosPesos;
        numVertices++;
    }

    public void removerVertice(int v) {
        numVertices--;

        LinkedList<Integer> novaAdj[] = new LinkedList[numVertices];
        int novosPesos[][] = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            novaAdj[i] = new LinkedList<>();

            Iterator<Integer> it;
            if (i < v) {
                it = adj[i].listIterator();

                for (int j = 0; j < numVertices; j++) {
                    if (j < v) {
                        novosPesos[i][j] = pesos[i][j];
                    } else {
                        novosPesos[i][j] = pesos[i][j + 1];
                    }
                }
            } else {
                it = adj[i + 1].listIterator();

                for (int j = 0; j < numVertices; j++) {
                    if (j < v) {
                        novosPesos[i][j] = pesos[i + 1][j];
                    } else {
                        novosPesos[i][j] = pesos[i + 1][j + 1];
                    }
                }
            }

            while (it.hasNext()) {
                Integer n = it.next();
                if (n < v) {
                    novaAdj[i].add(n);
                } else if (n > v) {
                    novaAdj[i].add(n - 1);
                }
            }
        }
        adj = novaAdj;
        pesos = novosPesos;
    }

    public boolean orientado() {
        boolean naoorientado = true;

        for (int i = 0; i < numVertices; i++) {
            Iterator<Integer> it = adj[i].listIterator();
            while (it.hasNext()) {
                Integer n = it.next();
                if (adj[n].indexOf(i) == -1) {
                    naoorientado = false;
                    break;
                }
            }
        }
        return !naoorientado;
    }

    public boolean pesos() {
        boolean bPesos = false;

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (pesos[i][j] > 1) {
                    bPesos = true;
                    break;
                }
            }
        }
        return bPesos;
    }

    public boolean conectado() {
        boolean visitado[] = new boolean[numVertices];
        int i;
        for (i = 0; i < numVertices; i++) {
            visitado[i] = false;
        }

        for (i = 0; i < numVertices; i++) {
            if (!adj[i].isEmpty()) {
                break;
            }
        }

        if (i == numVertices) {
            return true;
        }

        buscaEmProfundidadeR(i, visitado);

        for (i = 0; i < numVertices; i++) {
            if (visitado[i] == false && adj[i].size() > 0) {
                return false;
            }
        }

        return true;
    }

    public void adicionarAresta(int v1, int v2, boolean bidir) {
        if ((0 <= v1) && (v1 < numVertices) && (0 <= v2) && (v2 < numVertices)) {
            if (adj[v1].indexOf(v2) == -1) {
                pesos[v1][v2] = 1;

                ListIterator<Integer> it = adj[v1].listIterator();
                while (true) {
                    if (it.hasNext() == false) {
                        it.add(v2);
                        break;
                    }

                    Integer v = it.next();
                    if (v > v2) {
                        it.previous();
                        it.add(v2);
                        break;
                    }
                }
            }

            if (bidir) {
                if (adj[v2].indexOf(v1) == -1) {
                    pesos[v2][v1] = 1;

                    ListIterator<Integer> it = adj[v2].listIterator();
                    while (true) {
                        if (it.hasNext() == false) {
                            it.add(v1);
                            break;
                        }

                        Integer v = it.next();
                        if (v > v1) {
                            it.previous();
                            it.add(v1);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void adicionarAresta(int v1, int v2) {
        adicionarAresta(v1, v2, true);
    }

    public void removerAresta(int v1, int v2, boolean bidir) {
        if ((0 <= v1) && (v1 < numVertices) && (0 <= v2) && (v2 < numVertices)) {
            if (adj[v1].indexOf(v2) == -1) {
                adj[v1].remove(v2);
                pesos[v1][v2] = 0;
            }
            if (bidir) {
                if (adj[v2].indexOf(v1) == -1) {
                    adj[v2].remove(v1);
                    pesos[v2][v1] = 0;
                }
            }
        }
    }

    public void removerAresta(int v1, int v2) {
        removerAresta(v1, v2, true);
    }

    public Iterator<Integer> listaAdjacentes(int v) {
        if ((0 <= v) && (v < numVertices)) {
            return adj[v].listIterator();
        }

        return null;
    }

    public int grauSaida(int v) {
        if ((0 <= v) && (v < numVertices)) {
            return adj[v].size();
        } else {
            return -1;
        }
    }

    public int peso(int v1, int v2) {
        return pesos[v1][v2];
    }

    public void peso(int v1, int v2, int peso, boolean bidir) {
        pesos[v1][v2] = peso;
        if (bidir) {
            pesos[v2][v1] = peso;
        }
    }

    public void peso(int v1, int v2, int peso) {
        peso(v1, v2, peso, true);
    }

    boolean vizinho(int v1, int v2) {
        if ((0 <= v1) && (v1 < numVertices)) {
            if (adj[v1].indexOf(v2) == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}

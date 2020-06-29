/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.reismarcelo.grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 *
 * @author Marcelo dos Reis
 */
public class DesenharGrafos extends JPanel implements MouseMotionListener {

    private Grafo grafo = new Grafo();
    private static final int recW = 20;
    private static final int MAX = 100;
    private static final Color[] cores = {Color.red, Color.blue, Color.green,
        Color.cyan, Color.magenta, Color.yellow};

    private Rectangle[] vert = new Rectangle[MAX];
    private int numVertices = 0;
    private int selIndiceVertice = -1;
    private boolean bArraste = false;
    private boolean bBiDir = true;
    private Line2D[] realcado = null;
    private Color[] corRealcado = null;
    private boolean bOrdem = true;
    private int numRealcado = 0;

    public DesenharGrafos() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();

                int indiceVertice = verticeEmXY(x, y);
                if (indiceVertice < 0) // não clicou em um vértice
                {
                    if (selIndiceVertice == -1) {
                        adicionarVertice(x, y);
                    }

                    selIndiceVertice = -1;
                } else if (selIndiceVertice != indiceVertice) {
                    if (selIndiceVertice >= 0) {
                        adicionarAresta(selIndiceVertice,
                                indiceVertice);
                        selIndiceVertice = -1;
                    } else {
                        selIndiceVertice = indiceVertice;
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();

                if (evt.getClickCount() >= 2) {
                    removerVertice(selIndiceVertice);
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                repaint();
                if (bArraste) {
                    selIndiceVertice = -1;
                }
                bArraste = false;
            }
        });

        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean bOrientado = grafo.orientado();
        Graphics2D g2d = ((Graphics2D) g);

        if (numVertices > 0) {
            if (bOrientado) {
                g2d.drawString("Grafo Orientado!", 5, 17);
            } else {
                g2d.drawString("Grafo não Orientado!", 5, 17);
            }
        }

        for (int i = 0; i < numVertices; i++) {
            Iterator<Integer> it = grafo.listaAdjacentes(i);
            if (it == null) {
                continue;
            }

            while (it.hasNext()) {
                Integer n = it.next();
                desenharAresta(g2d, vert[i].x + (recW / 2), vert[i].y + (recW / 2),
                        vert[n].x + (recW / 2), vert[n].y + (recW / 2), bOrientado,
                        (grafo.pesos()) ? grafo.peso(i, n) : 0);

            }
        }

        Stroke olds = g2d.getStroke();
        g2d.setStroke(new BasicStroke(3));
        for (int i = 0; i < numRealcado; i++) {
            g2d.setColor(corRealcado[i]);
            g2d.draw(realcado[i]);
            if (bOrdem) {
                g2d.drawString((i + 1) + "",
                        (int) ((realcado[i].getX1() + realcado[i].getX2()) / 2),
                        (int) ((realcado[i].getY1() + realcado[i].getY2()) / 2));
            }
        }
        g2d.setStroke(olds);
        g2d.setColor(Color.black);

        numRealcado = 0; // desenha só uma vez

        for (int i = 0; i < numVertices; i++) {
            int x = vert[i].x;
            int y = vert[i].y;

            g2d.setColor(Color.white);
            g2d.fillRect(x, y, recW, recW);
            if (i == selIndiceVertice) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.black);
            }

            g2d.draw(vert[i]);

            String s = Integer.toString(i);
            FontMetrics fm = g2d.getFontMetrics();
            int dw = (recW - fm.stringWidth(s)) / 2;
            int dh = (recW - fm.getHeight()) / 2 + fm.getHeight() - 2;

            g2d.drawString(s, x + dw, y + dh);
        }
    }

    public boolean completo() {
        return grafo.completo();
    }

    public void completar() {
        grafo.completar();

        repaint();
    }

    public boolean euleriano() {
        return grafo.euleriano();
    }

    public String caminhoEuleriano() {
        String caminho = grafo.caminhoEuleriano();

        if (!"".equals(caminho)) {
            String[] arestas = caminho.trim().split("\t");

            realcado = new Line2D[arestas.length];
            corRealcado = new Color[arestas.length];
            bOrdem = true;
                    
            int i = 0;
            for (String aresta : arestas) {
                String[] vertices = aresta.trim().split("-");

                int v1 = Integer.parseInt(vertices[0]);
                int v2 = Integer.parseInt(vertices[1]);
                realcado[i] = new Line2D.Double(vert[v1].x + (recW / 2),
                        vert[v1].y + (recW / 2), vert[v2].x + (recW / 2),
                        vert[v2].y + (recW / 2));
                corRealcado[i] = Color.red;

                i++;
            }
            numRealcado = i;
        }
        repaint();

        return caminho;
    }

    String cicloHamiltoniano() {
        String caminho = grafo.cicloHamiltoniano();

        if (!"".equals(caminho)) {
            String[] vertices = caminho.trim().split("\t");

            realcado = new Line2D[vertices.length - 1];
            corRealcado = new Color[vertices.length - 1];
            bOrdem = true;

            int i = 0;
            int v1 = -1;
            for (String vertice : vertices) {
                int v2 = Integer.parseInt(vertice);

                if (v1 == -1) {
                    v1 = v2;
                    continue;
                }

                realcado[i] = new Line2D.Double(vert[v1].x + (recW / 2),
                        vert[v1].y + (recW / 2), vert[v2].x + (recW / 2),
                        vert[v2].y + (recW / 2));
                corRealcado[i] = Color.red;

                i++;
                v1 = v2;
            }
            numRealcado = i;
        }
        repaint();

        return caminho;
    }

    public String unicursal() {
        String caminho = grafo.unicursal();

        if (!"".equals(caminho)) {
            String[] arestas = caminho.trim().split("\t");

            realcado = new Line2D[arestas.length];
            corRealcado = new Color[arestas.length];
            bOrdem = true;

            int i = 0;
            for (String aresta : arestas) {
                String[] vertices = aresta.trim().split("-");

                int v1 = Integer.parseInt(vertices[0]);
                int v2 = Integer.parseInt(vertices[1]);
                realcado[i] = new Line2D.Double(vert[v1].x + (recW / 2),
                        vert[v1].y + (recW / 2), vert[v2].x + (recW / 2),
                        vert[v2].y + (recW / 2));
                corRealcado[i] = Color.red;

                i++;
            }
            numRealcado = i;
        }
        repaint();

        return caminho;
    }

    public String arvoreGeradoraMinima() {
        String caminho = grafo.arvoreGeradoraMinima();

        if (!"".equals(caminho)) {
            String[] arestas = caminho.trim().split("\n");

            realcado = new Line2D[arestas.length];
            corRealcado = new Color[arestas.length];
            bOrdem = false;

            int i = 0;
            for (String aresta : arestas) {
                String[] pesos = aresta.trim().split(":");
                String[] vertices = pesos[0].trim().split("-");
                int id = Integer.parseInt(pesos[2].trim());

                int v1 = Integer.parseInt(vertices[0]);
                int v2 = Integer.parseInt(vertices[1]);
                realcado[i] = new Line2D.Double(vert[v1].x + (recW / 2),
                        vert[v1].y + (recW / 2), vert[v2].x + (recW / 2),
                        vert[v2].y + (recW / 2));
                corRealcado[i] = cores[id % cores.length];

                i++;
            }
            numRealcado = i;
        }
        repaint();

        return caminho;
    }

    public int verticeEmXY(int x, int y) {
        for (int i = 0; i < numVertices; i++) {
            if (vert[i].contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    public void adicionarVertice(int x, int y) {
        if (numVertices < MAX) {
            vert[numVertices] = new Rectangle(x, y, recW, recW);
            grafo.adicionarVertice();

            selIndiceVertice = numVertices;

            numVertices++;

            repaint();
        }
    }

    public void removerVertice(int n) {
        if (n < 0 || n >= numVertices) {
            return;
        }

        grafo.removerVertice(n);
        numVertices--;
        for (int i = n; i < numVertices; i++) {
            vert[i] = vert[i + 1];

            // resolver o problema de apagar arestas
        }
        selIndiceVertice = -1;

        repaint();
    }

    public void adicionarAresta(int v1, int v2) {
        grafo.adicionarAresta(v1, v2, bBiDir);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (verticeEmXY(x, y) >= 0) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (selIndiceVertice >= 0) {
            bArraste = true;
            Graphics graphics = getGraphics();
            graphics.setXORMode(getBackground());

            ((Graphics2D) graphics).draw(vert[selIndiceVertice]);

            vert[selIndiceVertice].x = x;
            vert[selIndiceVertice].y = y;

            ((Graphics2D) graphics).draw(vert[selIndiceVertice]);

            graphics.dispose();
        }
    }

    public void limpar() {
        grafo = new Grafo();
        numVertices = 0;
        selIndiceVertice = -1;
        repaint();
    }

    public int numeroDeComponentes() {
        int n = grafo.numeroDeComponentes();
        String res = grafo.componentes();

        if (!"".equals(res)) {
            String[] componentes = res.trim().split("\t");

            numRealcado = 0;
            for (String componente : componentes) {
                String[] vertices = componente.trim().split("-");
                for (String vertice : vertices) {
                    int v = Integer.parseInt(vertice);

                    Iterator<Integer> it = grafo.listaAdjacentes(v);
                    if (it != null) {
                        while (it.hasNext()) {
                            it.next();

                            numRealcado++;
                        }
                    }
                }
            }

            realcado = new Line2D[numRealcado];
            corRealcado = new Color[numRealcado];
            bOrdem = false;

            int i = 0;
            int id = 0;
            for (String componente : componentes) {
                String[] vertices = componente.trim().split("-");
                for (String vertice : vertices) {
                    int v1 = Integer.parseInt(vertice);

                    Iterator<Integer> it = grafo.listaAdjacentes(v1);
                    if (it != null) {
                        while (it.hasNext()) {
                            int v2 = it.next();

                            realcado[i] = new Line2D.Double(vert[v1].x + (recW / 2),
                                    vert[v1].y + (recW / 2), vert[v2].x + (recW / 2),
                                    vert[v2].y + (recW / 2));
                            corRealcado[i] = cores[id % cores.length];

                            i++;
                        }
                    }
                }
                id++;
            }

            numRealcado = i;
        }
        repaint();

        return n;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    private void desenharAresta(Graphics2D g2d, int x1, int y1, int x2, int y2, boolean seta, int peso) {
        g2d.drawLine(x1, y1, x2, y2);

        if (seta) {
            double angle = Math.atan2(y2 - y1, x2 - x1);        //find angle of line

            int arrowHeight = 9;                 // change as seen fit
            int halfArrowWidth = 5;              // this too
            int distance = 25;

            int x = (x1 + x2) / 2;
            int y = (y1 + y2) / 2;

            x += (int) (distance * Math.cos(angle));
            y += (int) (distance * Math.sin(angle));
            int xbase = (int) (x - arrowHeight * Math.cos(angle));
            int ybase = (int) (y - arrowHeight * Math.sin(angle));

            int endx1 = (int) (xbase - halfArrowWidth * Math.cos(angle - Math.PI / 2));
            int endy1 = (int) (ybase - halfArrowWidth * Math.sin(angle - Math.PI / 2));

            int endx2 = (int) (xbase + halfArrowWidth * Math.cos(angle - Math.PI / 2));
            int endy2 = (int) (ybase + halfArrowWidth * Math.sin(angle - Math.PI / 2));

            g2d.drawLine(endx1, endy1, x, y);
            g2d.drawLine(endx2, endy2, x, y);
            g2d.drawLine(endx2, endy2, endx1, endy1);

            if (peso > 0) {
                Color oldc = g2d.getColor();
                g2d.setColor(Color.red);
                g2d.drawString(peso + "", x, y);
                g2d.setColor(oldc);
            }
        } else {
            if (peso > 0) {
                int x = (x1 + x2) / 2;
                int y = (y1 + y2) / 2;

                Color oldc = g2d.getColor();
                g2d.setColor(Color.red);
                g2d.drawString(peso + "", x, y);
                g2d.setColor(oldc);
            }
        }
    }

    public boolean salvar(String filename) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);
            for (int i = 0; i < numVertices; i++) {
                String line = i + "," + vert[i].x + "," + vert[i].y + ": ";

                Iterator<Integer> it = grafo.listaAdjacentes(i);
                if (it != null) {
                    while (it.hasNext()) {
                        Integer n = it.next();
                        line = line + n + " ";
                    }
                }
                fw.write(line);
                fw.write(System.lineSeparator());
            }

            if (grafo.pesos()) {
                fw.write("Pesos:");
                fw.write(System.lineSeparator());
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        fw.write(grafo.peso(i, j) + " ");
                    }
                    fw.write(System.lineSeparator());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public boolean carregar(String filename) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filename));

            limpar();

            boolean bOldBiDir = bBiDir;
            bBiDir = false;

            for (String line : allLines) {
                line = line.trim();
                if ("Pesos:".equals(line)) {
                    break;
                }

                String[] stringValidTokens = line.split(":");
                if (stringValidTokens.length >= 1) {
                    String[] coord = stringValidTokens[0].trim().split(",");

                    adicionarVertice(Integer.valueOf(coord[1].trim()), Integer.valueOf(coord[2].trim()));
                }
            }

            int v1 = 0;
            boolean bPesos = false;
            for (String line : allLines) {
                line = line.trim();
                if ("Pesos:".equals(line)) {
                    bPesos = true;
                    v1 = 0;
                    continue;
                }

                if (!bPesos) {
                    String[] stringValidTokens = line.split(":");

                    if (stringValidTokens.length > 1) {
                        String[] arestas = stringValidTokens[1].trim().split(" ");

                        for (String v2 : arestas) {
                            adicionarAresta(v1, Integer.valueOf(v2.trim()));
                        }
                    }
                } else {
                    String[] pesos = line.split(" ");

                    int v2 = 0;
                    for (String peso : pesos) {
                        grafo.peso(v1, v2, Integer.valueOf(peso.trim()));
                        v2++;
                    }
                }
                v1++;
            }

            bBiDir = bOldBiDir;
            selIndiceVertice = -1;
            bArraste = false;
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    // busca em profundidade a partir do vértice selecionado
    public String buscaEmProfundidade() {
        int v = 0;

        if (selIndiceVertice != -1) {
            v = selIndiceVertice;
        }

        return v + "\n" + grafo.buscaEmProfundidade(v);
    }

    // busca em largura a partir do vértice selecionado
    public String buscaEmLargura() {
        int v = 0;

        if (selIndiceVertice != -1) {
            v = selIndiceVertice;
        }

        return v + "\n" + grafo.buscaEmLargura(v);
    }

    public void biDir(boolean b) {
        bBiDir = b;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.reismarcelo.grafos;

import java.awt.event.ItemEvent;
import java.io.File;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcelo dos Reis
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        bBiDir = new javax.swing.JToggleButton();
        bPeso = new javax.swing.JButton();
        panel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taRes = new javax.swing.JTextArea();
        grafos = new DesenharGrafos();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miNovo = new javax.swing.JMenuItem();
        miAbrir = new javax.swing.JMenuItem();
        miSalvar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miDados = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miCompleto = new javax.swing.JMenuItem();
        miCompletar = new javax.swing.JMenuItem();
        miBuscaEmLargura = new javax.swing.JMenuItem();
        miBuscaEmProfundidade = new javax.swing.JMenuItem();
        miNumerodeComponentes = new javax.swing.JMenuItem();
        miHamiltoniano = new javax.swing.JMenuItem();
        miEuleriano = new javax.swing.JMenuItem();
        miUnicursal = new javax.swing.JMenuItem();
        miArvoreGeradoraMinima = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Grafos");
        setName("main"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        panel1.setMaximumSize(new java.awt.Dimension(32767, 30));
        panel1.setMinimumSize(new java.awt.Dimension(100, 30));
        panel1.setPreferredSize(new java.awt.Dimension(755, 30));

        bBiDir.setSelected(true);
        bBiDir.setText("Aresta Bi-Direcional");
        bBiDir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bBiDirItemStateChanged(evt);
            }
        });

        bPeso.setText("Pesos das Arestas");
        bPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bBiDir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bPeso)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBiDir)
                    .addComponent(bPeso))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        getContentPane().add(panel1, java.awt.BorderLayout.PAGE_START);

        panel3.setLayout(new java.awt.BorderLayout());

        taRes.setEditable(false);
        taRes.setColumns(60);
        taRes.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        taRes.setLineWrap(true);
        taRes.setRows(5);
        taRes.setTabSize(4);
        taRes.setWrapStyleWord(true);
        taRes.setMinimumSize(new java.awt.Dimension(300, 21));
        jScrollPane1.setViewportView(taRes);

        panel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panel3, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout grafosLayout = new javax.swing.GroupLayout(grafos);
        grafos.setLayout(grafosLayout);
        grafosLayout.setHorizontalGroup(
            grafosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );
        grafosLayout.setVerticalGroup(
            grafosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );

        getContentPane().add(grafos, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Arquivo");

        miNovo.setText("Novo");
        miNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNovoActionPerformed(evt);
            }
        });
        jMenu1.add(miNovo);

        miAbrir.setText("Abrir");
        miAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(miAbrir);

        miSalvar.setText("Salvar");
        miSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(miSalvar);
        jMenu1.add(jSeparator1);

        miDados.setText("Dados");
        miDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDadosActionPerformed(evt);
            }
        });
        jMenu1.add(miDados);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Lista");

        miCompleto.setText("1. É completo?");
        miCompleto.setToolTipText("Verificar se o grafo é completo");
        miCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCompletoActionPerformed(evt);
            }
        });
        jMenu2.add(miCompleto);

        miCompletar.setText("2. Completar");
        miCompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCompletarActionPerformed(evt);
            }
        });
        jMenu2.add(miCompletar);

        miBuscaEmLargura.setText("3. Busca em Largura");
        miBuscaEmLargura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBuscaEmLarguraActionPerformed(evt);
            }
        });
        jMenu2.add(miBuscaEmLargura);

        miBuscaEmProfundidade.setText("4. Busca em Profundidade");
        miBuscaEmProfundidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBuscaEmProfundidadeActionPerformed(evt);
            }
        });
        jMenu2.add(miBuscaEmProfundidade);

        miNumerodeComponentes.setText("5. Número de Componentes");
        miNumerodeComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNumerodeComponentesActionPerformed(evt);
            }
        });
        jMenu2.add(miNumerodeComponentes);

        miHamiltoniano.setText("6. É Hamiltoniano?");
        miHamiltoniano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miHamiltonianoActionPerformed(evt);
            }
        });
        jMenu2.add(miHamiltoniano);

        miEuleriano.setText("7. É Euleriano?");
        miEuleriano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEulerianoActionPerformed(evt);
            }
        });
        jMenu2.add(miEuleriano);

        miUnicursal.setText("8. É Unicursal?");
        miUnicursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUnicursalActionPerformed(evt);
            }
        });
        jMenu2.add(miUnicursal);

        miArvoreGeradoraMinima.setText("9. Árvore Geradora Mínima");
        miArvoreGeradoraMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miArvoreGeradoraMinimaActionPerformed(evt);
            }
        });
        jMenu2.add(miArvoreGeradoraMinima);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNovoActionPerformed
        ((DesenharGrafos) grafos).limpar();
        taRes.setText("");
    }//GEN-LAST:event_miNovoActionPerformed

    private void miCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCompletoActionPerformed
        boolean completo = ((DesenharGrafos) grafos).completo();
        taRes.append("-----------------\n");
        if (completo) {
            taRes.append("É completo!\n");
        } else {
            taRes.append("Não é completo!\n");
        }
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miCompletoActionPerformed

    private void miCompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCompletarActionPerformed
        ((DesenharGrafos) grafos).completar();
    }//GEN-LAST:event_miCompletarActionPerformed

    private void miNumerodeComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNumerodeComponentesActionPerformed
        int n = ((DesenharGrafos) grafos).numeroDeComponentes();
        taRes.append("-----------------\n");
        taRes.append("Tem " + n + " componente(s)!\n");
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miNumerodeComponentesActionPerformed

    private void miDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDadosActionPerformed
        Grafo grafo = ((DesenharGrafos) grafos).getGrafo();
        int tam = grafo.tamanho();

        taRes.append("-----------------\n");
        taRes.append("Arestas: adjacentes\n");
        for (int i = 0; i < tam; i++) {
            taRes.append(i + ": ");
            Iterator<Integer> it = grafo.listaAdjacentes(i);
            if (it != null) {
                while (it.hasNext()) {
                    Integer n = it.next();
                    taRes.append(n + "\t");
                }
            }
            taRes.append("\n");
        }

        taRes.append("Pesos das Arestas:\n");
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                taRes.append(grafo.peso(i, j) + "\t");
            }
            taRes.append("\n");
        }
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miDadosActionPerformed

    private void miHamiltonianoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miHamiltonianoActionPerformed
        String caminho = ((DesenharGrafos) grafos).cicloHamiltoniano();

        taRes.append("-----------------\n");
        if (!"".equals(caminho)) {
            taRes.append("É Hamiltoniano!\n");

            taRes.append(caminho + "\n");
        } else {
            taRes.append("Não é Hamiltoniano!\n");
        }
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miHamiltonianoActionPerformed

    private void miEulerianoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEulerianoActionPerformed
        boolean euleriano = ((DesenharGrafos) grafos).euleriano();
        taRes.append("-----------------\n");
        if (euleriano) {
            taRes.append("É Euleriano!\n");

            taRes.append(((DesenharGrafos) grafos).caminhoEuleriano() + "\n");
        } else {
            taRes.append("Não é Euleriano!\n");
        }
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miEulerianoActionPerformed

    private void miSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSalvarActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Salvar");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            ((DesenharGrafos) grafos).salvar(fileToSave.getAbsolutePath());
        }
    }//GEN-LAST:event_miSalvarActionPerformed

    private void miAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAbrirActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Carregar");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            ((DesenharGrafos) grafos).carregar(selectedFile.getAbsolutePath());
            taRes.setText("");
        }
    }//GEN-LAST:event_miAbrirActionPerformed

    private void bBiDirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bBiDirItemStateChanged
        if (((ItemEvent) evt).getStateChange() == ItemEvent.SELECTED) {
            ((DesenharGrafos) grafos).biDir(true);
        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
            ((DesenharGrafos) grafos).biDir(false);
        }
    }//GEN-LAST:event_bBiDirItemStateChanged

    private void miBuscaEmLarguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBuscaEmLarguraActionPerformed
        String caminho = ((DesenharGrafos) grafos).buscaEmLargura();
        taRes.append("-----------------\n");
        taRes.append("Busca em Largura: a partir do vértice " + caminho + "\n");
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miBuscaEmLarguraActionPerformed

    private void miBuscaEmProfundidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBuscaEmProfundidadeActionPerformed
        String caminho = ((DesenharGrafos) grafos).buscaEmProfundidade();
        taRes.append("-----------------\n");
        taRes.append("Busca em Profundidade: a partir do vértice " + caminho + "\n");
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miBuscaEmProfundidadeActionPerformed

    private void miUnicursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUnicursalActionPerformed
        String caminho = ((DesenharGrafos) grafos).unicursal();

        taRes.append("-----------------\n");
        if (!"".equals(caminho)) {
            taRes.append("É unicursal!\n");

            taRes.append(caminho + "\n");
        } else {
            taRes.append("Não é unicursal!\n");
        }
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miUnicursalActionPerformed

    private void bPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPesoActionPerformed
        Grafo grafo = ((DesenharGrafos) grafos).getGrafo();
        int tam = grafo.tamanho();
        boolean orientado = grafo.orientado();
        PesosArestas pesos = new PesosArestas(this);

        JTable tPesos = pesos.jArestas;

        DefaultTableModel modelo = (DefaultTableModel) tPesos.getModel();

        if (orientado) {
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    int peso = grafo.peso(i, j);
                    if (peso > 0) {
                        modelo.addRow(new Object[]{i + "-" + j, peso});
                    }
                }
            }
        } else {
            for (int i = 0; i < tam; i++) {
                for (int j = i; j < tam; j++) {
                    int peso = grafo.peso(i, j);
                    if (peso > 0) {
                        modelo.addRow(new Object[]{i + "-" + j, peso});
                    }
                }
            }
        }

        int r = pesos.showDialog();

        if (r == 0) {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String aresta = (String) modelo.getValueAt(i, 0);
                int peso = (int) modelo.getValueAt(i, 1);

                String vertices[] = aresta.split("-");
                int v1 = Integer.valueOf(vertices[0]);
                int v2 = Integer.valueOf(vertices[1]);

                grafo.peso(v1, v2, peso, !orientado);
            }
        }
        repaint();
    }//GEN-LAST:event_bPesoActionPerformed

    private void miArvoreGeradoraMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miArvoreGeradoraMinimaActionPerformed
        String arvore = ((DesenharGrafos) grafos).arvoreGeradoraMinima();

        taRes.append("-----------------\n");
        taRes.append("Árvore geradora mínima:\n");
        taRes.append("aresta: peso: componente\n");
        taRes.append(arvore);
        taRes.append("-----------------\n");
    }//GEN-LAST:event_miArvoreGeradoraMinimaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bBiDir;
    private javax.swing.JButton bPeso;
    private javax.swing.JPanel grafos;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem miAbrir;
    private javax.swing.JMenuItem miArvoreGeradoraMinima;
    private javax.swing.JMenuItem miBuscaEmLargura;
    private javax.swing.JMenuItem miBuscaEmProfundidade;
    private javax.swing.JMenuItem miCompletar;
    private javax.swing.JMenuItem miCompleto;
    private javax.swing.JMenuItem miDados;
    private javax.swing.JMenuItem miEuleriano;
    private javax.swing.JMenuItem miHamiltoniano;
    private javax.swing.JMenuItem miNovo;
    private javax.swing.JMenuItem miNumerodeComponentes;
    private javax.swing.JMenuItem miSalvar;
    private javax.swing.JMenuItem miUnicursal;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel3;
    private javax.swing.JTextArea taRes;
    // End of variables declaration//GEN-END:variables
}

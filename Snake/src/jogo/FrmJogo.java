/*
 * FrmJogo.java
 *
 * Created on 7 de Agosto de 2008, 09:51
 */
package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Glauco
 */
public class FrmJogo extends javax.swing.JFrame implements Runnable {
    private boolean esquerdo = false;
    private boolean direito = false;
    private boolean cima = false;
    private boolean baixo = false;
    private boolean reiniciar = false;
    
    private boolean rigidFrame, drawGrid;
    private int qntX, qntY, delay, tamCubo;
    /**
     * Creates new form FrmJogo
     */
    public FrmJogo() {
        initComponents();
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }
    
    public FrmJogo(boolean rigidFrame, boolean drawGrid, int qntX, int qntY, int delay, int tamCubo) {
        this.rigidFrame = rigidFrame;
        this.drawGrid = drawGrid;
        this.qntX = qntX;
        this.qntY = qntY;
        this.delay = delay;
        this.tamCubo = tamCubo;
        
        initComponents();
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdo = true;
        }

        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            direito = true;
        }
        
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            cima = true;
        }
        
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            baixo = true;
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_R)
            reiniciar = true;
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdo = false;
        }

        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            direito = false;
        }
        
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            cima = false;
        }
        
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            baixo = false;
        }

        if(evt.getKeyCode() == KeyEvent.VK_R)
            reiniciar = false;  
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmJogo().setVisible(true);
//            }
//        });
//    } 

    public void run() {
        BufferStrategy buffer = getBufferStrategy();
        Graphics bg;

        GameSnake g = new GameSnake(rigidFrame, drawGrid, qntX, qntY, delay, tamCubo);
        g.setTela(this);
        g.initGame();
       
        while (true) {
            //Aloca o Graphics
            bg = buffer.getDrawGraphics();

            bg.setFont(new Font("Dialog",Font.ITALIC,25));
            
            g.upDate(bg);
            g.setPlayerActions(direito, esquerdo, cima, baixo, reiniciar);

            //Libera o Graphics
            bg.dispose();
            //Mostra o desenho
            buffer.show();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

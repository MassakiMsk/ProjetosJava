/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 1545 IRON V4
 */
public class Frame{
    private int x, y, qntX, qntY, tamCubo, espessura, altura, largura;
    private Color colorFrame, colorGrid;
    private boolean drawGrid;
    
    public Frame(int x, int y, int qntX, int qntY, int tamCubo, Color colorFrame, Color colorGrid, boolean drawGrid) {
        this.espessura = 5;
        this.x = x - 1;
        this.y = y - 1;
        this.qntX = qntX;
        this.qntY = qntY;
        this.tamCubo = tamCubo;
        this.colorFrame = colorFrame;
        this.colorGrid = colorGrid;
        this.drawGrid = drawGrid;
        largura = qntX * tamCubo + 1;
        altura = qntY * tamCubo + 1;
    }

    public void desenhar(Graphics g) {
        g.setColor(colorFrame);
        for(int i = 0; i < espessura; i++) {
            g.drawRect(x - i, y - i, largura + 2 * i, altura + 2 * i);
        }
        if(drawGrid) {
            g.setColor(colorGrid);
            for(int i = 1; i < qntX; i++) {
                g.drawLine(x + tamCubo * i, y + 1, x + tamCubo * i, y + altura - 1);
            }
            for(int i = 1; i < qntY; i++) {
                g.drawLine(x + 1, y + tamCubo * i, x + largura - 1, y + tamCubo * i);
            }
        }
    }
}

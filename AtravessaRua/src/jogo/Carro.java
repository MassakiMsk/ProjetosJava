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
public class Carro extends Base {
    public Carro(int x, int y, int largura, int altura, Color color) {
        super(x, y, largura, altura, color);
    }

    @Override
    public void desenhar(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, largura, altura);
    }
}

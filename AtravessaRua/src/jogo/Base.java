/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 *
 * @author Abutua
 */
public abstract class Base {

    protected int x;
    protected int y;
    protected int incX = 1;
    protected int incY = 0;
    protected int largura;
    protected int altura;
    protected Color color;
    protected Rectangle rect;

    public Base(int x, int y, int largura, int altura, Color color) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.color = color;
        incX = 0;
        incY = 0;
        rect = new Rectangle(x, y, largura, altura);

    }

    public void mover() {
        this.x = x + incX;
        this.y = y + incY;
        attRect();
    }
    
    public void attRect() {
        rect.x = x;
        rect.y = y;
    }

    public boolean colisaoCom(Base b) {
        if (this.getClass().equals(b.getClass())) {
            return false;
        } else {
            return this.rect.intersects(b.rect);
        }
    }

    public abstract void desenhar(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIncX() {
        return incX;
    }

    public void setIncX(int incX) {
        this.incX = incX;
    }

    public int getIncY() {
        return incY;
    }

    public void setIncY(int incY) {
        this.incY = incY;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

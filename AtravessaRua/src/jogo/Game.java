/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Abutua
 */
public class Game  {
    
    private ArrayList<Base> objetos;
    private ArrayList <Base> lixo;
    private Base player;
    private int placar;
    private int largura;
    private int altura;
    private boolean fimDeJogo;
    private boolean vitoria;
    
    private int velCarro;
    private int alturaRetangulo;
    private int espacoRetangulo;
    private int tamPlayer;
    private boolean andou;

    public Game(FrmJogo j) {
        objetos = new ArrayList<Base>();
        lixo = new ArrayList<Base>();
        placar = 0;
        velCarro = 1;
        alturaRetangulo = 30;
        espacoRetangulo = 5;
        fimDeJogo = false;
        vitoria = false;
        andou = false;
        tamPlayer = alturaRetangulo;
        largura = 650;
        altura = 200 + (alturaRetangulo + espacoRetangulo) * 6;
        j.setSize(largura, altura);
    }
    
    public void initGame() {
        instanciaObjetos();
        player = new Player((largura - tamPlayer) / 2, 100 + (alturaRetangulo + espacoRetangulo) * 5, tamPlayer, tamPlayer, Color.WHITE);
        add(player);
    }
    
    private void instanciaObjetos() {
        add(new Calcada(0, 100 + (alturaRetangulo + espacoRetangulo) * 0, largura, alturaRetangulo, Color.BLUE));
        
        instanciaCarro( 50, 100 + (alturaRetangulo + espacoRetangulo) * 1, 120);
        instanciaCarro( 350, 100 + (alturaRetangulo + espacoRetangulo) * 1, 220);
        
        instanciaCarro( 130, 100 + (alturaRetangulo + espacoRetangulo) * 2, 300);
        
        instanciaCarro( 60, 100 + (alturaRetangulo + espacoRetangulo) * 3, 150);
        instanciaCarro( 450, 100 + (alturaRetangulo + espacoRetangulo) * 3, 150);
        
        instanciaCarro( 180, 100 + (alturaRetangulo + espacoRetangulo) * 4, 120);
        instanciaCarro( 450, 100 + (alturaRetangulo + espacoRetangulo) * 4, 120);
        
        add(new Calcada(0, 100 + (alturaRetangulo + espacoRetangulo) * 5, largura, alturaRetangulo, Color.BLUE));
    }
    
    private void instanciaCarro(int x, int y, int largura) {
        Base carro = new Carro(x, y, largura, alturaRetangulo, Color.BLUE);
        carro.setIncX(velCarro);
        add(carro);
    }
    
    public void upDate(Graphics bg) {
        limpaTela(bg);
        
        verificaVitoria();
        moverTodos();
        desenharPlacar(bg);
        desenharTodos(bg);
        verificarColisaoComGame();
        verificarColisaoComPlayer();
        verificarFim();
    }
    
    public void add(Base b) {
        objetos.add(b);
    }

    public void setPlayerActions(boolean pula) {
        if(pula) {
            if(!andou) {
                player.setY(player.getY() - (alturaRetangulo + espacoRetangulo));
                player.attRect();
                andou = true;
            }
        }
        else
            andou = false;
    }

    private void moverTodos() {
        for (Base b : objetos) {
            b.mover();
        }
    }

    private void desenharTodos(Graphics bg) {
        for (Base b : objetos) {
            b.desenhar(bg);
        }
    }

    private void verificarColisaoComPlayer() {
        boolean aux = false;
        for(Base b: objetos) {
            if(player.colisaoCom(b)) {
                verificaPlataforma(b);
                player.setIncX(b.getIncX());
                aux = true;
            }
        }
        if(!aux) {
            fimDeJogo = true;
        }
    }
    
    private void verificaVitoria() {
        if(player.getY() == 100) {
            vitoria = true;
        }
    }
    
    private void verificaPlataforma(Base b) {
        if(!(player.getX() >= b.getX() && player.getX() + player.getLargura() <= b.getX() + b.getLargura())) {
            fimDeJogo = true;
        }
    }
    
    private void verificarColisaoComGame() {
        for(Base b: objetos) {
            if(b instanceof Carro) {
                if(b.getX() <= 0 || b.getX() + b.getLargura() >= largura) {
                    for(Base aux: objetos) {
                        if(!(aux instanceof Player) && aux != b) {
                            if(aux.getY() == b.getY()) {
                                aux.setIncX(-aux.getIncX());
                            }
                        }
                    }
                    b.setIncX(-b.getIncX());
                }
            }
        }
    }

    private void desenharPlacar(Graphics bg) {
        bg.setColor(Color.BLACK);
        bg.drawString("Placar: " + placar, 100, 70);
    }

    private void limpaTela(Graphics bg) {
        bg.setColor(Color.GREEN);
        bg.fillRect(0, 0, largura, altura);      
    }
    
    private void verificarFim() {
        if(vitoria) {
            vitoria = false;
            placar++;
            velCarro *= -1;
            objetos.clear();
            lixo.clear();
            initGame();
        }
        else if(fimDeJogo) {
            fimDeJogo = false;
            placar = 0;
            objetos.clear();
            lixo.clear();
            initGame();
        }
        else {            
            objetos.removeAll(lixo);
            lixo.clear();
        }
    }
    
    public Base getPlayer() {
        return player;
    }

    public void setPlayer(Base player) {
        this.player = player;
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
}

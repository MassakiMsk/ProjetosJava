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
import javax.swing.JFrame;

/**
 *
 * @author 1545 IRON V4
 */
public class GameSnake {
    private int xInicial = 50;
    private int yInicial = 50;
    private int tamCubo = 20;
    private int qntX = 30;
    private int qntY = 30;
    private int delay = 200;
    private boolean rigidFrame = true;
    private boolean drawGrid = true;
    
    private ArrayList<Base> corpo = new ArrayList<Base>();
    private Base player;
    private Base fruta;
    private Frame frame;
    private int largura = tamCubo * qntX;
    private int altura = tamCubo * qntY;
    private int tamInicial = qntX / 5;
    private int direcao = 0;
    private long ultimoMov;
    private int placar = 0;
    private boolean comeu = false;
    private boolean fimDeJogo= false;
    
    public GameSnake() {
        frame = new Frame(xInicial, yInicial, qntX, qntY, tamCubo, Color.RED, Color.BLACK, drawGrid);
    }
    
    public GameSnake(boolean rigidFrame, boolean drawGrid, int qntX, int qntY, int delay, int tamCubo) {
        this.rigidFrame = rigidFrame;
        this.drawGrid = drawGrid;
        this.qntX = qntX;
        this.qntY = qntY;
        this.delay = delay;
        this.tamCubo = tamCubo;
        
        largura = tamCubo * qntX;
        altura = tamCubo * qntY;
        tamInicial = qntX / 5;
        frame = new Frame(xInicial, yInicial, qntX, qntY, tamCubo, Color.RED, Color.BLACK, drawGrid);
    }
    
    public void setTela(FrmJogo j) {
        j.setSize(largura + xInicial * 2, altura + yInicial + xInicial);
    }
    
    public void initGame() {
        instanciaPlayer();
        instanciaFruta();
        placar = 0;
        ultimoMov = System.currentTimeMillis();
    }
    
    public void add(Base b) {
        corpo.add(b);
    }
    
    private void instanciaPlayer() {
        player = new Cubo((qntX / 2 + (tamInicial / 2)) * tamCubo + xInicial, (qntY / 2) * tamCubo + yInicial, tamCubo, Color.BLACK);
        add(player);
        
        for(int i = 1; i < tamInicial; i++) {
            Base b = new Cubo((qntX / 2 + (tamInicial / 2) - i) * tamCubo + xInicial, (qntY / 2) * tamCubo + yInicial, tamCubo, Color.BLACK);
            add(b);
        }
    }
    
    private void instanciaFruta() {
        int posX = 0, posY = 0;
        boolean aux = true;
        while(aux) {
            aux = false;
            posX = new Random().nextInt(qntX);
            posY = new Random().nextInt(qntY);
            
            for(Base b : corpo) {
                if(b.getX() == posX * tamCubo + xInicial && b.getY() == posY * tamCubo + yInicial)
                    aux = true;
            }
        }
        fruta = new Cubo(posX * tamCubo + xInicial, posY * tamCubo + yInicial, tamCubo, Color.BLUE);
    }
    
    public void upDate(Graphics bg) {
        limpaTela(bg);
        if(fimDeJogo) {
            fimDeJogo(bg);
        }
        else {
            desenharPlacar(bg);
            desenharTodos(bg);
            long tempoAtual = System.currentTimeMillis();
            if(tempoAtual >  ultimoMov + delay) {
                ultimoMov = tempoAtual;
                moverTodos();
                verificarColisaoComJogo();
                verificarColisaoComFruta();
                verificarFim();
            }
        }   
    }
    
    public void setPlayerActions(boolean direito,
                                boolean esquerdo,
                                boolean cima,
                                boolean baixo,
                                boolean reiniciar) {
        if (direito && direcao != 3) {
            player.setIncX(tamCubo);
            player.setIncY(0);
        }
        else if (esquerdo && direcao != 1) {
            player.setIncX(-tamCubo);
            player.setIncY(0);
        }
        else if (baixo && direcao != 4) {
            player.setIncY(tamCubo);
            player.setIncX(0);
        }
        else if (cima && direcao != 2) {
            player.setIncY(-tamCubo);
            player.setIncX(0);
        }

        if(fimDeJogo && reiniciar)
        {
            fimDeJogo = false;
            reiniciar = false;
            initGame();
        }
    }

    private void moverTodos() {
        Base r = new Cubo(corpo.get(corpo.size() - 1).getX(),
                          corpo.get(corpo.size() - 1).getY(),
                          corpo.get(corpo.size() - 1).getLargura(),
                          corpo.get(corpo.size() - 1).getAltura(),
                          corpo.get(corpo.size() - 1).getColor());

        for(int i = corpo.size() - 1; i > 0; i--) {
            corpo.get(i).setX(corpo.get(i - 1).getX());
            corpo.get(i).setY(corpo.get(i - 1).getY());
        }
        corpo.get(0).mover();

        if(comeu) {
            comeu = false;
            add(r);
        }
        
        if(player.getIncX() > 0) 
            direcao = 1;
        else if(player.getIncX() < 0)
            direcao = 3;
        else if(player.getIncY() > 0)
            direcao = 2;
        else if(player.getIncY() < 0)
            direcao = 4;
    }

    private void desenharTodos(Graphics bg) {
        for(Base b : corpo) {
            b.desenhar(bg);
        }
        fruta.desenhar(bg);
        frame.desenhar(bg);
    }

    private void verificarColisaoComFruta() {
        //if(player.getX() == fruta.getX() && player.getY() == fruta.getY()) {
        if(player.colisaoCom(fruta)) {
            comeu = true;
            placar++;
            instanciaFruta();
        }
    }
    
    private void verificarColisaoComPlayer() {
        for(Base b : corpo) {
            if(b != player && b.getX() == player.getX() && b.getY() == player.getY()) {
                fimDeJogo = true;
            }
        }
    }
    
    private void verificarColisaoComJogo() {
        if(rigidFrame) {
            if(player.getX() < xInicial || player.getX() == xInicial + largura || player.getY() < yInicial || player.getY() == yInicial + altura)
                fimDeJogo = true;
        }
        else {
            if(player.getX() < xInicial)
                player.setX((qntX - 1) * tamCubo + xInicial);
            else if(player.getX() == xInicial + largura)
                player.setX(xInicial);
            else if(player.getY() < yInicial)
                player.setY((qntY - 1) * tamCubo + yInicial);
            else if(player.getY() == yInicial + altura)
                player.setY(yInicial);
            player.refreshRect();
        }
    }

    private void desenharPlacar(Graphics bg) {
        bg.setColor(Color.BLACK);
        bg.drawString("Placar: " + placar, xInicial + largura - 120, yInicial + altura + xInicial - 20);
    }

    private void limpaTela(Graphics bg) {    
        bg.setColor(Color.GREEN);
        bg.fillRect(0, 0, largura + xInicial * 2, altura + yInicial + xInicial);
    }

    private void verificarFim() {
        verificarColisaoComPlayer();
    }

    private void fimDeJogo(Graphics bg) {
        corpo.clear();
        bg.setColor(Color.BLACK);
        String msg = "FIM DE JOGO - Tecla 'R' para reiniciar.";
        bg.drawString(msg,20,100);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
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
    // </editor-fold>
}

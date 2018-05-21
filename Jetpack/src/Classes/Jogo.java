/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import static Classes.Cubo.gl;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.ArrayList;
import java.util.Random;
import javax.media.opengl.GL2;

public class Jogo {
    static GL2 gl;
    static GLUT glut;
    Cubo player, chao, teto, fuel, vazio;
    ArrayList<Cubo> cubos = new ArrayList<Cubo>();
    ArrayList<Cubo> comb = new ArrayList<Cubo>();
    private long ultimoObstaculo;
    int pontos = 0, combustivel = 2000;
    boolean perdeu = false;
    
    public Jogo() {
        gerar();
    }
    
    public void gerar(){
        player = new Cubo(null, 0, 100, 0, 0, 100, 0, 0, 0, 0, 50, 50, 50);
        chao = new Cubo(null, 0, -500, 0, 0, 0, 0, 0, 0, 0, 1300, 50, 50);
        teto = new Cubo(null, 0, 500, 0, 0, 0, 0, 0, 0, 0, 1300, 50, 50);
        vazio = new Cubo(null, -200, -490, 25, 0, 0, 0, 0, 0, 0, combustivel / 4, 20, 48);
        fuel = new Cubo(null, -200, -490, 25, 0, 0, 0, 0, 0, 0, combustivel / 4, 20, 50);
        ultimoObstaculo = System.currentTimeMillis();
    } 
    
    public boolean att(boolean dir, boolean esq, boolean fre, boolean tra) {
        desenhar();
        gl.glPushMatrix();
            gl.glColor3d(1, 1, 1);
                gl.glRasterPos3f((float) 4, 4, 0);
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Pontos: " + pontos);
            gl.glPopMatrix();
        if(!perdeu) {
            acao(dir, esq, fre, tra);
            cair();
            geraObstaculo();
            moveObstaculo();
            if(colisao()) {
                fimDeJogo();
            }
        }
        else 
            return true;
        return false;
    }
    
    public boolean colisao() {
        ArrayList<Cubo> lixo = new ArrayList<Cubo>();
        for(Cubo c : cubos) {
            if(player.colisao(c)) {
                lixo.add(c);
                return true;
            }
        }
        if(player.colisao(chao) || player.colisao(teto)) {
            return true;
        }
        for(Cubo c : comb) {
            if(player.colisao(c)) {
                combustivel = 2000;
                lixo.add(c);
            }
        }
        cubos.removeAll(lixo);
        comb.removeAll(lixo);
        lixo.clear();
        
        return false;
    }
    
    public void cair() {
        if(!player.colisao(chao))
            player.y -= 1;
    }
    
    public void desenhar() {
        player.desenhar(1, 0, 0);
        chao.desenhar(0, 1, 0);
        teto.desenhar(0, 1, 0);
        vazio.desenhar(0.3f, 0.3f, 0.3f);
        fuel.sx = combustivel / 4;
        fuel.x = -200 - (2000 - combustivel) / 8;
        fuel.desenhar(1, 0, 0.3f);
        for(Cubo c : cubos) {
            c.desenhar(0, 0, 1);
        }
        for(Cubo c : comb) {
            c.desenhar(1, 0.5f, 0);
        }
    }
    
    public void acao(boolean direita, boolean esquerda, boolean cima, boolean baixo) {
        if(direita && player.x < 550 && combustivel > 0) {
            player.px += 3;
            player.x += 3;
            combustivel--;
        }
        else if(esquerda && player.x > -550 && combustivel > 0) {
            player.px -= 3;
            player.x -= 3;
            combustivel--;
        }
        if(cima && combustivel > 0) {
            if(!player.colisao(teto)) {
                player.py += 4;
                player.y += 4;
            }
            combustivel--;
        }
        else if(baixo && !player.colisao(chao)) {
            player.py -= 2;
            player.y -= 2;
            combustivel--;
        }
        player.desenhaVertice();
    }
    
    public void geraObstaculo() {
        long tempoAtual = System.currentTimeMillis();
        
        
        if(tempoAtual > ultimoObstaculo + 1500) {
            ultimoObstaculo = tempoAtual;
            if(new Random().nextInt(5) == 4) {
                Cubo c = new Cubo(null, 600, new Random().nextInt(760) - 380, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50);
                comb.add(c);
            }
            else {
                Cubo c = new Cubo(null, 600, new Random().nextInt(760) - 380, 0, 0, 0, 0, 0, 0, 0, 50, 200, 50);
                //Cubo c = new Cubo(null, 600, -380, 0, 0, 0, 0, 0, 0, 0, 100, 300, 50);
                cubos.add(c);
            }
        }
    }

    private void moveObstaculo() {
        ArrayList<Cubo> lixo = new ArrayList<Cubo>();
        for(Cubo c : cubos) {
            c.x -= 3;
            if(c.x < -600) {
                lixo.add(c);
                pontos++;
            }
            c.desenhaVertice();
        }
        
        for(Cubo c : comb) {
            c.x -= 4;
            if(c.x < -600) {
                lixo.add(c);
            }
            c.desenhaVertice();
        }
        cubos.removeAll(lixo);
        lixo.clear();
    }

    private void fimDeJogo() {
        cubos.clear();
        comb.clear();
        perdeu = true;
    }
}

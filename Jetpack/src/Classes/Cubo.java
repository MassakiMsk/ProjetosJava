/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Point;
import java.awt.Rectangle;
import static java.lang.Math.*;
import java.util.Collections;
import javax.media.opengl.GL2;

/**
 *
 * @author 1545 IRON V4
 */
public class Cubo{
    static GL2 gl;
    static GLUT glut;
    Cubo pai;
    int x, y, z, px, py, pz;
    float rx, ry, rz;
    public int sx, sy, sz;
    double sin, cos, tan, hip, tet;
    double x1, x2, x3, x4, y1, y2, y3, y4, xmin, xmax, ymin, ymax;
    Rectangle rect;
    
    public Cubo(Cubo pai, int x, int y, int z, int px, int py, int pz, float rx, float ry, float rz, int sx, int sy, int sz) {
        this.pai = pai;
        this.x = x;
        this.y = y;
        this.z = z;
        this.px = px;
        this.py = py;
        this.pz = pz;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        rect = new Rectangle(x - sx / 2, y - sy / 2, sx, sy);
        desenhaVertice();
    }
    
    public boolean colisao(Cubo c) {
        if(rect.intersects(c.rect))
            return true;
        /*
        
        if(c.x1 > xmin && c.x1 < xmax && c.y1 > ymin && c.y1 < ymax)
            return true;
        if(c.x2 > xmin && c.x2 < xmax && c.y2 > ymin && c.y2 < ymax)
            return true;
        if(c.x3 > xmin && c.x3 < xmax && c.y3 > ymin && c.y3 < ymax)
            return true;
        if(c.x4 > xmin && c.x4 < xmax && c.y4 > ymin && c.y4 < ymax)
            return true;
        
        if(x1 > c.xmin && x1 < c.xmax && y1 > c.ymin && y2 < c.ymax)
            return true;
        if(x2 > c.xmin && x2 < c.xmax && y2 > c.ymin && y2 < c.ymax)
            return true;
        if(x3 > c.xmin && x3 < c.xmax && y3 > c.ymin && y3 < c.ymax)
            return true;
        if(x4 > c.xmin && x4 < c.xmax && y4 > c.ymin && y4 < c.ymax)
            return true;
        */
        
        return false;
    }
    
    public void desenhaVertice() {
        rect.x = x - sx / 2;
        rect.y = y - sy / 2;
        
        /*
        tan = sx / (float)sy;
        tet = Math.toDegrees(Math.atan(tan));
        double tet1 = Math.toDegrees(Math.atan(sy / (float)sx));
        hip = Math.sqrt((sx * sx + sy * sy) / 4.0);
        
        x1 = Math.cos(Math.toRadians(tet - (90 - rz))) * hip + px + Math.sin(Math.toRadians(rz)) * (x - px);
        y1 = Math.sin(Math.toRadians(tet - (90 - rz))) * hip + py + Math.cos(Math.toRadians(rz)) * (y - py);
        x2 = -Math.cos(Math.toRadians(tet - (90 - rz))) * hip + px + Math.sin(Math.toRadians(rz)) * (x - px);
        y2 = -Math.sin(Math.toRadians(tet - (90 - rz))) * hip + py + Math.cos(Math.toRadians(rz)) * (y - py);
        x3 = Math.sin(Math.toRadians(90 - rz - tet1)) * hip + px + Math.sin(Math.toRadians(rz)) * (x - px);
        y3 = Math.cos(Math.toRadians(90 - rz - tet1)) * hip + py + Math.cos(Math.toRadians(rz)) * (y - py);
        x4 = -Math.sin(Math.toRadians(90 - rz - tet1)) * hip + px + Math.sin(Math.toRadians(rz)) * (x - px);
        y4 = -Math.cos(Math.toRadians(90 - rz - tet1)) * hip + py + Math.cos(Math.toRadians(rz)) * (y - py);
        
        xmax = x1;
        if(x2 > xmax)
            xmax = x2;
        if(x3 > xmax)
            xmax = x3;
        if(x4 > xmax)
            xmax = x4;
        
        xmin = x1;
        if(x2 < xmin)
            xmin = x2;
        if(x3 < xmin)
            xmin = x3;
        if(x4 < xmin)
            xmin = x4;
        
        ymax = y1;
        if(y2 > ymax)
            ymax = y2;
        if(y3 > ymax)
            ymax = y3;
        if(y4 > ymax)
            ymax = y4;
        
        ymin = y1;
        if(y2 < ymin)
            ymin = y2;
        if(y3 < ymin)
            ymin = y3;
        if(y4 < ymin)
            ymin = y4;
        
        /*
        gl.glTranslated(0, 0, sz / 200.0);
        gl.glColor3d(0, 0, 1);
        
        gl.glPushMatrix();
            gl.glTranslated(x1 / 100.0, y1 / 100.0, 0);
            glut.glutSolidSphere(0.1, 5, 5);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(x2 / 100.0, y2 / 100.0, 0);
            glut.glutSolidSphere(0.1, 5, 5);
        gl.glPopMatrix();
        
        gl.glColor3d(1,1,1);
        gl.glPushMatrix();
            gl.glTranslated(x3 / 100.0, y3 / 100.0, 0);
            glut.glutSolidSphere(0.1, 5, 5);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(x4 / 100.0, y4 / 100.0, 0);
            glut.glutSolidSphere(0.1, 5, 5);
        gl.glPopMatrix();
        */
    }
    
    public void desenhar(float r, float g, float b) {
        int ret = 0;
        
        gl.glColor3d(r, g, b);
        gl.glPushMatrix();
        if(pai != null) {
            ret = pai.referencia();
            gl.glTranslated((px - pai.x) / 100.0, (py - pai.y) / 100.0, (pz - pai.z) / 100.0);
        }
        else
            gl.glTranslated(px / 100.0, py / 100.0, pz / 100.0);
        
        gl.glRotated(rx, 1, 0, 0);
        gl.glRotated(ry, 0, 1, 0);
        gl.glRotated(rz, 0, 0, 1);
        gl.glTranslated((x - px) / 100.0, (y - py) / 100.0, (z - pz) / 100.0);
        gl.glPushMatrix();
        gl.glScaled(1, sy / (float)sx, sz / (float)sx);
        glut.glutSolidCube((float)(sx / 100.0));
        gl.glPopMatrix();
        
        gl.glPopMatrix();
            
        for(int i = 0; i < ret; i++)
            gl.glPopMatrix();
    }
    
    public int referencia() {
        int ret = 0;
        
        gl.glPushMatrix();
        if(pai != null)  {
            ret = pai.referencia();
            gl.glTranslated((px - pai.x) / 100.0, (py - pai.y) / 100.0, (pz - pai.z) / 100.0);
        }
        else
            gl.glTranslated(px / 100.0, py / 100.0, pz / 100.0);
            
        gl.glRotated(rx, 1, 0, 0);
        gl.glRotated(ry, 0, 1, 0);
        gl.glRotated(rz, 0, 0, 1);
        gl.glTranslated((x - px) / 100.0, (y - py) / 100.0, (z - pz) / 100.0);
        
        return ret + 1;
    }
    
    public static void setGl(GL2 gl) {
        Cubo.gl = gl;
    }

    public static void setGlut(GLUT glut) {
        Cubo.glut = glut;
    }
    
    public int getSx() {
        return sx;
    }

    public void setSx(int sx) {
        this.sx = sx;
    }

    public int getSy() {
        return sy;
    }

    public void setSy(int sy) {
        this.sy = sy;
    }

    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public Cubo getPai() {
        return pai;
    }

    public void setPai(Cubo pai) {
        this.pai = pai;
    }

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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getPz() {
        return pz;
    }

    public void setPz(int pz) {
        this.pz = pz;
    }

    public float getRx() {
        return rx;
    }

    public void setRx(float rx) {
        this.rx = rx;
    }

    public float getRy() {
        return ry;
    }

    public void setRy(float ry) {
        this.ry = ry;
    }

    public float getRz() {
        return rz;
    }

    public void setRz(float rz) {
        this.rz = rz;
    }
    
    
    
}

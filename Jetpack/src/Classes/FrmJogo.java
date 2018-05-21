package Classes;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import com.sun.javafx.geom.transform.Identity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.scene.transform.MatrixType;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class FrmJogo implements GLEventListener, KeyListener{
    GLU glu = new GLU();
    GLUT glut = new GLUT();
    boolean esq, dir, fre, tra, restart;

    public static void main(String args[]) {
        new FrmJogo();
    }

    public FrmJogo() {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);
        JFrame frame = new JFrame("Exemplo01");
        frame.setSize(1000, 1000);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });
        frame.addKeyListener(this);
    }
    
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) {
        GL2 gl = gLAutoDrawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 1, 30);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0, 0, -5);
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
    }

    public void dispose(GLAutoDrawable glad) {
    }
    Jogo j;
    public void init(GLAutoDrawable glAuto) {
        j = new Jogo();
        Animator a = new Animator(glAuto);
        a.start();
        
        
    }
    float g = 0;
    @Override
    public void display(GLAutoDrawable glAuto) {
        GL2 gl = glAuto.getGL().getGL2();
        gl.glEnable(GL2.GL_LIGHTING);  
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        Cubo.setGl(gl);
        Cubo.setGlut(glut);
        Jogo.gl = gl;
        Jogo.glut = glut;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslated(0, 0, -10);
        gl.glRotated(5, 1, 0, 0);
        
        if(j.att(dir, esq, fre, tra)) {
            gl.glPushMatrix();
            gl.glColor3d(1, 1, 1);
                gl.glRasterPos3f((float) -2, 0, 0);
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Fim de jogo, precione R para recomeçar");
            gl.glPopMatrix();
        }
        if(restart) {
            j = new Jogo();
            restart = false;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_UP)
            fre = true;
        if(key.getKeyCode() == KeyEvent.VK_DOWN)
            tra = true;
        if(key.getKeyCode() == KeyEvent.VK_RIGHT)
            dir = true;
        if(key.getKeyCode() == KeyEvent.VK_LEFT)
            esq = true;
        if(key.getKeyCode() == KeyEvent.VK_R)
            restart = true;
    }

    @Override
    public void keyReleased(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_UP)
            fre = false;
        if(key.getKeyCode() == KeyEvent.VK_DOWN)
            tra = false;
        if(key.getKeyCode() == KeyEvent.VK_RIGHT)
            dir = false;
        if(key.getKeyCode() == KeyEvent.VK_LEFT)
            esq = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

}


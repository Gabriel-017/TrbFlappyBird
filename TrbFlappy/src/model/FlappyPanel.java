package model;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FlappyPanel extends JPanel implements KeyListener, ActionListener {

    //variavesi
   final int WIDTH = 525, HEIGHT = 550; //tela
   final int VELOCIDADECANO = 5;
   final int CANOWITHD = 50;
    int flappyAltura = HEIGHT / 4;
    int flappyVel = 0, flappyAcel = 8, flappyI = 1;
    int score = 0;
        int[] canoX = {WIDTH, WIDTH + WIDTH / 2};
        int[] espaco = {(int)(Math.random() * (HEIGHT - 150)), (int)(Math.random() * (HEIGHT - 150))};
            boolean FimDeJogo = false;
            Timer tempo = new Timer(40, this);


    public FlappyPanel (){

        //fundo
        setSize(WIDTH, HEIGHT);
        setFocusable(true);
        addKeyListener(this);

        setBackground(Color.BLUE);

    }

    public void actionPerformed(ActionEvent e){

        //configuração do cubo
        flappyAcel += flappyI;
        flappyVel += flappyAcel;

            canoX[0] -= VELOCIDADECANO;
            canoX[1] -= VELOCIDADECANO;

        repaint();

    }

    public void keyPressed(KeyEvent e){

     int code = e.getKeyCode();

     //velocidade do "pulo"
        if(code == e.VK_SPACE){
                flappyAcel = -10;

        }
                //botão para iniciar
            if (code == e.VK_I){
                tempo.start();

            }
                //botão para reiniciar
            if (code == e.VK_R){
                tempo.stop();
                flappyAltura = HEIGHT / 4;
                flappyVel = 0;
                flappyAcel = 8;
                score = 0;
                 canoX[0] = WIDTH;
                 canoX[1] = WIDTH + WIDTH / 2;
                    espaco[0] = (int)(Math.random() * (HEIGHT - 150));
                    espaco[1] = (int)(Math.random() * (HEIGHT - 150));

                    FimDeJogo = false;

                repaint();
            }

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //exibir componentes

       if (!FimDeJogo) {

         g.setColor(Color.WHITE);
         g.drawString("PONTOS: " + score, WIDTH / 2, 10);

           logica();
           drawCano(g);
           drawFlappy(g);
       }
        else {
           g.setColor(Color.WHITE);
           g.drawString("PONTOS: " + score, WIDTH / 2, 10);
       }
       }

    private void drawFlappy(Graphics g){

        //desenhar o cubo

        g.setColor(Color.YELLOW);

        //BufferedImage jogador = export("Resources/Bird.png"); (tentativa de trocar o cubo pela imagem do passaro)

        //g.drawImage(jogador, 75, flappyAltura + flappyVel, null);(tentativa de trocar o cubo pela imagem do passaro)

        //getClass().getResource("/resources/imagem/myImage.Bird.png");(tentativa de trocar o cubo pela imagem do passaro)

        g.fillRect(75, flappyAltura + flappyVel, 25, 25);

    }

    private void drawCano(Graphics g){

        for (int i = 0; i < 2; i++) {

            // canos
            g.setColor(Color.GREEN);
            g.fillRect(canoX[i], 0, CANOWITHD, HEIGHT);


            // espaço entre os canos
            g.setColor(Color.BLUE);
            g.fillRect(canoX[i], espaco[i], CANOWITHD, 100);
        }
        }

    private void logica() {

        //configuração da hitbox
        for (int i = 0; i < 2; i++) {

            if (canoX[i] <= 100 && canoX[i] + CANOWITHD >= 100
                    || canoX[1] <= 1000 && canoX[i] + CANOWITHD >= 1000) {

                if ((flappyAltura + flappyVel) >= 0 && (flappyAltura + flappyVel) <= espaco[i]
                        || (flappyAltura + flappyVel + 25) >= espaco[i] + 100 && (flappyAltura + flappyVel + 25) <= HEIGHT) {

                    FimDeJogo = true;
                }
            }

            if (flappyAltura + flappyVel <= 0
                    || flappyAltura + flappyVel + 25 >= HEIGHT){

                        FimDeJogo = true;
            }

            if(75 > canoX[i] + CANOWITHD){
                score++;
            }

            if(canoX[i] + CANOWITHD <= 0){
                canoX[i] = WIDTH;
                    espaco[i] = (int)(Math.random() * (HEIGHT - 150));
            }
        }
    }

    //tentativa de exportar a imagem do passaro

    public BufferedImage export (String path){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    //manter key event
    public void keyReleased(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){
    }
}

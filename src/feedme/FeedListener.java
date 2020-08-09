package feedme;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedListener extends MouseAdapter {

    boolean hungry = false;
    long startTime;
    long endTime;
    int limit;

    @Override

    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (!hungry) {
            System.out.println("Prehranili ste ljubimca. Igra je gotova!");
            System.exit(0);
        }
        endTime = System.currentTimeMillis() - startTime;
        if (endTime < 1000){
            System.out.println("WOOOW!!! Ovim tempom ishrane sam zgodan do leta :)");
        } else if (endTime < 2000) {
            System.out.println("Mega brzo!!!");
        } else if (endTime < 3000) {
            System.out.println("Super brzo!");
        } else if (endTime < 7000) {
            System.out.println("Osrednje...");
        } else if (endTime < 9000) {
            System.out.println("Pozuri covece, umrecu od gladi!");
        } else if((endTime > 9000) || (endTime < 15000)) {
            System.out.println("Umireeeem, brze!!!");
        }
        System.out.println("Nahranjen u: " + endTime);
        hungry = false;
    }

    public FeedListener() {
        startGame();
    }

    public void startGame() {
        System.out.println("Kad ljubimac kaze da je gladan kliknte na 'Nahrani me' u roku od 15 sekundi");
        Thread t = new Thread(() -> {
            try {
                hungry = false;
                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(5000));
                } catch (InterruptedException ex) {
                }
                startTime = System.currentTimeMillis();
                hungry = true;
                System.out.println("Gladan sam! Nahrani me... (" + startTime + ")");
                Thread.sleep(15000);
                if (hungry) {
                    System.out.println("Pregladneo sam i umro. Zbogom!");
                    System.exit(0);
                }
            } catch (InterruptedException ex) {
            }
            startGame();
        }
        );
        t.start();
    }
}

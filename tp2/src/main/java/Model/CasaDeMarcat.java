package Model;

import com.sun.source.tree.SynchronizedTree;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class CasaDeMarcat implements Runnable{
        BlockingQueue<Client> clienti;

        Thread thread;
        private int ID;
        private boolean b=true;
        private static int contor;
        public  Object monitor  = new Object();

        public static AtomicInteger timpcurent=new AtomicInteger(0);

        public CasaDeMarcat() {
            this.clienti = new ArrayBlockingQueue<>(100) {
            };
            contor=contor+1;
            this.ID=contor;
        }

        public  void run() {

            while (b) {
                while (!this.clienti.isEmpty()) {

                    Client client = this.clienti.peek();
                    client.setAsteptare(client.getAsteptare() - 1);

                    if (client.getSosire() + client.getAsteptare() <= client.getSosire()) {
                        this.clienti.remove(client);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(this.ID + "  " + this.clienti.isEmpty());
                }
            }
        }

        public synchronized BlockingQueue<Client> getClienti() {
            return clienti;
        }

        public synchronized void setTimpcurent(Integer timpcurent) {
            this.timpcurent = new AtomicInteger(timpcurent);
        }

    public int getID() {
        return ID;
    }

    public Integer getTimp()
    {
        Integer timp=0;
        for(Client client:this.clienti)
        {
            timp+=client.getAsteptare();
        }
        return timp;
    }

    public void signal()
    {
        synchronized (monitor)
        {
        this.notifyAll();
         }
    }

    public void setB(boolean b) {
        this.b = b;
    }
}




package Model;

import java.util.Random;

public class Client {
    private int sosire;
    private int asteptare;

    private int ID;
    private static int contor;

    public void setAsteptare(Integer asteptarea) {
        this.asteptare = asteptarea;
    }

    public void setSosire(Integer sosire) {
        sosire = sosire;
    }

    public Integer getAsteptare() {
        return asteptare;
    }

    public int getID() {
        return ID;
    }

    public Integer getSosire() {
        return sosire;
    }

    public Client(Integer s, Integer a)
    {
        this.sosire=s;
        this.asteptare=a;
        contor=contor+1;
        this.ID=contor;
    }

    public Client()
    {  contor=contor+1;
        this.ID=contor;}

    public Client ClientRandoom(Integer minSosire , Integer maxSosire,Integer minAsteptare, Integer maxAsteptare)
    {
        contor--;
        Client ret=new Client();
        Random rand=new Random();
        ret.sosire=rand.nextInt(maxSosire-minSosire+1)+minSosire;
        ret.asteptare= rand.nextInt(maxAsteptare-minAsteptare+1)+minAsteptare;
    return  ret;
    }



}



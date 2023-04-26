package Model;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Magazin {
    private ArrayList<CasaDeMarcat> vectorCase;
    private BlockingQueue<Client> vectorClienti;
    private Integer contorClienti=0;

    public  ArrayList<Thread> vecThreads;

    public Magazin()
    {   this.vectorClienti=new ArrayBlockingQueue<>(100);
        this.vectorCase=new ArrayList<>(3);
        this.vecThreads= new ArrayList<>() ;
    }

    public Client ClientInMagazin(Integer MA,Integer ma,Integer MS,Integer ms)
    {
        Client clientnou=new Client();
        clientnou=clientnou.ClientRandoom(ms,MS,ma,MA);
        this.getVectorClienti().add(clientnou);
        this.contorClienti++;
        return clientnou;
    }

    public ArrayList<CasaDeMarcat> getVectorCase() {
        return vectorCase;
    }



    public BlockingQueue<Client> getVectorClienti() {
        return vectorClienti;
    }

    public void setVectorCase(ArrayList<CasaDeMarcat> vectorCase) {
        this.vectorCase = vectorCase;
    }

    public void setVectorClienti(ArrayBlockingQueue<Client> vectorClienti) {
        this.vectorClienti = vectorClienti;
    }
    public Integer getContorClineti() {
        return contorClienti;
    }

    public void plaseazaClient(Client client)
    {   int min=-1;
        int timp=Integer.MAX_VALUE;
        for(CasaDeMarcat casa: this.vectorCase)
        {   if(casa.getClienti().isEmpty())
            {
                casa.clienti.add(client);
                return;
            }
            else if(timp>=casa.getTimp())
            {   timp=casa.getTimp();
                min=casa.getID();

            }
        }
        this.vectorCase.get(min-1).clienti.add(client);
    }

}

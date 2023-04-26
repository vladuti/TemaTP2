package Controller;

import Model.CasaDeMarcat;
import Model.Client;
import View.Interfata;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    public ActionListener Submit(JTextField t1, JTextField t2, JTextField t3, JTextField t4,JTextField t5,JTextField t6,JTextField t7, Interfata interfata)
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            interfata.setMinAasteapta(Integer.parseInt(t1.getText()));
            interfata.setMaxAasteapta(Integer.parseInt(t2.getText()));
            interfata.setMinSosire(Integer.parseInt(t3.getText()));
            interfata.setMaxSosire(Integer.parseInt(t4.getText()));
            interfata.setNumarCase(Integer.parseInt(t5.getText()));
            interfata.setNrClienti(Integer.parseInt(t6.getText()));
            interfata.setTimpMaxim(Integer.parseInt(t7.getText()));
                ArrayList<CasaDeMarcat> vec=new ArrayList<>(interfata.getNumarCase());
                interfata.getMagazin().setVectorCase(vec);
                for(int i=0;i<interfata.getNumarCase();i++)
                {
                    CasaDeMarcat aux=new CasaDeMarcat();

                    Thread thread=new Thread(aux);
                    interfata.getMagazin().vecThreads.add(thread);
                    thread.start();
                    interfata.getMagazin().getVectorCase().add(aux);
                }

                for(int i=0;i<interfata.getNrClienti();i++) {
                    interfata.getMagazin().ClientInMagazin(interfata.getMaxAasteapta(), interfata.getMinAasteapta(), interfata.getMaxSosire(), interfata.getMinSosire());
                }
            }
        };
    }



    public ActionListener Start(Interfata interfata, JLabel timp,JTextField t7,JTextArea text) {


        return new ActionListener() {
            int i=0;
            @Override
            public void actionPerformed(ActionEvent e) {

                interfata.setTimpcurent( new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ScheduledExecutorService incrementare = Executors.newSingleThreadScheduledExecutor();
                        incrementare.schedule(new Runnable() {
                            @Override
                            public void run() {
                                    i+=1;
                            }
                        }, 1, TimeUnit.SECONDS);
                        int ok =0;
                        int ok1=1;
                        if(interfata.getMagazin().getVectorClienti().isEmpty())ok=1;
                        for(CasaDeMarcat casa: interfata.getMagazin().getVectorCase())
                        {
                            if(!casa.getClienti().isEmpty()) {
                                ok1=0;

                            }
                        }
                        int plus=ok+ok1;
                        if(i<=Integer.parseInt(t7.getText()) && plus!=2)
                        {
                            timp.setText(Long.toString(i));
                            text.setText(text.getText() + "\n" + interfata.afisareMagazin());
                            CasaDeMarcat.timpcurent = new AtomicInteger(i);
                            for (Client client : interfata.getMagazin().getVectorClienti()) {
                                if (client.getSosire() <= i) {
                                    interfata.getMagazin().plaseazaClient(client);
                                    interfata.getMagazin().getVectorClienti().remove(client);
                                }

                            }

                        }
                        else {
                            for(CasaDeMarcat casa: interfata.getMagazin().getVectorCase())
                            {
                                casa.setB(false);
                            }
                            if(plus==2) i=Integer.parseInt(t7.getText())+1;
                            text.setText(text.getText() + "\n" + interfata.afisareMagazin());
                            interfata.getTimpcurent().setRepeats(false);
                        }

                    }
                }));
                interfata.getTimpcurent().setRepeats(true);
                interfata.getTimpcurent().start();



            }

        };
    }


}



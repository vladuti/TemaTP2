package View;

import Controller.Controller;
import Model.CasaDeMarcat;
import Model.Client;
import Model.Magazin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class Interfata {
   private Integer minAasteapta;
   private Integer maxAasteapta;
   private Integer minSosire;
   private Integer maxSosire;
   private Timer timpcurent;
   private Magazin magazin;
    private Integer nrClienti;
    private int timpMaxim;

    private Integer numarCase;

    public Interfata() {

        this.magazin=new Magazin();


        JFrame frame = new JFrame("LIDL");
        JPanel panou = new JPanel();
        panou.setLayout(null);

        JButton btnStart = new JButton("Start");

        JButton btnSubmit = new JButton("Submit");

        btnStart.setBounds(10, 350, 130, 30);

        btnSubmit.setBounds(10, 390, 130, 30);

        JLabel timp=new JLabel("0");
        JLabel l1=new JLabel("MIN ASTEAPTA: ");
        JLabel l2=new JLabel("MAX ASTEAPTA: ");
        JLabel l3=new JLabel("MIN SOSIRE:   ");
        JLabel l4=new JLabel("MAX SOSIRE:   ");
        JLabel l5=new JLabel("NUMAR CASE:   ");
        JLabel l6=new JLabel("NUMAR CLIENTI:");
        JLabel l7=new JLabel("TIMP MAXIM:   ");

        timp.setBounds(0,10,30,30);
        l1.setBounds(10,20,200,20);
        l2.setBounds(10,60,200,20);
        l3.setBounds(10,100,200,20);
        l4.setBounds(10,140,200,20);
        l5.setBounds(10,180,200,20);
        l6.setBounds(10,220,200,20);
        l7.setBounds(10,260,200,20);

        JTextField t1=new JTextField();
        JTextField t2=new JTextField();
        JTextField t3=new JTextField();
        JTextField t4=new JTextField();
        JTextField t5=new JTextField();
        JTextField t6=new JTextField();
        JTextField t7=new JTextField();

        t1.setBounds(110,20,30,20);
        t2.setBounds(110,60,30,20);
        t3.setBounds(110,100,30,20);
        t4.setBounds(110,140,30,20);
        t5.setBounds(110,180,30,20);
        t6.setBounds(110,220,30,20);
        t7.setBounds(110,260,30,20);





        panou.add(btnStart);
        panou.add(btnSubmit);

        panou.add(timp);

        panou.add(l1);
        panou.add(l2);
        panou.add(l3);
        panou.add(l4);
        panou.add(l5);
        panou.add(l6);
        panou.add(l7);

        panou.add(t1);
        panou.add(t2);
        panou.add(t3);
        panou.add(t4);
        panou.add(t5);
        panou.add(t6);
        panou.add(t7);


        JTextArea text=new JTextArea();
        JScrollPane area=new JScrollPane(text);
        area.setBounds(190,20,350,300);
        panou.add(area);

        frame.add(panou);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Controller control =new Controller();

        this.timpcurent= new Timer(1000, control.Start(this,timp,t7,text));
        btnSubmit.addActionListener(control.Submit(t1,t2,t3,t4,t5,t6,t7,this));
        btnStart.addActionListener(control.Start(this,timp,t7,text));
    }


    public void setMaxAasteapta(Integer maxAasteapta) {
        this.maxAasteapta = maxAasteapta;
    }

    public void setMaxSosire(Integer maxSosire) {
        this.maxSosire = maxSosire;
    }

    public void setMinAasteapta(Integer minAasteapta) {
        this.minAasteapta = minAasteapta;
    }

    public void setMinSosire(Integer minSosire) {
        this.minSosire = minSosire;
    }

    public Integer getMaxAasteapta() {
        return maxAasteapta;
    }

    public Integer getMaxSosire() {
        return maxSosire;
    }

    public Integer getMinAasteapta() {
        return minAasteapta;
    }

    public Integer getMinSosire() {
        return minSosire;
    }

    public Timer getTimpcurent() {
        return timpcurent;
    }

    public void setTimpcurent(Timer timpcurent) {
        this.timpcurent = timpcurent;
    }

    public Magazin getMagazin() {
        return magazin;
    }

    public void setMagazin(Magazin magazin) {
        this.magazin = magazin;
    }

    public void setTimpMaxim(Integer timpMaxim) {
        timpMaxim = timpMaxim;
    }

    public Integer getTimpMaxim() {
        return timpMaxim;
    }


    public void setNrClienti(Integer nrClienti) {
        this.nrClienti = nrClienti;
    }

    public Integer getNrClienti() {
        return nrClienti;
    }

    public void setNumarCase(Integer numarCase) {
        this.numarCase = numarCase;
    }

    public Integer getNumarCase() {
        return numarCase;
    }

    public String afisareMagazin()
    {

        String str="";
        str+="Clienti in magazin :\n";
        for(Client i:this.magazin.getVectorClienti())
        {
            str+="Clientul " +(i.getID()) + " (" + i.getSosire() + "," + i.getAsteptare() + ") ";

        }
        str+="\n";
        for(int i=0;i<numarCase;i++)
        {
            str+="Casa " +(i+1)+": ";
            for(Client client:magazin.getVectorCase().get(i).getClienti())
            {
                str+="Clientul " +client.getID() + " (" + client.getSosire() + "," + client.getAsteptare() + ") ";
            }
            str+="\n";
        }
        str+="\n";
        return str;
    }


}

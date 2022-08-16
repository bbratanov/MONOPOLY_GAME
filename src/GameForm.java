import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class GameForm {
    Date startTime;
    Date finishTime;
    public static JFrame frame;
    private ImageIcon background;
    private JLabel backgroundLabel;
    private JLabel exitButton;
    private JLabel sellButton;
    private JLabel numLabel;

    private JLabel throwButton;
    private JLabel player;
    private JLabel player2;
    private ImageIcon plIm2;
    private ImageIcon plIm;

    private JLabel moneyLabel;

    private JLabel YourBuildingLABEL0;
    private JLabel YourBuildingLABEL1;
    private JLabel YourBuildingLABEL2;
    private JLabel YourBuildingLABEL3;
    private JLabel YourBuildingLABEL4;
    private JLabel YourBuildingLABEL5;
    private JLabel YourBuildingLABEL6;

    private ArrayList<JLabel> YourBuildingLabel = new ArrayList<JLabel>(7);

    private ArrayList<Integer> XList = new ArrayList<>(24);
    private ArrayList<Integer> YList = new ArrayList<>(24);

    private int POS=0;
    private int LOOP=1;
    private int MONEY;
    private int Y=500;
    private int X=20;
    private int Y2=500;
    private int X2=20;
    private int POS2=0;
private boolean canstart=false;
    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private String addr= "localhost"; // ip адрес клиента
    private int port= 3448; // порт соединения

private int CHOSENPLid;
private String CHOSENPLname;

    private Game game = new Game();
    public GameForm(){

        choseAva();
        try {
            this.socket = new Socket(addr, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new GameForm.ReadMsg().start();


        } catch (IOException e) {

            GameForm.this.downService();
        }








        YList.add(500);
        YList.add(400);
        YList.add(300);
        YList.add(200);
        YList.add(100);
        YList.add(0);

        YList.add(0);
        YList.add(0);
        YList.add(0);
        YList.add(0);
        YList.add(0);
        YList.add(0);

        YList.add(0);
        YList.add(100);
        YList.add(200);
        YList.add(300);
        YList.add(400);
        YList.add(500);

        YList.add(500);
        YList.add(500);
        YList.add(500);
        YList.add(500);
        YList.add(500);
        YList.add(500);


        XList.add(20);
        XList.add(20);
        XList.add(20);
        XList.add(20);
        XList.add(20);
        XList.add(20);

        XList.add(120);
        XList.add(220);
        XList.add(320);
        XList.add(420);
        XList.add(520);
        XList.add(620);

        XList.add(720);
        XList.add(720);
        XList.add(720);
        XList.add(720);
        XList.add(720);
        XList.add(720);

        XList.add(620);
        XList.add(520);
        XList.add(420);
        XList.add(320);
        XList.add(220);
        XList.add(120);





        try {
            background= new ImageIcon(ImageIO.read(getClass().getResource("/resourses/board_for_GAME.jpeg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        backgroundLabel = new JLabel(background);
        backgroundLabel.setSize(800,600);


        try {
            plIm= new ImageIcon(ImageIO.read(getClass().getResource(CHOSENPLname)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player = new JLabel(plIm);
        player.setBounds(X,Y,70,70);
        player.setOpaque(false);


        exitButton = new JLabel("EXIT");
        exitButton.setBounds(635,450,230,50);
        exitButton.setForeground(new Color(110,99,100));
        exitButton.setFont(new Font("Monopoly", Font.BOLD,25));
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameOver(false);
                frame.dispose();
              //  System.exit(0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(new Color(196,30,58));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(new Color(110,99,100));

            }
        });


        sellButton = new JLabel("SELL");
        sellButton.setBounds(635,125,230,50);
        sellButton.setForeground(new Color(110,99,100));
        sellButton.setFont(new Font("Monopoly", Font.BOLD,25));
        sellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sellButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            sellSMTH();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sellButton.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sellButton.setForeground(new Color(110,99,100));
            }
        });



        YourBuildingLABEL0 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL0.setBounds(100,200,10,100);
        YourBuildingLABEL0.setVisible(false);
        YourBuildingLABEL0.setBackground(Color.blue);
        YourBuildingLABEL0.setOpaque(true);

        YourBuildingLABEL1 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL1.setBounds(100,100,100,10);
        YourBuildingLABEL1.setVisible(false);
        YourBuildingLABEL1.setBackground(Color.blue);
        YourBuildingLABEL1.setOpaque(true);

        YourBuildingLABEL2 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL2.setBounds(200,100,100,10);
        YourBuildingLABEL2.setVisible(false);
        YourBuildingLABEL2.setBackground(Color.blue);
        YourBuildingLABEL2.setOpaque(true);

        YourBuildingLABEL3 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL3.setBounds(400,100,100,10);
        YourBuildingLABEL3.setVisible(false);
        YourBuildingLABEL3.setBackground(Color.blue);
        YourBuildingLABEL3.setOpaque(true);


        YourBuildingLABEL4 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL4.setBounds(690,300,10,100);
        YourBuildingLABEL4.setVisible(false);
        YourBuildingLABEL4.setBackground(Color.blue);
        YourBuildingLABEL4.setOpaque(true);

        YourBuildingLABEL5 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL5.setBounds(600,490,100,10);
        YourBuildingLABEL5.setVisible(false);
        YourBuildingLABEL5.setBackground(Color.blue);
        YourBuildingLABEL5.setOpaque(true);

        YourBuildingLABEL6 = new JLabel("", SwingConstants.CENTER);
        YourBuildingLABEL6.setBounds(200,490,100,10);
        YourBuildingLABEL6.setVisible(false);
        YourBuildingLABEL6.setBackground(Color.blue);
        YourBuildingLABEL6.setOpaque(true);

        YourBuildingLabel.add(YourBuildingLABEL0);
        YourBuildingLabel.add(YourBuildingLABEL1);
        YourBuildingLabel.add(YourBuildingLABEL2);
        YourBuildingLabel.add(YourBuildingLABEL3);
        YourBuildingLabel.add(YourBuildingLABEL4);
        YourBuildingLabel.add(YourBuildingLABEL5);
        YourBuildingLabel.add(YourBuildingLABEL6);


        moneyLabel = new JLabel("MONEY: "+game.getMONEY()+"$");
        moneyLabel.setBounds(105,125,330,50);
        moneyLabel.setForeground(new Color(110,99,100));
        moneyLabel.setFont(new Font("Monopoly", Font.BOLD,25));







        numLabel = new JLabel("-", SwingConstants.CENTER);
        numLabel.setBounds(375,275,50,50);
        numLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        numLabel.setBackground(Color.BLACK);
        numLabel.setForeground(Color.WHITE);
        numLabel.setFont(new Font("Monopoly", Font.BOLD,40));
        numLabel.setOpaque(true);




            throwButton = new JLabel("THROW DICE", SwingConstants.CENTER);
        throwButton.setBounds(340,327,120,40);
        throwButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        throwButton.setBackground(Color.BLACK);
        throwButton.setForeground(Color.WHITE);
        throwButton.setFont(new Font("Monopoly", Font.BOLD,14));
        throwButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        throwButton.setOpaque(true);
        throwButton.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!game.isInPRISON())  {
        int a = (int) ( Math.random() * 4 )+1;

        try {
            out.write(String.valueOf(a)+"\r\n"); // отправляем на сервер
            out.flush(); // чистим
            throwButton.setBackground(Color.GRAY);
            throwButton.setEnabled(false);
        } catch (IOException q) {GameForm.this.downService();}






        numLabel.setForeground(Color.green);
        numLabel.setText(String.valueOf(a));

     //if you in prison

          moveXY(a,1);
          player.setBounds(X,Y,70,70);
          game.action(POS);



        moneyLabel.setText("MONEY: "+game.getMONEY()+"$");


     //ACTIONS

            if(game.isExtraTHROW()){
                try {
                    out.write("e"+"\r\n"); // отправляем на сервер
                    out.flush(); // чистим
                } catch (IOException q) {GameForm.this.downService();}
                throwButton.setBackground(Color.black);
                throwButton.setEnabled(true);
                game.setExtraTHROW(false);
            }


        if(game.sendMoney!=-1){
            try {
                out.write("m" +game.sendMoney+ "\r\n"); // отправляем на сервер
                out.flush(); // чистим
            }catch (Exception q){}
            game.sendMoney=-1;
        }
        if(game.newBought>-1)         {
            try {
                out.write("b" +game.newBought+ "\r\n"); // отправляем на сервер
                out.flush(); // чистим
            }catch (Exception q){}
            drawBuilding();
        }
        if(game.gotPRISE)               newPrice();          //IF YOU GET OR LOOSE MONEY
        if(POS==0)                      newLoop();            //IF YOU END LOOP
        if(game.isInPRISON())          enterPrison();        //IF YOU ENTER PRISON

        if(game.getMONEY()<0)    gameOver(false); //IF MONEY IS OVER
        }
        else if(game.isInPRISON()==true){
            JOptionPane.showMessageDialog(frame,"YOU ESCAPE FROM THE PRISON","PRISON",JOptionPane.PLAIN_MESSAGE);
            try {
                out.write("p" + "\r\n"); // отправляем на сервер
                out.flush(); // чистим
            }catch (Exception q){}
            throwButton.setBackground(Color.GRAY);
            throwButton.setEnabled(false);
            game.setInPRISON(false);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
});


backgroundLabel.add(sellButton);
        backgroundLabel.add(YourBuildingLABEL0);
        backgroundLabel.add(YourBuildingLABEL1);
        backgroundLabel.add(YourBuildingLABEL2);
        backgroundLabel.add(YourBuildingLABEL3);
        backgroundLabel.add(YourBuildingLABEL4);
        backgroundLabel.add(YourBuildingLABEL5);
        backgroundLabel.add(YourBuildingLABEL6);

        backgroundLabel.add(moneyLabel);
        backgroundLabel.add(player);
        backgroundLabel.add(throwButton);
        backgroundLabel.add(numLabel);
        backgroundLabel.add(exitButton);


        frame= new JFrame("MONOPOLY");
      //  frame.setUndecorated(true);
        frame.setLocationRelativeTo(MenueForm.frame);
        frame.add(backgroundLabel);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
       // frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setEnabled(false);
        try {
            out.write("start" +CHOSENPLid+ "\r\n"); // отправляем на сервер
            out.flush(); // чистим
        }catch (Exception e){}
    }





    public void moveXY(int a,int player){
        if(player==1) {
            if (POS + a > 23) {
                POS = (POS + a) - 24;
                LOOP++;
            } else POS += a;
            Y = YList.get(POS);
            X = XList.get(POS);
        }
        else{
            if (POS2 + a > 23) {
                POS2 = (POS2 + a) - 24;
                LOOP++;
            } else POS2 += a;
            Y2 = YList.get(POS2);
            X2 = XList.get(POS2);
        }
    }

    public void gameOver(boolean win){
        try {
            out.write("l" + "\r\n"); // отправляем на сервер
            out.flush(); // чистим
        }catch (Exception e){}
        finishTime= new Date();
        MenueForm.lastGameres=win;
        MenueForm.sessionTime=(finishTime.getTime()-startTime.getTime())/1000;
        JOptionPane.showMessageDialog(frame,(win?"hooray":"you looser"),win?"winner":"looser",JOptionPane.PLAIN_MESSAGE);

        System.out.println(MenueForm.sessionTime);
        downService();
       // frame.dispose();
    }
    public void newLoop( ){
        JOptionPane.showMessageDialog(frame,("you have got: "+game.getPRICE()),"LOOP ENDED",JOptionPane.PLAIN_MESSAGE);
    }

    public void enterPrison(){
        JOptionPane.showMessageDialog(frame,("   YOU ARE IN PRISON "),"PRISON",JOptionPane.PLAIN_MESSAGE);
    }

    public void newPrice(){
        game.gotPRISE=false;
        JOptionPane.showMessageDialog(frame,(game.message),"MONEY",JOptionPane.PLAIN_MESSAGE);
    }
    public void drawBuilding(){

        YourBuildingLabel.get(game.newBought).setVisible(true);


        game.newBought=-1;
    }


    public void sellSMTH(){
        int amount=0;
        for (int i = 0; i <7; i++) {
            if (game.YOURbuildings.get(i)!=null&&game.YOURbuildings.get(i)==true) amount++;

        }
        String b[] =new String[amount];
        int j=0;
        for (int i = 0; i < 7; i++) {
            if (game.YOURbuildings.get(i)!=null&&game.YOURbuildings.get(i)==true) {
                switch (i){
                    case 0: {
                        b[j]="BANK";
                        break;
                    }
                    case 1:{
                        b[j]="FACTORY1";
                        break;
                    }
                    case 2:{
                        b[j]="PRECIOUS1";
                        break;
                    }
                    case 3:{
                        b[j]="SKYSCRAPER1";
                        break;
                    }
                    case 4:{
                        b[j]="FACTORY2";
                        break;
                    }
                    case 5:{
                        b[j]="PRECIOUS2";
                        break;
                    }
                    case 6:{
                        b[j]="SKYSCRAPER2";
                        break;
                    }
                }
                j++;
            }

        }
        JComboBox cb = new JComboBox(b);
        int input ;
        input= JOptionPane.showConfirmDialog(frame,cb,"SHOP",JOptionPane.DEFAULT_OPTION);
        int r=-1;
        if (input==JOptionPane.OK_OPTION){
             r= cb.getSelectedIndex();
        }
if(r!=-1) {
    int c = 0;
    j = 0;
    do {
        if (game.YOURbuildings.get(j)!=null&&game.YOURbuildings.get(j)==true) {
            if (c == r) break;
            else c++;
        }
        j++;
    } while (j < 7);
    if (j==0) game.setMONEY(game.getMONEY()+2500);
    if (j==1) game.setMONEY(game.getMONEY()+2000);
    if (j==2) game.setMONEY(game.getMONEY()+1000);
    if (j==3) game.setMONEY(game.getMONEY()+1500);
    if (j==4) game.setMONEY(game.getMONEY()+1000);
    if (j==5) game.setMONEY(game.getMONEY()+1000);
    if (j==6) game.setMONEY(game.getMONEY()+1000);
    game.YOURbuildings.set(j, null);
    YourBuildingLabel.get(j).setVisible(false);
    try {
        out.write("s" +j+ "\r\n"); // отправляем на сервер
        out.flush(); // чистим
    }catch (Exception e){}
    moneyLabel.setText("MONEY: "+game.getMONEY()+"$");
}



    }
    private String choseAva(){
        String b[] =new String[3];
        b[0]="BOXER";
        b[1]="DALMATIN";
        b[2]="EINSTEIN";
        JComboBox cb = new JComboBox(b);
        int input ;




        input= JOptionPane.showConfirmDialog(MenueForm.frame,cb,"SHOP",JOptionPane.DEFAULT_OPTION);
        int r=-1;
        if (input==JOptionPane.OK_OPTION){
            r= cb.getSelectedIndex();
            CHOSENPLid=r;
            switch (r){
                case 0:CHOSENPLname= "resourses/icons/boxer_dog_animal_15972.png"; break;
                case 1: CHOSENPLname="resourses/icons/dalmatian_dog_animal_15959.png"; break;
                case 2: CHOSENPLname="resourses/icons/einstein_dog_animal_15969.png"; break;
            }
        }


        return CHOSENPLname;


    }


    private class ReadMsg extends Thread {
        @Override
        public void run() {



            String str;
            try {
                while (true) {
                    str = in.readLine(); // ждем сообщения с сервера
                    if(str!=null){
                    if(str.lastIndexOf("start")!=-1&&!canstart){ //start
                        JOptionPane.showMessageDialog(frame,("LETS START"),"GAME",JOptionPane.PLAIN_MESSAGE);
                        addnewPL(Integer.parseInt(str.replace("start","")));
                        startTime= new Date();
                        frame.setEnabled(true);
                    canstart=true;
                    } else if (str.lastIndexOf("b")!=-1) { //bought
                        System.out.println(str);
                        System.out.println(Integer.parseInt(str.replace("b","")));
                        game.YOURbuildings.set(Integer.parseInt(str.replace("b","")),false);
                        YourBuildingLabel.get(Integer.parseInt(str.replace("b",""))).setBackground(Color.red);
                        YourBuildingLabel.get(Integer.parseInt(str.replace("b",""))).setVisible(true);
                    } else if (str.lastIndexOf("s")!=-1) { //sell
                        game.YOURbuildings.set(Integer.parseInt(str.replace("s","")),null);
                        YourBuildingLabel.get(Integer.parseInt(str.replace("s",""))).setBackground(Color.blue);
                        YourBuildingLabel.get(Integer.parseInt(str.replace("s",""))).setVisible(false);
                    } else if (str.lastIndexOf("m")!=-1) { //money +
                        game.setMONEY(game.getMONEY()+Integer.parseInt(str.replace("m","")));
                        moneyLabel.setText("MONEY: "+game.getMONEY()+"$");
                        JOptionPane.showMessageDialog(frame,"SECOND PLAYER PAYS YOU: "+Integer.parseInt(str.replace("m",""))+"$","MONEY",JOptionPane.PLAIN_MESSAGE);

                    } else if (str.lastIndexOf("p")!=-1) {
                        throwButton.setBackground(Color.BLACK);
                        throwButton.setEnabled(true);
                    } else if (str.lastIndexOf("e")!=-1) {
                        throwButton.setBackground(Color.gray);
                        throwButton.setEnabled(false);
                    } else if (str.lastIndexOf("l")!=-1) {
                        gameOver(true);
                    } else {
                        throwButton.setBackground(Color.BLACK);
                        throwButton.setEnabled(true);
                        numLabel.setForeground(Color.red);
                          numLabel.setText(str);
                        moveXY(Integer.parseInt(str),2);
                        player2.setBounds(X2,Y2,70,70);
    System.out.println(str); // пишем сообщение с сервера на консоль
} }}
            } catch (IOException e) {
                GameForm.this.downService();
            }
        }
    }

    // нить отправляющая сообщения приходящие с консоли на сервер



    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                frame.dispose();
            }
        } catch (IOException ignored) {}
    }


    private void addnewPL(int num){
        String name2="";
        switch (num){
            case 0:name2= "resourses/icons/boxer_dog_animal_15972.png"; break;
            case 1: name2="resourses/icons/dalmatian_dog_animal_15959.png"; break;
            case 2: name2="resourses/icons/einstein_dog_animal_15969.png"; break;
        }
        try {
            plIm2= new ImageIcon(ImageIO.read(getClass().getResource(name2)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player2 = new JLabel(plIm2);
        player2.setBounds(X-5,Y,70,70);
        player2.setOpaque(false);
        backgroundLabel.add(player2);
    }
}

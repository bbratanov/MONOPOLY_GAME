import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class MenueForm {
    public static ArrayList<String> list = new ArrayList<>();
    public static String name;
    public static long sessionTime;
    public static boolean lastGameres;
    public static JFrame frame;
    private ImageIcon background;
    private JLabel backgroundLabel;
    private JLabel menueTitleLabel;
    private JLabel startButton;
    private JLabel statisticsButton;
    private JLabel exitButton;
    public MenueForm(){
        try {
            background= new ImageIcon(ImageIO.read(getClass().getResource("/resourses/nonopoly.jpeg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
       // background= new ImageIcon("/Users/mishabratanov/Desktop/STUDY/TI/Monopoly/src/resourses/nonopoly.jpeg");
        backgroundLabel = new JLabel(background);
        backgroundLabel.setSize(500,455);



                menueTitleLabel = new JLabel("MENU");
     menueTitleLabel.setBounds(170,50,300,50);
     menueTitleLabel.setForeground(Color.WHITE);
       menueTitleLabel.setFont(new Font("Monopoly", Font.BOLD,52));

        startButton = new JLabel("START");
        startButton.setBounds(190,150,137,40);
        startButton.setForeground(new Color(110,99,100));
        startButton.setFont(new Font("Monopoly", Font.BOLD,37));
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {



                GameForm play = new GameForm();
                startButton.setEnabled(false);
           // frame.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setForeground(new Color(233,201,88));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setForeground(new Color(110,99,100));

            }
        });


        statisticsButton = new JLabel("STATISTICS");
        statisticsButton.setBounds(150,210,230,50);
        statisticsButton.setForeground(new Color(110,99,100));
        statisticsButton.setFont(new Font("Monopoly", Font.BOLD,37));
        statisticsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        statisticsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                list.clear();
                binaryDeSerialization();
                String st="";
                for (int i = 0; i < list.size(); i++) {
                    st=st+list.get(i)+"\n";
                }
                JOptionPane.showMessageDialog(frame,st,"STATISTICS",JOptionPane.PLAIN_MESSAGE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                statisticsButton.setForeground(new Color(233,201,88));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statisticsButton.setForeground(new Color(110,99,100));

            }
        });




        exitButton = new JLabel("EXIT");
        exitButton.setBounds(415,370,230,50);
        exitButton.setForeground(new Color(110,99,100));
        exitButton.setFont(new Font("Monopoly", Font.BOLD,25));
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                list.clear();
                binaryDeSerialization();
                binarySerialization();
                System.exit(0);
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


backgroundLabel.add(exitButton);
        backgroundLabel.add(statisticsButton);
        backgroundLabel.add(startButton);
        backgroundLabel.add(menueTitleLabel);


        frame= new JFrame("MONOPOLY");
        frame.add(backgroundLabel);
        frame.setSize(500,455);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        askName();



    }


    private static void binarySerialization(){
        try {

            File file = new File("saves.out");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            String t="";
            if(sessionTime/60>0){
                t=sessionTime/60+"m"+(sessionTime-(sessionTime/60)*60)+"s";
            }else t= (sessionTime)+"s";
            String msg="NAME:"+name+" TIME:"+t+" RESULT:"+(lastGameres?"win":"loose");
            list.add(msg);
            objectOutputStream2.writeObject(list);
            byteArrayOutputStream.writeTo(fileOutputStream);
            objectOutputStream2.flush();

        }catch (Exception e){
            System.out.println("e");
        }

    }

    private static void binaryDeSerialization(){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("saves.out"));
            list = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static void askName(){
        JTextField cb = new JTextField();
        int input ;
        input= JOptionPane.showConfirmDialog(frame,cb,"NAME:",JOptionPane.DEFAULT_OPTION);
        int r=-1;
        if (input==JOptionPane.OK_OPTION){
            if(cb.getText()==null||cb.getText().equals("")){
                name="NONAME";
            }else{
            name= (cb.getText());}
        }

    }
}

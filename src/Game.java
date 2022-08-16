import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class Game {

    private int LOOP;
    private int PRICE;
    private int MONEY;
    private boolean inPRISON;

    public int sendMoney=-1;
    public String message;
    public boolean gotPRISE;

    public int newBought=-1;

    private ArrayList<Integer> EXTRAmoney = new ArrayList<>(6);

    public void setExtraTHROW(boolean extraTHROW) {
        this.extraTHROW = extraTHROW;
    }

    public void setMONEY(int MONEY) {
        this.MONEY = MONEY;
    }

    public ArrayList<Boolean> YOURbuildings = new ArrayList<>(7);
  //  public ArrayList<Boolean> SecondBuildings = new ArrayList<>(7);
    private boolean extraTHROW;

    public Game(){
        LOOP=1;
        PRICE=500;
        MONEY=6000;
        inPRISON=false;
        extraTHROW=false;
        EXTRAmoney.add(-100);
        EXTRAmoney.add(400);
        EXTRAmoney.add(-400);
        EXTRAmoney.add(600);
        EXTRAmoney.add(-600);
        EXTRAmoney.add(100);

        YOURbuildings.add(null);
        YOURbuildings.add(null);
        YOURbuildings.add(null);
        YOURbuildings.add(null);
        YOURbuildings.add(null);
        YOURbuildings.add(null);
        YOURbuildings.add(null);
    }

    public int getLOOP() {
        return LOOP;
    }

    public int getPRICE() {
        return PRICE;
    }

    public int getMONEY() {
        return MONEY;
    }

    public boolean isExtraTHROW() {
        return extraTHROW;
    }

    public boolean isInPRISON() {
        return inPRISON;
    }

    public void setInPRISON(boolean inPRISON) {
        this.inPRISON = inPRISON;
    }

    public void action(int POS){
        if(!inPRISON) {
            switch (POS) {
                case 0: {
                    if (LOOP % 4 == 0) {
                        PRICE -= 100;
                    }
                    MONEY += PRICE;

                    break;
                }
                case 1:
                case 14: {
                    if (MONEY > 0)
                        MONEY += PRICE;
                    else MONEY += 100;
                    gotPRISE=true;
                    message="HAPPINES! YOU HAVE GOT: "+(PRICE>0?PRICE:"100")+"$";
                    break;
                }
                case 2:{
                    int a = (int) ( Math.random() * 6 );
                    MONEY-=Math.abs(EXTRAmoney.get(a));
                    gotPRISE=true;
                    message="Oh GODS! \n MONEY: "+EXTRAmoney.get(a)+"$";
                    break;
                }
                case 12: {
                    int a = (int) ( Math.random() * 6 );
                    MONEY+=Math.abs(EXTRAmoney.get(a));
                    gotPRISE=true;

                    message="Oh GODS! YOU OPEN NEW ISLAND\n YOU FIND: "+(Math.abs(EXTRAmoney.get(a)))+"$";
                    break;
                }
                case 3: {
                    if(YOURbuildings.get(0)==null){
                     int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"BANK\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                   if(r==0){
                       if(MONEY>=5000) {
                           MONEY -= 5000;
                           YOURbuildings.set(0,true);
                           newBought=0;
                       }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                   }

                    } else if (YOURbuildings.get(0)==false) {
                        MONEY -= 5000/4;
                        sendMoney=5000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+5000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }


                    break;
                }
                case 4: {
                    if (MONEY > 0)
                        MONEY += PRICE * 3;
                    else MONEY += 100 * 3;
                    gotPRISE=true;
                    message=" YOU FIND TREASURE\n YOU FIND: "+(PRICE>0?PRICE*3:300)+"$";
                    break;
                }
                case 5: {

                    int a = (int) ( Math.random() * 6 );
                    MONEY-=Math.abs(EXTRAmoney.get(a));
                    gotPRISE=true;
                    message=" HOW WAS YOUR HOLIDAYS\n YOU SPEND: -"+(Math.abs(EXTRAmoney.get(a)))+"$";
                    break;
                }
                case 6: {
                    if(YOURbuildings.get(1)==null){
                        int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"FACTORY\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                        if(r==0){
                            if(MONEY>=4000) {
                                MONEY -= 4000;
                                YOURbuildings.set(1,true);
                                newBought=1;
                            }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else if (YOURbuildings.get(1)==false) {
                        MONEY -= 4000/4;
                        sendMoney=4000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+4000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }


                    break;
                }
                case 7: {
                    //// bought

                    if(YOURbuildings.get(2)==null){
                        int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"PRECIOUS\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                        if(r==0){
                            if(MONEY>=2000) {
                                MONEY -= 2000;
                                YOURbuildings.set(2,true);
                                newBought=2;
                            }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else if (YOURbuildings.get(2)==false) {
                        MONEY -= 2000/4;
                        sendMoney=2000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+2000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }

                    break;
                }
                case 16:
                case 8: {

                    JTextField cb = new JTextField();
                    int input ;
                    input= JOptionPane.showConfirmDialog(GameForm.frame,cb,"CHOOSE NUM FROM 1 to 6",JOptionPane.DEFAULT_OPTION);
                    int r=-1;
                    if (input==JOptionPane.OK_OPTION){
                        r= Integer.parseInt(cb.getText());
                    }
                    int a= (int) ( Math.random() * 6 )+1;
                    if(r==a){

                        JOptionPane.showMessageDialog(GameForm.frame,"You WON NUMBER IS:"+r+"\nMONEY: "+PRICE*10+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                        MONEY+=PRICE*10;
                    }else {
                        JOptionPane.showMessageDialog(GameForm.frame, "You LOOSE : -"+PRICE+"$\n You select:" + r + "\nBut answer is: " + a, "MONEY", JOptionPane.PLAIN_MESSAGE);
                        MONEY-=PRICE;
                    }
                    break;
                }
                case 9: {
                    //// bought

                    if(YOURbuildings.get(3)==null){
                        int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"SKYSCRAPER\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                        if(r==0){
                            if(MONEY>=3000) {
                                MONEY -= 3000;
                                YOURbuildings.set(3,true);
                                newBought=3;
                            }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else if (YOURbuildings.get(3)==false) {
                        MONEY -= 3000/4;
                        sendMoney=3000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+3000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }

                    break;
                }
                case 10: {
                    int a = (int) ( Math.random() * 6 );
                    MONEY+=Math.abs(EXTRAmoney.get(a));
                    gotPRISE=true;
                    message=" YOU HAVE GOT A PRICE\n MONEY: "+(Math.abs(EXTRAmoney.get(a)))+"$";
                    break;
                }
                case 11: {
                    inPRISON = true;
                    break;
                }
                case 13: {
                    int a = (int) ( Math.random() * 6 );
                    MONEY-=Math.abs(EXTRAmoney.get(a));
                    gotPRISE=true;
                    message="Oh GODS! \n MONEY: "+EXTRAmoney.get(a)+"$";

                    break;
                }
                case 15: {
                    //// bought
                    if(YOURbuildings.get(4)==null){
                        int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"FACTORY\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                        if(r==0){
                            if(MONEY>=2000) {
                                MONEY -= 2000;
                                YOURbuildings.set(4,true);
                                newBought=4;
                            }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else if (YOURbuildings.get(4)==false) {
                        MONEY -= 2000/4;
                        sendMoney=2000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+2000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }

                    break;
                }

                case 17: {
                    extraTHROW=true;

                    break;
                }
                case 18: {


                    if(YOURbuildings.get(5)==null){
                        int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"PRECIOUS\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                        if(r==0){
                            if(MONEY>=2000) {
                                MONEY -= 2000;
                                YOURbuildings.set(5,true);
                                newBought=5;
                            }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else if (YOURbuildings.get(5)==false) {
                        MONEY -= 2000/4;
                        sendMoney=2000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+2000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }



                    break;
                }
                case 19: {
                    if (MONEY > 0)
                        MONEY += PRICE * 3;
                    else MONEY += 100 * 3;
                    gotPRISE=true;
                    message=" YOU FIND TREASURE\n YOU FIND: "+(PRICE>0?PRICE*3:300)+"$";
                    break;
                }
                case 20: {
                    int a = (int) ( Math.random() * 6 );
                    MONEY+=EXTRAmoney.get(a);
                    gotPRISE=true;
                    message="Oh GODS! \n MONEY: "+EXTRAmoney.get(a)+"$";
                    break;
                }
                case 21: {
                    inPRISON = true;
                    break;
                }
                case 22: {

                    ////BOUGHT
                    if(YOURbuildings.get(6)==null){
                        int r=  JOptionPane.showConfirmDialog(GameForm.frame,"YOU WANT TO BUY \"SKYSCRAPER\"","PURCHASE",JOptionPane.YES_NO_OPTION);
                        if(r==0){
                            if(MONEY>=2000) {
                                MONEY -= 2000;
                                YOURbuildings.set(6,true);
                                newBought=6;
                            }else  JOptionPane.showMessageDialog(GameForm.frame,"NOT ENOUGH MONEY","MONEY",JOptionPane.PLAIN_MESSAGE);
                        }

                    }else if (YOURbuildings.get(6)==false) {
                        MONEY -= 2000/4;
                        sendMoney=2000/4;
                        JOptionPane.showMessageDialog(GameForm.frame,"PAY FOR STANDING HERE: "+2000/4+"$","MONEY",JOptionPane.PLAIN_MESSAGE);
                    }

                    break;
                }
                case 23: {
                    int a = (int) ( Math.random() * 6 );
                    MONEY+=Math.abs(EXTRAmoney.get(a));
                    gotPRISE=true;
                    message=" YOU HAVE GOT A PRICE\n MONEY: "+(Math.abs(EXTRAmoney.get(a)))+"$";
                    break;
                }


            }
        }
        else inPRISON=false;
    }



}

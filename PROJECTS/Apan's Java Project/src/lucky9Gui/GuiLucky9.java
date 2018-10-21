package lucky9Gui;

/*
Created by:
Guiller B. Apan 
2015
*/

import java.lang.String;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField; 
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class GuiLucky9 extends JFrame implements ActionListener{

	private JPanel titlePanel, pPanel,dPanel, btnPanel;
	private JButton newButton, dealButton, exitButton;
	private JLabel card1, card2, card3, card4, card5, card6;
	private JLabel pName, pScore, dName, dScore;
	private JTextField betTF,moneyTF;
	private ImageIcon[] imgIcon  = new ImageIcon[40];
	private String strName;
	private int intMoney= 1000;
	private int plrScore = 0;
	private int dlrScore = 0;
	private boolean start = true;
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
        	@Override
                public void run() {

                    GuiLucky9 go = new GuiLucky9();
                    go.setVisible(true);
                }
            }); 
        }
        
        public GuiLucky9() {
            loadImages();
            setUp();
            playGame();
        }

        public final void setUp() {

            //setIconImage(new ImageIcon("gdl.png").getImage()); //custom icon
            setBackground( new Color(250,185,0) );
            setLayout( new BorderLayout(3,3) );

            //TITLE
            titlePanel = new JPanel();
            titlePanel.setBackground(new Color(134,45,0));
            JLabel title = new JLabel("Welcome to Pinoy Lucky 9 Game",JLabel.CENTER );
            title.setFont(new Font("STENCIL", 1, 20)); 
            title.setForeground(new Color(255, 204, 0));
            titlePanel.add(title);
            titlePanel.setBorder(BorderFactory.createLineBorder( new Color(153,51,0), 2) ); 
            add(titlePanel, BorderLayout.NORTH);

	    //Player's Panel (Left)
            pPanel =new JPanel();
            pPanel.setLayout(new GridLayout(2,1));
            pPanel.setBackground(new Color(153,51,0));
            pPanel.setPreferredSize(new Dimension(350, 100));
            pPanel.add(card1 = new JLabel());
            pPanel.add(card2 = new JLabel());
            pPanel.add(card3 = new JLabel());
            pName = new JLabel("  Player: ");
            pName.setForeground(new Color(255, 255, 255));
            pScore = new JLabel(" Total Pts:  ");
            pScore.setForeground(new Color(255, 255, 255));
            pPanel.add(pName);
            pPanel.add(pScore);
            pPanel.setBorder(BorderFactory.createLineBorder( new Color(153,51,0), 3) );
            add(pPanel, BorderLayout.WEST);

            //Dealer's Panel (Right)
            dPanel =new JPanel();
            dPanel.setLayout(new GridLayout(2,1));
            dPanel.setBackground(new Color(134,45,0));
            dPanel.setPreferredSize(new Dimension(350, 100));
            dPanel.add(card4 = new JLabel(new ImageIcon("card.gif")));
            dPanel.add(card5 = new JLabel(new ImageIcon("card.gif")));
            dPanel.add(card6 = new JLabel(new ImageIcon("card.gif")));
            dName = new JLabel("  The Dealer     ");
            dName.setForeground(new Color(255, 255, 255));
            dScore = new JLabel("  Total Pts:  ");
            dScore.setForeground(new Color(255, 255, 255));
            dPanel.add(dName);
            dPanel.add(dScore);
            dPanel.setBorder(BorderFactory.createLineBorder( new Color(134,45,0), 1) );
            add(dPanel, BorderLayout.EAST);

            //BUTTONS & TEXTBOX
            btnPanel = new JPanel();
            btnPanel.setBackground( new Color(12,168,16) );
            add(btnPanel, BorderLayout.SOUTH);

            newButton = new JButton( "NEW GAME" );
            newButton.setEnabled(false);
            newButton.addActionListener(this);
            btnPanel.add(newButton);

            dealButton = new JButton( "DEAL" );
            dealButton.addActionListener(this);
            btnPanel.add(dealButton);

            exitButton = new JButton( "EXIT" );
            exitButton.addActionListener(this);
            btnPanel.add(exitButton);

            btnPanel.add(new JLabel(" BET:", JLabel.RIGHT));
            betTF = new JTextField("10", 7);
            betTF.setMargin( new Insets(3,3,3,3) );
            btnPanel.add(betTF);
            btnPanel.add(new JLabel(" MONEY:", JLabel.RIGHT));
            moneyTF = new JTextField("1000", 7);
            moneyTF.setMargin( new Insets(3,3,3,3) );
            moneyTF.setEditable(false);
            btnPanel.add(moneyTF);
            btnPanel.setBorder(BorderFactory.createLineBorder( new Color(48,48,48), 3) );

            setTitle("THE PINOY LUCKY 9");
            setSize(700,500);
            setResizable(false); 
            Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation( (screensize.width - getWidth())/2, (screensize.height - getHeight())/2 );
            setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            setVisible(true);

        }

        private void loadImages(){
            String[] arrCard = new String[40];
            String[] arrSuit = {"c", "d", "h", "s"};
            int counter = 0;

            for(int i=0; i < arrSuit.length; i++){
                for(int j=0; j < 10; j++){
                    arrCard[i+j] = arrSuit[i] + (j+1) + ".gif";
                    imgIcon[counter] = new ImageIcon(arrSuit[i] + (j+1) + ".gif");
                    counter++;
                }
            }	   
        }
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getActionCommand().equals("DEAL")){
                    dealIt();
            }else if (evt.getActionCommand().equals("NEW GAME")){
                    start=true;
                    playGame();
            }else if (evt.getActionCommand().equals("EXIT")){
                    System.exit(0);
            }
        }
        private void playGame(){

                try{
                    plrScore =0;
                    dlrScore =0;
                    pScore.setText(" Total Pts: 0");
                    dScore.setText(" Total Pts: 0");

                    if (start == true){
                            pName.setText("  Player: ");
                            card1.setIcon(new ImageIcon("card.gif"));
                            card2.setIcon(new ImageIcon("card.gif"));
                            card3.setIcon(new ImageIcon("card.gif"));
                            card4.setIcon(new ImageIcon("card.gif"));
                            card5.setIcon(new ImageIcon("card.gif"));
                            card6.setIcon(new ImageIcon("card.gif"));
                            JOptionPane.showMessageDialog(null, "Welcome to Pinoy Lucky 9 Game. \n [press ENTER to continue]","The Pinoy Lucky 9",JOptionPane.INFORMATION_MESSAGE);
                            strName = JOptionPane.showInputDialog(null, "Enter Your Name: ", "The Pinoy Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                            if(strName.isEmpty()){
                                strName = "player1";
                            }
                            pName.setText("  Player: " + strName);
                            newButton.setEnabled(false);
                            intMoney = 1000;
                            moneyTF.setText("" + intMoney);
                            JOptionPane.showMessageDialog(null, "Start the Game by pressing the DEAL Button below.", "The Pinoy Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                            dealIt();
                    }
                }catch(NullPointerException e){
                        JOptionPane.showMessageDialog(null, "Please enter your name", "The Pinoy Lucky 9", JOptionPane.ERROR_MESSAGE);
                }
        }

        private void dealIt(){
                int aIndex, bIndex, cIndex, dIndex, eIndex, fIndex;
                int bet, answer, totalMoney;

                try{
                    bet = Integer.parseInt(betTF.getText());
                    totalMoney = Integer.parseInt(moneyTF.getText());
                    
                    if(bet > totalMoney){
                        JOptionPane.showMessageDialog(null, "Your bet exceeds your total money. Please enter your bet below.", "The Pinoy's Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        aIndex = randomizeNum(0);
                        bIndex = randomizeNum(1);
                        cIndex = randomizeNum(2);
                        dIndex = randomizeNum(3);
                        eIndex = randomizeNum(4);
                        fIndex = randomizeNum(5);

                        card1.setVisible(false);
                        card2.setVisible(false);
                        card3.setVisible(false);
                        card4.setVisible(false);
                        card5.setVisible(false);
                        card6.setVisible(false);

                        card1.setIcon(imgIcon[aIndex]);
                        card2.setIcon(imgIcon[bIndex]);
                        card3.setIcon(imgIcon[cIndex]);
                        card4.setIcon(imgIcon[dIndex]);
                        card5.setIcon(imgIcon[eIndex]);
                        card6.setIcon(imgIcon[fIndex]);

                        JOptionPane.showMessageDialog(null, "Show your cards at hand. \n [press ENTER to continue]", "The Pinoy's Lucky 9", JOptionPane.INFORMATION_MESSAGE);

                        //Points
                        card1.setVisible(true);
                        card2.setVisible(true);
                        plrScore = (aIndex % 10+1) + (bIndex % 10 +1);
                        pScore.setText(" Total Pts:  " + (plrScore %10));

                        answer = JOptionPane.showConfirmDialog(null,"Draw your last card? ","Pinoy's Lucky 9",JOptionPane.YES_NO_OPTION);

                        if (answer == 0){
                            card3.setVisible(true);
                            plrScore =(aIndex % 10+1) + (bIndex % 10+1)+(cIndex % 10+1) ;
                        }else{
                            JOptionPane.showMessageDialog(null, "You're very confident. Last card was disregarded. \n [press ENTER to continue]", "The Pinoy Lucky 9", JOptionPane.PLAIN_MESSAGE);
                        }
                        plrScore %= 10;
                        pScore.setText(" Total Pts:  " + (plrScore %10));

                        JOptionPane.showMessageDialog(null, "Show the Dealer's Hand", "The Pinoy's Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                        card4.setVisible(true);
                        card5.setVisible(true);
                        dlrScore = (dIndex% 10+1) + (eIndex % 10+1);
                        dlrScore %= 10;
                        dScore.setText(" Total Pts:  " + (dlrScore%10));

                        if (dlrScore < 6){
                                JOptionPane.showMessageDialog(null, "The Dealer has drawn another card. \n [press ENTER to continue]", "The Pinoy Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                                card6.setVisible(true);
                                dlrScore =(dIndex % 10+1) + (eIndex % 10+1)+(fIndex % 10+1) ;
                                dlrScore %= 10;
                                dScore.setText(" Total Pts:  " + (dlrScore%10));
                        }

                        if ((plrScore %10) > (dlrScore %10)){
                                intMoney += bet;
                                JOptionPane.showMessageDialog(null, strName + " WINS! You've got your wager. +" + bet + " Php" + "\n" + "Your total Money is " + intMoney , "The Pinoy Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                        }else if ((plrScore %10) < (dlrScore %10)){
                                intMoney -= bet;
                                JOptionPane.showMessageDialog(null, strName + " LOSE! It has cost you -" + bet + " Php" + "\n" + "Your total Money is " + intMoney , "The Pinoy Lucky 9", JOptionPane.INFORMATION_MESSAGE);
                        }else if((plrScore %10) == (dlrScore %10)){
                                JOptionPane.showMessageDialog(null, "DEADLOCK! " + bet + " Php" + "\n" + "Your total Money is " + intMoney, "The Pinoy Lucky 9", JOptionPane.INFORMATION_MESSAGE );
                        }

                        moneyTF.setText("" + intMoney);
                        start =false;
                        newButton.setEnabled(true);

                        answer = JOptionPane.showConfirmDialog(null,"Deal Again?","Pinoy Lucky 9",JOptionPane.YES_NO_OPTION);
                        if (answer == 0){
                                start=false;
                                playGame();
                        }else{
                                int answer2 = JOptionPane.showConfirmDialog(null,"Do you want to Quit? If you want to change the bet, click NO.","Pinoy Lucky 9",JOptionPane.YES_NO_OPTION);
                                if (answer2 == 0){
                                    System.exit(0);
                                }
                        }
                    }
                    
                    

                }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Not a number", "The Pinoy Lucky 9", JOptionPane.ERROR_MESSAGE );
                }
        }


        private int randomizeNum(int i){
            int[] arr = new int[6];
            int temp, temp2 = 0;

            temp = (int) (Math.random()*40);
            arr[i] = temp;

            switch(i){

            case 0:
                    return temp;
            case 1:
                    if (temp != arr[0]) return temp2 = temp;
                    else  return temp2 =(int) (Math.random()*40);
            case 2:
                    if (temp != arr[0] || temp != arr[1]) return temp;
                    else return temp2 =(int) (Math.random()*40);
            case 3:
                    if (temp != arr[0] || temp != arr[1] || temp != arr[2] ) return temp;
                    else return temp2 =(int) (Math.random()*40);
            case 4:
                    if (temp != arr[0] || temp != arr[1] || temp != arr[2] || temp != arr[3]) return temp;
                    else return temp2 =(int) (Math.random()*40);
            case 5:
                    if (temp != arr[0] || temp != arr[1] || temp != arr[2] || temp != arr[3] || temp != arr[4]) return temp;
                    else return temp2 =(int) (Math.random()*40);

            default:
                    break;
            }
            return temp2;
        }
	
} // end class 



	
	

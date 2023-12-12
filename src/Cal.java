import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Data
public class Cal implements ActionListener , MouseListener  {

    double numA, numB;//they don't store large use bigClass to use it
    String equationString = "";//we use it if we have spacial situation in equation
    //JTextField equation
    String resultString;//we use it if we have spacial situation in equation
    //JTextField result
    String sign = "";//we declare it to make equation kind + or - or * .....

    private boolean simpleMood = true ;//if yu want to add extra panel and add another
    //buttons and mathematical operation yu can use these componets
//*************** frame detail *******************
    JFrame frame = new JFrame();
        final int FRAME_X = 1000;
        final int FRAME_Y = 100;
        int FRAME_WIDTH = 320 + 14;//14 is deference of frame
        final int FRAME_HEIGHT = 440;
        ImageIcon frameIcon = new ImageIcon("pics/calculator logo.png");

//*************** Background detail *******************
    JPanel background1;

    final int Background_X = 0;
    final int Background_Y = 0;
    int Background_WIDTH = FRAME_WIDTH - 14  ;
    final int Background_HEIGHT = FRAME_HEIGHT - 37;
    Color BackgroundColor = new Color(0x212529);//212529FF

//*************** monitor detail *******************

    JPanel monitor;

    final int monitor_X = 1;
    final int monitor_Y = 1;
    final int monitor_WIDTH = Background_WIDTH - monitor_X * 2;//because i want to have same gap with edges
    final int monitor_HEIGHT = 130;
    int size_of_monitor_font = 35;
    Font monitorFont = new Font("JetBrains Mono ExtraBold",Font.BOLD,size_of_monitor_font);
    Color monitorColor = new Color(0x495057);

    //*************** equation detail *******************
     public JTextField equation;//we declare it as public because maybe we need it in another class
    final int equation_X = monitor_X + 5;
    final int equation_Y = monitor_Y + 2;
    final int equation_WIDTH = monitor_WIDTH;//because i want to have same gap with edges
    final int equation_HEIGHT = 35;
    Color equationColor = new Color(0xf8f9fa);//f8f9fa

    //*************** equation detail *******************
    JTextField result;
    int result_WIDTH = 100;//because i want to have same gap with edges
    final int result_HEIGHT = 35;
    int result_X = monitor_WIDTH - result_WIDTH  ; //it is default it would change its method
    final int result_Y = monitor_Y + monitor_HEIGHT - 35 - 2;
    Color resultColor = new Color(0xf8f9fa);//f8f9fa

    //*************** num pad detail *******************

    //numbers 0 to 9 declare by arrays
     public JButton buttons[];
    final int Buttons_WIDTH = 50;
    final int Buttons_HEIGHT = 50;
    int size_of_buttons_font = 40;
    final int Buttongap = 2;
    Color ButtonsFontColor = new Color(0xadb5bd);
    Color ButtonsBackColor = new Color(0x6c757d);
    Color ButtonsBorderColor = new Color(0x343a40);
    Font buttonsFont = new Font("JetBrains Mono ExtraBold",Font.BOLD,size_of_buttons_font);

    //*************** all buttons detail *******************
    JButton dotButton = new JButton(),
            plusButton = new JButton(), minusButton = new JButton(),
            multiButton = new JButton(), divisionButton = new JButton(),
            equalButton = new JButton(),powerButton = new JButton(),
            sqrtButton = new JButton(),
            tanButton = new JButton(),
            cotButton = new JButton(),
            sinButton = new JButton(),
            cosButton = new JButton(),
            settingsButton = new JButton(),//setting button is embeded but not get spatial function ,
            // declare it in your favorite way
            persentButton = new JButton(),
            factorialButton = new JButton(),
            moodBut = new JButton(),//if yu want to add extra panel and add another
    //buttons and mathematical operation yu can use these componets
            negativeBut = new JButton(),
            clearButton = new JButton(),
            deleteButton = new JButton();
            ;


    public Cal()throws Exception {
    /*  column:
        |0|1|2|3|4|5|6 ...
      4:|7|8|9|√|p| |
row:  3:|4|5|6|×|÷| |
      2:|1|2|3|+|-| |
      1:|.|0| |+|=| |
     ...
        */

        frameCreation();
        BackGroundCreation();
        monitorCreation();
        equationCreation();
        resultCreation();
        numPadCreation();
        dotButtonCreation();
        clearButCreation();
        negativeCreation();
        deleteButtonCreation();



        //rows  start at 0 (left) to (right)
        //column  start at 0 (up) to (down)
        ButtonCreation(plusButton,
                "+",
                2, 3,
                Buttons_WIDTH,Buttons_HEIGHT + Buttons_HEIGHT + Buttongap,
                ButtonsBackColor);
        ButtonCreation(minusButton,
                "-",
                2, 4,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        ButtonCreation(equalButton,
                "=",
                1, 4,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);




        ButtonCreation(multiButton,
                "×",
                3, 3,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);

        ButtonCreation(divisionButton,
                "÷",
                3, 4,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);

        ButtonCreation(powerButton,
                "p",
                4, 4,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);

        ButtonCreation(sqrtButton,
                "√",
                4, 3,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);




        ButtonCreation(tanButton,
                "tan",
                4, 5,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        //because this size of font and size of height make text to ... so we change just font size
        //not method parameters and monitorFont field
        tanButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));

        ButtonCreation(cotButton, "" +
                        "cot",
                3, 5,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        //because this size of font and size of height make text to ... so we change just font size
        //not method parameters and monitorFont field
        cotButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));

        ButtonCreation(sinButton,
                "sin",
                2, 5,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        //because this size of font and size of height make text to ... so we change just font size
        //not method parameters and monitorFont feild
        sinButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));

        ButtonCreation(cosButton,
                "cot",
                1, 5,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        //because this size of font and size of height make text to ... so we change just font size
        //not method parameters and monitorFont field
        cosButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));


        ButtonCreation(settingsButton,
                "",
                5, 5,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        /************************************************************************************/
        //changing the size of picture to proper size
        BufferedImage settingImage = ImageIO.read(new File("pics/setting.icon.png"));
        Image imageOfSetting = settingImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon settingIcon = new ImageIcon(imageOfSetting);
        /************************************************************************************/
        settingsButton.setIcon(settingIcon);

        ButtonCreation(settingsButton,
                "",
                5, 5,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);


        ButtonCreation(persentButton,
                "%",
                5, 4,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);

        ButtonCreation(factorialButton,
                "x!",
                5, 3,
                Buttons_WIDTH,Buttons_HEIGHT,
                ButtonsBackColor);
        factorialButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,15));

        frame.add(equation);
        frame.add(result);
        frame.add(monitor);
        frame.add(background1);
        JPanel ss =new JPanel();


        frame.setVisible(true);

    }


    //for another button making same productive method would be okay
    /**
    * this method is optimised if you figure out the pattern in constructor and rows and column
    * this method prevent you of extra codding for adding and creating another
    * */
    private void ButtonCreation(JButton button,String title,int row,int column, int width, int height,Color colorOfBack) {
        //setting rows and columns to corect index base on pattern on structure
        //row is right
        width++;

        button.setText(title);
        button.setSize(width,height);
        button.setFocusable(false);
        button.setFont(monitorFont);
        button.setForeground(ButtonsFontColor);
        button.setBackground(colorOfBack.darker());
        button.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));
        button.addActionListener(this);
        button.addMouseListener(this);


        //it is some kind of architect for locating exactly
        //for changing horizontal position just change the number : number * (button.getWidth() + buttongap)
        /** pay attention at column and row  */

        button.setLocation(monitor_X + (column * (button.getWidth() + Buttongap) ) ,
                Background_HEIGHT - ( row * (Buttons_HEIGHT + Buttongap) ) - 5  );


        frame.add(button);
    }


    private void dotButtonCreation() {
        /*
        7  8  9
        4  5  6
        1  2  3
        .  < - "dotButton"
        */
        dotButton = new JButton(".");
        dotButton.setSize(Buttons_WIDTH,Buttons_HEIGHT);
        dotButton.setFocusable(false);
        dotButton.setFont(monitorFont);
        dotButton.setForeground(ButtonsFontColor);
        dotButton.setBackground(ButtonsBackColor.darker());
        dotButton.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));
        dotButton.addActionListener(this);
        dotButton.addMouseListener(this);
        //it is some kind of architect for locating exactly
        //for changing horizontal position just change the number : number * (clreatBut.getWidth() + Buttongap)
        dotButton.setLocation(monitor_X + (0 * (dotButton.getWidth() + Buttongap) ) ,
                Background_HEIGHT -  1 * (Buttons_HEIGHT + Buttongap) - 5);
        frame.add(dotButton);
    }
    private void clearButCreation() {/*
        7  8  9
        4  5  6
        1  2  3
        .  0  00   < - "dotButton"
        */
        clearButton  = new JButton("clear");
        clearButton.setSize(Buttons_WIDTH,Buttons_HEIGHT);
        clearButton.setFocusable(false);
        clearButton.setFont(monitorFont);
        clearButton.setForeground(ButtonsFontColor);
        clearButton.setBackground(ButtonsBackColor.darker());
        clearButton.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));
        clearButton.addActionListener(this);
        clearButton.addMouseListener(this);
        clearButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,15));

        //it is some kind of architect for locating exactly
        //for changing horizontal position just change the number : number * (clreatBut.getWidth() + Buttongap)
        clearButton.setLocation(monitor_X + (0 /* <= row number*/ * (Buttons_WIDTH + Buttongap) ) ,
                Background_HEIGHT - 5 /* <= column number*/* (Buttons_HEIGHT + Buttongap) - 5 /* <= space around number*/);
        frame.add(clearButton);
    }
    
    
    private void negativeCreation() {/*
        7  8  9
        4  5  6
        1  2  3
        .  0  00   < - "dotButton"
        */
        negativeBut  = new JButton("-(");
        negativeBut.setSize(Buttons_WIDTH,Buttons_HEIGHT);
        negativeBut.setFocusable(false);
        negativeBut.setFont(monitorFont);
        negativeBut.setForeground(ButtonsFontColor);
        negativeBut.setBackground(ButtonsBackColor.darker());
        negativeBut.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));
        negativeBut.addActionListener(this);
        negativeBut.addMouseListener(this);
        negativeBut.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,15));

        //it is some kind of architect for locating exactly
        //for changing horizontal position just change the number : number * (clreatBut.getWidth() + Buttongap)
        negativeBut.setLocation(monitor_X + (2 * (Buttons_WIDTH + Buttongap) ) ,
                Background_HEIGHT -  1 *(Buttons_HEIGHT + Buttongap) - 5);
        frame.add(negativeBut);
    }



    private void deleteButtonCreation() throws Exception{
        /***********************************************************************************/
        //changing the size of picture to proper size
        BufferedImage deleteImage = ImageIO.read(new File("pics/deletingImage.png"));
        Image iangeOfdeleteing = deleteImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon deleticon = new ImageIcon(iangeOfdeleteing);
        /************************************************************************************/
        deleteButton.setIcon(deleticon);


        deleteButton.setSize(Buttons_WIDTH,Buttons_HEIGHT);
        deleteButton.setFocusable(false);
        deleteButton.setFont(monitorFont);
        deleteButton.setForeground(ButtonsFontColor);
        deleteButton.setBackground(ButtonsBackColor.darker());
        deleteButton.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));
        deleteButton.addActionListener(this);
        deleteButton.addMouseListener(this);
        deleteButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,15));

        //it is some kind of architect for locating exactly
        //for changing horizontal position just change the number : number * (clreatBut.getWidth() + Buttongap)
        deleteButton.setLocation(monitor_X + (2 * (Buttons_WIDTH + Buttongap) ) ,
                Background_HEIGHT -  5 *(Buttons_HEIGHT + Buttongap) - 5);
        frame.add(deleteButton);
    }

    private void numPadCreation() {
        /*
        7  8  9
        4  5  6
        1  2  3
        .  0  00
        */


        //creating number button except 0 because of its position
        buttons = new JButton[10];/************************/
        for (int i = 0 ; i < buttons.length ; i++){
            buttons[i] = new JButton(Integer.toString(i));
            buttons[i].setSize(Buttons_WIDTH,Buttons_HEIGHT);
            buttons[i].setFocusable(false);
            buttons[i].setFont(buttonsFont);
            buttons[i].setForeground(ButtonsFontColor);
            buttons[i].setBackground(ButtonsBackColor);
            buttons[i].setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));
            buttons[i].addActionListener(this);
            buttons[i].addMouseListener(this);
        }

        //adding 0 to cal
        //for changing horizontal position just change the number : number * (buttons[0].getWidth() + Buttongap)
        buttons[0].setLocation(monitor_X + (1 * (buttons[0].getWidth() + Buttongap) ) ,
                Background_HEIGHT -  (Buttons_HEIGHT + Buttongap) - 5);
        frame.add(buttons[0]);


        //adding buttons 1 , 2 , 3
        //for changing horizontal position just change the number : number * (buttons[i].getWidth() + Buttongap)
        for (int i = 1 , y = 0; i <= 3;i++ , y++){
            //this equation is for changing automatically or organized when we change panel size
            buttons[i].setLocation(monitor_X + (y * (buttons[i].getWidth() + Buttongap) ) ,
                    Background_HEIGHT -  2 * (Buttons_HEIGHT + Buttongap) - 5);
            frame.add(buttons[i]);
        }

        //adding buttons 4 , 5 , 6
        //for changing horizontal position just change the number : number * (buttons[i].getWidth() + Buttongap)
        for (int i = 4 , y = 0; i <= 6;i++, y++){
            //this equation is for changing automatically or organized when we change panel size
            buttons[i].setLocation(monitor_X + (y * (buttons[i].getWidth() + Buttongap)) ,
                    Background_HEIGHT -  3 * (Buttons_HEIGHT + Buttongap) - 5 );
            frame.add(buttons[i]);
        }

        //adding buttons 7 , 8 , 9
        //for changing horizontal position just change the number : number * (buttons[i].getWidth() + Buttongap)
        for (int i = 7 , y = 0; i <= 9;i++, y++){
            //this equation is for changing automatically or organized when we change panel size
            buttons[i].setLocation(monitor_X + (y * (buttons[i].getWidth() + Buttongap)) ,
                    Background_HEIGHT - 4 * (Buttons_HEIGHT + Buttongap) - 5);
            frame.add(buttons[i]);
        }

    }

    private void resultCreation() {
        result = new JTextField();

        result.setEditable(false);
        result.setFont(monitorFont);
        result.setForeground(resultColor);
        result.setBounds(result_X,result_Y,result_WIDTH,result_HEIGHT);
        //result.setBackground(resultColor);
        result.setBorder(BorderFactory.createBevelBorder(5));
        result.setCaretColor(monitorColor.darker());
        result.setOpaque(false);

        System.out.println("result done!");
    }

    private void equationCreation() {
        equation = new JTextField();

        equation.setEditable(false);
        equation.setText(equationString);
        equation.setCaretPosition(equation.getText().length());//to set caret in the end of the text

        equation.setFont(monitorFont);
        equation.setForeground(equationColor);
        equation.setBounds(equation_X,equation_Y,equation_WIDTH,equation_HEIGHT);
        //equation.setBackground(equationColor);
        equation.setBorder(BorderFactory.createBevelBorder(5));

        equation.setCaretColor(equationColor.darker());
        equation.setOpaque(false);


        System.out.println("equation done!");
    }

    private void monitorCreation() {
        monitor = new JPanel();

        //monitor.setLayout(new GridLayout());
        monitor.setBounds(monitor_X,monitor_Y,monitor_WIDTH,monitor_HEIGHT);
        //monitor.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        monitor.setBackground(monitorColor);
        monitor.setOpaque(true);

        frame.add(monitor);
        System.out.println("monitor done!");

    }
    private void BackGroundCreation() {
        background1 = new JPanel();
//        JButton button = new JButton("but");
//        button.setBounds(50,450,150,70);
//        background1.add(button);

        background1.setBounds(Background_X,Background_Y,Background_WIDTH,Background_HEIGHT);
        //background1.setPreferredSize(new Dimension(Background_WIDTH,Background_HEIGHT));
        background1.setBackground(BackgroundColor);

        //background1.setLayout(new GridLayout());
        System.out.println("background done!");

    }

    private void frameCreation() throws Exception {
        frame.setTitle("calculator");
        frame.setIconImage(frameIcon.getImage());
        frame.setBounds(FRAME_X,FRAME_Y,FRAME_WIDTH,FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        //setResizable(true);
        //pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x737373));
        frame.setLayout(null);


        //setVisible(true);
        System.out.println("frame done!");

    }

    public void numButOperation(String number){
        //result.setText("");
        //equationString = equation.getText();
        //we add by equation's old data
        equation.setText(equation.getText().concat(number));
        equationString.concat(number);
        //equation.setText(equationString);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if you get button addListener or addKey... by pressing once it runs twice
        if (buttons[0] == e.getSource()) {

            numButOperation("0");

        } else if (buttons[1] == e.getSource()) {

            numButOperation("1");
        } else if (buttons[2] == e.getSource()) {

            numButOperation("2");
        } else if (buttons[3] == e.getSource()) {

            numButOperation("3");
        } else if (buttons[4] == e.getSource()) {
            numButOperation("4");
        } else if (buttons[5] == e.getSource()) {
            numButOperation("5");
        } else if (buttons[6] == e.getSource()) {
            numButOperation("6");
        } else if (buttons[7] == e.getSource()) {
            numButOperation("7");
        } else if (buttons[8] == e.getSource()) {
            numButOperation("8");
        } else if (buttons[9] == e.getSource()) {
            numButOperation("9");
        } else if (dotButton == e.getSource()) {
            equationString = equation.getText();
            if (equationString.charAt(equationString.length() - 1) != '.')
                equationString = equationString.concat(".");
            equation.setText(equationString);
        }

        //***********************************************************************************
        else if (clearButton == e.getSource()) {
            numA = 0 ;
            numB = 0;
            equation.setText("");
            result.setText("");
        } else if (equalButton == e.getSource()) {

            equalOperation();

        }
        //***********************************************************************************
        else if (plusButton == e.getSource()) {

            sign = "+";

            //it will omit the last sing if it exit
            //we use it as * and - because we have bug in there
            signChanging("+");
            System.out.println("591::  " + equationString);

            //equationString = equation.getText();
            if (equation.getText().charAt(equation.getText().length() - 1) != '+') {
                if(result.getText().equalsIgnoreCase("")) {

                    numA = Double.parseDouble(equation.getText());
                    System.out.println("numA = " + numA);

                }else{
                    numA = Double.parseDouble(result.getText());
                    equation.setText(result.getText() + "");
                    System.out.println("!!! numA = " + numA);
                }
                //equationString = equationString.concat();
                equation.setText(equation.getText().concat("+"));
                equationString = "";
            }
            
        } else if (minusButton == e.getSource()) {

            sign = "-";
            signChanging("-");

            //equationString = equation.getText();
            if (equation.getText().charAt(equation.getText().length() - 1) != '-') {
                if(result.getText().equalsIgnoreCase("")){
                    numA = Double.parseDouble(equation.getText());
                    System.out.println("numA = " + numA);}
                else{
                    numA = Double.parseDouble(result.getText());
                    equation.setText(result.getText() + "");
                    System.out.println("!!! numA = " + numA);
                }
                //equationString = equationString.concat();
                equation.setText(equation.getText().concat("-"));
                equationString = "";}


            } else if (multiButton == e.getSource()) {

            sign = "*";
            signChanging("×");
                //equationString = equation.getText();
                if (equation.getText().charAt(equation.getText().length() - 1) != '×' ||
                        equation.getText().charAt(equation.getText().length() - 1) != '*' ){
                    if(result.getText().equalsIgnoreCase("")){
                        numA = Double.parseDouble(equation.getText());
                        System.out.println("numA = " + numA);}
                    else{
                        numA = Double.parseDouble(result.getText());
                        equation.setText(result.getText() + "");
                        System.out.println("!!! numA = " + numA);
                    }
                //equationString = equationString.concat();
                equation.setText(equation.getText().concat("×"));
                equationString = "";
            }

            } else if (divisionButton == e.getSource()) {

            sign = "/";
            signChanging("÷");
                //equationString = equation.getText();
                if (equation.getText().charAt(equation.getText().length() - 1) != '÷' ||
                        equation.getText().charAt(equation.getText().length() - 1) != '÷')
                    if(result.getText().equalsIgnoreCase("")){
                        numA = Double.parseDouble(equation.getText());
                        System.out.println("numA = " + numA);}
                    else{
                        numA = Double.parseDouble(result.getText());
                        equation.setText(result.getText() + "");
                        System.out.println("!!! numA = " + numA);
                    }
            equation.setText(equation.getText().concat("÷"));
            equationString = "";
            }



        //***********************************************************************************
        else if (sqrtButton == e.getSource()) {
            sign = "√";

            //signChanging("÷"); //we do  not still need it
            //equationString = equation.getText();\

            equationString = equation.getText();
            equationString = equationString.concat("√");
            equation.setText("√" + equation.getText());


            equalOperation();
            }
        else if (powerButton == e.getSource()) {

            sign = "p";
            if (equation.getText().charAt(equation.getText().length() - 1) != 'p')
                if(result.getText().equalsIgnoreCase("")){
                    numA = Double.parseDouble(equation.getText());
                    System.out.println("numA = " + numA);}
                else{
                    numA = Double.parseDouble(result.getText());
                    equation.setText(result.getText() + "");
                    System.out.println("!!! numA = " + numA);
                }
            //equationString = equationString.concat();
            equation.setText(equation.getText().concat(" pow("));
            equalOperation();
            }
        //***********************************************************************************
        else if (tanButton == e.getSource()) {

                sign = "tan";
                //signChanging("÷"); //we do  not still need it
                //equationString = equation.getText();\

                equationString = equation.getText();
                equationString = equationString.concat("°:tan ");
                equation.setText(equationString);


                equalOperation();
            }
        else if (cotButton == e.getSource()) {
            sign = "cot";
            //signChanging("÷"); //we do  not still need it
            //equationString = equation.getText();\

            equationString = equation.getText();
            equationString = equationString.concat("°:cot ");
            equation.setText(equationString);


            equalOperation();
            }
        else if (sinButton == e.getSource()) {
            sign = "sin";
            //signChanging("÷"); //we do  not still need it
            //equationString = equation.getText();\

            equationString = equation.getText();
            equationString = equationString.concat("°:sin ");
            equation.setText(equationString);


            equalOperation();
            }
        else if (cosButton == e.getSource()) {
            sign = "cos";
            //signChanging("÷"); //we do  not still need it
            //equationString = equation.getText();\

            equationString = equation.getText();
            equationString = equationString.concat("°:cos ");
            equation.setText(equationString);


            equalOperation();
            }
        //***********************************************************************************

        else if (settingsButton == e.getSource()) {

            }
        else if (factorialButton == e.getSource()) {
            sign = "x!";

            equationString = equation.getText();
            equationString = equationString.concat("! ");
            equation.setText(equationString);

            equalOperation();
            }
        else if (persentButton == e.getSource()) {
            sign = "%";
            if (equation.getText().charAt(equation.getText().length() - 1) != 'p')
                if(result.getText().equalsIgnoreCase("")){
                    numA = Double.parseDouble(equation.getText());
                    System.out.println("numA = " + numA);}
                else{
                    numA = Double.parseDouble(result.getText());
                    equation.setText(result.getText() + "");
                    System.out.println("!!! numA = " + numA);
                }
            //equationString = equationString.concat();
            equation.setText(equation.getText().concat(" % of "));
            equalOperation();
            }


            if (moodBut == e.getSource()) {
                if (simpleMood == true) {
                    moodBut.setText("scientific mood");
                    frame.setSize(FRAME_WIDTH + 3 * (Buttons_WIDTH + Buttongap)
                            , FRAME_HEIGHT);
                    background1.setSize(FRAME_WIDTH + 3 * (Buttons_WIDTH + Buttongap),
                            Background_HEIGHT);
                    simpleMood = false;
                } else if (simpleMood == false) {
                    moodBut.setText("simple mood");
                    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
                    background1.setSize(Background_WIDTH, Background_HEIGHT);
                    simpleMood = true;

                }
            }else if(deleteButton == e.getSource()){
                equation.setText(
                        equation.getText().substring(0,
                                equation.getText().length()-1)
                );
            }else if(negativeBut == e.getSource()){
                equation.setText(equation.getText().concat(" -"));
                equationString.concat(" -");
            }

        }

//this method help when we shoud press sign to declare the equation type to omit the previous
    //sign and replace it with new and correct sign just for some of them not cos and tan and ...
    public void signChanging(String sign){
        if (sign.equalsIgnoreCase("+")){
            if (equation.getText().charAt(equation.getText().length() - 1) == '-' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '×' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '÷' ) {
                equation.setText(equation.getText().replace(
                        "" + equation.getText().charAt(equation.getText().length() - 1)
                        ,""));
            }
            
        }else if (sign.equalsIgnoreCase("-")){
            if (equation.getText().charAt(equation.getText().length() - 1) == '+' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '×' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '÷' ) {
                equation.setText(equation.getText().replace(
                        "" + equation.getText().charAt(equation.getText().length() - 1)
                        ,""));
            }
            
        }else if (sign.equalsIgnoreCase("×")){
            if (equation.getText().charAt(equation.getText().length() - 1) == '+' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '-' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '÷' ) {
                equation.setText(equation.getText().replace(
                        "" + equation.getText().charAt(equation.getText().length() - 1)
                        ,""));
            }
            
        }else if (sign.equalsIgnoreCase("÷")){
            if (equation.getText().charAt(equation.getText().length() - 1) == '+' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '-' ||
                    equation.getText().charAt(equation.getText().length() - 1) == '×' ) {
                equation.setText(equation.getText().replace(
                        "" + equation.getText().charAt(equation.getText().length() - 1)
                        ,""));
            }
        }
    }

//when we press = button program recognize what to do and the algorithm is in the method
    public void equalOperation(){
        System.out.println("equation = " + equation.getText());
        System.out.println("equationString = " + equationString);//it won't store last ca
        //nut numB is okey


        if (sign == "+"){
            System.out.println("\"+\" if acticated");
            //prepairing numB from text
            numB = Double.parseDouble(equation.getText().substring(
                    equation.getText().lastIndexOf("+"),
                    equation.getText().length()));
            System.out.println("numB  = "+numB);
            System.out.println("numA  = "+numA);
            System.out.println("result is "+
            calculatorOperation.BiOperator("+",numA,numB)
            );


            if((calculatorOperation.BiOperator("+",numA,numB) + "").contains(".0")){
                result.setText("" + calculatorOperation.BiOperator("+",numA,numB) );
                result.setText(result.getText().replace(".0",""));

            }
            else {
                result.setText("" + calculatorOperation.BiOperator("+",numA,numB) );
            }
        }


        else if (sign == "-"){
            System.out.println("\"-\" if acticated");
            numB = Double.parseDouble(equation.getText().substring(
                    equation.getText().lastIndexOf("-"),
                    equation.getText().length()));
            System.out.println("numB  = "+numB);//numB would store as negative number
            System.out.println("numB  = "+numA);//numB would store as negative number
            System.out.println("result is "+
                    calculatorOperation.BiOperator("-",numA,numB)
            );

            if((calculatorOperation.BiOperator("-",numA,numB) + "").contains(".0")){
                result.setText("" + calculatorOperation.BiOperator("-",numA,numB) );
                result.setText(result.getText().replace(".0",""));
            }
            else {
                result.setText("" + calculatorOperation.BiOperator("-",numA,numB) );
            }
        }


        else if (sign == "*"){
            System.out.println("\"×\" if acticated");
            numB = Double.parseDouble(equation.getText().substring(
                    equation.getText().lastIndexOf("×") + 1,
                    equation.getText().length()));
            System.out.println("numB  = "+numB);//numB would store as negative number
            System.out.println("result is "+
                    calculatorOperation.BiOperator("*",numA,numB)
            );


            if((calculatorOperation.BiOperator("*",numA,numB) + "").contains(".0")){
                result.setText("" + calculatorOperation.BiOperator("*",numA,numB) );
                result.setText(result.getText().replace(".0",""));
            }
            else {
                result.setText("" + calculatorOperation.BiOperator("*",numA,numB) );
            }
        }



        else if (sign == "/"){
            System.out.println("\"÷\" if activated");
            numB = Double.parseDouble(equation.getText().substring(
                    equation.getText().lastIndexOf("÷") + 1,
                    equation.getText().length()));
            System.out.println("numB  = "+numB);//numB would store as negative number
            System.out.println("result is "+
                    calculatorOperation.BiOperator("/",numA,numB)
            );

            if((calculatorOperation.BiOperator("/",numA,numB) + "").contains(".0")){
                result.setText("" + calculatorOperation.BiOperator("/",numA,numB) );
                result.setText(result.getText().replace(".0",""));
            }
            else {
                result.setText("" + calculatorOperation.BiOperator("/",numA,numB) );
            }

        }


        else if (sign == "x!" ) {
            System.out.println("\"x!\" if activated");
            numA = Double.parseDouble(equation.getText().substring(
                    0,
                    equation.getText().lastIndexOf("!")));
            System.out.println("numB  = "+numB);//numB would store as negative number
            System.out.println("result is in big "+
                    calculatorOperation.factorialBigInteger(numA) +
                    "\n\nresult is in double "+
                    calculatorOperation.factorialDouble(numA)
            );

            if((calculatorOperation.factorialDouble(numA)+"").contains(".0")){
                result.setText("" + calculatorOperation.factorialDouble(numA) );
                result.setText(result.getText().replace(".0",""));
            }
            else {
                result.setText("" + calculatorOperation.factorialDouble(numA) );
            }

        }

        //***********************************************************************************
        else if (sign == "tan"){
                System.out.println("\"tan\" if activated");//for debuging
                numA = Double.parseDouble(equation.getText().substring(
                    0,
                    equation.getText().indexOf("°:tan")));
                System.out.println("numA  = tan("+numA);//for debug in console
                System.out.println("result is "+
                    calculatorOperation.triangleOperation("tan",numA)//numB will be 0 and will
                    //not be used
                        +"\n"
                );

            if((calculatorOperation.BiOperator("tan",numA,numB) + "").contains(".0")){
                //BUG :   why persian
                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("tan",numA),2) );
                result.setText(result.getText().replace(".0",""));
            }
            else {

                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("tan",numA),2) );

            }
            //prepairng size of result if it big it will expand and mre bigger to show entire bum

        }

        else if (sign == "cot"){
                System.out.println("\"cot\" if activated");//for debuging
                numA = Double.parseDouble(equation.getText().substring(
                    0,
                    equation.getText().indexOf("°:cot")));
                System.out.println("numA  = cot("+numA);//for debug in console
                System.out.println("result is "+
                    calculatorOperation.triangleOperation("cot",numA)//numB will be 0 and will
                    //not be used
                        +"\n"
                );

            if((calculatorOperation.BiOperator("cot",numA,numB) + "").contains(".0")){
                //BUG :   why persian
                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("cot",numA),2) );
                result.setText(result.getText().replace(".0",""));
            }
            else {

                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("cot",numA),2) );

            }

        }

        else if (sign == "sin"){
                System.out.println("\"sin\" if activated");//for debuging
                numA = Double.parseDouble(equation.getText().substring(
                    0,
                    equation.getText().indexOf("°:sin")));
                System.out.println("numA  = sin("+numA);//for debug in console
                System.out.println("result is "+
                    calculatorOperation.triangleOperation("sin",numA)//numB will be 0 and will
                    //not be used
                        +"\n"
                );

            if((calculatorOperation.BiOperator("sin",numA,numB) + "").contains(".0")){
                //BUG :   why persian
                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("sin",numA),2) );
                result.setText(result.getText().replace(".0",""));
            }
            else {

                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("sin",numA),2) );

            }

        }

        else if (sign == "cos"){
                System.out.println("\"cos\" if activated");//for debuging
                numA = Double.parseDouble(equation.getText().substring(
                    0,
                    equation.getText().indexOf("°:cos")));
                System.out.println("numA  = cos("+numA);//for debug in console
                System.out.println("result is "+
                    calculatorOperation.triangleOperation("cos",numA)//numB will be 0 and will
                    //not be used
                        +"\n"
                );

            if((calculatorOperation.BiOperator("cos",numA,numB) + "").contains(".0")){
                //BUG :   why persian
                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("cos",numA),2) );
                result.setText(result.getText().replace(".0",""));
            }
            else {

                result.setText(""+calculatorOperation.round(calculatorOperation.triangleOperation("cos",numA),2) );

            }
        }
        //***********************************************************************************
        else if(sign == "√"){
            System.out.println("\"sqrt\" if activated");//for debuging

            numA = Double.parseDouble(equation.getText().substring(
                    equation.getText().indexOf("√") + 1,
                    equation.getText().length()));

            System.out.println("numA  = √"+numA);//for debug in console
            System.out.println("result is "+
                    calculatorOperation.monoOperator("√",numA,0)//numB will be 0 and will
                    //not be used
                    +"\n"
            );


                result.setText(""+calculatorOperation.round(calculatorOperation.monoOperator("√",numA,0),2) );

        }else if(sign == "p"){
            System.out.println("\"power\" if activated");//for debuging

            numB = Double.parseDouble(equation.getText().substring(
                    equation.getText().lastIndexOf("(") + 1,
                    equation.getText().length()));
            //when program execute this code you get exception and it is ok the algorithem
            //execute correctly

            System.out.println("numA  = power:"+numA);//for debug in console
            System.out.println("result is "+
                    calculatorOperation.monoOperator("p",numA,numB)
                    +"\n"
            );


                result.setText(""+calculatorOperation.round(calculatorOperation.monoOperator("p",numA,numB),2) );

        }
        else if(sign == "%"){
            System.out.println("\"percent\" if activated");//for debuging

            numB = Double.parseDouble(equation.getText().substring(
                    equation.getText().lastIndexOf("f") + 1,
                    equation.getText().length()));
            //when program execute this code you get exception and it is ok the algorithm
            //execute correctly

            System.out.println("numA  = power:"+numA);//for debug in console
            System.out.println("result is "+
                    calculatorOperation.monoOperator("%",numA,numB)
                    +"\n"
            );


                result.setText(""+calculatorOperation.round(calculatorOperation.monoOperator("%",numA,numB),5) );

        }
        //***********************************************************************************
        //prepairng size of result if it big it will expand and mre bigger to show entire bum
        if (result.getText().length() > 5 && result.getText().length() <= 8){
            result.setBounds(result_X - 70 ,result_Y,result_WIDTH + 70,result_HEIGHT);


            //this style of coding is because
            // if we want to change font we can change all usage of font from scratch by this coding
            result.setFont(new Font(monitorFont.getFontName(),monitorFont.getStyle(),monitorFont.getSize() ));

            }else if (result.getText().length() > 8){
            result.setBounds(result_X - 130 ,result_Y,result_WIDTH + 130,result_HEIGHT);


            //this style of coding is because
            // if we want to change font we can change all usage of font from scratch by this coding
            result.setFont(new Font(monitorFont.getFontName(),monitorFont.getStyle(),monitorFont.getSize() - 15));

            }
        else {
            //this style of coding is because
            // if we want to change font we can change all usage of font from scratch by this coding
            result.setFont(new Font(monitorFont.getFontName(),monitorFont.getStyle(),monitorFont.getSize()));
            result.setBounds(result_X ,result_Y,result_WIDTH ,result_HEIGHT);

            }
        
    }
    
    //this methos is declared just for better UI and change appearance
    public void whenMouseEnterNumButton(JButton button){
        button.setForeground(new Color(0xFFFFFF));
        button.setBackground(new Color(0x848E96));
        button.setBorder(BorderFactory.createLineBorder(BackgroundColor,3,false));


    }
    
    //this methos is declared just for better UI and change appearance
    public void whenMouseExitNumButton(JButton button){
        button.setForeground(ButtonsFontColor);
        button.setBackground(ButtonsBackColor);
        button.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor,2,false));

    }
    
    //Hhis methos is declared just for better UI and change appearance
    public void whenMouseEnterOperatingButton(JButton button){
        button.setForeground(new Color(0xFFFFFF));//6C757D
        button.setBackground(new Color(0xBB495057));
        button.setBorder(BorderFactory.createLineBorder(BackgroundColor,//212529
                3,
                false));

    }
    
    //this method is declared just for better UI and change appearance operation buttons
    public void whenMouseExitOperatingButton(JButton button) {
        button.setFont(monitorFont);
        button.setForeground(ButtonsFontColor);
        button.setBackground(ButtonsBackColor.darker());
        button.setBorder(BorderFactory.createLineBorder(ButtonsBorderColor, 2, false));

    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(buttons[0] == e.getSource()){
            whenMouseEnterNumButton(buttons[0]);
        }else if(buttons[1] == e.getSource()){
            whenMouseEnterNumButton(buttons[1]);
        }else if(buttons[2] == e.getSource()){
            whenMouseEnterNumButton(buttons[2]);
        }else if(buttons[3] == e.getSource()){
            whenMouseEnterNumButton(buttons[3]);
        }else if(buttons[4] == e.getSource()){
            whenMouseEnterNumButton(buttons[4]);;
        }else if(buttons[5] == e.getSource()){
            whenMouseEnterNumButton(buttons[5]);
        }else if(buttons[6] == e.getSource()){
            whenMouseEnterNumButton(buttons[6]);
        }else if(buttons[7] == e.getSource()){
            whenMouseEnterNumButton(buttons[7]);
        }else if(buttons[8] == e.getSource()){
            whenMouseEnterNumButton(buttons[8]);
        }else if(buttons[9] == e.getSource()){
            whenMouseEnterNumButton(buttons[9]);
        }

        else if(dotButton == e.getSource()) {
            whenMouseEnterOperatingButton(dotButton);
        }else if(clearButton == e.getSource()) {
            whenMouseEnterOperatingButton(clearButton);
        }


        else if(equalButton == e.getSource()){
            whenMouseEnterOperatingButton(equalButton);
        }else if(plusButton == e.getSource()){
            whenMouseEnterOperatingButton(plusButton);
        }else if(minusButton== e.getSource()){
            whenMouseEnterOperatingButton(minusButton);
        }else if(multiButton == e.getSource()){
            whenMouseEnterOperatingButton(multiButton);
        }else if(divisionButton == e.getSource()){
            whenMouseEnterOperatingButton(divisionButton);
        }

        else if(sqrtButton == e.getSource()){
            whenMouseEnterOperatingButton(sqrtButton);
        }else if(powerButton == e.getSource()){
            whenMouseEnterOperatingButton(powerButton);
        }

        else if(tanButton == e.getSource()){
            whenMouseEnterOperatingButton(tanButton);

        }else if(cotButton == e.getSource()){
            whenMouseEnterOperatingButton(cotButton);
        }else if(sinButton == e.getSource()){
            whenMouseEnterOperatingButton(sinButton);
        }else if(cosButton == e.getSource()){
            whenMouseEnterOperatingButton(cosButton);
        }else if(settingsButton == e.getSource()){
            whenMouseEnterOperatingButton(settingsButton);
        }
        else if(factorialButton == e.getSource()){
            whenMouseEnterOperatingButton(factorialButton);
        }
        else if(persentButton == e.getSource()){
            whenMouseEnterOperatingButton(persentButton);
        }
        else if(moodBut == e.getSource()){
            whenMouseEnterOperatingButton(moodBut);
        }else if(negativeBut == e.getSource()){
            whenMouseEnterOperatingButton(negativeBut);
        }else if( deleteButton == e.getSource()){
            whenMouseEnterOperatingButton(deleteButton);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(buttons[0] == e.getSource()){
            whenMouseExitNumButton(buttons[0]);
        }else if(buttons[1] == e.getSource()){
            whenMouseExitNumButton(buttons[1]);
        }else if(buttons[2] == e.getSource()){
            whenMouseExitNumButton(buttons[2]);
        }else if(buttons[3] == e.getSource()){
            whenMouseExitNumButton(buttons[3]);
        }else if(buttons[4] == e.getSource()){
            whenMouseExitNumButton(buttons[4]);;
        }else if(buttons[5] == e.getSource()){
            whenMouseExitNumButton(buttons[5]);
        }else if(buttons[6] == e.getSource()){
            whenMouseExitNumButton(buttons[6]);
        }else if(buttons[7] == e.getSource()){
            whenMouseExitNumButton(buttons[7]);
        }else if(buttons[8] == e.getSource()){
            whenMouseExitNumButton(buttons[8]);
        }else if(buttons[9] == e.getSource()){
            whenMouseExitNumButton(buttons[9]);
        }
        
        
        
        if(equalButton == e.getSource()){
            whenMouseExitOperatingButton(equalButton);
        }else if (plusButton == e.getSource()){
            whenMouseExitOperatingButton(plusButton);
        }else if(minusButton== e.getSource()){
            whenMouseExitOperatingButton(minusButton);
        }else if(multiButton == e.getSource()){
            whenMouseExitOperatingButton(multiButton);
        }else if(divisionButton == e.getSource()){
            whenMouseExitOperatingButton(divisionButton);
        }
        else if(dotButton == e.getSource()) {
            whenMouseExitOperatingButton(dotButton);
        }else if(clearButton == e.getSource()) {
            whenMouseExitOperatingButton(clearButton);
            clearButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,15));


        }


        else if(sqrtButton == e.getSource()){
            whenMouseExitOperatingButton(sqrtButton);
        }else if(powerButton == e.getSource()){
            whenMouseExitOperatingButton(powerButton);
        }


        else if(tanButton == e.getSource()){
            whenMouseExitOperatingButton(tanButton);
            tanButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));
        }else if(cotButton == e.getSource()){
            whenMouseExitOperatingButton(cotButton);
            cotButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));
        }else if(sinButton == e.getSource()){
            whenMouseExitOperatingButton(sinButton);
            sinButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));
        }else if(cosButton == e.getSource()){
            whenMouseExitOperatingButton(cosButton);
            cosButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,21));
        }

        else if(settingsButton == e.getSource()){
            whenMouseExitOperatingButton(settingsButton);
        }
        else if(factorialButton == e.getSource()){
            whenMouseExitOperatingButton(factorialButton);
            factorialButton.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,15));
        }
        else if(persentButton == e.getSource()){
            whenMouseExitOperatingButton(persentButton);
        }

        else if(moodBut == e.getSource()){
            whenMouseExitOperatingButton(moodBut);
            moodBut.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,20));
        }else if(negativeBut == e.getSource()){
            whenMouseExitOperatingButton(negativeBut);
            negativeBut.setFont(new Font("JetBrains Mono ExtraBold",Font.BOLD,20));
        }else if(deleteButton == e.getSource()){
            whenMouseExitOperatingButton(deleteButton);
        }
    }

}

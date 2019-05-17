import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Notebook extends JFrame implements ActionListener {
    JButton search, setColor;
    JPopupMenu popUp;
    private JMenuBar Menu;
    private JMenu MenuFile, MenuSetts, MenuHelp, MenuOptions, MenuOptions2;
    private JMenuItem mOpen, mSave, mClose, mset1, mset2, mProg, mOpt1, mOpt2, InnerOpt1, InnerOpt2,popCopy,popPaste,popJoin;
    JCheckBoxMenuItem BoxOpt1, BoxOpt2;
    JTextArea Notes;
    JComboBox colCombo;
    JColorChooser colorChooser;

    String chosenText;

    public Notebook() {
        setTitle("Notebook");
        setSize(615, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Menu = new JMenuBar();
        MenuFile = new JMenu("File");
        MenuSetts = new JMenu("Tools");
        MenuHelp = new JMenu("Help");
        setJMenuBar(Menu);
        Menu.add(MenuFile);
        Menu.add(MenuSetts);
        Menu.add(MenuHelp);
        MenuHelp.addActionListener(this);

        mOpen = new JMenuItem("Open", 'O');
        mOpen.addActionListener(this);
        mClose = new JMenuItem("Exit");
        mSave = new JMenuItem("Save");
        mSave.addActionListener(this);
        mClose.addActionListener(this);
        mClose.setAccelerator(KeyStroke.getKeyStroke("ctrl X")); //skrot

        MenuFile.add(mOpen);
        MenuFile.addSeparator();
        MenuFile.add(mSave);
        MenuFile.addSeparator();
        MenuFile.add(mClose);
        mClose.addActionListener(this);

        mset1 = new JMenuItem("Tool 1");
        MenuSetts.add(mset1);
        mset1.addActionListener(this);
        //mset1.setEnabled(false);
        mset2 = new JMenuItem("Tool 2");
        MenuSetts.add(mset2);
        mset2.addActionListener(this);

        MenuOptions = new JMenu("Additional options");
        InnerOpt1 = new JMenuItem("Additional option 1");
        InnerOpt2 = new JMenuItem("Additional option 2");
        MenuOptions.add(InnerOpt1);
        MenuOptions.add(InnerOpt2);
        MenuSetts.add(MenuOptions);

        MenuOptions = new JMenu("Options");
        MenuSetts.add(MenuOptions);
        mOpt1 = new JMenuItem("Option 1");
        MenuOptions.add(mOpt1);
        mOpt2 = new JCheckBoxMenuItem("Option 2");   //jcheckbox
        MenuOptions.add(mOpt2);
        mOpt2.addActionListener(this);


        mProg = new JMenuItem("Help ");
        MenuHelp.add(mProg);
        mProg.addActionListener(this);

        Notes = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(Notes);
        scrollPane.setBounds(50, 50, 500, 400);
        Notes.setBackground(new Color(204,204,204));
        add(scrollPane);

        search = new JButton("Find word");
        search.setBounds(50,470,150,20);
        add(search);
        search.addActionListener(this);
        popUp = new JPopupMenu();
        popCopy = new JMenuItem("Copy");
        popPaste= new JMenuItem("Paste");
        popJoin = new JMenuItem("Attach");
        popUp.add(popCopy);
        popCopy.addActionListener(this);
        popUp.add(popPaste);
        popPaste.addActionListener(this);
        popUp.add(popJoin);
        popJoin.addActionListener(this);
        Notes.setComponentPopupMenu(popUp);

        colCombo = new JComboBox();
        colCombo.setBounds(50,500,100,20);
        colCombo.addItem("Black");
        colCombo.addItem("Red");
        colCombo.addItem("Blue");
        colCombo.addItem("Yellow");
        colCombo.addItem("*Unvisible*");
        add(colCombo);
        colCombo.addActionListener(this);

        setColor = new JButton("Set different color");
        setColor.setBounds(150,500,206,20);
        add(setColor);
        setColor.addActionListener(this);

    }

    public static void main(String[] args) {
        Notebook m = new Notebook();
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mClose) {
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "WARNING", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                dispose();
            } else if (answer == JOptionPane.NO_OPTION || answer == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showInternalMessageDialog(null, "Program is going to turn off");
            }
        }
        if (source == mOpt2) {
            if (mOpt2.isSelected()) {
                mset2.setEnabled(true);
            } else if (!mOpt2.isSelected()) {
                mset2.setEnabled(false);
            }
        }
        if (source == mProg) {
            JOptionPane.showMessageDialog(this, "Version 1.0.\nALL RIGHTS RESERVED. KN®, CORP.", "", JOptionPane.WARNING_MESSAGE);
        }
        if (source == mset1) {
            String key = JOptionPane.showInputDialog("Enter password");
            int x = Integer.parseInt(key);
            if (x == 1999) {
                String input = JOptionPane.showInputDialog("Enter User's ID ");
                int ID = Integer.parseInt(input);
                int res = ID * (int) (Math.random() * 20) + 2;
                String output = String.format("%d10.", res);
                JOptionPane.showMessageDialog(null, "Twój numer: " + output);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid password!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (source == mOpen) {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {  //Jak juz klikniemy na dany plik to...
                File file = fc.getSelectedFile();
                //JOptionPane.showMessageDialog(null,"Wybrany plik: "+file.getName() +"\nScieżka: "+file.getAbsolutePath());
                try {
                    Scanner in = new Scanner(file);
                    while (in.hasNext()) {
                        Notes.append(in.nextLine() + "\n");
                    }
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                }
            }
        }
        if (source == mSave) {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                // JOptionPane.showMessageDialog(null,"Scieżka pliku: "+file.getAbsolutePath());
                try {
                    PrintWriter printWriter = new PrintWriter(file);
                    Scanner in = new Scanner(Notes.getText());
                    while (in.hasNext()) {
                        printWriter.println(in.nextLine() + "\n");
                        printWriter.close();
                    }
                } catch (FileNotFoundException error) {
                    error.printStackTrace();
                }

            }
        }
        if(source==search) {
            String word = JOptionPane.showInputDialog("Podaj wyraz ");
            //String word = searchWord.getText();
            String text = Notes.getText();
            String results="";
            int i =0;
            int index ;
            int startIndex=0;
            while((index =text.toLowerCase().indexOf(word.toLowerCase(),startIndex))!= -1)
            {
                startIndex=index + word.length();
                i++;
                results = results + " " + index;
            }
            JOptionPane.showMessageDialog(null,word + " Appear "+i+" times: "+ "\nOn positions: "+results);
        }
        if(source==popCopy){
             chosenText = Notes.getSelectedText();
        }
        if(source==popPaste){
            Notes.insert(chosenText,Notes.getCaretPosition()); //dodaje na pozycje karetki
        }
        if(source==popJoin){
            Notes.append("\n"+chosenText);                      // dodaje na koniec pliku
        }
        if(source==colCombo){
            String color = colCombo.getSelectedItem().toString();
            if(color.equals("Black"))
                Notes.setForeground(Color.black);
            else if(color.equals("Yellow"))
                Notes.setForeground(Color.yellow);
            else if(color.equals("Blue"))
                Notes.setForeground(Color.blue);
            else if(color.equals("Red"))
                Notes.setForeground(Color.red);
            else if(color.equals("*Invisible*"))
                Notes.setForeground(Color.white);
        }
        if(source==setColor){
            Color chosenColor=JColorChooser.showDialog(null,"Set different color",Color.black);
            Notes.setForeground(chosenColor);

        }
    }
}
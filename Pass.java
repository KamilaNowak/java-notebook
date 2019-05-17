import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Pass extends JFrame implements ActionListener
{
    JPasswordField passwordField;
    PasswordPanel pPanel;
    private JTextArea Notes;
    private JScrollPane scrollPane;
    private JButton addUser;
    public Pass()
    {
        setTitle("Password Checher");
        setSize(400,500);
        setLayout(null);

       /* passwordField = new JPasswordField();
        passwordField.setBounds(50,300 ,150,20);
        passwordField.addActionListener(this);
        add(passwordField); */

        Notes = new JTextArea();
        Notes.setWrapStyleWord(true);
        scrollPane = new JScrollPane(Notes);
        scrollPane.setBounds(50,50,300,200);
        add(scrollPane);

        addUser = new JButton("Dodaj użytkownika");
        addUser.setBounds(50,350,150,20);
        add(addUser);
        addUser.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    //JOptionPane.showMessageDialog(null, String.valueOf(passwordField.getPassword()));
        Object source = e.getSource();
        if(source==addUser) {
            if (pPanel == null)
                pPanel = new PasswordPanel(this);
            pPanel.setVisible(true);
         }
        pPanel.setFocus();
        if(pPanel.isGood()){
         Notes.append(pPanel.getUser()+ " , ");
         Notes.append(pPanel.getPassword()+"\n");
        }
    }
    public static void main(String[] args)
    {
        Pass p = new Pass();
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
    }
}
class PasswordPanel extends JDialog implements ActionListener
{
    private JLabel labelUser,labelPass;
    private JTextField textFieldUser;
    JPasswordField textFieldPassword;
    private JButton ok,cancel;
    private boolean goodData;

    public PasswordPanel(JFrame owner) //podokienko z JFrame
    {
        super(owner,"Wprowdzanie hasła",true);
        setSize(400,300);

        setLayout(null);

        labelUser = new JLabel("User");
        labelUser.setBounds(20,20,100,20);
        add(labelUser);
        textFieldUser = new JTextField();
        textFieldUser.setBounds(60,20,170,20);
        add(textFieldUser);
        labelPass = new JLabel("Hasło");
        labelPass.setBounds(20,50,100,20);
        add(labelPass);
        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(60,50,170,20);
        add(textFieldPassword);
        ok = new JButton("Zatwierdź");
        ok.setBounds(30,90,100,20);
        add(ok);
        ok.addActionListener(this);
        cancel = new JButton("Odrzuć");
        cancel.setBounds(135,90,100,20);
        cancel.addActionListener(this);
        add(cancel);
    }
    public String getUser(){
        return  textFieldUser.getText();
    }
    public String getPassword(){
        return new String(textFieldPassword.getPassword());
    }
    public boolean isGood(){
        return goodData;
    }
    public void setFocus(){
        textFieldUser.requestFocusInWindow();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();
        if(source==ok){
            goodData=true; }
        else {
            goodData = false; }
        setVisible(false);
    }
}

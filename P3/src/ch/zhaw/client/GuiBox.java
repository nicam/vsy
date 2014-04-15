package ch.zhaw.client;

import ch.zhaw.CommandInterpreter;
import ch.zhaw.server.ContextMagic;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class GuiBox extends JFrame implements ActionListener {

    private String serverName = null;

    private MenuItem clear;

    private TextArea output;
    private TextField input;

    private Button submit;
    private CommandInterpreter server = null;

    public GuiBox() {
        this.setTitle("GuiBox");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar;
        Menu menuEdit;

        menuBar = new MenuBar();
        this.setMenuBar(menuBar);

        // Edit Menu
        menuBar.add(menuEdit = new Menu("Edit"));
        menuEdit.add(this.clear = new MenuItem("Clear"));
        this.clear.addActionListener(this);

        // Ouput Container
        this.add(this.output = new TextArea(), "Center");
        this.output.setForeground(Color.white);
        this.output.setBackground(new Color(52, 73, 94));
        this.output.setFont(new Font("Consolas", 2, 14));
        this.output.setEditable(true);

        Panel localPanel = new Panel();
        localPanel.setLayout(new BorderLayout());
        add(localPanel, "South");

        this.input = new TextField(200);
        this.input.setForeground(Color.black);
        this.input.setBackground(Color.white);
        this.input.addActionListener(this);
        localPanel.add(this.input, "Center");

        this.submit = new Button("Submit");
        localPanel.add(this.submit, "East");
        this.submit.addActionListener(this);

        this.setSize(600, 500);
        this.setVisible(true);
        this.input.requestFocus();
    }

    public static void main(String[] args) {
        GuiBox localGuiBox = new GuiBox();
    }

    private void connectToRmiServer(){
        try {
            Context context = ContextMagic.createContext(Environment.host, Environment.port);
            Object rmiServer = context.lookup("EchoRmiServer");
            if(rmiServer instanceof CommandInterpreter){
                this.server = (CommandInterpreter) rmiServer;
                this.setTitle("Connected to " + this.serverName);
                this.clear();
            }
            this.server = (CommandInterpreter) PortableRemoteObject.narrow(rmiServer, CommandInterpreter.class);
        }catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private void interpret(String input) {
        try {
            MyBooringObject obj = new MyBooringObject();
            obj.setTest(input);

            if (this.server != null) {
                try {
                    this.server.interpret(obj);

                    this.output.append(obj.getTest() + "\n");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    this.server = null;
                }
            } else {
                this.connectToRmiServer();
                this.interpret(input);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        this.output.setText("");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.submit || actionEvent.getSource() == this.input) {
            this.interpret(this.input.getText());
        }

        if (actionEvent.getSource() == this.clear) {
            this.clear();
        }
    }

    public void log(String paramString) {
        System.out.println(paramString);
    }
}
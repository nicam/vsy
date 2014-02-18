package ch.zhaw.client;

import ch.zhaw.CommandInterpreter;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GuiBox extends JFrame implements ActionListener {

    private String serverName = null;


    private MenuItem clear;
    private MenuItem connect;

    private TextArea output;
    private TextField input;

    private Button submit;
    private CommandInterpreter server = null;

    public GuiBox() {
        this.setTitle("GuiBox");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar;

        Menu menuEdit;
        Menu menuServer;

        menuBar = new MenuBar();
        this.setMenuBar(menuBar);

        // Edit Menu
        menuBar.add(menuEdit = new Menu("Edit"));
        menuEdit.add(this.clear = new MenuItem("Clear"));
        this.clear.addActionListener(this);

        // Server Menu
        menuBar.add(menuServer = new Menu("Server"));
        menuServer.add(this.connect = new MenuItem("Connect ..."));
        this.connect.addActionListener(this);

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
        String rmiServerName = (String)JOptionPane.showInputDialog("Bitte RmiServernamen eingeben:");
        try {
            Object rmiServer = Naming.lookup(rmiServerName);
            if(rmiServer instanceof CommandInterpreter){
                this.server = (CommandInterpreter) rmiServer;
                this.serverName = rmiServerName;
                this.setTitle("Connected to " + this.serverName);
                this.clear();
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void interpret(String paramString) {
        if (this.server != null) {
            try {
                String result = this.server.interpret(paramString);
                this.output.append(result + "\n");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            this.connectToRmiServer();
            this.interpret(paramString);
        }
    }

    private void clear() {
        this.output.setText("");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.submit || actionEvent.getSource() == this.input) {
            this.interpret(this.input.getText());
        }

        if (actionEvent.getSource() == this.connect) {
            this.connectToRmiServer();
        }

        if (actionEvent.getSource() == this.clear) {
            this.clear();
        }
    }

    public void log(String paramString) {
        System.out.println(paramString);
    }
}
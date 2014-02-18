package ch.zhaw;

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

    private void connectToServer() {
        FileDialog fileDialog = new FileDialog(this, "Please select server class", 0);
        fileDialog.setVisible(true);

        String paramString = fileDialog.getFile();

        if (paramString == null) {
            return;
        }
        paramString = paramString.replace(".class", "");

        log("Selected class name = " + paramString);

        String packageName;
        try {
            packageName = this.getClass().getPackage().getName();
            log("Our own package is [" + packageName + "]");
            paramString = packageName + "." + paramString;
        } catch (Exception localException2) {
            log("No package Name");
        }

        log("complete class name appears to be " + paramString);

        try {
            Object object = Class.forName(paramString).newInstance();
            if ((object instanceof CommandInterpreter)) {
                this.server = (CommandInterpreter) object;
                this.serverName = paramString;
                this.setTitle("Connected to " + this.serverName);
                this.clear();
            } else {
                log("Class " + paramString + " is not instance of CommandInterpreter");
            }
        } catch (Exception localException1) {
            log("Problem connecting to " + paramString + " (" + localException1.toString() + ")");
        }
    }

    private void interpret(String paramString) {
        if (this.server != null) {
            this.output.append(this.server.interpret(paramString) + "\n");
        } else {
            this.connectToServer();
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
            this.connectToServer();
        }

        if (actionEvent.getSource() == this.clear) {
            this.clear();
        }
    }

    public void log(String paramString) {
        System.out.println(paramString);
    }
}
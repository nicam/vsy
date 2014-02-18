import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GuiBox extends Frame
        implements WindowListener, ActionListener, ItemListener
{
    private final String ich = "GuiBox-D ";
    private String filename = null;
    private String servername = null;
    private MenuBar menubalken;
    private Menu menudatei;
    private Menu menuedit;
    private Menu menuserver;
    private Menu menufeeder;
    private MenuItem oeffnen;
    private MenuItem speichern;
    private MenuItem speichernals;
    private MenuItem quit;
    private MenuItem clear;
    private MenuItem verbinden;
    private MenuItem feedfromfile;
    private MenuItem feedselection;
    private TextArea ausgabe;
    private TextField eingabe;
    private String commandline;
    private Button enterTaste;
    private Choice history;
    private final int hcount = 6;
    private Class ServerClass;
    private CommandInterpreter server = null;

    public GuiBox(String[] paramArrayOfString)
    {
        setTitle("GuiBox-D ");
        say("Sali.");

        if (paramArrayOfString.length > 0) {
            connectToServer(paramArrayOfString[0]);
        }

        this.menubalken = new MenuBar();
        setMenuBar(this.menubalken);

        this.menubalken.add(this.menudatei = new Menu("File"));
        this.menudatei.add(this.oeffnen = new MenuItem("Open ..."));
        this.oeffnen.addActionListener(this);
        this.menudatei.add(this.speichern = new MenuItem("Save"));
        this.speichern.addActionListener(this);
        this.menudatei.add(this.speichernals = new MenuItem("Save as ..."));
        this.speichernals.addActionListener(this);
        this.menudatei.addSeparator();
        this.menudatei.add(this.quit = new MenuItem("Quit"));
        this.quit.addActionListener(this);
        this.menubalken.add(this.menuedit = new Menu("Edit"));
        this.menuedit.add(this.clear = new MenuItem("Clear"));
        this.clear.addActionListener(this);
        this.menubalken.add(this.menuserver = new Menu("Server"));
        this.menuserver.add(this.verbinden = new MenuItem("Connect ..."));
        this.verbinden.addActionListener(this);
        this.menubalken.add(this.menufeeder = new Menu("Feed"));
        this.menufeeder.add(this.feedfromfile = new MenuItem("From File ..."));
        this.feedfromfile.addActionListener(this);
        this.menufeeder.add(this.feedselection = new MenuItem("From Selection"));
        this.feedselection.addActionListener(this);

        add(this.ausgabe = new TextArea(), "Center");
        this.ausgabe.setBackground(new Color(150, 255, 150));
        this.ausgabe.setFont(new Font("Courier", 2, 14));
        this.ausgabe.setEditable(true);

        Panel localPanel = new Panel();
        localPanel.setLayout(new BorderLayout());
        add(localPanel, "South");

        this.eingabe = new TextField(200);
        this.eingabe.setBackground(new Color(255, 255, 100));
        this.eingabe.addActionListener(this);
        localPanel.add(this.eingabe, "Center");
        this.enterTaste = new Button("<- Enter");
        localPanel.add(this.enterTaste, "East");
        this.enterTaste.addActionListener(this);

        this.history = new Choice();
        localPanel.add(this.history, "South");
        this.history.addItemListener(this);
        this.history.add("*");

        addWindowListener(this);
        setSize(600, 500);
        setVisible(true);
        this.eingabe.requestFocus();
    }

    private void connectToServer(String paramString)
    {
        if (paramString == null) {
            localObject1 = new FileDialog(this, "Please select server class", 0);

            ((FileDialog)localObject1).setVisible(true);

            paramString = ((FileDialog)localObject1).getFile();
            int i = paramString.indexOf(".class");
            if (i >= 0) paramString = paramString.substring(0, i); else
                paramString = null;
            say("Selected class name = " + paramString);

            String str2 = null;
            try { str2 = getClass().getPackage().getName(); } catch (Exception localException2) {
                str2 = null;
            }say("Our own package is [" + str2 + "]");

            if (str2 != null) paramString = str2 + "." + paramString;
            say("complete class name appears to be " + paramString);
        }

        Object localObject1 = "";
        if (paramString != null)
            try {
                say("Starting the tricky part ...");
                say("Class Name to connect to is " + paramString);
                Object localObject2 = Class.forName(paramString).newInstance();
                if ((localObject2 instanceof CommandInterpreter)) {
                    this.server = ((CommandInterpreter)localObject2);
                    this.servername = paramString;
                } else {
                    localObject1 = "(" + paramString + ".class does not implement required interface.)";
                }
            } catch (Exception localException1) { say("Problem connecting to " + paramString + " (" + localException1.toString() + ")"); }

        String str1;
        if (this.server != null) {
            if (this.servername == paramString) str1 = "connected to " + paramString; else
                str1 = "still connected to " + this.servername + " " + (String)localObject1;
        }
        else str1 = "not connected " + (String)localObject1;

        setTitle("GuiBox-D " + str1);

        interpret("pronto ?");
    }

    private void dispatchCommand()
    {
        this.commandline = this.eingabe.getText();

        if (!this.history.getItem(0).equals(this.commandline)) this.history.insert(this.commandline, 0);

        for (int i = this.history.getItemCount(); i > 6; i--) this.history.remove(6);

        interpret(this.commandline);
    }

    private void interpret(String paramString)
    {
        if (this.server != null) {
            this.ausgabe.append(this.server.interpret(paramString) + "\n");
        }
        else {
            connectToServer(null);
            interpret(paramString);
        }
    }

    private void fileOpener(String paramString)
    {
        Object localObject;
        if (paramString == null) {
            localObject = new FileDialog(this, "Open File ...");
            ((FileDialog)localObject).setVisible(true);
            paramString = ((FileDialog)localObject).getDirectory() + ((FileDialog)localObject).getFile();
        }
        try
        {
            localObject = new BufferedReader(new FileReader(paramString));
            this.ausgabe.setText("");
            String str;
            while ((str = ((BufferedReader)localObject).readLine()) != null) {
                this.ausgabe.append(str + "\n");
            }
            ((BufferedReader)localObject).close();
            this.filename = paramString; } catch (Exception localException) {
            say(localException.toString());
        }
    }

    private void fileSaver(String paramString)
    {
        Object localObject;
        if (paramString == null) {
            localObject = new FileDialog(this, "Save ...", 1);

            ((FileDialog)localObject).setVisible(true);
            paramString = ((FileDialog)localObject).getDirectory() + ((FileDialog)localObject).getFile();
            this.filename = paramString;
        }
        try
        {
            localObject = new PrintWriter(new FileWriter(paramString));
            ((PrintWriter)localObject).print(this.ausgabe.getText());
            ((PrintWriter)localObject).close(); } catch (Exception localException) {
            say(localException.toString());
        }
    }

    private void feedfrom(String paramString)
    {
        Object localObject;
        if (paramString == null) {
            localObject = new FileDialog(this, "File to feed from ...");
            ((FileDialog)localObject).setVisible(true);
            paramString = ((FileDialog)localObject).getDirectory() + ((FileDialog)localObject).getFile();
        }
        try
        {
            localObject = new BufferedReader(new FileReader(paramString));
            String str;
            while ((str = ((BufferedReader)localObject).readLine()) != null) {
                interpret(str);
            }
            ((BufferedReader)localObject).close(); } catch (Exception localException) {
            say(localException.toString());
        }
    }

    private void feedmarked() {
        String str = this.ausgabe.getSelectedText();

        StringTokenizer localStringTokenizer = new StringTokenizer(str, "\n");

        while (localStringTokenizer.hasMoreTokens()) interpret(localStringTokenizer.nextToken());
    }

    public void itemStateChanged(ItemEvent paramItemEvent)
    {
        if (paramItemEvent.getSource() == this.history)
            this.eingabe.setText(this.history.getSelectedItem());
    }

    public void actionPerformed(ActionEvent paramActionEvent)
    {
        if (paramActionEvent.getSource() == this.enterTaste) dispatchCommand();

        if (paramActionEvent.getSource() == this.eingabe) { dispatchCommand(); this.eingabe.setText("");
        }

        if (paramActionEvent.getSource() == this.verbinden) connectToServer(null);

        if (paramActionEvent.getSource() == this.clear) this.ausgabe.setText("");

        if (paramActionEvent.getSource() == this.feedfromfile) feedfrom(null);
        if (paramActionEvent.getSource() == this.feedselection) feedmarked();

        if (paramActionEvent.getSource() == this.oeffnen) fileOpener(null);
        if (paramActionEvent.getSource() == this.speichern) fileSaver(this.filename);
        if (paramActionEvent.getSource() == this.speichernals) fileSaver(null);
        if (paramActionEvent.getSource() == this.quit) {
            say("... und tschüss.");
            System.exit(0);
        }
    }

    public void windowClosing(WindowEvent paramWindowEvent)
    {
        say("... und tschüss.");
        System.exit(0);
    }
    public void windowOpened(WindowEvent paramWindowEvent) {
    }
    public void windowClosed(WindowEvent paramWindowEvent) {
    }
    public void windowIconified(WindowEvent paramWindowEvent) {
    }
    public void windowDeiconified(WindowEvent paramWindowEvent) {
    }
    public void windowActivated(WindowEvent paramWindowEvent) {
    }
    public void windowDeactivated(WindowEvent paramWindowEvent) {
    }
    public static void main(String[] paramArrayOfString) { GuiBox localGuiBox = new GuiBox(paramArrayOfString); }

    public void say(String paramString) {
        System.out.println(paramString);
    }
}
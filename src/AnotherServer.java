/** AnyServer 
 @author E. Mumprecht
 @version 1.0 -- Gerüst für irgendeinen "Server"
 */

public class AnotherServer implements CommandInterpreter {

    private StringBuffer result;

    //----- Es braucht unbedingt den parameterlosen Konstruktor!
    public AnotherServer(){}

    //----- Was folgt, implementiert das CommandInterpreter Interface.

    public String interpret(String command) {
        result = new StringBuffer();
        result.append("* Die Eingabe war <");
        result.append(command);
        result.append(">");
        return(result.toString());
    }

}//AnyServer
      
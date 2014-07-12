import java.io.IOException;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
public class AntlrTest {
    public static void main (String[] args) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(System.in);
            Ex2Lexer lexer = new Ex2Lexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Ex2Parser parser = new Ex2Parser(tokens);
            parser.prog();
        } catch (IOException e) {}
        catch (RecognitionException e) {}
    }
}

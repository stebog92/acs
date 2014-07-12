import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;


public class __Test__ {

    public static void main(String args[]) throws Exception {
        TelLexer lex = new TelLexer(new ANTLRFileStream("/home/mihai/CPL/lab1/output/__Test___input.txt", "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        TelParser g = new TelParser(tokens, 49100, null);
        try {
            g.stat();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
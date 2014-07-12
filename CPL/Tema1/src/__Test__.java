import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;


public class __Test__ {

    public static void main(String args[]) throws Exception {
        LCPLTreeBuilderLexer lex = new LCPLTreeBuilderLexer(new ANTLRFileStream("/home/mihai/CPL/Tema1/src/__Test___input.txt", "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        LCPLTreeBuilderParser g = new LCPLTreeBuilderParser(tokens, 49100, null);
        try {
            g.program();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
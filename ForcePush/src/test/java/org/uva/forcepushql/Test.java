package org.uva.forcepushql;

import org.antlr.v4.runtime.*;
import org.uva.forcepushql.antlr.GrammarLexer;
import org.uva.forcepushql.antlr.GrammarParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        File testFile = new File("C:\\Users\\georg\\Documents\\GitHub\\endless-ql\\ForcePush\\src\\main\\resources\\antlr\\TestInput.txt");
        InputStream stream = new FileInputStream(testFile);
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(stream);
        // create a lexer that feeds off of input CharStream
        GrammarLexer lexer = new GrammarLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        GrammarParser parser = new GrammarParser(tokens);
        // begin parsing at rule x
        parser.formStructure();
    }
}
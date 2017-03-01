package com.github.mshockwave.antlr4sqlparser;

import com.github.mshockwave.antlr4sqlparser.antlr.SQLiteLexer;
import com.github.mshockwave.antlr4sqlparser.antlr.SQLiteParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

        while(true){
            System.out.print(">>");
            if(!scanner.hasNextLine()) break;
            String inputLine = scanner.nextLine();
            System.out.println("");

            try {
                ByteArrayInputStream bytesStream = new ByteArrayInputStream(inputLine.getBytes());
                SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(bytesStream));
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                SQLiteParser parser = new SQLiteParser(tokens);
                ParseTree parseTree = parser.parse();

                DemoListener listener = new DemoListener();
                parseTreeWalker.walk(listener, parseTree);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.sun.tools.javac.parser;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Position;
import java.util.Queue;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VirtualParser extends JavacParser {
    private boolean hasErrors;

    public VirtualParser(JavacParser javacParser) {
        super(javacParser, new VirtualScanner(javacParser.S));
    }

    public static boolean tryParse(JavacParser javacParser, Consumer<JavacParser> consumer) {
        try {
            consumer.accept(new VirtualParser(javacParser));
            return true;
        } catch (AssertionError unused) {
            return false;
        }
    }

    public boolean hasErrors() {
        return this.hasErrors;
    }

    @Override // com.sun.tools.javac.parser.JavacParser
    public void reportSyntaxError(int i, JCDiagnostic.Error error) {
        this.hasErrors = true;
    }

    @Override // com.sun.tools.javac.parser.JavacParser
    public JCTree.JCErroneous syntaxError(int i, JCDiagnostic.Error error) {
        this.hasErrors = true;
        return this.F.Erroneous();
    }

    @Override // com.sun.tools.javac.parser.JavacParser
    public void reportSyntaxError(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Error error) {
        this.hasErrors = true;
    }

    public static class VirtualScanner implements Lexer {
        Lexer S;
        int offset = 0;
        private Tokens.Token prevToken;
        private Tokens.Token token;

        public VirtualScanner(Lexer lexer) {
            while (lexer instanceof VirtualScanner) {
                VirtualScanner virtualScanner = (VirtualScanner) lexer;
                Lexer lexer2 = virtualScanner.S;
                this.offset += virtualScanner.offset;
                lexer = lexer2;
            }
            this.S = lexer;
            this.token = lexer.token();
            this.prevToken = this.S.prevToken();
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public int errPos() {
            return this.S.errPos();
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public Queue<Tokens.Comment> getDocComments() {
            return this.S.getDocComments();
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public Position.LineMap getLineMap() {
            return this.S.getLineMap();
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public void lintWarning(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.LintWarning lintWarning) {
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public void nextToken() {
            this.prevToken = this.token;
            this.offset++;
            this.token = token();
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public Tokens.Token prevToken() {
            return this.prevToken;
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public void setPrevToken(Tokens.Token token) {
            this.prevToken = token;
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public Tokens.Token split() {
            Tokens.Token[] tokenArrSplit = this.token.split(((Scanner) this.S).tokens);
            this.prevToken = tokenArrSplit[0];
            Tokens.Token token = tokenArrSplit[1];
            this.token = token;
            return token;
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public Tokens.Token token(int i) {
            return this.S.token(this.offset + i);
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public void errPos(int i) {
            this.S.errPos(i);
        }

        @Override // com.sun.tools.javac.parser.Lexer
        public Tokens.Token token() {
            return token(0);
        }
    }

    @Override // com.sun.tools.javac.parser.JavacParser
    public JCTree.JCErroneous syntaxError(int i, List<? extends JCTree> list, JCDiagnostic.Error error) {
        this.hasErrors = true;
        return this.F.Erroneous();
    }
}

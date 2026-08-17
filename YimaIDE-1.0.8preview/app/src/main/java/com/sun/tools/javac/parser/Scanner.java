package com.sun.tools.javac.parser;

import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Position;
import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Scanner implements Lexer {
    private final Queue<Tokens.Comment> docComments;
    private Tokens.Token prevToken;
    private final List<Tokens.Token> savedTokens;
    private Tokens.Token token;
    private final JavaTokenizer tokenizer;
    protected Tokens tokens;

    /* JADX INFO: renamed from: com.sun.tools.javac.parser.Scanner$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle;

        static {
            int[] iArr = new int[Tokens.Comment.CommentStyle.values().length];
            $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle = iArr;
            try {
                iArr[Tokens.Comment.CommentStyle.JAVADOC_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[Tokens.Comment.CommentStyle.JAVADOC_LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public Scanner(ScannerFactory scannerFactory, JavaTokenizer javaTokenizer) {
        this.savedTokens = new ArrayList();
        this.docComments = new ArrayDeque();
        this.tokenizer = javaTokenizer;
        this.tokens = scannerFactory.tokens;
        Tokens.Token token = Tokens.DUMMY;
        this.prevToken = token;
        this.token = token;
    }

    private void ensureLookahead(int i) {
        for (int size = this.savedTokens.size(); size < i; size++) {
            this.savedTokens.add(this.tokenizer.readToken());
        }
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public int errPos() {
        return this.tokenizer.errPos();
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public Queue<Tokens.Comment> getDocComments() {
        return this.docComments;
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public Position.LineMap getLineMap() {
        return this.tokenizer.getLineMap();
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public void lintWarning(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.LintWarning lintWarning) {
        this.tokenizer.log.warning(diagnosticPosition, lintWarning);
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public void nextToken() {
        this.prevToken = this.token;
        if (!this.savedTokens.isEmpty()) {
            this.token = this.savedTokens.remove(0);
            return;
        }
        Tokens.Token token = this.tokenizer.readToken();
        this.token = token;
        com.sun.tools.javac.util.List<Tokens.Comment> list = token.comments;
        if (list != null) {
            for (Tokens.Comment comment : list) {
                int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[comment.getStyle().ordinal()];
                if (i == 1 || i == 2) {
                    this.docComments.add(comment);
                }
            }
        }
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
        Tokens.Token[] tokenArrSplit = this.token.split(this.tokens);
        this.prevToken = tokenArrSplit[0];
        Tokens.Token token = tokenArrSplit[1];
        this.token = token;
        return token;
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public Tokens.Token token(int i) {
        if (i == 0) {
            return this.token;
        }
        ensureLookahead(i);
        return this.savedTokens.get(i - 1);
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public void errPos(int i) {
        this.tokenizer.errPos(i);
    }

    @Override // com.sun.tools.javac.parser.Lexer
    public Tokens.Token token() {
        return token(0);
    }

    public Scanner(ScannerFactory scannerFactory, char[] cArr, int i) {
        this(scannerFactory, new JavaTokenizer(scannerFactory, cArr, i));
    }

    public Scanner(ScannerFactory scannerFactory, CharBuffer charBuffer) {
        this(scannerFactory, new JavaTokenizer(scannerFactory, charBuffer));
    }
}

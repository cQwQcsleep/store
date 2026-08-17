package com.sun.tools.javac.parser;

import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Position;
import java.util.Queue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Lexer {
    int errPos();

    void errPos(int i);

    Queue<Tokens.Comment> getDocComments();

    Position.LineMap getLineMap();

    void lintWarning(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.LintWarning lintWarning);

    void nextToken();

    Tokens.Token prevToken();

    void setPrevToken(Tokens.Token token);

    Tokens.Token split();

    Tokens.Token token();

    Tokens.Token token(int i);
}

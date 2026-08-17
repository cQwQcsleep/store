package io.github.rosemoe.sora.lang.completion.snippet.parser;

import kotlin.text.Typography;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public enum TokenType {
    Dollar(Typography.dollar),
    Colon(':'),
    Comma(','),
    CurlyOpen('{'),
    CurlyClose('}'),
    Backslash('\\'),
    Forwardslash('/'),
    Pipe('|'),
    Int,
    VariableName,
    Format,
    Plus('+'),
    Dash('-'),
    QuestionMark('?'),
    Backtick('`'),
    EOF;

    private final char target;

    TokenType(char c) {
        this.target = c;
    }

    public char getTargetCharacter() {
        return this.target;
    }

    TokenType() {
        this((char) 0);
    }
}

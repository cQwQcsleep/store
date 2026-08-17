package io.github.rosemoe.sora.lang.completion.snippet.parser;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Token {
    public int index;
    public int length;
    public TokenType type;

    public Token(int i, int i2, TokenType tokenType) {
        this.index = i;
        this.length = i2;
        this.type = tokenType;
    }

    public String toString() {
        return "Token{index=" + this.index + ", length=" + this.length + ", type=" + this.type + '}';
    }
}

package org.antlr.v4.runtime;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface WritableToken extends Token {
    void setChannel(int i);

    void setCharPositionInLine(int i);

    void setLine(int i);

    void setText(String str);

    void setTokenIndex(int i);

    void setType(int i);
}

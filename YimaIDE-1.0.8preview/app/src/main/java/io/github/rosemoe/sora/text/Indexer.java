package io.github.rosemoe.sora.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Indexer {
    int getCharColumn(int i);

    int getCharIndex(int i, int i2);

    int getCharLine(int i);

    CharPosition getCharPosition(int i);

    CharPosition getCharPosition(int i, int i2);

    void getCharPosition(int i, int i2, CharPosition charPosition);

    void getCharPosition(int i, CharPosition charPosition);
}

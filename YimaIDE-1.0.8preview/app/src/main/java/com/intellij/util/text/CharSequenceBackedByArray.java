package com.intellij.util.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface CharSequenceBackedByArray extends CharSequence {
    void getChars(char[] cArr, int i);

    char[] getChars();
}

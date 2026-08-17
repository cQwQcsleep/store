package org.jetbrains.kotlin.js.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface TextOutput {
    int getColumn();

    int getLine();

    int getPosition();

    void indentIn();

    void indentOut();

    void maybeIndent();

    void newline();

    void print(char c);

    void print(double d);

    void print(int i);

    void print(CharSequence charSequence);

    void print(char[] cArr);
}

package org.jline.reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface Buffer {
    int atChar(int i);

    int backspace(int i);

    boolean backspace();

    boolean clear();

    Buffer copy();

    void copyFrom(Buffer buffer);

    int currChar();

    boolean currChar(int i);

    int cursor();

    boolean cursor(int i);

    int delete(int i);

    boolean delete();

    boolean down();

    int length();

    int move(int i);

    boolean moveXY(int i, int i2);

    int nextChar();

    int prevChar();

    String substring(int i);

    String substring(int i, int i2);

    String toString();

    boolean up();

    String upToCursor();

    void write(int i);

    void write(CharSequence charSequence);

    void write(CharSequence charSequence, boolean z);
}

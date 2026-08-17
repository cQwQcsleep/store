package com.sun.org.apache.xerces.internal.xni;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLString {
    public static final int DEFAULT_SIZE = 32;
    public char[] ch;
    public int length;
    public int offset;

    public XMLString(char[] cArr, int i, int i2) {
        setValues(cArr, i, i2);
    }

    public void append(String str) {
        int length = str.length();
        int i = this.length;
        int i2 = i + length;
        char[] cArr = this.ch;
        if (i2 > cArr.length) {
            int length2 = cArr.length * 2;
            if (length2 < cArr.length + length + 32) {
                length2 = cArr.length + length + 32;
            }
            char[] cArr2 = new char[length2];
            System.arraycopy(cArr, 0, cArr2, 0, i);
            this.ch = cArr2;
        }
        str.getChars(0, length, this.ch, this.length);
        this.length += length;
    }

    public void clear() {
        this.ch = null;
        this.offset = 0;
        this.length = -1;
    }

    public boolean equals(String str) {
        if (str == null || this.length != str.length()) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (this.ch[this.offset + i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void setValues(XMLString xMLString) {
        setValues(xMLString.ch, xMLString.offset, xMLString.length);
    }

    public String toString() {
        int i = this.length;
        return i > 0 ? new String(this.ch, this.offset, i) : "";
    }

    public XMLString() {
    }

    public XMLString(XMLString xMLString) {
        setValues(xMLString);
    }

    public void setValues(char[] cArr, int i, int i2) {
        this.ch = cArr;
        this.offset = i;
        this.length = i2;
    }

    public boolean equals(char[] cArr, int i, int i2) {
        if (cArr == null || this.length != i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.ch[this.offset + i3] != cArr[i + i3]) {
                return false;
            }
        }
        return true;
    }

    public void append(char c) {
        int i = this.length;
        int i2 = i + 1;
        char[] cArr = this.ch;
        if (i2 > cArr.length) {
            int length = cArr.length * 2;
            if (length < cArr.length + 32) {
                length = cArr.length + 32;
            }
            char[] cArr2 = new char[length];
            System.arraycopy(cArr, 0, cArr2, 0, i);
            this.ch = cArr2;
        }
        char[] cArr3 = this.ch;
        int i3 = this.length;
        cArr3[i3] = c;
        this.length = i3 + 1;
    }

    public void append(char[] cArr, int i, int i2) {
        int i3 = this.length;
        int i4 = i3 + i2;
        char[] cArr2 = this.ch;
        if (i4 > cArr2.length) {
            int length = cArr2.length * 2;
            if (length < cArr2.length + i2 + 32) {
                length = cArr2.length + i2 + 32;
            }
            char[] cArr3 = new char[length];
            System.arraycopy(cArr2, 0, cArr3, 0, i3);
            this.ch = cArr3;
        }
        if (cArr == null || i2 <= 0) {
            return;
        }
        System.arraycopy(cArr, i, this.ch, this.length, i2);
        this.length += i2;
    }

    public void append(XMLString xMLString) {
        append(xMLString.ch, xMLString.offset, xMLString.length);
    }
}

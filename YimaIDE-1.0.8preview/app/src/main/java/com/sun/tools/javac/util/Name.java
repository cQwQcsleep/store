package com.sun.tools.javac.util;

import com.sun.tools.javac.jvm.PoolConstant;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Name implements javax.lang.model.element.Name, PoolConstant, Comparable<Name> {
    public final Table table;

    @FunctionalInterface
    public interface NameMapper<X> {
        X map(byte[] bArr, int i, int i2);
    }

    public static abstract class Table {
        public final Names names;

        public Table(Names names) {
            this.names = names;
        }

        public abstract void dispose();

        public abstract Name fromChars(char[] cArr, int i, int i2);

        public Name fromString(String str) {
            char[] charArray = str.toCharArray();
            return fromChars(charArray, 0, charArray.length);
        }

        public Name fromUtf(byte[] bArr) throws InvalidUtfException {
            return fromUtf(bArr, 0, bArr.length, Convert.Validation.STRICT);
        }

        public abstract Name fromUtf(byte[] bArr, int i, int i2, Convert.Validation validation) throws InvalidUtfException;
    }

    public Name(Table table) {
        this.table = table;
    }

    public Name append(char c, Name name) {
        return this.table.fromString(toString() + c + name.toString());
    }

    public char charAt(int i) {
        return toString().charAt(i);
    }

    @Override // java.lang.Comparable
    public int compareTo(Name name) {
        return toString().compareTo(name.toString());
    }

    public boolean contentEquals(CharSequence charSequence) {
        return toString().equals(charSequence.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            Name name = (Name) obj;
            if (this.table == name.table && nameEquals(name)) {
                return true;
            }
        }
        return false;
    }

    public abstract void getUtf8Bytes(byte[] bArr, int i);

    public abstract int getUtf8Length();

    public abstract int hashCode();

    public boolean isEmpty() {
        return length() == 0;
    }

    public int lastIndexOfAscii(char c) {
        return toString().lastIndexOf(c);
    }

    public int length() {
        return toString().length();
    }

    public <X> X map(NameMapper<X> nameMapper) {
        byte[] utf = toUtf();
        return nameMapper.map(utf, 0, utf.length);
    }

    public abstract boolean nameEquals(Name name);

    @Override // com.sun.tools.javac.jvm.PoolConstant
    public final int poolTag() {
        return 1;
    }

    public boolean startsWith(Name name) {
        return toString().startsWith(name.toString());
    }

    public Name subName(int i, int i2) {
        return this.table.fromString(toString().substring(i, i2));
    }

    public CharSequence subSequence(int i, int i2) {
        return toString().subSequence(i, i2);
    }

    public abstract String toString();

    public byte[] toUtf() {
        byte[] bArr = new byte[getUtf8Length()];
        getUtf8Bytes(bArr, 0);
        return bArr;
    }

    public Name subName(int i) {
        return this.table.fromString(toString().substring(i));
    }

    public Name append(Name name) {
        return this.table.fromString(toString() + name.toString());
    }
}

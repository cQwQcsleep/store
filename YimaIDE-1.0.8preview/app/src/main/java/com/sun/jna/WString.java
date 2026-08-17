package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class WString implements CharSequence, Comparable {
    private String string;

    public WString(String str) {
        if (str != null) {
            this.string = str;
        } else {
            x0e.a("String initializer must be non-null");
            throw null;
        }
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return toString().charAt(i);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return toString().compareTo(obj.toString());
    }

    public boolean equals(Object obj) {
        return (obj instanceof WString) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return toString().length();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return toString().subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.string;
    }
}

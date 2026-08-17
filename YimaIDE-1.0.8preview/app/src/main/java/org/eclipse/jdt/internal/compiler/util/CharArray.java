package org.eclipse.jdt.internal.compiler.util;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
final class CharArray implements Comparable<CharArray> {
    private final char[] key;

    public CharArray(char[] cArr) {
        this.key = cArr;
    }

    @Override // java.lang.Comparable
    public int compareTo(CharArray charArray) {
        return Arrays.compare(this.key, charArray.key);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CharArray) {
            return Arrays.equals(this.key, ((CharArray) obj).key);
        }
        return false;
    }

    public char[] getKey() {
        return this.key;
    }

    public int hashCode() {
        return Arrays.hashCode(this.key);
    }

    public String toString() {
        return Arrays.toString(this.key);
    }
}

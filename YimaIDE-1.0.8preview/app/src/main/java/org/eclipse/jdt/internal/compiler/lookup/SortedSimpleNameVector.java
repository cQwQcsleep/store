package org.eclipse.jdt.internal.compiler.lookup;

import java.util.Arrays;
import org.eclipse.jdt.internal.compiler.util.SortedCharArrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
final class SortedSimpleNameVector {
    static int INITIAL_SIZE = 10;
    int size = 0;
    char[][] elements = new char[INITIAL_SIZE][];

    public boolean add(char[] cArr) {
        int iBinarySearch = Arrays.binarySearch(this.elements, 0, this.size, cArr, SortedCharArrays.CHAR_ARR_COMPARATOR);
        if (iBinarySearch >= 0) {
            return false;
        }
        char[][] cArr2 = this.elements;
        int i = this.size;
        char[][] cArr3 = i < cArr2.length ? cArr2 : new char[cArr2.length * 2][];
        this.size = i + 1;
        this.elements = (char[][]) SortedCharArrays.insertIntoArray(cArr2, cArr3, cArr, -(iBinarySearch + 1), i);
        return true;
    }

    public char[] elementAt(int i) {
        return this.elements[i];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            sb.append(this.elements[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}

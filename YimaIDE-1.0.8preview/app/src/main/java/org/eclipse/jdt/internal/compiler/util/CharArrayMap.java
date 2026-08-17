package org.eclipse.jdt.internal.compiler.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CharArrayMap<P> implements CharArrayMapper<P> {
    private char[][] keyTable;
    private int size;
    private P[] valueTable;

    public CharArrayMap(int i) {
        i = i <= 0 ? 0 : i;
        this.size = 0;
        this.keyTable = new char[i][];
        this.valueTable = (P[]) new Object[i];
    }

    private void grow() {
        char[][] cArr = this.keyTable;
        int length = (cArr.length > 1 ? cArr.length : 1) * 2;
        this.keyTable = (char[][]) Arrays.copyOfRange(cArr, 0, length);
        this.valueTable = (P[]) Arrays.copyOfRange(this.valueTable, 0, length);
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public boolean containsKey(char[] cArr) {
        for (int i = 0; i < this.size; i++) {
            if (Arrays.equals(this.keyTable[i], cArr)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public P get(char[] cArr) {
        for (int i = 0; i < this.size; i++) {
            if (Arrays.equals(this.keyTable[i], cArr)) {
                return this.valueTable[i];
            }
        }
        return null;
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public Collection<char[]> keys() {
        return (Collection) Arrays.stream(this.keyTable).filter(new Predicate() { // from class: of1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((char[]) obj);
            }
        }).collect(Collectors.toList());
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public P put(char[] cArr, P p) {
        int i = 0;
        while (true) {
            int i2 = this.size;
            char[][] cArr2 = this.keyTable;
            if (i >= i2) {
                if (i >= cArr2.length) {
                    grow();
                }
                this.keyTable[i] = cArr;
                this.valueTable[i] = p;
                this.size++;
                return null;
            }
            if (Arrays.equals(cArr2[i], cArr)) {
                P[] pArr = this.valueTable;
                P p2 = pArr[i];
                pArr[i] = p;
                return p2;
            }
            i++;
        }
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public int size() {
        return this.size;
    }

    public String toString() {
        return CharArrayMapper.toString(this);
    }

    public void transferTo(CharArrayMapper<P> charArrayMapper) {
        for (int i = 0; i < this.size; i++) {
            char[] cArr = this.keyTable[i];
            if (cArr != null) {
                charArrayMapper.put(cArr, this.valueTable[i]);
            }
        }
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public Collection<P> values() {
        return (Collection) Arrays.stream(this.valueTable).filter(new Predicate() { // from class: e6b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull(obj);
            }
        }).collect(Collectors.toList());
    }

    public CharArrayMap() {
        this(0);
    }
}

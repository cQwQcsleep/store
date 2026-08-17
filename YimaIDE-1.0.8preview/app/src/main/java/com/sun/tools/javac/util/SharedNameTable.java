package com.sun.tools.javac.util;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SharedNameTable extends Utf8NameTable {
    private static List<SoftReference<SharedNameTable>> freelist = List.nil();
    public byte[] bytes;
    private int hashMask;
    private NameImpl[] hashes;
    private int nc;

    public static final class NameImpl extends Utf8NameTable.NameImpl {
        final int index;
        final int length;
        final NameImpl next;

        public NameImpl(SharedNameTable sharedNameTable, int i, int i2, NameImpl nameImpl) {
            super(sharedNameTable);
            this.index = i;
            this.length = i2;
            this.next = nameImpl;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public byte[] getByteData() {
            return ((SharedNameTable) this.table).bytes;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public int getByteLength() {
            return this.length;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public int getByteOffset() {
            return this.index;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public int getNameIndex() {
            return this.index;
        }
    }

    public SharedNameTable(Names names, int i, int i2) {
        super(names);
        this.nc = 0;
        if (Integer.bitCount(i) != 1) {
            j2d.a();
            throw null;
        }
        this.hashMask = i - 1;
        this.hashes = new NameImpl[i];
        this.bytes = new byte[i2];
    }

    private NameImpl addName(int i, int i2, int i3) {
        NameImpl nameImpl = new NameImpl(this, i, i2, this.hashes[i3]);
        this.hashes[i3] = nameImpl;
        this.nc = i + Math.max(i2, 1);
        return nameImpl;
    }

    public static synchronized SharedNameTable create(Names names) {
        while (freelist.nonEmpty()) {
            SharedNameTable sharedNameTable = freelist.head.get();
            freelist = freelist.tail;
            if (sharedNameTable != null) {
                return sharedNameTable;
            }
        }
        return new SharedNameTable(names);
    }

    private static synchronized void dispose(SharedNameTable sharedNameTable) {
        freelist = freelist.prepend(new SoftReference<>(sharedNameTable));
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromChars(char[] cArr, int i, int i2) {
        int i3 = this.nc;
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.bytes, (i2 * 3) + i3);
        this.bytes = bArrEnsureCapacity;
        int iChars2utf = Convert.chars2utf(cArr, i, bArrEnsureCapacity, i3, i2) - i3;
        int iHashValue = Utf8NameTable.hashValue(bArrEnsureCapacity, i3, iChars2utf) & this.hashMask;
        NameImpl nameImpl = this.hashes[iHashValue];
        while (nameImpl != null && (nameImpl.getByteLength() != iChars2utf || !Utf8NameTable.equals(bArrEnsureCapacity, nameImpl.index, bArrEnsureCapacity, i3, iChars2utf))) {
            nameImpl = nameImpl.next;
        }
        return nameImpl == null ? addName(i3, iChars2utf, iHashValue) : nameImpl;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromUtf(byte[] bArr, int i, int i2, Convert.Validation validation) throws InvalidUtfException {
        if (validation != Convert.Validation.NONE) {
            Convert.utfValidate(bArr, i, i2, validation);
        }
        int iHashValue = Utf8NameTable.hashValue(bArr, i, i2) & this.hashMask;
        NameImpl nameImpl = this.hashes[iHashValue];
        byte[] bArr2 = this.bytes;
        while (nameImpl != null && (nameImpl.getByteLength() != i2 || !Utf8NameTable.equals(bArr2, nameImpl.index, bArr, i, i2))) {
            nameImpl = nameImpl.next;
        }
        if (nameImpl != null) {
            return nameImpl;
        }
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(bArr2, this.nc + i2);
        this.bytes = bArrEnsureCapacity;
        System.arraycopy(bArr, i, bArrEnsureCapacity, this.nc, i2);
        return addName(this.nc, i2, iHashValue);
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public void dispose() {
        dispose(this);
    }

    public SharedNameTable(Names names) {
        this(names, 32768, 131072);
    }
}

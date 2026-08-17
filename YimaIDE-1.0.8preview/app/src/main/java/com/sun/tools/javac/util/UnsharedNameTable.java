package com.sun.tools.javac.util;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnsharedNameTable extends Utf8NameTable {
    private int hashMask;
    private HashEntry[] hashes;
    public int index;

    public static class HashEntry extends WeakReference<NameImpl> {
        HashEntry next;

        public HashEntry(NameImpl nameImpl) {
            super(nameImpl);
        }
    }

    public static final class NameImpl extends Utf8NameTable.NameImpl {
        final byte[] bytes;
        final int index;

        public NameImpl(UnsharedNameTable unsharedNameTable, byte[] bArr, int i) {
            super(unsharedNameTable);
            this.bytes = bArr;
            this.index = i;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public byte[] getByteData() {
            return this.bytes;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public int getByteLength() {
            return this.bytes.length;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public int getByteOffset() {
            return 0;
        }

        @Override // com.sun.tools.javac.util.Utf8NameTable.NameImpl
        public int getNameIndex() {
            return this.index;
        }
    }

    public UnsharedNameTable(Names names, int i) {
        super(names);
        this.hashes = null;
        if (Integer.bitCount(i) != 1) {
            j2d.a();
            throw null;
        }
        this.hashMask = i - 1;
        this.hashes = new HashEntry[i];
    }

    public static UnsharedNameTable create(Names names) {
        return new UnsharedNameTable(names);
    }

    private Name fromValidUtf(byte[] bArr, int i, int i2) {
        int iHashValue = Utf8NameTable.hashValue(bArr, i, i2) & this.hashMask;
        HashEntry hashEntry = this.hashes[iHashValue];
        HashEntry hashEntry2 = null;
        HashEntry hashEntry3 = hashEntry;
        while (hashEntry != null) {
            NameImpl nameImpl = hashEntry.get();
            if (nameImpl == null) {
                if (hashEntry3 == hashEntry) {
                    HashEntry[] hashEntryArr = this.hashes;
                    HashEntry hashEntry4 = hashEntry.next;
                    hashEntryArr[iHashValue] = hashEntry4;
                    hashEntry3 = hashEntry4;
                } else {
                    Assert.checkNonNull(hashEntry2, "previousNonNullTableEntry cannot be null here.");
                    hashEntry2.next = hashEntry.next;
                }
            } else {
                if (nameImpl.getByteLength() == i2 && Utf8NameTable.equals(nameImpl.bytes, 0, bArr, i, i2)) {
                    return nameImpl;
                }
                hashEntry2 = hashEntry;
            }
            hashEntry = hashEntry.next;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        int i3 = this.index;
        this.index = i3 + 1;
        NameImpl nameImpl2 = new NameImpl(this, bArr2, i3);
        HashEntry hashEntry5 = new HashEntry(nameImpl2);
        if (hashEntry2 == null) {
            this.hashes[iHashValue] = hashEntry5;
            return nameImpl2;
        }
        Assert.checkNull((Object) hashEntry2.next, "previousNonNullTableEntry.next must be null.");
        hashEntry2.next = hashEntry5;
        return nameImpl2;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public void dispose() {
        this.hashes = null;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromChars(char[] cArr, int i, int i2) {
        byte[] bArr = new byte[i2 * 3];
        return fromValidUtf(bArr, 0, Convert.chars2utf(cArr, i, bArr, 0, i2));
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromUtf(byte[] bArr, int i, int i2, Convert.Validation validation) throws InvalidUtfException {
        if (validation != Convert.Validation.NONE) {
            Convert.utfValidate(bArr, i, i2, validation);
        }
        return fromValidUtf(bArr, i, i2);
    }

    public UnsharedNameTable(Names names) {
        this(names, 32768);
    }
}

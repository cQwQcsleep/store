package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Utf8NameTable extends Name.Table {
    public Utf8NameTable(Names names) {
        super(names);
    }

    public static boolean equals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                return true;
            }
            int i5 = i + 1;
            int i6 = i2 + 1;
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i = i5;
            i3 = i4;
            i2 = i6;
        }
    }

    public static int hashValue(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                return i3;
            }
            i3 = bArr[i] + ((i3 << 5) - i3);
            i++;
            i2 = i4;
        }
    }

    public static abstract class NameImpl extends Name {
        public NameImpl(Utf8NameTable utf8NameTable) {
            super(utf8NameTable);
        }

        @Override // com.sun.tools.javac.util.Name
        public Name append(char c, Name name) {
            Assert.check((c & (-128)) == 0);
            NameImpl nameImpl = (NameImpl) name;
            Assert.check(nameImpl.table == this.table);
            byte[] byteData = getByteData();
            byte[] byteData2 = nameImpl.getByteData();
            int byteOffset = getByteOffset();
            int byteOffset2 = nameImpl.getByteOffset();
            int byteLength = getByteLength();
            int byteLength2 = nameImpl.getByteLength();
            int i = byteLength + 1;
            int i2 = i + byteLength2;
            byte[] bArr = new byte[i2];
            System.arraycopy(byteData, byteOffset, bArr, 0, byteLength);
            bArr[byteLength] = (byte) c;
            System.arraycopy(byteData2, byteOffset2, bArr, i, byteLength2);
            try {
                return this.table.fromUtf(bArr, 0, i2, Convert.Validation.NONE);
            } catch (InvalidUtfException e) {
                throw new AssertionError("invalid UTF8 data", e);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sun.tools.javac.util.Name, java.lang.Comparable
        public int compareTo(Name name) {
            if (!(name instanceof NameImpl)) {
                return super.compareTo(name);
            }
            NameImpl nameImpl = (NameImpl) name;
            byte[] byteData = getByteData();
            byte[] byteData2 = nameImpl.getByteData();
            int byteOffset = getByteOffset();
            int byteOffset2 = nameImpl.getByteOffset();
            int byteLength = getByteLength();
            int byteLength2 = nameImpl.getByteLength();
            while (true) {
                int i = 0;
                if (byteLength <= 0 || byteLength2 <= 0) {
                    break;
                }
                int i2 = byteOffset + 1;
                int i3 = byteData[byteOffset] & 255;
                int i4 = byteOffset2 + 1;
                int i5 = byteData2[byteOffset2] & 255;
                if (i3 == 192 && (byteData[i2] & 63) == 0) {
                    byteOffset += 2;
                    byteLength--;
                    i3 = 0;
                } else {
                    byteOffset = i2;
                }
                if (i5 == 192 && (byteData2[i4] & 63) == 0) {
                    byteOffset2 += 2;
                    byteLength2--;
                } else {
                    byteOffset2 = i4;
                    i = i5;
                }
                int i6 = i3 - i;
                if (i6 != 0) {
                    return i6;
                }
                byteLength--;
                byteLength2--;
            }
            if (byteLength > 0) {
                return 1;
            }
            return byteLength2 > 0 ? -1 : 0;
        }

        public abstract byte[] getByteData();

        public abstract int getByteLength();

        public abstract int getByteOffset();

        public abstract int getNameIndex();

        @Override // com.sun.tools.javac.util.Name
        public void getUtf8Bytes(byte[] bArr, int i) {
            System.arraycopy(getByteData(), getByteOffset(), bArr, i, getByteLength());
        }

        @Override // com.sun.tools.javac.util.Name
        public int getUtf8Length() {
            return getByteLength();
        }

        @Override // com.sun.tools.javac.util.Name
        public int hashCode() {
            return getNameIndex();
        }

        @Override // com.sun.tools.javac.util.Name
        public int lastIndexOfAscii(char c) {
            Assert.check((c & (-128)) == 0);
            byte b = (byte) c;
            byte[] byteData = getByteData();
            int byteOffset = getByteOffset();
            int byteLength = getByteLength() - 1;
            while (byteLength >= 0 && byteData[byteOffset + byteLength] != b) {
                byteLength--;
            }
            return byteLength <= 0 ? byteLength : Convert.utfNumChars(byteData, byteOffset, byteLength);
        }

        @Override // com.sun.tools.javac.util.Name
        public int length() {
            return Convert.utfNumChars(getByteData(), getByteOffset(), getByteLength());
        }

        @Override // com.sun.tools.javac.util.Name
        public boolean nameEquals(Name name) {
            return ((NameImpl) name).getNameIndex() == getNameIndex();
        }

        @Override // com.sun.tools.javac.util.Name
        public boolean startsWith(Name name) {
            NameImpl nameImpl = (NameImpl) name;
            Assert.check(nameImpl.table == this.table);
            int byteLength = getByteLength();
            int byteLength2 = nameImpl.getByteLength();
            if (byteLength < byteLength2) {
                return false;
            }
            byte[] byteData = getByteData();
            byte[] byteData2 = nameImpl.getByteData();
            int byteOffset = getByteOffset() + byteLength2;
            int byteOffset2 = nameImpl.getByteOffset() + byteLength2;
            while (true) {
                int i = byteLength2 - 1;
                if (byteLength2 <= 0) {
                    return true;
                }
                byteOffset--;
                byteOffset2--;
                if (byteData[byteOffset] != byteData2[byteOffset2]) {
                    return false;
                }
                byteLength2 = i;
            }
        }

        @Override // com.sun.tools.javac.util.Name
        public String toString() {
            try {
                return Convert.utf2string(getByteData(), getByteOffset(), getByteLength(), Convert.Validation.NONE);
            } catch (InvalidUtfException e) {
                throw new AssertionError("invalid UTF8 data", e);
            }
        }

        @Override // com.sun.tools.javac.util.Name
        public Name append(Name name) {
            NameImpl nameImpl = (NameImpl) name;
            Assert.check(nameImpl.table == this.table);
            byte[] byteData = getByteData();
            byte[] byteData2 = nameImpl.getByteData();
            int byteOffset = getByteOffset();
            int byteOffset2 = nameImpl.getByteOffset();
            int byteLength = getByteLength();
            int byteLength2 = nameImpl.getByteLength();
            int i = byteLength + byteLength2;
            byte[] bArr = new byte[i];
            System.arraycopy(byteData, byteOffset, bArr, 0, byteLength);
            System.arraycopy(byteData2, byteOffset2, bArr, byteLength, byteLength2);
            try {
                return this.table.fromUtf(bArr, 0, i, Convert.Validation.NONE);
            } catch (InvalidUtfException e) {
                throw new AssertionError("invalid UTF8 data", e);
            }
        }
    }
}

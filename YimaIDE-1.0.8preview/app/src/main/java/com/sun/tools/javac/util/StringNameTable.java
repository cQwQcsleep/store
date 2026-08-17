package com.sun.tools.javac.util;

import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringNameTable extends Name.Table {
    private final boolean intern;
    private final HashMap<String, Name> nameMap;

    public static final class NameImpl extends Name {
        private final String string;

        public NameImpl(StringNameTable stringNameTable, String str) {
            super(stringNameTable);
            this.string = str;
        }

        @Override // com.sun.tools.javac.util.Name
        public boolean contentEquals(CharSequence charSequence) {
            return this.string.contentEquals(charSequence);
        }

        @Override // com.sun.tools.javac.util.Name
        public void getUtf8Bytes(byte[] bArr, int i) {
            Convert.chars2utf(this.string.toCharArray(), 0, bArr, i, this.string.length());
        }

        @Override // com.sun.tools.javac.util.Name
        public int getUtf8Length() {
            int length = this.string.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = this.string.charAt(i2);
                if (cCharAt > 127 || cCharAt == 0) {
                    i = cCharAt > 2047 ? i + 2 : i + 1;
                }
            }
            return length + i;
        }

        @Override // com.sun.tools.javac.util.Name
        public int hashCode() {
            return this.string.hashCode();
        }

        @Override // com.sun.tools.javac.util.Name
        public boolean nameEquals(Name name) {
            return ((NameImpl) name).string.equals(this.string);
        }

        @Override // com.sun.tools.javac.util.Name
        public String toString() {
            return this.string;
        }
    }

    public StringNameTable(Names names, int i, boolean z) {
        super(names);
        this.nameMap = new HashMap<>(i);
        this.intern = z;
    }

    public static StringNameTable create(Names names, boolean z) {
        return new StringNameTable(names, z);
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public void dispose() {
        this.nameMap.clear();
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromChars(char[] cArr, int i, int i2) {
        return fromString(new String(cArr, i, i2));
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromString(String str) {
        Name name = this.nameMap.get(str);
        if (name != null) {
            return name;
        }
        if (this.intern) {
            str = str.intern();
        }
        NameImpl nameImpl = new NameImpl(this, str);
        this.nameMap.put(str, nameImpl);
        return nameImpl;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromUtf(byte[] bArr, int i, int i2, Convert.Validation validation) throws InvalidUtfException {
        return fromString(Convert.utf2string(bArr, i, i2, validation));
    }

    public StringNameTable(Names names, boolean z) {
        this(names, 32768, z);
    }
}

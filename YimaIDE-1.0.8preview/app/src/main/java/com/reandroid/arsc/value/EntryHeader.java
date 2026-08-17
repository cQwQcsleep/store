package com.reandroid.arsc.value;

import com.intellij.psi.PsiKeyword;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntryHeader extends ValueHeader {
    private static final short HEADER_SIZE_SCALAR = 8;

    public EntryHeader() {
        super(8);
    }

    @Override // com.reandroid.arsc.value.ValueHeader
    public String toString() {
        if (isNull()) {
            return PsiKeyword.NULL;
        }
        StringBuilder sb = new StringBuilder();
        int size = getSize();
        int size2 = readSize();
        if (size != 8) {
            sb.append("size=");
            sb.append(size);
        }
        if (size != size2) {
            sb.append(" readSize=");
            sb.append(size2);
        }
        if (isComplex()) {
            sb.append(" complex");
        }
        if (isPublic()) {
            sb.append(" public");
        }
        if (isWeak()) {
            sb.append(" weak");
        }
        if (isCompact()) {
            sb.append(" compact");
        }
        String name = getName();
        if (name != null) {
            sb.append(" name=");
            sb.append(name);
        } else {
            sb.append(" key=");
            sb.append(getKey());
        }
        return sb.toString();
    }
}

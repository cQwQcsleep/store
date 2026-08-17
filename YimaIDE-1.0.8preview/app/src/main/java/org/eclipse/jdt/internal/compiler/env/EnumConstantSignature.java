package org.eclipse.jdt.internal.compiler.env;

import java.util.Arrays;
import org.eclipse.jdt.core.compiler.CharOperation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class EnumConstantSignature {
    char[] constName;
    char[] typeName;

    public EnumConstantSignature(char[] cArr, char[] cArr2) {
        this.typeName = cArr;
        this.constName = cArr2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EnumConstantSignature enumConstantSignature = (EnumConstantSignature) obj;
        if (Arrays.equals(this.constName, enumConstantSignature.constName)) {
            return Arrays.equals(this.typeName, enumConstantSignature.typeName);
        }
        return false;
    }

    public char[] getEnumConstantName() {
        return this.constName;
    }

    public char[] getTypeName() {
        return this.typeName;
    }

    public int hashCode() {
        return ((CharOperation.hashCode(this.constName) + 31) * 31) + CharOperation.hashCode(this.typeName);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.typeName);
        sb.append('.');
        sb.append(this.constName);
        return sb.toString();
    }
}

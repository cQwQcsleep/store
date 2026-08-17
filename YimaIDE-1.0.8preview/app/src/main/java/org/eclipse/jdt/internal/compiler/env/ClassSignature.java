package org.eclipse.jdt.internal.compiler.env;

import java.util.Arrays;
import org.eclipse.jdt.core.compiler.CharOperation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ClassSignature {
    char[] className;

    public ClassSignature(char[] cArr) {
        this.className = cArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Arrays.equals(this.className, ((ClassSignature) obj).className);
        }
        return false;
    }

    public char[] getTypeName() {
        return this.className;
    }

    public int hashCode() {
        return 31 + CharOperation.hashCode(this.className);
    }

    public String toString() {
        return this.className + ".class";
    }
}

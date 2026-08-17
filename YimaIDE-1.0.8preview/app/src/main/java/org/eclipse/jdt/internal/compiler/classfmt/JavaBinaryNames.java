package org.eclipse.jdt.internal.compiler.classfmt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class JavaBinaryNames {
    public static boolean isClinit(char[] cArr) {
        return cArr[0] == '<' && cArr.length == 8;
    }

    public static boolean isConstructor(char[] cArr) {
        return cArr[0] == '<' && cArr.length == 6;
    }
}

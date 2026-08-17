package org.eclipse.jdt.internal.compiler.env;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface INameEnvironment {
    void cleanup();

    NameEnvironmentAnswer findType(char[] cArr, char[][] cArr2);

    NameEnvironmentAnswer findType(char[][] cArr);

    boolean isPackage(char[][] cArr, char[] cArr2);
}

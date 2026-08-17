package org.jetbrains.kotlin.resolve.jvm.jvmSignature;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class JvmClassSignature {
    private final List<String> interfaces;
    private final String javaGenericSignature;
    private final String name;
    private final String superclassName;

    public JvmClassSignature(String str, String str2, List<String> list, String str3) {
        this.name = str;
        this.superclassName = str2;
        this.interfaces = list;
        this.javaGenericSignature = str3;
    }

    public List<String> getInterfaces() {
        return this.interfaces;
    }

    public String getJavaGenericSignature() {
        return this.javaGenericSignature;
    }

    public String getName() {
        return this.name;
    }

    public String getSuperclassName() {
        return this.superclassName;
    }
}

package com.sun.tools.javac.util;

import nbjavac.ModuleWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleHelper {
    private static final String[] javacInternalPackages = {"com.sun.tools.javac.api", "com.sun.tools.javac.code", "com.sun.tools.javac.comp", "com.sun.tools.javac.file", "com.sun.tools.javac.jvm", "com.sun.tools.javac.main", "com.sun.tools.javac.model", "com.sun.tools.javac.parser", "com.sun.tools.javac.platform", "com.sun.tools.javac.processing", "com.sun.tools.javac.tree", "com.sun.tools.javac.util", "com.sun.tools.doclint"};

    public static void addExports(ModuleWrapper moduleWrapper, ModuleWrapper moduleWrapper2) {
        for (String str : javacInternalPackages) {
            moduleWrapper.addExports(str, moduleWrapper2);
        }
    }
}

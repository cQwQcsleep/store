package com.intellij.util.lang;

import java.io.IOException;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface Loader {
    Class<?> findClass(String str, String str2, ClassPath.ClassDataConsumer classDataConsumer) throws IOException;

    Path getPath();

    Resource getResource(String str);
}

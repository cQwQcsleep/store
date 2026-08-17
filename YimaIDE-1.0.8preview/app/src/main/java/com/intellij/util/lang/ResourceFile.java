package com.intellij.util.lang;

import java.io.IOException;
import java.util.jar.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface ResourceFile {
    ClasspathCache.IndexRegistrar buildClassPathCacheData() throws IOException;

    Class<?> findClass(String str, String str2, JarLoader jarLoader, ClassPath.ClassDataConsumer classDataConsumer) throws IOException;

    Resource getResource(String str, JarLoader jarLoader) throws IOException;

    Attributes loadManifestAttributes() throws IOException;
}

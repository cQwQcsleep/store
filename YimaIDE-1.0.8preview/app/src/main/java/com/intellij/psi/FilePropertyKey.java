package com.intellij.psi;

import com.intellij.openapi.vfs.VirtualFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface FilePropertyKey<T> {
    T getPersistentValue(VirtualFile virtualFile);

    boolean setPersistentValue(VirtualFile virtualFile, T t);
}

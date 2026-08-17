package com.google.common.io;

import java.nio.file.FileSystemException;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class InsecureRecursiveDeleteException extends FileSystemException {
    public InsecureRecursiveDeleteException(String str) {
        super(str, null, "unable to guarantee security of recursive delete");
    }
}

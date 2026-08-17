package com.intellij.util.io;

import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class VersionUpdatedException extends CorruptedException {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 2) {
            objArr[0] = "expectedVersion";
        } else if (i != 3) {
            objArr[0] = "file";
        } else {
            objArr[0] = "actualVersion";
        }
        objArr[1] = "com/intellij/util/io/VersionUpdatedException";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VersionUpdatedException(Path path, Object obj, Object obj2) {
        super("Storage version updated, file = " + path + ", expected version = " + obj + ", actual version = " + obj2);
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        if (obj == null) {
            $$$reportNull$$$0(2);
        }
        if (obj2 == null) {
            $$$reportNull$$$0(3);
        }
    }
}

package jdk.internal.jimage;

import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class NativeImageBuffer {
    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: jdk.internal.jimage.NativeImageBuffer.1
            @Override // java.security.PrivilegedAction
            public Void run() {
                System.loadLibrary("jimage");
                return null;
            }
        });
    }

    public static native ByteBuffer getNativeMap(String str);
}

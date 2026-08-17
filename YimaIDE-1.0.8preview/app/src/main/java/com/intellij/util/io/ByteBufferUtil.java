package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.containers.Unsafe;
import com.intellij.util.lang.JavaVersion;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ByteBufferUtil {
    private static final MethodHandle invokeCleaner = findInvokeCleaner();
    private static final MethodHandle address = findAddress();
    private static final int byteArrayBaseOffset = byteArrayBaseOffset();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 1 || i == 2) {
            objArr[0] = "src";
        } else if (i != 3) {
            objArr[0] = "buffer";
        } else {
            objArr[0] = "com/intellij/util/io/ByteBufferUtil";
        }
        if (i != 3) {
            objArr[1] = "com/intellij/util/io/ByteBufferUtil";
        } else {
            objArr[1] = "getLogger";
        }
        if (i == 1) {
            objArr[2] = "copyMemory";
        } else if (i == 2) {
            objArr[2] = "getAddress";
        } else if (i != 3) {
            objArr[2] = "cleanBuffer";
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    private static int byteArrayBaseOffset() {
        return Unsafe.arrayBaseOffset(byte[].class);
    }

    public static boolean cleanBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(0);
        }
        if (!byteBuffer.isDirect()) {
            return true;
        }
        MethodHandle methodHandle = invokeCleaner;
        if (methodHandle != null) {
            try {
                (void) methodHandle.invoke(byteBuffer);
                return true;
            } catch (Throwable th) {
                getLogger().warn(th);
                return false;
            }
        }
        try {
            Class<?> cls = Class.forName("sun.nio.ch.DirectBuffer");
            Class<?> cls2 = Class.forName("sun.misc.Cleaner");
            Object objInvoke = cls.getDeclaredMethod("cleaner", null).invoke(byteBuffer, null);
            if (objInvoke != null) {
                cls2.getDeclaredMethod("clean", null).invoke(objInvoke, null);
            }
            return true;
        } catch (Exception e) {
            getLogger().warn(e);
            return false;
        }
    }

    public static void copyMemory(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(1);
        }
        MethodHandle methodHandle = address;
        if (methodHandle != null && byteBuffer.isDirect()) {
            try {
                Unsafe.copyMemory(null, (long) methodHandle.invoke(byteBuffer) + ((long) i), bArr, byteArrayBaseOffset + i2, i3);
                return;
            } catch (Throwable th) {
                getLogger().warn(th);
            }
        }
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i);
        byteBufferDuplicate.get(bArr, i2, i3);
    }

    private static MethodHandle findAddress() {
        try {
            if (JavaVersion.current().feature < 9) {
                return null;
            }
            return MethodHandles.lookup().findVirtual(Class.forName("sun.nio.ch.DirectBuffer"), "address", MethodType.methodType(Long.TYPE));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static MethodHandle findInvokeCleaner() {
        try {
            if (JavaVersion.current().feature < 9) {
                return null;
            }
            Object unsafe = ReflectionUtil.getUnsafe();
            return MethodHandles.publicLookup().findVirtual(unsafe.getClass(), "invokeCleaner", MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class)).bindTo(unsafe);
        } catch (Throwable th) {
            Logger.getInstance(ByteBufferUtil.class).warn(th);
            return null;
        }
    }

    private static Logger getLogger() {
        Logger logger = Logger.getInstance(ByteBufferUtil.class);
        if (logger == null) {
            $$$reportNull$$$0(3);
        }
        return logger;
    }
}

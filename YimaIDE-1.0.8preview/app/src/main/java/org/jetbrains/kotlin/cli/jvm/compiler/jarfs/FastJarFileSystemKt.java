package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.FastJarFileSystemKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u0016\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005H\u0002*$\b\u0002\u0010\u0000\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\b"}, d2 = {"RandomAccessFileAndBuffer", "Lkotlin/Pair;", "Ljava/io/RandomAccessFile;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer;", "prepareCleanerCallback", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", Argument.Delimiters.none, "kotlin-compiler"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FastJarFileSystemKt {
    public static Unit a(Method method, Method method2, ByteBuffer byteBuffer) throws IllegalAccessException, InvocationTargetException {
        byteBuffer.getClass();
        Object objInvoke = method.invoke(byteBuffer, null);
        if (objInvoke != null) {
            method2.invoke(objInvoke, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<ByteBuffer, Unit> prepareCleanerCallback() {
        try {
            final Method method = Class.forName("java.nio.DirectByteBuffer").getMethod("cleaner", null);
            method.setAccessible(true);
            final Method method2 = Class.forName("sun.misc.Cleaner").getMethod("clean", null);
            method2.setAccessible(true);
            return new Function1() { // from class: yn4
                public final Object invoke(Object obj) {
                    return FastJarFileSystemKt.a(method, method2, (ByteBuffer) obj);
                }
            };
        } catch (Exception unused) {
            return null;
        }
    }
}

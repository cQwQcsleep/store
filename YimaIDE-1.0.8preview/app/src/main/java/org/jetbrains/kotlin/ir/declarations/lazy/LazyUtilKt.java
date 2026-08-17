package org.jetbrains.kotlin.ir.declarations.lazy;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.properties.ReadWriteProperty;
import org.jetbrains.kotlin.ir.IrLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0007¨\u0006\b"}, d2 = {"lazyVar", "Lkotlin/properties/ReadWriteProperty;", "", "T", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "initializer", "Lkotlin/Function0;", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class LazyUtilKt {
    public static final <T> ReadWriteProperty<Object, T> lazyVar(IrLock irLock, Function0<? extends T> function0) {
        irLock.getClass();
        function0.getClass();
        return new SynchronizedLazyVar(irLock, function0);
    }
}

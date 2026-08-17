package org.jetbrains.kotlin.ir.backend.js.lower;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"ES6_THROWABLE_CONSTRUCTOR_SLOT", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getES6_THROWABLE_CONSTRUCTOR_SLOT", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "ES6_THROWABLE_CONSTRUCTOR_SLOT$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CaptureStackTraceInThrowablesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final Lazy ES6_THROWABLE_CONSTRUCTOR_SLOT$delegate;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference0Impl<>(CaptureStackTraceInThrowablesKt.class, "ES6_THROWABLE_CONSTRUCTOR_SLOT", "getES6_THROWABLE_CONSTRUCTOR_SLOT()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 1)};
        $$delegatedProperties = kPropertyArr;
        ES6_THROWABLE_CONSTRUCTOR_SLOT$delegate = IrDeclarationOriginImpl.Regular.INSTANCE.provideDelegate(null, kPropertyArr[0]);
    }

    public static final IrDeclarationOrigin getES6_THROWABLE_CONSTRUCTOR_SLOT() {
        return (IrDeclarationOrigin) ES6_THROWABLE_CONSTRUCTOR_SLOT$delegate.getValue();
    }
}

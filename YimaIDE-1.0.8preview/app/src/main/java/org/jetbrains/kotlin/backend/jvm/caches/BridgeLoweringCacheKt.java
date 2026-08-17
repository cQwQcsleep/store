package org.jetbrains.kotlin.backend.jvm.caches;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"<set-?>", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "cachedJvmSignature", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getCachedJvmSignature", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/org/objectweb/asm/commons/Method;", "setCachedJvmSignature", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/org/objectweb/asm/commons/Method;)V", "cachedJvmSignature$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class BridgeLoweringCacheKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute cachedJvmSignature$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(BridgeLoweringCacheKt.class, "cachedJvmSignature", "getCachedJvmSignature(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/org/objectweb/asm/commons/Method;", 1)};
        $$delegatedProperties = kPropertyArr;
        cachedJvmSignature$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Method getCachedJvmSignature(IrFunction irFunction) {
        return (Method) IrAttributeKt.get(irFunction, cachedJvmSignature$delegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setCachedJvmSignature(IrFunction irFunction, Method method) {
        IrAttributeKt.set(irFunction, cachedJvmSignature$delegate, method);
    }
}

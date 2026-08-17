package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u001b\u0010\u0006\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\u0005\u001a\u0004\b\u0007\u0010\u0003¨\u0006\f"}, d2 = {"BOUND_VALUE_PARAMETER", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getBOUND_VALUE_PARAMETER", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "BOUND_VALUE_PARAMETER$delegate", "Lkotlin/Lazy;", "BOUND_RECEIVER_PARAMETER", "getBOUND_RECEIVER_PARAMETER", "BOUND_RECEIVER_PARAMETER$delegate", "isLocalNotInner", "", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class LocalDeclarationsLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final Lazy BOUND_RECEIVER_PARAMETER$delegate;
    private static final Lazy BOUND_VALUE_PARAMETER$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference0Impl<>(LocalDeclarationsLoweringKt.class, "BOUND_VALUE_PARAMETER", "getBOUND_VALUE_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 1), new PropertyReference0Impl<>(LocalDeclarationsLoweringKt.class, "BOUND_RECEIVER_PARAMETER", "getBOUND_RECEIVER_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 1)};
        $$delegatedProperties = kPropertyArr;
        IrDeclarationOriginImpl.Synthetic synthetic = IrDeclarationOriginImpl.Synthetic.INSTANCE;
        BOUND_VALUE_PARAMETER$delegate = synthetic.provideDelegate((Object) null, kPropertyArr[0]);
        BOUND_RECEIVER_PARAMETER$delegate = synthetic.provideDelegate((Object) null, kPropertyArr[1]);
    }

    public static final IrDeclarationOrigin getBOUND_RECEIVER_PARAMETER() {
        return (IrDeclarationOrigin) BOUND_RECEIVER_PARAMETER$delegate.getValue();
    }

    public static final IrDeclarationOrigin getBOUND_VALUE_PARAMETER() {
        return (IrDeclarationOrigin) BOUND_VALUE_PARAMETER$delegate.getValue();
    }

    public static final boolean isLocalNotInner(IrClass irClass) {
        irClass.getClass();
        return Intrinsics.areEqual(irClass.getVisibility(), DescriptorVisibilities.LOCAL) && !irClass.isInner();
    }
}

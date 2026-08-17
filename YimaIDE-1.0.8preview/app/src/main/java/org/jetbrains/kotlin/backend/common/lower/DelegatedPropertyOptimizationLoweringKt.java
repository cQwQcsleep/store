package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"KPROPERTIES_FOR_DELEGATION", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getKPROPERTIES_FOR_DELEGATION", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "KPROPERTIES_FOR_DELEGATION$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class DelegatedPropertyOptimizationLoweringKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final Lazy KPROPERTIES_FOR_DELEGATION$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference0Impl<>(DelegatedPropertyOptimizationLoweringKt.class, "KPROPERTIES_FOR_DELEGATION", "getKPROPERTIES_FOR_DELEGATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 1)};
        $$delegatedProperties = kPropertyArr;
        KPROPERTIES_FOR_DELEGATION$delegate = IrDeclarationOriginImpl.Regular.INSTANCE.provideDelegate((Object) null, kPropertyArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrDeclarationOrigin getKPROPERTIES_FOR_DELEGATION() {
        return (IrDeclarationOrigin) KPROPERTIES_FOR_DELEGATION$delegate.getValue();
    }
}

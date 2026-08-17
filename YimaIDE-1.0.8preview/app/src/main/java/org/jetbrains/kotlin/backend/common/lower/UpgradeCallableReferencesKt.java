package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"LAMBDA_EXTENSION_RECEIVER", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getLAMBDA_EXTENSION_RECEIVER", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "LAMBDA_EXTENSION_RECEIVER$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UpgradeCallableReferencesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final Lazy LAMBDA_EXTENSION_RECEIVER$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference0Impl<>(UpgradeCallableReferencesKt.class, "LAMBDA_EXTENSION_RECEIVER", "getLAMBDA_EXTENSION_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 1)};
        $$delegatedProperties = kPropertyArr;
        LAMBDA_EXTENSION_RECEIVER$delegate = IrDeclarationOriginImpl.Regular.INSTANCE.provideDelegate((Object) null, kPropertyArr[0]);
    }

    public static final IrDeclarationOrigin getLAMBDA_EXTENSION_RECEIVER() {
        return (IrDeclarationOrigin) LAMBDA_EXTENSION_RECEIVER$delegate.getValue();
    }
}

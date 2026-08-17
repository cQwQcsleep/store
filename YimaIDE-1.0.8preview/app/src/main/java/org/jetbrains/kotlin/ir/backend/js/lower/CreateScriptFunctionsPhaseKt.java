package org.jetbrains.kotlin.ir.backend.js.lower;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"SCRIPT_FUNCTION", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getSCRIPT_FUNCTION", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "SCRIPT_FUNCTION$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CreateScriptFunctionsPhaseKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final Lazy SCRIPT_FUNCTION$delegate;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference0Impl<>(CreateScriptFunctionsPhaseKt.class, "SCRIPT_FUNCTION", "getSCRIPT_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 1)};
        $$delegatedProperties = kPropertyArr;
        SCRIPT_FUNCTION$delegate = IrDeclarationOriginImpl.Regular.INSTANCE.provideDelegate(null, kPropertyArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrDeclarationOrigin getSCRIPT_FUNCTION() {
        return (IrDeclarationOrigin) SCRIPT_FUNCTION$delegate.getValue();
    }
}

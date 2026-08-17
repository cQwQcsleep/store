package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"canHaveDefaultValue", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class DefaultArgumentStubGeneratorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean canHaveDefaultValue(IrValueParameter irValueParameter) {
        if (irValueParameter.getKind() == IrParameterKind.DispatchReceiver || irValueParameter.getKind() == IrParameterKind.ExtensionReceiver) {
            return false;
        }
        IrDeclarationOrigin origin = irValueParameter.getOrigin();
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.INSTANCE;
        return (Intrinsics.areEqual(origin, companion.getMOVED_DISPATCH_RECEIVER()) || Intrinsics.areEqual(irValueParameter.getOrigin(), companion.getMOVED_EXTENSION_RECEIVER())) ? false : true;
    }
}

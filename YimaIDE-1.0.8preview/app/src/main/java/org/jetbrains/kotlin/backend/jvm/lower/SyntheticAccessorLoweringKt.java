package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrFakeOverrideUtilsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002¨\u0006\u0002"}, d2 = {"resolveFieldFakeOverride", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SyntheticAccessorLoweringKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final IrField resolveFieldFakeOverride(IrField irField) {
        IrPropertySymbol correspondingPropertySymbol = irField.getCorrespondingPropertySymbol();
        IrProperty owner = correspondingPropertySymbol != null ? correspondingPropertySymbol.getOwner() : null;
        if (owner == null || !owner.isFakeOverride()) {
            return irField;
        }
        IrField backingField = IrFakeOverrideUtilsKt.resolveFakeOverrideOrFail(owner).getBackingField();
        if (backingField != null) {
            return backingField;
        }
        mu3.a("Fake override property ", RenderIrElementKt.render$default(owner, (DumpIrTreeOptions) null, 1, (Object) null), " with backing field overrides a real property with no backing field: ", RenderIrElementKt.render$default(IrFakeOverrideUtilsKt.resolveFakeOverrideOrFail(owner), (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }
}

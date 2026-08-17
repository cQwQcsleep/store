package org.jetbrains.kotlin.backend.common.linkage.partial;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class PartiallyLinkedIrTreePatcher$ExpressionTransformer$checkArgumentsAndValueParameters$functionsToCheckDefaultValues$2 implements Function0<List<? extends IrFunction>> {
    final /* synthetic */ IrFunction $function;

    public PartiallyLinkedIrTreePatcher$ExpressionTransformer$checkArgumentsAndValueParameters$functionsToCheckDefaultValues$2(IrFunction irFunction) {
        this.$function = irFunction;
    }

    public final List<IrFunction> invoke() {
        IrOverridableDeclaration irOverridableDeclaration = this.$function;
        if (irOverridableDeclaration instanceof IrConstructor) {
            return CollectionsKt.listOf(irOverridableDeclaration);
        }
        if (!(irOverridableDeclaration instanceof IrSimpleFunction)) {
            bu8.a();
            return null;
        }
        List listAllOverridden = IrUtilsKt.allOverridden(irOverridableDeclaration, true);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listAllOverridden) {
            IrSimpleFunction irSimpleFunction = (IrSimpleFunction) obj;
            if (!irSimpleFunction.isFakeOverride() && !Intrinsics.areEqual(irSimpleFunction.getOrigin(), IrDeclarationOrigin.INSTANCE.getDELEGATED_MEMBER())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

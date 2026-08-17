package org.jetbrains.kotlin.backend.common.serialization;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"knownBuiltins", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "getKnownBuiltins", "(Lorg/jetbrains/kotlin/ir/IrBuiltIns;)Ljava/util/List;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class DeclarationTableKt {
    public static final List<IrDeclaration> getKnownBuiltins(IrBuiltIns irBuiltIns) {
        irBuiltIns.getClass();
        return irBuiltIns.getOperatorsPackageFragment().getDeclarations();
    }
}

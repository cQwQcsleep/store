package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationContainer;
import org.jetbrains.kotlin.ir.declarations.IrFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/DeclarationContainerLoweringPass;", "Lorg/jetbrains/kotlin/backend/common/FileLoweringPass;", "lower", "", "irDeclarationContainer", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationContainer;", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface DeclarationContainerLoweringPass extends FileLoweringPass {
    void lower(IrDeclarationContainer irDeclarationContainer);

    @Override // org.jetbrains.kotlin.backend.common.FileLoweringPass
    default void lower(IrFile irFile) {
        irFile.getClass();
        LowerKt.runOnFilePostfix(this, irFile);
    }
}

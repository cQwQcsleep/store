package org.jetbrains.kotlin.ir.validation.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.ir.validation.IrValidationError;
import org.jetbrains.kotlin.ir.validation.checkers.context.ContextUpdater;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u0007\b\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/validation/checkers/IrChecker;", "Lorg/jetbrains/kotlin/ir/validation/IrValidationError$Cause;", "requiredContextUpdaters", "", "Lorg/jetbrains/kotlin/ir/validation/checkers/context/ContextUpdater;", "getRequiredContextUpdaters", "()Ljava/util/Set;", "Lorg/jetbrains/kotlin/ir/validation/checkers/IrElementChecker;", "Lorg/jetbrains/kotlin/ir/validation/checkers/IrSymbolChecker;", "Lorg/jetbrains/kotlin/ir/validation/checkers/IrTypeChecker;", "org.jetbrains.kotlin:ir.validation"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrChecker extends IrValidationError.Cause {
    default Set<ContextUpdater> getRequiredContextUpdaters() {
        return SetsKt.emptySet();
    }
}

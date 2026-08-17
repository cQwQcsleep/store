package org.jetbrains.kotlin.ir.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.ModuleLoweringPass;
import org.jetbrains.kotlin.backend.common.PreSerializationLoweringContext;
import org.jetbrains.kotlin.backend.common.phaser.PhasePrerequisites;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.inline.checkers.IrInlineDeclarationChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@PhasePrerequisites({FunctionInlining.class})
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bÊ\u0001\u0010\b\u000f\u0012\f\b\u0010\u0012\b\b\fJ\u0004\b\t0\u0011¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/ir/inline/InlineDeclarationCheckerLowering;", "Context", "Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;", "Lorg/jetbrains/kotlin/backend/common/ModuleLoweringPass;", "context", "<init>", "(Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;)V", "getContext", "()Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;", "Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "org.jetbrains.kotlin:ir.inline", "Lorg/jetbrains/kotlin/backend/common/phaser/PhasePrerequisites;", "value", "Lorg/jetbrains/kotlin/ir/inline/FunctionInlining;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InlineDeclarationCheckerLowering<Context extends PreSerializationLoweringContext> implements ModuleLoweringPass {
    private final Context context;

    public InlineDeclarationCheckerLowering(Context context) {
        context.getClass();
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        irModule.accept(new IrInlineDeclarationChecker(this.context), null);
    }
}

package org.jetbrains.kotlin.cfg.pseudocode.instructions;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.BlockScope;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u0004\u0018\u00010\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/BlockScope;", "", "parentScope", "block", "Lorg/jetbrains/kotlin/psi/KtElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/BlockScope;Lorg/jetbrains/kotlin/psi/KtElement;)V", "getBlock", "()Lorg/jetbrains/kotlin/psi/KtElement;", "depth", "", "getDepth", "()I", "blockScopeForContainingDeclaration", "getBlockScopeForContainingDeclaration", "()Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/BlockScope;", "blockScopeForContainingDeclaration$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BlockScope {
    private final KtElement block;

    /* JADX INFO: renamed from: blockScopeForContainingDeclaration$delegate, reason: from kotlin metadata */
    private final Lazy blockScopeForContainingDeclaration;
    private final int depth;
    private final BlockScope parentScope;

    public BlockScope(BlockScope blockScope, KtElement ktElement) {
        ktElement.getClass();
        this.parentScope = blockScope;
        this.block = ktElement;
        this.depth = (blockScope != null ? blockScope.depth : 0) + 1;
        this.blockScopeForContainingDeclaration = LazyKt.lazy(new Function0() { // from class: mx0
            public final Object invoke() {
                return BlockScope.a(this.b);
            }
        });
    }

    public static BlockScope a(BlockScope blockScope) {
        while (blockScope != null && !(blockScope.block instanceof KtDeclaration)) {
            blockScope = blockScope.parentScope;
        }
        return blockScope;
    }

    public final KtElement getBlock() {
        return this.block;
    }

    public final BlockScope getBlockScopeForContainingDeclaration() {
        return (BlockScope) this.blockScopeForContainingDeclaration.getValue();
    }

    public final int getDepth() {
        return this.depth;
    }
}

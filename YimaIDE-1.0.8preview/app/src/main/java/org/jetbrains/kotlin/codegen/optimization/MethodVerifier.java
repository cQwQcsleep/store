package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicVerifier;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/MethodVerifier;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "checkPoint", Argument.Delimiters.none, "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "transform", Argument.Delimiters.none, "internalClassName", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MethodVerifier extends MethodTransformer {
    private final String checkPoint;
    private final GenerationState generationState;

    public MethodVerifier(String str, GenerationState generationState) {
        str.getClass();
        generationState.getClass();
        this.checkPoint = str;
        this.generationState = generationState;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        if (this.generationState.getConfig().getShouldValidateBytecode()) {
            try {
                MethodTransformer.analyze(internalClassName, methodNode, new BasicVerifier());
            } catch (Throwable th) {
                throw new AssertionError(this.checkPoint + ": incorrect bytecode", th);
            }
        }
    }
}

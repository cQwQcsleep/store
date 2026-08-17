package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.HashSet;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR!\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u000fj\b\u0012\u0004\u0012\u00020\u0003`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/DecompiledTryDescriptor;", Argument.Delimiters.none, "tryStartLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;)V", "getTryStartLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "defaultHandlerTcb", "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "getDefaultHandlerTcb", "()Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "setDefaultHandlerTcb", "(Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;)V", "handlerStartLabels", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getHandlerStartLabels", "()Ljava/util/HashSet;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class DecompiledTryDescriptor {
    private TryCatchBlockNode defaultHandlerTcb;
    private final HashSet<LabelNode> handlerStartLabels;
    private final LabelNode tryStartLabel;

    public DecompiledTryDescriptor(LabelNode labelNode) {
        labelNode.getClass();
        this.tryStartLabel = labelNode;
        this.handlerStartLabels = new HashSet<>();
    }

    public final TryCatchBlockNode getDefaultHandlerTcb() {
        return this.defaultHandlerTcb;
    }

    public final HashSet<LabelNode> getHandlerStartLabels() {
        return this.handlerStartLabels;
    }

    public final LabelNode getTryStartLabel() {
        return this.tryStartLabel;
    }

    public final void setDefaultHandlerTcb(TryCatchBlockNode tryCatchBlockNode) {
        this.defaultHandlerTcb = tryCatchBlockNode;
    }
}

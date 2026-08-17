package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/DeferredMethodVisitor;", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "intermediate", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "resultNode", "Lkotlin/Function0;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lkotlin/jvm/functions/Function0;)V", "getIntermediate", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "visitEnd", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeferredMethodVisitor extends MethodVisitor {
    private final MethodNode intermediate;
    private final Function0<MethodVisitor> resultNode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeferredMethodVisitor(MethodNode methodNode, Function0<? extends MethodVisitor> function0) {
        super(589824, methodNode);
        methodNode.getClass();
        function0.getClass();
        this.intermediate = methodNode;
        this.resultNode = function0;
    }

    public final MethodNode getIntermediate() {
        return this.intermediate;
    }

    public void visitEnd() {
        super.visitEnd();
        this.intermediate.accept((MethodVisitor) this.resultNode.invoke());
    }
}

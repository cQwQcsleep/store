package org.jetbrains.kotlin.codegen.optimization;

import java.util.ListIterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/RedundantCheckcastsBeforeAastoreMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantCheckcastsBeforeAastoreMethodTransformer extends MethodTransformer {
    public static final RedundantCheckcastsBeforeAastoreMethodTransformer INSTANCE = new RedundantCheckcastsBeforeAastoreMethodTransformer();

    private RedundantCheckcastsBeforeAastoreMethodTransformer() {
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        AbstractInsnNode next;
        internalClassName.getClass();
        methodNode.getClass();
        ListIterator it = methodNode.instructions.iterator();
        it.getClass();
        while (it.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) it.next();
            if (abstractInsnNode.getOpcode() == 192 && (next = abstractInsnNode.getNext()) != null && next.getOpcode() == 83) {
                ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
                AbstractInsnNode previous = abstractInsnNode.getPrevious();
                previous.getClass();
                boolean zIsOperationReifiedMarker = companion.isOperationReifiedMarker(previous);
                it.remove();
                if (zIsOperationReifiedMarker) {
                    for (int i = 1; i < 4; i++) {
                        it.previous();
                        it.remove();
                    }
                }
            }
        }
    }
}

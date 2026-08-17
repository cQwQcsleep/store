package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"reificationArgument", "Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "getReificationArgument", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;)Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "operationKind", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$OperationKind;", "getOperationKind", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;)Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$OperationKind;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReifiedTypeInlinerKt {
    public static final ReifiedTypeInliner.OperationKind getOperationKind(MethodInsnNode methodInsnNode) {
        AbstractInsnNode previous;
        Integer intConstant;
        methodInsnNode.getClass();
        AbstractInsnNode previous2 = methodInsnNode.getPrevious();
        if (previous2 == null || (previous = previous2.getPrevious()) == null || (intConstant = UtilKt.getIntConstant(previous)) == null) {
            return null;
        }
        return (ReifiedTypeInliner.OperationKind) CollectionsKt.getOrNull(ReifiedTypeInliner.OperationKind.getEntries(), intConstant.intValue());
    }

    public static final ReificationArgument getReificationArgument(MethodInsnNode methodInsnNode) {
        methodInsnNode.getClass();
        LdcInsnNode previous = methodInsnNode.getPrevious();
        previous.getClass();
        if (previous.getOpcode() != 18) {
            return null;
        }
        Object obj = previous.cst;
        obj.getClass();
        String str = (String) obj;
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (str.charAt(i) != '[') {
                return new ReificationArgument(StringsKt.removeSuffix(str.substring(i), "?"), StringsKt.endsWith$default(str, '?', false, 2, (Object) null), i);
            }
            i++;
        }
        i = -1;
        return new ReificationArgument(StringsKt.removeSuffix(str.substring(i), "?"), StringsKt.endsWith$default(str, '?', false, 2, (Object) null), i);
    }
}

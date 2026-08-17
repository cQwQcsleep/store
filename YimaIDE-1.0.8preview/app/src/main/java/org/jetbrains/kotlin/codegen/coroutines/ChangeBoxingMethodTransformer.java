package org.jetbrains.kotlin.codegen.coroutines;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.coroutines.ChangeBoxingMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreterKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ChangeBoxingMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "wrapperToInternalBoxing", Argument.Delimiters.none, Argument.Delimiters.none, "transform", Argument.Delimiters.none, "internalClassName", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ChangeBoxingMethodTransformer extends MethodTransformer {
    public static final ChangeBoxingMethodTransformer INSTANCE = new ChangeBoxingMethodTransformer();
    private static final Map<String, String> wrapperToInternalBoxing;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class EntriesMappings {
        public static final /* synthetic */ EnumEntries<JvmPrimitiveType> entries$0 = EnumEntriesKt.enumEntries(JvmPrimitiveType.values());
    }

    static {
        HashMap map = new HashMap();
        for (JvmPrimitiveType jvmPrimitiveType : EntriesMappings.entries$0) {
            FqName wrapperFqName = jvmPrimitiveType.getWrapperFqName();
            wrapperFqName.getClass();
            String str = CodegenUtilKt.topLevelClassInternalName(wrapperFqName);
            StringBuilder sb = new StringBuilder("box");
            String javaKeywordName = jvmPrimitiveType.getJavaKeywordName();
            javaKeywordName.getClass();
            if (javaKeywordName.length() > 0) {
                javaKeywordName = Character.toUpperCase(javaKeywordName.charAt(0)) + javaKeywordName.substring(1);
            }
            sb.append(javaKeywordName);
            map.put(str, sb.toString());
        }
        wrapperToInternalBoxing = map;
    }

    private ChangeBoxingMethodTransformer() {
    }

    public static boolean a(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return BoxingInterpreterKt.isPrimitiveBoxing(abstractInsnNode);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (MethodInsnNode methodInsnNode : SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: re1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ChangeBoxingMethodTransformer.a((AbstractInsnNode) obj));
            }
        })) {
            methodInsnNode.getOpcode();
            MethodInsnNode methodInsnNode2 = methodInsnNode;
            String str = wrapperToInternalBoxing.get(methodInsnNode2.owner);
            if (str == null) {
                pe1.a("expected primitive wrapper, but got ", methodInsnNode2.owner);
                return;
            }
            methodNode.instructions.set(methodInsnNode, new MethodInsnNode(methodInsnNode2.getOpcode(), ChangeBoxingMethodTransformerKt.BOXING_CLASS_INTERNAL_NAME, str, methodInsnNode2.desc, false));
        }
    }
}

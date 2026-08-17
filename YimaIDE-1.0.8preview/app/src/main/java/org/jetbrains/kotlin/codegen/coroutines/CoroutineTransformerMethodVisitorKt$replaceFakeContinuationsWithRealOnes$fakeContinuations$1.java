package org.jetbrains.kotlin.codegen.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CoroutineTransformerMethodVisitorKt$replaceFakeContinuationsWithRealOnes$fakeContinuations$1 extends FunctionReferenceImpl implements Function1<AbstractInsnNode, Boolean> {
    public static final CoroutineTransformerMethodVisitorKt$replaceFakeContinuationsWithRealOnes$fakeContinuations$1 INSTANCE = new CoroutineTransformerMethodVisitorKt$replaceFakeContinuationsWithRealOnes$fakeContinuations$1();

    public CoroutineTransformerMethodVisitorKt$replaceFakeContinuationsWithRealOnes$fakeContinuations$1() {
        super(1, InlineCodegenUtilsKt.class, "isFakeContinuationMarker", "isFakeContinuationMarker(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Z", 1);
    }

    public final Boolean invoke(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return Boolean.valueOf(InlineCodegenUtilsKt.isFakeContinuationMarker(abstractInsnNode));
    }
}

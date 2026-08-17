package org.jetbrains.kotlin.codegen.optimization.transformer;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B!\b\u0016\u0012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006\"\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0004\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/transformer/CompositeMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "transformers", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;)V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class CompositeMethodTransformer extends MethodTransformer {
    private final List<MethodTransformer> transformers;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CompositeMethodTransformer(MethodTransformer... methodTransformerArr) {
        this((List<? extends MethodTransformer>) ArraysKt.filterNotNull(methodTransformerArr));
        methodTransformerArr.getClass();
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        Iterator<T> it = this.transformers.iterator();
        while (it.hasNext()) {
            ((MethodTransformer) it.next()).transform(internalClassName, methodNode);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeMethodTransformer(List<? extends MethodTransformer> list) {
        list.getClass();
        this.transformers = list;
    }
}

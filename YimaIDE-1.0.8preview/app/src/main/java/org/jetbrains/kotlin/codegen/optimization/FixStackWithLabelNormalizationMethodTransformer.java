package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.transformer.CompositeMethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/FixStackWithLabelNormalizationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/CompositeMethodTransformer;", "<init>", "()V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FixStackWithLabelNormalizationMethodTransformer extends CompositeMethodTransformer {
    public FixStackWithLabelNormalizationMethodTransformer() {
        super(new LabelNormalizationMethodTransformer(), new FixStackMethodTransformer());
    }
}

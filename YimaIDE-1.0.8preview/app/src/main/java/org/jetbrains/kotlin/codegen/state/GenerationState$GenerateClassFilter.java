package org.jetbrains.kotlin.codegen.state;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/GenerationState$GenerateClassFilter;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "shouldGenerateClass", "", "processingClassOrObject", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "shouldGeneratePackagePart", "ktFile", "Lorg/jetbrains/kotlin/psi/KtFile;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class GenerationState$GenerateClassFilter {
    public abstract boolean shouldGenerateClass(KtClassOrObject processingClassOrObject);

    public abstract boolean shouldGeneratePackagePart(KtFile ktFile);
}

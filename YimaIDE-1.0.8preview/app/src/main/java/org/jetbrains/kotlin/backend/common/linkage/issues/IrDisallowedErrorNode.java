package org.jetbrains.kotlin.backend.common.linkage.issues;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrDisallowedErrorNode;", "Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrDeserializationException;", "clazz", "Ljava/lang/Class;", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Class;)V", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrDisallowedErrorNode extends IrDeserializationException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrDisallowedErrorNode(Class<? extends IrAnnotationContainer> cls) {
        super(cls.getClass().getSimpleName().concat(" found but error nodes are not allowed."), null);
        cls.getClass();
    }
}

package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrField;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/EnumEntriesIntrinsicMappingsCache;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getEnumEntriesIntrinsicMappings", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "containingClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "enumClass", "generateMappingsClasses", "", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class EnumEntriesIntrinsicMappingsCache {
    public abstract void generateMappingsClasses();

    public abstract IrField getEnumEntriesIntrinsicMappings(IrClass containingClass, IrClass enumClass);
}

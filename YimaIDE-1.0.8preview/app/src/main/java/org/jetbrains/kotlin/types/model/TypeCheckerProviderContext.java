package org.jetbrains.kotlin.types.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.types.TypeCheckerState;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H&J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "", "newTypeCheckerState", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "typeSystemContext", "Lorg/jetbrains/kotlin/types/model/TypeSystemContext;", "errorTypesEqualToAnything", "", "stubTypesEqualToAnything", "dnnTypesEqualToFlexible", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TypeCheckerProviderContext {
    static /* synthetic */ TypeCheckerState newTypeCheckerState$default(TypeCheckerProviderContext typeCheckerProviderContext, TypeSystemContext typeSystemContext, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: newTypeCheckerState");
            return null;
        }
        if ((i & 8) != 0) {
            z3 = false;
        }
        return typeCheckerProviderContext.newTypeCheckerState(typeSystemContext, z, z2, z3);
    }

    TypeCheckerState newTypeCheckerState(TypeSystemContext typeSystemContext, boolean errorTypesEqualToAnything, boolean stubTypesEqualToAnything, boolean dnnTypesEqualToFlexible);

    default TypeCheckerState newTypeCheckerState(boolean errorTypesEqualToAnything, boolean stubTypesEqualToAnything, boolean dnnTypesEqualToFlexible) {
        if (this instanceof TypeSystemContext) {
            return newTypeCheckerState((TypeSystemContext) this, errorTypesEqualToAnything, stubTypesEqualToAnything, dnnTypesEqualToFlexible);
        }
        xz8.a("All current implementations are expected to be TypeSystemContext, but ", Reflection.getOrCreateKotlinClass(getClass()).getQualifiedName(), " found");
        return null;
    }

    static /* synthetic */ TypeCheckerState newTypeCheckerState$default(TypeCheckerProviderContext typeCheckerProviderContext, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: newTypeCheckerState");
            return null;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        return typeCheckerProviderContext.newTypeCheckerState(z, z2, z3);
    }
}

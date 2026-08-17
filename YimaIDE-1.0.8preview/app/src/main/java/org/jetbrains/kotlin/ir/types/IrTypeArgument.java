package org.jetbrains.kotlin.ir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H¦\u0082\u0004J\n\u0010\u0006\u001a\u00020\u0007H¦\u0080\u0004\u0082\u0001\u0002\b\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "equals", "", "other", "", "hashCode", "", "Lorg/jetbrains/kotlin/ir/types/IrStarProjection;", "Lorg/jetbrains/kotlin/ir/types/IrTypeProjection;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrTypeArgument extends TypeArgumentMarker {
    boolean equals(Object other);

    int hashCode();
}

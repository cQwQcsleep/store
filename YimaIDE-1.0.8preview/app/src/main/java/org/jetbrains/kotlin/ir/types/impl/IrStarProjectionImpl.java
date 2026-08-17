package org.jetbrains.kotlin.ir.types.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.types.IrStarProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/impl/IrStarProjectionImpl;", "Lorg/jetbrains/kotlin/ir/types/IrStarProjection;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class IrStarProjectionImpl implements IrStarProjection {
    public static final IrStarProjectionImpl INSTANCE = new IrStarProjectionImpl();

    private IrStarProjectionImpl() {
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeArgument
    public boolean equals(Object other) {
        return this == other || (other instanceof IrStarProjectionImpl);
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeArgument
    public int hashCode() {
        return 247260265;
    }

    public String toString() {
        return "IrStarProjectionImpl";
    }
}

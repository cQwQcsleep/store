package org.jetbrains.kotlin.ir.types.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/impl/IrTypeProjectionImpl;", "Lorg/jetbrains/kotlin/ir/types/IrTypeProjection;", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "<init>", "(Lorg/jetbrains/kotlin/ir/types/IrType;Lorg/jetbrains/kotlin/types/Variance;)V", "getType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrTypeProjectionImpl implements IrTypeProjection {
    private final IrType type;
    private final Variance variance;

    public IrTypeProjectionImpl(IrType irType, Variance variance) {
        irType.getClass();
        variance.getClass();
        this.type = irType;
        this.variance = variance;
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeArgument
    public boolean equals(Object other) {
        if (!(other instanceof IrTypeProjectionImpl)) {
            return false;
        }
        IrTypeProjectionImpl irTypeProjectionImpl = (IrTypeProjectionImpl) other;
        return Intrinsics.areEqual(getType(), irTypeProjectionImpl.getType()) && getVariance() == irTypeProjectionImpl.getVariance();
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeProjection
    public IrType getType() {
        return this.type;
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeProjection
    public Variance getVariance() {
        return this.variance;
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeArgument
    public int hashCode() {
        return (getType().hashCode() * 31) + getVariance().hashCode();
    }
}

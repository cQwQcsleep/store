package org.jetbrains.kotlin.ir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.mpp.TypeRefMarker;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\t\b\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H¦\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H¦\u0080\u0004R\u0011\u0010\u0007\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0001\u0003\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/ir/types/IrTypeProjection;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/kotlin/mpp/TypeRefMarker;", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", "<init>", "()V", "type", "getType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "originalKotlinType", "Lorg/jetbrains/kotlin/types/KotlinType;", "getOriginalKotlinType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "equals", "", "other", "", "hashCode", "", "Lorg/jetbrains/kotlin/ir/types/IrDynamicType;", "Lorg/jetbrains/kotlin/ir/types/IrErrorType;", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrType implements IrAnnotationContainer, IrTypeProjection, TypeRefMarker, KotlinTypeMarker {
    public /* synthetic */ IrType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeArgument
    public abstract boolean equals(Object other);

    public KotlinType getOriginalKotlinType() {
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeProjection
    public final IrType getType() {
        return this;
    }

    @Override // org.jetbrains.kotlin.ir.types.IrTypeArgument
    public abstract int hashCode();

    private IrType() {
    }
}

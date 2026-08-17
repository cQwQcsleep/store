package org.jetbrains.kotlin.ir.types.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0082\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00020\u0001X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/impl/IrDelegatedSimpleType;", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "delegate", "getDelegate", "()Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "classifier", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "getClassifier", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "nullability", "Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "getNullability", "()Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "arguments", "", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "getArguments", "()Ljava/util/List;", "annotations", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "getAnnotations", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrDelegatedSimpleType extends IrSimpleType {
    public boolean equals(Object other) {
        return Intrinsics.areEqual(getDelegate(), other);
    }

    public List<IrAnnotation> getAnnotations() {
        return getDelegate().getAnnotations();
    }

    public List<IrTypeArgument> getArguments() {
        return getDelegate().getArguments();
    }

    public IrClassifierSymbol getClassifier() {
        return getDelegate().getClassifier();
    }

    public abstract IrSimpleType getDelegate();

    public SimpleTypeNullability getNullability() {
        return getDelegate().getNullability();
    }

    public int hashCode() {
        return getDelegate().hashCode();
    }

    public String toString() {
        return getDelegate().toString();
    }
}

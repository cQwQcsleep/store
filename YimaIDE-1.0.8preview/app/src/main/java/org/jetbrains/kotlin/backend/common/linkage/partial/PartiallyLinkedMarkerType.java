package org.jetbrains.kotlin.backend.common.linkage.partial;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0082\u0004J\n\u0010\u001e\u001a\u00020\u001fH\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000e¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartiallyLinkedMarkerType;", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "builtIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "unusableClassifier", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrBuiltIns;Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;)V", "getUnusableClassifier", "()Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;", "annotations", "", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "getAnnotations", "()Ljava/util/List;", "classifier", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getClassifier", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "nullability", "Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "getNullability", "()Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "arguments", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "getArguments", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PartiallyLinkedMarkerType extends IrSimpleType {
    private final IrClassSymbol classifier;
    private final ClassifierPartialLinkageStatus.Unusable unusableClassifier;

    public PartiallyLinkedMarkerType(IrBuiltIns irBuiltIns, ClassifierPartialLinkageStatus.Unusable unusable) {
        irBuiltIns.getClass();
        unusable.getClass();
        this.unusableClassifier = unusable;
        this.classifier = irBuiltIns.getAnyClass();
    }

    public boolean equals(Object other) {
        PartiallyLinkedMarkerType partiallyLinkedMarkerType = other instanceof PartiallyLinkedMarkerType ? (PartiallyLinkedMarkerType) other : null;
        return Intrinsics.areEqual(partiallyLinkedMarkerType != null ? partiallyLinkedMarkerType.unusableClassifier : null, this.unusableClassifier);
    }

    public List<IrAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    public List<IrTypeArgument> getArguments() {
        return CollectionsKt.emptyList();
    }

    public SimpleTypeNullability getNullability() {
        return SimpleTypeNullability.MARKED_NULLABLE;
    }

    public final ClassifierPartialLinkageStatus.Unusable getUnusableClassifier() {
        return this.unusableClassifier;
    }

    public int hashCode() {
        return this.unusableClassifier.hashCode();
    }

    /* JADX INFO: renamed from: getClassifier, reason: from getter and merged with bridge method [inline-methods] */
    public IrClassSymbol m155getClassifier() {
        return this.classifier;
    }
}

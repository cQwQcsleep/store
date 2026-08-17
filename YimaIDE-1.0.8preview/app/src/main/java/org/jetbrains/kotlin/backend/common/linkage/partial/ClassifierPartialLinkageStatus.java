package org.jetbrains.kotlin.backend.common.linkage.partial;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus;", "", "Unusable", "Usable", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Usable;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ClassifierPartialLinkageStatus {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Usable;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Usable implements ClassifierPartialLinkageStatus {
        public static final Usable INSTANCE = new Usable();

        private Usable() {
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0005\u0006\u0007\b\t\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "CanBeRootCause", "MissingClassifier", "InvalidInheritance", "AnnotationWithUnacceptableParameter", "DueToOtherClassifier", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$DueToOtherClassifier;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Unusable extends ClassifierPartialLinkageStatus {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001\u0082\u0001\u0003\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$AnnotationWithUnacceptableParameter;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$InvalidInheritance;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$MissingClassifier;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface CanBeRootCause extends Unusable {
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$DueToOtherClassifier;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "rootCause", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "getRootCause", "()Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class DueToOtherClassifier implements Unusable {
            private final CanBeRootCause rootCause;
            private final IrClassifierSymbol symbol;

            public DueToOtherClassifier(IrClassifierSymbol irClassifierSymbol, CanBeRootCause canBeRootCause) {
                irClassifierSymbol.getClass();
                canBeRootCause.getClass();
                this.symbol = irClassifierSymbol;
                this.rootCause = canBeRootCause;
            }

            public static /* synthetic */ DueToOtherClassifier copy$default(DueToOtherClassifier dueToOtherClassifier, IrClassifierSymbol irClassifierSymbol, CanBeRootCause canBeRootCause, int i, Object obj) {
                if ((i & 1) != 0) {
                    irClassifierSymbol = dueToOtherClassifier.symbol;
                }
                if ((i & 2) != 0) {
                    canBeRootCause = dueToOtherClassifier.rootCause;
                }
                return dueToOtherClassifier.copy(irClassifierSymbol, canBeRootCause);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final IrClassifierSymbol getSymbol() {
                return this.symbol;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final CanBeRootCause getRootCause() {
                return this.rootCause;
            }

            public final DueToOtherClassifier copy(IrClassifierSymbol symbol, CanBeRootCause rootCause) {
                symbol.getClass();
                rootCause.getClass();
                return new DueToOtherClassifier(symbol, rootCause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DueToOtherClassifier)) {
                    return false;
                }
                DueToOtherClassifier dueToOtherClassifier = (DueToOtherClassifier) other;
                return Intrinsics.areEqual(this.symbol, dueToOtherClassifier.symbol) && Intrinsics.areEqual(this.rootCause, dueToOtherClassifier.rootCause);
            }

            public final CanBeRootCause getRootCause() {
                return this.rootCause;
            }

            @Override // org.jetbrains.kotlin.backend.common.linkage.partial.ClassifierPartialLinkageStatus.Unusable
            /* JADX INFO: renamed from: getSymbol */
            public IrClassifierSymbol mo150getSymbol() {
                return this.symbol;
            }

            public int hashCode() {
                return (this.symbol.hashCode() * 31) + this.rootCause.hashCode();
            }

            public String toString() {
                return "DueToOtherClassifier(symbol=" + this.symbol + ", rootCause=" + this.rootCause + Util.C_PARAM_END;
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$MissingClassifier;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class MissingClassifier implements CanBeRootCause {
            private final IrClassifierSymbol symbol;

            public MissingClassifier(IrClassifierSymbol irClassifierSymbol) {
                irClassifierSymbol.getClass();
                this.symbol = irClassifierSymbol;
            }

            public static /* synthetic */ MissingClassifier copy$default(MissingClassifier missingClassifier, IrClassifierSymbol irClassifierSymbol, int i, Object obj) {
                if ((i & 1) != 0) {
                    irClassifierSymbol = missingClassifier.symbol;
                }
                return missingClassifier.copy(irClassifierSymbol);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final IrClassifierSymbol getSymbol() {
                return this.symbol;
            }

            public final MissingClassifier copy(IrClassifierSymbol symbol) {
                symbol.getClass();
                return new MissingClassifier(symbol);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof MissingClassifier) && Intrinsics.areEqual(this.symbol, ((MissingClassifier) other).symbol);
            }

            @Override // org.jetbrains.kotlin.backend.common.linkage.partial.ClassifierPartialLinkageStatus.Unusable
            /* JADX INFO: renamed from: getSymbol */
            public IrClassifierSymbol mo150getSymbol() {
                return this.symbol;
            }

            public int hashCode() {
                return this.symbol.hashCode();
            }

            public String toString() {
                return "MissingClassifier(symbol=" + this.symbol + Util.C_PARAM_END;
            }
        }

        /* JADX INFO: renamed from: getSymbol */
        IrClassifierSymbol mo150getSymbol();

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$AnnotationWithUnacceptableParameter;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "unacceptableClassifierSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getUnacceptableClassifierSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class AnnotationWithUnacceptableParameter implements CanBeRootCause {
            private final IrClassSymbol symbol;
            private final IrClassifierSymbol unacceptableClassifierSymbol;

            public AnnotationWithUnacceptableParameter(IrClassSymbol irClassSymbol, IrClassifierSymbol irClassifierSymbol) {
                irClassSymbol.getClass();
                irClassifierSymbol.getClass();
                this.symbol = irClassSymbol;
                this.unacceptableClassifierSymbol = irClassifierSymbol;
            }

            public static /* synthetic */ AnnotationWithUnacceptableParameter copy$default(AnnotationWithUnacceptableParameter annotationWithUnacceptableParameter, IrClassSymbol irClassSymbol, IrClassifierSymbol irClassifierSymbol, int i, Object obj) {
                if ((i & 1) != 0) {
                    irClassSymbol = annotationWithUnacceptableParameter.symbol;
                }
                if ((i & 2) != 0) {
                    irClassifierSymbol = annotationWithUnacceptableParameter.unacceptableClassifierSymbol;
                }
                return annotationWithUnacceptableParameter.copy(irClassSymbol, irClassifierSymbol);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final IrClassSymbol getSymbol() {
                return this.symbol;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final IrClassifierSymbol getUnacceptableClassifierSymbol() {
                return this.unacceptableClassifierSymbol;
            }

            public final AnnotationWithUnacceptableParameter copy(IrClassSymbol symbol, IrClassifierSymbol unacceptableClassifierSymbol) {
                symbol.getClass();
                unacceptableClassifierSymbol.getClass();
                return new AnnotationWithUnacceptableParameter(symbol, unacceptableClassifierSymbol);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof AnnotationWithUnacceptableParameter)) {
                    return false;
                }
                AnnotationWithUnacceptableParameter annotationWithUnacceptableParameter = (AnnotationWithUnacceptableParameter) other;
                return Intrinsics.areEqual(this.symbol, annotationWithUnacceptableParameter.symbol) && Intrinsics.areEqual(this.unacceptableClassifierSymbol, annotationWithUnacceptableParameter.unacceptableClassifierSymbol);
            }

            public final IrClassifierSymbol getUnacceptableClassifierSymbol() {
                return this.unacceptableClassifierSymbol;
            }

            public int hashCode() {
                return (this.symbol.hashCode() * 31) + this.unacceptableClassifierSymbol.hashCode();
            }

            public String toString() {
                return "AnnotationWithUnacceptableParameter(symbol=" + this.symbol + ", unacceptableClassifierSymbol=" + this.unacceptableClassifierSymbol + Util.C_PARAM_END;
            }

            @Override // org.jetbrains.kotlin.backend.common.linkage.partial.ClassifierPartialLinkageStatus.Unusable
            /* JADX INFO: renamed from: getSymbol, reason: merged with bridge method [inline-methods] */
            public IrClassSymbol mo150getSymbol() {
                return this.symbol;
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$InvalidInheritance;", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ClassifierPartialLinkageStatus$Unusable$CanBeRootCause;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "superClassSymbols", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;Ljava/util/Collection;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getSuperClassSymbols", "()Ljava/util/Collection;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class InvalidInheritance implements CanBeRootCause {
            private final Collection<IrClassSymbol> superClassSymbols;
            private final IrClassSymbol symbol;

            public InvalidInheritance(IrClassSymbol irClassSymbol, Collection<? extends IrClassSymbol> collection) {
                irClassSymbol.getClass();
                collection.getClass();
                this.symbol = irClassSymbol;
                this.superClassSymbols = collection;
                if (collection.isEmpty()) {
                    k2d.a("Check failed.");
                    throw null;
                }
            }

            public final Collection<IrClassSymbol> getSuperClassSymbols() {
                return this.superClassSymbols;
            }

            @Override // org.jetbrains.kotlin.backend.common.linkage.partial.ClassifierPartialLinkageStatus.Unusable
            /* JADX INFO: renamed from: getSymbol, reason: from getter and merged with bridge method [inline-methods] */
            public IrClassSymbol mo150getSymbol() {
                return this.symbol;
            }
        }
    }
}

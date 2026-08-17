package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "StoredInVariable", "StoredInField", "Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue$StoredInField;", "Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue$StoredInVariable;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class BoundValue {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue$StoredInField;", "Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue;", "symbol", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/declarations/IrField;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class StoredInField extends BoundValue {
        private final IrField symbol;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StoredInField(IrField irField) {
            super(null);
            irField.getClass();
            this.symbol = irField;
        }

        public final IrField getSymbol() {
            return this.symbol;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue$StoredInVariable;", "Lorg/jetbrains/kotlin/backend/jvm/lower/BoundValue;", "symbol", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class StoredInVariable extends BoundValue {
        private final IrVariable symbol;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StoredInVariable(IrVariable irVariable) {
            super(null);
            irVariable.getClass();
            this.symbol = irVariable;
        }

        public final IrVariable getSymbol() {
            return this.symbol;
        }
    }

    public /* synthetic */ BoundValue(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private BoundValue() {
    }
}

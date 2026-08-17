package org.jetbrains.kotlin.backend.jvm.codegen;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "FunctionReturnType", "ValueParameterType", "FieldType", "TypeParameterBoundType", "Supertype", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$FieldType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$FunctionReturnType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$Supertype;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$TypeParameterBoundType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$ValueParameterType;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class TypeAnnotationPosition {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$FieldType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition;", "field", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "getField", "()Lorg/jetbrains/kotlin/ir/declarations/IrField;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FieldType extends TypeAnnotationPosition {
        private final IrField field;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FieldType(IrField irField) {
            super(null);
            irField.getClass();
            this.field = irField;
        }

        public final IrField getField() {
            return this.field;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$FunctionReturnType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition;", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FunctionReturnType extends TypeAnnotationPosition {
        private final IrFunction function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FunctionReturnType(IrFunction irFunction) {
            super(null);
            irFunction.getClass();
            this.function = irFunction;
        }

        public final IrFunction getFunction() {
            return this.function;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$Supertype;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Supertype extends TypeAnnotationPosition {
        public static final Supertype INSTANCE = new Supertype();

        private Supertype() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Supertype);
        }

        public int hashCode() {
            return 543422486;
        }

        public String toString() {
            return "Supertype";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$TypeParameterBoundType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class TypeParameterBoundType extends TypeAnnotationPosition {
        public static final TypeParameterBoundType INSTANCE = new TypeParameterBoundType();

        private TypeParameterBoundType() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof TypeParameterBoundType);
        }

        public int hashCode() {
            return -1377812536;
        }

        public String toString() {
            return "TypeParameterBoundType";
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition$ValueParameterType;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TypeAnnotationPosition;", "parameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;)V", "getParameter", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ValueParameterType extends TypeAnnotationPosition {
        private final IrValueParameter parameter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValueParameterType(IrValueParameter irValueParameter) {
            super(null);
            irValueParameter.getClass();
            this.parameter = irValueParameter;
        }

        public final IrValueParameter getParameter() {
            return this.parameter;
        }
    }

    public /* synthetic */ TypeAnnotationPosition(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private TypeAnnotationPosition() {
    }
}

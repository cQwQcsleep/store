package org.jetbrains.kotlin.backend.konan.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\b\b\t\n\u000b\f\r\u000e\u000fB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\b\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "FunctionArgument", "FunctionCallResult", "FunctionPointerParameter", "FunctionPointerReturnValue", "ObjCMethodParameter", "ObjCMethodReturnValue", "BlockParameter", "BlockReturnValue", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$BlockParameter;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$BlockReturnValue;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionArgument;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionCallResult;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionPointerParameter;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionPointerReturnValue;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$ObjCMethodParameter;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$ObjCMethodReturnValue;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class TypeLocation {
    private final IrElement element;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$BlockParameter;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "index", "", "blockLocation", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ILorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;)V", "getIndex", "()I", "getBlockLocation", "()Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class BlockParameter extends TypeLocation {
        private final TypeLocation blockLocation;
        private final int index;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BlockParameter(int i, TypeLocation typeLocation) {
            super(typeLocation.getElement(), null);
            typeLocation.getClass();
            this.index = i;
            this.blockLocation = typeLocation;
        }

        public final TypeLocation getBlockLocation() {
            return this.blockLocation;
        }

        public final int getIndex() {
            return this.index;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$BlockReturnValue;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "blockLocation", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;)V", "getBlockLocation", "()Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class BlockReturnValue extends TypeLocation {
        private final TypeLocation blockLocation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BlockReturnValue(TypeLocation typeLocation) {
            super(typeLocation.getElement(), null);
            typeLocation.getClass();
            this.blockLocation = typeLocation;
        }

        public final TypeLocation getBlockLocation() {
            return this.blockLocation;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionArgument;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "argument", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "getArgument", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FunctionArgument extends TypeLocation {
        private final IrExpression argument;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FunctionArgument(IrExpression irExpression) {
            super(irExpression, null);
            irExpression.getClass();
            this.argument = irExpression;
        }

        public final IrExpression getArgument() {
            return this.argument;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionCallResult;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;)V", "getCall", "()Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FunctionCallResult extends TypeLocation {
        private final IrFunctionAccessExpression call;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FunctionCallResult(IrFunctionAccessExpression irFunctionAccessExpression) {
            super(irFunctionAccessExpression, null);
            irFunctionAccessExpression.getClass();
            this.call = irFunctionAccessExpression;
        }

        public final IrFunctionAccessExpression getCall() {
            return this.call;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionPointerParameter;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", "index", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ILorg/jetbrains/kotlin/ir/IrElement;)V", "getIndex", "()I", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FunctionPointerParameter extends TypeLocation {
        private final int index;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FunctionPointerParameter(int i, IrElement irElement) {
            super(irElement, null);
            irElement.getClass();
            this.index = i;
        }

        public final int getIndex() {
            return this.index;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$FunctionPointerReturnValue;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FunctionPointerReturnValue extends TypeLocation {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FunctionPointerReturnValue(IrElement irElement) {
            super(irElement, null);
            irElement.getClass();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$ObjCMethodParameter;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ObjCMethodParameter extends TypeLocation {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ObjCMethodParameter(IrElement irElement) {
            super(irElement, null);
            irElement.getClass();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation$ObjCMethodReturnValue;", "Lorg/jetbrains/kotlin/backend/konan/lower/TypeLocation;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ObjCMethodReturnValue extends TypeLocation {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ObjCMethodReturnValue(IrElement irElement) {
            super(irElement, null);
            irElement.getClass();
        }
    }

    private TypeLocation(IrElement irElement) {
        this.element = irElement;
    }

    public final IrElement getElement() {
        return this.element;
    }

    public /* synthetic */ TypeLocation(IrElement irElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(irElement);
    }
}

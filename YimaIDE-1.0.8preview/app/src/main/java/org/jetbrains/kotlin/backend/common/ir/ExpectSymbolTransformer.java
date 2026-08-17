package org.jetbrains.kotlin.backend.common.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrClassReference;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrDelegatingConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrEnumConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H$J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\nH$J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\rH$J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0006\u001a\u00020\u0010H$J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020'H\u0016¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/ExpectSymbolTransformer;", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitorVoid;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getActualClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getActualProperty", "Lorg/jetbrains/kotlin/backend/common/ir/ExpectSymbolTransformer$ActualPropertyResult;", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getActualConstructor", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "getActualFunction", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "isTargetDeclaration", "", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "visitElement", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", "visitConstructorCall", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "visitDelegatingConstructorCall", "Lorg/jetbrains/kotlin/ir/expressions/IrDelegatingConstructorCall;", "visitEnumConstructorCall", "Lorg/jetbrains/kotlin/ir/expressions/IrEnumConstructorCall;", "visitCall", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "visitPropertyReference", "Lorg/jetbrains/kotlin/ir/expressions/IrPropertyReference;", "visitFunctionReference", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionReference;", "visitClassReference", "Lorg/jetbrains/kotlin/ir/expressions/IrClassReference;", "ActualPropertyResult", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExpectSymbolTransformer extends IrVisitorVoid {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0084\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/ExpectSymbolTransformer$ActualPropertyResult;", "", "propertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getterSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "setterSymbol", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;)V", "getPropertySymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getGetterSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getSetterSymbol", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class ActualPropertyResult {
        private final IrSimpleFunctionSymbol getterSymbol;
        private final IrPropertySymbol propertySymbol;
        private final IrSimpleFunctionSymbol setterSymbol;

        public ActualPropertyResult(IrPropertySymbol irPropertySymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol2) {
            irPropertySymbol.getClass();
            this.propertySymbol = irPropertySymbol;
            this.getterSymbol = irSimpleFunctionSymbol;
            this.setterSymbol = irSimpleFunctionSymbol2;
        }

        public static /* synthetic */ ActualPropertyResult copy$default(ActualPropertyResult actualPropertyResult, IrPropertySymbol irPropertySymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol2, int i, Object obj) {
            if ((i & 1) != 0) {
                irPropertySymbol = actualPropertyResult.propertySymbol;
            }
            if ((i & 2) != 0) {
                irSimpleFunctionSymbol = actualPropertyResult.getterSymbol;
            }
            if ((i & 4) != 0) {
                irSimpleFunctionSymbol2 = actualPropertyResult.setterSymbol;
            }
            return actualPropertyResult.copy(irPropertySymbol, irSimpleFunctionSymbol, irSimpleFunctionSymbol2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrPropertySymbol getPropertySymbol() {
            return this.propertySymbol;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final IrSimpleFunctionSymbol getGetterSymbol() {
            return this.getterSymbol;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final IrSimpleFunctionSymbol getSetterSymbol() {
            return this.setterSymbol;
        }

        public final ActualPropertyResult copy(IrPropertySymbol propertySymbol, IrSimpleFunctionSymbol getterSymbol, IrSimpleFunctionSymbol setterSymbol) {
            propertySymbol.getClass();
            return new ActualPropertyResult(propertySymbol, getterSymbol, setterSymbol);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActualPropertyResult)) {
                return false;
            }
            ActualPropertyResult actualPropertyResult = (ActualPropertyResult) other;
            return Intrinsics.areEqual(this.propertySymbol, actualPropertyResult.propertySymbol) && Intrinsics.areEqual(this.getterSymbol, actualPropertyResult.getterSymbol) && Intrinsics.areEqual(this.setterSymbol, actualPropertyResult.setterSymbol);
        }

        public final IrSimpleFunctionSymbol getGetterSymbol() {
            return this.getterSymbol;
        }

        public final IrPropertySymbol getPropertySymbol() {
            return this.propertySymbol;
        }

        public final IrSimpleFunctionSymbol getSetterSymbol() {
            return this.setterSymbol;
        }

        public int hashCode() {
            int iHashCode = this.propertySymbol.hashCode() * 31;
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = this.getterSymbol;
            int iHashCode2 = (iHashCode + (irSimpleFunctionSymbol == null ? 0 : irSimpleFunctionSymbol.hashCode())) * 31;
            IrSimpleFunctionSymbol irSimpleFunctionSymbol2 = this.setterSymbol;
            return iHashCode2 + (irSimpleFunctionSymbol2 != null ? irSimpleFunctionSymbol2.hashCode() : 0);
        }

        public String toString() {
            return "ActualPropertyResult(propertySymbol=" + this.propertySymbol + ", getterSymbol=" + this.getterSymbol + ", setterSymbol=" + this.setterSymbol + Util.C_PARAM_END;
        }
    }

    public abstract IrClassSymbol getActualClass(ClassDescriptor descriptor);

    public abstract IrConstructorSymbol getActualConstructor(ClassConstructorDescriptor descriptor);

    public abstract IrSimpleFunctionSymbol getActualFunction(FunctionDescriptor descriptor);

    public abstract ActualPropertyResult getActualProperty(PropertyDescriptor descriptor);

    public boolean isTargetDeclaration(IrDeclaration declaration) {
        declaration.getClass();
        return org.jetbrains.kotlin.ir.util.IrUtilsKt.isExpect(declaration);
    }

    public void visitCall(IrCall expression) {
        IrSimpleFunctionSymbol actualFunction;
        expression.getClass();
        super.visitCall(expression);
        if (isTargetDeclaration(expression.getSymbol().getOwner()) && (actualFunction = getActualFunction(expression.getSymbol().getDescriptor())) != null) {
            expression.setSymbol(actualFunction);
        }
    }

    public void visitClassReference(IrClassReference expression) {
        IrClassSymbol actualClass;
        expression.getClass();
        super.visitClassReference(expression);
        IrClassSymbol symbol = expression.getSymbol();
        IrClassSymbol irClassSymbol = symbol instanceof IrClassSymbol ? symbol : null;
        if (irClassSymbol == null || !isTargetDeclaration((IrDeclaration) irClassSymbol.getOwner()) || (actualClass = getActualClass((ClassDescriptor) irClassSymbol.getDescriptor())) == null) {
            return;
        }
        expression.setSymbol(actualClass);
    }

    public void visitConstructorCall(IrConstructorCall expression) {
        IrConstructorSymbol actualConstructor;
        expression.getClass();
        super.visitConstructorCall(expression);
        if (isTargetDeclaration(expression.getSymbol().getOwner()) && (actualConstructor = getActualConstructor((ClassConstructorDescriptor) expression.getSymbol().getDescriptor())) != null) {
            expression.setSymbol(actualConstructor);
        }
    }

    public void visitDelegatingConstructorCall(IrDelegatingConstructorCall expression) {
        IrConstructorSymbol actualConstructor;
        expression.getClass();
        super.visitDelegatingConstructorCall(expression);
        if (isTargetDeclaration(expression.getSymbol().getOwner()) && (actualConstructor = getActualConstructor((ClassConstructorDescriptor) expression.getSymbol().getDescriptor())) != null) {
            expression.setSymbol(actualConstructor);
        }
    }

    public void visitElement(IrElement element) {
        element.getClass();
        element.acceptChildren(this, (Object) null);
    }

    public void visitEnumConstructorCall(IrEnumConstructorCall expression) {
        IrConstructorSymbol actualConstructor;
        expression.getClass();
        super.visitEnumConstructorCall(expression);
        if (isTargetDeclaration(expression.getSymbol().getOwner()) && (actualConstructor = getActualConstructor((ClassConstructorDescriptor) expression.getSymbol().getDescriptor())) != null) {
            expression.setSymbol(actualConstructor);
        }
    }

    public void visitFunctionReference(IrFunctionReference expression) {
        IrSimpleFunctionSymbol actualFunction;
        expression.getClass();
        super.visitFunctionReference(expression);
        if (isTargetDeclaration(expression.getSymbol().getOwner()) && (actualFunction = getActualFunction(expression.getSymbol().getDescriptor())) != null) {
            expression.setSymbol(actualFunction);
        }
    }

    public void visitPropertyReference(IrPropertyReference expression) {
        ActualPropertyResult actualProperty;
        expression.getClass();
        super.visitPropertyReference(expression);
        if (isTargetDeclaration((IrDeclaration) expression.getSymbol().getOwner()) && (actualProperty = getActualProperty((PropertyDescriptor) expression.getSymbol().getDescriptor())) != null) {
            IrPropertySymbol propertySymbol = actualProperty.getPropertySymbol();
            IrSimpleFunctionSymbol getterSymbol = actualProperty.getGetterSymbol();
            IrSimpleFunctionSymbol setterSymbol = actualProperty.getSetterSymbol();
            expression.setSymbol(propertySymbol);
            expression.setGetter(getterSymbol);
            expression.setSetter(setterSymbol);
        }
    }
}

package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J=\u0010\u0018\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/SpecialMethodWithDefaultInfo;", "", "defaultValueGenerator", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "argumentsToCheck", "", "needsGenericSignature", "", "needsUnsubstitutedBridge", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lkotlin/jvm/functions/Function1;IZZ)V", "getDefaultValueGenerator", "()Lkotlin/jvm/functions/Function1;", "getArgumentsToCheck", "()I", "getNeedsGenericSignature", "()Z", "getNeedsUnsubstitutedBridge", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class SpecialMethodWithDefaultInfo {
    private final int argumentsToCheck;
    private final Function1<IrSimpleFunction, IrExpression> defaultValueGenerator;
    private final boolean needsGenericSignature;
    private final boolean needsUnsubstitutedBridge;

    public SpecialMethodWithDefaultInfo(Function1<? super IrSimpleFunction, ? extends IrExpression> function1, int i, boolean z, boolean z2) {
        function1.getClass();
        this.defaultValueGenerator = function1;
        this.argumentsToCheck = i;
        this.needsGenericSignature = z;
        this.needsUnsubstitutedBridge = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SpecialMethodWithDefaultInfo copy$default(SpecialMethodWithDefaultInfo specialMethodWithDefaultInfo, Function1 function1, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = specialMethodWithDefaultInfo.defaultValueGenerator;
        }
        if ((i2 & 2) != 0) {
            i = specialMethodWithDefaultInfo.argumentsToCheck;
        }
        if ((i2 & 4) != 0) {
            z = specialMethodWithDefaultInfo.needsGenericSignature;
        }
        if ((i2 & 8) != 0) {
            z2 = specialMethodWithDefaultInfo.needsUnsubstitutedBridge;
        }
        return specialMethodWithDefaultInfo.copy(function1, i, z, z2);
    }

    public final Function1<IrSimpleFunction, IrExpression> component1() {
        return this.defaultValueGenerator;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getArgumentsToCheck() {
        return this.argumentsToCheck;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getNeedsGenericSignature() {
        return this.needsGenericSignature;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getNeedsUnsubstitutedBridge() {
        return this.needsUnsubstitutedBridge;
    }

    public final SpecialMethodWithDefaultInfo copy(Function1<? super IrSimpleFunction, ? extends IrExpression> defaultValueGenerator, int argumentsToCheck, boolean needsGenericSignature, boolean needsUnsubstitutedBridge) {
        defaultValueGenerator.getClass();
        return new SpecialMethodWithDefaultInfo(defaultValueGenerator, argumentsToCheck, needsGenericSignature, needsUnsubstitutedBridge);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SpecialMethodWithDefaultInfo)) {
            return false;
        }
        SpecialMethodWithDefaultInfo specialMethodWithDefaultInfo = (SpecialMethodWithDefaultInfo) other;
        return Intrinsics.areEqual(this.defaultValueGenerator, specialMethodWithDefaultInfo.defaultValueGenerator) && this.argumentsToCheck == specialMethodWithDefaultInfo.argumentsToCheck && this.needsGenericSignature == specialMethodWithDefaultInfo.needsGenericSignature && this.needsUnsubstitutedBridge == specialMethodWithDefaultInfo.needsUnsubstitutedBridge;
    }

    public final int getArgumentsToCheck() {
        return this.argumentsToCheck;
    }

    public final Function1<IrSimpleFunction, IrExpression> getDefaultValueGenerator() {
        return this.defaultValueGenerator;
    }

    public final boolean getNeedsGenericSignature() {
        return this.needsGenericSignature;
    }

    public final boolean getNeedsUnsubstitutedBridge() {
        return this.needsUnsubstitutedBridge;
    }

    public int hashCode() {
        return (((((this.defaultValueGenerator.hashCode() * 31) + Integer.hashCode(this.argumentsToCheck)) * 31) + Boolean.hashCode(this.needsGenericSignature)) * 31) + Boolean.hashCode(this.needsUnsubstitutedBridge);
    }

    public String toString() {
        return "SpecialMethodWithDefaultInfo(defaultValueGenerator=" + this.defaultValueGenerator + ", argumentsToCheck=" + this.argumentsToCheck + ", needsGenericSignature=" + this.needsGenericSignature + ", needsUnsubstitutedBridge=" + this.needsUnsubstitutedBridge + Util.C_PARAM_END;
    }

    public /* synthetic */ SpecialMethodWithDefaultInfo(Function1 function1, int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, i, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2);
    }
}

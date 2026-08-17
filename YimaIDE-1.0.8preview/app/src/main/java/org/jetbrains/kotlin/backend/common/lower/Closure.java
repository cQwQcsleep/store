package org.jetbrains.kotlin.backend.common.lower;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/Closure;", "", "capturedValues", "", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", "capturedTypeParameters", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;Ljava/util/List;)V", "getCapturedValues", "()Ljava/util/List;", "getCapturedTypeParameters", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class Closure {
    private final List<IrTypeParameter> capturedTypeParameters;
    private final List<IrValueSymbol> capturedValues;

    public Closure(List<? extends IrValueSymbol> list, List<? extends IrTypeParameter> list2) {
        list.getClass();
        list2.getClass();
        this.capturedValues = list;
        this.capturedTypeParameters = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Closure copy$default(Closure closure, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = closure.capturedValues;
        }
        if ((i & 2) != 0) {
            list2 = closure.capturedTypeParameters;
        }
        return closure.copy(list, list2);
    }

    public final List<IrValueSymbol> component1() {
        return this.capturedValues;
    }

    public final List<IrTypeParameter> component2() {
        return this.capturedTypeParameters;
    }

    public final Closure copy(List<? extends IrValueSymbol> capturedValues, List<? extends IrTypeParameter> capturedTypeParameters) {
        capturedValues.getClass();
        capturedTypeParameters.getClass();
        return new Closure(capturedValues, capturedTypeParameters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Closure)) {
            return false;
        }
        Closure closure = (Closure) other;
        return Intrinsics.areEqual(this.capturedValues, closure.capturedValues) && Intrinsics.areEqual(this.capturedTypeParameters, closure.capturedTypeParameters);
    }

    public final List<IrTypeParameter> getCapturedTypeParameters() {
        return this.capturedTypeParameters;
    }

    public final List<IrValueSymbol> getCapturedValues() {
        return this.capturedValues;
    }

    public int hashCode() {
        return (this.capturedValues.hashCode() * 31) + this.capturedTypeParameters.hashCode();
    }

    public String toString() {
        return "Closure(capturedValues=" + this.capturedValues + ", capturedTypeParameters=" + this.capturedTypeParameters + Util.C_PARAM_END;
    }
}

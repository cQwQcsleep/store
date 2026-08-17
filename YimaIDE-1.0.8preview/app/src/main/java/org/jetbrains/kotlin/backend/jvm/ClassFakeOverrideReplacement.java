package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "DefaultImplsRedirection", "DefaultCompatibilityBridge", "None", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement$DefaultCompatibilityBridge;", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement$DefaultImplsRedirection;", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement$None;", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ClassFakeOverrideReplacement {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement$DefaultCompatibilityBridge;", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", "newFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "superFunction", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "getNewFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getSuperFunction", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class DefaultCompatibilityBridge extends ClassFakeOverrideReplacement {
        private final IrSimpleFunction newFunction;
        private final IrSimpleFunction superFunction;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultCompatibilityBridge(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
            super(null);
            irSimpleFunction.getClass();
            irSimpleFunction2.getClass();
            this.newFunction = irSimpleFunction;
            this.superFunction = irSimpleFunction2;
        }

        public static /* synthetic */ DefaultCompatibilityBridge copy$default(DefaultCompatibilityBridge defaultCompatibilityBridge, IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, int i, Object obj) {
            if ((i & 1) != 0) {
                irSimpleFunction = defaultCompatibilityBridge.newFunction;
            }
            if ((i & 2) != 0) {
                irSimpleFunction2 = defaultCompatibilityBridge.superFunction;
            }
            return defaultCompatibilityBridge.copy(irSimpleFunction, irSimpleFunction2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrSimpleFunction getNewFunction() {
            return this.newFunction;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final IrSimpleFunction getSuperFunction() {
            return this.superFunction;
        }

        public final DefaultCompatibilityBridge copy(IrSimpleFunction newFunction, IrSimpleFunction superFunction) {
            newFunction.getClass();
            superFunction.getClass();
            return new DefaultCompatibilityBridge(newFunction, superFunction);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DefaultCompatibilityBridge)) {
                return false;
            }
            DefaultCompatibilityBridge defaultCompatibilityBridge = (DefaultCompatibilityBridge) other;
            return Intrinsics.areEqual(this.newFunction, defaultCompatibilityBridge.newFunction) && Intrinsics.areEqual(this.superFunction, defaultCompatibilityBridge.superFunction);
        }

        public final IrSimpleFunction getNewFunction() {
            return this.newFunction;
        }

        public final IrSimpleFunction getSuperFunction() {
            return this.superFunction;
        }

        public int hashCode() {
            return (this.newFunction.hashCode() * 31) + this.superFunction.hashCode();
        }

        public String toString() {
            return "DefaultCompatibilityBridge(newFunction=" + this.newFunction + ", superFunction=" + this.superFunction + Util.C_PARAM_END;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement$DefaultImplsRedirection;", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", "newFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "superFunction", "callee", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "getNewFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getSuperFunction", "getCallee", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class DefaultImplsRedirection extends ClassFakeOverrideReplacement {
        private final IrSimpleFunction callee;
        private final IrSimpleFunction newFunction;
        private final IrSimpleFunction superFunction;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultImplsRedirection(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, IrSimpleFunction irSimpleFunction3) {
            super(null);
            irSimpleFunction.getClass();
            irSimpleFunction2.getClass();
            irSimpleFunction3.getClass();
            this.newFunction = irSimpleFunction;
            this.superFunction = irSimpleFunction2;
            this.callee = irSimpleFunction3;
        }

        public static /* synthetic */ DefaultImplsRedirection copy$default(DefaultImplsRedirection defaultImplsRedirection, IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, IrSimpleFunction irSimpleFunction3, int i, Object obj) {
            if ((i & 1) != 0) {
                irSimpleFunction = defaultImplsRedirection.newFunction;
            }
            if ((i & 2) != 0) {
                irSimpleFunction2 = defaultImplsRedirection.superFunction;
            }
            if ((i & 4) != 0) {
                irSimpleFunction3 = defaultImplsRedirection.callee;
            }
            return defaultImplsRedirection.copy(irSimpleFunction, irSimpleFunction2, irSimpleFunction3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrSimpleFunction getNewFunction() {
            return this.newFunction;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final IrSimpleFunction getSuperFunction() {
            return this.superFunction;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final IrSimpleFunction getCallee() {
            return this.callee;
        }

        public final DefaultImplsRedirection copy(IrSimpleFunction newFunction, IrSimpleFunction superFunction, IrSimpleFunction callee) {
            newFunction.getClass();
            superFunction.getClass();
            callee.getClass();
            return new DefaultImplsRedirection(newFunction, superFunction, callee);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DefaultImplsRedirection)) {
                return false;
            }
            DefaultImplsRedirection defaultImplsRedirection = (DefaultImplsRedirection) other;
            return Intrinsics.areEqual(this.newFunction, defaultImplsRedirection.newFunction) && Intrinsics.areEqual(this.superFunction, defaultImplsRedirection.superFunction) && Intrinsics.areEqual(this.callee, defaultImplsRedirection.callee);
        }

        public final IrSimpleFunction getCallee() {
            return this.callee;
        }

        public final IrSimpleFunction getNewFunction() {
            return this.newFunction;
        }

        public final IrSimpleFunction getSuperFunction() {
            return this.superFunction;
        }

        public int hashCode() {
            return (((this.newFunction.hashCode() * 31) + this.superFunction.hashCode()) * 31) + this.callee.hashCode();
        }

        public String toString() {
            return "DefaultImplsRedirection(newFunction=" + this.newFunction + ", superFunction=" + this.superFunction + ", callee=" + this.callee + Util.C_PARAM_END;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement$None;", "Lorg/jetbrains/kotlin/backend/jvm/ClassFakeOverrideReplacement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class None extends ClassFakeOverrideReplacement {
        public static final None INSTANCE = new None();

        private None() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof None);
        }

        public int hashCode() {
            return 1075490459;
        }

        public String toString() {
            return "None";
        }
    }

    public /* synthetic */ ClassFakeOverrideReplacement(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ClassFakeOverrideReplacement() {
    }
}

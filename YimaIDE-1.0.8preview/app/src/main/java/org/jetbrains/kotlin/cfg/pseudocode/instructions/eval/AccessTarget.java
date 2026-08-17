package org.jetbrains.kotlin.cfg.pseudocode.instructions.eval;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Declaration", "Call", "BlackBox", "Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget$BlackBox;", "Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget$Call;", "Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget$Declaration;", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class AccessTarget {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget$BlackBox;", "Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class BlackBox extends AccessTarget {
        public static final BlackBox INSTANCE = new BlackBox();

        private BlackBox() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget$Call;", "Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget;", "resolvedCall", "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;)V", "getResolvedCall", "()Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Call extends AccessTarget {
        private final ResolvedCall<?> resolvedCall;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Call(ResolvedCall<?> resolvedCall) {
            super(null);
            resolvedCall.getClass();
            this.resolvedCall = resolvedCall;
        }

        public boolean equals(Object other) {
            return (other instanceof Call) && Intrinsics.areEqual(this.resolvedCall, ((Call) other).resolvedCall);
        }

        public final ResolvedCall<?> getResolvedCall() {
            return this.resolvedCall;
        }

        public int hashCode() {
            return this.resolvedCall.hashCode();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget$Declaration;", "Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/AccessTarget;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Declaration extends AccessTarget {
        private final VariableDescriptor descriptor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Declaration(VariableDescriptor variableDescriptor) {
            super(null);
            variableDescriptor.getClass();
            this.descriptor = variableDescriptor;
        }

        public boolean equals(Object other) {
            return (other instanceof Declaration) && Intrinsics.areEqual(this.descriptor, ((Declaration) other).descriptor);
        }

        public final VariableDescriptor getDescriptor() {
            return this.descriptor;
        }

        public int hashCode() {
            return this.descriptor.hashCode();
        }
    }

    public /* synthetic */ AccessTarget(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AccessTarget() {
    }
}

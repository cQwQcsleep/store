package org.jetbrains.kotlin.backend.jvm.lower.indy;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/LambdaMetafactoryArguments;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Success;", "samMethod", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "fakeInstanceMethod", "extraOverriddenMethods", "", "shouldBeSerializable", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Ljava/util/List;Z)V", "getSamMethod", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getFakeInstanceMethod", "getExtraOverriddenMethods", "()Ljava/util/List;", "getShouldBeSerializable", "()Z", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LambdaMetafactoryArguments extends MetafactoryArgumentsResult.Success {
    private final List<IrSimpleFunction> extraOverriddenMethods;
    private final IrSimpleFunction fakeInstanceMethod;
    private final IrSimpleFunction samMethod;
    private final boolean shouldBeSerializable;

    public LambdaMetafactoryArguments(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, List<? extends IrSimpleFunction> list, boolean z) {
        irSimpleFunction.getClass();
        irSimpleFunction2.getClass();
        list.getClass();
        this.samMethod = irSimpleFunction;
        this.fakeInstanceMethod = irSimpleFunction2;
        this.extraOverriddenMethods = list;
        this.shouldBeSerializable = z;
    }

    public final List<IrSimpleFunction> getExtraOverriddenMethods() {
        return this.extraOverriddenMethods;
    }

    public final IrSimpleFunction getFakeInstanceMethod() {
        return this.fakeInstanceMethod;
    }

    public final IrSimpleFunction getSamMethod() {
        return this.samMethod;
    }

    public final boolean getShouldBeSerializable() {
        return this.shouldBeSerializable;
    }
}

package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\u0019\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001a\u0010\u0007¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/JvmLoweredStatementOrigin;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "DEFAULT_STUB_CALL_TO_IMPLEMENTATION", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "getDEFAULT_STUB_CALL_TO_IMPLEMENTATION", "()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "DEFAULT_STUB_CALL_TO_IMPLEMENTATION$delegate", "Lkotlin/properties/ReadOnlyProperty;", "DO_WHILE_COUNTER_LOOP", "getDO_WHILE_COUNTER_LOOP", "DO_WHILE_COUNTER_LOOP$delegate", "FAKE_CONTINUATION", "getFAKE_CONTINUATION", "FAKE_CONTINUATION$delegate", "FOLDED_SAFE_CALL", "getFOLDED_SAFE_CALL", "FOLDED_SAFE_CALL$delegate", "FOLDED_ELVIS", "getFOLDED_ELVIS", "FOLDED_ELVIS$delegate", "WHEN_AS_TYPE_SWITCH", "getWHEN_AS_TYPE_SWITCH", "WHEN_AS_TYPE_SWITCH$delegate", "INLINE_SUSPEND_PARAM_DEFAULT_VALUE", "getINLINE_SUSPEND_PARAM_DEFAULT_VALUE", "INLINE_SUSPEND_PARAM_DEFAULT_VALUE$delegate", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmLoweredStatementOrigin {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: DEFAULT_STUB_CALL_TO_IMPLEMENTATION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty DEFAULT_STUB_CALL_TO_IMPLEMENTATION;

    /* JADX INFO: renamed from: DO_WHILE_COUNTER_LOOP$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty DO_WHILE_COUNTER_LOOP;

    /* JADX INFO: renamed from: FAKE_CONTINUATION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty FAKE_CONTINUATION;

    /* JADX INFO: renamed from: FOLDED_ELVIS$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty FOLDED_ELVIS;

    /* JADX INFO: renamed from: FOLDED_SAFE_CALL$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty FOLDED_SAFE_CALL;

    /* JADX INFO: renamed from: INLINE_SUSPEND_PARAM_DEFAULT_VALUE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty INLINE_SUSPEND_PARAM_DEFAULT_VALUE;
    public static final JvmLoweredStatementOrigin INSTANCE;

    /* JADX INFO: renamed from: WHEN_AS_TYPE_SWITCH$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty WHEN_AS_TYPE_SWITCH;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "DEFAULT_STUB_CALL_TO_IMPLEMENTATION", "getDEFAULT_STUB_CALL_TO_IMPLEMENTATION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "DO_WHILE_COUNTER_LOOP", "getDO_WHILE_COUNTER_LOOP()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "FAKE_CONTINUATION", "getFAKE_CONTINUATION()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "FOLDED_SAFE_CALL", "getFOLDED_SAFE_CALL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "FOLDED_ELVIS", "getFOLDED_ELVIS()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "WHEN_AS_TYPE_SWITCH", "getWHEN_AS_TYPE_SWITCH()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JvmLoweredStatementOrigin.class, "INLINE_SUSPEND_PARAM_DEFAULT_VALUE", "getINLINE_SUSPEND_PARAM_DEFAULT_VALUE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0)};
        $$delegatedProperties = kPropertyArr;
        JvmLoweredStatementOrigin jvmLoweredStatementOrigin = new JvmLoweredStatementOrigin();
        INSTANCE = jvmLoweredStatementOrigin;
        IrStatementOriginImpl.Companion companion = IrStatementOriginImpl.Companion;
        DEFAULT_STUB_CALL_TO_IMPLEMENTATION = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[0]);
        DO_WHILE_COUNTER_LOOP = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[1]);
        FAKE_CONTINUATION = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[2]);
        FOLDED_SAFE_CALL = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[3]);
        FOLDED_ELVIS = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[4]);
        WHEN_AS_TYPE_SWITCH = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[5]);
        INLINE_SUSPEND_PARAM_DEFAULT_VALUE = companion.provideDelegate(jvmLoweredStatementOrigin, kPropertyArr[6]);
    }

    private JvmLoweredStatementOrigin() {
    }

    public final IrStatementOriginImpl getDEFAULT_STUB_CALL_TO_IMPLEMENTATION() {
        return (IrStatementOriginImpl) DEFAULT_STUB_CALL_TO_IMPLEMENTATION.getValue(this, $$delegatedProperties[0]);
    }

    public final IrStatementOriginImpl getDO_WHILE_COUNTER_LOOP() {
        return (IrStatementOriginImpl) DO_WHILE_COUNTER_LOOP.getValue(this, $$delegatedProperties[1]);
    }

    public final IrStatementOriginImpl getFAKE_CONTINUATION() {
        return (IrStatementOriginImpl) FAKE_CONTINUATION.getValue(this, $$delegatedProperties[2]);
    }

    public final IrStatementOriginImpl getFOLDED_ELVIS() {
        return (IrStatementOriginImpl) FOLDED_ELVIS.getValue(this, $$delegatedProperties[4]);
    }

    public final IrStatementOriginImpl getFOLDED_SAFE_CALL() {
        return (IrStatementOriginImpl) FOLDED_SAFE_CALL.getValue(this, $$delegatedProperties[3]);
    }

    public final IrStatementOriginImpl getINLINE_SUSPEND_PARAM_DEFAULT_VALUE() {
        return (IrStatementOriginImpl) INLINE_SUSPEND_PARAM_DEFAULT_VALUE.getValue(this, $$delegatedProperties[6]);
    }

    public final IrStatementOriginImpl getWHEN_AS_TYPE_SWITCH() {
        return (IrStatementOriginImpl) WHEN_AS_TYPE_SWITCH.getValue(this, $$delegatedProperties[5]);
    }
}

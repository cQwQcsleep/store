package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\r\u0010\u0007R\u001b\u0010\u000f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\t\u001a\u0004\b\u0010\u0010\u0007R\u001b\u0010\u0012\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0013\u0010\u0007R\u001b\u0010\u0015\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\t\u001a\u0004\b\u0016\u0010\u0007R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001e\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010\t\u001a\u0004\b\u001f\u0010\u0007R\u001b\u0010!\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\t\u001a\u0004\b\"\u0010\u0007R\u001b\u0010$\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\t\u001a\u0004\b%\u0010\u0007R\u001b\u0010'\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\t\u001a\u0004\b(\u0010\u0007¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/JsStatementOrigins;", "", "<init>", "()V", "BIND_CALL", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "getBIND_CALL", "()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "BIND_CALL$delegate", "Lkotlin/properties/ReadOnlyProperty;", "STATEMENT_ORIGIN_COROUTINE_IMPL", "getSTATEMENT_ORIGIN_COROUTINE_IMPL", "SYNTHESIZED_STATEMENT", "getSYNTHESIZED_STATEMENT", "SYNTHESIZED_STATEMENT$delegate", "CALLABLE_REFERENCE_CREATE", "getCALLABLE_REFERENCE_CREATE", "CALLABLE_REFERENCE_CREATE$delegate", "CALLABLE_REFERENCE_INVOKE", "getCALLABLE_REFERENCE_INVOKE", "CALLABLE_REFERENCE_INVOKE$delegate", "EXPLICIT_INVOKE", "getEXPLICIT_INVOKE", "EXPLICIT_INVOKE$delegate", "FACTORY_ORIGIN", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getFACTORY_ORIGIN", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "FACTORY_ORIGIN$delegate", "Lkotlin/Lazy;", "COROUTINE_ROOT_LOOP", "getCOROUTINE_ROOT_LOOP", "COROUTINE_ROOT_LOOP$delegate", "COROUTINE_SWITCH", "getCOROUTINE_SWITCH", "COROUTINE_SWITCH$delegate", "CLASS_REFERENCE", "getCLASS_REFERENCE", "CLASS_REFERENCE$delegate", "IMPLEMENTATION_DELEGATION_CALL", "getIMPLEMENTATION_DELEGATION_CALL", "IMPLEMENTATION_DELEGATION_CALL$delegate", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsStatementOrigins {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: BIND_CALL$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty BIND_CALL;

    /* JADX INFO: renamed from: CALLABLE_REFERENCE_CREATE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CALLABLE_REFERENCE_CREATE;

    /* JADX INFO: renamed from: CALLABLE_REFERENCE_INVOKE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CALLABLE_REFERENCE_INVOKE;

    /* JADX INFO: renamed from: CLASS_REFERENCE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CLASS_REFERENCE;

    /* JADX INFO: renamed from: COROUTINE_ROOT_LOOP$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COROUTINE_ROOT_LOOP;

    /* JADX INFO: renamed from: COROUTINE_SWITCH$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COROUTINE_SWITCH;

    /* JADX INFO: renamed from: EXPLICIT_INVOKE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty EXPLICIT_INVOKE;

    /* JADX INFO: renamed from: FACTORY_ORIGIN$delegate, reason: from kotlin metadata */
    private static final Lazy FACTORY_ORIGIN;

    /* JADX INFO: renamed from: IMPLEMENTATION_DELEGATION_CALL$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty IMPLEMENTATION_DELEGATION_CALL;
    public static final JsStatementOrigins INSTANCE;
    private static final IrStatementOriginImpl STATEMENT_ORIGIN_COROUTINE_IMPL;

    /* JADX INFO: renamed from: SYNTHESIZED_STATEMENT$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty SYNTHESIZED_STATEMENT;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(JsStatementOrigins.class, "BIND_CALL", "getBIND_CALL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "SYNTHESIZED_STATEMENT", "getSYNTHESIZED_STATEMENT()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "CALLABLE_REFERENCE_CREATE", "getCALLABLE_REFERENCE_CREATE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "CALLABLE_REFERENCE_INVOKE", "getCALLABLE_REFERENCE_INVOKE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "EXPLICIT_INVOKE", "getEXPLICIT_INVOKE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "FACTORY_ORIGIN", "getFACTORY_ORIGIN()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "COROUTINE_ROOT_LOOP", "getCOROUTINE_ROOT_LOOP()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "COROUTINE_SWITCH", "getCOROUTINE_SWITCH()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "CLASS_REFERENCE", "getCLASS_REFERENCE()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0), new PropertyReference1Impl<>(JsStatementOrigins.class, "IMPLEMENTATION_DELEGATION_CALL", "getIMPLEMENTATION_DELEGATION_CALL()Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", 0)};
        $$delegatedProperties = kPropertyArr;
        JsStatementOrigins jsStatementOrigins = new JsStatementOrigins();
        INSTANCE = jsStatementOrigins;
        IrStatementOriginImpl.Companion companion = IrStatementOriginImpl.INSTANCE;
        BIND_CALL = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[0]);
        STATEMENT_ORIGIN_COROUTINE_IMPL = new IrStatementOriginImpl("COROUTINE_IMPL");
        SYNTHESIZED_STATEMENT = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[1]);
        CALLABLE_REFERENCE_CREATE = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[2]);
        CALLABLE_REFERENCE_INVOKE = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[3]);
        EXPLICIT_INVOKE = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[4]);
        FACTORY_ORIGIN = IrDeclarationOriginImpl.Regular.INSTANCE.provideDelegate(jsStatementOrigins, kPropertyArr[5]);
        COROUTINE_ROOT_LOOP = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[6]);
        COROUTINE_SWITCH = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[7]);
        CLASS_REFERENCE = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[8]);
        IMPLEMENTATION_DELEGATION_CALL = companion.m389provideDelegate((Object) jsStatementOrigins, kPropertyArr[9]);
    }

    private JsStatementOrigins() {
    }

    public final IrStatementOriginImpl getBIND_CALL() {
        return (IrStatementOriginImpl) BIND_CALL.getValue(this, $$delegatedProperties[0]);
    }

    public final IrStatementOriginImpl getCALLABLE_REFERENCE_CREATE() {
        return (IrStatementOriginImpl) CALLABLE_REFERENCE_CREATE.getValue(this, $$delegatedProperties[2]);
    }

    public final IrStatementOriginImpl getCALLABLE_REFERENCE_INVOKE() {
        return (IrStatementOriginImpl) CALLABLE_REFERENCE_INVOKE.getValue(this, $$delegatedProperties[3]);
    }

    public final IrStatementOriginImpl getCLASS_REFERENCE() {
        return (IrStatementOriginImpl) CLASS_REFERENCE.getValue(this, $$delegatedProperties[8]);
    }

    public final IrStatementOriginImpl getCOROUTINE_ROOT_LOOP() {
        return (IrStatementOriginImpl) COROUTINE_ROOT_LOOP.getValue(this, $$delegatedProperties[6]);
    }

    public final IrStatementOriginImpl getCOROUTINE_SWITCH() {
        return (IrStatementOriginImpl) COROUTINE_SWITCH.getValue(this, $$delegatedProperties[7]);
    }

    public final IrStatementOriginImpl getEXPLICIT_INVOKE() {
        return (IrStatementOriginImpl) EXPLICIT_INVOKE.getValue(this, $$delegatedProperties[4]);
    }

    public final IrDeclarationOrigin getFACTORY_ORIGIN() {
        return (IrDeclarationOrigin) FACTORY_ORIGIN.getValue();
    }

    public final IrStatementOriginImpl getIMPLEMENTATION_DELEGATION_CALL() {
        return (IrStatementOriginImpl) IMPLEMENTATION_DELEGATION_CALL.getValue(this, $$delegatedProperties[9]);
    }

    public final IrStatementOriginImpl getSTATEMENT_ORIGIN_COROUTINE_IMPL() {
        return STATEMENT_ORIGIN_COROUTINE_IMPL;
    }

    public final IrStatementOriginImpl getSYNTHESIZED_STATEMENT() {
        return (IrStatementOriginImpl) SYNTHESIZED_STATEMENT.getValue(this, $$delegatedProperties[1]);
    }
}

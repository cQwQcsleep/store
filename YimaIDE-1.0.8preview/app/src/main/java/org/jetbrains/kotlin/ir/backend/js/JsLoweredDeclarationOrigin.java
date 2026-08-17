package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0005R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\u0019\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001a\u0010\u0007R\u001b\u0010\u001c\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001d\u0010\u0007R\u001b\u0010\u001f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\t\u001a\u0004\b \u0010\u0007R\u001b\u0010\"\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\t\u001a\u0004\b#\u0010\u0007¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/JsLoweredDeclarationOrigin;", "", "<init>", "()V", "JS_INTRINSICS_STUB", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getJS_INTRINSICS_STUB", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "JS_INTRINSICS_STUB$delegate", "Lkotlin/Lazy;", "JS_CLOSURE_BOX_CLASS_DECLARATION", "getJS_CLOSURE_BOX_CLASS_DECLARATION", "JS_CLOSURE_BOX_CLASS_DECLARATION$delegate", "BRIDGE_WITH_STABLE_NAME", "getBRIDGE_WITH_STABLE_NAME", "BRIDGE_WITH_STABLE_NAME$delegate", "BRIDGE_WITHOUT_STABLE_NAME", "getBRIDGE_WITHOUT_STABLE_NAME", "BRIDGE_WITHOUT_STABLE_NAME$delegate", "BRIDGE_PROPERTY_ACCESSOR", "getBRIDGE_PROPERTY_ACCESSOR", "BRIDGE_PROPERTY_ACCESSOR$delegate", "OBJECT_GET_INSTANCE_FUNCTION", "getOBJECT_GET_INSTANCE_FUNCTION", "OBJECT_GET_INSTANCE_FUNCTION$delegate", "JS_SHADOWED_EXPORT", "getJS_SHADOWED_EXPORT", "JS_SHADOWED_EXPORT$delegate", "JS_SUPER_CONTEXT_PARAMETER", "getJS_SUPER_CONTEXT_PARAMETER", "JS_SUPER_CONTEXT_PARAMETER$delegate", "JS_SHADOWED_DEFAULT_PARAMETER", "getJS_SHADOWED_DEFAULT_PARAMETER", "JS_SHADOWED_DEFAULT_PARAMETER$delegate", "ENUM_GET_INSTANCE_FUNCTION", "getENUM_GET_INSTANCE_FUNCTION", "ENUM_GET_INSTANCE_FUNCTION$delegate", "isBridgeDeclarationOrigin", "", "origin", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsLoweredDeclarationOrigin {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: BRIDGE_PROPERTY_ACCESSOR$delegate, reason: from kotlin metadata */
    private static final Lazy BRIDGE_PROPERTY_ACCESSOR;

    /* JADX INFO: renamed from: BRIDGE_WITHOUT_STABLE_NAME$delegate, reason: from kotlin metadata */
    private static final Lazy BRIDGE_WITHOUT_STABLE_NAME;

    /* JADX INFO: renamed from: BRIDGE_WITH_STABLE_NAME$delegate, reason: from kotlin metadata */
    private static final Lazy BRIDGE_WITH_STABLE_NAME;

    /* JADX INFO: renamed from: ENUM_GET_INSTANCE_FUNCTION$delegate, reason: from kotlin metadata */
    private static final Lazy ENUM_GET_INSTANCE_FUNCTION;
    public static final JsLoweredDeclarationOrigin INSTANCE;

    /* JADX INFO: renamed from: JS_CLOSURE_BOX_CLASS_DECLARATION$delegate, reason: from kotlin metadata */
    private static final Lazy JS_CLOSURE_BOX_CLASS_DECLARATION;

    /* JADX INFO: renamed from: JS_INTRINSICS_STUB$delegate, reason: from kotlin metadata */
    private static final Lazy JS_INTRINSICS_STUB;

    /* JADX INFO: renamed from: JS_SHADOWED_DEFAULT_PARAMETER$delegate, reason: from kotlin metadata */
    private static final Lazy JS_SHADOWED_DEFAULT_PARAMETER;

    /* JADX INFO: renamed from: JS_SHADOWED_EXPORT$delegate, reason: from kotlin metadata */
    private static final Lazy JS_SHADOWED_EXPORT;

    /* JADX INFO: renamed from: JS_SUPER_CONTEXT_PARAMETER$delegate, reason: from kotlin metadata */
    private static final Lazy JS_SUPER_CONTEXT_PARAMETER;

    /* JADX INFO: renamed from: OBJECT_GET_INSTANCE_FUNCTION$delegate, reason: from kotlin metadata */
    private static final Lazy OBJECT_GET_INSTANCE_FUNCTION;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "JS_INTRINSICS_STUB", "getJS_INTRINSICS_STUB()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "JS_CLOSURE_BOX_CLASS_DECLARATION", "getJS_CLOSURE_BOX_CLASS_DECLARATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "BRIDGE_WITH_STABLE_NAME", "getBRIDGE_WITH_STABLE_NAME()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "BRIDGE_WITHOUT_STABLE_NAME", "getBRIDGE_WITHOUT_STABLE_NAME()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "BRIDGE_PROPERTY_ACCESSOR", "getBRIDGE_PROPERTY_ACCESSOR()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "OBJECT_GET_INSTANCE_FUNCTION", "getOBJECT_GET_INSTANCE_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "JS_SHADOWED_EXPORT", "getJS_SHADOWED_EXPORT()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "JS_SUPER_CONTEXT_PARAMETER", "getJS_SUPER_CONTEXT_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "JS_SHADOWED_DEFAULT_PARAMETER", "getJS_SHADOWED_DEFAULT_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(JsLoweredDeclarationOrigin.class, "ENUM_GET_INSTANCE_FUNCTION", "getENUM_GET_INSTANCE_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0)};
        $$delegatedProperties = kPropertyArr;
        JsLoweredDeclarationOrigin jsLoweredDeclarationOrigin = new JsLoweredDeclarationOrigin();
        INSTANCE = jsLoweredDeclarationOrigin;
        IrDeclarationOriginImpl.Regular regular = IrDeclarationOriginImpl.Regular.INSTANCE;
        JS_INTRINSICS_STUB = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[0]);
        JS_CLOSURE_BOX_CLASS_DECLARATION = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[1]);
        BRIDGE_WITH_STABLE_NAME = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[2]);
        BRIDGE_WITHOUT_STABLE_NAME = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[3]);
        BRIDGE_PROPERTY_ACCESSOR = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[4]);
        OBJECT_GET_INSTANCE_FUNCTION = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[5]);
        JS_SHADOWED_EXPORT = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[6]);
        JS_SUPER_CONTEXT_PARAMETER = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[7]);
        JS_SHADOWED_DEFAULT_PARAMETER = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[8]);
        ENUM_GET_INSTANCE_FUNCTION = regular.provideDelegate(jsLoweredDeclarationOrigin, kPropertyArr[9]);
    }

    private JsLoweredDeclarationOrigin() {
    }

    public final IrDeclarationOrigin getBRIDGE_PROPERTY_ACCESSOR() {
        return (IrDeclarationOrigin) BRIDGE_PROPERTY_ACCESSOR.getValue();
    }

    public final IrDeclarationOrigin getBRIDGE_WITHOUT_STABLE_NAME() {
        return (IrDeclarationOrigin) BRIDGE_WITHOUT_STABLE_NAME.getValue();
    }

    public final IrDeclarationOrigin getBRIDGE_WITH_STABLE_NAME() {
        return (IrDeclarationOrigin) BRIDGE_WITH_STABLE_NAME.getValue();
    }

    public final IrDeclarationOrigin getENUM_GET_INSTANCE_FUNCTION() {
        return (IrDeclarationOrigin) ENUM_GET_INSTANCE_FUNCTION.getValue();
    }

    public final IrDeclarationOrigin getJS_CLOSURE_BOX_CLASS_DECLARATION() {
        return (IrDeclarationOrigin) JS_CLOSURE_BOX_CLASS_DECLARATION.getValue();
    }

    public final IrDeclarationOrigin getJS_INTRINSICS_STUB() {
        return (IrDeclarationOrigin) JS_INTRINSICS_STUB.getValue();
    }

    public final IrDeclarationOrigin getJS_SHADOWED_DEFAULT_PARAMETER() {
        return (IrDeclarationOrigin) JS_SHADOWED_DEFAULT_PARAMETER.getValue();
    }

    public final IrDeclarationOrigin getJS_SHADOWED_EXPORT() {
        return (IrDeclarationOrigin) JS_SHADOWED_EXPORT.getValue();
    }

    public final IrDeclarationOrigin getJS_SUPER_CONTEXT_PARAMETER() {
        return (IrDeclarationOrigin) JS_SUPER_CONTEXT_PARAMETER.getValue();
    }

    public final IrDeclarationOrigin getOBJECT_GET_INSTANCE_FUNCTION() {
        return (IrDeclarationOrigin) OBJECT_GET_INSTANCE_FUNCTION.getValue();
    }

    public final boolean isBridgeDeclarationOrigin(IrDeclarationOrigin origin) {
        origin.getClass();
        return Intrinsics.areEqual(origin, getBRIDGE_WITH_STABLE_NAME()) || Intrinsics.areEqual(origin, getBRIDGE_WITHOUT_STABLE_NAME()) || Intrinsics.areEqual(origin, getBRIDGE_PROPERTY_ACCESSOR());
    }
}

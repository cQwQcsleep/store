package org.jetbrains.kotlin.backend.konan;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.NativeRuntimeNames$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b+\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/RuntimeNames;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "symbolNameAnnotation", "Lorg/jetbrains/kotlin/name/FqName;", "getSymbolNameAnnotation", "()Lorg/jetbrains/kotlin/name/FqName;", "cnameAnnotation", "getCnameAnnotation", "exportForCppRuntime", "getExportForCppRuntime", "exportedBridge", "getExportedBridge", "exportTypeInfoAnnotation", "getExportTypeInfoAnnotation", "cCall", "getCCall", "cCallDirect", "getCCallDirect", "cGlobalAccess", "getCGlobalAccess", "cGlobalAccessPointer", "getCGlobalAccessPointer", "cStructMemberAt", "getCStructMemberAt", "cStructArrayMemberAt", "getCStructArrayMemberAt", "cStructBitField", "getCStructBitField", "objCMethodImp", "getObjCMethodImp", "independent", "getIndependent", "filterExceptions", "getFilterExceptions", "kotlinNativeInternalPackageName", "getKotlinNativeInternalPackageName", "kotlinNativeInternalTestPackageName", "getKotlinNativeInternalTestPackageName", "kotlinxCInteropInternalPackageName", "getKotlinxCInteropInternalPackageName", "kotlinNativeCoroutinesInternalPackageName", "getKotlinNativeCoroutinesInternalPackageName", "associatedObjectKey", "getAssociatedObjectKey", "typedIntrinsicAnnotation", "getTypedIntrinsicAnnotation", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class RuntimeNames {
    public static final RuntimeNames INSTANCE = new RuntimeNames();
    private static final FqName associatedObjectKey;
    private static final FqName cCall;
    private static final FqName cCallDirect;
    private static final FqName cGlobalAccess;
    private static final FqName cGlobalAccessPointer;
    private static final FqName cStructArrayMemberAt;
    private static final FqName cStructBitField;
    private static final FqName cStructMemberAt;
    private static final FqName cnameAnnotation;
    private static final FqName exportForCppRuntime;
    private static final FqName exportTypeInfoAnnotation;
    private static final FqName exportedBridge;
    private static final FqName filterExceptions;
    private static final FqName independent;
    private static final FqName kotlinNativeCoroutinesInternalPackageName;
    private static final FqName kotlinNativeInternalPackageName;
    private static final FqName kotlinNativeInternalTestPackageName;
    private static final FqName kotlinxCInteropInternalPackageName;
    private static final FqName objCMethodImp;
    private static final FqName symbolNameAnnotation;
    private static final FqName typedIntrinsicAnnotation;

    static {
        NativeRuntimeNames$Annotations nativeRuntimeNames$Annotations = NativeRuntimeNames$Annotations.INSTANCE;
        symbolNameAnnotation = nativeRuntimeNames$Annotations.getSymbolNameClassId().asSingleFqName();
        cnameAnnotation = nativeRuntimeNames$Annotations.getCNameClassId().asSingleFqName();
        exportForCppRuntime = nativeRuntimeNames$Annotations.getExportForCppRuntimeClassId().asSingleFqName();
        exportedBridge = nativeRuntimeNames$Annotations.getExportedBridgeClassId().asSingleFqName();
        exportTypeInfoAnnotation = nativeRuntimeNames$Annotations.getExportTypeInfoClassId().asSingleFqName();
        cCall = new FqName("kotlinx.cinterop.internal.CCall");
        cCallDirect = new FqName("kotlinx.cinterop.internal.CCall.Direct");
        cGlobalAccess = new FqName("kotlinx.cinterop.internal.CGlobalAccess");
        cGlobalAccessPointer = new FqName("kotlinx.cinterop.internal.CGlobalAccess.Pointer");
        cStructMemberAt = new FqName("kotlinx.cinterop.internal.CStruct.MemberAt");
        cStructArrayMemberAt = new FqName("kotlinx.cinterop.internal.CStruct.ArrayMemberAt");
        cStructBitField = new FqName("kotlinx.cinterop.internal.CStruct.BitField");
        objCMethodImp = new FqName("kotlinx.cinterop.ObjCMethodImp");
        independent = new FqName("kotlin.native.internal.Independent");
        filterExceptions = new FqName("kotlin.native.internal.FilterExceptions");
        FqName.Companion companion = FqName.Companion;
        kotlinNativeInternalPackageName = companion.fromSegments(CollectionsKt.listOf(new String[]{"kotlin", "native", "internal"}));
        kotlinNativeInternalTestPackageName = companion.fromSegments(CollectionsKt.listOf(new String[]{"kotlin", "native", "internal", "test"}));
        kotlinxCInteropInternalPackageName = companion.fromSegments(CollectionsKt.listOf(new String[]{"kotlinx", "cinterop", "internal"}));
        kotlinNativeCoroutinesInternalPackageName = companion.fromSegments(CollectionsKt.listOf(new String[]{"kotlin", "coroutines", "native", "internal"}));
        associatedObjectKey = new FqName("kotlin.reflect.AssociatedObjectKey");
        typedIntrinsicAnnotation = new FqName("kotlin.native.internal.TypedIntrinsic");
    }

    private RuntimeNames() {
    }

    public final FqName getAssociatedObjectKey() {
        return associatedObjectKey;
    }

    public final FqName getCCall() {
        return cCall;
    }

    public final FqName getCCallDirect() {
        return cCallDirect;
    }

    public final FqName getCGlobalAccess() {
        return cGlobalAccess;
    }

    public final FqName getCGlobalAccessPointer() {
        return cGlobalAccessPointer;
    }

    public final FqName getCStructArrayMemberAt() {
        return cStructArrayMemberAt;
    }

    public final FqName getCStructBitField() {
        return cStructBitField;
    }

    public final FqName getCStructMemberAt() {
        return cStructMemberAt;
    }

    public final FqName getCnameAnnotation() {
        return cnameAnnotation;
    }

    public final FqName getExportForCppRuntime() {
        return exportForCppRuntime;
    }

    public final FqName getExportTypeInfoAnnotation() {
        return exportTypeInfoAnnotation;
    }

    public final FqName getExportedBridge() {
        return exportedBridge;
    }

    public final FqName getFilterExceptions() {
        return filterExceptions;
    }

    public final FqName getIndependent() {
        return independent;
    }

    public final FqName getKotlinNativeCoroutinesInternalPackageName() {
        return kotlinNativeCoroutinesInternalPackageName;
    }

    public final FqName getKotlinNativeInternalPackageName() {
        return kotlinNativeInternalPackageName;
    }

    public final FqName getKotlinNativeInternalTestPackageName() {
        return kotlinNativeInternalTestPackageName;
    }

    public final FqName getKotlinxCInteropInternalPackageName() {
        return kotlinxCInteropInternalPackageName;
    }

    public final FqName getObjCMethodImp() {
        return objCMethodImp;
    }

    public final FqName getSymbolNameAnnotation() {
        return symbolNameAnnotation;
    }

    public final FqName getTypedIntrinsicAnnotation() {
        return typedIntrinsicAnnotation;
    }
}

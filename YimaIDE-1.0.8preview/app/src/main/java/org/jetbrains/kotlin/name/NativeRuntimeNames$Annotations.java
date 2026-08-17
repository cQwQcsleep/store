package org.jetbrains.kotlin.name;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/name/NativeRuntimeNames$Annotations;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "symbolNameClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getSymbolNameClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "cNameClassId", "getCNameClassId", "exportedBridgeClassId", "getExportedBridgeClassId", "exportForCppRuntimeClassId", "getExportForCppRuntimeClassId", "exportForCompilerClassId", "getExportForCompilerClassId", "exportTypeInfoClassId", "getExportTypeInfoClassId", "gcUnsafeCallClassId", "getGcUnsafeCallClassId", "Throws", "getThrows", "ThrowsAlias", "getThrowsAlias", "SharedImmutable", "getSharedImmutable", "SharedImmutableAlias", "getSharedImmutableAlias", "ThreadLocal", "getThreadLocal", "ThreadLocalAlias", "getThreadLocalAlias", "PointsTo", "getPointsTo", "Escapes", "getEscapes", "EscapesNothing", "getEscapesNothing", "HasFinalizer", "getHasFinalizer", "BindClassToObjCName", "getBindClassToObjCName", "org.jetbrains.kotlin:compiler.common.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NativeRuntimeNames$Annotations {
    private static final ClassId BindClassToObjCName;
    private static final ClassId Escapes;
    private static final ClassId EscapesNothing;
    private static final ClassId HasFinalizer;
    public static final NativeRuntimeNames$Annotations INSTANCE = new NativeRuntimeNames$Annotations();
    private static final ClassId PointsTo;
    private static final ClassId SharedImmutable;
    private static final ClassId SharedImmutableAlias;
    private static final ClassId ThreadLocal;
    private static final ClassId ThreadLocalAlias;
    private static final ClassId Throws;
    private static final ClassId ThrowsAlias;
    private static final ClassId cNameClassId;
    private static final ClassId exportForCompilerClassId;
    private static final ClassId exportForCppRuntimeClassId;
    private static final ClassId exportTypeInfoClassId;
    private static final ClassId exportedBridgeClassId;
    private static final ClassId gcUnsafeCallClassId;
    private static final ClassId symbolNameClassId;

    static {
        FqName fqNameAccess$getKotlinNativePackage$p = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier = Name.identifier("SymbolName");
        nameIdentifier.getClass();
        symbolNameClassId = new ClassId(fqNameAccess$getKotlinNativePackage$p, nameIdentifier);
        FqName fqNameAccess$getKotlinNativePackage$p2 = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier2 = Name.identifier("CName");
        nameIdentifier2.getClass();
        cNameClassId = new ClassId(fqNameAccess$getKotlinNativePackage$p2, nameIdentifier2);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier3 = Name.identifier("ExportedBridge");
        nameIdentifier3.getClass();
        exportedBridgeClassId = new ClassId(fqNameAccess$getKotlinNativeInternalPackage$p, nameIdentifier3);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p2 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier4 = Name.identifier("ExportForCppRuntime");
        nameIdentifier4.getClass();
        exportForCppRuntimeClassId = new ClassId(fqNameAccess$getKotlinNativeInternalPackage$p2, nameIdentifier4);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p3 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier5 = Name.identifier("ExportForCompiler");
        nameIdentifier5.getClass();
        exportForCompilerClassId = new ClassId(fqNameAccess$getKotlinNativeInternalPackage$p3, nameIdentifier5);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p4 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier6 = Name.identifier("ExportTypeInfo");
        nameIdentifier6.getClass();
        exportTypeInfoClassId = new ClassId(fqNameAccess$getKotlinNativeInternalPackage$p4, nameIdentifier6);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p5 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier7 = Name.identifier("GCUnsafeCall");
        nameIdentifier7.getClass();
        gcUnsafeCallClassId = new ClassId(fqNameAccess$getKotlinNativeInternalPackage$p5, nameIdentifier7);
        FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier8 = Name.identifier("Throws");
        nameIdentifier8.getClass();
        Throws = new ClassId(base_kotlin_package, nameIdentifier8);
        FqName fqNameAccess$getKotlinNativePackage$p3 = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier9 = Name.identifier("Throws");
        nameIdentifier9.getClass();
        ThrowsAlias = new ClassId(fqNameAccess$getKotlinNativePackage$p3, nameIdentifier9);
        FqName fqNameAccess$getKotlinNativePackage$p4 = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier10 = Name.identifier("concurrent");
        nameIdentifier10.getClass();
        FqName fqNameChild = fqNameAccess$getKotlinNativePackage$p4.child(nameIdentifier10);
        Name nameIdentifier11 = Name.identifier("SharedImmutable");
        nameIdentifier11.getClass();
        SharedImmutable = new ClassId(fqNameChild, nameIdentifier11);
        FqName fqNameAccess$getKotlinNativePackage$p5 = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier12 = Name.identifier("SharedImmutable");
        nameIdentifier12.getClass();
        SharedImmutableAlias = new ClassId(fqNameAccess$getKotlinNativePackage$p5, nameIdentifier12);
        FqName fqNameAccess$getKotlinNativePackage$p6 = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier13 = Name.identifier("concurrent");
        nameIdentifier13.getClass();
        FqName fqNameChild2 = fqNameAccess$getKotlinNativePackage$p6.child(nameIdentifier13);
        Name nameIdentifier14 = Name.identifier("ThreadLocal");
        nameIdentifier14.getClass();
        ThreadLocal = new ClassId(fqNameChild2, nameIdentifier14);
        FqName fqNameAccess$getKotlinNativePackage$p7 = NativeRuntimeNames.access$getKotlinNativePackage$p();
        Name nameIdentifier15 = Name.identifier("ThreadLocal");
        nameIdentifier15.getClass();
        ThreadLocalAlias = new ClassId(fqNameAccess$getKotlinNativePackage$p7, nameIdentifier15);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p6 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier16 = Name.identifier("escapeAnalysis");
        nameIdentifier16.getClass();
        FqName fqNameChild3 = fqNameAccess$getKotlinNativeInternalPackage$p6.child(nameIdentifier16);
        Name nameIdentifier17 = Name.identifier("PointsTo");
        nameIdentifier17.getClass();
        PointsTo = new ClassId(fqNameChild3, nameIdentifier17);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p7 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier18 = Name.identifier("escapeAnalysis");
        nameIdentifier18.getClass();
        FqName fqNameChild4 = fqNameAccess$getKotlinNativeInternalPackage$p7.child(nameIdentifier18);
        Name nameIdentifier19 = Name.identifier("Escapes");
        nameIdentifier19.getClass();
        ClassId classId = new ClassId(fqNameChild4, nameIdentifier19);
        Escapes = classId;
        Name nameIdentifier20 = Name.identifier("Nothing");
        nameIdentifier20.getClass();
        EscapesNothing = classId.createNestedClassId(nameIdentifier20);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p8 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier21 = Name.identifier("HasFinalizer");
        nameIdentifier21.getClass();
        HasFinalizer = new ClassId(fqNameAccess$getKotlinNativeInternalPackage$p8, nameIdentifier21);
        FqName fqNameAccess$getKotlinNativeInternalPackage$p9 = NativeRuntimeNames.access$getKotlinNativeInternalPackage$p();
        Name nameIdentifier22 = Name.identifier("objc");
        nameIdentifier22.getClass();
        FqName fqNameChild5 = fqNameAccess$getKotlinNativeInternalPackage$p9.child(nameIdentifier22);
        Name nameIdentifier23 = Name.identifier("BindClassToObjCName");
        nameIdentifier23.getClass();
        BindClassToObjCName = new ClassId(fqNameChild5, nameIdentifier23);
    }

    private NativeRuntimeNames$Annotations() {
    }

    public final ClassId getBindClassToObjCName() {
        return BindClassToObjCName;
    }

    public final ClassId getCNameClassId() {
        return cNameClassId;
    }

    public final ClassId getEscapes() {
        return Escapes;
    }

    public final ClassId getEscapesNothing() {
        return EscapesNothing;
    }

    public final ClassId getExportForCompilerClassId() {
        return exportForCompilerClassId;
    }

    public final ClassId getExportForCppRuntimeClassId() {
        return exportForCppRuntimeClassId;
    }

    public final ClassId getExportTypeInfoClassId() {
        return exportTypeInfoClassId;
    }

    public final ClassId getExportedBridgeClassId() {
        return exportedBridgeClassId;
    }

    public final ClassId getGcUnsafeCallClassId() {
        return gcUnsafeCallClassId;
    }

    public final ClassId getHasFinalizer() {
        return HasFinalizer;
    }

    public final ClassId getPointsTo() {
        return PointsTo;
    }

    public final ClassId getSharedImmutable() {
        return SharedImmutable;
    }

    public final ClassId getSharedImmutableAlias() {
        return SharedImmutableAlias;
    }

    public final ClassId getSymbolNameClassId() {
        return symbolNameClassId;
    }

    public final ClassId getThreadLocal() {
        return ThreadLocal;
    }

    public final ClassId getThreadLocalAlias() {
        return ThreadLocalAlias;
    }

    public final ClassId getThrows() {
        return Throws;
    }

    public final ClassId getThrowsAlias() {
        return ThrowsAlias;
    }
}

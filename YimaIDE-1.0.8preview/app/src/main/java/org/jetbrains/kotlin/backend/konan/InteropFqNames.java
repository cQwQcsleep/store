package org.jetbrains.kotlin.backend.konan;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b#\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u00107\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010;\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b<\u0010:R\u0011\u0010=\u001a\u00020>¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0011\u0010A\u001a\u00020>¢\u0006\b\n\u0000\u001a\u0004\bB\u0010@R\u0011\u0010C\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bD\u0010:R\u0011\u0010E\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bF\u0010:R\u0011\u0010G\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bH\u0010:R\u0011\u0010I\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010:R\u0011\u0010K\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bL\u0010:R\u0011\u0010M\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bN\u0010:R\u0011\u0010O\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bP\u0010:R\u0011\u0010Q\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bR\u0010:R\u0011\u0010S\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bT\u0010:R\u0011\u0010U\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bV\u0010:R\u0011\u0010W\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bX\u0010:R\u0011\u0010Y\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010:R\u0011\u0010[\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010:R\u0011\u0010]\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b^\u0010:R\u0011\u0010_\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b`\u0010:¨\u0006a"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/InteropFqNames;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "cPointerName", "", "nativePointedName", "cVariableName", "objCObjectBaseName", "objCOverrideInitName", "objCOutletName", "objCMethodImpName", "exportObjCClassName", "nativeHeapName", "cValueName", "cValuesName", "cValuesRefName", "cEnumName", "cStructVarName", "cEnumVarName", "cPrimitiveVarName", "cPointedName", "interopStubsName", "managedTypeName", "memScopeName", "foreignObjCObjectName", "cOpaqueName", "objCObjectName", "objCObjectBaseMetaName", "objCClassName", "objCClassOfName", "objCProtocolName", "nativeMemUtilsName", "TypeName", "cstrPropertyName", "wcstrPropertyName", "nativePointedRawPtrPropertyName", "cPointerRawValuePropertyName", "getObjCClassFunName", "objCObjectSuperInitCheckFunName", "allocObjCObjectFunName", "typeOfFunName", "objCObjectInitByFunName", "objCObjectRawPtrFunName", "interpretObjCPointerFunName", "interpretObjCPointerOrNullFunName", "interpretNullablePointedFunName", "interpretCPointerFunName", "nativePointedGetRawPointerFunName", "cPointerGetRawValueFunName", "cValueWriteFunName", "cValueReadFunName", "allocTypeFunName", "cToKotlinBridgeName", "kotlinToCBridgeName", "packageName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageName", "()Lorg/jetbrains/kotlin/name/FqName;", "internalPackageName", "getInternalPackageName", "cPointer", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "getCPointer", "()Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "nativePointed", "getNativePointed", "objCObjectBase", "getObjCObjectBase", "objCOverrideInit", "getObjCOverrideInit", "objCOutlet", "getObjCOutlet", "objCMethodImp", "getObjCMethodImp", "exportObjCClass", "getExportObjCClass", "cValue", "getCValue", "cValues", "getCValues", "cValuesRef", "getCValuesRef", "cEnum", "getCEnum", "cStructVar", "getCStructVar", "cPointed", "getCPointed", "interopStubs", "getInteropStubs", "managedType", "getManagedType", "cToKotlinBridge", "getCToKotlinBridge", "kotlinToCBridge", "getKotlinToCBridge", "org.jetbrains.kotlin:base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InteropFqNames {
    public static final InteropFqNames INSTANCE = new InteropFqNames();
    public static final String TypeName = "Type";
    public static final String allocObjCObjectFunName = "allocObjCObject";
    public static final String allocTypeFunName = "alloc";
    private static final FqName cEnum;
    public static final String cEnumName = "CEnum";
    public static final String cEnumVarName = "CEnumVar";
    public static final String cOpaqueName = "COpaque";
    private static final FqName cPointed;
    public static final String cPointedName = "CPointed";
    private static final FqNameUnsafe cPointer;
    public static final String cPointerGetRawValueFunName = "getRawValue";
    public static final String cPointerName = "CPointer";
    public static final String cPointerRawValuePropertyName = "rawValue";
    public static final String cPrimitiveVarName = "CPrimitiveVar";
    private static final FqName cStructVar;
    public static final String cStructVarName = "CStructVar";
    private static final FqName cToKotlinBridge;
    public static final String cToKotlinBridgeName = "CToKotlinBridge";
    private static final FqName cValue;
    public static final String cValueName = "CValue";
    public static final String cValueReadFunName = "readValue";
    public static final String cValueWriteFunName = "write";
    private static final FqName cValues;
    public static final String cValuesName = "CValues";
    private static final FqName cValuesRef;
    public static final String cValuesRefName = "CValuesRef";
    public static final String cVariableName = "CVariable";
    public static final String cstrPropertyName = "cstr";
    private static final FqName exportObjCClass;
    public static final String exportObjCClassName = "ExportObjCClass";
    public static final String foreignObjCObjectName = "ForeignObjCObject";
    public static final String getObjCClassFunName = "getObjCClass";
    private static final FqName internalPackageName;
    private static final FqName interopStubs;
    public static final String interopStubsName = "InteropStubs";
    public static final String interpretCPointerFunName = "interpretCPointer";
    public static final String interpretNullablePointedFunName = "interpretNullablePointed";
    public static final String interpretObjCPointerFunName = "interpretObjCPointer";
    public static final String interpretObjCPointerOrNullFunName = "interpretObjCPointerOrNull";
    private static final FqName kotlinToCBridge;
    public static final String kotlinToCBridgeName = "KotlinToCBridge";
    private static final FqName managedType;
    public static final String managedTypeName = "ManagedType";
    public static final String memScopeName = "MemScope";
    public static final String nativeHeapName = "nativeHeap";
    public static final String nativeMemUtilsName = "nativeMemUtils";
    private static final FqNameUnsafe nativePointed;
    public static final String nativePointedGetRawPointerFunName = "getRawPointer";
    public static final String nativePointedName = "NativePointed";
    public static final String nativePointedRawPtrPropertyName = "rawPtr";
    public static final String objCClassName = "ObjCClass";
    public static final String objCClassOfName = "ObjCClassOf";
    private static final FqName objCMethodImp;
    public static final String objCMethodImpName = "ObjCMethodImp";
    private static final FqName objCObjectBase;
    public static final String objCObjectBaseMetaName = "ObjCObjectBaseMeta";
    public static final String objCObjectBaseName = "ObjCObjectBase";
    public static final String objCObjectInitByFunName = "initBy";
    public static final String objCObjectName = "ObjCObject";
    public static final String objCObjectRawPtrFunName = "objcPtr";
    public static final String objCObjectSuperInitCheckFunName = "superInitCheck";
    private static final FqName objCOutlet;
    public static final String objCOutletName = "ObjCOutlet";
    private static final FqName objCOverrideInit;
    public static final String objCOverrideInitName = "OverrideInit";
    public static final String objCProtocolName = "ObjCProtocol";
    private static final FqName packageName;
    public static final String typeOfFunName = "typeOf";
    public static final String wcstrPropertyName = "wcstr";

    static {
        FqName fqName = new FqName("kotlinx.cinterop");
        packageName = fqName;
        FqName fqName2 = new FqName("kotlinx.cinterop.internal");
        internalPackageName = fqName2;
        cPointer = InteropUtilsKt.child(fqName, cPointerName).toUnsafe();
        nativePointed = InteropUtilsKt.child(fqName, nativePointedName).toUnsafe();
        FqName fqNameChild = InteropUtilsKt.child(fqName, objCObjectBaseName);
        objCObjectBase = fqNameChild;
        objCOverrideInit = InteropUtilsKt.child(fqNameChild, objCOverrideInitName);
        objCOutlet = InteropUtilsKt.child(fqName, objCOutletName);
        objCMethodImp = InteropUtilsKt.child(fqName, objCMethodImpName);
        exportObjCClass = InteropUtilsKt.child(fqName, exportObjCClassName);
        cValue = InteropUtilsKt.child(fqName, cValueName);
        cValues = InteropUtilsKt.child(fqName, cValuesName);
        cValuesRef = InteropUtilsKt.child(fqName, cValuesRefName);
        cEnum = InteropUtilsKt.child(fqName, cEnumName);
        cStructVar = InteropUtilsKt.child(fqName, cStructVarName);
        cPointed = InteropUtilsKt.child(fqName, cPointedName);
        interopStubs = InteropUtilsKt.child(fqName, interopStubsName);
        managedType = InteropUtilsKt.child(fqName, managedTypeName);
        cToKotlinBridge = InteropUtilsKt.child(fqName2, cToKotlinBridgeName);
        kotlinToCBridge = InteropUtilsKt.child(fqName2, kotlinToCBridgeName);
    }

    private InteropFqNames() {
    }

    public final FqName getCEnum() {
        return cEnum;
    }

    public final FqName getCPointed() {
        return cPointed;
    }

    public final FqNameUnsafe getCPointer() {
        return cPointer;
    }

    public final FqName getCStructVar() {
        return cStructVar;
    }

    public final FqName getCToKotlinBridge() {
        return cToKotlinBridge;
    }

    public final FqName getCValue() {
        return cValue;
    }

    public final FqName getCValues() {
        return cValues;
    }

    public final FqName getCValuesRef() {
        return cValuesRef;
    }

    public final FqName getExportObjCClass() {
        return exportObjCClass;
    }

    public final FqName getInternalPackageName() {
        return internalPackageName;
    }

    public final FqName getInteropStubs() {
        return interopStubs;
    }

    public final FqName getKotlinToCBridge() {
        return kotlinToCBridge;
    }

    public final FqName getManagedType() {
        return managedType;
    }

    public final FqNameUnsafe getNativePointed() {
        return nativePointed;
    }

    public final FqName getObjCMethodImp() {
        return objCMethodImp;
    }

    public final FqName getObjCObjectBase() {
        return objCObjectBase;
    }

    public final FqName getObjCOutlet() {
        return objCOutlet;
    }

    public final FqName getObjCOverrideInit() {
        return objCOverrideInit;
    }

    public final FqName getPackageName() {
        return packageName;
    }
}

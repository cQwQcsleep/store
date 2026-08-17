package org.jetbrains.kotlin.name;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002/0B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u001e\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010 \u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\"\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010$\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010&\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0017R\u000e\u0010(\u001a\u00020)X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020)X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020)X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020)X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020)X\u0086T¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/name/NativeStandardInteropNames;", "", "<init>", "()V", "cInteropPackage", "Lorg/jetbrains/kotlin/name/FqName;", "getCInteropPackage", "()Lorg/jetbrains/kotlin/name/FqName;", "COpaque", "Lorg/jetbrains/kotlin/name/Name;", "getCOpaque$org_jetbrains_kotlin_compiler_common_native", "()Lorg/jetbrains/kotlin/name/Name;", "CStructVar", "getCStructVar$org_jetbrains_kotlin_compiler_common_native", "ObjCObjectBase", "getObjCObjectBase$org_jetbrains_kotlin_compiler_common_native", "ObjCObject", "getObjCObject$org_jetbrains_kotlin_compiler_common_native", "ExperimentalForeignApi", "getExperimentalForeignApi", "objCDirectClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getObjCDirectClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "objCMethodClassId", "getObjCMethodClassId", "objCObjectClassId", "getObjCObjectClassId", "objCFactoryClassId", "getObjCFactoryClassId", "objCConstructorClassId", "getObjCConstructorClassId", "externalObjCClassClassId", "getExternalObjCClassClassId", "objCActionClassId", "getObjCActionClassId", "objCOutletClassId", "getObjCOutletClassId", "objCOverrideInitClassId", "getObjCOverrideInitClassId", "cTypeDefinitionsFileName", "", "nativePointed", "cPointer", "cValue", "cValuesRef", "cEnumVar", "Annotations", "ForwardDeclarations", "org.jetbrains.kotlin:compiler.common.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NativeStandardInteropNames {
    private static final Name COpaque;
    private static final Name CStructVar;
    private static final Name ExperimentalForeignApi;
    public static final NativeStandardInteropNames INSTANCE = new NativeStandardInteropNames();
    private static final Name ObjCObject;
    private static final Name ObjCObjectBase;
    public static final String cEnumVar = "CEnumVar";
    private static final FqName cInteropPackage;
    public static final String cPointer = "CPointer";
    public static final String cTypeDefinitionsFileName = "CTypeDefinitions";
    public static final String cValue = "CValue";
    public static final String cValuesRef = "CValuesRef";
    private static final ClassId externalObjCClassClassId;
    public static final String nativePointed = "NativePointed";
    private static final ClassId objCActionClassId;
    private static final ClassId objCConstructorClassId;
    private static final ClassId objCDirectClassId;
    private static final ClassId objCFactoryClassId;
    private static final ClassId objCMethodClassId;
    private static final ClassId objCObjectClassId;
    private static final ClassId objCOutletClassId;
    private static final ClassId objCOverrideInitClassId;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/name/NativeStandardInteropNames$Annotations;", "", "<init>", "()V", "objCSignatureOverrideClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getObjCSignatureOverrideClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:compiler.common.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Annotations {
        public static final Annotations INSTANCE = new Annotations();
        private static final ClassId objCSignatureOverrideClassId;

        static {
            FqName cInteropPackage = NativeStandardInteropNames.INSTANCE.getCInteropPackage();
            Name nameIdentifier = Name.identifier("ObjCSignatureOverride");
            nameIdentifier.getClass();
            objCSignatureOverrideClassId = new ClassId(cInteropPackage, nameIdentifier);
        }

        private Annotations() {
        }

        public final ClassId getObjCSignatureOverrideClassId() {
            return objCSignatureOverrideClassId;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/name/NativeStandardInteropNames$ForwardDeclarations;", "", "<init>", "()V", "cNamesPackage", "Lorg/jetbrains/kotlin/name/FqName;", "cNamesStructsPackage", "getCNamesStructsPackage", "()Lorg/jetbrains/kotlin/name/FqName;", "objCNamesPackage", "objCNamesClassesPackage", "getObjCNamesClassesPackage", "objCNamesProtocolsPackage", "getObjCNamesProtocolsPackage", "syntheticPackages", "", "getSyntheticPackages", "()Ljava/util/Set;", "org.jetbrains.kotlin:compiler.common.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ForwardDeclarations {
        public static final ForwardDeclarations INSTANCE = new ForwardDeclarations();
        private static final FqName cNamesPackage;
        private static final FqName cNamesStructsPackage;
        private static final FqName objCNamesClassesPackage;
        private static final FqName objCNamesPackage;
        private static final FqName objCNamesProtocolsPackage;
        private static final Set<FqName> syntheticPackages;

        static {
            FqName fqName = new FqName("cnames");
            cNamesPackage = fqName;
            Name nameIdentifier = Name.identifier("structs");
            nameIdentifier.getClass();
            cNamesStructsPackage = fqName.child(nameIdentifier);
            FqName fqName2 = new FqName("objcnames");
            objCNamesPackage = fqName2;
            Name nameIdentifier2 = Name.identifier("classes");
            nameIdentifier2.getClass();
            objCNamesClassesPackage = fqName2.child(nameIdentifier2);
            Name nameIdentifier3 = Name.identifier("protocols");
            nameIdentifier3.getClass();
            objCNamesProtocolsPackage = fqName2.child(nameIdentifier3);
            syntheticPackages = SetsKt.setOf(new FqName[]{fqName, fqName2});
        }

        private ForwardDeclarations() {
        }

        public final FqName getCNamesStructsPackage() {
            return cNamesStructsPackage;
        }

        public final FqName getObjCNamesClassesPackage() {
            return objCNamesClassesPackage;
        }

        public final FqName getObjCNamesProtocolsPackage() {
            return objCNamesProtocolsPackage;
        }

        public final Set<FqName> getSyntheticPackages() {
            return syntheticPackages;
        }
    }

    static {
        FqName fqName = new FqName("kotlinx.cinterop");
        cInteropPackage = fqName;
        Name nameIdentifier = Name.identifier("COpaque");
        nameIdentifier.getClass();
        COpaque = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("CStructVar");
        nameIdentifier2.getClass();
        CStructVar = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("ObjCObjectBase");
        nameIdentifier3.getClass();
        ObjCObjectBase = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("ObjCObject");
        nameIdentifier4.getClass();
        ObjCObject = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("ExperimentalForeignApi");
        nameIdentifier5.getClass();
        ExperimentalForeignApi = nameIdentifier5;
        Name nameIdentifier6 = Name.identifier("ObjCDirect");
        nameIdentifier6.getClass();
        objCDirectClassId = new ClassId(fqName, nameIdentifier6);
        Name nameIdentifier7 = Name.identifier("ObjCMethod");
        nameIdentifier7.getClass();
        objCMethodClassId = new ClassId(fqName, nameIdentifier7);
        Name nameIdentifier8 = Name.identifier("ObjCObject");
        nameIdentifier8.getClass();
        objCObjectClassId = new ClassId(fqName, nameIdentifier8);
        Name nameIdentifier9 = Name.identifier("ObjCFactory");
        nameIdentifier9.getClass();
        objCFactoryClassId = new ClassId(fqName, nameIdentifier9);
        Name nameIdentifier10 = Name.identifier("ObjCConstructor");
        nameIdentifier10.getClass();
        objCConstructorClassId = new ClassId(fqName, nameIdentifier10);
        Name nameIdentifier11 = Name.identifier("ExternalObjCClass");
        nameIdentifier11.getClass();
        externalObjCClassClassId = new ClassId(fqName, nameIdentifier11);
        Name nameIdentifier12 = Name.identifier("ObjCAction");
        nameIdentifier12.getClass();
        objCActionClassId = new ClassId(fqName, nameIdentifier12);
        Name nameIdentifier13 = Name.identifier("ObjCOutlet");
        nameIdentifier13.getClass();
        objCOutletClassId = new ClassId(fqName, nameIdentifier13);
        Name nameIdentifier14 = Name.identifier("ObjCObjectBase.OverrideInit");
        nameIdentifier14.getClass();
        objCOverrideInitClassId = new ClassId(fqName, nameIdentifier14);
    }

    private NativeStandardInteropNames() {
    }

    public final FqName getCInteropPackage() {
        return cInteropPackage;
    }

    public final Name getCOpaque$org_jetbrains_kotlin_compiler_common_native() {
        return COpaque;
    }

    public final Name getCStructVar$org_jetbrains_kotlin_compiler_common_native() {
        return CStructVar;
    }

    public final Name getExperimentalForeignApi() {
        return ExperimentalForeignApi;
    }

    public final ClassId getExternalObjCClassClassId() {
        return externalObjCClassClassId;
    }

    public final ClassId getObjCActionClassId() {
        return objCActionClassId;
    }

    public final ClassId getObjCConstructorClassId() {
        return objCConstructorClassId;
    }

    public final ClassId getObjCDirectClassId() {
        return objCDirectClassId;
    }

    public final ClassId getObjCFactoryClassId() {
        return objCFactoryClassId;
    }

    public final ClassId getObjCMethodClassId() {
        return objCMethodClassId;
    }

    public final Name getObjCObject$org_jetbrains_kotlin_compiler_common_native() {
        return ObjCObject;
    }

    public final Name getObjCObjectBase$org_jetbrains_kotlin_compiler_common_native() {
        return ObjCObjectBase;
    }

    public final ClassId getObjCObjectClassId() {
        return objCObjectClassId;
    }

    public final ClassId getObjCOutletClassId() {
        return objCOutletClassId;
    }

    public final ClassId getObjCOverrideInitClassId() {
        return objCOverrideInitClassId;
    }
}

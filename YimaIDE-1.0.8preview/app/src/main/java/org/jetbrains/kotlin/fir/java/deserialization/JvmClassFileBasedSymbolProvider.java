package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.KotlinCompilerVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.PackagePartsCacheData;
import org.jetbrains.kotlin.fir.java.FirJavaFacade;
import org.jetbrains.kotlin.fir.java.deserialization.JvmClassFileBasedSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder$Result$KotlinClass;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinderKt;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinarySourceElement;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.PathHolder;
import org.jetbrains.kotlin.load.kotlin.PathHolderExtensionsKt;
import org.jetbrains.kotlin.load.kotlin.header.KotlinClassHeader;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmFlags;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.kotlin.serialization.deserialization.IncompatibleVersionErrorData;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.PreReleaseInfo;
import org.jetbrains.kotlin.util.MetadataHelpersKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001@BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001fH\u0014J\u0018\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u001c\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101H\u0014J\u0010\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0014J\u0010\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u001aH\u0016J\u0010\u00107\u001a\u0004\u0018\u000108*\u0004\u0018\u00010\u001dH\u0002J0\u00109\u001a\u0004\u0018\u0001H:\"\b\b\u0000\u0010:*\u00020;2\u0006\u0010<\u001a\u00020#2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H:0>H\u0082\b¢\u0006\u0002\u0010?R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010!\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\"*\u00020#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0018\u0010&\u001a\u00020\u0015*\u00020#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0018\u0010(\u001a\u00020)*\u00020#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/JvmClassFileBasedSymbolProvider;", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "packagePartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "kotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "javaFacade", "Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "ownMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "reportErrorsOnPreReleaseDependencies", Argument.Delimiters.none, "computePackagePartsInfos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "computePackagePartInfo", "partName", Argument.Delimiters.none, "computePackageSetWithNonClassDeclarations", Argument.Delimiters.none, "knownTopLevelClassesInPackage", "incompatibility", "Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "getIncompatibility", "(Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;)Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "isPreReleaseInvisible", "(Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;)Z", "abiStability", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerAbiStability;", "getAbiStability", "(Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;)Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerAbiStability;", "extractClassMetadata", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "parentContext", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "isNewPlaceForBodyGeneration", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "hasPackage", "fqName", "toPath", "Ljava/nio/file/Path;", "parseProto", "T", Argument.Delimiters.none, "klass", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "JavaAwareFlexibleTypeFactory", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class JvmClassFileBasedSymbolProvider extends AbstractFirDeserializedSymbolProvider {
    private final FirJavaFacade javaFacade;
    private final KotlinClassFinder kotlinClassFinder;
    private final MetadataVersion ownMetadataVersion;
    private final PackagePartProvider packagePartProvider;
    private final boolean reportErrorsOnPreReleaseDependencies;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/JvmClassFileBasedSymbolProvider$JavaAwareFlexibleTypeFactory;", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "<init>", "()V", "createFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "createDynamicType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class JavaAwareFlexibleTypeFactory implements FirTypeDeserializer.FlexibleTypeFactory {
        public static final JavaAwareFlexibleTypeFactory INSTANCE = new JavaAwareFlexibleTypeFactory();

        private JavaAwareFlexibleTypeFactory() {
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer.FlexibleTypeFactory
        /* JADX INFO: renamed from: createDynamicType */
        public ConeKotlinType mo620createDynamicType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound) {
            proto.getClass();
            lowerBound.getClass();
            upperBound.getClass();
            return FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE.mo620createDynamicType(proto, lowerBound, upperBound);
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer.FlexibleTypeFactory
        public ConeFlexibleType createFlexibleType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound) {
            proto.getClass();
            lowerBound.getClass();
            upperBound.getClass();
            boolean zHasExtension = proto.hasExtension(JvmProtoBuf.isRaw);
            if (zHasExtension) {
                return ConeRawType.INSTANCE.create(lowerBound, upperBound);
            }
            if (!zHasExtension) {
                return FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE.createFlexibleType(proto, lowerBound, upperBound);
            }
            bu8.a();
            return null;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            try {
                iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KotlinClassHeader.Kind.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[KotlinClassHeader.Kind.CLASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[KotlinClassHeader.Kind.SYNTHETIC_CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmClassFileBasedSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackagePartProvider packagePartProvider, KotlinClassFinder kotlinClassFinder, FirJavaFacade firJavaFacade, FirDeclarationOrigin firDeclarationOrigin) {
        super(firSession, moduleDataProvider, firKotlinScopeProvider, firDeclarationOrigin, BuiltInSerializerProtocol.INSTANCE);
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        packagePartProvider.getClass();
        kotlinClassFinder.getClass();
        firJavaFacade.getClass();
        firDeclarationOrigin.getClass();
        this.packagePartProvider = packagePartProvider;
        this.kotlinClassFinder = kotlinClassFinder;
        this.javaFacade = firJavaFacade;
        this.ownMetadataVersion = MetadataHelpersKt.toJvmMetadataVersion(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getLanguageVersion());
        LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession);
        this.reportErrorsOnPreReleaseDependencies = (((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getSkipPrereleaseCheck())).booleanValue() || languageVersionSettings.isPreRelease() || KotlinCompilerVersion.isPreRelease()) ? false : true;
    }

    private final PackagePartsCacheData computePackagePartInfo(FqName packageFqName, String partName) {
        String[] strings;
        Pair packageDataFrom;
        ClassId.Companion companion = ClassId.Companion;
        FqName fqNameForTopLevelClassMaybeWithDollars = JvmClassName.byInternalName(partName).getFqNameForTopLevelClassMaybeWithDollars();
        fqNameForTopLevelClassMaybeWithDollars.getClass();
        ClassId classId = companion.topLevel(fqNameForTopLevelClassMaybeWithDollars);
        if (!this.javaFacade.hasTopLevelClassOf(classId)) {
            return null;
        }
        KotlinClassFinder.Result resultFindKotlinClassOrContent = this.kotlinClassFinder.findKotlinClassOrContent(classId, this.ownMetadataVersion);
        KotlinClassFinder$Result$KotlinClass kotlinClassFinder$Result$KotlinClass = resultFindKotlinClassOrContent instanceof KotlinClassFinder$Result$KotlinClass ? (KotlinClassFinder$Result$KotlinClass) resultFindKotlinClassOrContent : null;
        if (kotlinClassFinder$Result$KotlinClass == null) {
            return null;
        }
        KotlinJvmBinaryClass kotlinJvmBinaryClass = kotlinClassFinder$Result$KotlinClass.getKotlinJvmBinaryClass();
        byte[] byteContent = kotlinClassFinder$Result$KotlinClass.getByteContent();
        KotlinClassHeader classHeader = kotlinJvmBinaryClass.getClassHeader();
        switch (WhenMappings.$EnumSwitchMapping$0[classHeader.getKind().ordinal()]) {
            case 1:
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                String[] data = classHeader.getData();
                if ((data == null && (data = classHeader.getIncompatibleData()) == null) || (strings = classHeader.getStrings()) == null) {
                    return null;
                }
                String multifileClassName = classHeader.getMultifileClassName();
                if (multifileClassName == null || multifileClassName.length() <= 0) {
                    multifileClassName = null;
                }
                FqName fqNameForTopLevelClassMaybeWithDollars2 = multifileClassName != null ? JvmClassName.byInternalName(multifileClassName).getFqNameForTopLevelClassMaybeWithDollars() : null;
                KotlinJvmBinaryClass kotlinJvmBinaryClassFindKotlinClass = fqNameForTopLevelClassMaybeWithDollars2 != null ? KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, companion.topLevel(fqNameForTopLevelClassMaybeWithDollars2), this.ownMetadataVersion) : null;
                ModuleDataProvider moduleDataProvider = getModuleDataProvider();
                PathHolder containingLibraryPath = kotlinJvmBinaryClass.getContainingLibraryPath();
                FirModuleData moduleData = moduleDataProvider.getModuleData(containingLibraryPath != null ? PathHolderExtensionsKt.asNioPath(containingLibraryPath) : null);
                if (moduleData == null) {
                    return null;
                }
                try {
                    packageDataFrom = JvmProtoBufUtil.readPackageDataFrom(data, strings);
                    break;
                } catch (Throwable th) {
                    if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getSkipMetadataVersionCheck())).booleanValue() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible(this.ownMetadataVersion)) {
                        if (!(th instanceof InvalidProtocolBufferException)) {
                            throw th;
                        }
                        throw new IllegalStateException("Could not read data from " + kotlinJvmBinaryClass.getLocation(), th);
                    }
                    packageDataFrom = null;
                }
                if (packageDataFrom == null) {
                    return null;
                }
                NameResolver nameResolver = (JvmNameResolver) packageDataFrom.component1();
                ProtoBuf.Package r15 = (ProtoBuf.Package) packageDataFrom.component2();
                DeserializedContainerSource jvmPackagePartSource = new JvmPackagePartSource(kotlinJvmBinaryClass, r15, nameResolver, getIncompatibility(kotlinJvmBinaryClass), isPreReleaseInvisible(kotlinJvmBinaryClass), getAbiStability(kotlinJvmBinaryClass));
                FirDeserializationContext.Companion companion2 = FirDeserializationContext.INSTANCE;
                JvmBinaryAnnotationDeserializer jvmBinaryAnnotationDeserializer = new JvmBinaryAnnotationDeserializer(getSession(), kotlinJvmBinaryClass, this.kotlinClassFinder, byteContent);
                JavaAwareFlexibleTypeFactory javaAwareFlexibleTypeFactory = JavaAwareFlexibleTypeFactory.INSTANCE;
                if (kotlinJvmBinaryClassFindKotlinClass != null) {
                    kotlinJvmBinaryClass = kotlinJvmBinaryClassFindKotlinClass;
                }
                return new PackagePartsCacheData(r15, companion2.createForPackage(packageFqName, r15, nameResolver, moduleData, jvmBinaryAnnotationDeserializer, javaAwareFlexibleTypeFactory, new FirJvmConstDeserializer(kotlinJvmBinaryClass, BuiltInSerializerProtocol.INSTANCE), FirKDocDeserializer.Empty.INSTANCE, jvmPackagePartSource), null, null, 12, null);
            default:
                bu8.a();
            case 3:
            case 4:
            case 5:
            case 6:
                return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static Unit f(JvmClassFileBasedSymbolProvider jvmClassFileBasedSymbolProvider, ClassId classId, JavaClass javaClass, FirRegularClassSymbol firRegularClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firRegularClassSymbol.getClass();
        FirJavaFacade firJavaFacade = jvmClassFileBasedSymbolProvider.javaFacade;
        ClassId outerClassId = classId.getOuterClassId();
        firJavaFacade.convertJavaClassToFir(firRegularClassSymbol, outerClassId != null ? AbstractFirDeserializedSymbolProvider.getClass$default(jvmClassFileBasedSymbolProvider, outerClassId, null, 2, null) : null, javaClass);
        return Unit.INSTANCE;
    }

    private final DeserializedContainerAbiStability getAbiStability(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (!((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getAllowUnstableDependencies())).booleanValue() && kotlinJvmBinaryClass.getClassHeader().isUnstableJvmIrBinary()) {
            return DeserializedContainerAbiStability.UNSTABLE;
        }
        return DeserializedContainerAbiStability.STABLE;
    }

    private final IncompatibleVersionErrorData<MetadataVersion> getIncompatibility(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getSkipMetadataVersionCheck())).booleanValue() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible(this.ownMetadataVersion)) {
            return null;
        }
        MetadataVersion metadataVersion = kotlinJvmBinaryClass.getClassHeader().getMetadataVersion();
        MetadataVersion metadataVersion2 = MetadataVersion.INSTANCE;
        MetadataVersion metadataVersion3 = this.ownMetadataVersion;
        return new IncompatibleVersionErrorData<>(metadataVersion, metadataVersion2, metadataVersion3, metadataVersion3.lastSupportedVersionWithThisLanguageVersion(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isStrictSemantics()), kotlinJvmBinaryClass.getLocation());
    }

    private final boolean isPreReleaseInvisible(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        return this.reportErrorsOnPreReleaseDependencies && kotlinJvmBinaryClass.getClassHeader().isPreRelease();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public List<PackagePartsCacheData> computePackagePartsInfos(FqName packageFqName) {
        packageFqName.getClass();
        List listFindPackageParts = this.packagePartProvider.findPackageParts(packageFqName.asString());
        ArrayList arrayList = new ArrayList();
        Iterator it = listFindPackageParts.iterator();
        while (it.hasNext()) {
            PackagePartsCacheData packagePartsCacheDataComputePackagePartInfo = computePackagePartInfo(packageFqName, (String) it.next());
            if (packagePartsCacheDataComputePackagePartInfo != null) {
                arrayList.add(packagePartsCacheDataComputePackagePartInfo);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> computePackageSetWithNonClassDeclarations() {
        return this.packagePartProvider.computePackageSetWithNonClassDeclarations();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
    
        if (((r13 != null ? (org.jetbrains.kotlin.fir.declarations.FirRegularClass) r13.getFir() : null) instanceof org.jetbrains.kotlin.fir.java.declarations.FirJavaClass) == false) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult extractClassMetadata(final ClassId classId, FirDeserializationContext parentContext) {
        String[] strings;
        Pair classDataFrom;
        classId.getClass();
        if (!this.javaFacade.hasTopLevelClassOf(classId)) {
            return null;
        }
        KotlinClassFinder.Result.ClassFileContent classFileContentFindKotlinClassOrContent = this.kotlinClassFinder.findKotlinClassOrContent(classId, this.ownMetadataVersion);
        if (!(classFileContentFindKotlinClassOrContent instanceof KotlinClassFinder$Result$KotlinClass)) {
            if (parentContext == null) {
                if (classId.isNestedClass()) {
                    FirRegularClassSymbol class$default = AbstractFirDeserializedSymbolProvider.getClass$default(this, classId.getOutermostClassId(), null, 2, null);
                }
                KotlinClassFinder.Result.ClassFileContent classFileContent = classFileContentFindKotlinClassOrContent instanceof KotlinClassFinder.Result.ClassFileContent ? classFileContentFindKotlinClassOrContent : null;
                final JavaClass javaClassFindClass = this.javaFacade.findClass(classId, classFileContent != null ? classFileContent.getContent() : null);
                if (javaClassFindClass == null) {
                    return null;
                }
                return new AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult.NoMetadata(new Function1() { // from class: aw7
                    public final Object invoke(Object obj) {
                        return JvmClassFileBasedSymbolProvider.f(this.b, classId, javaClassFindClass, (FirRegularClassSymbol) obj);
                    }
                });
            }
            return null;
        }
        KotlinClassFinder$Result$KotlinClass kotlinClassFinder$Result$KotlinClass = (KotlinClassFinder$Result$KotlinClass) classFileContentFindKotlinClassOrContent;
        KotlinJvmBinaryClass kotlinJvmBinaryClass = kotlinClassFinder$Result$KotlinClass.getKotlinJvmBinaryClass();
        if (kotlinJvmBinaryClass.getClassHeader().getKind() != KotlinClassHeader.Kind.CLASS || !Intrinsics.areEqual(kotlinJvmBinaryClass.getClassId(), classId)) {
            return null;
        }
        String[] data = kotlinJvmBinaryClass.getClassHeader().getData();
        if ((data == null && (data = kotlinJvmBinaryClass.getClassHeader().getIncompatibleData()) == null) || (strings = kotlinJvmBinaryClass.getClassHeader().getStrings()) == null) {
            return null;
        }
        try {
            classDataFrom = JvmProtoBufUtil.readClassDataFrom(data, strings);
        } catch (Throwable th) {
            if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getSkipMetadataVersionCheck())).booleanValue() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible(this.ownMetadataVersion)) {
                if (!(th instanceof InvalidProtocolBufferException)) {
                    throw th;
                }
                throw new IllegalStateException("Could not read data from " + kotlinJvmBinaryClass.getLocation(), th);
            }
            classDataFrom = null;
        }
        if (classDataFrom == null) {
            return null;
        }
        JvmNameResolver jvmNameResolver = (JvmNameResolver) classDataFrom.component1();
        ProtoBuf.Class r6 = (ProtoBuf.Class) classDataFrom.component2();
        JvmBinaryAnnotationDeserializer jvmBinaryAnnotationDeserializer = new JvmBinaryAnnotationDeserializer(getSession(), kotlinJvmBinaryClass, this.kotlinClassFinder, kotlinClassFinder$Result$KotlinClass.getByteContent());
        ModuleDataProvider moduleDataProvider = getModuleDataProvider();
        PathHolder containingLibraryPath = kotlinJvmBinaryClass.getContainingLibraryPath();
        return new AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult.Metadata(jvmNameResolver, r6, jvmBinaryAnnotationDeserializer, moduleDataProvider.getModuleData(containingLibraryPath != null ? PathHolderExtensionsKt.asNioPath(containingLibraryPath) : null), new KotlinJvmBinarySourceElement(kotlinJvmBinaryClass, getIncompatibility(kotlinJvmBinaryClass), new PreReleaseInfo(isPreReleaseInvisible(kotlinJvmBinaryClass), (List) null, 2, (DefaultConstructorMarker) null), getAbiStability(kotlinJvmBinaryClass)), JavaAwareFlexibleTypeFactory.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return this.javaFacade.hasPackage(fqName);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public boolean isNewPlaceForBodyGeneration(ProtoBuf.Class classProto) {
        classProto.getClass();
        Flags.BooleanFlagField is_compiled_in_jvm_default_mode = JvmFlags.INSTANCE.getIS_COMPILED_IN_JVM_DEFAULT_MODE();
        Object extension = classProto.getExtension(JvmProtoBuf.jvmClassFlags);
        extension.getClass();
        Boolean bool = is_compiled_in_jvm_default_mode.get(((Number) extension).intValue());
        bool.getClass();
        return bool.booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> knownTopLevelClassesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        return this.javaFacade.knownClassNamesInPackage(packageFqName);
    }

    public /* synthetic */ JvmClassFileBasedSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackagePartProvider packagePartProvider, KotlinClassFinder kotlinClassFinder, FirJavaFacade firJavaFacade, FirDeclarationOrigin firDeclarationOrigin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, moduleDataProvider, firKotlinScopeProvider, packagePartProvider, kotlinClassFinder, firJavaFacade, (i & 64) != 0 ? FirDeclarationOrigin.Library.INSTANCE : firDeclarationOrigin);
    }
}

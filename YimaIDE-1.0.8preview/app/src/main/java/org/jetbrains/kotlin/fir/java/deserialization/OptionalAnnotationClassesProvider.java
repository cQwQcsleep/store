package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializationUtilKt;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.MetadataBasedAnnotationDeserializer;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.PackagePartsCacheData;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.java.deserialization.OptionalAnnotationClassesProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmFlags;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.serialization.deserialization.ClassData;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0085\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0011\u0018\u0000 62\u00020\u0001:\u00016B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010(\u001a\u00020 H\u0014J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00190!H\u0014J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00190!2\u0006\u0010(\u001a\u00020 H\u0014J\u001c\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020\u00162\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0014J\u0010\u00104\u001a\u0002012\u0006\u00105\u001a\u00020 H\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R9\u0010\u0013\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR-\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190!0\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u001d\u001a\u0004\b\"\u0010#¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider;", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "packagePartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "getPackagePartProvider", "()Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "annotationDeserializer", "org/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider$annotationDeserializer$1", "Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider$annotationDeserializer$1;", "optionalAnnotationClassesAndPackages", "Lkotlin/Pair;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/serialization/deserialization/ClassData;", Argument.Delimiters.none, Argument.Delimiters.none, "getOptionalAnnotationClassesAndPackages", "()Lkotlin/Pair;", "optionalAnnotationClassesAndPackages$delegate", "Lkotlin/Lazy;", "optionalAnnotationClassNamesByPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "getOptionalAnnotationClassNamesByPackage", "()Ljava/util/Map;", "optionalAnnotationClassNamesByPackage$delegate", "computePackagePartsInfos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;", "packageFqName", "computePackageSetWithNonClassDeclarations", "knownTopLevelClassesInPackage", "extractClassMetadata", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "classId", "parentContext", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "isNewPlaceForBodyGeneration", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "hasPackage", "fqName", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OptionalAnnotationClassesProvider extends AbstractFirDeserializedSymbolProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final OptionalAnnotationClassesProvider$annotationDeserializer$1 annotationDeserializer;

    /* JADX INFO: renamed from: optionalAnnotationClassNamesByPackage$delegate, reason: from kotlin metadata */
    private final Lazy optionalAnnotationClassNamesByPackage;

    /* JADX INFO: renamed from: optionalAnnotationClassesAndPackages$delegate, reason: from kotlin metadata */
    private final Lazy optionalAnnotationClassesAndPackages;
    private final PackagePartProvider packagePartProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r6v1, types: [org.jetbrains.kotlin.fir.java.deserialization.OptionalAnnotationClassesProvider$annotationDeserializer$1] */
    public OptionalAnnotationClassesProvider(final FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackagePartProvider packagePartProvider, FirDeclarationOrigin firDeclarationOrigin) {
        super(firSession, moduleDataProvider, firKotlinScopeProvider, firDeclarationOrigin, BuiltInSerializerProtocol.INSTANCE);
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        packagePartProvider.getClass();
        firDeclarationOrigin.getClass();
        this.packagePartProvider = packagePartProvider;
        this.annotationDeserializer = new MetadataBasedAnnotationDeserializer() { // from class: org.jetbrains.kotlin.fir.java.deserialization.OptionalAnnotationClassesProvider$annotationDeserializer$1
            {
                super(this.$session);
            }

            @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
            public List<FirAnnotation> loadClassAnnotations(ProtoBuf.Class classProto, NameResolver nameResolver) {
                classProto.getClass();
                nameResolver.getClass();
                List annotationList = classProto.getAnnotationList();
                annotationList.getClass();
                return !annotationList.isEmpty() ? AnnotationDeserializationUtilKt.loadAnnotationsFromMetadata$default(this.$session, annotationList, nameResolver, null, 8, null) : super.loadClassAnnotations(classProto, nameResolver);
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.optionalAnnotationClassesAndPackages = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: vqa
            public final Object invoke() {
                return OptionalAnnotationClassesProvider.f(this.b);
            }
        });
        this.optionalAnnotationClassNamesByPackage = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: wqa
            public final Object invoke() {
                return OptionalAnnotationClassesProvider.g(this.b);
            }
        });
    }

    public static Pair f(OptionalAnnotationClassesProvider optionalAnnotationClassesProvider) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ClassData classData : optionalAnnotationClassesProvider.packagePartProvider.getAllOptionalAnnotationClasses()) {
            ClassId classId = NameResolverUtilKt.getClassId(classData.getNameResolver(), classData.getClassProto().getFqName());
            linkedHashMap.put(classId, classData);
            linkedHashSet.add(classId.getPackageFqName().asString());
        }
        return new Pair(linkedHashMap, linkedHashSet);
    }

    public static Map g(OptionalAnnotationClassesProvider optionalAnnotationClassesProvider) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (ClassId classId : ((Map) optionalAnnotationClassesProvider.getOptionalAnnotationClassesAndPackages().getFirst()).keySet()) {
            FqName packageFqName = classId.getPackageFqName();
            Object linkedHashSet = mapCreateMapBuilder.get(packageFqName);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                mapCreateMapBuilder.put(packageFqName, linkedHashSet);
            }
            String strAsString = classId.getShortClassName().asString();
            strAsString.getClass();
            ((Set) linkedHashSet).add(strAsString);
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    private final Map<FqName, Set<String>> getOptionalAnnotationClassNamesByPackage() {
        return (Map) this.optionalAnnotationClassNamesByPackage.getValue();
    }

    private final Pair<Map<ClassId, ClassData>, Set<String>> getOptionalAnnotationClassesAndPackages() {
        return (Pair) this.optionalAnnotationClassesAndPackages.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public List<PackagePartsCacheData> computePackagePartsInfos(FqName packageFqName) {
        packageFqName.getClass();
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> computePackageSetWithNonClassDeclarations() {
        return (Set) getOptionalAnnotationClassesAndPackages().getSecond();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult extractClassMetadata(ClassId classId, FirDeserializationContext parentContext) {
        classId.getClass();
        ClassData classData = (ClassData) ((Map) getOptionalAnnotationClassesAndPackages().getFirst()).get(classId);
        if (classData == null) {
            return null;
        }
        return new AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult.Metadata(classData.getNameResolver(), classData.getClassProto(), this.annotationDeserializer, (FirModuleData) CollectionsKt.last(getModuleDataProvider().getAllModuleData()), null, FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE);
    }

    public final PackagePartProvider getPackagePartProvider() {
        return this.packagePartProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return ((Set) getOptionalAnnotationClassesAndPackages().getSecond()).contains(fqName.asString());
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
        Set<String> set = getOptionalAnnotationClassNamesByPackage().get(packageFqName);
        return set == null ? SetsKt.emptySet() : set;
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "createIfNeeded", "Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "packagePartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ OptionalAnnotationClassesProvider createIfNeeded$default(Companion companion, FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackagePartProvider packagePartProvider, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
            if ((i & 16) != 0) {
                firDeclarationOrigin = FirDeclarationOrigin.Library.INSTANCE;
            }
            return companion.createIfNeeded(firSession, moduleDataProvider, firKotlinScopeProvider, packagePartProvider, firDeclarationOrigin);
        }

        public final OptionalAnnotationClassesProvider createIfNeeded(FirSession session, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider kotlinScopeProvider, PackagePartProvider packagePartProvider, FirDeclarationOrigin defaultDeserializationOrigin) {
            session.getClass();
            moduleDataProvider.getClass();
            kotlinScopeProvider.getClass();
            packagePartProvider.getClass();
            defaultDeserializationOrigin.getClass();
            if (packagePartProvider.mayHaveOptionalAnnotationClasses()) {
                return new OptionalAnnotationClassesProvider(session, moduleDataProvider, kotlinScopeProvider, packagePartProvider, defaultDeserializationOrigin);
            }
            return null;
        }

        private Companion() {
        }
    }

    public /* synthetic */ OptionalAnnotationClassesProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackagePartProvider packagePartProvider, FirDeclarationOrigin firDeclarationOrigin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, moduleDataProvider, firKotlinScopeProvider, packagePartProvider, (i & 16) != 0 ? FirDeclarationOrigin.Library.INSTANCE : firDeclarationOrigin);
    }
}

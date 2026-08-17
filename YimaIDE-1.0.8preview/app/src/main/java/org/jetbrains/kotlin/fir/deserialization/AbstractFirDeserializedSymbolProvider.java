package org.jetbrains.kotlin.fir.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001jB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0016\u00107\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010'\u001a\u00020!H$J\u0010\u00108\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H$J\u0018\u00109\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\u0006\u0010'\u001a\u00020!H$J\u001e\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010<\u001a\u00020+2\n\b\u0002\u0010=\u001a\u0004\u0018\u000100H$J\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH$J\u0016\u0010F\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010'\u001a\u00020!H\u0002J<\u0010G\u001a$\u0012\u0006\u0012\u0004\u0018\u00010,\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020J\u0018\u00010Ij\u0004\u0018\u0001`K0H2\u0006\u0010<\u001a\u00020+2\b\u0010L\u001a\u0004\u0018\u00010-H\u0002J>\u0010M\u001a$\u0012\u0006\u0012\u0004\u0018\u00010/\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020J\u0018\u00010Ij\u0004\u0018\u0001`N0H2\u0006\u0010<\u001a\u00020+2\n\b\u0002\u0010=\u001a\u0004\u0018\u000100H\u0002J\u0016\u0010O\u001a\b\u0012\u0004\u0012\u0002040(2\u0006\u00103\u001a\u000202H\u0002J\u0016\u0010P\u001a\b\u0012\u0004\u0012\u0002060(2\u0006\u00103\u001a\u000202H\u0002J \u0010Q\u001a\u00020J2\u0006\u0010R\u001a\u00020)2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0016J \u0010W\u001a\u00020J2\u0006\u0010R\u001a\u00020)2\u0006\u0010S\u001a\u00020X2\u0006\u0010U\u001a\u00020YH\u0016J\u0016\u0010Z\u001a\b\u0012\u0004\u0012\u00020)0[2\u0006\u0010'\u001a\u00020!H\u0002J\u001e\u0010\\\u001a\u0004\u0018\u00010/2\u0006\u0010<\u001a\u00020+2\n\b\u0002\u0010=\u001a\u0004\u0018\u000100H\u0004J\u001c\u0010]\u001a\u0004\u0018\u00010,2\u0006\u0010<\u001a\u00020+2\b\u0010L\u001a\u0004\u0018\u00010-H\u0004J.\u0010^\u001a\u00020J2\u0010\u0010_\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030a0`2\u0006\u0010'\u001a\u00020!2\u0006\u0010&\u001a\u00020\"H\u0017b\u0002\bbJB\u0010c\u001a\b\u0012\u0004\u0012\u0002Hd0(\"\f\b\u0000\u0010d*\u0006\u0012\u0002\b\u00030a*\u001c\u0012\u0004\u0012\u000202\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0(\u0012\u0006\u0012\u0004\u0018\u00010#0 2\u0006\u0010e\u001a\u000202H\u0002J*\u0010f\u001a\u00020J2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002040`2\u0006\u0010'\u001a\u00020!2\u0006\u0010&\u001a\u00020\"H\u0017b\u0002\bbJ*\u0010g\u001a\u00020J2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002060`2\u0006\u0010'\u001a\u00020!2\u0006\u0010&\u001a\u00020\"H\u0017b\u0002\bbJ\u0016\u0010h\u001a\b\u0012\u0002\b\u0003\u0018\u00010i2\u0006\u0010<\u001a\u00020+H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR(\u0010\u001f\u001a\u001c\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u0015\u0012\u0006\u0012\u0004\u0018\u00010#0 X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010$\u001a+\u0012\u0013\u0012\u00110!¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(\u0012\u0006\u0012\u0004\u0018\u00010#0 X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010*\u001a\u0018\u0012\u0004\u0012\u00020+\u0012\u0006\u0012\u0004\u0018\u00010,\u0012\u0006\u0012\u0004\u0018\u00010-0 X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010.\u001a\u0018\u0012\u0004\u0012\u00020+\u0012\u0006\u0012\u0004\u0018\u00010/\u0012\u0006\u0012\u0004\u0018\u0001000 X\u0082\u0004¢\u0006\u0002\n\u0000R7\u00101\u001a+\u0012\u0013\u0012\u001102¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(3\u0012\n\u0012\b\u0012\u0004\u0012\u0002040(\u0012\u0006\u0012\u0004\u0018\u00010#0 X\u0082\u0004¢\u0006\u0002\n\u0000R7\u00105\u001a+\u0012\u0013\u0012\u001102¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(3\u0012\n\u0012\b\u0012\u0004\u0012\u0002060(\u0012\u0006\u0012\u0004\u0018\u00010#0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\u00020CX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010E¨\u0006k"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "serializerExtensionProtocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;)V", "getModuleDataProvider", "()Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "getKotlinScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "getDefaultDeserializationOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "packageNamesForNonClassDeclarations", Argument.Delimiters.none, Argument.Delimiters.none, "getPackageNamesForNonClassDeclarations", "()Ljava/util/Set;", "packageNamesForNonClassDeclarations$delegate", "Lkotlin/Lazy;", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "typeAliasesNamesByPackage", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "packagePartsCache", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "packageFqName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;", "typeAliasCache", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "Lorg/jetbrains/kotlin/fir/deserialization/FirNestedTypeAliasDeserializationContext;", "classCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "functionCache", "Lorg/jetbrains/kotlin/name/CallableId;", "callableId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "propertyCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "computePackagePartsInfos", "computePackageSetWithNonClassDeclarations", "knownTopLevelClassesInPackage", "extractClassMetadata", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "classId", "parentContext", "isNewPlaceForBodyGeneration", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "kdocDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "getKdocDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "tryComputePackagePartInfos", "findAndDeserializeTypeAlias", "Lkotlin/Pair;", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/deserialization/DeserializedTypeAliasPostProcessor;", "nestedTypeAliasContext", "findAndDeserializeClass", "Lorg/jetbrains/kotlin/fir/deserialization/DeserializedClassPostProcessor;", "loadFunctionsByCallableId", "loadPropertiesByCallableId", "loadFunctionExtensions", "packagePart", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "loadPropertyExtensions", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getPackageParts", Argument.Delimiters.none, "getClass", "getTypeAlias", "getTopLevelCallableSymbolsTo", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getCallables", "C", "id", "getTopLevelFunctionSymbolsTo", "getTopLevelPropertySymbolsTo", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "ClassMetadataFindResult", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirDeserializedSymbolProvider extends FirSymbolProvider {
    private final FirCache<ClassId, FirRegularClassSymbol, FirDeserializationContext> classCache;
    private final FirDeclarationOrigin defaultDeserializationOrigin;
    private final FirCache functionCache;
    private final FirKDocDeserializer kdocDeserializer;
    private final FirKotlinScopeProvider kotlinScopeProvider;
    private final ModuleDataProvider moduleDataProvider;

    /* JADX INFO: renamed from: packageNamesForNonClassDeclarations$delegate, reason: from kotlin metadata */
    private final Lazy packageNamesForNonClassDeclarations;
    private final FirCache packagePartsCache;
    private final FirCache propertyCache;
    private final SerializerExtensionProtocol serializerExtensionProtocol;
    private final FirSymbolNamesProvider symbolNamesProvider;
    private final FirCache<ClassId, FirTypeAliasSymbol, FirNestedTypeAliasDeserializationContext> typeAliasCache;
    private final FirCache typeAliasesNamesByPackage;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$findAndDeserializeClass$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<ClassId, FirDeserializationContext, FirRegularClassSymbol> {
        public AnonymousClass1(Object obj) {
            super(2, obj, AbstractFirDeserializedSymbolProvider.class, "getClass", "getClass(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", 0);
        }

        public final FirRegularClassSymbol invoke(ClassId classId, FirDeserializationContext firDeserializationContext) {
            classId.getClass();
            return ((AbstractFirDeserializedSymbolProvider) ((CallableReference) this).receiver).getClass(classId, firDeserializationContext);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$findAndDeserializeClass$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<ClassId, FirNestedTypeAliasDeserializationContext, FirTypeAliasSymbol> {
        public AnonymousClass2(Object obj) {
            super(2, obj, AbstractFirDeserializedSymbolProvider.class, "getTypeAlias", "getTypeAlias(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/deserialization/FirNestedTypeAliasDeserializationContext;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", 0);
        }

        public final FirTypeAliasSymbol invoke(ClassId classId, FirNestedTypeAliasDeserializationContext firNestedTypeAliasDeserializationContext) {
            classId.getClass();
            return ((AbstractFirDeserializedSymbolProvider) ((CallableReference) this).receiver).getTypeAlias(classId, firNestedTypeAliasDeserializationContext);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractFirDeserializedSymbolProvider(final FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, FirDeclarationOrigin firDeclarationOrigin, SerializerExtensionProtocol serializerExtensionProtocol) {
        super(firSession);
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        firDeclarationOrigin.getClass();
        serializerExtensionProtocol.getClass();
        this.moduleDataProvider = moduleDataProvider;
        this.kotlinScopeProvider = firKotlinScopeProvider;
        this.defaultDeserializationOrigin = firDeclarationOrigin;
        this.serializerExtensionProtocol = serializerExtensionProtocol;
        this.packageNamesForNonClassDeclarations = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: nm
            public final Object invoke() {
                return AbstractFirDeserializedSymbolProvider.e(this.b);
            }
        });
        this.symbolNamesProvider = new FirCachedSymbolNamesProvider(firSession) { // from class: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<String> computePackageNames() {
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<String> computePackageNamesWithTopLevelCallables() {
                return this.getPackageNamesForNonClassDeclarations();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<String> computePackageNamesWithTopLevelClassifiers() {
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<Name> computeTopLevelCallableNames(FqName packageFqName) {
                packageFqName.getClass();
                Collection<PackagePartsCacheData> packageParts = this.getPackageParts(packageFqName);
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (PackagePartsCacheData packagePartsCacheData : packageParts) {
                    CollectionsKt.addAll(linkedHashSet, SetsKt.plus(packagePartsCacheData.getTopLevelFunctionNameIndex().keySet(), packagePartsCacheData.getTopLevelPropertyNameIndex().keySet()));
                }
                return linkedHashSet;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<Name> computeTopLevelClassifierNames(FqName packageFqName) {
                Set<Name> setEmptySet;
                packageFqName.getClass();
                Set<String> setKnownTopLevelClassesInPackage = this.knownTopLevelClassesInPackage(packageFqName);
                if (setKnownTopLevelClassesInPackage != null) {
                    Set<String> set = setKnownTopLevelClassesInPackage;
                    if (set.isEmpty()) {
                        setEmptySet = SetsKt.emptySet();
                    } else {
                        setEmptySet = new LinkedHashSet<>();
                        Iterator<T> it = set.iterator();
                        while (it.hasNext()) {
                            setEmptySet.add(Name.identifier((String) it.next()));
                        }
                    }
                    if (setEmptySet != null) {
                        Set packageNamesForNonClassDeclarations = this.getPackageNamesForNonClassDeclarations();
                        if (packageNamesForNonClassDeclarations == null || packageNamesForNonClassDeclarations.contains(packageFqName.asString())) {
                            Set set2 = (Set) this.typeAliasesNamesByPackage.getValue(packageFqName, null);
                            if (!set2.isEmpty()) {
                                Set setCreateSetBuilder = SetsKt.createSetBuilder();
                                setCreateSetBuilder.addAll(setEmptySet);
                                setCreateSetBuilder.addAll(set2);
                                return SetsKt.build(setCreateSetBuilder);
                            }
                        }
                        return setEmptySet;
                    }
                }
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificCallablePackageNamesComputation() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelCallables() {
                return this.getPackageNamesForNonClassDeclarations();
            }
        };
        this.typeAliasesNamesByPackage = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$special$$inlined$createCache$1
            public final Set<Name> invoke(FqName fqName, Void r2) {
                fqName.getClass();
                Collection packageParts = this.this$0.getPackageParts(fqName);
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator it = packageParts.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(linkedHashSet, ((PackagePartsCacheData) it.next()).getTypeAliasNameIndex().keySet());
                }
                return linkedHashSet;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
        this.packagePartsCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$special$$inlined$createCache$2
            public final List<? extends PackagePartsCacheData> invoke(FqName fqName, Void r2) {
                fqName.getClass();
                return this.$receiver$inlined.tryComputePackagePartInfos(fqName);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
        this.typeAliasCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCacheWithPostCompute(new AbstractFirDeserializedSymbolProvider$typeAliasCache$1(this), new Function3() { // from class: om
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AbstractFirDeserializedSymbolProvider.a((ClassId) obj, (FirTypeAliasSymbol) obj2, (Function1) obj3);
            }
        });
        this.classCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCacheWithPostCompute(new Function2() { // from class: pm
            public final Object invoke(Object obj, Object obj2) {
                return AbstractFirDeserializedSymbolProvider.d(this.b, (ClassId) obj, (FirDeserializationContext) obj2);
            }
        }, new Function3() { // from class: qm
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AbstractFirDeserializedSymbolProvider.c((ClassId) obj, (FirRegularClassSymbol) obj2, (Function1) obj3);
            }
        });
        this.functionCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$special$$inlined$createCache$3
            public final List<? extends FirNamedFunctionSymbol> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.$receiver$inlined.loadFunctionsByCallableId(callableId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((CallableId) obj, (Void) obj2);
            }
        });
        this.propertyCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider$special$$inlined$createCache$4
            public final List<? extends FirPropertySymbol> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.$receiver$inlined.loadPropertiesByCallableId(callableId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((CallableId) obj, (Void) obj2);
            }
        });
        this.kdocDeserializer = FirKDocDeserializerKt.getEffectiveKdocDeserializer(firSession);
    }

    public static Unit a(ClassId classId, FirTypeAliasSymbol firTypeAliasSymbol, Function1 function1) {
        classId.getClass();
        if (function1 != null && firTypeAliasSymbol != null) {
            function1.invoke(firTypeAliasSymbol);
        }
        return Unit.INSTANCE;
    }

    public static Unit c(ClassId classId, FirRegularClassSymbol firRegularClassSymbol, Function1 function1) {
        classId.getClass();
        if (function1 != null && firRegularClassSymbol != null) {
            function1.invoke(firRegularClassSymbol);
        }
        return Unit.INSTANCE;
    }

    public static Pair d(AbstractFirDeserializedSymbolProvider abstractFirDeserializedSymbolProvider, ClassId classId, FirDeserializationContext firDeserializationContext) {
        classId.getClass();
        return abstractFirDeserializedSymbolProvider.findAndDeserializeClass(classId, firDeserializationContext);
    }

    public static Set e(AbstractFirDeserializedSymbolProvider abstractFirDeserializedSymbolProvider) {
        return abstractFirDeserializedSymbolProvider.computePackageSetWithNonClassDeclarations();
    }

    public static /* synthetic */ ClassMetadataFindResult extractClassMetadata$default(AbstractFirDeserializedSymbolProvider abstractFirDeserializedSymbolProvider, ClassId classId, FirDeserializationContext firDeserializationContext, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: extractClassMetadata");
            return null;
        }
        if ((i & 2) != 0) {
            firDeserializationContext = null;
        }
        return abstractFirDeserializedSymbolProvider.extractClassMetadata(classId, firDeserializationContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Pair<FirRegularClassSymbol, Function1<FirRegularClassSymbol, Unit>> findAndDeserializeClass(ClassId classId, FirDeserializationContext parentContext) {
        ClassMetadataFindResult classMetadataFindResultExtractClassMetadata = extractClassMetadata(classId, parentContext);
        if (classMetadataFindResultExtractClassMetadata instanceof ClassMetadataFindResult.NoMetadata) {
            return TuplesKt.to(new FirRegularClassSymbol(classId), ((ClassMetadataFindResult.NoMetadata) classMetadataFindResultExtractClassMetadata).getClassPostProcessor());
        }
        if (!(classMetadataFindResultExtractClassMetadata instanceof ClassMetadataFindResult.Metadata)) {
            if (classMetadataFindResultExtractClassMetadata == null) {
                return TuplesKt.to(null, null);
            }
            bu8.a();
            return null;
        }
        ClassMetadataFindResult.Metadata metadata = (ClassMetadataFindResult.Metadata) classMetadataFindResultExtractClassMetadata;
        NameResolver nameResolver = metadata.getNameResolver();
        ProtoBuf.Class classProto = metadata.getClassProto();
        AnnotationDeserializer annotationDeserializer = metadata.getAnnotationDeserializer();
        FirModuleData moduleData = metadata.getModuleData();
        DeserializedContainerSource sourceElement = metadata.getSourceElement();
        if (moduleData == null) {
            return TuplesKt.to(null, null);
        }
        FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(classId);
        ClassDeserializationKt.deserializeClassToSymbol(classId, classProto, firRegularClassSymbol, nameResolver, getSession(), moduleData, annotationDeserializer, this.kdocDeserializer, metadata.getFlexibleTypeFactory(), this.kotlinScopeProvider, this.serializerExtensionProtocol, parentContext, sourceElement, this.defaultDeserializationOrigin, new AnonymousClass1(this), new AnonymousClass2(this));
        ClassMembersKt.setNewPlaceForBodyGeneration((FirRegularClass) firRegularClassSymbol.getFir(), Boolean.valueOf(isNewPlaceForBodyGeneration(classProto)));
        return TuplesKt.to(firRegularClassSymbol, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:28:0x0092  */
    /* JADX WARN: Code duplicated, block: B:30:0x0097 A[RETURN] */
    public final Pair<FirTypeAliasSymbol, Function1<FirTypeAliasSymbol, Unit>> findAndDeserializeTypeAlias(final ClassId classId, FirNestedTypeAliasDeserializationContext nestedTypeAliasContext) {
        Pair<FirTypeAliasSymbol, Function1<FirTypeAliasSymbol, Unit>> pair;
        if (nestedTypeAliasContext != null) {
            if (classId.isNestedClass()) {
                return TuplesKt.to(FirMemberDeserializer.loadTypeAlias$default(nestedTypeAliasContext.getMemberDeserializer(), nestedTypeAliasContext.getProto(), classId, nestedTypeAliasContext.getScopeProvider(), null, 8, null).getSymbol(), null);
            }
            w01.a("Failed requirement.");
            return null;
        }
        if (classId.isNestedClass()) {
            return TuplesKt.to(null, null);
        }
        for (final PackagePartsCacheData packagePartsCacheData : getPackageParts(classId.getPackageFqName())) {
            List<Integer> list = packagePartsCacheData.getTypeAliasNameIndex().get(classId.getShortClassName());
            if (list == null || list.isEmpty()) {
                pair = null;
            } else {
                final ProtoBuf.TypeAlias typeAlias = packagePartsCacheData.getProto().getTypeAlias(((Number) CollectionsKt.single(list)).intValue());
                pair = TuplesKt.to(new FirTypeAliasSymbol(classId), new Function1() { // from class: mm
                    public final Object invoke(Object obj) {
                        return AbstractFirDeserializedSymbolProvider.findAndDeserializeTypeAlias$lambda$0$0(packagePartsCacheData, typeAlias, classId, this, (FirTypeAliasSymbol) obj);
                    }
                });
            }
            if (pair != null) {
                if (pair == null) {
                    return TuplesKt.to(null, null);
                }
                return pair;
            }
        }
        pair = null;
        if (pair == null) {
            return TuplesKt.to(null, null);
        }
        return pair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v4, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static final Unit findAndDeserializeTypeAlias$lambda$0$0(PackagePartsCacheData packagePartsCacheData, ProtoBuf.TypeAlias typeAlias, ClassId classId, AbstractFirDeserializedSymbolProvider abstractFirDeserializedSymbolProvider, FirTypeAliasSymbol firTypeAliasSymbol) {
        firTypeAliasSymbol.getClass();
        FirMemberDeserializer memberDeserializer = packagePartsCacheData.getContext().getMemberDeserializer();
        typeAlias.getClass();
        memberDeserializer.loadTypeAlias(typeAlias, classId, abstractFirDeserializedSymbolProvider.kotlinScopeProvider, firTypeAliasSymbol);
        if (!packagePartsCacheData.getFileAnnotations().isEmpty()) {
            DeclarationAttributesKt.setKlibFileAnnotations(firTypeAliasSymbol.getFir(), packagePartsCacheData.getFileAnnotations());
        }
        return Unit.INSTANCE;
    }

    private final <C extends FirCallableSymbol<?>> List<C> getCallables(FirCache firCache, CallableId callableId) {
        return !getSymbolNamesProvider().mayHaveTopLevelCallable(callableId.getPackageName(), callableId.getCallableName()) ? CollectionsKt.emptyList() : (List) firCache.getValue(callableId, null);
    }

    public static /* synthetic */ FirRegularClassSymbol getClass$default(AbstractFirDeserializedSymbolProvider abstractFirDeserializedSymbolProvider, ClassId classId, FirDeserializationContext firDeserializationContext, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getClass");
            return null;
        }
        if ((i & 2) != 0) {
            firDeserializationContext = null;
        }
        return abstractFirDeserializedSymbolProvider.getClass(classId, firDeserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> getPackageNamesForNonClassDeclarations() {
        return (Set) this.packageNamesForNonClassDeclarations.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<PackagePartsCacheData> getPackageParts(FqName packageFqName) {
        return (Collection) this.packagePartsCache.getValue(packageFqName, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<FirNamedFunctionSymbol> loadFunctionsByCallableId(CallableId callableId) {
        List listEmptyList;
        Collection<PackagePartsCacheData> packageParts = getPackageParts(callableId.getPackageName());
        ArrayList arrayList = new ArrayList();
        for (PackagePartsCacheData packagePartsCacheData : packageParts) {
            List<Integer> list = packagePartsCacheData.getTopLevelFunctionNameIndex().get(callableId.getCallableName());
            if (list == null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                List<Integer> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    ProtoBuf.Function function = packagePartsCacheData.getProto().getFunction(iIntValue);
                    FirMemberDeserializer memberDeserializer = packagePartsCacheData.getContext().getMemberDeserializer();
                    ProtoBuf.Function function2 = packagePartsCacheData.getProto().getFunction(iIntValue);
                    function2.getClass();
                    FirNamedFunction firNamedFunctionLoadFunction$default = FirMemberDeserializer.loadFunction$default(memberDeserializer, function2, null, null, this.defaultDeserializationOrigin, 6, null);
                    function.getClass();
                    loadFunctionExtensions(packagePartsCacheData, function, firNamedFunctionLoadFunction$default);
                    if (!packagePartsCacheData.getFileAnnotations().isEmpty()) {
                        DeclarationAttributesKt.setKlibFileAnnotations(firNamedFunctionLoadFunction$default, packagePartsCacheData.getFileAnnotations());
                    }
                    arrayList2.add(firNamedFunctionLoadFunction$default.getSymbol());
                }
                listEmptyList = arrayList2;
            }
            CollectionsKt.addAll(arrayList, listEmptyList);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<FirPropertySymbol> loadPropertiesByCallableId(CallableId callableId) {
        List listEmptyList;
        Collection<PackagePartsCacheData> packageParts = getPackageParts(callableId.getPackageName());
        ArrayList arrayList = new ArrayList();
        for (PackagePartsCacheData packagePartsCacheData : packageParts) {
            List<Integer> list = packagePartsCacheData.getTopLevelPropertyNameIndex().get(callableId.getCallableName());
            if (list == null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                List<Integer> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    ProtoBuf.Property property = packagePartsCacheData.getProto().getProperty(((Number) it.next()).intValue());
                    FirMemberDeserializer memberDeserializer = packagePartsCacheData.getContext().getMemberDeserializer();
                    property.getClass();
                    FirProperty firPropertyLoadProperty$default = FirMemberDeserializer.loadProperty$default(memberDeserializer, property, null, null, 6, null);
                    loadPropertyExtensions(packagePartsCacheData, property, firPropertyLoadProperty$default);
                    if (!packagePartsCacheData.getFileAnnotations().isEmpty()) {
                        DeclarationAttributesKt.setKlibFileAnnotations(firPropertyLoadProperty$default, packagePartsCacheData.getFileAnnotations());
                    }
                    arrayList2.add(firPropertyLoadProperty$default.getSymbol());
                }
                listEmptyList = arrayList2;
            }
            CollectionsKt.addAll(arrayList, listEmptyList);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<PackagePartsCacheData> tryComputePackagePartInfos(FqName packageFqName) {
        return computePackagePartsInfos(packageFqName);
    }

    public abstract List<PackagePartsCacheData> computePackagePartsInfos(FqName packageFqName);

    public abstract Set<String> computePackageSetWithNonClassDeclarations();

    public abstract ClassMetadataFindResult extractClassMetadata(ClassId classId, FirDeserializationContext parentContext);

    public final FirRegularClassSymbol getClass(ClassId classId, FirDeserializationContext parentContext) {
        classId.getClass();
        ClassId outerClassId = classId.getOuterClassId();
        if (outerClassId == null && !getSymbolNamesProvider().mayHaveTopLevelClassifier(classId)) {
            return null;
        }
        if (outerClassId != null && !getSymbolNamesProvider().mayHaveTopLevelClassifier(classId.getOutermostClassId())) {
            return null;
        }
        if (parentContext == null && outerClassId != null) {
            FirRegularClassSymbol valueIfComputed = this.classCache.getValueIfComputed(classId);
            if (valueIfComputed != null) {
                return valueIfComputed;
            }
            getClass(outerClassId, null);
        }
        return this.classCache.getValue(classId, parentContext);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        FirTypeAliasSymbol typeAlias;
        classId.getClass();
        FirRegularClassSymbol class$default = getClass$default(this, classId, null, 2, null);
        return ((class$default == null || class$default.getRawStatus().isExpect()) && (typeAlias = getTypeAlias(classId, null)) != null) ? typeAlias : class$default;
    }

    public final FirDeclarationOrigin getDefaultDeserializationOrigin() {
        return this.defaultDeserializationOrigin;
    }

    public final FirKDocDeserializer getKdocDeserializer() {
        return this.kdocDeserializer;
    }

    public final FirKotlinScopeProvider getKotlinScopeProvider() {
        return this.kotlinScopeProvider;
    }

    public final ModuleDataProvider getModuleDataProvider() {
        return this.moduleDataProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        CallableId callableId = new CallableId(packageFqName, name);
        List<FirCallableSymbol<?>> list = destination;
        CollectionsKt.addAll(list, getCallables(this.functionCache, callableId));
        CollectionsKt.addAll(list, getCallables(this.propertyCache, callableId));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        CollectionsKt.addAll(destination, getCallables(this.functionCache, new CallableId(packageFqName, name)));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        CollectionsKt.addAll(destination, getCallables(this.propertyCache, new CallableId(packageFqName, name)));
    }

    public final FirTypeAliasSymbol getTypeAlias(ClassId classId, FirNestedTypeAliasDeserializationContext nestedTypeAliasContext) {
        classId.getClass();
        ClassId outerClassId = classId.getOuterClassId();
        if (nestedTypeAliasContext != null) {
            if (outerClassId != null) {
                return this.typeAliasCache.getValue(classId, nestedTypeAliasContext);
            }
            w01.a("Failed requirement.");
            return null;
        }
        if (outerClassId == null) {
            FqName packageFqName = classId.getPackageFqName();
            Set<String> packageNamesForNonClassDeclarations = getPackageNamesForNonClassDeclarations();
            if ((packageNamesForNonClassDeclarations != null && !packageNamesForNonClassDeclarations.contains(packageFqName.asString())) || !((Set) this.typeAliasesNamesByPackage.getValue(packageFqName, null)).contains(classId.getShortClassName())) {
                return null;
            }
        } else {
            FirTypeAliasSymbol valueIfComputed = this.typeAliasCache.getValueIfComputed(classId);
            if (valueIfComputed != null) {
                return valueIfComputed;
            }
            getClass(outerClassId, null);
        }
        return this.typeAliasCache.getValue(classId, nestedTypeAliasContext);
    }

    public abstract boolean isNewPlaceForBodyGeneration(ProtoBuf.Class classProto);

    public abstract Set<String> knownTopLevelClassesInPackage(FqName packageFqName);

    public void loadFunctionExtensions(PackagePartsCacheData packagePart, ProtoBuf.Function proto, FirFunction fir) {
        packagePart.getClass();
        proto.getClass();
        fir.getClass();
    }

    public void loadPropertyExtensions(PackagePartsCacheData packagePart, ProtoBuf.Property proto, FirProperty fir) {
        packagePart.getClass();
        proto.getClass();
        fir.getClass();
    }

    @kotlin.Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", Argument.Delimiters.none, "<init>", "()V", "NoMetadata", "Metadata", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult$Metadata;", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult$NoMetadata;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ClassMetadataFindResult {

        @kotlin.Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003JK\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0083\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult$Metadata;", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "annotationDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "sourceElement", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "<init>", "(Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;)V", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getClassProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "getAnnotationDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getSourceElement", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getFlexibleTypeFactory", "()Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class Metadata extends ClassMetadataFindResult {
            private final AnnotationDeserializer annotationDeserializer;
            private final ProtoBuf.Class classProto;
            private final FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory;
            private final FirModuleData moduleData;
            private final NameResolver nameResolver;
            private final DeserializedContainerSource sourceElement;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Metadata(NameResolver nameResolver, ProtoBuf.Class r3, AnnotationDeserializer annotationDeserializer, FirModuleData firModuleData, DeserializedContainerSource deserializedContainerSource, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory) {
                super(null);
                nameResolver.getClass();
                r3.getClass();
                flexibleTypeFactory.getClass();
                this.nameResolver = nameResolver;
                this.classProto = r3;
                this.annotationDeserializer = annotationDeserializer;
                this.moduleData = firModuleData;
                this.sourceElement = deserializedContainerSource;
                this.flexibleTypeFactory = flexibleTypeFactory;
            }

            public static /* synthetic */ Metadata copy$default(Metadata metadata, NameResolver nameResolver, ProtoBuf.Class r2, AnnotationDeserializer annotationDeserializer, FirModuleData firModuleData, DeserializedContainerSource deserializedContainerSource, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, int i, Object obj) {
                if ((i & 1) != 0) {
                    nameResolver = metadata.nameResolver;
                }
                if ((i & 2) != 0) {
                    r2 = metadata.classProto;
                }
                if ((i & 4) != 0) {
                    annotationDeserializer = metadata.annotationDeserializer;
                }
                if ((i & 8) != 0) {
                    firModuleData = metadata.moduleData;
                }
                if ((i & 16) != 0) {
                    deserializedContainerSource = metadata.sourceElement;
                }
                if ((i & 32) != 0) {
                    flexibleTypeFactory = metadata.flexibleTypeFactory;
                }
                DeserializedContainerSource deserializedContainerSource2 = deserializedContainerSource;
                FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory2 = flexibleTypeFactory;
                return metadata.copy(nameResolver, r2, annotationDeserializer, firModuleData, deserializedContainerSource2, flexibleTypeFactory2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final NameResolver getNameResolver() {
                return this.nameResolver;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final ProtoBuf.Class getClassProto() {
                return this.classProto;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final AnnotationDeserializer getAnnotationDeserializer() {
                return this.annotationDeserializer;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final FirModuleData getModuleData() {
                return this.moduleData;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final DeserializedContainerSource getSourceElement() {
                return this.sourceElement;
            }

            /* JADX INFO: renamed from: component6, reason: from getter */
            public final FirTypeDeserializer.FlexibleTypeFactory getFlexibleTypeFactory() {
                return this.flexibleTypeFactory;
            }

            public final Metadata copy(NameResolver nameResolver, ProtoBuf.Class classProto, AnnotationDeserializer annotationDeserializer, FirModuleData moduleData, DeserializedContainerSource sourceElement, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory) {
                nameResolver.getClass();
                classProto.getClass();
                flexibleTypeFactory.getClass();
                return new Metadata(nameResolver, classProto, annotationDeserializer, moduleData, sourceElement, flexibleTypeFactory);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Metadata)) {
                    return false;
                }
                Metadata metadata = (Metadata) other;
                return Intrinsics.areEqual(this.nameResolver, metadata.nameResolver) && Intrinsics.areEqual(this.classProto, metadata.classProto) && Intrinsics.areEqual(this.annotationDeserializer, metadata.annotationDeserializer) && Intrinsics.areEqual(this.moduleData, metadata.moduleData) && Intrinsics.areEqual(this.sourceElement, metadata.sourceElement) && Intrinsics.areEqual(this.flexibleTypeFactory, metadata.flexibleTypeFactory);
            }

            public final AnnotationDeserializer getAnnotationDeserializer() {
                return this.annotationDeserializer;
            }

            public final ProtoBuf.Class getClassProto() {
                return this.classProto;
            }

            public final FirTypeDeserializer.FlexibleTypeFactory getFlexibleTypeFactory() {
                return this.flexibleTypeFactory;
            }

            public final FirModuleData getModuleData() {
                return this.moduleData;
            }

            public final NameResolver getNameResolver() {
                return this.nameResolver;
            }

            public final DeserializedContainerSource getSourceElement() {
                return this.sourceElement;
            }

            public int hashCode() {
                int iHashCode = ((this.nameResolver.hashCode() * 31) + this.classProto.hashCode()) * 31;
                AnnotationDeserializer annotationDeserializer = this.annotationDeserializer;
                int iHashCode2 = (iHashCode + (annotationDeserializer == null ? 0 : annotationDeserializer.hashCode())) * 31;
                FirModuleData firModuleData = this.moduleData;
                int iHashCode3 = (iHashCode2 + (firModuleData == null ? 0 : firModuleData.hashCode())) * 31;
                DeserializedContainerSource deserializedContainerSource = this.sourceElement;
                return ((iHashCode3 + (deserializedContainerSource != null ? deserializedContainerSource.hashCode() : 0)) * 31) + this.flexibleTypeFactory.hashCode();
            }

            public String toString() {
                return "Metadata(nameResolver=" + this.nameResolver + ", classProto=" + this.classProto + ", annotationDeserializer=" + this.annotationDeserializer + ", moduleData=" + this.moduleData + ", sourceElement=" + this.sourceElement + ", flexibleTypeFactory=" + this.flexibleTypeFactory + ')';
            }
        }

        @kotlin.Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006HÆ\u0003J#\u0010\f\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult$NoMetadata;", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "classPostProcessor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/deserialization/DeserializedClassPostProcessor;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getClassPostProcessor", "()Lkotlin/jvm/functions/Function1;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class NoMetadata extends ClassMetadataFindResult {
            private final Function1<FirRegularClassSymbol, Unit> classPostProcessor;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public NoMetadata(Function1<? super FirRegularClassSymbol, Unit> function1) {
                super(null);
                function1.getClass();
                this.classPostProcessor = function1;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ NoMetadata copy$default(NoMetadata noMetadata, Function1 function1, int i, Object obj) {
                if ((i & 1) != 0) {
                    function1 = noMetadata.classPostProcessor;
                }
                return noMetadata.copy(function1);
            }

            public final Function1<FirRegularClassSymbol, Unit> component1() {
                return this.classPostProcessor;
            }

            public final NoMetadata copy(Function1<? super FirRegularClassSymbol, Unit> classPostProcessor) {
                classPostProcessor.getClass();
                return new NoMetadata(classPostProcessor);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NoMetadata) && Intrinsics.areEqual(this.classPostProcessor, ((NoMetadata) other).classPostProcessor);
            }

            public final Function1<FirRegularClassSymbol, Unit> getClassPostProcessor() {
                return this.classPostProcessor;
            }

            public int hashCode() {
                return this.classPostProcessor.hashCode();
            }

            public String toString() {
                return "NoMetadata(classPostProcessor=" + this.classPostProcessor + ')';
            }
        }

        public /* synthetic */ ClassMetadataFindResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ClassMetadataFindResult() {
        }
    }
}

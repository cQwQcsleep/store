package org.jetbrains.kotlin.fir.scopes.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.builtins.jvm.JvmBuiltInsSignatures;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.HiddenEverywhereBesideSuperCallsStatus;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.scopes.impl.FirStandardOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.jvm.JvmMappedScope;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.load.java.BuiltinSpecialProperties;
import org.jetbrains.kotlin.load.java.PropertiesConventionUtilKt;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.load.kotlin.SignatureBuildingComponents;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ^2\u00020\u0001:\u0003\\]^B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0001\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ$\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020+0.H\u0016J\u0010\u00100\u001a\u00020/2\u0006\u0010,\u001a\u00020&H\u0002J\u0010\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020/H\u0002J\u0018\u00103\u001a\u00020\u000b2\u0006\u00102\u001a\u00020/2\u0006\u00104\u001a\u000205H\u0002J\f\u00106\u001a\u00020\u000b*\u00020/H\u0002J\u0010\u00107\u001a\u00020\u000b2\u0006\u00104\u001a\u000205H\u0002J\u0010\u00108\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020&H\u0002J\u0010\u00109\u001a\u00020\u000b2\u0006\u00102\u001a\u00020/H\u0002J\f\u0010:\u001a\u00020\u000b*\u00020/H\u0002J\u0010\u0010;\u001a\u00020<2\u0006\u00104\u001a\u000205H\u0002J(\u0010=\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u0016\u0010-\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030>\u0012\u0004\u0012\u00020+0.H\u0016J\u0018\u0010?\u001a\u00020/2\u0006\u00102\u001a\u00020/2\u0006\u0010@\u001a\u00020<H\u0002J\u0014\u0010A\u001a\u00020+*\u00020B2\u0006\u0010@\u001a\u00020<H\u0002J*\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020/2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020D0FH\u0016J\u001c\u0010K\u001a\u00020+2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020+0.H\u0016J\f\u0010L\u001a\u00020\u000b*\u00020MH\u0002J\u0010\u0010N\u001a\u00020H2\u0006\u00102\u001a\u00020HH\u0002J*\u0010O\u001a\u00020D2\u0006\u0010P\u001a\u00020Q2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020D0FH\u0016J.\u0010R\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u001c\u0010-\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030S\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020+0FH\u0016J\u000e\u0010T\u001a\b\u0012\u0004\u0012\u00020&0%H\u0016J\u000e\u0010U\u001a\b\u0012\u0004\u0012\u00020&0%H\u0016J\n\u0010V\u001a\u000205H\u0096\u0080\u0004J\u001c\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020\u00032\u0006\u0010Y\u001a\u00020ZH\u0017b\u0002\b[R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001b\u001a\u0004\b \u0010!R\u000e\u0010#\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u001b\u001a\u0004\b'\u0010(R!\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\u001b\u001a\u0004\bI\u0010!¨\u0006_²\u0006\u0010\u0010`\u001a\b\u0012\u0004\u0012\u0002050%X\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "firKotlinClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "firJavaClass", "declaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "javaMappedClassUseSiteScope", "filterOutJvmPlatformDeclarations", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Z)V", "mappedSymbolCache", "Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage$MappedSymbolsCache;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirStandardOverrideChecker;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "kotlinDispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "declaredScopeOfMutableVersion", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getDeclaredScopeOfMutableVersion", "()Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "declaredScopeOfMutableVersion$delegate", "Lkotlin/Lazy;", "isMutableContainer", "allJavaMappedSuperClassIds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getAllJavaMappedSuperClassIds", "()Ljava/util/List;", "allJavaMappedSuperClassIds$delegate", "isList", "myCallableNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getMyCallableNames", "()Ljava/util/Set;", "myCallableNames$delegate", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "createHiddenFakeFunction", "isOverrideOfKotlinDeclaredFunction", "symbol", "isMutabilityViolation", "jvmDescriptor", Argument.Delimiters.none, "isOverrideOfKotlinBuiltinPropertyGetter", "isRenamedJdkMethod", "isTherePropertyWithNameInKotlinClass", "isDeclaredInBuiltinClass", "isDeclaredInMappedJavaClass", "getJdkMethodStatus", "Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$JDKMemberStatus;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "createMappedFunction", "jdkMemberStatus", "setHiddenAttributeIfNecessary", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lkotlin/Function2;", "firKotlinClassConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getFirKotlinClassConstructors", "firKotlinClassConstructors$delegate", "processDeclaredConstructors", "isDeprecated", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "createMappedConstructor", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processClassifiersByNameWithSubstitution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "getCallableNames", "getClassifierNames", "toString", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "JDKMemberStatus", "FirMappedSymbolStorage", "Companion", "org.jetbrains.kotlin:fir-jvm", "declaredSignatures"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmMappedScope extends FirTypeScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Name GET_FIRST_NAME;
    private static final Name GET_LAST_NAME;

    /* JADX INFO: renamed from: allJavaMappedSuperClassIds$delegate, reason: from kotlin metadata */
    private final Lazy allJavaMappedSuperClassIds;
    private final FirContainingNamesAwareScope declaredMemberScope;

    /* JADX INFO: renamed from: declaredScopeOfMutableVersion$delegate, reason: from kotlin metadata */
    private final Lazy declaredScopeOfMutableVersion;
    private final boolean filterOutJvmPlatformDeclarations;
    private final FirRegularClass firJavaClass;
    private final FirRegularClass firKotlinClass;

    /* JADX INFO: renamed from: firKotlinClassConstructors$delegate, reason: from kotlin metadata */
    private final Lazy firKotlinClassConstructors;
    private final boolean isList;
    private final boolean isMutableContainer;
    private final FirTypeScope javaMappedClassUseSiteScope;
    private final ConeClassLikeType kotlinDispatchReceiverType;
    private final FirMappedSymbolStorage.MappedSymbolsCache mappedSymbolCache;

    /* JADX INFO: renamed from: myCallableNames$delegate, reason: from kotlin metadata */
    private final Lazy myCallableNames;
    private final FirStandardOverrideChecker overrideChecker;
    private final FirSession session;
    private final ConeSubstitutor substitutor;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$JDKMemberStatus;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "HIDDEN", "VISIBLE", "DROP", "HIDDEN_IN_DECLARING_CLASS_ONLY", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum JDKMemberStatus {
        HIDDEN,
        VISIBLE,
        DROP,
        HIDDEN_IN_DECLARING_CLASS_ONLY;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<JDKMemberStatus> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.jvm.JvmMappedScope$isOverrideOfKotlinDeclaredFunction$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<FirNamedFunctionSymbol, Boolean> {
        public AnonymousClass1(Object obj) {
            super(1, obj, JvmMappedScope.class, "isDeclaredInBuiltinClass", "isDeclaredInBuiltinClass(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", 0);
        }

        public final Boolean invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
            firNamedFunctionSymbol.getClass();
            return Boolean.valueOf(((JvmMappedScope) ((CallableReference) this).receiver).isDeclaredInBuiltinClass(firNamedFunctionSymbol));
        }
    }

    static {
        Name nameIdentifier = Name.identifier("getFirst");
        nameIdentifier.getClass();
        GET_FIRST_NAME = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("getLast");
        nameIdentifier2.getClass();
        GET_LAST_NAME = nameIdentifier2;
    }

    public JvmMappedScope(FirSession firSession, FirRegularClass firRegularClass, FirRegularClass firRegularClass2, FirContainingNamesAwareScope firContainingNamesAwareScope, FirTypeScope firTypeScope, boolean z) {
        firSession.getClass();
        firRegularClass.getClass();
        firRegularClass2.getClass();
        firContainingNamesAwareScope.getClass();
        firTypeScope.getClass();
        this.session = firSession;
        this.firKotlinClass = firRegularClass;
        this.firJavaClass = firRegularClass2;
        this.declaredMemberScope = firContainingNamesAwareScope;
        this.javaMappedClassUseSiteScope = firTypeScope;
        this.filterOutJvmPlatformDeclarations = z;
        this.mappedSymbolCache = (FirMappedSymbolStorage.MappedSymbolsCache) JvmMappedScopeKt.getMappedSymbolStorage(firSession).getCacheByOwner().getValue(firRegularClass.getSymbol(), null);
        this.overrideChecker = new FirStandardOverrideChecker(firSession);
        this.substitutor = INSTANCE.createMappingSubstitutor(firRegularClass2, firRegularClass, firSession);
        this.kotlinDispatchReceiverType = ScopeUtilsKt.defaultType(firRegularClass);
        this.declaredScopeOfMutableVersion = LazyKt.lazy(new Function0() { // from class: yy7
            public final Object invoke() {
                return JvmMappedScope.f(this.b);
            }
        });
        this.isMutableContainer = JavaToKotlinClassMap.INSTANCE.isMutable(FirDeclarationUtilKt.getClassId(firRegularClass));
        this.allJavaMappedSuperClassIds = LazyKt.lazy(new Function0() { // from class: zy7
            public final Object invoke() {
                return JvmMappedScope.d(this.b);
            }
        });
        this.isList = Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firRegularClass), StandardClassIds.INSTANCE.getList());
        this.myCallableNames = LazyKt.lazy(new Function0() { // from class: az7
            public final Object invoke() {
                return JvmMappedScope.j(this.b);
            }
        });
        this.firKotlinClassConstructors = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: bz7
            public final Object invoke() {
                return JvmMappedScope.h(this.b);
            }
        });
    }

    public static boolean b(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        ClassId classId;
        firNamedFunctionSymbol.getClass();
        return (ClassMembersKt.isSubstitutionOrIntersectionOverride(firNamedFunctionSymbol) || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firNamedFunctionSymbol)) == null || (classId = coneClassLikeLookupTagContainingClassLookupTag.getClassId()) == null || !JavaToKotlinClassMap.INSTANCE.isMutable(classId)) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(JvmMappedScope jvmMappedScope, Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        FirConstructor firConstructor = (FirConstructor) firConstructorSymbol.getFir();
        if (!firConstructor.getStatus().getVisibility().getIsPublicAPI() || jvmMappedScope.isDeprecated(firConstructor)) {
            return Unit.INSTANCE;
        }
        if (JvmBuiltInsSignatures.INSTANCE.getVISIBLE_CONSTRUCTOR_SIGNATURES().contains(SignatureBuildingComponents.INSTANCE.signature(FirDeclarationUtilKt.getClassId(jvmMappedScope.firJavaClass), SignatureUtilsKt.computeJvmDescriptor$default(firConstructor, null, false, null, 7, null))) && !processDeclaredConstructors$lambda$0$isTrivialCopyConstructor(firConstructor, jvmMappedScope)) {
            List<FirConstructorSymbol> firKotlinClassConstructors = jvmMappedScope.getFirKotlinClassConstructors();
            if (!(firKotlinClassConstructors instanceof Collection) || !firKotlinClassConstructors.isEmpty()) {
                Iterator<T> it = firKotlinClassConstructors.iterator();
                while (it.hasNext()) {
                    if (processDeclaredConstructors$lambda$0$isShadowedBy(firConstructor, jvmMappedScope, (FirConstructorSymbol) it.next())) {
                        return Unit.INSTANCE;
                    }
                }
            }
            function1.invoke(jvmMappedScope.mappedSymbolCache.getMappedConstructors().getValue(firConstructorSymbol, jvmMappedScope));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirNamedFunctionSymbol createHiddenFakeFunction(Name name) {
        ConeKotlinType coneErrorType;
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setModuleData(this.firKotlinClass.getModuleData());
        firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.Synthetic.FakeHiddenInPreparationForNewJdk.INSTANCE);
        firNamedFunctionBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.OPEN, EffectiveVisibility.Public.INSTANCE));
        firNamedFunctionBuilder.setLocal(this.firKotlinClass.getIsLocal());
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) CollectionsKt.firstOrNull(this.firKotlinClass.getTypeParameters());
        if (firTypeParameterRef != null) {
            coneErrorType = new ConeTypeParameterTypeImpl(firTypeParameterRef.getSymbol().getLookupTag(), false, null, 4, null);
        } else {
            coneErrorType = new ConeErrorType(new ConeSimpleDiagnostic("No type parameter found on '" + this.firKotlinClass.getClassKind() + '\'', null, 2, null), false, null, null, null, null, null, 126, null);
        }
        firResolvedTypeRefBuilder.setConeType(coneErrorType);
        firNamedFunctionBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
        firNamedFunctionBuilder.setName(name);
        firNamedFunctionBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(this.firKotlinClass));
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(FirDeclarationUtilKt.getClassId(this.firKotlinClass), name)));
        firNamedFunctionBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        FirNamedFunction firNamedFunctionMo289build = firNamedFunctionBuilder.mo289build();
        DeprecationUtilsKt.setHiddenEverywhereBesideSuperCallsStatus(firNamedFunctionMo289build, HiddenEverywhereBesideSuperCallsStatus.HIDDEN_FAKE);
        return firNamedFunctionMo289build.getSymbol();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirConstructorSymbol createMappedConstructor(FirConstructorSymbol symbol) {
        FirConstructor firConstructor = (FirConstructor) symbol.getFir();
        ClassId classId = FirDeclarationUtilKt.getClassId(this.firKotlinClass);
        FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(new CallableId(classId, classId.getShortClassName()));
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        FirSession firSession = this.session;
        ConeClassLikeLookupTag lookupTag = this.firKotlinClass.getSymbol().getLookupTag();
        FirDeclarationOrigin origin = firConstructor.getOrigin();
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = this.substitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(firConstructor.getReturnTypeRef()));
        List<FirValueParameter> valueParameters = firConstructor.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(this.substitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef())));
        }
        firFakeOverrideGenerator.createCopyForFirConstructor(firConstructorSymbol, firSession, firConstructor, lookupTag, origin, null, coneKotlinTypeSubstituteOrSelf, arrayList, CollectionsKt.emptyList(), null, false, null, firConstructor.getSource());
        return firConstructorSymbol;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunctionSymbol createMappedFunction(FirNamedFunctionSymbol symbol, JDKMemberStatus jdkMemberStatus) {
        FirNamedFunction firNamedFunction = (FirNamedFunction) symbol.getFir();
        FirNamedFunctionSymbol firNamedFunctionSymbol = new FirNamedFunctionSymbol(new CallableId(FirDeclarationUtilKt.getClassId(this.firKotlinClass), symbol.getCallableId().getCallableName()));
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        ConeClassLikeLookupTag lookupTag = this.firKotlinClass.getSymbol().getLookupTag();
        FirSession firSession = this.session;
        FirDeclarationOrigin origin = firNamedFunction.getOrigin();
        ConeClassLikeType coneClassLikeType = this.kotlinDispatchReceiverType;
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(this.substitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef())));
        }
        setHiddenAttributeIfNecessary(FirFakeOverrideGenerator.createCopyForFirFunction$default(firFakeOverrideGenerator, firNamedFunctionSymbol, firNamedFunction, lookupTag, firSession, origin, false, coneClassLikeType, arrayList, null, null, null, this.substitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef())), null, null, null, firNamedFunction.getSource(), false, 30496, null), jdkMemberStatus);
        return firNamedFunctionSymbol;
    }

    public static List d(JvmMappedScope jvmMappedScope) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(FirDeclarationUtilKt.getClassId(jvmMappedScope.firJavaClass));
        List list = listCreateListBuilder;
        Iterator<T> it = SupertypeUtilsKt.lookupSuperTypes(jvmMappedScope.firJavaClass.getSymbol(), true, true, jvmMappedScope.session).iterator();
        while (it.hasNext()) {
            ClassId classId = ((ConeClassLikeType) it.next()).getLookupTag().getClassId();
            ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classId.asSingleFqName().toUnsafe());
            if (classIdMapKotlinToJava != null) {
                classId = classIdMapKotlinToJava;
            }
            list.add(classId);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Set e(List list, JvmMappedScope jvmMappedScope, Name name) {
        final Set setCreateSetBuilder = SetsKt.createSetBuilder();
        Set set = setCreateSetBuilder;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            set.add(SignatureUtilsKt.computeJvmDescriptor$default((FirFunction) ((FirNamedFunctionSymbol) it.next()).getFir(), null, false, null, 7, null));
        }
        FirScope declaredScopeOfMutableVersion = jvmMappedScope.getDeclaredScopeOfMutableVersion();
        if (declaredScopeOfMutableVersion != null) {
            declaredScopeOfMutableVersion.processFunctionsByName(name, new Function1() { // from class: ez7
                public final Object invoke(Object obj) {
                    return JvmMappedScope.processFunctionsByName$lambda$1$0$1(setCreateSetBuilder, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    public static FirContainingNamesAwareScope f(JvmMappedScope jvmMappedScope) {
        ClassId onlyToMutable = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(FirDeclarationUtilKt.getClassId(jvmMappedScope.firKotlinClass));
        if (onlyToMutable != null) {
            FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(jvmMappedScope.session).getClassLikeSymbolByClassId(onlyToMutable);
            FirClassSymbol firClassSymbol = classLikeSymbolByClassId instanceof FirClassSymbol ? (FirClassSymbol) classLikeSymbolByClassId : null;
            if (firClassSymbol != null) {
                return FirDeclaredMemberScopeProviderKt.declaredMemberScope(jvmMappedScope.session, (FirClassSymbol<?>) firClassSymbol, FirResolvePhase.TYPES);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit g(JvmMappedScope jvmMappedScope, Ref.BooleanRef booleanRef, Function1 function1, Lazy lazy, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        JDKMemberStatus jdkMethodStatus;
        Modality modality;
        firNamedFunctionSymbol.getClass();
        if (jvmMappedScope.isDeclaredInMappedJavaClass(firNamedFunctionSymbol)) {
            FirDeclarationStatus status = ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getStatus();
            status.getClass();
            if (((FirResolvedDeclarationStatus) status).getVisibility().getIsPublicAPI()) {
                String strComputeJvmDescriptor$default = SignatureUtilsKt.computeJvmDescriptor$default((FirFunction) firNamedFunctionSymbol.getFir(), null, false, null, 7, null);
                if (processFunctionsByName$lambda$2(lazy).contains(strComputeJvmDescriptor$default)) {
                    return Unit.INSTANCE;
                }
                if (jvmMappedScope.isRenamedJdkMethod(strComputeJvmDescriptor$default) || jvmMappedScope.isOverrideOfKotlinBuiltinPropertyGetter(firNamedFunctionSymbol)) {
                    return Unit.INSTANCE;
                }
                if (!jvmMappedScope.isOverrideOfKotlinDeclaredFunction(firNamedFunctionSymbol) && !jvmMappedScope.isMutabilityViolation(firNamedFunctionSymbol, strComputeJvmDescriptor$default) && (jdkMethodStatus = jvmMappedScope.getJdkMethodStatus(strComputeJvmDescriptor$default)) != JDKMemberStatus.DROP) {
                    if ((jdkMethodStatus == JDKMemberStatus.HIDDEN || jdkMethodStatus == JDKMemberStatus.HIDDEN_IN_DECLARING_CLASS_ONLY) && ((modality = jvmMappedScope.firKotlinClass.getStatus().getModality()) == null || modality == Modality.FINAL)) {
                        return Unit.INSTANCE;
                    }
                    FirNamedFunctionSymbol value = jvmMappedScope.mappedSymbolCache.getMappedFunctions().getValue(firNamedFunctionSymbol, TuplesKt.to(jvmMappedScope, jdkMethodStatus));
                    if (booleanRef.element) {
                        List<ClassId> allJavaMappedSuperClassIds = jvmMappedScope.getAllJavaMappedSuperClassIds();
                        if (!(allJavaMappedSuperClassIds instanceof Collection) || !allJavaMappedSuperClassIds.isEmpty()) {
                            Iterator<T> it = allJavaMappedSuperClassIds.iterator();
                            while (it.hasNext()) {
                                if (JvmBuiltInsSignatures.INSTANCE.getDEPRECATED_LIST_METHODS().contains(SignatureBuildingComponents.INSTANCE.signature((ClassId) it.next(), strComputeJvmDescriptor$default))) {
                                    booleanRef.element = false;
                                    break;
                                }
                            }
                        }
                    }
                    function1.invoke(value);
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    private final List<ClassId> getAllJavaMappedSuperClassIds() {
        return (List) this.allJavaMappedSuperClassIds.getValue();
    }

    private final FirScope getDeclaredScopeOfMutableVersion() {
        return (FirScope) this.declaredScopeOfMutableVersion.getValue();
    }

    private final List<FirConstructorSymbol> getFirKotlinClassConstructors() {
        return (List) this.firKotlinClassConstructors.getValue();
    }

    private final JDKMemberStatus getJdkMethodStatus(String jvmDescriptor) {
        Iterator<ClassId> it = getAllJavaMappedSuperClassIds().iterator();
        while (it.hasNext()) {
            String strSignature = SignatureBuildingComponents.INSTANCE.signature(it.next(), jvmDescriptor);
            JvmBuiltInsSignatures jvmBuiltInsSignatures = JvmBuiltInsSignatures.INSTANCE;
            if (jvmBuiltInsSignatures.getHIDDEN_METHOD_SIGNATURES().contains(strSignature)) {
                return JDKMemberStatus.HIDDEN;
            }
            if (jvmBuiltInsSignatures.getVISIBLE_METHOD_SIGNATURES().contains(strSignature)) {
                return JDKMemberStatus.VISIBLE;
            }
            if (jvmBuiltInsSignatures.getDROP_LIST_METHOD_SIGNATURES().contains(strSignature)) {
                return JDKMemberStatus.DROP;
            }
        }
        return JDKMemberStatus.HIDDEN_IN_DECLARING_CLASS_ONLY;
    }

    private final Set<Name> getMyCallableNames() {
        return (Set) this.myCallableNames.getValue();
    }

    public static List h(JvmMappedScope jvmMappedScope) {
        return DeclarationUtilsKt.constructors(jvmMappedScope.firKotlinClass, jvmMappedScope.session);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDeclaredInBuiltinClass(FirNamedFunctionSymbol symbol) {
        return symbol.getOrigin().isBuiltIns() || Intrinsics.areEqual(symbol.getOrigin(), FirDeclarationOrigin.Library.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isDeclaredInMappedJavaClass(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        return !ClassMembersKt.isSubstitutionOrIntersectionOverride((FirCallableDeclaration) firNamedFunctionSymbol.getFir()) && CallableIdUtilsKt.isRealOwnerOf(this.firJavaClass.getSymbol().getLookupTag(), ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getSymbol());
    }

    private final boolean isDeprecated(FirDeclaration firDeclaration) {
        return DeprecationUtilsKt.getDeprecation(firDeclaration.getSymbol(), this.session, null) != null;
    }

    private final boolean isMutabilityViolation(FirNamedFunctionSymbol symbol, String jvmDescriptor) {
        if (JvmBuiltInsSignatures.INSTANCE.getMUTABLE_METHOD_SIGNATURES().contains(SignatureBuildingComponents.INSTANCE.signature(FirDeclarationUtilKt.getClassId(this.firJavaClass), jvmDescriptor)) != this.isMutableContainer) {
            return true;
        }
        return FirTypeScopeKt.anyOverriddenOf(this.javaMappedClassUseSiteScope, symbol, new Function1() { // from class: dz7
            public final Object invoke(Object obj) {
                return Boolean.valueOf(JvmMappedScope.b((FirNamedFunctionSymbol) obj));
            }
        });
    }

    private final boolean isOverrideOfKotlinBuiltinPropertyGetter(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        FqName fqNameChild = FirDeclarationUtilKt.getClassId(this.firJavaClass).asSingleFqName().child(firNamedFunctionSymbol.getName());
        if (!firNamedFunctionSymbol.getValueParameterSymbols().isEmpty()) {
            return false;
        }
        if (BuiltinSpecialProperties.INSTANCE.getGETTER_FQ_NAMES().contains(fqNameChild)) {
            return true;
        }
        List propertyNamesCandidatesByAccessorName = PropertiesConventionUtilKt.getPropertyNamesCandidatesByAccessorName(firNamedFunctionSymbol.getName());
        if ((propertyNamesCandidatesByAccessorName instanceof Collection) && propertyNamesCandidatesByAccessorName.isEmpty()) {
            return false;
        }
        Iterator it = propertyNamesCandidatesByAccessorName.iterator();
        while (it.hasNext()) {
            if (isTherePropertyWithNameInKotlinClass((Name) it.next())) {
                return true;
            }
        }
        return false;
    }

    private final boolean isOverrideOfKotlinDeclaredFunction(FirNamedFunctionSymbol symbol) {
        return FirTypeScopeKt.anyOverriddenOf(this.javaMappedClassUseSiteScope, symbol, new AnonymousClass1(this));
    }

    private final boolean isRenamedJdkMethod(String jvmDescriptor) {
        return SpecialGenericSignatures.Companion.getJVM_SIGNATURES_FOR_RENAMED_BUILT_INS().contains(SignatureBuildingComponents.INSTANCE.signature(FirDeclarationUtilKt.getClassId(this.firJavaClass), jvmDescriptor));
    }

    private final boolean isTherePropertyWithNameInKotlinClass(Name name) {
        if (this.declaredMemberScope.getCallableNames().contains(name)) {
            return !FirScopeKt.getProperties(this.declaredMemberScope, name).isEmpty();
        }
        return false;
    }

    public static Set j(JvmMappedScope jvmMappedScope) {
        Set setPlus;
        Modality modality = jvmMappedScope.firKotlinClass.getStatus().getModality();
        if (modality == null || modality == Modality.FINAL) {
            String string = jvmMappedScope.firJavaClass.getSymbol().getClassId().toString();
            Set visible_method_signatures = JvmBuiltInsSignatures.INSTANCE.getVISIBLE_METHOD_SIGNATURES();
            ArrayList<String> arrayList = new ArrayList();
            for (Object obj : visible_method_signatures) {
                String str = (String) obj;
                if (JvmBuiltInsSignatures.INSTANCE.getMUTABLE_METHOD_SIGNATURES().contains(str) == jvmMappedScope.isMutableContainer && StringsKt.startsWith$default(str, string, false, 2, (Object) null)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (String str2 : arrayList) {
                arrayList2.add(Name.identifier(str2.substring(string.length() + 1, StringsKt.indexOf$default(str2, "(", 0, false, 6, (Object) null))));
            }
            setPlus = SetsKt.plus(jvmMappedScope.declaredMemberScope.getCallableNames(), arrayList2);
        } else {
            setPlus = SetsKt.plus(jvmMappedScope.declaredMemberScope.getCallableNames(), jvmMappedScope.javaMappedClassUseSiteScope.getCallableNames());
        }
        if (jvmMappedScope.isList) {
            Name name = GET_FIRST_NAME;
            if (!setPlus.contains(name) || !setPlus.contains(GET_LAST_NAME)) {
                return SetsKt.plus(setPlus, CollectionsKt.listOf(new Name[]{name, GET_LAST_NAME}));
            }
        }
        return setPlus;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit k(JvmMappedScope jvmMappedScope, List list, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!jvmMappedScope.filterOutJvmPlatformDeclarations || FirJvmPlatformDeclarationFilter.INSTANCE.isFunctionAvailable((FirNamedFunction) firNamedFunctionSymbol.getFir(), jvmMappedScope.javaMappedClassUseSiteScope, jvmMappedScope.session)) {
            list.add(firNamedFunctionSymbol);
            function1.invoke(firNamedFunctionSymbol);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean processDeclaredConstructors$lambda$0$isShadowedBy(FirConstructor firConstructor, JvmMappedScope jvmMappedScope, FirConstructorSymbol firConstructorSymbol) {
        ConeSubstitutor coneSubstitutorBuildSubstitutorForOverridesCheck;
        List<FirValueParameter> valueParameters = firConstructor.getValueParameters();
        List<FirValueParameter> valueParameters2 = ((FirConstructor) firConstructorSymbol.getFir()).getValueParameters();
        if (valueParameters.size() != valueParameters2.size() || (coneSubstitutorBuildSubstitutorForOverridesCheck = FirAbstractOverrideCheckerKt.buildSubstitutorForOverridesCheck((FirCallableDeclaration) firConstructorSymbol.getFir(), firConstructor, jvmMappedScope.session)) == null) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(valueParameters2, valueParameters);
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            return true;
        }
        for (Pair pair : listZip) {
            if (!jvmMappedScope.overrideChecker.isEqualTypes(((FirValueParameter) pair.component1()).getReturnTypeRef(), ((FirValueParameter) pair.component2()).getReturnTypeRef(), coneSubstitutorBuildSubstitutorForOverridesCheck)) {
                return false;
            }
        }
        return true;
    }

    private static final boolean processDeclaredConstructors$lambda$0$isTrivialCopyConstructor(FirConstructor firConstructor, JvmMappedScope jvmMappedScope) {
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.singleOrNull(firConstructor.getValueParameters());
        if (firValueParameter != null) {
            return Intrinsics.areEqual(ConeTypeUtilsKt.getClassLikeLookupTagIfAny(ConeTypeUtilsKt.lowerBoundIfFlexible(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()))), jvmMappedScope.firKotlinClass.getSymbol().getLookupTag());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit processFunctionsByName$lambda$1$0$1(Set set, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        set.add(SignatureUtilsKt.computeJvmDescriptor$default((FirFunction) firNamedFunctionSymbol.getFir(), null, false, null, 7, null));
        return Unit.INSTANCE;
    }

    private static final Set<String> processFunctionsByName$lambda$2(Lazy<? extends Set<String>> lazy) {
        return (Set) lazy.getValue();
    }

    private final void setHiddenAttributeIfNecessary(FirCallableDeclaration firCallableDeclaration, JDKMemberStatus jDKMemberStatus) {
        if (jDKMemberStatus == JDKMemberStatus.HIDDEN) {
            DeprecationUtilsKt.setHiddenEverywhereBesideSuperCallsStatus(firCallableDeclaration, HiddenEverywhereBesideSuperCallsStatus.HIDDEN);
        } else if (jDKMemberStatus == JDKMemberStatus.HIDDEN_IN_DECLARING_CLASS_ONLY) {
            DeprecationUtilsKt.setHiddenEverywhereBesideSuperCallsStatus(firCallableDeclaration, HiddenEverywhereBesideSuperCallsStatus.HIDDEN_IN_DECLARING_CLASS_ONLY);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return getMyCallableNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return this.declaredMemberScope.getClassifierNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processClassifiersByNameWithSubstitution(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.javaMappedClassUseSiteScope.processDeclaredConstructors(new Function1() { // from class: cz7
            public final Object invoke(Object obj) {
                return JvmMappedScope.c(this.b, processor, (FirConstructorSymbol) obj);
            }
        });
        this.declaredMemberScope.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return ProcessorAction.NONE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return ProcessorAction.NONE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(final Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        final ArrayList arrayList = new ArrayList();
        this.declaredMemberScope.processFunctionsByName(name, new Function1() { // from class: fz7
            public final Object invoke(Object obj) {
                return JvmMappedScope.k(this.b, arrayList, processor, (FirNamedFunctionSymbol) obj);
            }
        });
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: gz7
            public final Object invoke() {
                return JvmMappedScope.e(arrayList, this, name);
            }
        });
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = this.isList && (Intrinsics.areEqual(name, GET_FIRST_NAME) || Intrinsics.areEqual(name, GET_LAST_NAME));
        this.javaMappedClassUseSiteScope.processFunctionsByName(name, new Function1() { // from class: hz7
            public final Object invoke(Object obj) {
                return JvmMappedScope.g(this.b, booleanRef, processor, lazy, (FirNamedFunctionSymbol) obj);
            }
        });
        if (booleanRef.element) {
            processor.invoke(this.mappedSymbolCache.getHiddenFakeFunctions().getValue(name, this));
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processPropertiesByName(name, processor);
    }

    public String toString() {
        return "JVM mapped scope for " + FirDeclarationUtilKt.getClassId(this.firKotlinClass);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public JvmMappedScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirRegularClass firRegularClass = this.firKotlinClass;
        FirRegularClass firRegularClass2 = this.firJavaClass;
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope;
        }
        FirContainingNamesAwareScope firContainingNamesAwareScope = firContainingNamesAwareScopeWithReplacedSessionOrNull;
        FirTypeScope firTypeScopeWithReplacedSessionOrNull = this.javaMappedClassUseSiteScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firTypeScopeWithReplacedSessionOrNull == null) {
            firTypeScopeWithReplacedSessionOrNull = this.javaMappedClassUseSiteScope;
        }
        return new JvmMappedScope(newSession, firRegularClass, firRegularClass2, firContainingNamesAwareScope, firTypeScopeWithReplacedSessionOrNull, this.filterOutJvmPlatformDeclarations);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$Companion;", Argument.Delimiters.none, "<init>", "()V", "createMappingSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "fromClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "toClass", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "GET_FIRST_NAME", "Lorg/jetbrains/kotlin/name/Name;", "GET_LAST_NAME", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ConeSubstitutor createMappingSubstitutor(FirRegularClass fromClass, FirRegularClass toClass, FirSession session) {
            List<Pair> listZip = CollectionsKt.zip(fromClass.getTypeParameters(), toClass.getTypeParameters());
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listZip, 10)), 16));
            for (Pair pair : listZip) {
                Pair pair2 = TuplesKt.to(((FirTypeParameterRef) pair.component1()).getSymbol(), new ConeTypeParameterTypeImpl(new ConeTypeParameterLookupTag(((FirTypeParameterRef) pair.component2()).getSymbol()), false, null, 4, null));
                linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
            }
            return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, session, false, 4, null);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "cacheByOwner", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage$MappedSymbolsCache;", Argument.Delimiters.none, "getCacheByOwner$org_jetbrains_kotlin_fir_jvm", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "MappedSymbolsCache", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirMappedSymbolStorage implements FirSessionComponent {
        private final FirCache cacheByOwner;
        private final FirCachesFactory cachesFactory;

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R/\u0010\u0006\u001a \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR#\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR#\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage$MappedSymbolsCache;", Argument.Delimiters.none, "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "mappedFunctions", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope;", "Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$JDKMemberStatus;", "getMappedFunctions", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "hiddenFakeFunctions", "Lorg/jetbrains/kotlin/name/Name;", "getHiddenFakeFunctions", "mappedConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getMappedConstructors", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class MappedSymbolsCache {
            private final FirCache<Name, FirNamedFunctionSymbol, JvmMappedScope> hiddenFakeFunctions;
            private final FirCache<FirConstructorSymbol, FirConstructorSymbol, JvmMappedScope> mappedConstructors;
            private final FirCache<FirNamedFunctionSymbol, FirNamedFunctionSymbol, Pair<JvmMappedScope, ? extends JDKMemberStatus>> mappedFunctions;

            public MappedSymbolsCache(FirCachesFactory firCachesFactory) {
                firCachesFactory.getClass();
                this.mappedFunctions = firCachesFactory.createCache(new Function2() { // from class: iz7
                    public final Object invoke(Object obj, Object obj2) {
                        return JvmMappedScope.FirMappedSymbolStorage.MappedSymbolsCache.c((FirNamedFunctionSymbol) obj, (Pair) obj2);
                    }
                });
                this.hiddenFakeFunctions = firCachesFactory.createCache(new Function2() { // from class: jz7
                    public final Object invoke(Object obj, Object obj2) {
                        return JvmMappedScope.FirMappedSymbolStorage.MappedSymbolsCache.b((Name) obj, (JvmMappedScope) obj2);
                    }
                });
                this.mappedConstructors = firCachesFactory.createCache(new Function2() { // from class: kz7
                    public final Object invoke(Object obj, Object obj2) {
                        return JvmMappedScope.FirMappedSymbolStorage.MappedSymbolsCache.a((FirConstructorSymbol) obj, (JvmMappedScope) obj2);
                    }
                });
            }

            public static FirConstructorSymbol a(FirConstructorSymbol firConstructorSymbol, JvmMappedScope jvmMappedScope) {
                firConstructorSymbol.getClass();
                jvmMappedScope.getClass();
                return jvmMappedScope.createMappedConstructor(firConstructorSymbol);
            }

            public static FirNamedFunctionSymbol b(Name name, JvmMappedScope jvmMappedScope) {
                name.getClass();
                jvmMappedScope.getClass();
                return jvmMappedScope.createHiddenFakeFunction(name);
            }

            public static FirNamedFunctionSymbol c(FirNamedFunctionSymbol firNamedFunctionSymbol, Pair pair) {
                firNamedFunctionSymbol.getClass();
                pair.getClass();
                return ((JvmMappedScope) pair.component1()).createMappedFunction(firNamedFunctionSymbol, (JDKMemberStatus) pair.component2());
            }

            public final FirCache<Name, FirNamedFunctionSymbol, JvmMappedScope> getHiddenFakeFunctions() {
                return this.hiddenFakeFunctions;
            }

            public final FirCache<FirConstructorSymbol, FirConstructorSymbol, JvmMappedScope> getMappedConstructors() {
                return this.mappedConstructors;
            }

            public final FirCache<FirNamedFunctionSymbol, FirNamedFunctionSymbol, Pair<JvmMappedScope, ? extends JDKMemberStatus>> getMappedFunctions() {
                return this.mappedFunctions;
            }
        }

        public FirMappedSymbolStorage(FirCachesFactory firCachesFactory) {
            firCachesFactory.getClass();
            this.cachesFactory = firCachesFactory;
            this.cacheByOwner = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.jvm.JvmMappedScope$FirMappedSymbolStorage$special$$inlined$createCache$1
                public final JvmMappedScope.FirMappedSymbolStorage.MappedSymbolsCache invoke(FirRegularClassSymbol firRegularClassSymbol, Void r2) {
                    firRegularClassSymbol.getClass();
                    return new JvmMappedScope.FirMappedSymbolStorage.MappedSymbolsCache(this.this$0.cachesFactory);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((FirRegularClassSymbol) obj, (Void) obj2);
                }
            });
        }

        /* JADX INFO: renamed from: getCacheByOwner$org_jetbrains_kotlin_fir_jvm, reason: from getter */
        public final FirCache getCacheByOwner() {
            return this.cacheByOwner;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public FirMappedSymbolStorage(FirSession firSession) {
            this(FirCachesFactoryKt.getFirCachesFactory(firSession));
            firSession.getClass();
        }
    }
}

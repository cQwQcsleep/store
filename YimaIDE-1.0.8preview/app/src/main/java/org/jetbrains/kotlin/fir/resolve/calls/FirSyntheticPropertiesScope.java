package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.CallToPotentiallyHiddenSymbolResult;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilderKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticPropertiesScope;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirUnstableSmartcastTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 82\u00020\u0001:\u000389:B;\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0012\u0004\u0012\u00020\u00130\u0017H\u0016J\b\u0010\u0019\u001a\u00020\rH\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u001bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001bH\u0016J@\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\r2\u0016\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0012\u0004\u0012\u00020\u00130\u0017H\u0002J2\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\rH\u0002J(\u0010+\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020\u0007H\u0002J\f\u0010.\u001a\u00020/*\u00020!H\u0002J\u0012\u00100\u001a\u00020\r2\b\u00101\u001a\u0004\u0018\u000102H\u0002J\u001e\u00103\u001a\u0004\u0018\u00010\u00002\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u000206H\u0017b\u0002\b7R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "baseScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "syntheticNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticNamesProvider;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "isSuperCall", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticNamesProvider;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "processPropertiesByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "shouldSearchForJavaRecordComponents", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "checkGetAndCreateSynthetic", "propertyName", "getterName", "getterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "needCheckForSetter", "buildSyntheticProperty", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "setter", "getterCompatibility", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$SyntheticGetterCompatibility;", "deprecatedOverrideOfHidden", "setterTypeIsConsistentWithGetterType", "setterSymbol", "setterParameterType", "computeGetterCompatibility", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$GetterCompatibilityResult;", "isJavaTypeOnThePath", "baseType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "Companion", "SyntheticGetterCompatibility", "GetterCompatibilityResult", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticPropertiesScope extends FirContainingNamesAwareScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirTypeScope baseScope;
    private final ConeKotlinType dispatchReceiverType;
    private final boolean isSuperCall;
    private final ReturnTypeCalculator returnTypeCalculator;
    private final FirSession session;
    private final FirSyntheticNamesProvider syntheticNamesProvider;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$GetterCompatibilityResult;", Argument.Delimiters.none, "compatibility", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$SyntheticGetterCompatibility;", "deprecatedOverrideOfHidden", Argument.Delimiters.none, "originalJavaGetter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$SyntheticGetterCompatibility;ZLorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "getCompatibility", "()Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$SyntheticGetterCompatibility;", "getDeprecatedOverrideOfHidden", "()Z", "getOriginalJavaGetter", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class GetterCompatibilityResult {
        private final SyntheticGetterCompatibility compatibility;
        private final boolean deprecatedOverrideOfHidden;
        private final FirNamedFunctionSymbol originalJavaGetter;

        public GetterCompatibilityResult(SyntheticGetterCompatibility syntheticGetterCompatibility, boolean z, FirNamedFunctionSymbol firNamedFunctionSymbol) {
            syntheticGetterCompatibility.getClass();
            this.compatibility = syntheticGetterCompatibility;
            this.deprecatedOverrideOfHidden = z;
            this.originalJavaGetter = firNamedFunctionSymbol;
        }

        public static /* synthetic */ GetterCompatibilityResult copy$default(GetterCompatibilityResult getterCompatibilityResult, SyntheticGetterCompatibility syntheticGetterCompatibility, boolean z, FirNamedFunctionSymbol firNamedFunctionSymbol, int i, Object obj) {
            if ((i & 1) != 0) {
                syntheticGetterCompatibility = getterCompatibilityResult.compatibility;
            }
            if ((i & 2) != 0) {
                z = getterCompatibilityResult.deprecatedOverrideOfHidden;
            }
            if ((i & 4) != 0) {
                firNamedFunctionSymbol = getterCompatibilityResult.originalJavaGetter;
            }
            return getterCompatibilityResult.copy(syntheticGetterCompatibility, z, firNamedFunctionSymbol);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SyntheticGetterCompatibility getCompatibility() {
            return this.compatibility;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getDeprecatedOverrideOfHidden() {
            return this.deprecatedOverrideOfHidden;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final FirNamedFunctionSymbol getOriginalJavaGetter() {
            return this.originalJavaGetter;
        }

        public final GetterCompatibilityResult copy(SyntheticGetterCompatibility compatibility, boolean deprecatedOverrideOfHidden, FirNamedFunctionSymbol originalJavaGetter) {
            compatibility.getClass();
            return new GetterCompatibilityResult(compatibility, deprecatedOverrideOfHidden, originalJavaGetter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GetterCompatibilityResult)) {
                return false;
            }
            GetterCompatibilityResult getterCompatibilityResult = (GetterCompatibilityResult) other;
            return this.compatibility == getterCompatibilityResult.compatibility && this.deprecatedOverrideOfHidden == getterCompatibilityResult.deprecatedOverrideOfHidden && Intrinsics.areEqual(this.originalJavaGetter, getterCompatibilityResult.originalJavaGetter);
        }

        public final SyntheticGetterCompatibility getCompatibility() {
            return this.compatibility;
        }

        public final boolean getDeprecatedOverrideOfHidden() {
            return this.deprecatedOverrideOfHidden;
        }

        public final FirNamedFunctionSymbol getOriginalJavaGetter() {
            return this.originalJavaGetter;
        }

        public int hashCode() {
            int iHashCode = ((this.compatibility.hashCode() * 31) + Boolean.hashCode(this.deprecatedOverrideOfHidden)) * 31;
            FirNamedFunctionSymbol firNamedFunctionSymbol = this.originalJavaGetter;
            return iHashCode + (firNamedFunctionSymbol == null ? 0 : firNamedFunctionSymbol.hashCode());
        }

        public String toString() {
            return "GetterCompatibilityResult(compatibility=" + this.compatibility + ", deprecatedOverrideOfHidden=" + this.deprecatedOverrideOfHidden + ", originalJavaGetter=" + this.originalJavaGetter + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$SyntheticGetterCompatibility;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Incompatible", "HasKotlinOrigin", "HasJavaOrigin", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum SyntheticGetterCompatibility {
        Incompatible,
        HasKotlinOrigin,
        HasJavaOrigin;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<SyntheticGetterCompatibility> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CallToPotentiallyHiddenSymbolResult.values().length];
            try {
                iArr[CallToPotentiallyHiddenSymbolResult.Hidden.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CallToPotentiallyHiddenSymbolResult.VisibleWithDeprecation.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CallToPotentiallyHiddenSymbolResult.Visible.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirSyntheticPropertiesScope(FirSession firSession, FirTypeScope firTypeScope, ConeKotlinType coneKotlinType, FirSyntheticNamesProvider firSyntheticNamesProvider, ReturnTypeCalculator returnTypeCalculator, boolean z) {
        this.session = firSession;
        this.baseScope = firTypeScope;
        this.dispatchReceiverType = coneKotlinType;
        this.syntheticNamesProvider = firSyntheticNamesProvider;
        this.returnTypeCalculator = returnTypeCalculator;
        this.isSuperCall = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(FirSyntheticPropertiesScope firSyntheticPropertiesScope, Name name, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (Intrinsics.areEqual(ClassMembersKt.isJavaRecordComponent((FirFunction) firNamedFunctionSymbol.getFir()), Boolean.TRUE)) {
            firSyntheticPropertiesScope.checkGetAndCreateSynthetic(name, name, firNamedFunctionSymbol, false, function1);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirSyntheticProperty buildSyntheticProperty(final Name propertyName, final FirNamedFunction getter, final FirNamedFunction setter, SyntheticGetterCompatibility getterCompatibility, boolean deprecatedOverrideOfHidden) {
        FqName relativeClassName;
        FqName packageName;
        ClassId classId;
        ClassId classId2;
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) getter.getSymbol().getFir();
        while (true) {
            relativeClassName = null;
            if (!ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                break;
            }
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return null;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull((FirNamedFunctionSymbol) symbol);
        if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull == null || (classId2 = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId()) == null || (packageName = classId2.getPackageFqName()) == null) {
            packageName = getter.getSymbol().getCallableId().getPackageName();
        }
        final FqName fqName = packageName;
        if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null && (classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId()) != null) {
            relativeClassName = classId.getRelativeClassName();
        }
        final FqName fqName2 = relativeClassName;
        FirSyntheticProperty firSyntheticPropertyBuildSyntheticProperty = FirSyntheticPropertyBuilderKt.buildSyntheticProperty(new Function1() { // from class: cf5
            public final Object invoke(Object obj) {
                return FirSyntheticPropertiesScope.d(this.b, propertyName, getter, fqName, fqName2, setter, (FirSyntheticPropertyBuilder) obj);
            }
        });
        if (getterCompatibility != SyntheticGetterCompatibility.HasJavaOrigin) {
            SyntheticsKt.setNoJavaOrigin(firSyntheticPropertyBuildSyntheticProperty, Boolean.TRUE);
        }
        if (deprecatedOverrideOfHidden) {
            SyntheticsKt.setDeprecatedOverrideOfHidden(firSyntheticPropertyBuildSyntheticProperty, Boolean.TRUE);
        }
        return firSyntheticPropertyBuildSyntheticProperty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(Ref.ObjectRef objectRef, FirSyntheticPropertiesScope firSyntheticPropertiesScope, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        ConeKotlinType coneType;
        if (objectRef.element != null) {
            return Unit.INSTANCE;
        }
        FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol2.getFir();
        if (!firNamedFunction.getTypeParameters().isEmpty() || firNamedFunction.getStatus().isStatic()) {
            return Unit.INSTANCE;
        }
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.singleOrNull(firNamedFunction.getValueParameters());
        if (firValueParameter != null && !firValueParameter.getIsVararg()) {
            FirResolvedTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            if (firResolvedTypeRef == null || (coneType = firResolvedTypeRef.getConeType()) == null) {
                return Unit.INSTANCE;
            }
            if (!firSyntheticPropertiesScope.setterTypeIsConsistentWithGetterType(name, firNamedFunctionSymbol, firNamedFunctionSymbol2, coneType)) {
                return Unit.INSTANCE;
            }
            objectRef.element = firNamedFunctionSymbol2.getFir();
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:73:0x00fc  */
    /* JADX WARN: Multi-variable type inference failed */
    private final void checkGetAndCreateSynthetic(final Name propertyName, Name getterName, final FirNamedFunctionSymbol getterSymbol, boolean needCheckForSetter, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        ConeKotlinType coneType;
        FirNamedFunction firNamedFunction;
        FirNamedFunction firNamedFunction2;
        FirTypeRef returnTypeRef;
        ReturnTypeCalculator returnTypeCalculator;
        FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnTypeOrNull;
        FirNamedFunction firNamedFunction3 = (FirNamedFunction) getterSymbol.getFir();
        if (firNamedFunction3.getTypeParameters().isEmpty() && firNamedFunction3.getValueParameters().isEmpty() && !firNamedFunction3.getStatus().isStatic()) {
            GetterCompatibilityResult getterCompatibilityResultComputeGetterCompatibility = computeGetterCompatibility(getterSymbol);
            SyntheticGetterCompatibility compatibility = getterCompatibilityResultComputeGetterCompatibility.getCompatibility();
            boolean deprecatedOverrideOfHidden = getterCompatibilityResultComputeGetterCompatibility.getDeprecatedOverrideOfHidden();
            FirNamedFunctionSymbol originalJavaGetter = getterCompatibilityResultComputeGetterCompatibility.getOriginalJavaGetter();
            if (compatibility == SyntheticGetterCompatibility.Incompatible) {
                return;
            }
            FirResolvedTypeRef returnTypeRef2 = firNamedFunction3.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef2 instanceof FirResolvedTypeRef ? returnTypeRef2 : null;
            ConeKotlinType coneType2 = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType2 == null && needCheckForSetter && ((returnTypeCalculator = this.returnTypeCalculator) == null || (firResolvedTypeRefTryCalculateReturnTypeOrNull = returnTypeCalculator.tryCalculateReturnTypeOrNull(firNamedFunction3)) == null || (coneType2 = firResolvedTypeRefTryCalculateReturnTypeOrNull.getConeType()) == null || (coneType2 instanceof ConeErrorType))) {
                coneType2 = null;
            }
            if (originalJavaGetter == null || (firNamedFunction2 = (FirNamedFunction) originalJavaGetter.getFir()) == null || (returnTypeRef = firNamedFunction2.getReturnTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) == null) {
                coneType = coneType2;
            }
            if (coneType == null || !ConeBuiltinTypeUtilsKt.isUnit(coneType) || CompilerConeAttributesKt.getHasEnhancedNullability(coneType)) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (needCheckForSetter && coneType2 != null) {
                    this.baseScope.processFunctionsByName(this.syntheticNamesProvider.setterNameByGetterName(getterName), new Function1() { // from class: ef5
                        public final Object invoke(Object obj) {
                            return FirSyntheticPropertiesScope.c(objectRef, this, propertyName, getterSymbol, (FirNamedFunctionSymbol) obj);
                        }
                    });
                }
                FirSyntheticProperty firSyntheticPropertyBuildSyntheticProperty = buildSyntheticProperty(propertyName, firNamedFunction3, (FirNamedFunction) objectRef.element, compatibility, deprecatedOverrideOfHidden);
                FirNamedFunction firNamedFunction4 = (FirNamedFunction) ((ClassMembersKt.isSubstitutionOverride(firNamedFunction3) || (firNamedFunction3.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firNamedFunction3) : null);
                if (firNamedFunction4 != null) {
                    FirNamedFunction firNamedFunction5 = (FirNamedFunction) objectRef.element;
                    if (firNamedFunction5 == null) {
                        firNamedFunction = (FirNamedFunction) objectRef.element;
                    } else {
                        firNamedFunction = (FirNamedFunction) ((ClassMembersKt.isSubstitutionOverride(firNamedFunction5) || (firNamedFunction5.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firNamedFunction5) : null);
                        if (firNamedFunction == null) {
                            firNamedFunction = (FirNamedFunction) objectRef.element;
                        }
                    }
                    ClassMembersKt.setOriginalForSubstitutionOverrideAttr(firSyntheticPropertyBuildSyntheticProperty, buildSyntheticProperty(propertyName, firNamedFunction4, firNamedFunction, compatibility, deprecatedOverrideOfHidden));
                }
                FirSyntheticPropertySymbol symbol = firSyntheticPropertyBuildSyntheticProperty.getSymbol();
                FirTypeScope firTypeScope = this.baseScope;
                FirUnstableSmartcastTypeScope firUnstableSmartcastTypeScope = firTypeScope instanceof FirUnstableSmartcastTypeScope ? (FirUnstableSmartcastTypeScope) firTypeScope : null;
                if (firUnstableSmartcastTypeScope != null && firUnstableSmartcastTypeScope.isSymbolFromUnstableSmartcast(getterSymbol)) {
                    firUnstableSmartcastTypeScope.markSymbolFromUnstableSmartcast(symbol);
                }
                processor.invoke(symbol);
            }
        }
    }

    private static final Unit checkGetAndCreateSynthetic$lambda$1(Ref.ObjectRef<FirNamedFunction> objectRef, FirSyntheticPropertiesScope firSyntheticPropertiesScope, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        throw null;
    }

    private final GetterCompatibilityResult computeGetterCompatibility(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        boolean zSupportsFeature = FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).supportsFeature(LanguageFeature.DontCreateSyntheticPropertiesWithoutBaseJavaGetter);
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        SyntheticGetterCompatibility syntheticGetterCompatibility = SyntheticGetterCompatibility.Incompatible;
        objectRef.element = syntheticGetterCompatibility;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        computeGetterCompatibility$checkJavaOrigin(this, booleanRef, booleanRef2, !zSupportsFeature, objectRef, objectRef2, new LinkedHashSet(), firNamedFunctionSymbol, this.baseScope, false);
        if (!booleanRef.element) {
            Object obj = objectRef.element;
            if (obj != syntheticGetterCompatibility) {
                syntheticGetterCompatibility = (SyntheticGetterCompatibility) obj;
            } else if (!zSupportsFeature && isJavaTypeOnThePath(firNamedFunctionSymbol.getDispatchReceiverType())) {
                syntheticGetterCompatibility = SyntheticGetterCompatibility.HasKotlinOrigin;
            }
        }
        return new GetterCompatibilityResult(syntheticGetterCompatibility, booleanRef2.element, (FirNamedFunctionSymbol) objectRef2.element);
    }

    private static final void computeGetterCompatibility$checkJavaOrigin(FirSyntheticPropertiesScope firSyntheticPropertiesScope, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, boolean z, Ref.ObjectRef<SyntheticGetterCompatibility> objectRef, Ref.ObjectRef<FirNamedFunctionSymbol> objectRef2, Set<MemberWithBaseScope<FirNamedFunctionSymbol>> set, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope, boolean z2) {
        Ref.ObjectRef<FirNamedFunctionSymbol> objectRef3;
        SyntheticGetterCompatibility syntheticGetterCompatibility;
        int i = WhenMappings.$EnumSwitchMapping$0[DeprecationUtilsKt.hiddenStatusOfCall(firNamedFunctionSymbol, firSyntheticPropertiesScope.isSuperCall, z2).ordinal()];
        if (i == 1) {
            booleanRef.element = true;
        } else if (i == 2) {
            booleanRef2.element = true;
        } else if (i != 3) {
            bu8.a();
            return;
        }
        List<MemberWithBaseScope<FirNamedFunctionSymbol>> directOverriddenFunctionsWithBaseScope = FirTypeScopeKt.getDirectOverriddenFunctionsWithBaseScope(firTypeScope, firNamedFunctionSymbol);
        if (Intrinsics.areEqual(firNamedFunctionSymbol.getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE)) {
            if (directOverriddenFunctionsWithBaseScope.isEmpty()) {
                syntheticGetterCompatibility = SyntheticGetterCompatibility.HasJavaOrigin;
                objectRef3 = objectRef2;
                objectRef3.element = firNamedFunctionSymbol;
            } else {
                objectRef3 = objectRef2;
                syntheticGetterCompatibility = z ? SyntheticGetterCompatibility.HasKotlinOrigin : SyntheticGetterCompatibility.Incompatible;
            }
            objectRef.element = ComparisonsKt.maxOf((Comparable) objectRef.element, syntheticGetterCompatibility);
        } else {
            objectRef3 = objectRef2;
        }
        for (MemberWithBaseScope<FirNamedFunctionSymbol> memberWithBaseScope : directOverriddenFunctionsWithBaseScope) {
            if (set.add(memberWithBaseScope)) {
                computeGetterCompatibility$checkJavaOrigin(firSyntheticPropertiesScope, booleanRef, booleanRef2, z, objectRef, objectRef3, set, (FirNamedFunctionSymbol) memberWithBaseScope.getMember(), memberWithBaseScope.getBaseScope(), true);
            }
            objectRef3 = objectRef2;
        }
    }

    public static Unit d(FirSyntheticPropertiesScope firSyntheticPropertiesScope, Name name, FirNamedFunction firNamedFunction, FqName fqName, FqName fqName2, FirNamedFunction firNamedFunction2, FirSyntheticPropertyBuilder firSyntheticPropertyBuilder) {
        firSyntheticPropertyBuilder.getClass();
        firSyntheticPropertyBuilder.setModuleData(FirModuleDataKt.getModuleData(firSyntheticPropertiesScope.session));
        firSyntheticPropertyBuilder.setName(name);
        firSyntheticPropertyBuilder.setSymbol(new FirSimpleSyntheticPropertySymbol(new CallableId(fqName, fqName2, name), firNamedFunction.getSymbol().getCallableId()));
        firSyntheticPropertyBuilder.setDelegateGetter(firNamedFunction);
        firSyntheticPropertyBuilder.setDelegateSetter(firNamedFunction2);
        firSyntheticPropertyBuilder.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAccessors(firSyntheticPropertiesScope.session, firNamedFunction, firNamedFunction2));
        return Unit.INSTANCE;
    }

    public static Unit e(FirSyntheticPropertiesScope firSyntheticPropertiesScope, Name name, Name name2, Function1 function1, Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        firSyntheticPropertiesScope.checkGetAndCreateSynthetic(name, name2, firNamedFunctionSymbol, true, function1);
        booleanRef.element = true;
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction f(Ref.BooleanRef booleanRef, FirSyntheticPropertiesScope firSyntheticPropertiesScope, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2, FirTypeScope firTypeScope) {
        FirNamedFunction delegate;
        firNamedFunctionSymbol2.getClass();
        firTypeScope.getClass();
        if (booleanRef.element) {
            return ProcessorAction.STOP;
        }
        ConeSimpleKotlinType dispatchReceiverType = firNamedFunctionSymbol2.getDispatchReceiverType();
        if (dispatchReceiverType == null) {
            return ProcessorAction.NEXT;
        }
        List<FirVariableSymbol<?>> properties = FirScopeKt.getProperties(new FirSyntheticPropertiesScope(firSyntheticPropertiesScope.session, firTypeScope, dispatchReceiverType, firSyntheticPropertiesScope.syntheticNamesProvider, firSyntheticPropertiesScope.returnTypeCalculator, firSyntheticPropertiesScope.isSuperCall), name);
        if (!(properties instanceof Collection) || !properties.isEmpty()) {
            Iterator<T> it = properties.iterator();
            while (it.hasNext()) {
                FirVariable firVariable = (FirVariable) ((FirVariableSymbol) it.next()).getFir();
                if (firVariable instanceof FirSyntheticProperty) {
                    FirSyntheticPropertyAccessor setter = ((FirSyntheticProperty) firVariable).getSetter();
                    if (Intrinsics.areEqual((setter == null || (delegate = setter.getDelegate()) == null) ? null : delegate.getSymbol(), firNamedFunctionSymbol == null ? firNamedFunctionSymbol2 : firNamedFunctionSymbol)) {
                        booleanRef.element = true;
                        return ProcessorAction.STOP;
                    }
                }
            }
        }
        return ProcessorAction.NEXT;
    }

    private final boolean isJavaTypeOnThePath(ConeSimpleKotlinType baseType) {
        ConeClassifierLookupTag lookupTagIfAny;
        ConeClassifierLookupTag lookupTagIfAny2;
        FirClassifierSymbol<?> symbol;
        if (baseType != null && (lookupTagIfAny = ConeTypeUtilsKt.getLookupTagIfAny(baseType)) != null && (lookupTagIfAny2 = ConeTypeUtilsKt.getLookupTagIfAny(this.dispatchReceiverType)) != null && (symbol = ToSymbolUtilsKt.toSymbol(lookupTagIfAny2, this.session)) != null) {
            ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(this.session);
            ConeKotlinType coneKotlinType = this.dispatchReceiverType;
            if ((coneKotlinType instanceof ConeClassLikeType) && isJavaTypeOnThePath$checkType(this, typeContext, lookupTagIfAny, (ConeClassLikeType) coneKotlinType)) {
                return true;
            }
            if (symbol instanceof FirClassLikeSymbol) {
                Iterator<ConeClassLikeType> it = SupertypeUtilsKt.lookupSuperTypes((FirClassLikeSymbol) symbol, true, true, this.session).iterator();
                while (it.hasNext()) {
                    if (isJavaTypeOnThePath$checkType(this, typeContext, lookupTagIfAny, it.next())) {
                        return true;
                    }
                }
                return false;
            }
            if (symbol instanceof FirTypeParameterSymbol) {
                return false;
            }
            bu8.a();
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isJavaTypeOnThePath$checkType(FirSyntheticPropertiesScope firSyntheticPropertiesScope, ConeInferenceContext coneInferenceContext, ConeClassifierLookupTag coneClassifierLookupTag, ConeClassLikeType coneClassLikeType) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeType, firSyntheticPropertiesScope.session);
        if (regularClassSymbol == null) {
            return false;
        }
        FirDeclarationOrigin origin = regularClassSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            E fir = regularClassSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return false;
            }
        }
        return AbstractTypeChecker.INSTANCE.isSubtypeOfClass(coneInferenceContext, coneClassLikeType.getLookupTag(), coneClassifierLookupTag);
    }

    private final boolean setterTypeIsConsistentWithGetterType(Name propertyName, FirNamedFunctionSymbol getterSymbol, FirNamedFunctionSymbol setterSymbol, ConeKotlinType setterParameterType) {
        ConeKotlinType coneType = getterSymbol.getResolvedReturnTypeRef().getConeType();
        AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
        if (AbstractTypeChecker.equalTypes$default(abstractTypeChecker, TypeComponentsKt.getTypeContext(this.session), coneType, setterParameterType, false, false, 24, (Object) null)) {
            return true;
        }
        if (AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, TypeComponentsKt.getTypeContext(this.session), coneType, setterParameterType, false, 8, (Object) null)) {
            return setterTypeIsConsistentWithGetterType$processOverrides(this, propertyName, setterSymbol, null) || setterTypeIsConsistentWithGetterType$processOverrides(this, propertyName, getterSymbol, setterSymbol);
        }
        return false;
    }

    private static final boolean setterTypeIsConsistentWithGetterType$processOverrides(final FirSyntheticPropertiesScope firSyntheticPropertiesScope, final Name name, FirNamedFunctionSymbol firNamedFunctionSymbol, final FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        firSyntheticPropertiesScope.baseScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, new Function2() { // from class: df5
            public final Object invoke(Object obj, Object obj2) {
                return FirSyntheticPropertiesScope.f(booleanRef, firSyntheticPropertiesScope, name, firNamedFunctionSymbol2, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
            }
        });
        return booleanRef.element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean shouldSearchForJavaRecordComponents() {
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(this.dispatchReceiverType, this.session);
        if (regularClassSymbol == null) {
            return true;
        }
        Boolean boolIsJavaRecord = ClassMembersKt.isJavaRecord((FirRegularClass) regularClassSymbol.getFir());
        if (boolIsJavaRecord != null) {
            return boolIsJavaRecord.booleanValue();
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        Set<Name> callableNames = this.baseScope.getCallableNames();
        HashSet hashSet = new HashSet();
        Iterator<T> it = callableNames.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(hashSet, this.syntheticNamesProvider.possiblePropertyNamesByAccessorName((Name) it.next()));
        }
        return hashSet;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return SetsKt.emptySet();
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(final Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        List<Name> listPossibleGetterNamesByPropertyName = this.syntheticNamesProvider.possibleGetterNamesByPropertyName(name);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        for (final Name name2 : listPossibleGetterNamesByPropertyName) {
            this.baseScope.processFunctionsByName(name2, new Function1() { // from class: ff5
                public final Object invoke(Object obj) {
                    return FirSyntheticPropertiesScope.e(this.b, name, name2, processor, booleanRef, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        if (booleanRef.element || !shouldSearchForJavaRecordComponents()) {
            return;
        }
        this.baseScope.processFunctionsByName(name, new Function1() { // from class: gf5
            public final Object invoke(Object obj) {
                return FirSyntheticPropertiesScope.b(this.b, name, processor, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirSyntheticPropertiesScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirTypeScope firTypeScopeWithReplacedSessionOrNull = this.baseScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firTypeScopeWithReplacedSessionOrNull == null) {
            firTypeScopeWithReplacedSessionOrNull = this.baseScope;
        }
        return new FirSyntheticPropertiesScope(newSession, firTypeScopeWithReplacedSessionOrNull, this.dispatchReceiverType, this.syntheticNamesProvider, this.returnTypeCalculator, this.isSuperCall);
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope$Companion;", Argument.Delimiters.none, "<init>", "()V", "createIfSyntheticNamesProviderIsDefined", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticPropertiesScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "baseScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "isSuperCall", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FirSyntheticPropertiesScope createIfSyntheticNamesProviderIsDefined$default(Companion companion, FirSession firSession, ConeKotlinType coneKotlinType, FirTypeScope firTypeScope, ReturnTypeCalculator returnTypeCalculator, boolean z, int i, Object obj) {
            if ((i & 8) != 0) {
                returnTypeCalculator = null;
            }
            ReturnTypeCalculator returnTypeCalculator2 = returnTypeCalculator;
            if ((i & 16) != 0) {
                z = false;
            }
            return companion.createIfSyntheticNamesProviderIsDefined(firSession, coneKotlinType, firTypeScope, returnTypeCalculator2, z);
        }

        public final FirSyntheticPropertiesScope createIfSyntheticNamesProviderIsDefined(FirSession session, ConeKotlinType dispatchReceiverType, FirTypeScope baseScope, ReturnTypeCalculator returnTypeCalculator, boolean isSuperCall) {
            session.getClass();
            dispatchReceiverType.getClass();
            baseScope.getClass();
            FirSyntheticNamesProvider syntheticNamesProvider = FirSyntheticNamesProviderKt.getSyntheticNamesProvider(session);
            if (syntheticNamesProvider == null) {
                return null;
            }
            return new FirSyntheticPropertiesScope(session, baseScope, dispatchReceiverType, syntheticNamesProvider, returnTypeCalculator, isSuperCall, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirSyntheticPropertiesScope(FirSession firSession, FirTypeScope firTypeScope, ConeKotlinType coneKotlinType, FirSyntheticNamesProvider firSyntheticNamesProvider, ReturnTypeCalculator returnTypeCalculator, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firTypeScope, coneKotlinType, firSyntheticNamesProvider, returnTypeCalculator, z);
    }
}

package org.jetbrains.kotlin.fir.declarations;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.Checks;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.ScopesKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001:\u0004)*+,B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00072\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000bJB\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00072\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00120\u000bR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0015R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0015¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/Checks;", Argument.Delimiters.none, "<init>", "()V", "full", "Lorg/jetbrains/kotlin/fir/declarations/Check;", "requiredResolvePhase", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "implementation", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "simple", "message", Argument.Delimiters.none, "predicate", Argument.Delimiters.none, "memberOrExtension", "getMemberOrExtension", "()Lorg/jetbrains/kotlin/fir/declarations/Check;", "memberOrExtensionOrCompanionBlockMember", "getMemberOrExtensionOrCompanionBlockMember", "member", "getMember", "notExtension", "getNotExtension", "noContextParameters", "getNoContextParameters", "nonSuspend", "getNonSuspend", "noDefaults", "getNoDefaults", "onlyLastVararg", "getOnlyLastVararg", "noDefaultAndVarargs", "getNoDefaultAndVarargs", "kPropertyType", "Lorg/jetbrains/kotlin/fir/types/impl/ConeClassLikeTypeImpl;", "isKProperty", "ValueParametersCount", "Returns", "EqualsOverridesEqualsOfAny", "FeatureIsSupported", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Checks {
    public static final Checks INSTANCE;
    private static final Check isKProperty;
    private static final ConeClassLikeTypeImpl kPropertyType;
    private static final Check member;
    private static final Check memberOrExtension;
    private static final Check memberOrExtensionOrCompanionBlockMember;
    private static final Check noContextParameters;
    private static final Check noDefaultAndVarargs;
    private static final Check noDefaults;
    private static final Check nonSuspend;
    private static final Check notExtension;
    private static final Check onlyLastVararg;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/Checks$EqualsOverridesEqualsOfAny;", "Lorg/jetbrains/kotlin/fir/declarations/Check;", "<init>", "()V", "check", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EqualsOverridesEqualsOfAny extends Check {
        public static final EqualsOverridesEqualsOfAny INSTANCE = new EqualsOverridesEqualsOfAny();

        private EqualsOverridesEqualsOfAny() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX WARN: Code duplicated, block: B:33:0x0089  */
        @Override // org.jetbrains.kotlin.fir.declarations.Check
        public OperatorDiagnostic check(FirNamedFunction function, FirSession session, ScopeSession scopeSession) throws UninitializedPropertyAccessException {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
            FirRegularClassSymbol regularClassSymbol;
            StringBuilder sb;
            function.getClass();
            session.getClass();
            if (scopeSession == null || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(function)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, session)) == null) {
                return null;
            }
            boolean zSupportsFeature = FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).supportsFeature(LanguageFeature.CustomEqualsInValueClasses);
            Collection<FirFunctionSymbol<?>> collectionOverriddenFunctions = ScopesKt.overriddenFunctions(function.getSymbol(), regularClassSymbol, session, scopeSession);
            if ((collectionOverriddenFunctions instanceof Collection) && collectionOverriddenFunctions.isEmpty()) {
                if (zSupportsFeature) {
                    sb = new StringBuilder("must override 'equals()' in Any");
                    if (zSupportsFeature) {
                        sb.append(" or define 'equals(other: " + ConeTypeUtilsKt.renderReadable(ConeTypeUtilsKt.replaceArgumentsWithStarProjections(ScopeUtilsKt.defaultType(regularClassSymbol))) + "): Boolean'");
                    }
                    return new OperatorDiagnostic.IllegalOperatorDiagnostic(sb.toString());
                }
                sb = new StringBuilder("must override 'equals()' in Any");
                if (zSupportsFeature) {
                    sb.append(" or define 'equals(other: " + ConeTypeUtilsKt.renderReadable(ConeTypeUtilsKt.replaceArgumentsWithStarProjections(ScopeUtilsKt.defaultType(regularClassSymbol))) + "): Boolean'");
                }
                return new OperatorDiagnostic.IllegalOperatorDiagnostic(sb.toString());
            }
            Iterator<T> it = collectionOverriddenFunctions.iterator();
            while (it.hasNext()) {
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag((FirFunctionSymbol) it.next());
                if (Intrinsics.areEqual(coneClassLikeLookupTagContainingClassLookupTag2 != null ? coneClassLikeLookupTagContainingClassLookupTag2.getClassId() : null, StandardClassIds.INSTANCE.getAny())) {
                }
            }
            if ((zSupportsFeature || !ValueClassesUtilsKt.isTypedEqualsInValueClass(function.getSymbol(), session)) && !Intrinsics.areEqual(regularClassSymbol.getClassId(), StandardClassIds.INSTANCE.getAny())) {
                sb = new StringBuilder("must override 'equals()' in Any");
                if (zSupportsFeature && (regularClassSymbol.getRawStatus().isInline() || regularClassSymbol.getRawStatus().isValue())) {
                    sb.append(" or define 'equals(other: " + ConeTypeUtilsKt.renderReadable(ConeTypeUtilsKt.replaceArgumentsWithStarProjections(ScopeUtilsKt.defaultType(regularClassSymbol))) + "): Boolean'");
                }
                return new OperatorDiagnostic.IllegalOperatorDiagnostic(sb.toString());
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/Checks$FeatureIsSupported;", "Lorg/jetbrains/kotlin/fir/declarations/Check;", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;)V", "getFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "check", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FeatureIsSupported extends Check {
        private final LanguageFeature feature;

        public FeatureIsSupported(LanguageFeature languageFeature) {
            languageFeature.getClass();
            this.feature = languageFeature;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.Check
        public OperatorDiagnostic check(FirNamedFunction function, FirSession session, ScopeSession scopeSession) {
            function.getClass();
            session.getClass();
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).supportsFeature(this.feature)) {
                return null;
            }
            return new OperatorDiagnostic.Unsupported(this.feature);
        }

        public final LanguageFeature getFeature() {
            return this.feature;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\tR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/Checks$Returns;", Argument.Delimiters.none, "<init>", "()V", "returnsCheck", "Lorg/jetbrains/kotlin/fir/declarations/Check;", "message", Argument.Delimiters.none, "predicate", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/FirSession;", Argument.Delimiters.none, "boolean", "getBoolean", "()Lorg/jetbrains/kotlin/fir/declarations/Check;", "int", "getInt", "unit", "getUnit", "outerOfCompanionWhereDefined", "getOuterOfCompanionWhereDefined", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Returns {
        public static final Returns INSTANCE;
        private static final Check boolean;
        private static final Check int;
        private static final Check outerOfCompanionWhereDefined;
        private static final Check unit;

        static {
            Returns returns = new Returns();
            INSTANCE = returns;
            boolean = returns.returnsCheck("must return 'Boolean'", new Function2() { // from class: vr1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.Returns.a((FirNamedFunction) obj, (FirSession) obj2));
                }
            });
            int = returns.returnsCheck("must return 'Int'", new Function2() { // from class: wr1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.Returns.c((FirNamedFunction) obj, (FirSession) obj2));
                }
            });
            unit = returns.returnsCheck("must return 'Unit'", new Function2() { // from class: xr1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.Returns.f((FirNamedFunction) obj, (FirSession) obj2));
                }
            });
            outerOfCompanionWhereDefined = Checks.INSTANCE.full(new Function1() { // from class: yr1
                public final Object invoke(Object obj) {
                    return Checks.Returns.d((FirNamedFunction) obj);
                }
            }, new Function2() { // from class: zr1
                public final Object invoke(Object obj, Object obj2) {
                    return Checks.Returns.b((FirNamedFunction) obj, (FirSession) obj2);
                }
            });
        }

        private Returns() {
        }

        public static boolean a(FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return ConeBuiltinTypeUtilsKt.isBoolean(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()), firSession, (Function1) null, 2, (Object) null));
        }

        public static OperatorDiagnostic b(FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            ConeSimpleKotlinType dispatchReceiverType = firNamedFunction.getDispatchReceiverType();
            FirRegularClassSymbol regularClassSymbol = dispatchReceiverType != null ? ToSymbolUtilsKt.toRegularClassSymbol(dispatchReceiverType, firSession) : null;
            if (regularClassSymbol == null || !regularClassSymbol.getRawStatus().isCompanion()) {
                return new OperatorDiagnostic.IllegalOperatorDiagnostic("must be a member of companion");
            }
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(regularClassSymbol);
            FirRegularClassSymbol firRegularClassSymbol = containingClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol : null;
            if (firRegularClassSymbol != null) {
                ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()), firSession, (Function1) null, 2, (Object) null);
                ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeFullyExpandedType$default);
                if (!(coneRigidTypeLowerBoundIfFlexible instanceof ConeErrorType)) {
                    if (coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType) {
                        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible;
                        if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneClassLikeType), firRegularClassSymbol.getClassId())) {
                            if (coneClassLikeType.getIsMarkedNullable()) {
                                return new OperatorDiagnostic.ReturnTypeMismatchWithOuterClass(firRegularClassSymbol, true, false, 4, null);
                            }
                            if (coneKotlinTypeFullyExpandedType$default instanceof ConeFlexibleType) {
                                return new OperatorDiagnostic.ReturnTypeMismatchWithOuterClass(firRegularClassSymbol, false, true, 2, null);
                            }
                        }
                    }
                    return new OperatorDiagnostic.ReturnTypeMismatchWithOuterClass(firRegularClassSymbol, false, false, 6, null);
                }
            }
            return null;
        }

        public static boolean c(FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return ConeBuiltinTypeUtilsKt.isInt(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()), firSession, (Function1) null, 2, (Object) null));
        }

        public static FirResolvePhase d(FirNamedFunction firNamedFunction) {
            firNamedFunction.getClass();
            FirTypeRef returnTypeRef = firNamedFunction.getReturnTypeRef();
            if (returnTypeRef instanceof FirResolvedTypeRef) {
                return FirResolvePhase.STATUS;
            }
            return returnTypeRef instanceof FirImplicitTypeRef ? FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE : FirResolvePhase.TYPES;
        }

        public static FirResolvePhase e(FirNamedFunction firNamedFunction) {
            firNamedFunction.getClass();
            FirTypeRef returnTypeRef = firNamedFunction.getReturnTypeRef();
            if (returnTypeRef instanceof FirResolvedTypeRef) {
                return null;
            }
            return returnTypeRef instanceof FirImplicitTypeRef ? FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE : FirResolvePhase.TYPES;
        }

        public static boolean f(FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return ConeBuiltinTypeUtilsKt.isUnit(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()), firSession, (Function1) null, 2, (Object) null));
        }

        public final Check getBoolean() {
            return boolean;
        }

        public final Check getInt() {
            return int;
        }

        public final Check getOuterOfCompanionWhereDefined() {
            return outerOfCompanionWhereDefined;
        }

        public final Check getUnit() {
            return unit;
        }

        public final Check returnsCheck(String message, Function2<? super FirNamedFunction, ? super FirSession, Boolean> predicate) {
            message.getClass();
            predicate.getClass();
            return Checks.INSTANCE.simple(message, new Function1() { // from class: ur1
                public final Object invoke(Object obj) {
                    return Checks.Returns.e((FirNamedFunction) obj);
                }
            }, predicate);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/Checks$ValueParametersCount;", Argument.Delimiters.none, "<init>", "()V", "atLeast", "Lorg/jetbrains/kotlin/fir/declarations/Check;", "n", Argument.Delimiters.none, "exactly", "atMost", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "single", "getSingle", "()Lorg/jetbrains/kotlin/fir/declarations/Check;", "none", "getNone", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ValueParametersCount {
        public static final ValueParametersCount INSTANCE = new ValueParametersCount();
        private static final Check none;
        private static final Check single;

        static {
            Checks checks = Checks.INSTANCE;
            single = Checks.simple$default(checks, "must have a single value parameter", null, new Function2() { // from class: bs1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.ValueParametersCount.b((FirNamedFunction) obj, (FirSession) obj2));
                }
            }, 2, null);
            none = Checks.simple$default(checks, "must have no value parameters", null, new Function2() { // from class: cs1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.ValueParametersCount.e((FirNamedFunction) obj, (FirSession) obj2));
                }
            }, 2, null);
        }

        private ValueParametersCount() {
        }

        public static boolean a(int i, FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return firNamedFunction.getValueParameters().size() == i;
        }

        public static boolean b(FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return firNamedFunction.getValueParameters().size() == 1;
        }

        public static boolean c(int i, FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return firNamedFunction.getValueParameters().size() >= i;
        }

        public static OperatorDiagnostic d(int i, LanguageFeature languageFeature, FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            if (firNamedFunction.getValueParameters().size() <= i) {
                return null;
            }
            StringBuilder sb = new StringBuilder("must have at most ");
            sb.append(i);
            sb.append(" value parameter");
            sb.append(i > 1 ? "s" : Argument.Delimiters.none);
            String string = sb.toString();
            return languageFeature != null ? new OperatorDiagnostic.DeprecatedOperatorDiagnostic(string, languageFeature) : new OperatorDiagnostic.IllegalOperatorDiagnostic(string);
        }

        public static boolean e(FirNamedFunction firNamedFunction, FirSession firSession) {
            firNamedFunction.getClass();
            firSession.getClass();
            return firNamedFunction.getValueParameters().isEmpty();
        }

        public final Check atLeast(final int n) {
            Checks checks = Checks.INSTANCE;
            StringBuilder sb = new StringBuilder("must have at least ");
            sb.append(n);
            sb.append(" value parameter");
            sb.append(n > 1 ? "s" : Argument.Delimiters.none);
            return Checks.simple$default(checks, sb.toString(), null, new Function2() { // from class: ds1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.ValueParametersCount.c(n, (FirNamedFunction) obj, (FirSession) obj2));
                }
            }, 2, null);
        }

        public final Check atMost(final int n, final LanguageFeature feature) {
            return Checks.full$default(Checks.INSTANCE, null, new Function2() { // from class: as1
                public final Object invoke(Object obj, Object obj2) {
                    return Checks.ValueParametersCount.d(n, feature, (FirNamedFunction) obj, (FirSession) obj2);
                }
            }, 1, null);
        }

        public final Check exactly(final int n) {
            return Checks.simple$default(Checks.INSTANCE, "must have exactly " + n + " value parameters", null, new Function2() { // from class: es1
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(Checks.ValueParametersCount.a(n, (FirNamedFunction) obj, (FirSession) obj2));
                }
            }, 2, null);
        }

        public final Check getNone() {
            return none;
        }

        public final Check getSingle() {
            return single;
        }
    }

    static {
        Checks checks = new Checks();
        INSTANCE = checks;
        memberOrExtension = simple$default(checks, "must be a member or an extension function", null, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.a
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.i((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null);
        memberOrExtensionOrCompanionBlockMember = simple$default(checks, "must be a member, an extension function or companion block member", null, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.m
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.j((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null);
        member = simple$default(checks, "must be a member function", null, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.n
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.f((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null);
        notExtension = simple$default(checks, "must not have an extension receiver", null, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.o
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.n((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null);
        noContextParameters = simple$default(checks, "must not have context parameters", null, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.p
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.d((FirNamedFunction) obj, (FirSession) obj2));
            }
        }, 2, null);
        nonSuspend = checks.simple("must not be suspend", new Function1() { // from class: org.jetbrains.kotlin.fir.declarations.b
            public final Object invoke(Object obj) {
                return Checks.p((FirNamedFunction) obj);
            }
        }, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.c
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.g((FirNamedFunction) obj, (FirSession) obj2));
            }
        });
        noDefaults = checks.simple("must not have parameters with default values", new Function1() { // from class: org.jetbrains.kotlin.fir.declarations.d
            public final Object invoke(Object obj) {
                return Checks.b((FirNamedFunction) obj);
            }
        }, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.e
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.l((FirNamedFunction) obj, (FirSession) obj2));
            }
        });
        onlyLastVararg = checks.simple("must not have vararg parameters other than the last one", new Function1() { // from class: org.jetbrains.kotlin.fir.declarations.f
            public final Object invoke(Object obj) {
                return Checks.e((FirNamedFunction) obj);
            }
        }, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.h
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.m((FirNamedFunction) obj, (FirSession) obj2));
            }
        });
        noDefaultAndVarargs = checks.full(new Function1() { // from class: org.jetbrains.kotlin.fir.declarations.i
            public final Object invoke(Object obj) {
                return Checks.o((FirNamedFunction) obj);
            }
        }, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.j
            public final Object invoke(Object obj, Object obj2) {
                return Checks.a((FirNamedFunction) obj, (FirSession) obj2);
            }
        });
        kPropertyType = new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getKProperty()), new ConeStarProjection[]{ConeStarProjection.INSTANCE}, false, null, 8, null);
        isKProperty = checks.simple("second parameter must be of type KProperty<*> or its supertype", new Function1() { // from class: org.jetbrains.kotlin.fir.declarations.k
            public final Object invoke(Object obj) {
                return Checks.h((FirNamedFunction) obj);
            }
        }, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.l
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(Checks.c((FirNamedFunction) obj, (FirSession) obj2));
            }
        });
    }

    private Checks() {
    }

    public static OperatorDiagnostic a(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        for (FirValueParameter firValueParameter : firNamedFunction.getValueParameters()) {
            if (firValueParameter.getDefaultValue() != null) {
                return new OperatorDiagnostic.IllegalOperatorDiagnostic("must not have parameters with default values");
            }
            if (firValueParameter.getIsVararg()) {
                return new OperatorDiagnostic.IllegalOperatorDiagnostic("must not have varargs");
            }
        }
        return null;
    }

    public static FirResolvePhase b(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return FirResolvePhase.BODY_RESOLVE;
    }

    public static boolean c(FirNamedFunction firNamedFunction, FirSession firSession) {
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        firNamedFunction.getClass();
        firSession.getClass();
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.getOrNull(firNamedFunction.getValueParameters(), 1);
        if (firValueParameter == null || (returnTypeRef = firValueParameter.getReturnTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) == null) {
            return false;
        }
        return TypeUtilsKt.isSubtypeOf(kPropertyType, coneType, firSession, true);
    }

    public static boolean d(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        return firNamedFunction.getContextParameters().isEmpty();
    }

    public static FirResolvePhase e(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return FirResolvePhase.BODY_RESOLVE;
    }

    public static boolean f(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        return firNamedFunction.getDispatchReceiverType() != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Check full$default(Checks checks, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return checks.full(function1, function2);
    }

    public static boolean g(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        return !firNamedFunction.getStatus().isSuspend();
    }

    public static FirResolvePhase h(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return FirResolvePhase.TYPES;
    }

    public static boolean i(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        return (firNamedFunction.getDispatchReceiverType() == null && firNamedFunction.getReceiverParameter() == null) ? false : true;
    }

    public static boolean j(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        return (firNamedFunction.getDispatchReceiverType() == null && firNamedFunction.getReceiverParameter() == null && !FirStatusUtilsKt.isCompanionBlockMember(firNamedFunction)) ? false : true;
    }

    public static OperatorDiagnostic k(Function2 function2, String str, FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        if (((Boolean) function2.invoke(firNamedFunction, firSession)).booleanValue()) {
            return null;
        }
        return new OperatorDiagnostic.IllegalOperatorDiagnostic(str);
    }

    public static boolean l(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            return true;
        }
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            if (((FirValueParameter) it.next()).getDefaultValue() != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean m(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        List listDropLast = CollectionsKt.dropLast(firNamedFunction.getValueParameters(), 1);
        if (!(listDropLast instanceof Collection) || !listDropLast.isEmpty()) {
            Iterator it = listDropLast.iterator();
            while (it.hasNext()) {
                if (((FirValueParameter) it.next()).getIsVararg()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean n(FirNamedFunction firNamedFunction, FirSession firSession) {
        firNamedFunction.getClass();
        firSession.getClass();
        return !FirDeclarationUtilKt.isExtension(firNamedFunction);
    }

    public static FirResolvePhase o(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return FirResolvePhase.BODY_RESOLVE;
    }

    public static FirResolvePhase p(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return FirResolvePhase.STATUS;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Check simple$default(Checks checks, String str, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        return checks.simple(str, function1, function2);
    }

    public final Check full(final Function1<? super FirNamedFunction, ? extends FirResolvePhase> requiredResolvePhase, final Function2<? super FirNamedFunction, ? super FirSession, ? extends OperatorDiagnostic> implementation) {
        implementation.getClass();
        return new Check() { // from class: org.jetbrains.kotlin.fir.declarations.Checks.full.1
            @Override // org.jetbrains.kotlin.fir.declarations.Check
            public OperatorDiagnostic check(FirNamedFunction function, FirSession session, ScopeSession scopeSession) {
                FirResolvePhase firResolvePhase;
                function.getClass();
                session.getClass();
                Function1<FirNamedFunction, FirResolvePhase> function1 = requiredResolvePhase;
                if (function1 != null && (firResolvePhase = (FirResolvePhase) function1.invoke(function)) != null) {
                    FirLazyDeclarationResolverKt.lazyResolveToPhase(function, firResolvePhase);
                }
                return (OperatorDiagnostic) implementation.invoke(function, session);
            }
        };
    }

    public final Check getMember() {
        return member;
    }

    public final Check getMemberOrExtension() {
        return memberOrExtension;
    }

    public final Check getMemberOrExtensionOrCompanionBlockMember() {
        return memberOrExtensionOrCompanionBlockMember;
    }

    public final Check getNoContextParameters() {
        return noContextParameters;
    }

    public final Check getNoDefaultAndVarargs() {
        return noDefaultAndVarargs;
    }

    public final Check getNoDefaults() {
        return noDefaults;
    }

    public final Check getNonSuspend() {
        return nonSuspend;
    }

    public final Check getNotExtension() {
        return notExtension;
    }

    public final Check getOnlyLastVararg() {
        return onlyLastVararg;
    }

    public final Check isKProperty() {
        return isKProperty;
    }

    public final Check simple(final String message, Function1<? super FirNamedFunction, ? extends FirResolvePhase> requiredResolvePhase, final Function2<? super FirNamedFunction, ? super FirSession, Boolean> predicate) {
        message.getClass();
        predicate.getClass();
        return full(requiredResolvePhase, new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.g
            public final Object invoke(Object obj, Object obj2) {
                return Checks.k(predicate, message, (FirNamedFunction) obj, (FirSession) obj2);
            }
        });
    }
}

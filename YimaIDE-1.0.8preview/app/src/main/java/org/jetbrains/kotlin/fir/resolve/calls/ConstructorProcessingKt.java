package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorProcessingKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirTypeCandidateCollector;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\u00010\nH\u0002\u001a<\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\u00010\nH\u0000\u001a.\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a4\u0010\u0011\u001a\u00020\u00012\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0016\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001aE\u0010\u0015\u001a\u00020\u00012\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u00010\nH\u0002R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"processConstructorsByName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "bodyResolveComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "constructorFilter", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConstructorFilter;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processFunctionsAndConstructorsByName", "getFirstClassifierOrNull", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeCandidate;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "processSyntheticConstructors", "matchedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "processConstructors", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lkotlin/jvm/functions/Function1;)V", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstructorProcessingKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static boolean a(FirTypeCandidateCollector firTypeCandidateCollector, ConstructorFilter constructorFilter, FirScope firScope, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) throws KotlinIllegalArgumentExceptionWithAttachments {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        getFirstClassifierOrNull$process(constructorFilter, firTypeCandidateCollector, firScope, firClassifierSymbol, coneSubstitutor);
        return firTypeCandidateCollector.getApplicability() == CandidateApplicability.RESOLVED;
    }

    private static final FirTypeCandidateCollector.TypeCandidate getFirstClassifierOrNull(final FirScope firScope, CallInfo callInfo, final ConstructorFilter constructorFilter, FirSession firSession, BodyResolveComponents bodyResolveComponents) {
        final FirTypeCandidateCollector firTypeCandidateCollector = new FirTypeCandidateCollector(firSession, bodyResolveComponents.getFile(), bodyResolveComponents.getContainingDeclarations(), null, false, 24, null);
        if (firScope instanceof FirDefaultStarImportingScope) {
            ((FirDefaultStarImportingScope) firScope).processClassifiersByNameWithSubstitutionFromBothLevelsConditionally(callInfo.getName(), new Function2() { // from class: gu2
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(ConstructorProcessingKt.a(firTypeCandidateCollector, constructorFilter, firScope, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2));
                }
            });
        } else {
            firScope.processClassifiersByNameWithSubstitution(callInfo.getName(), new AnonymousClass2(constructorFilter, firTypeCandidateCollector, firScope));
        }
        return firTypeCandidateCollector.getResult().resolvedCandidateOrNull();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void getFirstClassifierOrNull$process(ConstructorFilter constructorFilter, FirTypeCandidateCollector firTypeCandidateCollector, FirScope firScope, FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object fir = firClassifierSymbol.getFir();
        if ((fir instanceof FirClassLikeDeclaration) && constructorFilter.accepts((FirMemberDeclaration) fir)) {
            firTypeCandidateCollector.processCandidate(firClassifierSymbol, coneSubstitutor, ResolveUtilsKt.toResolvedSymbolOrigin(firScope));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private static final void processConstructors(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirClassLikeSymbol<?> firClassLikeSymbol, ConeSubstitutor coneSubstitutor, final Function1<? super FirFunctionSymbol<?>, Unit> function1) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirScope firScopeScopeForClass;
        FirSession session = sessionAndScopeSessionHolder.getSession();
        ?? fir = firClassLikeSymbol.getFir();
        try {
            if (firClassLikeSymbol instanceof FirTypeAliasSymbol) {
                firScopeScopeForClass = FirKotlinScopeProviderKt.scopeForTypeAlias((FirTypeAlias) ((FirTypeAliasSymbol) firClassLikeSymbol).getFir(), sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession());
            } else {
                if (!(firClassLikeSymbol instanceof FirClassSymbol)) {
                    throw new NoWhenBranchMatchedException();
                }
                FirClass firClass = (FirClass) ((FirClassSymbol) firClassLikeSymbol).getFir();
                firScopeScopeForClass = WhenMappings.$EnumSwitchMapping$0[firClass.getClassKind().ordinal()] == 1 ? null : FirKotlinScopeProviderKt.scopeForClass(sessionAndScopeSessionHolder, firClass, coneSubstitutor, firClass.getSymbol(), FirResolvePhase.STATUS);
            }
            if (firScopeScopeForClass != null) {
                firScopeScopeForClass.processDeclaredConstructors(new Function1() { // from class: hu2
                    public final Object invoke(Object obj) {
                        return ConstructorProcessingKt.processConstructors$lambda$0$0(function1, (FirConstructorSymbol) obj);
                    }
                });
                Unit unit = Unit.INSTANCE;
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(fir, th);
            wq6.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processConstructors$lambda$0$0(Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        function1.invoke(firConstructorSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final void processConstructorsByName(FirScope firScope, CallInfo callInfo, BodyResolveComponents bodyResolveComponents, ConstructorFilter constructorFilter, Function1<? super FirCallableSymbol<?>, Unit> function1) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirTypeCandidateCollector.TypeCandidate firstClassifierOrNull = getFirstClassifierOrNull(firScope, callInfo, constructorFilter, bodyResolveComponents.getSession(), bodyResolveComponents);
        if (firstClassifierOrNull == null) {
            return;
        }
        FirBasedSymbol<?> firBasedSymbolComponent1 = firstClassifierOrNull.component1();
        ConeSubstitutor coneSubstitutorComponent2 = firstClassifierOrNull.getSubstitutor();
        FirClassLikeSymbol firClassLikeSymbol = firBasedSymbolComponent1 instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) firBasedSymbolComponent1 : null;
        if (firClassLikeSymbol == null) {
            return;
        }
        coneSubstitutorComponent2.getClass();
        processConstructors(bodyResolveComponents, firClassLikeSymbol, coneSubstitutorComponent2, function1);
        processSyntheticConstructors(firClassLikeSymbol, function1, bodyResolveComponents);
    }

    public static final void processFunctionsAndConstructorsByName(FirScope firScope, CallInfo callInfo, BodyResolveComponents bodyResolveComponents, ConstructorFilter constructorFilter, Function1<? super FirCallableSymbol<?>, Unit> function1) {
        firScope.getClass();
        callInfo.getClass();
        bodyResolveComponents.getClass();
        constructorFilter.getClass();
        function1.getClass();
        processConstructorsByName(firScope, callInfo, bodyResolveComponents, constructorFilter, function1);
        firScope.processFunctionsByName(callInfo.getName(), function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void processSyntheticConstructors(FirClassLikeSymbol<?> firClassLikeSymbol, Function1<? super FirFunctionSymbol<?>, Unit> function1, BodyResolveComponents bodyResolveComponents) {
        FirNamedFunction samConstructor = bodyResolveComponents.getSamResolver().getSamConstructor((FirClassLikeDeclaration) firClassLikeSymbol.getFir());
        if (samConstructor != null) {
            function1.invoke(samConstructor.getSymbol());
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.ConstructorProcessingKt$getFirstClassifierOrNull$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit> {
        final /* synthetic */ FirTypeCandidateCollector $collector;
        final /* synthetic */ ConstructorFilter $constructorFilter;
        final /* synthetic */ FirScope $this_getFirstClassifierOrNull;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ConstructorFilter constructorFilter, FirTypeCandidateCollector firTypeCandidateCollector, FirScope firScope) {
            super(2, Intrinsics.Kotlin.class, "process", "getFirstClassifierOrNull$process(Lorg/jetbrains/kotlin/fir/resolve/calls/ConstructorFilter;Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)V", 0);
            this.$constructorFilter = constructorFilter;
            this.$collector = firTypeCandidateCollector;
            this.$this_getFirstClassifierOrNull = firScope;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) throws KotlinIllegalArgumentExceptionWithAttachments {
            firClassifierSymbol.getClass();
            coneSubstitutor.getClass();
            ConstructorProcessingKt.getFirstClassifierOrNull$process(this.$constructorFilter, this.$collector, this.$this_getFirstClassifierOrNull, firClassifierSymbol, coneSubstitutor);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) throws KotlinIllegalArgumentExceptionWithAttachments {
            invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
            return Unit.INSTANCE;
        }
    }
}

package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.resolve.FirRegularTowerDataContexts;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.ReturnTypeCalculatorWithJump;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001:\u00012BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\bH\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010'\u001a\u00020\bH\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010'\u001a\u00020\bH\u0004J\u0010\u00100\u001a\u00020&2\u0006\u0010'\u001a\u00020\bH\u0002J\u0010\u00101\u001a\u00020&2\u0006\u0010'\u001a\u00020\bH\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001c\u0010,\u001a\u00020-*\u0006\u0012\u0002\b\u00030.8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010/¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "implicitBodyResolveComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;", "designationMapForLocalClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "outerTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getImplicitBodyResolveComputationSession", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;", "getDesignationMapForLocalClasses", "()Ljava/util/Map;", "getOuterTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "getOuterBodyResolveContext", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "callableCopyTypeCalculator", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "getCallableCopyTypeCalculator", "()Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "outerTowerDataContexts", "Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;", "getOuterTowerDataContexts$annotations", "()V", "getOuterTowerDataContexts", "()Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;", "tryCalculateReturnTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "declaration", "resolvedToContractsIfNecessary", Argument.Delimiters.none, "recursionInImplicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "isUnresolvedReplProperty", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "computeReturnTypeRef", "resolveDeclaration", "CallableCopyTypeCalculatorWithJump", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ReturnTypeCalculatorWithJump extends ReturnTypeCalculator {
    private final CallableCopyTypeCalculator callableCopyTypeCalculator;
    private final Map<FirCallableDeclaration, List<FirClassLikeDeclaration>> designationMapForLocalClasses;
    private final ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession;
    private final BodyResolveContext outerBodyResolveContext;
    private final FirRegularTowerDataContexts outerTowerDataContexts;
    private final FirAbstractBodyResolveTransformerDispatcher outerTransformer;
    private final ScopeSession scopeSession;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump$CallableCopyTypeCalculatorWithJump;", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;)V", "getResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class CallableCopyTypeCalculatorWithJump extends CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator {
        public CallableCopyTypeCalculatorWithJump() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator
        public FirResolvedTypeRef getResolvedTypeRef(FirCallableDeclaration firCallableDeclaration) {
            firCallableDeclaration.getClass();
            return ReturnTypeCalculatorWithJump.this.computeReturnTypeRef(firCallableDeclaration);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ReturnTypeCalculatorWithJump(ScopeSession scopeSession, ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession, Map<FirCallableDeclaration, ? extends List<? extends FirClassLikeDeclaration>> map, FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher, BodyResolveContext bodyResolveContext) {
        scopeSession.getClass();
        implicitBodyResolveComputationSession.getClass();
        map.getClass();
        this.scopeSession = scopeSession;
        this.implicitBodyResolveComputationSession = implicitBodyResolveComputationSession;
        this.designationMapForLocalClasses = map;
        this.outerTransformer = firAbstractBodyResolveTransformerDispatcher;
        this.outerBodyResolveContext = bodyResolveContext;
        this.callableCopyTypeCalculator = new CallableCopyTypeCalculatorWithJump();
        this.outerTowerDataContexts = bodyResolveContext != null ? bodyResolveContext.getRegularTowerDataContexts() : null;
    }

    public static ConeClassLikeLookupTag a(FirSession firSession, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        coneClassLikeLookupTag.getClass();
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, firSession);
        if (symbol != null) {
            return ClassMembersKt.getContainingClassLookupTag(symbol);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirResolvedTypeRef computeReturnTypeRef(FirCallableDeclaration declaration) {
        FirResolvedTypeRef firResolvedTypeRefRecursionInImplicitTypeRef;
        FirCallableSymbol<FirCallableDeclaration> propertySymbol = declaration instanceof FirBackingField ? ((FirBackingField) declaration).getPropertySymbol() : declaration.getSymbol();
        ImplicitBodyResolveComputationStatus status$org_jetbrains_kotlin_resolve = this.implicitBodyResolveComputationSession.getStatus$org_jetbrains_kotlin_resolve(propertySymbol);
        if (status$org_jetbrains_kotlin_resolve instanceof ImplicitBodyResolveComputationStatus.Computed) {
            firResolvedTypeRefRecursionInImplicitTypeRef = ((ImplicitBodyResolveComputationStatus.Computed) status$org_jetbrains_kotlin_resolve).getResolvedTypeRef();
        } else {
            firResolvedTypeRefRecursionInImplicitTypeRef = ((status$org_jetbrains_kotlin_resolve instanceof ImplicitBodyResolveComputationStatus.Computing) || isUnresolvedReplProperty(propertySymbol)) ? recursionInImplicitTypeRef(declaration) : null;
        }
        if (firResolvedTypeRefRecursionInImplicitTypeRef == null) {
            FirTypeRef returnTypeRef = declaration.getReturnTypeRef();
            firResolvedTypeRefRecursionInImplicitTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) returnTypeRef : null;
        }
        if (firResolvedTypeRefRecursionInImplicitTypeRef != null) {
            return firResolvedTypeRefRecursionInImplicitTypeRef;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = declaration.getSymbol();
        if (!ClassMembersKt.isCopyCreatedInScope(symbol)) {
            resolveDeclaration(propertySymbol.getFir());
            FirResolvedTypeRef returnTypeRef2 = declaration.getReturnTypeRef();
            returnTypeRef2.getClass();
            return returnTypeRef2;
        }
        StringBuilder sb = new StringBuilder("callableCopySubstitution was not calculated for callable copy: ");
        sb.append(symbol);
        sb.append(" with origin ");
        sb.append(declaration.getOrigin());
        ywd.a(sb, " and return type ", declaration.getReturnTypeRef());
        return null;
    }

    public static /* synthetic */ void getOuterTowerDataContexts$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isUnresolvedReplProperty(FirCallableSymbol<?> firCallableSymbol) {
        if (!(firCallableSymbol instanceof FirRegularPropertySymbol) || !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firCallableSymbol), Boolean.TRUE)) {
            return false;
        }
        FirRegularPropertySymbol firRegularPropertySymbol = (FirRegularPropertySymbol) firCallableSymbol;
        FirExpression initializer = ((FirProperty) firRegularPropertySymbol.getFir()).getInitializer();
        FirReplExpressionReference firReplExpressionReference = initializer instanceof FirReplExpressionReference ? (FirReplExpressionReference) initializer : null;
        FirExpression delegate = ((FirProperty) firRegularPropertySymbol.getFir()).getDelegate();
        FirReplExpressionReference firReplExpressionReference2 = delegate instanceof FirReplExpressionReference ? (FirReplExpressionReference) delegate : null;
        if (firReplExpressionReference == null && firReplExpressionReference2 == null) {
            return false;
        }
        return (firReplExpressionReference == null || !FirTypeUtilsKt.getHasResolvedType(firReplExpressionReference)) && (firReplExpressionReference2 == null || !FirTypeUtilsKt.getHasResolvedType(firReplExpressionReference2));
    }

    private final void resolvedToContractsIfNecessary(FirCallableDeclaration declaration) {
        if (!((declaration instanceof FirProperty) && (((FirProperty) declaration).getSymbol() instanceof FirRegularPropertySymbol)) && (!(declaration instanceof FirNamedFunction) || Intrinsics.areEqual(((FirNamedFunction) declaration).getStatus().getVisibility(), Visibilities.Local.INSTANCE))) {
            return;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(declaration, FirResolvePhase.CONTRACTS);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator
    public CallableCopyTypeCalculator getCallableCopyTypeCalculator() {
        return this.callableCopyTypeCalculator;
    }

    public final Map<FirCallableDeclaration, List<FirClassLikeDeclaration>> getDesignationMapForLocalClasses() {
        return this.designationMapForLocalClasses;
    }

    public final ImplicitBodyResolveComputationSession getImplicitBodyResolveComputationSession() {
        return this.implicitBodyResolveComputationSession;
    }

    public final BodyResolveContext getOuterBodyResolveContext() {
        return this.outerBodyResolveContext;
    }

    public final FirRegularTowerDataContexts getOuterTowerDataContexts() {
        return this.outerTowerDataContexts;
    }

    public final FirAbstractBodyResolveTransformerDispatcher getOuterTransformer() {
        return this.outerTransformer;
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final FirErrorTypeRef recursionInImplicitTypeRef(FirCallableDeclaration declaration) {
        declaration.getClass();
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Recursive implicit type", DiagnosticKind.RecursionInImplicitTypes));
        FirErrorTypeRef firErrorTypeRefBuild = firErrorTypeRefBuilder.build();
        this.implicitBodyResolveComputationSession.calculateAndStoreNonTrivialLoop(declaration.getSymbol());
        return firErrorTypeRefBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirResolvedTypeRef resolveDeclaration(FirCallableDeclaration declaration) {
        FirScript firScript;
        Pair pair;
        List<FirDeclaration> declarations;
        Object next;
        FirResolvedTypeRef firResolvedTypeRefResolveDeclaration;
        declaration.getClass();
        final FirSession session = declaration.getModuleData().getSession();
        FirCallableSymbol<FirCallableDeclaration> symbol = declaration.getSymbol();
        if (!this.designationMapForLocalClasses.containsKey(declaration)) {
            FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher = this.outerTransformer;
            ReturnTypeCalculator returnTypeCalculator = firAbstractBodyResolveTransformerDispatcher != null ? firAbstractBodyResolveTransformerDispatcher.getReturnTypeCalculator() : null;
            ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump = returnTypeCalculator instanceof ReturnTypeCalculatorWithJump ? (ReturnTypeCalculatorWithJump) returnTypeCalculator : null;
            if (returnTypeCalculatorWithJump != null && (firResolvedTypeRefResolveDeclaration = returnTypeCalculatorWithJump.resolveDeclaration(declaration)) != null) {
                return firResolvedTypeRefResolveDeclaration;
            }
            FirFile firCallableContainerFile = FirProviderKt.getFirProvider(session).getFirCallableContainerFile(symbol);
            if (firCallableContainerFile == null || (declarations = firCallableContainerFile.getDeclarations()) == null) {
                firScript = null;
            } else {
                Iterator<T> it = declarations.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!(next instanceof FirScript));
                firScript = (FirScript) next;
            }
            Sequence sequenceGenerateSequence = SequencesKt.generateSequence(ClassMembersKt.containingClassLookupTag(symbol), new Function1() { // from class: ljc
                public final Object invoke(Object obj) {
                    return ReturnTypeCalculatorWithJump.a(session, (ConeClassLikeLookupTag) obj);
                }
            });
            ArrayList arrayList = new ArrayList();
            Iterator it2 = sequenceGenerateSequence.iterator();
            while (it2.hasNext()) {
                FirClassLikeSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) it2.next(), session);
                arrayList.add(symbol2 != null ? (FirClassLikeDeclaration) symbol2.getFir() : null);
            }
            if (firCallableContainerFile != null) {
                if (!arrayList.isEmpty()) {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        if (((FirClassLikeDeclaration) it3.next()) == null) {
                        }
                    }
                }
                pair = TuplesKt.to(CollectionsKt.plus(CollectionsKt.listOfNotNull(new FirElement[]{firCallableContainerFile, firScript}), CollectionsKt.asReversed(CollectionsKt.filterNotNull(arrayList))), null);
            }
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Cannot calculate return type (local class/object?)", DiagnosticKind.InferenceError));
            return firErrorTypeRefBuilder.build();
        }
        pair = TuplesKt.to(MapsKt.getValue(this.designationMapForLocalClasses, declaration), this.outerBodyResolveContext);
        List list = (List) pair.component1();
        BodyResolveContext bodyResolveContext = (BodyResolveContext) pair.component2();
        FirRegularTowerDataContexts regularTowerDataContexts = bodyResolveContext != null ? bodyResolveContext.getRegularTowerDataContexts() : null;
        if (bodyResolveContext != null) {
            FirRegularTowerDataContexts firRegularTowerDataContexts = this.outerTowerDataContexts;
            firRegularTowerDataContexts.getClass();
            bodyResolveContext.setRegularTowerDataContexts(firRegularTowerDataContexts);
        }
        FirDesignatedBodyResolveTransformerForReturnTypeCalculator firDesignatedBodyResolveTransformerForReturnTypeCalculator = new FirDesignatedBodyResolveTransformerForReturnTypeCalculator(CollectionsKt.plus(CollectionsKt.drop(list, 1), declaration).iterator(), session, this.scopeSession, this.implicitBodyResolveComputationSession, this, bodyResolveContext);
        ((FirDeclaration) CollectionsKt.first(list)).transform(firDesignatedBodyResolveTransformerForReturnTypeCalculator, ResolutionMode.ContextDependent.INSTANCE);
        FirElement lastResult = firDesignatedBodyResolveTransformerForReturnTypeCalculator.getLastResult();
        FirCallableDeclaration firCallableDeclaration = lastResult instanceof FirCallableDeclaration ? (FirCallableDeclaration) lastResult : null;
        if (firCallableDeclaration == null) {
            StringBuilder sb = new StringBuilder("Unexpected lastResult: ");
            FirElement lastResult2 = firDesignatedBodyResolveTransformerForReturnTypeCalculator.getLastResult();
            j37.a(sb, lastResult2 != null ? UtilsKt.render(lastResult2) : null);
            return null;
        }
        FirResolvedTypeRef returnTypeRef = firCallableDeclaration.getReturnTypeRef();
        if (!(returnTypeRef instanceof FirResolvedTypeRef)) {
            b6c.a(UtilsKt.render(firCallableDeclaration));
            return null;
        }
        if (regularTowerDataContexts != null) {
            bodyResolveContext.setRegularTowerDataContexts(regularTowerDataContexts);
        }
        return returnTypeRef;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator
    public FirResolvedTypeRef tryCalculateReturnTypeOrNull(FirCallableDeclaration declaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirNamedFunctionSymbol originalForWrappedIntegerOperator;
        declaration.getClass();
        if (Intrinsics.areEqual(declaration.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
            return ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getDefault().tryCalculateReturnType(declaration);
        }
        if (declaration instanceof FirValueParameter) {
            FirValueParameter firValueParameter = (FirValueParameter) declaration;
            if (firValueParameter.getReturnTypeRef() instanceof FirImplicitTypeRef) {
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Unsupported: implicit VP type", null, 2, null));
                firValueParameter.replaceReturnTypeRef(firErrorTypeRefBuilder.build());
            }
        }
        if ((declaration instanceof FirNamedFunction) && (originalForWrappedIntegerOperator = FirIntegerConstantOperatorScopeKt.getOriginalForWrappedIntegerOperator((FirNamedFunction) declaration)) != null) {
            tryCalculateReturnTypeOrNull((FirCallableDeclaration) originalForWrappedIntegerOperator.getFir());
        }
        resolvedToContractsIfNecessary(declaration);
        FirResolvedTypeRef returnTypeRef = declaration.getReturnTypeRef();
        if (returnTypeRef instanceof FirResolvedTypeRef) {
            return returnTypeRef;
        }
        if (declaration instanceof FirSyntheticProperty) {
            return tryCalculateReturnType(((FirSyntheticProperty) declaration).getGetter().getDelegate());
        }
        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(declaration);
        FirCallableDeclaration wrapped = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
        if (wrapped != null) {
            FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnType = tryCalculateReturnType(wrapped);
            if (declaration.getReturnTypeRef() instanceof FirImplicitTypeRef) {
                declaration.replaceReturnTypeRef(firResolvedTypeRefTryCalculateReturnType);
            }
            return firResolvedTypeRefTryCalculateReturnType;
        }
        if (!ClassMembersKt.getCanHaveDeferredReturnTypeCalculation(declaration)) {
            return computeReturnTypeRef(declaration);
        }
        FirResolvedTypeRef firResolvedTypeRefMo617computeReturnType = getCallableCopyTypeCalculator().mo617computeReturnType(declaration);
        if (firResolvedTypeRefMo617computeReturnType instanceof FirResolvedTypeRef) {
            return firResolvedTypeRefMo617computeReturnType;
        }
        StringBuilder sb = new StringBuilder("Unexpected return type: ");
        sb.append(firResolvedTypeRefMo617computeReturnType != null ? Reflection.getOrCreateKotlinClass(firResolvedTypeRefMo617computeReturnType.getClass()).getSimpleName() : null);
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(sb.toString());
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", declaration);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public /* synthetic */ ReturnTypeCalculatorWithJump(ScopeSession scopeSession, ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession, Map map, FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(scopeSession, implicitBodyResolveComputationSession, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? null : firAbstractBodyResolveTransformerDispatcher, (i & 16) != 0 ? null : bodyResolveContext);
    }
}

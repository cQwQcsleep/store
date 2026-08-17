package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirImplicitAwareBodyResolveTransformer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001c\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J-\u0010\"\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$2\u0006\u0010%\u001a\u0002H#2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H#0'H\u0002¢\u0006\u0002\u0010(R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirImplicitAwareBodyResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirBodyResolveTransformer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "implicitBodyResolveComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "implicitTypeOnly", Argument.Delimiters.none, "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;ZLorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "transformForeignAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "annotationCall", "transformDeclarationContent", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "declaration", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "transformProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "computeCachedTransformationResult", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "member", "transform", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lkotlin/jvm/functions/Function0;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirImplicitAwareBodyResolveTransformer extends FirBodyResolveTransformer {
    private final ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirImplicitAwareBodyResolveTransformer(FirSession firSession, ScopeSession scopeSession, ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession, FirResolvePhase firResolvePhase, boolean z, ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump, BodyResolveContext bodyResolveContext) {
        super(firSession, firResolvePhase, z, scopeSession, returnTypeCalculatorWithJump, bodyResolveContext);
        firSession.getClass();
        scopeSession.getClass();
        implicitBodyResolveComputationSession.getClass();
        firResolvePhase.getClass();
        returnTypeCalculatorWithJump.getClass();
        this.implicitBodyResolveComputationSession = implicitBodyResolveComputationSession;
    }

    public static FirProperty b(FirImplicitAwareBodyResolveTransformer firImplicitAwareBodyResolveTransformer, FirProperty firProperty, ResolutionMode resolutionMode) {
        return super.transformProperty(firProperty, resolutionMode);
    }

    public static FirNamedFunction c(FirImplicitAwareBodyResolveTransformer firImplicitAwareBodyResolveTransformer, FirNamedFunction firNamedFunction, ResolutionMode resolutionMode) {
        return super.transformNamedFunction(firNamedFunction, resolutionMode);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0031  */
    private final <D extends FirCallableDeclaration> D computeCachedTransformationResult(D member, Function0<? extends D> transform) {
        boolean z;
        if (!getImplicitTypeOnly() && (member.getReturnTypeRef() instanceof FirResolvedTypeRef)) {
            return (D) transform.invoke();
        }
        boolean z2 = member instanceof FirProperty;
        boolean z3 = false;
        if (z2) {
            FirBackingField backingField = ((FirProperty) member).getBackingField();
            if ((backingField != null ? backingField.getReturnTypeRef() : null) instanceof FirResolvedTypeRef) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        if (z2 && member.getStatus().isConst()) {
            z3 = true;
        }
        if ((member.getReturnTypeRef() instanceof FirResolvedTypeRef) && !z && !z3) {
            return member;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = member.getSymbol();
        ImplicitBodyResolveComputationStatus status$org_jetbrains_kotlin_resolve = this.implicitBodyResolveComputationSession.getStatus$org_jetbrains_kotlin_resolve(symbol);
        if (status$org_jetbrains_kotlin_resolve instanceof ImplicitBodyResolveComputationStatus.Computed) {
            D d = (D) ((ImplicitBodyResolveComputationStatus.Computed) status$org_jetbrains_kotlin_resolve).getTransformedDeclaration();
            d.getClass();
            return d;
        }
        if (status$org_jetbrains_kotlin_resolve instanceof ImplicitBodyResolveComputationStatus.NotComputed) {
            return (D) this.implicitBodyResolveComputationSession.compute$org_jetbrains_kotlin_resolve(symbol, transform);
        }
        StringBuilder sb = new StringBuilder("Unexpected status in transformCallableMember (");
        sb.append(status$org_jetbrains_kotlin_resolve);
        ywd.a(sb, ") for ", UtilsKt.render(member));
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public FirDeclaration transformDeclarationContent(FirDeclaration declaration, ResolutionMode data) {
        declaration.getClass();
        data.getClass();
        if (getImplicitTypeOnly() && (declaration instanceof FirRegularClass)) {
            FirRegularClass firRegularClass = (FirRegularClass) declaration;
            if (!firRegularClass.getIsLocal()) {
                return firRegularClass.transformDeclarations((FirTransformer<? super ResolutionMode>) this, data);
            }
        }
        return super.transformDeclarationContent(declaration, data);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public FirAnnotationCall transformForeignAnnotationCall(FirBasedSymbol<?> symbol, FirAnnotationCall annotationCall) {
        FirAnnotationCall firAnnotationCallTransformForeignAnnotationCall;
        symbol.getClass();
        annotationCall.getClass();
        ReturnTypeCalculator returnTypeCalculator = getReturnTypeCalculator();
        returnTypeCalculator.getClass();
        FirAbstractBodyResolveTransformerDispatcher outerTransformer = ((ReturnTypeCalculatorWithJump) returnTypeCalculator).getOuterTransformer();
        return (outerTransformer == null || (firAnnotationCallTransformForeignAnnotationCall = outerTransformer.transformForeignAnnotationCall(symbol, annotationCall)) == null) ? annotationCall : firAnnotationCallTransformForeignAnnotationCall;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirNamedFunction transformNamedFunction(final FirNamedFunction namedFunction, final ResolutionMode data) {
        namedFunction.getClass();
        data.getClass();
        return (FirNamedFunction) computeCachedTransformationResult(namedFunction, new Function0() { // from class: p85
            public final Object invoke() {
                return FirImplicitAwareBodyResolveTransformer.c(this.b, namedFunction, data);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirProperty transformProperty(final FirProperty property, final ResolutionMode data) {
        property.getClass();
        data.getClass();
        return (FirProperty) computeCachedTransformationResult(property, new Function0() { // from class: o85
            public final Object invoke() {
                return FirImplicitAwareBodyResolveTransformer.b(this.b, property, data);
            }
        });
    }

    public /* synthetic */ FirImplicitAwareBodyResolveTransformer(FirSession firSession, ScopeSession scopeSession, ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession, FirResolvePhase firResolvePhase, boolean z, ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, implicitBodyResolveComputationSession, firResolvePhase, z, returnTypeCalculatorWithJump, (i & 64) != 0 ? null : bodyResolveContext);
    }
}

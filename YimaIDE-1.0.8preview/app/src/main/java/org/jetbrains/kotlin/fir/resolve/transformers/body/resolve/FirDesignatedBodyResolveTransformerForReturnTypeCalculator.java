package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001BA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDesignatedBodyResolveTransformerForReturnTypeCalculator;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirImplicitAwareBodyResolveTransformer;", "designation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "implicitBodyResolveComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Ljava/util/Iterator;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "lastResult", "getLastResult", "()Lorg/jetbrains/kotlin/fir/FirElement;", "setLastResult", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "transformDeclarationContent", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "declaration", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirDesignatedBodyResolveTransformerForReturnTypeCalculator extends FirImplicitAwareBodyResolveTransformer {
    private final Iterator<FirElement> designation;
    private FirElement lastResult;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FirDesignatedBodyResolveTransformerForReturnTypeCalculator(Iterator<? extends FirElement> it, FirSession firSession, ScopeSession scopeSession, ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession, ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump, BodyResolveContext bodyResolveContext) {
        super(firSession, scopeSession, implicitBodyResolveComputationSession, FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE, true, returnTypeCalculatorWithJump, bodyResolveContext);
        it.getClass();
        firSession.getClass();
        scopeSession.getClass();
        implicitBodyResolveComputationSession.getClass();
        returnTypeCalculatorWithJump.getClass();
        this.designation = it;
    }

    public final FirElement getLastResult() {
        return this.lastResult;
    }

    public final void setLastResult(FirElement firElement) {
        this.lastResult = firElement;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirImplicitAwareBodyResolveTransformer, org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public FirDeclaration transformDeclarationContent(FirDeclaration declaration, ResolutionMode data) {
        declaration.getClass();
        data.getClass();
        if (!this.designation.hasNext()) {
            return super.transformDeclarationContent(declaration, data);
        }
        FirDeclaration firDeclaration = (FirDeclaration) this.designation.next().transform(this, data);
        if (!this.designation.hasNext() && this.lastResult == null) {
            this.lastResult = firDeclaration;
        }
        return declaration;
    }

    public /* synthetic */ FirDesignatedBodyResolveTransformerForReturnTypeCalculator(Iterator it, FirSession firSession, ScopeSession scopeSession, ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession, ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(it, firSession, scopeSession, implicitBodyResolveComputationSession, returnTypeCalculatorWithJump, (i & 32) != 0 ? null : bodyResolveContext);
    }
}

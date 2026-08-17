package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\b\u001a\u00020\t*\u00020\nH\u0014J\f\u0010\u000b\u001a\u00020\t*\u00020\nH\u0014J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirDesignatedStatusResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/AbstractFirStatusResolveTransformer;", "designation", "Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState;", "statusComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState;Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;)V", "needResolveMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "needResolveNestedClassifiers", "transformClassContent", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "data", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirDesignatedStatusResolveTransformer extends AbstractFirStatusResolveTransformer {
    private final DesignationState designation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDesignatedStatusResolveTransformer(DesignationState designationState, StatusComputationSession statusComputationSession) {
        super(statusComputationSession);
        designationState.getClass();
        statusComputationSession.getClass();
        this.designation = designationState;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.AbstractFirStatusResolveTransformer
    public boolean needResolveMembers(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return this.designation.getClassLocated();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.AbstractFirStatusResolveTransformer
    public boolean needResolveNestedClassifiers(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return !this.designation.getClassLocated();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.AbstractFirStatusResolveTransformer
    public FirStatement transformClassContent(FirClass firClass, FirResolvedDeclarationStatus data) {
        firClass.getClass();
        FirSession session = getSession();
        try {
            if (this.designation.shouldSkipClass(firClass)) {
                return firClass;
            }
            FirLazyDeclarationResolverKt.lazyResolveToPhase(firClass.getSymbol(), FirResolvePhase.TYPES);
            boolean classLocated = this.designation.getClassLocated();
            if (classLocated) {
                Intrinsics.areEqual(firClass, this.designation.getTargetClass());
                StatusComputationSession.StatusComputationStatus statusComputationStatusStartComputing = getStatusComputationSession().startComputing(firClass);
                getStatusComputationSession().forceResolveStatusesOfSupertypes(firClass);
                if (statusComputationStatusStartComputing != StatusComputationSession.StatusComputationStatus.Computed) {
                    firClass.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, getStatusResolver().resolveStatus(firClass, getContainingClass(), false));
                }
            } else if (!(firClass.getStatus() instanceof FirResolvedDeclarationStatus)) {
                firClass.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, getStatusResolver().resolveStatus(firClass, getContainingClass(), false));
                getStatusComputationSession().computeOnlyClassStatus(firClass);
            }
            FirStatement firStatementTransformClass = transformClass(firClass, data);
            if (classLocated) {
                getStatusComputationSession().endComputing(firClass);
            }
            return firStatementTransformClass;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firClass, th);
            wq6.a();
            return null;
        }
    }
}

package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.expressions.FirStatement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0014J\f\u0010\t\u001a\u00020\u0007*\u00020\bH\u0014J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirStatusResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/AbstractFirStatusResolveTransformer;", "statusComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;)V", "needResolveMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "needResolveNestedClassifiers", "transformClassContent", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "data", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirStatusResolveTransformer extends AbstractFirStatusResolveTransformer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirStatusResolveTransformer(StatusComputationSession statusComputationSession) {
        super(statusComputationSession);
        statusComputationSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.AbstractFirStatusResolveTransformer
    public boolean needResolveMembers(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return ((firDeclaration instanceof FirRegularClass) && getStatusComputationSession().get((FirClass) firDeclaration) == StatusComputationSession.StatusComputationStatus.Computed) ? false : true;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.AbstractFirStatusResolveTransformer
    public boolean needResolveNestedClassifiers(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.AbstractFirStatusResolveTransformer
    public FirStatement transformClassContent(FirClass firClass, FirResolvedDeclarationStatus data) {
        firClass.getClass();
        StatusComputationSession.StatusComputationStatus statusComputationStatusStartComputing = getStatusComputationSession().startComputing(firClass);
        getStatusComputationSession().forceResolveStatusesOfSupertypes(firClass);
        if (statusComputationStatusStartComputing != StatusComputationSession.StatusComputationStatus.Computed) {
            transformClassStatus(firClass);
            transformValueClassRepresentation(firClass);
        }
        FirStatement firStatementTransformClass = transformClass(firClass, data);
        getStatusComputationSession().endComputing(firClass);
        return firStatementTransformClass;
    }
}

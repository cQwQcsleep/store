package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedForWarningConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR*\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/FirJavaNullabilityWarningUpperBoundsProvider;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "substitutor", "Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider$PlatformUpperBoundViolatedDiagnosticFactory;", "getDiagnostic", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "diagnosticForTypeAlias", "getDiagnosticForTypeAlias", "getAdditionalUpperBound", "coneKotlinType", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaNullabilityWarningUpperBoundsProvider extends FirPlatformUpperBoundsProvider {
    private final EnhancedForWarningConeSubstitutor substitutor;

    public FirJavaNullabilityWarningUpperBoundsProvider(FirSession firSession) {
        firSession.getClass();
        this.substitutor = new EnhancedForWarningConeSubstitutor(TypeComponentsKt.getTypeContext(firSession), null, 2, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider
    public ConeKotlinType getAdditionalUpperBound(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return this.substitutor.substituteOrNull(coneKotlinType);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider
    public KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getDiagnostic() {
        return FirJvmErrors.INSTANCE.getUPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider
    public KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getDiagnosticForTypeAlias() {
        return FirJvmErrors.INSTANCE.getUPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS();
    }
}

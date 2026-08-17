package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u0006H&J\u001a\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\u0011H\u0017b\u0002\b\u0012R(\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR(\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tZ3\b\u0004\u0010\u0013\"\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u001a¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider$PlatformUpperBoundViolatedDiagnosticFactory;", "getDiagnostic", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "diagnosticForTypeAlias", "getDiagnosticForTypeAlias", "getAdditionalUpperBound", "coneKotlinType", "createComposed", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "PlatformUpperBoundViolatedDiagnosticFactory", "Composed", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPlatformUpperBoundsProvider implements FirComposableSessionComponent<FirPlatformUpperBoundsProvider> {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR*\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nj\u0002`\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nj\u0002`\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider$Composed;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider$PlatformUpperBoundViolatedDiagnosticFactory;", "getDiagnostic", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "diagnosticForTypeAlias", "getDiagnosticForTypeAlias", "getAdditionalUpperBound", "coneKotlinType", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirPlatformUpperBoundsProvider implements FirComposableSessionComponent.Composed<FirPlatformUpperBoundsProvider> {
        private final List<FirPlatformUpperBoundsProvider> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirPlatformUpperBoundsProvider> list) {
            list.getClass();
            this.components = list;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider
        public ConeKotlinType getAdditionalUpperBound(ConeKotlinType coneKotlinType) throws KotlinNothingValueException {
            coneKotlinType.getClass();
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            throw new KotlinNothingValueException();
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirPlatformUpperBoundsProvider> getComponents() {
            return this.components;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider
        public KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getDiagnostic() throws KotlinNothingValueException {
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            throw new KotlinNothingValueException();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider
        public KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getDiagnosticForTypeAlias() throws KotlinNothingValueException {
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirPlatformUpperBoundsProvider> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract ConeKotlinType getAdditionalUpperBound(ConeKotlinType coneKotlinType);

    public abstract KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getDiagnostic();

    public abstract KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getDiagnosticForTypeAlias();

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirPlatformUpperBoundsProvider>) list);
    }
}

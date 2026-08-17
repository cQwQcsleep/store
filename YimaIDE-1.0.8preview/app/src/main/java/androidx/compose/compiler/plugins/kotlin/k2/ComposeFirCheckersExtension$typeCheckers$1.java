package androidx.compose.compiler.plugins.kotlin.k2;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R$\u0010\u0002\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u00060\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/compose/compiler/plugins/kotlin/k2/ComposeFirCheckersExtension$typeCheckers$1", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "resolvedTypeRefCheckers", "", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "getResolvedTypeRefCheckers", "()Ljava/util/Set;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeFirCheckersExtension$typeCheckers$1 extends TypeCheckers {
    private final Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers = SetsKt.setOf(ComposableAnnotationChecker.INSTANCE);

    public Set<FirTypeChecker<FirResolvedTypeRef>> getResolvedTypeRefCheckers() {
        return this.resolvedTypeRefCheckers;
    }
}

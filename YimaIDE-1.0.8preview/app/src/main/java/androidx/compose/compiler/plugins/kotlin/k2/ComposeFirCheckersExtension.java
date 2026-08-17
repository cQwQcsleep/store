package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposeFirCheckersExtension;", "Lorg/jetbrains/kotlin/fir/analysis/extensions/FirAdditionalCheckersExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "declarationCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "getDeclarationCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "typeCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "getTypeCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "expressionCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "getExpressionCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposeFirCheckersExtension extends FirAdditionalCheckersExtension {
    private final DeclarationCheckers declarationCheckers;
    private final ExpressionCheckers expressionCheckers;
    private final TypeCheckers typeCheckers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposeFirCheckersExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.declarationCheckers = new declarationCheckers.1();
        this.typeCheckers = new typeCheckers.1();
        this.expressionCheckers = new expressionCheckers.1();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
    public DeclarationCheckers getDeclarationCheckers() {
        return this.declarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
    public ExpressionCheckers getExpressionCheckers() {
        return this.expressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
    public TypeCheckers getTypeCheckers() {
        return this.typeCheckers;
    }
}

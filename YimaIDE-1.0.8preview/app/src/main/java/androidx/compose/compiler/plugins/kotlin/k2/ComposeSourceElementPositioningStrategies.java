package androidx.compose.compiler.plugins.kotlin.k2;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy;
import org.jetbrains.kotlin.diagnostics.OffsetsOnlyPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.PositioningStrategies;
import org.jetbrains.kotlin.diagnostics.PositioningStrategy;
import org.jetbrains.kotlin.diagnostics.PositioningStrategyKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.psi.KtTryExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposeSourceElementPositioningStrategies;", "", "<init>", "()V", "PSI_TRY_KEYWORD", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lorg/jetbrains/kotlin/psi/KtTryExpression;", "LIGHT_TREE_TRY_KEYWORD", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "PSI_DECLARATION_NAME_OR_DEFAULT", "Lcom/intellij/psi/PsiElement;", "TRY_KEYWORD", "Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;", "getTRY_KEYWORD", "()Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;", "DECLARATION_NAME_OR_DEFAULT", "getDECLARATION_NAME_OR_DEFAULT", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeSourceElementPositioningStrategies {
    private static final SourceElementPositioningStrategy DECLARATION_NAME_OR_DEFAULT;
    public static final ComposeSourceElementPositioningStrategies INSTANCE = new ComposeSourceElementPositioningStrategies();
    private static final LightTreePositioningStrategy LIGHT_TREE_TRY_KEYWORD;
    private static final PositioningStrategy<PsiElement> PSI_DECLARATION_NAME_OR_DEFAULT;
    private static final PositioningStrategy<KtTryExpression> PSI_TRY_KEYWORD;
    private static final SourceElementPositioningStrategy TRY_KEYWORD;

    static {
        PositioningStrategy<KtTryExpression> positioningStrategy = new PositioningStrategy<KtTryExpression>() { // from class: androidx.compose.compiler.plugins.kotlin.k2.ComposeSourceElementPositioningStrategies$PSI_TRY_KEYWORD$1
            public List<TextRange> mark(KtTryExpression element) {
                element.getClass();
                PsiElement tryKeyword = element.getTryKeyword();
                return tryKeyword != null ? PositioningStrategyKt.markElement(tryKeyword) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        PSI_TRY_KEYWORD = positioningStrategy;
        LIGHT_TREE_TRY_KEYWORD.1 r2 = new LIGHT_TREE_TRY_KEYWORD.1();
        LIGHT_TREE_TRY_KEYWORD = r2;
        PositioningStrategy<PsiElement> positioningStrategy2 = new PositioningStrategy<PsiElement>() { // from class: androidx.compose.compiler.plugins.kotlin.k2.ComposeSourceElementPositioningStrategies$PSI_DECLARATION_NAME_OR_DEFAULT$1
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                return element instanceof KtNamedDeclaration ? PositioningStrategies.INSTANCE.getDECLARATION_NAME().mark(element) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        PSI_DECLARATION_NAME_OR_DEFAULT = positioningStrategy2;
        TRY_KEYWORD = new SourceElementPositioningStrategy(r2, positioningStrategy, (OffsetsOnlyPositioningStrategy) null, 4, (DefaultConstructorMarker) null);
        DECLARATION_NAME_OR_DEFAULT = new SourceElementPositioningStrategy(LightTreePositioningStrategies.INSTANCE.getDECLARATION_NAME(), positioningStrategy2, (OffsetsOnlyPositioningStrategy) null, 4, (DefaultConstructorMarker) null);
    }

    private ComposeSourceElementPositioningStrategies() {
    }

    public final SourceElementPositioningStrategy getDECLARATION_NAME_OR_DEFAULT() {
        return DECLARATION_NAME_OR_DEFAULT;
    }

    public final SourceElementPositioningStrategy getTRY_KEYWORD() {
        return TRY_KEYWORD;
    }
}

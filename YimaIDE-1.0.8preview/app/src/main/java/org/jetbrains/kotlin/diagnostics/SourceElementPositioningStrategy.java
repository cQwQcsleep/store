package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "lightTreeStrategy", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "psiStrategy", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "offsetsOnlyPositioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/OffsetsOnlyPositioningStrategy;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/OffsetsOnlyPositioningStrategy;)V", "markDiagnostic", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "isValid", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "hackyIsValid", "psi", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceElementPositioningStrategy extends AbstractSourceElementPositioningStrategy {
    private final LightTreePositioningStrategy lightTreeStrategy;
    private final OffsetsOnlyPositioningStrategy offsetsOnlyPositioningStrategy;
    private final PositioningStrategy<?> psiStrategy;

    public SourceElementPositioningStrategy(LightTreePositioningStrategy lightTreePositioningStrategy, PositioningStrategy<?> positioningStrategy, OffsetsOnlyPositioningStrategy offsetsOnlyPositioningStrategy) {
        lightTreePositioningStrategy.getClass();
        positioningStrategy.getClass();
        offsetsOnlyPositioningStrategy.getClass();
        this.lightTreeStrategy = lightTreePositioningStrategy;
        this.psiStrategy = positioningStrategy;
        this.offsetsOnlyPositioningStrategy = offsetsOnlyPositioningStrategy;
    }

    private final boolean hackyIsValid(PositioningStrategy<?> positioningStrategy, PsiElement psiElement) {
        positioningStrategy.getClass();
        return positioningStrategy.isValid(psiElement);
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy
    public boolean isValid(AbstractKtSourceElement element) {
        element.getClass();
        if (element instanceof KtPsiSourceElement) {
            return hackyIsValid(this.psiStrategy, ((KtPsiSourceElement) element).getPsi());
        }
        if (!(element instanceof KtLightSourceElement)) {
            return true;
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) element;
        return this.lightTreeStrategy.isValid(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure());
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy
    public List<TextRange> markDiagnostic(KtDiagnosticWithSource diagnostic) {
        diagnostic.getClass();
        KtSourceElement element = diagnostic.getElement();
        if (element instanceof KtPsiSourceElement) {
            return this.psiStrategy.markDiagnostic(diagnostic);
        }
        return element instanceof KtLightSourceElement ? this.lightTreeStrategy.markKtDiagnostic(element, diagnostic) : this.offsetsOnlyPositioningStrategy.markKtDiagnostic(element, diagnostic);
    }

    public /* synthetic */ SourceElementPositioningStrategy(LightTreePositioningStrategy lightTreePositioningStrategy, PositioningStrategy positioningStrategy, OffsetsOnlyPositioningStrategy offsetsOnlyPositioningStrategy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lightTreePositioningStrategy, positioningStrategy, (i & 4) != 0 ? new OffsetsOnlyPositioningStrategy() : offsetsOnlyPositioningStrategy);
    }
}

package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cfg.UnreachableCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PsiPositioningStrategies;", Argument.Delimiters.none, "<init>", "()V", "UNREACHABLE_CODE", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lcom/intellij/psi/PsiElement;", "getUNREACHABLE_CODE", "()Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "ACTUAL_DECLARATION_NAME", "getACTUAL_DECLARATION_NAME", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiPositioningStrategies {
    public static final PsiPositioningStrategies INSTANCE = new PsiPositioningStrategies();
    private static final PositioningStrategy<PsiElement> UNREACHABLE_CODE = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PsiPositioningStrategies$UNREACHABLE_CODE$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> markDiagnostic(DiagnosticMarker diagnostic) {
            diagnostic.getClass();
            KtDiagnosticWithParameters2 ktDiagnosticWithParameters2 = (KtDiagnosticWithParameters2) diagnostic;
            KtPsiSourceElement element = ktDiagnosticWithParameters2.getElement();
            element.getClass();
            UnreachableCode.Companion companion = UnreachableCode.Companion;
            KtElement psi = element.getPsi();
            psi.getClass();
            KtElement ktElement = psi;
            Iterable iterable = (Iterable) ktDiagnosticWithParameters2.getA();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PsiElement psi2 = KtSourceElementKt.getPsi((KtSourceElement) it.next());
                KtElement ktElement2 = psi2 instanceof KtElement ? (KtElement) psi2 : null;
                if (ktElement2 != null) {
                    arrayList.add(ktElement2);
                }
            }
            Set set = CollectionsKt.toSet(arrayList);
            Iterable iterable2 = (Iterable) ktDiagnosticWithParameters2.getB();
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = iterable2.iterator();
            while (it2.hasNext()) {
                KtElement psi3 = KtSourceElementKt.getPsi((KtSourceElement) it2.next());
                KtElement ktElement3 = psi3 instanceof KtElement ? psi3 : null;
                if (ktElement3 != null) {
                    arrayList2.add(ktElement3);
                }
            }
            return companion.getUnreachableTextRanges(ktElement, set, CollectionsKt.toSet(arrayList2));
        }
    };
    private static final PositioningStrategy<PsiElement> ACTUAL_DECLARATION_NAME = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PsiPositioningStrategies$ACTUAL_DECLARATION_NAME$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> markDiagnostic(DiagnosticMarker diagnostic) {
            PsiElement nameIdentifier;
            diagnostic.getClass();
            if (!(diagnostic instanceof KtDiagnosticWithSource)) {
                w01.a("Failed requirement.");
                return null;
            }
            KtNamedDeclaration psi = KtSourceElementKt.getPsi(((KtDiagnosticWithSource) diagnostic).getElement());
            if (psi == null) {
                return CollectionsKt.emptyList();
            }
            KtNamedDeclaration ktNamedDeclaration = psi instanceof KtNamedDeclaration ? psi : null;
            return (ktNamedDeclaration == null || (nameIdentifier = ktNamedDeclaration.getNameIdentifier()) == null) ? mark(psi) : mark(nameIdentifier);
        }
    };

    private PsiPositioningStrategies() {
    }

    public final PositioningStrategy<PsiElement> getACTUAL_DECLARATION_NAME() {
        return ACTUAL_DECLARATION_NAME;
    }

    public final PositioningStrategy<PsiElement> getUNREACHABLE_CODE() {
        return UNREACHABLE_CODE;
    }
}

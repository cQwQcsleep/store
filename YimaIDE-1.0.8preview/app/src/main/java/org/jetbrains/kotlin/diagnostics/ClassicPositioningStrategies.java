package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cfg.UnreachableCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R \u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t*\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/ClassicPositioningStrategies;", Argument.Delimiters.none, "<init>", "()V", "ACTUAL_DECLARATION_NAME", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lorg/jetbrains/kotlin/psi/KtNamedDeclaration;", "Lkotlin/jvm/JvmField;", "firstIncompatibility", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "getFirstIncompatibility", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;)Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "INCOMPATIBLE_DECLARATION", "UNREACHABLE_CODE", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassicPositioningStrategies {
    public static final ClassicPositioningStrategies INSTANCE = new ClassicPositioningStrategies();
    public static final PositioningStrategy<KtNamedDeclaration> ACTUAL_DECLARATION_NAME = new PositioningStrategies.DeclarationHeader<KtNamedDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.ClassicPositioningStrategies$ACTUAL_DECLARATION_NAME$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(KtNamedDeclaration element) {
            element.getClass();
            PsiElement nameIdentifier = element.getNameIdentifier();
            if (nameIdentifier != null) {
                return PositioningStrategyKt.markElement(nameIdentifier);
            }
            return element instanceof KtNamedFunction ? PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.mark(element) : PositioningStrategies.DEFAULT.mark(element);
        }
    };
    public static final PositioningStrategy<KtNamedDeclaration> INCOMPATIBLE_DECLARATION = new INCOMPATIBLE_DECLARATION.1();
    public static final PositioningStrategy<PsiElement> UNREACHABLE_CODE = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.ClassicPositioningStrategies$UNREACHABLE_CODE$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> markDiagnostic(DiagnosticMarker diagnostic) {
            diagnostic.getClass();
            DiagnosticWithParameters2Marker diagnosticWithParameters2Marker = (DiagnosticWithParameters2Marker) diagnostic;
            UnreachableCode.Companion companion = UnreachableCode.Companion;
            KtElement psiElement = diagnosticWithParameters2Marker.getPsiElement();
            psiElement.getClass();
            return companion.getUnreachableTextRanges(psiElement, (Set) diagnosticWithParameters2Marker.getA(), (Set) diagnosticWithParameters2Marker.getB());
        }
    };

    private ClassicPositioningStrategies() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final K1ExpectActualCompatibility.Incompatible<MemberDescriptor> getFirstIncompatibility(DiagnosticMarker diagnosticMarker) {
        Object b;
        String factoryName = diagnosticMarker.getFactoryName();
        if (Intrinsics.areEqual(factoryName, Errors.NO_ACTUAL_FOR_EXPECT.getName())) {
            b = ((DiagnosticWithParameters3Marker) diagnosticMarker).getC();
        } else {
            b = Intrinsics.areEqual(factoryName, Errors.ACTUAL_WITHOUT_EXPECT.getName()) ? ((DiagnosticWithParameters2Marker) diagnosticMarker).getB() : null;
        }
        Map map = b instanceof Map ? (Map) b : null;
        if (map == null) {
            return null;
        }
        return (K1ExpectActualCompatibility.Incompatible) CollectionsKt.firstOrNull(map.keySet());
    }
}

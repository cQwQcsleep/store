package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0018\u0010\u0011\u001a\u00020\u0012*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNestedClassChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "isInsideAnonymousObject", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "description", Argument.Delimiters.none, "getDescription", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/String;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNestedClassChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirNestedClassChecker INSTANCE = new FirNestedClassChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ClassKind.OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirNestedClassChecker() {
        super(MppCheckerKind.Common);
    }

    private final String getDescription(FirRegularClass firRegularClass) {
        switch (WhenMappings.$EnumSwitchMapping$0[firRegularClass.getClassKind().ordinal()]) {
            case 1:
                return "Class";
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return "Interface";
            case 3:
                return "Enum class";
            case 4:
                return "Enum entry";
            case 5:
                return "Annotation class";
            case 6:
                return firRegularClass.getStatus().isCompanion() ? "Companion object" : "Object";
            default:
                bu8.a();
                return null;
        }
    }

    private final boolean isInsideAnonymousObject(CheckerContext checkerContext) {
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        if ((containingDeclarations instanceof Collection) && containingDeclarations.isEmpty()) {
            return false;
        }
        Iterator<T> it = containingDeclarations.iterator();
        while (it.hasNext()) {
            if (((FirBasedSymbol) it.next()) instanceof FirAnonymousObjectSymbol) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (DeclarationAttributesKt.isReplSnippetDeclaration(firRegularClass) != null) {
            return;
        }
        boolean zIsCompanion = firRegularClass.getStatus().isCompanion();
        if (zIsCompanion || !((firRegularClass.getClassKind().isSingleton() || firRegularClass.getClassKind() == ClassKind.ENUM_CLASS) && firRegularClass.getIsLocal())) {
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirClassSymbol firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
            if (firClassSymbol == null) {
                return;
            }
            if (!zIsCompanion || firClassSymbol.getRawStatus().isInner()) {
                if (firClassSymbol.getClassKind() == ClassKind.ENUM_ENTRY && !firRegularClass.getStatus().isInner() && !zIsCompanion) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNESTED_CLASS_NOT_ALLOWED(), (Object) getDescription(firRegularClass), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
                boolean z = Intrinsics.areEqual(firClassSymbol.getResolvedStatus().getEffectiveVisibility(), EffectiveVisibility.Local.INSTANCE) && !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firClassSymbol), Boolean.TRUE);
                if (firRegularClass.getStatus().isInner()) {
                    return;
                }
                if (firClassSymbol.getRawStatus().isInner() || z || isInsideAnonymousObject(checkerContext)) {
                    if (firRegularClass.getIsLocal() && zIsCompanion) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactoryForDeprecation1) FirErrors.INSTANCE.getNESTED_CLASS_NOT_ALLOWED_IN_LOCAL(), (Object) getDescription(firRegularClass), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNESTED_CLASS_NOT_ALLOWED(), (Object) getDescription(firRegularClass), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
            }
        }
    }
}

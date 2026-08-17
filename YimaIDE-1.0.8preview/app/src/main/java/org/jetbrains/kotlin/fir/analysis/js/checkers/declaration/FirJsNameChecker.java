package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsNameChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsNameChecker INSTANCE = new FirJsNameChecker();

    private FirJsNameChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firDeclaration;
            FirPropertyAccessor getter = firProperty.getGetter();
            FirPropertyAccessorSymbol symbol = getter != null ? getter.getSymbol() : null;
            FirPropertyAccessor setter = firProperty.getSetter();
            List listListOfNotNull = CollectionsKt.listOfNotNull(new FirPropertyAccessorSymbol[]{symbol, setter != null ? setter.getSymbol() : null});
            List list = listListOfNotNull;
            int i = 0;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (FirJsHelpersKt.getJsName((FirPropertyAccessorSymbol) it.next(), checkerContext.getSession()) != null && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (i > 0 && i < listListOfNotNull.size()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirJsErrors.INSTANCE.getJS_NAME_IS_NOT_ON_ALL_ACCESSORS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, JsStandardClassIds.Annotations.JsName, checkerContext.getSession());
        if (annotationByClassId == null) {
            return;
        }
        KtSourceElement source = annotationByClassId.getSource();
        if (source == null) {
            source = firDeclaration.getSource();
        }
        KtSourceElement ktSourceElement = source;
        if (FirHelpersKt.getAnnotationStringParameter(firDeclaration.getSymbol(), JsStandardClassIds.Annotations.JsNative, checkerContext.getSession()) != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_NAME_PROHIBITED_FOR_NAMED_NATIVE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (((firDeclaration instanceof FirCallableDeclaration) && ((FirMemberDeclaration) firDeclaration).getStatus().isOverride()) || ((firDeclaration instanceof FirPropertyAccessor) && ((FirPropertyAccessor) firDeclaration).getPropertySymbol().getResolvedStatus().isOverride())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_NAME_PROHIBITED_FOR_OVERRIDE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (firDeclaration instanceof FirConstructor) {
            if (((FirConstructor) firDeclaration).getIsPrimary()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        } else if (firDeclaration instanceof FirPropertyAccessor) {
            if (FirJsHelpersKt.getJsName(((FirPropertyAccessor) firDeclaration).getPropertySymbol(), checkerContext.getSession()) != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_NAME_ON_ACCESSOR_AND_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        } else if ((firDeclaration instanceof FirProperty) && FirDeclarationUtilKt.isExtension((FirCallableDeclaration) firDeclaration)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}

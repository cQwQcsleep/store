package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0013J,\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "<init>", "()V", "hidesFromObjCClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getHidesFromObjCClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "refinesInSwiftClassId", "getRefinesInSwiftClassId", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "findRefinedAnnotations", "Lkotlin/Pair;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCRefinementChecker extends FirDeclarationChecker<FirCallableDeclaration> {
    public static final FirNativeObjCRefinementChecker INSTANCE = new FirNativeObjCRefinementChecker();
    private static final ClassId hidesFromObjCClassId;
    private static final ClassId refinesInSwiftClassId;

    static {
        ClassId.Companion companion = ClassId.Companion;
        hidesFromObjCClassId = companion.topLevel(new FqName("kotlin.native.HidesFromObjC"));
        refinesInSwiftClassId = companion.topLevel(new FqName("kotlin.native.RefinesInSwift"));
    }

    private FirNativeObjCRefinementChecker() {
        super(MppCheckerKind.Platform);
    }

    private final Pair<List<FirAnnotation>, List<FirAnnotation>> findRefinedAnnotations(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (FirAnnotation firAnnotation : firCallableDeclaration.getAnnotations()) {
            FirClassLikeSymbol<?> annotationClassLikeSymbol = FirAnnotationUtilsKt.toAnnotationClassLikeSymbol(firAnnotation, firSession);
            List<FirAnnotation> resolvedAnnotationsWithClassIds = annotationClassLikeSymbol != null ? annotationClassLikeSymbol.getResolvedAnnotationsWithClassIds() : null;
            if (resolvedAnnotationsWithClassIds == null) {
                resolvedAnnotationsWithClassIds = CollectionsKt.emptyList();
            }
            Iterator<FirAnnotation> it = resolvedAnnotationsWithClassIds.iterator();
            while (it.hasNext()) {
                ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId(it.next(), firSession);
                if (Intrinsics.areEqual(annotationClassId, hidesFromObjCClassId)) {
                    arrayList.add(firAnnotation);
                    break;
                }
                if (Intrinsics.areEqual(annotationClassId, refinesInSwiftClassId)) {
                    arrayList2.add(firAnnotation);
                    break;
                }
            }
        }
        return TuplesKt.to(arrayList, arrayList2);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if ((firCallableDeclaration instanceof FirNamedFunction) || (firCallableDeclaration instanceof FirProperty)) {
            Pair<List<FirAnnotation>, List<FirAnnotation>> pairFindRefinedAnnotations = findRefinedAnnotations(firCallableDeclaration, checkerContext.getSession());
            List<? extends FirAnnotation> list = (List) pairFindRefinedAnnotations.component1();
            List<? extends FirAnnotation> list2 = (List) pairFindRefinedAnnotations.component2();
            if (!list.isEmpty() && !list2.isEmpty()) {
                Iterator<? extends FirAnnotation> it = list2.iterator();
                while (it.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it.next().getSource(), FirNativeErrors.INSTANCE.getREDUNDANT_SWIFT_REFINEMENT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirClassSymbol firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
            if (firClassSymbol != null) {
                FirNativeObjCRefinementOverridesChecker.INSTANCE.check(checkerContext, diagnosticReporter, FirHelpersKt.unsubstitutedScope(checkerContext, (FirClassSymbol<?>) firClassSymbol), firCallableDeclaration.getSymbol(), firCallableDeclaration, list, list2);
            }
        }
    }

    public final ClassId getHidesFromObjCClassId() {
        return hidesFromObjCClassId;
    }

    public final ClassId getRefinesInSwiftClassId() {
        return refinesInSwiftClassId;
    }
}

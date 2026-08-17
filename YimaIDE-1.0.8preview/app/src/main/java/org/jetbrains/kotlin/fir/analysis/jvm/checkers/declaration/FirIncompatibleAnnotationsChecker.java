package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.EnumValueArgumentInfo;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.utils.KotlinToJavaAnnotationTargetsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ3\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirIncompatibleAnnotationsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "reportIncompatibleTargets", "kotlinTarget", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "javaTarget", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "extractArguments", Argument.Delimiters.none, Argument.Delimiters.none, "argumentName", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIncompatibleAnnotationsChecker extends FirDeclarationChecker<FirClass> {
    public static final FirIncompatibleAnnotationsChecker INSTANCE = new FirIncompatibleAnnotationsChecker();

    private FirIncompatibleAnnotationsChecker() {
        super(MppCheckerKind.Common);
    }

    private final Set<String> extractArguments(FirAnnotation firAnnotation, Name name) {
        Name enumEntryName;
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, name, false, 2, null);
        List<FirExpression> listUnwrapAndFlattenArgument = firExpressionFindArgumentByName$default != null ? FirExpressionUtilKt.unwrapAndFlattenArgument(firExpressionFindArgumentByName$default, true) : null;
        if (listUnwrapAndFlattenArgument == null) {
            listUnwrapAndFlattenArgument = CollectionsKt.emptyList();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = listUnwrapAndFlattenArgument.iterator();
        while (it.hasNext()) {
            EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo = FirAnnotationUtilsKt.extractEnumValueArgumentInfo((FirExpression) it.next());
            String strAsString = (enumValueArgumentInfoExtractEnumValueArgumentInfo == null || (enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName()) == null) ? null : enumEntryName.asString();
            if (strAsString != null) {
                linkedHashSet.add(strAsString);
            }
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firClass, JvmStandardClassIds.Annotations.Java.INSTANCE.getTarget(), checkerContext.getSession());
        if (annotationByClassId == null) {
            return;
        }
        FirAnnotation targetAnnotation = FirAnnotationHelpersKt.getTargetAnnotation(firClass, checkerContext.getSession());
        if (targetAnnotation == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getANNOTATION_TARGETS_ONLY_IN_JAVA(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            reportIncompatibleTargets(checkerContext, diagnosticReporter, targetAnnotation, annotationByClassId);
        }
    }

    public final void reportIncompatibleTargets(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotation firAnnotation, FirAnnotation firAnnotation2) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firAnnotation.getClass();
        firAnnotation2.getClass();
        Set<String> setExtractArguments = extractArguments(firAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getTargetAllowedTargets());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : setExtractArguments) {
            String str = (String) KotlinToJavaAnnotationTargetsKt.getKOTLIN_TO_JAVA_ANNOTATION_TARGETS().get((String) obj);
            Object arrayList = linkedHashMap.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(str, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        Map mutableMap = MapsKt.toMutableMap(linkedHashMap);
        mutableMap.remove(null);
        Iterator<T> it = extractArguments(firAnnotation2, StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue()).iterator();
        while (it.hasNext()) {
            mutableMap.remove((String) it.next());
        }
        if (mutableMap.isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation2.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getINCOMPATIBLE_ANNOTATION_TARGETS(), (Object) CollectionsKt.filterNotNull(mutableMap.keySet()), (Object) CollectionsKt.flatten(mutableMap.values()), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }
}

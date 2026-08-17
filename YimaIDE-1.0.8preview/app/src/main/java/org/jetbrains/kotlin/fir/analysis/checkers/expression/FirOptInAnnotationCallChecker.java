package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ \u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000f2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013J?\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001eR\u0018\u0010\u0014\u001a\u00020\u0011*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInAnnotationCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;)V", "getSubclassOptInApplicabilityAndMessage", "Lkotlin/Pair;", Argument.Delimiters.none, Argument.Delimiters.none, "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "representation", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getRepresentation", "(Lorg/jetbrains/kotlin/descriptors/ClassKind;)Ljava/lang/String;", "checkOptInArgumentIsMarker", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "annotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInAnnotationCallChecker extends FirExpressionChecker<FirAnnotationCall> {
    public static final FirOptInAnnotationCallChecker INSTANCE = new FirOptInAnnotationCallChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.ENUM_ENTRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirOptInAnnotationCallChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkOptInArgumentIsMarker(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirRegularClassSymbol firRegularClassSymbol, ClassId classId, KtSourceElement ktSourceElement) {
        KtDiagnosticFactory1<ClassId> subclass_opt_in_argument_is_not_marker;
        if (FirOptInUsageBaseChecker.INSTANCE.loadExperimentalityForMarkerAnnotation(firRegularClassSymbol, checkerContext.getSession()) == null) {
            OptInNames optInNames = OptInNames.INSTANCE;
            if (Intrinsics.areEqual(classId, optInNames.getOPT_IN_CLASS_ID())) {
                subclass_opt_in_argument_is_not_marker = FirErrors.INSTANCE.getOPT_IN_ARGUMENT_IS_NOT_MARKER();
            } else if (!Intrinsics.areEqual(classId, optInNames.getSUBCLASS_OPT_IN_REQUIRED_CLASS_ID())) {
                return;
            } else {
                subclass_opt_in_argument_is_not_marker = FirErrors.INSTANCE.getSUBCLASS_OPT_IN_ARGUMENT_IS_NOT_MARKER();
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) subclass_opt_in_argument_is_not_marker, (Object) firRegularClassSymbol.getClassId(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final String getRepresentation(ClassKind classKind) {
        if (WhenMappings.$EnumSwitchMapping$0[classKind.ordinal()] == 1) {
            return "enum entry";
        }
        String codeRepresentation = classKind.getCodeRepresentation();
        codeRepresentation.getClass();
        return codeRepresentation;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationCall firAnnotationCall) {
        List<Pair<FirRegularClassSymbol, KtSourceElement>> listExtractClassesAndSourcesFromArgument;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firAnnotationCall.getClass();
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(firAnnotationCall.getAnnotationTypeRef()));
        if (classLikeLookupTagIfAny == null) {
            return;
        }
        ClassId classId = classLikeLookupTagIfAny.getClassId();
        OptInNames optInNames = OptInNames.INSTANCE;
        boolean zAreEqual = Intrinsics.areEqual(classId, optInNames.getREQUIRES_OPT_IN_CLASS_ID());
        boolean zAreEqual2 = Intrinsics.areEqual(classId, optInNames.getOPT_IN_CLASS_ID());
        boolean zAreEqual3 = Intrinsics.areEqual(classId, optInNames.getSUBCLASS_OPT_IN_REQUIRED_CLASS_ID());
        if (zAreEqual || zAreEqual2) {
            if (zAreEqual2) {
                if (firAnnotationCall.getArgumentList().getArguments().isEmpty()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotationCall.getSource(), FirErrors.INSTANCE.getOPT_IN_WITHOUT_ARGUMENTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotationCall, optInNames.getOPT_IN_ANNOTATION_CLASS(), false, 2, null);
                listExtractClassesAndSourcesFromArgument = firExpressionFindArgumentByName$default != null ? FirAnnotationHelpersKt.extractClassesAndSourcesFromArgument(firExpressionFindArgumentByName$default, checkerContext.getSession()) : null;
                if (listExtractClassesAndSourcesFromArgument == null) {
                    listExtractClassesAndSourcesFromArgument = CollectionsKt.emptyList();
                }
                for (Pair<FirRegularClassSymbol, KtSourceElement> pair : listExtractClassesAndSourcesFromArgument) {
                    checkOptInArgumentIsMarker(diagnosticReporter, checkerContext, (FirRegularClassSymbol) pair.component1(), classId, (KtSourceElement) pair.component2());
                }
                return;
            }
            return;
        }
        if (zAreEqual3) {
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirClassSymbol<?> firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
            if (firClassSymbol != null) {
                Pair<Boolean, String> subclassOptInApplicabilityAndMessage = getSubclassOptInApplicabilityAndMessage(firClassSymbol);
                boolean zBooleanValue = ((Boolean) subclassOptInApplicabilityAndMessage.component1()).booleanValue();
                String str = (String) subclassOptInApplicabilityAndMessage.component2();
                if (!zBooleanValue && str != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotationCall.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getSUBCLASS_OPT_IN_INAPPLICABLE(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
            }
            FirExpression firExpressionFindArgumentByName$default2 = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotationCall, optInNames.getOPT_IN_ANNOTATION_CLASS(), false, 2, null);
            listExtractClassesAndSourcesFromArgument = firExpressionFindArgumentByName$default2 != null ? FirAnnotationHelpersKt.extractClassesAndSourcesFromArgument(firExpressionFindArgumentByName$default2, checkerContext.getSession()) : null;
            if (listExtractClassesAndSourcesFromArgument == null) {
                listExtractClassesAndSourcesFromArgument = CollectionsKt.emptyList();
            }
            Iterator<T> it = listExtractClassesAndSourcesFromArgument.iterator();
            while (it.hasNext()) {
                Pair pair2 = (Pair) it.next();
                INSTANCE.checkOptInArgumentIsMarker(diagnosticReporter, checkerContext, (FirRegularClassSymbol) pair2.component1(), classId, (KtSourceElement) pair2.component2());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Pair<Boolean, String> getSubclassOptInApplicabilityAndMessage(FirClassSymbol<?> classSymbol) {
        classSymbol.getClass();
        ClassKind classKind = classSymbol.getClassKind();
        String representation = getRepresentation(classKind);
        if (classKind == ClassKind.ENUM_CLASS || classKind == ClassKind.OBJECT || classKind == ClassKind.ANNOTATION_CLASS) {
            return TuplesKt.to(Boolean.FALSE, representation);
        }
        Modality modality = FirHelpersKt.modality(classSymbol);
        if (modality == Modality.FINAL || modality == Modality.SEALED) {
            Boolean bool = Boolean.FALSE;
            StringBuilder sb = new StringBuilder();
            String lowerCase = modality.name().toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            sb.append(lowerCase);
            sb.append(' ');
            sb.append(representation);
            return TuplesKt.to(bool, sb.toString());
        }
        if (classSymbol.getRawStatus().isFun()) {
            return TuplesKt.to(Boolean.FALSE, "fun interface");
        }
        if (!((FirClassLikeDeclaration) classSymbol.getFir()).getIsLocal()) {
            return TuplesKt.to(Boolean.TRUE, null);
        }
        return TuplesKt.to(Boolean.FALSE, "local " + representation);
    }
}

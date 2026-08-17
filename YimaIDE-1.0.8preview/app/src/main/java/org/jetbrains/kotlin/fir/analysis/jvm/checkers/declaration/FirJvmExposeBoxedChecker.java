package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.JvmStandardClassIds$Annotations$ParameterNames;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J5\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0002H\u0002R\u00020\u000eR\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0015J7\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0010\u001a\u00020\u0002H\u0002R\u00020\u000eR\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0019J\f\u0010\u001a\u001a\u00020\u0007*\u00020\u0002H\u0002J\u0014\u0010\u001b\u001a\u00020\u0007*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0014\u0010\u001f\u001a\u00020\u0007*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0014\u0010 \u001a\u00020\u0007*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0014\u0010!\u001a\u00020\u0007*\u00020\"2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmExposeBoxedChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkJvmExposeBoxedAnnotation", "jvmExposeBoxedAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkJvmNameHasDifferentName", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "cannotRename", "isWithInlineClass", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isMangledOrWithResult", "canBeOverloadedByExposed", "isInline", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmExposeBoxedChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJvmExposeBoxedChecker INSTANCE = new FirJvmExposeBoxedChecker();

    private FirJvmExposeBoxedChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean canBeOverloadedByExposed(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        FirTypeRef typeRef;
        FirReceiverParameter receiverParameter = firCallableDeclaration.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null && isInline(typeRef, firSession)) {
            return true;
        }
        List<FirValueParameter> contextParameters = firCallableDeclaration.getContextParameters();
        if (!(contextParameters instanceof Collection) || !contextParameters.isEmpty()) {
            Iterator<T> it = contextParameters.iterator();
            while (it.hasNext()) {
                if (INSTANCE.isInline(((FirValueParameter) it.next()).getReturnTypeRef(), firSession)) {
                    return true;
                }
            }
        }
        if (firCallableDeclaration instanceof FirFunction) {
            List<FirValueParameter> valueParameters = ((FirFunction) firCallableDeclaration).getValueParameters();
            if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
                Iterator<T> it2 = valueParameters.iterator();
                while (it2.hasNext()) {
                    if (INSTANCE.isInline(((FirValueParameter) it2.next()).getReturnTypeRef(), firSession)) {
                        return true;
                    }
                }
            }
        }
        if (!(firCallableDeclaration instanceof FirConstructor)) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration);
            FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession) : null;
            if (regularClassSymbol != null) {
                return regularClassSymbol.getRawStatus().isInline() || regularClassSymbol.getRawStatus().isValue();
            }
        }
        return false;
    }

    private final boolean cannotRename(FirDeclaration firDeclaration) {
        return (firDeclaration instanceof FirClass) || (firDeclaration instanceof FirConstructor);
    }

    private final void checkJvmExposeBoxedAnnotation(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirAnnotation firAnnotation, FirDeclaration firDeclaration) {
        Modality modality;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirRegularClassSymbol regularClassSymbol;
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, JvmStandardClassIds$Annotations$ParameterNames.INSTANCE.getJvmExposeBoxedName(), false, 2, null);
        if (firExpressionFindArgumentByName$default != null) {
            if (cannotRename(firDeclaration)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpressionFindArgumentByName$default.getSource(), FirJvmErrors.INSTANCE.getINAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            String str = value instanceof String ? (String) value : null;
            if (str != null && !Name.isValidIdentifier(str)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirLiteralExpression) firExpressionFindArgumentByName$default).getSource(), FirJvmErrors.INSTANCE.getILLEGAL_JVM_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if ((firDeclaration instanceof FirFunction) && Intrinsics.areEqual(FirDeclarationUtilKt.getNameOrSpecialName((FirMemberDeclaration) firDeclaration).asString(), str)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirLiteralExpression) firExpressionFindArgumentByName$default).getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        if ((firDeclaration instanceof FirClass) && ((FirClass) firDeclaration).getClassKind() == ClassKind.INTERFACE) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (firDeclaration instanceof FirCallableDeclaration) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
            if (!isWithInlineClass(firCallableDeclaration, checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getUSELESS_JVM_EXPOSE_BOXED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (firExpressionFindArgumentByName$default == null && !isMangledOrWithResult(firCallableDeclaration, checkerContext.getSession()) && (firDeclaration instanceof FirFunction)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_REQUIRES_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (FirAnnotationUtilsKt.hasAnnotation(firDeclaration, JvmStandardClassIds.JVM_SYNTHETIC_ANNOTATION_CLASS_ID, checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            Modality modality2 = firMemberDeclaration.getStatus().getModality();
            if (modality2 != null && modality2 != (modality = Modality.FINAL) && (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration)) != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) != null && regularClassSymbol.getResolvedStatus().getModality() != modality) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (!canBeOverloadedByExposed(firCallableDeclaration, checkerContext.getSession())) {
                checkJvmNameHasDifferentName(diagnosticReporter, checkerContext, firExpressionFindArgumentByName$default, firDeclaration);
            }
            List<FirTypeParameterRef> typeParameters = ClassMembersKt.getPropertyIfAccessor(firCallableDeclaration).getTypeParameters();
            if (!(typeParameters instanceof Collection) || !typeParameters.isEmpty()) {
                Iterator<T> it = typeParameters.iterator();
                while (it.hasNext()) {
                    if (((FirTypeParameterRef) it.next()).getSymbol().isReified()) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        break;
                    }
                }
            }
            if ((firDeclaration instanceof FirFunction) && firMemberDeclaration.getStatus().isSuspend()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (DeclarationUtilsKt.isLocalDeclaredInBlock(firDeclaration)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkJvmNameHasDifferentName(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirExpression firExpression, FirDeclaration firDeclaration) {
        if (firExpression == null) {
            return;
        }
        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        String str = value instanceof String ? (String) value : null;
        if (str != null && Intrinsics.areEqual(str, JavaUtilsKt.findJvmNameValue(firDeclaration))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirLiteralExpression) firExpression).getSource(), FirJvmErrors.INSTANCE.getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final boolean isInline(FirTypeRef firTypeRef, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(firTypeRef, firSession);
        if (regularClassSymbol != null) {
            return regularClassSymbol.getRawStatus().isInline() || regularClassSymbol.getRawStatus().isValue();
        }
        return false;
    }

    private final boolean isMangledOrWithResult(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        if (canBeOverloadedByExposed(firCallableDeclaration, firSession)) {
            return true;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration);
        if ((coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession) : null) != null) {
            return isInline(firCallableDeclaration.getReturnTypeRef(), firSession);
        }
        return false;
    }

    private final boolean isWithInlineClass(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        return canBeOverloadedByExposed(firCallableDeclaration, firSession) || isInline(firCallableDeclaration.getReturnTypeRef(), firSession);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, JvmStandardClassIds.INSTANCE.getJVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID(), checkerContext.getSession());
        if (annotationByClassId != null) {
            checkJvmExposeBoxedAnnotation(diagnosticReporter, checkerContext, annotationByClassId, firDeclaration);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}

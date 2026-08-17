package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBinaryLogicExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.KtBooleanExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalReturnsDeclaration;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtErroneousCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtErroneousConstantReference;
import org.jetbrains.kotlin.contracts.description.KtErroneousContractElement;
import org.jetbrains.kotlin.contracts.description.KtErroneousIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtErroneousValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtIsNullPredicate;
import org.jetbrains.kotlin.contracts.description.KtLogicalNot;
import org.jetbrains.kotlin.contracts.description.KtReturnsEffectDeclaration;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirCastDiagnosticsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirContractElementDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirErrorContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeContractMayNotHaveLabel;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeContractDescriptionError;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002/0B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0017J5\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u001bJ5\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001eH\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u001fJ\f\u0010 \u001a\u00020\u001d*\u00020\u0002H\u0002J-\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001aH\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010#J5\u0010$\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&H\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010'J-\u0010(\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001aH\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010#J9\u0010)\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010.R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirContractChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "EMPTY_CONTRACT_MESSAGE", Argument.Delimiters.none, "DUPLICATE_CALLS_IN_PLACE_MESSAGE", "INVALID_CONTRACT_BLOCK", "CALLS_IN_PLACE_ON_CONTEXT_PARAMETER", "CONDITIONAL_RETURNS_EXPRESSION_NOT_SUPPORTED", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "checkAnnotationsNotAllowed", "contractCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "checkUnresolvedEffects", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "checkContractNotAllowed", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;)Z", "isContractOnOperatorForbidden", "checkDuplicateCallsInPlace", "description", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;)V", "checkCallsInPlaceOnContextParameter", "valueParametersCount", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;I)V", "checkComplexArgumentConditions", "checkDiagnosticsFromFirBuilder", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/KtSourceElement;)V", "DiagnosticExtractor", "ErasedCastChecker", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirContractChecker INSTANCE = new FirContractChecker();

    private FirContractChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkAnnotationsNotAllowed(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirBlock body;
        Object objSingleOrNull = CollectionsKt.singleOrNull(firFunctionCall.getArgumentList().getArguments());
        FirAnonymousFunctionExpression firAnonymousFunctionExpression = objSingleOrNull instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) objSingleOrNull : null;
        if (firAnonymousFunctionExpression == null || !firAnonymousFunctionExpression.getAnonymousFunction().getIsLambda() || (body = firAnonymousFunctionExpression.getAnonymousFunction().getBody()) == null) {
            return;
        }
        body.acceptChildren(new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirContractChecker.checkAnnotationsNotAllowed.1
            public void visitAnnotation(FirAnnotation annotation) {
                annotation.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotation.getSource(), FirErrors.INSTANCE.getANNOTATION_IN_CONTRACT_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }

            public void visitAnnotationCall(FirAnnotationCall annotationCall) {
                annotationCall.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationCall.getSource(), FirErrors.INSTANCE.getANNOTATION_IN_CONTRACT_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }

            public void visitElement(FirElement element) {
                element.getClass();
                element.acceptChildren(this);
            }
        });
    }

    private final void checkCallsInPlaceOnContextParameter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedContractDescription firResolvedContractDescription, int i) {
        Iterator<FirEffectDeclaration> it = firResolvedContractDescription.getEffects().iterator();
        while (it.hasNext()) {
            KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = it.next().getEffect();
            if ((effect instanceof KtCallsEffectDeclaration) && ((KtCallsEffectDeclaration) effect).getValueParameterReference().getParameterIndex() >= i) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedContractDescription.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) "callsInPlace contract cannot be applied to context parameter because context arguments can never be lambdas.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private final void checkComplexArgumentConditions(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedContractDescription firResolvedContractDescription) {
        List<FirEffectDeclaration> effects = firResolvedContractDescription.getEffects();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = effects.iterator();
        while (it.hasNext()) {
            KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = ((FirEffectDeclaration) it.next()).getEffect();
            KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration = effect instanceof KtConditionalReturnsDeclaration ? (KtConditionalReturnsDeclaration) effect : null;
            if (ktConditionalReturnsDeclaration != null) {
                arrayList.add(ktConditionalReturnsDeclaration);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            if (checkComplexArgumentConditions$containsUnsupportedElements(((KtConditionalReturnsDeclaration) it2.next()).getArgumentsCondition())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedContractDescription.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) "Arbitrary expressions are not supported in this contract, only 'null'` and 'is' checks are supported", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private static final boolean checkComplexArgumentConditions$containsUnsupportedElements(KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpression) {
        if (ktBooleanExpression instanceof KtLogicalNot) {
            return checkComplexArgumentConditions$containsUnsupportedElements(((KtLogicalNot) ktBooleanExpression).getArg());
        }
        return (ktBooleanExpression instanceof KtBinaryLogicExpression) || (ktBooleanExpression instanceof KtBooleanValueParameterReference) || (ktBooleanExpression instanceof KtBooleanConstantReference);
    }

    private final boolean checkContractNotAllowed(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction, FirContractDescription firContractDescription) {
        KtSourceElement source = firContractDescription.getSource();
        if (!((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind)) {
            return false;
        }
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowContractsOnPropertyAccessors);
        boolean zIsEnabled2 = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowContractsOnSomeOperators);
        if (!zIsEnabled && ((firFunction instanceof FirPropertyAccessor) || (firFunction instanceof FirAnonymousFunction))) {
            checkContractNotAllowed$contractNotAllowed(checkerContext, diagnosticReporter, source, "Contracts are only allowed for functions.");
            return true;
        }
        if (zIsEnabled && (firFunction instanceof FirAnonymousFunction)) {
            checkContractNotAllowed$contractNotAllowed(checkerContext, diagnosticReporter, source, "Contracts are not allowed for anonymous functions.");
            return true;
        }
        if (firFunction.getStatus().getModality() == Modality.ABSTRACT || firFunction.getStatus().getModality() == Modality.OPEN || firFunction.getStatus().isOverride()) {
            checkContractNotAllowed$contractNotAllowed(checkerContext, diagnosticReporter, source, "Contracts are not allowed for open or override functions.");
            return true;
        }
        if (!zIsEnabled2 && firFunction.getStatus().isOperator()) {
            checkContractNotAllowed$contractNotAllowed(checkerContext, diagnosticReporter, source, "Contracts are not allowed for operator functions.");
            return true;
        }
        if (!zIsEnabled2 || !firFunction.getStatus().isOperator() || !isContractOnOperatorForbidden(firFunction)) {
            if (!firFunction.getSymbol().isLocal()) {
                return false;
            }
            checkContractNotAllowed$contractNotAllowed(checkerContext, diagnosticReporter, source, "Contracts are not allowed for local functions.");
            return true;
        }
        checkContractNotAllowed$contractNotAllowed(checkerContext, diagnosticReporter, source, "Contracts are not allowed for operator " + FirDeclarationUtilKt.getNameOrSpecialName(firFunction) + '.');
        return true;
    }

    private static final void checkContractNotAllowed$contractNotAllowed(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, String str) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONTRACT_NOT_ALLOWED(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private final void checkDiagnosticsFromFirBuilder(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement) {
        ConeContractMayNotHaveLabel coneContractMayNotHaveLabel = ConeContractMayNotHaveLabel.INSTANCE;
        if (Intrinsics.areEqual(coneDiagnostic, coneContractMayNotHaveLabel)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) coneContractMayNotHaveLabel.getReason(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkDuplicateCallsInPlace(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedContractDescription firResolvedContractDescription) {
        List<FirEffectDeclaration> effects = firResolvedContractDescription.getEffects();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = effects.iterator();
        while (it.hasNext()) {
            KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = ((FirEffectDeclaration) it.next()).getEffect();
            KtCallsEffectDeclaration ktCallsEffectDeclaration = effect instanceof KtCallsEffectDeclaration ? (KtCallsEffectDeclaration) effect : null;
            if (ktCallsEffectDeclaration != null) {
                arrayList.add(ktCallsEffectDeclaration);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int parameterIndex = ((KtCallsEffectDeclaration) it2.next()).getValueParameterReference().getParameterIndex();
            if (linkedHashSet.contains(Integer.valueOf(parameterIndex))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedContractDescription.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) "A value parameter may not be annotated with callsInPlace twice", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                linkedHashSet.add(Integer.valueOf(parameterIndex));
                checkerContext = checkerContext;
            }
        }
    }

    private final void checkUnresolvedEffects(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedContractDescription firResolvedContractDescription, FirFunction firFunction) {
        ErasedCastChecker erasedCastChecker = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowCheckForErasedTypesInContracts) ? null : new ErasedCastChecker(firFunction, checkerContext);
        for (FirContractElementDeclaration firContractElementDeclaration : firResolvedContractDescription.getUnresolvedEffects()) {
            ConeDiagnostic coneDiagnostic = (ConeDiagnostic) firContractElementDeclaration.getEffect().accept(DiagnosticExtractor.INSTANCE, null);
            if (coneDiagnostic == null) {
                coneDiagnostic = erasedCastChecker != null ? (ConeDiagnostic) firContractElementDeclaration.getEffect().accept(erasedCastChecker, null) : null;
                if (coneDiagnostic == null) {
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firContractElementDeclaration.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) coneDiagnostic.getReason(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (erasedCastChecker != null) {
            for (FirEffectDeclaration firEffectDeclaration : firResolvedContractDescription.getEffects()) {
                ConeDiagnostic coneDiagnostic2 = (ConeDiagnostic) firEffectDeclaration.getEffect().accept(erasedCastChecker, null);
                if (coneDiagnostic2 != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEffectDeclaration.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) coneDiagnostic2.getReason(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    private final boolean isContractOnOperatorForbidden(FirFunction firFunction) {
        Name nameOrSpecialName = FirDeclarationUtilKt.getNameOrSpecialName(firFunction);
        return Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.EQUALS) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.COMPARE_TO) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.GET_VALUE) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.SET_VALUE) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.PROVIDE_DELEGATE) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.GET) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.SET) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.PLUS) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.MINUS) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.TIMES) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.DIV) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.REM) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.PLUS_ASSIGN) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.MINUS_ASSIGN) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.TIMES_ASSIGN) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.DIV_ASSIGN) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.REM_ASSIGN) || Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.OF);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirContractDescriptionOwner firContractDescriptionOwner;
        FirContractDescription contractDescription;
        List<FirStatement> statements;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        if (!(firFunction instanceof FirContractDescriptionOwner) || (contractDescription = (firContractDescriptionOwner = (FirContractDescriptionOwner) firFunction).getContractDescription()) == null || checkContractNotAllowed(checkerContext, diagnosticReporter, firFunction, contractDescription)) {
            return;
        }
        FirBlock body = firContractDescriptionOwner.getBody();
        FirStatement firStatement = (body == null || (statements = body.getStatements()) == null) ? null : (FirStatement) CollectionsKt.firstOrNull(statements);
        FirContractCallBlock firContractCallBlock = firStatement instanceof FirContractCallBlock ? (FirContractCallBlock) firStatement : null;
        FirFunctionCall call = firContractCallBlock != null ? firContractCallBlock.getCall() : null;
        if (call != null) {
            checkAnnotationsNotAllowed(checkerContext, diagnosticReporter, call);
        }
        if (contractDescription instanceof FirResolvedContractDescription) {
            FirResolvedContractDescription firResolvedContractDescription = (FirResolvedContractDescription) contractDescription;
            checkUnresolvedEffects(checkerContext, diagnosticReporter, firResolvedContractDescription, firFunction);
            checkDuplicateCallsInPlace(checkerContext, diagnosticReporter, firResolvedContractDescription);
            checkComplexArgumentConditions(checkerContext, diagnosticReporter, firResolvedContractDescription);
            if (!firFunction.getContextParameters().isEmpty()) {
                checkCallsInPlaceOnContextParameter(checkerContext, diagnosticReporter, firResolvedContractDescription, ((FirContractDescriptionOwner) firFunction).getValueParameters().size());
            }
            if (firResolvedContractDescription.getEffects().isEmpty() && firResolvedContractDescription.getUnresolvedEffects().isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedContractDescription.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) "Empty contract block is not allowed", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            checkDiagnosticsFromFirBuilder(checkerContext, diagnosticReporter, firResolvedContractDescription.getDiagnostic(), firResolvedContractDescription.getSource());
            return;
        }
        if (contractDescription instanceof FirErrorContractDescription) {
            FirErrorContractDescription firErrorContractDescription = (FirErrorContractDescription) contractDescription;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firErrorContractDescription.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), (Object) "Contract block could not be resolved", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            checkDiagnosticsFromFirBuilder(checkerContext, diagnosticReporter, firErrorContractDescription.getDiagnostic(), firErrorContractDescription.getSource());
        } else {
            if (!(contractDescription instanceof FirRawContractDescription) && !(contractDescription instanceof FirLegacyRawContractDescription)) {
                bu8.a();
                return;
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected contract description kind: " + Reflection.getOrCreateKotlinClass(contractDescription.getClass()).getSimpleName(), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", firFunction);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    @Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J,\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\tj\u0002`\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010\f\u001a\u0004\u0018\u00010\u00022\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u000ej\u0002`\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00142\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0017j\u0002`\u00182\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u001bj\u0002`\u001c2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010\u001d\u001a\u00020\u00022\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u001e2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010\u001f\u001a\u0004\u0018\u00010\u00022\u0016\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020!j\u0002`\"2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010#\u001a\u0004\u0018\u00010\u00022\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020%j\u0002`&2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010'\u001a\u0004\u0018\u00010\u00022\u0016\u0010(\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020)j\u0002`*2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010+\u001a\u00020\u00022\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020,2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J,\u0010-\u001a\u0004\u0018\u00010\u00022\u0016\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020/j\u0002`02\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J&\u00101\u001a\u00020\u00022\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J&\u00104\u001a\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J&\u00107\u001a\u00020\u00022\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002092\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirContractChecker$DiagnosticExtractor;", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "()V", "visitContractDescriptionElement", "contractDescriptionElement", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractDescriptionElement;", "data", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalEffectDeclaration;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeConditionalEffectDeclaration;", "visitConditionalReturnsDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;", "visitHoldsInEffectDeclaration", "holdsInEffect", "Lorg/jetbrains/kotlin/contracts/description/KtHoldsInEffectDeclaration;", "visitReturnsEffectDeclaration", "returnsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsEffectDeclaration;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeReturnsEffectDeclaration;", "visitCallsEffectDeclaration", "callsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtCallsEffectDeclaration;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeCallsEffectDeclaration;", "visitErroneousCallsEffectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousCallsEffectDeclaration;", "visitLogicalBinaryOperationContractExpression", "binaryLogicExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeBinaryLogicExpression;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/KtLogicalNot;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeLogicalNot;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeIsInstancePredicate;", "visitErroneousIsInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousIsInstancePredicate;", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsNullPredicate;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeIsNullPredicate;", "visitErroneousConstantReference", "erroneousConstantReference", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousConstantReference;", "visitErroneousValueParameterReference", "valueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousValueParameterReference;", "visitErroneousElement", "element", "Lorg/jetbrains/kotlin/contracts/description/KtErroneousContractElement;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DiagnosticExtractor extends KtContractDescriptionVisitor {
        public static final DiagnosticExtractor INSTANCE = new DiagnosticExtractor();

        private DiagnosticExtractor() {
        }

        public ConeDiagnostic visitCallsEffectDeclaration(KtCallsEffectDeclaration<ConeKotlinType, ConeDiagnostic> callsEffect, Void data) {
            callsEffect.getClass();
            return (ConeDiagnostic) callsEffect.getValueParameterReference().accept(this, data);
        }

        public ConeDiagnostic visitConditionalEffectDeclaration(KtConditionalEffectDeclaration<ConeKotlinType, ConeDiagnostic> conditionalEffect, Void data) {
            conditionalEffect.getClass();
            ConeDiagnostic coneDiagnostic = (ConeDiagnostic) conditionalEffect.getEffect().accept(this, null);
            return coneDiagnostic == null ? (ConeDiagnostic) conditionalEffect.getCondition().accept(this, null) : coneDiagnostic;
        }

        public ConeDiagnostic visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration<ConeKotlinType, ConeDiagnostic> conditionalEffect, Void data) {
            conditionalEffect.getClass();
            ConeDiagnostic coneDiagnostic = (ConeDiagnostic) conditionalEffect.getArgumentsCondition().accept(this, null);
            return coneDiagnostic == null ? (ConeDiagnostic) conditionalEffect.getReturnsEffect().accept(this, null) : coneDiagnostic;
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitContractDescriptionElement(KtContractDescriptionElement ktContractDescriptionElement, Object obj) {
            return visitContractDescriptionElement((KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic>) ktContractDescriptionElement, (Void) obj);
        }

        public ConeDiagnostic visitErroneousCallsEffectDeclaration(KtErroneousCallsEffectDeclaration<ConeKotlinType, ConeDiagnostic> callsEffect, Void data) {
            callsEffect.getClass();
            return callsEffect.getDiagnostic();
        }

        public ConeDiagnostic visitErroneousConstantReference(KtErroneousConstantReference<ConeKotlinType, ConeDiagnostic> erroneousConstantReference, Void data) {
            erroneousConstantReference.getClass();
            return erroneousConstantReference.getDiagnostic();
        }

        public ConeDiagnostic visitErroneousElement(KtErroneousContractElement<ConeKotlinType, ConeDiagnostic> element, Void data) {
            element.getClass();
            return element.getDiagnostic();
        }

        public ConeDiagnostic visitErroneousIsInstancePredicate(KtErroneousIsInstancePredicate<ConeKotlinType, ConeDiagnostic> isInstancePredicate, Void data) {
            isInstancePredicate.getClass();
            return isInstancePredicate.getDiagnostic();
        }

        public ConeDiagnostic visitErroneousValueParameterReference(KtErroneousValueParameterReference<ConeKotlinType, ConeDiagnostic> valueParameterReference, Void data) {
            valueParameterReference.getClass();
            return valueParameterReference.getDiagnostic();
        }

        public ConeDiagnostic visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration<ConeKotlinType, ConeDiagnostic> holdsInEffect, Void data) {
            holdsInEffect.getClass();
            ConeDiagnostic coneDiagnostic = (ConeDiagnostic) holdsInEffect.getArgumentsCondition().accept(this, null);
            return coneDiagnostic == null ? (ConeDiagnostic) holdsInEffect.getValueParameterReference().accept(this, null) : coneDiagnostic;
        }

        public ConeDiagnostic visitIsInstancePredicate(KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic> isInstancePredicate, Void data) {
            isInstancePredicate.getClass();
            return (ConeDiagnostic) isInstancePredicate.getArg().accept(this, data);
        }

        public ConeDiagnostic visitIsNullPredicate(KtIsNullPredicate<ConeKotlinType, ConeDiagnostic> isNullPredicate, Void data) {
            isNullPredicate.getClass();
            return (ConeDiagnostic) isNullPredicate.getArg().accept(this, data);
        }

        public ConeDiagnostic visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic> binaryLogicExpression, Void data) {
            binaryLogicExpression.getClass();
            ConeDiagnostic coneDiagnostic = (ConeDiagnostic) binaryLogicExpression.getLeft().accept(this, null);
            return coneDiagnostic == null ? (ConeDiagnostic) binaryLogicExpression.getRight().accept(this, null) : coneDiagnostic;
        }

        public ConeDiagnostic visitLogicalNot(KtLogicalNot<ConeKotlinType, ConeDiagnostic> logicalNot, Void data) {
            logicalNot.getClass();
            return (ConeDiagnostic) logicalNot.getArg().accept(this, null);
        }

        public ConeDiagnostic visitReturnsEffectDeclaration(KtReturnsEffectDeclaration<ConeKotlinType, ConeDiagnostic> returnsEffect, Void data) {
            returnsEffect.getClass();
            return (ConeDiagnostic) returnsEffect.getValue().accept(this, null);
        }

        public ConeDiagnostic visitContractDescriptionElement(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> contractDescriptionElement, Void data) {
            contractDescriptionElement.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitErroneousCallsEffectDeclaration(KtErroneousCallsEffectDeclaration ktErroneousCallsEffectDeclaration, Object obj) {
            return visitErroneousCallsEffectDeclaration((KtErroneousCallsEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktErroneousCallsEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitErroneousConstantReference(KtErroneousConstantReference ktErroneousConstantReference, Object obj) {
            return visitErroneousConstantReference((KtErroneousConstantReference<ConeKotlinType, ConeDiagnostic>) ktErroneousConstantReference, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitErroneousElement(KtErroneousContractElement ktErroneousContractElement, Object obj) {
            return visitErroneousElement((KtErroneousContractElement<ConeKotlinType, ConeDiagnostic>) ktErroneousContractElement, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitErroneousIsInstancePredicate(KtErroneousIsInstancePredicate ktErroneousIsInstancePredicate, Object obj) {
            return visitErroneousIsInstancePredicate((KtErroneousIsInstancePredicate<ConeKotlinType, ConeDiagnostic>) ktErroneousIsInstancePredicate, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitErroneousValueParameterReference(KtErroneousValueParameterReference ktErroneousValueParameterReference, Object obj) {
            return visitErroneousValueParameterReference((KtErroneousValueParameterReference<ConeKotlinType, ConeDiagnostic>) ktErroneousValueParameterReference, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitCallsEffectDeclaration(KtCallsEffectDeclaration ktCallsEffectDeclaration, Object obj) {
            return visitCallsEffectDeclaration((KtCallsEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktCallsEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitIsInstancePredicate(KtIsInstancePredicate ktIsInstancePredicate, Object obj) {
            return visitIsInstancePredicate((KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic>) ktIsInstancePredicate, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitIsNullPredicate(KtIsNullPredicate ktIsNullPredicate, Object obj) {
            return visitIsNullPredicate((KtIsNullPredicate<ConeKotlinType, ConeDiagnostic>) ktIsNullPredicate, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitLogicalNot(KtLogicalNot ktLogicalNot, Object obj) {
            return visitLogicalNot((KtLogicalNot<ConeKotlinType, ConeDiagnostic>) ktLogicalNot, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitReturnsEffectDeclaration(KtReturnsEffectDeclaration ktReturnsEffectDeclaration, Object obj) {
            return visitReturnsEffectDeclaration((KtReturnsEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktReturnsEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitConditionalEffectDeclaration(KtConditionalEffectDeclaration ktConditionalEffectDeclaration, Object obj) {
            return visitConditionalEffectDeclaration((KtConditionalEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktConditionalEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration, Object obj) {
            return visitConditionalReturnsDeclaration((KtConditionalReturnsDeclaration<ConeKotlinType, ConeDiagnostic>) ktConditionalReturnsDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration ktHoldsInEffectDeclaration, Object obj) {
            return visitHoldsInEffectDeclaration((KtHoldsInEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktHoldsInEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression ktBinaryLogicExpression, Object obj) {
            return visitLogicalBinaryOperationContractExpression((KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic>) ktBinaryLogicExpression, (Void) obj);
        }
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ(\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0016\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u001a2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u001b\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u001d2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010\u001e\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020 2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J(\u0010!\u001a\u0004\u0018\u00010\u00022\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020#2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirContractChecker$ErasedCastChecker;", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "visitContractDescriptionElement", "contractDescriptionElement", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "data", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalEffectDeclaration;", "visitConditionalReturnsDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;", "visitHoldsInEffectDeclaration", "holdsInEffect", "Lorg/jetbrains/kotlin/contracts/description/KtHoldsInEffectDeclaration;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;", "visitLogicalBinaryOperationContractExpression", "binaryLogicExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/KtLogicalNot;", "getParameterType", "index", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ErasedCastChecker extends KtContractDescriptionVisitor {
        private final CheckerContext context;
        private final FirFunction declaration;

        public ErasedCastChecker(FirFunction firFunction, CheckerContext checkerContext) {
            firFunction.getClass();
            checkerContext.getClass();
            this.declaration = firFunction;
            this.context = checkerContext;
        }

        private final ConeKotlinType getParameterType(int index) {
            FirFunction firFunction = this.declaration;
            FirCallableSymbol propertySymbol = firFunction instanceof FirPropertyAccessor ? ((FirPropertyAccessor) firFunction).getPropertySymbol() : firFunction.getSymbol();
            if (index != -1) {
                return (index < 0 || index >= this.declaration.getValueParameters().size()) ? propertySymbol.getContextParameterSymbols().get(index - this.declaration.getValueParameters().size()).getResolvedReturnType() : FirTypeUtilsKt.getConeType(this.declaration.getValueParameters().get(index).getReturnTypeRef());
            }
            ConeKotlinType resolvedReceiverType = propertySymbol.getResolvedReceiverType();
            if (resolvedReceiverType != null) {
                return resolvedReceiverType;
            }
            ConeSimpleKotlinType dispatchReceiverType = this.declaration.getSymbol().getDispatchReceiverType();
            if (dispatchReceiverType != null) {
                return dispatchReceiverType;
            }
            k2d.a("Contract references non-existent receiver");
            return null;
        }

        public final CheckerContext getContext() {
            return this.context;
        }

        public final FirFunction getDeclaration() {
            return this.declaration;
        }

        public ConeDiagnostic visitConditionalEffectDeclaration(KtConditionalEffectDeclaration<ConeKotlinType, ConeDiagnostic> conditionalEffect, Void data) {
            conditionalEffect.getClass();
            return (ConeDiagnostic) conditionalEffect.getCondition().accept(this, data);
        }

        public ConeDiagnostic visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration<ConeKotlinType, ConeDiagnostic> conditionalEffect, Void data) {
            conditionalEffect.getClass();
            return (ConeDiagnostic) conditionalEffect.getArgumentsCondition().accept(this, data);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitContractDescriptionElement(KtContractDescriptionElement ktContractDescriptionElement, Object obj) {
            return visitContractDescriptionElement((KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic>) ktContractDescriptionElement, (Void) obj);
        }

        public ConeDiagnostic visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration<ConeKotlinType, ConeDiagnostic> holdsInEffect, Void data) {
            holdsInEffect.getClass();
            return (ConeDiagnostic) holdsInEffect.getArgumentsCondition().accept(this, data);
        }

        public ConeDiagnostic visitIsInstancePredicate(KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic> isInstancePredicate, Void data) {
            isInstancePredicate.getClass();
            if (FirCastDiagnosticsHelpersKt.isCastErased(this.context, getParameterType(isInstancePredicate.getArg().getParameterIndex()), isInstancePredicate.getType())) {
                return ConeContractDescriptionError.ErasedIsCheck.INSTANCE;
            }
            return null;
        }

        public ConeDiagnostic visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic> binaryLogicExpression, Void data) {
            binaryLogicExpression.getClass();
            ConeDiagnostic coneDiagnostic = (ConeDiagnostic) binaryLogicExpression.getLeft().accept(this, data);
            return coneDiagnostic == null ? (ConeDiagnostic) binaryLogicExpression.getRight().accept(this, data) : coneDiagnostic;
        }

        public ConeDiagnostic visitLogicalNot(KtLogicalNot<ConeKotlinType, ConeDiagnostic> logicalNot, Void data) {
            logicalNot.getClass();
            return (ConeDiagnostic) logicalNot.getArg().accept(this, data);
        }

        public ConeDiagnostic visitContractDescriptionElement(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> contractDescriptionElement, Void data) {
            contractDescriptionElement.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitConditionalEffectDeclaration(KtConditionalEffectDeclaration ktConditionalEffectDeclaration, Object obj) {
            return visitConditionalEffectDeclaration((KtConditionalEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktConditionalEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration, Object obj) {
            return visitConditionalReturnsDeclaration((KtConditionalReturnsDeclaration<ConeKotlinType, ConeDiagnostic>) ktConditionalReturnsDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration ktHoldsInEffectDeclaration, Object obj) {
            return visitHoldsInEffectDeclaration((KtHoldsInEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktHoldsInEffectDeclaration, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitLogicalNot(KtLogicalNot ktLogicalNot, Object obj) {
            return visitLogicalNot((KtLogicalNot<ConeKotlinType, ConeDiagnostic>) ktLogicalNot, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression ktBinaryLogicExpression, Object obj) {
            return visitLogicalBinaryOperationContractExpression((KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic>) ktBinaryLogicExpression, (Void) obj);
        }

        @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
        public /* bridge */ /* synthetic */ Object visitIsInstancePredicate(KtIsInstancePredicate ktIsInstancePredicate, Object obj) {
            return visitIsInstancePredicate((KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic>) ktIsInstancePredicate, (Void) obj);
        }
    }
}

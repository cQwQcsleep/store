package org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH&J-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H&R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J-\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H&R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J\u0012\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH&J-\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J)\u0010\u001b\u001a\u00020\u000e*\u00020\u0002H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J)\u0010\u001c\u001a\u00020\u000e*\u00020\u0002H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J)\u0010\u001d\u001a\u00020\u000e*\u00020\u0002H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J)\u0010\u001e\u001a\u00020\u000e*\u00020\u0002H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J\u0014\u0010\u001f\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010 \u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002J\f\u0010!\u001a\u00020\u0005*\u00020\u0002H\u0002J\f\u0010\"\u001a\u00020\u0005*\u00020#H\u0002J\f\u0010$\u001a\u00020\u0005*\u00020\u0002H\u0002J\f\u0010%\u001a\u00020\u0005*\u00020&H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/declaration/FirWebCommonExternalChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "allowCompanionInInterface", Argument.Delimiters.none, "<init>", "(Z)V", "isNativeOrEffectivelyExternal", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reportExternalEnum", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "additionalCheck", "isDefinedExternallyCallableId", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "hasExternalLikeAnnotations", "check", "checkBody", "checkDelegation", "checkAnonymousInitializer", "checkConstructorPropertyParam", "isDirectlyExternal", "isPrivateMemberOfExternalClass", "isNonAbstractMemberIfInterface", "isNullableProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "hasValidExternalBody", "isDefinedExternallyExpression", "Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirWebCommonExternalChecker extends FirDeclarationChecker<FirDeclaration> {
    private final boolean allowCompanionInInterface;

    public FirWebCommonExternalChecker(boolean z) {
        super(MppCheckerKind.Common);
        this.allowCompanionInInterface = z;
    }

    private final void checkAnonymousInitializer(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        if (firDeclaration instanceof FirClass) {
            Iterator<FirAnonymousInitializer> it = FirDeclarationUtilKt.getAnonymousInitializers((FirClass) firDeclaration).iterator();
            while (it.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it.next().getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_ANONYMOUS_INITIALIZER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0027  */
    private final void checkBody(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirExpression result;
        if (firDeclaration instanceof FirDefaultPropertyAccessor) {
            return;
        }
        FirBlock body = firDeclaration instanceof FirFunction ? ((FirFunction) firDeclaration).getBody() : firDeclaration instanceof FirAnonymousInitializer ? ((FirAnonymousInitializer) firDeclaration).getBody() : null;
        if (firDeclaration instanceof FirEnumEntry) {
            result = null;
        } else {
            KtSourceElement source = firDeclaration.getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) {
                result = null;
            } else if (firDeclaration instanceof FirVariable) {
                result = ((FirVariable) firDeclaration).getInitializer();
            } else if (body instanceof FirSingleExpressionBlock) {
                FirStatement statement = ((FirSingleExpressionBlock) body).getStatement();
                FirReturnExpression firReturnExpression = statement instanceof FirReturnExpression ? (FirReturnExpression) statement : null;
                if (firReturnExpression != null) {
                    result = firReturnExpression.getResult();
                } else {
                    result = null;
                }
            } else {
                result = null;
            }
        }
        KtSourceElement source2 = firDeclaration.getSource();
        if ((source2 != null ? source2.getKind() : null) instanceof KtRealSourceElementKind) {
            boolean z = (((body instanceof FirSingleExpressionBlock) || hasValidExternalBody(firDeclaration)) && (result == null || isDefinedExternallyExpression(result))) ? false : true;
            if (z && body != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) body.getSource(), FirWebCommonErrors.INSTANCE.getWRONG_BODY_OF_EXTERNAL_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (z && result != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) result.getSource(), FirWebCommonErrors.INSTANCE.getWRONG_INITIALIZER_OF_EXTERNAL_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firDeclaration instanceof FirFunction) {
                List<FirValueParameter> valueParameters = ((FirFunction) firDeclaration).getValueParameters();
                ArrayList<FirExpression> arrayList = new ArrayList();
                Iterator<T> it = valueParameters.iterator();
                while (it.hasNext()) {
                    FirExpression defaultValue = ((FirValueParameter) it.next()).getDefaultValue();
                    if (defaultValue != null) {
                        arrayList.add(defaultValue);
                    }
                }
                for (FirExpression firExpression : arrayList) {
                    if (!isDefinedExternallyExpression(firExpression)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), FirWebCommonErrors.INSTANCE.getWRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
        }
    }

    private final void checkConstructorPropertyParam(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        if (firDeclaration instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firDeclaration;
            KtSourceElement source = firProperty.getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) {
                FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration);
                FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
                if (firClassSymbol == null || firClassSymbol.getRawStatus().isData() || firClassSymbol.getClassKind() == ClassKind.ANNOTATION_CLASS) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkDelegation(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirExpression delegate;
        KtSourceElement source;
        KtSourceElement source2;
        if ((firDeclaration instanceof FirMemberDeclaration) && isNativeOrEffectivelyExternal(((FirMemberDeclaration) firDeclaration).getSymbol(), checkerContext.getSession())) {
            KtSourceElementKind kind = null;
            if (firDeclaration instanceof FirClass) {
                FirClass firClass = (FirClass) firDeclaration;
                FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession());
                if (firConstructorSymbolPrimaryConstructorIfAny != null) {
                    FirDelegatedConstructorCall resolvedDelegatedConstructorCall = firConstructorSymbolPrimaryConstructorIfAny.getResolvedDelegatedConstructorCall();
                    if (resolvedDelegatedConstructorCall != null && (source2 = resolvedDelegatedConstructorCall.getSource()) != null) {
                        kind = source2.getKind();
                    }
                    if (kind instanceof KtRealSourceElementKind) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedDelegatedConstructorCall.getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_DELEGATED_CONSTRUCTOR_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
                for (Map.Entry<FirTypeRef, FirFieldSymbol> entry : FirHelpersKt.collectSupertypesWithDelegates(firClass).entrySet()) {
                    FirTypeRef key = entry.getKey();
                    if (entry.getValue() != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) key.getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_DELEGATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
                return;
            }
            if (firDeclaration instanceof FirConstructor) {
                FirConstructor firConstructor = (FirConstructor) firDeclaration;
                if (!firConstructor.getIsPrimary()) {
                    FirDelegatedConstructorCall delegatedConstructor = firConstructor.getDelegatedConstructor();
                    if (delegatedConstructor != null && (source = delegatedConstructor.getSource()) != null) {
                        kind = source.getKind();
                    }
                    if (kind instanceof KtRealSourceElementKind) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) delegatedConstructor.getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_DELEGATED_CONSTRUCTOR_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        return;
                    }
                    return;
                }
            }
            if (!(firDeclaration instanceof FirProperty) || (delegate = ((FirProperty) firDeclaration).getDelegate()) == null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) delegate.getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_DELEGATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final boolean hasValidExternalBody(FirDeclaration firDeclaration) {
        FirBlock body;
        FirElement firElement;
        if (firDeclaration instanceof FirFunction) {
            body = ((FirFunction) firDeclaration).getBody();
        } else {
            if (!(firDeclaration instanceof FirAnonymousInitializer)) {
                return true;
            }
            body = ((FirAnonymousInitializer) firDeclaration).getBody();
        }
        if (body instanceof FirSingleExpressionBlock) {
            return isDefinedExternallyExpression(body);
        }
        if (body == null || (firElement = (FirStatement) CollectionsKt.singleOrNull(body.getStatements())) == null) {
            return false;
        }
        return isDefinedExternallyExpression(firElement);
    }

    private final boolean isDefinedExternallyExpression(FirElement firElement) {
        FirNamedReference calleeReference;
        FirPropertySymbol resolvedPropertySymbol$default;
        FirPropertyAccessExpression firPropertyAccessExpression = firElement instanceof FirPropertyAccessExpression ? (FirPropertyAccessExpression) firElement : null;
        if (firPropertyAccessExpression == null || (calleeReference = firPropertyAccessExpression.getCalleeReference()) == null || (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null)) == null) {
            return false;
        }
        return isDefinedExternallyCallableId(resolvedPropertySymbol$default.getCallableId());
    }

    private final boolean isDirectlyExternal(FirDeclaration firDeclaration, FirSession firSession) {
        if (!(firDeclaration instanceof FirDefaultPropertyAccessor)) {
            KtSourceElement source = firDeclaration.getSource();
            if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.EXTERNAL_KEYWORD;
                ktModifierKeywordToken.getClass();
                return FirKeywordUtilsKt.hasModifier(firDeclaration, ktModifierKeywordToken) || hasExternalLikeAnnotations(firDeclaration, firSession);
            }
        }
        return false;
    }

    private final boolean isNonAbstractMemberIfInterface(FirDeclaration firDeclaration) {
        if (!(firDeclaration instanceof FirBackingField) && (firDeclaration instanceof FirCallableDeclaration) && ((FirMemberDeclaration) firDeclaration).getStatus().getModality() != Modality.ABSTRACT) {
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration);
            FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
            if ((firClassSymbol != null ? firClassSymbol.getClassKind() : null) == ClassKind.INTERFACE && !(firDeclaration instanceof FirPropertyAccessor)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isNullableProperty(FirCallableDeclaration firCallableDeclaration) {
        return (firCallableDeclaration instanceof FirProperty) && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(FirTypeUtilsKt.getConeType(((FirProperty) firCallableDeclaration).getReturnTypeRef()));
    }

    private final boolean isPrivateMemberOfExternalClass(FirDeclaration firDeclaration, FirSession firSession) {
        FirClassLikeSymbol<?> containingClassSymbol;
        if (firDeclaration instanceof FirBackingField) {
            return false;
        }
        if (!((firDeclaration instanceof FirPropertyAccessor) && Intrinsics.areEqual(((FirMemberDeclaration) firDeclaration).getStatus().getVisibility(), ((FirPropertyAccessor) firDeclaration).getPropertySymbol().getResolvedStatus().getVisibility())) && (firDeclaration instanceof FirMemberDeclaration) && Intrinsics.areEqual(((FirMemberDeclaration) firDeclaration).getStatus().getVisibility(), Visibilities.Private.INSTANCE) && (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration)) != null) {
            return isNativeOrEffectivelyExternal(containingClassSymbol, firSession);
        }
        return false;
    }

    public abstract void additionalCheck(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration);

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        String str;
        String str2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (isNativeOrEffectivelyExternal(firDeclaration.getSymbol(), checkerContext.getSession())) {
            if (!FirHelpersKt.isTopLevel(checkerContext) && !(firDeclaration instanceof FirPropertyAccessor) && isDirectlyExternal(firDeclaration, checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), FirWebCommonErrors.INSTANCE.getNESTED_EXTERNAL_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            boolean z = firDeclaration instanceof FirClass;
            if (z) {
                FirClass firClass = (FirClass) firDeclaration;
                if (firClass.getStatus().isData()) {
                    str2 = "data class";
                } else if (firClass.getStatus().isInner()) {
                    str2 = "inner class";
                } else if (firClass.getStatus().isInline() || firClass.getStatus().isValue()) {
                    str2 = "value class";
                } else if (firClass.getStatus().isFun()) {
                    str2 = "fun interface";
                } else {
                    str2 = firClass.getClassKind() == ClassKind.ANNOTATION_CLASS ? "annotation class" : null;
                }
                String str3 = str2;
                if (str3 != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) str3, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                if (firClass.getClassKind() == ClassKind.ENUM_CLASS) {
                    reportExternalEnum(checkerContext, diagnosticReporter, firDeclaration);
                }
            }
            if (!(firDeclaration instanceof FirConstructor) && !(firDeclaration instanceof FirField) && isPrivateMemberOfExternalClass(firDeclaration, checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) "private member of class", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            if (z) {
                FirClass firClass2 = (FirClass) firDeclaration;
                ClassKind classKind = firClass2.getClassKind();
                ClassKind classKind2 = ClassKind.INTERFACE;
                if (classKind != classKind2 && ((!this.allowCompanionInInterface || !firClass2.getStatus().isCompanion()) && (firBasedSymbol instanceof FirClassSymbol) && ((FirClassSymbol) firBasedSymbol).getClassKind() == classKind2)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass2.getSource(), FirWebCommonErrors.INSTANCE.getNESTED_CLASS_IN_EXTERNAL_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            if (this.allowCompanionInInterface && z) {
                FirClass firClass3 = (FirClass) firDeclaration;
                if (firClass3.getStatus().isCompanion() && (firBasedSymbol instanceof FirClassSymbol) && ((FirClassSymbol) firBasedSymbol).getClassKind() == ClassKind.INTERFACE && !Intrinsics.areEqual(FirDeclarationUtilKt.getNameOrSpecialName((FirMemberDeclaration) firDeclaration), SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass3.getSource(), FirWebCommonErrors.INSTANCE.getNAMED_COMPANION_IN_EXTERNAL_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            if (!(firDeclaration instanceof FirPropertyAccessor) && (firDeclaration instanceof FirCallableDeclaration)) {
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
                if (FirDeclarationUtilKt.isExtension(firCallableDeclaration)) {
                    if (firCallableDeclaration instanceof FirFunction) {
                        str = "extension function";
                    } else {
                        str = firCallableDeclaration instanceof FirProperty ? "extension property" : "extension member";
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration.getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
            if ((firDeclaration instanceof FirCallableDeclaration) && isNonAbstractMemberIfInterface(firDeclaration)) {
                FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firDeclaration;
                if (!isNullableProperty(firCallableDeclaration2)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration2.getSource(), FirWebCommonErrors.INSTANCE.getNON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            checkBody(checkerContext, diagnosticReporter, firDeclaration);
            checkDelegation(checkerContext, diagnosticReporter, firDeclaration);
            checkAnonymousInitializer(checkerContext, diagnosticReporter, firDeclaration);
            if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.JsExternalPropertyParameters)) {
                checkConstructorPropertyParam(checkerContext, diagnosticReporter, firDeclaration);
            }
            additionalCheck(checkerContext, diagnosticReporter, firDeclaration);
        }
    }

    public abstract boolean hasExternalLikeAnnotations(FirDeclaration declaration, FirSession session);

    public abstract boolean isDefinedExternallyCallableId(CallableId callableId);

    public abstract boolean isNativeOrEffectivelyExternal(FirBasedSymbol<?> symbol, FirSession session);

    public abstract void reportExternalEnum(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration);
}

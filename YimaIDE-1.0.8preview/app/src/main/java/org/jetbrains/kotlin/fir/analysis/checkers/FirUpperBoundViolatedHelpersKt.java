package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u001aA\u0010\u0000\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tR\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u000b\u001a_\u0010\u0000\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tH\u0000R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0011\u001a\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\b\u0012\u0004\u0012\u00020\u00150\u0013\u001a*\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001b\u001a\u00020\u001c\u001aQ\u0010\u001d\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001e\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u001f\u001a\u008b\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001e\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\tR\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010#\u001ai\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,2\u0006\u0010 \u001a\u00020\t2\b\u0010-\u001a\u0004\u0018\u00010\u00072\b\u0010.\u001a\u0004\u0018\u00010\u000fH\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010/\u001a\u001c\u00100\u001a\u0004\u0018\u00010\r*\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u001c\u001a\u0014\u00109\u001a\u00020\u0014*\u00020\u00142\b\u0010:\u001a\u0004\u0018\u00010;\"!\u00102\u001a\u0004\u0018\u000103*\u0002048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b5\u00106\"!\u0010<\u001a\u0004\u0018\u00010'*\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\b=\u0010>\"\u001b\u0010A\u001a\b\u0012\u0004\u0012\u00020'0\u0013*\u00020\u001c8F¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006D"}, d2 = {"checkUpperBoundViolated", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isIgnoreTypeParameters", Argument.Delimiters.none, "isInsideTypeOperatorOrParameterBounds", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;ZZ)V", "notExpandedType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "fallbackSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "mustRelaxDueToArgumentInteractionsBug", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;ZLorg/jetbrains/kotlin/KtSourceElement;ZZ)V", "toTypeArgumentsWithSourceInfo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "createSubstitutorForUpperBoundViolationCheck", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "typeParameters", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "typeArguments", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "checkUpperBoundViolatedInLhsOfGetClass", "substitutor", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/KtSourceElement;)V", "isReportExpansionError", "isTypeAliasExpansionInLHSOfGetClass", "isTypealiasExpansion", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;ZZLorg/jetbrains/kotlin/KtSourceElement;ZZZZ)V", "reportUpperBoundViolationWarningIfNecessary", "onTypeParameter", "additionalUpperBoundsProvider", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;", "argumentType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "upperBound", "typeSystemContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "argumentTypeRef", "argumentSource", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;ZLorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtSourceElement;)Z", "fullyExpandedTypeWithSource", "useSiteSession", "sourceAttribute", "Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getSourceAttribute", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceAttribute;", "sourceAttribute$delegate", "Lkotlin/properties/ReadOnlyProperty;", "withSource", "source", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", "platformUpperBoundsProvider", "getPlatformUpperBoundsProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;", "platformUpperBoundsProvider$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "platformUpperBoundsProviders", "getPlatformUpperBoundsProviders", "(Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/util/List;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUpperBoundViolatedHelpersKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirUpperBoundViolatedHelpersKt.class, "sourceAttribute", "getSourceAttribute(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceAttribute;", 1), new PropertyReference1Impl<>(FirUpperBoundViolatedHelpersKt.class, "platformUpperBoundsProvider", "getPlatformUpperBoundsProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPlatformUpperBoundsProvider;", 1)};
    private static final NullableArrayMapAccessor platformUpperBoundsProvider$delegate;
    private static final ReadOnlyProperty sourceAttribute$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(SourceAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        sourceAttribute$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
        platformUpperBoundsProvider$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirPlatformUpperBoundsProvider.class));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkUpperBoundViolated(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<FirTypeParameterSymbol> list, List<? extends ConeTypeProjection> list2, ConeSubstitutor coneSubstitutor, boolean z, boolean z2, KtSourceElement ktSourceElement, boolean z3, boolean z4, boolean z5, boolean z6) throws KotlinIllegalArgumentExceptionWithAttachments {
        KtDiagnosticFactory4<ConeKotlinType, ConeKotlinType, ConeKotlinType, String> upper_bound_violated_deprecation_warning;
        KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> upper_bound_violated_in_typealias_expansion_deprecation_warning;
        FirTypeRef firTypeRef;
        ConeAttributes attributes;
        CheckerContext checkerContext2 = checkerContext;
        checkerContext2.getClass();
        diagnosticReporter.getClass();
        list.getClass();
        list2.getClass();
        coneSubstitutor.getClass();
        int iMin = Math.min(list.size(), list2.size());
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(checkerContext2.getSession());
        List<FirPlatformUpperBoundsProvider> platformUpperBoundsProviders = getPlatformUpperBoundsProviders(checkerContext2.getSession());
        int i = 0;
        while (i < iMin) {
            FirTypeParameterSymbol firTypeParameterSymbol = list.get(i);
            ConeKotlinType type = ConeTypeProjectionKt.getType(list2.get(i));
            SourceAttribute sourceAttribute = (type == null || (attributes = type.getAttributes()) == null) ? null : getSourceAttribute(attributes);
            FirTypeRef typeRef = sourceAttribute != null ? sourceAttribute.getTypeRef() : null;
            KtSourceElement source = sourceAttribute != null ? sourceAttribute.getSource() : null;
            if (type != null) {
                boolean z7 = (!FirHelpersKt.isExplicitTypeArgumentSource(source) && LanguageVersionUtilsKt.isDisabled(checkerContext2, LanguageFeature.DontIgnoreUpperBoundViolatedOnImplicitArguments)) || z4;
                if (z3) {
                    upper_bound_violated_deprecation_warning = (KtDiagnosticFactory4) KtDiagnosticReportHelpersKt.chooseFactory(checkerContext2, FirErrors.INSTANCE.getUPPER_BOUND_VIOLATED_IN_TYPE_OPERATOR_OR_PARAMETER_BOUNDS());
                } else {
                    upper_bound_violated_deprecation_warning = z7 ? FirErrors.INSTANCE.getUPPER_BOUND_VIOLATED_DEPRECATION_WARNING() : FirErrors.INSTANCE.getUPPER_BOUND_VIOLATED();
                }
                if (z5) {
                    upper_bound_violated_in_typealias_expansion_deprecation_warning = FirErrors.INSTANCE.getUPPER_BOUND_VIOLATED_IN_LHS_OF_CLASS_LITERAL_WARNING();
                } else {
                    upper_bound_violated_in_typealias_expansion_deprecation_warning = z7 ? FirErrors.INSTANCE.getUPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_DEPRECATION_WARNING() : FirErrors.INSTANCE.getUPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION();
                }
                if (z2 && (type.getTypeArguments().length != 0 || (type instanceof ConeTypeParameterType))) {
                    firTypeRef = typeRef;
                    break;
                }
                List<FirResolvedTypeRef> resolvedBounds = firTypeParameterSymbol.getResolvedBounds();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
                Iterator<T> it = resolvedBounds.iterator();
                while (it.hasNext()) {
                    arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
                }
                ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf((ConeKotlinType) typeContext.m684intersectTypes((Collection) arrayList));
                if (AbstractTypeChecker.INSTANCE.isSubtypeOf(typeContext, type, coneKotlinTypeSubstituteOrSelf, true)) {
                    ConeKotlinType coneKotlinType = coneKotlinTypeSubstituteOrSelf;
                    Iterator<FirPlatformUpperBoundsProvider> it2 = platformUpperBoundsProviders.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            ConeKotlinType coneKotlinType2 = type;
                            ConeKotlinType coneKotlinType3 = coneKotlinType;
                            ConeInferenceContext coneInferenceContext = typeContext;
                            firTypeRef = typeRef;
                            boolean zReportUpperBoundViolationWarningIfNecessary = reportUpperBoundViolationWarningIfNecessary(checkerContext, diagnosticReporter, firTypeParameterSymbol, it2.next(), coneKotlinType2, coneKotlinType3, coneInferenceContext, z, firTypeRef, source == null ? ktSourceElement : source);
                            type = coneKotlinType2;
                            coneKotlinType = coneKotlinType3;
                            typeContext = coneInferenceContext;
                            if (zReportUpperBoundViolationWarningIfNecessary) {
                                break;
                            } else {
                                typeRef = firTypeRef;
                            }
                        }
                    }
                } else if (z && (typeRef == null || z6)) {
                    if (source == null) {
                        source = ktSourceElement;
                    }
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeTypeParameterType>) ((KtDiagnosticFactory3<Object, Object, Object>) upper_bound_violated_in_typealias_expansion_deprecation_warning), coneKotlinTypeSubstituteOrSelf, type, FirNestedClassifierScopeKt.toConeType(firTypeParameterSymbol), (64 & 64) != 0 ? null : null);
                } else {
                    String str = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinTypeSubstituteOrSelf) instanceof ConeCapturedType ? "Consider removing the explicit type arguments" : Argument.Delimiters.none;
                    if (source == null) {
                        source = ktSourceElement;
                    }
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory4<ConeKotlinType, ConeKotlinType, ConeTypeParameterType, String>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) upper_bound_violated_deprecation_warning), coneKotlinTypeSubstituteOrSelf, type, FirNestedClassifierScopeKt.toConeType(firTypeParameterSymbol), str, (128 & 128) != 0 ? null : null);
                }
                firTypeRef = typeRef;
                break;
                if (type instanceof ConeClassLikeType) {
                    checkUpperBoundViolated$default(checkerContext, diagnosticReporter, firTypeRef, (ConeClassLikeType) type, z2, ktSourceElement, false, false, 192, null);
                }
            }
            i++;
            checkerContext2 = checkerContext;
        }
    }

    public static /* synthetic */ void checkUpperBoundViolated$default(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List list, List list2, ConeSubstitutor coneSubstitutor, boolean z, boolean z2, KtSourceElement ktSourceElement, boolean z3, boolean z4, boolean z5, boolean z6, int i, Object obj) {
        if ((i & 32) != 0) {
            z = false;
        }
        if ((i & 64) != 0) {
            z2 = false;
        }
        if ((i & 256) != 0) {
            z3 = false;
        }
        if ((i & 512) != 0) {
            z4 = false;
        }
        if ((i & 1024) != 0) {
            z5 = false;
        }
        checkUpperBoundViolated(checkerContext, diagnosticReporter, list, list2, coneSubstitutor, z, z2, ktSourceElement, z3, z4, z5, z6);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkUpperBoundViolatedInLhsOfGetClass(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<FirTypeParameterSymbol> list, List<? extends ConeTypeProjection> list2, ConeSubstitutor coneSubstitutor, KtSourceElement ktSourceElement) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        list.getClass();
        list2.getClass();
        coneSubstitutor.getClass();
        checkUpperBoundViolated(checkerContext, diagnosticReporter, list, list2, coneSubstitutor, true, false, ktSourceElement, false, false, true, true);
    }

    public static final ConeSubstitutor createSubstitutorForUpperBoundViolationCheck(List<FirTypeParameterSymbol> list, List<? extends ConeTypeProjection> list2, FirSession firSession) {
        list.getClass();
        list2.getClass();
        firSession.getClass();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(list);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
        for (IndexedValue indexedValue : iterableWithIndex) {
            Object value = indexedValue.getValue();
            ConeKotlinType coneKotlinType = list2.get(indexedValue.getIndex());
            coneKotlinType.getClass();
            Pair pair = new Pair(value, coneKotlinType);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, firSession, false, 4, null);
    }

    public static final ConeClassLikeType fullyExpandedTypeWithSource(ConeClassLikeType coneClassLikeType, FirTypeRef firTypeRef, FirSession firSession) {
        coneClassLikeType.getClass();
        firTypeRef.getClass();
        firSession.getClass();
        List<FirTypeRefSource> listExtractArgumentsTypeRefAndSource = FirHelpersKt.extractArgumentsTypeRefAndSource(firTypeRef);
        if (listExtractArgumentsTypeRefAndSource == null) {
            return null;
        }
        ConeTypeProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        ArrayList arrayList = new ArrayList(typeArguments.length);
        int length = typeArguments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            ConeTypeProjection coneTypeProjectionWithSource = typeArguments[i];
            int i3 = i2 + 1;
            FirTypeRefSource firTypeRefSource = (FirTypeRefSource) CollectionsKt.getOrNull(listExtractArgumentsTypeRefAndSource, i2);
            if (firTypeRefSource != null) {
                coneTypeProjectionWithSource = withSource(coneTypeProjectionWithSource, firTypeRefSource);
            }
            arrayList.add(coneTypeProjectionWithSource);
            i++;
            i2 = i3;
        }
        return TypeExpansionUtilsKt.fullyExpandedType$default(ConeTypeUtilsKt.withArguments(coneClassLikeType, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0])), firSession, (Function1) null, 2, (Object) null);
    }

    private static final FirPlatformUpperBoundsProvider getPlatformUpperBoundsProvider(FirSession firSession) {
        return (FirPlatformUpperBoundsProvider) platformUpperBoundsProvider$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final List<FirPlatformUpperBoundsProvider> getPlatformUpperBoundsProviders(FirSession firSession) {
        firSession.getClass();
        FirPlatformUpperBoundsProvider platformUpperBoundsProvider = getPlatformUpperBoundsProvider(firSession);
        if (platformUpperBoundsProvider == null) {
            return CollectionsKt.emptyList();
        }
        return platformUpperBoundsProvider instanceof FirPlatformUpperBoundsProvider.Composed ? ((FirPlatformUpperBoundsProvider.Composed) platformUpperBoundsProvider).getComponents() : CollectionsKt.listOf(platformUpperBoundsProvider);
    }

    private static final SourceAttribute getSourceAttribute(ConeAttributes coneAttributes) {
        return (SourceAttribute) sourceAttribute$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }

    private static final boolean reportUpperBoundViolationWarningIfNecessary(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameterSymbol firTypeParameterSymbol, FirPlatformUpperBoundsProvider firPlatformUpperBoundsProvider, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, ConeInferenceContext coneInferenceContext, boolean z, FirTypeRef firTypeRef, KtSourceElement ktSourceElement) {
        ConeKotlinType coneType;
        ConeKotlinType additionalUpperBound = firPlatformUpperBoundsProvider.getAdditionalUpperBound(coneKotlinType2);
        if (additionalUpperBound == null || AbstractTypeChecker.INSTANCE.isSubtypeOf(coneInferenceContext, coneKotlinType, additionalUpperBound, true)) {
            return false;
        }
        KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> diagnosticForTypeAlias = (z && firTypeRef == null) ? firPlatformUpperBoundsProvider.getDiagnosticForTypeAlias() : firPlatformUpperBoundsProvider.getDiagnostic();
        ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute explicitTypeArgumentIfMadeFlexibleSynthetically = ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt.getExplicitTypeArgumentIfMadeFlexibleSynthetically(coneKotlinType.getAttributes());
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeTypeParameterType>) ((KtDiagnosticFactory3<Object, Object, Object>) diagnosticForTypeAlias), coneKotlinType2, (explicitTypeArgumentIfMadeFlexibleSynthetically == null || (coneType = explicitTypeArgumentIfMadeFlexibleSynthetically.getConeType()) == null) ? coneKotlinType : coneType, FirNestedClassifierScopeKt.toConeType(firTypeParameterSymbol), (64 & 64) != 0 ? null : null);
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final List<ConeTypeProjection> toTypeArgumentsWithSourceInfo(List<? extends FirTypeProjection> list) throws KotlinIllegalArgumentExceptionWithAttachments {
        list.getClass();
        List<? extends FirTypeProjection> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (FirTypeProjection firTypeProjection : list2) {
            ConeTypeProjection coneTypeProjection = FirTypeUtilsKt.toConeTypeProjection(firTypeProjection);
            FirTypeRef typeRef = null;
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = firTypeProjection instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) firTypeProjection : null;
            if (firTypeProjectionWithVariance != null) {
                typeRef = firTypeProjectionWithVariance.getTypeRef();
            }
            arrayList.add(withSource(coneTypeProjection, new FirTypeRefSource(typeRef, firTypeProjection.getSource())));
        }
        return arrayList;
    }

    public static final ConeTypeProjection withSource(ConeTypeProjection coneTypeProjection, FirTypeRefSource firTypeRefSource) {
        coneTypeProjection.getClass();
        if (firTypeRefSource == null || !(coneTypeProjection instanceof ConeKotlinTypeProjection)) {
            return coneTypeProjection;
        }
        ConeKotlinTypeProjection coneKotlinTypeProjection = (ConeKotlinTypeProjection) coneTypeProjection;
        return ConeTypeProjectionKt.replaceType(coneKotlinTypeProjection, TypeUtilsKt.withAttributes(coneKotlinTypeProjection.getType(), ConeAttributes.INSTANCE.create(CollectionsKt.listOf(new SourceAttribute(firTypeRefSource))).add(coneKotlinTypeProjection.getType().getAttributes())));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ void checkUpperBoundViolated$default(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, ConeClassLikeType coneClassLikeType, boolean z, KtSourceElement ktSourceElement, boolean z2, boolean z3, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 16) != 0) {
            z = false;
        }
        if ((i & 64) != 0) {
            z2 = false;
        }
        if ((i & 128) != 0) {
            z3 = false;
        }
        checkUpperBoundViolated(checkerContext, diagnosticReporter, firTypeRef, coneClassLikeType, z, ktSourceElement, z2, z3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ void checkUpperBoundViolated$default(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, boolean z, boolean z2, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 8) != 0) {
            z = false;
        }
        if ((i & 16) != 0) {
            z2 = false;
        }
        checkUpperBoundViolated(checkerContext, diagnosticReporter, firTypeRef, z, z2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkUpperBoundViolated(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, ConeClassLikeType coneClassLikeType, boolean z, KtSourceElement ktSourceElement, boolean z2, boolean z3) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeClassLikeType coneClassLikeType2;
        ConeClassLikeType coneClassLikeTypeFullyExpandedType;
        FirRegularClassSymbol regularClassSymbol;
        ConeKotlinType coneKotlinTypeFullyExpandedTypeWithSource;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        coneClassLikeType.getClass();
        if (firTypeRef != null) {
            ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneClassLikeType);
            ConeClassLikeType coneClassLikeType3 = abbreviatedTypeOrSelf instanceof ConeClassLikeType ? (ConeClassLikeType) abbreviatedTypeOrSelf : null;
            if (coneClassLikeType3 == null || (coneKotlinTypeFullyExpandedTypeWithSource = fullyExpandedTypeWithSource(coneClassLikeType3, firTypeRef, checkerContext.getSession())) == null) {
                return;
            }
            ConeTypeProjection[] typeArguments = coneKotlinTypeFullyExpandedTypeWithSource.getTypeArguments();
            if (typeArguments.length != 0) {
                int length = typeArguments.length;
                ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
                for (int i = 0; i < length; i++) {
                    coneTypeProjectionArr[i] = withSource(typeArguments[i], new FirTypeRefSource(null, firTypeRef.getSource()));
                }
                coneKotlinTypeFullyExpandedTypeWithSource = TypeUtilsKt.withArguments(coneKotlinTypeFullyExpandedTypeWithSource, coneTypeProjectionArr);
            }
            coneClassLikeTypeFullyExpandedType = (ConeClassLikeType) coneKotlinTypeFullyExpandedTypeWithSource;
            if (coneClassLikeTypeFullyExpandedType == null) {
                return;
            } else {
                coneClassLikeType2 = coneClassLikeType;
            }
        } else {
            coneClassLikeType2 = coneClassLikeType;
            coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext, coneClassLikeType2);
        }
        if (coneClassLikeTypeFullyExpandedType.getTypeArguments().length == 0 || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeTypeFullyExpandedType.getLookupTag())) == null) {
            return;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = regularClassSymbol.getTypeParameterSymbols();
        if (typeParameterSymbols.isEmpty()) {
            return;
        }
        checkUpperBoundViolated$default(checkerContext, diagnosticReporter, typeParameterSymbols, ArraysKt.toList(coneClassLikeTypeFullyExpandedType.getTypeArguments()), new FE10LikeConeSubstitutor(MapsKt.toMap(CollectionsKt.zip(typeParameterSymbols, coneClassLikeTypeFullyExpandedType.getTypeArguments())), checkerContext.getSession()), true, z, ktSourceElement, z2, z3, false, !Intrinsics.areEqual(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneClassLikeType2)), AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneClassLikeType2)), 1024, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkUpperBoundViolated(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, boolean z, boolean z2) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = (firTypeRef == null || (coneType = FirTypeUtilsKt.getConeType(firTypeRef)) == null) ? null : ConeTypeUtilsKt.lowerBoundIfFlexible(coneType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null) {
            return;
        }
        checkUpperBoundViolated$default(checkerContext, diagnosticReporter, firTypeRef, coneClassLikeType, z, firTypeRef.getSource(), z2, false, 128, null);
    }
}

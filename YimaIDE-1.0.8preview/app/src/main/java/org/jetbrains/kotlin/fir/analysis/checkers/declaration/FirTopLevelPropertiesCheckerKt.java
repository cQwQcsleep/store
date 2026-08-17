package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.cfa.FirPropertyInitializationAnalyzerKt;
import org.jetbrains.kotlin.fir.analysis.cfa.PropertyInitializationCheckProcessor;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventOccurrencesRangeAtNode;
import org.jetbrains.kotlin.fir.analysis.cfa.util.PropertyInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.NormalPath;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeLocalVariableNoTypeOrInitializer;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u001e\u0012\f\u0012\n\u0012\u0002\b\u00030\u000ej\u0002`\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rj\u0004\u0018\u0001`\u0011*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0012\u001aS\u0010\u0013\u001a\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001aH\u0000R\u00020\u0004R\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a\f\u0010\u001d\u001a\u00020\u001a*\u00020\u001eH\u0002\u001aO\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001aH\u0002R\u00020\u0004R\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010#\u001a\u0018\u00103\u001a\u0004\u0018\u000104*\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002\u001a \u00103\u001a\u0004\u0018\u000104*\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u00105\u001a\u000206H\u0002\"\u0018\u0010$\u001a\u00020%*\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u001a\u0010(\u001a\u00020\u001a*\u0004\u0018\u00010)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0018\u0010,\u001a\u00020\u001a*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u0018\u0010/\u001a\u00020\u001a*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u0010.\"\u0018\u00101\u001a\u00020\u001a*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u0010.\"\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"checkFileLikeDeclaration", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "topLevelPropertySymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/List;)V", "collectionInitializationInfo", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfo;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/List;)Lkotlinx/collections/immutable/PersistentMap;", "checkPropertyInitializer", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "propertySymbol", "modifierList", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", "isDefinitelyAssigned", Argument.Delimiters.none, "reachable", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;ZZ)V", "noExplicitType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "reportMustBeInitialized", "propertySource", "Lorg/jetbrains/kotlin/KtSourceElement;", "isOpenValDeferredInitDeprecationWarning", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;ZLorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "deprecationWarning", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getDeprecationWarning", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "hasImplementation", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "getHasImplementation", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;)Z", "hasSetterAccessorImplementation", "getHasSetterAccessorImplementation", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "hasAnyAccessorImplementation", "getHasAnyAccessorImplementation", "hasAllAccessorImplementation", "getHasAllAccessorImplementation", "getEffectiveModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "KOTLINX_SERIALIZATION_TRANSIENT", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTopLevelPropertiesCheckerKt {
    private static final ClassId KOTLINX_SERIALIZATION_TRANSIENT;

    static {
        FqName fqNameFromSegments = FqName.Companion.fromSegments(StringsKt.split$default("kotlinx.serialization", new String[]{"."}, false, 0, 6, (Object) null));
        Name nameIdentifier = Name.identifier("Transient");
        nameIdentifier.getClass();
        KOTLINX_SERIALIZATION_TRANSIENT = new ClassId(fqNameFromSegments, nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:19:0x0040  */
    public static final void checkFileLikeDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, List<? extends FirPropertySymbol> list) throws KotlinIllegalArgumentExceptionWithAttachments {
        boolean z;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode> persistentMapCollectionInitializationInfo = collectionInitializationInfo(checkerContext, diagnosticReporter, firDeclaration, list);
        for (FirPropertySymbol firPropertySymbol : list) {
            EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = persistentMapCollectionInitializationInfo != null ? (EventOccurrencesRangeAtNode) persistentMapCollectionInitializationInfo.get(firPropertySymbol) : null;
            if (eventOccurrencesRangeAtNode == null || (range = eventOccurrencesRangeAtNode.getRange()) == null) {
                z = false;
            } else {
                z = true;
                if (!EventOccurrencesRangeKt.isDefinitelyVisited(range) || (firPropertySymbol.getRawStatus().isLateInit() && eventOccurrencesRangeAtNode.getMustBeLateinit())) {
                    z = false;
                }
            }
            FirMemberPropertiesCheckerKt.checkProperty(checkerContext, diagnosticReporter, null, firPropertySymbol, z, true);
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:111:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:167:0x029b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:168:0x029d  */
    /* JADX WARN: Code duplicated, block: B:169:0x02af  */
    /* JADX WARN: Code duplicated, block: B:172:0x02b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:196:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:197:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    public static final void checkPropertyInitializer(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirClass firClass, FirPropertySymbol firPropertySymbol, FirModifierList firModifierList, boolean z, boolean z2) {
        KtSourceElement source;
        CheckerContext checkerContext2;
        boolean z3;
        KtSourceElement ktSourceElement;
        KtSourceElement ktSourceElement2;
        CheckerContext checkerContext3;
        FirBackingFieldSymbol backingFieldSymbol;
        KtSourceElement source2;
        FirPropertyAccessorSymbol getterSymbol;
        FirPropertyAccessorSymbol getterSymbol2;
        FirPropertyAccessorSymbol getterSymbol3;
        FirResolvedTypeRef resolvedReturnTypeRef;
        KtSourceElement source3;
        KtSourceElement source4;
        FirPropertySymbol firPropertySymbol2 = firPropertySymbol;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        firPropertySymbol2.getClass();
        boolean z4 = firClass != null && firClass.getClassKind() == ClassKind.INTERFACE;
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ABSTRACT_KEYWORD;
        ktModifierKeywordToken.getClass();
        boolean zContains = FirKeywordUtilsKt.contains(firModifierList, ktModifierKeywordToken);
        if (firPropertySymbol2.getResolvedStatus().getModality() == Modality.ABSTRACT || zContains) {
            FirResolvedTypeRef resolvedReturnTypeRef2 = firPropertySymbol2.getResolvedReturnTypeRef();
            if (firPropertySymbol2.getHasInitializer() || firPropertySymbol2.getDelegate() != null || !noExplicitType(resolvedReturnTypeRef2) || (source = firPropertySymbol2.getSource()) == null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getABSTRACT_PROPERTY_WITHOUT_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        boolean hasBackingField = DeclarationAttributesKt.getHasBackingField(firPropertySymbol2);
        if (z4 && hasBackingField && getHasAnyAccessorImplementation(firPropertySymbol2) && (source4 = firPropertySymbol2.getSource()) != null) {
            checkerContext2 = checkerContext;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) source4, FirErrors.INSTANCE.getBACKING_FIELD_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            checkerContext2 = checkerContext;
        }
        boolean zIsEffectivelyExpect = DeclarationUtilsKt.isEffectivelyExpect(checkerContext2, firPropertySymbol2, firClass != null ? firClass.getSymbol() : null);
        if (firPropertySymbol2.getHasInitializer()) {
            KtSourceElement initializerSource = firPropertySymbol2.getInitializerSource();
            if (initializerSource != null) {
                if (z4) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) initializerSource, FirErrors.INSTANCE.getPROPERTY_INITIALIZER_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                if (zIsEffectivelyExpect) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializerSource, FirErrors.INSTANCE.getEXPECTED_PROPERTY_INITIALIZER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                if (!hasBackingField) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializerSource, FirErrors.INSTANCE.getPROPERTY_INITIALIZER_NO_BACKING_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                } else {
                    if (firPropertySymbol2.getReceiverParameterSymbol() == null || FirSymbolStatusUtilsKt.isCompanionExtension(firPropertySymbol2)) {
                        return;
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializerSource, FirErrors.INSTANCE.getEXTENSION_PROPERTY_WITH_BACKING_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            }
            return;
        }
        if (firPropertySymbol2.getDelegate() != null) {
            FirExpression delegate = firPropertySymbol2.getDelegate();
            if (delegate == null || (source3 = delegate.getSource()) == null) {
                return;
            }
            if (z4) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source3, FirErrors.INSTANCE.getDELEGATED_PROPERTY_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            } else {
                if (zIsEffectivelyExpect) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source3, FirErrors.INSTANCE.getEXPECTED_DELEGATED_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                return;
            }
        }
        KtSourceElement source5 = firPropertySymbol2.getSource();
        if (source5 == null) {
            return;
        }
        boolean zIsEffectivelyExternal = DeclarationUtilsKt.isEffectivelyExternal(checkerContext, firPropertySymbol2, firClass != null ? firClass.getSymbol() : null);
        boolean z5 = noExplicitType(firPropertySymbol2.getResolvedReturnTypeRef()) && !DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol2) && (((getterSymbol = firPropertySymbol2.getGetterSymbol()) != null && getterSymbol.isDefault()) || !((getterSymbol2 = firPropertySymbol2.getGetterSymbol()) == null || !getterSymbol2.getHasBody() || (getterSymbol3 = firPropertySymbol2.getGetterSymbol()) == null || (resolvedReturnTypeRef = getterSymbol3.getResolvedReturnTypeRef()) == null || !noExplicitType(resolvedReturnTypeRef)));
        if (firPropertySymbol2.getHasInitializer()) {
            z3 = true;
        } else if (DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol2)) {
            FirBackingFieldSymbol backingFieldSymbol2 = firPropertySymbol2.getBackingFieldSymbol();
            if ((backingFieldSymbol2 != null ? backingFieldSymbol2.getResolvedInitializer() : null) == null) {
                if (z) {
                }
                z3 = false;
            } else {
                z3 = true;
            }
        } else if (z || getHasSetterAccessorImplementation(firPropertySymbol2) || (getEffectiveModality(firPropertySymbol2, firClass, checkerContext.get$languageVersionSettings()) == Modality.OPEN && !Intrinsics.areEqual(firPropertySymbol2.getResolvedStatus().getEffectiveVisibility(), EffectiveVisibility.PrivateInClass.INSTANCE))) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (hasBackingField && !z4 && !firPropertySymbol2.getRawStatus().isLateInit() && (((backingFieldSymbol = firPropertySymbol2.getBackingFieldSymbol()) == null || !backingFieldSymbol.getRawStatus().isLateInit()) && !zIsEffectivelyExpect && !zIsEffectivelyExternal)) {
            if (firPropertySymbol2.getReceiverParameterSymbol() == null || getHasAllAccessorImplementation(firPropertySymbol2) || DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol2)) {
                ktSourceElement = source5;
                if (!z3 && z2) {
                    boolean z6 = LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProhibitOpenValDeferredInitialization) && getEffectiveModality(firPropertySymbol2, firClass, checkerContext.get$languageVersionSettings()) == Modality.OPEN && firPropertySymbol2.isVal() && z;
                    if (!z6 || getEffectiveModality(firPropertySymbol2, firClass) != Modality.FINAL) {
                        FirBackingFieldSymbol backingFieldSymbol3 = DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol2) ? firPropertySymbol2.getBackingFieldSymbol() : null;
                        reportMustBeInitialized(diagnosticReporter, checkerContext, firPropertySymbol2, z, firClass, (backingFieldSymbol3 == null || (source2 = backingFieldSymbol3.getSource()) == null) ? ktSourceElement : source2, z6);
                        firPropertySymbol2 = firPropertySymbol2;
                    }
                }
                if (firPropertySymbol2.getRawStatus().isLateInit()) {
                    if (zIsEffectivelyExpect) {
                        checkerContext3 = checkerContext;
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext3, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getEXPECTED_LATEINIT_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    } else {
                        checkerContext3 = checkerContext;
                    }
                    if (LanguageVersionUtilsKt.isEnabled(checkerContext3, LanguageFeature.EnableDfaWarningsInK2) || !hasBackingField || DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol2) || z4 || !z3) {
                        return;
                    }
                    FirBackingFieldSymbol backingFieldSymbol4 = firPropertySymbol2.getBackingFieldSymbol();
                    if ((backingFieldSymbol4 == null || !FirAnnotationUtilsKt.hasAnnotation(backingFieldSymbol4, StandardClassIds$Annotations.INSTANCE.getTransient(), checkerContext3.getSession())) && !FirAnnotationUtilsKt.hasAnnotation(firPropertySymbol2, KOTLINX_SERIALIZATION_TRANSIENT, checkerContext3.getSession())) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext3, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getUNNECESSARY_LATEINIT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        return;
                    }
                    return;
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source5, FirErrors.INSTANCE.getEXTENSION_PROPERTY_MUST_HAVE_ACCESSORS_OR_BE_ABSTRACT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            ktSourceElement = source5;
            ktSourceElement2 = ktSourceElement;
            if (firPropertySymbol2.getRawStatus().isLateInit()) {
                if (zIsEffectivelyExpect) {
                    checkerContext3 = checkerContext;
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext3, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getEXPECTED_LATEINIT_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else {
                    checkerContext3 = checkerContext;
                }
                if (LanguageVersionUtilsKt.isEnabled(checkerContext3, LanguageFeature.EnableDfaWarningsInK2)) {
                }
            }
        }
        ktSourceElement = source5;
        if (z5) {
            ktSourceElement2 = ktSourceElement;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, firPropertySymbol2.getRawStatus().isLateInit() ? FirErrors.INSTANCE.getLATEINIT_PROPERTY_WITHOUT_TYPE() : FirErrors.INSTANCE.getPROPERTY_WITH_NO_TYPE_NO_INITIALIZER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            ktSourceElement2 = ktSourceElement;
        }
        if (firPropertySymbol2.getRawStatus().isLateInit()) {
            if (zIsEffectivelyExpect) {
                checkerContext3 = checkerContext;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext3, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getEXPECTED_LATEINIT_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext3 = checkerContext;
            }
            if (LanguageVersionUtilsKt.isEnabled(checkerContext3, LanguageFeature.EnableDfaWarningsInK2)) {
            }
        }
    }

    public static /* synthetic */ void checkPropertyInitializer$default(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirClass firClass, FirPropertySymbol firPropertySymbol, FirModifierList firModifierList, boolean z, boolean z2, int i, Object obj) {
        if ((i & 64) != 0) {
            z2 = true;
        }
        checkPropertyInitializer(diagnosticReporter, checkerContext, firClass, firPropertySymbol, firModifierList, z, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode> collectionInitializationInfo(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, List<? extends FirPropertySymbol> list) {
        FirControlFlowGraphReference controlFlowGraphReference;
        ControlFlowGraph controlFlowGraph;
        FirControlFlowGraphOwner firControlFlowGraphOwner = firDeclaration instanceof FirControlFlowGraphOwner ? (FirControlFlowGraphOwner) firDeclaration : null;
        if (firControlFlowGraphOwner == null || (controlFlowGraphReference = firControlFlowGraphOwner.getControlFlowGraphReference()) == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (FirPropertySymbol firPropertySymbol : list) {
            if (!FirPropertyInitializationAnalyzerKt.requiresInitialization(firPropertySymbol, true)) {
                firPropertySymbol = null;
            }
            if (firPropertySymbol != null) {
                linkedHashSet.add(firPropertySymbol);
            }
        }
        if (linkedHashSet.isEmpty()) {
            return null;
        }
        PropertyInitializationInfoData propertyInitializationInfoData = new PropertyInitializationInfoData(linkedHashSet, SetsKt.emptySet(), null, controlFlowGraph);
        PropertyInitializationCheckProcessor.INSTANCE.check(checkerContext, diagnosticReporter, propertyInitializationInfoData, true);
        return (PersistentMap) propertyInitializationInfoData.getValue(controlFlowGraph.getExitNode()).get(NormalPath.INSTANCE);
    }

    private static final KtDiagnosticFactory0 getDeprecationWarning(KtDiagnosticFactory0 ktDiagnosticFactory0) {
        FirErrors firErrors = FirErrors.INSTANCE;
        if (Intrinsics.areEqual(ktDiagnosticFactory0, firErrors.getMUST_BE_INITIALIZED())) {
            return firErrors.getMUST_BE_INITIALIZED_WARNING();
        }
        if (Intrinsics.areEqual(ktDiagnosticFactory0, firErrors.getMUST_BE_INITIALIZED_OR_BE_ABSTRACT())) {
            return firErrors.getMUST_BE_INITIALIZED_OR_BE_ABSTRACT_WARNING();
        }
        if (Intrinsics.areEqual(ktDiagnosticFactory0, firErrors.getMUST_BE_INITIALIZED_OR_BE_FINAL())) {
            return firErrors.getMUST_BE_INITIALIZED_OR_BE_FINAL_WARNING();
        }
        if (Intrinsics.areEqual(ktDiagnosticFactory0, firErrors.getMUST_BE_INITIALIZED_OR_FINAL_OR_ABSTRACT())) {
            return firErrors.getMUST_BE_INITIALIZED_OR_FINAL_OR_ABSTRACT_WARNING();
        }
        k2d.a("Only MUST_BE_INITIALIZED is supported");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0022  */
    private static final Modality getEffectiveModality(FirPropertySymbol firPropertySymbol, FirClass firClass) {
        boolean z;
        FirDeclarationStatus status;
        if (firPropertySymbol.getResolvedStatus().getModality() != Modality.OPEN) {
            z = false;
        } else {
            if (((firClass == null || (status = firClass.getStatus()) == null) ? null : status.getModality()) == Modality.FINAL) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            return Modality.FINAL;
        }
        if (!z) {
            return firPropertySymbol.getResolvedStatus().getModality();
        }
        bu8.a();
        return null;
    }

    private static final boolean getHasAllAccessorImplementation(FirPropertySymbol firPropertySymbol) {
        if (getHasImplementation(firPropertySymbol.getGetterSymbol())) {
            return firPropertySymbol.isVal() || getHasImplementation(firPropertySymbol.getSetterSymbol());
        }
        return false;
    }

    private static final boolean getHasAnyAccessorImplementation(FirPropertySymbol firPropertySymbol) {
        return getHasImplementation(firPropertySymbol.getGetterSymbol()) || getHasImplementation(firPropertySymbol.getSetterSymbol());
    }

    private static final boolean getHasImplementation(FirPropertyAccessorSymbol firPropertyAccessorSymbol) {
        return (firPropertyAccessorSymbol == null || !firPropertyAccessorSymbol.isDefault()) && firPropertyAccessorSymbol != null && firPropertyAccessorSymbol.getHasBody();
    }

    private static final boolean getHasSetterAccessorImplementation(FirPropertySymbol firPropertySymbol) {
        return getHasImplementation(firPropertySymbol.getSetterSymbol());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean noExplicitType(FirTypeRef firTypeRef) {
        return (firTypeRef instanceof FirErrorTypeRef) && (((FirErrorTypeRef) firTypeRef).getDiagnostic() instanceof ConeLocalVariableNoTypeOrInitializer);
    }

    private static final void reportMustBeInitialized(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirPropertySymbol firPropertySymbol, boolean z, FirClass firClass, KtSourceElement ktSourceElement, boolean z2) {
        KtDiagnosticFactory0 must_be_initialized_or_be_abstract;
        if (firPropertySymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
            k2d.a("reportMustBeInitialized isn't called for abstract properties");
            return;
        }
        boolean z3 = (firClass == null || getHasSetterAccessorImplementation(firPropertySymbol) || getEffectiveModality(firPropertySymbol, firClass, checkerContext.get$languageVersionSettings()) == Modality.FINAL || !z) ? false : true;
        boolean z4 = (firClass == null || getHasAnyAccessorImplementation(firPropertySymbol) || DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol)) ? false : true;
        if (z2 && !z3 && z4) {
            k2d.a("Not reachable case. Every \"open val + deferred init\" case that could be made `abstract`, also could be made `final`");
            return;
        }
        boolean z5 = LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProhibitMissedMustBeInitializedWhenThereIsNoPrimaryConstructor) && firClass != null && org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession()) == null && z;
        if (DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol)) {
            must_be_initialized_or_be_abstract = FirErrors.INSTANCE.getEXPLICIT_FIELD_MUST_BE_INITIALIZED();
        } else if (z3 && z4) {
            must_be_initialized_or_be_abstract = FirErrors.INSTANCE.getMUST_BE_INITIALIZED_OR_FINAL_OR_ABSTRACT();
        } else if (z3) {
            must_be_initialized_or_be_abstract = FirErrors.INSTANCE.getMUST_BE_INITIALIZED_OR_BE_FINAL();
        } else {
            must_be_initialized_or_be_abstract = z4 ? FirErrors.INSTANCE.getMUST_BE_INITIALIZED_OR_BE_ABSTRACT() : FirErrors.INSTANCE.getMUST_BE_INITIALIZED();
        }
        boolean z6 = z5 || z2;
        if (z6) {
            must_be_initialized_or_be_abstract = getDeprecationWarning(must_be_initialized_or_be_abstract);
        } else if (z6) {
            bu8.a();
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, must_be_initialized_or_be_abstract, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private static final Modality getEffectiveModality(FirPropertySymbol firPropertySymbol, FirClass firClass, LanguageVersionSettings languageVersionSettings) {
        boolean zSupportsFeature = languageVersionSettings.supportsFeature(LanguageFeature.TakeIntoAccountEffectivelyFinalInMustBeInitializedCheck);
        if (zSupportsFeature) {
            return getEffectiveModality(firPropertySymbol, firClass);
        }
        if (!zSupportsFeature) {
            return firPropertySymbol.getResolvedStatus().getModality();
        }
        bu8.a();
        return null;
    }
}

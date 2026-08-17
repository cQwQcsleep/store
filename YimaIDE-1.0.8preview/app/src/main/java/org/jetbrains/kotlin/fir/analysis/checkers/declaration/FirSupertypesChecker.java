package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import com.intellij.lang.LighterASTNode;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
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
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirInconsistentTypeParameterHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeRefSource;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J;\u0010\u0012\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J5\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001aJ=\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001dJ-\u0010\u001e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J\u000e\u0010 \u001a\u0004\u0018\u00010!*\u00020\"H\u0002J-\u0010#\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSupertypesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkAnnotationOnSuperclass", "superTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "checkClassCannotBeExtendedDirectly", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "checkProjectionInImmediateArgumentToSupertype", Argument.Delimiters.none, "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Z", "checkSupertypeOnTypeAliasWithTypeProjection", "fullyExpandedType", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "checkDelegationNotToInterface", "checkNamedFunctionTypeParameter", "findSourceForParameterName", "Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "checkDelegationWithoutPrimaryConstructor", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSupertypesChecker extends FirDeclarationChecker<FirClass> {
    public static final FirSupertypesChecker INSTANCE = new FirSupertypesChecker();

    private FirSupertypesChecker() {
        super(MppCheckerKind.Platform);
    }

    private final void checkAnnotationOnSuperclass(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef) {
        for (FirAnnotation firAnnotation : firTypeRef.getAnnotations()) {
            if (firAnnotation.getUseSiteTarget() != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getANNOTATION_ON_SUPERCLASS_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkClassCannotBeExtendedDirectly(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirClassifierSymbol<?> firClassifierSymbol, FirTypeRef firTypeRef) {
        if ((firClassifierSymbol instanceof FirRegularClassSymbol) && Intrinsics.areEqual(((FirRegularClassSymbol) firClassifierSymbol).getClassId(), StandardClassIds.INSTANCE.getEnum())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCLASS_CANNOT_BE_EXTENDED_DIRECTLY(), (Object) firClassifierSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkDelegationNotToInterface(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        FirRegularClassSymbol regularClassSymbol;
        for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
            if ((firDeclaration instanceof FirField) && Intrinsics.areEqual(((FirMemberDeclaration) firDeclaration).getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
                FirField firField = (FirField) firDeclaration;
                if (FirHelpersKt.isDelegated(firField.getName()) && (regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(firField.getReturnTypeRef(), checkerContext.getSession())) != null && regularClassSymbol.getClassKind() != ClassKind.INTERFACE) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firField.getReturnTypeRef().getSource(), FirErrors.INSTANCE.getDELEGATION_NOT_TO_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    private final void checkDelegationWithoutPrimaryConstructor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        if (firClass.getClassKind() == ClassKind.INTERFACE || firClass.getStatus().isExpect() || org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession()) != null) {
            return;
        }
        for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
            if (firDeclaration instanceof FirField) {
                FirField firField = (FirField) firDeclaration;
                if (Intrinsics.areEqual(firField.getSymbol().getResolvedStatus().getVisibility(), Visibilities.Private.INSTANCE) && FirHelpersKt.isDelegated(firField.getName())) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firField.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Delegation without primary constructor is unsupported.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    private final void checkNamedFunctionTypeParameter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef) {
        FirFunctionTypeRef delegatedTypeRef;
        KtSourceElement ktSourceElementFindSourceForParameterName;
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        if (firResolvedTypeRef == null || (delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef()) == null || !(delegatedTypeRef instanceof FirFunctionTypeRef)) {
            return;
        }
        for (FirFunctionTypeParameter firFunctionTypeParameter : delegatedTypeRef.getParameters()) {
            if (firFunctionTypeParameter.getName() != null && (ktSourceElementFindSourceForParameterName = findSourceForParameterName(firFunctionTypeParameter)) != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElementFindSourceForParameterName, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Named parameter in function type as supertype is unsupported.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private final boolean checkProjectionInImmediateArgumentToSupertype(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirTypeRef firTypeRef) {
        DiagnosticReporter diagnosticReporter2;
        CheckerContext checkerContext2;
        FirTypeRefSource firTypeRefSource;
        List<FirTypeRefSource> listExtractArgumentsTypeRefAndSource = FirHelpersKt.extractArgumentsTypeRefAndSource(firTypeRef);
        int i = 0;
        if (listExtractArgumentsTypeRefAndSource == null) {
            return false;
        }
        ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
        int length = typeArguments.length;
        boolean z = false;
        while (i < length) {
            if (!FirHelpersKt.isConflictingOrNotInvariant(typeArguments[i]) || (firTypeRefSource = (FirTypeRefSource) CollectionsKt.getOrNull(listExtractArgumentsTypeRefAndSource, i)) == null) {
                diagnosticReporter2 = diagnosticReporter;
                checkerContext2 = checkerContext;
            } else {
                KtSourceElement source = firTypeRefSource.getSource();
                if (source == null) {
                    source = firTypeRef.getSource();
                }
                diagnosticReporter2 = diagnosticReporter;
                checkerContext2 = checkerContext;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getPROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                z = true;
            }
            i++;
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
        return z;
    }

    private final void checkSupertypeOnTypeAliasWithTypeProjection(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirTypeRef firTypeRef) {
        if (ToSymbolUtilsKt.toSymbol(checkerContext, coneKotlinType) instanceof FirTypeAliasSymbol) {
            ConeTypeProjection[] typeArguments = coneKotlinType2.getTypeArguments();
            for (ConeTypeProjection coneTypeProjection : typeArguments) {
                if (FirHelpersKt.isConflictingOrNotInvariant(coneTypeProjection)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getCONSTRUCTOR_OR_SUPERTYPE_ON_TYPEALIAS_WITH_TYPE_PROJECTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            }
        }
    }

    private final KtSourceElement findSourceForParameterName(FirFunctionTypeParameter firFunctionTypeParameter) {
        Object next;
        Name name = firFunctionTypeParameter.getName();
        if (name == null) {
            return null;
        }
        FlyweightCapableTreeStructure treeStructure = firFunctionTypeParameter.getSource().getTreeStructure();
        Iterator it = LightTreeUtilsKt.getChildren(firFunctionTypeParameter.getSource().getLighterASTNode(), treeStructure).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            LighterASTNode lighterASTNode = (LighterASTNode) next;
            if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtTokens.IDENTIFIER) && Intrinsics.areEqual(treeStructure.toString(lighterASTNode), name.getIdentifier())) {
                break;
            }
        }
        LighterASTNode lighterASTNode2 = (LighterASTNode) next;
        if (lighterASTNode2 == null) {
            return null;
        }
        return new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), treeStructure, KtRealSourceElementKind.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0067  */
    /* JADX WARN: Code duplicated, block: B:33:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:38:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:49:0x0120  */
    /* JADX WARN: Code duplicated, block: B:52:0x0126  */
    /* JADX WARN: Code duplicated, block: B:54:0x012c  */
    /* JADX WARN: Code duplicated, block: B:55:0x014a  */
    /* JADX WARN: Code duplicated, block: B:58:0x015b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:59:0x015d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x015f  */
    /* JADX WARN: Code duplicated, block: B:61:0x0175  */
    /* JADX WARN: Code duplicated, block: B:68:0x019a  */
    /* JADX WARN: Code duplicated, block: B:69:0x019d  */
    /* JADX WARN: Code duplicated, block: B:80:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:81:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:84:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:93:0x020c A[SYNTHETIC] */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        ConeKotlinType coneKotlinType;
        boolean z;
        ConeKotlinType coneKotlinType2;
        FirTypeRef firTypeRef;
        DiagnosticReporter diagnosticReporter2;
        FirTypeRef firTypeRef2;
        ConeKotlinType coneKotlinType3;
        FirClassifierSymbol<?> symbol;
        boolean z2;
        ConeKotlinType coneKotlinType4;
        FirClassifierSymbol<?> firClassifierSymbol;
        FirTypeRef firTypeRef3;
        FirRegularClassSymbol firRegularClassSymbol;
        boolean z3;
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        CheckerContext checkerContext2 = checkerContext;
        checkerContext2.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        KtSourceElement source = firClass.getSource();
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
            return;
        }
        boolean z4 = firClass.getClassKind() == ClassKind.INTERFACE;
        HashSet hashSet = new HashSet();
        boolean z5 = !z4;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        for (FirTypeRef firTypeRef4 : firClass.getSuperTypeRefs()) {
            if (firTypeRef4.getSource() != null) {
                KtSourceElement source2 = firTypeRef4.getSource();
                if (!Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.EnumSuperTypeRef.INSTANCE)) {
                    ConeKotlinType coneKotlinTypeFullyExpandedType2 = TypeExpansionUtilsKt.fullyExpandedType(checkerContext2, FirTypeUtilsKt.getConeType(firTypeRef4));
                    ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneKotlinTypeFullyExpandedType2);
                    boolean z10 = abbreviatedTypeOrSelf instanceof ConeDynamicType;
                    if (ConeTypeUtilsKt.isMarkedNullable(abbreviatedTypeOrSelf)) {
                        coneKotlinType2 = coneKotlinTypeFullyExpandedType2;
                        coneKotlinType = abbreviatedTypeOrSelf;
                        firTypeRef = firTypeRef4;
                        z = z10;
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firTypeRef4.getSource(), FirErrors.INSTANCE.getNULLABLE_SUPERTYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    } else {
                        coneKotlinType = abbreviatedTypeOrSelf;
                        z = z10;
                        coneKotlinType2 = coneKotlinTypeFullyExpandedType2;
                        firTypeRef = firTypeRef4;
                        if (ConeTypeUtilsKt.isMarkedNullable(coneKotlinType2)) {
                            checkerContext2 = checkerContext;
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getNULLABLE_SUPERTYPE_THROUGH_TYPEALIAS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                        if (z6) {
                            diagnosticReporter2 = diagnosticReporter;
                        } else {
                            coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext2, coneKotlinType);
                            if ((!CompilerConeAttributesKt.isExtensionFunctionType(coneKotlinTypeFullyExpandedType) || CompilerConeAttributesKt.getHasContextParameters(coneKotlinTypeFullyExpandedType)) && LanguageVersionUtilsKt.isDisabled(checkerContext2, LanguageFeature.FunctionalTypeWithExtensionAsSupertype)) {
                                diagnosticReporter2 = diagnosticReporter;
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getSUPERTYPE_IS_EXTENSION_OR_CONTEXT_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                z6 = true;
                            } else {
                                diagnosticReporter2 = diagnosticReporter;
                            }
                        }
                        firTypeRef2 = firTypeRef;
                        checkAnnotationOnSuperclass(checkerContext2, diagnosticReporter2, firTypeRef2);
                        coneKotlinType3 = coneKotlinType2;
                        symbol = ToSymbolUtilsKt.toSymbol(checkerContext2, coneKotlinType3);
                        if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext2.getSession()).supportsFeature(LanguageFeature.AllowAnyAsAnActualTypeForExpectInterface) || !ConeBuiltinTypeUtilsKt.isAny(coneKotlinType3) || AbbreviatedTypeAttributeKt.getAbbreviatedType(coneKotlinType3) == null) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        if (symbol instanceof FirRegularClassSymbol) {
                            if (hashSet.add(symbol)) {
                                firTypeRef3 = firTypeRef2;
                                coneKotlinType4 = coneKotlinType3;
                                firClassifierSymbol = symbol;
                            } else {
                                coneKotlinType4 = coneKotlinType3;
                                firTypeRef3 = firTypeRef2;
                                firClassifierSymbol = symbol;
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef2.getSource(), FirErrors.INSTANCE.getSUPERTYPE_APPEARS_TWICE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            }
                            firRegularClassSymbol = (FirRegularClassSymbol) firClassifierSymbol;
                            if (firRegularClassSymbol.getClassKind() != ClassKind.INTERFACE) {
                                if (z7) {
                                    z7 = true;
                                } else if (!z2) {
                                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getMANY_CLASSES_IN_SUPERTYPE_LIST(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                }
                                if (!z5 && !z && !z2) {
                                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getINTERFACE_WITH_SUPERCLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                    z5 = true;
                                }
                            }
                            if (firRegularClassSymbol.getClassKind() == ClassKind.OBJECT) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (!z8 && !z3 && ((FirClassLikeSymbol) firClassifierSymbol).getResolvedStatus().getModality() == Modality.FINAL && !z) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getFINAL_SUPERTYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                z8 = true;
                            }
                            if (z9 && z3) {
                                checkerContext2 = checkerContext;
                                diagnosticReporter2 = diagnosticReporter;
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getSINGLETON_IN_SUPERTYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                z9 = true;
                            } else {
                                checkerContext2 = checkerContext;
                                diagnosticReporter2 = diagnosticReporter;
                            }
                            firTypeRef2 = firTypeRef3;
                        } else {
                            coneKotlinType4 = coneKotlinType3;
                            firClassifierSymbol = symbol;
                        }
                        checkClassCannotBeExtendedDirectly(diagnosticReporter2, checkerContext2, firClassifierSymbol, firTypeRef2);
                        checkNamedFunctionTypeParameter(checkerContext2, diagnosticReporter2, firTypeRef2);
                        if (!checkProjectionInImmediateArgumentToSupertype(diagnosticReporter2, checkerContext2, coneKotlinType, firTypeRef2)) {
                            DiagnosticReporter diagnosticReporter3 = diagnosticReporter2;
                            CheckerContext checkerContext3 = checkerContext2;
                            checkSupertypeOnTypeAliasWithTypeProjection(diagnosticReporter3, checkerContext3, coneKotlinType, coneKotlinType4, firTypeRef2);
                            checkerContext2 = checkerContext3;
                        }
                    }
                    checkerContext2 = checkerContext;
                    if (z6) {
                        coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext2, coneKotlinType);
                        if (CompilerConeAttributesKt.isExtensionFunctionType(coneKotlinTypeFullyExpandedType)) {
                            diagnosticReporter2 = diagnosticReporter;
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getSUPERTYPE_IS_EXTENSION_OR_CONTEXT_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            z6 = true;
                        } else {
                            diagnosticReporter2 = diagnosticReporter;
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getSUPERTYPE_IS_EXTENSION_OR_CONTEXT_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            z6 = true;
                        }
                    } else {
                        diagnosticReporter2 = diagnosticReporter;
                    }
                    firTypeRef2 = firTypeRef;
                    checkAnnotationOnSuperclass(checkerContext2, diagnosticReporter2, firTypeRef2);
                    coneKotlinType3 = coneKotlinType2;
                    symbol = ToSymbolUtilsKt.toSymbol(checkerContext2, coneKotlinType3);
                    if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext2.getSession()).supportsFeature(LanguageFeature.AllowAnyAsAnActualTypeForExpectInterface)) {
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                    if (symbol instanceof FirRegularClassSymbol) {
                        if (hashSet.add(symbol)) {
                            coneKotlinType4 = coneKotlinType3;
                            firTypeRef3 = firTypeRef2;
                            firClassifierSymbol = symbol;
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef2.getSource(), FirErrors.INSTANCE.getSUPERTYPE_APPEARS_TWICE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        } else {
                            firTypeRef3 = firTypeRef2;
                            coneKotlinType4 = coneKotlinType3;
                            firClassifierSymbol = symbol;
                        }
                        firRegularClassSymbol = (FirRegularClassSymbol) firClassifierSymbol;
                        if (firRegularClassSymbol.getClassKind() != ClassKind.INTERFACE) {
                            if (z7) {
                                z7 = true;
                            } else if (!z2) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getMANY_CLASSES_IN_SUPERTYPE_LIST(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            }
                            if (!z5) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getINTERFACE_WITH_SUPERCLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                z5 = true;
                            }
                        }
                        if (firRegularClassSymbol.getClassKind() == ClassKind.OBJECT) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (!z8) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef3.getSource(), FirErrors.INSTANCE.getFINAL_SUPERTYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            z8 = true;
                        }
                        if (z9) {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                        } else {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                        }
                        firTypeRef2 = firTypeRef3;
                    } else {
                        coneKotlinType4 = coneKotlinType3;
                        firClassifierSymbol = symbol;
                    }
                    checkClassCannotBeExtendedDirectly(diagnosticReporter2, checkerContext2, firClassifierSymbol, firTypeRef2);
                    checkNamedFunctionTypeParameter(checkerContext2, diagnosticReporter2, firTypeRef2);
                    if (!checkProjectionInImmediateArgumentToSupertype(diagnosticReporter2, checkerContext2, coneKotlinType, firTypeRef2)) {
                        DiagnosticReporter diagnosticReporter4 = diagnosticReporter2;
                        CheckerContext checkerContext4 = checkerContext2;
                        checkSupertypeOnTypeAliasWithTypeProjection(diagnosticReporter4, checkerContext4, coneKotlinType, coneKotlinType4, firTypeRef2);
                        checkerContext2 = checkerContext4;
                    }
                }
            }
        }
        checkDelegationNotToInterface(checkerContext, diagnosticReporter, firClass);
        checkDelegationWithoutPrimaryConstructor(checkerContext, diagnosticReporter, firClass);
        if (firClass.getSuperTypeRefs().size() > 1) {
            FirInconsistentTypeParameterHelpersKt.checkInconsistentTypeParameters(checkerContext2, diagnosticReporter, CollectionsKt.listOf(TuplesKt.to(null, firClass.getSymbol())), firClass.getSource(), true);
        }
    }
}

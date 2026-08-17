package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeRefSource;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirClassVarianceChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.types.EnrichedProjectionKind;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ1\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J;\u0010\u0012\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0018JA\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001eJ]\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020 2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\"\u001a\u00020#H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassVarianceChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkCallableDeclaration", "member", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "checkTypeParameters", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;Lorg/jetbrains/kotlin/types/Variance;)V", "checkVarianceConflict", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/types/Variance;Lorg/jetbrains/kotlin/KtSourceElement;)V", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "containingType", "isInAbbreviation", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/types/Variance;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirClassVarianceChecker extends FirDeclarationChecker<FirClass> {
    public static final FirClassVarianceChecker INSTANCE = new FirClassVarianceChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ProjectionKind.values().length];
            try {
                iArr[ProjectionKind.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProjectionKind.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProjectionKind.INVARIANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EnrichedProjectionKind.values().length];
            try {
                iArr2[EnrichedProjectionKind.OUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[EnrichedProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[EnrichedProjectionKind.INV.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[EnrichedProjectionKind.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private FirClassVarianceChecker() {
        super(MppCheckerKind.Common);
    }

    public static Unit b(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            FirCallableSymbol<?> firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
            if (Visibilities.INSTANCE.isPrivate(firCallableSymbol.getResolvedStatus().getVisibility())) {
                return Unit.INSTANCE;
            }
            FirClassVarianceChecker firClassVarianceChecker = INSTANCE;
            firClassVarianceChecker.checkTypeParameters(checkerContext, diagnosticReporter, firCallableSymbol.getOwnTypeParameterSymbols(), Variance.IN_VARIANCE);
            firClassVarianceChecker.checkCallableDeclaration(checkerContext, diagnosticReporter, firCallableSymbol);
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
            if (Visibilities.INSTANCE.isPrivate(firClassLikeSymbol.getResolvedStatus().getVisibility())) {
                return Unit.INSTANCE;
            }
            if (!(firBasedSymbol instanceof FirClassSymbol)) {
                INSTANCE.checkTypeParameters(checkerContext, diagnosticReporter, firClassLikeSymbol.getOwnTypeParameterSymbols(), Variance.IN_VARIANCE);
            }
        }
        return Unit.INSTANCE;
    }

    private final void checkCallableDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol) {
        KtSourceElement source = firCallableSymbol.getSource();
        if (source != null && !(source.getKind() instanceof KtFakeSourceElementKind)) {
            Iterator<FirValueParameterSymbol> it = firCallableSymbol.getContextParameterSymbols().iterator();
            while (it.hasNext()) {
                checkVarianceConflict$default(this, checkerContext, diagnosticReporter, it.next().getResolvedReturnTypeRef(), Variance.IN_VARIANCE, null, 16, null);
            }
            if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
                Iterator<FirValueParameterSymbol> it2 = ((FirNamedFunctionSymbol) firCallableSymbol).getValueParameterSymbols().iterator();
                while (it2.hasNext()) {
                    checkVarianceConflict$default(this, checkerContext, diagnosticReporter, it2.next().getResolvedReturnTypeRef(), Variance.IN_VARIANCE, null, 16, null);
                }
            }
        }
        Variance variance = ((firCallableSymbol instanceof FirPropertySymbol) && ((FirPropertySymbol) firCallableSymbol).isVar()) ? Variance.INVARIANT : Variance.OUT_VARIANCE;
        KtSourceElement source2 = firCallableSymbol.getResolvedReturnTypeRef().getSource();
        checkVarianceConflict(checkerContext, diagnosticReporter, firCallableSymbol.getResolvedReturnTypeRef(), variance, (source2 == null || !(source == null || !(source2.getKind() instanceof KtFakeSourceElementKind) || (source.getKind() instanceof KtFakeSourceElementKind))) ? source : source2);
        FirResolvedTypeRef resolvedReceiverTypeRef = firCallableSymbol.getResolvedReceiverTypeRef();
        if (resolvedReceiverTypeRef != null) {
            checkVarianceConflict$default(this, checkerContext, diagnosticReporter, resolvedReceiverTypeRef, Variance.IN_VARIANCE, null, 16, null);
        }
    }

    private final void checkTypeParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<FirTypeParameterSymbol> list, Variance variance) {
        Iterator<FirTypeParameterSymbol> it = list.iterator();
        while (it.hasNext()) {
            Iterator<FirResolvedTypeRef> it2 = it.next().getResolvedBounds().iterator();
            while (it2.hasNext()) {
                checkVarianceConflict$default(this, checkerContext, diagnosticReporter, it2.next(), variance, null, 16, null);
            }
        }
    }

    private final void checkVarianceConflict(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, Variance variance, FirTypeRef firTypeRef, ConeKotlinType coneKotlinType2, KtSourceElement ktSourceElement, boolean z) {
        Variance variance2;
        Variance variance3;
        Variance variance4;
        FirTypeRef typeRef;
        KtSourceElement source;
        Variance varianceOpposite;
        if (coneKotlinType instanceof ConeTypeParameterType) {
            ConeSimpleKotlinType coneSimpleKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext, (ConeSimpleKotlinType) coneKotlinType);
            FirTypeParameterSymbol typeParameterSymbol = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol();
            KtSourceElement source2 = ktSourceElement == null ? firTypeRef != null ? firTypeRef.getSource() : null : ktSourceElement;
            if (source2 == null || typeParameterSymbol.getVariance().allowsPosition(variance) || coneSimpleKotlinTypeFullyExpandedType.getAttributes().contains((ConeAttribute<?>) CompilerConeAttributes.UnsafeVariance.INSTANCE)) {
                return;
            }
            FirErrors firErrors = FirErrors.INSTANCE;
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, (KtDiagnosticFactory4<FirTypeParameterSymbol, Variance, Variance, ConeKotlinType>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) (z ? firErrors.getTYPE_VARIANCE_CONFLICT_IN_EXPANDED_TYPE() : firErrors.getTYPE_VARIANCE_CONFLICT_ERROR())), typeParameterSymbol, typeParameterSymbol.getVariance(), variance, coneKotlinType2, (128 & 128) != 0 ? null : null);
            return;
        }
        if (!(coneKotlinType instanceof ConeClassLikeType)) {
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ReportTypeVarianceConflictsInDnnAndFlexible)) {
                    checkVarianceConflict(checkerContext, diagnosticReporter, ((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), variance, firTypeRef, coneKotlinType2, ktSourceElement, z);
                    return;
                }
                return;
            } else if (coneKotlinType instanceof ConeFlexibleType) {
                if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ReportTypeVarianceConflictsInDnnAndFlexible)) {
                    checkVarianceConflict(checkerContext, diagnosticReporter, ((ConeFlexibleType) coneKotlinType).getLowerBound(), variance, firTypeRef, coneKotlinType2, ktSourceElement, z);
                    return;
                }
                return;
            } else {
                if (!(coneKotlinType instanceof ConeIntersectionType)) {
                    b88.a("Unexpected type ", coneKotlinType.getClass(), " in checkVarianceConflict");
                    return;
                }
                Iterator<T> it = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes().iterator();
                while (it.hasNext()) {
                    INSTANCE.checkVarianceConflict(checkerContext, diagnosticReporter, (ConeKotlinType) it.next(), variance, firTypeRef, coneKotlinType2, ktSourceElement, z);
                }
                return;
            }
        }
        ConeClassLikeType coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext, (ConeClassLikeType) coneKotlinType);
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeTypeFullyExpandedType.getLookupTag());
        if (symbol instanceof FirClassSymbol) {
            List<FirTypeRefSource> listExtractArgumentsTypeRefAndSource = FirHelpersKt.extractArgumentsTypeRefAndSource(firTypeRef);
            ConeTypeProjection[] typeArguments = coneClassLikeTypeFullyExpandedType.getTypeArguments();
            int length = typeArguments.length;
            for (int i = 0; i < length; i++) {
                ConeTypeProjection coneTypeProjection = typeArguments[i];
                FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) CollectionsKt.getOrNull(((FirClassSymbol) symbol).getTypeParameterSymbols(), i);
                if (firTypeParameterSymbol != null && (variance2 = firTypeParameterSymbol.getVariance()) != null) {
                    int i2 = WhenMappings.$EnumSwitchMapping$0[coneTypeProjection.getKind().ordinal()];
                    if (i2 == 1) {
                        variance3 = Variance.IN_VARIANCE;
                    } else if (i2 == 2) {
                        variance3 = Variance.OUT_VARIANCE;
                    } else if (i2 != 3) {
                        continue;
                    } else {
                        variance3 = Variance.INVARIANT;
                    }
                    ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                    if (type == null) {
                        continue;
                    } else {
                        int i3 = WhenMappings.$EnumSwitchMapping$1[EnrichedProjectionKind.Companion.getEffectiveProjectionKind(variance2, variance3).ordinal()];
                        if (i3 != 1) {
                            if (i3 == 2) {
                                varianceOpposite = variance.opposite();
                            } else if (i3 == 3) {
                                varianceOpposite = Variance.INVARIANT;
                            } else {
                                if (i3 != 4) {
                                    bu8.a();
                                    return;
                                }
                                variance4 = null;
                            }
                            variance4 = varianceOpposite;
                        } else {
                            variance4 = variance;
                        }
                        if (variance4 != null) {
                            FirTypeRefSource firTypeRefSource = listExtractArgumentsTypeRefAndSource != null ? (FirTypeRefSource) CollectionsKt.getOrNull(listExtractArgumentsTypeRefAndSource, i) : null;
                            checkVarianceConflict(checkerContext, diagnosticReporter, type, variance4, firTypeRefSource != null ? firTypeRefSource.getTypeRef() : null, coneKotlinType2, (firTypeRefSource == null || (typeRef = firTypeRefSource.getTypeRef()) == null || (source = typeRef.getSource()) == null) ? ktSourceElement : source, AbbreviatedTypeAttributeKt.isTypealiasExpansion(coneKotlinType));
                        }
                    }
                }
            }
        }
    }

    public static /* synthetic */ void checkVarianceConflict$default(FirClassVarianceChecker firClassVarianceChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, Variance variance, FirTypeRef firTypeRef, ConeKotlinType coneKotlinType2, KtSourceElement ktSourceElement, boolean z, int i, Object obj) {
        firClassVarianceChecker.checkVarianceConflict(checkerContext, diagnosticReporter, coneKotlinType, variance, firTypeRef, coneKotlinType2, (i & 64) != 0 ? null : ktSourceElement, (i & 128) != 0 ? false : z);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        List<FirTypeParameterRef> typeParameters = firClass.getTypeParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : typeParameters) {
            if (obj instanceof FirTypeParameter) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((FirTypeParameter) it.next()).getSymbol());
        }
        checkTypeParameters(checkerContext, diagnosticReporter, arrayList2, Variance.OUT_VARIANCE);
        Iterator<FirTypeRef> it2 = firClass.getSuperTypeRefs().iterator();
        while (it2.hasNext()) {
            checkVarianceConflict$default(this, checkerContext, diagnosticReporter, it2.next(), Variance.OUT_VARIANCE, null, 16, null);
        }
        org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclarations$default(firClass, checkerContext.getSession(), (FirResolvePhase) null, new Function1() { // from class: pz4
            public final Object invoke(Object obj2) {
                return FirClassVarianceChecker.b(checkerContext, diagnosticReporter, (FirBasedSymbol) obj2);
            }
        }, 2, (Object) null);
    }

    public static /* synthetic */ void checkVarianceConflict$default(FirClassVarianceChecker firClassVarianceChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, Variance variance, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 16) != 0) {
            ktSourceElement = null;
        }
        firClassVarianceChecker.checkVarianceConflict(checkerContext, diagnosticReporter, firTypeRef, variance, ktSourceElement);
    }

    private final void checkVarianceConflict(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, Variance variance, KtSourceElement ktSourceElement) {
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firTypeRef));
        checkVarianceConflict$default(this, checkerContext, diagnosticReporter, coneKotlinTypeFullyExpandedType, variance, firTypeRef, coneKotlinTypeFullyExpandedType, ktSourceElement == null ? firTypeRef.getSource() : ktSourceElement, false, 128, null);
    }
}

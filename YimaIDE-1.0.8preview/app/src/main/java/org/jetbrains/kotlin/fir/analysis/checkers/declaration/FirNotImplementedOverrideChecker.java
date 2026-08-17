package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMemberScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.ImplementationStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ+\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011H\u0002\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0001(\u0000J\u0010\u0010\u0013\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u0014H\u0002ò\u0001\u0004\n\u00020\u0012¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNotImplementedOverrideChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "isInitializerOfEnumEntry", Argument.Delimiters.none, "containingDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "isFromInterfaceOrEnum", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNotImplementedOverrideChecker extends FirDeclarationChecker<FirClass> {
    public static final FirNotImplementedOverrideChecker INSTANCE = new FirNotImplementedOverrideChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImplementationStatus.values().length];
            try {
                iArr[ImplementationStatus.AMBIGUOUSLY_INHERITED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ImplementationStatus.NOT_IMPLEMENTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ImplementationStatus.VAR_IMPLEMENTED_BY_VAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirNotImplementedOverrideChecker() {
        super(MppCheckerKind.Platform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void check$collectSymbol(FirTypeScope firTypeScope, List<FirCallableSymbol<?>> list, List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> list2, List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> list3, List<FirCallableSymbol<?>> list4, CheckerContext checkerContext, FirClassSymbol<? extends FirClass> firClassSymbol, List<FirCallableSymbol<?>> list5, List<FirCallableSymbol<?>> list6, List<FirCallableSymbol<?>> list7, List<FirIntersectionCallableSymbol> list8, ClassKind classKind, FirCallableSymbol<?> firCallableSymbol) {
        Object obj;
        Object next;
        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableSymbol);
        if (delegatedWrapperData == null || (firCallableSymbol instanceof FirIntersectionCallableSymbol)) {
            int i = WhenMappings.$EnumSwitchMapping$0[FirHelpersKt.getImplementationStatus(checkerContext, firCallableSymbol, firClassSymbol).ordinal()];
            if (i == 1) {
                list5.add(firCallableSymbol);
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                firCallableSymbol.getClass();
                list8.add((FirIntersectionCallableSymbol) firCallableSymbol);
                return;
            }
            if (FirVisibilityCheckerKt.isVisibleInClass(firCallableSymbol, firClassSymbol)) {
                list6.add(firCallableSymbol);
                return;
            } else {
                list7.add(firCallableSymbol);
                return;
            }
        }
        List<MemberWithBaseScope<FirCallableSymbol<?>>> directOverriddenMembersWithBaseScope = FirTypeScopeKt.getDirectOverriddenMembersWithBaseScope(firTypeScope, firCallableSymbol);
        Collection arrayList = new ArrayList();
        for (Object obj2 : directOverriddenMembersWithBaseScope) {
            if (!Intrinsics.areEqual(((MemberWithBaseScope) obj2).getMember(), firCallableSymbol)) {
                arrayList.add(obj2);
            }
        }
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            arrayList = FirOverrideUtilsKt.filterOutOverriddenFunctions(arrayList);
        } else if (firCallableSymbol instanceof FirPropertySymbol) {
            arrayList = FirOverrideUtilsKt.filterOutOverriddenProperties(arrayList);
        }
        Collection collection = arrayList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList2.add(((MemberWithBaseScope) it.next()).getMember());
        }
        FirCallableDeclaration wrapped = delegatedWrapperData.getWrapped();
        while (true) {
            obj = null;
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(wrapped) || (wrapped.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(wrapped) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(wrapped) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(wrapped) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            } else {
                wrapped = originalForSubstitutionOverrideAttr;
            }
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = wrapped.getSymbol();
        if (Intrinsics.areEqual(FirDelegatedMemberScopeKt.getMultipleDelegatesWithTheSameSignature(firCallableSymbol), Boolean.TRUE)) {
            list.add(firCallableSymbol);
        }
        Iterator it2 = arrayList2.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (((FirCallableSymbol) next).getResolvedStatus().getModality() != Modality.FINAL);
        FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) next;
        for (Object obj3 : arrayList2) {
            FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) obj3;
            if (firCallableSymbol3.getResolvedStatus().getModality() == Modality.OPEN) {
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol3.getFir();
                while (true) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    }
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        break;
                    } else {
                        firCallableDeclaration = originalForSubstitutionOverrideAttr2;
                    }
                }
                FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration.getSymbol();
                if (symbol2 != null) {
                    if (!Intrinsics.areEqual(symbol, symbol2)) {
                        obj = obj3;
                        break;
                    }
                } else {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                    return;
                }
            }
        }
        FirCallableSymbol firCallableSymbol4 = (FirCallableSymbol) obj;
        if (firCallableSymbol2 != null) {
            list2.add(TuplesKt.to(firCallableSymbol, firCallableSymbol2));
        } else if (firCallableSymbol4 != null) {
            list3.add(TuplesKt.to(firCallableSymbol, firCallableSymbol4));
        }
        if (check$isIncorrectlyDelegated(delegatedWrapperData, classKind, checkerContext, firClassSymbol)) {
            list4.add(firCallableSymbol);
        }
    }

    private static final boolean check$isIncorrectlyDelegated(DelegatedWrapperData<FirCallableDeclaration> delegatedWrapperData, ClassKind classKind, CheckerContext checkerContext, FirClassSymbol<? extends FirClass> firClassSymbol) {
        ConeClassLikeLookupTag lookupTag;
        ConeKotlinType resolvedType;
        if (classKind != ClassKind.OBJECT) {
            return false;
        }
        FirExpression resolvedInitializer = delegatedWrapperData.getDelegateFieldSymbol().getResolvedInitializer();
        FirClassLikeSymbol<?> symbol = null;
        ConeKotlinType coneKotlinTypeFullyExpandedType = (resolvedInitializer == null || (resolvedType = FirTypeUtilsKt.getResolvedType(resolvedInitializer)) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType(checkerContext, resolvedType);
        ConeClassLikeType coneClassLikeType = coneKotlinTypeFullyExpandedType instanceof ConeClassLikeType ? (ConeClassLikeType) coneKotlinTypeFullyExpandedType : null;
        if (coneClassLikeType != null && (lookupTag = coneClassLikeType.getLookupTag()) != null) {
            symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, lookupTag);
        }
        return Intrinsics.areEqual(symbol, firClassSymbol);
    }

    private final boolean isFromInterfaceOrEnum(FirCallableSymbol<?> firCallableSymbol) {
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
        FirRegularClassSymbol firRegularClassSymbol = containingClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol : null;
        return firRegularClassSymbol != null && (firRegularClassSymbol.getClassKind() == ClassKind.INTERFACE || firRegularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS);
    }

    private final boolean isInitializerOfEnumEntry(FirClass firClass, FirBasedSymbol<?> firBasedSymbol) {
        return (firBasedSymbol instanceof FirEnumEntrySymbol) && Intrinsics.areEqual(((FirEnumEntrySymbol) firBasedSymbol).getInitializerObjectSymbol(), firClass.getSymbol());
    }

    /* JADX WARN: Code duplicated, block: B:231:0x052a  */
    /* JADX WARN: Code duplicated, block: B:236:0x053a  */
    /* JADX WARN: Code duplicated, block: B:239:0x0544  */
    /* JADX WARN: Code duplicated, block: B:241:0x0550  */
    /* JADX WARN: Code duplicated, block: B:244:0x055b  */
    /* JADX WARN: Code duplicated, block: B:287:0x0560 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:297:? A[LOOP:18: B:237:0x053e->B:297:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        KtSourceElement source;
        FirClassSymbol<FirClass> firClassSymbol;
        FirClassSymbol<FirClass> firClassSymbol2;
        FirRegularClassSymbol regularClassSymbol;
        List list;
        Iterator it;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        ClassKind classKind;
        FirRegularClassSymbol regularClassSymbol2;
        FirCallableSymbol firCallableSymbol;
        FirCallableSymbol firCallableSymbol2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if ((!firClass.getStatus().isExpect() || ((Boolean) checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getMetadataCompilation())).booleanValue()) && (source = firClass.getSource()) != null) {
            KtSourceElementKind kind = source.getKind();
            if (!(kind instanceof KtFakeSourceElementKind) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.EnumInitializer.INSTANCE)) {
                Modality modality = FirHelpersKt.modality(firClass);
                ClassKind classKind2 = firClass.getClassKind();
                if (classKind2 == ClassKind.ANNOTATION_CLASS || classKind2 == ClassKind.ENUM_CLASS) {
                    return;
                }
                boolean z = modality == Modality.ABSTRACT || modality == Modality.SEALED || (classKind2 == ClassKind.INTERFACE && modality == Modality.OPEN);
                FirClassSymbol<FirClass> symbol = firClass.getSymbol();
                FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass);
                ArrayList arrayList = new ArrayList();
                ArrayList<DeclarationSymbolMarker> arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList<Pair> arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                ArrayList<FirIntersectionCallableSymbol> arrayList7 = new ArrayList();
                ArrayList arrayList8 = new ArrayList();
                checkerContext.getSession();
                for (Name name : firTypeScopeUnsubstitutedScope.getCallableNames()) {
                    ArrayList arrayList9 = arrayList5;
                    firTypeScopeUnsubstitutedScope.processFunctionsByName(name, new AnonymousClass1(firTypeScopeUnsubstitutedScope, arrayList3, arrayList4, arrayList5, arrayList8, checkerContext, symbol, arrayList2, arrayList, arrayList6, arrayList7, classKind2));
                    firTypeScopeUnsubstitutedScope.processPropertiesByName(name, new AnonymousClass2(firTypeScopeUnsubstitutedScope, arrayList3, arrayList4, arrayList9, arrayList8, checkerContext, symbol, arrayList2, arrayList, arrayList6, arrayList7, classKind2));
                    source = source;
                    arrayList3 = arrayList3;
                    arrayList5 = arrayList9;
                    arrayList6 = arrayList6;
                    arrayList7 = arrayList7;
                }
                ArrayList arrayList10 = arrayList3;
                ArrayList<Pair> arrayList11 = arrayList5;
                FirClassSymbol<FirClass> firClassSymbol3 = symbol;
                ArrayList arrayList12 = arrayList6;
                KtSourceElement ktSourceElement = source;
                for (FirIntersectionCallableSymbol firIntersectionCallableSymbol : arrayList7) {
                    Iterator<T> it2 = firIntersectionCallableSymbol.getIntersections().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            hb9.a("Collection contains no element matching the predicate.");
                            return;
                        }
                        firCallableSymbol = (FirCallableSymbol) it2.next();
                        if (!(firCallableSymbol instanceof FirPropertySymbol) || !((FirPropertySymbol) firCallableSymbol).isVal() || firCallableSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
                            ktSourceElement = ktSourceElement;
                            firClassSymbol3 = firClassSymbol3;
                            arrayList8 = arrayList8;
                        }
                    }
                    Iterator<T> it3 = firIntersectionCallableSymbol.getIntersections().iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            hb9.a("Collection contains no element matching the predicate.");
                            return;
                        }
                        firCallableSymbol2 = (FirCallableSymbol) it3.next();
                        if (!(firCallableSymbol2 instanceof FirPropertySymbol) || !((FirPropertySymbol) firCallableSymbol2).isVar() || firCallableSymbol2.getResolvedStatus().getModality() != Modality.ABSTRACT) {
                            ktSourceElement = ktSourceElement;
                            firClassSymbol3 = firClassSymbol3;
                            arrayList8 = arrayList8;
                        }
                    }
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation3<FirClassSymbol<FirClass>, FirCallableSymbol, FirCallableSymbol>) ((KtDiagnosticFactoryForDeprecation3<Object, Object, Object>) FirErrors.INSTANCE.getVAR_IMPLEMENTED_BY_INHERITED_VAL()), firClassSymbol3, firCallableSymbol2, firCallableSymbol, (64 & 64) != 0 ? null : null);
                    arrayList8 = arrayList8;
                }
                KtSourceElement ktSourceElement2 = ktSourceElement;
                FirClassSymbol<FirClass> firClassSymbol4 = firClassSymbol3;
                ArrayList arrayList13 = arrayList8;
                if (z) {
                    firClassSymbol = firClassSymbol4;
                } else {
                    ArrayList arrayList14 = new ArrayList();
                    ArrayList arrayList15 = new ArrayList();
                    for (Object obj : arrayList) {
                        FirNotImplementedOverrideChecker firNotImplementedOverrideChecker = INSTANCE;
                        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) obj).getFir();
                        while (true) {
                            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                            if (originalForSubstitutionOverrideAttr == null) {
                                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                            }
                            if (originalForSubstitutionOverrideAttr == null) {
                                break;
                            } else {
                                firCallableDeclaration = originalForSubstitutionOverrideAttr;
                            }
                        }
                        FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration.getSymbol();
                        if (symbol2 == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                            return;
                        } else if (firNotImplementedOverrideChecker.isFromInterfaceOrEnum(symbol2)) {
                            arrayList14.add(obj);
                        } else {
                            arrayList15.add(obj);
                        }
                    }
                    Pair pair = new Pair(arrayList14, arrayList15);
                    List list2 = (List) pair.component1();
                    List list3 = (List) pair.component2();
                    FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
                    ArrayList arrayList16 = new ArrayList();
                    ArrayList arrayList17 = new ArrayList();
                    for (Object obj2 : list2) {
                        if (INSTANCE.isInitializerOfEnumEntry(firClass, firBasedSymbol)) {
                            arrayList16.add(obj2);
                        } else {
                            arrayList17.add(obj2);
                        }
                    }
                    Pair pair2 = new Pair(arrayList16, arrayList17);
                    List list4 = (List) pair2.component1();
                    List list5 = (List) pair2.component2();
                    if ((firBasedSymbol instanceof FirEnumEntrySymbol) && !list4.isEmpty()) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getABSTRACT_MEMBER_NOT_IMPLEMENTED_BY_ENUM_ENTRY(), (Object) firBasedSymbol, (Object) list4, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    }
                    if (list5.isEmpty()) {
                        firClassSymbol = firClassSymbol4;
                    } else {
                        KtDiagnosticFactory2<FirClassSymbol<?>, List<FirCallableSymbol<?>>> abstract_member_not_implemented = FirErrors.INSTANCE.getABSTRACT_MEMBER_NOT_IMPLEMENTED();
                        List list6 = list5;
                        ArrayList arrayList18 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
                        Iterator it4 = list6.iterator();
                        while (it4.hasNext()) {
                            FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) ((FirCallableSymbol) it4.next()).getFir();
                            while (true) {
                                FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                                if (originalForSubstitutionOverrideAttr2 == null) {
                                    originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
                                }
                                if (originalForSubstitutionOverrideAttr2 == null) {
                                    break;
                                } else {
                                    firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
                                }
                            }
                            FirCallableSymbol<FirCallableDeclaration> symbol3 = firCallableDeclaration2.getSymbol();
                            if (symbol3 == null) {
                                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                                return;
                            }
                            arrayList18.add(symbol3);
                        }
                        firClassSymbol = firClassSymbol4;
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) abstract_member_not_implemented, (Object) firClassSymbol, (Object) arrayList18, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    }
                    if (!list3.isEmpty()) {
                        KtDiagnosticFactory2<FirClassSymbol<?>, List<FirCallableSymbol<?>>> abstract_class_member_not_implemented = FirErrors.INSTANCE.getABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED();
                        List list7 = list3;
                        ArrayList arrayList19 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
                        Iterator it5 = list7.iterator();
                        while (it5.hasNext()) {
                            FirCallableDeclaration firCallableDeclaration3 = (FirCallableDeclaration) ((FirCallableSymbol) it5.next()).getFir();
                            while (true) {
                                FirCallableDeclaration originalForSubstitutionOverrideAttr3 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration3) || (firCallableDeclaration3.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration3) : null;
                                if (originalForSubstitutionOverrideAttr3 == null) {
                                    originalForSubstitutionOverrideAttr3 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration3) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration3) : null;
                                }
                                if (originalForSubstitutionOverrideAttr3 == null) {
                                    break;
                                } else {
                                    firCallableDeclaration3 = originalForSubstitutionOverrideAttr3;
                                }
                            }
                            FirCallableSymbol<FirCallableDeclaration> symbol4 = firCallableDeclaration3.getSymbol();
                            if (symbol4 == null) {
                                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                                return;
                            }
                            arrayList19.add(symbol4);
                        }
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) abstract_class_member_not_implemented, (Object) firClassSymbol, (Object) arrayList19, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    }
                    if (!arrayList13.isEmpty()) {
                        KtDiagnosticFactoryForDeprecation2<FirClassSymbol<?>, List<FirCallableSymbol<?>>> abstract_member_incorrectly_delegated = FirErrors.INSTANCE.getABSTRACT_MEMBER_INCORRECTLY_DELEGATED();
                        ArrayList arrayList20 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList13, (int) r1));
                        Iterator it6 = arrayList13.iterator();
                        while (it6.hasNext()) {
                            FirCallableDeclaration firCallableDeclaration4 = (FirCallableDeclaration) ((FirCallableSymbol) it6.next()).getFir();
                            while (true) {
                                FirCallableDeclaration originalForSubstitutionOverrideAttr4 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration4) || (firCallableDeclaration4.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration4) : null;
                                if (originalForSubstitutionOverrideAttr4 == null) {
                                    originalForSubstitutionOverrideAttr4 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration4) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration4) : null;
                                }
                                if (originalForSubstitutionOverrideAttr4 == null) {
                                    break;
                                } else {
                                    firCallableDeclaration4 = originalForSubstitutionOverrideAttr4;
                                }
                            }
                            FirCallableSymbol<FirCallableDeclaration> symbol5 = firCallableDeclaration4.getSymbol();
                            if (symbol5 == null) {
                                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                                return;
                            }
                            arrayList20.add(symbol5);
                        }
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactoryForDeprecation2) abstract_member_incorrectly_delegated, (Object) firClassSymbol, (Object) arrayList20, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    }
                }
                if (!z && !arrayList12.isEmpty()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getINVISIBLE_ABSTRACT_MEMBER_FROM_SUPER_ERROR(), (Object) firClassSymbol, (Object) arrayList12, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                Iterator it7 = arrayList10.iterator();
                while (it7.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getMANY_IMPL_MEMBER_NOT_IMPLEMENTED(), (Object) firClassSymbol, it7.next(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                FirClassSymbol<FirClass> firClassSymbol5 = firClassSymbol;
                for (Pair pair3 : arrayList4) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getOVERRIDING_FINAL_MEMBER_BY_DELEGATION(), pair3.component1(), pair3.component2(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                for (Pair pair4 : arrayList11) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE(), pair4.component1(), pair4.component2(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                if (arrayList10.isEmpty()) {
                    for (DeclarationSymbolMarker declarationSymbolMarker : arrayList2) {
                        declarationSymbolMarker.getClass();
                        Collection<FirCallableSymbol<?>> intersections = ((FirIntersectionCallableSymbol) declarationSymbolMarker).getIntersections();
                        ArrayList arrayList21 = new ArrayList();
                        ArrayList arrayList22 = new ArrayList();
                        for (Object obj3 : intersections) {
                            if (((FirCallableSymbol) obj3).getResolvedStatus().getModality() == Modality.ABSTRACT) {
                                arrayList21.add(obj3);
                            } else {
                                arrayList22.add(obj3);
                            }
                        }
                        Pair pair5 = new Pair(arrayList21, arrayList22);
                        List list8 = (List) pair5.component1();
                        List list9 = (List) pair5.component2();
                        if ((list9 instanceof Collection) && list9.isEmpty()) {
                            firClassSymbol2 = firClassSymbol5;
                            if (z) {
                                list = list8;
                                if (list instanceof Collection) {
                                    it = list.iterator();
                                    while (it.hasNext()) {
                                        coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) it.next());
                                        if (coneClassLikeLookupTagContainingClassLookupTag != null) {
                                            classKind = null;
                                        } else {
                                            classKind = null;
                                        }
                                        if (classKind == ClassKind.CLASS) {
                                            return;
                                        }
                                    }
                                } else {
                                    it = list.iterator();
                                    while (it.hasNext()) {
                                        coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) it.next());
                                        if (coneClassLikeLookupTagContainingClassLookupTag != null) {
                                            classKind = null;
                                        } else {
                                            classKind = null;
                                        }
                                        if (classKind == ClassKind.CLASS) {
                                            return;
                                        }
                                    }
                                }
                            }
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getMANY_INTERFACES_MEMBER_NOT_IMPLEMENTED(), (Object) firClassSymbol2, (Object) declarationSymbolMarker, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        } else {
                            Iterator it8 = list9.iterator();
                            while (true) {
                                if (it8.hasNext()) {
                                    ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) it8.next());
                                    if (((coneClassLikeLookupTagContainingClassLookupTag2 == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag2)) == null) ? null : regularClassSymbol.getClassKind()) == ClassKind.CLASS) {
                                        firClassSymbol2 = firClassSymbol5;
                                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getMANY_IMPL_MEMBER_NOT_IMPLEMENTED(), (Object) firClassSymbol2, (Object) declarationSymbolMarker, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                                    }
                                } else {
                                    firClassSymbol2 = firClassSymbol5;
                                    if (z) {
                                        list = list8;
                                        if ((list instanceof Collection) || !list.isEmpty()) {
                                            it = list.iterator();
                                            while (it.hasNext()) {
                                                coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) it.next());
                                                if (coneClassLikeLookupTagContainingClassLookupTag != null || (regularClassSymbol2 = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) == null) {
                                                    classKind = null;
                                                } else {
                                                    classKind = regularClassSymbol2.getClassKind();
                                                }
                                                if (classKind == ClassKind.CLASS) {
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) FirErrors.INSTANCE.getMANY_INTERFACES_MEMBER_NOT_IMPLEMENTED(), (Object) firClassSymbol2, (Object) declarationSymbolMarker, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                                }
                            }
                        }
                        firClassSymbol5 = firClassSymbol2;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNotImplementedOverrideChecker$check$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<FirCallableSymbol<?>, Unit> {
        final /* synthetic */ ClassKind $classKind;
        final /* synthetic */ FirTypeScope $classScope;
        final /* synthetic */ FirClassSymbol<FirClass> $classSymbol;
        final /* synthetic */ CheckerContext $context;
        final /* synthetic */ List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> $delegationOverrideOfFinal;
        final /* synthetic */ List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> $delegationOverrideOfOpen;
        final /* synthetic */ List<FirCallableSymbol<?>> $incorrectlyDelegatedSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $invisibleSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $manyImplementationsDelegationSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $notImplementedIntersectionSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $notImplementedSymbols;
        final /* synthetic */ List<FirIntersectionCallableSymbol> $varsImplementedByInheritedVal;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(FirTypeScope firTypeScope, List<FirCallableSymbol<?>> list, List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> list2, List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> list3, List<FirCallableSymbol<?>> list4, CheckerContext checkerContext, FirClassSymbol<? extends FirClass> firClassSymbol, List<FirCallableSymbol<?>> list5, List<FirCallableSymbol<?>> list6, List<FirCallableSymbol<?>> list7, List<FirIntersectionCallableSymbol> list8, ClassKind classKind) {
            super(1, Intrinsics.Kotlin.class, "collectSymbol", "check$collectSymbol(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/descriptors/ClassKind;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", 0);
            this.$classScope = firTypeScope;
            this.$manyImplementationsDelegationSymbols = list;
            this.$delegationOverrideOfFinal = list2;
            this.$delegationOverrideOfOpen = list3;
            this.$incorrectlyDelegatedSymbols = list4;
            this.$context = checkerContext;
            this.$classSymbol = firClassSymbol;
            this.$notImplementedIntersectionSymbols = list5;
            this.$notImplementedSymbols = list6;
            this.$invisibleSymbols = list7;
            this.$varsImplementedByInheritedVal = list8;
            this.$classKind = classKind;
        }

        public final void invoke(FirCallableSymbol<?> firCallableSymbol) {
            firCallableSymbol.getClass();
            FirNotImplementedOverrideChecker.check$collectSymbol(this.$classScope, this.$manyImplementationsDelegationSymbols, this.$delegationOverrideOfFinal, this.$delegationOverrideOfOpen, this.$incorrectlyDelegatedSymbols, this.$context, this.$classSymbol, this.$notImplementedIntersectionSymbols, this.$notImplementedSymbols, this.$invisibleSymbols, this.$varsImplementedByInheritedVal, this.$classKind, firCallableSymbol);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((FirCallableSymbol<?>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNotImplementedOverrideChecker$check$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<FirCallableSymbol<?>, Unit> {
        final /* synthetic */ ClassKind $classKind;
        final /* synthetic */ FirTypeScope $classScope;
        final /* synthetic */ FirClassSymbol<FirClass> $classSymbol;
        final /* synthetic */ CheckerContext $context;
        final /* synthetic */ List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> $delegationOverrideOfFinal;
        final /* synthetic */ List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> $delegationOverrideOfOpen;
        final /* synthetic */ List<FirCallableSymbol<?>> $incorrectlyDelegatedSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $invisibleSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $manyImplementationsDelegationSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $notImplementedIntersectionSymbols;
        final /* synthetic */ List<FirCallableSymbol<?>> $notImplementedSymbols;
        final /* synthetic */ List<FirIntersectionCallableSymbol> $varsImplementedByInheritedVal;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(FirTypeScope firTypeScope, List<FirCallableSymbol<?>> list, List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> list2, List<Pair<FirCallableSymbol<?>, FirCallableSymbol<?>>> list3, List<FirCallableSymbol<?>> list4, CheckerContext checkerContext, FirClassSymbol<? extends FirClass> firClassSymbol, List<FirCallableSymbol<?>> list5, List<FirCallableSymbol<?>> list6, List<FirCallableSymbol<?>> list7, List<FirIntersectionCallableSymbol> list8, ClassKind classKind) {
            super(1, Intrinsics.Kotlin.class, "collectSymbol", "check$collectSymbol(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/descriptors/ClassKind;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", 0);
            this.$classScope = firTypeScope;
            this.$manyImplementationsDelegationSymbols = list;
            this.$delegationOverrideOfFinal = list2;
            this.$delegationOverrideOfOpen = list3;
            this.$incorrectlyDelegatedSymbols = list4;
            this.$context = checkerContext;
            this.$classSymbol = firClassSymbol;
            this.$notImplementedIntersectionSymbols = list5;
            this.$notImplementedSymbols = list6;
            this.$invisibleSymbols = list7;
            this.$varsImplementedByInheritedVal = list8;
            this.$classKind = classKind;
        }

        public final void invoke(FirCallableSymbol<?> firCallableSymbol) {
            firCallableSymbol.getClass();
            FirNotImplementedOverrideChecker.check$collectSymbol(this.$classScope, this.$manyImplementationsDelegationSymbols, this.$delegationOverrideOfFinal, this.$delegationOverrideOfOpen, this.$incorrectlyDelegatedSymbols, this.$context, this.$classSymbol, this.$notImplementedIntersectionSymbols, this.$notImplementedSymbols, this.$invisibleSymbols, this.$varsImplementedByInheritedVal, this.$classKind, firCallableSymbol);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((FirCallableSymbol<?>) obj);
            return Unit.INSTANCE;
        }
    }
}

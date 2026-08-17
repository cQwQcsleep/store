package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirConflictsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirDeclarationCollector;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u0011H\u0002Jq\u0010\u0012\u001a\u00020\u00072 \u0010\u0013\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u00160\u00142 \u0010\u0017\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u00160\u00142\u0006\u0010\u0018\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0019J \u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u00152\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002J3\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"2\u0010\u0010#\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150$H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010%R\u001c\u0010\u001e\u001a\u00020\u001b*\u0006\u0012\u0002\b\u00030\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConflictsDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "getDestructuredParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "reportConflicts", "declarationConflictingSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/utils/SmartSet;", "declarationShadowedViaContextParameters", "container", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/Map;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "isExpectAndNonExpect", Argument.Delimiters.none, "first", "second", "isPrimaryConstructor", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "checkFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "inspector", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConflictsDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirConflictsDeclarationChecker INSTANCE = new FirConflictsDeclarationChecker();

    private FirConflictsDeclarationChecker() {
        super(MppCheckerKind.Platform);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void checkFile(CheckerContext checkerContext, FirFile firFile, FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector) throws KotlinIllegalArgumentExceptionWithAttachments {
        ScopeSession scopeSession = checkerContext.getSessionHolder().getScopeSession();
        Pair pair = TuplesKt.to(UtilsKt.getPackageFqName(firFile), checkerContext.getSession());
        ScopeSessionKey<?, ?> package_member = FirPackageMemberScopeKt.getPACKAGE_MEMBER();
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(pair);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(pair, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object obj = map2.get(package_member);
        if (obj == null) {
            FirPackageMemberScope firPackageMemberScope = new FirPackageMemberScope(UtilsKt.getPackageFqName(firFile), checkerContext.getSessionHolder().getSession(), null, null, 12, null);
            map2.put(package_member, firPackageMemberScope);
            obj = firPackageMemberScope;
        }
        FirConflictsHelpersKt.collectTopLevel(firDeclarationCollector, firFile, (FirPackageMemberScope) obj);
    }

    private final List<FirVariable> getDestructuredParameters(FirCallableDeclaration declaration) {
        FirPropertyAccessor setter;
        List<FirValueParameter> valueParameters;
        List<FirStatement> statements;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        for (Object obj : declaration.getContextParameters()) {
            if (((FirValueParameter) obj).getValueParameterKind() == FirValueParameterKind.ContextParameter) {
                list.add(obj);
            }
        }
        if (declaration instanceof FirFunction) {
            FirFunction firFunction = (FirFunction) declaration;
            listCreateListBuilder.addAll(firFunction.getValueParameters());
            List<FirValueParameter> valueParameters2 = firFunction.getValueParameters();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : valueParameters2) {
                if (Intrinsics.areEqual(((FirValueParameter) obj2).getName(), SpecialNames.DESTRUCT)) {
                    arrayList.add(obj2);
                }
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((FirValueParameter) it.next()).getSymbol());
            }
            FirBlock body = firFunction.getBody();
            if (body != null && (statements = body.getStatements()) != null) {
                List list2 = listCreateListBuilder;
                for (FirStatement firStatement : statements) {
                    FirVariable firVariable = firStatement instanceof FirVariable ? (FirVariable) firStatement : null;
                    if (firVariable == null || !CollectionsKt.contains(linkedHashSet, FirDeclarationUtilKt.getDestructuredParameter(firVariable))) {
                        firVariable = null;
                    }
                    if (firVariable != null) {
                        list2.add(firVariable);
                    }
                }
            }
        }
        if ((declaration instanceof FirProperty) && (setter = ((FirProperty) declaration).getSetter()) != null) {
            KtSourceElement source = setter.getSource();
            FirPropertyAccessor firPropertyAccessor = Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DefaultAccessor.INSTANCE) ? null : setter;
            if (firPropertyAccessor != null && (valueParameters = firPropertyAccessor.getValueParameters()) != null) {
                listCreateListBuilder.addAll(valueParameters);
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private final boolean isExpectAndNonExpect(FirBasedSymbol<?> first, FirBasedSymbol<?> second) {
        FirResolvedDeclarationStatus resolvedStatus = FirConflictsHelpersKt.getResolvedStatus(first);
        boolean z = false;
        boolean z2 = resolvedStatus != null && resolvedStatus.isExpect();
        FirResolvedDeclarationStatus resolvedStatus2 = FirConflictsHelpersKt.getResolvedStatus(second);
        if (resolvedStatus2 != null && resolvedStatus2.isExpect()) {
            z = true;
        }
        return z2 ^ z;
    }

    private final boolean isPrimaryConstructor(FirBasedSymbol<?> firBasedSymbol) {
        return ((firBasedSymbol instanceof FirConstructorSymbol) && ((FirConstructorSymbol) firBasedSymbol).isPrimary()) || Intrinsics.areEqual(firBasedSymbol.getOrigin(), FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE);
    }

    private final void reportConflicts(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, Map<FirBasedSymbol<?>, SmartSet<FirBasedSymbol<?>>> map, Map<FirBasedSymbol<?>, SmartSet<FirBasedSymbol<?>>> map2, FirDeclaration firDeclaration) {
        TypeAliasConstructorInfo<?> typeAliasConstructorInfo;
        FirTypeAliasSymbol typeAliasSymbol;
        for (Map.Entry<FirBasedSymbol<?>, SmartSet<FirBasedSymbol<?>>> entry : map.entrySet()) {
            FirBasedSymbol<?> key = entry.getKey();
            SmartSet<FirBasedSymbol<?>> value = entry.getValue();
            KtSourceElement source = null;
            FirConstructorSymbol firConstructorSymbol = key instanceof FirConstructorSymbol ? (FirConstructorSymbol) key : null;
            if (firConstructorSymbol != null && (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructorSymbol)) != null && (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) != null) {
                source = typeAliasSymbol.getSource();
            }
            FirDeclarationOrigin origin = key.getOrigin();
            if (!(key instanceof FirCallableSymbol)) {
                source = key.getSource();
            } else if (Intrinsics.areEqual(origin, FirDeclarationOrigin.Source.INSTANCE)) {
                source = ((FirCallableSymbol) key).getSource();
            } else if (!Intrinsics.areEqual(origin, FirDeclarationOrigin.Library.INSTANCE)) {
                if (!Intrinsics.areEqual(origin, FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE)) {
                    source = firDeclaration.getSource();
                }
            }
            KtSourceElement ktSourceElement = source;
            if (!value.isEmpty()) {
                if (INSTANCE.isPrimaryConstructor(key)) {
                    if (!value.isEmpty()) {
                        Iterator it = value.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (!INSTANCE.isPrimaryConstructor((FirBasedSymbol) it.next())) {
                                }
                            }
                        }
                    }
                }
                FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) CollectionsKt.singleOrNull(value);
                if (firBasedSymbol == null || !INSTANCE.isExpectAndNonExpect(key, firBasedSymbol)) {
                    PlatformConflictDeclarationsDiagnosticDispatcher conflictDeclarationsDiagnosticDispatcher = FirConflictsDeclarationCheckerKt.getConflictDeclarationsDiagnosticDispatcher(checkerContext.getSession());
                    if (conflictDeclarationsDiagnosticDispatcher == null) {
                        conflictDeclarationsDiagnosticDispatcher = PlatformConflictDeclarationsDiagnosticDispatcher.DEFAULT.INSTANCE;
                    }
                    KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> diagnostic = conflictDeclarationsDiagnosticDispatcher.getDiagnostic(checkerContext, key, value);
                    if (diagnostic != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) diagnostic, (Object) value, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                } else {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXPECT_AND_ACTUAL_IN_THE_SAME_MODULE(), (Object) key, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
        for (Map.Entry<FirBasedSymbol<?>, SmartSet<FirBasedSymbol<?>>> entry2 : map2.entrySet()) {
            FirBasedSymbol<?> key2 = entry2.getKey();
            SmartSet<FirBasedSymbol<?>> value2 = entry2.getValue();
            if (!value2.isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) key2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONTEXTUAL_OVERLOAD_SHADOWED(), (Object) value2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirFile) {
            FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector = new FirDeclarationCollector<>(checkerContext);
            checkFile(checkerContext, (FirFile) firDeclaration, firDeclarationCollector);
            reportConflicts(diagnosticReporter, checkerContext, firDeclarationCollector.getDeclarationConflictingSymbols(), firDeclarationCollector.getDeclarationShadowedViaContextParameters(), firDeclaration);
            return;
        }
        if (firDeclaration instanceof FirClass) {
            FirClass firClass = (FirClass) firDeclaration;
            KtSourceElement source = firClass.getSource();
            if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind)) {
                FirConflictsHelpersKt.checkForLocalRedeclarations(checkerContext, diagnosticReporter, firClass.getTypeParameters());
            }
            FirDeclarationCollector firDeclarationCollector2 = new FirDeclarationCollector(checkerContext);
            FirConflictsHelpersKt.collectClassMembers(firDeclarationCollector2, firClass.getSymbol());
            reportConflicts(diagnosticReporter, checkerContext, firDeclarationCollector2.getDeclarationConflictingSymbols(), firDeclarationCollector2.getDeclarationShadowedViaContextParameters(), firDeclaration);
            return;
        }
        KtSourceElement source2 = firDeclaration.getSource();
        if (((source2 != null ? source2.getKind() : null) instanceof KtFakeSourceElementKind) || !(firDeclaration instanceof FirTypeParameterRefsOwner)) {
            return;
        }
        if ((firDeclaration instanceof FirFunction) || (firDeclaration instanceof FirProperty)) {
            FirConflictsHelpersKt.checkForLocalRedeclarations(checkerContext, diagnosticReporter, getDestructuredParameters((FirCallableDeclaration) firDeclaration));
        }
        FirConflictsHelpersKt.checkForLocalRedeclarations(checkerContext, diagnosticReporter, ((FirTypeParameterRefsOwner) firDeclaration).getTypeParameters());
    }
}

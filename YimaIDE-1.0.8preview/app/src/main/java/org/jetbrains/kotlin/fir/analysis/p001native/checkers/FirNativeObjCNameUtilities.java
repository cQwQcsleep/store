package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\"B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000b*\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u000b*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J?\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001cR\u00020\u0013R\u00020\u0015j\u0006\u0010\u0014\u001a\u00020\u0013j\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010\u001dJ%\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001a*\u0006\u0012\u0002\b\u00030\u001aH\u0002R\u00020\u0013j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u00020!*\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n0\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameUtilities;", Argument.Delimiters.none, "<init>", "()V", "objCNameClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "swiftNameName", "Lorg/jetbrains/kotlin/name/Name;", "exactName", "getObjCNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameUtilities$ObjCName;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getObjCName", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "checkCallableMember", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "firTypeScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "memberSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "declarationToReport", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "getFirstBaseSymbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "allNamesEquals", Argument.Delimiters.none, "ObjCName", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCNameUtilities {
    private static final Name exactName;
    private static final Name swiftNameName;
    public static final FirNativeObjCNameUtilities INSTANCE = new FirNativeObjCNameUtilities();
    private static final ClassId objCNameClassId = ClassId.Companion.topLevel(new FqName("kotlin.native.ObjCName"));

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameUtilities$ObjCName;", Argument.Delimiters.none, "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "getAnnotation", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", ModuleXmlParser.NAME, Argument.Delimiters.none, "getName", "()Ljava/lang/String;", "swiftName", "getSwiftName", "exact", Argument.Delimiters.none, "getExact", "()Z", "equals", "other", "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ObjCName {
        private final FirAnnotation annotation;
        private final boolean exact;
        private final String name;
        private final String swiftName;

        public ObjCName(FirAnnotation firAnnotation) {
            firAnnotation.getClass();
            this.annotation = firAnnotation;
            this.name = FirAnnotationUtilsKt.getStringArgument(firAnnotation, StandardNames.NAME);
            this.swiftName = FirAnnotationUtilsKt.getStringArgument(firAnnotation, FirNativeObjCNameUtilities.swiftNameName);
            Boolean booleanArgument = FirAnnotationUtilsKt.getBooleanArgument(firAnnotation, FirNativeObjCNameUtilities.exactName);
            this.exact = booleanArgument != null ? booleanArgument.booleanValue() : false;
        }

        public boolean equals(Object other) {
            if (!(other instanceof ObjCName)) {
                return false;
            }
            ObjCName objCName = (ObjCName) other;
            return Intrinsics.areEqual(this.name, objCName.name) && Intrinsics.areEqual(this.swiftName, objCName.swiftName) && this.exact == objCName.exact;
        }

        public final FirAnnotation getAnnotation() {
            return this.annotation;
        }

        public final boolean getExact() {
            return this.exact;
        }

        public final String getName() {
            return this.name;
        }

        public final String getSwiftName() {
            return this.swiftName;
        }

        public int hashCode() {
            String str = this.name;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.swiftName;
            return ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Boolean.hashCode(this.exact);
        }
    }

    static {
        Name nameIdentifier = Name.identifier("swiftName");
        nameIdentifier.getClass();
        swiftNameName = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("exact");
        nameIdentifier2.getClass();
        exactName = nameIdentifier2;
    }

    private FirNativeObjCNameUtilities() {
    }

    private final boolean allNamesEquals(List<? extends List<ObjCName>> list) {
        List<ObjCName> list2 = list.get(0);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            if (!Intrinsics.areEqual(list2, list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirCallableSymbol<?> getFirstBaseSymbol(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        FirClassLikeSymbol<?> symbol;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirTypeScope firTypeScopeUnsubstitutedScope;
        checkerContext.getSession();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol);
        if (coneClassLikeLookupTagContainingClassLookupTag != null && (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) != null && (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, symbol)) != null && (firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClassSymbolFullyExpandedClass)) != null) {
            List<FirCallableSymbol<?>> directOverriddenSafe = FirTypeScopeKt.getDirectOverriddenSafe(firTypeScopeUnsubstitutedScope, firCallableSymbol);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenSafe, 10));
            Iterator<T> it = directOverriddenSafe.iterator();
            while (it.hasNext()) {
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) it.next()).getFir();
                while (true) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        break;
                    }
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
                FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration.getSymbol();
                if (symbol2 == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                    return null;
                }
                arrayList.add(symbol2);
            }
            if (!arrayList.isEmpty()) {
                return getFirstBaseSymbol(checkerContext, (FirCallableSymbol) CollectionsKt.first(arrayList));
            }
        }
        return firCallableSymbol;
    }

    private final ObjCName getObjCName(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firAnnotationContainer, objCNameClassId, firSession);
        if (annotationByClassId != null) {
            return new ObjCName(annotationByClassId);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void checkCallableMember(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeScope.getClass();
        firCallableSymbol.getClass();
        firDeclaration.getClass();
        List<FirCallableSymbol<?>> directOverriddenSafe = FirTypeScopeKt.getDirectOverriddenSafe(firTypeScope, firCallableSymbol);
        ArrayList<FirCallableSymbol<?>> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenSafe, 10));
        Iterator<T> it = directOverriddenSafe.iterator();
        while (it.hasNext()) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) it.next()).getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                } else {
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return;
            }
            arrayList.add(symbol);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (FirCallableSymbol<?> firCallableSymbol2 : arrayList) {
            FirNativeObjCNameUtilities firNativeObjCNameUtilities = INSTANCE;
            arrayList2.add(firNativeObjCNameUtilities.getObjCNames(firNativeObjCNameUtilities.getFirstBaseSymbol(checkerContext, firCallableSymbol2), checkerContext.getSession()));
        }
        if (allNamesEquals(arrayList2)) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) it2.next());
            FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag) : null;
            if (regularClassSymbol != null) {
                arrayList3.add(regularClassSymbol);
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), (KtDiagnosticFactory2) FirNativeErrors.INSTANCE.getINCOMPATIBLE_OBJC_NAME_OVERRIDE(), (Object) firCallableSymbol, (Object) arrayList3, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    public final List<ObjCName> getObjCNames(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, FirResolvePhase.ANNOTATION_ARGUMENTS);
        if (!(firBasedSymbol instanceof FirFunctionSymbol)) {
            if (!(firBasedSymbol instanceof FirPropertySymbol)) {
                return CollectionsKt.listOf(getObjCName(firBasedSymbol, firSession));
            }
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            FirNativeObjCNameUtilities firNativeObjCNameUtilities = INSTANCE;
            listCreateListBuilder.add(firNativeObjCNameUtilities.getObjCName(firBasedSymbol, firSession));
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firBasedSymbol;
            FirReceiverParameterSymbol receiverParameterSymbol = firPropertySymbol.getReceiverParameterSymbol();
            listCreateListBuilder.add(receiverParameterSymbol != null ? firNativeObjCNameUtilities.getObjCName(receiverParameterSymbol, firSession) : null);
            Iterator<T> it = firPropertySymbol.getContextParameterSymbols().iterator();
            while (it.hasNext()) {
                listCreateListBuilder.add(INSTANCE.getObjCName((FirValueParameterSymbol) it.next(), firSession));
            }
            return CollectionsKt.build(listCreateListBuilder);
        }
        List listCreateListBuilder2 = CollectionsKt.createListBuilder();
        FirNativeObjCNameUtilities firNativeObjCNameUtilities2 = INSTANCE;
        listCreateListBuilder2.add(firNativeObjCNameUtilities2.getObjCName(firBasedSymbol, firSession));
        FirFunctionSymbol firFunctionSymbol = (FirFunctionSymbol) firBasedSymbol;
        FirResolvedTypeRef resolvedReceiverTypeRef = firFunctionSymbol.getResolvedReceiverTypeRef();
        listCreateListBuilder2.add(resolvedReceiverTypeRef != null ? firNativeObjCNameUtilities2.getObjCName((FirAnnotationContainer) resolvedReceiverTypeRef, firSession) : null);
        FirReceiverParameterSymbol receiverParameterSymbol2 = firFunctionSymbol.getReceiverParameterSymbol();
        listCreateListBuilder2.add(receiverParameterSymbol2 != null ? firNativeObjCNameUtilities2.getObjCName(receiverParameterSymbol2, firSession) : null);
        Iterator<T> it2 = firFunctionSymbol.getValueParameterSymbols().iterator();
        while (it2.hasNext()) {
            listCreateListBuilder2.add(INSTANCE.getObjCName((FirValueParameterSymbol) it2.next(), firSession));
        }
        Iterator<T> it3 = firFunctionSymbol.getContextParameterSymbols().iterator();
        while (it3.hasNext()) {
            listCreateListBuilder2.add(INSTANCE.getObjCName((FirValueParameterSymbol) it3.next(), firSession));
        }
        return CollectionsKt.build(listCreateListBuilder2);
    }

    private final ObjCName getObjCName(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firBasedSymbol, objCNameClassId, firSession);
        if (annotationByClassId != null) {
            return new ObjCName(annotationByClassId);
        }
        return null;
    }
}

package org.jetbrains.kotlin.fir.resolve.optimization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$H\u0016J\u0016\u0010%\u001a\u00020\u00132\f\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bH\u0002J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0006\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0007j\b\u0012\u0004\u0012\u00020\f`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/optimization/FirReachabilityAnalyzer;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitorVoid;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "reachable", "Ljava/util/HashSet;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lkotlin/collections/HashSet;", "worklist", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "processed", "collectReachableSymbols", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitResolvedNamedReference", "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "mark", "symbol", "declaration", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReachabilityAnalyzer extends FirVisitorVoid {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HashSet<FirDeclaration> processed;
    private final HashSet<FirBasedSymbol<?>> reachable;
    private final FirSession session;
    private final ArrayDeque<FirDeclaration> worklist;

    public FirReachabilityAnalyzer(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.reachable = new HashSet<>();
        this.worklist = new ArrayDeque<>();
        this.processed = new HashSet<>();
    }

    private final void mark(FirBasedSymbol<?> symbol) {
        FirDeclaration fir;
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        CallableId callableId;
        if (symbol == null || (fir = symbol.getFir()) == null) {
            return;
        }
        ClassId classId = null;
        if (fir instanceof FirCallableDeclaration) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr != null) {
                mark(originalForSubstitutionOverrideAttr.getSymbol());
            }
        }
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol != null && (callableId = firCallableSymbol.getCallableId()) != null) {
            classId = callableId.getClassId();
        }
        if (classId != null && !classId.isLocal() && (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(this.session).getClassLikeSymbolByClassId(classId)) != null) {
            mark(classLikeSymbolByClassId);
        }
        mark(fir);
    }

    public final Set<FirBasedSymbol<?>> collectReachableSymbols(FirFile file) {
        file.getClass();
        Iterator<T> it = INSTANCE.collectRoots(file).iterator();
        while (it.hasNext()) {
            mark((FirDeclaration) it.next());
        }
        while (!this.worklist.isEmpty()) {
            FirDeclaration firDeclaration = (FirDeclaration) this.worklist.removeFirst();
            if (this.processed.add(firDeclaration)) {
                firDeclaration.accept(this);
            }
        }
        return this.reachable;
    }

    public void visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess) {
        callableReferenceAccess.getClass();
        visitElement(callableReferenceAccess);
        FirNamedReference calleeReference = callableReferenceAccess.getCalleeReference();
        FirResolvedNamedReference firResolvedNamedReference = calleeReference instanceof FirResolvedNamedReference ? (FirResolvedNamedReference) calleeReference : null;
        mark(firResolvedNamedReference != null ? firResolvedNamedReference.getResolvedSymbol() : null);
    }

    public void visitElement(FirElement element) {
        element.getClass();
        element.acceptChildren(this);
    }

    public void visitRegularClass(FirRegularClass regularClass) {
        regularClass.getClass();
        Iterator<T> it = regularClass.getAnnotations().iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(this);
        }
        Iterator<T> it2 = regularClass.getSuperTypeRefs().iterator();
        while (it2.hasNext()) {
            ((FirTypeRef) it2.next()).accept(this);
        }
        Iterator<T> it3 = regularClass.getTypeParameters().iterator();
        while (it3.hasNext()) {
            ((FirTypeParameterRef) it3.next()).accept(this);
        }
        Iterator<T> it4 = regularClass.getContextParameters().iterator();
        while (it4.hasNext()) {
            ((FirValueParameter) it4.next()).accept(this);
        }
        for (FirDeclaration firDeclaration : regularClass.getDeclarations()) {
            if (firDeclaration instanceof FirConstructor) {
                FirConstructor firConstructor = (FirConstructor) firDeclaration;
                if (firConstructor.getIsPrimary()) {
                    mark(firConstructor.getSymbol());
                }
            }
            if (INSTANCE.shouldPreserveAnchor(firDeclaration)) {
                mark(firDeclaration.getSymbol());
            }
        }
        if (regularClass.getStatus().isInline() || regularClass.getStatus().isValue()) {
            for (FirDeclaration firDeclaration2 : regularClass.getDeclarations()) {
                if (firDeclaration2 instanceof FirConstructor) {
                    FirConstructor firConstructor2 = (FirConstructor) firDeclaration2;
                    if (firConstructor2.getIsPrimary()) {
                        List<FirValueParameter> valueParameters = firConstructor2.getValueParameters();
                        HashSet hashSet = new HashSet();
                        Iterator<T> it5 = valueParameters.iterator();
                        while (it5.hasNext()) {
                            hashSet.add(((FirValueParameter) it5.next()).getName());
                        }
                        for (FirDeclaration firDeclaration3 : regularClass.getDeclarations()) {
                            if ((firDeclaration3 instanceof FirProperty) && hashSet.contains(((FirProperty) firDeclaration3).getName())) {
                                mark(firDeclaration3);
                            }
                        }
                    }
                }
                if (firDeclaration2 instanceof FirNamedFunction) {
                    FirNamedFunction firNamedFunction = (FirNamedFunction) firDeclaration2;
                    if (Intrinsics.areEqual(firNamedFunction.getName().asString(), "equals")) {
                        mark(firNamedFunction.getSymbol());
                    }
                }
            }
        }
        if (regularClass.getClassKind() == ClassKind.ENUM_CLASS) {
            for (FirDeclaration firDeclaration4 : regularClass.getDeclarations()) {
                if (firDeclaration4 instanceof FirConstructor) {
                    mark(((FirConstructor) firDeclaration4).getSymbol());
                }
            }
        }
    }

    public void visitResolvedNamedReference(FirResolvedNamedReference resolvedNamedReference) {
        resolvedNamedReference.getClass();
        visitElement(resolvedNamedReference);
        mark(resolvedNamedReference.getResolvedSymbol());
    }

    public void visitResolvedQualifier(FirResolvedQualifier resolvedQualifier) {
        resolvedQualifier.getClass();
        visitElement(resolvedQualifier);
        mark(resolvedQualifier.getSymbol());
    }

    public void visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef) {
        resolvedTypeRef.getClass();
        visitElement(resolvedTypeRef);
        ConeKotlinType coneType = resolvedTypeRef.getConeType();
        mark(ToSymbolUtilsKt.toSymbol(coneType, this.session));
        ConeKotlinType abbreviatedType = AbbreviatedTypeAttributeKt.getAbbreviatedType(coneType);
        if (abbreviatedType instanceof ConeClassLikeType) {
            mark(ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) abbreviatedType).getLookupTag(), this.session));
        }
        if (coneType instanceof ConeClassLikeType) {
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) coneType).getLookupTag(), this.session);
            if (symbol instanceof FirTypeAliasSymbol) {
                mark(symbol);
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0012\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/optimization/FirReachabilityAnalyzer$Companion;", Argument.Delimiters.none, "<init>", "()V", "collectRoots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "isRoot", Argument.Delimiters.none, "decl", "isPrivateScope", "isTopLevel", "shouldPreserveAnchor", "declaration", "bodyHasAnonymousObject", "body", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean bodyHasAnonymousObject(FirBlock body) {
            if (body == null) {
                return false;
            }
            List<FirStatement> statements = body.getStatements();
            if ((statements instanceof Collection) && statements.isEmpty()) {
                return false;
            }
            for (FirStatement firStatement : statements) {
                if ((firStatement instanceof FirAnonymousObjectExpression) || ((firStatement instanceof FirReturnExpression) && (((FirReturnExpression) firStatement).getResult() instanceof FirAnonymousObjectExpression))) {
                    return true;
                }
            }
            return false;
        }

        private static final void collectRoots$addRoots(ArrayList<FirDeclaration> arrayList, List<? extends FirDeclaration> list, boolean z, boolean z2) {
            for (FirDeclaration firDeclaration : list) {
                if (FirReachabilityAnalyzer.INSTANCE.isRoot(firDeclaration, z, z2)) {
                    arrayList.add(firDeclaration);
                }
                if (firDeclaration instanceof FirRegularClass) {
                    FirRegularClass firRegularClass = (FirRegularClass) firDeclaration;
                    collectRoots$addRoots(arrayList, firRegularClass.getDeclarations(), Intrinsics.areEqual(firRegularClass.getStatus().getVisibility(), Visibilities.Private.INSTANCE) || z, false);
                }
            }
        }

        private final boolean isRoot(FirDeclaration decl, boolean isPrivateScope, boolean isTopLevel) {
            if (isPrivateScope) {
                return false;
            }
            if ((decl instanceof FirEnumEntry) || !decl.getAnnotations().isEmpty()) {
                return true;
            }
            if ((decl instanceof FirConstructor) && ((FirConstructor) decl).getIsPrimary()) {
                return true;
            }
            if (isTopLevel && (decl instanceof FirRegularClass)) {
                return true;
            }
            if (decl instanceof FirMemberDeclaration) {
                FirDeclarationStatus status = ((FirMemberDeclaration) decl).getStatus();
                Visibility visibility = status.getVisibility();
                if (status.isConst() || status.isInline() || Intrinsics.areEqual(visibility, Visibilities.Public.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldPreserveAnchor(FirDeclaration declaration) {
            if (declaration instanceof FirProperty) {
                return ((FirProperty) declaration).getInitializer() instanceof FirAnonymousObjectExpression;
            }
            if (declaration instanceof FirFunction) {
                return bodyHasAnonymousObject(((FirFunction) declaration).getBody());
            }
            if (declaration instanceof FirAnonymousInitializer) {
                return bodyHasAnonymousObject(((FirAnonymousInitializer) declaration).getBody());
            }
            return false;
        }

        public final List<FirDeclaration> collectRoots(FirFile file) {
            file.getClass();
            ArrayList arrayList = new ArrayList();
            collectRoots$addRoots(arrayList, file.getDeclarations(), false, true);
            return arrayList;
        }

        private Companion() {
        }
    }

    private final void mark(FirDeclaration declaration) {
        if (this.reachable.add(declaration.getSymbol())) {
            this.worklist.add(declaration);
        }
    }
}

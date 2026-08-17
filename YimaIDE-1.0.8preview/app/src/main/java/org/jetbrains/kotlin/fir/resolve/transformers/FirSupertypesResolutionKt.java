package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.LocalClassesNavigationInfo;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirMemberTypeParameterScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeWithSubstitutionKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.utils.CollectionsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\b¢\u0006\u0002\u0010\u0010\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\t*\u00020\u0002H\u0002\u001a6\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002\u001a\"\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002\u001a+\u0010$\u001a\b\u0012\u0004\u0012\u0002H%0#\"\u0004\b\u0000\u0010%*\b\u0012\u0004\u0012\u0002H%0#2\u0006\u0010&\u001a\u0002H%H\u0002¢\u0006\u0002\u0010'\u001a,\u0010(\u001a\b\u0012\u0004\u0012\u0002H%0#\"\u0004\b\u0000\u0010%*\b\u0012\u0004\u0012\u0002H%0#2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H%0\u0013H\u0002\u001a*\u0010*\u001a\f\u0012\u0004\u0012\u00020\t0#j\u0002`+*\f\u0012\u0004\u0012\u00020\t0#j\u0002`+2\b\u0010,\u001a\u0004\u0018\u00010\tH\u0002*\u0018\b\u0002\u0010\"\"\b\u0012\u0004\u0012\u00020\t0#2\b\u0012\u0004\u0012\u00020\t0#¨\u0006-"}, d2 = {"runSupertypeResolvePhaseForLocalClass", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "currentScopeList", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "localClassesNavigationInfo", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/util/List;)Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "typeParametersScope", "createOtherScopesForNestedClassesOrCompanion", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "supertypeComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;", "withCompanionScopes", Argument.Delimiters.none, "createErrorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "sourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "message", Argument.Delimiters.none, "kind", "Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "ScopePersistentList", "Lkotlinx/collections/immutable/PersistentList;", "push", "E", "element", "(Lkotlinx/collections/immutable/PersistentList;Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentList;", "pushAll", "collection", "pushIfNotNull", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ScopePersistentList;", "scope", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSupertypesResolutionKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FirErrorTypeRef createErrorTypeRef(KtSourceElement ktSourceElement, String str, DiagnosticKind diagnosticKind) {
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setSource(ktSourceElement);
        firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic(str, diagnosticKind));
        return firErrorTypeRefBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection<FirScope> createOtherScopesForNestedClassesOrCompanion(FirClass firClass, FirSession firSession, ScopeSession scopeSession, SupertypeComputationSession supertypeComputationSession, boolean z) {
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addIfNotNull(arrayList, FirDeclaredMemberScopeProviderKt.nestedClassifierScope(firSession, firClass));
        if (z) {
            List<FirDeclaration> declarations = firClass.getDeclarations();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : declarations) {
                if (obj instanceof FirRegularClass) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (((FirRegularClass) obj2).getStatus().isCompanion()) {
                    arrayList3.add(obj2);
                }
            }
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                CollectionsKt.addIfNotNull(arrayList, FirDeclaredMemberScopeProviderKt.nestedClassifierScope(firSession, (FirRegularClass) it.next()));
            }
        }
        for (ConeClassLikeType coneClassLikeType : SupertypeUtilsKt.lookupSuperTypes(firClass, false, true, firSession, true, supertypeComputationSession.getSupertypesSupplier())) {
            FirContainingNamesAwareScope nestedClassifierScope = ScopesKt.getNestedClassifierScope(coneClassLikeType.getLookupTag(), firSession, scopeSession);
            FirContainingNamesAwareScope firContainingNamesAwareScopeWrapNestedClassifierScopeWithSubstitutionForSuperType = nestedClassifierScope != null ? FirNestedClassifierScopeWithSubstitutionKt.wrapNestedClassifierScopeWithSubstitutionForSuperType(nestedClassifierScope, coneClassLikeType, firSession) : null;
            if (firContainingNamesAwareScopeWrapNestedClassifierScopeWithSubstitutionForSuperType != null) {
                arrayList.add(firContainingNamesAwareScopeWrapNestedClassifierScopeWithSubstitutionForSuperType);
            }
        }
        return arrayList;
    }

    private static final <E> PersistentList<E> push(PersistentList<? extends E> persistentList, E e) {
        return persistentList.add(0, e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E> PersistentList<E> pushAll(PersistentList<? extends E> persistentList, Collection<? extends E> collection) {
        return persistentList.addAll(0, collection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final PersistentList<FirScope> pushIfNotNull(PersistentList<? extends FirScope> persistentList, FirScope firScope) {
        return firScope == null ? persistentList : push(persistentList, firScope);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <F extends FirClassLikeDeclaration> F runSupertypeResolvePhaseForLocalClass(F f, FirSession firSession, ScopeSession scopeSession, List<? extends FirScope> list, LocalClassesNavigationInfo localClassesNavigationInfo, FirFile firFile, List<? extends FirDeclaration> list2) throws KotlinIllegalArgumentExceptionWithAttachments {
        f.getClass();
        firSession.getClass();
        scopeSession.getClass();
        list.getClass();
        localClassesNavigationInfo.getClass();
        firFile.getClass();
        list2.getClass();
        SupertypeComputationSession supertypeComputationSessionSuperTypesPhaseSession = FirJumpingPhaseComputationSessionForLocalClassesProviderKt.getJumpingPhaseComputationSessionForLocalClassesProvider(firSession).superTypesPhaseSession();
        f.accept(new FirSupertypeResolverVisitor(firSession, supertypeComputationSessionSuperTypesPhaseSession, scopeSession, ExtensionsKt.toPersistentList(list), localClassesNavigationInfo, firFile, list2), null);
        supertypeComputationSessionSuperTypesPhaseSession.breakLoops(firSession, localClassesNavigationInfo);
        return (F) f.transform(new FirApplySupertypesTransformer(supertypeComputationSessionSuperTypesPhaseSession, firSession, scopeSession), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirScope typeParametersScope(FirClassLikeDeclaration firClassLikeDeclaration) {
        if (firClassLikeDeclaration.getTypeParameters().isEmpty()) {
            return null;
        }
        return new FirMemberTypeParameterScope(firClassLikeDeclaration);
    }
}

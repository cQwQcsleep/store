package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ)\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\u0006\u0010\u000e\u001a\u0002H\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0002J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u001a\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirApplySupertypesTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultTransformer;", Argument.Delimiters.none, "supertypeComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformDeclarationContent", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "declaration", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "applyResolvedSupertypesToClass", Argument.Delimiters.none, "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "transformAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "transformTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirApplySupertypesTransformer extends FirDefaultTransformer<Object> {
    private final ScopeSession scopeSession;
    private final FirSession session;
    private final SupertypeComputationSession supertypeComputationSession;

    public FirApplySupertypesTransformer(SupertypeComputationSession supertypeComputationSession, FirSession firSession, ScopeSession scopeSession) {
        supertypeComputationSession.getClass();
        firSession.getClass();
        scopeSession.getClass();
        this.supertypeComputationSession = supertypeComputationSession;
        this.session = firSession;
        this.scopeSession = scopeSession;
    }

    private final void applyResolvedSupertypesToClass(FirClass firClass) {
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        if (!(superTypeRefs instanceof Collection) || !superTypeRefs.isEmpty()) {
            for (FirTypeRef firTypeRef : superTypeRefs) {
                if (!(firTypeRef instanceof FirResolvedTypeRef) || (firTypeRef instanceof FirImplicitBuiltinTypeRef)) {
                    List<FirResolvedTypeRef> resolvedSupertypeRefs = this.supertypeComputationSession.getResolvedSupertypeRefs(firClass);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedSupertypeRefs, 10));
                    Iterator<T> it = resolvedSupertypeRefs.iterator();
                    while (it.hasNext()) {
                        arrayList.add(this.supertypeComputationSession.expandTypealiasInPlace((FirResolvedTypeRef) it.next(), this.session));
                    }
                    firClass.replaceSuperTypeRefs(arrayList);
                    break;
                }
            }
        }
        PlatformSupertypeUpdater platformSupertypeUpdater = PlatformSupertypeUpdaterKt.getPlatformSupertypeUpdater(this.session);
        if (platformSupertypeUpdater != null) {
            platformSupertypeUpdater.updateSupertypesIfNeeded(firClass, this.scopeSession);
        }
    }

    private final FirDeclaration transformDeclarationContent(FirDeclaration declaration, Object data) {
        FirElement firElementTransformChildren = declaration.transformChildren(this, data);
        firElementTransformChildren.getClass();
        return (FirDeclaration) firElementTransformChildren;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObject(FirAnonymousObject anonymousObject, Object data) {
        anonymousObject.getClass();
        applyResolvedSupertypesToClass(anonymousObject);
        FirElement firElementTransformChildren = anonymousObject.transformChildren(this, data);
        firElementTransformChildren.getClass();
        return (FirAnonymousObject) firElementTransformChildren;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Object data) {
        element.getClass();
        return element;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Object data) {
        file.getClass();
        try {
            FirDeclaration firDeclarationTransformDeclarationContent = transformDeclarationContent(file, null);
            firDeclarationTransformDeclarationContent.getClass();
            return (FirFile) firDeclarationTransformDeclarationContent;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        applyResolvedSupertypesToClass(regularClass);
        FirDeclaration firDeclarationTransformDeclarationContent = transformDeclarationContent(regularClass, null);
        firDeclarationTransformDeclarationContent.getClass();
        return (FirRegularClass) firDeclarationTransformDeclarationContent;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, Object data) {
        replSnippet.getClass();
        FirDeclaration firDeclarationTransformDeclarationContent = transformDeclarationContent(replSnippet, null);
        firDeclarationTransformDeclarationContent.getClass();
        return (FirReplSnippet) firDeclarationTransformDeclarationContent;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTypeAlias(FirTypeAlias typeAlias, Object data) {
        typeAlias.getClass();
        if (typeAlias.getExpandedTypeRef() instanceof FirResolvedTypeRef) {
            return typeAlias;
        }
        typeAlias.replaceExpandedTypeRef(this.supertypeComputationSession.expandTypealiasInPlace(this.supertypeComputationSession.getResolvedExpandedTypeRef(typeAlias), this.session));
        return typeAlias;
    }
}

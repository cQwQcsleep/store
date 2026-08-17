package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.DesignationState;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0013J\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0015J\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\f¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", Argument.Delimiters.none, "<init>", "()V", "filesWithResolvedImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "importsAreResolved", Argument.Delimiters.none, "file", "useCacheForImportScope", "getUseCacheForImportScope", "()Z", "recordThatImportsAreResolved", Argument.Delimiters.none, "annotationResolved", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "declarationsWithAnnotationResolutionInProgress", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "declarationsWithResolvedAnnotations", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "annotationResolutionWasAlreadyStarted", "klass", "annotationsAreResolved", "declaration", "treatNonSourceDeclarationsAsResolved", "getTreatNonSourceDeclarationsAsResolved", "recordThatAnnotationResolutionStarted", "recordThatAnnotationsAreResolved", "resolveAnnotationsOnAnnotationIfNeeded", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "resolveAnnotationSymbol", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class CompilerRequiredAnnotationsComputationSession {
    private final Set<FirFile> filesWithResolvedImports = new LinkedHashSet();
    private final Set<FirClassLikeDeclaration> declarationsWithAnnotationResolutionInProgress = new LinkedHashSet();
    private final Set<FirAnnotationContainer> declarationsWithResolvedAnnotations = new LinkedHashSet();

    public final boolean annotationResolutionWasAlreadyStarted(FirClassLikeDeclaration klass) {
        klass.getClass();
        return this.declarationsWithAnnotationResolutionInProgress.contains(klass);
    }

    public void annotationResolved(FirAnnotationCall annotation) {
        annotation.getClass();
    }

    public final boolean annotationsAreResolved(FirAnnotationContainer declaration) {
        declaration.getClass();
        if (declaration instanceof FirFile) {
            return false;
        }
        if (getTreatNonSourceDeclarationsAsResolved() && (declaration instanceof FirDeclaration)) {
            FirDeclaration firDeclaration = (FirDeclaration) declaration;
            if (!Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Source.INSTANCE) && !Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Synthetic.ReplContainerClass.INSTANCE)) {
                return true;
            }
        }
        return this.declarationsWithResolvedAnnotations.contains(declaration);
    }

    public boolean getTreatNonSourceDeclarationsAsResolved() {
        return true;
    }

    public boolean getUseCacheForImportScope() {
        return false;
    }

    public final boolean importsAreResolved(FirFile file) {
        file.getClass();
        return this.filesWithResolvedImports.contains(file);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final void recordThatAnnotationResolutionStarted(FirClassLikeDeclaration klass) throws KotlinIllegalStateExceptionWithAttachments {
        klass.getClass();
        if (this.declarationsWithAnnotationResolutionInProgress.add(klass)) {
            return;
        }
        KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("Annotation resolution was already started");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "class", klass);
        kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalStateExceptionWithAttachments;
    }

    public final void recordThatAnnotationsAreResolved(FirAnnotationContainer declaration) {
        declaration.getClass();
        TypeIntrinsics.asMutableCollection(this.declarationsWithAnnotationResolutionInProgress).remove(declaration);
        this.declarationsWithResolvedAnnotations.add(declaration);
    }

    public final void recordThatImportsAreResolved(FirFile file) {
        file.getClass();
        if (this.filesWithResolvedImports.add(file)) {
            return;
        }
        k2d.a("Imports are resolved twice");
    }

    public void resolveAnnotationSymbol(FirRegularClassSymbol symbol, ScopeSession scopeSession) {
        symbol.getClass();
        scopeSession.getClass();
        DesignationState designationStateCreate = DesignationState.INSTANCE.create(symbol, MapsKt.emptyMap(), true);
        if (designationStateCreate == null) {
            return;
        }
        FirTransformerUtilKt.transformSingle(designationStateCreate.getFirstDeclaration(), new FirDesignatedCompilerRequiredAnnotationsResolveTransformer(designationStateCreate.getFirstDeclaration().getModuleData().getSession(), scopeSession, this, designationStateCreate), null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resolveAnnotationsOnAnnotationIfNeeded(FirRegularClassSymbol symbol, ScopeSession scopeSession) {
        symbol.getClass();
        scopeSession.getClass();
        FirRegularClass firRegularClass = (FirRegularClass) symbol.getFir();
        if (annotationsAreResolved(firRegularClass) || firRegularClass.getAnnotations().isEmpty()) {
            return;
        }
        resolveAnnotationSymbol(symbol, scopeSession);
    }
}

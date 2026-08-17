package org.jetbrains.kotlin.fir.symbols;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0007b\u0002\b\u0011¢\u0006\u0002\u0010\u0010R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u001e\u0010\b\u001a\u00028\u00008FX\u0087\u0004r\u0002\b\f¢\u0006\f\u0012\u0004\b\t\u0010\u0005\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R$\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8FX\u0087\u0004r\u0002\b\f¢\u0006\f\u0012\u0004\b$\u0010\u0005\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020#0\"8F¢\u0006\u0006\u001a\u0004\b(\u0010&R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020#0\"8F¢\u0006\u0006\u001a\u0004\b*\u0010&R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020#0\"8F¢\u0006\u0006\u001a\u0004\b,\u0010&R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\"8F¢\u0006\u0006\u001a\u0004\b/\u0010&¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "E", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "<init>", "()V", "_fir", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "fir", "getFir$annotations", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "bind", Argument.Delimiters.none, "e", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "isBound", Argument.Delimiters.none, "()Z", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations$annotations", "getAnnotations", "()Ljava/util/List;", "resolvedAnnotationsWithArguments", "getResolvedAnnotationsWithArguments", "resolvedAnnotationsWithClassIds", "getResolvedAnnotationsWithClassIds", "resolvedCompilerAnnotationsWithClassIds", "getResolvedCompilerAnnotationsWithClassIds", "resolvedAnnotationClassIds", "Lorg/jetbrains/kotlin/name/ClassId;", "getResolvedAnnotationClassIds", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirBasedSymbol<E extends FirDeclaration> implements DeclarationSymbolMarker {
    private E _fir;

    @SymbolInternals
    public static /* synthetic */ void getAnnotations$annotations() {
    }

    @SymbolInternals
    public static /* synthetic */ void getFir$annotations() {
    }

    @FirImplementationDetail
    public final void bind(E e) {
        e.getClass();
        this._fir = e;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final List<FirAnnotation> getAnnotations() throws KotlinIllegalArgumentExceptionWithAttachments {
        return getFir().getAnnotations();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final E getFir() throws KotlinIllegalArgumentExceptionWithAttachments {
        E e = this._fir;
        if (e != null) {
            return e;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Fir is not initialized for " + Reflection.getOrCreateKotlinClass(getClass()), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirSymbolIdEntry(exceptionAttachmentBuilder, "symbol", this);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public final FirModuleData getModuleData() {
        return getFir().getModuleData();
    }

    public final FirDeclarationOrigin getOrigin() {
        return getFir().getOrigin();
    }

    public final List<ClassId> getResolvedAnnotationClassIds() {
        return FirBasedSymbolKt.resolvedAnnotationClassIds(getFir(), this);
    }

    public final List<FirAnnotation> getResolvedAnnotationsWithArguments() {
        return FirBasedSymbolKt.resolvedAnnotationsWithArguments(getFir(), this);
    }

    public final List<FirAnnotation> getResolvedAnnotationsWithClassIds() {
        return FirBasedSymbolKt.resolvedAnnotationsWithClassIds(getFir(), this);
    }

    public final List<FirAnnotation> getResolvedCompilerAnnotationsWithClassIds() {
        return FirBasedSymbolKt.resolvedCompilerRequiredAnnotations(getFir(), this);
    }

    public final KtSourceElement getSource() {
        return getFir().getSource();
    }

    public final boolean isBound() {
        return this._fir != null;
    }
}

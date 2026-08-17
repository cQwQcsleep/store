package org.jetbrains.kotlin.fir.declarations;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirGeneratedElementsValidator;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "()V", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitArgumentList", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "visitNamedReference", "namedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "visitTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "visitDeclarationStatus", "declarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "visitTypeParameterRef", "typeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirGeneratedElementsValidator extends FirDefaultVisitor<Unit, Object> {
    public static final FirGeneratedElementsValidator INSTANCE = new FirGeneratedElementsValidator();

    private FirGeneratedElementsValidator() {
    }

    /* JADX INFO: renamed from: visitAnnotation, reason: collision with other method in class */
    public void m278visitAnnotation(FirAnnotation annotation, Object data) {
        annotation.getClass();
        annotation.acceptChildren(this, null);
    }

    /* JADX INFO: renamed from: visitAnnotationCall, reason: collision with other method in class */
    public void m279visitAnnotationCall(FirAnnotationCall annotationCall, Object data) {
        annotationCall.getClass();
        annotationCall.acceptChildren(this, null);
    }

    /* JADX INFO: renamed from: visitArgumentList, reason: collision with other method in class */
    public void m280visitArgumentList(FirArgumentList argumentList, Object data) {
        argumentList.getClass();
        if ((argumentList instanceof FirResolvedArgumentList) || (argumentList instanceof FirEmptyArgumentList)) {
            ((FirAbstractArgumentList) argumentList).acceptChildren(this, null);
        } else {
            w01.a("Failed requirement.");
        }
    }

    /* JADX INFO: renamed from: visitDeclarationStatus, reason: collision with other method in class */
    public void m281visitDeclarationStatus(FirDeclarationStatus declarationStatus, Object data) {
        declarationStatus.getClass();
        if (declarationStatus instanceof FirResolvedDeclarationStatus) {
            return;
        }
        w01.a("Failed requirement.");
    }

    /* JADX INFO: renamed from: visitElement, reason: collision with other method in class */
    public void m282visitElement(FirElement element, Object data) {
        element.getClass();
        element.acceptChildren(this, null);
    }

    /* JADX INFO: renamed from: visitNamedReference, reason: collision with other method in class */
    public void m283visitNamedReference(FirNamedReference namedReference, Object data) {
        namedReference.getClass();
        if (namedReference instanceof FirResolvedNamedReference) {
            namedReference.acceptChildren(this, null);
        } else {
            w01.a("Failed requirement.");
        }
    }

    /* JADX INFO: renamed from: visitRegularClass, reason: collision with other method in class */
    public void m284visitRegularClass(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        regularClass.acceptChildren(this, null);
    }

    /* JADX INFO: renamed from: visitResolvedTypeRef, reason: collision with other method in class */
    public void m285visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, Object data) {
        resolvedTypeRef.getClass();
        Iterator it = resolvedTypeRef.getAnnotations().iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(INSTANCE, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: visitTypeParameterRef, reason: collision with other method in class */
    public void m286visitTypeParameterRef(FirTypeParameterRef typeParameterRef, Object data) {
        typeParameterRef.getClass();
        ((FirTypeParameter) typeParameterRef.getSymbol().getFir()).accept(this, null);
    }

    /* JADX INFO: renamed from: visitTypeRef, reason: collision with other method in class */
    public void m287visitTypeRef(FirTypeRef typeRef, Object data) {
        typeRef.getClass();
        typeRef.acceptChildren(this, null);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnnotation(FirAnnotation firAnnotation, Object obj) {
        m278visitAnnotation(firAnnotation, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnnotationCall(FirAnnotationCall firAnnotationCall, Object obj) {
        m279visitAnnotationCall(firAnnotationCall, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
        m282visitElement(firElement, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitRegularClass(FirRegularClass firRegularClass, Object obj) {
        m284visitRegularClass(firRegularClass, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitTypeRef(FirTypeRef firTypeRef, Object obj) {
        m287visitTypeRef(firTypeRef, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitDeclarationStatus(FirDeclarationStatus firDeclarationStatus, Object obj) {
        m281visitDeclarationStatus(firDeclarationStatus, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitNamedReference(FirNamedReference firNamedReference, Object obj) {
        m283visitNamedReference(firNamedReference, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitTypeParameterRef(FirTypeParameterRef firTypeParameterRef, Object obj) {
        m286visitTypeParameterRef(firTypeParameterRef, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitArgumentList(FirArgumentList firArgumentList, Object obj) {
        m280visitArgumentList(firArgumentList, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitResolvedTypeRef(FirResolvedTypeRef firResolvedTypeRef, Object obj) {
        m285visitResolvedTypeRef(firResolvedTypeRef, obj);
        return Unit.INSTANCE;
    }
}

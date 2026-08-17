package org.jetbrains.kotlin.asJava.elements;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.asJava.classes.ImplUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\r\u001a\u00020\u0003H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\u0013\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/LazyPsiReferenceDelegate;", "Lcom/intellij/psi/PsiReference;", "psiElement", "Lcom/intellij/psi/PsiElement;", "referenceProvider", "Lkotlin/Function0;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;Lkotlin/jvm/functions/Function0;)V", "delegate", "getDelegate", "()Lcom/intellij/psi/PsiReference;", "delegate$delegate", "Lkotlin/Lazy;", "getElement", "resolve", "getRangeInElement", "Lcom/intellij/openapi/util/TextRange;", "getCanonicalText", "", "handleElementRename", "newElementName", "bindToElement", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "isSoft", "", "isReferenceTo", "getVariants", "", "", "()[Ljava/lang/Object;", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class LazyPsiReferenceDelegate implements PsiReference {

    /* JADX INFO: renamed from: delegate$delegate, reason: from kotlin metadata */
    private final Lazy delegate;
    private final PsiElement psiElement;

    public LazyPsiReferenceDelegate(PsiElement psiElement, Function0<? extends PsiReference> function0) {
        psiElement.getClass();
        function0.getClass();
        this.psiElement = psiElement;
        this.delegate = ImplUtilsKt.lazyPub(function0);
    }

    private final PsiReference getDelegate() {
        return (PsiReference) this.delegate.getValue();
    }

    public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
        PsiElement psiElementBindToElement;
        element.getClass();
        PsiReference delegate = getDelegate();
        if (delegate != null && (psiElementBindToElement = delegate.bindToElement(element)) != null) {
            return psiElementBindToElement;
        }
        ptb.a("can't rename LazyPsiReferenceDelegate");
        return null;
    }

    public String getCanonicalText() {
        String canonicalText;
        PsiReference delegate = getDelegate();
        return (delegate == null || (canonicalText = delegate.getCanonicalText()) == null) ? "<no-text>" : canonicalText;
    }

    /* JADX INFO: renamed from: getElement, reason: from getter */
    public PsiElement getPsiElement() {
        return this.psiElement;
    }

    public TextRange getRangeInElement() {
        TextRange rangeInElement;
        PsiReference delegate = getDelegate();
        if (delegate != null && (rangeInElement = delegate.getRangeInElement()) != null) {
            return rangeInElement;
        }
        TextRange textRange = this.psiElement.getTextRange();
        textRange.getClass();
        return textRange;
    }

    public Object[] getVariants() {
        Object[] variants;
        PsiReference delegate = getDelegate();
        return (delegate == null || (variants = delegate.getVariants()) == null) ? new Object[0] : variants;
    }

    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        PsiElement psiElementHandleElementRename;
        newElementName.getClass();
        PsiReference delegate = getDelegate();
        return (delegate == null || (psiElementHandleElementRename = delegate.handleElementRename(newElementName)) == null) ? getPsiElement() : psiElementHandleElementRename;
    }

    public boolean isReferenceTo(PsiElement element) {
        element.getClass();
        PsiReference delegate = getDelegate();
        if (delegate != null) {
            return delegate.isReferenceTo(element);
        }
        return false;
    }

    public boolean isSoft() {
        PsiReference delegate = getDelegate();
        if (delegate != null) {
            return delegate.isSoft();
        }
        return false;
    }

    public PsiElement resolve() {
        PsiReference delegate = getDelegate();
        if (delegate != null) {
            return delegate.resolve();
        }
        return null;
    }
}

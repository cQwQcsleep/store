package org.jetbrains.kotlin.utils;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import java.util.Iterator;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.utils.PsiUtilsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a'\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\"\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"getElementTextWithContext", "", "psiElement", "Lcom/intellij/psi/PsiElement;", "parentOfType", "psiClassNames", "", "(Lcom/intellij/psi/PsiElement;[Ljava/lang/String;)Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class PsiUtilsKt {
    public static boolean a(PsiElement psiElement) {
        psiElement.getClass();
        return !(psiElement instanceof PsiFile);
    }

    public static PsiElement b(PsiElement psiElement) {
        psiElement.getClass();
        return psiElement.getParent();
    }

    public static final String getElementTextWithContext(final PsiElement psiElement) {
        psiElement.getClass();
        try {
            Result.Companion companion = Result.Companion;
            if (!psiElement.isValid()) {
                return "<invalid element " + psiElement + ", invalidation reason: " + PsiInvalidElementAccessException.findOutInvalidationReason(psiElement) + '>';
            }
            final String str = "ELEMENT";
            PsiElement containingFile = psiElement.getContainingFile();
            PsiElement psiElementParentOfType = parentOfType(psiElement, "KtImportDirective");
            if (psiElementParentOfType == null && (psiElementParentOfType = parentOfType(psiElement, "KtPackageDirective")) == null && (psiElementParentOfType = parentOfType(psiElement, "KtDeclarationWithBody", "KtClassOrObject", "KtScript")) == null && (psiElementParentOfType = parentOfType(psiElement, "KtProperty")) == null) {
                psiElementParentOfType = containingFile;
            }
            final StringBuilder sb = new StringBuilder();
            psiElementParentOfType.accept(new PsiElementVisitor() { // from class: org.jetbrains.kotlin.utils.PsiUtilsKt$getElementTextWithContext$1$elementTextInContext$1$1
                public void visitElement(PsiElement element) {
                    element.getClass();
                    if (element == psiElement) {
                        sb.append("<" + str + '>');
                    }
                    if (element instanceof LeafPsiElement) {
                        sb.append(((LeafPsiElement) element).getText());
                    } else {
                        element.acceptChildren(this);
                    }
                    if (element == psiElement) {
                        sb.append("</" + str + '>');
                    }
                }
            });
            String string = kotlin.text.StringsKt.trim(kotlin.text.StringsKt.trimIndent(sb.toString())).toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<File name: " + containingFile.getName() + ", Physical: " + containingFile.isPhysical() + '>');
            sb2.append('\n');
            sb2.append(string);
            return sb2.toString();
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Object obj = Result.constructor-impl(ResultKt.createFailure(th));
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                obj = "EXCEPTION: Could not get element text in context due to an exception:\n" + th2 + '\n' + ExceptionsKt.stackTraceToString(th2) + '>';
            }
            return (String) obj;
        }
    }

    private static final PsiElement parentOfType(PsiElement psiElement, String... strArr) {
        Object next;
        Iterator it = SequencesKt.filter(SequencesKt.generateSequence(psiElement, new Function1() { // from class: dub
            public final Object invoke(Object obj) {
                return PsiUtilsKt.b((PsiElement) obj);
            }
        }), new Function1() { // from class: nub
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PsiUtilsKt.a((PsiElement) obj));
            }
        }).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (parentOfType$acceptsClass(strArr, ((PsiElement) next).getClass())) {
                return (PsiElement) next;
            }
        }
        next = null;
        return (PsiElement) next;
    }

    private static final boolean parentOfType$acceptsClass(String[] strArr, Class<?> cls) {
        if (ArraysKt.contains(strArr, cls.getSimpleName())) {
            return true;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && parentOfType$acceptsClass(strArr, superclass)) {
            return true;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        interfaces.getClass();
        for (Class<?> cls2 : interfaces) {
            cls2.getClass();
            if (parentOfType$acceptsClass(strArr, cls2)) {
                return true;
            }
        }
        return false;
    }
}

package org.jetbrains.kotlin.utils.exceptions;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.utils.PsiUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachementBuilderUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\u001c\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"withPsiEntry", "", "Lorg/jetbrains/kotlin/utils/exceptions/ExceptionAttachmentBuilder;", "name", "", "psi", "Lcom/intellij/psi/PsiElement;", "withVirtualFileEntry", "virtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ExceptionAttachementBuilderUtilsKt {
    public static String a(PsiElement psiElement) {
        psiElement.getClass();
        return PsiUtilsKt.getElementTextWithContext(psiElement);
    }

    public static String b(VirtualFile virtualFile) {
        virtualFile.getClass();
        return "path: " + virtualFile.getPath() + ", filetype: " + virtualFile.getFileType() + " ,filesystem," + virtualFile.getFileSystem();
    }

    public static final void withPsiEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, PsiElement psiElement) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        exceptionAttachmentBuilder.withEntry(str, psiElement, new Function1() { // from class: fd4
            public final Object invoke(Object obj) {
                return ExceptionAttachementBuilderUtilsKt.a((PsiElement) obj);
            }
        });
    }

    public static final void withVirtualFileEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, VirtualFile virtualFile) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        exceptionAttachmentBuilder.withEntry(str, virtualFile, new Function1() { // from class: gd4
            public final Object invoke(Object obj) {
                return ExceptionAttachementBuilderUtilsKt.b((VirtualFile) obj);
            }
        });
    }
}

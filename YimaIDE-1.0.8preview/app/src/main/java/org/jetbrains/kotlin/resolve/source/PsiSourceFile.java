package org.jetbrains.kotlin.resolve.source;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/resolve/source/PsiSourceFile;", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "psiFile", "Lcom/intellij/psi/PsiFile;", "<init>", "(Lcom/intellij/psi/PsiFile;)V", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "equals", "", "other", "", "hashCode", "", "toString", "", "getName", "org.jetbrains.kotlin:psi-frontend-utils"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class PsiSourceFile implements SourceFile {
    private final PsiFile psiFile;

    public PsiSourceFile(PsiFile psiFile) {
        psiFile.getClass();
        this.psiFile = psiFile;
    }

    public boolean equals(Object other) {
        return (other instanceof PsiSourceFile) && Intrinsics.areEqual(this.psiFile, ((PsiSourceFile) other).psiFile);
    }

    public String getName() {
        VirtualFile virtualFile = this.psiFile.getVirtualFile();
        if (virtualFile != null) {
            return virtualFile.getName();
        }
        return null;
    }

    public final PsiFile getPsiFile() {
        return this.psiFile;
    }

    public int hashCode() {
        return this.psiFile.hashCode();
    }

    public String toString() {
        String path = this.psiFile.getVirtualFile().getPath();
        path.getClass();
        return path;
    }
}

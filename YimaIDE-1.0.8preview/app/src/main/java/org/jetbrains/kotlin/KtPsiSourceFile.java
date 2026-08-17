package org.jetbrains.kotlin;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/KtPsiSourceFile;", "Lorg/jetbrains/kotlin/KtSourceFile;", "psiFile", "Lcom/intellij/psi/PsiFile;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiFile;)V", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "name", "", "getName", "()Ljava/lang/String;", "path", "getPath", "getContentsAsStream", "Ljava/io/InputStream;", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KtPsiSourceFile implements KtSourceFile {
    private final PsiFile psiFile;

    public KtPsiSourceFile(PsiFile psiFile) {
        psiFile.getClass();
        this.psiFile = psiFile;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        KtPsiSourceFile ktPsiSourceFile = other instanceof KtPsiSourceFile ? (KtPsiSourceFile) other : null;
        return Intrinsics.areEqual(ktPsiSourceFile != null ? ktPsiSourceFile.psiFile : null, this.psiFile);
    }

    public InputStream getContentsAsStream() {
        InputStream inputStream = this.psiFile.getVirtualFile().getInputStream();
        inputStream.getClass();
        return inputStream;
    }

    public String getName() {
        String name = this.psiFile.getName();
        name.getClass();
        return name;
    }

    public String getPath() {
        VirtualFile virtualFile = this.psiFile.getVirtualFile();
        if (virtualFile != null) {
            return virtualFile.getPath();
        }
        return null;
    }

    public final PsiFile getPsiFile() {
        return this.psiFile;
    }

    public int hashCode() {
        return this.psiFile.hashCode();
    }
}

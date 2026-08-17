package org.jetbrains.kotlin.load.java.structure.impl.source;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementPsiSourceWithFixedPsi;", "PSI", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementPsiSource;", "psi", "<init>", "(Lcom/intellij/psi/PsiElement;)V", "getPsi", "()Lcom/intellij/psi/PsiElement;", "Lcom/intellij/psi/PsiElement;", "factory", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;", "getFactory", "()Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaElementPsiSourceWithFixedPsi<PSI extends PsiElement> extends JavaElementPsiSource<PSI> {
    private final PSI psi;

    public JavaElementPsiSourceWithFixedPsi(PSI psi) {
        psi.getClass();
        this.psi = psi;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof JavaElementPsiSourceWithFixedPsi) && Intrinsics.areEqual(getPsi(), ((JavaElementPsiSourceWithFixedPsi) other).getPsi());
    }

    @Override // org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementPsiSource
    public JavaElementSourceFactory getFactory() {
        JavaElementSourceFactory.Companion companion = JavaElementSourceFactory.INSTANCE;
        Project project = getPsi().getProject();
        project.getClass();
        return companion.getInstance(project);
    }

    @Override // org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementPsiSource
    public PSI getPsi() {
        return this.psi;
    }

    public int hashCode() {
        return getPsi().hashCode();
    }

    public String toString() {
        return getPsi().toString();
    }
}

package org.jetbrains.kotlin.ir.declarations;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "isConst", "", "()Z", "psi", "Lcom/intellij/psi/PsiElement;", "getPsi", "()Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface MetadataSource$Property extends MetadataSource {
    PsiElement getPsi();

    boolean isConst();
}

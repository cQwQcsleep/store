package org.jetbrains.kotlin.load.java.structure.impl.source;

import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.impl.file.PsiPackageImpl;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/impl/source/SingleFileRootPsiPackage;", "Lcom/intellij/psi/impl/file/PsiPackageImpl;", "manager", "Lcom/intellij/psi/PsiManager;", "qualifiedName", Argument.Delimiters.none, "annotationsList", "Lcom/intellij/psi/PsiModifierList;", "<init>", "(Lcom/intellij/psi/PsiManager;Ljava/lang/String;Lcom/intellij/psi/PsiModifierList;)V", "isValid", Argument.Delimiters.none, "getAnnotationList", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SingleFileRootPsiPackage extends PsiPackageImpl {
    private final PsiModifierList annotationsList;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleFileRootPsiPackage(PsiManager psiManager, String str, PsiModifierList psiModifierList) {
        super(psiManager, str);
        psiManager.getClass();
        str.getClass();
        this.annotationsList = psiModifierList;
    }

    /* JADX INFO: renamed from: getAnnotationList, reason: from getter */
    public PsiModifierList getAnnotationsList() {
        return this.annotationsList;
    }

    public boolean isValid() {
        return true;
    }
}

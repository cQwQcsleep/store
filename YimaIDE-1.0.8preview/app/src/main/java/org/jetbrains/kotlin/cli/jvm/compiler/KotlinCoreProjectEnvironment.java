package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.core.CoreProjectEnvironment;
import com.intellij.core.JavaCoreProjectEnvironment;
import com.intellij.openapi.Disposable;
import com.intellij.psi.PsiManager;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.KotlinCliJavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreProjectEnvironment;", "Lcom/intellij/core/JavaCoreProjectEnvironment;", "disposable", "Lcom/intellij/openapi/Disposable;", "applicationEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;", "<init>", "(Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;)V", "createCoreFileManager", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class KotlinCoreProjectEnvironment extends JavaCoreProjectEnvironment {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinCoreProjectEnvironment(Disposable disposable, KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironment) {
        super(disposable, kotlinCoreApplicationEnvironment);
        disposable.getClass();
        kotlinCoreApplicationEnvironment.getClass();
    }

    public KotlinCliJavaFileManager createCoreFileManager() {
        PsiManager psiManager = PsiManager.getInstance(((CoreProjectEnvironment) this).project);
        psiManager.getClass();
        return new KotlinCliJavaFileManagerImpl(psiManager);
    }
}

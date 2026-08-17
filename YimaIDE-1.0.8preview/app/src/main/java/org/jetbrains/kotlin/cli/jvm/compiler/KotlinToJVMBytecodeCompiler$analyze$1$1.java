package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class KotlinToJVMBytecodeCompiler$analyze$1$1 extends FunctionReferenceImpl implements Function1<GlobalSearchScope, JvmPackagePartProvider> {
    public KotlinToJVMBytecodeCompiler$analyze$1$1(Object obj) {
        super(1, obj, KotlinCoreEnvironment.class, "createPackagePartProvider", "createPackagePartProvider(Lcom/intellij/psi/search/GlobalSearchScope;)Lorg/jetbrains/kotlin/cli/jvm/compiler/JvmPackagePartProvider;", 0);
    }

    public final JvmPackagePartProvider invoke(GlobalSearchScope globalSearchScope) {
        globalSearchScope.getClass();
        return ((KotlinCoreEnvironment) ((CallableReference) this).receiver).createPackagePartProvider(globalSearchScope);
    }
}

package org.jetbrains.kotlin.resolve.jvm;

import com.intellij.psi.impl.file.impl.JavaFileManager;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.load.java.JavaClassFinder;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;", "Lcom/intellij/psi/impl/file/impl/JavaFileManager;", "findClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "request", "Lorg/jetbrains/kotlin/load/java/JavaClassFinder$Request;", "searchScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "knownClassNamesInPackage", "", "", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface KotlinCliJavaFileManager extends JavaFileManager {
    JavaClass findClass(JavaClassFinder.Request request, GlobalSearchScope searchScope);

    Set<String> knownClassNamesInPackage(FqName packageFqName);
}

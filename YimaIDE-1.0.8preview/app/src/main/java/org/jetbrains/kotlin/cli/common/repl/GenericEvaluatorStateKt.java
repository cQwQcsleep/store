package org.jetbrains.kotlin.cli.common.repl;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¨\u0006\u0007"}, d2 = {"makeReplClassLoader", "Lorg/jetbrains/kotlin/cli/common/repl/ReplClassLoader;", "baseClassloader", "Ljava/lang/ClassLoader;", "baseClasspath", Argument.Delimiters.none, "Ljava/io/File;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GenericEvaluatorStateKt {
    public static final ReplClassLoader makeReplClassLoader(ClassLoader classLoader, Iterable<? extends File> iterable) {
        iterable.getClass();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<? extends File> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toURI().toURL());
        }
        return new ReplClassLoader(new URLClassLoader((URL[]) arrayList.toArray(new URL[0]), classLoader));
    }
}

package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0007b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¨\u0006\r"}, d2 = {"extractKotlinSourcesFromFreeCompilerArguments", "", "Ljava/io/File;", "compilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "kotlinFilenameExtensions", "", "", "includeJavaSources", "", "Lkotlin/Deprecated;", "message", "Temporary function to reuse the logic. KT-62759", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IncrementalCompilerRunnerKt {
    @Deprecated(message = "Temporary function to reuse the logic. KT-62759")
    public static final List<File> extractKotlinSourcesFromFreeCompilerArguments(CommonCompilerArguments commonCompilerArguments, Set<String> set, boolean z) {
        commonCompilerArguments.getClass();
        set.getClass();
        Set<String> set2 = set;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set2, 10));
        Iterator<T> it = set2.iterator();
        while (it.hasNext()) {
            arrayList.add("." + ((String) it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (String str : commonCompilerArguments.getFreeArgs()) {
            File file = new File(str);
            if (file.isFile()) {
                if (!arrayList.isEmpty()) {
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            String str2 = (String) it2.next();
                            String path = file.getPath();
                            path.getClass();
                            if (StringsKt.endsWith(path, str2, true)) {
                                arrayList3.add(file);
                            }
                        }
                    }
                }
                if (z) {
                    String path2 = file.getPath();
                    path2.getClass();
                    if (StringsKt.endsWith(path2, ".java", true)) {
                        arrayList3.add(file);
                        arrayList2.add(str);
                    }
                }
                arrayList2.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        commonCompilerArguments.setFreeArgs(arrayList2);
        return arrayList3;
    }
}

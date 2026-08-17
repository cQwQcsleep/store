package org.jetbrains.kotlin.js.sourceMap;

import java.io.File;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.js.sourceMap.RelativePathCalculator;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007*\u00020\u0003H\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0003R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/js/sourceMap/RelativePathCalculator;", "", "baseDir", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "getAllParents", "", "baseDirPath", "calculateRelativePathTo", "", "file", "org.jetbrains.kotlin:js.sourcemap"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class RelativePathCalculator {
    private final List<File> baseDirPath;

    public RelativePathCalculator(File file) {
        file.getClass();
        this.baseDirPath = getAllParents(file);
    }

    public static File a(File file) {
        file.getClass();
        return file.getParentFile();
    }

    private final List<File> getAllParents(File file) {
        File absoluteFile = file.getAbsoluteFile();
        absoluteFile.getClass();
        return CollectionsKt.asReversed(SequencesKt.toList(SequencesKt.generateSequence(FilesKt.normalize(absoluteFile), new Function1() { // from class: wac
            public final Object invoke(Object obj) {
                return RelativePathCalculator.a((File) obj);
            }
        })));
    }

    public final String calculateRelativePathTo(File file) {
        file.getClass();
        List<File> allParents = getAllParents(file);
        int i = 0;
        if (!Intrinsics.areEqual(this.baseDirPath.get(0), allParents.get(0))) {
            return null;
        }
        List<Pair> listZip = CollectionsKt.zip(this.baseDirPath, allParents);
        if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
            for (Pair pair : listZip) {
                if (Intrinsics.areEqual((File) pair.component1(), (File) pair.component2()) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int size = this.baseDirPath.size();
        for (int i2 = i; i2 < size; i2++) {
            sb.append("../");
        }
        int size2 = allParents.size();
        while (i < size2) {
            sb.append(allParents.get(i).getName());
            sb.append(AbiQualifiedName.SEPARATOR);
            i++;
        }
        sb.setLength(StringsKt.getLastIndex(sb));
        return sb.toString();
    }
}

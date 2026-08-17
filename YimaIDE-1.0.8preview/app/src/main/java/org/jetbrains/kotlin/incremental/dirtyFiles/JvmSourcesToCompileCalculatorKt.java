package org.jetbrains.kotlin.incremental.dirtyFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.incremental.ChangedFiles;
import org.jetbrains.kotlin.incremental.LookupSymbol;
import org.jetbrains.kotlin.incremental.UtilsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"processLookupSymbolsForAndroidLayouts", "", "Lorg/jetbrains/kotlin/incremental/LookupSymbol;", "changedFiles", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles$Known;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmSourcesToCompileCalculatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection<LookupSymbol> processLookupSymbolsForAndroidLayouts(ChangedFiles.DeterminableFiles.Known known) {
        ArrayList arrayList = new ArrayList();
        for (File file : CollectionsKt.plus(known.getModified(), known.getRemoved())) {
            String lowerCase = FilesKt.getExtension(file).toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            if (Intrinsics.areEqual(lowerCase, "xml")) {
                String name = file.getName();
                name.getClass();
                arrayList.add(new LookupSymbol(UtilsKt.ANDROID_LAYOUT_CONTENT_LOOKUP_NAME, StringsKt.substringBeforeLast$default(name, AbiCompoundName.SEPARATOR, (String) null, 2, (Object) null)));
            }
        }
        return arrayList;
    }
}

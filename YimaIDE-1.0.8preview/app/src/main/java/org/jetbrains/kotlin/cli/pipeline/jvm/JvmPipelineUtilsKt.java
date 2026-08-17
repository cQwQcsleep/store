package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\u0004"}, d2 = {"asKtFilesList", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "Lorg/jetbrains/kotlin/KtSourceFile;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmPipelineUtilsKt {
    public static final List<KtFile> asKtFilesList(List<? extends KtSourceFile> list) {
        list.getClass();
        List<? extends KtSourceFile> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            KtPsiSourceFile ktPsiSourceFile = (KtSourceFile) it.next();
            ktPsiSourceFile.getClass();
            KtFile psiFile = ktPsiSourceFile.getPsiFile();
            psiFile.getClass();
            arrayList.add(psiFile);
        }
        return arrayList;
    }
}

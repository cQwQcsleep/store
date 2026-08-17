package org.jetbrains.kotlin.fir.builder;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004\u001a5\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0005H\u0007b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"toMutableOrEmpty", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "T", Argument.Delimiters.none, "(Ljava/util/List;)Ljava/util/List;", Argument.Delimiters.none, "toMutableOrEmptyForImmutable", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBuilderDslKt {
    public static final <T> List<T> toMutableOrEmpty(List<T> list) {
        List<T> list2 = list;
        return (list2 == null || list2.isEmpty()) ? MutableOrEmptyList.INSTANCE.m213empty5e3fPpI() : MutableOrEmptyList.m196constructorimpl(list);
    }

    public static final <T> List<T> toMutableOrEmptyForImmutable(List<? extends T> list) {
        List<? extends T> list2 = list;
        return (list2 == null || list2.isEmpty()) ? MutableOrEmptyList.INSTANCE.m213empty5e3fPpI() : MutableOrEmptyList.m196constructorimpl(CollectionsKt.toMutableList(list2));
    }
}

package kotlin.reflect;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.library.SearchPathResolverKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0006\b\u0005\u0012\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lkotlin/reflect/KParameter$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "INSTANCE", "CONTEXT", "Lkotlin/ExperimentalContextParameters;", "EXTENSION_RECEIVER", "VALUE", SearchPathResolverKt.KOTLIN_JKLIB_STDLIB_NAME}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum KParameter$Kind {
    INSTANCE,
    CONTEXT,
    EXTENSION_RECEIVER,
    VALUE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<KParameter$Kind> getEntries() {
        return $ENTRIES;
    }
}

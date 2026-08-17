package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/ItemKind;", "", "<init>", "(Ljava/lang/String;I)V", "Open", "Close", "ResultPrefix", "AnyParameters", "Token", "Number", "End", "Invalid", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
enum ItemKind {
    Open,
    Close,
    ResultPrefix,
    AnyParameters,
    Token,
    Number,
    End,
    Invalid;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ItemKind> getEntries() {
        return $ENTRIES;
    }
}

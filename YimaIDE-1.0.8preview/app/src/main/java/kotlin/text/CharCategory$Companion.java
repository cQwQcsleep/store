package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004¨\u0006\b"}, d2 = {"Lkotlin/text/CharCategory$Companion;", HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "()V", "valueOf", "Lkotlin/text/CharCategory;", "category", HttpUrl.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CharCategory$Companion {
    public /* synthetic */ CharCategory$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final CharCategory valueOf(int category) {
        if (category >= 0 && category < 17) {
            return (CharCategory) CharCategory.getEntries().get(category);
        }
        if (18 <= category && category < 31) {
            return (CharCategory) CharCategory.getEntries().get(category - 1);
        }
        ty8.a("Category #", category, " is not defined.");
        return null;
    }

    private CharCategory$Companion() {
    }
}

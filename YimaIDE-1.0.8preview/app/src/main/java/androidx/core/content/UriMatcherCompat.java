package androidx.core.content;

import android.content.UriMatcher;
import android.net.Uri;
import androidx.core.content.UriMatcherCompat;
import androidx.core.util.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class UriMatcherCompat {
    private UriMatcherCompat() {
    }

    public static /* synthetic */ boolean a(UriMatcher uriMatcher, Uri uri) {
        return uriMatcher.match(uri) != -1;
    }

    public static Predicate<Uri> asPredicate(final UriMatcher uriMatcher) {
        return new Predicate() { // from class: k1f
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return UriMatcherCompat.a(uriMatcher, (Uri) obj);
            }
        };
    }
}

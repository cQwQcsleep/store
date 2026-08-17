package defpackage;

import com.reandroid.archive.ArchiveEntry;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final /* synthetic */ class ee0 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((ArchiveEntry) obj).isFile();
    }
}

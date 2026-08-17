package defpackage;

import com.android.tools.r8.ArchiveProgramResourceProvider;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class he0 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ArchiveProgramResourceProvider.includeClassFileOrDexEntries((String) obj);
    }
}

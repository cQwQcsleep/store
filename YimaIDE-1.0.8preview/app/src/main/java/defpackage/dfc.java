package defpackage;

import com.reandroid.arsc.chunk.xml.ResXmlElement;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class dfc implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((ResXmlElement) obj).isUndefined();
    }
}

package org.jetbrains.kotlin.library;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¨\u0006\u0005"}, d2 = {"parseIrSignatureVersions", "", "Lorg/jetbrains/kotlin/library/KotlinIrSignatureVersion;", "", "toManifestValue", "kotlin-util-klib"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KotlinIrSignatureVersionKt {
    public static final Set<KotlinIrSignatureVersion> parseIrSignatureVersions(String str) {
        str.getClass();
        List listSplit$default = StringsKt.split$default(str, new String[]{","}, false, 0, 6, (Object) null);
        HashSet hashSet = new HashSet();
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            hashSet.add(new KotlinIrSignatureVersion(Integer.parseInt((String) it.next())));
        }
        return hashSet;
    }

    public static final String toManifestValue(Set<KotlinIrSignatureVersion> set) {
        set.getClass();
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(set, new Comparator() { // from class: org.jetbrains.kotlin.library.KotlinIrSignatureVersionKt$toManifestValue$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((KotlinIrSignatureVersion) t).getNumber()), Integer.valueOf(((KotlinIrSignatureVersion) t2).getNumber()));
            }
        }), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1<KotlinIrSignatureVersion, CharSequence>() { // from class: org.jetbrains.kotlin.library.KotlinIrSignatureVersionKt.toManifestValue.2
            public final CharSequence invoke(KotlinIrSignatureVersion kotlinIrSignatureVersion) {
                kotlinIrSignatureVersion.getClass();
                return String.valueOf(kotlinIrSignatureVersion.getNumber());
            }
        }, 30, (Object) null);
    }
}

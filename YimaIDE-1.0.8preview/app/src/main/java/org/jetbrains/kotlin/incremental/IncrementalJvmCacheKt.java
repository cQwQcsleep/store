package org.jetbrains.kotlin.incremental;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a@\u0010\u0005\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u0007\"\u0004\b\u0001\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b0\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\u000b\u001a \u0010\f\u001a\u00020\u0001\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0007*\b\u0012\u0004\u0012\u0002H\r0\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"KOTLIN_CACHE_DIRECTORY_NAME", "", "md5", "", "", "dumpMap", "K", "", "V", "", "dumpValue", "Lkotlin/Function1;", "dumpCollection", "T", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class IncrementalJvmCacheKt {
    public static final String KOTLIN_CACHE_DIRECTORY_NAME = "kotlin";

    /* JADX INFO: renamed from: org.jetbrains.kotlin.incremental.IncrementalJvmCacheKt$dumpCollection$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Object, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, Object.class, "toString", "toString()Ljava/lang/String;", 0);
        }

        public final String invoke(Object obj) {
            obj.getClass();
            return obj.toString();
        }
    }

    public static final <T extends Comparable<? super T>> String dumpCollection(Collection<? extends T> collection) {
        collection.getClass();
        return "[" + CollectionsKt.joinToString$default(CollectionsKt.sorted(collection), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, AnonymousClass1.INSTANCE, 30, (Object) null) + ']';
    }

    public static final <K extends Comparable<? super K>, V> String dumpMap(Map<K, ? extends V> map, Function1<? super V, String> function1) {
        String str;
        map.getClass();
        function1.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Comparable comparable : CollectionsKt.sorted(map.keySet())) {
            if (sb.length() != 1) {
                sb.append(", ");
            }
            V v = map.get(comparable);
            if (v == null || (str = (String) function1.invoke(v)) == null) {
                str = "null";
            }
            sb.append(comparable + " -> " + str);
        }
        sb.append("}");
        return sb.toString();
    }

    public static final long md5(byte[] bArr) {
        bArr.getClass();
        byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(bArr);
        bArrDigest.getClass();
        return (((long) bArrDigest[0]) & 255) | ((((long) bArrDigest[1]) & 255) << 8) | ((((long) bArrDigest[2]) & 255) << 16) | ((((long) bArrDigest[3]) & 255) << 24) | ((((long) bArrDigest[4]) & 255) << 32) | ((((long) bArrDigest[5]) & 255) << 40) | ((((long) bArrDigest[6]) & 255) << 48) | ((255 & ((long) bArrDigest[7])) << 56);
    }
}

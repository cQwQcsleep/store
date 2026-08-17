package org.jetbrains.kotlin.util.slicedMap;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\t\u001a\u00020\u0001H\u0082\b\u001a7\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0011"}, d2 = {"MAGIC", "", "MAX_SHIFT", "THRESHOLD", "EMPTY_ARRAY", "", "", "[Ljava/lang/Object;", "computeHash", "shift", "put", "", "array", "aShift", "key", "value", "([Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;)Z", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class OpenAddressLinearProbingHashTableKt {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean put(Object[] objArr, int i, Object obj, Object obj2) {
        int iHashCode = ((obj.hashCode() * (-1640531527)) >>> i) << 1;
        while (true) {
            Object obj3 = objArr[iHashCode];
            if (obj3 == null) {
                objArr[iHashCode] = obj;
                objArr[iHashCode + 1] = obj2;
                return true;
            }
            if (Intrinsics.areEqual(obj3, obj)) {
                objArr[iHashCode + 1] = obj2;
                return false;
            }
            if (iHashCode == 0) {
                iHashCode = objArr.length;
            }
            iHashCode -= 2;
        }
    }
}

package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import org.bouncycastle.crypto.hpke.HPKE;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0003b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0004\b\u0003\u0010\u0004\u001a7\u0010\u0000\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0004\b\u000b\u0010\f\u001a7\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\r0\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000eb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0004\b\u000e\u0010\u0004\u001a7\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u000f0\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0004\b\u0010\u0010\u0004¨\u0006\u0011"}, d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "sumOfUInt", "(Lkotlin/sequences/Sequence;)I", "Lkotlin/jvm/JvmName;", "name", "Lkotlin/SinceKotlin;", "version", "1.5", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UByte;", "sumOfUByte", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = 49, xs = "kotlin/sequences/USequencesKt")
class USequencesKt___USequencesKt {
    public static final int sumOfUByte(Sequence<UByte> sequence) {
        sequence.getClass();
        Iterator<UByte> it = sequence.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = UInt.constructor-impl(i + UInt.constructor-impl(it.next().unbox-impl() & 255));
        }
        return i;
    }

    public static final int sumOfUInt(Sequence<UInt> sequence) {
        sequence.getClass();
        Iterator<UInt> it = sequence.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = UInt.constructor-impl(i + it.next().unbox-impl());
        }
        return i;
    }

    public static final long sumOfULong(Sequence<ULong> sequence) {
        sequence.getClass();
        Iterator<ULong> it = sequence.iterator();
        long j = 0;
        while (it.hasNext()) {
            j = ULong.constructor-impl(j + it.next().unbox-impl());
        }
        return j;
    }

    public static final int sumOfUShort(Sequence<UShort> sequence) {
        sequence.getClass();
        Iterator<UShort> it = sequence.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = UInt.constructor-impl(i + UInt.constructor-impl(it.next().unbox-impl() & HPKE.aead_EXPORT_ONLY));
        }
        return i;
    }
}

package kotlin.collections;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a+\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007b\u0002\b\b¢\u0006\u0002\u0010\u0004\u001a+\u0010\t\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u000b0\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007b\u0002\b\b¢\u0006\u0002\u0010\f\u001a+\u0010\r\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u000f0\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007b\u0002\b\b¢\u0006\u0002\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00130\u0002H\u0087\u0080\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007b\u0002\b\b¢\u0006\u0002\u0010\u0014\u001a7\u0010\u0015\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u0087\u0080\u0004b\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u0017b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b¢\u0006\u0004\b\u0017\u0010\u0018\u001a7\u0010\u0015\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u000f0\u0016H\u0087\u0080\u0004b\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001cb\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b¢\u0006\u0004\b\u001c\u0010\u001d\u001a7\u0010\u0015\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0087\u0080\u0004b\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001eb\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b¢\u0006\u0004\b\u001e\u0010\u0018\u001a7\u0010\u0015\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0087\u0080\u0004b\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001fb\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b¢\u0006\u0004\b\u001f\u0010\u0018¨\u0006 "}, d2 = {"toUByteArray", "Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "(Ljava/util/Collection;)[B", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/ExperimentalUnsignedTypes;", "toUIntArray", "Lkotlin/UIntArray;", "Lkotlin/UInt;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "Lkotlin/ULong;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "Lkotlin/UShort;", "(Ljava/util/Collection;)[S", "sum", "", "sumOfUInt", "(Ljava/lang/Iterable;)I", "Lkotlin/jvm/JvmName;", "name", "1.5", "sumOfULong", "(Ljava/lang/Iterable;)J", "sumOfUByte", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/collections/UCollectionsKt")
class UCollectionsKt___UCollectionsKt {
    public static final int sumOfUByte(Iterable<UByte> iterable) {
        iterable.getClass();
        Iterator<UByte> it2 = iterable.iterator();
        int iM133constructorimpl = 0;
        while (it2.hasNext()) {
            iM133constructorimpl = UInt.m133constructorimpl(iM133constructorimpl + UInt.m133constructorimpl(it2.next().getData() & UByte.MAX_VALUE));
        }
        return iM133constructorimpl;
    }

    public static final int sumOfUInt(Iterable<UInt> iterable) {
        iterable.getClass();
        Iterator<UInt> it2 = iterable.iterator();
        int iM133constructorimpl = 0;
        while (it2.hasNext()) {
            iM133constructorimpl = UInt.m133constructorimpl(iM133constructorimpl + it2.next().getData());
        }
        return iM133constructorimpl;
    }

    public static final long sumOfULong(Iterable<ULong> iterable) {
        iterable.getClass();
        Iterator<ULong> it2 = iterable.iterator();
        long jM212constructorimpl = 0;
        while (it2.hasNext()) {
            jM212constructorimpl = ULong.m212constructorimpl(jM212constructorimpl + it2.next().getData());
        }
        return jM212constructorimpl;
    }

    public static final int sumOfUShort(Iterable<UShort> iterable) {
        iterable.getClass();
        Iterator<UShort> it2 = iterable.iterator();
        int iM133constructorimpl = 0;
        while (it2.hasNext()) {
            iM133constructorimpl = UInt.m133constructorimpl(iM133constructorimpl + UInt.m133constructorimpl(it2.next().getData() & UShort.MAX_VALUE));
        }
        return iM133constructorimpl;
    }

    public static final byte[] toUByteArray(Collection<UByte> collection) {
        collection.getClass();
        byte[] bArrM108constructorimpl = UByteArray.m108constructorimpl(collection.size());
        Iterator<UByte> it2 = collection.iterator();
        int i = 0;
        while (it2.hasNext()) {
            UByteArray.m119setVurrAj0(bArrM108constructorimpl, i, it2.next().getData());
            i++;
        }
        return bArrM108constructorimpl;
    }

    public static final int[] toUIntArray(Collection<UInt> collection) {
        collection.getClass();
        int[] iArrM187constructorimpl = UIntArray.m187constructorimpl(collection.size());
        Iterator<UInt> it2 = collection.iterator();
        int i = 0;
        while (it2.hasNext()) {
            UIntArray.m198setVXSXFK8(iArrM187constructorimpl, i, it2.next().getData());
            i++;
        }
        return iArrM187constructorimpl;
    }

    public static final long[] toULongArray(Collection<ULong> collection) {
        collection.getClass();
        long[] jArrM266constructorimpl = ULongArray.m266constructorimpl(collection.size());
        Iterator<ULong> it2 = collection.iterator();
        int i = 0;
        while (it2.hasNext()) {
            ULongArray.m277setk8EXiF4(jArrM266constructorimpl, i, it2.next().getData());
            i++;
        }
        return jArrM266constructorimpl;
    }

    public static final short[] toUShortArray(Collection<UShort> collection) {
        collection.getClass();
        short[] sArrM371constructorimpl = UShortArray.m371constructorimpl(collection.size());
        Iterator<UShort> it2 = collection.iterator();
        int i = 0;
        while (it2.hasNext()) {
            UShortArray.m382set01HTLdE(sArrM371constructorimpl, i, it2.next().getData());
            i++;
        }
        return sArrM371constructorimpl;
    }
}

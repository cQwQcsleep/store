package org.jetbrains.kotlin.backend.common.serialization;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;
import org.jetbrains.kotlin.backend.jvm.JvmSyntheticAccessorGenerator;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\b\u001a\u00020\tH\u0096\u0080\u0004¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004¢\u0006\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/FingerprintHash;", "", "hash", "Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "constructor-impl", "(Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;)Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "getHash", "()Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "toString", "", "toString-impl", "(Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;)Ljava/lang/String;", "toByteArray", "", "toByteArray-impl", "(Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;)[B", "equals", "", "other", "equals-impl", "(Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;)I", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class FingerprintHash {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Hash128Bits hash;

    private /* synthetic */ FingerprintHash(Hash128Bits hash128Bits) {
        this.hash = hash128Bits;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FingerprintHash m217boximpl(Hash128Bits hash128Bits) {
        return new FingerprintHash(hash128Bits);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static Hash128Bits m218constructorimpl(Hash128Bits hash128Bits) {
        hash128Bits.getClass();
        return hash128Bits;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m219equalsimpl(Hash128Bits hash128Bits, Object obj) {
        return (obj instanceof FingerprintHash) && Intrinsics.areEqual(hash128Bits, ((FingerprintHash) obj).m224unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m220equalsimpl0(Hash128Bits hash128Bits, Hash128Bits hash128Bits2) {
        return Intrinsics.areEqual(hash128Bits, hash128Bits2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m221hashCodeimpl(Hash128Bits hash128Bits) {
        return hash128Bits.hashCode();
    }

    /* JADX INFO: renamed from: toByteArray-impl, reason: not valid java name */
    public static final byte[] m222toByteArrayimpl(Hash128Bits hash128Bits) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        byteBufferAllocate.putLong(hash128Bits.m233getLowBytessVKNKU());
        byteBufferAllocate.putLong(8, hash128Bits.m232getHighBytessVKNKU());
        byte[] bArrArray = byteBufferAllocate.array();
        bArrArray.getClass();
        return bArrArray;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m223toStringimpl(Hash128Bits hash128Bits) {
        return UStringsKt.toString-JSWoG40(hash128Bits.m233getLowBytessVKNKU(), 36) + '.' + UStringsKt.toString-JSWoG40(hash128Bits.m232getHighBytessVKNKU(), 36);
    }

    public boolean equals(Object obj) {
        return m219equalsimpl(this.hash, obj);
    }

    public final Hash128Bits getHash() {
        return this.hash;
    }

    public int hashCode() {
        return m221hashCodeimpl(this.hash);
    }

    public String toString() {
        return m223toStringimpl(this.hash);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Hash128Bits m224unboximpl() {
        return this.hash;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/FingerprintHash$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "HASH_SEPARATOR", "", "fromString", "Lorg/jetbrains/kotlin/backend/common/serialization/FingerprintHash;", JvmSyntheticAccessorGenerator.SUPER_QUALIFIER_SUFFIX_MARKER, "fromString-LA3xOQI", "(Ljava/lang/String;)Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "fromByteArray", "bytes", "", "fromByteArray-uGak_n4", "([B)Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: fromByteArray-uGak_n4, reason: not valid java name */
        public final Hash128Bits m225fromByteArrayuGak_n4(byte[] bytes) {
            bytes.getClass();
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bytes);
            return FingerprintHash.m218constructorimpl(new Hash128Bits(ULong.constructor-impl(byteBufferWrap.getLong(0)), ULong.constructor-impl(byteBufferWrap.getLong(8)), null));
        }

        /* JADX INFO: renamed from: fromString-LA3xOQI, reason: not valid java name */
        public final Hash128Bits m226fromStringLA3xOQI(String s) {
            s.getClass();
            List listSplit$default = StringsKt.split$default(s, new String[]{"."}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList();
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                ULong uLongOrNull = UStringsKt.toULongOrNull((String) it.next(), 36);
                if (uLongOrNull != null) {
                    arrayList.add(uLongOrNull);
                }
            }
            if (arrayList.size() != 2) {
                arrayList = null;
            }
            if (arrayList != null) {
                return FingerprintHash.m218constructorimpl(new Hash128Bits(((ULong) arrayList.get(0)).unbox-impl(), ((ULong) arrayList.get(1)).unbox-impl(), null));
            }
            return null;
        }

        private Companion() {
        }
    }
}

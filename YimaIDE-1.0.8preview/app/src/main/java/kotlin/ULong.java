package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.jvm.JvmInline;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0087@\u0018\u0000 \u0084\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0084\u0001B\u0019\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0002\b\u0006\u001a\u0002\b\u0007¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0011H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0014H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0097\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u0019\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u0019\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u001c\u0010\u001dJ!\u0010\u0019\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0014H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\u001e\u0010\u001fJ!\u0010\u0019\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b \u0010!J!\u0010\"\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b#\u0010\u001bJ!\u0010\"\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b$\u0010\u001dJ!\u0010\"\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0014H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b%\u0010\u001fJ!\u0010\"\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b&\u0010!J!\u0010'\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b(\u0010\u001bJ!\u0010'\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b)\u0010\u001dJ!\u0010'\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0014H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b*\u0010\u001fJ!\u0010'\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b+\u0010!J!\u0010,\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b-\u0010\u001bJ!\u0010,\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b.\u0010\u001dJ!\u0010,\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0014H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b/\u0010\u001fJ!\u0010,\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b0\u0010!J!\u00101\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b2\u0010\u001bJ!\u00101\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b3\u0010\u001dJ!\u00101\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0014H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b4\u0010\u001fJ!\u00101\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b5\u0010!J!\u00106\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b7\u0010\u001bJ!\u00106\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b8\u0010\u001dJ!\u00106\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0014H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b9\u0010\u001fJ!\u00106\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b:\u0010!J!\u0010;\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b<\u0010=J!\u0010;\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0011H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b>\u0010?J!\u0010;\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u0014H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b@\u0010\u0016J!\u0010;\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bA\u0010!J\u0015\u0010B\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010¢\u0006\u0004\bC\u0010\u0005J\u0015\u0010D\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010¢\u0006\u0004\bE\u0010\u0005J\u001d\u0010F\u001a\u00020G2\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\u0002\b\u0010¢\u0006\u0004\bH\u0010IJ=\u0010J\u001a\u00020G2\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008a\u0004b\f\bL\u0012\b\bM\u0012\u0004\b\b(Nb\u0010\bO\u0012\f\bP\u0012\b\b\fJ\u0004\b\t0Qb\u0002\b\u0010¢\u0006\u0004\bK\u0010IJ!\u0010R\u001a\u00020\u00002\u0006\u0010S\u001a\u00020\u000bH\u0087\u008c\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bT\u0010\u001fJ!\u0010U\u001a\u00020\u00002\u0006\u0010S\u001a\u00020\u000bH\u0087\u008c\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bV\u0010\u001fJ!\u0010W\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008c\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bX\u0010!J!\u0010Y\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008c\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bZ\u0010!J!\u0010[\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0087\u008c\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b\\\u0010!J\u0019\u0010]\u001a\u00020\u0000H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\b^\u0010\u0005J\u0019\u0010_\u001a\u00020`H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\ba\u0010bJ\u0019\u0010c\u001a\u00020dH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\be\u0010fJ\u0019\u0010g\u001a\u00020\u000bH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bh\u0010iJ\u0019\u0010j\u001a\u00020\u0003H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bk\u0010\u0005J\u0019\u0010l\u001a\u00020\rH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bm\u0010bJ\u0019\u0010n\u001a\u00020\u0011H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bo\u0010fJ\u0019\u0010p\u001a\u00020\u0014H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bq\u0010iJ\u0019\u0010r\u001a\u00020\u0000H\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bs\u0010\u0005J\u0019\u0010t\u001a\u00020uH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bv\u0010wJ\u0019\u0010x\u001a\u00020yH\u0087\u0088\u0004b\u0002\b\u0010b\u0002\b\u0006¢\u0006\u0004\bz\u0010{J\u0015\u0010|\u001a\u00020}H\u0097\u0080\u0004b\u0002\b\u0006¢\u0006\u0004\b~\u0010\u007fJ\u0017\u0010\u0080\u0001\u001a\u00030\u0081\u00012\t\u0010\f\u001a\u0005\u0018\u00010\u0082\u0001HÖ\u0083\u0004J\u000b\u0010\u0083\u0001\u001a\u00020\u000bHÖ\u0081\u0004R\u001b\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0084\br\u0002\b\u0007¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\t\u0088\u0001\u0002\u0092\u0001\u00020\u0003Ê\u0001\r\bL\u0012\t\bM\u0012\u0005\b\b(\u0086\u0001Ê\u0001\u0003\b\u0087\u0001¨\u0006\u0085\u0001"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "Lkotlin/internal/IntrinsicConstEvaluation;", "Lkotlin/PublishedApi;", "getData$annotations", "()V", "compareTo", "", "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/internal/InlineOnly;", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "plus", "plus-7apg3OU", "(JB)J", "plus-xj2QHRw", "(JS)J", "plus-WZ4Q5Ns", "(JI)J", "plus-VKZWuLQ", "(JJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(JB)B", "mod-xj2QHRw", "(JS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-s-VKNKU", "dec", "dec-s-VKNKU", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeUntil", "rangeUntil-VKZWuLQ", "Lkotlin/SinceKotlin;", "version", "1.9", "Lkotlin/WasExperimental;", "markerClass", "Lkotlin/ExperimentalStdlibApi;", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "and", "and-VKZWuLQ", "or", "or-VKZWuLQ", "xor", "xor-VKZWuLQ", "inv", "inv-s-VKNKU", "toByte", "", "toByte-impl", "(J)B", "toShort", "", "toShort-impl", "(J)S", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(J)F", "toDouble", "", "toDouble-impl", "(J)D", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib", "1.5", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
@JvmInline
public final class ULong implements Comparable<ULong> {
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* JADX INFO: renamed from: and-VKZWuLQ, reason: not valid java name */
    private static final long m205andVKZWuLQ(long j, long j2) {
        return m212constructorimpl(j & j2);
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ULong m206boximpl(long j) {
        return new ULong(j);
    }

    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m207compareTo7apg3OU(long j, byte b) {
        return Long.compareUnsigned(j, m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private int m208compareToVKZWuLQ(long j) {
        return UnsignedKt.ulongCompare(getData(), j);
    }

    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m210compareToWZ4Q5Ns(long j, int i) {
        return Long.compareUnsigned(j, m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m211compareToxj2QHRw(long j, short s) {
        return Long.compareUnsigned(j, m212constructorimpl(((long) s) & 65535));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m212constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: dec-s-VKNKU, reason: not valid java name */
    private static final long m213decsVKNKU(long j) {
        return m212constructorimpl(j - 1);
    }

    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    private static final long m214div7apg3OU(long j, byte b) {
        return Long.divideUnsigned(j, m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m215divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m391ulongDivideeb3DHEI(j, j2);
    }

    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final long m216divWZ4Q5Ns(long j, int i) {
        return Long.divideUnsigned(j, m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    private static final long m217divxj2QHRw(long j, short s) {
        return Long.divideUnsigned(j, m212constructorimpl(((long) s) & 65535));
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m218equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).getData();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m219equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final long m220floorDiv7apg3OU(long j, byte b) {
        return Long.divideUnsigned(j, m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m221floorDivVKZWuLQ(long j, long j2) {
        return Long.divideUnsigned(j, j2);
    }

    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final long m222floorDivWZ4Q5Ns(long j, int i) {
        return Long.divideUnsigned(j, m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final long m223floorDivxj2QHRw(long j, short s) {
        return Long.divideUnsigned(j, m212constructorimpl(((long) s) & 65535));
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m224hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: inc-s-VKNKU, reason: not valid java name */
    private static final long m225incsVKNKU(long j) {
        return m212constructorimpl(j + 1);
    }

    /* JADX INFO: renamed from: inv-s-VKNKU, reason: not valid java name */
    private static final long m226invsVKNKU(long j) {
        return m212constructorimpl(~j);
    }

    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    private static final long m227minus7apg3OU(long j, byte b) {
        return m212constructorimpl(j - m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m228minusVKZWuLQ(long j, long j2) {
        return m212constructorimpl(j - j2);
    }

    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final long m229minusWZ4Q5Ns(long j, int i) {
        return m212constructorimpl(j - m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final long m230minusxj2QHRw(long j, short s) {
        return m212constructorimpl(j - m212constructorimpl(((long) s) & 65535));
    }

    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m231mod7apg3OU(long j, byte b) {
        return UByte.m56constructorimpl((byte) Long.remainderUnsigned(j, m212constructorimpl(((long) b) & 255)));
    }

    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m232modVKZWuLQ(long j, long j2) {
        return Long.remainderUnsigned(j, j2);
    }

    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m233modWZ4Q5Ns(long j, int i) {
        return UInt.m133constructorimpl((int) Long.remainderUnsigned(j, m212constructorimpl(((long) i) & 4294967295L)));
    }

    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m234modxj2QHRw(long j, short s) {
        return UShort.m319constructorimpl((short) Long.remainderUnsigned(j, m212constructorimpl(((long) s) & 65535)));
    }

    /* JADX INFO: renamed from: or-VKZWuLQ, reason: not valid java name */
    private static final long m235orVKZWuLQ(long j, long j2) {
        return m212constructorimpl(j | j2);
    }

    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    private static final long m236plus7apg3OU(long j, byte b) {
        return m212constructorimpl(j + m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m237plusVKZWuLQ(long j, long j2) {
        return m212constructorimpl(j + j2);
    }

    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final long m238plusWZ4Q5Ns(long j, int i) {
        return m212constructorimpl(j + m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final long m239plusxj2QHRw(long j, short s) {
        return m212constructorimpl(j + m212constructorimpl(((long) s) & 65535));
    }

    /* JADX INFO: renamed from: rangeTo-VKZWuLQ, reason: not valid java name */
    private static final ULongRange m240rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2, null);
    }

    /* JADX INFO: renamed from: rangeUntil-VKZWuLQ, reason: not valid java name */
    private static final ULongRange m241rangeUntilVKZWuLQ(long j, long j2) {
        return URangesKt.m1334untileb3DHEI(j, j2);
    }

    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    private static final long m242rem7apg3OU(long j, byte b) {
        return Long.remainderUnsigned(j, m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m243remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m392ulongRemaindereb3DHEI(j, j2);
    }

    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final long m244remWZ4Q5Ns(long j, int i) {
        return Long.remainderUnsigned(j, m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final long m245remxj2QHRw(long j, short s) {
        return Long.remainderUnsigned(j, m212constructorimpl(((long) s) & 65535));
    }

    /* JADX INFO: renamed from: shl-s-VKNKU, reason: not valid java name */
    private static final long m246shlsVKNKU(long j, int i) {
        return m212constructorimpl(j << i);
    }

    /* JADX INFO: renamed from: shr-s-VKNKU, reason: not valid java name */
    private static final long m247shrsVKNKU(long j, int i) {
        return m212constructorimpl(j >>> i);
    }

    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    private static final long m248times7apg3OU(long j, byte b) {
        return m212constructorimpl(j * m212constructorimpl(((long) b) & 255));
    }

    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m249timesVKZWuLQ(long j, long j2) {
        return m212constructorimpl(j * j2);
    }

    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final long m250timesWZ4Q5Ns(long j, int i) {
        return m212constructorimpl(j * m212constructorimpl(((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    private static final long m251timesxj2QHRw(long j, short s) {
        return m212constructorimpl(j * m212constructorimpl(((long) s) & 65535));
    }

    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    private static final byte m252toByteimpl(long j) {
        return (byte) j;
    }

    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    private static final double m253toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    private static final float m254toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    private static final int m255toIntimpl(long j) {
        return (int) j;
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    private static final long m256toLongimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    private static final short m257toShortimpl(long j) {
        return (short) j;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m258toStringimpl(long j) {
        return UnsignedKt.ulongToString(j, 10);
    }

    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m259toUBytew2LRezQ(long j) {
        return UByte.m56constructorimpl((byte) j);
    }

    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m260toUIntpVg5ArA(long j) {
        return UInt.m133constructorimpl((int) j);
    }

    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m261toULongsVKNKU(long j) {
        return j;
    }

    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m262toUShortMh2AYeg(long j) {
        return UShort.m319constructorimpl((short) j);
    }

    /* JADX INFO: renamed from: xor-VKZWuLQ, reason: not valid java name */
    private static final long m263xorVKZWuLQ(long j, long j2) {
        return m212constructorimpl(j ^ j2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(getData(), uLong.getData());
    }

    public boolean equals(Object other) {
        return m218equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m224hashCodeimpl(this.data);
    }

    public String toString() {
        return m258toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static int m209compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }
}

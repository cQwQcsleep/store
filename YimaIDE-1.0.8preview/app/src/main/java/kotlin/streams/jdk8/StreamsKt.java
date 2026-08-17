package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.sequences.Sequence;
import kotlin.streams.jdk8.StreamsKt;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\bH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\nH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\fH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a.\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a.\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f*\u00020\bH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f*\u00020\nH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f*\u00020\fH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¨\u0006\u0010"}, d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "T", "Ljava/util/stream/Stream;", "Lkotlin/SinceKotlin;", "version", "1.2", HttpUrl.FRAGMENT_ENCODE_SET, "Ljava/util/stream/IntStream;", HttpUrl.FRAGMENT_ENCODE_SET, "Ljava/util/stream/LongStream;", HttpUrl.FRAGMENT_ENCODE_SET, "Ljava/util/stream/DoubleStream;", "asStream", "toList", HttpUrl.FRAGMENT_ENCODE_SET, "kotlin-stdlib-jdk8"}, k = 2, mv = {2, 4, 0}, pn = "kotlin.streams", xi = 48)
public final class StreamsKt {
    public static Spliterator a(Sequence sequence) {
        return Spliterators.spliteratorUnknownSize(sequence.iterator(), 16);
    }

    public static final <T> Sequence<T> asSequence(final Stream<T> stream) {
        stream.getClass();
        return new Sequence<T>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                Iterator<T> it = stream.iterator();
                it.getClass();
                return it;
            }
        };
    }

    public static final <T> Stream<T> asStream(final Sequence<? extends T> sequence) {
        sequence.getClass();
        Stream<T> stream = StreamSupport.stream(new Supplier() { // from class: gqd
            @Override // java.util.function.Supplier
            public final Object get() {
                return StreamsKt.a(sequence);
            }
        }, 16, false);
        stream.getClass();
        return stream;
    }

    public static final <T> List<T> toList(Stream<T> stream) {
        stream.getClass();
        Object objCollect = stream.collect(Collectors.toList());
        objCollect.getClass();
        return (List) objCollect;
    }

    public static final Sequence<Integer> asSequence(final IntStream intStream) {
        intStream.getClass();
        return new Sequence<Integer>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$2
            @Override // kotlin.sequences.Sequence
            public Iterator<Integer> iterator() {
                Iterator<Integer> it = intStream.iterator();
                it.getClass();
                return it;
            }
        };
    }

    public static final Sequence<Long> asSequence(final LongStream longStream) {
        longStream.getClass();
        return new Sequence<Long>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$3
            @Override // kotlin.sequences.Sequence
            public Iterator<Long> iterator() {
                Iterator<Long> it = longStream.iterator();
                it.getClass();
                return it;
            }
        };
    }

    public static final Sequence<Double> asSequence(final DoubleStream doubleStream) {
        doubleStream.getClass();
        return new Sequence<Double>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$4
            @Override // kotlin.sequences.Sequence
            public Iterator<Double> iterator() {
                Iterator<Double> it = doubleStream.iterator();
                it.getClass();
                return it;
            }
        };
    }

    public static final List<Integer> toList(IntStream intStream) {
        intStream.getClass();
        int[] array = intStream.toArray();
        array.getClass();
        return ArraysKt.asList(array);
    }

    public static final List<Long> toList(LongStream longStream) {
        longStream.getClass();
        long[] array = longStream.toArray();
        array.getClass();
        return ArraysKt.asList(array);
    }

    public static final List<Double> toList(DoubleStream doubleStream) {
        doubleStream.getClass();
        double[] array = doubleStream.toArray();
        array.getClass();
        return ArraysKt.asList(array);
    }
}

package com.shadow.okhttp3.internal;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.ArrayIteratorKt;
import com.shadow.kotlin.ranges.IntRange;
import com.shadow.kotlin.ranges.RangesKt;
import com.shadow.kotlin.text.Charsets;
import com.shadow.kotlin.text.Regex;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okhttp3.Call;
import com.shadow.okhttp3.EventListener;
import com.shadow.okhttp3.Headers;
import com.shadow.okhttp3.HttpUrl;
import com.shadow.okhttp3.MediaType;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.RequestBody;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.ResponseBody;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.http2.Header;
import com.shadow.okhttp3.internal.io.FileSystem;
import com.shadow.okio.Buffer;
import com.shadow.okio.BufferedSink;
import com.shadow.okio.BufferedSource;
import com.shadow.okio.ByteString;
import com.shadow.okio.Options;
import com.shadow.okio.Sink;
import com.shadow.okio.Source;
import core.pro.android.notify.k;
import j$.util.DesugarTimeZone;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final Headers EMPTY_HEADERS = Headers.Companion.of(new String[0]);
    public static final RequestBody EMPTY_REQUEST;
    public static final ResponseBody EMPTY_RESPONSE;
    private static final Options UNICODE_BOMS;
    public static final TimeZone UTC;
    private static final Regex VERIFY_AS_IP_ADDRESS;
    public static final boolean assertionsEnabled;
    public static final String okHttpName;
    public static final String userAgent = "okhttp/4.12.0";

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_RESPONSE = ResponseBody.Companion.create$default(ResponseBody.Companion, bArr, (MediaType) null, 1, (Object) null);
        EMPTY_REQUEST = RequestBody.Companion.create$default(RequestBody.Companion, bArr, (MediaType) null, 0, 0, 7, (Object) null);
        Options.Companion companion = Options.Companion;
        ByteString.Companion companion2 = ByteString.Companion;
        UNICODE_BOMS = companion.of(companion2.decodeHex("efbbbf"), companion2.decodeHex("feff"), companion2.decodeHex("fffe"), companion2.decodeHex("0000ffff"), companion2.decodeHex("ffff0000"));
        TimeZone timeZone = DesugarTimeZone.getTimeZone("GMT");
        CloseableKt.checkNotNull(timeZone);
        UTC = timeZone;
        VERIFY_AS_IP_ADDRESS = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        assertionsEnabled = false;
        String strM = StringsKt.m(OkHttpClient.class.getName(), "com.shadow.okhttp3.");
        if (StringsKt.f(strM, "Client", false)) {
            strM = strM.substring(0, strM.length() - 6);
            CloseableKt.checkNotNullExpressionValue(strM, "substring(...)");
        }
        okHttpName = strM;
    }

    public static final <E> void addIfAbsent(List<E> list, E e) {
        CloseableKt.checkNotNullParameter(list, "<this>");
        if (list.contains(e)) {
            return;
        }
        list.add(e);
    }

    public static final int and(byte b, int i) {
        return b & i;
    }

    public static final EventListener.Factory asFactory(EventListener eventListener) {
        CloseableKt.checkNotNullParameter(eventListener, "<this>");
        return new k(2, eventListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EventListener asFactory$lambda$8(EventListener eventListener, Call call) {
        CloseableKt.checkNotNullParameter(eventListener, "$this_asFactory");
        CloseableKt.checkNotNullParameter(call, "it");
        return eventListener;
    }

    public static final void assertThreadDoesntHoldLock(Object obj) {
        CloseableKt.checkNotNullParameter(obj, "<this>");
        if (assertionsEnabled && Thread.holdsLock(obj)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + obj);
        }
    }

    public static final void assertThreadHoldsLock(Object obj) {
        CloseableKt.checkNotNullParameter(obj, "<this>");
        if (!assertionsEnabled || Thread.holdsLock(obj)) {
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + obj);
    }

    public static final boolean canParseAsIpAddress(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        return VERIFY_AS_IP_ADDRESS.matches(str);
    }

    public static final boolean canReuseConnectionFor(HttpUrl httpUrl, HttpUrl httpUrl2) {
        CloseableKt.checkNotNullParameter(httpUrl, "<this>");
        CloseableKt.checkNotNullParameter(httpUrl2, "other");
        return CloseableKt.areEqual(httpUrl.host(), httpUrl2.host()) && httpUrl.port() == httpUrl2.port() && CloseableKt.areEqual(httpUrl.scheme(), httpUrl2.scheme());
    }

    public static final int checkDuration(String str, long j, TimeUnit timeUnit) {
        CloseableKt.checkNotNullParameter(str, "name");
        if (j < 0) {
            throw new IllegalStateException(str.concat(" < 0").toString());
        }
        if (timeUnit == null) {
            throw new IllegalStateException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException(str.concat(" too large.").toString());
        }
        if (millis != 0 || j <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException(str.concat(" too small.").toString());
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void closeQuietly(Closeable closeable) throws IOException {
        CloseableKt.checkNotNullParameter(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final String[] concat(String[] strArr, String str) {
        CloseableKt.checkNotNullParameter(strArr, "<this>");
        CloseableKt.checkNotNullParameter(str, "value");
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length + 1);
        CloseableKt.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        String[] strArr2 = (String[]) objArrCopyOf;
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    public static final int delimiterOffset(String str, String str2, int i, int i2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str2, "delimiters");
        while (i < i2) {
            if (StringsKt.d(str2, str.charAt(i))) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int delimiterOffset$default(String str, String str2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return delimiterOffset(str, str2, i, i2);
    }

    public static final boolean discard(Source source, int i, TimeUnit timeUnit) {
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(timeUnit, "timeUnit");
        try {
            return skipAll(source, i, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final <T> List<T> filterList(Iterable<? extends T> iterable, Function1<? super T, Boolean> function1) {
        CloseableKt.checkNotNullParameter(iterable, "<this>");
        CloseableKt.checkNotNullParameter(function1, "predicate");
        ArrayList arrayList = EmptyList.INSTANCE;
        for (T t : iterable) {
            if (((Boolean) function1.invoke(t)).booleanValue()) {
                if (arrayList.isEmpty()) {
                    arrayList = new ArrayList();
                }
                RangesKt.asMutableList(arrayList).add(t);
            }
        }
        return arrayList;
    }

    public static final String format(String str, Object... objArr) {
        CloseableKt.checkNotNullParameter(str, "format");
        CloseableKt.checkNotNullParameter(objArr, "args");
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        return String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    public static final boolean hasIntersection(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        CloseableKt.checkNotNullParameter(strArr, "<this>");
        CloseableKt.checkNotNullParameter(comparator, "comparator");
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            for (String str : strArr) {
                Iterator it = ArrayIteratorKt.iterator(strArr2);
                while (it.hasNext()) {
                    if (comparator.compare(str, (String) it.next()) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final long headersContentLength(Response response) {
        CloseableKt.checkNotNullParameter(response, "<this>");
        String str = response.headers().get("Content-Length");
        if (str != null) {
            return toLongOrDefault(str, -1L);
        }
        return -1L;
    }

    public static final void ignoreIoExceptions(Function0<Unit> function0) {
        CloseableKt.checkNotNullParameter(function0, "block");
        try {
            function0.invoke();
        } catch (IOException unused) {
        }
    }

    @SafeVarargs
    public static final <T> List<T> immutableListOf(T... tArr) {
        CloseableKt.checkNotNullParameter(tArr, "elements");
        Object[] objArr = (Object[]) tArr.clone();
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        CloseableKt.checkNotNullParameter(objArrCopyOf, "elements");
        List<T> listUnmodifiableList = Collections.unmodifiableList(objArrCopyOf.length > 0 ? ArraysKt.a(objArrCopyOf) : EmptyList.INSTANCE);
        CloseableKt.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(listOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    public static final int indexOf(String[] strArr, String str, Comparator<String> comparator) {
        CloseableKt.checkNotNullParameter(strArr, "<this>");
        CloseableKt.checkNotNullParameter(str, "value");
        CloseableKt.checkNotNullParameter(comparator, "comparator");
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (comparator.compare(strArr[i], str) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfControlOrNonAscii(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (CloseableKt.compare(cCharAt, 31) <= 0 || CloseableKt.compare(cCharAt, 127) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfFirstNonAsciiWhitespace(String str, int i, int i2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int indexOfFirstNonAsciiWhitespace$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return indexOfFirstNonAsciiWhitespace(str, i, i2);
    }

    public static final int indexOfLastNonAsciiWhitespace(String str, int i, int i2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        int i3 = i2 - 1;
        if (i <= i3) {
            while (true) {
                char cCharAt = str.charAt(i3);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i3 + 1;
                }
                if (i3 == i) {
                    break;
                }
                i3--;
            }
        }
        return i;
    }

    public static /* synthetic */ int indexOfLastNonAsciiWhitespace$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return indexOfLastNonAsciiWhitespace(str, i, i2);
    }

    public static final int indexOfNonWhitespace(String str, int i) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        int length = str.length();
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt != ' ' && cCharAt != '\t') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    public static /* synthetic */ int indexOfNonWhitespace$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return indexOfNonWhitespace(str, i);
    }

    public static final String[] intersect(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        CloseableKt.checkNotNullParameter(strArr, "<this>");
        CloseableKt.checkNotNullParameter(strArr2, "other");
        CloseableKt.checkNotNullParameter(comparator, "comparator");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean isCivilized(FileSystem fileSystem, File file) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(file, "file");
        Sink sink = fileSystem.sink(file);
        try {
            fileSystem.delete(file);
            CloseableKt.closeFinally(sink, null);
            return true;
        } catch (IOException unused) {
            CloseableKt.closeFinally(sink, null);
            fileSystem.delete(file);
            return false;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(sink, th);
                throw th2;
            }
        }
    }

    public static final boolean isHealthy(Socket socket, BufferedSource bufferedSource) throws SocketException {
        CloseableKt.checkNotNullParameter(socket, "<this>");
        CloseableKt.checkNotNullParameter(bufferedSource, "source");
        try {
            int soTimeout = socket.getSoTimeout();
            try {
                socket.setSoTimeout(1);
                boolean z = !bufferedSource.exhausted();
                socket.setSoTimeout(soTimeout);
                return z;
            } catch (Throwable th) {
                socket.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public static final boolean isSensitiveHeader(String str) {
        CloseableKt.checkNotNullParameter(str, "name");
        return str.equalsIgnoreCase("Authorization") || str.equalsIgnoreCase("Cookie") || str.equalsIgnoreCase("Proxy-Authorization") || str.equalsIgnoreCase("Set-Cookie");
    }

    public static final void notify(Object obj) {
        CloseableKt.checkNotNullParameter(obj, "<this>");
        obj.notify();
    }

    public static final void notifyAll(Object obj) {
        CloseableKt.checkNotNullParameter(obj, "<this>");
        obj.notifyAll();
    }

    public static final int parseHexDigit(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('a' <= c && c < 'g') {
            return c - 'W';
        }
        if ('A' > c || c >= 'G') {
            return -1;
        }
        return c - '7';
    }

    public static final String peerName(Socket socket) {
        CloseableKt.checkNotNullParameter(socket, "<this>");
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        if (!(remoteSocketAddress instanceof InetSocketAddress)) {
            return remoteSocketAddress.toString();
        }
        String hostName = ((InetSocketAddress) remoteSocketAddress).getHostName();
        CloseableKt.checkNotNullExpressionValue(hostName, "address.hostName");
        return hostName;
    }

    public static final Charset readBomAsCharset(BufferedSource bufferedSource, Charset charset) throws IOException {
        CloseableKt.checkNotNullParameter(bufferedSource, "<this>");
        CloseableKt.checkNotNullParameter(charset, "default");
        int iSelect = bufferedSource.select(UNICODE_BOMS);
        if (iSelect == -1) {
            return charset;
        }
        if (iSelect == 0) {
            Charset charset2 = StandardCharsets.UTF_8;
            CloseableKt.checkNotNullExpressionValue(charset2, "UTF_8");
            return charset2;
        }
        if (iSelect == 1) {
            Charset charset3 = StandardCharsets.UTF_16BE;
            CloseableKt.checkNotNullExpressionValue(charset3, "UTF_16BE");
            return charset3;
        }
        if (iSelect == 2) {
            Charset charset4 = StandardCharsets.UTF_16LE;
            CloseableKt.checkNotNullExpressionValue(charset4, "UTF_16LE");
            return charset4;
        }
        if (iSelect == 3) {
            return Charsets.UTF32_BE();
        }
        if (iSelect == 4) {
            return Charsets.UTF32_LE();
        }
        throw new AssertionError();
    }

    public static final <T> T readFieldOrNull(Object obj, Class<T> cls, String str) {
        T tCast;
        Object fieldOrNull;
        CloseableKt.checkNotNullParameter(obj, "instance");
        CloseableKt.checkNotNullParameter(cls, "fieldType");
        CloseableKt.checkNotNullParameter(str, "fieldName");
        Class<?> superclass = obj.getClass();
        while (true) {
            tCast = null;
            if (superclass.equals(Object.class)) {
                if (str.equals("delegate") || (fieldOrNull = readFieldOrNull(obj, Object.class, "delegate")) == null) {
                    return null;
                }
                return (T) readFieldOrNull(fieldOrNull, cls, str);
            }
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (!cls.isInstance(obj2)) {
                    break;
                }
                tCast = cls.cast(obj2);
                break;
            } catch (NoSuchFieldException unused) {
                superclass = superclass.getSuperclass();
                CloseableKt.checkNotNullExpressionValue(superclass, "c.superclass");
            }
        }
        return tCast;
    }

    public static final int readMedium(BufferedSource bufferedSource) throws IOException {
        CloseableKt.checkNotNullParameter(bufferedSource, "<this>");
        return and(bufferedSource.readByte(), 255) | (and(bufferedSource.readByte(), 255) << 16) | (and(bufferedSource.readByte(), 255) << 8);
    }

    public static final boolean skipAll(Source source, int i, TimeUnit timeUnit) throws IOException {
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(timeUnit, "timeUnit");
        long jNanoTime = System.nanoTime();
        long jDeadlineNanoTime = source.timeout().hasDeadline() ? source.timeout().deadlineNanoTime() - jNanoTime : Long.MAX_VALUE;
        source.timeout().deadlineNanoTime(Math.min(jDeadlineNanoTime, timeUnit.toNanos(i)) + jNanoTime);
        try {
            Buffer buffer = new Buffer();
            while (source.read(buffer, 8192L) != -1) {
                buffer.clear();
            }
            if (jDeadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(jNanoTime + jDeadlineNanoTime);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jDeadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(jNanoTime + jDeadlineNanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (jDeadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(jNanoTime + jDeadlineNanoTime);
            }
            throw th;
        }
    }

    public static final ThreadFactory threadFactory(final String str, final boolean z) {
        CloseableKt.checkNotNullParameter(str, "name");
        return new ThreadFactory(str, z) { // from class: core.pro.android.notify.n2
            public final String a;
            public final boolean b;

            {
                this.a = str;
                this.b = z;
            }

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                String str2 = "ۧۚ۟۬۟ۥۢ۬ۢۨۢ۠ۧ۫ۡۘۖۧۜۜۢۨۘ۟۠۠۟ۢۖۘۙ۟ۨ";
                while (true) {
                    switch ((((str2.hashCode() ^ 869) ^ 400) ^ 912) ^ 1388271432) {
                        case 100552460:
                            str2 = "ۚۧۢۙ۟۠ۢۘۜۘ۬ۥ۬۫ۘۡۘۘۘۢۘۨۘۨ۠ۨۘ۬ۥۥۤ۬ۤ";
                            break;
                        case 1336401213:
                            str2 = "ۡ۟ۥۘۘ۫ۢۧۜۡۘۧۥۥۖۚۧۚۙۡۗۗ۠۟ۡۤ۠ۗۚۦۛۨ۟۟ۧۗۡ۫ۢۙۖۘۙۛۤ";
                            break;
                        case 1513223411:
                            return Util.threadFactory$lambda$1(this.a, this.b, runnable);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread threadFactory$lambda$1(String str, boolean z, Runnable runnable) {
        CloseableKt.checkNotNullParameter(str, "$name");
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(z);
        return thread;
    }

    public static final void threadName(String str, Function0<Unit> function0) {
        CloseableKt.checkNotNullParameter(str, "name");
        CloseableKt.checkNotNullParameter(function0, "block");
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(str);
        try {
            function0.invoke();
        } finally {
            threadCurrentThread.setName(name);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.shadow.kotlin.ranges.IntProgressionIterator] */
    public static final List<Header> toHeaderList(Headers headers) {
        CloseableKt.checkNotNullParameter(headers, "<this>");
        IntRange intRangeB = RangesKt.b(0, headers.size());
        ArrayList arrayList = new ArrayList(CollectionsKt.b(intRangeB));
        ?? Iterator2 = intRangeB.iterator2();
        while (Iterator2.hasNext()) {
            int iNextInt = Iterator2.nextInt();
            arrayList.add(new Header(headers.name(iNextInt), headers.value(iNextInt)));
        }
        return arrayList;
    }

    public static final Headers toHeaders(List<Header> list) {
        CloseableKt.checkNotNullParameter(list, "<this>");
        Headers.Builder builder = new Headers.Builder();
        for (Header header : list) {
            builder.addLenient$okhttp(header.component1().utf8(), header.component2().utf8());
        }
        return builder.build();
    }

    public static final String toHexString(long j) {
        String hexString = Long.toHexString(j);
        CloseableKt.checkNotNullExpressionValue(hexString, "toHexString(this)");
        return hexString;
    }

    public static final String toHostHeader(HttpUrl httpUrl, boolean z) {
        String strHost;
        CloseableKt.checkNotNullParameter(httpUrl, "<this>");
        if (StringsKt.e(httpUrl.host(), ":")) {
            strHost = "[" + httpUrl.host() + ']';
        } else {
            strHost = httpUrl.host();
        }
        if (!z && httpUrl.port() == HttpUrl.Companion.defaultPort(httpUrl.scheme())) {
            return strHost;
        }
        return strHost + ':' + httpUrl.port();
    }

    public static /* synthetic */ String toHostHeader$default(HttpUrl httpUrl, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toHostHeader(httpUrl, z);
    }

    public static final <T> List<T> toImmutableList(List<? extends T> list) {
        CloseableKt.checkNotNullParameter(list, "<this>");
        List<T> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        CloseableKt.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    public static final <K, V> Map<K, V> toImmutableMap(Map<K, ? extends V> map) {
        CloseableKt.checkNotNullParameter(map, "<this>");
        if (map.isEmpty()) {
            return MapsKt.a();
        }
        Map<K, V> mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(map));
        CloseableKt.checkNotNullExpressionValue(mapUnmodifiableMap, "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
        return mapUnmodifiableMap;
    }

    public static final long toLongOrDefault(String str, long j) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static final int toNonNegativeInt(String str, int i) throws NumberFormatException {
        if (str != null) {
            try {
                long j = Long.parseLong(str);
                if (j > 2147483647L) {
                    return Integer.MAX_VALUE;
                }
                if (j < 0) {
                    return 0;
                }
                return (int) j;
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public static final String trimSubstring(String str, int i, int i2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        int iIndexOfFirstNonAsciiWhitespace = indexOfFirstNonAsciiWhitespace(str, i, i2);
        String strSubstring = str.substring(iIndexOfFirstNonAsciiWhitespace, indexOfLastNonAsciiWhitespace(str, iIndexOfFirstNonAsciiWhitespace, i2));
        CloseableKt.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String trimSubstring$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return trimSubstring(str, i, i2);
    }

    public static final void wait(Object obj) throws InterruptedException {
        CloseableKt.checkNotNullParameter(obj, "<this>");
        obj.wait();
    }

    public static final Throwable withSuppressed(Exception exc, List<? extends Exception> list) {
        CloseableKt.checkNotNullParameter(exc, "<this>");
        CloseableKt.checkNotNullParameter(list, "suppressed");
        Iterator<? extends Exception> it = list.iterator();
        while (it.hasNext()) {
            LazyKt.a(exc, it.next());
        }
        return exc;
    }

    public static final void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        CloseableKt.checkNotNullParameter(bufferedSink, "<this>");
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    public static final int and(short s, int i) {
        return s & i;
    }

    public static final int delimiterOffset(String str, char c, int i, int i2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        while (i < i2) {
            if (str.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static /* synthetic */ int delimiterOffset$default(String str, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return delimiterOffset(str, c, i, i2);
    }

    public static final String toHexString(int i) {
        String hexString = Integer.toHexString(i);
        CloseableKt.checkNotNullExpressionValue(hexString, "toHexString(this)");
        return hexString;
    }

    public static final long and(int i, long j) {
        return i & j;
    }

    public static final void closeQuietly(Socket socket) throws IOException {
        CloseableKt.checkNotNullParameter(socket, "<this>");
        try {
            socket.close();
        } catch (AssertionError e) {
            throw e;
        } catch (RuntimeException e2) {
            if (!CloseableKt.areEqual(e2.getMessage(), "bio == null")) {
                throw e2;
            }
        } catch (Exception unused) {
        }
    }

    public static final void closeQuietly(ServerSocket serverSocket) throws IOException {
        CloseableKt.checkNotNullParameter(serverSocket, "<this>");
        try {
            serverSocket.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final int skipAll(Buffer buffer, byte b) throws EOFException {
        CloseableKt.checkNotNullParameter(buffer, "<this>");
        int i = 0;
        while (!buffer.exhausted() && buffer.getByte(0L) == b) {
            i++;
            buffer.readByte();
        }
        return i;
    }
}

package com.shadow.okhttp3.internal.publicsuffix;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.jvm.internal.Ref$ObjectRef;
import com.shadow.kotlin.sequences.DropSequence;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.platform.Platform;
import com.shadow.okio.BufferedSource;
import com.shadow.okio.GzipSource;
import com.shadow.okio.Okio;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.sequences.Sequence;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class PublicSuffixDatabase {
    private static final char EXCEPTION_MARKER = '!';
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    public static final Companion Companion = new Companion(null);
    private static final byte[] WILDCARD_LABEL = {42};
    private static final List<String> PREVAILING_RULE = CollectionsKt.d("*");
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String binarySearch(byte[] bArr, byte[][] bArr2, int i) {
            int i2;
            int iAnd;
            boolean z;
            int iAnd2;
            int length = bArr.length;
            int i3 = 0;
            while (i3 < length) {
                int i4 = (i3 + length) / 2;
                while (i4 > -1 && bArr[i4] != 10) {
                    i4--;
                }
                int i5 = i4 + 1;
                int i6 = 1;
                while (true) {
                    i2 = i5 + i6;
                    if (bArr[i2] == 10) {
                        break;
                    }
                    i6++;
                }
                int i7 = i2 - i5;
                int i8 = i;
                boolean z2 = false;
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    if (z2) {
                        iAnd = 46;
                        z = false;
                    } else {
                        boolean z3 = z2;
                        iAnd = Util.and(bArr2[i8][i9], 255);
                        z = z3;
                    }
                    iAnd2 = iAnd - Util.and(bArr[i5 + i10], 255);
                    if (iAnd2 != 0) {
                        break;
                    }
                    i10++;
                    i9++;
                    if (i10 == i7) {
                        break;
                    }
                    if (bArr2[i8].length != i9) {
                        z2 = z;
                    } else {
                        if (i8 == bArr2.length - 1) {
                            break;
                        }
                        i8++;
                        z2 = true;
                        i9 = -1;
                    }
                }
                if (iAnd2 >= 0) {
                    if (iAnd2 <= 0) {
                        int i11 = i7 - i10;
                        int length2 = bArr2[i8].length - i9;
                        int length3 = bArr2.length;
                        for (int i12 = i8 + 1; i12 < length3; i12++) {
                            length2 += bArr2[i12].length;
                        }
                        if (length2 >= i11) {
                            if (length2 <= i11) {
                                Charset charset = StandardCharsets.UTF_8;
                                CloseableKt.checkNotNullExpressionValue(charset, "UTF_8");
                                return new String(bArr, i5, i7, charset);
                            }
                        }
                    }
                    i3 = i2 + 1;
                }
                length = i4;
            }
            return null;
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }

        private Companion() {
        }
    }

    private final List<String> findMatchingRule(List<String> list) throws InterruptedException {
        String str;
        String strBinarySearch;
        String strBinarySearch2;
        if (this.listRead.get() || !this.listRead.compareAndSet(false, true)) {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            readTheListUninterruptibly();
        }
        if (this.publicSuffixListBytes == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        int size = list.size();
        byte[][] bArr = new byte[size][];
        for (int i = 0; i < size; i++) {
            String str2 = list.get(i);
            Charset charset = StandardCharsets.UTF_8;
            CloseableKt.checkNotNullExpressionValue(charset, "UTF_8");
            byte[] bytes = str2.getBytes(charset);
            CloseableKt.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i] = bytes;
        }
        int i2 = 0;
        while (true) {
            str = null;
            if (i2 >= size) {
                strBinarySearch = null;
                break;
            }
            Companion companion = Companion;
            byte[] bArr2 = this.publicSuffixListBytes;
            if (bArr2 == null) {
                CloseableKt.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                throw null;
            }
            strBinarySearch = companion.binarySearch(bArr2, bArr, i2);
            if (strBinarySearch != null) {
                break;
            }
            i2++;
        }
        if (size > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i3 = 0; i3 < length; i3++) {
                bArr3[i3] = WILDCARD_LABEL;
                Companion companion2 = Companion;
                byte[] bArr4 = this.publicSuffixListBytes;
                if (bArr4 == null) {
                    CloseableKt.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                    throw null;
                }
                strBinarySearch2 = companion2.binarySearch(bArr4, bArr3, i3);
                if (strBinarySearch2 != null) {
                    break;
                }
            }
            strBinarySearch2 = null;
        } else {
            strBinarySearch2 = null;
        }
        if (strBinarySearch2 != null) {
            int i4 = size - 1;
            int i5 = 0;
            while (true) {
                if (i5 >= i4) {
                    break;
                }
                Companion companion3 = Companion;
                byte[] bArr5 = this.publicSuffixExceptionListBytes;
                if (bArr5 == null) {
                    CloseableKt.throwUninitializedPropertyAccessException("publicSuffixExceptionListBytes");
                    throw null;
                }
                String strBinarySearch3 = companion3.binarySearch(bArr5, bArr, i5);
                if (strBinarySearch3 != null) {
                    str = strBinarySearch3;
                    break;
                }
                i5++;
            }
        }
        if (str != null) {
            return StringsKt.p("!".concat(str), new char[]{'.'});
        }
        if (strBinarySearch == null && strBinarySearch2 == null) {
            return PREVAILING_RULE;
        }
        List<String> listP = strBinarySearch != null ? StringsKt.p(strBinarySearch, new char[]{'.'}) : EmptyList.INSTANCE;
        List<String> listP2 = strBinarySearch2 != null ? StringsKt.p(strBinarySearch2, new char[]{'.'}) : EmptyList.INSTANCE;
        return listP.size() > listP2.size() ? listP : listP2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, byte[]] */
    private final void readTheList() throws IOException {
        try {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
            if (resourceAsStream == null) {
                return;
            }
            BufferedSource bufferedSourceBuffer = Okio.buffer(new GzipSource(Okio.source(resourceAsStream)));
            try {
                ref$ObjectRef.element = bufferedSourceBuffer.readByteArray(bufferedSourceBuffer.readInt());
                ref$ObjectRef2.element = bufferedSourceBuffer.readByteArray(bufferedSourceBuffer.readInt());
                CloseableKt.closeFinally(bufferedSourceBuffer, null);
                synchronized (this) {
                    T t = ref$ObjectRef.element;
                    CloseableKt.checkNotNull(t);
                    this.publicSuffixListBytes = (byte[]) t;
                    T t2 = ref$ObjectRef2.element;
                    CloseableKt.checkNotNull(t2);
                    this.publicSuffixExceptionListBytes = (byte[]) t2;
                }
            } finally {
            }
        } finally {
            this.readCompleteLatch.countDown();
        }
    }

    private final void readTheListUninterruptibly() {
        boolean z = false;
        while (true) {
            try {
                try {
                    readTheList();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z = true;
                } catch (IOException e) {
                    Platform.Companion.get().log("Failed to read public suffix list", 5, e);
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private final List<String> splitDomain(String str) {
        List<String> listP = StringsKt.p(str, new char[]{'.'});
        if (listP.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        if (!CloseableKt.areEqual(listP.get(listP.size() - 1), "")) {
            return listP;
        }
        int size = listP.size() - 1;
        if (size < 0) {
            size = 0;
        }
        if (size < 0) {
            throw new IllegalArgumentException(("Requested element count " + size + " is less than zero.").toString());
        }
        if (size == 0) {
            return EmptyList.INSTANCE;
        }
        if (size >= listP.size()) {
            return CollectionsKt.g(listP);
        }
        if (size == 1) {
            if (listP.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            return CollectionsKt.d(listP.get(0));
        }
        ArrayList arrayList = new ArrayList(size);
        Iterator<T> it = listP.iterator();
        int i = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i++;
            if (i == size) {
                break;
            }
        }
        int size2 = arrayList.size();
        return size2 != 0 ? size2 != 1 ? arrayList : CollectionsKt.d(arrayList.get(0)) : EmptyList.INSTANCE;
    }

    public final String getEffectiveTldPlusOne(String str) throws InterruptedException {
        int size;
        int size2;
        CloseableKt.checkNotNullParameter(str, "domain");
        String unicode = IDN.toUnicode(str);
        CloseableKt.checkNotNullExpressionValue(unicode, "unicodeDomain");
        List<String> listSplitDomain = splitDomain(unicode);
        List<String> listFindMatchingRule = findMatchingRule(listSplitDomain);
        int i = 0;
        if (listSplitDomain.size() == listFindMatchingRule.size() && listFindMatchingRule.get(0).charAt(0) != '!') {
            return null;
        }
        if (listFindMatchingRule.get(0).charAt(0) == '!') {
            size = listSplitDomain.size();
            size2 = listFindMatchingRule.size();
        } else {
            size = listSplitDomain.size();
            size2 = listFindMatchingRule.size() + 1;
        }
        int i2 = size - size2;
        final List<String> listSplitDomain2 = splitDomain(str);
        CloseableKt.checkNotNullParameter(listSplitDomain2, "<this>");
        DropSequence dropSequence = new Sequence<Object>() { // from class: com.shadow.kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1
            public final Iterator<Object> iterator() {
                return listSplitDomain2.iterator();
            }
        };
        if (i2 < 0) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        }
        if (i2 != 0) {
            dropSequence = new DropSequence(dropSequence, i2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        for (Object obj : dropSequence) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) ".");
            }
            StringsKt.b(sb, obj, null);
        }
        sb.append((CharSequence) "");
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final void setListBytes(byte[] bArr, byte[] bArr2) {
        CloseableKt.checkNotNullParameter(bArr, "publicSuffixListBytes");
        CloseableKt.checkNotNullParameter(bArr2, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}

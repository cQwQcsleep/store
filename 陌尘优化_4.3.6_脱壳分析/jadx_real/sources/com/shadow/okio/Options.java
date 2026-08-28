package com.shadow.okio;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public static final Companion Companion = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void buildTrieRecursive(long j, Buffer buffer, int i, List<? extends ByteString> list, int i2, int i3, List<Integer> list2) throws IOException {
            int i4;
            int i5;
            int i6;
            int i7;
            Buffer buffer2;
            int i8 = i;
            if (i2 >= i3) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            for (int i9 = i2; i9 < i3; i9++) {
                if (list.get(i9).size() < i8) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
            ByteString byteString = list.get(i2);
            ByteString byteString2 = list.get(i3 - 1);
            if (i8 == byteString.size()) {
                int iIntValue = list2.get(i2).intValue();
                int i10 = i2 + 1;
                ByteString byteString3 = list.get(i10);
                i4 = i10;
                i5 = iIntValue;
                byteString = byteString3;
            } else {
                i4 = i2;
                i5 = -1;
            }
            if (byteString.getByte(i8) == byteString2.getByte(i8)) {
                int iMin = Math.min(byteString.size(), byteString2.size());
                int i11 = 0;
                for (int i12 = i8; i12 < iMin && byteString.getByte(i12) == byteString2.getByte(i12); i12++) {
                    i11++;
                }
                long intCount = j + getIntCount(buffer) + 2 + i11 + 1;
                buffer.writeInt(-i11);
                buffer.writeInt(i5);
                int i13 = i11 + i8;
                while (i8 < i13) {
                    buffer.writeInt(byteString.getByte(i8) & 255);
                    i8++;
                }
                if (i4 + 1 == i3) {
                    if (i13 != list.get(i4).size()) {
                        throw new IllegalStateException("Check failed.");
                    }
                    buffer.writeInt(list2.get(i4).intValue());
                    return;
                } else {
                    Buffer buffer3 = new Buffer();
                    buffer.writeInt(((int) (getIntCount(buffer3) + intCount)) * (-1));
                    buildTrieRecursive(intCount, buffer3, i13, list, i4, i3, list2);
                    buffer.writeAll(buffer3);
                    return;
                }
            }
            int i14 = 1;
            for (int i15 = i4 + 1; i15 < i3; i15++) {
                if (list.get(i15 - 1).getByte(i8) != list.get(i15).getByte(i8)) {
                    i14++;
                }
            }
            long intCount2 = j + getIntCount(buffer) + 2 + (i14 * 2);
            buffer.writeInt(i14);
            buffer.writeInt(i5);
            for (int i16 = i4; i16 < i3; i16++) {
                byte b = list.get(i16).getByte(i8);
                if (i16 == i4 || b != list.get(i16 - 1).getByte(i8)) {
                    buffer.writeInt(b & 255);
                }
            }
            Buffer buffer4 = new Buffer();
            while (i4 < i3) {
                byte b2 = list.get(i4).getByte(i8);
                int i17 = i4 + 1;
                int i18 = i17;
                while (true) {
                    if (i18 >= i3) {
                        i6 = i3;
                        break;
                    } else {
                        if (b2 != list.get(i18).getByte(i8)) {
                            i6 = i18;
                            break;
                        }
                        i18++;
                    }
                }
                if (i17 == i6 && i8 + 1 == list.get(i4).size()) {
                    buffer.writeInt(list2.get(i4).intValue());
                    i7 = i6;
                    buffer2 = buffer4;
                } else {
                    buffer.writeInt(((int) (intCount2 + getIntCount(buffer4))) * (-1));
                    i7 = i6;
                    buffer2 = buffer4;
                    buildTrieRecursive(intCount2, buffer4, i8 + 1, list, i4, i6, list2);
                }
                buffer4 = buffer2;
                i4 = i7;
            }
            buffer.writeAll(buffer4);
        }

        public static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) throws IOException {
            companion.buildTrieRecursive((i4 & 1) != 0 ? 0L : j, buffer, (i4 & 4) != 0 ? 0 : i, list, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? list.size() : i3, list2);
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:75:0x0129, code lost:
        
            continue;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Options of(ByteString... byteStringArr) throws IOException {
            int i;
            CloseableKt.checkNotNullParameter(byteStringArr, "byteStrings");
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (byteStringArr.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, defaultConstructorMarker);
            }
            ArrayList arrayListG = ArraysKt.g(byteStringArr);
            CollectionsKt.f(arrayListG);
            int size = arrayListG.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(-1);
            }
            int length = byteStringArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                ByteString byteString = byteStringArr[i3];
                int i5 = i4 + 1;
                int size2 = arrayListG.size();
                int size3 = arrayListG.size();
                if (size2 < 0) {
                    throw new IllegalArgumentException("fromIndex (0) is greater than toIndex (" + size2 + ").");
                }
                if (size2 > size3) {
                    throw new IndexOutOfBoundsException("toIndex (" + size2 + ") is greater than size (" + size3 + ").");
                }
                int i6 = size2 - 1;
                int i7 = 0;
                while (true) {
                    if (i7 > i6) {
                        i = -(i7 + 1);
                        break;
                    }
                    int iCompareTo = 1;
                    i = (i7 + i6) >>> 1;
                    Comparable comparable = (Comparable) arrayListG.get(i);
                    if (comparable == byteString) {
                        iCompareTo = 0;
                    } else if (comparable == null) {
                        iCompareTo = -1;
                    } else if (byteString != null) {
                        iCompareTo = comparable.compareTo(byteString);
                    }
                    if (iCompareTo < 0) {
                        i7 = i + 1;
                    } else if (iCompareTo > 0) {
                        i6 = i - 1;
                    }
                }
                arrayList.set(i, Integer.valueOf(i4));
                i3++;
                i4 = i5;
            }
            if (((ByteString) arrayListG.get(0)).size() <= 0) {
                throw new IllegalArgumentException("the empty byte string is not a supported option");
            }
            int i8 = 0;
            while (i8 < arrayListG.size()) {
                ByteString byteString2 = (ByteString) arrayListG.get(i8);
                int i9 = i8 + 1;
                int i10 = i9;
                while (i10 < arrayListG.size()) {
                    ByteString byteString3 = (ByteString) arrayListG.get(i10);
                    if (byteString3.startsWith(byteString2)) {
                        if (byteString3.size() == byteString2.size()) {
                            throw new IllegalArgumentException(("duplicate option: " + byteString3).toString());
                        }
                        if (((Number) arrayList.get(i10)).intValue() > ((Number) arrayList.get(i8)).intValue()) {
                            arrayListG.remove(i10);
                            arrayList.remove(i10);
                        } else {
                            i10++;
                        }
                    }
                }
                i8 = i9;
            }
            Buffer buffer = new Buffer();
            buildTrieRecursive$default(this, 0L, buffer, 0, arrayListG, 0, 0, arrayList, 53, null);
            int intCount = (int) getIntCount(buffer);
            int[] iArr = new int[intCount];
            for (int i11 = 0; i11 < intCount; i11++) {
                iArr[i11] = buffer.readInt();
            }
            Object[] objArrCopyOf = Arrays.copyOf(byteStringArr, byteStringArr.length);
            CloseableKt.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            return new Options((ByteString[]) objArrCopyOf, iArr, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    public static final Options of(ByteString... byteStringArr) {
        return Companion.of(byteStringArr);
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains((Options) byteString);
    }

    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    public int getSize() {
        return this.byteStrings.length;
    }

    public final int[] getTrie$okio() {
        return this.trie;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf((Options) byteString);
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf((Options) byteString);
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    /* renamed from: get, reason: merged with bridge method [inline-methods] */
    public ByteString m177get(int i) {
        return this.byteStrings[i];
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }
}

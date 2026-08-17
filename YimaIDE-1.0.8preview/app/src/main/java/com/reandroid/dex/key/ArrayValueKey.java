package com.reandroid.dex.key;

import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayValueKey extends ArrayKey<Key> {
    private static final ArrayValueKey EMPTY = new ArrayValueKey(KeyList.EMPTY_ARRAY);

    private ArrayValueKey(Key[] keyArr) {
        super(keyArr);
    }

    public static ArrayValueKey create(ArrayKey<?> arrayKey) {
        if (arrayKey instanceof ArrayValueKey) {
            return (ArrayValueKey) arrayKey;
        }
        return arrayKey.isEmpty() ? empty() : of(arrayKey.getElements());
    }

    public static ArrayValueKey empty() {
        return EMPTY;
    }

    public static ArrayValueKey of(Key... keyArr) {
        return (keyArr == null || keyArr.length == 0) ? empty() : new ArrayValueKey(keyArr);
    }

    public static ArrayValueKey parse(String str) {
        throw new RuntimeException("ArrayValueKey.parse not implemented");
    }

    public static ArrayValueKey read(SmaliReader smaliReader) throws IOException {
        SmaliParseException.expect(smaliReader, '{');
        return of(ArrayKey.readElements(smaliReader, '}'));
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey add(Key key) {
        return (ArrayValueKey) super.add(key);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [com.reandroid.dex.key.Key] */
    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        smaliWriter.append('{');
        smaliWriter.indentPlus();
        for (int i = 0; i < size; i++) {
            ?? r2 = get(i);
            if (i != 0) {
                smaliWriter.append(',');
            }
            smaliWriter.newLine();
            r2.append(smaliWriter);
        }
        smaliWriter.indentMinus();
        if (size != 0) {
            smaliWriter.newLine();
        }
        smaliWriter.append('}');
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey clearDuplicates() {
        return (ArrayValueKey) super.clearDuplicates();
    }

    public boolean[] getBooleanValues() {
        return ArrayKeyHelper.toBooleanValues(this);
    }

    public byte[] getByteValues() {
        return ArrayKeyHelper.toByteValues(this);
    }

    public char[] getCharValues() {
        return ArrayKeyHelper.toCharValues(this);
    }

    public double[] getDoubleValues() {
        return ArrayKeyHelper.toDoubleValues(this);
    }

    public float[] getFloatValues() {
        return ArrayKeyHelper.toFloatValues(this);
    }

    public int[] getIntegerValues() {
        return ArrayKeyHelper.toIntValues(this);
    }

    public long[] getLongValues() {
        return ArrayKeyHelper.toLongValues(this);
    }

    public long[] getNumberValues() {
        return ArrayKeyHelper.toNumberValues(this);
    }

    public short[] getShortValues() {
        return ArrayKeyHelper.toShortValues(this);
    }

    public String[] getStringValues() {
        return ArrayKeyHelper.toStringValues(this);
    }

    public boolean isBooleans() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.BooleanKey.class, this);
    }

    public boolean isBytes() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.ByteKey.class, this);
    }

    public boolean isChars() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.CharKey.class, this);
    }

    public boolean isDoubles() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.DoubleKey.class, this);
    }

    public boolean isFloats() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.FloatKey.class, this);
    }

    public boolean isIntegers() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.IntegerKey.class, this);
    }

    public boolean isLongs() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.LongKey.class, this);
    }

    public boolean isNumbers() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.NumberKey.class, this);
    }

    public boolean isShorts() {
        return ArrayKeyHelper.isAllTypeOf(PrimitiveKey.ShortKey.class, this);
    }

    public boolean isStrings() {
        return ArrayKeyHelper.isAllTypeOf(StringKey.class, this);
    }

    public boolean isValuesTypeOfKey(Class<? extends Key> cls) {
        return ArrayKeyHelper.isAllTypeOf(cls, this);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey remove(Key key) {
        return (ArrayValueKey) super.remove(key);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey removeIf(Predicate<? super Key> predicate) {
        return (ArrayValueKey) super.removeIf((Predicate) predicate);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public ArrayValueKey replaceKey(Key key, Key key2) {
        return (ArrayValueKey) super.replaceKey(key, key2);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey set(int i, Key key) {
        return (ArrayValueKey) super.set(i, key);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey sort(Comparator<? super Key> comparator) {
        return (ArrayValueKey) super.sort((Comparator) comparator);
    }

    public Iterator<String> stringValuesIterator() {
        return ComputeIterator.of(iterator(StringKey.class), new Function() { // from class: rg0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((StringKey) obj).getString();
            }
        });
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey newInstance(Key[] keyArr) {
        return of(keyArr);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ ArrayKey clearDuplicates(Comparator comparator) {
        return clearDuplicates((Comparator<? super Key>) comparator);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList removeIf(Predicate predicate) {
        return removeIf((Predicate<? super Key>) predicate);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList sort(Comparator comparator) {
        return sort((Comparator<? super Key>) comparator);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ ArrayKey removeIf(Predicate predicate) {
        return removeIf((Predicate<? super Key>) predicate);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ ArrayKey sort(Comparator comparator) {
        return sort((Comparator<? super Key>) comparator);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList clearDuplicates(Comparator comparator) {
        return clearDuplicates((Comparator<? super Key>) comparator);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey clearDuplicates(Comparator<? super Key> comparator) {
        return (ArrayValueKey) super.clearDuplicates((Comparator) comparator);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ArrayValueKey remove(int i) {
        return (ArrayValueKey) super.remove(i);
    }

    public static ArrayValueKey of(String[] strArr) {
        return of(ArrayKeyHelper.toStringKeys(strArr));
    }

    public static ArrayValueKey of(byte[] bArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(bArr));
    }

    public static ArrayValueKey of(short[] sArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(sArr));
    }

    public static ArrayValueKey of(int[] iArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(iArr));
    }

    public static ArrayValueKey of(long[] jArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(jArr));
    }

    public static ArrayValueKey of(float[] fArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(fArr));
    }

    public static ArrayValueKey of(double[] dArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(dArr));
    }

    public static ArrayValueKey of(char[] cArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(cArr));
    }

    public static ArrayValueKey of(boolean[] zArr) {
        return of(ArrayKeyHelper.toPrimitiveKeys(zArr));
    }
}

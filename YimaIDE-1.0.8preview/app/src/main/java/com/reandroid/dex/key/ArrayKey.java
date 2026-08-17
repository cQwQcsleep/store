package com.reandroid.dex.key;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayKey<T extends Key> extends KeyList<T> {
    private static final ArrayKey<?> EMPTY = new ArrayKey<>(KeyList.EMPTY_ARRAY);

    public ArrayKey(Key[] keyArr) {
        super(keyArr);
    }

    public static <E extends Key> ArrayKey<E> create(Key... keyArr) {
        return (keyArr == null || keyArr.length == 0) ? empty() : new ArrayKey<>(keyArr);
    }

    public static <E extends Key> ArrayKey<E> empty() {
        return (ArrayKey<E>) EMPTY;
    }

    public static <E extends Key> ArrayKey<E> parse(String str) {
        throw new RuntimeException("ArrayKey.parse not implemented");
    }

    public static <E extends Key> ArrayKey<E> read(SmaliReader smaliReader, char c) throws IOException {
        return create(readElements(smaliReader, c));
    }

    public static Key[] readElements(SmaliReader smaliReader, char c) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        if (smaliReader.getASCII(smaliReader.position()) == c) {
            smaliReader.readASCII();
            return KeyList.EMPTY_ARRAY;
        }
        ArrayCollection arrayCollection = new ArrayCollection();
        while (true) {
            arrayCollection.add(KeyUtil.readKey(smaliReader));
            smaliReader.skipWhitespacesOrComment();
            if (smaliReader.getASCII(smaliReader.position()) == c) {
                SmaliParseException.expect(smaliReader, c);
                return (Key[]) arrayCollection.toArrayFill(new Key[arrayCollection.size()]);
            }
            SmaliParseException.expect(smaliReader, ',');
        }
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> add(T t) {
        return (ArrayKey) super.add((Key) t);
    }

    public void append(SmaliWriter smaliWriter, String str) throws IOException {
        int size = size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            T t = get(i);
            if (z) {
                smaliWriter.append((CharSequence) str);
            }
            if (t == null) {
                smaliWriter.append("# null");
            } else {
                t.append(smaliWriter);
            }
            i++;
            z = true;
        }
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> clearDuplicates() {
        return (ArrayKey) super.clearDuplicates();
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        return !(obj instanceof ArrayKey) ? StringsUtil.compareToString(this, obj) : compareElements((ArrayKey) obj);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ArrayKey) {
            return equalsElements((ArrayKey) obj);
        }
        return false;
    }

    @Override // com.reandroid.dex.key.KeyList
    public int hashCode() {
        return getHashCode();
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> newInstance(Key[] keyArr) {
        if (getClass() == ArrayKey.class) {
            return create(keyArr);
        }
        f63.a("Method not implemented");
        return null;
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> remove(T t) {
        return (ArrayKey) super.remove((Key) t);
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> removeIf(Predicate<? super T> predicate) {
        return (ArrayKey) super.removeIf((Predicate) predicate);
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public ArrayKey<T> replaceKey(Key key, Key key2) {
        return (ArrayKey) super.replaceKey(key, key2);
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> set(int i, T t) {
        return (ArrayKey) super.set(i, (Key) t);
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> sort(Comparator<? super T> comparator) {
        return (ArrayKey) super.sort((Comparator) comparator);
    }

    public String toString(String str) {
        StringWriter stringWriter = new StringWriter();
        SmaliWriter smaliWriter = new SmaliWriter(stringWriter);
        try {
            append(smaliWriter, str);
            smaliWriter.close();
            return stringWriter.toString();
        } catch (IOException e) {
            return "# " + e.toString();
        }
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> clearDuplicates(Comparator<? super T> comparator) {
        return (ArrayKey) super.clearDuplicates((Comparator) comparator);
    }

    @Override // com.reandroid.dex.key.KeyList
    public ArrayKey<T> remove(int i) {
        return (ArrayKey) super.remove(i);
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(smaliWriter, ", ");
    }

    @Override // com.reandroid.dex.key.KeyList
    public String toString() {
        return toString(", ");
    }
}

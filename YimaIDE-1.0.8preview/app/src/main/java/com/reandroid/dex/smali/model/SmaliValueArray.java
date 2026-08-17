package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueArray extends SmaliValue implements Iterable<SmaliValue> {
    private final SmaliSet<SmaliValue> values;

    public SmaliValueArray() {
        SmaliSet<SmaliValue> smaliSet = new SmaliSet<>();
        this.values = smaliSet;
        smaliSet.setParent(this);
    }

    private SmaliValue createNext(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        if (smaliReader.get() == 125) {
            return null;
        }
        return SmaliValueFactory.create(smaliReader);
    }

    public boolean add(SmaliValue smaliValue) {
        return this.values.add(smaliValue);
    }

    public void addValue(Key key) {
        SmaliValue smaliValueCreateForValue = SmaliValueFactory.createForValue(key);
        add(smaliValueCreateForValue);
        smaliValueCreateForValue.setKey(key);
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('{');
        boolean z = false;
        for (SmaliValue smaliValue : this) {
            if (z) {
                smaliWriter.append(',');
            } else {
                smaliWriter.indentPlus();
            }
            smaliWriter.newLine();
            smaliValue.append(smaliWriter);
            z = true;
        }
        if (z) {
            smaliWriter.indentMinus();
            smaliWriter.newLine();
        }
        smaliWriter.append('}');
    }

    public void clear() {
        this.values.clear();
    }

    public boolean contains(SmaliValue smaliValue) {
        return this.values.contains(smaliValue);
    }

    public SmaliValue get(int i) {
        return (SmaliValue) this.values.get(i);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public ArrayValueKey getKey() {
        int size = size();
        Key[] keyArr = new Key[size];
        for (int i = 0; i < size; i++) {
            keyArr[i] = get(i).getKey();
        }
        return ArrayValueKey.of(keyArr);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.ARRAY;
    }

    public SmaliSet<SmaliValue> getValues() {
        return this.values;
    }

    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<SmaliValue> iterator() {
        return this.values.iterator();
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        SmaliParseException.expect(smaliReader, '{');
        smaliReader.skipWhitespaces();
        while (true) {
            SmaliValue smaliValueCreateNext = createNext(smaliReader);
            if (smaliValueCreateNext == null) {
                smaliReader.skipWhitespaces();
                SmaliParseException.expect(smaliReader, '}');
                return;
            } else {
                add(smaliValueCreateNext);
                smaliValueCreateNext.parse(smaliReader);
                smaliReader.skipWhitespacesOrComment();
                if (smaliReader.get() == 44) {
                    smaliReader.skip(1);
                }
            }
        }
    }

    public SmaliValue remove(int i) {
        return (SmaliValue) this.values.remove(i);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        clear();
        Iterator<T> it = ((ArrayValueKey) key).iterator();
        while (it.hasNext()) {
            addValue((Key) it.next());
        }
    }

    public int size() {
        return this.values.size();
    }

    public boolean remove(SmaliValue smaliValue) {
        return this.values.remove(smaliValue);
    }
}

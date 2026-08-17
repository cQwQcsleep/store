package com.reandroid.dex.dexopt;

import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.collection.ArraySupplierIterator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodBitmap extends FixedBlockContainer implements LinkableProfileItem, JSONConvert<JSONArray> {
    private final IntegerReference countReference;
    private final BooleanList postStartupList;
    private final BooleanList startupList;

    public MethodBitmap(IntegerReference integerReference) {
        super(2);
        this.countReference = integerReference;
        BooleanList booleanList = new BooleanList(integerReference);
        this.startupList = booleanList;
        BooleanList booleanList2 = new BooleanList(integerReference);
        this.postStartupList = booleanList2;
        addChild(0, booleanList);
        addChild(1, booleanList2);
    }

    private void updateCountReference() {
        this.countReference.set(size());
    }

    public int countBytes() {
        return BitItem.bitsToBytes(size() * 2);
    }

    public void ensureSize(int i) {
        if (i > size()) {
            setSize(i);
        }
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONArray jSONArray) {
        int length = jSONArray == null ? 0 : jSONArray.length();
        ensureSize(length);
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            getOrCreate(jSONObject.getInt("id")).fromJson(jSONObject);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MethodBitmapElement get(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            return null;
        }
        return new MethodBitmapElement((BooleanBit) this.startupList.get(i), (BooleanBit) this.postStartupList.get(i));
    }

    public byte[] getBytes() {
        int size = size();
        if (size == 0) {
            return new byte[0];
        }
        BitItem bitItem = new BitItem(size * 2);
        this.postStartupList.writeTo(bitItem, this.startupList.writeTo(bitItem, 0));
        return bitItem.getBytes();
    }

    public MethodBitmapElement getOrCreate(int i) {
        ensureSize(i + 1);
        return get(i);
    }

    public Iterator<MethodBitmapElement> iterator() {
        return ArraySupplierIterator.of(new ArraySupplier<MethodBitmapElement>() { // from class: com.reandroid.dex.dexopt.MethodBitmap.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.common.ArraySupplier
            public MethodBitmapElement get(int i) {
                return MethodBitmap.this.get(i);
            }

            @Override // com.reandroid.common.CountSupplier
            public int getCount() {
                return MethodBitmap.this.size();
            }
        });
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        LinkableProfileItem.linkAll(dexFile, iterator());
    }

    public void moveTo(MethodBitmapElement methodBitmapElement, int i) {
        ensureSize(i + 1);
        this.startupList.moveTo(methodBitmapElement.startup(), i);
        this.postStartupList.moveTo(methodBitmapElement.postStartup(), i);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        BooleanList booleanList = this.startupList;
        BooleanList booleanList2 = this.postStartupList;
        int i = this.countReference.get();
        booleanList.setSize(i);
        booleanList2.setSize(i);
        BitItem bitItem = new BitItem(i * 2);
        bitItem.onReadBytes(blockReader);
        booleanList2.readFrom(bitItem, booleanList.readFrom(bitItem, 0));
    }

    public void onRefreshed() {
        super.onRefreshed();
        updateCountReference();
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        byte[] bytes = getBytes();
        int length = bytes.length;
        outputStream.write(bytes, 0, length);
        return length;
    }

    public boolean remove(MethodBitmapElement methodBitmapElement) {
        if (!this.startupList.remove(methodBitmapElement.startup())) {
            return false;
        }
        this.postStartupList.remove(methodBitmapElement.postStartup());
        updateCountReference();
        return true;
    }

    public void removeIf(Predicate<? super MethodBitmapElement> predicate) {
        int size = size();
        int i = 0;
        while (i < size) {
            if (predicate.test(get(i)) && remove(i)) {
                i--;
                size = size();
            }
            i++;
        }
    }

    public void setSize(int i) {
        this.startupList.setSize(i);
        this.postStartupList.setSize(i);
        this.countReference.set(i);
    }

    public int size() {
        return this.startupList.size();
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONArray toJson() {
        int size = size();
        JSONArray jSONArray = new JSONArray(size);
        for (int i = 0; i < size; i++) {
            MethodBitmapElement methodBitmapElement = get(i);
            if (methodBitmapElement.getFlags() != 0) {
                jSONArray.put(methodBitmapElement.toJson());
            }
        }
        return jSONArray;
    }

    public String toString() {
        return "size=" + size();
    }

    public void update(DexFile dexFile, boolean z) {
        if (!z) {
            LinkableProfileItem.updateAll(dexFile, iterator());
        } else {
            setSize(dexFile.getCount(SectionType.METHOD_ID));
            link(dexFile);
        }
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        update(dexFile, false);
    }

    public boolean remove(int i) {
        if (this.startupList.remove(i) == 0) {
            return false;
        }
        this.postStartupList.remove(i);
        updateCountReference();
        return true;
    }
}

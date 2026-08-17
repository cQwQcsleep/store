package com.reandroid.arsc.pool;

import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.StringCreator;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.list.StringItemList;
import com.reandroid.utils.collection.CollectionUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TypeStringPool extends StringPool<TypeString> {
    private final IntegerReference typeIdOffsetReference;

    public TypeStringPool(boolean z, IntegerItem integerItem) {
        super(z, false, new StringCreator() { // from class: sve
            @Override // com.reandroid.arsc.item.StringCreator
            public final StringItem newInstance(boolean z2) {
                return new TypeString(z2);
            }
        });
        this.typeIdOffsetReference = integerItem;
    }

    private void ensureStringsSize(int i) {
        StringItemList<TypeString> stringsArray = getStringsArray();
        int size = stringsArray.size();
        if (i > size) {
            stringsArray.setSize(i);
            while (size < i) {
                ((TypeString) stringsArray.get(size)).set("type-" + size);
                size++;
            }
        }
    }

    private int toIndex(int i) {
        return (i - 1) - this.typeIdOffsetReference.get();
    }

    private int toTypeId(int i) {
        return i + 1 + this.typeIdOffsetReference.get();
    }

    public TypeString getById(int i) {
        return (TypeString) super.get(toIndex(i));
    }

    public TypeString getByName(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            TypeString typeString = get(i);
            if (str.equals(typeString.get())) {
                return typeString;
            }
        }
        return null;
    }

    public int getLastId() {
        return toTypeId(size() - 1);
    }

    @Override // com.reandroid.arsc.pool.StringPool
    @Deprecated
    public final TypeString getOrCreate(String str) {
        TypeString typeString = (TypeString) CollectionUtil.getSingle(getAll(str));
        if (typeString != null) {
            return typeString;
        }
        kg9.a("Can not create TypeString (", str, ") without type id. use getOrCreate(typeId, typeName)");
        return null;
    }

    public int idOf(TypeString typeString) {
        if (typeString == null) {
            return 0;
        }
        return toTypeId(typeString.getIndex());
    }

    public int idOf(String str) {
        return idOf(getByName(str));
    }

    public TypeString getOrCreate(int i, String str) {
        ensureStringsSize(toIndex(i) + 1);
        TypeString byId = getById(i);
        byId.set(str);
        return byId;
    }
}

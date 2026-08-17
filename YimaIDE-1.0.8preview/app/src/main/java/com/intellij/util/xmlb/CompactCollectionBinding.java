package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.serialization.MutableAccessor;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class CompactCollectionBinding implements NestedBinding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final MutableAccessor accessor;
    private final String name;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 10) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 10) ? 2 : 3];
        switch (i) {
            case 1:
            case 10:
                objArr[0] = "com/intellij/util/xmlb/CompactCollectionBinding";
                break;
            case 2:
            case 5:
            case 8:
            case 11:
                objArr[0] = "element";
                break;
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "bean";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "parent";
                break;
            case 9:
            case 12:
                objArr[0] = "adapter";
                break;
            default:
                objArr[0] = "accessor";
                break;
        }
        if (i == 1) {
            objArr[1] = "getAccessor";
        } else if (i != 10) {
            objArr[1] = "com/intellij/util/xmlb/CompactCollectionBinding";
        } else {
            objArr[1] = "deserialize";
        }
        switch (i) {
            case 1:
            case 10:
                break;
            case 2:
                objArr[2] = "deserializeToJson";
                break;
            case 3:
                objArr[2] = "toJson";
                break;
            case 4:
            case 5:
                objArr[2] = "setFromJson";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "serialize";
                break;
            case 8:
            case 9:
                objArr[2] = "deserialize";
                break;
            case 11:
            case 12:
                objArr[2] = "isBoundTo";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 10) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public CompactCollectionBinding(MutableAccessor mutableAccessor) {
        if (mutableAccessor == null) {
            $$$reportNull$$$0(0);
        }
        this.accessor = mutableAccessor;
        this.name = mutableAccessor.getName();
    }

    private boolean isNameEqual(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(this.name)) {
            return true;
        }
        if (str.length() == this.name.length() + 2 && str.startsWith("my") && Character.isUpperCase(str.charAt(2))) {
            String str2 = this.name;
            if (str.regionMatches(true, 2, str2, 0, str2.length())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object obj, T t, DomAdapter<T> domAdapter) {
        String attributeValue;
        if (t == null) {
            $$$reportNull$$$0(8);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(9);
        }
        List list = (List) this.accessor.read(obj);
        list.clear();
        if (domAdapter.getName(t).equals(this.name)) {
            for (T t2 : domAdapter.getChildren(t)) {
                if (domAdapter.getName(t2).equals("item") && (attributeValue = domAdapter.getAttributeValue(t2, "value")) != null) {
                    list.add(attributeValue);
                }
            }
        } else {
            T child = domAdapter.getChild(t, "value");
            if (child != null) {
                child = domAdapter.getChild(child, "list");
            }
            if (child != null) {
                for (T t3 : domAdapter.getChildren(child)) {
                    String attributeValue2 = domAdapter.getName(t3).equals("item") ? domAdapter.getAttributeValue(t3, "itemvalue") : null;
                    if (attributeValue2 != null) {
                        list.add(attributeValue2);
                    }
                }
            }
        }
        return list;
    }

    @Override // com.intellij.util.xmlb.NestedBinding
    public MutableAccessor getAccessor() {
        MutableAccessor mutableAccessor = this.accessor;
        if (mutableAccessor == null) {
            $$$reportNull$$$0(1);
        }
        return mutableAccessor;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(11);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(12);
        }
        String name = domAdapter.getName(t);
        if (isNameEqual(name)) {
            return true;
        }
        if (name.equals("option")) {
            return isNameEqual(domAdapter.getAttributeValue(t, "name"));
        }
        return false;
    }
}

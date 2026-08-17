package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.serialization.ClassUtil;
import com.intellij.serialization.MutableAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class TextBinding implements NestedBinding {
    private final MutableAccessor accessor;
    private final Class<?> valueClass;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 5 ? 3 : 2];
        switch (i) {
            case 1:
            case 4:
            case 8:
            case 10:
                objArr[0] = "element";
                break;
            case 2:
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "bean";
                break;
            case 5:
                objArr[0] = "com/intellij/util/xmlb/TextBinding";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "parent";
                break;
            case 9:
            case 11:
                objArr[0] = "adapter";
                break;
            case 12:
                objArr[0] = "context";
                break;
            default:
                objArr[0] = "accessor";
                break;
        }
        if (i != 5) {
            objArr[1] = "com/intellij/util/xmlb/TextBinding";
        } else {
            objArr[1] = "getAccessor";
        }
        switch (i) {
            case 1:
                objArr[2] = "deserializeToJson";
                break;
            case 2:
                objArr[2] = "toJson";
                break;
            case 3:
            case 4:
                objArr[2] = "setFromJson";
                break;
            case 5:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "serialize";
                break;
            case 8:
            case 9:
                objArr[2] = "isBoundTo";
                break;
            case 10:
            case 11:
                objArr[2] = "deserialize";
                break;
            case 12:
                objArr[2] = "setValue";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 5) {
            throw new IllegalStateException(str2);
        }
    }

    public TextBinding(MutableAccessor mutableAccessor) {
        if (mutableAccessor == null) {
            $$$reportNull$$$0(0);
        }
        this.accessor = mutableAccessor;
        this.valueClass = ClassUtil.typeToClass(mutableAccessor.getGenericType());
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object obj, T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(10);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(11);
        }
        return obj;
    }

    @Override // com.intellij.util.xmlb.NestedBinding
    public MutableAccessor getAccessor() {
        MutableAccessor mutableAccessor = this.accessor;
        if (mutableAccessor == null) {
            $$$reportNull$$$0(5);
        }
        return mutableAccessor;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(8);
        }
        if (domAdapter != null) {
            return false;
        }
        $$$reportNull$$$0(9);
        return false;
    }

    public void setValue(Object obj, String str) {
        if (obj == null) {
            $$$reportNull$$$0(12);
        }
        XmlSerializerImpl.doSet(obj, str, this.accessor, this.valueClass);
    }
}

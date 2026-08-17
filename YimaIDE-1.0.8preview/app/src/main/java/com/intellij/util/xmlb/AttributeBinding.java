package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.serialization.ClassUtil;
import com.intellij.serialization.MutableAccessor;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.xmlb.annotations.Attribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class AttributeBinding implements NestedBinding {
    private final MutableAccessor accessor;
    private final Converter<Object> converter;
    final String name;
    final Class<?> valueClass;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 6 || i == 12) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 6 || i == 12) ? 2 : 3];
        switch (i) {
            case 1:
            case 9:
            case 14:
                objArr[0] = "element";
                break;
            case 2:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 13:
                objArr[0] = "bean";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 12:
                objArr[0] = "com/intellij/util/xmlb/AttributeBinding";
                break;
            case 5:
                objArr[0] = "data";
                break;
            case 8:
                objArr[0] = "parent";
                break;
            case 10:
            case 15:
                objArr[0] = "adapter";
                break;
            case 11:
                objArr[0] = "context";
                break;
            default:
                objArr[0] = "accessor";
                break;
        }
        if (i == 3) {
            objArr[1] = "toJson";
        } else if (i == 6) {
            objArr[1] = "getAccessor";
        } else if (i != 12) {
            objArr[1] = "com/intellij/util/xmlb/AttributeBinding";
        } else {
            objArr[1] = "deserialize";
        }
        switch (i) {
            case 1:
                objArr[2] = "deserializeToJson";
                break;
            case 2:
                objArr[2] = "toJson";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 12:
                break;
            case 4:
            case 5:
                objArr[2] = "setFromJson";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "serialize";
                break;
            case 9:
            case 10:
            case 11:
                objArr[2] = "deserialize";
                break;
            case 13:
                objArr[2] = "setValue";
                break;
            case 14:
            case 15:
                objArr[2] = "isBoundTo";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 6 && i != 12) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public AttributeBinding(MutableAccessor mutableAccessor, Attribute attribute) {
        if (mutableAccessor == null) {
            $$$reportNull$$$0(0);
        }
        this.accessor = mutableAccessor;
        Converter<Object> converter = null;
        String strValue = attribute == null ? null : attribute.value();
        this.name = (strValue == null || strValue.isEmpty()) ? mutableAccessor.getName() : strValue;
        Class<? extends Converter> clsConverter = attribute == null ? null : attribute.converter();
        if (clsConverter != null && clsConverter != Converter.class) {
            converter = (Converter) ReflectionUtil.newInstance(clsConverter, false);
        }
        this.converter = converter;
        this.valueClass = ClassUtil.typeToClass(mutableAccessor.getGenericType());
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object obj, T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(9);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(10);
        }
        return obj;
    }

    @Override // com.intellij.util.xmlb.NestedBinding
    public MutableAccessor getAccessor() {
        MutableAccessor mutableAccessor = this.accessor;
        if (mutableAccessor == null) {
            $$$reportNull$$$0(6);
        }
        return mutableAccessor;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(14);
        }
        if (domAdapter != null) {
            return false;
        }
        $$$reportNull$$$0(15);
        return false;
    }

    public void setValue(Object obj, String str) {
        if (obj == null) {
            $$$reportNull$$$0(13);
        }
        Converter<Object> converter = this.converter;
        MutableAccessor mutableAccessor = this.accessor;
        if (converter == null) {
            XmlSerializerImpl.doSet(obj, str, mutableAccessor, this.valueClass);
        } else {
            mutableAccessor.set(obj, str == null ? null : converter.fromString(str));
        }
    }

    public String toString() {
        return "AttributeBinding(name=" + this.name + ")";
    }
}

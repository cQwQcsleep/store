package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class XmlSerializer {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 5 || i == 10) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 5 || i == 10) ? 2 : 3];
        switch (i) {
            case 2:
            case 5:
            case 10:
                objArr[0] = "com/intellij/util/xmlb/XmlSerializer";
                break;
            case 3:
            case 8:
            case 12:
            case 14:
                objArr[0] = "element";
                break;
            case 4:
            case 9:
                objArr[0] = "aClass";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "url";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
            case 13:
                objArr[0] = "bean";
                break;
            default:
                objArr[0] = "object";
                break;
        }
        if (i == 2) {
            objArr[1] = "serialize";
        } else if (i == 5) {
            objArr[1] = "deserialize";
        } else if (i != 10) {
            objArr[1] = "com/intellij/util/xmlb/XmlSerializer";
        } else {
            objArr[1] = "getBeanBinding";
        }
        switch (i) {
            case 2:
            case 5:
            case 10:
                break;
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "deserialize";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "deserializeInto";
                break;
            case 9:
                objArr[2] = "getBeanBinding";
                break;
            case 11:
            case 12:
            case 13:
            case 14:
                objArr[2] = "serializeInto";
                break;
            default:
                objArr[2] = "serialize";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 5 && i != 10) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static BeanBinding getBeanBinding(Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(9);
        }
        BeanBinding beanBinding = (BeanBinding) XmlSerializerImpl.serializer.getRootBinding(cls, cls);
        if (beanBinding == null) {
            $$$reportNull$$$0(10);
        }
        return beanBinding;
    }
}

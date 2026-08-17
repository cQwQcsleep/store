package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.XCollection;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class XmlSerializerUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 4 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 4 ? 3 : 2];
        switch (i) {
            case 1:
                objArr[0] = "to";
                break;
            case 2:
            default:
                objArr[0] = "from";
                break;
            case 3:
                objArr[0] = "aClass";
                break;
            case 4:
                objArr[0] = "com/intellij/util/xmlb/XmlSerializerUtil";
                break;
            case 5:
                objArr[0] = "property";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "optionTag";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[0] = "annotation";
                break;
        }
        if (i != 4) {
            objArr[1] = "com/intellij/util/xmlb/XmlSerializerUtil";
        } else {
            objArr[1] = "getAccessors";
        }
        switch (i) {
            case 2:
                objArr[2] = "createCopy";
                break;
            case 3:
                objArr[2] = "getAccessors";
                break;
            case 4:
                break;
            case 5:
                objArr[2] = "getPropertyFilter";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "getConverter";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "getElementTypes";
                break;
            default:
                objArr[2] = "copyBean";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 4) {
            throw new IllegalStateException(str2);
        }
    }

    private XmlSerializerUtil() {
    }

    public static Class<? extends Converter> getConverter(OptionTag optionTag) {
        if (optionTag == null) {
            $$$reportNull$$$0(6);
        }
        Class<? extends Converter> clsConverter = optionTag.converter();
        if (clsConverter == Converter.class) {
            return null;
        }
        return clsConverter;
    }

    public static Class<?>[] getElementTypes(AbstractCollection abstractCollection) {
        if (abstractCollection == null) {
            $$$reportNull$$$0(8);
        }
        return abstractCollection.elementTypes();
    }

    public static Class<?>[] getElementTypes(XCollection xCollection) {
        if (xCollection == null) {
            $$$reportNull$$$0(7);
        }
        return xCollection.elementTypes();
    }
}

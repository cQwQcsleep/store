package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.serialization.MutableAccessor;
import com.intellij.util.xmlb.annotations.Tag;
import java.util.List;
import org.jdom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class JDOMElementBinding implements MultiNodeBinding, NestedBinding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final MutableAccessor accessor;
    private final String tagName;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 11 || i == 15) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 11 || i == 15) ? 2 : 3];
        switch (i) {
            case 1:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 13:
            case 16:
                objArr[0] = "element";
                break;
            case 2:
            case 3:
            case 11:
            case 15:
                objArr[0] = "com/intellij/util/xmlb/JDOMElementBinding";
                break;
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "bean";
                break;
            case 8:
                objArr[0] = "parent";
                break;
            case 9:
            case 12:
                objArr[0] = "elements";
                break;
            case 10:
            case 14:
            case 17:
                objArr[0] = "adapter";
                break;
            default:
                objArr[0] = "accessor";
                break;
        }
        if (i == 2) {
            objArr[1] = "deserializeToJson";
        } else if (i == 3) {
            objArr[1] = "getAccessor";
        } else if (i == 11) {
            objArr[1] = "deserializeList";
        } else if (i != 15) {
            objArr[1] = "com/intellij/util/xmlb/JDOMElementBinding";
        } else {
            objArr[1] = "deserialize";
        }
        switch (i) {
            case 1:
                objArr[2] = "deserializeToJson";
                break;
            case 2:
            case 3:
            case 11:
            case 15:
                break;
            case 4:
                objArr[2] = "toJson";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "setFromJson";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "serialize";
                break;
            case 9:
            case 10:
                objArr[2] = "deserializeList";
                break;
            case 12:
                objArr[2] = "doDeserializeListToJson";
                break;
            case 13:
            case 14:
                objArr[2] = "deserialize";
                break;
            case 16:
            case 17:
                objArr[2] = "isBoundTo";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 11 && i != 15) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public JDOMElementBinding(MutableAccessor mutableAccessor) {
        if (mutableAccessor == null) {
            $$$reportNull$$$0(0);
        }
        this.accessor = mutableAccessor;
        Tag tag = (Tag) mutableAccessor.getAnnotation(Tag.class);
        String strValue = tag == null ? null : tag.value();
        this.tagName = (strValue == null || strValue.isEmpty()) ? mutableAccessor.getName() : strValue;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object obj, T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(13);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(14);
        }
        this.accessor.set(obj, t);
        if (obj == null) {
            $$$reportNull$$$0(15);
        }
        return obj;
    }

    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public <T> Object deserializeList(Object obj, List<? extends T> list, DomAdapter<T> domAdapter) {
        if (list == null) {
            $$$reportNull$$$0(9);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(10);
        }
        if (domAdapter != JdomAdapter.INSTANCE) {
            c41.a("XmlElement is not supported by JDOMElementBinding");
            return null;
        }
        boolean zIsArray = this.accessor.getValueClass().isArray();
        MutableAccessor mutableAccessor = this.accessor;
        if (zIsArray) {
            mutableAccessor.set(obj, list.toArray(new Element[0]));
        } else {
            mutableAccessor.set(obj, list.get(0));
        }
        if (obj == null) {
            $$$reportNull$$$0(11);
        }
        return obj;
    }

    @Override // com.intellij.util.xmlb.NestedBinding
    public MutableAccessor getAccessor() {
        MutableAccessor mutableAccessor = this.accessor;
        if (mutableAccessor == null) {
            $$$reportNull$$$0(3);
        }
        return mutableAccessor;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(16);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(17);
        }
        return domAdapter.getName(t).equals(this.tagName);
    }

    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public boolean isMulti() {
        return this.accessor.getValueClass().isArray();
    }
}

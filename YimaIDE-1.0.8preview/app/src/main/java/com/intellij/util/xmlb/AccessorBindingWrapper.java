package com.intellij.util.xmlb;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.text.StringUtilRt;
import com.intellij.serialization.MutableAccessor;
import com.intellij.util.xml.dom.XmlElement;
import com.intellij.util.xmlb.annotations.Property;
import java.awt.Rectangle;
import java.util.List;
import org.jdom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class AccessorBindingWrapper implements MultiNodeBinding, NestedBinding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final MutableAccessor accessor;
    private final Property.Style beanStyle;
    private final Binding binding;
    final boolean isFlat;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 16) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 16) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "binding";
                break;
            case 2:
            case 16:
                objArr[0] = "com/intellij/util/xmlb/AccessorBindingWrapper";
                break;
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "bean";
                break;
            case 5:
            case 8:
            case 10:
            case 11:
            case 12:
            case 17:
                objArr[0] = "element";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "parent";
                break;
            case 9:
            case 14:
            case 18:
                objArr[0] = "adapter";
                break;
            case 13:
            case 15:
                objArr[0] = "elements";
                break;
            default:
                objArr[0] = "accessor";
                break;
        }
        if (i == 2) {
            objArr[1] = "getAccessor";
        } else if (i != 16) {
            objArr[1] = "com/intellij/util/xmlb/AccessorBindingWrapper";
        } else {
            objArr[1] = "doDeserializeListToJson";
        }
        switch (i) {
            case 2:
            case 16:
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
            case 10:
                objArr[2] = "deserializeToJson";
                break;
            case 11:
            case 12:
                objArr[2] = "deserializeUnsafe";
                break;
            case 13:
            case 14:
                objArr[2] = "deserializeList";
                break;
            case 15:
                objArr[2] = "doDeserializeListToJson";
                break;
            case 17:
            case 18:
                objArr[2] = "isBoundTo";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 16) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public AccessorBindingWrapper(MutableAccessor mutableAccessor, Binding binding, boolean z, Property.Style style) {
        if (mutableAccessor == null) {
            $$$reportNull$$$0(0);
        }
        if (binding == null) {
            $$$reportNull$$$0(1);
        }
        this.accessor = mutableAccessor;
        this.binding = binding;
        this.isFlat = z;
        this.beanStyle = style;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object obj, T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(8);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(9);
        }
        return domAdapter == JdomAdapter.INSTANCE ? deserializeUnsafe(obj, (Element) t) : deserializeUnsafe(obj, (XmlElement) t);
    }

    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public <T> Object deserializeList(Object obj, List<? extends T> list, DomAdapter<T> domAdapter) {
        if (list == null) {
            $$$reportNull$$$0(13);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(14);
        }
        Object obj2 = this.accessor.read(obj);
        if ((this.binding instanceof BeanBinding) && !this.accessor.isWritable()) {
            ((BeanBinding) this.binding).deserializeInto(obj2, list.get(0), domAdapter);
            return null;
        }
        Object objDeserializeList = MapBindingKt.deserializeList(this.binding, obj2, list, domAdapter);
        if (obj2 == objDeserializeList) {
            return null;
        }
        this.accessor.set(obj, objDeserializeList);
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0071  */
    /* JADX WARN: Code duplicated, block: B:24:0x007b  */
    public Object deserializeUnsafe(Object obj, Element element) {
        Object objDeserialize;
        if (element == null) {
            $$$reportNull$$$0(11);
        }
        Object obj2 = this.accessor.read(obj);
        if ((this.binding instanceof BeanBinding) && !this.accessor.isWritable()) {
            ((BeanBinding) this.binding).deserializeInto(obj2, element);
            return obj;
        }
        if (this.beanStyle == Property.Style.ATTRIBUTE) {
            Binding binding = this.binding;
            if ((binding instanceof BeanBinding) && ((BeanBinding) binding).beanClass == Rectangle.class) {
                String attributeValue = element.getAttributeValue("x");
                String attributeValue2 = element.getAttributeValue("y");
                String attributeValue3 = element.getAttributeValue("width");
                String attributeValue4 = element.getAttributeValue("height");
                if (attributeValue != null && attributeValue2 != null && attributeValue3 != null && attributeValue4 != null) {
                    objDeserialize = new Rectangle(StringUtilRt.parseInt(attributeValue, 0), StringUtilRt.parseInt(attributeValue2, 0), StringUtilRt.parseInt(attributeValue3, 0), StringUtilRt.parseInt(attributeValue4, 0));
                }
            } else {
                objDeserialize = this.binding.deserialize(obj2, element, JdomAdapter.INSTANCE);
            }
            if (obj2 != objDeserialize) {
                this.accessor.set(obj, objDeserialize);
            }
        } else {
            objDeserialize = this.binding.deserialize(obj2, element, JdomAdapter.INSTANCE);
            if (obj2 != objDeserialize) {
                this.accessor.set(obj, objDeserialize);
            }
        }
        return obj;
    }

    @Override // com.intellij.util.xmlb.NestedBinding
    public MutableAccessor getAccessor() {
        MutableAccessor mutableAccessor = this.accessor;
        if (mutableAccessor == null) {
            $$$reportNull$$$0(2);
        }
        return mutableAccessor;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T t, DomAdapter<T> domAdapter) {
        if (t == null) {
            $$$reportNull$$$0(17);
        }
        if (domAdapter == null) {
            $$$reportNull$$$0(18);
        }
        Binding binding = this.binding;
        return binding instanceof MapBinding ? ((MapBinding) binding).isBoundToWithoutProperty(domAdapter.getName(t)) : binding.isBoundTo(t, domAdapter);
    }

    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public boolean isMulti() {
        Binding binding = this.binding;
        return (binding instanceof MultiNodeBinding) && ((MultiNodeBinding) binding).isMulti();
    }

    public String toString() {
        return this.binding.toString();
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0071  */
    /* JADX WARN: Code duplicated, block: B:24:0x007b  */
    public Object deserializeUnsafe(Object obj, XmlElement xmlElement) {
        Object objDeserialize;
        if (xmlElement == null) {
            $$$reportNull$$$0(12);
        }
        Object obj2 = this.accessor.read(obj);
        if ((this.binding instanceof BeanBinding) && !this.accessor.isWritable()) {
            ((BeanBinding) this.binding).deserializeInto(obj2, xmlElement);
            return obj;
        }
        if (this.beanStyle == Property.Style.ATTRIBUTE) {
            Binding binding = this.binding;
            if ((binding instanceof BeanBinding) && ((BeanBinding) binding).beanClass == Rectangle.class) {
                String attributeValue = xmlElement.getAttributeValue("x");
                String attributeValue2 = xmlElement.getAttributeValue("y");
                String attributeValue3 = xmlElement.getAttributeValue("width");
                String attributeValue4 = xmlElement.getAttributeValue("height");
                if (attributeValue != null && attributeValue2 != null && attributeValue3 != null && attributeValue4 != null) {
                    objDeserialize = new Rectangle(StringUtilRt.parseInt(attributeValue, 0), StringUtilRt.parseInt(attributeValue2, 0), StringUtilRt.parseInt(attributeValue3, 0), StringUtilRt.parseInt(attributeValue4, 0));
                }
            } else {
                objDeserialize = this.binding.deserialize(obj2, xmlElement, XmlDomAdapter.INSTANCE);
            }
            if (obj2 != objDeserialize) {
                this.accessor.set(obj, objDeserialize);
            }
        } else {
            objDeserialize = this.binding.deserialize(obj2, xmlElement, XmlDomAdapter.INSTANCE);
            if (obj2 != objDeserialize) {
                this.accessor.set(obj, objDeserialize);
            }
        }
        return obj;
    }
}

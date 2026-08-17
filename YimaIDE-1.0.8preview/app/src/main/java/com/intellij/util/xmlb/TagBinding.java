package com.intellij.util.xmlb;

import com.intellij.serialization.ClassUtil;
import com.intellij.serialization.MutableAccessor;
import defpackage.eyf;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001Bi\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0002J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u001cH\u0016J\"\u0010$\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010%\u001a\u00020&2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010'\u001a\u0004\u0018\u00010\u001c2\u0006\u0010 \u001a\u00020&H\u0016J7\u0010(\u001a\u00020\u0016\"\b\b\u0000\u0010)*\u00020\u00162\b\u0010*\u001a\u0004\u0018\u00010\u00162\u0006\u0010 \u001a\u0002H)2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H)0,H\u0016¢\u0006\u0002\u0010-J-\u0010.\u001a\u00020\u000f\"\b\b\u0000\u0010)*\u00020\u00162\u0006\u0010 \u001a\u0002H)2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H)0,H\u0016¢\u0006\u0002\u0010/J\b\u00100\u001a\u00020\u0007H\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u00061"}, d2 = {"Lcom/intellij/util/xmlb/TagBinding;", "Lcom/intellij/util/xmlb/NestedBinding;", "binding", "Lcom/intellij/util/xmlb/Binding;", "accessor", "Lcom/intellij/serialization/MutableAccessor;", "nameAttributeValue", "", "converterClass", "Ljava/lang/Class;", "Lcom/intellij/util/xmlb/Converter;", "tag", "nameAttribute", "valueAttribute", "serializeBeanBindingWithoutWrapperTag", "", "textIfTagValueEmpty", "<init>", "(Lcom/intellij/util/xmlb/Binding;Lcom/intellij/serialization/MutableAccessor;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAccessor", "()Lcom/intellij/serialization/MutableAccessor;", "converter", "", "setValue", "", "bean", "value", "toJson", "Lkotlinx/serialization/json/JsonElement;", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "setFromJson", "element", "propertyName", "getPropertyName", "()Ljava/lang/String;", "serialize", "parent", "Lorg/jdom/Element;", "deserializeToJson", "deserialize", "T", "context", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "(Ljava/lang/Object;Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Ljava/lang/Object;", "isBoundTo", "(Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Z", "toString", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TagBinding implements NestedBinding {
    private final MutableAccessor accessor;
    public final Binding binding;
    private final Converter<Object> converter;
    private final String nameAttribute;
    private final String nameAttributeValue;
    private final boolean serializeBeanBindingWithoutWrapperTag;
    private final String tag;
    private final String textIfTagValueEmpty;
    private final String valueAttribute;

    public TagBinding(Binding binding, MutableAccessor mutableAccessor, String str, Class<? extends Converter<?>> cls, String str2, String str3, String str4, boolean z, String str5) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        mutableAccessor.getClass();
        str2.getClass();
        str5.getClass();
        this.binding = binding;
        this.accessor = mutableAccessor;
        this.nameAttributeValue = str;
        this.tag = str2;
        this.nameAttribute = str3;
        this.valueAttribute = str4;
        this.serializeBeanBindingWithoutWrapperTag = z;
        this.textIfTagValueEmpty = str5;
        Converter<?> converter = null;
        if (cls != null) {
            Constructor<? extends Converter<?>> declaredConstructor = cls.getDeclaredConstructor(null);
            try {
                declaredConstructor.setAccessible(true);
            } catch (SecurityException unused) {
            }
            Converter<?> converterNewInstance = declaredConstructor.newInstance(null);
            converterNewInstance.getClass();
            converter = converterNewInstance;
        }
        this.converter = converter;
    }

    private final void setValue(Object bean, String value) {
        if (this.converter != null) {
            getAccessor().set(bean, value != null ? this.converter.fromString(value) : null);
            return;
        }
        try {
            XmlSerializerImpl.doSet(bean, value, getAccessor(), ClassUtil.typeToClass(getAccessor().getGenericType()));
        } catch (Exception e) {
            eyf.a("Cannot set value for field ", getAccessor().getName(), e);
        }
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object context, T element, DomAdapter<T> adapter) {
        Binding binding;
        element.getClass();
        adapter.getClass();
        context.getClass();
        String str = this.valueAttribute;
        if (str != null) {
            String attributeValue = adapter.getAttributeValue(element, str);
            if (this.converter != null) {
                getAccessor().set(context, attributeValue != null ? this.converter.fromString(attributeValue) : null);
                return context;
            }
            if (this.binding == null) {
                XmlSerializerImpl.doSet(context, attributeValue, getAccessor(), ClassUtil.typeToClass(getAccessor().getGenericType()));
                return context;
            }
            getAccessor().set(context, this.binding.deserialize(context, element, adapter));
            return context;
        }
        if (this.converter != null || (binding = this.binding) == null) {
            setValue(context, adapter.getTextValue(element, this.textIfTagValueEmpty));
            return context;
        }
        if (binding instanceof BeanBinding) {
            if (!this.serializeBeanBindingWithoutWrapperTag) {
                element = adapter.firstElement(element);
            }
            getAccessor().set(context, element != null ? ((BeanBinding) this.binding).deserialize(null, element, adapter) : null);
            return context;
        }
        if (!(binding instanceof CollectionBinding) && !(binding instanceof MapBinding)) {
            wdh.a("Binding ", this.binding, " is not expected");
            return null;
        }
        List<T> children = adapter.getChildren(element);
        if (getAccessor().isWritable()) {
            Object obj = getAccessor().read(context);
            if (!children.isEmpty() || obj != null) {
                Binding binding2 = this.binding;
                binding2.getClass();
                getAccessor().set(context, ((MultiNodeBinding) binding2).deserializeList(obj, children, adapter));
                return context;
            }
        } else {
            Object obj2 = getAccessor().read(context);
            if (obj2 != null || !children.isEmpty()) {
                Binding binding3 = this.binding;
                binding3.getClass();
                Object objDeserializeList = ((MultiNodeBinding) binding3).deserializeList(obj2, children, adapter);
                if (obj2 != objDeserializeList) {
                    getAccessor().set(context, objDeserializeList);
                }
            }
        }
        return context;
    }

    @Override // com.intellij.util.xmlb.NestedBinding
    public MutableAccessor getAccessor() {
        return this.accessor;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T element, DomAdapter<T> adapter) {
        element.getClass();
        adapter.getClass();
        if (!Intrinsics.areEqual(adapter.getName(element), this.tag)) {
            return false;
        }
        String str = this.nameAttribute;
        return str == null || Intrinsics.areEqual(adapter.getAttributeValue(element, str), this.nameAttributeValue);
    }

    public String toString() {
        return "TagBinding(nameAttributeValue=" + this.nameAttributeValue + ", tag=" + this.tag + ", binding=" + this.binding + ')';
    }

    public /* synthetic */ TagBinding(Binding binding, MutableAccessor mutableAccessor, String str, Class cls, String str2, String str3, String str4, boolean z, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(binding, mutableAccessor, str, cls, str2, str3, str4, (i & 128) != 0 ? false : z, (i & 256) != 0 ? "" : str5);
    }
}

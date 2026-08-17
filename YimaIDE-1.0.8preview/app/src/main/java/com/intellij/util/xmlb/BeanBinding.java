package com.intellij.util.xmlb;

import com.intellij.serialization.MutableAccessor;
import com.intellij.util.ThreeState;
import com.intellij.util.xml.dom.XmlElement;
import com.intellij.util.xmlb.annotations.Property;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jdom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J'\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0016\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001cJ0\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0016\u0018\u00010\"2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001c\u0010#\u001a\u0004\u0018\u00010\u00182\b\u0010$\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001f\u001a\u00020\u0016H\u0016J\u001c\u0010%\u001a\u0004\u0018\u00010 2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J \u0010%\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010&\u001a\u00020 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\"\u0010%\u001a\u0004\u0018\u00010 2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ&\u0010(\u001a\u0004\u0018\u00010 2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J6\u0010*\u001a\u0004\u0018\u00010 2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010,\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010-\u001a\u00020\u001cH\u0007J7\u0010.\u001a\u00020\u0018\"\b\b\u0000\u0010/*\u00020\u00182\b\u00100\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001f\u001a\u0002H/2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H/02H\u0017¢\u0006\u0002\u00103J\u0016\u00104\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 J5\u00104\u001a\u00020\u0010\"\b\b\u0000\u0010/*\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u0002H/2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H/02H\u0007¢\u0006\u0002\u00105J\u0018\u00104\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u000206H\u0007J\b\u00107\u001a\u00020\u0018H\u0016J\u001e\u00108\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u00182\u0006\u00109\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020:J!\u0010;\u001a\b\u0012\u0004\u0012\u00020\b0<2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0>H\u0000¢\u0006\u0002\b?J\u0014\u0010@\u001a\u00020\u00102\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\b0<J-\u0010B\u001a\u00020\u001c\"\b\b\u0000\u0010/*\u00020\u00182\u0006\u0010\u001f\u001a\u0002H/2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H/02H\u0017¢\u0006\u0002\u0010CJ\b\u0010D\u001a\u00020\bH\u0016J&\u0010E\u001a\u0004\u0018\u00010 2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0007R\u0014\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/intellij/util/xmlb/BeanBinding;", "Lcom/intellij/util/xmlb/Binding;", "Lcom/intellij/util/xmlb/RootBinding;", "beanClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "tagName", "", "bindings", "", "Lcom/intellij/util/xmlb/NestedBinding;", "[Lcom/intellij/util/xmlb/NestedBinding;", "compareByFields", "Lcom/intellij/util/ThreeState;", "init", "", "originalType", "Ljava/lang/reflect/Type;", "serializer", "Lcom/intellij/util/xmlb/Serializer;", "toJson", "Lkotlinx/serialization/json/JsonElement;", "bean", "", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "includeClassDiscriminator", "", "toJson$intellij_platform_util", "deserializeToJson", "element", "Lorg/jdom/Element;", "serializeToJsonImpl", "", "fromJson", "currentValue", "serialize", "parent", "createElementIfEmpty", "serializeProperties", "preCreatedElement", "serializeProperty", "binding", "parentElement", "isFilterPropertyItself", "deserialize", "T", "context", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "(Ljava/lang/Object;Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Ljava/lang/Object;", "deserializeInto", "(Ljava/lang/Object;Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)V", "Lcom/intellij/util/xml/dom/XmlElement;", "newInstance", "equalByFields", "defaultValue", "Lcom/intellij/util/xmlb/SkipDefaultsSerializationFilter;", "computeBindingWeights", "Lit/unimi/dsi/fastutil/objects/Object2FloatMap;", "accessorNameTracker", "", "computeBindingWeights$intellij_platform_util", "sortBindings", "weights", "isBoundTo", "(Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Z", "toString", "serializeInto", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class BeanBinding implements RootBinding {
    public final Class<?> beanClass;
    public NestedBinding[] bindings;
    public ThreeState compareByFields;
    public final String tagName;

    public BeanBinding(Class<?> cls) {
        cls.getClass();
        this.beanClass = cls;
        this.compareByFields = ThreeState.UNSURE;
        cls.isArray();
        cls.isPrimitive();
        String tagName = BeanBindingKt.getTagName(cls);
        this.tagName = tagName;
        StringsKt.isBlank(tagName);
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object context, T element, DomAdapter<T> adapter) throws Throwable {
        element.getClass();
        adapter.getClass();
        Object objNewInstance = newInstance();
        deserializeInto(objNewInstance, element, adapter);
        return objNewInstance;
    }

    public final <T> void deserializeInto(Object bean, T element, DomAdapter<T> adapter) {
        bean.getClass();
        element.getClass();
        adapter.getClass();
        if (Intrinsics.areEqual(adapter, JdomAdapter.INSTANCE)) {
            NestedBinding[] nestedBindingArr = this.bindings;
            nestedBindingArr.getClass();
            BeanBindingKt.deserializeJdomIntoBean(bean, (Element) element, nestedBindingArr, null);
        } else {
            if (!Intrinsics.areEqual(adapter, XmlDomAdapter.INSTANCE)) {
                bu8.a();
                return;
            }
            NestedBinding[] nestedBindingArr2 = this.bindings;
            nestedBindingArr2.getClass();
            BeanBindingKt.deserializeBeanInto(bean, (XmlElement) element, nestedBindingArr2);
        }
    }

    @Override // com.intellij.util.xmlb.Binding
    public final synchronized void init(Type originalType, Serializer serializer) {
        Property.Style style;
        try {
            originalType.getClass();
            serializer.getClass();
            List<MutableAccessor> listCollect = BeanBindingKt.PROPERTY_COLLECTOR.collect(this.beanClass);
            if (listCollect.isEmpty()) {
                this.bindings = BeanBindingKt.EMPTY_BINDINGS;
            } else {
                Property property = (Property) this.beanClass.getAnnotation(Property.class);
                if (property == null || (style = property.style()) == null) {
                    style = Property.Style.OPTION_TAG;
                }
                int size = listCollect.size();
                NestedBinding[] nestedBindingArr = new NestedBinding[size];
                for (int i = 0; i < size; i++) {
                    NestedBinding nestedBindingCreateBinding = BeanBindingKt.createBinding(listCollect.get(i), serializer, style);
                    nestedBindingCreateBinding.init(originalType, serializer);
                    nestedBindingArr[i] = nestedBindingCreateBinding;
                }
                this.bindings = nestedBindingArr;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T element, DomAdapter<T> adapter) {
        element.getClass();
        adapter.getClass();
        return Intrinsics.areEqual(adapter.getName(element), this.tagName);
    }

    public Object newInstance() throws Throwable {
        try {
            Constructor<?> declaredConstructor = this.beanClass.getDeclaredConstructor(null);
            try {
                declaredConstructor.setAccessible(true);
            } catch (SecurityException unused) {
            }
            Object objNewInstance = declaredConstructor.newInstance(null);
            objNewInstance.getClass();
            return objNewInstance;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException == null) {
                throw e;
            }
            throw targetException;
        }
    }

    public String toString() {
        return "BeanBinding(" + this.beanClass.getName() + ", tagName=" + this.tagName + ')';
    }

    public final void deserializeInto(Object bean, Element element) {
        bean.getClass();
        element.getClass();
        NestedBinding[] nestedBindingArr = this.bindings;
        nestedBindingArr.getClass();
        BeanBindingKt.deserializeJdomIntoBean(bean, element, nestedBindingArr, null);
    }

    public final void deserializeInto(Object bean, XmlElement element) {
        bean.getClass();
        element.getClass();
        NestedBinding[] nestedBindingArr = this.bindings;
        nestedBindingArr.getClass();
        BeanBindingKt.deserializeBeanInto(bean, element, nestedBindingArr);
    }
}

package com.intellij.util.xmlb;

import com.intellij.serialization.ClassUtil;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.XMap;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B;\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001c\u001a\u00020\u000b2\u000e\u0010\u001d\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\tH\u0002J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010$\u001a\u0004\u0018\u00010!2\b\u0010%\u001a\u0004\u0018\u00010!2\u0006\u0010&\u001a\u00020\u001fH\u0016J\u001c\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\"\u0010'\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010)\u001a\u00020(2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J:\u00102\u001a\u0004\u0018\u00010!\"\b\b\u0000\u00103*\u00020!2\b\u0010%\u001a\u0004\u0018\u00010!2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H3052\f\u00106\u001a\b\u0012\u0004\u0012\u0002H307H\u0016J9\u00108\u001a\u0004\u0018\u00010!\"\b\b\u0000\u00103*\u00020!2\b\u00109\u001a\u0004\u0018\u00010!2\u0006\u0010&\u001a\u0002H32\f\u00106\u001a\b\u0012\u0004\u0012\u0002H307H\u0016¢\u0006\u0002\u0010:J\u001a\u00108\u001a\u0004\u0018\u00010!2\b\u00109\u001a\u0004\u0018\u00010!2\u0006\u0010&\u001a\u00020(J:\u0010;\u001a\u0004\u0018\u00010!\"\b\b\u0000\u00103*\u00020!2\b\u0010%\u001a\u0004\u0018\u00010!2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H3052\f\u00106\u001a\b\u0012\u0004\u0012\u0002H307H\u0002J\u0010\u0010=\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020(H\u0016J\u0016\u0010>\u001a\u00020\u001f2\f\u00104\u001a\b\u0012\u0004\u0012\u00020(05H\u0016J0\u0010?\u001a\u0004\u0018\u00010\u001f2\u0006\u0010@\u001a\u00020(2\u0006\u0010A\u001a\u00020+2\b\u0010B\u001a\u0004\u0018\u00010\u00122\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\bH\u0002J6\u0010C\u001a\u00020\u00162\u0006\u0010@\u001a\u00020(2\u0006\u0010A\u001a\u00020+2\b\u0010D\u001a\u0004\u0018\u00010!2\b\u0010B\u001a\u0004\u0018\u00010\u00122\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002JW\u0010E\u001a\u0004\u0018\u00010!\"\b\b\u0000\u00103*\u00020!2\u0006\u0010@\u001a\u0002H32\u0006\u0010A\u001a\u00020+2\b\u00109\u001a\u0004\u0018\u00010!2\b\u0010B\u001a\u0004\u0018\u00010\u00122\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\b2\f\u00106\u001a\b\u0012\u0004\u0012\u0002H307H\u0002¢\u0006\u0002\u0010FJ\u000e\u0010G\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020+J-\u0010I\u001a\u00020\u000b\"\b\b\u0000\u00103*\u00020!2\u0006\u0010&\u001a\u0002H32\f\u00106\u001a\b\u0012\u0004\u0012\u0002H307H\u0016¢\u0006\u0002\u0010JR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000eR\u0014\u0010*\u001a\u00020+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u0010-R\u0014\u00100\u001a\u00020+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u0010-¨\u0006K"}, d2 = {"Lcom/intellij/util/xmlb/MapBinding;", "Lcom/intellij/util/xmlb/MultiNodeBinding;", "Lcom/intellij/util/xmlb/RootBinding;", "oldAnnotation", "Lcom/intellij/util/xmlb/annotations/MapAnnotation;", "annotation", "Lcom/intellij/util/xmlb/annotations/XMap;", "mapClass", "Ljava/lang/Class;", "", "isSurroundWithTag", "", "<init>", "(Lcom/intellij/util/xmlb/annotations/MapAnnotation;Lcom/intellij/util/xmlb/annotations/XMap;Ljava/lang/Class;Z)V", "()Z", "keyClass", "valueClass", "keyBinding", "Lcom/intellij/util/xmlb/Binding;", "valueBinding", "isSurroundKey", "init", "", "originalType", "Ljava/lang/reflect/Type;", "serializer", "Lcom/intellij/util/xmlb/Serializer;", "isMulti", "isSortMap", "map", "toJson", "Lkotlinx/serialization/json/JsonElement;", "bean", "", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "fromJson", "currentValue", "element", "serialize", "Lorg/jdom/Element;", "parent", "entryElementName", "", "getEntryElementName", "()Ljava/lang/String;", "keyAttributeName", "getKeyAttributeName", "valueAttributeName", "getValueAttributeName", "deserializeList", "T", "elements", "", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "deserialize", "context", "(Ljava/lang/Object;Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Ljava/lang/Object;", "deserializeMap", "childNodes", "deserializeToJson", "doDeserializeListToJson", "deserializeKeyOrValueToJson", "entry", "attributeName", "binding", "serializeKeyOrValue", "value", "deserializeKeyOrValue", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lcom/intellij/util/xmlb/Binding;Ljava/lang/Class;Lcom/intellij/util/xmlb/DomAdapter;)Ljava/lang/Object;", "isBoundToWithoutProperty", "elementName", "isBoundTo", "(Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Z", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MapBinding implements MultiNodeBinding, RootBinding {
    private final XMap annotation;
    private final boolean isSurroundKey;
    private final boolean isSurroundWithTag;
    private Binding keyBinding;
    private Class<?> keyClass;
    private final Class<? extends Map<?, ?>> mapClass;
    private final MapAnnotation oldAnnotation;
    private Binding valueBinding;
    private Class<?> valueClass;

    public MapBinding(MapAnnotation mapAnnotation, XMap xMap, Class<? extends Map<?, ?>> cls, boolean z) {
        cls.getClass();
        this.oldAnnotation = mapAnnotation;
        this.annotation = xMap;
        this.mapClass = cls;
        this.isSurroundWithTag = z;
        this.isSurroundKey = xMap == null && (mapAnnotation == null || mapAnnotation.surroundKeyWithTag());
    }

    private final <T> Object deserializeKeyOrValue(T entry, String attributeName, Object context, Binding binding, Class<?> valueClass, DomAdapter<T> adapter) {
        String attributeValue = adapter.getAttributeValue(entry, attributeName);
        if (attributeValue != null) {
            return XmlSerializerImpl.convert(attributeValue, valueClass);
        }
        if (this.isSurroundKey) {
            T child = adapter.getChild(entry, attributeName);
            List<T> children = child == null ? Collections.EMPTY_LIST : adapter.getChildren(child);
            if (children.isEmpty()) {
                return null;
            }
            binding.getClass();
            return MapBindingKt.deserializeList(binding, null, children, adapter);
        }
        if (binding == null) {
            k2d.a("Required value was null.");
            return null;
        }
        for (T t : adapter.getChildren(entry)) {
            if (binding.isBoundTo(t, adapter)) {
                return binding.deserialize(context, t, adapter);
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001a  */
    private final <T> Object deserializeMap(Object currentValue, List<? extends T> childNodes, DomAdapter<T> adapter) {
        Map map;
        if (currentValue == null) {
            map = null;
        } else {
            if (childNodes.isEmpty()) {
                return currentValue;
            }
            if (ClassUtil.isMutableMap((Map) currentValue)) {
                map = TypeIntrinsics.asMutableMap(currentValue);
                map.clear();
            } else {
                map = null;
            }
        }
        for (T t : childNodes) {
            if (Intrinsics.areEqual(adapter.getName(t), this.getEntryElementName())) {
                if (map == null) {
                    if (Intrinsics.areEqual(this.mapClass, Map.class)) {
                        map = new HashMap();
                    } else {
                        try {
                            Object objNewInstance = ReflectionUtil.newInstance(this.mapClass);
                            objNewInstance.getClass();
                            map = TypeIntrinsics.asMutableMap(objNewInstance);
                        } catch (Exception e) {
                            BeanBindingKt.LOG.warn(e);
                            map = new HashMap();
                        }
                    }
                }
                map.getClass();
                String keyAttributeName = this.getKeyAttributeName();
                Binding binding = this.keyBinding;
                Class<?> cls = this.keyClass;
                cls.getClass();
                MapBinding mapBinding = this;
                Object obj = currentValue;
                Object objDeserializeKeyOrValue = mapBinding.deserializeKeyOrValue(t, keyAttributeName, obj, binding, cls, adapter);
                String valueAttributeName = mapBinding.getValueAttributeName();
                Binding binding2 = mapBinding.valueBinding;
                Class<?> cls2 = mapBinding.valueClass;
                cls2.getClass();
                map.put(objDeserializeKeyOrValue, mapBinding.deserializeKeyOrValue(t, valueAttributeName, obj, binding2, cls2, adapter));
                this = mapBinding;
                currentValue = obj;
            } else {
                BeanBindingKt.LOG.warn("unexpected entry for serialized Map will be skipped: " + t);
            }
        }
        return map;
    }

    private final String getEntryElementName() {
        String strEntryTagName;
        XMap xMap = this.annotation;
        if (xMap != null) {
            return xMap.entryTagName();
        }
        MapAnnotation mapAnnotation = this.oldAnnotation;
        return (mapAnnotation == null || (strEntryTagName = mapAnnotation.entryTagName()) == null) ? "entry" : strEntryTagName;
    }

    private final String getKeyAttributeName() {
        String strKeyAttributeName;
        XMap xMap = this.annotation;
        if (xMap != null && (strKeyAttributeName = xMap.keyAttributeName()) != null) {
            return strKeyAttributeName;
        }
        MapAnnotation mapAnnotation = this.oldAnnotation;
        return mapAnnotation != null ? mapAnnotation.keyAttributeName() : "key";
    }

    private final String getValueAttributeName() {
        String strValueAttributeName;
        XMap xMap = this.annotation;
        if (xMap != null && (strValueAttributeName = xMap.valueAttributeName()) != null) {
            return strValueAttributeName;
        }
        MapAnnotation mapAnnotation = this.oldAnnotation;
        return mapAnnotation != null ? mapAnnotation.valueAttributeName() : "value";
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object context, T element, DomAdapter<T> adapter) {
        element.getClass();
        adapter.getClass();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public <T> Object deserializeList(Object currentValue, List<? extends T> elements, DomAdapter<T> adapter) {
        elements.getClass();
        adapter.getClass();
        if (getIsSurroundWithTag()) {
            elements = adapter.getChildren(CollectionsKt.single(elements));
        }
        return deserializeMap(currentValue, elements, adapter);
    }

    @Override // com.intellij.util.xmlb.Binding
    public void init(Type originalType, Serializer serializer) {
        Type type;
        originalType.getClass();
        serializer.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) originalType;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.keyClass = ClassUtil.typeToClass(actualTypeArguments[0]);
        if (actualTypeArguments.length == 1) {
            String typeName = parameterizedType.getRawType().getTypeName();
            if (!Intrinsics.areEqual(typeName, "it.unimi.dsi.fastutil.objects.Object2IntMap") && !Intrinsics.areEqual(typeName, "it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap")) {
                o1c.a("Value class is unknown for ", parameterizedType.getTypeName());
                return;
            }
            type = Integer.TYPE;
        } else {
            type = actualTypeArguments[1];
        }
        this.valueClass = ClassUtil.typeToClass(type);
        Class<?> cls = this.keyClass;
        cls.getClass();
        Type type2 = actualTypeArguments[0];
        type2.getClass();
        this.keyBinding = serializer.getBinding(cls, type2);
        Class<?> cls2 = this.valueClass;
        cls2.getClass();
        this.valueBinding = serializer.getBinding(cls2, type);
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T element, DomAdapter<T> adapter) {
        element.getClass();
        adapter.getClass();
        MapAnnotation mapAnnotation = this.oldAnnotation;
        if (mapAnnotation != null && !mapAnnotation.surroundWithTag()) {
            return Intrinsics.areEqual(this.oldAnnotation.entryTagName(), adapter.getName(element));
        }
        XMap xMap = this.annotation;
        return xMap != null ? Intrinsics.areEqual(xMap.propertyElementName(), adapter.getName(element)) : Intrinsics.areEqual(adapter.getName(element), "map");
    }

    public final boolean isBoundToWithoutProperty(String elementName) {
        elementName.getClass();
        XMap xMap = this.annotation;
        if (xMap != null) {
            return Intrinsics.areEqual(elementName, xMap.entryTagName());
        }
        MapAnnotation mapAnnotation = this.oldAnnotation;
        return (mapAnnotation == null || mapAnnotation.surroundWithTag()) ? Intrinsics.areEqual(elementName, "map") : Intrinsics.areEqual(elementName, this.oldAnnotation.entryTagName());
    }

    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public boolean isMulti() {
        return true;
    }

    /* JADX INFO: renamed from: isSurroundWithTag, reason: from getter */
    public boolean getIsSurroundWithTag() {
        return this.isSurroundWithTag;
    }
}

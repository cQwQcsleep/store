package com.intellij.util.xmlb;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.serialization.ClassUtil;
import com.intellij.serialization.MutableAccessor;
import com.intellij.serialization.SerializationException;
import com.intellij.util.xml.dom.XmlElement;
import com.intellij.util.xmlb.BeanBindingKt;
import com.intellij.util.xmlb.MultiNodeBinding;
import com.intellij.util.xmlb.NestedBinding;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Text;
import com.intellij.util.xmlb.annotations.XCollection;
import com.intellij.util.xmlb.annotations.XMap;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jdom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f\u001a\b\u0010\r\u001a\u00020\u000eH\u0007\u001a+\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0002\u0010\u0017\u001a3\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u001a2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0002¢\u0006\u0002\u0010\u001e\u001a=\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u001a2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010!H\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010#\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0006\u001a\u0018\u0010%\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0006\u001a \u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002\u001a\"\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010'\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u000100H\u0002\u001a\u0014\u00101\u001a\u00020\u001d2\n\u00102\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a\u0014\u00103\u001a\u00020\u00102\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a\u0016\u00104\u001a\u0004\u0018\u00010\u00102\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a2\u00105\u001a\u00020\u001d2\b\u00106\u001a\u0004\u0018\u0001072\u0006\u0010$\u001a\u00020\u00062\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u001dH\u0007\u001a\u000e\u0010<\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0006\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0080T¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "PROPERTY_COLLECTOR", "Lcom/intellij/util/xmlb/XmlSerializerPropertyCollector;", "EMPTY_BINDINGS", "", "Lcom/intellij/util/xmlb/NestedBinding;", "[Lcom/intellij/util/xmlb/NestedBinding;", "getBeanAccessors", "", "Lcom/intellij/serialization/MutableAccessor;", "aClass", "Ljava/lang/Class;", "clearPropertyCollectorCache", "", "JSON_CLASS_DISCRIMINATOR_KEY", "", "deserializeBeanInto", "result", "", "element", "Lcom/intellij/util/xml/dom/XmlElement;", "bindings", "(Ljava/lang/Object;Lcom/intellij/util/xml/dom/XmlElement;[Lcom/intellij/util/xmlb/NestedBinding;)V", "deserializeBeanXmlToJson", "Lkotlinx/serialization/json/JsonElement;", "Lorg/jdom/Element;", "jsonDiscriminator", "includeClassDiscriminator", "", "(Lorg/jdom/Element;[Lcom/intellij/util/xmlb/NestedBinding;Ljava/lang/String;Z)Lkotlinx/serialization/json/JsonElement;", "deserializeJdomIntoBean", "accessorNameTracker", "", "(Ljava/lang/Object;Lorg/jdom/Element;[Lcom/intellij/util/xmlb/NestedBinding;Ljava/util/Set;)V", "deserializeNestedBindingInto", "binding", "deserializeNestedBindingToJson", "createBinding", "accessor", "serializer", "Lcom/intellij/util/xmlb/Serializer;", "propertyStyle", "Lcom/intellij/util/xmlb/annotations/Property$Style;", "createOptionTagBindingByAnnotation", "Lcom/intellij/util/xmlb/TagBinding;", "optionTag", "Lcom/intellij/util/xmlb/annotations/OptionTag;", "Lcom/intellij/util/xmlb/Binding;", "isAssertBindings", "startClass", "getTagName", "getTagNameFromAnnotation", "isPropertySkipped", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "rootBinding", "Lcom/intellij/util/xmlb/BeanBinding;", "bean", "isFilterPropertyItself", "normalizePropertyNameForKotlinx", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BeanBindingKt {
    private static final NestedBinding[] EMPTY_BINDINGS;
    public static final Logger LOG;
    private static final XmlSerializerPropertyCollector PROPERTY_COLLECTOR;

    static {
        Logger logger = Logger.getInstance(Binding.class);
        logger.getClass();
        LOG = logger;
        PROPERTY_COLLECTOR = new XmlSerializerPropertyCollector(new MyPropertyCollectorConfiguration());
        EMPTY_BINDINGS = new NestedBinding[0];
    }

    public static List a(NestedBinding nestedBinding) {
        nestedBinding.getClass();
        return new ArrayList();
    }

    public static List b(Function1 function1, Object obj) {
        return (List) function1.invoke(obj);
    }

    public static List c(Function1 function1, Object obj) {
        return (List) function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.serialization.SerializationException */
    public static final NestedBinding createBinding(MutableAccessor mutableAccessor, Serializer serializer, Property.Style style) throws SerializationException {
        Binding binding;
        boolean zSurroundWithTag;
        boolean zFlat;
        XMap xMap;
        Attribute attribute = (Attribute) mutableAccessor.getAnnotation(Attribute.class);
        if (attribute != null) {
            return new AttributeBinding(mutableAccessor, attribute);
        }
        if (mutableAccessor.isAnnotationPresent(Text.class)) {
            return new TextBinding(mutableAccessor);
        }
        Type genericType = mutableAccessor.getGenericType();
        genericType.getClass();
        Class<?> clsTypeToClass = ClassUtil.typeToClass(genericType);
        clsTypeToClass.getClass();
        if (ClassUtil.isPrimitive(clsTypeToClass)) {
            binding = null;
        } else {
            Binding bindingCreateClassBinding = XmlSerializerImpl.createClassBinding(clsTypeToClass, mutableAccessor, genericType, serializer);
            if (bindingCreateClassBinding == null) {
                bindingCreateClassBinding = serializer.getRootBinding(clsTypeToClass, genericType);
            } else {
                bindingCreateClassBinding.init(genericType, serializer);
                if (bindingCreateClassBinding instanceof CompactCollectionBinding) {
                    return (NestedBinding) bindingCreateClassBinding;
                }
            }
            binding = bindingCreateClassBinding;
        }
        OptionTag optionTag = (OptionTag) mutableAccessor.getAnnotation(OptionTag.class);
        if (optionTag != null && XmlSerializerUtil.getConverter(optionTag) != null) {
            return createOptionTagBindingByAnnotation(optionTag, mutableAccessor, binding);
        }
        if (binding instanceof JDOMElementBinding) {
            return (NestedBinding) binding;
        }
        Tag tag = (Tag) mutableAccessor.getAnnotation(Tag.class);
        if (tag != null) {
            String strValue = tag.value();
            if (strValue.length() == 0) {
                strValue = mutableAccessor.getName();
                strValue.getClass();
            }
            return new TagBinding(binding, mutableAccessor, null, null, strValue, null, null, false, tag.textIfEmpty(), 128, null);
        }
        Property property = (Property) mutableAccessor.getAnnotation(Property.class);
        if (property != null) {
            zSurroundWithTag = property.surroundWithTag();
            zFlat = property.flat();
        } else {
            zSurroundWithTag = true;
            zFlat = false;
        }
        if (!zSurroundWithTag || zFlat) {
            if (zFlat && !(binding instanceof BeanBinding)) {
                throw new XmlSerializationException("inline supported only for BeanBinding: " + mutableAccessor);
            }
            if (binding != null && !(binding instanceof TextBinding)) {
                property.getClass();
                return new AccessorBindingWrapper(mutableAccessor, binding, zFlat, property.style());
            }
            throw new XmlSerializationException("Text-serializable properties can't be serialized without surrounding tags: " + mutableAccessor);
        }
        XCollection xCollection = (XCollection) mutableAccessor.getAnnotation(XCollection.class);
        if (xCollection != null && (xCollection.propertyElementName().length() != 0 || xCollection.style() == XCollection.Style.v2)) {
            String strPropertyElementName = xCollection.propertyElementName();
            if (strPropertyElementName.length() == 0) {
                strPropertyElementName = mutableAccessor.getName();
                strPropertyElementName.getClass();
            }
            return new TagBinding(binding, mutableAccessor, null, null, strPropertyElementName, null, null, false, "", 128, null);
        }
        if (optionTag != null || (xMap = (XMap) mutableAccessor.getAnnotation(XMap.class)) == null) {
            if (style == Property.Style.ATTRIBUTE) {
                return new AttributeBinding(mutableAccessor, null);
            }
            if (optionTag == null) {
                return new TagBinding(binding, mutableAccessor, mutableAccessor.getName(), null, "option", "name", binding == null ? "value" : null, false, null, 384, null);
            }
            return createOptionTagBindingByAnnotation(optionTag, mutableAccessor, binding);
        }
        String strPropertyElementName2 = xMap.propertyElementName();
        if (strPropertyElementName2.length() == 0) {
            strPropertyElementName2 = mutableAccessor.getName();
            strPropertyElementName2.getClass();
        }
        return new TagBinding(binding, mutableAccessor, null, null, strPropertyElementName2, null, null, false, null, 384, null);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0056  */
    private static final TagBinding createOptionTagBindingByAnnotation(OptionTag optionTag, MutableAccessor mutableAccessor, Binding binding) {
        String str;
        String str2;
        String strValue;
        String strValueAttribute;
        String strTag = optionTag.tag();
        String strNameAttribute = optionTag.nameAttribute();
        if (strNameAttribute.length() > 0) {
            str2 = strNameAttribute;
            str = null;
        } else {
            str = null;
            str2 = null;
        }
        Class<? extends Converter> converter = XmlSerializerUtil.getConverter(optionTag);
        boolean z = binding == null || converter != null;
        if (str2 == null) {
            strValue = str;
        } else {
            strValue = optionTag.value();
            if (strValue.length() == 0) {
                strValue = mutableAccessor.getName();
                strValue.getClass();
            }
        }
        if (str2 == null && Intrinsics.areEqual(strTag, "option")) {
            strTag = mutableAccessor.getName();
        }
        strTag.getClass();
        if (z) {
            strValueAttribute = optionTag.valueAttribute();
            if (strValueAttribute.length() <= 0) {
                strValueAttribute = str;
            }
        } else {
            strValueAttribute = str;
        }
        return new TagBinding(binding, mutableAccessor, strValue, converter, strTag, str2, strValueAttribute, (binding instanceof BeanBinding) && optionTag.valueAttribute().length() == 0, null, 256, null);
    }

    public static List d(MultiNodeBinding multiNodeBinding) {
        multiNodeBinding.getClass();
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deserializeBeanInto(Object obj, XmlElement xmlElement, NestedBinding[] nestedBindingArr) {
        int i = 0;
        for (NestedBinding nestedBinding : nestedBindingArr) {
            if (nestedBinding instanceof AttributeBinding) {
                i++;
                AttributeBinding attributeBinding = (AttributeBinding) nestedBinding;
                String str = (String) xmlElement.attributes.get(attributeBinding.name);
                if (str != null) {
                    attributeBinding.setValue(obj, str);
                }
            } else {
                String str2 = xmlElement.content;
                if (str2 != null && (nestedBinding instanceof TextBinding)) {
                    str2.getClass();
                    ((TextBinding) nestedBinding).setValue(obj, str2);
                }
            }
        }
        if (i == nestedBindingArr.length) {
            return;
        }
        LinkedHashMap linkedHashMap = null;
        for (XmlElement xmlElement2 : xmlElement.children) {
            for (NestedBinding nestedBinding2 : nestedBindingArr) {
                if (!(nestedBinding2 instanceof AttributeBinding) && !(nestedBinding2 instanceof TextBinding)) {
                    XmlDomAdapter xmlDomAdapter = XmlDomAdapter.INSTANCE;
                    if (nestedBinding2.isBoundTo(xmlElement2, xmlDomAdapter)) {
                        if (!(nestedBinding2 instanceof MultiNodeBinding) || !((MultiNodeBinding) nestedBinding2).isMulti()) {
                            nestedBinding2.deserialize(obj, xmlElement2, xmlDomAdapter);
                            break;
                        }
                        if (linkedHashMap == null) {
                            linkedHashMap = new LinkedHashMap();
                        }
                        final Function1 function1 = new Function1() { // from class: gu0
                            public final Object invoke(Object obj2) {
                                return BeanBindingKt.d((MultiNodeBinding) obj2);
                            }
                        };
                        ((List) linkedHashMap.computeIfAbsent(nestedBinding2, new Function() { // from class: hu0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj2) {
                                return BeanBindingKt.b(function1, obj2);
                            }
                        })).add(xmlElement2);
                        break;
                    }
                }
            }
        }
        for (NestedBinding nestedBinding3 : nestedBindingArr) {
            if (nestedBinding3 instanceof AccessorBindingWrapper) {
                AccessorBindingWrapper accessorBindingWrapper = (AccessorBindingWrapper) nestedBinding3;
                if (accessorBindingWrapper.isFlat) {
                    accessorBindingWrapper.deserialize(obj, xmlElement, XmlDomAdapter.INSTANCE);
                }
            }
        }
        if (linkedHashMap != null) {
            for (Object obj2 : linkedHashMap.keySet()) {
                obj2.getClass();
                MultiNodeBinding multiNodeBinding = (MultiNodeBinding) obj2;
                Object obj3 = linkedHashMap.get(multiNodeBinding);
                obj3.getClass();
                multiNodeBinding.deserializeList(obj, (List) obj3, XmlDomAdapter.INSTANCE);
            }
        }
    }

    public static final void deserializeJdomIntoBean(Object obj, Element element, NestedBinding[] nestedBindingArr, Set<String> set) {
        obj.getClass();
        element.getClass();
        nestedBindingArr.getClass();
        if (element.hasAttributes()) {
            for (org.jdom.Attribute attribute : element.getAttributes()) {
                for (NestedBinding nestedBinding : nestedBindingArr) {
                    if (nestedBinding instanceof AttributeBinding) {
                        AttributeBinding attributeBinding = (AttributeBinding) nestedBinding;
                        if (Intrinsics.areEqual(attributeBinding.name, attribute.getName())) {
                            if (set != null) {
                                String name = attributeBinding.getAccessor().getName();
                                name.getClass();
                                set.add(name);
                            }
                            attributeBinding.setValue(obj, attribute.getValue());
                            break;
                        }
                    }
                }
            }
        }
        LinkedHashMap linkedHashMap = null;
        for (org.jdom.Text text : element.getContent()) {
            for (NestedBinding nestedBinding2 : nestedBindingArr) {
                if (!(text instanceof org.jdom.Text)) {
                    text.getClass();
                    Element element2 = (Element) text;
                    JdomAdapter jdomAdapter = JdomAdapter.INSTANCE;
                    if (nestedBinding2.isBoundTo(element2, jdomAdapter)) {
                        if (!(nestedBinding2 instanceof MultiNodeBinding) || !((MultiNodeBinding) nestedBinding2).isMulti()) {
                            if (set != null) {
                                String name2 = nestedBinding2.getAccessor().getName();
                                name2.getClass();
                                set.add(name2);
                            }
                            nestedBinding2.deserialize(obj, element2, jdomAdapter);
                            break;
                        }
                        if (linkedHashMap == null) {
                            linkedHashMap = new LinkedHashMap();
                        }
                        final Function1 function1 = new Function1() { // from class: iu0
                            public final Object invoke(Object obj2) {
                                return BeanBindingKt.a((NestedBinding) obj2);
                            }
                        };
                        ((List) linkedHashMap.computeIfAbsent(nestedBinding2, new Function() { // from class: ju0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj2) {
                                return BeanBindingKt.c(function1, obj2);
                            }
                        })).add(element2);
                        break;
                    }
                } else if (nestedBinding2 instanceof TextBinding) {
                    ((TextBinding) nestedBinding2).setValue(obj, text.getValue());
                }
            }
        }
        for (NestedBinding nestedBinding3 : nestedBindingArr) {
            if (nestedBinding3 instanceof AccessorBindingWrapper) {
                AccessorBindingWrapper accessorBindingWrapper = (AccessorBindingWrapper) nestedBinding3;
                if (accessorBindingWrapper.isFlat) {
                    accessorBindingWrapper.deserialize(obj, element, JdomAdapter.INSTANCE);
                }
            }
        }
        if (linkedHashMap != null) {
            for (Object obj2 : linkedHashMap.keySet()) {
                obj2.getClass();
                NestedBinding nestedBinding4 = (NestedBinding) obj2;
                if (set != null) {
                    String name3 = nestedBinding4.getAccessor().getName();
                    name3.getClass();
                    set.add(name3);
                }
                MultiNodeBinding multiNodeBinding = (MultiNodeBinding) nestedBinding4;
                Object obj3 = linkedHashMap.get(nestedBinding4);
                obj3.getClass();
                multiNodeBinding.deserializeList(obj, (List) obj3, JdomAdapter.INSTANCE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getTagName(Class<?> cls) {
        int i;
        Class<?> superclass = cls;
        do {
            String tagNameFromAnnotation = getTagNameFromAnnotation(superclass);
            if (tagNameFromAnnotation != null) {
                return tagNameFromAnnotation;
            }
            superclass = superclass.getSuperclass();
        } while (superclass != null);
        String simpleName = cls.getSimpleName();
        if (simpleName.length() == 0) {
            simpleName = cls.getSuperclass().getSimpleName();
        }
        String str = simpleName;
        int iLastIndexOf$default = StringsKt.lastIndexOf$default(str, '$', 0, false, 6, (Object) null);
        return (iLastIndexOf$default <= 0 || str.length() <= (i = iLastIndexOf$default + 1)) ? str : str.substring(i);
    }

    private static final String getTagNameFromAnnotation(Class<?> cls) {
        String strValue;
        Tag tag = (Tag) cls.getAnnotation(Tag.class);
        if (tag == null || (strValue = tag.value()) == null || strValue.length() <= 0) {
            return null;
        }
        return strValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAssertBindings(Class<?> cls) {
        do {
            Property property = (Property) cls.getAnnotation(Property.class);
            if (property != null && !property.assertIfNoBindings()) {
                return true;
            }
            cls = cls.getSuperclass();
        } while (cls != null);
        return false;
    }
}

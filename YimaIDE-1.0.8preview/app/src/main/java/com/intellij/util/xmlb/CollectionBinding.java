package com.intellij.util.xmlb;

import com.intellij.serialization.ClassUtil;
import com.intellij.util.SmartList;
import com.intellij.util.xmlb.annotations.Transient;
import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BU\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u0012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u00172\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001c\u0010%\u001a\u0004\u0018\u00010\"2\b\u0010&\u001a\u0004\u0018\u00010\"2\u0006\u0010'\u001a\u00020(H\u0016J\u001a\u0010)\u001a\u00020*2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\"\u0010)\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"2\u0006\u0010+\u001a\u00020*2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010,\u001a\u00020(2\u0006\u0010'\u001a\u00020*H\u0016J\u0016\u0010-\u001a\u00020(2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020*0\u0016H\u0016J7\u0010/\u001a\u00020\"\"\b\b\u0000\u00100*\u00020\"2\b\u00101\u001a\u0004\u0018\u00010\"2\u0006\u0010'\u001a\u0002H02\f\u00102\u001a\b\u0012\u0004\u0012\u0002H003H\u0016¢\u0006\u0002\u00104J8\u00105\u001a\u00020\"\"\b\b\u0000\u00100*\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010\"2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H00\u00162\f\u00102\u001a\b\u0012\u0004\u0012\u0002H003H\u0016J$\u00106\u001a\u00020\u00192\b\u00107\u001a\u0004\u0018\u00010\"2\u0006\u0010+\u001a\u00020*2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J;\u00108\u001a\u0004\u0018\u00010\"\"\b\b\u0000\u00100*\u00020\"2\u0006\u00109\u001a\u0002H02\f\u00102\u001a\b\u0012\u0004\u0012\u0002H0032\b\u0010!\u001a\u0004\u0018\u00010\"H\u0000¢\u0006\u0004\b:\u0010;J-\u0010<\u001a\u00020\u0007\"\b\b\u0000\u00100*\u00020\"2\u0006\u0010'\u001a\u0002H02\f\u00102\u001a\b\u0012\u0004\u0012\u0002H003H\u0016¢\u0006\u0002\u0010=R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0013R\u0010\u0010\b\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\f8\u0000X\u0081\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013¨\u0006>"}, d2 = {"Lcom/intellij/util/xmlb/CollectionBinding;", "Lcom/intellij/util/xmlb/MultiNodeBinding;", "Lcom/intellij/util/xmlb/RootBinding;", "elementName", "", "valueAttributeName", "isSurroundWithTag", "", "isSortOrderedSet", "itemType", "Ljava/lang/Class;", "elementTypes", "", "serializer", "Lcom/intellij/util/xmlb/Serializer;", "strategy", "Lcom/intellij/util/xmlb/CollectionStrategy;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/Class;[Ljava/lang/Class;Lcom/intellij/util/xmlb/Serializer;Lcom/intellij/util/xmlb/CollectionStrategy;)V", "()Z", "[Ljava/lang/Class;", "itemBindings", "", "Lcom/intellij/util/xmlb/Binding;", "init", "", "originalType", "Ljava/lang/reflect/Type;", "isMulti", "getItemBinding", "aClass", "toJson", "Lkotlinx/serialization/json/JsonArray;", "bean", "", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "fromJson", "currentValue", "element", "Lkotlinx/serialization/json/JsonElement;", "serialize", "Lorg/jdom/Element;", "parent", "deserializeToJson", "doDeserializeListToJson", "elements", "deserialize", "T", "context", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "(Ljava/lang/Object;Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Ljava/lang/Object;", "deserializeList", "serializeItem", "value", "deserializeItem", "node", "deserializeItem$intellij_platform_util", "(Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;Ljava/lang/Object;)Ljava/lang/Object;", "isBoundTo", "(Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Z", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CollectionBinding implements MultiNodeBinding, RootBinding {
    private final String elementName;
    public final Class<?>[] elementTypes;
    public final boolean isSortOrderedSet;
    private final boolean isSurroundWithTag;
    private List<? extends Binding> itemBindings;
    public final Class<?> itemType;
    private final Serializer serializer;
    private final CollectionStrategy strategy;
    private final String valueAttributeName;

    public CollectionBinding(String str, String str2, boolean z, boolean z2, Class<?> cls, Class<?>[] clsArr, Serializer serializer, CollectionStrategy collectionStrategy) {
        str.getClass();
        str2.getClass();
        cls.getClass();
        clsArr.getClass();
        serializer.getClass();
        collectionStrategy.getClass();
        this.elementName = str;
        this.valueAttributeName = str2;
        this.isSurroundWithTag = z;
        this.isSortOrderedSet = z2;
        this.itemType = cls;
        this.elementTypes = clsArr;
        this.serializer = serializer;
        this.strategy = collectionStrategy;
    }

    private final Binding getItemBinding(Class<?> aClass, Serializer serializer) {
        if (ClassUtil.isPrimitive(aClass) || aClass.isAnnotationPresent(Transient.class)) {
            return null;
        }
        return serializer.getRootBinding(aClass, aClass);
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> Object deserialize(Object context, T element, DomAdapter<T> adapter) {
        element.getClass();
        adapter.getClass();
        return this.strategy.deserializeList(context, getIsSurroundWithTag() ? adapter.getChildren(element) : CollectionsKt.listOf(element), adapter, this);
    }

    public final <T> Object deserializeItem$intellij_platform_util(T node, DomAdapter<T> adapter, Object bean) {
        T next;
        node.getClass();
        adapter.getClass();
        List<? extends Binding> list = this.itemBindings;
        list.getClass();
        Iterator<T> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((Binding) next).isBoundTo(node, adapter));
        Binding binding = (Binding) next;
        if (binding != null) {
            return binding.deserialize(bean, node, adapter);
        }
        String str = this.valueAttributeName;
        return XmlSerializerImpl.convert(str.length() == 0 ? adapter.getTextValue(node, "") : adapter.getAttributeValue(node, str), this.itemType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.intellij.util.xmlb.MultiNodeBinding
    public <T> Object deserializeList(Object currentValue, List<? extends T> elements, DomAdapter<T> adapter) {
        elements.getClass();
        adapter.getClass();
        if (!getIsSurroundWithTag()) {
            return this.strategy.deserializeList(currentValue, elements, adapter, this);
        }
        Object objSingle = CollectionsKt.single(elements);
        CollectionStrategy collectionStrategy = this.strategy;
        if (currentValue == null && Intrinsics.areEqual(adapter.getName(objSingle), "set")) {
            currentValue = new HashSet();
        }
        return collectionStrategy.deserializeList(currentValue, adapter.getChildren(objSingle), adapter, this);
    }

    @Override // com.intellij.util.xmlb.Binding
    public void init(Type originalType, Serializer serializer) {
        AbstractList smartList;
        originalType.getClass();
        serializer.getClass();
        Binding itemBinding = getItemBinding(this.itemType, serializer);
        Class<?>[] clsArr = this.elementTypes;
        if (clsArr.length == 0) {
            this.itemBindings = itemBinding == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(itemBinding);
            return;
        }
        if (itemBinding == null) {
            smartList = clsArr.length == 1 ? new SmartList() : new ArrayList(clsArr.length);
        } else {
            ArrayList arrayList = new ArrayList(clsArr.length + 1);
            arrayList.add(itemBinding);
            smartList = arrayList;
        }
        for (Class<?> cls : clsArr) {
            Binding itemBinding2 = getItemBinding(cls, serializer);
            if (itemBinding2 != null && !smartList.contains(itemBinding2)) {
                smartList.add(itemBinding2);
            }
        }
        boolean zIsEmpty = smartList.isEmpty();
        List<? extends Binding> listEmptyList = smartList;
        if (zIsEmpty) {
            listEmptyList = CollectionsKt.emptyList();
        }
        this.itemBindings = listEmptyList;
    }

    @Override // com.intellij.util.xmlb.Binding
    public <T> boolean isBoundTo(T element, DomAdapter<T> adapter) {
        element.getClass();
        adapter.getClass();
        if (getIsSurroundWithTag()) {
            return Intrinsics.areEqual(adapter.getName(element), this.strategy.getCollectionTagName(null));
        }
        List<? extends Binding> list = this.itemBindings;
        list.getClass();
        if (list.isEmpty()) {
            return Intrinsics.areEqual(adapter.getName(element), this.elementName);
        }
        List<? extends Binding> list2 = this.itemBindings;
        list2.getClass();
        List<? extends Binding> list3 = list2;
        if ((list3 instanceof Collection) && list3.isEmpty()) {
            return false;
        }
        Iterator<T> it = list3.iterator();
        while (it.hasNext()) {
            if (((Binding) it.next()).isBoundTo(element, adapter)) {
                return true;
            }
        }
        return false;
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

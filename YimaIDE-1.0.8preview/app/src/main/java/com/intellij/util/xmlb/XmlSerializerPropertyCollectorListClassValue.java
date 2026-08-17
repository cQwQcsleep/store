package com.intellij.util.xmlb;

import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.serialization.MutableAccessor;
import com.intellij.serialization.PropertyCollector;
import com.intellij.util.ClearableClassValue;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/intellij/util/xmlb/XmlSerializerPropertyCollectorListClassValue;", "Lcom/intellij/util/ClearableClassValue;", "", "Lcom/intellij/serialization/MutableAccessor;", "configuration", "Lcom/intellij/serialization/PropertyCollector$Configuration;", "<init>", "(Lcom/intellij/serialization/PropertyCollector$Configuration;)V", "computeValueImpl", "aClass", "Ljava/lang/Class;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class XmlSerializerPropertyCollectorListClassValue extends ClearableClassValue<List<? extends MutableAccessor>> {
    private final PropertyCollector.Configuration configuration;

    public XmlSerializerPropertyCollectorListClassValue(PropertyCollector.Configuration configuration) {
        configuration.getClass();
        this.configuration = configuration;
    }

    @Override // com.intellij.util.ClearableClassValue
    /* JADX INFO: renamed from: computeValueImpl, reason: avoid collision after fix types in other method */
    public List<? extends MutableAccessor> computeValueImpl2(Class<?> aClass) {
        aClass.getClass();
        List<? extends MutableAccessor> listDoCollect = PropertyCollector.doCollect(aClass, this.configuration, (ClassValue) null);
        listDoCollect.getClass();
        if (!listDoCollect.isEmpty() || BeanBindingKt.isAssertBindings(aClass)) {
            return listDoCollect;
        }
        if (JDOMExternalizable.class.isAssignableFrom(aClass)) {
            BeanBindingKt.LOG.error("Do not compute bindings for JDOMExternalizable: ".concat(aClass.getName()));
        } else if (aClass.isEnum()) {
            BeanBindingKt.LOG.error("Do not compute bindings for enum: ".concat(aClass.getName()));
        } else if (aClass == String.class) {
            BeanBindingKt.LOG.error("Do not compute bindings for String");
        }
        BeanBindingKt.LOG.debug("No accessors for " + aClass.getName() + ". This means that state class cannot be serialized properly. Please see https://jb.gg/ij-psoc");
        List<? extends MutableAccessor> list = Collections.EMPTY_LIST;
        list.getClass();
        return list;
    }

    @Override // com.intellij.util.ClearableClassValue
    public /* bridge */ /* synthetic */ List<? extends MutableAccessor> computeValueImpl(Class cls) {
        return computeValueImpl2((Class<?>) cls);
    }
}

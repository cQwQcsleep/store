package com.intellij.util.xmlb;

import com.intellij.serialization.PropertyCollector;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.CollectionBean;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Text;
import com.intellij.util.xmlb.annotations.Transient;
import com.intellij.util.xmlb.annotations.XCollection;
import com.intellij.util.xmlb.annotations.XMap;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/intellij/util/xmlb/MyPropertyCollectorConfiguration;", "Lcom/intellij/serialization/PropertyCollector$Configuration;", "<init>", "()V", "isAnnotatedAsTransient", "", "element", "Ljava/lang/reflect/AnnotatedElement;", "hasStoreAnnotations", "Ljava/lang/reflect/AccessibleObject;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class MyPropertyCollectorConfiguration extends PropertyCollector.Configuration {
    public MyPropertyCollectorConfiguration() {
        super(true, false, false);
    }

    public boolean hasStoreAnnotations(AccessibleObject element) {
        element.getClass();
        return element.isAnnotationPresent(OptionTag.class) || element.isAnnotationPresent(Tag.class) || element.isAnnotationPresent(Attribute.class) || element.isAnnotationPresent(Property.class) || element.isAnnotationPresent(Text.class) || element.isAnnotationPresent(CollectionBean.class) || element.isAnnotationPresent(MapAnnotation.class) || element.isAnnotationPresent(XMap.class) || element.isAnnotationPresent(XCollection.class) || element.isAnnotationPresent(AbstractCollection.class);
    }

    public boolean isAnnotatedAsTransient(AnnotatedElement element) {
        element.getClass();
        return element.isAnnotationPresent(Transient.class);
    }
}

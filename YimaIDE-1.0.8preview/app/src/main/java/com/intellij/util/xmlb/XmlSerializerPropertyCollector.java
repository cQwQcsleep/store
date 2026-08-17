package com.intellij.util.xmlb;

import com.intellij.serialization.MutableAccessor;
import com.intellij.serialization.PropertyCollector;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0016J\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/intellij/util/xmlb/XmlSerializerPropertyCollector;", "Lcom/intellij/serialization/PropertyCollector;", "configuration", "Lcom/intellij/serialization/PropertyCollector$Configuration;", "<init>", "(Lcom/intellij/serialization/PropertyCollector$Configuration;)V", "accessorCache", "Lcom/intellij/util/xmlb/XmlSerializerPropertyCollectorListClassValue;", "collect", "", "Lcom/intellij/serialization/MutableAccessor;", "aClass", "Ljava/lang/Class;", "clear", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class XmlSerializerPropertyCollector extends PropertyCollector {
    private final XmlSerializerPropertyCollectorListClassValue accessorCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public XmlSerializerPropertyCollector(PropertyCollector.Configuration configuration) {
        super(configuration);
        configuration.getClass();
        this.accessorCache = new XmlSerializerPropertyCollectorListClassValue(configuration);
    }

    public List<MutableAccessor> collect(Class<?> aClass) {
        aClass.getClass();
        Object obj = this.accessorCache.get(aClass);
        obj.getClass();
        return (List) obj;
    }
}

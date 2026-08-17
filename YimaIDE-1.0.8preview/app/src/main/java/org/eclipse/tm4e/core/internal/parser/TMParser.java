package org.eclipse.tm4e.core.internal.parser;

import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface TMParser {

    public interface ObjectFactory<T extends PropertySettable<?>> {
        PropertySettable<?> createChild(PropertyPath propertyPath, Class<?> cls);

        T createRoot();
    }

    public interface PropertyPath extends Iterable<Object> {
        int depth();

        Object first();

        Object get(int i);

        Object last();
    }

    <T extends PropertySettable<?>> T parse(Reader reader, ObjectFactory<T> objectFactory) throws Exception;
}

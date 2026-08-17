package com.intellij.util.pico;

import java.util.List;
import org.picocontainer.ComponentAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ComponentParameter {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "expectedType";
        } else if (i != 3) {
            objArr[0] = "container";
        } else {
            objArr[0] = "excludeKey";
        }
        objArr[1] = "com/intellij/util/pico/ComponentParameter";
        if (i == 2 || i == 3) {
            objArr[2] = "getTargetAdapter";
        } else {
            objArr[2] = "resolveAdapter";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    private static ComponentAdapter getTargetAdapter(DefaultPicoContainer defaultPicoContainer, Class<?> cls, Object obj) {
        if (defaultPicoContainer == null) {
            $$$reportNull$$$0(2);
        }
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        ComponentAdapter componentAdapter = defaultPicoContainer.getComponentAdapter(cls);
        if (componentAdapter != null && !obj.equals(componentAdapter.getComponentKey())) {
            return componentAdapter;
        }
        List<ComponentAdapter> componentAdaptersOfType = defaultPicoContainer.getComponentAdaptersOfType(cls);
        ComponentAdapter componentAdapter2 = null;
        for (ComponentAdapter componentAdapter3 : componentAdaptersOfType) {
            if (componentAdapter3.getComponentKey().equals(obj)) {
                componentAdapter2 = componentAdapter3;
            }
        }
        componentAdaptersOfType.remove(componentAdapter2);
        if (componentAdaptersOfType.isEmpty()) {
            if (defaultPicoContainer.getParent() == null) {
                return null;
            }
            return defaultPicoContainer.getParent().getComponentAdapterOfType(cls);
        }
        if (componentAdaptersOfType.size() == 1) {
            return componentAdaptersOfType.get(0);
        }
        int size = componentAdaptersOfType.size();
        Class[] clsArr = new Class[size];
        for (int i = 0; i < size; i++) {
            clsArr[i] = componentAdaptersOfType.get(i).getComponentImplementation();
        }
        throw new AmbiguousComponentResolutionException(cls, clsArr);
    }

    public static ComponentAdapter resolveAdapter(DefaultPicoContainer defaultPicoContainer, ComponentAdapter componentAdapter, Class<?> cls) {
        if (defaultPicoContainer == null) {
            $$$reportNull$$$0(0);
        }
        if (cls == null) {
            $$$reportNull$$$0(1);
        }
        if (componentAdapter == null) {
            return defaultPicoContainer.getComponentAdapter(cls);
        }
        ComponentAdapter targetAdapter = getTargetAdapter(defaultPicoContainer, cls, componentAdapter.getComponentKey());
        if (targetAdapter != null && cls.isAssignableFrom(targetAdapter.getComponentImplementation())) {
            return targetAdapter;
        }
        return null;
    }
}

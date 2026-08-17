package org.picocontainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface ComponentAdapter {
    Class<?> getComponentImplementation();

    Object getComponentInstance();

    Object getComponentKey();
}

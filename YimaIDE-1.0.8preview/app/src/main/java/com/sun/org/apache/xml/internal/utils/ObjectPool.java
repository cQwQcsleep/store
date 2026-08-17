package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ObjectPool implements Serializable {
    static final long serialVersionUID = -8519013691660936643L;
    private final List<Object> freeStack;
    private final Class<?> objectType;

    public ObjectPool(String str) {
        try {
            this.objectType = ObjectFactory.findProviderClass(str, true);
            this.freeStack = new ArrayList();
        } catch (ClassNotFoundException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    public synchronized void freeInstance(Object obj) {
        this.freeStack.add(obj);
    }

    public synchronized Object getInstance() {
        if (this.freeStack.isEmpty()) {
            try {
                return this.objectType.getConstructor(null).newInstance(null);
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
                throw new RuntimeException(XMLMessages.createXMLMessage("ER_EXCEPTION_CREATING_POOL", null));
            }
        }
        List<Object> list = this.freeStack;
        return list.remove(list.size() - 1);
    }

    public synchronized Object getInstanceIfFree() {
        if (this.freeStack.isEmpty()) {
            return null;
        }
        List<Object> list = this.freeStack;
        return list.remove(list.size() - 1);
    }

    public ObjectPool(Class<?> cls) {
        this.objectType = cls;
        this.freeStack = new ArrayList();
    }

    public ObjectPool(Class<?> cls, int i) {
        this.objectType = cls;
        this.freeStack = new ArrayList(i);
    }

    public ObjectPool() {
        this.objectType = null;
        this.freeStack = new ArrayList();
    }
}

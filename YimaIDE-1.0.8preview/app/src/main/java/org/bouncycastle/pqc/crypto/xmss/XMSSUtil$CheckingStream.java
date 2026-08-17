package org.bouncycastle.pqc.crypto.xmss;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class XMSSUtil$CheckingStream extends ObjectInputStream {
    private static final Set components;
    private boolean found;
    private final Class mainClass;

    static {
        HashSet hashSet = new HashSet();
        components = hashSet;
        hashSet.add("java.util.TreeMap");
        hashSet.add("java.lang.Integer");
        hashSet.add("java.lang.Number");
        hashSet.add("org.bouncycastle.pqc.crypto.xmss.BDS");
        hashSet.add("java.util.ArrayList");
        hashSet.add("org.bouncycastle.pqc.crypto.xmss.XMSSNode");
        hashSet.add("[B");
        hashSet.add("java.util.LinkedList");
        hashSet.add("java.util.Stack");
        hashSet.add("java.util.Vector");
        hashSet.add("[Ljava.lang.Object;");
        hashSet.add("org.bouncycastle.pqc.crypto.xmss.BDSTreeHash");
    }

    public XMSSUtil$CheckingStream(Class cls, InputStream inputStream) throws IOException {
        super(inputStream);
        this.found = false;
        this.mainClass = cls;
    }

    @Override // java.io.ObjectInputStream
    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        if (this.found) {
            if (!components.contains(objectStreamClass.getName())) {
                throw new InvalidClassException("unexpected class: ", objectStreamClass.getName());
            }
        } else {
            if (!objectStreamClass.getName().equals(this.mainClass.getName())) {
                throw new InvalidClassException("unexpected class: ", objectStreamClass.getName());
            }
            this.found = true;
        }
        return super.resolveClass(objectStreamClass);
    }
}

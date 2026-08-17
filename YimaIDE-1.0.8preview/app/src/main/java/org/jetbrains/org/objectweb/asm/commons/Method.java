package org.jetbrains.org.objectweb.asm.commons;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Method {
    private static final Map<String, String> PRIMITIVE_TYPE_DESCRIPTORS;
    private final String descriptor;
    private final String name;

    static {
        HashMap map = new HashMap();
        map.put("void", "V");
        map.put("byte", "B");
        map.put("char", "C");
        map.put("double", "D");
        map.put("float", "F");
        map.put("int", "I");
        map.put("long", "J");
        map.put("short", "S");
        map.put("boolean", "Z");
        PRIMITIVE_TYPE_DESCRIPTORS = map;
    }

    public Method(String str, String str2) {
        this.name = str;
        this.descriptor = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Method)) {
            return false;
        }
        Method method = (Method) obj;
        return this.name.equals(method.name) && this.descriptor.equals(method.descriptor);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(this.descriptor);
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String getName() {
        return this.name;
    }

    public Type getReturnType() {
        return Type.getReturnType(this.descriptor);
    }

    public int hashCode() {
        return this.descriptor.hashCode() ^ this.name.hashCode();
    }

    public String toString() {
        return this.name + this.descriptor;
    }

    public Method(String str, Type type, Type[] typeArr) {
        this(str, Type.getMethodDescriptor(type, typeArr));
    }
}

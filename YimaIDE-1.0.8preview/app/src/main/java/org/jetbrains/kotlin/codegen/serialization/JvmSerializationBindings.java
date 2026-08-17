package org.jetbrains.kotlin.codegen.serialization;

import kotlin.Pair;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.util.slicedMap.BasicWritableSlice;
import org.jetbrains.kotlin.util.slicedMap.MutableSlicedMap;
import org.jetbrains.kotlin.util.slicedMap.SlicedMapImpl;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public final class JvmSerializationBindings {
    private final MutableSlicedMap map = new SlicedMapImpl(false);
    public static final SerializationMappingSlice<FunctionDescriptor, Method> METHOD_FOR_FUNCTION = SerializationMappingSlice.create();
    public static final SerializationMappingSlice<PropertyDescriptor, Pair<Type, String>> FIELD_FOR_PROPERTY = SerializationMappingSlice.create();
    public static final SerializationMappingSlice<PropertyDescriptor, Method> SYNTHETIC_METHOD_FOR_PROPERTY = SerializationMappingSlice.create();
    public static final SerializationMappingSlice<PropertyDescriptor, Method> DELEGATE_METHOD_FOR_PROPERTY = SerializationMappingSlice.create();

    /* JADX WARN: Code duplicated, block: B:8:0x0017  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "key";
        } else if (i == 2) {
            objArr[0] = "value";
        } else if (i != 4) {
            objArr[0] = "slice";
        } else {
            objArr[0] = "key";
        }
        objArr[1] = "org/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings";
        if (i == 3 || i == 4) {
            objArr[2] = "get";
        } else {
            objArr[2] = "put";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    static {
        BasicWritableSlice.initSliceDebugNames(JvmSerializationBindings.class);
    }

    public <K, V> V get(SerializationMappingSlice<K, V> serializationMappingSlice, K k) {
        if (serializationMappingSlice == null) {
            $$$reportNull$$$0(3);
        }
        if (k == null) {
            $$$reportNull$$$0(4);
        }
        return (V) this.map.get(serializationMappingSlice, k);
    }

    public <K, V> void put(SerializationMappingSlice<K, V> serializationMappingSlice, K k, V v) {
        if (serializationMappingSlice == null) {
            $$$reportNull$$$0(0);
        }
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        this.map.put(serializationMappingSlice, k, v);
    }
}

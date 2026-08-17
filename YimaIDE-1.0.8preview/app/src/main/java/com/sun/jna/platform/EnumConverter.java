package com.sun.jna.platform;

import com.sun.jna.FromNativeContext;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;
import java.lang.Enum;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumConverter<T extends Enum<T>> implements TypeConverter {
    private final Class<T> clazz;

    public EnumConverter(Class<T> cls) {
        this.clazz = cls;
    }

    @Override // com.sun.jna.FromNativeConverter
    public T fromNative(Object obj, FromNativeContext fromNativeContext) {
        return this.clazz.getEnumConstants()[((Integer) obj).intValue()];
    }

    @Override // com.sun.jna.FromNativeConverter, com.sun.jna.ToNativeConverter
    public Class<Integer> nativeType() {
        return Integer.class;
    }

    @Override // com.sun.jna.ToNativeConverter
    public Integer toNative(Object obj, ToNativeContext toNativeContext) {
        return Integer.valueOf(this.clazz.cast(obj).ordinal());
    }
}

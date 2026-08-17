package org.eclipse.jdt.annotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum DefaultLocation {
    PARAMETER,
    RETURN_TYPE,
    FIELD,
    TYPE_PARAMETER,
    TYPE_BOUND,
    TYPE_ARGUMENT,
    ARRAY_CONTENTS,
    RECORD_COMPONENT;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static DefaultLocation[] valuesCustom() {
        DefaultLocation[] defaultLocationArrValuesCustom = values();
        int length = defaultLocationArrValuesCustom.length;
        DefaultLocation[] defaultLocationArr = new DefaultLocation[length];
        System.arraycopy(defaultLocationArrValuesCustom, 0, defaultLocationArr, 0, length);
        return defaultLocationArr;
    }
}

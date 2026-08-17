package org.eclipse.jdt.internal.compiler.env;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IBinaryTypeAnnotation {
    public static final int[] NO_TYPE_PATH = new int[0];

    IBinaryAnnotation getAnnotation();

    int getBoundIndex();

    int getMethodFormalParameterIndex();

    int getSupertypeIndex();

    int getTargetType();

    int getThrowsTypeIndex();

    int getTypeParameterIndex();

    int[] getTypePath();
}

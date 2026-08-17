package org.jetbrains.kotlin.asJava.classes;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bb\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\n\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/asJava/classes/TypeParametersSupport;", "D", "T", "", "parameters", "", "declaration", "(Ljava/lang/Object;)Ljava/util/List;", "name", "", "typeParameter", "(Ljava/lang/Object;)Ljava/lang/String;", "hasNonTrivialBounds", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "asDescriptor", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
interface TypeParametersSupport<D, T> {
    TypeParameterDescriptor asDescriptor(T typeParameter);

    boolean hasNonTrivialBounds(D declaration, T typeParameter);

    String name(T typeParameter);

    List<T> parameters(D declaration);
}

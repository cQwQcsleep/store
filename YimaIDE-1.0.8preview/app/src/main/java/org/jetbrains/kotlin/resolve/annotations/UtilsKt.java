package org.jetbrains.kotlin.resolve.annotations;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.constants.ConstantValue;
import org.jetbrains.kotlin.resolve.constants.ErrorValue;
import org.jetbrains.kotlin.resolve.constants.StringValue;
import org.jetbrains.kotlin.utils.CollectionUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004\u001a$\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"argumentValue", "Lorg/jetbrains/kotlin/resolve/constants/ConstantValue;", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "parameterName", "", "getAnnotationStringValue", "name", "getArgumentValueOrNull", "T", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;Ljava/lang/String;)Ljava/lang/Object;", "org.jetbrains.kotlin:descriptors"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    public static final ConstantValue<?> argumentValue(AnnotationDescriptor annotationDescriptor, String str) {
        annotationDescriptor.getClass();
        str.getClass();
        Object obj = annotationDescriptor.getAllValueArguments().get(Name.identifier(str));
        if (((ConstantValue) obj) instanceof ErrorValue) {
            obj = null;
        }
        return (ConstantValue) obj;
    }

    public static final String getAnnotationStringValue(AnnotationDescriptor annotationDescriptor, String str) {
        String str2;
        annotationDescriptor.getClass();
        str.getClass();
        StringValue stringValueArgumentValue = argumentValue(annotationDescriptor, str);
        StringValue stringValue = stringValueArgumentValue instanceof StringValue ? stringValueArgumentValue : null;
        if (stringValue != null && (str2 = (String) stringValue.getValue()) != null) {
            return str2;
        }
        a11.a("Expected value ", str, " at annotation ", annotationDescriptor);
        return null;
    }

    public static final /* synthetic */ <T> T getArgumentValueOrNull(AnnotationDescriptor annotationDescriptor, String str) {
        annotationDescriptor.getClass();
        str.getClass();
        Set setEntrySet = annotationDescriptor.getAllValueArguments().entrySet();
        ArrayList arrayList = new ArrayList();
        for (T t : setEntrySet) {
            if (Intrinsics.areEqual(((Name) ((Map.Entry) t).getKey()).asString(), str)) {
                arrayList.add(t);
            }
        }
        Map.Entry entry = (Map.Entry) CollectionUtilKt.atMostOne(arrayList);
        ConstantValue constantValue = entry != null ? (ConstantValue) entry.getValue() : null;
        T t2 = constantValue != null ? (T) constantValue.getValue() : null;
        Intrinsics.reifiedOperationMarker(1, "T?");
        return t2;
    }
}

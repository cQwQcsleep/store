package org.jetbrains.kotlin.types;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.types.checker.KotlinTypeRefiner;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentListMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH'J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/types/SimpleType;", "Lorg/jetbrains/kotlin/types/UnwrappedType;", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "Lorg/jetbrains/kotlin/types/model/TypeArgumentListMarker;", "<init>", "()V", "replaceAttributes", "newAttributes", "Lorg/jetbrains/kotlin/types/TypeAttributes;", "makeNullableAsSpecified", "newNullability", "", "refine", "kotlinTypeRefiner", "Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;", "toString", "", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SimpleType extends UnwrappedType implements SimpleTypeMarker, TypeArgumentListMarker {
    public SimpleType() {
        super((DefaultConstructorMarker) null);
    }

    public abstract SimpleType makeNullableAsSpecified(boolean newNullability);

    public abstract SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner);

    public abstract SimpleType replaceAttributes(TypeAttributes newAttributes);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = getAnnotations().iterator();
        while (it.hasNext()) {
            StringsKt.append(sb, new String[]{"[", DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.DEBUG_TEXT, (AnnotationDescriptor) it.next(), null, 2, null), "] "});
        }
        sb.append(getConstructor());
        if (!getArguments().isEmpty()) {
            CollectionsKt.joinTo$default(getArguments(), sb, ", ", "<", ">", 0, (CharSequence) null, (Function1) null, 112, (Object) null);
        }
        if (isMarkedNullable()) {
            sb.append("?");
        }
        return sb.toString();
    }
}

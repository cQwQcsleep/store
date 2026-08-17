package org.jetbrains.kotlin.backend.common.serialization.mangle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\b2!\u0010\t\u001a\u001d\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u000b\"\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"collectForMangler", "", "T", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "params", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleConstant;", "collect", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "publishedApiAnnotation", "Lorg/jetbrains/kotlin/name/FqName;", "getPublishedApiAnnotation", "()Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class MangleUtilsKt {
    private static final FqName publishedApiAnnotation = new FqName("kotlin.PublishedApi");

    public static final <T> void collectForMangler(Iterable<? extends T> iterable, StringBuilder sb, MangleConstant mangleConstant, Function2<? super StringBuilder, ? super T, Unit> function2) {
        iterable.getClass();
        sb.getClass();
        mangleConstant.getClass();
        function2.getClass();
        sb.append(mangleConstant.getPrefix());
        boolean z = true;
        boolean z2 = true;
        for (T t : iterable) {
            if (z2) {
                z2 = false;
            } else if (z) {
                sb.append(mangleConstant.getSeparator());
            }
            int length = sb.length();
            function2.invoke(sb, t);
            z = length < sb.length();
        }
        if (!z && StringsKt.last(sb) == mangleConstant.getSeparator()) {
            sb.deleteCharAt(StringsKt.getLastIndex(sb));
        }
        sb.append(mangleConstant.getSuffix());
    }

    public static final FqName getPublishedApiAnnotation() {
        return publishedApiAnnotation;
    }
}

package kotlin.jvm.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.reflect.KType;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005Ê\u0001\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¨\u0006\u0006"}, d2 = {"Lkotlin/jvm/internal/KTypeBase;", "Lkotlin/reflect/KType;", "javaType", "Ljava/lang/reflect/Type;", "getJavaType", "()Ljava/lang/reflect/Type;", "kotlin-stdlib", "Lkotlin/SinceKotlin;", "version", "1.4"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface KTypeBase extends KType {
    Type getJavaType();
}

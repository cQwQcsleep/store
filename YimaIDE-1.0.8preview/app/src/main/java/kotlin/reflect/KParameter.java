package kotlin.reflect;

import kotlin.Metadata;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u001bR\u0013\u0010\u0002\u001a\u00020\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u00020\u000bX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u00020\u000fX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u00020\u0013X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R)\u0010\u0015\u001a\u00020\u00138&X§\u0084\br\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a¢\u0006\f\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0015\u0010\u0014¨\u0006\u001c"}, d2 = {"Lkotlin/reflect/KParameter;", "Lkotlin/reflect/KAnnotatedElement;", "index", HttpUrl.FRAGMENT_ENCODE_SET, "getIndex", "()I", "name", HttpUrl.FRAGMENT_ENCODE_SET, "getName", "()Ljava/lang/String;", "type", "Lkotlin/reflect/KType;", "getType", "()Lkotlin/reflect/KType;", "kind", "Lkotlin/reflect/KParameter$Kind;", "getKind", "()Lkotlin/reflect/KParameter$Kind;", "isOptional", HttpUrl.FRAGMENT_ENCODE_SET, "()Z", "isVararg", "isVararg$annotations", "()V", "Lkotlin/SinceKotlin;", "version", "1.1", "Kind", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KParameter extends KAnnotatedElement {
    int getIndex();

    Kind getKind();

    String getName();

    KType getType();

    boolean isOptional();

    boolean isVararg();
}

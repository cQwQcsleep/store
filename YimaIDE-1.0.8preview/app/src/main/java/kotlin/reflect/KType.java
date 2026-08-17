package kotlin.reflect;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R+\u0010\u0002\u001a\u0004\u0018\u00010\u00038&X§\u0084\br\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R/\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8&X§\u0084\br\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n¢\u0006\f\u0012\u0004\b\u000e\u0010\u0005\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u00020\u0012X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013¨\u0006\u0014"}, d2 = {"Lkotlin/reflect/KType;", "Lkotlin/reflect/KAnnotatedElement;", "classifier", "Lkotlin/reflect/KClassifier;", "getClassifier$annotations", "()V", "getClassifier", "()Lkotlin/reflect/KClassifier;", "Lkotlin/SinceKotlin;", "version", "1.1", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "getArguments$annotations", "getArguments", "()Ljava/util/List;", "isMarkedNullable", "", "()Z", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface KType extends KAnnotatedElement {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class DefaultImpls {
        public static /* synthetic */ void getArguments$annotations() {
        }

        public static /* synthetic */ void getClassifier$annotations() {
        }
    }

    List<KTypeProjection> getArguments();

    KClassifier getClassifier();

    boolean isMarkedNullable();
}

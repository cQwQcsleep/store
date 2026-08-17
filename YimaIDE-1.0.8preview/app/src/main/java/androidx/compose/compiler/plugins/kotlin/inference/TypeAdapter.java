package androidx.compose.compiler.plugins.kotlin.inference;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u0004H&¢\u0006\u0002\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/TypeAdapter;", "Type", "", "declaredSchemaOf", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "type", "(Ljava/lang/Object;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "currentInferredSchemeOf", "updatedInferredScheme", "", "scheme", "(Ljava/lang/Object;Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface TypeAdapter<Type> {
    Scheme currentInferredSchemeOf(Type type);

    Scheme declaredSchemaOf(Type type);

    void updatedInferredScheme(Type type, Scheme scheme);
}

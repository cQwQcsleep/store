package org.jetbrains.kotlin.psi;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameRenderingUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class CreateByPatternKt$SUPPORTED_ARGUMENT_TYPES$2 extends AdaptedFunctionReference implements Function1<Name, String> {
    public static final CreateByPatternKt$SUPPORTED_ARGUMENT_TYPES$2 INSTANCE = new CreateByPatternKt$SUPPORTED_ARGUMENT_TYPES$2();

    public CreateByPatternKt$SUPPORTED_ARGUMENT_TYPES$2() {
        super(1, NameRenderingUtils.class, "render", "render(Lorg/jetbrains/kotlin/name/Name;Z)Ljava/lang/String;", 1);
    }

    public final String invoke(Name name) {
        name.getClass();
        return NameRenderingUtils.render$default(name, false, 1, null);
    }
}

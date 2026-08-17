package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H&R\u001e\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", Argument.Delimiters.none, "<init>", "()V", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getBuilder", "()Ljava/lang/StringBuilder;", "setBuilder", "(Ljava/lang/StringBuilder;)V", "renderClassId", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "renderCallableId", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeIdRenderer {
    public StringBuilder builder;

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final StringBuilder getBuilder() throws UninitializedPropertyAccessException {
        StringBuilder sb = this.builder;
        if (sb != null) {
            return sb;
        }
        Intrinsics.throwUninitializedPropertyAccessException("builder");
        return null;
    }

    public abstract void renderCallableId(CallableId callableId);

    public abstract void renderClassId(ClassId classId);

    public final void setBuilder(StringBuilder sb) {
        sb.getClass();
        this.builder = sb;
    }
}

package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeIdFullRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "<init>", "()V", "renderClassId", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "renderCallableId", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeIdFullRenderer extends ConeIdRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.ConeIdRenderer
    public void renderCallableId(CallableId callableId) {
        callableId.getClass();
        getBuilder().append(callableId);
    }

    @Override // org.jetbrains.kotlin.fir.renderer.ConeIdRenderer
    public void renderClassId(ClassId classId) {
        classId.getClass();
        getBuilder().append(classId.asString());
    }
}

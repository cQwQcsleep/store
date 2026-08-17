package androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"hiddenFromObjCClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getHiddenFromObjCClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class AddHiddenFromObjCLoweringKt {
    private static final ClassId hiddenFromObjCClassId = ClassId.Companion.fromString$default(ClassId.Companion, "kotlin/native/HiddenFromObjC", false, 2, (Object) null);

    public static final ClassId getHiddenFromObjCClassId() {
        return hiddenFromObjCClassId;
    }
}

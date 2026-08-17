package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/types/AbstractTypePreparator$Default;", "Lorg/jetbrains/kotlin/types/AbstractTypePreparator;", "<init>", "()V", "prepareType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "type", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AbstractTypePreparator$Default extends AbstractTypePreparator {
    public static final AbstractTypePreparator$Default INSTANCE = new AbstractTypePreparator$Default();

    private AbstractTypePreparator$Default() {
    }

    public KotlinTypeMarker prepareType(KotlinTypeMarker type) {
        type.getClass();
        return type;
    }
}

package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/util/ModuleVisibilityHelper$EMPTY;", "Lorg/jetbrains/kotlin/util/ModuleVisibilityHelper;", "<init>", "()V", "isInFriendModule", "", "what", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "from", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class ModuleVisibilityHelper$EMPTY implements ModuleVisibilityHelper {
    public static final ModuleVisibilityHelper$EMPTY INSTANCE = new ModuleVisibilityHelper$EMPTY();

    private ModuleVisibilityHelper$EMPTY() {
    }

    public boolean isInFriendModule(DeclarationDescriptor what, DeclarationDescriptor from) {
        what.getClass();
        from.getClass();
        return true;
    }
}

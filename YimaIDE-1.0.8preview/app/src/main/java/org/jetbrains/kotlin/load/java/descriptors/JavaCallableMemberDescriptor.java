package org.jetbrains.kotlin.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface JavaCallableMemberDescriptor extends CallableMemberDescriptor {
    JavaCallableMemberDescriptor enhance(KotlinType kotlinType, List<KotlinType> list, KotlinType kotlinType2, Pair<CallableDescriptor.UserDataKey<?>, ?> pair);
}

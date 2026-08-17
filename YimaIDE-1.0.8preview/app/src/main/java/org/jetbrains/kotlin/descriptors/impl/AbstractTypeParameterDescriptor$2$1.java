package org.jetbrains.kotlin.descriptors.impl;

import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.resolve.scopes.TypeIntersectionScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class AbstractTypeParameterDescriptor$2$1 implements Function0<MemberScope> {
    final /* synthetic */ AbstractTypeParameterDescriptor.2 this$1;

    public AbstractTypeParameterDescriptor$2$1(AbstractTypeParameterDescriptor.2 r1) {
        this.this$1 = r1;
    }

    public MemberScope invoke() {
        return TypeIntersectionScope.create("Scope for type parameter " + this.this$1.val$name.asString(), this.this$1.this$0.getUpperBounds());
    }
}

package org.jetbrains.kotlin.js.naming;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/js/naming/SuggestedName;", "", "names", "", "", "stable", "", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "scope", "<init>", "(Ljava/util/List;ZLorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)V", "getNames", "()Ljava/util/List;", "getStable", "()Z", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getScope", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SuggestedName {
    private final DeclarationDescriptor descriptor;
    private final List<String> names;
    private final DeclarationDescriptor scope;
    private final boolean stable;

    public SuggestedName(List<String> list, boolean z, DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        list.getClass();
        declarationDescriptor.getClass();
        declarationDescriptor2.getClass();
        this.names = list;
        this.stable = z;
        this.descriptor = declarationDescriptor;
        this.scope = declarationDescriptor2;
    }

    public final DeclarationDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final List<String> getNames() {
        return this.names;
    }

    public final DeclarationDescriptor getScope() {
        return this.scope;
    }

    public final boolean getStable() {
        return this.stable;
    }
}

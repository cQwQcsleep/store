package org.jetbrains.kotlin.contracts.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.inference.components.EmptySubstitutor;
import org.jetbrains.kotlin.resolve.calls.inference.components.NewTypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", Argument.Delimiters.none, "substitutor", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/NewTypeSubstitutor;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/inference/components/NewTypeSubstitutor;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;)V", "getSubstitutor", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/NewTypeSubstitutor;", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "Companion", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESTypeSubstitution {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final KotlinBuiltIns builtIns;
    private final NewTypeSubstitutor substitutor;

    public ESTypeSubstitution(NewTypeSubstitutor newTypeSubstitutor, KotlinBuiltIns kotlinBuiltIns) {
        newTypeSubstitutor.getClass();
        kotlinBuiltIns.getClass();
        this.substitutor = newTypeSubstitutor;
        this.builtIns = kotlinBuiltIns;
    }

    public final KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    public final NewTypeSubstitutor getSubstitutor() {
        return this.substitutor;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution$Companion;", Argument.Delimiters.none, "<init>", "()V", "empty", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ESTypeSubstitution empty(KotlinBuiltIns builtIns) {
            builtIns.getClass();
            return new ESTypeSubstitution(EmptySubstitutor.INSTANCE, builtIns);
        }

        private Companion() {
        }
    }
}

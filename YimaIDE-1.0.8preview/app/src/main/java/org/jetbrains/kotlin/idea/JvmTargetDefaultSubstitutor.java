package org.jetbrains.kotlin.idea;

import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KMutableProperty1;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.library.KlibConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Deprecated(message = "Minimal supported jvmTarget version is 1.8")
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0002J\u0016\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010H\u0016R\"\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/idea/JvmTargetDefaultSubstitutor;", "Lorg/jetbrains/kotlin/idea/ExplicitDefaultSubstitutor;", "<init>", "()V", "substitutedProperty", "Lkotlin/reflect/KMutableProperty1;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "", "getSubstitutedProperty", "()Lkotlin/reflect/KMutableProperty1;", "oldDefault", "getOldDefault", "()Ljava/lang/String;", "newDefault", "getNewDefault", "prepareSubstitution", "", KlibConstants.KLIB_DEFAULT_COMPONENT_NAME, "oldSubstitution", "getOldSubstitution", "()Ljava/util/List;", "newSubstitution", "getNewSubstitution", "isSubstitutable", "", "args", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JvmTargetDefaultSubstitutor extends ExplicitDefaultSubstitutor {
    public static final JvmTargetDefaultSubstitutor INSTANCE = new JvmTargetDefaultSubstitutor();

    private JvmTargetDefaultSubstitutor() {
        super(null);
    }

    private final String getNewDefault() {
        return JvmTarget.JVM_1_8.getDescription();
    }

    private final String getOldDefault() {
        return JvmTarget.JVM_1_6.getDescription();
    }

    private final List<String> prepareSubstitution(String str) {
        return CollectionsKt.listOf(new String[]{getArgument().value(), str});
    }

    @Override // org.jetbrains.kotlin.idea.ExplicitDefaultSubstitutor
    public List<String> getNewSubstitution() {
        return prepareSubstitution(getNewDefault());
    }

    @Override // org.jetbrains.kotlin.idea.ExplicitDefaultSubstitutor
    public List<String> getOldSubstitution() {
        return prepareSubstitution(getOldDefault());
    }

    @Override // org.jetbrains.kotlin.idea.ExplicitDefaultSubstitutor
    public boolean isSubstitutable(List<String> args) {
        args.getClass();
        return !args.contains(getArgument().value());
    }

    @Override // org.jetbrains.kotlin.idea.ExplicitDefaultSubstitutor
    /* JADX INFO: renamed from: getSubstitutedProperty, reason: merged with bridge method [inline-methods] */
    public KMutableProperty1<K2JVMCompilerArguments, String> mo79getSubstitutedProperty() {
        return new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.idea.JvmTargetDefaultSubstitutor$substitutedProperty$1
            public Object get(Object obj) {
                return ((K2JVMCompilerArguments) obj).getJvmTarget();
            }

            public void set(Object obj, Object obj2) {
                ((K2JVMCompilerArguments) obj).setJvmTarget((String) obj2);
            }
        };
    }
}

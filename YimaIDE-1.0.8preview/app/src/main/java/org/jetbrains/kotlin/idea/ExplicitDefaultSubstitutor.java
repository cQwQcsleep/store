package org.jetbrains.kotlin.idea;

import defpackage.b88;
import java.lang.reflect.Field;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KProperty1;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonToolArguments;
import org.jetbrains.kotlin.idea.ExplicitDefaultSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH&R\"\u0010\u0004\u001a\u0012\u0012\u0006\b\u0001\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001b\u0010\u0013\u001a\u00020\u00148DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016\u0082\u0001\u0001\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/idea/ExplicitDefaultSubstitutor;", "", "<init>", "()V", "substitutedProperty", "Lkotlin/reflect/KProperty1;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "", "getSubstitutedProperty", "()Lkotlin/reflect/KProperty1;", "oldSubstitution", "", "getOldSubstitution", "()Ljava/util/List;", "newSubstitution", "getNewSubstitution", "isSubstitutable", "", "args", "argument", "Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "getArgument", "()Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "argument$delegate", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/idea/JvmTargetDefaultSubstitutor;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ExplicitDefaultSubstitutor {

    /* JADX INFO: renamed from: argument$delegate, reason: from kotlin metadata */
    private final Lazy argument;

    private ExplicitDefaultSubstitutor() {
        this.argument = LazyKt.lazy(new Function0() { // from class: cf4
            public final Object invoke() {
                return ExplicitDefaultSubstitutor.a(this.b);
            }
        });
    }

    public static Argument a(ExplicitDefaultSubstitutor explicitDefaultSubstitutor) {
        Argument annotation;
        Field javaField = ReflectJvmMapping.getJavaField(explicitDefaultSubstitutor.mo79getSubstitutedProperty());
        if (javaField != null && (annotation = javaField.getAnnotation(Argument.class)) != null) {
            return annotation;
        }
        b88.a("Property \"", explicitDefaultSubstitutor.mo79getSubstitutedProperty().getName(), "\" has no Argument annotation");
        return null;
    }

    public final Argument getArgument() {
        return (Argument) this.argument.getValue();
    }

    public abstract List<String> getNewSubstitution();

    public abstract List<String> getOldSubstitution();

    /* JADX INFO: renamed from: getSubstitutedProperty */
    public abstract KProperty1<? extends CommonToolArguments, String> mo79getSubstitutedProperty();

    public abstract boolean isSubstitutable(List<String> args);

    public /* synthetic */ ExplicitDefaultSubstitutor(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

package org.jetbrains.kotlin.idea;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.CommonToolArguments;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\"+\u0010\u0000\u001a\u001c\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"defaultSubstitutors", "", "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "", "Lorg/jetbrains/kotlin/idea/ExplicitDefaultSubstitutor;", "getDefaultSubstitutors", "()Ljava/util/Map;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ExplicitDefaultSubstitutorsKt {
    private static final Map<KClass<? extends CommonToolArguments>, Collection<ExplicitDefaultSubstitutor>> defaultSubstitutors = MapsKt.emptyMap();

    public static final Map<KClass<? extends CommonToolArguments>, Collection<ExplicitDefaultSubstitutor>> getDefaultSubstitutors() {
        return defaultSubstitutors;
    }
}

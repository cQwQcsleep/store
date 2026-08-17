package org.jetbrains.kotlin.backend.common.descriptors;

import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorPsiUtilsKt;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\"V\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0087\u0004r0\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\"\b\u000b\u0012\u001e\b\u000bB\u001a\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0000\u0012\f\b\u000e\u0012\b\b\fJ\u0004\b\b(\u000f¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"explicitParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "getExplicitParameters$annotations", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)V", "getExplicitParameters", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Ljava/util/List;", "Lkotlin/Deprecated;", "message", "Please use org.jetbrains.kotlin.descriptors.explicitParameters", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "imports", "org.jetbrains.kotlin.descriptors.explicitParameters", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DescriptorPsiUtilsCompatKt {
    public static final List<ParameterDescriptor> getExplicitParameters(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return DescriptorPsiUtilsKt.getExplicitParameters(callableDescriptor);
    }

    @Deprecated(message = "Please use org.jetbrains.kotlin.descriptors.explicitParameters", replaceWith = @ReplaceWith(expression = "explicitParameters", imports = {"org.jetbrains.kotlin.descriptors.explicitParameters"}))
    public static /* synthetic */ void getExplicitParameters$annotations(CallableDescriptor callableDescriptor) {
    }
}

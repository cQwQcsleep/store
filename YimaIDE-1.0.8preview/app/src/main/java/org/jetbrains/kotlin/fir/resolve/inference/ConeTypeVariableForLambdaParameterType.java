package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaParameterType;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeVariableForLambdaParameterType extends ConeTypeVariable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeVariableForLambdaParameterType(String str) {
        super(str, null, 2, null);
        str.getClass();
    }
}

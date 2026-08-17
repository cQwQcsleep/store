package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u00102\u001a\u00020\t2\u0006\u00103\u001a\u000201H\u0002J\u0010\u00104\u001a\u00020\t2\u0006\u00103\u001a\u000201H\u0002J\u0010\u00105\u001a\u00020\t2\u0006\u00103\u001a\u000201H\u0002J\u0010\u00106\u001a\u00020\t2\u0006\u00103\u001a\u000201H\u0002J\u0018\u00106\u001a\u00020\t2\u0006\u00107\u001a\u0002012\u0006\u00103\u001a\u000201H\u0002J \u00106\u001a\u00020\t2\u0006\u00107\u001a\u0002012\b\u00108\u001a\u0004\u0018\u0001012\u0006\u00103\u001a\u000201R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000bR\u0011\u0010\u001c\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000bR\u0011\u0010\u001e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000bR\u0011\u0010 \u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000bR\u0011\u0010\"\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000bR\u0011\u0010$\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000bR\u0011\u0010&\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u000bR\u0011\u0010(\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u000bR\u0011\u0010*\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u000bR\u0011\u0010,\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u000bR\u0011\u0010.\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u000bR\u000e\u00100\u001a\u000201X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000201X\u0082T¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ContractsDslNames;", Argument.Delimiters.none, "<init>", "()V", "CONTRACTS_DSL_ANNOTATION_FQN", "Lorg/jetbrains/kotlin/name/FqName;", "getCONTRACTS_DSL_ANNOTATION_FQN", "()Lorg/jetbrains/kotlin/name/FqName;", "SIMPLE_EFFECT", "Lorg/jetbrains/kotlin/name/CallableId;", "getSIMPLE_EFFECT", "()Lorg/jetbrains/kotlin/name/CallableId;", "EFFECT", "getEFFECT", "CONDITIONAL_EFFECT", "getCONDITIONAL_EFFECT", "RETURNS_EFFECT", "getRETURNS_EFFECT", "RETURNS_NOT_NULL_EFFECT", "getRETURNS_NOT_NULL_EFFECT", "CALLS_IN_PLACE_EFFECT", "getCALLS_IN_PLACE_EFFECT", "CONTRACT", "getCONTRACT", "IMPLIES", "getIMPLIES", "RETURNS", "getRETURNS", "RETURNS_NOT_NULL", "getRETURNS_NOT_NULL", "CALLS_IN_PLACE", "getCALLS_IN_PLACE", "IMPLIES_BUILDER", "getIMPLIES_BUILDER", "HOLDS_IN", "getHOLDS_IN", "RETURNS_RESULT_OF", "getRETURNS_RESULT_OF", "INVOCATION_KIND_ENUM", "getINVOCATION_KIND_ENUM", "EXACTLY_ONCE_KIND", "getEXACTLY_ONCE_KIND", "AT_LEAST_ONCE_KIND", "getAT_LEAST_ONCE_KIND", "UNKNOWN_KIND", "getUNKNOWN_KIND", "AT_MOST_ONCE_KIND", "getAT_MOST_ONCE_KIND", "CONTRACT_BUILDER", Argument.Delimiters.none, "contractBuilder", ModuleXmlParser.NAME, "invocationKind", "simpleEffect", "id", "packageName", "className", "CONTRACT_PACKAGE", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractsDslNames {
    private static final CallableId AT_LEAST_ONCE_KIND;
    private static final CallableId AT_MOST_ONCE_KIND;
    private static final CallableId CALLS_IN_PLACE;
    private static final CallableId CALLS_IN_PLACE_EFFECT;
    private static final CallableId CONDITIONAL_EFFECT;
    private static final CallableId CONTRACT;
    private static final FqName CONTRACTS_DSL_ANNOTATION_FQN;
    private static final CallableId EFFECT;
    private static final CallableId EXACTLY_ONCE_KIND;
    private static final CallableId HOLDS_IN;
    private static final CallableId IMPLIES;
    private static final CallableId IMPLIES_BUILDER;
    public static final ContractsDslNames INSTANCE;
    private static final CallableId INVOCATION_KIND_ENUM;
    private static final CallableId RETURNS;
    private static final CallableId RETURNS_EFFECT;
    private static final CallableId RETURNS_NOT_NULL;
    private static final CallableId RETURNS_NOT_NULL_EFFECT;
    private static final CallableId RETURNS_RESULT_OF;
    private static final CallableId SIMPLE_EFFECT;
    private static final CallableId UNKNOWN_KIND;

    static {
        ContractsDslNames contractsDslNames = new ContractsDslNames();
        INSTANCE = contractsDslNames;
        CONTRACTS_DSL_ANNOTATION_FQN = new FqName("kotlin.internal.ContractsDsl");
        SIMPLE_EFFECT = contractsDslNames.id("SimpleEffect");
        EFFECT = contractsDslNames.id("Effect");
        CONDITIONAL_EFFECT = contractsDslNames.id("ConditionalEffect");
        RETURNS_EFFECT = contractsDslNames.id("Returns");
        RETURNS_NOT_NULL_EFFECT = contractsDslNames.id("ReturnsNotNull");
        CALLS_IN_PLACE_EFFECT = contractsDslNames.id("CallsInPlace");
        CONTRACT = contractsDslNames.id("contract");
        IMPLIES = contractsDslNames.simpleEffect("implies");
        RETURNS = contractsDslNames.contractBuilder("returns");
        RETURNS_NOT_NULL = contractsDslNames.contractBuilder("returnsNotNull");
        CALLS_IN_PLACE = contractsDslNames.contractBuilder("callsInPlace");
        IMPLIES_BUILDER = contractsDslNames.contractBuilder("implies");
        HOLDS_IN = contractsDslNames.contractBuilder("holdsIn");
        RETURNS_RESULT_OF = contractsDslNames.contractBuilder("returnsResultOf");
        INVOCATION_KIND_ENUM = contractsDslNames.id("InvocationKind");
        EXACTLY_ONCE_KIND = contractsDslNames.invocationKind("EXACTLY_ONCE");
        AT_LEAST_ONCE_KIND = contractsDslNames.invocationKind("AT_LEAST_ONCE");
        UNKNOWN_KIND = contractsDslNames.invocationKind("UNKNOWN");
        AT_MOST_ONCE_KIND = contractsDslNames.invocationKind("AT_MOST_ONCE");
    }

    private ContractsDslNames() {
    }

    private final CallableId contractBuilder(String name) {
        return id("kotlin.contracts", "ContractBuilder", name);
    }

    private final CallableId invocationKind(String name) {
        return id("kotlin.contracts", INVOCATION_KIND_ENUM.getCallableName().asString(), name);
    }

    private final CallableId simpleEffect(String name) {
        return id("kotlin.contracts", SIMPLE_EFFECT.getCallableName().asString(), name);
    }

    public final CallableId getAT_LEAST_ONCE_KIND() {
        return AT_LEAST_ONCE_KIND;
    }

    public final CallableId getAT_MOST_ONCE_KIND() {
        return AT_MOST_ONCE_KIND;
    }

    public final CallableId getCALLS_IN_PLACE() {
        return CALLS_IN_PLACE;
    }

    public final CallableId getCALLS_IN_PLACE_EFFECT() {
        return CALLS_IN_PLACE_EFFECT;
    }

    public final CallableId getCONDITIONAL_EFFECT() {
        return CONDITIONAL_EFFECT;
    }

    public final CallableId getCONTRACT() {
        return CONTRACT;
    }

    public final FqName getCONTRACTS_DSL_ANNOTATION_FQN() {
        return CONTRACTS_DSL_ANNOTATION_FQN;
    }

    public final CallableId getEFFECT() {
        return EFFECT;
    }

    public final CallableId getEXACTLY_ONCE_KIND() {
        return EXACTLY_ONCE_KIND;
    }

    public final CallableId getHOLDS_IN() {
        return HOLDS_IN;
    }

    public final CallableId getIMPLIES() {
        return IMPLIES;
    }

    public final CallableId getIMPLIES_BUILDER() {
        return IMPLIES_BUILDER;
    }

    public final CallableId getINVOCATION_KIND_ENUM() {
        return INVOCATION_KIND_ENUM;
    }

    public final CallableId getRETURNS() {
        return RETURNS;
    }

    public final CallableId getRETURNS_EFFECT() {
        return RETURNS_EFFECT;
    }

    public final CallableId getRETURNS_NOT_NULL() {
        return RETURNS_NOT_NULL;
    }

    public final CallableId getRETURNS_NOT_NULL_EFFECT() {
        return RETURNS_NOT_NULL_EFFECT;
    }

    public final CallableId getRETURNS_RESULT_OF() {
        return RETURNS_RESULT_OF;
    }

    public final CallableId getSIMPLE_EFFECT() {
        return SIMPLE_EFFECT;
    }

    public final CallableId getUNKNOWN_KIND() {
        return UNKNOWN_KIND;
    }

    public final CallableId id(String packageName, String className, String name) {
        packageName.getClass();
        name.getClass();
        FqName fqName = new FqName(packageName);
        FqName fqName2 = className != null ? new FqName(className) : null;
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        return new CallableId(fqName, fqName2, nameIdentifier);
    }

    private final CallableId id(String packageName, String name) {
        return id(packageName, null, name);
    }

    private final CallableId id(String name) {
        return id("kotlin.contracts", name);
    }
}

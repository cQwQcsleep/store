package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B9\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMapping;", "KT", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "asmType", "Lorg/jetbrains/org/objectweb/asm/Type;", "signature", Argument.Delimiters.none, "isReified", Argument.Delimiters.none, "reificationArgument", "Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "reifiedTypeParametersUsages", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "<init>", "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/org/objectweb/asm/Type;Ljava/lang/String;ZLorg/jetbrains/kotlin/codegen/inline/ReificationArgument;Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;)V", "getType", "()Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "getAsmType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getSignature", "()Ljava/lang/String;", "()Z", "getReificationArgument", "()Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "getReifiedTypeParametersUsages", "()Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeParameterMapping<KT extends KotlinTypeMarker> {
    private final Type asmType;
    private final boolean isReified;
    private final ReificationArgument reificationArgument;
    private final ReifiedTypeParametersUsages reifiedTypeParametersUsages;
    private final String signature;
    private final KT type;

    public TypeParameterMapping(KT kt, Type type, String str, boolean z, ReificationArgument reificationArgument, ReifiedTypeParametersUsages reifiedTypeParametersUsages) {
        kt.getClass();
        type.getClass();
        str.getClass();
        reifiedTypeParametersUsages.getClass();
        this.type = kt;
        this.asmType = type;
        this.signature = str;
        this.isReified = z;
        this.reificationArgument = reificationArgument;
        this.reifiedTypeParametersUsages = reifiedTypeParametersUsages;
    }

    public final Type getAsmType() {
        return this.asmType;
    }

    public final ReificationArgument getReificationArgument() {
        return this.reificationArgument;
    }

    public final ReifiedTypeParametersUsages getReifiedTypeParametersUsages() {
        return this.reifiedTypeParametersUsages;
    }

    public final String getSignature() {
        return this.signature;
    }

    public final KT getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isReified, reason: from getter */
    public final boolean getIsReified() {
        return this.isReified;
    }
}

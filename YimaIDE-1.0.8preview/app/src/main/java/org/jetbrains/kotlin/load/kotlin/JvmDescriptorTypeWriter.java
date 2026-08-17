package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u0015\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u0004\u0018\u00018\u00002\b\u0010\t\u001a\u0004\u0018\u00018\u0000@BX\u0084\u000e¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmDescriptorTypeWriter;", "T", Argument.Delimiters.none, "jvmTypeFactory", "Lorg/jetbrains/kotlin/load/kotlin/JvmTypeFactory;", "<init>", "(Lorg/jetbrains/kotlin/load/kotlin/JvmTypeFactory;)V", "jvmCurrentTypeArrayLevel", Argument.Delimiters.none, "value", "jvmCurrentType", "getJvmCurrentType", "()Ljava/lang/Object;", "Ljava/lang/Object;", "clearCurrentType", Argument.Delimiters.none, "writeArrayType", "writeArrayEnd", "writeClass", "objectType", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "writeJvmTypeAsIs", ModuleXmlParser.TYPE, "writeTypeVariable", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/name/Name;Ljava/lang/Object;)V", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class JvmDescriptorTypeWriter<T> {
    private T jvmCurrentType;
    private int jvmCurrentTypeArrayLevel;
    private final JvmTypeFactory<T> jvmTypeFactory;

    public JvmDescriptorTypeWriter(JvmTypeFactory<T> jvmTypeFactory) {
        jvmTypeFactory.getClass();
        this.jvmTypeFactory = jvmTypeFactory;
    }

    public final void clearCurrentType() {
        this.jvmCurrentType = null;
        this.jvmCurrentTypeArrayLevel = 0;
    }

    public final T getJvmCurrentType() {
        return this.jvmCurrentType;
    }

    public void writeArrayEnd() {
    }

    public void writeArrayType() {
        if (this.jvmCurrentType == null) {
            this.jvmCurrentTypeArrayLevel++;
        }
    }

    public void writeClass(T objectType) {
        objectType.getClass();
        writeJvmTypeAsIs(objectType);
    }

    public final void writeJvmTypeAsIs(T type) {
        type.getClass();
        if (this.jvmCurrentType == null) {
            if (this.jvmCurrentTypeArrayLevel > 0) {
                type = (T) this.jvmTypeFactory.createFromString(StringsKt.repeat("[", this.jvmCurrentTypeArrayLevel) + this.jvmTypeFactory.toString(type));
            }
            this.jvmCurrentType = type;
        }
    }

    public void writeTypeVariable(Name name, T type) {
        name.getClass();
        type.getClass();
        writeJvmTypeAsIs(type);
    }
}

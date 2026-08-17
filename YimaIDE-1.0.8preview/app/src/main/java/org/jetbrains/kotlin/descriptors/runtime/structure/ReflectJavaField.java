package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaField;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaField;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaMember;", "Lorg/jetbrains/kotlin/load/java/structure/JavaField;", "member", "Ljava/lang/reflect/Field;", "<init>", "(Ljava/lang/reflect/Field;)V", "getMember", "()Ljava/lang/reflect/Field;", "isEnumEntry", Argument.Delimiters.none, "()Z", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "getType", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "initializerValue", Argument.Delimiters.none, "getInitializerValue", "()Ljava/lang/Object;", "hasConstantNotNullInitializer", "getHasConstantNotNullInitializer", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectJavaField extends ReflectJavaMember implements JavaField {
    private final Field member;

    public ReflectJavaField(Field field) {
        field.getClass();
        this.member = field;
    }

    public boolean getHasConstantNotNullInitializer() {
        return false;
    }

    public Object getInitializerValue() {
        return null;
    }

    /* JADX INFO: renamed from: getType, reason: merged with bridge method [inline-methods] */
    public ReflectJavaType m183getType() {
        ReflectJavaType.Companion companion = ReflectJavaType.INSTANCE;
        Type genericType = getMember().getGenericType();
        genericType.getClass();
        return companion.create(genericType);
    }

    public boolean isEnumEntry() {
        return getMember().isEnumConstant();
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaMember
    public Field getMember() {
        return this.member;
    }
}

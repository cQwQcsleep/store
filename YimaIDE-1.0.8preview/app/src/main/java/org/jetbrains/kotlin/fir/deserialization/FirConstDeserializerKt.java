package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"buildFirConstant", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "protoValue", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation$Argument$Value;", "sourceValue", Argument.Delimiters.none, "constKind", Argument.Delimiters.none, "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "isUnsigned", Argument.Delimiters.none, "replaceName", "Lorg/jetbrains/kotlin/name/CallableId;", "newName", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstDeserializerKt {
    /* JADX WARN: Code duplicated, block: B:105:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:107:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:109:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:122:0x020f  */
    /* JADX WARN: Code duplicated, block: B:37:0x0077  */
    /* JADX WARN: Code duplicated, block: B:44:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:46:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:55:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:57:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:60:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:67:0x0110  */
    /* JADX WARN: Code duplicated, block: B:69:0x0114  */
    /* JADX WARN: Code duplicated, block: B:71:0x0119  */
    /* JADX WARN: Code duplicated, block: B:72:0x011f  */
    /* JADX WARN: Code duplicated, block: B:80:0x0146  */
    /* JADX WARN: Code duplicated, block: B:81:0x014b  */
    /* JADX WARN: Code duplicated, block: B:89:0x0172  */
    /* JADX WARN: Code duplicated, block: B:90:0x0177  */
    /* JADX WARN: Code duplicated, block: B:98:0x019d  */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01c5, code lost:
    
        if (r10.equals("B") == false) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01cc, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01d1, code lost:
    
        if (r8 != null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01d3, code lost:
    
        r9 = java.lang.Long.valueOf(r8.getIntValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01db, code lost:
    
        r9.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01f3, code lost:
    
        return org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r1, java.lang.Byte.valueOf(((java.lang.Number) r9).byteValue()), null, true, null, 40, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
    
        if (r10.equals("FLOAT") == false) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
    
        if (r10.equals("LONG") == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
    
        if (r10.equals("CHAR") == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        if (r10.equals("BYTE") == false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
    
        if (r10.equals("INT") == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a1, code lost:
    
        if (r10.equals("S") == false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a9, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ae, code lost:
    
        if (r8 != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b0, code lost:
    
        r9 = java.lang.Long.valueOf(r8.getIntValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b8, code lost:
    
        r9.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d0, code lost:
    
        return org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r1, java.lang.Short.valueOf(((java.lang.Number) r9).shortValue()), null, true, null, 40, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d7, code lost:
    
        if (r10.equals("J") == false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00df, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00e4, code lost:
    
        if (r8 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e6, code lost:
    
        r8 = r8.getIntValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00eb, code lost:
    
        r9.getClass();
        r8 = ((java.lang.Long) r9).longValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0103, code lost:
    
        return org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r1, java.lang.Long.valueOf(r8), null, true, null, 40, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x010a, code lost:
    
        if (r10.equals("I") == false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0112, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0117, code lost:
    
        if (r8 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0119, code lost:
    
        r8 = (int) r8.getIntValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x011f, code lost:
    
        r9.getClass();
        r8 = ((java.lang.Integer) r9).intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0137, code lost:
    
        return org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r1, java.lang.Integer.valueOf(r8), null, true, null, 40, null);
     */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v5 org.jetbrains.kotlin.types.ConstantValueKind$UnsignedShort, still in use, count: 1, list:
      (r1v5 org.jetbrains.kotlin.types.ConstantValueKind$UnsignedShort) from 0x00cc: INVOKE 
      (null org.jetbrains.kotlin.KtSourceElement)
      (r1v5 org.jetbrains.kotlin.types.ConstantValueKind$UnsignedShort)
      (wrap java.lang.Short:0x00c1: INVOKE 
      (wrap short:0x00bd: INVOKE (wrap java.lang.Number:0x00bb: CHECK_CAST (java.lang.Number) (r9v10 java.lang.Object)) VIRTUAL call: java.lang.Number.shortValue():short A[MD:():short (c), WRAPPED])
     STATIC call: java.lang.Short.valueOf(short):java.lang.Short A[MD:(short):java.lang.Short (c), WRAPPED])
      (null java.util.List)
      true
      (null java.lang.String)
      (40 int)
      (null java.lang.Object)
     STATIC call: org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt.buildLiteralExpression$default(org.jetbrains.kotlin.KtSourceElement, org.jetbrains.kotlin.types.ConstantValueKind, java.lang.Object, java.util.List, boolean, java.lang.String, int, java.lang.Object):org.jetbrains.kotlin.fir.expressions.FirLiteralExpression A[MD:(org.jetbrains.kotlin.KtSourceElement, org.jetbrains.kotlin.types.ConstantValueKind, java.lang.Object, java.util.List, boolean, java.lang.String, int, java.lang.Object):org.jetbrains.kotlin.fir.expressions.FirLiteralExpression (m), WRAPPED]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
    	at jadx.core.utils.InsnRemover.removeAllMarked(InsnRemover.java:276)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:354)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final FirLiteralExpression buildFirConstant(ProtoBuf.Annotation.Argument.Value value, Object obj, String str, NameResolver nameResolver, boolean z) {
        ConstantValueKind.UnsignedInt unsignedInt;
        ConstantValueKind.UnsignedByte unsignedByte;
        ConstantValueKind.UnsignedLong unsignedLong;
        float fFloatValue;
        ConstantValueKind.UnsignedShort unsignedShort;
        String string;
        double dDoubleValue;
        str.getClass();
        nameResolver.getClass();
        switch (str) {
            case "STRING":
                if (!str.equals("STRING")) {
                    return null;
                }
                ConstantValueKind.String string2 = ConstantValueKind.String.INSTANCE;
                if (value != null || (string = nameResolver.getString(value.getStringValue())) == null) {
                    obj.getClass();
                    string = (String) obj;
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, string2, string, null, true, null, 40, null);
            case "B":
                if (z) {
                    unsignedByte = ConstantValueKind.UnsignedByte.INSTANCE;
                } else {
                    unsignedByte = ConstantValueKind.Byte.INSTANCE;
                }
            case "C":
                if (!str.equals("C")) {
                    return null;
                }
                ConstantValueKind.Char r1 = ConstantValueKind.Char.INSTANCE;
                if (value != null) {
                    obj = Long.valueOf(value.getIntValue());
                }
                obj.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r1, Character.valueOf((char) ((Number) obj).intValue()), null, true, null, 40, null);
            case "D":
                if (!str.equals("D")) {
                    return null;
                }
                ConstantValueKind.Double r2 = ConstantValueKind.Double.INSTANCE;
                if (value != null) {
                    dDoubleValue = value.getDoubleValue();
                } else {
                    obj.getClass();
                    dDoubleValue = ((Double) obj).doubleValue();
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r2, Double.valueOf(dDoubleValue), null, true, null, 40, null);
            case "F":
                if (!str.equals("F")) {
                    return null;
                }
                ConstantValueKind.Float r3 = ConstantValueKind.Float.INSTANCE;
                if (value != null) {
                    fFloatValue = value.getFloatValue();
                } else {
                    obj.getClass();
                    fFloatValue = ((Float) obj).floatValue();
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r3, Float.valueOf(fFloatValue), null, true, null, 40, null);
            case "I":
                if (z) {
                    unsignedInt = ConstantValueKind.UnsignedInt.INSTANCE;
                } else {
                    unsignedInt = ConstantValueKind.Int.INSTANCE;
                }
            case "J":
                if (z) {
                    unsignedLong = ConstantValueKind.UnsignedLong.INSTANCE;
                } else {
                    unsignedLong = ConstantValueKind.Long.INSTANCE;
                }
            case "S":
                if (z) {
                    unsignedShort = ConstantValueKind.UnsignedShort.INSTANCE;
                } else {
                    unsignedShort = ConstantValueKind.Short.INSTANCE;
                }
            case "Z":
                if (!str.equals("Z")) {
                    return null;
                }
                ConstantValueKind.Boolean r4 = ConstantValueKind.Boolean.INSTANCE;
                if (value != null) {
                    obj = Integer.valueOf((int) value.getIntValue());
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r4, Boolean.valueOf(!Intrinsics.areEqual(obj, (Object) 0)), null, true, null, 40, null);
            case "INT":
                if (z) {
                    unsignedInt = ConstantValueKind.UnsignedInt.INSTANCE;
                } else {
                    unsignedInt = ConstantValueKind.Int.INSTANCE;
                }
            case "BYTE":
                if (z) {
                    unsignedByte = ConstantValueKind.UnsignedByte.INSTANCE;
                } else {
                    unsignedByte = ConstantValueKind.Byte.INSTANCE;
                }
            case "CHAR":
                if (!str.equals("C")) {
                    return null;
                }
                ConstantValueKind.Char r5 = ConstantValueKind.Char.INSTANCE;
                if (value != null) {
                    obj = Long.valueOf(value.getIntValue());
                }
                obj.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r5, Character.valueOf((char) ((Number) obj).intValue()), null, true, null, 40, null);
            case "LONG":
                if (z) {
                    unsignedLong = ConstantValueKind.UnsignedLong.INSTANCE;
                } else {
                    unsignedLong = ConstantValueKind.Long.INSTANCE;
                }
            case "FLOAT":
                if (!str.equals("F")) {
                    return null;
                }
                ConstantValueKind.Float r6 = ConstantValueKind.Float.INSTANCE;
                if (value != null) {
                    fFloatValue = value.getFloatValue();
                } else {
                    obj.getClass();
                    fFloatValue = ((Float) obj).floatValue();
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r6, Float.valueOf(fFloatValue), null, true, null, 40, null);
            case "SHORT":
                if (z) {
                    unsignedShort = ConstantValueKind.UnsignedShort.INSTANCE;
                } else {
                    unsignedShort = ConstantValueKind.Short.INSTANCE;
                }
            case "BOOLEAN":
                if (!str.equals("Z")) {
                    return null;
                }
                ConstantValueKind.Boolean r7 = ConstantValueKind.Boolean.INSTANCE;
                if (value != null) {
                    obj = Integer.valueOf((int) value.getIntValue());
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r7, Boolean.valueOf(!Intrinsics.areEqual(obj, (Object) 0)), null, true, null, 40, null);
            case "Ljava/lang/String;":
                if (!str.equals("STRING")) {
                    return null;
                }
                ConstantValueKind.String string3 = ConstantValueKind.String.INSTANCE;
                if (value != null || (string = nameResolver.getString(value.getStringValue())) == null) {
                    obj.getClass();
                    string = (String) obj;
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, string3, string, null, true, null, 40, null);
            case "DOUBLE":
                if (!str.equals("D")) {
                    return null;
                }
                ConstantValueKind.Double r8 = ConstantValueKind.Double.INSTANCE;
                if (value != null) {
                    dDoubleValue = value.getDoubleValue();
                } else {
                    obj.getClass();
                    dDoubleValue = ((Double) obj).doubleValue();
                }
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r8, Double.valueOf(dDoubleValue), null, true, null, 40, null);
            default:
                return null;
        }
    }

    public static final CallableId replaceName(CallableId callableId, Name name) {
        callableId.getClass();
        name.getClass();
        return new CallableId(callableId.getPackageName(), callableId.getClassName(), name);
    }
}

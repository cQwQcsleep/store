package androidx.window.embedding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.R;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ$\u0010\f\u001a\u00020\r*\u0012\u0012\u0004\u0012\u00020\u00060\u000ej\b\u0012\u0004\u0012\u00020\u0006`\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002¨\u0006%"}, d2 = {"Landroidx/window/embedding/RuleParser;", "", "<init>", "()V", "parseRules", "", "Landroidx/window/embedding/EmbeddingRule;", "context", "Landroid/content/Context;", "staticRuleResourceId", "", "parseRules$window_release", "addRuleWithDuplicatedTagCheck", "", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "rule", "parseSplitPairRule", "Landroidx/window/embedding/SplitPairRule;", "parser", "Landroid/content/res/XmlResourceParser;", "parseSplitPlaceholderRule", "Landroidx/window/embedding/SplitPlaceholderRule;", "parseSplitPairFilter", "Landroidx/window/embedding/SplitPairFilter;", "parseActivityRule", "Landroidx/window/embedding/ActivityRule;", "parseActivityFilter", "Landroidx/window/embedding/ActivityFilter;", "parseDividerAttributes", "Landroidx/window/embedding/DividerAttributes;", "buildClassName", "Landroid/content/ComponentName;", "pkg", "", "clsSeq", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RuleParser {
    public static final RuleParser INSTANCE = new RuleParser();

    private RuleParser() {
    }

    private final void addRuleWithDuplicatedTagCheck(HashSet<EmbeddingRule> hashSet, EmbeddingRule embeddingRule) {
        String tag = embeddingRule.getTag();
        for (EmbeddingRule embeddingRule2 : hashSet) {
            if (tag != null && Intrinsics.areEqual(tag, embeddingRule2.getTag())) {
                zba.a("Duplicated tag: ", tag, " for ", embeddingRule, ". The tag must be unique in XML rule definition.");
                return;
            }
        }
        hashSet.add(embeddingRule);
    }

    private final ComponentName buildClassName(String pkg, CharSequence clsSeq) {
        if (clsSeq == null || clsSeq.length() == 0) {
            w01.a("Activity name must not be null");
            return null;
        }
        String string = clsSeq.toString();
        if (string.charAt(0) == '.') {
            return new ComponentName(pkg, pkg + string);
        }
        int iIndexOf$default = StringsKt.indexOf$default(string, '/', 0, false, 6, (Object) null);
        if (iIndexOf$default > 0) {
            pkg = string.substring(0, iIndexOf$default);
            string = string.substring(iIndexOf$default + 1);
        }
        if (Intrinsics.areEqual(string, "*") || StringsKt.indexOf$default(string, '.', 0, false, 6, (Object) null) >= 0) {
            return new ComponentName(pkg, string);
        }
        return new ComponentName(pkg, pkg + '.' + string);
    }

    private final ActivityFilter parseActivityFilter(Context context, XmlResourceParser parser) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.ActivityFilter, 0, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.ActivityFilter_activityName);
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.ActivityFilter_activityAction);
        String packageName = context.getApplicationContext().getPackageName();
        packageName.getClass();
        return new ActivityFilter(buildClassName(packageName, string), string2);
    }

    private final ActivityRule parseActivityRule(Context context, XmlResourceParser parser) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.ActivityRule, 0, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.ActivityRule_tag);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ActivityRule_alwaysExpand, false);
        typedArrayObtainStyledAttributes.recycle();
        ActivityRule.Builder alwaysExpand = new ActivityRule.Builder(SetsKt.emptySet()).setAlwaysExpand(z);
        if (string != null) {
            alwaysExpand.setTag(string);
        }
        return alwaysExpand.build();
    }

    private final DividerAttributes parseDividerAttributes(Context context, XmlResourceParser parser) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.DividerAttributes, 0, 0);
        int i = typedArrayObtainStyledAttributes.getInt(R.styleable.DividerAttributes_embeddingDividerType, 0);
        DividerAttributes.Companion companion = DividerAttributes.INSTANCE;
        companion.validateXmlDividerAttributes$window_release(i, typedArrayObtainStyledAttributes.hasValue(R.styleable.DividerAttributes_dragRangeMinRatio), typedArrayObtainStyledAttributes.hasValue(R.styleable.DividerAttributes_dragRangeMaxRatio), typedArrayObtainStyledAttributes.hasValue(R.styleable.DividerAttributes_isDraggingToFullscreenAllowed));
        return companion.createDividerAttributes$window_release(i, typedArrayObtainStyledAttributes.getInt(R.styleable.DividerAttributes_embeddingDividerWidthDp, -1), typedArrayObtainStyledAttributes.getColor(R.styleable.DividerAttributes_embeddingDividerColor, -16777216), typedArrayObtainStyledAttributes.getFloat(R.styleable.DividerAttributes_dragRangeMinRatio, -1.0f), typedArrayObtainStyledAttributes.getFloat(R.styleable.DividerAttributes_dragRangeMaxRatio, -1.0f), typedArrayObtainStyledAttributes.getBoolean(R.styleable.DividerAttributes_isDraggingToFullscreenAllowed, false));
    }

    private final SplitPairFilter parseSplitPairFilter(Context context, XmlResourceParser parser) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPairFilter, 0, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.SplitPairFilter_primaryActivityName);
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityName);
        String string3 = typedArrayObtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityAction);
        String packageName = context.getApplicationContext().getPackageName();
        packageName.getClass();
        return new SplitPairFilter(buildClassName(packageName, string), buildClassName(packageName, string2), string3);
    }

    private final SplitPairRule parseSplitPairRule(Context context, XmlResourceParser parser) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPairRule, 0, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.SplitPairRule_tag);
        float f = typedArrayObtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitRatio, 0.5f);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinWidthDp, 600);
        int integer2 = typedArrayObtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinHeightDp, 600);
        int integer3 = typedArrayObtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinSmallestWidthDp, 600);
        float f2 = typedArrayObtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue());
        float f3 = typedArrayObtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue());
        int i = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPairRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue());
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishPrimaryWithSecondary, SplitRule.FinishBehavior.NEVER.getValue());
        int i3 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishSecondaryWithPrimary, SplitRule.FinishBehavior.ALWAYS.getValue());
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SplitPairRule_clearTop, false);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.SplitPairRule_animationBackgroundColor, 0);
        int i4 = R.styleable.SplitPairRule_splitOpenAnimation;
        EmbeddingAnimationParams.AnimationSpec animationSpec = EmbeddingAnimationParams.AnimationSpec.DEFAULT;
        int i5 = typedArrayObtainStyledAttributes.getInt(i4, animationSpec.getValue());
        int i6 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPairRule_splitCloseAnimation, animationSpec.getValue());
        int i7 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPairRule_splitChangeAnimation, animationSpec.getValue());
        typedArrayObtainStyledAttributes.recycle();
        EmbeddingAnimationParams.Builder animationBackground = new EmbeddingAnimationParams.Builder().setAnimationBackground(EmbeddingAnimationBackground.INSTANCE.buildFromValue$window_release(color));
        EmbeddingAnimationParams.AnimationSpec.Companion companion = EmbeddingAnimationParams.AnimationSpec.INSTANCE;
        SplitAttributes splitAttributesBuild = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.INSTANCE.getLayoutDirectionFromValue$window_release(i)).setAnimationParams(animationBackground.setOpenAnimation(companion.getAnimationSpecFromValue$window_release(i5)).setCloseAnimation(companion.getAnimationSpecFromValue$window_release(i6)).setChangeAnimation(companion.getAnimationSpecFromValue$window_release(i7)).build()).build();
        SplitPairRule.Builder minSmallestWidthDp = new SplitPairRule.Builder((Set<SplitPairFilter>) SetsKt.emptySet()).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3);
        EmbeddingAspectRatio.Companion companion2 = EmbeddingAspectRatio.INSTANCE;
        SplitPairRule.Builder maxAspectRatioInLandscape = minSmallestWidthDp.setMaxAspectRatioInPortrait(companion2.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(companion2.buildAspectRatioFromValue$window_release(f3));
        SplitRule.FinishBehavior.Companion companion3 = SplitRule.FinishBehavior.INSTANCE;
        return maxAspectRatioInLandscape.setFinishPrimaryWithSecondary(companion3.getFinishBehaviorFromValue$window_release(i2)).setFinishSecondaryWithPrimary(companion3.getFinishBehaviorFromValue$window_release(i3)).setClearTop(z).setDefaultSplitAttributes(splitAttributesBuild).build();
    }

    private final SplitPlaceholderRule parseSplitPlaceholderRule(Context context, XmlResourceParser parser) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPlaceholderRule, 0, 0);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_tag);
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_placeholderActivityName);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SplitPlaceholderRule_stickyPlaceholder, false);
        int i = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.ALWAYS.getValue());
        if (i == SplitRule.FinishBehavior.NEVER.getValue()) {
            w01.a("Never is not a valid configuration for Placeholder activities. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API");
            return null;
        }
        float f = typedArrayObtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitRatio, 0.5f);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinWidthDp, 600);
        int integer2 = typedArrayObtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinHeightDp, 600);
        int integer3 = typedArrayObtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinSmallestWidthDp, 600);
        float f2 = typedArrayObtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue());
        float f3 = typedArrayObtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue());
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue());
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.SplitPlaceholderRule_animationBackgroundColor, 0);
        int i3 = R.styleable.SplitPlaceholderRule_splitOpenAnimation;
        EmbeddingAnimationParams.AnimationSpec animationSpec = EmbeddingAnimationParams.AnimationSpec.DEFAULT;
        int i4 = typedArrayObtainStyledAttributes.getInt(i3, animationSpec.getValue());
        int i5 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_splitCloseAnimation, animationSpec.getValue());
        int i6 = typedArrayObtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_splitChangeAnimation, animationSpec.getValue());
        typedArrayObtainStyledAttributes.recycle();
        EmbeddingAnimationParams.Builder animationBackground = new EmbeddingAnimationParams.Builder().setAnimationBackground(EmbeddingAnimationBackground.INSTANCE.buildFromValue$window_release(color));
        EmbeddingAnimationParams.AnimationSpec.Companion companion = EmbeddingAnimationParams.AnimationSpec.INSTANCE;
        SplitAttributes splitAttributesBuild = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.INSTANCE.getLayoutDirectionFromValue$window_release(i2)).setAnimationParams(animationBackground.setOpenAnimation(companion.getAnimationSpecFromValue$window_release(i4)).setCloseAnimation(companion.getAnimationSpecFromValue$window_release(i5)).setChangeAnimation(companion.getAnimationSpecFromValue$window_release(i6)).build()).build();
        String packageName = context.getApplicationContext().getPackageName();
        RuleParser ruleParser = INSTANCE;
        packageName.getClass();
        ComponentName componentNameBuildClassName = ruleParser.buildClassName(packageName, string2);
        Set setEmptySet = SetsKt.emptySet();
        Intent component = new Intent().setComponent(componentNameBuildClassName);
        component.getClass();
        SplitPlaceholderRule.Builder minSmallestWidthDp = new SplitPlaceholderRule.Builder(setEmptySet, component).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3);
        EmbeddingAspectRatio.Companion companion2 = EmbeddingAspectRatio.INSTANCE;
        return minSmallestWidthDp.setMaxAspectRatioInPortrait(companion2.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(companion2.buildAspectRatioFromValue$window_release(f3)).setSticky(z).setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(i)).setDefaultSplitAttributes(splitAttributesBuild).build();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final Set<EmbeddingRule> parseRules$window_release(Context context, int staticRuleResourceId) throws XmlPullParserException, IOException {
        SplitPlaceholderRule splitPlaceholderRuleBuild;
        SplitPairRule splitPairRuleBuild;
        ActivityRule activityRulePlus$window_release;
        context.getClass();
        try {
            XmlResourceParser xml = context.getResources().getXml(staticRuleResourceId);
            xml.getClass();
            HashSet<EmbeddingRule> hashSet = new HashSet<>();
            int depth = xml.getDepth();
            int next = xml.next();
            SplitPairRule splitPairRule = null;
            ActivityRule activityRule = null;
            SplitPlaceholderRule splitPlaceholderRule = null;
            while (next != 1 && (next != 3 || xml.getDepth() > depth)) {
                if (xml.getEventType() != 2 || Intrinsics.areEqual("split-config", xml.getName())) {
                    next = xml.next();
                } else {
                    String name = xml.getName();
                    if (name != null) {
                        switch (name.hashCode()) {
                            case 304713008:
                                if (name.equals("DividerAttributes")) {
                                    if (splitPairRule == null && splitPlaceholderRule == null) {
                                        w01.a("Found orphaned DividerAttributes");
                                        return null;
                                    }
                                    DividerAttributes dividerAttributes = parseDividerAttributes(context, xml);
                                    if (splitPairRule != null) {
                                        hashSet.remove(splitPairRule);
                                        splitPairRuleBuild = new SplitPairRule.Builder(splitPairRule).setDefaultSplitAttributes(new SplitAttributes.Builder(splitPairRule.getDefaultSplitAttributes()).setDividerAttributes(dividerAttributes).build()).build();
                                        addRuleWithDuplicatedTagCheck(hashSet, splitPairRuleBuild);
                                        splitPairRule = splitPairRuleBuild;
                                    } else if (splitPlaceholderRule != null) {
                                        hashSet.remove(splitPlaceholderRule);
                                        splitPlaceholderRuleBuild = new SplitPlaceholderRule.Builder(splitPlaceholderRule).setDefaultSplitAttributes(new SplitAttributes.Builder(splitPlaceholderRule.getDefaultSplitAttributes()).setDividerAttributes(dividerAttributes).build()).build();
                                        addRuleWithDuplicatedTagCheck(hashSet, splitPlaceholderRuleBuild);
                                        splitPlaceholderRule = splitPlaceholderRuleBuild;
                                    }
                                }
                                break;
                            case 511422343:
                                if (name.equals("ActivityFilter")) {
                                    if (activityRule == null && splitPlaceholderRule == null) {
                                        w01.a("Found orphaned ActivityFilter");
                                        return null;
                                    }
                                    ActivityFilter activityFilter = parseActivityFilter(context, xml);
                                    if (activityRule != null) {
                                        hashSet.remove(activityRule);
                                        activityRulePlus$window_release = activityRule.plus$window_release(activityFilter);
                                        addRuleWithDuplicatedTagCheck(hashSet, activityRulePlus$window_release);
                                        activityRule = activityRulePlus$window_release;
                                    } else if (splitPlaceholderRule != null) {
                                        hashSet.remove(splitPlaceholderRule);
                                        splitPlaceholderRuleBuild = splitPlaceholderRule.plus$window_release(activityFilter);
                                        addRuleWithDuplicatedTagCheck(hashSet, splitPlaceholderRuleBuild);
                                        splitPlaceholderRule = splitPlaceholderRuleBuild;
                                    }
                                }
                                break;
                            case 520447504:
                                if (name.equals("SplitPairRule")) {
                                    splitPairRuleBuild = parseSplitPairRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, splitPairRuleBuild);
                                    activityRule = null;
                                    splitPlaceholderRule = null;
                                    splitPairRule = splitPairRuleBuild;
                                }
                                break;
                            case 1579230604:
                                if (name.equals("SplitPairFilter")) {
                                    if (splitPairRule == null) {
                                        w01.a("Found orphaned SplitPairFilter outside of SplitPairRule");
                                        return null;
                                    }
                                    SplitPairFilter splitPairFilter = parseSplitPairFilter(context, xml);
                                    hashSet.remove(splitPairRule);
                                    splitPairRuleBuild = splitPairRule.plus$window_release(splitPairFilter);
                                    addRuleWithDuplicatedTagCheck(hashSet, splitPairRuleBuild);
                                    splitPairRule = splitPairRuleBuild;
                                }
                                break;
                            case 1793077963:
                                if (name.equals("ActivityRule")) {
                                    activityRulePlus$window_release = parseActivityRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, activityRulePlus$window_release);
                                    splitPairRule = null;
                                    splitPlaceholderRule = null;
                                    activityRule = activityRulePlus$window_release;
                                }
                                break;
                            case 2050988213:
                                if (name.equals("SplitPlaceholderRule")) {
                                    splitPlaceholderRuleBuild = parseSplitPlaceholderRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, splitPlaceholderRuleBuild);
                                    splitPairRule = null;
                                    activityRule = null;
                                    splitPlaceholderRule = splitPlaceholderRuleBuild;
                                }
                                break;
                        }
                    }
                    next = xml.next();
                }
            }
            return hashSet;
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }
}

package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicSecureTextFieldKt;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SecureTextFieldKt$OutlinedSecureTextField$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ TextFieldColors $colors;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ InputTransformation $inputTransformation;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ boolean $isError;
    final /* synthetic */ KeyboardOptions $keyboardOptions;
    final /* synthetic */ Function3<TextFieldLabelScope, Composer, Integer, Unit> $label;
    final /* synthetic */ TextFieldLabelPosition $labelPosition;
    final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
    final /* synthetic */ TextStyle $mergedTextStyle;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ KeyboardActionHandler $onKeyboardAction;
    final /* synthetic */ Function2<Density, Function0<TextLayoutResult>, Unit> $onTextLayout;
    final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
    final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
    final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
    final /* synthetic */ char $textObfuscationCharacter;
    final /* synthetic */ int $textObfuscationMode;
    final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

    /* JADX WARN: Multi-variable type inference failed */
    public SecureTextFieldKt$OutlinedSecureTextField$1(Modifier modifier, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, TextFieldLabelPosition textFieldLabelPosition, boolean z, TextFieldColors textFieldColors, TextFieldState textFieldState, boolean z2, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, PaddingValues paddingValues, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function9, int i, char c, Shape shape) {
        this.$modifier = modifier;
        this.$label = function3;
        this.$labelPosition = textFieldLabelPosition;
        this.$isError = z;
        this.$colors = textFieldColors;
        this.$state = textFieldState;
        this.$enabled = z2;
        this.$interactionSource = mutableInteractionSource;
        this.$placeholder = function2;
        this.$leadingIcon = function4;
        this.$trailingIcon = function5;
        this.$prefix = function6;
        this.$suffix = function7;
        this.$supportingText = function8;
        this.$contentPadding = paddingValues;
        this.$inputTransformation = inputTransformation;
        this.$mergedTextStyle = textStyle;
        this.$keyboardOptions = keyboardOptions;
        this.$onKeyboardAction = keyboardActionHandler;
        this.$onTextLayout = function9;
        this.$textObfuscationMode = i;
        this.$textObfuscationCharacter = c;
        this.$shape = shape;
    }

    public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        Modifier modifier;
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1147500080, i, -1, "androidx.compose.material3.OutlinedSecureTextField.<anonymous> (SecureTextField.kt:327)");
        }
        Modifier modifier2 = this.$modifier;
        if (this.$label == null || (this.$labelPosition instanceof TextFieldLabelPosition.Above)) {
            composer.startReplaceGroup(1530795410);
            composer.endReplaceGroup();
            modifier = Modifier.INSTANCE;
        } else {
            composer.startReplaceGroup(1530411723);
            Modifier.Companion companion = Modifier.INSTANCE;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.u3
                    public final Object invoke(Object obj) {
                        return SecureTextFieldKt$OutlinedSecureTextField$1.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            modifier = PaddingKt.padding-qDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) objRememberedValue), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight(composer, 0), 0.0f, 0.0f, 13, (Object) null);
            composer.endReplaceGroup();
        }
        Modifier modifierThen = modifier2.then(modifier);
        boolean z = this.$isError;
        Strings.Companion companion2 = Strings.INSTANCE;
        Modifier modifierDefaultErrorSemantics = TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(androidx.compose.ui.R.string.default_error_message), composer, 0));
        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
        Modifier modifier3 = SizeKt.defaultMinSize-VpY3zN4(modifierDefaultErrorSemantics, outlinedTextFieldDefaults.m732getMinWidthD9Ej5fM(), outlinedTextFieldDefaults.m731getMinHeightD9Ej5fM());
        SolidColor solidColor = new SolidColor(this.$colors.m1014cursorColorvNxB06k$material3(this.$isError), null);
        TextFieldState textFieldState = this.$state;
        final boolean z2 = this.$enabled;
        TextFieldLineLimits.SingleLine singleLine = TextFieldLineLimits.SingleLine.INSTANCE;
        final MutableInteractionSource mutableInteractionSource = this.$interactionSource;
        TextFieldLabelPosition textFieldLabelPosition = this.$labelPosition;
        Function3<TextFieldLabelScope, Composer, Integer, Unit> function3 = this.$label;
        Function2<Composer, Integer, Unit> function2 = this.$placeholder;
        Function2<Composer, Integer, Unit> function4 = this.$leadingIcon;
        Function2<Composer, Integer, Unit> function5 = this.$trailingIcon;
        Function2<Composer, Integer, Unit> function6 = this.$prefix;
        Function2<Composer, Integer, Unit> function7 = this.$suffix;
        Function2<Composer, Integer, Unit> function8 = this.$supportingText;
        final boolean z3 = this.$isError;
        final TextFieldColors textFieldColors = this.$colors;
        PaddingValues paddingValues = this.$contentPadding;
        final Shape shape = this.$shape;
        BasicSecureTextFieldKt.BasicSecureTextField-egD4TGM(this.$state, modifier3, this.$enabled, false, this.$inputTransformation, this.$mergedTextStyle, this.$keyboardOptions, this.$onKeyboardAction, this.$onTextLayout, this.$interactionSource, solidColor, outlinedTextFieldDefaults.decorator(textFieldState, z2, singleLine, null, mutableInteractionSource, textFieldLabelPosition, function3, function2, function4, function5, function6, function7, function8, z3, textFieldColors, paddingValues, ComposableLambdaKt.rememberComposableLambda(-1406782897, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SecureTextFieldKt$OutlinedSecureTextField$1.2
            public final void invoke(Composer composer2, int i2) {
                if (!composer2.shouldExecute((i2 & 3) != 2, i2 & 1)) {
                    composer2.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1406782897, i2, -1, "androidx.compose.material3.OutlinedSecureTextField.<anonymous>.<anonymous> (SecureTextField.kt:376)");
                }
                OutlinedTextFieldDefaults.INSTANCE.m726Container4EFweAY(z2, z3, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, composer2, 100663296, ComposerKt.invocationKey);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }
        }, composer, 54), composer, 3456, 14155776, 0), this.$textObfuscationMode, this.$textObfuscationCharacter, composer, 0, 0, 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}

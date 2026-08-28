package com.vivo.shell;

import Ark.VMProtect;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class AboutAuthorActivity extends Activity {
    static {
        VMProtect.ArkSafeVM(1055330058);
    }

    public void Back(View view) {
        finish();
    }

    public void ContactAuthor(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mqq://card/show_pslcard?src_type=internal&uin=3295445516"));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void ContactQQ(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mqq://card/show_pslcard?src_type=internal&uin=3150530137"));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void OnNewCardClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=1063959709&card_type=group"));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    public void reward(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        SpannableString spannableString = new SpannableString("赞赏支持");
        spannableString.setSpan(new AbsoluteSizeSpan((int) (24.0f * getResources().getDisplayMetrics().scaledDensity)), 0, spannableString.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(2130771970)), 0, spannableString.length(), 33);
        AlertDialog.Builder title = builder.setTitle(spannableString);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        int i = (int) (12.0f * getResources().getDisplayMetrics().density);
        linearLayout.setPadding(i, i, i, i);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(2130837520);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (320.0f * getResources().getDisplayMetrics().density), (int) (320.0f * getResources().getDisplayMetrics().density));
        layoutParams.gravity = 17;
        linearLayout.addView(imageView, layoutParams);
        AlertDialog alertDialogCreate = title.setView(linearLayout).setNegativeButton("取消", (DialogInterface.OnClickListener) null).setCancelable(false).create();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getResources().getColor(2130771969));
        gradientDrawable.setCornerRadius(30.0f * getResources().getDisplayMetrics().density);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(gradientDrawable);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85f);
            window.setAttributes(attributes);
        }
        Button button = alertDialogCreate.getButton(-2);
        if (button != null) {
            button.setTextColor(-16744449);
        }
    }
}

package net.macdidi.myinteractive;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorActivity extends Activity
{
    private LinearLayout color_gallery;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        processViews();
        ColorListener listener = new ColorListener();

        for (Colors c : Colors.values())
        {
            Button button = new Button(this);
            button.setId(c.parseColor());
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(128, 128);
            layout.setMargins(6, 6, 6, 6);
            button.setLayoutParams(layout);
            button.setBackgroundColor(c.parseColor());
            button.setOnClickListener(listener);
            color_gallery.addView(button);
        }
    }

    private void processViews()
    {
        color_gallery = (LinearLayout) findViewById(R.id.color_gallery);
    }

    private class ColorListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            String action = ColorActivity.this.getIntent().getAction();

            if (action != null && action.equals("net.macdidi.myinteractive.CHOOSE_COLOR"))
            {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ColorActivity.this).edit();
                editor.putInt("DEFAULT_COLOR", v.getId());
                editor.commit();
                finish();
            }
            else
            {
                Intent result = getIntent();
                result.putExtra("colorId", v.getId());
                setResult(Activity.RESULT_OK, result);
                finish();
            }

        }
    }
}

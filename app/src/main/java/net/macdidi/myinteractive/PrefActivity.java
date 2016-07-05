package net.macdidi.myinteractive;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by Owen on 07/05/2016.
 */
public class PrefActivity extends PreferenceActivity
{
    private SharedPreferences _sharedPreferences;
    private Preference _defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreference);
        _defaultColor = findPreference("DEFAULT_COLOR");
        _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        int color = _sharedPreferences.getInt("DEFAULT_COLOR",-1);
        if (color != -1)
        {
            _defaultColor.setSummary(getString(R.string.default_color_summary)+": " + ItemActivity.getColors(color));
        }
    }
}

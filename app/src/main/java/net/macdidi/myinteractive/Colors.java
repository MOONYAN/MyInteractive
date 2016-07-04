package net.macdidi.myinteractive;

import android.graphics.Color;

/**
 * Created by Owen on 07/02/2016.
 */
public enum Colors
{
    LIGHTGREY("#D3D3D3"),
    BLUE("#33B5E5"),
    PURPLE("#AA66CC"),
    GREEN("#99CC00"),
    ORANGE("#FFBB33"),
    RED("#FF4444");

    String _code;

    Colors(String code)
    {
        _code = code;
    }

    public String getCode()
    {
        return _code;
    }

    public int parseColor() {
        return Color.parseColor(_code);
    }
}

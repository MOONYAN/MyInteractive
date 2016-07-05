package net.macdidi.myinteractive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class ItemActivity extends AppCompatActivity
{
    private static final int START_CAMERA = 0;
    private static final int START_RECORD = 1;
    private static final int START_LOCATION = 2;
    private static final int START_ALARM = 3;
    private static final int START_COLOR = 4;
    private Item item;

    EditText title_text;
    EditText content_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
//        System.out.println("Sys out ItemActivity in");
//        Log.d("in", "onCreate: success");
        Log.d("----------", "onCreate: ");
        processViews();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("net.macdidi.myinteractive.EDIT_ITEM"))
        {
            item = (Item) intent.getExtras().getSerializable("net.macdidi.myinteractive.Item");

//            String titleText = intent.getStringExtra("titleText");
//            title_text.setText(titleText);
            title_text.setText(item.getTitle());
            content_text.setText(item.getContent());
        }
        else
        {
            item = new Item();
        }
    }

    private void processViews()
    {
        title_text = (EditText) findViewById(R.id.title_text);
        content_text = (EditText) findViewById(R.id.content_text);
    }

    public void clickFunction(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.take_picture:
                break;
            case R.id.record_sound:
                break;
            case R.id.set_location:
                break;
            case R.id.set_alarm:
                break;
            case R.id.select_color:
                startActivityForResult(new Intent(this, ColorActivity.class), START_COLOR);
                break;
        }
    }

    public void onSubmit(View view)
    {
        if (view.getId() == R.id.ok_item)
        {

            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();

            item.setTitle(titleText);
            item.setContent(contentText);

            if (getIntent().getAction().equals("net.macdidi.myinteractive.EDIT_ITEM"))
            {
                item.setLastModify(new Date().getTime());
            }
            else
            {
                item.setDatetime(new Date().getTime());
            }
            Intent result = getIntent();
            result.putExtra("net.macdidi.myinteractive.Item", item);
//            result.putExtra("titleText", titleText);
//            result.putExtra("contentText", contentText);
            setResult(Activity.RESULT_OK, result);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case START_CAMERA:
                    break;
                case START_RECORD:
                    break;
                case START_LOCATION:
                    break;
                case START_ALARM:
                    break;
                case START_COLOR:
                    int colorId = data.getIntExtra("colorId", Colors.LIGHTGREY.parseColor());
                    item.setColor(getColors(colorId));
                    break;
            }
        }
    }

    public static Colors getColors(int colorId)
    {
        Colors result = Colors.LIGHTGREY;

        if (colorId == Colors.BLUE.parseColor())
        {
            result = Colors.BLUE;
        }
        else if (colorId == Colors.PURPLE.parseColor())
        {
            result = Colors.PURPLE;
        }
        else if (colorId == Colors.GREEN.parseColor())
        {
            result = Colors.GREEN;
        }
        else if (colorId == Colors.ORANGE.parseColor())
        {
            result = Colors.ORANGE;
        }
        else if (colorId == Colors.RED.parseColor())
        {
            result = Colors.RED;
        }

        return result;
    }
}

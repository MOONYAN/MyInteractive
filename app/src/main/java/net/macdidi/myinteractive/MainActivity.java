package net.macdidi.myinteractive;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView _listView;
    private TextView _textView;

//    ArrayList<String> _data = new ArrayList<>();
//        private ArrayAdapter<String> _adapter;
//        static final String[] _data =
//            {
//                    "關於Android Tutorial的事情",
//                    "一隻非常可愛的小狗狗!",
//                    "一首非常好聽的音樂！"
//            };

    private ItemAdapter _itemAdapter;
    private List<Item> _items;
    private MenuItem _addItem, _searchItem, _revertItem, _deleteItem;
    private int _selectCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processViews();
        processControllers();

//        _data.add("關於Android Tutorial的事情");
//        _data.add("一隻非常可愛的小狗狗!");
//        _data.add("一首非常好聽的音樂！");
//
//        int layoutId = android.R.layout.simple_list_item_1;
//        _adapter = new ArrayAdapter<String>(this, layoutId, _data);
//        _listView.setAdapter(_adapter);

        _items = new ArrayList<>();
        _items.add(new Item(1, new Date().getTime(), Colors.RED, "關於Android Tutorial的事情.", "Hello content", "", "", 0, 0, 0));
        _items.add(new Item(2, new Date().getTime(), Colors.BLUE, "一隻非常可愛的小狗狗!", "她的名字叫「大熱狗」，又叫\n作「奶嘴」，是一隻非常可愛\n的小狗。", "", "", 0, 0, 0));
        _items.add(new Item(3, new Date().getTime(), Colors.GREEN, "一首非常好聽的音樂！", "Hello content", "", "", 0, 0, 0));

        _itemAdapter = new ItemAdapter(this, R.layout.single_item, _items);
        _listView.setAdapter(_itemAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        _addItem = menu.findItem(R.id.add_item);
        _searchItem = menu.findItem(R.id.search_item);
        _revertItem = menu.findItem(R.id.revert_item);
        _deleteItem = menu.findItem(R.id.delete_item);
        ProcessMenu(null);

        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    private void processViews()
    {
        _listView = (ListView) findViewById(R.id.listView);
        _textView = (TextView) findViewById(R.id.show_app_name);
    }

    private void processControllers()
    {
        ProcessSetOnItemClick();
        ProcessSetOnItemLongClick();
//        ProcessSetOnTouch();
//        ProcessSetOnKey();
//        ProcessSetOnLongClick();
//        ProcessSetOnClick();
    }

    private void ProcessSetOnItemLongClick()
    {
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
//                Toast.makeText(MainActivity.this, (CharSequence) _items.get(position), Toast.LENGTH_SHORT).show();
                Item item = _itemAdapter.get(position);
                ProcessMenu(item);
                _itemAdapter.set(position, item);
                return true;
            }
        };
        _listView.setOnItemLongClickListener(listener);
    }

    private void ProcessSetOnItemClick()
    {
//        final String[] data = {
//                "關於Android Tutorial的事情",
//                "一隻非常可愛的小狗狗!",
//                "一首非常好聽的音樂！"
//        };
//        int layoutId = android.R.layout.simple_list_item_1;
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layoutId, data);
//        ListView listView = (ListView) findViewById(R.id.listView);
//        _listView.setAdapter(_adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Item item = _itemAdapter.getItem(position);

                if (_selectCount > 0)
                {
                    ProcessMenu(item);
                    _itemAdapter.set(position, item);
                }
                else
                {
                    Intent intent = new Intent("net.macdidi.myinteractive.EDIT_ITEM");

                    intent.putExtra("position", position);
                    intent.putExtra("net.macdidi.myinteractive.Item", item);
//                intent.putExtra("titleText", (CharSequence) _items.get(position));
                    startActivityForResult(intent, 1);
//                Toast.makeText(MainActivity.this, _data.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        };
        _listView.setOnItemClickListener(listener);
    }

    private void ProcessMenu(Item item)
    {
        if (item != null)
        {
            item.setSelected(!item.isSelected());
            if (item.isSelected())
            {
                _selectCount++;
            }
            else
            {
                _selectCount--;
            }
        }
        _addItem.setVisible(_selectCount == 0);
        _searchItem.setVisible(_selectCount == 0);
        _revertItem.setVisible(_selectCount > 0);
        _deleteItem.setVisible(_selectCount > 0);
    }

    private void ProcessSetOnTouch()
    {
//        TextView textView = (TextView) findViewById(R.id.show_app_name);
        View.OnTouchListener listener = new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name)
                        .setMessage(event.toString())
                        .show();
                return false;
            }
        };
        _textView.setOnTouchListener(listener);
    }

    private void ProcessSetOnKey()
    {
//        TextView textView = (TextView) findViewById(R.id.show_app_name);
        View.OnKeyListener listener = new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name)
                        .setMessage(event.toString())
                        .show();
                return false;
            }
        };
        _textView.setOnKeyListener(listener);
    }

    private void ProcessSetOnLongClick()
    {
//        TextView textView = (TextView) findViewById(R.id.show_app_name);
        View.OnLongClickListener listener = new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name)
                        .setMessage(R.string.about)
                        .show();
                return false;
            }
        };
        _textView.setOnLongClickListener(listener);
    }

    private void ProcessSetOnClick()
    {
//        TextView textView = (TextView) findViewById(R.id.show_app_name);
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name)
                        .setMessage(R.string.about)
                        .show();
            }
        };
        _textView.setOnClickListener(listener);
    }

    public void AboutApp(View view)
    {
//        Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void clickMenuItem(final MenuItem item)
    {
        int itemId = item.getItemId();

        switch (itemId)
        {
            case R.id.search_item:
                break;
            case R.id.add_item:
//                Intent intent = new Intent(this, ItemActivity.class);
                Intent intent = new Intent("net.macdidi.myinteractive.ADD_ITEM");
                startActivityForResult(intent, 0);
                break;
            case R.id.revert_item:
                for (int i = 0; i < _itemAdapter.getCount(); i++)
                {
                    Item revertItem = _itemAdapter.getItem(i);
                    if (revertItem.isSelected())
                    {
                        revertItem.setSelected(false);
                        _itemAdapter.set(i,revertItem);
                    }
                }
                _selectCount = 0;
                ProcessMenu(null);
                break;
            case R.id.delete_item:
                if(_selectCount == 0)
                {
                    break;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String message = getString(R.string.delete_item);
                builder.setTitle(R.string.delete).setMessage(String.format(message,_selectCount));
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        int index = _itemAdapter.getCount()-1;
                        while (index > -1)
                        {
                            Item deleteItem = _itemAdapter.get(index);
                            if (deleteItem.isSelected())
                            {
                                _itemAdapter.remove(deleteItem);
                            }
                            index--;
                        }
                        _itemAdapter.notifyDataSetChanged();
                        _selectCount = 0;
                        ProcessMenu(null);
                    }
                });
                builder.setNegativeButton(android.R.string.no,null);
                builder.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            Item item = (Item) data.getExtras().getSerializable("net.macdidi.myinteractive.Item");
//            String titleText = data.getStringExtra("titleText");

            if (requestCode == 0)
            {
                item.setId(_items.size() + 1);
                item.setDatetime(new Date().getTime());
                _items.add(item);
                _itemAdapter.notifyDataSetChanged();
//                _data.add(titleText);
//                _adapter.notifyDataSetChanged();
            }
            else if (requestCode == 1)
            {
                int position = data.getIntExtra("position", -1);
                if (position != -1)
                {
                    _items.set(position, item);
                    _itemAdapter.notifyDataSetChanged();
//                    _data.set(position, titleText);
//                    _adapter.notifyDataSetChanged();
                }
            }
        }
//        super.onActivityResult(requestCode, resultCode, data);
    }
}

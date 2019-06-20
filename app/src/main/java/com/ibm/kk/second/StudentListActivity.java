package com.ibm.kk.second;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends ListActivity implements AdapterView.OnClickListener,AdapterView.OnItemLongClickListener {

    private Button addStudent;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private List<Long> list;
    private Button searchButton;
    private Button deleteButton;
    private SCManager dao;
    private SCItem sc;
    private Boolean isDeleteList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = new ArrayList<Long>();
        sc = new SCItem();
        dao = new SCManager(this);
        deleteButton = (Button) findViewById(R.id.bn_delete);
        addStudent = (Button) findViewById(R.id.btn_add_student);
        searchButton = (Button) findViewById(R.id.bn_search_id);
        listView = getListView();
        addStudent.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnCreateContextMenuListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    public  void onClick(View v) {

        if (v == addStudent) {
            startActivity(new Intent(StudentListActivity.this, AddStudentActivity.class));
        } else if (v == searchButton) {
            startActivity(new Intent(this, Search.class));
        } else if (v == deleteButton) {
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    long id = list.get(i);
                    int count = dao.delete(id);
                }
                load();
            }
        }
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this); //getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        sc = (SCItem) listView.getTag();
        final long sc_id = sc.getId();
        Intent intent = new Intent();
        switch (item_id) {
            case R.id.delete:
                deleteSCInformation(sc_id);
                break;
            case R.id.look:
                intent.putExtra("student", sc);
                intent.setClass(this, ShowActivity.class);
                this.startActivity(intent);
                break;
            case R.id.write:
                intent.putExtra("student", sc);
                intent.setClass(this, AddStudentActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        SCItem student = (SCItem) dao.getStudentFromView(view, id);
        listView.setTag(student);
        registerForContextMenu(listView);
        return false;
    }

    public void load() {
        SCDBHelper scDBHelper = new SCDBHelper(
                StudentListActivity.this);
        SQLiteDatabase database = scDBHelper.getWritableDatabase();
        cursor = database.query(Table.STUDENT_TABLE, null, null, null,
                null, null, Table.StudentColumns.MODIFY_TIME + " desc");
        startManagingCursor(cursor);
        adapter = new SimpleCursorAdapter(this, R.layout.sc_list_item,
                cursor, new String[] { Table.StudentColumns.ID,
                Table.StudentColumns.NAME,
                Table.StudentColumns.NUM,
                Table.StudentColumns.PERIOD,
                Table.StudentColumns.GRADE,
                Table.StudentColumns.TYPE,
                Table.StudentColumns.PLACE,
                Table.StudentColumns.TRAIN_DATE }, new int[] {
                R.id.tv_pro_id, R.id.tv_pro_name, R.id.tv_pro_num,R.id.tv_pro_period,
                R.id.tv_pro_grade, R.id.tv_pro_type, R.id.tv_pro_place,
                R.id.tv_pro_traindate });
        listView.setAdapter(adapter);
    }

    private void deleteSCInformation(final long delete_id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("信息删除")
                .setMessage("确定删除所选记录?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int raws = dao.delete(delete_id);
                        deleteButton.setVisibility(View.GONE);
                        isDeleteList = !isDeleteList;
                        load();
                        if (raws > 0) {
                            Toast.makeText(StudentListActivity.this, "删除成功!",
                                    Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(StudentListActivity.this, "删除失败!",
                                    Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}

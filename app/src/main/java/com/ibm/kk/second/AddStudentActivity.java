package com.ibm.kk.second;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudentActivity extends AppCompatActivity implements AdapterView.OnClickListener{
    private static final String TAG = "AddStudentActivity";
    private final static int DATE_DIALOG = 1;
    private static final int DATE_PICKER_ID = 1;
    private TextView idText;
    private EditText nameText;
    private EditText periodText;
    private EditText numText;
    private EditText placeText;
    private EditText dataText;
    private RadioGroup group;
    private RadioButton button1;
    private RadioButton button2;
    private CheckBox box1;
    private CheckBox box2;
    private CheckBox box3;
    private CheckBox box4;
    private Button restoreButton;
    private String sex;
    private Button resetButton;
    private Long student_id;
    private StudentDao dao;
    private boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        idText = (TextView) findViewById(R.id.tv_pro_id);
        nameText = (EditText) findViewById(R.id.et_name);
        numText = (EditText) findViewById(R.id.et_num);
        periodText = (EditText) findViewById(R.id.et_period);
        button1 = (RadioButton) findViewById(R.id.rb_grade1);
        button2 = (RadioButton) findViewById(R.id.rb_grade2);
        placeText = (EditText) findViewById(R.id.et_place);
        dataText = (EditText) findViewById(R.id.et_traindate);
        group = (RadioGroup) findViewById(R.id.rg_grade);
        box1 = (CheckBox) findViewById(R.id.box1);
        box2 = (CheckBox) findViewById(R.id.box2);
        box3 = (CheckBox) findViewById(R.id.box3);
        box4 = (CheckBox) findViewById(R.id.box4);
        restoreButton = (Button) findViewById(R.id.btn_save);
        resetButton = (Button) findViewById(R.id.btn_clear);
        dao = new StudentDao(new StudentDBHelper(this)); // 设置监听
        restoreButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        dataText.setOnClickListener(this);
        checkIsAddStudent();
        }
    // 检查此时Activity是否用于添加项目信息
    private void checkIsAddStudent() {
        Intent intent = getIntent();
        Serializable serial = intent.getSerializableExtra(TableContanst.STUDENT_TABLE);
        if (serial == null) {
            isAdd = true;
            dataText.setText(getCurrentDate());
        } else {
            isAdd = false;
            Student s = (Student) serial;
            showEditUI(s);
        }
    }
    //显示项目信息更新
    private void showEditUI(Student student) {
        // 先将Student携带的数据还原到student的每一个属性中去
        student_id = student.getId();
        String name = student.getName();
        String num = student.getNum();
        String period = student.getPeriod();
        String place = student.getPlace();
        String data = student.getTrainDate();
        String type = student.getType();
        String grade = student.getGrade();
        if (grade.toString().equals("校级以上")) {
            button2.setChecked(true);
        } else if (grade.toString().equals("校级以下")) {
            button1.setChecked(true);
        }
        if (type != null && !"".equals(type)) {
            if (box1.getText().toString().indexOf(type) >= 0) {
                box1.setChecked(true);
            }
            if (box2.getText().toString().indexOf(type) >= 0) {
                box2.setChecked(true);
            }
            if (box3.getText().toString().indexOf(type) >= 0) {
                box3.setChecked(true);
            }
            if (box4.getText().toString().indexOf(type) >= 0) {
                box4.setChecked(true);
            }
        }
        // 还原数据
        idText.setText(student_id + "");
        nameText.setText(name + "");
        numText.setText(num + "");
        periodText.setText(period + "");
        placeText.setText(place + "");
        dataText.setText(data + "");
        setTitle("学员信息更新");
        restoreButton.setText("更新");
    }
    public void onClick(View v) {
        // 收集数据
        if (v == restoreButton) {
            if (!checkUIInput()) {// 界面输入验证
                return;
            }
            Student student = getStudentFromUI();
            if (isAdd) {
                long id = dao.addStudent(student);
                dao.closeDB();
                if (id > 0) {
                    Toast.makeText(this, "保存成功， ID=" + id,Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "保存失败，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            } else if (!isAdd) {
                long id = dao.addStudent(student);
                dao.closeDB();
                if (id > 0) {
                    Toast.makeText(this, "更新成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "更新失败，请重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v == resetButton) {
            clearUIData();
        } else if (v == dataText) {
            showDialog(DATE_PICKER_ID);
        }
    }
    //       清空界面的数据
    private void clearUIData() {
        nameText.setText("");
        numText.setText("");
        periodText.setText("");
        placeText.setText("");
        dataText.setText("");
        box1.setChecked(false);
        box2.setChecked(false);
        group.clearCheck();
    }
    //      收集界面输入的数据，并将封装成Student对象
    private Student getStudentFromUI() {
        String name = nameText.getText().toString();
        String num = numText.getText().toString();
        String grade = ((RadioButton) findViewById(group
                .getCheckedRadioButtonId())).getText().toString();
        String type = "";
        if (box1.isChecked()) {
            type += box1.getText();
        }
        if (box2.isChecked()) {
            if (type.equals("")) {
                type += box2.getText();
            } else {
                type += "," + box2.getText();
            }
            if (type.equals("")) {
                type += box3.getText();
            } else {
                type += "," + box3.getText();
            }
            if (type.equals("")) {
                type += box4.getText();
            } else {
                type += "," + box4.getText();
            }
        }
        String trainDate = dataText.getText().toString();
        String period = periodText.getText().toString();
        String place = placeText.getText().toString();
        String modifyDateTime = getCurrentDateTime();
        Student s=new Student(name, num, period, grade, type, place, trainDate, modifyDateTime);
        if (!isAdd) {
            s.setId(Integer.parseInt(idText.getText().toString()));
            dao.deleteStudentById(student_id);
        }
        return s;
    }
          //* 得到当前的日期时间
    private String getCurrentDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
    //      * 得到当前的日期
    private String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
    //验证用户是否按要求输入了数据
    private boolean checkUIInput() { // name, age, sex
        String name = nameText.getText().toString();
        String num = numText.getText().toString();
        String period = periodText.getText().toString();
        int id = group.getCheckedRadioButtonId();
        String message = null;
        View invadView = null;
        if (name.trim().length() == 0) {
            message = "请输入姓名！";
            invadView = nameText;
        }  else if (period.trim().length() == 0) {
            message = "请输入学时！";
            invadView = periodText;
        } else if (num.trim().length() == 0) {
            message = "请输入次数！";
            invadView = numText;
        } else if (id == -1) {
            message = "请选择等级！";
        }
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (invadView != null)
                invadView.requestFocus();
            return false;
        }         return true;     }
    //时间的监听与事件
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dataText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, onDateSetListener, 2011, 8, 14);
        }
        return null;
    }
}
package com.shu.cocoinapp.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.shu.cocoinapp.R;
import com.shu.cocoinapp.Model.Record;

import java.util.Calendar;
import java.util.Locale;

import com.shu.cocoinapp.Utils.TimeUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private Button btnDate;
    private TextView tvYear;
    private TextView tvMonth;
    private ListView lv;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        defaultDateSet();
//        btnDate = findViewById(R.id.btnSelectDate);
//        btnDate.setOnClickListener(this);
    }

    // 多个按钮共用一个onClick()方法，通过按钮的id区分单击了哪个按钮
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutLeft:
                showDatePickerDialog(this, 2, tvYear, tvMonth);
            case R.id.btn_add:
                Record[] records = getRecords();
                String record_info1 = records[0].kind+" 类别:"+records[0].category+
                        " 金额:"+records[0].money+"\n描述:"+records[0].description;
                String record_info2 = records[1].kind+" 类别:"+records[1].category+
                        " 金额:"+records[1].money+"\n描述:"+records[1].description;
                String[] records_info = {record_info1, record_info2};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, records_info);
                lv = findViewById(R.id.lv_records);
                lv.setAdapter(adapter);
        }
//        if (v.getId() == R.id.btnSelectDate) {
//            Calendar calendar = Calendar.getInstance();
//            DatePickerDialog dialog = new DatePickerDialog(this, this,
//                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH));
//            dialog.show();
//        }
        if (v.getId() == R.id.layoutLeft) {
            showDatePickerDialog(this, 2, tvYear, tvMonth);
        }
    }

    /*
    这里只想显示年月的选择，不需要day，尝试隐藏。
    当前设置的年月数据需要记录，来显示对应的记账数据。
    */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tvY,
                                            final TextView tvM) {
        DatePickerDialog dialog =
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvY.setText(year + "年");
                String m = String.format("%02d", month+1);
                tvM.setText(m + "月");
            }
        },
                // java的切片语法：str.substring(start, end);
                Integer.parseInt(tvY.getText().toString().substring(0, 4)),
                Integer.parseInt(tvM.getText().toString().substring(0, 2))-1,
                1);
                dialog.show();
                dialog.setTitle(R.string.dateDialogTitle);
                DatePicker dp = dialog.getDatePicker();
        // 隐藏掉日期，只保留年和月
                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0))
                .getChildAt(1).setVisibility(View.GONE);
    }

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        String date = String.format("%d年%d月", year, monthOfYear+1);
//        btnDate.setText(date);
    }

    // 当用户还没有设置时间时，defaultDateSet方法为显示年月的控件初始化了值
    public void defaultDateSet() {
        String[] dateArray = TimeUtils.getCurYMD();
        tvYear.setText(String.format(getString(R.string.year), dateArray[0]));
        tvMonth.setText(String.format(getString(R.string.month), dateArray[1]));
    }

    public Record[] getRecords() {
        Record record1 = new Record("支出", "娱乐",
                                    10.5, "唱卡拉OK",
                                     calendar.get(Calendar.YEAR),
                                     calendar.get(Calendar.MONTH),
                                     calendar.get(Calendar.DAY_OF_MONTH));
        Record record2 = new Record("收入", "工资",
                120.3, "出去打工",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        return new Record[] {record1, record2};
    }
}

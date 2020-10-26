package com.example.caldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etInput;
    TextView tvView;
    Button btnC;
    Button btnResetAll;
    Button btnChia;
    Button btnNhan;
    Button btnCong;
    Button btnTru;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn0;
    Button btnDauCham;
    Button btnDauBang;

    private ArrayList<String> arrOperation;// Mảng chứa các phép tính + - * /
    private ArrayList<Double> arrNumber; // Mảng chứa các số


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setOnClickViews();
    }

    public void initWidget() {
        etInput = findViewById(R.id.etInput);
        tvView = findViewById(R.id.tvView);

        btnC = findViewById(R.id.btnC);
        btnResetAll = findViewById(R.id.btnResetAll);
        btnChia = findViewById(R.id.btnChia);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);

        btnDauCham = findViewById(R.id.btnDauCham);
        btnDauBang = findViewById(R.id.btnDauBang);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }

    public void setOnClickViews() {

        btnC.setOnClickListener(this);
        btnResetAll.setOnClickListener(this);
        btnChia.setOnClickListener(this);
        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);

        btnDauCham.setOnClickListener(this);
        btnDauBang.setOnClickListener(this);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                etInput.append("0");
                break;
            case R.id.btn1:
                etInput.append("1");
                break;
            case R.id.btn2:
                etInput.append("2");
                break;
            case R.id.btn3:
                etInput.append("3");
                break;
            case R.id.btn4:
                etInput.append("4");
                break;
            case R.id.btn5:
                etInput.append("5");
                break;
            case R.id.btn6:
                etInput.append("6");
                break;
            case R.id.btn7:
                etInput.append("7");
                break;
            case R.id.btn8:
                etInput.append("8");
                break;
            case R.id.btn9:
                etInput.append("9");
                break;
            case R.id.btnC:
//                String numberAfterDelete = deleteNumber(etInput.getText().toString());
//                etInput.setText(numberAfterDelete);

                // Gọi lệnh Backspace như trên bàn phím máy tính
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(etInput, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btnResetAll:
                etInput.setText("");
                tvView.setText("");
                break;
            case R.id.btnChia:
                etInput.append("/");
                break;
            case R.id.btnCong:
                etInput.append("+");
                break;
            case R.id.btnTru:
                etInput.append("-");
                break;
            case R.id.btnNhan:
                etInput.append("*");
                break;
            case R.id.btnDauBang:
//                double result = Double.parseDouble(etInput.getText().toString());
//                tvView.setText(result + "");
                addOperation(etInput.getText().toString());
                addNumber(etInput.getText().toString());
                DecimalFormat df = new DecimalFormat("#,###,###.##");
                double resultKQ = 0;

                if (arrOperation.size() >= arrNumber.size() || arrOperation.size() < 1) {
                    resultKQ=arrNumber.get(0);
                    tvView.setText(df.format(resultKQ));
                } else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    resultKQ = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    resultKQ = resultKQ + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    resultKQ = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    resultKQ = resultKQ - arrNumber.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    resultKQ = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    resultKQ = resultKQ * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    resultKQ = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    resultKQ = resultKQ / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    tvView.setText(df.format(resultKQ));
                }
                break;


            case R.id.btnDauCham:
                etInput.append(".");
                break;

        }
    }

    //    public String deleteNumber(String number) {
//        int length = number.length();
//        String temp = number.substring(0, length - 1);
//        return temp;
//    }

    //Hàm lấy các phép tính lưu vào 1 MẢNG
    public int addOperation(String phepTinh) {
        arrOperation = new ArrayList<>();
        char[] charPhepTinh = phepTinh.toCharArray(); // Lấy từng ký tự của phép tính vừa nhập
        for (int i = 0; i < charPhepTinh.length; i++) {
            switch (charPhepTinh[i]) {
                case '+':
                    arrOperation.add(charPhepTinh[i] + "");
                    break;
                case '-':
                    arrOperation.add(charPhepTinh[i] + "");
                    break;
                case '*':
                    arrOperation.add(charPhepTinh[i] + "");
                    break;
                case '/':
                    arrOperation.add(charPhepTinh[i] + "");
                    break;
                default:
                    break;
            }

        }
        return 0;
    }
    //Hàm lấy các số không lấy các dấu của phép tính

    public void addNumber(String ipphepTinh) {
        arrNumber = new ArrayList<>();
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = p.matcher(ipphepTinh);
        while (matcher.find()) {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }

    }

}



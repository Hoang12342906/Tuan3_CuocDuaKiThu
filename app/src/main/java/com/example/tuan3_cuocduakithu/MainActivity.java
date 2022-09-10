package com.example.tuan3_cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    ImageButton ibtPlay;
    int diem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        txtDiem.setText(diem + "");

        CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                //kiểm tra win
                if (skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    ibtPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if(cbOne.isChecked()){
                        diem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán đúng!", Toast.LENGTH_SHORT).show();
                    }else{
                        diem -=5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai!", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(diem + "");
                    EnableCheckBox();
                }
                if (skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();

                    if(cbTwo.isChecked()){
                        diem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán đúng!", Toast.LENGTH_SHORT).show();
                    }else{
                        diem -=5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai!", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(diem + "");
                    EnableCheckBox();
                }

                if (skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();

                    if(cbThree.isChecked()){
                        diem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán đúng!", Toast.LENGTH_SHORT).show();
                    }else{
                        diem -=5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai!", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(diem + "");
                }

                skOne.setProgress(skOne.getProgress() + one);
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };
        ibtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);

                    ibtPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    DisableCheckbox();
                }else {
                    Toast.makeText(MainActivity.this, "Xin hãy đặt cược trước khi bắt đầu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    // bỏ check 2 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //bỏ check 1 3
                cbOne.setChecked(false);
                cbThree.setChecked(false);
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //bỏ check 1 2
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
            }
        });
    }

    private  void EnableCheckBox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private  void DisableCheckbox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void AnhXa(){
        txtDiem = (TextView) findViewById(R.id.textviewDiemSo);
        ibtPlay = (ImageButton) findViewById(R.id.buttonPlay);
        cbOne   = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo   = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);
        skOne   = (SeekBar) findViewById(R.id.seekbarOne);
        skTwo   = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree = (SeekBar) findViewById(R.id.seekbarThree);
    }
}
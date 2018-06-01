package com.example.ds.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText join_id,join_pw,join_email,edit_num;
    Button check_btn,join_btn;
    int num;
    String [] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        mAuth=FirebaseAuth.getInstance();
        join_id=(EditText)findViewById(R.id.join_id);
        join_pw=(EditText)findViewById(R.id.join_pw);
        join_email=(EditText)findViewById(R.id.join_email);
        join_btn=(Button)findViewById(R.id.join_btn);
        check_btn=(Button)findViewById(R.id.check_btn);
        edit_num=(EditText)findViewById(R.id.edit_num);


    }

    private void createUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"회원가입에 성공했습니다.",Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"회원가입에 실패했습니다.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void join(View view) {
        createUser(join_id.getText().toString(),join_pw.getText().toString());
    }

    public void sendMail(View view) {
        num=(int)(Math.random()*9999);
        arr=join_email.getText().toString().split("@");
        if(arr[1].equals("duksung.ac.kr")) {
            GMailSender sender = new GMailSender("projectd1888@gmail.com", "wltjsrla456"); // SUBSTITUTE HERE
            try {
                sender.sendMail(
                        "덕성여자대학교 학생 인증 메일입니다.",
                        "인증번호:" + num,
                        "projectd1888@gmail.com",
                        arr[0] +"@duksung.ac.kr"
                );
                Toast.makeText(getApplicationContext(), "메일을 보냈습니다.", Toast.LENGTH_LONG).show();
                check_btn.setEnabled(true);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"메일형식이 다릅니다.",Toast.LENGTH_LONG).show();
        }
    }

    public void check(View view) {
        String inputNum=edit_num.getText().toString();
        if(inputNum.equals(num+"")){
            Toast.makeText(getApplicationContext(),"인증 성공했습니다.",Toast.LENGTH_LONG).show();
            join_btn.setEnabled(true);
        }
        else{
            Toast.makeText(getApplicationContext(),"인증 실패했습니다.",Toast.LENGTH_LONG).show();
        }
    }
}

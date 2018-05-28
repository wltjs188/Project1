package com.example.ds.project;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText join_id;
    EditText join_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        mAuth=FirebaseAuth.getInstance();
        join_id=(EditText)findViewById(R.id.join_id);
        join_pw=(EditText)findViewById(R.id.join_pw);
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
}

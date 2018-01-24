package com.app.diceroid.nerede.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.diceroid.nerede.R;
import com.app.diceroid.nerede.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KidsAddActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private RadioButton timeOne;
    private RadioButton timeFive;
    private RadioButton timeThree;
    private RadioButton timeEight;
    private Button kidsAddButon;
    private EditText editTextName;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_add);



        firebaseAuth = FirebaseAuth.getInstance();
        timeOne = (RadioButton) findViewById(R.id.timeOne);
        timeThree = (RadioButton) findViewById(R.id.timeThree);
        timeFive = (RadioButton) findViewById(R.id.timeFive);
        timeEight = (RadioButton) findViewById(R.id.timeEight);
        kidsAddButon = (Button) findViewById(R.id.kidsAddButon);
        editTextName = (EditText) findViewById(R.id.editTextName);
        textView = (TextView) findViewById(R.id.textView);




        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();





        kidsAddButon.setOnClickListener(this);

    }

    private void kidsAdd(){
        String name = editTextName.getText().toString().trim();
        UserInformation userInformation = new UserInformation(name);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Kayıt Başarılı",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

      if(view == kidsAddButon){
          kidsAdd();
      }
    }
}

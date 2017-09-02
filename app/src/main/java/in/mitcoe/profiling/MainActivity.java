package in.mitcoe.profiling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static in.mitcoe.profiling.ProfileEntry.dataPresentFlag;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button profileBtn, viewProfilesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileBtn = (Button)findViewById(R.id.profileInfo);
        profileBtn.setOnClickListener(this);

        viewProfilesBtn = (Button) findViewById(R.id.viewProfiles);
        viewProfilesBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.profileInfo:
                startActivity(new Intent(MainActivity.this,ProfileEntry.class));
                break;
            case R.id.viewProfiles:
                if(dataPresentFlag == 1){

                }
                else{
                    Toast.makeText(MainActivity.this,"Enter Profile Data First!",Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}

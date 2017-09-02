package in.mitcoe.profiling;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Pat on 08/31/2017.
 */



public class ProfileEntry extends AppCompatActivity {

    databaseHelper peopleDB;
    Button btnAddData,btnViewData,btnUpdateData; //btnDeleteData;
    EditText etName,etEmail,etLocality,etMobile; //etID;
    static int dataPresentFlag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        peopleDB = new databaseHelper(this);
        btnAddData = (Button)findViewById(R.id.btnAddData);
        etName = (EditText)findViewById(R.id.etNewName);
        etEmail = (EditText)findViewById(R.id.etNewEmail);
        etLocality = (EditText)findViewById(R.id.etNewLocality);
        etMobile = (EditText) findViewById(R.id.etNewMobile);

        btnViewData = (Button)findViewById(R.id.btnViewData);
        btnUpdateData = (Button)findViewById(R.id.btnID);
        // etID = (EditText)findViewById(R.id.etID);
        // btnDeleteData = (Button)findViewById(R.id.btnDelete);
        AddData();
        ViewData();
        UpdateData();
        // DeleteData();

    }

    public void AddData()
    {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String locality = etLocality.getText().toString();
                String mobile = etMobile.getText().toString();

                boolean insertData = peopleDB.addData(name,email,locality,mobile);
                if(insertData)
                {
                    dataPresentFlag = 1;
                    Toast.makeText(ProfileEntry.this,"Data Entered Successfully",Toast.LENGTH_LONG).show();
                    // etID.setText("");
                    etName.setText("");
                    etEmail.setText("");
                    etLocality.setText("");
                    etMobile.setText("");
                }
                else
                {
                    Toast.makeText(ProfileEntry.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewData()
    {
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = peopleDB.showData();

                if(data.getCount() == 0)
                {
                    //message
                    display("Error","No data found");
                }
                StringBuffer buffer = new StringBuffer();
                while(data.moveToNext())
                {
                    buffer.append("ID: " + "001" + "\n");
                    buffer.append("NAME: " + data.getString(1) + "\n");
                    buffer.append("EMAIL: " + data.getString(2) + "\n");
                    buffer.append("CITY: " + data.getString(3) + "\n");
                    buffer.append("MOBILE: " + data.getString(4) + "\n");
                    //display("All Stored Data: ",buffer.toString());
                }
                display("All Stored Data: ",buffer.toString());

            }
        });
    }

    public void display(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void UpdateData()
    {
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean update = peopleDB.updateData("001",etName.getText().toString(),etEmail.getText().toString(),etLocality.getText().toString(), etMobile.getText().toString());
                if(update==true)
                {
                    Toast.makeText(ProfileEntry.this,"Successfully updated data",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ProfileEntry.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

//    public void DeleteData()
//    {
//        btnDeleteData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Integer deleterow = peopleDB.deleteProduct("001");
//                if(deleterow>0)
//                {
//                    Toast.makeText(ProfileEntry.this,"Successfully deleted data!",Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    Toast.makeText(ProfileEntry.this,"Something went wrong!",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//
//    }
}

package com.anik.sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etxtID,etxtName,etxtEmail;
    Button btnInsert,btnView,btnUpdate,btnDelete;

    SqliteDB mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtID=findViewById(R.id.etxt_id);
        etxtName=findViewById(R.id.etxt_name);
        etxtEmail=findViewById(R.id.etxt_email);

        btnInsert=findViewById(R.id.btn_insert);


        mydb=new SqliteDB(MainActivity.this);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=etxtID.getText().toString();
                String name=etxtName.getText().toString();
                String email=etxtEmail.getText().toString();

                if (id.isEmpty())
                {
                    etxtID.setError("oiii id input deee!");
                    etxtID.requestFocus();
                }

              else  if (name.isEmpty())
                {
                    etxtName.setError("oiii name input deee!");
                    etxtName.requestFocus();
                }

               else if (email.isEmpty())
                {
                    etxtEmail.setError("oiii email input deee!");
                    etxtEmail.requestFocus();
                }


                else
                {
                    boolean check=mydb.insertData(id,name,email);

                    if (check==true)
                    {
                        Toast.makeText(MainActivity.this, "Data insert successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Data not insertet.Try again!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }


    //for data
    public void viewData(View v)
    {
        Cursor result=mydb.display();
        if(result.getCount()==0)
        {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }

        else
        {
            StringBuffer buffer=new StringBuffer();
            result.moveToFirst();

            do {

                buffer.append("ID: "+result.getString(0)+"\n");
                buffer.append("Name: "+result.getString(1)+"\n");
                buffer.append("Email: "+result.getString(2)+"\n");

            }while (result.moveToNext());

            showData("Information",buffer.toString());
        }


    }



    public void showData(String title,String data)
    {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage(data);
        dialog.show();
    }



    //for update info
    public void updateData(View v)
    {
        String id=etxtID.getText().toString();
        String name=etxtName.getText().toString();
        String email=etxtEmail.getText().toString();

        if (id.isEmpty())
        {
            etxtID.setError("oiii id input deee!");
            etxtID.requestFocus();
        }

        else  if (name.isEmpty())
        {
            etxtName.setError("oiii name input deee!");
            etxtName.requestFocus();
        }

        else if (email.isEmpty())
        {
            etxtEmail.setError("oiii email input deee!");
            etxtEmail.requestFocus();
        }


        else
        {
            boolean check=mydb.updateData(id,name,email);

            if (check==true)
            {
                Toast.makeText(MainActivity.this, "Data update successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Data not update.Try again!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //for deleting
    public void deleteData(View v)
    {
        String id=etxtID.getText().toString();
        int check=mydb.deleteData(id);
        if(check==1)
        {
            Toast.makeText(this, "Data Deleted Sucessfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Data not deleted", Toast.LENGTH_SHORT).show();
        }
    }

}

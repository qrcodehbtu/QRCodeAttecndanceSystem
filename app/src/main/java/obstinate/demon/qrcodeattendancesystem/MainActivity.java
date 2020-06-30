package obstinate.demon.qrcodeattendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button b;
    private EditText editname;
    DatabaseReference dref;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref = FirebaseDatabase.getInstance().getReference();
        editname = (EditText) findViewById(R.id.editName);
        iv = (ImageView) findViewById(R.id.qrView);
        b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String name = editname.getText().toString();
                dref.child("Name").push().setValue(name);
                QRCodeGen qrgen= new QRCodeGen(200, 200);
                Bitmap bt = qrgen.generateQRCode(editname.getText().toString());
                iv.setImageBitmap(bt);
                Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
            }
        });
    }
}
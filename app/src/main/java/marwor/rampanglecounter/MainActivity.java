package marwor.rampanglecounter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView HeightGetText;
    private TextView LenghtGetText;
    private TextView AngleGetText;
    private TextView PercentGetText;
    private Button CountButton;
    private Button ResetButton;
    private String lenght;
    private String height;
    private String angle;
    private String percent;
    private CountingClass countingClass=new CountingClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HeightGetText =(TextView)findViewById(R.id.HeightGetText);
        LenghtGetText=(TextView)findViewById(R.id.LenghtGetText);
        AngleGetText=(TextView)findViewById(R.id.AngleGetText);
        PercentGetText=(TextView)findViewById(R.id.PercentGetText);
        CountButton=(Button)findViewById(R.id.CountButton);
        ResetButton=(Button)findViewById(R.id.ResetButton);

        CountButton.setOnClickListener(this);
        ResetButton.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CountButton:
                double convert;
                height= HeightGetText.getText().toString();
                lenght=LenghtGetText.getText().toString();
                angle=AngleGetText.getText().toString();
                percent=PercentGetText.getText().toString();
                countingClass.CoutingClass(height,lenght,angle,percent);
                if(countingClass.EnoughData()==false){
                    ErrorDialog errorDialog=new ErrorDialog();
                    errorDialog.show(getFragmentManager(),"123");
                }else{
                    convert=countingClass.CountingHeight();
                    HeightGetText.setText(Double.toString(convert));
                    convert=countingClass.CountingLength();
                    LenghtGetText.setText(Double.toString(convert));
                    convert=countingClass.CountingAngle();
                    AngleGetText.setText(Double.toString(convert));
                    convert=countingClass.CountingPercent();
                    PercentGetText.setText(Double.toString(convert));
                }
                break;
            case R.id.ResetButton:
                AngleGetText.setText("");
                HeightGetText.setText("");
                LenghtGetText.setText("");
                PercentGetText.setText("");
                break;

        }
    }
    private class  ErrorDialog extends DialogFragment{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder errorDialog;
            errorDialog = new AlertDialog.Builder(getActivity());
            errorDialog.setMessage("We need more data").setPositiveButton("ok",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            return errorDialog.create();
        }

    }
}

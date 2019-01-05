package mobile.gaco.com.gaco;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEmail, txtPassword;
    private Button btnLogin, btnRegis, btnLogout;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegis = findViewById(R.id.btnRegis);
        btnRegis.setOnClickListener(this);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            defaultUI();
        }else{
            updateUI();
        }

    }

    private void defaultUI(){
        btnLogout.setEnabled(false);
        btnRegis.setEnabled(false);
        btnLogin.setEnabled(true);
        txtEmail.setEnabled(false);
        txtPassword.setEnabled(false);
    }

    private void updateUI() {
        btnLogout.setEnabled(true);
        btnLogin.setEnabled(true);
        btnRegis.setEnabled(false);
        txtEmail.setEnabled(true);
        txtPassword.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                updateUI();
            }else{
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Login Dibatalkan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                startActivityForResult(AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()))
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN);

                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(LoginActivity.this, BerandaActivity.class));
                break;
        }

    }
}

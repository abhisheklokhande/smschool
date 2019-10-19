package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText textmail, textpassword ;
    Button btnlogin;

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;

    private Button mFacebookBtn;
    private Button signin;
    private Button signup;
    private Button forget;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;


    private static final String TAG = "FACELOG" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textmail = findViewById(R.id.editText);
        textpassword = findViewById(R.id.editText2);
        btnlogin = findViewById(R.id.button4);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = textmail.getText().toString().trim();
                String password = textpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6) {
                    Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();

                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    //startActivity(new Intent(getApplicationContext(),dashboard.class));
                                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    checkEmailVerification();


                                } else {

                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });

        signup = (Button) findViewById(R.id.button5);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
            }
        });

        forget = (Button) findViewById(R.id.button6);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, forgetpassword.class);
                startActivity(intent);
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mFacebookBtn = (Button) findViewById(R.id.button);

        signin = (Button) findViewById(R.id.button3);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button3:
                        signIn();
                        break;
                }

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();

        mFacebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFacebookBtn.setEnabled(false);

                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null) {
        updateUI();
        }
    }

    private void updateUI() {

        Toast.makeText(MainActivity.this, "you are logged in" , Toast.LENGTH_LONG).show();
        Intent dashboardIntent = new Intent(MainActivity.this, dashboard.class);
        startActivity(dashboardIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mFacebookBtn.setEnabled(true);
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            mFacebookBtn.setEnabled(true);
                            updateUI();
                        }

                        // ...
                    }
                });
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.button3), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }


    private void checkEmailVerification() {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailfalg = firebaseUser.isEmailVerified();

        if(emailfalg) {
            startActivity(new Intent(MainActivity.this, dashboard.class));
        }
        else {
            Toast.makeText(this,"verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }

    }


}

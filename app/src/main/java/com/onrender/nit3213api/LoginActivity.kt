package com.onrender.nit3213api

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val vm: LoginViewModel by viewModels()

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        setContentView(R.layout.activity_login)

        val etUser = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val spinner = findViewById<Spinner>(R.id.spLocation)
        val btn = findViewById<Button>(R.id.btnLogin)
        val prog = findViewById<ProgressBar>(R.id.progress)
        val tvError = findViewById<TextView>(R.id.tvError)

        // setup spinner items ["footscray","sydney","br"]
        btn.setOnClickListener {
            val username = etUser.text.toString().trim()
            val password = etPass.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                tvError.text = "Please enter your first name and student ID."
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            val loc = spinner.selectedItem.toString()
            vm.login(loc, etUser.text.toString(), etPass.text.toString())
        }

        lifecycleScope.launchWhenStarted {
            vm.uiState.collect { state ->
                when (state) {
                    is LoginUiState.Loading -> {
                        btnLogin.isEnabled = false
                        prog.visibility = View.VISIBLE
                        tvError.visibility = GONE }
                    is LoginUiState.Success -> {
                        btnLogin.isEnabled = true
                        prog.visibility = GONE
                        // start DashboardActivity with keypass
                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                        intent.putExtra("keypass", state.keypass)
                        startActivity(intent)
                        finish()
                    }
                    is LoginUiState.Error -> {
                        btnLogin.isEnabled = true
                        prog.visibility = GONE
                        tvError.text = state.message
                        tvError.visibility = VISIBLE
                    }
                    else -> {}
                }
            }
        }
    }
}

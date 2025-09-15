package com.onrender.nit3213api

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private val vm: DashboardViewModel by viewModels()
    private lateinit var adapter: EntitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val keypass = intent.getStringExtra("keypass") ?: ""
        adapter = EntitiesAdapter { entity ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("entity", entity) // Parcelable
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.rvEntities).adapter = adapter
        findViewById<RecyclerView>(R.id.rvEntities).layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenStarted {
            vm.uiState.collect { state ->
                when (state) {
                    is DashboardUiState.Loading -> { /* show loader */ }
                    is DashboardUiState.Success -> {
                        adapter.submitList(state.data.entities)
                        findViewById<TextView>(R.id.tvCount).text = "Total: ${state.data.entityTotal}"
                    }
                    is DashboardUiState.Error -> { /* show error */ }
                }
            }
        }

        vm.loadDashboard(keypass)
    }
}
